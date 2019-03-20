package com.tenly.system.service;

import java.util.List;
import java.util.Map;

import com.tenly.system.bean.PageBean;
import com.tenly.system.bean.SysMenu;

public interface IFunctionMenuService {

	void pageQueryFunctionMenu(PageBean pageBean);

	List<SysMenu> findAllFunctionMenu();
	//对应角色菜单ID
	List<Map<String, Object>> findRoleFunctionMenu(String roleId);

	List<SysMenu> findFunctionListByUserId(Integer userid);

	void addFunctionMenu(SysMenu sysMenu);
	
	void updateFunctionMenu(SysMenu sysMenu);


}
