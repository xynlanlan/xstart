package com.example.start.module.service.impl;

import com.example.start.common.base.Pager;
import com.example.start.common.exception.ExceptionCode;
import com.example.start.common.exception.ServiceException;
import com.example.start.common.utils.EntityUtils;
import com.example.start.module.dao.SysUserMapper;
import com.example.start.module.entity.SysUser;
import com.example.start.module.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional
    public int add(SysUser entity) throws ServiceException {
    	try{
            int count = sysUserMapper.findAccountCount(0L,entity.getLoginAccount());
            if(count>0){
                throw new ServiceException(ExceptionCode.FREQUENCY_ERROR.getCode(),"登录账号不能重复，请重新输入！");
            }
            encryptPassword(entity);
            EntityUtils.createEntity(entity);
    		return sysUserMapper.insertSelective(entity);
    	}catch(Exception e){
    		throw new ServiceException("[Save has error]", e);
    	}       
    }
    /**
     * 加密密码
     */
    private void encryptPassword(SysUser user){
        String password = user.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        user.setPassword(password);
    }
 	@Override
    @Transactional
    public int update(SysUser entity) throws ServiceException {
    	try{
            int count = sysUserMapper.findAccountCount(entity.getId(),entity.getLoginAccount());
            if(count>0){
                throw new ServiceException(ExceptionCode.FREQUENCY_ERROR.getCode(),"登录账号不能重复，请重新输入！");
            }
            EntityUtils.updateEntity(entity);
    		return sysUserMapper.updateByPkSelective(entity);
	    }catch(Exception e){
			throw new ServiceException("[Update has error]", e);
		}       
    }   

    @Override
    @Transactional
    public int delete(Long id) throws ServiceException {
        return sysUserMapper.deleteByPK(id);
    }

    @Override
    @Transactional
    public int disabled(Long id, Integer status) throws ServiceException {
        SysUser entity = new SysUser();
        entity.setId(id);
        entity.setDisable(status==1);
        return update(entity);
    }

    @Override
    @Transactional
    public SysUser findByAccount(String account) throws ServiceException {
        try{
            return sysUserMapper.findByAccount(account);
        }catch(Exception e){
            throw new ServiceException("[Query has error]", e);
        }
    }

    @Override
    @Transactional
    public int batchDelete(List<Long> ids) throws ServiceException {
        try{
            return sysUserMapper.batchDelete(ids);
        }catch(Exception e){
            throw new ServiceException("[Delete has error]", e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = null;
        try {
            user = sysUserMapper.findByAccount(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码不正确!");
        }
        return new User(user.getUserName(), user.getPassword(), emptyList());
    }
}
