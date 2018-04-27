package com.example.start.module.controller;

import com.example.start.common.base.BaseController;
import com.example.start.common.base.Pager;
import com.example.start.common.exception.ServiceException;
import com.example.start.module.model.User;
import com.example.start.module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "list",method = RequestMethod.POST)
	public Map<String, Object> getUsers(@RequestBody Pager<User> pager) throws ServiceException {
		return success(userService.findByPager(pager));
	}

	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> save(User user) throws ServiceException {
		return success(userService.add(user));
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Map<String, Object> update(User user) throws ServiceException {
		return success(userService.add(user));
	}

	@RequestMapping(value = "{id}",method = RequestMethod.GET)
	public Map<String, Object> get(@PathVariable("id") Long id) throws ServiceException {
		return success(userService.findOne(id));
	}

	@RequestMapping(value = "{id}",method = RequestMethod.DELETE)
	public Map<String, Object> del(@PathVariable("id") Long id) throws ServiceException {
		return success(userService.delete(id));
	}
}
