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
import com.tenly.system.bean.SysMenu;
import com.tenly.system.bean.SysUser;
import com.tenly.system.service.IFunctionMenuService;

@Controller
@RequestMapping("/system/functionmenu")
public class FunctionMenuControl {

	@Autowired
	private IFunctionMenuService functionMenu;
	/**
	 * 页面跳转
	 * @return
	 */
	@RequestMapping("/functionmenu")
	public String jumpFunctionMenu(){
		return "/system/sys/function-menu";
	}
	
	/**
	 * 查询权限菜单列表
	 * @return
	 */
	@RequestMapping("/pageQueryFunctionMenu")
	public @ResponseBody PageBean pageQueryFunctionMenu(HttpServletRequest request,
			HttpServletResponse response,String page,String rows){
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(Integer.valueOf(page));
		pageBean.setPageSize(Integer.valueOf(rows));
		functionMenu.pageQueryFunctionMenu(pageBean);
		return pageBean;
	}
	/**
	 * 查询权限菜单
	 * @return
	 */
	@RequestMapping("/findAllFunctionMenu")
	public @ResponseBody List<SysMenu> findAllFunctionMenu(HttpServletRequest request,
			HttpServletResponse response){
		SysUser user = (SysUser)request.getSession().getAttribute("user");
		List<SysMenu> findAllFunctionMenu = functionMenu.findFunctionListByUserId(user.getId());
		return findAllFunctionMenu;
	}
	/**
	 * 查询所有菜单
	 * @return
	 */
	@RequestMapping("/findAll")
	public @ResponseBody Map findAll(HttpServletRequest request,
			HttpServletResponse response, String roleId){
		List<SysMenu> findAllFunctionMenu = functionMenu.findAllFunctionMenu();
		List<Map<String, Object>> findRoleFunctionMenu = functionMenu.findRoleFunctionMenu(roleId);
		Map<String, List> map = new HashMap<String, List>();
		map.put("findRoleFunctionMenu", findRoleFunctionMenu);
		map.put("findAllFunctionMenu", findAllFunctionMenu);
		
		return map;//findAllFunctionMenu;
	}
	/**
	 * 添加功能权限
	 * @return
	 */
	@RequestMapping("/addFunctionMenu")
	public void addFunctionMenu(HttpServletRequest request,
			HttpServletResponse response,SysMenu sysMenu){
		functionMenu.addFunctionMenu(sysMenu);
	}
	/**
	 * 更新功能权限菜单
	 */
	@RequestMapping("/updateFunctionMenu")
	public void updateFunctionMenu(HttpServletRequest request,
			HttpServletResponse response,SysMenu sysMenu){
		functionMenu.updateFunctionMenu(sysMenu);
	}
	
	@RequestMapping("/delFunctionMenu")
	public @ResponseBody String delFunctionMenu(){
		//TODO
		return "";
	}

	
}
