package com.example.start.module.controller;

import com.example.start.common.base.BaseController;
import com.example.start.common.base.Pager;
import com.example.start.common.exception.ServiceException;
import com.example.start.common.interceptor.RequiredPermission;
import com.example.start.module.entity.ArticleInfo;
import com.example.start.module.service.ArticleInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/articleInfo")
public class ArticleInfoController extends BaseController {

	@Autowired
	private ArticleInfoService articleInfoService;


	@RequiredPermission
    @ApiOperation(value="列表", notes="根据条件获取列表")
    @ApiImplicitParam(name = "pager", value = "分页实体pager", required = true, dataType = "pager")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> list(@RequestBody Pager<ArticleInfo> pager) throws ServiceException {
        return success(articleInfoService.findByPager(pager));
    }


    @RequiredPermission
    @ApiOperation(value="新增", notes="根据entity对象创建")
    @ApiImplicitParam(name = "entity", value = "实体entity", required = true, dataType = "entity")
    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> add(@RequestBody ArticleInfo entity) throws ServiceException {    	
        return success(articleInfoService.add(entity));
    }

    @RequiredPermission
    @ApiOperation(value="修改", notes="根据entity对象编辑")
    @ApiImplicitParam(name = "entity", value = "实体entity", required = true, dataType = "entity")
    @RequestMapping(method = RequestMethod.PUT)
    public Map<String, Object> update(@RequestBody ArticleInfo entity) throws ServiceException {    	
        return success(articleInfoService.update(entity));
    }

    @ApiOperation(value="删除", notes="根据id删除")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long",paramType = "path")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Map<String, Object> delete(@PathVariable("id") Long id) throws ServiceException {
        return success(articleInfoService.delete(id));
    }

    @ApiOperation(value="查询详情", notes="根据id查询")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long",paramType = "path")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Map<String, Object> get(@PathVariable("id") Long id) throws ServiceException {
        return success(articleInfoService.findOne(id));
    }	

}
