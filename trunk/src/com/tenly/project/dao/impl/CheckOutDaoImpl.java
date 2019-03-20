package com.tenly.project.dao.impl;

import java.sql.*;
import java.util.*;

import com.tenly.common.projecttools.*;
import com.tenly.system.bean.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tenly.project.bean.CheckoutException;
import com.tenly.project.bean.DataLose;
import com.tenly.project.bean.LKJWithDataModal;
import com.tenly.project.bean.YwDriverOperationEvaluation;
import com.tenly.project.dao.ICheckOutDao;
import com.tenly.system.bean.PageBean;

@SuppressWarnings("all")
@Repository
public class CheckOutDaoImpl implements ICheckOutDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> queryCurrentCheckOut(String driverID) {
        String sqls = "select b.driver_id,b.driver_name from (" +
                "select driver_id,driver_name from dm_driver_org group by driver_id) b"
                + " where b.driver_id=?";
        Map<String, Object> queryForMap = null;
        try {
            queryForMap = jdbcTemplate.queryForMap(sqls, new Object[]{driverID});
        } catch (EmptyResultDataAccessException e) {
            queryForMap = null;
        }
        return queryForMap;
    }

    /**
     * ----------------------------对接运安处理乘务员对本次出退勤之间各个项点的问题--------------------------------
     */
    public List<Map<String, Object>> queryCurrentDriverAnalyData(
            String driverId, String startDate, String endDate, PageBean pageBean) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("select f.*,");
        sb.append("(select name from dm_point where id=f.scenario_code) new_scenario_code,");
        sb.append("(select name from dm_point where id=f.index_type_code) new_index_type_code,");
        sb.append("(select name from dm_point where id=f.index_code) new_index_code,");
        sb.append("(select name from dm_point where id=f.problem_code) new_problem_code");
        sb.append(" from (");
        sb.append(" select c.*,b.level from (");
        sb.append(" select  a.driver_id,a.scenario_code,a.index_type_code,a.index_code,a.problem_code,count(problem_code) count_problem_code ");
        sb.append(" from yw_driver_operation_evaluation a ");
        sb.append(" where startTime>='"+startDate+"' and startTime<='"+endDate+"'");
        sb.append(" and driver_id='"+driverId+"' and qualified='违标'");
        sb.append(" group by a.scenario_code,a.index_type_code,a.index_code,a.problem_code,a.driver_id");
        sb.append(" ) c");
        sb.append(" inner join dm_problem_level b on c.problem_code=b.id");
        sb.append(" ) f ORDER BY level");
        List<Map<String, Object>> anasyResult = jdbcTemplate.queryForList(sb.toString());
        int count = 1;
        for (Map<String, Object> map : anasyResult) {
            map.put("id", count);
            count++;
        }
        return anasyResult;
    }

    /**
     * 查询所有项点名称
     */
    @Override
    public List<Map<String, Object>> findAllItem() throws Exception {
        String sql = "SELECT id,name,parent_id,type FROM dm_point";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * |
     * 查询项点分析的违标详情
     */
    public List<Map<String, Object>> itemsDetailPro(YwDriverOperationEvaluation checkOut)
            throws Exception {
        String[] splits = checkOut.getNoteStartDateAndTermial().split("_");
        StringBuilder sb = new StringBuilder();
        sb.append("select a.level,b.*,");
        sb.append(" (select name from dm_point where id=b.scenario_code) new_scenario_code,");
        sb.append(" (select name from dm_point where id=b.index_type_code) new_index_type_code,");
        sb.append(" (select name from dm_point where id=b.index_code) new_index_code,");
        sb.append(" (select name from dm_point where id=b.problem_code) new_problem_code");
        sb.append(" from dm_problem_level a");
        sb.append(" JOIN");
        sb.append(" (");
        sb.append(" select * from  yw_checkout_operation_exception_tmp where");
        sb.append(" driver_id = ?");
        sb.append(" AND startTime >= ?");
        sb.append(" AND startTime <= ?");
        sb.append(" and scenario_code=?");
        sb.append(" and index_type_code=?");
        sb.append(" and index_code=?");
        sb.append(" AND problem_code =?");
        sb.append(" AND qualified = ?");
        sb.append(" ) b");
        sb.append(" on a.id=b.problem_code and a.level=?");
        List<Map<String, Object>> list =jdbcTemplate.queryForList(sb.toString(),
                new Object[]{
                        checkOut.getDriver_id(),
                        splits[0],splits[1],
                        checkOut.getScenario_code(),
                        checkOut.getIndex_type_code(),
                        checkOut.getIndex_code(),
                        checkOut.getProblem_code(),"违标",
                        checkOut.getLevel()
                });

        return list;
    }

    /**
     * 获取lkj中标表的数据，始发终到数据
     */
    public List<Map<String, Object>> currentDriverStartAndEnd(String driverId,String fsjh,
                                                              String startDate, String endDate) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * from yw_data_mid where shifashijian>='");
        sb.append(startDate+ "' and zhongdaoshijian<='" + endDate + "'");
        sb.append(" and (sjh='" + driverId + "' or fsjh='"+driverId+"')");
        sb.append(" ORDER  by zhongdaoshijian desc");
        return jdbcTemplate.queryForList(sb.toString());
    }

    /**
     * 缺失原因
     */
    public List<Map<String, Object>> getLoseCause() {
        String sql = "select * from yw_losecause";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 退勤结果计算
     */
    public List<Map<String, Object>> getCurrentDriverCheckOutResult(String driverId) throws Exception {
        //查询出当前 司机的退勤的多少趟车或者说文件
        List<Map<String, Object>> queryForList = getMiddleObj(driverId);
        //统计总分数
        StringBuilder sb = new StringBuilder();
        sb.append("select ABS(sum(sumDeductScore)) sumDeductScore from (");
        sb.append("select driverId,scenarioName,indexTypeName,index_name indexName,");
        sb.append("sum(sumDeductScore) sumDeductScore,count(countDeductScore) countDeductScore ");
        sb.append("from (SELECT cc.driver_id driverId,");
        sb.append(" cc.scenario_name scenarioName,cc.index_type_name indexTypeName,cc.index_name,");
        sb.append("CAST(cc.deduct_score AS DECIMAL(10,2)) sumDeductScore,");
        sb.append("CAST(cc.deduct_score AS DECIMAL(10,2)) countDeductScore");
        sb.append(" FROM yw_driver_operation_evaluation cc");
        sb.append(" WHERE cc.date >='" + (String) (queryForList.get(0).get("min_driverdate")) + "' "
                + "and  cc.date<='" + (String) (queryForList.get(0).get("max_driverdate")) + "' ");
        sb.append("AND cc.driver_id = '" + driverId + "' AND CAST(deduct_score AS DECIMAL(10,2))<=0");
        sb.append(") tbl_result group by driverId,scenarioName,indexTypeName,index_name) sum_deduct_score");
        return jdbcTemplate.queryForList(sb.toString());
    }

    /**
     * 查询计算条件
     */
    public List<Map<String, Object>> getCondition() throws Exception {
        String sql = "select * from yw_checkout_condition";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 通过司机的转出时间查询当前司机的轨迹
     *
     * @param driverId
     * @return
     */
    public List<Map<String, Object>> getMiddleObj(String driverId) {
        //查询出当前 司机的退勤的多少趟车或者说文件
        String middleSql = "select max(driverdate) max_driverdate,min(driverdate) min_driverdate from yw_data_mid "
                + "where zhuanchushijian=(select max(zhuanchushijian) max_zhuanchushijian "
                + "from yw_data_mid where sjh='" + driverId + "') and sjh='" + driverId + "'";
        List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(middleSql);
        return queryForList;
    }

    /**
     * 获取司机对于当前分析有异议的原因
     */
    public List<Map<String, Object>> getExceptionCause() throws Exception {
        String sql = "select * from yw_losecause where types='2'";
        return jdbcTemplate.queryForList(sql);
    }

    /*
     * 获取作业项点所有不为空的处理措施
     * (non-Javadoc)
     * @see com.tenly.project.dao.ICheckOutDao#findAllItemsProblem()
     */
    @Override
    public List<Map<String, Object>> findAllItemsProblem() throws Exception {
        String sql = "select * from yw_jw_item where item_measures is not null";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 查询退勤 和级别
     */
    @Override
    public List<Map<String, Object>> getCheckOutResultDetailAndLevel()
            throws Exception {
        String sql = " select * from dm_point item inner join "
                + "yw_parameterinfo_checkout pc on item.id = pc.sys_point_id  group by item.name";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 保存文件缺失原因
     */
    @Override
    public void saveDataLoseCause(String sjh, String traincheci,
                                  String file_lose_cause) throws Exception {
            String sql = "insert into yw_checkout_filelose_cause(" +
                "driver_id,tran_num,file_lose_cause,save_date)values(?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{sjh, traincheci, file_lose_cause, Util.getSystemCurrentDate()});
    }

    /**
     * 查询司机上次的退勤时间
     */
    @Override
    public Map<String, Object> queryDriverSecondRequest(String ondate,String offdate,String driver_id)
            throws Exception {
        String sql = "select driver_id,chuqin_time,tuiqin_time,is_checkout,sys_date " +
                "from yw_checkout_confirm where driver_id='"+driver_id.trim()+"'  order by sys_date desc limit 1";
        return jdbcTemplate.queryForMap(sql);
    }

    /**
     * 获取当前司机的上一次退勤时间
     */
    @Override
    public Map<String, Object> findDriverOnCheckOutTime(String driverId)
            throws Exception {
        String sql = "select MAX(chuqin_time) chuqin_time," +
                "MAX(tuiqin_time) tuiqin from yw_checkout_confirm where driver_id='" + driverId + "'";
        return jdbcTemplate.queryForMap(sql);
    }

    /**
     * -----------------------测试当前司机出退勤始发终到数据完整性----------------------------
     */
    @Override
    public List<Map<String, Object>> testCurrentDriverStartAndEnd(
            String driverId) {
        List<Map<String, Object>> queryForList = getMiddleObj(driverId);

        String sql = "select dtm.sjh,dtm.shifazhan,dtm.zhongdaozhan,dtm.shifashijian,"
                + "dtm.zhongdaoshijian,dtm.drivertime,dtm.traincheci "
                + "from yw_data_mid dtm where sjh='" + driverId + "' "
                + "and driverdate>='" + (String) (queryForList.get(0).get("min_driverdate")) + "' "
                + "and driverdate<='" + (String) (queryForList.get(0).get("max_driverdate")) + "' ";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * ------------------------测试乘务员出退勤的一个分析结果----------------------------------
     */
    @Override
    public List<Map<String, Object>> testQueryCurrentDriverAnalyData(String driverId,
                                                                     PageBean pageBean) {
        List<Map<String, Object>> queryForList = getMiddleObj(driverId);
        StringBuilder sb = new StringBuilder();
        sb.append("select a.scenario_code,a.index_type_code,a.index_code,a.problem_code,");
        sb.append("b.driver_id,b.count_problem_code,a.count_show_problem_code");
        sb.append(",d.level,");
        sb.append("case when d.level='A' THEN 1");
        sb.append(" when d.level='B' THEN 2");
        sb.append(" when d.level='C' THEN 3");
        sb.append(" else 9");
        sb.append(" END problem_level");
        sb.append(" from (");
        sb.append("select");
        sb.append(" scenario_code,index_type_code,index_code,problem_code,");
        sb.append("count(problem_code) count_show_problem_code,driver_id");
        sb.append(" from yw_driver_operation_evaluation");
        sb.append(" where driver_id='" + driverId + "'");
        sb.append(" and startTime>='2018-05-01 16:01:01'");
        sb.append(" and startTime<='2018-11-01 16:01:01'");
        sb.append(" group by scenario_code,index_type_code,index_code,problem_code,driver_id) a");
        sb.append(" RIGHT JOIN");
        sb.append(" (select");
        sb.append(" scenario_code,index_type_code,index_code,problem_code,");
        sb.append("count(problem_code) count_problem_code,driver_id");
        sb.append(" from yw_driver_operation_evaluation");
        sb.append(" where driver_id='" + driverId + "'");
        sb.append(" and startTime>='2018-05-01 16:01:01'");
        sb.append("  and startTime<='2018-11-01 16:01:01'");
        sb.append(" and qualified='违标'");
        sb.append(" group by scenario_code,index_type_code,index_code,problem_code,driver_id) b");
        sb.append(" on a.driver_id=b.driver_id");
        sb.append(" and a.index_type_code=b.index_type_code ");
        sb.append(" and a.index_code=b.index_code");
        sb.append(" and a.problem_code=b.problem_code");
        sb.append(" and a.scenario_code=b.scenario_code");
        sb.append(" join dm_problem_level d");
        sb.append(" on b.problem_code=d.id");
        List<Map<String, Object>> anasyResult = jdbcTemplate.queryForList(sb.toString());
        return anasyResult;
    }


    @Override
    public List<Map<String, Object>> findAllPositionToDriverId()
            throws Exception {
        String sql = "select * from yw_checkout_position_driver";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 通过机务段段代码获取机务段对机车乘务员编写工号的规则
     * 并获取其前两位数
     */
    @Override
    public List<Map<String, Object>> findCodeRuleByJiWuDuanCode(
            String jiwuduan_code) throws Exception {
        return jdbcTemplate.queryForList(
                new StringBuilder(
                        "select * from yw_checkout_position_driver where jiwuduan_code='" + jiwuduan_code + "'"
                ).toString()
        );
    }

    /**
     * 查询当前乘务员是否真实存在
     */
    @Override
    public Map<String, Object> queryDriverIsExsits(String string,
                                                   String driver_code) throws Exception {
        Map<String, Object> queryForMap = null;
        String sql = "select WORKNO driver_id,PERSONNAME driver_name  from dm_driverinfo where WORKNO=?";
        try {
            queryForMap = jdbcTemplate.queryForMap(sql, new Object[]{driver_code});
        } catch (EmptyResultDataAccessException e) {
            queryForMap = null;
        }
        return queryForMap;
    }

    /**
     * 页面无数据的时候保存暂时保存退勤的数据     以便现在这个人已经退勤了
     */
    @Override
    public void saveExitsPro(String driver_id) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into yw_checkout_confirm(driver_id,");
        sb.append("chuqin_time,tuiqin_time,is_checkout,other)values(?,?,?,?,?)");
        Object[] o = new Object[]{driver_id,
                "",
                Commons.getSystemCurrentDate(),
                "1",
                ""};
        jdbcTemplate.update(sb.toString(), o);

    }

    @Override
    public void saveDataLose(List<DataLose> dataLoseList, String driverId,
                             String checkInAndOutTimes,
                             String systemCurrentDate, String checkoutResult) throws Exception {
        StringBuilder sb = new StringBuilder();

        //出退勤时间
        String[] splits = null;
        if (StringUtils.isNotBlank(checkInAndOutTimes) && !StringUtils.isEmpty(checkInAndOutTimes)) {
            splits = checkInAndOutTimes.split("_");
        }

        //保存退勤记录
        sb.append("insert into yw_checkout_confirm(driver_id," +
                "chuqin_time,tuiqin_time,is_checkout,sys_date)values(?,?,?,?,?)");
        Object[] object = new Object[]{driverId, splits == null ? "" : splits[0], splits == null ? "" : splits[1],
                "1",
                systemCurrentDate};
        jdbcTemplate.update(sb.toString(), object);

        //保存退勤结果		数据
        String resSQL = "insert into yw_checkout_result_info(driver_id," +
                "result_data,sys_date,ondutytime,offdutytime) values(?,?,?,?,?)";
        Object[] oRes = new Object[]{driverId, checkoutResult, systemCurrentDate,splits[0],splits[1]};
        jdbcTemplate.update(resSQL, oRes);
        //查询乘务员的组织结构
        String driverGroup  = "select locomotive_depot,workshop,workteam,group_," +
                "locomotive_depot_id,workshop_id,workteam_id,group_id,driver_name from dm_driver_org" +
                " where driver_id='"+driverId+"'";
        List<Map<String, Object>> driverGroupList = jdbcTemplate.queryForList(driverGroup);
        Map<String, Object> objectMap = driverGroupList.get(0);
        //数据缺失保存
        if (dataLoseList == null || dataLoseList.size() < 1) return;
        sb = new StringBuilder();
        sb.append("insert into yw_checkout_filelose_cause(");
        sb.append("driver_id,tran_num,driver_time,save_date,");
        sb.append("file_lose_cause,start_station,end_station,");
        sb.append("start_time_station,end_time_station,");
        sb.append("check_in_time,check_out_time");
        sb.append(",locomotive_depot,workshop,workteam,group_,");
        sb.append("locomotive_depot_id,workshop_id,workteam_id,group_id,driver_name,exception_uuid");
        sb.append(")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        for (DataLose dt : dataLoseList) {
            String uuid = UUID.randomUUID().toString().replace("-","");
            Object[] o = new Object[]{driverId,
                    dt.getTraincheci()==null?"":dt.getTraincheci(),
                    dt.getDrivertime()==null?"":dt.getDrivertime(),
                    systemCurrentDate,
                    dt.getDefect().replaceAll("\n","").trim(),
                    dt.getShifazhan()==null?"":dt.getShifazhan(),
                    dt.getZhongdaozhan()==null?"":dt.getZhongdaozhan(),
                    dt.getShifashijian()==null?"":dt.getShifashijian(),
                    dt.getZhongdaoshijian()==null?"":dt.getZhongdaoshijian(),
                    splits == null ? "" : splits[0],
                    splits == null ? "" : splits[1],
                    (String)objectMap.get("locomotive_depot"),
                    (String)objectMap.get("workshop"),
                    (String)objectMap.get("workteam"),
                    (String)objectMap.get("group_"),
                    (Integer)objectMap.get("locomotive_depot_id"),
                    (Integer)objectMap.get("workshop_id"),
                    (Integer)objectMap.get("workteam_id"),
                    (Integer)objectMap.get("group_id"),
                    (String)objectMap.get("driver_name"),
                    uuid
            };
            jdbcTemplate.update(sb.toString(), o);
        }
    }

    @Override
    public void saveStartAndEndStation(final List<Map<String, Object>> ol,
                                       final String driverId, String checkInAndOutTimes,
                                       final String systemCurrentDate) throws Exception {
        if (ol == null || ol.size() < 1) return;
        String[] splits = null;
        if (StringUtils.isNotBlank(checkInAndOutTimes) && !StringUtils.isEmpty(checkInAndOutTimes)) {
            splits = checkInAndOutTimes.split("_");
        }
        final String[] arr = splits;

        jdbcTemplate.batchUpdate(
                new StringBuilder("insert into yw_checkout_jl(zhongdaozhan,traincheci," +
                        "lkj_isnotexits,shifazhan,drivertime,sjh,shifashijian,zhongdaoshijian,yun_an_isnotexits," +
                        "is_exists_suspicious,check_in_time,check_out_time,defect,save_date)" +
                        "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)").toString(),
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement pstm, int i) throws SQLException {
                        Map<String, Object> objectMap = ol.get(i);
                        pstm.setString(1, (String) objectMap.get("zhongdaozhan"));
                        pstm.setString(2, (String) objectMap.get("traincheci"));
                        pstm.setString(3, (String) objectMap.get("lkj_isnotexits"));
                        pstm.setString(4, (String) objectMap.get("shifazhan"));
                        pstm.setString(5, (String) objectMap.get("drivertime"));
                        pstm.setString(6, driverId);
                        pstm.setString(7, (String) objectMap.get("shifashijian"));
                        pstm.setString(8, (String) objectMap.get("zhongdaoshijian"));
                       // pstm.setInt(9, (Integer) objectMap.get("id"));
                        pstm.setString(9, (String) objectMap.get("yun_an_isnotexits"));
                        pstm.setString(10, (String) objectMap.get("is_exists_suspicious"));
                        pstm.setString(11, arr == null ? "" : arr[0]);
                        pstm.setString(12, arr == null ? "" : arr[1]);
                        pstm.setString(13, (String) objectMap.get("defect"));
                        pstm.setString(14,systemCurrentDate);
                    }

                    @Override
                    public int getBatchSize() {
                        return ol.size();
                    }
                });
    }

    @Override
    public void saveAnasysResult(final List<Map<String, Object>> rows,final String systemCurrentDate)
            throws Exception {

        if (rows == null || rows.size() < 1) return;
        StringBuilder sb = new StringBuilder();
        sb.append("insert into yw_checkout_anasys_result(");
        sb.append("train_batch_no,scenario_code,index_type_code,index_code,problem_code");
        sb.append(",count_problem,item_level");
        sb.append(",driver_id,check_in_time,check_out_time,sys_date,new_scenario_name");
        sb.append(",new_index_type_name,new_index_name,new_problem_name)");
        sb.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        jdbcTemplate.batchUpdate(sb.toString(), new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement pstm, int i) throws SQLException {
                        Map<String, Object> omap = rows.get(i);
                        String st = (String) omap.get("noteStartDateAndTermial");
                        final String[] arr = st.split("_");
                        pstm.setString(1, (String) omap.get("train_batch_no"));
                        pstm.setString(2, (String) omap.get("scenario_code"));
                        pstm.setString(3, (String) omap.get("index_type_code"));
                        pstm.setString(4, (String) omap.get("index_code"));
                        pstm.setString(5, (String) omap.get("problem_code"));
                       //pstm.setInt(6, 0);
                        pstm.setInt(6, ((Long) omap.get("count_problem_code")).intValue());
                        pstm.setString(7, (String) omap.get("level"));
                        //pstm.setInt(9, ((Long) omap.get("problem_level")).intValue());
                        pstm.setString(8, (String) omap.get("driver_id"));
                        pstm.setString(9, arr[0]);
                        pstm.setString(10, arr[1]);
                        pstm.setString(11, systemCurrentDate);
                        pstm.setString(12, (String) omap.get("new_scenario_code"));
                        pstm.setString(13, (String) omap.get("new_index_type_code"));
                        pstm.setString(14, (String) omap.get("new_index_code"));
                        pstm.setString(15, (String) omap.get("new_problem_code"));
                    }

                    @Override
                    public int getBatchSize() {
                        return rows.size();
                    }
                }
        );
    }

    /**
     * 更新临时表中乘务员对A类问题的确认，BC类问题无需确认
     * @param ywDriverOperationEvaluations
     * @throws Exception
     */
    @Override
    public void saveExceptionData(
            final List<YwDriverOperationEvaluation> ywDriverOperationEvaluations) throws Exception {
        String dts = Commons.getSystemCurrentDate();
        //d6eca873cc9e47a1962817c972df36c3_0_无
        String sql = "";
        String[] splits = null;
        String tmpstr = "";
        for(YwDriverOperationEvaluation yw:ywDriverOperationEvaluations){
            tmpstr = yw.getDriverException();
            splits = tmpstr.split("_");
            sql = "";
            sql+="update yw_checkout_operation_exception_tmp set is_driver_confirm='"+splits[1]+"',";
            sql+="driver_confirm='"+splits[2]+"',create_date='"+dts+"' where exception_id='"+splits[0]+"'";
            jdbcTemplate.execute(sql);
        }
    }

    @Override
    public List<LinkedHashMap<String, Object>> findHostoryRecordWithLKJ(
            String page, String pageSize, String date, String time,
            String train_batch_no, String driver_id, String region)throws Exception  {
        List<LinkedHashMap<String, Object>> lkjHosttoryRecord = getLkjHosttoryRecord(page, pageSize, date, time,
                train_batch_no, region);
        return lkjHosttoryRecord;
    }

    /**
     * 2018-12-19
     * 保存分析结果的数据
     * 每次保存前删除自己的记录的时候，不能直接用清空表的操作，因为很多人都要用这张临时表
     * @param maps
     * @throws Exception
     */
    @Override
    public void saveAnasysisResult(String driverId ,String noteStartDateAndTermial) throws Exception {
            //清空表数据
            final String[] splits = noteStartDateAndTermial.split("_");
            String fsql = "select * from yw_driver_operation_evaluation where driver_id=? and startTime>=? and startTime<=?";
            final List<Map<String, Object>> list = jdbcTemplate.queryForList(fsql, new Object[]{driverId, splits[0], splits[1]});
            //然后保存到数据库，并且生成uuid唯一标识
            String sql = "insert into yw_checkout_operation_exception_tmp(date,train_batch_no,time,startTime,zhandian" +
                    ",region,line_code,scenario_code,index_type_code,index_code,problem_code" +
                    ",deduct_score,qualified,value,memo,driver_id,locomotive_depot_id,workshop_id" +
                    ",workteam_id,group_id,is_use,exception_id,other,ondutytime,offdutytime)" +
                    "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement pstm, int i) throws SQLException {
                    String uuid = UUID.randomUUID().toString().replace("-","");
                    Map<String, Object> yoe = list.get(i);
                    pstm.setString(1,(String)yoe.get("date"));
                    pstm.setString(2,(String)yoe.get("train_batch_no"));
                    pstm.setString(3,(String)yoe.get("time"));
                    pstm.setString(4,(String)yoe.get("startTime"));
                    pstm.setString(5,(String)yoe.get("zhandian"));
                    pstm.setString(6,(String)yoe.get("region"));
                    pstm.setString(7,(String)yoe.get("line_code"));
                    pstm.setString(8,(String)yoe.get("scenario_code"));
                    pstm.setString(9,(String)yoe.get("index_type_code"));
                    pstm.setString(10,(String)yoe.get("index_code"));
                    pstm.setString(11,(String)yoe.get("problem_code"));
                    pstm.setString(12,(String)yoe.get("deduct_score"));
                    pstm.setString(13,(String)yoe.get("qualified"));
                    pstm.setString(14,(String)yoe.get("value"));
                    pstm.setString(15,(String)yoe.get("memo"));
                    pstm.setString(16,(String)yoe.get("driver_id"));
                    pstm.setString(17,String.valueOf((Integer)yoe.get("locomotive_depot_id")));
                    pstm.setString(18,(String)yoe.get("workshop_id"));
                    pstm.setString(19,(String)yoe.get("workteam_id"));
                    pstm.setString(20,(String)yoe.get("group_id"));
//                    pstm.setString(21,(String)yoe.get("tuiqin"));
                    pstm.setString(21,(String)yoe.get("is_use"));
                    pstm.setString(22,uuid);
                    pstm.setString(23,"");
                    pstm.setString(24,splits[0]);
                    pstm.setString(25,splits[1]);
                }
                @Override
                public int getBatchSize() {
                    return list.size();
                }
            });
        }

    @Override
    public List<Map<String, Object>> findItemProblemDetail(
            YwDriverOperationEvaluation checkOut) throws Exception {
        String[] splits = checkOut.getNoteStartDateAndTermial().split("_");
        StringBuilder sb = new StringBuilder();
        sb.append("select a.*,b.level from (");
        sb.append("SELECT date,train_batch_no,time,startTime,zhandian,exception_id,");
        sb.append("region,scenario_code,index_type_code,");
        sb.append("index_code,problem_code,deduct_score,");
        sb.append("qualified,value,memo,driver_id,locomotive_depot_id,");
        sb.append("workshop_id,workteam_id,group_id");
        sb.append(" FROM yw_checkout_operation_exception");
        //过滤条件
        sb.append(" WHERE driver_id='" + checkOut.getDriver_id() + "'");
        sb.append(" AND startTime>='" + splits[0] + "'");
        sb.append(" AND startTime<='" + splits[1] + "'");
        sb.append(" AND problem_code='" + checkOut.getProblem_code() + "'");
        sb.append(" AND qualified='违标'");
        sb.append(") a left join dm_problem_level b on a.problem_code=b.id");
        return jdbcTemplate.queryForList(sb.toString());
    }

    /**
     * 处理yw_checkout_operation_exception_tmp表数据到yw_checkout_operation_exception
     * @param driver_id
     * @throws Exception
     */
    @Override
    public void saveForTmpToOperationException(String driver_id) throws Exception {
        String sql = "insert into  yw_checkout_operation_exception  " +
                "select * from yw_checkout_operation_exception_tmp where driver_id='"+driver_id+"'";
        jdbcTemplate.execute(sql);

        String delsql = "delete from yw_checkout_operation_exception_tmp where driver_id='"+driver_id+"'";
        jdbcTemplate.execute(delsql);
    }

    @Override
    public Map<String, Object> queryByFuSiJiCode(String driverId) throws Exception {
        String sql = "select sjh,fsjh from yw_data_mid where sjh='"+driverId+"' or fsjh='"+driverId+"' ORDER BY zhongdaoshijian DESC limit 1";
        return jdbcTemplate.queryForMap(sql);
    }

    @Override
    public void saveComputerIp(SysUser user, String jwcode,String ip,String hostname) throws Exception {
        String username = user.getUsername();
        String systemCurrentDate = Commons.getSystemCurrentDate();
        String slq = "insert into yw_checkout_login_log(" +
                "login_user,jwcode,login_time,login_ip,login_computer_name,log_type)values(?,?,?,?,?,?)";
        jdbcTemplate.update(slq,new Object[]{username,jwcode,systemCurrentDate,ip,hostname,"1"});
    }

    @Override
    public void deleteTempTblDataByDriverCode(String driverId) throws Exception {
        String delsql = "delete from yw_checkout_operation_exception_tmp where driver_id='"+driverId+"'";
        jdbcTemplate.execute(delsql);
    }

    @Override
    public List<String> findJWDChuTuiQinPlace(String str) throws Exception {
        String sql = "select places from yw_checkout_position_driver where jiwuduan_code='"+str+"'";
        List<String> list = jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return  rs.getString("places");
            }
        });
        List<String> slist = new ArrayList<String>();
        String s = list.get(0);
        String[] split = s.split(",");
        for(String ss:split){
            slist.add(ss.trim());
        }
        return slist;
    }

    @Override
    public List<Map<String, Object>> findAllJWDPlace() {
        String sql = "select * from yw_checkout_position_driver";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> findAllCJByDriverIdAndDate(String driverId, String onTime, String tmp) {
        String sql = "select strnumber,strsitename,CONVERT(VARCHAR(20),strTime,20) strtime,nType ntype from VIEW_CJ where (nType=1 or nType=0) and strNumber=? and strTime>=? and strTime<=?";
        SQLServerDBUtils server = new SQLServerDBUtils();
        List<Map<String, Object>> list = server.excuteQuery(sql, new Object[]{driverId,onTime,tmp});
        return list;
    }

    @Override
    public List<Map<String, Object>> findAllCJByDriverIdAndDateWithORCL(String driverId, String onTime, String tmp) {
        String sql="select view_cj_ntype,VIEW_CJ_STRNAME \"driverName\",VIEW_CJ_STRNUMBER \"strnumber\",VIEW_CJ_STRSITENAME \"strsitename\",TO_CHAR(VIEW_CJ_STRTIME,'YYYY-MM-DD HH24:MI:SS') \"strtime\"";
                sql+=",VIEW_CJ_NTYPE ntype from V_CJ where (view_cj_ntype = 0 or view_cj_ntype=1) and  VIEW_CJ_STRNUMBER=? and TO_CHAR(VIEW_CJ_STRTIME,'YYYY-MM-DD HH24:MI:SS')>=? and TO_CHAR(VIEW_CJ_STRTIME,'YYYY-MM-DD HH24:MI:SS')<=?";
        List<Map<String, Object>> list = ODBUtils.doQuery(sql, new Object[]{driverId,onTime,tmp});
        return list;
    }

    @Override
    public List<Map<String, Object>> findAllPoint() throws Exception {
        String sql = "select * from yw_checkout_point";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getTableTitile(String date, String time, String train_batch_no) {
        String sql  = "select * from dm_jcxh aa JOIN (SELECT train_type from yw_data_mid "
                + "WHERE driverdate=? AND drivertime=? and traincheci=?)bb ON bb.train_type=aa.cxfh";

        return jdbcTemplate.queryForList(sql,new Object[]{ date,  time,  train_batch_no});
    }


    private static String getItemProblemSql(String driver_id,String startDate,String endDate,String problem_code){
        StringBuilder sb = new StringBuilder();
        sb.append("select a.*,b.level from (");
        sb.append("SELECT date,train_batch_no,time,startTime,zhandian,");
        sb.append("region,scenario_code,index_type_code,");
        sb.append("index_code,problem_code,deduct_score,");
        sb.append("qualified,value,memo,driver_id,CONCAT(locomotive_depot_id,'') locomotive_depot_id,");
        sb.append("workshop_id,workteam_id,group_id");
        sb.append(" FROM yw_driver_operation_evaluation");
        //过滤条件
        sb.append(" WHERE driver_id='" + driver_id + "'");
        sb.append(" AND startTime>='" + startDate + "'");
        sb.append(" AND startTime<='" + endDate + "'");
        sb.append(" AND problem_code='" + problem_code + "'");
        sb.append(" AND qualified='违标'");
        sb.append(") a left join dm_problem_level b on a.problem_code=b.id");
        return sb.toString();
    }


    private static List<LinkedHashMap<String,Object>> getLkjHosttoryRecord(
            String page, String pageSize, String date, String kaichetime,
            String train_no,String region){
        String partition2 = train_no + "_" + kaichetime;
        List<String> list1 = new ArrayList<String>();
        if (region.contains(";")) {
            String[] arr = region.split(";");
            for (int i = 0; i < arr.length; i++) {
                list1.add(arr[i]);
            }
        } else {
            list1.add(region);
        }

        // 生成区间条件
        String whereStr = "(";
        for (int i = 0; i < list1.size(); i++) {
            int startIndex = Integer.parseInt(list1.get(i).split("-")[0]) + 1;
            int endIndex = Integer.parseInt(list1.get(i).split("-")[1]) + 1;
            whereStr += "(indexnum >= " + startIndex + " and indexnum <= "
                    + endIndex + ")";
            if (i < list1.size() - 1) {
                whereStr += " or ";
            }
        }
        whereStr += ")";

        Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
        List<LinkedHashMap<String, Object>> list = new ArrayList<>();
        try {
            conn = HVUtils.getConnection();
            stmt = conn.createStatement();
            String sql = "select * from default.yw_data_detail where"
                    + " driverdate='" + date + "' and traincheci_drivertime='"
                    + partition2 + "' and " + whereStr;
            result = stmt.executeQuery(sql);
            ResultSetMetaData metaData = result.getMetaData();
            String[] columnNames = new String[metaData.getColumnCount()];
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columnNames[i - 1] = metaData.getColumnName(i).split("\\.")[1];
            }
            while (result.next()) {
                LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
                for (int i = 1; i <= columnNames.length; i++) {
                    map.put(columnNames[i - 1], result.getObject(i));
                }
                list.add(map);
            }
            HVUtils.getHiveClose(conn,stmt,result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HVUtils.getHiveClose(conn,stmt,result);
        }
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> map1,
                               Map<String, Object> map2) {
                int int1 = Integer.parseInt(map1.get("indexnum").toString());
                int int2 = Integer.parseInt(map2.get("indexnum").toString());
                return int1 - int2;
            }

        });
        return list;
    }
}










