package com.tenly.system.realm;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.tenly.system.bean.SysMenu;
import com.tenly.system.bean.SysUser;
import com.tenly.system.dao.IFunctionMenuDao;
import com.tenly.system.dao.UserDao;
//AuthorizingRealm
public class TenlyRealm extends AuthorizingRealm {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private IFunctionMenuDao functionMenuDao;
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken passwordToken = (UsernamePasswordToken)token;
		//获得页面输入的用户名
		String username = passwordToken.getUsername();
		SysUser user = userDao.findAUserByUsername(username);
		if(user == null){
			//页面输入的用户名不存在
			return null;
		}
		//简单认证信息对象
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		//框架负责比对数据库中的密码和页面输入的密码是否一致
		return info;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//获取当前登录用户对象
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		//User user2 = (User) principals.getPrimaryPrincipal();
		// 根据当前登录用户查询数据库，获取实际对应的权限
		List<SysMenu> list = null;
		if(user.getUsername().equals("admin")){
//			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
			//超级管理员内置用户，查询所有权限数据
			list = functionMenuDao.findAll();
		}else{
			list = functionMenuDao.findFunctionListByUserId(user.getId());
		}
		
		for (SysMenu function : list) {
			info.addStringPermission(function.getCode());
		}
		return info;
	}
}
