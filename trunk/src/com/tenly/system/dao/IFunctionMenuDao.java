package com.tenly.system.dao;

import java.util.List;
import java.util.Map;

import com.tenly.system.bean.PageBean;
import com.tenly.system.bean.SysMenu;

public interface IFunctionMenuDao {

	void pageQueryFunctionMenu(PageBean pageBean);

	List<SysMenu> findAll();
	List<Map<String, Object>> findRoleFunctionMenu(String roleId);

	List<SysMenu> findFunctionListByUserId(Integer id);

	void addFunctionMenu(SysMenu sysMenu);

	void updateFunctionMenu(SysMenu sysMenu);

}
