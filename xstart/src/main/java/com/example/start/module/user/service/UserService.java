package com.example.start.module.user.service;

import com.example.start.module.user.model.User;

import java.util.List;

public interface UserService {
    List<User> findByPager(User user, int page, int row);

    User findOne(Long id);


    int add(User entity);


    int update(User entity);


    int delete(Long id);
}
