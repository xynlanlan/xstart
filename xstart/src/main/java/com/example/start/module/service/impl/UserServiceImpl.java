package com.example.start.module.service.impl;

import com.example.start.common.base.Pager;
import com.example.start.common.exception.ServiceException;
import com.example.start.module.dao.UserMapper;
import com.example.start.module.model.User;
import com.example.start.module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Pager<User> findByPager(Pager<User> pager) throws ServiceException {
        return pager.pagingQuery(new Pager.Callback<User>() {
            @Override
            public List<User> query(User user) throws ServiceException {
                return userMapper.find(user);
            }
        });
    }

    @Override
    public User findOne(Long id) throws ServiceException {
        try{
            return userMapper.findByPK(id);
        }catch(Exception e){
            throw new ServiceException("[Query has error]", e);
        }
    }

    @Override
    public int add(User entity) throws ServiceException {
        try{
            return userMapper.insertSelective(entity);
        }catch(Exception e){
            throw new ServiceException("[add has error]", e);
        }
    }

    @Override
    public int update(User entity) throws ServiceException {
        try{
            return userMapper.updateByPkSelective(entity);
        }catch(Exception e){
            throw new ServiceException("[update has error]", e);
        }
    }

    @Override
    public int delete(Long id) throws ServiceException {
        try{
            return userMapper.deleteByPK(id);
        }catch(Exception e){
            throw new ServiceException("[delete has error]", e);
        }
    }
}
