package com.example.start.module.service;

import com.example.start.common.base.Pager;
import com.example.start.module.entity.SysRole;
import com.example.start.common.exception.ServiceException;

import java.util.List;

public interface SysRoleService {
	

    Pager<SysRole> findByPager(Pager<SysRole> pager) throws ServiceException;
    
    
    SysRole findOne(Long id) throws ServiceException;
    

    int add(SysRole entity) throws ServiceException;
    
  
    int update(SysRole entity) throws ServiceException;  

 
    int delete(Long id) throws ServiceException;

    int disabled(Long id, Integer status) throws ServiceException;

    int batchDelete(List<Long> ids) throws ServiceException;
}
