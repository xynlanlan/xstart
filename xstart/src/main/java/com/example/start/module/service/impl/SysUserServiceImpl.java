package com.example.start.module.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.start.common.base.Pager;
import com.example.start.module.entity.SysUser;
import com.example.start.common.exception.ServiceException;
import com.example.start.module.dao.SysUserMapper;
import com.example.start.module.service.SysUserService;
import static java.util.Collections.emptyList;


@Component
public class SysUserServiceImpl implements UserDetailsService, SysUserService {
	
	@Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Pager<SysUser> findByPager(Pager<SysUser> pager) throws ServiceException {

        return pager.pagingQuery(new Pager.Callback<SysUser>() {
            @Override
            public List<SysUser> query(SysUser entity) throws ServiceException {
                List<SysUser> list = new ArrayList<>();
                return list;
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

    @Override
    public int disabled(Long id, Integer status) throws ServiceException {
        SysUser entity = new SysUser();
        entity.setId(id);
        entity.setDisable(status==1);
        return update(entity);
    }

    @Override
    public SysUser findByNameAndPassword(SysUser user) throws ServiceException {
        try{
            return sysUserMapper.findByNameAndPassword(user);
        }catch(Exception e){
            throw new ServiceException("[Query has error]", e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new User("admin", "9a934152433a974a6a0a2e6b26c6af82", emptyList());
    }
}
