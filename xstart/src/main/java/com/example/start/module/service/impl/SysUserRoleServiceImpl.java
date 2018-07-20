package com.example.start.module.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.start.common.base.Pager;
import com.example.start.module.entity.SysUserRole;
import com.example.start.common.exception.ServiceException;
import com.example.start.module.dao.SysUserRoleMapper;
import com.example.start.module.service.SysUserRoleService;
import org.springframework.transaction.annotation.Transactional;


@Component
public class SysUserRoleServiceImpl implements SysUserRoleService {
	
	@Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Pager<SysUserRole> findByPager(Pager<SysUserRole> pager) throws ServiceException {

        return pager.pagingQuery(new Pager.Callback<SysUserRole>() {
            @Override
            public List<SysUserRole> query(SysUserRole entity) throws ServiceException {
                return sysUserRoleMapper.find(entity);
            }
        });
    }
    
    @Override
    public SysUserRole findOne(Long id) throws ServiceException {
    	try{
    		 return sysUserRoleMapper.findByPK(id);
    	}catch(Exception e){
    		throw new ServiceException("[Query has error]", e);
    	}       
    }

    @Override
    @Transactional
    public int add(SysUserRole entity) throws ServiceException {
    	try{
    		return sysUserRoleMapper.insertSelective(entity);
    	}catch(Exception e){
    		throw new ServiceException("[Save has error]", e);
    	}       
    }

 	@Override
    @Transactional
    public int update(SysUserRole entity) throws ServiceException {
    	try{
    		return sysUserRoleMapper.updateByPkSelective(entity);
	    }catch(Exception e){
			throw new ServiceException("[Update has error]", e);
		}       
    }   

    @Override
    @Transactional
    public int delete(Long id) throws ServiceException {
        return sysUserRoleMapper.deleteByPK(id);
    }
}
