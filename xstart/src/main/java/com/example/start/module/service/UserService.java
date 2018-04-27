package com.example.start.module.service;

import com.example.start.common.base.Pager;
import com.example.start.common.exception.ServiceException;
import com.example.start.module.model.User;

public interface UserService {
    Pager<User> findByPager(Pager<User> pager) throws ServiceException;

    User findOne(Long id) throws ServiceException;


    int add(User entity) throws ServiceException;


    int update(User entity) throws ServiceException;


    int delete(Long id) throws ServiceException;
}
