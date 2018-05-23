package com.example.start.module.controller;

import com.example.start.common.base.BaseController;
import com.example.start.common.exception.ExceptionCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController extends BaseController {


    @RequestMapping(value = "/needLogin")
    public Map<String,Object> needLogin() {
        return failure(ExceptionCode.ILLEGAL_ACCESS.getCode(),"您还没有登录，请登录！");
    }

}
