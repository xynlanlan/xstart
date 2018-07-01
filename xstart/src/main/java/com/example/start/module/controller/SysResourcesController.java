package com.example.start.module.controller;

import com.example.start.common.base.BaseController;
import com.example.start.common.exception.ExceptionCode;
import com.example.start.common.exception.ServiceException;
import com.example.start.common.interceptor.RequiredPermission;
import com.example.start.common.utils.StringUtil;
import com.example.start.module.entity.SysResources;
import com.example.start.module.service.SysResourcesService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;



@RestController
@RequestMapping("api/resources")
public class SysResourcesController extends BaseController {
	
	@Autowired
	private SysResourcesService sysResourcesService;

    @RequiredPermission
    @ApiOperation(value="资源列表", notes="根据条件获取资源列表")
    @ApiImplicitParam(name = "entity", value = "资源实体entity", required = false, dataType = "entity")
	@RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> list(@RequestBody SysResources entity) throws ServiceException {
        return success(sysResourcesService.findList(entity));
    }
    @RequiredPermission
    @ApiOperation(value="新增资源", notes="根据entity对象创建资源")
    @ApiImplicitParam(name = "entity", value = "资源实体entity", required = true, dataType = "entity")
    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> add(@RequestBody SysResources entity) throws ServiceException {
        if(entity == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
        if(StringUtil.isNull(entity.getMenuName())){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请输入菜单名！");
        }
        if(entity.getSort() == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请输入菜单排序！");
        }
        if(entity.getDisabled() == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请选择启用/禁用！");
        }
        return success(sysResourcesService.add(entity));
    }
    @RequiredPermission
    @ApiOperation(value="修改资源", notes="根据entity对象编辑资源")
    @ApiImplicitParam(name = "entity", value = "资源实体entity", required = true, dataType = "entity")
    @RequestMapping(method = RequestMethod.PUT)
    public Map<String, Object> update(@RequestBody SysResources entity) throws ServiceException {
        if(entity == null || entity.getId() == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
        if(StringUtil.isNull(entity.getMenuName())){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请输入菜单名！");
        }
        if(entity.getSort() == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请输入菜单排序！");
        }
        if(entity.getDisabled() == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER.getCode(),"请选择启用/禁用！");
        }
        return success(sysResourcesService.update(entity));
    }

    @RequiredPermission
    @ApiOperation(value="删除资源", notes="根据id删除资源信息")
    @ApiImplicitParam(name = "id", value = "资源ID", required = true, dataType = "Long",paramType = "path")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Map<String, Object> delete(@PathVariable("id") Long id) throws ServiceException {
        if(id == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
        return success(sysResourcesService.delete(id));
    }
    @RequiredPermission
    @ApiOperation(value="启用/禁用资源", notes="根据id启用/禁用资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "资源ID", required = true, dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "status", value = "状态", required = true, dataType = "Integer",paramType = "path")
    })
    @RequestMapping(value = "{id}/{status}", method = RequestMethod.GET)
    public Map<String, Object> disabled(@PathVariable("id") Long id,@PathVariable("status") Integer status) throws ServiceException {
        if(id == null || status == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
        return success(sysResourcesService.disabled(id,status));
    }

    @RequiredPermission
    @ApiOperation(value="查询资源详情", notes="根据id获取资源信息")
    @ApiImplicitParam(name = "id", value = "资源ID", required = true, dataType = "Long",paramType = "path")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Map<String, Object> get(@PathVariable("id") Long id) throws ServiceException {
        if(id == null){
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
        return success(sysResourcesService.findOne(id));
    }	

}
