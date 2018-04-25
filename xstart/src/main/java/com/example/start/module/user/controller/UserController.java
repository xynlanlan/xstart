package com.example.start.module.user.controller;

import java.util.List;
import java.util.Map;

import com.example.start.common.base.BaseController;
import com.example.start.module.user.model.User;
import com.example.start.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RequestMapping("user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@GetMapping("getUsers/{page}/{rows}")
	public String getUsers(Model model, @PathVariable("page")int page, @PathVariable("rows")int rows) {
		List<User> list = userService.findByPager(null, page, rows);
		model.addAttribute("list",list);
		return "member/member_list";
	}
	
	@GetMapping("{id}")
	public String get(Model model,@PathVariable("id") Long id) {
		User user = userService.findOne(id);
		model.addAttribute("user",user);
		return "details";
	}
	
}
