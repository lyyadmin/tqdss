package com.tenly.project.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tenly.project.bean.CheckOutRule;
import com.tenly.project.bean.ItemsMeasures;
import com.tenly.project.bean.LoseCause;
import com.tenly.project.dao.ICheckOutRuleDao;

@Repository
public class CheckOutRuleDaoImpl implements ICheckOutRuleDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 保存过滤条件
	 */
	@Override
	public void saveRule(CheckOutRule rule) throws Exception {
			String sql	= "insert into yw_checkout_condition(con_value,con_symbol_first,"
					+ "con_name,con_symbol_sedond,con_value_end,other)values("+rule.getConValue()+",'"+rule.getConSymbolFirst()+"'"
							+ ",'"+rule.getConName().split("_")[1]+"','"+rule.getConSymbolSedond()+"',"+rule.getConValueEnd()+","+rule.getOther()+")";
			jdbcTemplate.execute(sql);
	}

	/**
	 * 查询项点
	 */
	@Override
	public List<Map<String, Object>> queryForItems() throws Exception {
			String sql = "select dd.driver_id,dd.index_name from yw_driver_operation_evaluation dd GROUP BY index_name ORDER BY dd.date asc";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 查询所有过滤条件
	 */
	@Override
	public List<Map<String, Object>> findAllCondition() throws Exception {
		String sql = "select condtion_id conditionId,con_name conName,con_symbol_first conSymbolFirst"
				+ ",con_value conValue,con_symbol_sedond conSymbolSedond,con_value_end conValueEnd"
				+ ",con_type conType,other from yw_checkout_condition";
		return jdbcTemplate.queryForList(sql);
	}
	/**
	 * 保存规则集
	 */
	@Override
	public void addCondtion(CheckOutRule rule) throws Exception {
		String sql = "insert into yw_checkout_condition(con_value,con_symbol_first,con_name,con_type,other)values("
				+ ""+rule.getConValue()+",'"+rule.getConSymbolFirst()+"','"+rule.getConName()+"','"+rule.getConType()+"'"
						+ ",'"+rule.getOther()+"')";
		jdbcTemplate.execute(sql);
	}

	/**|
	 * 
	 * 更新
	 */
	@Override
	public void checkOutRuleDao(CheckOutRule rule) throws Exception {
		String sql = " update yw_checkout_condition set con_value="+rule.getConValue()+","
				+ "con_symbol_first='"+rule.getConSymbolFirst()+"',"
						+ "con_name='"+rule.getConName()+"',"
								+ "con_type='"+rule.getConType()+"',"
										+ "other='"+rule.getOther()+"' where condtion_id ="+rule.getConditionId();
		jdbcTemplate.update(sql);
	}

	/**
	 * 删除规则集
	 */
	@Override
	public void delCondition(CheckOutRule rule) throws Exception {
		String sql = "delete from yw_checkout_condition where condtion_id="+rule.getConditionId();
		jdbcTemplate.execute(sql);
	}
	/**
	 * 文件缺失原因和配置异议
	 */
	@Override
	public List<Map<String, Object>> findAllCause() throws Exception {
		String sql = "select * from yw_losecause order by types";
		return jdbcTemplate.queryForList(sql);
	}
	/**
	 * 文件缺失原因和配置异议
	 */
	@Override
	public void updateCause(LoseCause loseCause) throws Exception {
		String sql = "update yw_losecause set cause_name='"+loseCause.getCause_name()+"',"
				+ "types='"+loseCause.getTypes()+"',other='"+loseCause.getOther()+"'"
				+ "where lose_id="+loseCause.getLose_id();
		jdbcTemplate.execute(sql);
	}
	/**
	 * 对分析有异议原因
	 */
	@Override
	public List<Map<String, Object>> findAllExceptionCause() throws Exception {
		String sql = "select * from yw_losecause where types='2'";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> getLoseFileCause() throws Exception {
		String sql = "select * from yw_losecause where types='1'";
		return jdbcTemplate.queryForList(sql);
	}
	/**
	 * 查询所有的项点
	 */
	@Override
	public List<Map<String, Object>> findAllItem() throws Exception {
		String sql = "select param.yj_id,param.yj_level,param.sys_point_id,param.measure,param.point_type,dm.name"
				+ " from yw_parameterinfo_checkout param"
				+ " inner join dm_point dm on param.sys_point_id=dm.id";
		return jdbcTemplate.queryForList(sql);
	}
	/**
	 * 处理项点问题的处理结果
	 */
	@Override
	public void saveItems(ItemsMeasures[] rowObj) throws Exception {
		StringBuilder sb = null;
		if(rowObj.length>0){
			for(ItemsMeasures items:rowObj){
				sb = new StringBuilder();
				sb.append("update yw_jw_item set item_measures='"+items.getItem_measures()+"' "
						+ "where item_id="+items.getItem_id());
				jdbcTemplate.execute(sb.toString());
			}
		}
	}
	/**
	 * 保存文件缺失原因
	 */
	@Override
	public void saveFileExceptionCause(LoseCause cause) throws Exception {
		String sql = "insert into yw_losecause(cause_name,types,other) values(?,?,?)";
		jdbcTemplate.update(sql, new Object[]{cause.getCause_name(),cause.getTypes(),cause.getOther()});
	}

	@Override
	public List querySysPointListByYjLevel( String yjlevel,String selpoint) {
		String sql = " select piy.sys_point_id from yw_parameterinfo_checkout piy where yj_level=? and point_type=?";
	return jdbcTemplate.queryForList(sql,new Object[]{yjlevel,selpoint});
	}


	@SuppressWarnings("rawtypes")
	public Map queryOldYjcs(Integer userId)throws Exception{
			String sql = " select * from yw_parameter_info where business_type = 3 and user_id = ? ";
			Map map = jdbcTemplate.queryForMap(sql,new Object[]{userId});	
			return map;
	}
	
	/**
	 * 更新信息表
	 */
	public void updateYjcs(Integer userId,String selpoint){
		String sql = " UPDATE yw_parameter_info SET point_type = ? WHERE business_type = 3 and user_id = ?";
		jdbcTemplate.update(sql,new Object[]{selpoint,userId});		
	}
	/**
	 * 添加项点
	 */
	public void addYjcsDetail(String sys_point_id,String yjlevel,Long pId,String measure){
		String sql = " insert into yw_parameterinfo_checkout (yj_level,sys_point_id,pi_id,measure) VALUES (?,?,?,?) ";
		jdbcTemplate.update(sql,new Object[]{yjlevel,sys_point_id,pId,measure});			
	}

	/**
	 * 删除关联
	 */
	@Override
	public void deleteYjcsDetail(Long pId, String yjlevel) throws Exception {
		String sql = " DELETE FROM yw_parameterinfo_checkout WHERE pi_id = ? and yj_level = ? ";
		jdbcTemplate.update(sql,new Object[]{pId,yjlevel});
	}
	
	/**
	 * 删除改变了项点的记录
	 */
	@Override
	public void deleteYjcsDetail(Long pId) throws Exception {
		String sql = " DELETE FROM yw_parameterinfo_checkout WHERE pi_id = ?";
		jdbcTemplate.update(sql,new Object[]{pId});		
	}
	/**
	 * 删除文件缺失和有无异议的数据
	 */
	@Override
	public void delCauseRow(String loseID) throws Exception {
		String sql = "delete from yw_losecause where lose_id="+Integer.valueOf(loseID);
		jdbcTemplate.update(sql);
	}

	@Override
	public List getSysPointList(String selpoint, String yjlevel)
			throws Exception {
		String sql = "";
		if("1".equals(selpoint)){
			//作业场景
			sql = " select id,name,parent_id pId from dm_point where length(id) = 1 ";
//			return jdbcTemplate.queryForList(sql,new Object[]{yjlevel,selpoint});
		}else if("2".equals(selpoint)){
			//作业项目
			sql = " select id,name,parent_id pId from dm_point where length(id) <= 3 and length(id)>1 ";
//			return jdbcTemplate.queryForList(sql,new Object[]{yjlevel,selpoint});
		}else if("3".equals(selpoint)){
			//作业问题
			sql = " select id,name,parent_id pId from dm_point where "
					+ "length(id)>3 ";
		}
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql,new Object[]{});
		
		return queryForList;
	}

	@Override
	public List<Map<String, Object>> findAllParameterInfo() throws Exception {
		String findAllSQL = "select * from yw_parameterinfo_checkout";
		return jdbcTemplate.queryForList(findAllSQL);
	}
	/**
	 * 删除这个级别的项点
	 */
	@Override
	public void deleteYjLevel(String yjlevel) throws Exception {
		String sql = "delete from yw_parameterinfo_checkout where yj_level="+Integer.valueOf(yjlevel);
		jdbcTemplate.execute(sql);
	}
	/**
	 * 查询 不等于级别，等于项点的删除
	 */
	@Override
	public void queryByYjLevel(String yjlevel, String pointId) throws Exception {
		String sql = "delete from yw_parameterinfo_checkout "
				+ "where yj_level<>"+Integer.valueOf(yjlevel) +" and sys_point_id="+Integer.valueOf(pointId);
		jdbcTemplate.execute(sql);
	}

	/**
	 * 添加	参数配置
	 */
	@Override
	public void addPoint(String yjlevel, String pointId, String measure,String selpoint)
			throws Exception {
		String sql = "insert into yw_parameterinfo_checkout(yj_level,sys_point_id,measure,point_type)"
				+ "values(?,?,?,?)";
		jdbcTemplate.update(sql, new Object[]{Integer.valueOf(yjlevel),Integer.valueOf(pointId),
				measure,Integer.valueOf(selpoint)});
	}
	/*删除项点
	 * (non-Javadoc)
	 * @see com.tenly.project.dao.ICheckOutRuleDao#deleteAll()
	 */
	@Override
	public void deleteAll() throws Exception {
		String sql = "delete from yw_parameterinfo_checkout";
		jdbcTemplate.execute(sql);
	}
	
}






