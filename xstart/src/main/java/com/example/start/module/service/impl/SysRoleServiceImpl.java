package com.example.start.module.service.impl;

import java.util.List;

import com.example.start.common.exception.ExceptionCode;
import com.example.start.common.utils.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.start.common.base.Pager;
import com.example.start.module.entity.SysRole;
import com.example.start.common.exception.ServiceException;
import com.example.start.module.dao.SysRoleMapper;
import com.example.start.module.service.SysRoleService;
import org.springframework.transaction.annotation.Transactional;


@Component
public class SysRoleServiceImpl implements SysRoleService {
	
	@Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public Pager<SysRole> findByPager(Pager<SysRole> pager) throws ServiceException {

        return pager.pagingQuery(new Pager.Callback<SysRole>() {
            @Override
            public List<SysRole> query(SysRole entity) throws ServiceException {
                return sysRoleMapper.find(entity);
            }
        });
    }
    
    @Override
    public SysRole findOne(Long id) throws ServiceException {
    	try{
    		 return sysRoleMapper.findByPK(id);
    	}catch(Exception e){
    		throw new ServiceException("[Query has error]", e);
    	}       
    }

    @Override
    @Transactional
    public int add(SysRole entity) throws ServiceException {
    	try{
            int count = sysRoleMapper.findRoleNameCount(0L,entity.getRoleName());
            if(count>0){
                throw new ServiceException(ExceptionCode.FREQUENCY_ERROR.getCode(),"角色名不能重复，请重新输入！");
            }
            EntityUtils.createEntity(entity);
    		return sysRoleMapper.insertSelective(entity);
    	}catch(Exception e){
    		throw new ServiceException("[Save has error]", e);
    	}       
    }

 	@Override
    @Transactional
    public int update(SysRole entity) throws ServiceException {
    	try{
            int count = sysRoleMapper.findRoleNameCount(entity.getId(),entity.getRoleName());
            if(count>0){
                throw new ServiceException(ExceptionCode.FREQUENCY_ERROR.getCode(),"角色名不能重复，请重新输入！");
            }
            EntityUtils.updateEntity(entity);
    		return sysRoleMapper.updateByPkSelective(entity);
	    }catch(Exception e){
			throw new ServiceException("[Update has error]", e);
		}       
    }   

    @Override
    @Transactional
    public int delete(Long id) throws ServiceException {
        return sysRoleMapper.deleteByPK(id);
    }

    @Override
    @Transactional
    public int disabled(Long id, Integer status) throws ServiceException {
        SysRole entity = new SysRole();
        entity.setId(id);
        entity.setDisabled(status==1);
        return update(entity);
    }

    @Override
    @Transactional
    public int batchDelete(List<Long> ids) throws ServiceException {
        try{
            return sysRoleMapper.batchDelete(ids);
        }catch(Exception e){
            throw new ServiceException("[Delete has error]", e);
        }
    }
}
