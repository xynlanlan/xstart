package com.example.start.module.controller;

import com.example.start.common.base.BaseController;
import com.example.start.common.exception.ExceptionCode;
import com.example.start.common.exception.ServiceException;
import com.example.start.common.utils.MD5;
import com.example.start.module.entity.SysUser;
import com.example.start.module.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController extends BaseController {
    @Autowired
    private SysUserService sysUserService;
    //用户密码加密key
    private final static String signKey = "_start_login_";

    /**
     * 后台登录接口
     * @param user
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Map<String, Object> update(@RequestBody SysUser user) throws ServiceException {
        if(user == null || user.getLoginAccount()==null || user.getPassword() == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
        StringBuilder sb = new StringBuilder(user.getLoginAccount());
        sb.append(signKey).append(user.getPassword());
        String password = MD5.encrypt(sb.toString().getBytes());
        user.setPassword(password);
        user = sysUserService.findByNameAndPassword(user);
        if(user==null){
            throw new ServiceException(ExceptionCode.PASSWORD_ERROR);
        }else if(user.getDisable()){
            throw new ServiceException(ExceptionCode.SYSTEM_ERROR.getCode(),"账号已被禁用，请联系系统管理员！");
        }

        return success(null);
    }
}
