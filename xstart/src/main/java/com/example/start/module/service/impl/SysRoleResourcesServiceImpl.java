package com.example.start.module.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.start.common.base.Pager;
import com.example.start.module.entity.SysRoleResources;
import com.example.start.common.exception.ServiceException;
import com.example.start.module.dao.SysRoleResourcesMapper;
import com.example.start.module.service.SysRoleResourcesService;



@Component
public class SysRoleResourcesServiceImpl implements SysRoleResourcesService {
	
	@Autowired
    private SysRoleResourcesMapper sysRoleResourcesMapper;

    @Override
    public Pager<SysRoleResources> findByPager(Pager<SysRoleResources> pager) throws ServiceException {

        return pager.pagingQuery(new Pager.Callback<SysRoleResources>() {
            @Override
            public List<SysRoleResources> query(SysRoleResources entity) throws ServiceException {
                return sysRoleResourcesMapper.find(entity);
            }
        });
    }
    
    @Override
    public SysRoleResources findOne(Long id) throws ServiceException {
    	try{
    		 return sysRoleResourcesMapper.findByPK(id);
    	}catch(Exception e){
    		throw new ServiceException("[Query has error]", e);
    	}       
    }

    @Override
    public int add(SysRoleResources entity) throws ServiceException {
    	try{
    		return sysRoleResourcesMapper.insertSelective(entity);
    	}catch(Exception e){
    		throw new ServiceException("[Save has error]", e);
    	}       
    }

 	@Override
    public int update(SysRoleResources entity) throws ServiceException {
    	try{
    		return sysRoleResourcesMapper.updateByPkSelective(entity);
	    }catch(Exception e){
			throw new ServiceException("[Update has error]", e);
		}       
    }   

    @Override
    public int delete(Long id) throws ServiceException {
        return sysRoleResourcesMapper.deleteByPK(id);
    }
}
