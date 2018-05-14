package com.example.start.module.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.start.module.service.SysRoleService;
import com.example.start.common.base.Pager;
import com.example.start.module.entity.SysRole;
import com.example.start.common.exception.ServiceException;
import com.example.start.common.base.BaseController;



@RestController
@RequestMapping("api/sysRole")
public class SysRoleController extends BaseController {
	
	@Autowired
	private SysRoleService sysRoleService;
	
	
	@RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> list(@RequestBody Pager<SysRole> pager) throws ServiceException {

        return success(sysRoleService.findByPager(pager));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> add(@RequestBody SysRole entity) throws ServiceException {    	
        return success(sysRoleService.add(entity));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Map<String, Object> update(@RequestBody SysRole entity) throws ServiceException {    	
        return success(sysRoleService.update(entity));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Map<String, Object> delete(@PathVariable("id") Long id) throws ServiceException {
        
        return success(sysRoleService.delete(id));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Map<String, Object> get(@PathVariable("id") Long id) throws ServiceException {
        return success(sysRoleService.findOne(id));
    }	

}
