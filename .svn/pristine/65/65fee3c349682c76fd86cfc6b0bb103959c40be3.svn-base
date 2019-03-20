package com.tenly.project.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.tenly.project.bean.CheckoutException;
import com.tenly.project.bean.DataLose;
import com.tenly.project.bean.LKJWithDataModal;
import com.tenly.project.bean.YwDriverOperationEvaluation;
import com.tenly.system.bean.PageBean;
import com.tenly.system.bean.SysUser;

public interface ICheckOutDao {

	Map<String, Object> queryCurrentCheckOut(String driverID);

	List<Map<String, Object>> queryCurrentDriverAnalyData(
			String driverId,String startDate,String endDate,PageBean pageBean) throws Exception;

	List<Map<String, Object>> itemsDetailPro(YwDriverOperationEvaluation checkOut)throws Exception;

	List<Map<String, Object>> currentDriverStartAndEnd(String driverId,String fsjh,
			String startDate, String endDate)throws Exception;

	List<Map<String, Object>> getLoseCause();

	List<Map<String, Object>> getCurrentDriverCheckOutResult(String driverId)throws Exception;

	List<Map<String, Object>> getCondition()throws Exception;

	List<Map<String, Object>> getExceptionCause()throws Exception;

	List<Map<String, Object>> findAllItemsProblem()throws Exception;

	List<Map<String, Object>> getCheckOutResultDetailAndLevel()throws Exception;

	void saveDataLoseCause(String sjh, String traincheci, String file_lose_cause)throws Exception;

	Map<String, Object> queryDriverSecondRequest(String ondate,String offdate,String driver_id)throws Exception;

	Map<String, Object> findDriverOnCheckOutTime(String driverId)throws Exception;

	List<Map<String, Object>> testCurrentDriverStartAndEnd(String driverId);

	List<Map<String, Object>> testQueryCurrentDriverAnalyData(String driverId, PageBean pageBean);

	List<Map<String, Object>> findAllPositionToDriverId()throws Exception;

	List<Map<String, Object>> findCodeRuleByJiWuDuanCode(String jiwuduan_code)throws Exception;

	Map<String, Object> queryDriverIsExsits(String string, String driver_code)throws Exception;

	List<Map<String, Object>> findAllItem()throws Exception;

	void saveExitsPro(String driver_id)throws Exception;

	void saveDataLose(List<DataLose> dataLoseList, String driverId,
			String checkInAndOutTimes,String systemCurrentDate,String checkoutResult)throws Exception;

	void saveStartAndEndStation(List<Map<String, Object>> ol, String driverId,
			String checkInAndOutTimes ,String systemCurrentDate)throws Exception;

	void saveAnasysResult(List<Map<String, Object>> rows,String systemCurrentDate)throws Exception;

    void saveExceptionData(List<YwDriverOperationEvaluation> ywDriverOperationEvaluations)throws Exception;

	List<LinkedHashMap<String, Object>> findHostoryRecordWithLKJ(
    		String page, String pageSize, String date, String time,
			String train_batch_no, String driver_id, String region)throws Exception;

    void saveAnasysisResult(String driverId,String noteStartDateAndTermial)throws Exception;

    List<Map<String,Object>> findItemProblemDetail(YwDriverOperationEvaluation checkOut)throws Exception;

	void saveForTmpToOperationException(String driver_id)throws Exception;

	Map<String, Object>  queryByFuSiJiCode(String driverId)throws Exception;

    void saveComputerIp(SysUser user, String jwcode,String ip,String reName) throws Exception;

    void deleteTempTblDataByDriverCode(String diriverId)throws Exception;

    List<String> findJWDChuTuiQinPlace(String str)throws Exception;

    List<Map<String,Object>> findAllJWDPlace();

    List<Map<String,Object>> findAllCJByDriverIdAndDate(String driverId, String onTime, String tmp);

    List<Map<String,Object>> findAllCJByDriverIdAndDateWithORCL(String driverId, String onTime, String tmp);

    List<Map<String,Object>> findAllPoint()throws Exception;

    List<Map<String,Object>> getTableTitile(String date, String time, String train_batch_no);
}
