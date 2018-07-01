package com.example.start.module.controller;

import com.example.start.common.base.BaseController;
import com.example.start.common.constant.Constants;
import com.example.start.common.exception.ExceptionCode;
import com.example.start.common.exception.ServiceException;
import com.example.start.common.interceptor.RequiredPermission;
import com.example.start.common.utils.StringUtil;
import com.example.start.module.entity.SysUser;
import com.example.start.module.service.SysUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class LoginController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private SysUserService sysUserService;
    /*@ApiOperation(value="检测登录")
    @RequestMapping(value = "/needLogin")
    public Map<String,Object> needLogin() {
        return failure(ExceptionCode.ILLEGAL_ACCESS.getCode(),"您还没有登录，请登录！");
    }
*/
    @ApiOperation(value="后台登录")
    @ApiImplicitParam(name = "entity", value = "用户实体entity", required = true, dataType = "entity")
    @RequestMapping(value = "/alogin",method = RequestMethod.POST)
    public Map<String,Object> alogin(HttpSession session, @RequestBody SysUser entity) throws ServiceException {
        if(entity == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
        if(StringUtil.isNull(entity.getLoginAccount())){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请输入登录账号！");
        }
        if(StringUtil.isNull(entity.getPassword())){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请输入登录密码！");
        }
        SysUser user = sysUserService.findByAccount(entity.getLoginAccount());
        if(user == null){
            throw new ServiceException(ExceptionCode.PASSWORD_ERROR);
        }
        if(!entity.getPassword().equals(user.getPassword())){
            throw new ServiceException(ExceptionCode.PASSWORD_ERROR.getCode(),"密码错误，请重新输入！");
        }
        session.setAttribute(Constants.PROJECT_NAME + Constants.USER_ + user.getId(),user);
        return success();
    }
    @RequiredPermission
    @ApiOperation(value="注销")
    @RequestMapping(value = "/alogout")
    public Map<String,Object> alogout(HttpSession session) throws ServiceException {
        session.removeAttribute(session.getId());

        return success();
    }
}
