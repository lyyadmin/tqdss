package com.tenly.system.service;

import java.text.ParseException;
import java.util.List;

import com.tenly.system.bean.SysOrganization;
import com.tenly.system.bean.SysUser;

public interface UserService {
	
	public List<?> queryUserByName(SysUser user);
	
	public List<?> queryUserByMd5Pwd(SysUser user);
	
	/**
	 * 获取当前用户的组织架构信息
	 * @param user
	 * @return
	 */
	public List<SysOrganization> queryUserOrganizationByUser(SysUser user) throws ParseException;	
}
