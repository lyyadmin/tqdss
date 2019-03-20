package com.tenly.project.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.tenly.project.bean.CheckoutException;
import com.tenly.project.bean.DataLose;
import com.tenly.project.bean.YwDriverOperationEvaluation;
import com.tenly.system.bean.PageBean;
import com.tenly.system.bean.SysUser;

public interface ICheckOutService {

	Map<String, Object> queryCurrentCheckOut(String driverID);

	void queryCurrentDriverAnalyData(String trim,PageBean pageBean) throws Exception;

	List<Map<String, Object>> itemsDetailPro(YwDriverOperationEvaluation checkOut)throws Exception;

	List<Map<String, Object>> getLoseCause();

	List<Map<String, Object>> getCurrentDriverCheckOutResult(String driverId)throws Exception;

	List<Map<String, Object>> getCondition()throws Exception;

	List<Map<String, Object>> getExceptionCause()throws Exception;

	List<Map<String, Object>> findAllItemsProblem()throws Exception;

	List<Map<String, Object>> getCheckOutResultDetailAndLevel()throws Exception;

	void saveDataLoseCause(String sjh, String traincheci, String file_lose_cause)throws Exception;

	String queryDriverSecondRequest(String driver_id)throws Exception;

	List<Map<String, Object>> findAllPositionToDriverId()throws Exception;

	List<Map<String, Object>> findCodeRuleByJiWuDuanCode(String jiwuduan_code)throws Exception;

	Map<String, Object> queryDriverIsExsits(String string, String driver_code)throws Exception;

	void testQueryCurrentDriverAnalyDataGrid(String trim, PageBean pageBean)throws Exception;

	List<Map<String, Object>> testCurrentDriverStartAndEnd(String driverId)throws Exception;

	void saveAll(List<DataLose> dataLoseList,
			List<Map<String, Object>> ol, String driverId,String checkInAndOutTimes,
			List<Map<String, Object>> rows,String checkoutResult)throws Exception;


    void saveExceptionData(List<YwDriverOperationEvaluation> ywDriverOperationEvaluations)throws Exception;

	Map<String,Object> getDriverJLWithStartAndEndStation( String driverId) throws Exception;

	List<LinkedHashMap<String, Object>> findHostoryRecordWithLKJ(String page, String pageSize
			, String date, String time, String train_batch_no, String driver_id, String region) throws Exception;


    void saveForTmpToOperationException(String driver_id)throws Exception;

    String queryCheckining(String driver_code)throws Exception;

    String sendMessages(String driverCode, String sendContent)throws Exception;

    void saveComputerIp(SysUser user,String jwcode,String ip,String reName)throws Exception;

    void deleteTempTblDataByDriverCode(List<YwDriverOperationEvaluation> maps)throws Exception;

    List<Map<String,Object>> getLKJColumsNames(String date, String time, String train_batch_no);
}
