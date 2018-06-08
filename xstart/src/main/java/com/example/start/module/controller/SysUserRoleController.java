package com.example.start.module.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.start.module.service.SysUserRoleService;
import com.example.start.common.base.Pager;
import com.example.start.module.entity.SysUserRole;
import com.example.start.common.exception.ServiceException;
import com.example.start.common.base.BaseController;



@RestController
@RequestMapping("api/userRole")
public class SysUserRoleController extends BaseController {
	
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	
	/*@RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> list(@RequestBody Pager<SysUserRole> pager) throws ServiceException {

        return success(sysUserRoleService.findByPager(pager));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> add(@RequestBody SysUserRole entity) throws ServiceException {    	
        return success(sysUserRoleService.add(entity));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Map<String, Object> update(@RequestBody SysUserRole entity) throws ServiceException {    	
        return success(sysUserRoleService.update(entity));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Map<String, Object> delete(@PathVariable("id") Long id) throws ServiceException {
        
        return success(sysUserRoleService.delete(id));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Map<String, Object> get(@PathVariable("id") Long id) throws ServiceException {
        return success(sysUserRoleService.findOne(id));
    }	*/

}
