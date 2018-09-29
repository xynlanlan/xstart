package com.example.start.module.controller;


import com.example.start.common.base.BaseController;
import com.example.start.common.exception.ExceptionCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Api(description = "Security相关接口")
@RequestMapping("/common")
public class LoginController extends BaseController {

    @RequestMapping(value = "/needLogin",method = RequestMethod.GET)
    @ApiOperation(value = "没有登录")
    public Map<String, Object> needLogin(){
        return failure(ExceptionCode.NOT_LOGIN.getCode(),ExceptionCode.NOT_LOGIN.getMessage());
    }
}
