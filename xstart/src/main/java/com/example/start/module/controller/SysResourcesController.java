package com.example.start.module.controller;

import com.example.start.common.base.BaseController;
import com.example.start.common.exception.ServiceException;
import com.example.start.module.entity.SysResources;
import com.example.start.module.service.SysResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;



@RestController
@RequestMapping("api/resources")
public class SysResourcesController extends BaseController {
	
	@Autowired
	private SysResourcesService sysResourcesService;
	
	
	@RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> list(@RequestBody SysResources entity) throws ServiceException {

        return success(sysResourcesService.findList(entity));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> add(@RequestBody SysResources entity) throws ServiceException {    	
        return success(sysResourcesService.add(entity));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Map<String, Object> update(@RequestBody SysResources entity) throws ServiceException {    	
        return success(sysResourcesService.update(entity));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Map<String, Object> delete(@PathVariable("id") Long id) throws ServiceException {
        
        return success(sysResourcesService.delete(id));
    }

    @RequestMapping(value = "{id}/{status}", method = RequestMethod.GET)
    public Map<String, Object> disabled(@PathVariable("id") Long id,@PathVariable("status") Integer status) throws ServiceException {

        return success(sysResourcesService.disabled(id,status));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Map<String, Object> get(@PathVariable("id") Long id) throws ServiceException {
        return success(sysResourcesService.findOne(id));
    }	

}
