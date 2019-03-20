package com.tenly.project.service;

import java.util.List;
import java.util.Map;

import com.tenly.project.bean.YwCheckutAnasysResult;
import com.tenly.system.bean.PageBean;

public interface ICheckOutBuffetQueryService {
	void findAll(PageBean pb, String page, String rows);

	List<Map<String, Object>> findAllItemName();

	List<Map<String, Object>> findItemsHabitsAna(String startDate,String enddate,String driver_code);

	Map queryItemConditionOne(String start, String end, String taskScene,
			String taskItem, String taskItemPoint,String taskProblem, String driver_code)throws Exception;

	Map QueryitemConditionTwo(
			String startDate, String endDate,
			String driver_code, String taskScene,
			String taskItem, String taskItemPoint, String taskProblem)throws Exception;

	Map queryItemByLevel(String itemValue, String level)throws Exception;

    List<Map<String,Object>> getCheckOutConfirmTime(String driverCode,String startDate,String endDate)throws Exception;

	List<Map<String,Object>> getCurrentDriverStartAndEnd(String driverCode, String sys_date)throws Exception;

	List<Map<String,Object>> getCurrentDriverAnalyData(String driverCode,
													   String ondutytime,String  offdutytime)throws Exception;

    List<Map<String,Object>> getCurrentRowDetail(YwCheckutAnasysResult yar)throws Exception;

	Map getResultInfo(String driver_id, String ondutytime,String offdutytime)throws Exception;

    List<Map<String,Object>> getViolationDetail(
    		String startDate, String endDate, String driver_code,String item_code_name,String problem_level,
			String taskScene,String taskItem, String taskItemPoint, String taskProblem)throws Exception;
    
    List<Map<String,Object>> getCommonDetail(
    		String startDate, String endDate, String driver_code,String field_name,String field_value,String problem_level,
    		String taskScene,String taskItem, String taskItemPoint, String taskProblem)throws Exception;

	List<Map<String,Object>> queryProblemViolationGridDetail(
			String startDate, String endDate, String driver_code, String item_code)throws Exception;

	List<Map<String,Object>> queryProblemCommonGridDetail(
			String startDate, String endDate, String driver_code, String item_code,String field_name,String field_value)throws Exception;

	List<Map<String, Object>> getTaskEvaluate(String driver_code,
    		String startDate, String endDate,
			String taskScene, String taskitem, String taskItemPoint, String taskProblem)throws Exception;

    List<Map<String,Object>> getEvaluateContentByDriverCode(String driver_code,String evaldate);

	Map<String,Object> getChuTuiQinDate(String driver_code)throws Exception;

    List<Map<String,Object>> getTuiQinDate(String driver_code, String ondutytime)throws Exception;

    void findAllForChuTuiQinInfo(
    		PageBean pageBean, String ondutytime, String offdutytime, String driverCode)throws Exception;
}
