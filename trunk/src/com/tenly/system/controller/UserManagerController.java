package com.tenly.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenly.system.bean.PageBean;
import com.tenly.system.bean.SysOrganization;
import com.tenly.system.bean.SysRole;
import com.tenly.system.bean.SysUser;
import com.tenly.system.service.IUserManagerService;

@Controller
@RequestMapping("/system/usermanager/")
public class UserManagerController {
	
	@Autowired
	private IUserManagerService userManagerService;
	
	@RequestMapping("/user-manager")
	public String jumpUserManager(){
		return "/system/sys/user-manager";
	}
	
	/**
	 * 查询用户信息
	 * @return
	 */
	@RequestMapping("/userinfo")
	public @ResponseBody PageBean pageQueryUserInfo(HttpServletRequest request,HttpServletResponse response,String page,String rows){
		PageBean pageBaen = new PageBean();
		pageBaen.setCurrentPage(Integer.valueOf(page));
		pageBaen.setPageSize(Integer.valueOf(rows));
		userManagerService.pageQueryUserInfo(pageBaen);
		return pageBaen;
	}
	/**
	 * 添加用户信息
	 * @return
	 */
	@RequestMapping("/add")
	public @ResponseBody List<SysUser> addUser(HttpServletRequest request,HttpServletResponse response, String username, String realname, String oldPassword,
			String email, String phone, String roleId, String organizations){
//		userManagerService.save(user,role);
		return null;
	}
	/**
	 * 删除用户信息
	 * @return
	 */
	@RequestMapping("/del")
	public @ResponseBody List<SysUser> delUser(){
		System.out.println("------------------");
		return null;
	}
	/**
	 * 更新用户信息
	 * @return
	 */
	@RequestMapping("/updateinfo")
	public @ResponseBody List<SysUser> updateUser(HttpServletRequest request,HttpServletResponse response,SysUser
			user,String page,String rows,String role){
		userManagerService.updateUser(user,role);
		return null;
	}
	
	/**
	 * 新增用户时获取所有的角色信息和组织结构信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/findAll")
	public @ResponseBody Map<String, Object> findAll(HttpServletRequest request,HttpServletResponse response) {
		List<SysRole> sysRoles= userManagerService.queryRoles();
		List<SysOrganization> sysOrganizations = userManagerService.getAllOrganizations();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sysRoles", sysRoles);
		map.put("sysOrganizations", sysOrganizations);
		return map;
	}
	
//	/**
//	 * 查询当前用户角色
//	 */
//	@RequestMapping("/queryRoleByUserId")
//	public @ResponseBody List<SysRole> queryRoleByUserId(){
//		List<SysRole> list= userManagerService.queryRoleByUserId();
//		return list;
//	}
//	
//	/**
//	 * 获取所有组织结构信息
//	 * @return
//	 */
//	public @ResponseBody List<SysOrganization> getAllOrganizations() throws ParseException {
//		List<SysOrganization> allOrganizations = userManagerService.getAllOrganizations();
//		return allOrganizations;
//		
//	}
}
