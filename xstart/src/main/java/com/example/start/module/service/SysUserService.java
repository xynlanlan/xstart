package com.example.start.module.service;

import com.example.start.common.base.Pager;
import com.example.start.module.entity.SysUser;
import com.example.start.common.exception.ServiceException;

import java.util.List;

public interface SysUserService {
	

    Pager<SysUser> findByPager(Pager<SysUser> pager) throws ServiceException;
    
    
    SysUser findOne(Long id) throws ServiceException;
    

    int add(SysUser entity) throws ServiceException;
    
  
    int update(SysUser entity) throws ServiceException;  

 
    int delete(Long id) throws ServiceException;

    int disabled(Long id, Integer status) throws ServiceException;

    SysUser findByAccount(String account) throws ServiceException;

    int batchDelete(List<Long> ids) throws ServiceException;
}
