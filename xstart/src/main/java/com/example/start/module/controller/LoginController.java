package com.example.start.module.controller;

import com.example.start.common.base.BaseController;
import com.example.start.module.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController extends BaseController {

    @Autowired(required=true)
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String tologin() {
        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        return "login";
    }

    //用户登录检测
    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public void  checkLogin(@RequestBody SysUser user) {
        userDetailsService.loadUserByUsername(user.getLoginAccount());
    }

    @RequestMapping(value = "/needLogin",method = RequestMethod.GET)
    public Map<String, Object> needLogin(){

        return failure(401, "您还未登录");
    }
}
