package com.tenly.interceptor;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tenly.common.systools.Constant;
import com.tenly.common.systools.StringTools;
import com.tenly.system.bean.SysUser;
import com.tenly.system.service.UserService;

public class MyInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserService userservice;
	
	public void afterCompletion(HttpServletRequest arg0,HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		//System.out.println("afterCompletion");
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,Object arg2, ModelAndView arg3) throws Exception {
		//System.out.println("postHandle");
		
	}
	
	@SuppressWarnings("rawtypes")
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		request.setAttribute("contextPath",contextPath);
		if ((contextPath+Constant.ADMINUSER_INDEX_LINK).equals(uri)
				|| (contextPath+Constant.ADMINUSER_LOGIN_LINK).equals(uri)
				||(contextPath+Constant.QUERY_DRIVER_POSITION_ID).equals(uri)) {
			request.setAttribute("userloginStatusWithTQ", "0");
			return true;
		}else{
			String Md5UserId = "";
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					if (Constant.ADMINUSER_COOKIES_NAME.equals(cookie.getName())) {
						Md5UserId = cookie.getValue();
					}
				}
			}
			if(StringTools.isEmpty(Md5UserId)){
				request.setAttribute("userloginStatusWithTQ", "0");
				response.sendRedirect(request.getContextPath() + "/admin/system/index.do");
				return false;
			}
			//获取登录用户信息
			SysUser usermsg = new SysUser();
			usermsg.setSalt(Md5UserId);
			List user = userservice.queryUserByMd5Pwd(usermsg);
			if (user.isEmpty()) {
				request.setAttribute("userloginStatusWithTQ", "0");
				response.sendRedirect(request.getContextPath() + "/admin/system/index.do");
				return false;
			}
			request.setAttribute("userloginStatusWithTQ", "1");
			return true;
		}
	}
}
