package com.example.start.module.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.start.module.service.SysUserService;
import com.example.start.common.base.Pager;
import com.example.start.module.entity.SysUser;
import com.example.start.common.exception.ServiceException;
import com.example.start.common.base.BaseController;



@RestController
@RequestMapping("api/sysUser")
public class SysUserController extends BaseController {
	
	@Autowired
	private SysUserService sysUserService;
	
	
	@RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> list(@RequestBody Pager<SysUser> pager) throws ServiceException {

        return success(sysUserService.findByPager(pager));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> add(@RequestBody SysUser entity) throws ServiceException {    	
        return success(sysUserService.add(entity));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Map<String, Object> update(@RequestBody SysUser entity) throws ServiceException {    	
        return success(sysUserService.update(entity));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Map<String, Object> delete(@PathVariable("id") Long id) throws ServiceException {
        
        return success(sysUserService.delete(id));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Map<String, Object> get(@PathVariable("id") Long id) throws ServiceException {
        return success(sysUserService.findOne(id));
    }	

}
