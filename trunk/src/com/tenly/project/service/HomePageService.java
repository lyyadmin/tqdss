package com.tenly.project.service;

import java.util.List;
import java.util.Map;

import com.tenly.system.bean.SysUser;

public interface HomePageService {
	
	@SuppressWarnings("rawtypes")
	public List getSysPointList(String selpoint,String yjlevel);
	
	public String querySysPointListByYjLevel(Integer userId,String yjlevel,String selpoint);
	
	public void saveYjcs(Integer userId,String yjlevel,String selpoint,String pIdstr);
	
	@SuppressWarnings("rawtypes")
	public Map get3DData(SysUser user,String bdate,String edate);
	
	@SuppressWarnings("rawtypes")
	public Map getSysOrganization(SysUser user);
	
	@SuppressWarnings("rawtypes")
	public Map get3DDataDetail(SysUser user,String selMarkId,String bdate,String edate);
	
	@SuppressWarnings("rawtypes")
	public Map getParameterInfoByUser(SysUser user);
	
	public void saveZtFx(Integer userId,String deductScore,String dxquestion,String oldzdq1,String oldzdq2,String oldzdq3,String oldzdq4,String zdq1,String zdq2,String zdq3,String zdq4,String pIdstr1,String pIdstr2,String pIdstr3,String pIdstr4);
	
	@SuppressWarnings("rawtypes")
	public List getSysPointByZtFxList(String selName);
	
	public String querySysPointListByZT(Integer userId,String zd_question);
	
}
