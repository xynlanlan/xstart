package com.example.start.module.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.start.module.service.SysRoleResourcesService;
import com.example.start.common.base.Pager;
import com.example.start.module.entity.SysRoleResources;
import com.example.start.common.exception.ServiceException;
import com.example.start.common.base.BaseController;



@RestController
@RequestMapping("api/sysRoleResources")
public class SysRoleResourcesController extends BaseController {
	
	@Autowired
	private SysRoleResourcesService sysRoleResourcesService;
	
	
	@RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> list(@RequestBody Pager<SysRoleResources> pager) throws ServiceException {

        return success(sysRoleResourcesService.findByPager(pager));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> add(@RequestBody SysRoleResources entity) throws ServiceException {    	
        return success(sysRoleResourcesService.add(entity));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Map<String, Object> update(@RequestBody SysRoleResources entity) throws ServiceException {    	
        return success(sysRoleResourcesService.update(entity));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Map<String, Object> delete(@PathVariable("id") Long id) throws ServiceException {
        
        return success(sysRoleResourcesService.delete(id));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Map<String, Object> get(@PathVariable("id") Long id) throws ServiceException {
        return success(sysRoleResourcesService.findOne(id));
    }	

}
