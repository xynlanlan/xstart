package com.example.start.module.controller;

import java.util.Map;

import com.example.start.common.exception.ExceptionCode;
import com.example.start.common.utils.StringUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.start.module.service.SysRoleService;
import com.example.start.common.base.Pager;
import com.example.start.module.entity.SysRole;
import com.example.start.common.exception.ServiceException;
import com.example.start.common.base.BaseController;



@RestController
@RequestMapping("api/role")
public class SysRoleController extends BaseController {
	
	@Autowired
	private SysRoleService sysRoleService;

    @ApiOperation(value="角色列表", notes="根据条件获取角色列表")
    @ApiImplicitParam(name = "pager", value = "分页实体pager", required = true, dataType = "pager")
	@RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> list(@RequestBody Pager<SysRole> pager) throws ServiceException {

        return success(sysRoleService.findByPager(pager));
    }
    @ApiOperation(value="新增角色", notes="根据entity对象创建角色")
    @ApiImplicitParam(name = "entity", value = "角色实体entity", required = true, dataType = "entity")
    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> add(@RequestBody SysRole entity) throws ServiceException {
        if(entity == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
        if(StringUtil.isNull(entity.getRoleName())){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请输入角色名！");
        }
        if(StringUtil.isNull(entity.getAlias())){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请输入角色别名！");
        }
        if(entity.getDisabled() == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请选择启用/禁用！");
        }
        return success(sysRoleService.add(entity));
    }
    @ApiOperation(value="修改角色", notes="根据entity对象编辑角色")
    @ApiImplicitParam(name = "entity", value = "资源实体entity", required = true, dataType = "entity")
    @RequestMapping(method = RequestMethod.PUT)
    public Map<String, Object> update(@RequestBody SysRole entity) throws ServiceException {
        if(entity == null || entity.getId() == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
        if(StringUtil.isNull(entity.getRoleName())){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请输入角色名！");
        }
        if(StringUtil.isNull(entity.getAlias())){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请输入角色别名！");
        }
        if(entity.getDisabled() == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请选择启用/禁用！");
        }
        return success(sysRoleService.update(entity));
    }
    @ApiOperation(value="删除角色", notes="根据id删除角色信息")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long",paramType = "path")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Map<String, Object> delete(@PathVariable("id") Long id) throws ServiceException {
        if(id == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
        return success(sysRoleService.delete(id));
    }
    @ApiOperation(value="启用/禁用角色", notes="根据id启用/禁用角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "status", value = "状态", required = true, dataType = "Integer",paramType = "path")
    })
    @RequestMapping(value = "{id}/{status}", method = RequestMethod.GET)
    public Map<String, Object> disabled(@PathVariable("id") Long id,@PathVariable("status") Integer status) throws ServiceException {
        if(id == null || status == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
        return success(sysRoleService.disabled(id,status));
    }
    @ApiOperation(value="查询角色详情", notes="根据id获取角色信息")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long",paramType = "path")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Map<String, Object> get(@PathVariable("id") Long id) throws ServiceException {
        if(id == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
        return success(sysRoleService.findOne(id));
    }	

}
