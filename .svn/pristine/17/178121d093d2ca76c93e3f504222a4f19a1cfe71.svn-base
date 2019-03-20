package com.tenly.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenly.system.bean.PageBean;
import com.tenly.system.bean.SysOrganization;
import com.tenly.system.bean.SysRole;
import com.tenly.system.bean.SysUser;
import com.tenly.system.dao.IUserManagerDao;
import com.tenly.system.service.IUserManagerService;

@Service
public class UserManagerServiceImpl implements IUserManagerService {
	@Autowired
	private IUserManagerDao UserManagerDao;
	@Override
	public void pageQueryUserInfo(PageBean pageBaen) {
		
		UserManagerDao.pageQueryUserInfo(pageBaen);
	}
	@Override
	public List<SysRole> queryRoles() {
		return UserManagerDao.queryRoles();
	}
	@Override
	public void updateUser(SysUser user, String role) {
		UserManagerDao.updateUser(user,role);
	}
	@Override
	public void save(SysUser user,String role) {
		UserManagerDao.save(user,role);
	}
	@Override
	public List<SysOrganization> getAllOrganizations() {
		return UserManagerDao.getAllOrganizations();
	}

}
