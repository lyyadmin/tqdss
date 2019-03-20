package com.tenly.system.service.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenly.system.bean.SysOrganization;
import com.tenly.system.bean.SysUser;
import com.tenly.system.dao.UserDao;
import com.tenly.system.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserDao userdao;
	
	public List<?> queryUserByName(SysUser user) {
		return userdao.queryUserByName(user);
	}
	
	public List<?> queryUserByMd5Pwd(SysUser user) {
		return userdao.queryUserByMd5Pwd(user);
	}

	@Override
	public List<SysOrganization> queryUserOrganizationByUser(SysUser user) throws ParseException{
		return userdao.queryUserOrganizationByUser(user);
	}
	
}
