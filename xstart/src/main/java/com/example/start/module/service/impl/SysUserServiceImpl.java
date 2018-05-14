package com.example.start.module.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.start.common.base.Pager;
import com.example.start.module.entity.SysUser;
import com.example.start.common.exception.ServiceException;
import com.example.start.module.dao.SysUserMapper;
import com.example.start.module.service.SysUserService;



@Component
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Pager<SysUser> findByPager(Pager<SysUser> pager) throws ServiceException {

        return pager.pagingQuery(new Pager.Callback<SysUser>() {
            @Override
            public List<SysUser> query(SysUser entity) throws ServiceException {
                return sysUserMapper.find(entity);
            }
        });
    }
    
    @Override
    public SysUser findOne(Long id) throws ServiceException {
    	try{
    		 return sysUserMapper.findByPK(id);
    	}catch(Exception e){
    		throw new ServiceException("[Query has error]", e);
    	}       
    }

    @Override
    public int add(SysUser entity) throws ServiceException {
    	try{
    		return sysUserMapper.insertSelective(entity);
    	}catch(Exception e){
    		throw new ServiceException("[Save has error]", e);
    	}       
    }

 	@Override
    public int update(SysUser entity) throws ServiceException {
    	try{
    		return sysUserMapper.updateByPkSelective(entity);
	    }catch(Exception e){
			throw new ServiceException("[Update has error]", e);
		}       
    }   

    @Override
    public int delete(Long id) throws ServiceException {
        return sysUserMapper.deleteByPK(id);
    }
}
