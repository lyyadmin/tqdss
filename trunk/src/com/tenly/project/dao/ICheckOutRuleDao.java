package com.tenly.project.dao;

import java.util.List;
import java.util.Map;

import com.tenly.project.bean.CheckOutRule;
import com.tenly.project.bean.ItemsMeasures;
import com.tenly.project.bean.LoseCause;

public interface ICheckOutRuleDao {

	void saveRule(CheckOutRule rule)throws Exception;

	List<Map<String, Object>> queryForItems()throws Exception;

	List<Map<String, Object>> findAllCondition()throws Exception;

	void addCondtion(CheckOutRule rule)throws Exception;

	void checkOutRuleDao(CheckOutRule rule)throws Exception;

	void delCondition(CheckOutRule rule)throws Exception;

	List<Map<String, Object>> findAllCause()throws Exception;

	void updateCause(LoseCause loseCause)throws Exception;

	List<Map<String, Object>> findAllExceptionCause()throws Exception;

	List<Map<String, Object>> getLoseFileCause()throws Exception;

	List<Map<String, Object>> findAllItem()throws Exception;

	void saveItems(ItemsMeasures[] rowObj)throws Exception;

	void saveFileExceptionCause(LoseCause cause)throws Exception;

	List querySysPointListByYjLevel( String yjlevel,
			String selpoint);

	Map queryOldYjcs(Integer userId)throws Exception;

	void updateYjcs(Integer userId, String selpoint)throws Exception;

	void deleteYjcsDetail(Long pId, String yjlevel)throws Exception;

	void deleteYjcsDetail(Long pId)throws Exception;

	void addYjcsDetail(String string, String yjlevel, Long pId,String measure)throws Exception;

	void delCauseRow(String loseID)throws Exception;

	List getSysPointList(String selpoint, String yjlevel)throws Exception;

	List<Map<String, Object>> findAllParameterInfo()throws Exception;

	void deleteYjLevel(String yjlevel)throws Exception;

	void queryByYjLevel(String yjlevel, String pointId)throws Exception;

	void addPoint(String yjlevel, String pointId, String measure,String selpoint)throws Exception;

	void deleteAll()throws Exception;

}
