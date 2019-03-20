package com.tenly.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenly.system.bean.PageBean;
import com.tenly.system.bean.SysMenu;
import com.tenly.system.dao.IFunctionMenuDao;
import com.tenly.system.service.IFunctionMenuService;

@Service
public class FunctionMenuServiceImpl implements IFunctionMenuService {
	@Autowired
	private IFunctionMenuDao functionMenuDao;
	
	@Override
	public void pageQueryFunctionMenu(PageBean pageBean) {
		// TODO Auto-generated method stub
		functionMenuDao.pageQueryFunctionMenu(pageBean);
	}

	@Override
	public List<SysMenu> findAllFunctionMenu() {
		
		return functionMenuDao.findAll();
	}

	@Override
	public List<SysMenu> findFunctionListByUserId(Integer userid) {
		// TODO Auto-generated method stub
		return functionMenuDao.findFunctionListByUserId(userid);
	}

	@Override
	public void addFunctionMenu(SysMenu sysMenu) {
		functionMenuDao.addFunctionMenu(sysMenu);
	}

	@Override
	public void updateFunctionMenu(SysMenu sysMenu) {
		// TODO Auto-generated method stub
		functionMenuDao.updateFunctionMenu(sysMenu);
	}

	@Override
	public List<Map<String, Object>> findRoleFunctionMenu(String roleId) {
		// TODO Auto-generated method stub
		return functionMenuDao.findRoleFunctionMenu(roleId);
	}

}
