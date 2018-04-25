package com.example.start.module.user.service.impl;

import com.example.start.module.user.dao.UserMapper;
import com.example.start.module.user.model.User;
import com.example.start.module.user.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findByPager(User user, int page, int row) {
        if(page>0){
            PageHelper.startPage(page, row);
            List<User> list = userMapper.find(user);
            return list;
        }else{
            return new ArrayList<User>();
        }
    }

    @Override
    public User findOne(Long id) {
        return userMapper.findByPK(id);
    }

    @Override
    public int add(User entity) {
        return userMapper.insertSelective(entity);
    }

    @Override
    public int update(User entity) {
        return userMapper.updateByPkSelective(entity);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteByPK(id);
    }
}
