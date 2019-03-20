package com.tenly.system.dao;

import java.text.ParseException;
import java.util.List;

import com.tenly.system.bean.SysOrganization;
import com.tenly.system.bean.SysUser;

public interface UserDao {
	
	public List<?> queryUserByName(SysUser user);
	
	public List<?> queryUserByMd5Pwd(SysUser user);
	
	public SysUser findAUserByUsername(String username);
	
	public List<SysOrganization> queryUserOrganizationByUser(SysUser user) throws ParseException;
	
}
