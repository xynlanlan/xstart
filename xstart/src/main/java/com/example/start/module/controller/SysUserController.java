package com.example.start.module.controller;

import java.util.Map;

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
        return success(sysUserService.add(entity));
    }
    @ApiOperation(value="修改用户", notes="根据entity对象编辑用户")
    @ApiImplicitParam(name = "entity", value = "资源实体entity", required = true, dataType = "entity")
    @RequestMapping(method = RequestMethod.PUT)
    public Map<String, Object> update(@RequestBody SysUser entity) throws ServiceException {    	
        return success(sysUserService.update(entity));
    }
    @ApiOperation(value="删除用户", notes="根据id删除用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Map<String, Object> delete(@PathVariable("id") Long id) throws ServiceException {
        
        return success(sysUserService.delete(id));
    }
    @ApiOperation(value="启用/禁用用户", notes="根据id启用/禁用用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "status", value = "状态", required = true, dataType = "Integer",paramType = "path")
    })
    @RequestMapping(value = "{id}/{status}", method = RequestMethod.GET)
    public Map<String, Object> disabled(@PathVariable("id") Long id,@PathVariable("status") Integer status) throws ServiceException {

        return success(sysUserService.disabled(id,status));
    }
    @ApiOperation(value="查询用户详情", notes="根据id获取用户信息")
    @ApiImplicitParam(name = "id", value = "资源ID", required = true, dataType = "Long",paramType = "path")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Map<String, Object> get(@PathVariable("id") Long id) throws ServiceException {
        return success(sysUserService.findOne(id));
    }	

}
