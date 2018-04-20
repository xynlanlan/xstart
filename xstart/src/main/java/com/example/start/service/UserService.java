package com.example.start.service;

import com.example.start.model.User;

import java.util.List;

/**
 * Created by xueyn on 2018/4/20.
 */
public interface UserService {
    List<User> findByPager(User user,int page,int row);

    User findOne(Integer id);


    int add(User entity);


    int update(User entity);


    int delete(Integer id);
}
