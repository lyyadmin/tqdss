package com.tenly.project.dao;

import java.util.List;
import java.util.Map;

import com.tenly.system.bean.SysUser;

public interface HomePageDao {
	
	@SuppressWarnings("rawtypes")
	public List getSysPointList(String selpoint,String yjlevel);
	
	@SuppressWarnings("rawtypes")
	public List querySysPointListByYjLevel(Integer userId,String yjlevel,String selpoint);
	
	@SuppressWarnings("rawtypes")
	public Map queryOldYjcs(Integer userId);
	
	public void updateYjcs(Integer userId,String selpoint);
	
	public void deleteYjcsDetail(Long pId);
	
	public void deleteYjcsDetail(Long pId,String yjlevel);
	
	public void addYjcsDetail(String dm_point_id,String yjlevel,Long pId);
	
	@SuppressWarnings("rawtypes")
	public List querySysOrganizationByUser(SysUser user);
	
	@SuppressWarnings("rawtypes")
	public Map getScore(SysUser user,String yjlevel,String oid,String bdate,String edate);
	
	@SuppressWarnings("rawtypes")
	public Map getSysOrganization(SysUser user);
	
	@SuppressWarnings("rawtypes")
	public List querySysOrganizationBySelId(String selMarkId);
	
	@SuppressWarnings("rawtypes")
	public Map getParameterInfoByUser(SysUser user);
	
	@SuppressWarnings("rawtypes")
	public List getParameterInfoZTByUser(Long pi_id);
	
	public void saveZtFx(Integer userId,String deductScore,String dxquestion);
	
	@SuppressWarnings("rawtypes")
	public List getSysPointByZtFxList(String selName);
	
	@SuppressWarnings("rawtypes")
	public List querySysPointListByZT(Integer userId,String zd_question);
	
	public void delParameterInfoZt(Long pi_id,String zdq1);
	
	public void addParameterInfoZt(Long pi_id,String zdq1,String dm_point_id);
	
	public void updateParameterInfoZt(Long pi_id,String oldzdq1,String zdq1);
	
	@SuppressWarnings("rawtypes")
	public Map getZtFx(Integer userId);
	
	public void insertParameterInfoZt(Long pi_id,String zd_question);
	
}
