package com.example.start.service.impl;

import com.example.start.mapper.UserMapper;
import com.example.start.model.User;
import com.example.start.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xueyn on 2018/4/20.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired UserMapper userMapper;

    @Override
    public List<User> findByPager(User user,int page, int row) {
        if(page>0){
            PageHelper.startPage(page,row);
        }
        return userMapper.find(user);
    }

    @Override
    public User findOne(Integer id) {
        return null;
    }

    @Override
    public int add(User entity) {
        return 0;
    }

    @Override
    public int update(User entity) {
        return 0;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }
}
