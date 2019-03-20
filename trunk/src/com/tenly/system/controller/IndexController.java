package com.tenly.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenly.common.systools.StringTools;
import com.tenly.project.service.HomePageService;
import com.tenly.system.bean.SysMenu;
import com.tenly.system.bean.SysUser;
import com.tenly.system.service.IFunctionMenuService;
import com.tenly.system.service.UserService;

@Controller
@RequestMapping("/admin/system")
public class IndexController {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private UserService userservice;
	@Autowired
	private IFunctionMenuService functionMenuService;

	@Autowired
	private HomePageService homepageservice;

	//首页
	@RequestMapping("/index")
	public String indexLink(HttpServletRequest request, HttpServletResponse response,
							String userName,String password,String jwcode){
		String siteName = request.getSession().getServletContext().getInitParameter("sys_name");
		if(StringUtils.isEmpty(userName.trim())||StringUtils.isBlank(userName.trim())||
				StringUtils.isBlank(password.trim())||StringUtils.isEmpty(password.trim())){
			return "/system/sys/authentication";
		}
		if(StringUtils.isEmpty(jwcode.trim())||StringUtils.isBlank(jwcode.trim())){
			return "/system/sys/jwcode";
		}
		response.setHeader("Cache-Control", "no-cache");
		Subject subject = SecurityUtils.getSubject();//获得当前用户对象,状态为“未认证”
		AuthenticationToken token = new UsernamePasswordToken(userName.trim(), StringTools.md5(password.trim()));//创建用户名密码令牌对象
		try {
			subject.login(token);
			SysUser user = (SysUser) subject.getPrincipal();
			request.setAttribute("sys_name", siteName);
			request.setAttribute("jwcode",jwcode.trim());
			request.setAttribute("_salt", user.getSalt());
			request.getSession().setAttribute("sissionuser", user);
		} catch (UnknownAccountException uae) {
			uae.printStackTrace();
			return"/system/sys/authentication";
		} catch (IncorrectCredentialsException ice) {
			ice.printStackTrace();
			return "/system/sys/authentication";
		} catch (LockedAccountException lae) {
			lae.printStackTrace();
			return "/system/sys/authentication";
		} catch (ExcessiveAttemptsException eae) {
			eae.printStackTrace();
			return "/system/sys/authentication";
		} catch (AuthenticationException ae) {
			logger.error(ae);
			return "/system/sys/isconnect";
		}
		return "/project/lzm/checkout-analysis/MyHtml";
	}

	//登录后主界面
	@RequestMapping("/main")
	public String main(HttpServletRequest request, HttpServletResponse response) {
		String siteName = request.getSession().getServletContext().getInitParameter("sys_name");
		request.setAttribute("sys_name", siteName);
		SysUser user = (SysUser) request.getSession().getAttribute("sissionuser");
		List<SysMenu> findAllFunctionMenu = functionMenuService.findFunctionListByUserId(user.getId());
		request.setAttribute("jsonMenu", JSONArray.fromObject(findAllFunctionMenu).toString());
		return "/project/lzm/checkout-analysis/MyHtml";
	}

	//登录后主界面
	@RequestMapping("/MyHtml")
	public String myHtml(HttpServletRequest request, HttpServletResponse response) {
		return "/project/lzm/checkout-analysis/MyHtml";
	}

	//主界面链接
	@SuppressWarnings("rawtypes")
	@RequestMapping("/welcome")
	public String welcome(HttpServletRequest request, HttpServletResponse response) {
		SysUser user = (SysUser) request.getSession().getAttribute("sissionuser");
		Map omap = (Map) homepageservice.getSysOrganization(user);
		request.setAttribute("omap", omap);
		return "/project/lzm/checkout-analysis/checkout-1";
	}

	//主界面
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping("/get3ddata")
	public @ResponseBody
	Map get3ddata(HttpServletRequest request,
				  HttpServletResponse response,
				  @RequestParam("bdate") String bdate,
				  @RequestParam("edate") String edate
	) {
		SysUser user = (SysUser) request.getSession().getAttribute("sissionuser");
		bdate = bdate.replace("-", "");
		edate = edate.replace("-", "");
		Map map3d = (Map) homepageservice.get3DData(user, bdate, edate);

		Map map = new HashMap();
		map.put("status", "success");
		map.put("map3d", map3d);
		return map;
	}

	//子表链接
	@RequestMapping("/welcomedetail")
	public String welcomedetail(HttpServletRequest request,
								HttpServletResponse response,
								@RequestParam("bdate") String bdate,
								@RequestParam("edate") String edate,
								@RequestParam("selMarkId") String selMarkId,
								@RequestParam("markName") String markName
	) {
		request.setAttribute("bdate", bdate);
		request.setAttribute("edate", edate);
		request.setAttribute("selMarkId", selMarkId);
		request.setAttribute("markName", markName);
		return "/system/sys/welcomedetail";
	}

	//子表界面
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping("/get3ddatadetail")
	public @ResponseBody
	Map get3ddatadetail(HttpServletRequest request,
						HttpServletResponse response,
						@RequestParam("bdate") String bdate,
						@RequestParam("edate") String edate,
						@RequestParam("selMarkId") String selMarkId,
						@RequestParam("markName") String markName
	) {
		SysUser user = (SysUser) request.getSession().getAttribute("user");
		bdate = bdate.replace("-", "");
		edate = edate.replace("-", "");
		Map map3d = (Map) homepageservice.get3DDataDetail(user, selMarkId, bdate, edate);
		Map map = new HashMap();
		map.put("status", "success");
		map.put("map3d", map3d);
		return map;
	}

	//退出  //loginOut
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping("/loginOut")
	public @ResponseBody
	String loginOut(HttpServletRequest request, HttpServletResponse response,
					String authorizationId) {
		if (StringUtils.isBlank(authorizationId) || StringUtils.isEmpty(authorizationId)) {
			return "0";
		} else {
			if (!"123456".equals(authorizationId)) {
				return "0";
			}
		}

		//清空cookie
		Cookie killMyCookie = new Cookie("__adminuser", null);
		killMyCookie.setMaxAge(0);
		killMyCookie.setPath("/");
		response.addCookie(killMyCookie);
		//销毁session
		try {
			request.getSession().invalidate();
		} catch (IllegalStateException e) {

		}
		return "1";
	}


	/**
	 * 读入TXT文件
	 */
	private static Map<String,String> readFile() {
		//String pathname = PlatFormPar.getPropertiesVal("CONFIG_INIT");
		// 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
		//防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
		//不关闭文件会导致资源的泄露，读写文件都同理
		//Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件
		Map<String,String> smap = new HashMap<>();
		smap.put("jwcode","44");
		smap.put("username","admin");
		smap.put("password","123");
//		try (FileReader reader = new FileReader(pathname);
//			 BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
//		) {
//			String line="";
//			//网友推荐更加简洁的写法
//			int i = 0;
//			while ((line = br.readLine()) != null) {
//				// 一次读入一行数据
//				if(i!=0){
//					String[] split = line.split("=");
//					if(split[0].trim().equalsIgnoreCase("JWDCODE")){
//						smap.put("jwcode",split[1].trim());
//					}else if(split[0].trim().equals("USER")){
//						smap.put("username",split[1].trim());
//					}else if("PWD".equalsIgnoreCase(split[0].trim())){
//						smap.put("password",split[1].trim());
//					}
//				}
//				i++;
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return smap;
	}
}
