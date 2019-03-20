package com.tenly.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenly.system.service.UserService;

@Controller
@RequestMapping("/admin/system")
public class systemManageController {
	
	@Autowired
	private UserService userservice;
	
	//查询链接
	@RequestMapping("/user")
	public String userlink(HttpServletRequest request,HttpServletResponse response) {
		return "/system/user/user";
	}
	
}
