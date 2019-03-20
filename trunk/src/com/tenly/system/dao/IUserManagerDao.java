package com.tenly.system.dao;

import java.util.List;

import com.tenly.system.bean.PageBean;
import com.tenly.system.bean.SysOrganization;
import com.tenly.system.bean.SysRole;
import com.tenly.system.bean.SysUser;

public interface IUserManagerDao {

	void pageQueryUserInfo(PageBean pageBaen);

	List<SysRole> queryRoles();

	void updateUser(SysUser user, String role);

	void save(SysUser user,String role);
	
	List<SysOrganization> getAllOrganizations();

}
