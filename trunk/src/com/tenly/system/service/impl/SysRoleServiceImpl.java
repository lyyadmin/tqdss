package com.tenly.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenly.system.bean.PageBean;
import com.tenly.system.bean.SysRole;
import com.tenly.system.bean.SysUser;
import com.tenly.system.dao.ISysRoleDao;
import com.tenly.system.service.ISysRoleService;

@Service
public class SysRoleServiceImpl implements ISysRoleService {
	@Autowired
	private ISysRoleDao sysRoleDao;

	@Override
	public void pageQuerySysRole(PageBean pageBaen) {
		sysRoleDao.pageQuerySysRole(pageBaen);
	}

	@Override
	public void updateUserRole(SysRole role, String functionIds,SysUser user) {
		sysRoleDao.updateUserRole(role,functionIds,user);
	}

	@Override
	public void save(SysRole role, String functionRoleIds, SysUser user) {
		sysRoleDao.save(role,functionRoleIds,user);
	}

	@Override
	public void delRole(String role) {
		sysRoleDao.delRole(role);
		
	}

}
