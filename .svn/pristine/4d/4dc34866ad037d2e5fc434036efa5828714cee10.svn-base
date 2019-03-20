package com.tenly.project.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tenly.project.dao.HomePageDao;
import com.tenly.system.bean.SysUser;

@Repository
public class HomePageDaoImpl implements HomePageDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("rawtypes")
	public List getSysPointList(String selpoint,String yjlevel){
		String sql = "";
		if("1".equals(selpoint)){
			//作业场景
			sql = " select id,name,parent_id,(select count(*) from yw_parameter_info_yj piy INNER JOIN yw_parameter_info pi ON pi.pi_id = piy.pi_id where piy.dm_point_id = id and piy.yj_level = ? and pi.point_type = ?) as pcount from dm_point where length(id) = 1 ";
			return jdbcTemplate.queryForList(sql,new Object[]{yjlevel,selpoint});			
		}else if("2".equals(selpoint)){
			//作业项目
			sql = " select id,name,parent_id,(select count(*) from yw_parameter_info_yj piy INNER JOIN yw_parameter_info pi ON pi.pi_id = piy.pi_id where piy.dm_point_id like CONCAT(id,'%') and piy.yj_level = ? and pi.point_type = ?) as pcount from dm_point where length(id) <= 3 ";
			return jdbcTemplate.queryForList(sql,new Object[]{yjlevel,selpoint});
		}else if("3".equals(selpoint)){
			//作业问题
			sql = " select id,name,parent_id,(select count(*) from yw_parameter_info_yj piy INNER JOIN yw_parameter_info pi ON pi.pi_id = piy.pi_id where piy.dm_point_id like CONCAT(id,'%') and piy.yj_level = ? and pi.point_type = ?) as pcount from dm_point ";
		}
		return jdbcTemplate.queryForList(sql,new Object[]{yjlevel,selpoint});
	}
	
	@SuppressWarnings("rawtypes")
	public List querySysPointListByYjLevel(Integer userId,String yjlevel,String selpoint){
		String sql = " select piy.dm_point_id from yw_parameter_info pi INNER JOIN yw_parameter_info_yj piy ON pi.pi_id = piy.pi_id "
				 	+ " where business_type = 1 and pi.user_id = ? and yj_level = ? and point_type = ?  ";
		return jdbcTemplate.queryForList(sql,new Object[]{userId,yjlevel,selpoint});		
	}
	
	@SuppressWarnings("rawtypes")
	public Map queryOldYjcs(Integer userId){
		try {
			String sql = " select * from yw_parameter_info where business_type = 1 and user_id = ? ";
			Map map = jdbcTemplate.queryForMap(sql,new Object[]{userId});	
			return map;			
		} catch (Exception e) {
			return null;	
		}		
	}
	
	public void updateYjcs(Integer userId,String selpoint){
		String sql = " UPDATE yw_parameter_info SET point_type = ? WHERE business_type = 1 and user_id = ? ";
		jdbcTemplate.update(sql,new Object[]{selpoint,userId});		
	}
	
	public void deleteYjcsDetail(Long pId){
		String sql = " DELETE FROM yw_parameter_info_yj WHERE pi_id = ?";
		jdbcTemplate.update(sql,new Object[]{pId});		
	}
	
	public void deleteYjcsDetail(Long pId,String yjlevel){
		String sql = " DELETE FROM yw_parameter_info_yj WHERE pi_id = ? and yj_level = ? ";
		jdbcTemplate.update(sql,new Object[]{pId,yjlevel});		
	}
	
	public void addYjcsDetail(String dm_point_id,String yjlevel,Long pId){
		String sql = " insert into yw_parameter_info_yj (yj_level,dm_point_id,pi_id) VALUES (?,?,?) ";
		jdbcTemplate.update(sql,new Object[]{yjlevel,dm_point_id,pId});			
	}
	
	@SuppressWarnings("rawtypes")
	public List querySysOrganizationByUser(SysUser user){
		String sql = " select id,name from dm_organization where parent_id = (select organization_id from yw_user_organization where user_id = ?)  ";
		return jdbcTemplate.queryForList(sql,new Object[]{user.getId()});			
	}
	
	@SuppressWarnings("rawtypes")
	public Map getScore(SysUser user,String yjlevel,String oId,String bdate,String edate){
		try {
			String sql = " select REPLACE(IFNULL(sum(deduct_score),0),'-','') as dScore from ( "
							+ " select dm_point.name from yw_parameter_info pi INNER JOIN yw_parameter_info_yj piy ON pi.pi_id = piy.pi_id "
							+ " INNER JOIN dm_point ON piy.dm_point_id = dm_point.id "
							+ " where pi.user_id = ? and piy.yj_level = ? "
							+ " ) a INNER JOIN ( "
							+ " select doe.* from dm_organization syso INNER JOIN dm_driver_org dro ON syso.id = dro.group_id "
							+ " INNER JOIN yw_driver_operation_evaluation doe ON dro.driver_id = doe.driver_id "
							+ " where syso.id like ? and LENGTH(syso.id) = 7 and date between ? and ? "
							+ " ) b ON a.`name` = b.index_name ";
			Map map = jdbcTemplate.queryForMap(sql,new Object[]{user.getId(),yjlevel,oId,bdate,edate});	
			return map;			
		} catch (Exception e) {
			return null;	
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Map getSysOrganization(SysUser user){
		try {
			String sql = " select id,name from dm_organization where id = (select organization_id from yw_user_organization where user_id = ?) ";
			Map map = jdbcTemplate.queryForMap(sql,new Object[]{user.getId()});	
			return map;			
		} catch (Exception e) {
			return null;	
		}		
	}
	
	
	@SuppressWarnings("rawtypes")
	public List querySysOrganizationBySelId(String selMarkId){
		String sql = " select id,name from dm_organization where parent_id = ? ";
		return jdbcTemplate.queryForList(sql,new Object[]{selMarkId});			
	}
	
	@SuppressWarnings("rawtypes")
	public Map getParameterInfoByUser(SysUser user){
		try {
			String sql = " select * from yw_parameter_info where business_type = 2 and user_id = ? ";
			Map map = jdbcTemplate.queryForMap(sql,new Object[]{user.getId()});	
			return map;			
		} catch (Exception e) {
			return null;	
		}			
	}
	
	@SuppressWarnings("rawtypes")
	public List getParameterInfoZTByUser(Long pi_id){
		String sql = " select DISTINCT zd_question from yw_parameter_info_zt where pi_id = ? ";
		return jdbcTemplate.queryForList(sql,new Object[]{pi_id});			
	}
	
	public void saveZtFx(Integer userId,String deductScore,String dxquestion){
		String sql = " UPDATE yw_parameter_info SET deduct_score = ?,dx_question = ? WHERE business_type = 2 and user_id = ? ";
		jdbcTemplate.update(sql,new Object[]{deductScore,dxquestion,userId});				
	}
	
	@SuppressWarnings("rawtypes")
	public List getSysPointByZtFxList(String selName){
		String sql = " select id,name,parent_id,(select count(*) from yw_parameter_info_zt piy "
					+ " INNER JOIN yw_parameter_info pi ON pi.pi_id = piy.pi_id where piy.zd_question = ? "
					+ " and piy.dm_point_id like CONCAT(id,'%') and pi.point_type = 3) as pcount from dm_point ";
		return jdbcTemplate.queryForList(sql,new Object[]{selName});		
	}
	
	@SuppressWarnings("rawtypes")
	public List querySysPointListByZT(Integer userId,String zd_question){
		String sql = " select piy.dm_point_id from yw_parameter_info pi INNER JOIN yw_parameter_info_zt piy ON pi.pi_id = piy.pi_id "
					+ " where business_type = 2 and pi.user_id = ? and zd_question = ? and point_type = 3  ";
		return jdbcTemplate.queryForList(sql,new Object[]{userId,zd_question});			
	}
	
	public void delParameterInfoZt(Long pi_id,String zdq1){
		String sql = " delete from yw_parameter_info_zt where zd_question = ? and pi_id = ? ";
		jdbcTemplate.update(sql,new Object[]{zdq1,pi_id});			
	}
	
	public void addParameterInfoZt(Long pi_id,String zdq1,String dm_point_id){
		String sql = " insert into yw_parameter_info_zt (zd_question,dm_point_id,pi_id) values (?,?,?) ";
		jdbcTemplate.update(sql,new Object[]{zdq1,dm_point_id,pi_id});			
	}
	
	public void updateParameterInfoZt(Long pi_id,String oldzdq1,String zdq1){
		String sql = " UPDATE yw_parameter_info_zt SET zd_question = ? WHERE zd_question = ? and pi_id = ? ";
		jdbcTemplate.update(sql,new Object[]{zdq1,oldzdq1,pi_id});			
	}
	
	@SuppressWarnings("rawtypes")
	public Map getZtFx(Integer userId){
		try {
			String sql = " select * from yw_parameter_info WHERE business_type = 2 and user_id = ? ";
			Map map = jdbcTemplate.queryForMap(sql,new Object[]{userId});	
			return map;			
		} catch (Exception e) {
			return null;	
		}			
	}
	
	public void insertParameterInfoZt(Long pi_id,String zd_question){
		String sql = " insert into yw_parameter_info_zt (pi_id,zd_question) values (?,?) ";
		jdbcTemplate.update(sql,new Object[]{pi_id,zd_question});			
	}

}
