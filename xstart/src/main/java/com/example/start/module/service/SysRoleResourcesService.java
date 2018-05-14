package com.example.start.module.service;

import com.example.start.common.base.Pager;
import com.example.start.module.entity.SysRoleResources;
import com.example.start.common.exception.ServiceException;

public interface SysRoleResourcesService {
	

    Pager<SysRoleResources> findByPager(Pager<SysRoleResources> pager) throws ServiceException;
    
    
    SysRoleResources findOne(Long id) throws ServiceException;
    

    int add(SysRoleResources entity) throws ServiceException;
    
  
    int update(SysRoleResources entity) throws ServiceException;  

 
    int delete(Long id) throws ServiceException;

}
