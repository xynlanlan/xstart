package com.example.start.module.service;

import com.example.start.common.exception.ServiceException;
import com.example.start.module.entity.SysResources;

import java.util.List;

public interface SysResourcesService {
	

    List<SysResources> findList(SysResources entity) throws ServiceException;
    
    
    SysResources findOne(Long id) throws ServiceException;
    

    int add(SysResources entity) throws ServiceException;
    
  
    int update(SysResources entity) throws ServiceException;  

 
    int delete(Long id) throws ServiceException;

    int disabled(Long id, Integer status) throws ServiceException;
}
