package com.example.start.module.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.example.start.common.exception.ExceptionCode;
import com.example.start.common.utils.StringUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.start.module.service.SysUserService;
import com.example.start.common.base.Pager;
import com.example.start.module.entity.SysUser;
import com.example.start.common.exception.ServiceException;
import com.example.start.common.base.BaseController;



@RestController
@RequestMapping("api/user")
public class SysUserController extends BaseController {
	
	@Autowired
	private SysUserService sysUserService;

    @ApiOperation(value="用户列表", notes="根据条件获取用户列表")
    @ApiImplicitParam(name = "pager", value = "分页实体pager", required = true, dataType = "pager")
	@RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> list(@RequestBody Pager<SysUser> pager) throws ServiceException {

        return success(sysUserService.findByPager(pager));
    }
    @ApiOperation(value="新增用户", notes="根据entity对象创建用户")
    @ApiImplicitParam(name = "entity", value = "用户实体entity", required = true, dataType = "entity")
    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> add(@RequestBody SysUser entity) throws ServiceException {
        if(entity == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
        if(StringUtil.isNull(entity.getLoginAccount())){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请输入登录账号！");
        }
        if(StringUtil.isNull(entity.getUserName())){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请输入用户名！");
        }
        if(StringUtil.isNull(entity.getPassword())){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请设置密码！");
        }
        if(StringUtil.isNull(entity.getPhone())){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请输入手机号！");
        }
        if(entity.getSex() == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请选择性别！");
        }
        if(entity.getDisable() == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请选择启用/禁用！");
        }
        return success(sysUserService.add(entity));
    }
    @ApiOperation(value="修改用户", notes="根据entity对象编辑用户")
    @ApiImplicitParam(name = "entity", value = "资源实体entity", required = true, dataType = "entity")
    @RequestMapping(method = RequestMethod.PUT)
    public Map<String, Object> update(@RequestBody SysUser entity) throws ServiceException {
        if(entity == null || entity.getId() == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
        if(StringUtil.isNull(entity.getLoginAccount())){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请输入登录账号！");
        }
        if(StringUtil.isNull(entity.getUserName())){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请输入用户名！");
        }
        if(StringUtil.isNull(entity.getPassword())){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请设置密码！");
        }
        if(StringUtil.isNull(entity.getPhone())){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请输入手机号！");
        }
        if(entity.getSex() == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请选择性别！");
        }
        if(entity.getDisable() == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请选择启用/禁用！");
        }
        return success(sysUserService.update(entity));
    }
    @ApiOperation(value="删除用户(包含批量)", notes="根据ids删除用户信息")
    @ApiImplicitParam(name = "strs", value = "用户ID", required = true, dataType = "String[]",paramType = "path")
    @RequestMapping(value = "{strs}", method = RequestMethod.DELETE)
    public Map<String, Object> delete(@PathVariable("strs") String[] strs) throws ServiceException {
        if(strs == null || strs.length == 0){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            ids.add(Long.valueOf(strs[i]));
        }
        return success(sysUserService.batchDelete(ids));
    }
    @ApiOperation(value="启用/禁用用户", notes="根据id启用/禁用用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "status", value = "状态", required = true, dataType = "Integer",paramType = "path")
    })
    @RequestMapping(value = "{id}/{status}", method = RequestMethod.GET)
    public Map<String, Object> disabled(@PathVariable("id") Long id,@PathVariable("status") Integer status) throws ServiceException {
        if(id == null || status == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
        return success(sysUserService.disabled(id,status));
    }
    @ApiOperation(value="查询用户详情", notes="根据id获取用户信息")
    @ApiImplicitParam(name = "id", value = "资源ID", required = true, dataType = "Long",paramType = "path")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Map<String, Object> get(@PathVariable("id") Long id) throws ServiceException {
        if(id == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
        return success(sysUserService.findOne(id));
    }
}
