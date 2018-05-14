package com.example.start.module.service;

import com.example.start.common.base.Pager;
import com.example.start.module.entity.SysUserRole;
import com.example.start.common.exception.ServiceException;

public interface SysUserRoleService {
	

    Pager<SysUserRole> findByPager(Pager<SysUserRole> pager) throws ServiceException;
    
    
    SysUserRole findOne(Long id) throws ServiceException;
    

    int add(SysUserRole entity) throws ServiceException;
    
  
    int update(SysUserRole entity) throws ServiceException;  

 
    int delete(Long id) throws ServiceException;

}
