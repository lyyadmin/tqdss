package com.tenly.project.service;

import java.util.List;
import java.util.Map;

import com.tenly.project.bean.CheckOutRule;
import com.tenly.project.bean.ItemsMeasures;
import com.tenly.project.bean.LoseCause;

public interface ICheckOutRuleService {

	void saveRule(CheckOutRule rule)throws Exception;

	List<Map<String, Object>> queryForItems()throws Exception;

	List<Map<String, Object>> findAllCondition()throws Exception;

	void addCondtion(CheckOutRule rule)throws Exception;

	void updateCondtion(CheckOutRule rule)throws Exception;

	void delCondition(CheckOutRule rule)throws Exception;

	List<Map<String,Object>> findAllCause()throws Exception;

	void updateCause(LoseCause loseCause)throws Exception;

	List<Map<String, Object>> findAllExceptionCause()throws Exception;

	List<Map<String, Object>> getLoseFileCause()throws Exception;

	List<Map<String, Object>> findAllItem()throws Exception;

	void saveItems(ItemsMeasures[] rowObj)throws Exception;

	void saveFileExceptionCause(LoseCause cause)throws Exception;

	String querySysPointListByYjLevel( String yjlevel,
			String selpoint);

	void saveYjcs( String yjlevel, String selpoint, String pIdstr,String measure)throws Exception;

	void delCauseRow(String loseID)throws Exception;

	List getSysPointList(String selpoint, String yjlevel)throws Exception;
}
