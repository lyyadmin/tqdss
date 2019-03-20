package com.tenly.project.dao.impl;

import java.util.List;
import java.util.Map;

import com.tenly.common.projecttools.Commons;
import com.tenly.project.bean.YwCheckutAnasysResult;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tenly.project.dao.ICheckOutBuffetQueryDao;
import com.tenly.system.bean.PageBean;

@SuppressWarnings("all")
@Repository
public class CheckOutBuffetQueryDaoImpl implements ICheckOutBuffetQueryDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void findAll(PageBean pb, String page, String rows) {
        String sql = "select * from yw_checkout_confirm";
        List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql);
        pb.setRows(queryForList);
    }

    /**
     * 查询所有的项点
     */
    @Override
    public List<Map<String, Object>> findAllItemName() {
        String sql = "select id,name,parent_id,type from dm_point";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 作业习惯分析  中问题发生次数和发生的频率
     */
    @Override
    public List<Map<String, Object>> findItemsHabitsAna(String startDate, String enddate, String driver_code) {
        StringBuffer sb = new StringBuffer();
        sb.append("select a.scenario_code,a.date,a.index_type_code,a.index_code,a.problem_code,");
        sb.append("b.driver_id,b.count_problem_code,a.count_show_problem_code");
        sb.append(",d.level,");
        sb.append("case when d.level='A' THEN 1");
        sb.append(" when d.level='B' THEN 2");
        sb.append(" when d.level='C' THEN 3");
        sb.append(" else 9");
        sb.append(" END problem_level");
        sb.append(" from (");
        sb.append("select");
        sb.append(" scenario_code,index_type_code,index_code,problem_code,date,");
        sb.append("count(problem_code) count_show_problem_code,driver_id");
        sb.append(" from yw_driver_operation_evaluation");
        sb.append(" where driver_id='" + driver_code + "'");
        sb.append(" and startTime>='" + startDate + "'");
        sb.append(" and startTime<='" + enddate + "'");
        sb.append(" group by scenario_code,index_type_code,index_code,problem_code,driver_id,date) a");
        sb.append(" RIGHT JOIN");
        sb.append(" (select");
        sb.append(" scenario_code,index_type_code,index_code,problem_code,");
        sb.append("count(problem_code) count_problem_code,driver_id");
        sb.append(" from yw_driver_operation_evaluation");
        sb.append(" where driver_id='" + driver_code + "'");
        sb.append(" and startTime>='" + startDate + "'");
        sb.append("  and startTime<='" + enddate + "'");
        sb.append(" and qualified='违标'");
        sb.append(" group by scenario_code,index_type_code,index_code,problem_code,driver_id) b");
        sb.append(" on a.driver_id=b.driver_id");
        sb.append(" and a.index_type_code=b.index_type_code ");
        sb.append(" and a.index_code=b.index_code");
        sb.append(" and a.problem_code=b.problem_code");
        sb.append(" and a.scenario_code=b.scenario_code");
        sb.append(" join dm_problem_level d");
        sb.append(" on b.problem_code=d.id");
        return jdbcTemplate.queryForList(sb.toString());
    }

    /**
     * 查询违规车次分布
     */
    @Override
    public List<Map<String, Object>> queryItemIrregularitiesTrain(String sql) throws Exception {
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 查询违规时段分布
     */
    @Override
    public List<Map<String, Object>> queryItemIrregularitiesTimes(
            String startDate, String endDate, String driver_code,
            String taskScene, String taskItem, String taskItemPoint, String taskProblem)
            throws Exception {
        String sbs = getStringBuilderToString(taskScene, taskItem, taskItemPoint, taskProblem);
        StringBuilder sb = new StringBuilder();
        sb.append("select (ALevel+BLevel+CLevel) abc,ALevel,BLevel,CLevel,hour_time from (");
        sb.append(" select sum(case when b.level='A' then a.count_problem_code ELSE 0 end) ALevel,");
        sb.append(" sum(case when b.level='B' then a.count_problem_code ELSE 0 end) BLevel,");
        sb.append(" sum(case when b.level='C' then a.count_problem_code ELSE 0 end) CLevel,");
        sb.append(" a.hour_time");
        sb.append(" from (");
        sb.append(" SELECT");
        sb.append(" problem_code,count(1) count_problem_code,");
        sb.append(" CASE WHEN SUBSTR(startTime FROM 12 FOR 2)='00' THEN '24'");
        sb.append(" ELSE SUBSTR(startTime FROM 12 FOR 2)");
        sb.append(" END hour_time");
        sb.append(" FROM");
        sb.append(" yw_driver_operation_evaluation d");
        sb.append(" WHERE");
        sb.append(" startTime >= '" + startDate + "'");
        sb.append(" AND startTime <= '" + endDate + "'");
        sb.append(" AND driver_id = '" + driver_code + "'");
        sb.append(" AND qualified = '违标'");
        sb.append(sbs);
        sb.append(" group by problem_code,hour_time order by hour_time asc");
        sb.append(" )a ");
        sb.append(" join dm_problem_level b ON a.problem_code = b.id");
        sb.append(" group by a.hour_time order by a.hour_time");
        sb.append(" ) d");
        return jdbcTemplate.queryForList(sb.toString());
    }

    private String getStringBuilderToString(
            String taskScene, String taskItem, String taskItemPoint, String taskProblem) {
        StringBuilder sbs = new StringBuilder();
        if (StringUtils.isNotBlank(taskScene) && !StringUtils.isEmpty(taskScene)&&!"0".equals(taskScene)) {
            sbs.append(" and scenario_code='" + taskScene + "'");
        }
        if (StringUtils.isNotBlank(taskItem) && !StringUtils.isEmpty(taskItem)&&!"0".equals(taskItem)) {
            sbs.append(" and index_type_code='" + taskItem + "'");
        }
        if (StringUtils.isNotBlank(taskItemPoint) && !StringUtils.isEmpty(taskItemPoint)&&!"0".equals(taskItemPoint)) {
            sbs.append(" and index_code='" + taskItemPoint + "'");
        }
        if (StringUtils.isNotBlank(taskProblem) && !StringUtils.isEmpty(taskProblem)&&!"0".equals(taskProblem)) {
            sbs.append(" and problem_code='" + taskProblem + "'");
        }
        return sbs.toString();
    }

    /**
     * 查询违标次数
     */
    @Override
    public List<Map<String, Object>> queryItemIrregularitiesTimesToAll(
            String sql) throws Exception {
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 根据作业项点分析条件中选择的作业场景  项点  项目  问题   来确定下级项点  项目  及问题
     * 条件筛选
     */
    @Override
    public List<Map<String, Object>> queryItemAll()
            throws Exception {
        String fSql = "select f.id,f.name,f.parent_id,f.type from dm_point f";
        return jdbcTemplate.queryForList(fSql);
    }

    @Override
    public List<Map<String, Object>> queryItemChildrenBySQL(String tSQL)
            throws Exception {
        if (StringUtils.isBlank(tSQL) || StringUtils.isEmpty(tSQL)) return null;
        return jdbcTemplate.queryForList(tSQL);
    }

    /**
     * 项点分析
     * 违标交路分布
     */
    @Override
    public List<Map<String, Object>> queryAllByDriverId(
            String startDate, String endDate, String driver_code,
            String taskScene, String taskItem, String taskItemPoint, String taskProblem)
            throws Exception {
        String sbs = getStringBuilderToString(taskScene, taskItem, taskItemPoint, taskProblem);
        StringBuilder sb = new StringBuilder();
        sb.append("select d.ALevel,d.BLevel,d.CLevel,d.line_name,(ALevel+BLevel+CLevel) abc from (");
        sb.append(" select sum(case when b.level='A' then count_problem_code ELSE 0 end ) ALevel,");
        sb.append(" sum(case when b.level='B' then count_problem_code ELSE 0 end ) BLevel,");
        sb.append(" sum(case when b.level='C' then count_problem_code ELSE 0 end ) CLevel,");
        sb.append(" c.line_name");
        sb.append(" from (");
        sb.append(" select problem_code,count(1) count_problem_code,line_code");
        sb.append(" from");
        sb.append(" yw_driver_operation_evaluation a");
        sb.append(" WHERE driver_id = '" + driver_code + "'");
        sb.append(" AND startTime >= '" + startDate + "'");
        sb.append(" AND startTime <= '" + endDate + "'");
        sb.append(" AND qualified = '违标'");
        sb.append(sbs);
        sb.append(" group by problem_code,line_code");
        sb.append(" ) a inner join dm_problem_level b on a.problem_code = b.id");
        sb.append(" inner join dm_line_road_name c on  c.line_id = a.line_code");
        sb.append(" group by line_name");
        sb.append(" ) d");
        return jdbcTemplate.queryForList(sb.toString());
    }

    /**
     * 区域分布
     *
     * @param start
     * @param end
     * @param driver_code
     * @param taskScene
     * @param taskItem
     * @param taskItemPoint
     * @param taskProblem
     * @return List<Map       <       String       ,               Object>>
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> queryItemIrregularitiesArea(
            String start, String end, String driver_code,
            String taskScene, String taskItem, String taskItemPoint, String taskProblem) throws Exception {
        String sbs = getStringBuilderToString(taskScene, taskItem, taskItemPoint, taskProblem);
        StringBuilder sb = new StringBuilder();
        sb.append("select ALevel,BLevel,CLevel,zhandian,(ALevel+BLevel+CLevel) abc from (");
        sb.append(" select sum(case when b.level='A' then count_problem_code ELSE 0 end ) ALevel,");
        sb.append(" sum(case when b.level='B' then count_problem_code ELSE 0 end ) BLevel,");
        sb.append(" sum(case when b.level='C' then count_problem_code ELSE 0 end ) CLevel,");
        sb.append(" a.zhandian");
        sb.append(" from (");
        sb.append(" SELECT zhandian,problem_code,count(1) count_problem_code");
        sb.append(" FROM yw_driver_operation_evaluation ");
        sb.append(" WHERE");
        sb.append(" startTime >= '" + start + "'");
        sb.append(" AND startTime <= '" + end + "'");
        sb.append(" AND driver_id = '" + driver_code + "'");
        sb.append(" AND qualified = '违标'");
        sb.append(sbs);
        sb.append(" group by zhandian,problem_code");
        sb.append(" ) a JOIN dm_problem_level b on a.problem_code = b.id");
        sb.append(" group by a.zhandian");
        sb.append(" ) d");
        return jdbcTemplate.queryForList(sb.toString());
    }

    /**
     * 作业违标分布
     *
     * @param start
     * @param end
     * @param driver_code
     * @param taskScene
     * @param taskItem
     * @param taskItemPoint
     * @param taskProblem
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> queryItemIrregularitiesPoint(
            String start,
            String end, String driver_code,
            String taskScene, String taskItem, String taskItemPoint, String taskProblem) throws Exception {
        StringBuilder sb = new StringBuilder();
        String str = "";
        String sql = "";
        if (StringUtils.isNotEmpty(taskItem)&&StringUtils.isNotBlank(taskItem)
                &&StringUtils.isNotEmpty(taskScene)&&StringUtils.isNotEmpty(taskScene)) {
            str = "index_code";
            sql = " AND scenario_code='" + taskScene + "' " +
                    "AND index_type_code='" + taskItem + "' ";
        }else if(StringUtils.isNotEmpty(taskScene)&&StringUtils.isNotBlank(taskScene)){
            str = "index_type_code";
            sql = " AND scenario_code='" + taskScene + "' ";
        }else{
            str = "scenario_code";
        }

        sb.append("select ALevel,BLevel,CLevel,"+str+" code_name,(ALevel+BLevel+CLevel) abc from (");
        sb.append(" select sum(case when b.level='A' then count_problem_code ELSE 0 end ) ALevel,");
        sb.append(" sum(case when b.level='B' then count_problem_code ELSE 0 end ) BLevel,");
        sb.append(" sum(case when b.level='C' then count_problem_code ELSE 0 end ) CLevel,");
        sb.append(" a."+str+"");
        sb.append(" from (");
        sb.append(" SELECT "+str+",problem_code,count(1) count_problem_code");
        sb.append(" FROM yw_driver_operation_evaluation ");
        sb.append(" WHERE");
        sb.append(" startTime >= '" + start + "'");
        sb.append(" AND startTime <= '" + end + "'");
        sb.append(" AND driver_id = '" + driver_code + "'");
        sb.append(" AND qualified = '违标' ");
        sb.append(sql);
        sb.append(" group by index_type_code,problem_code");
        sb.append(" ) a INNER JOIN dm_problem_level b on a.problem_code = b.id");
        sb.append(" group by a."+str+"");
        sb.append(" ) d");
        return jdbcTemplate.queryForList(sb.toString());
    }

    private String getItemSql(
            String item_code, String conditionName, String andCondition,
            String problem_level, String st, String end, String driver_id) {
        StringBuilder sb = new StringBuilder();
        sb.append("select concat(round(b.ct_" + item_code + " / a.show_" + item_code + "*100,1),'%') ratio,a." +
                item_code + " item_code,a.show_" + item_code + " as show_scenario_code ," +
                "b.ct_" + item_code + " as ct_scenario_code ");
        sb.append(" from (select " + item_code + ",count(" + item_code + ") show_" + item_code + "");
        sb.append(" from yw_driver_operation_evaluation t,dm_problem_level t1");
        sb.append(" where t.problem_code=t1.id and driver_id = '" + driver_id + "'");
        if (StringUtils.isNotBlank(andCondition) && !StringUtils.isEmpty(andCondition)) {
            sb.append(" and " + conditionName + "='" + andCondition + "'");
        }
        sb.append(" and startTime >= '" + st + "' and startTime <= '" + end + "'");
        sb.append(" and t1.level= '" + problem_level+ "'");
        sb.append(" group by " + item_code + " ) a");
        sb.append(" LEFT JOIN (select " + item_code + ",count(" + item_code + ") ct_" + item_code + " ");
        sb.append(" from yw_driver_operation_evaluation t,dm_problem_level t1");
        sb.append(" where  t.problem_code=t1.id and driver_id = '" + driver_id + "' and startTime >= '" + st + "'");
        sb.append(" and startTime <= '" + end + "' and qualified = '违标'");
        sb.append(" and t1.level= '" + problem_level+ "'");
        if (StringUtils.isNotBlank(andCondition) && !StringUtils.isEmpty(andCondition)) {
            sb.append(" and " + conditionName + "='" + andCondition + "'");
        }
        sb.append(" group by " + item_code + " ) b on a." + item_code + " = b." + item_code + "");
        sb.append(" group by a." + item_code + ",a.show_" + item_code + ",b.ct_" + item_code + "");
        return sb.toString();
    }
    
    
    @Override
    public List<Map<String, Object>> findOperationEvaluationByDateAndDriverCode(
            String item_code_name, String problem_level,String startDate, String endDate, String driver_code,
            String taskScene, String taskItem, String taskItemPoint, String taskProblem) throws Exception {
        StringBuilder sb = new StringBuilder();
        String itemsql = "";
        if (taskScene.equals("0")) {
            //统计所有
            itemsql += getItemSql("scenario_code", "scenario_code",
                    item_code_name,problem_level, startDate, endDate, driver_code);
            itemsql += " UNION ALL " + getItemSql("index_type_code", "scenario_code",
                    item_code_name,problem_level, startDate, endDate, driver_code);
            itemsql += " UNION ALL " + getItemSql("index_code", "scenario_code",
                    item_code_name,problem_level, startDate, endDate, driver_code);
            itemsql += " UNION ALL " + getItemSql("problem_code", "scenario_code",
                    item_code_name, problem_level,startDate, endDate, driver_code);
        } else if (taskItem.equals("0")) {
            itemsql += getItemSql("index_type_code", "index_type_code",
                    item_code_name,problem_level, startDate, endDate, driver_code);
            itemsql += " UNION ALL " + getItemSql("index_code", "index_type_code",
                    item_code_name,problem_level, startDate, endDate, driver_code);
            itemsql += " UNION ALL " + getItemSql("problem_code", "index_type_code",
                        item_code_name,problem_level, startDate, endDate, driver_code);
        } else if (taskItemPoint.equals("0")) {
            itemsql += getItemSql("index_code", "index_code",
                    item_code_name,problem_level, startDate, endDate, driver_code);
            itemsql += " UNION ALL " + getItemSql("problem_code", "index_code",
                    item_code_name,problem_level, startDate, endDate, driver_code);
        } else {
    		itemsql += getItemSql("problem_code", "problem_code",
                    item_code_name,problem_level, startDate, endDate, driver_code);
        }
           
        if ("".equals(itemsql)) return null;
        return jdbcTemplate.queryForList(itemsql);
    }

    @Override
    public List<Map<String, Object>> queryProblemViolationGridDetail(
            String startDate, String endDate, String driver_code, String item_code) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("select date,startTime,train_batch_no," +
                "zhandian,scenario_code,index_type_code,index_code,problem_code,memo,region,time,qualified");
        sb.append(" from yw_driver_operation_evaluation");
        sb.append(" where driver_id = '" + driver_code + "'");
        sb.append(" and startTime >= '" + startDate + "'");
        sb.append(" and startTime <= '" + endDate + "'");
        sb.append(" and problem_code='" + item_code + "'");
        
        return jdbcTemplate.queryForList(sb.toString());
    }
    
    @Override
    public List<Map<String, Object>> queryProblemCommonGridDetail(
            String startDate, String endDate, String driver_code,
            String item_code,String field_name,String field_value) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("select date,startTime,train_batch_no," +
                "zhandian,scenario_code,index_type_code,index_code,problem_code,memo,time,region,qualified");
        sb.append(" from yw_driver_operation_evaluation t,dm_line_road_name t1 ");
        sb.append(" where t.line_code=t1.line_id and driver_id = '" + driver_code + "'");
        sb.append(" and startTime >= '" + startDate + "'");
        sb.append(" and startTime <= '" + endDate + "'");
        sb.append(" and problem_code='" + item_code + "'");
        if("startTime".equals(field_name)){
			sb.append(" and concat(date_format(t."+field_name+",'%H'),'点')= '" + field_value+ "' ");
		}else if ("zhandian".equals(field_name)) {
			sb.append(" and t."+field_name+"= '" + field_value+ "' ");
		}else if ("line_name".equals(field_name)) {
			sb.append(" and t1."+field_name+"= '" + field_value+ "' ");
		}
        return jdbcTemplate.queryForList(sb.toString());
    }

    @Override
    public List<Map<String, Object>> getTaskEvaluate(String driver_code,
            String startDate, String endDate,
            String taskScene, String taskitem, String taskItemPoint, String taskProblem) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT t1.date,t1.ALevel,t1.BLevel,t1.CLevel");
        sb.append(" from");
        sb.append(" (select ");
        sb.append(" max(CASE WHEN b.level = 'A' THEN b.lev ELSE 0 END ) ALevel,");
        sb.append(" max( CASE WHEN b.level = 'B' THEN b.lev ELSE 0 END ) BLevel,");
        sb.append(" max( CASE WHEN b.level = 'C' THEN b.lev ELSE 0 END ) CLevel,");
        sb.append(" b.date");
        sb.append(" from");
        sb.append(" (");
        sb.append(" select eval.date,count(lev.level) lev,lev.level from (");
        sb.append(" select date,problem_code");
        sb.append(" from yw_driver_operation_evaluation where driver_id='"+driver_code+"' and qualified='违标'");
        sb.append(" and date>='"+startDate.replaceAll("-","")
                +"' and date<='"+endDate.replaceAll("-","")+"'");
        String stringBuilderToString = getStringBuilderToString(taskScene, taskitem, taskItemPoint, taskProblem);
        if(!stringBuilderToString.equals("")){
            sb.append(stringBuilderToString);
        }
        sb.append(" ) eval");
        sb.append(" join dm_problem_level lev on eval.problem_code=lev.id");
        sb.append(" group by eval.date,lev.level,eval.date");
        sb.append(" ) b group by b.date) t1");
        return jdbcTemplate.queryForList(sb.toString());
    }

    @Override
    public List<Map<String, Object>> findEvaluateBy(
            String driver_code,String startDate, String endDate,
            String taskScene, String taskitem, String taskItemPoint, String taskProblem) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select comment_date date, count(1) comment_count " +
                "from yw_comment_of_driver where driver_id = '"+driver_code+"'  ");
        sb.append(" and comment_date>='"+startDate.replaceAll("-","")
                +"' and comment_date<='"+endDate.replaceAll("-","")+"'");
        if(StringUtils.isNotBlank(taskScene)&&!StringUtils.isEmpty(taskScene)){
            if(taskScene.equals("0")){
                taskScene = "全部";
                sb.append(" and scenario_code='"+taskScene+"'");
            }
        }
        if(StringUtils.isNotBlank(taskitem)&&!StringUtils.isEmpty(taskitem)){
            if(taskitem.equals("0")){
                taskitem = "全部";
                sb.append(" and index_type_code='"+taskitem+"'");
            }
        }
        if(StringUtils.isNotBlank(taskItemPoint)&&!StringUtils.isEmpty(taskItemPoint)){
            if(taskItemPoint.equals("0")){
                taskItemPoint = "全部";
                sb.append(" and index_code='"+taskItemPoint+"'");
            }
        }
        if(StringUtils.isNotBlank(taskProblem)&&!StringUtils.isEmpty(taskProblem)){
            if(taskProblem.equals("0")){
                taskProblem = "全部";
                sb.append(" and problem_code='"+taskProblem+"'");
            }
        }
        sb.append(" group by comment_date");
        return jdbcTemplate.queryForList(sb.toString());
    }

    @Override
    public List<Map<String, Object>> getEvaluateContentByDriverCode(String driver_code,String evaldate) {
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        sb.append("a.*,us.realname,label.comment_label from");
        sb.append(" ( SELECT ");
        sb.append("comment_people_id,comment_date,locomotive_depot,workshop,workteam,group_");
        sb.append(",driver_id,scenario_code,index_type_code,index_code,problem_code,concat(start_date,'-',end_date) ");
        sb.append("startandenddate,label_value,comment_text");
        sb.append(" from yw_comment_of_driver where driver_id = '"+driver_code+"'");
        sb.append(" and comment_date = '"+evaldate+"'");
        sb.append(") a");
        sb.append(" left join sys_user us on a.comment_people_id=us.id ");
        sb.append("  left join yw_comment_label_value label  on label.label_value=a.label_value");

        return jdbcTemplate.queryForList(sb.toString());
    }

    /**
     * 历史查询，获取乘务员的出退勤时间，填充历史查询出退勤下拉选项
     * @param driver_code
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> getChuTuiQinDate(String driver_code) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("select GROUP_CONCAT(DISTINCT(a.ondutytime)) onday,a.ondutytime_ym,'1' type from (");
        sb.append("select SUBSTR(chuqin_time FROM 1 FOR 7) ondutytime_ym,SUBSTR(chuqin_time FROM 9 FOR 2) ondutytime");
        sb.append(" from yw_checkout_confirm where driver_id='"+driver_code+"'");
        sb.append(") a group by a.ondutytime_ym");
        sb.append(" UNION ALL");
        sb.append(" select GROUP_CONCAT(DISTINCT(b.offdutytime)) onday,b.offdutytime_ym,'2' type from (");
        sb.append("select SUBSTR(tuiqin_time FROM 1 FOR 7) offdutytime_ym,SUBSTR(tuiqin_time FROM 9 FOR 2) offdutytime");
        sb.append(" from yw_checkout_confirm where driver_id='"+driver_code+"'");
        sb.append(") b group by b.offdutytime_ym");
        return  jdbcTemplate.queryForList(sb.toString());
    }

    /**
     * 查询最近一次出退勤时间
     * @param driver_code
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> queryMaxCheckInOutTime(String driver_code) throws Exception {
        String sql  = "select max(chuqin_time) chuqin_time,tuiqin_time " +
                "from yw_checkout_confirm where driver_id = '"+driver_code+"'";
        sql += " group by tuiqin_time ";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getTuiQinDate(String driver_code, String ondutytime) throws Exception {
        String sql  = "select chuqin_time,substr(tuiqin_time from 1 for 10) tuiqin_time from yw_checkout_confirm where driver_id='"+driver_code+"'";
                sql += " and substr(chuqin_time from 1 for 10)='"+ondutytime.trim()+"'";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public void findAllForChuTuiQinInfo(
            PageBean pageBean, String ondutytime, String offdutytime, String driverCode) throws Exception {
        if(StringUtils.isNotEmpty(offdutytime)&&StringUtils.isNotBlank(offdutytime)){
            offdutytime = Commons.getToAfterDay(offdutytime.trim()+" 00:00:00", 1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("select driver_id,chuqin_time,tuiqin_time,is_checkout,sys_date");
        sb.append(" from yw_checkout_confirm");
        sb.append(" where driver_id='"+driverCode+"'");
        sb.append(" and sys_date>='"+ondutytime+"'");
        sb.append(" and sys_date<='"+offdutytime+"'");
        sb.append(" order by chuqin_time desc");
        String countSql = "select count(1) from yw_checkout_confirm where driver_id='"+driverCode+"'";
                countSql += " and sys_date>='"+ondutytime+"'";
                countSql += " and sys_date<='"+offdutytime+"'";
        int currentPage = pageBean.getCurrentPage();
        int pageSize = pageBean.getPageSize();
        int totalrows = jdbcTemplate.queryForObject(countSql,Integer.class);
        if (totalrows % pageSize == 0) {
            currentPage = totalrows / pageSize;
        } else {
            currentPage = totalrows / pageSize + 1;
        }
        if(currentPage<=1){
            sb.append(" limit 0,"+pageSize);
        }else{
            sb.append(" limit "+((currentPage-1)*pageSize)+","+pageSize);
        }
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sb.toString());
        int count=1;
        for(Map<String,Object> o:list){
            o.put("id",count);
            o.put("queryDetails","");
            count++;
        }
        pageBean.setTotal(totalrows);
        pageBean.setRows(list);
    }

    @Override
    public List<Map<String, Object>> getCheckOutConfirmTime(
            String driverCode, String startDate, String endDate) throws Exception {
        String sql = "select sys_date from yw_checkout_confirm where driver_id = '" + driverCode + "'";
        sql += " AND sys_date>='" + startDate + "'";
        endDate = endDate+" 00:00:00";
        String toAfterDay = Commons.getToAfterDay(endDate, 1);
        sql += " AND sys_date<'" + toAfterDay + "'";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 废弃
     * @param driverCode
     * @param sys_date
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> queryCheckInAndOutTimeByDriverCodeAndSysDate(
            String driverCode, String sys_date) throws Exception {
        String sql = "SELECT sys_date,chuqin_time,tuiqin_time " +
                "FROM yw_checkout_confirm " +
                "WHERE driver_id = '" + driverCode + "'" +
                " AND sys_date='" + sys_date + "'";
        return jdbcTemplate.queryForMap(sql);
    }

    /**
     * 交路图
     * @param driverCode
     * @param chuqin_time
     * @param tuiqin_time
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> getDriverJLGrahData(String driverCode,
            String chuqin_time, String tuiqin_time) throws Exception {
        String sql = "SELECT zhongdaozhan,traincheci,lkj_isnotexits,shifazhan,drivertime,sjh,shifashijian";
        sql += ",zhongdaoshijian,id,yun_an_isnotexits,is_exists_suspicious,check_in_time,check_out_time";
        sql += ",defect";
        sql += " FROM yw_checkout_jl";
        sql += " WHERE sjh='" + driverCode + "'";
        sql += " AND check_in_time='" + chuqin_time + "'";
        sql += " AND check_out_time='" + tuiqin_time + "'";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getCurrentDriverAnalyData(
            String chuqin_time, String tuiqin_time, String driverCode) throws Exception {
        String sql = "SELECT scenario_code,index_type_code,index_code,problem_code,count_show_problem_code";
        sql += ",count_problem,item_level,item_level_code,driver_id,check_in_time,check_out_time,sys_date";
        sql += ",new_scenario_name,new_index_type_name,new_index_name,new_problem_name";
        sql += " FROM yw_checkout_anasys_result";
        sql += " WHERE driver_id='" + driverCode + "'";
        sql += " AND check_in_time='" + chuqin_time + "'";
        sql += " AND check_out_time='" + tuiqin_time + "'";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getCurrentRowDetail(YwCheckutAnasysResult yar) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT date,train_batch_no,time,startTime,zhandian,");
        sb.append("region,scenario_code,index_type_code,");
        sb.append("index_code,problem_code,deduct_score,");
        sb.append("qualified,value,memo,driver_id,locomotive_depot_id,");
        sb.append("workshop_id,workteam_id,group_id");
        sb.append(" FROM yw_driver_operation_evaluation");
        //过滤条件
        sb.append(" WHERE driver_id='" + yar.getDriver_id() + "'");
        sb.append(" AND startTime>='" + yar.getCheck_in_time() + "'");
        sb.append(" AND startTime<='" + yar.getCheck_out_time() + "'");
        sb.append(" AND problem_code='" + yar.getProblem_code() + "'");
        sb.append(" AND qualified='违标'");
        return jdbcTemplate.queryForList(sb.toString());
    }

    /**
     * 退勤结果信息
     * @param driver_id
     * @param ondutytime
     * @param offdutytime
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> getResultInfo(String driver_id, String ondutytime,String offdutytime) throws Exception {
        String sql = "select driver_id,result_data,sys_date from yw_checkout_result_info";
        sql += " where driver_id='" + driver_id + "' AND ondutytime='" + ondutytime + "'";
        sql += " AND offdutytime='"+offdutytime+"'";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 统计强弱项：强项就是作业次数最多，违标次数最少，弱项就是违标次数最多，问题发生率也会增高
     * @param startDate
     * @param endDate
     * @param driver_code
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> queryStongItemByDate(
            String startDate, String endDate, String driver_code) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("select sum(g.ratio_index_type_code) ratio_index_type_code,");
        sb.append("sum(g.show_index_type_code) show_index_type_code,");
        sb.append("sum(g.count_index_type_code) count_index_type_code,p.`name` new_code_name from (");
        sb.append("SELECT CASE when ratio_index_type_code!='null' " +
                "THEN  ratio_index_type_code ELSE 0 end ratio_index_type_code,");
        sb.append("case when count_index_type_code!='null' " +
                "then count_index_type_code else 0 END count_index_type_code,");
        sb.append("show_index_type_code,index_type_code");
        sb.append(" FROM (");
        sb.append("select round(q.count_index_type_code/w.count_index_type_code,4) ratio_index_type_code,");
        sb.append("q.count_index_type_code,w.count_index_type_code show_index_type_code,w.index_type_code from (");
        sb.append("select count(a.index_type_code) count_index_type_code,index_type_code from (");
        sb.append("select index_type_code,driver_id,startTime,scenario_code,index_code,problem_code");
        sb.append(" from yw_driver_operation_evaluation");
        sb.append(" where driver_id ='" + driver_code + "'");
        sb.append(" and startTime>='" + startDate + "'");
        sb.append(" and startTime<='" + endDate + "'");
        sb.append(" and qualified = '违标'");
        sb.append(") a group by index_type_code ORDER BY count_index_type_code asc");
        sb.append(") q ");
        sb.append(" right join ");
        sb.append("(");
        sb.append("select count(a.index_type_code) count_index_type_code,index_type_code from (");
        sb.append("select index_type_code,driver_id,startTime,scenario_code,index_code,problem_code");
        sb.append(" from yw_driver_operation_evaluation");
        sb.append(" where driver_id = '" + driver_code + "'");
        sb.append(" and startTime>='" + startDate + "'");
        sb.append(" and startTime<='" + endDate + "'");
        sb.append(") a group by index_type_code ORDER BY count_index_type_code asc");
        sb.append(") w on q.index_type_code = w.index_type_code");
        sb.append("  ORDER BY ratio_index_type_code ASC");
        sb.append(") e");
        sb.append(") g");
        sb.append(" join dm_point p on g.index_type_code=p.id");
        sb.append(" group by p.`name`");
        return jdbcTemplate.queryForList(sb.toString());
    }


	@Override
	public List<Map<String, Object>> findCommonOperationEvaluation(
			String field_name,String field_value, String problem_level, String startDate,
			String endDate, String driver_code, String taskScene,
			String taskItem, String taskItemPoint, String taskProblem)
			throws Exception {
		StringBuilder sb = new StringBuilder();
        String itemsql = "";
        String subStr="";
        if (taskScene.equals("0")) {
            //统计所有
            itemsql += getCommonItemSql("scenario_code", "scenario_code",
                    field_name,field_value,problem_level, startDate, endDate, driver_code,taskScene,
        			taskItem, taskItemPoint, taskProblem,subStr);
            itemsql += " UNION ALL " + getCommonItemSql("index_type_code", "scenario_code",
                    field_name,field_value,problem_level, startDate, endDate, driver_code,taskScene,
        			taskItem, taskItemPoint, taskProblem,subStr);
            itemsql += " UNION ALL " + getCommonItemSql("index_code", "scenario_code",
                    field_name,field_value,problem_level, startDate, endDate, driver_code,taskScene,
        			taskItem, taskItemPoint, taskProblem,subStr);
            itemsql += " UNION ALL " + getCommonItemSql("problem_code", "scenario_code",
                    field_name,field_value, problem_level,startDate, endDate, driver_code,taskScene,
        			taskItem, taskItemPoint, taskProblem,subStr);
        } else if (taskItem.equals("0")) {
        	subStr="and scenario_code="+taskScene;
            itemsql += getCommonItemSql("index_type_code", "index_type_code",
                    field_name,field_value,problem_level, startDate, endDate, driver_code,taskScene,
        			taskItem, taskItemPoint, taskProblem,subStr);
            itemsql += " UNION ALL " + getCommonItemSql("index_code", "index_type_code",
                    field_name,field_value,problem_level, startDate, endDate, driver_code,taskScene,
        			taskItem, taskItemPoint, taskProblem,subStr);
            itemsql += " UNION ALL " + getCommonItemSql("problem_code", "index_type_code",
                        field_name,field_value,problem_level, startDate, endDate, driver_code,taskScene,
            			taskItem, taskItemPoint, taskProblem,subStr);
        } else if (taskItemPoint.equals("0")) {
        	subStr="and index_type_code="+taskItem;
            itemsql += getCommonItemSql("index_code", "index_code",
                    field_name,field_value,problem_level, startDate, endDate, driver_code,taskScene,
        			taskItem, taskItemPoint, taskProblem,subStr);
            itemsql += " UNION ALL " + getCommonItemSql("problem_code", "index_code",
                    field_name,field_value,problem_level, startDate, endDate, driver_code,taskScene,
        			taskItem, taskItemPoint, taskProblem,subStr);
        } else {
        	subStr="and index_code="+taskItemPoint;
    		itemsql += getCommonItemSql("problem_code", "problem_code",
                    field_name,field_value,problem_level, startDate, endDate, driver_code,taskScene,
        			taskItem, taskItemPoint, taskProblem,subStr);
        }
           
        if ("".equals(itemsql)) return null; 

        return jdbcTemplate.queryForList(itemsql);
	}

	/*itemsql += getItemSql("scenario_code", "scenario_code",
    item_code_name,problem_level, startDate, endDate, driver_code);*/
	public String getCommonItemSql(
	    String item_code, String conditionName, String field_name,
        String field_value,String problem_level, String st, String end, String driver_id,
	    String taskScene,String taskItem, String taskItemPoint, String taskProblem,String subStr) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("select concat(round(b.ct_" + item_code + " / a.show_" + item_code + "*100,1),'%') ratio,a." +
				item_code + " item_code,a.show_" + item_code + " as show_scenario_code ," +
                "b.ct_" + item_code + " as ct_scenario_code ");
		sb.append(" from (select " + item_code + ",count(" + item_code + ") show_" + item_code + " ");
		sb.append(" from yw_driver_operation_evaluation t,dm_problem_level t1,dm_line_road_name t2 ");
		sb.append(" where t.problem_code=t1.id and t.line_code=t2.line_id and driver_id = '" + driver_id + "' ");
		sb.append(subStr);
		sb.append(" and startTime >= '" + st + "' and startTime <= '" + end + "' ");
		sb.append(" and t1.level= '" + problem_level+ "'");
		if("startTime".equals(field_name)){
			sb.append(" and concat(date_format(t."+field_name+",'%H'),'点')= '" + field_value+ "' ");
		}else if ("zhandian".equals(field_name)) {
			sb.append(" and t."+field_name+"= '" + field_value+ "' ");
		}else if ("line_name".equals(field_name)) {
			sb.append(" and t2."+field_name+"= '" + field_value+ "' ");
		}
		sb.append(" group by " + item_code + " ) a");
		sb.append(" JOIN (select " + item_code + ",count(" + item_code + ") ct_" + item_code + " ");
		sb.append(" from yw_driver_operation_evaluation t,dm_problem_level t1,dm_line_road_name t2 ");
		sb.append(" where  t.problem_code=t1.id and t.line_code=t2.line_id " +
                "and driver_id = '" + driver_id + "' and startTime >= '" + st + "' ");
		sb.append(" and startTime <= '" + end + "' and qualified = '违标'");
		sb.append(" and t1.level= '" + problem_level+ "' ");
		if("startTime".equals(field_name)){
			sb.append(" and concat(date_format(t."+field_name+",'%H'),'点')= '" + field_value+ "' ");
		}else if ("zhandian".equals(field_name)) {
			sb.append(" and t."+field_name+"= '" + field_value+ "' ");
		}else if ("line_name".equals(field_name)) {
			sb.append(" and t2."+field_name+"= '" + field_value+ "' ");
		}
		sb.append(subStr);
		sb.append(" group by " + item_code + " ) b on a." + item_code + " = b." + item_code + "");
		sb.append(" group by a." + item_code + ",a.show_" + item_code + ",b.ct_" + item_code + "");
	
		return sb.toString();
	}
}
