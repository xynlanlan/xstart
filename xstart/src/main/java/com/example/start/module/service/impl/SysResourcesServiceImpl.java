package com.example.start.module.service.impl;

import com.example.start.common.exception.ServiceException;
import com.example.start.module.dao.SysResourcesMapper;
import com.example.start.module.entity.SysResources;
import com.example.start.module.service.SysResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public class SysResourcesServiceImpl implements SysResourcesService {
	
	@Autowired
    private SysResourcesMapper sysResourcesMapper;

    @Override
    public List<SysResources> findList(SysResources entity) throws ServiceException {

        return sysResourcesMapper.find(entity);
    }
    
    @Override
    public SysResources findOne(Long id) throws ServiceException {
    	try{
    		 return sysResourcesMapper.findByPK(id);
    	}catch(Exception e){
    		throw new ServiceException("[Query has error]", e);
    	}       
    }

    @Override
    public int add(SysResources entity) throws ServiceException {
    	try{
    		return sysResourcesMapper.insertSelective(entity);
    	}catch(Exception e){
    		throw new ServiceException("[Save has error]", e);
    	}       
    }

 	@Override
    public int update(SysResources entity) throws ServiceException {
    	try{
    		return sysResourcesMapper.updateByPkSelective(entity);
	    }catch(Exception e){
			throw new ServiceException("[Update has error]", e);
		}       
    }   

    @Override
    public int delete(Long id) throws ServiceException {
        return sysResourcesMapper.deleteByPK(id);
    }

    @Override
    public int disabled(Long id, Integer status) throws ServiceException {
        SysResources entity = new SysResources();
        entity.setId(id);
        entity.setDisabled(status==1);
        return update(entity);
    }
}
