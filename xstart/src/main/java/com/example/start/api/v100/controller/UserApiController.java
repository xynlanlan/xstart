package com.example.start.api.v100.controller;

import com.example.start.common.base.BaseController;
import com.example.start.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v100/user")
public class UserApiController extends BaseController{
    @Autowired
    public UserService userService;
    @GetMapping("{id}")
    public Map<String, Object> findUserById(@PathVariable("id") Long id){
        return success(userService.findOne(id));
    }
}
