package com.tenly.system.service;

import com.tenly.system.bean.PageBean;
import com.tenly.system.bean.SysRole;
import com.tenly.system.bean.SysUser;

public interface ISysRoleService {

	void pageQuerySysRole(PageBean pageBaen);

	void updateUserRole(SysRole role, String functionIds, SysUser user);

	void save(SysRole role, String functionRoleIds, SysUser user);

	void delRole(String role);

}
