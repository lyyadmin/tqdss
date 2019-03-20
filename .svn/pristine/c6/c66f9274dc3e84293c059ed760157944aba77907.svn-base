package com.tenly.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenly.system.bean.PageBean;
import com.tenly.system.bean.SysRole;
import com.tenly.system.bean.SysUser;
import com.tenly.system.service.ISysRoleService;

@Controller
@RequestMapping("/sys/sysrole")
public class SysRoleControl {
	
	@Autowired
	private ISysRoleService sysRoleService;
	
	@RequestMapping("/jumpSysRole")
	public String jumpSysRole(){
		
		return "/system/sys/sys-role";
	}
	
	@RequestMapping("/pageQuerySysRole")
	public @ResponseBody PageBean pageQuerySysRole(HttpServletRequest request,
			HttpServletResponse response,String page,String rows){
		PageBean pageBaen = new PageBean();
		pageBaen.setCurrentPage(Integer.valueOf(page));
		pageBaen.setPageSize(Integer.valueOf(rows));
		sysRoleService.pageQuerySysRole(pageBaen);
		return pageBaen;
	}
	/**
	 * 更新用户角色信息
	 * @param request
	 * @param response
	 * @param role
	 * @param functionIds
	 */
	@RequestMapping("/update")
	public void update(HttpServletRequest request,
			HttpServletResponse response,SysRole role,String functionIds){
		SysUser user = (SysUser)request.getSession().getAttribute("user");
		sysRoleService.updateUserRole(role,functionIds,user);
	}
	/**
	 * 添加角色
	 */
	@RequestMapping("/addRoleInfo")
	public void addRoleInfo(HttpServletRequest request,
			HttpServletResponse response,SysRole role,String functionRoleIds){
		SysUser user = (SysUser)request.getSession().getAttribute("user");
		
		try{
			sysRoleService.save(role,functionRoleIds,user);
		}catch(Exception e){
			//保存失败
			e.printStackTrace();
		}
	}
	/**
	 * 删除角色
	 */
	@RequestMapping("/delRoleInfo")
	public void delRoleInfo(HttpServletRequest request,
			HttpServletResponse response,String roleId){
		try{
			sysRoleService.delRole(roleId);
		}catch(Exception e){
			//保存失败
			e.printStackTrace();
		}
	}
}
