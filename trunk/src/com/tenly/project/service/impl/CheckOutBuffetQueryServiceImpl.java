package com.tenly.project.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import com.tenly.common.projecttools.Commons;
import com.tenly.project.bean.YwCheckutAnasysResult;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenly.project.dao.ICheckOutBuffetQueryDao;
import com.tenly.project.dao.ICheckOutDao;
import com.tenly.project.service.ICheckOutBuffetQueryService;
import com.tenly.system.bean.PageBean;

@SuppressWarnings("all")
@Service
public class CheckOutBuffetQueryServiceImpl implements ICheckOutBuffetQueryService {
    @Autowired
    private ICheckOutBuffetQueryDao checkOutBuffetQueryDao;
    @Autowired
    private ICheckOutDao checkOutDao;
    DecimalFormat df = new DecimalFormat("#.000");
    @Override
    public void findAll(PageBean pb, String page, String rows) {
        checkOutBuffetQueryDao.findAll(pb, page, rows);
    }

    @Override
    public List<Map<String, Object>> findAllItemName() {
        return checkOutBuffetQueryDao.findAllItemName();
    }

    @Override
    public List<Map<String, Object>> findItemsHabitsAna(String startDate, String enddate, String driver_code) {

        List<Map<String, Object>> findItemsHabitsAna =
                checkOutBuffetQueryDao.findItemsHabitsAna(startDate, enddate, driver_code);
        List<Map<String, Object>> allItemList = null;
        try {
            allItemList = checkOutDao.findAllItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        getLevelName(findItemsHabitsAna, allItemList);
        return findItemsHabitsAna;
    }

    @Override
    public Map queryItemConditionOne(String start, String end,
                                     String taskScene, String taskItem, String taskItemPoint, String taskProblem,
                                     String driver_code) throws Exception {
        Map map = new HashMap();
        if ("0".equals(taskScene) || "".equals(taskScene)) {
            taskScene = "";
        }
        if ("0".equals(taskItem) || "".equals(taskItem)) {
            taskItem = "";
        }
        if ("0".equals(taskItemPoint) || "".equals(taskItemPoint)) {
            taskItemPoint = "";
        }
        if ("0".equals(taskProblem) || "".equals(taskProblem)) {
            taskProblem = "";
        }
        //查询时段
        List<Map<String, Object>> queryItemIrregularitiesTimes =
                checkOutBuffetQueryDao.queryItemIrregularitiesTimes(
                        start, end, driver_code, taskScene, taskItem, taskItemPoint, taskProblem);
        getProblemRatio(queryItemIrregularitiesTimes);
        //查询的是交路分布
        List<Map<String, Object>> queryItemIrregularitiesJL =
                checkOutBuffetQueryDao.queryAllByDriverId(
                        start, end, driver_code, taskScene, taskItem, taskItemPoint, taskProblem);
        getProblemRatio(queryItemIrregularitiesJL);
        //查询违标区段分布
        List<Map<String, Object>> queryItemIrregularitiesArea =
                checkOutBuffetQueryDao.queryItemIrregularitiesArea(
                        start, end, driver_code, taskScene, taskItem, taskItemPoint, taskProblem);
        getProblemRatio(queryItemIrregularitiesArea);
        //查询违标项点
        List<Map<String, Object>> queryItemIrregularitiesPoint =
                checkOutBuffetQueryDao.queryItemIrregularitiesPoint(
                        start, end, driver_code, taskScene, taskItem, taskItemPoint, taskProblem);
        getProblemRatio(queryItemIrregularitiesPoint);

        if (queryItemIrregularitiesTimes.size() > 0) {
            String tms = (String) queryItemIrregularitiesTimes.get(
                    queryItemIrregularitiesTimes.size() - 1).get("hour_time");
            int ts = Integer.valueOf(tms);
            for (int i = 1; i < 24; i++) {
                if (ts < 24) {
                    Map<String, Object> objectMap = new HashMap<String, Object>();
                    //{ALevel=0, BLevel=1, CLevel=3, hour_time=12, ratio=0.0784}
                    objectMap.put("ALevel", 0);
                    objectMap.put("BLevel", 0);
                    objectMap.put("CLevel", 0);
                    objectMap.put("hour_time", ts + 1);
                    objectMap.put("ratio", BigDecimal.valueOf(0.00));
                    queryItemIrregularitiesTimes.add(objectMap);
                    ts += 1;
                } else {
                    break;
                }
            }
        }
        map.put("queryItemIrregularitiesTimes", queryItemIrregularitiesTimes);
        map.put("queryItemIrregularitiesJL", queryItemIrregularitiesJL);
        //区段分布，排序中间高两边低
        List<Map<String, Object>> areaList = new ArrayList<Map<String, Object>>();
        if (queryItemIrregularitiesArea != null && queryItemIrregularitiesArea.size() > 0) {
            getSortMiddleByList(queryItemIrregularitiesArea);
            //存入新的内存中，在前端去排序放置到最中间位置
            for (int i = 0; i < queryItemIrregularitiesArea.size(); i++) {
                if (i % 2 == 0) {
                    areaList.add(0, queryItemIrregularitiesArea.get(i));
                } else {
                    areaList.add(queryItemIrregularitiesArea.get(i));
                }
            }
        }
        map.put("queryItemIrregularitiesArea", areaList);
        List<Map<String, Object>> allItemName = checkOutBuffetQueryDao.findAllItemName();
        getAllItemNameByCode(queryItemIrregularitiesPoint, allItemName, "code_name",
                "id", "new_code_name", "name");
        //项点分布降序排序
        if (queryItemIrregularitiesPoint != null && queryItemIrregularitiesPoint.size() > 0) {
            getSortMiddleByList(queryItemIrregularitiesPoint);
        }
        map.put("queryItemIrregularitiesPoint", queryItemIrregularitiesPoint);
        return map;
    }

    /**
     * 计算占比
     * A B C的和除以总和
     * @param olist
     * @return
     */
    private static List<Map<String,Object>> getProblemRatio(List<Map<String,Object>> olist){
        DecimalFormat df = new java.text.DecimalFormat("#.####");
        if(olist!=null&&olist.size()>0){
            double t = 0;
            for(Map<String,Object> oLists:olist){
                BigDecimal bd = (BigDecimal)oLists.get("abc");
                double oabc = bd.doubleValue();
                t=t+oabc;
            }
            for(Map<String,Object> objectMap:olist){
                BigDecimal bd = (BigDecimal)objectMap.get("abc");
                double abc = bd.doubleValue();
                objectMap.put("ratio",df.format(abc/t));
            }
        }
        return olist;
    }



    /**
     * 作业执标分析
     */
    @Override
    public Map QueryitemConditionTwo(
            String startDate, String endDate,
            String driver_code, String taskScene,
            String taskItem, String taskItemPoint, String taskProblem) throws Exception {
        Map map = new HashMap();
        List<Map<String, Object>> list =
                checkOutBuffetQueryDao.queryStongItemByDate(startDate, endDate, driver_code);
        //统计强项,违标率是0，作业次数最多
        List<Map<String, Object>> stongItem = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> o : list) {
            Object object = o.get("ratio_index_type_code");
            if (object != null) {
                BigDecimal bd = (BigDecimal) object;
                double v = bd.doubleValue();
                if (v == 0.0000) {
                    stongItem.add(o);
                }
            }
        }
        List<Map<String, Object>> allItemName = checkOutBuffetQueryDao.findAllItemName();
        //由编码转换成名称
        Commons.getSort(stongItem, "ratio_index_type_code", "show_index_type_code");
        map.put("queryCurrentDriverStrongItem", stongItem);//强项
        Commons.getSortDesc(list, "ratio_index_type_code", "count_index_type_code");
        map.put("queryCurrentDriverLossItem", list);//弱项
        return map;
    }

    private void getLevelName(
            List<Map<String, Object>> queryCurrentDriverStrongItem,
            List<Map<String, Object>> allItemList) {
        for (Map<String, Object> info : queryCurrentDriverStrongItem) {
            String scenario_code = (String) info.get("scenario_code");
            String index_type_code = (String) info.get("index_type_code");
            String index_code = (String) info.get("index_code");
            String problem_code = (String) info.get("problem_code");
            for (Map<String, Object> item : allItemList) {
                String id = "" + (Integer) item.get("id");
                if (scenario_code.equals(id)) {
                    info.put("new_scenario_code", item.get("name"));
                }
                if (index_type_code.equals(id)) {
                    info.put("new_index_type_code", item.get("name"));
                }
                if (index_code.equals(id)) {
                    info.put("new_index_code", item.get("name"));
                }
                if (problem_code.equals(id)) {
                    info.put("new_problem_code", item.get("name"));
                }
            }

        }
    }

    private void getLevelNameByItemCode(
            List<Map<String, Object>> queryCurrentDriverStrongItem,
            List<Map<String, Object>> allItemList, String itemName) {
        for (Map<String, Object> info : queryCurrentDriverStrongItem) {
            String scenario_code = (String) info.get(itemName);
            for (Map<String, Object> item : allItemList) {
                String id = "" + (Integer) item.get("id");
                if (scenario_code.equals(id)) {
                    info.put("new_code_name", item.get("name"));
                }
            }

        }
    }

    /**
     * 根据作业项点分析条件中选择的作业场景  项点  项目  问题   来确定下级项点  项目  及问题
     */
    @Override
    public Map queryItemByLevel(String itemValue, String level)
            throws Exception {
        Map map = new HashMap();
        //如果是作业场景选项  需要查询 作业项目  项点   问题
        List<Map<String, Object>> lsit =
                checkOutBuffetQueryDao.queryItemAll();
        if (StringUtils.isNotBlank(itemValue) && !StringUtils.isEmpty(itemValue)
                && StringUtils.isNotBlank(level) && !StringUtils.isEmpty(level)
                ) {
            if ("1".equals(level)) {
                List<Map<String, Object>> firstChildrenNode = getFirstChildrenNode(itemValue, lsit);
                //作业项点
                List<Map<String, Object>> point = getChildrenNode(lsit, firstChildrenNode);
                //作业问题
                List<Map<String, Object>> problem = getChildrenNode(lsit, point);
                map.put("childrenList", firstChildrenNode);
                map.put("pointChildrenList", point);
                map.put("proChildrenList", problem);
            } else if ("2".equals(level)) {
                List<Map<String, Object>> firstChildrenNode = getFirstChildrenNode(itemValue, lsit);
                List<Map<String, Object>> childrenNode = getChildrenNode(lsit, firstChildrenNode);
                map.put("pointChildrenList", firstChildrenNode);
                map.put("proChildrenList", childrenNode);
            } else if ("3".equals(level)) {
                List<Map<String, Object>> firstChildrenNode = getFirstChildrenNode(itemValue, lsit);
                map.put("proChildrenList", firstChildrenNode);
            }
        }
        return map;
    }

    private List<Map<String, Object>> getFirstChildrenNode(String itemValue, List<Map<String, Object>> lsit) {
        List<Map<String, Object>> item = new ArrayList<Map<String, Object>>();
        //作业项目
        for (Map<String, Object> objectMap : lsit) {
            //二级作业项目，不用再次请求数据库
            int pid = (Integer) objectMap.get("parent_id");
            if (pid == Integer.valueOf(itemValue)) {
                item.add(objectMap);
            }
        }
        return item;
    }

    private List<Map<String, Object>> getChildrenNode(List<Map<String, Object>> lsit, List<Map<String, Object>> item) {
        List<Map<String, Object>> point = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> o1 : item) {
            int itemcode = (Integer) o1.get("id");
            for (Map<String, Object> objectMap : lsit) {
                int objectMapCode = (Integer) objectMap.get("parent_id");
                if (objectMapCode == itemcode) {
                    point.add(objectMap);
                }
            }

        }
        return point;
    }

    //-------------------------------------------自助查询退勤历史记录--------------------------------
    @Override
    public List<Map<String, Object>> getCheckOutConfirmTime(
            String driverCode, String startDate, String endDate) throws Exception {
        if (StringUtils.isNotBlank(driverCode) && !StringUtils.isEmpty(driverCode)
                && StringUtils.isNotBlank(startDate) && !StringUtils.isEmpty(startDate)
                && StringUtils.isNotBlank(endDate) && !StringUtils.isEmpty(endDate)) {
            return checkOutBuffetQueryDao.getCheckOutConfirmTime(driverCode, startDate, endDate);
        }
        return null;
    }

    /**
     * 数据完整性分析
     * 废弃
     *
     * @param driverCode
     * @param sys_date
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> getCurrentDriverStartAndEnd(String driverCode, String sys_date) throws Exception {
        if (StringUtils.isBlank(driverCode) || StringUtils.isEmpty(driverCode)
                || StringUtils.isBlank(sys_date) || StringUtils.isEmpty(sys_date)) return null;

        //先通过乘务员退勤确认时间获取乘务员的出勤和退勤时间    yw_checkout_confirm 表
        Map<String, Object> inAndOutTime =
                checkOutBuffetQueryDao.queryCheckInAndOutTimeByDriverCodeAndSysDate(driverCode, sys_date);
        //然后通过乘务员的出退勤时间和乘务员编号  获取乘务员的这段时间范围内的交路图数据  yw_checkout_jl 表
        if (checkOutBuffetQueryDao == null) return null;
        List<Map<String, Object>> list = checkOutBuffetQueryDao.getDriverJLGrahData(
                (String) inAndOutTime.get("chuqin_time"),
                (String) inAndOutTime.get("tuiqin_time"),
                driverCode);
        Commons.getListToSortReverseOrderByDate(list);
        return list;
    }

    /**
     * 项点分析结果
     *
     * @param driverCode
     * @param sys_date
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> getCurrentDriverAnalyData(String driverCode,
                                                               String ondutytime, String offdutytime) throws Exception {
        if (StringUtils.isBlank(driverCode) || StringUtils.isEmpty(driverCode)
                || StringUtils.isBlank(ondutytime) || StringUtils.isEmpty(ondutytime)) return null;
        //获取结果数据		yw_checkout_anasys_result 表
        List<Map<String, Object>> list = checkOutBuffetQueryDao.getCurrentDriverAnalyData(
                ondutytime,
                offdutytime,
                driverCode);
        if (list == null || list.size() < 1) return null;
        List<Map<String, Object>> nList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).put("id", i);
            nList.add(list.get(i));
        }
        return nList;
    }

    @Override
    public List<Map<String, Object>> getCurrentRowDetail(YwCheckutAnasysResult yar) throws Exception {
        //查询所有项点名称
        List<Map<String, Object>> allItemList = checkOutDao.findAllItem();
        List<Map<String, Object>> currentRowDetail = checkOutBuffetQueryDao.getCurrentRowDetail(yar);
        int count = 1;
        for (Map<String, Object> objectMap : currentRowDetail) {
            objectMap.put("queryDetailBtn", "0");
            objectMap.put("myId", count);
            count++;
        }
        //通过项点编码获取对应的名称
        getAllItemNameByCode(currentRowDetail, allItemList);
        return currentRowDetail;

    }

    /**
     * 自助查询
     * 项点分析  作业违标分布点击查看作业问题 以及详情 到lkj数据
     *
     * @param startDate
     * @param endDate
     * @param driver_code
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> getViolationDetail(
            String startDate, String endDate, String driver_code, String item_code_name, String problem_level,
            String taskScene, String taskItem, String taskItemPoint, String taskProblem) throws Exception {
        List<Map<String, Object>> list = null;
        String[] stArr = new String[]{"scenario_code", "index_type_code", "index_code", "problem_code"};
        if (StringUtils.isNotBlank(startDate) && !StringUtils.isEmpty(startDate)
                && StringUtils.isNotBlank(endDate) && !StringUtils.isEmpty(endDate)
                && StringUtils.isNotBlank(driver_code) && !StringUtils.isEmpty(driver_code)) {
            list = checkOutBuffetQueryDao.findOperationEvaluationByDateAndDriverCode(item_code_name, problem_level,
                    startDate, endDate, driver_code, taskScene, taskItem, taskItemPoint, taskProblem);
            //select startTime,zhandian,train_batch_no,scenario_code,index_type_code,index_code,problem_code
            //查询所有项点编码父节点  dm_point
            List<Map<String, Object>> allItemName = checkOutBuffetQueryDao.findAllItemName();
            int i = 0; //将pid设为0
            for (Map<String, Object> map : list) {
                String itemCode = (String) map.get("item_code");
                Object ratio = map.get("ratio");
                if (ratio != null) {
                    map.put("ratio", ratio);
                }
                for (Map<String, Object> ssp : allItemName) {
                    int itemCodeName = (Integer) ssp.get("id");
                    if (itemCode.equals(String.valueOf(itemCodeName))) {
                        String ss = (String) map.get("item_code");
                        Object s = map.get("ratio");
                        if (s != null) {
                            map.put("ratio", s);
                        }
                        map.put("id", Integer.valueOf((String) map.get("item_code")));
                        if (i == 0) {
                            map.put("pid", 0);
                        } else {
                            map.put("pid", ssp.get("parent_id"));
                        }
                        map.put("item_name", ssp.get("name"));
                        if (ss.length() > 6) {
                            map.put("scan_detail", "1");
                        } else {
                            map.put("scan_detail", "0");
                        }
                        i++;
                        break;
                    }
                }
            }
        }
        //4050101   这个项点编码在   dm_point中找不到
        return list;
    }

    @Override
    public List<Map<String, Object>> queryProblemViolationGridDetail(
            String startDate, String endDate, String driver_code, String item_code) throws Exception {

        return checkOutBuffetQueryDao.queryProblemViolationGridDetail(
                startDate, endDate, driver_code, item_code);
    }

    @Override
    public List<Map<String, Object>> queryProblemCommonGridDetail(
            String startDate, String endDate, String driver_code, String item_code, String field_name, String field_value) throws Exception {
        List<Map<String, Object>> list = checkOutBuffetQueryDao.queryProblemCommonGridDetail(
                startDate, endDate, driver_code, item_code, field_name, field_value);
        for (Map<String, Object> o : list) {
            o.put("dataDetailForLKJ", "");//查询数据明细按钮
        }

        return list;
    }

    /**
     * 自助查询
     * 作业执标分析，操作评价及违标次数
     *
     * @param startDate     开始时间
     * @param endDate       结束时间
     * @param taskScene     作业场景
     * @param taskitem      作业项目
     * @param taskItemPoint 作业项点
     * @param taskProblem   作业问题
     * @return
     */
    @Override
    public List<Map<String, Object>> getTaskEvaluate(String driver_code,
                                                     String startDate, String endDate,
                                                     String taskScene, String taskitem, String taskItemPoint, String taskProblem) throws Exception {

        //统计违标
        List<Map<String, Object>> taskEvaluate = checkOutBuffetQueryDao.getTaskEvaluate(driver_code, startDate, endDate,
                taskScene, taskitem, taskItemPoint, taskProblem);
        //查询作业评价
        List<Map<String, Object>> list = checkOutBuffetQueryDao.findEvaluateBy(driver_code,
                startDate, endDate,
                taskScene, taskitem, taskItemPoint, taskProblem);
        //违标跟作业评价进行交叉，先找到他们相同日期的，
        //把评价添加到相同日期里面，第二次循环找出评价日期不在违标范围内的，直接添加进行，违标的都为0，但是有评价的内容
        for (Map<String, Object> eval : list) {//评价内容
            for (Map<String, Object> item : taskEvaluate) {//违标内容
                String date = (String) item.get("date");
                String eDate = (String) eval.get("comment_date");
                if (date.equals(eDate)) {
                    item.put("comment_count", eval.get("comment_count"));//评价次数
                    break;
                }
            }
        }
        for (Map<String, Object> eval : list) {//评价内容
            boolean bl = true;
            for (Map<String, Object> item : taskEvaluate) {//违标内容
                String date = (String) item.get("date");
                String eDate = (String) eval.get("date");
                if (date.equals(eDate)) {
                    bl = false;
                    break;
                }
            }
            if (bl) {
                eval.put("ALevel", 0);
                eval.put("BLevel", 0);
                eval.put("CLevel", 0);
                taskEvaluate.add(eval);
            }
        }
        //排序
        Commons.sortEvalByDate(taskEvaluate, "date");
        return taskEvaluate;
    }

    @Override
    public List<Map<String, Object>> getEvaluateContentByDriverCode(String driver_code, String evaldate) {

        List<Map<String, Object>> allItemName = checkOutBuffetQueryDao.findAllItemName();
        List<Map<String, Object>> evaluateContentByDriverCode =
                checkOutBuffetQueryDao.getEvaluateContentByDriverCode(driver_code, evaldate);

        replaceItemPointCode(evaluateContentByDriverCode, allItemName);

        return evaluateContentByDriverCode;
    }

    /**
     * 历史查询，获取乘务员的出退勤时间，填充历史查询出退勤下拉选项
     *
     * @param driver_code
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> getChuTuiQinDate(String driver_code) throws Exception {
        //查询最近一次出勤
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> maxchutuiqin = checkOutBuffetQueryDao.queryMaxCheckInOutTime(driver_code);
        List<Map<String, Object>> chuTuiQinDate = checkOutBuffetQueryDao.getChuTuiQinDate(driver_code);
        //处理日期中天，月转为int类型，前端需要int类型
        if (chuTuiQinDate != null && chuTuiQinDate.size() > 0) {
            for (Map<String, Object> o : chuTuiQinDate) {
                String onday = (String) o.get("onday");
                String[] splits = onday.split(",");
                String str = "";
                for (String s : splits) {
                    int day = Integer.valueOf(s);
                    str += day + ",";
                }
                String nn = str.substring(0, str.length() - 1);
                o.put("onday", str);
                //处理日期中的月份为int然后转为String
                String mm = (String) o.get("ondutytime_ym");
                String[] mmarr = mm.split("-");
                int mms = Integer.valueOf(mmarr[1]);
                String nnmm = mmarr[0] + "-" + mms;
                o.put("ondutytime_ym", nnmm);
            }
        }
        map.put("maxchutuiqin", maxchutuiqin);
        map.put("chuTuiQinDate", chuTuiQinDate);
        return map;
    }

    /**
     * 通过乘务员的编号和出勤时间获取他的退勤时间
     *
     * @param driver_code
     * @param ondutytime
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> getTuiQinDate(String driver_code, String ondutytime) throws Exception {

        return checkOutBuffetQueryDao.getTuiQinDate(driver_code, ondutytime);
    }

    @Override
    public void findAllForChuTuiQinInfo(
            PageBean pageBean, String ondutytime, String offdutytime, String driverCode) throws Exception {
        checkOutBuffetQueryDao.findAllForChuTuiQinInfo( pageBean,  ondutytime,  offdutytime,  driverCode);
    }

    /**
     * 退勤结果
     *
     * @param driver_id
     * @param sys_date
     * @return
     * @throws Exception
     */
    @Override
    public Map getResultInfo(String driver_id, String ondutytime, String offdutytime) throws Exception {
        if (StringUtils.isBlank(driver_id) || StringUtils.isEmpty(driver_id)
                || StringUtils.isBlank(ondutytime) || StringUtils.isEmpty(ondutytime)) return null;
        Map map = new HashMap();
        //先查退勤结果
        List<Map<String, Object>> resInfo = checkOutBuffetQueryDao.getResultInfo(driver_id, ondutytime, offdutytime);
        //然后查交路信息
        List<Map<String, Object>> jlInfo = checkOutBuffetQueryDao.getDriverJLGrahData(driver_id, ondutytime, offdutytime);
        map.put("resInfo", resInfo);
        map.put("jlInfo", jlInfo);
        return map;
    }


    /**
     * 公共方法
     *
     * @param lsit
     * @return
     */
    private static String getItemGroupSQL(List<Map<String, Object>> lsit) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        if (lsit != null && lsit.size() > 0) {
            sb.append("select * from dm_point f");
            for (Map<String, Object> map : lsit) {
                Integer id = (Integer) map.get("id");
                if (count == 1) {
                    sb.append(" where parent_id=" + id + "");
                } else {
                    sb.append(" or parent_id=" + id + "");
                }
                count++;
            }
        }
        return sb.toString();
    }

    /**
     * 作业评价数据处理
     *
     * @param list
     * @param allItemList
     */
    private void replaceItemPointCode(
            List<Map<String, Object>> list, List<Map<String, Object>> allItemList) {
        for (Map<String, Object> info : list) {
            Object scenario_code = info.get("scenario_code");
            Object index_type_code = info.get("index_type_code");
            Object index_code = info.get("index_code");
            Object problem_code = info.get("problem_code");
            for (Map<String, Object> item : allItemList) {
                String id = "" + (Integer) item.get("id");
                if (scenario_code != null) {
                    String scenarioCode = (String) scenario_code;
                    if (!scenarioCode.equals("全部")) {
                        if (scenarioCode.equals(id)) {
                            info.put("new_scenario_code", (String) item.get("name"));
                        }
                    }
                }
                if (index_type_code != null) {
                    String scenarioIndexTypeCode = (String) index_type_code;
                    if (!scenarioIndexTypeCode.equals("全部")) {
                        if (scenarioIndexTypeCode.equals(id)) {
                            info.put("new_index_type_code", (String) item.get("name"));
                        }
                    }
                }
                if (index_code != null) {
                    String indexCode = (String) index_code;
                    if (!indexCode.equals("全部")) {
                        if (indexCode.equals(id)) {
                            info.put("new_index_code", (String) item.get("name"));
                        }
                    }
                }
                if (problem_code != null) {
                    String problemCode = (String) problem_code;
                    if (!problemCode.equals("全部")) {
                        if (problemCode.equals(id)) {
                            info.put("new_problem_code", (String) item.get("name"));
                        }
                    }
                }
            }
        }
        for (Map<String, Object> info : list) {
            Object scenario_code = info.get("scenario_code");
            if (scenario_code == null) {
                info.put("new_scenario_code", "");
            } else {
                String ss = (String) scenario_code;
                if (ss.equals("全部")) {
                    info.put("new_scenario_code", "全部");
                }
            }
            Object index_type_code = info.get("index_type_code");
            if (index_type_code == null) {
                info.put("new_index_type_code", "");
            } else {
                String ss = (String) index_type_code;
                if (ss.equals("全部")) {
                    info.put("new_index_type_code", "全部");
                }
            }
            Object index_code = info.get("index_code");
            if (index_code == null) {
                info.put("new_index_code", "");
            } else {
                String ss = (String) index_code;
                if (ss.equals("全部")) {
                    info.put("new_index_code", "全部");
                }
            }
            Object problem_code = info.get("problem_code");
            if (problem_code == null) {
                info.put("new_problem_code", "");
            } else {
                String ss = (String) problem_code;
                if (ss.equals("全部")) {
                    info.put("new_problem_code", "全部");
                }
            }
        }
    }


    private void getAllItemNameByCode(
            List<Map<String, Object>> list, List<Map<String, Object>> allItemList) {
        for (Map<String, Object> info : list) {
            String problem_code = (String) info.get("problem_code");
            for (Map<String, Object> item : allItemList) {
                String id = "" + (Integer) item.get("id");
                getNameByCode(info, problem_code, item, id);
            }
        }
    }

    private void getNameByCode(
            Map<String, Object> info,
            String problem_code, Map<String, Object> item, String id) {
        if (problem_code.equals(id)) {
            info.put("new_problem_name", item.get("name"));
        }
    }

    /**
     * @param list
     * @param allItemList
     * @param codeName    list 中key
     * @param codeIdName  项点名称id
     * @param infokey     新的项点名称key
     * @param itemName    获取项点的名称
     */
    private void getAllItemNameByCode(
            List<Map<String, Object>> list,
            List<Map<String, Object>> allItemList,
            String codeName,
            String codeIdName, String infokey, String itemName) {

        for (Map<String, Object> info : list) {
            String problem_code = (String) info.get(codeName);
            for (Map<String, Object> item : allItemList) {
                String id = "" + (Integer) item.get(codeIdName);
                getNameByCode(info, problem_code, item, id, infokey, itemName);
            }
        }
    }

    private void getNameByCode(
            Map<String, Object> info,
            String problem_code, Map<String, Object> item, String id, String infoKey, String itemName) {
        if (problem_code.equals(id)) {
            info.put(infoKey, item.get(itemName));
        }
    }

    @Override
    public List<Map<String, Object>> getCommonDetail(String startDate,
                                                     String endDate, String driver_code, String field_name, String field_value,
                                                     String problem_level, String taskScene, String taskItem,
                                                     String taskItemPoint, String taskProblem) throws Exception {

        List<Map<String, Object>> list = null;
        String[] stArr = new String[]{"scenario_code", "index_type_code", "index_code", "problem_code"};
        if (StringUtils.isNotBlank(startDate) && !StringUtils.isEmpty(startDate)
                && StringUtils.isNotBlank(endDate) && !StringUtils.isEmpty(endDate)
                && StringUtils.isNotBlank(driver_code) && !StringUtils.isEmpty(driver_code)) {
            list = checkOutBuffetQueryDao.findCommonOperationEvaluation(field_name, field_value, problem_level,
                    startDate, endDate, driver_code, taskScene, taskItem, taskItemPoint, taskProblem);
            //查询所有项点编码父节点  dm_point
            List<Map<String, Object>> allItemName = checkOutBuffetQueryDao.findAllItemName();
            int i = 0;//将pid设为0
            for (Map<String, Object> map : list) {
                String itemCode = (String) map.get("item_code");
                Object ratio = map.get("ratio");
                if (ratio != null) {
                    map.put("ratio", ratio);
                }
                for (Map<String, Object> ssp : allItemName) {
                    int itemCodeName = (Integer) ssp.get("id");
                    if (itemCode.equals(String.valueOf(itemCodeName))) {
                        String ss = (String) map.get("item_code");
                        String s = (String) map.get("ratio");
                        if (s != null) {
                            map.put("ratio", s);
                        }
                        map.put("id", Integer.valueOf((String) map.get("item_code")));
                        if (i == 0) {
                            map.put("pid", 0);
                        } else {
                            map.put("pid", ssp.get("parent_id"));
                        }
                        map.put("item_name", ssp.get("name"));
                        if (ss.length() > 6) {
                            map.put("scan_detail", "1");
                        } else {
                            map.put("scan_detail", "0");
                        }
                        i++;
                        break;
                    }
                }
            }
        }
        //4050101   这个项点编码在   dm_point中找不到
//        List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
//        for (int i = 0; i < list.size(); i++) {
//            if (problem_level.equals("A")) {
//
//            } else if (problem_level.equals("B")) {
//
//            } else if (problem_level.equals("C")) {
//
//            }
//        }
        return list;

    }

    /**
     * 降序排序
     *
     * @param o
     */
    private static void getSortMiddleByList(List<Map<String, Object>> o) {
        Collections.sort(o, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Object act_o1 = o1.get("ALevel");
                Object bct_o1 = o1.get("BLevel");
                Object cct_o1 = o1.get("CLevel");
                Object rct_o1 = o1.get("ratio");
                double ct = (rct_o1 == null ? 0 : Double.valueOf((String)rct_o1))
                        + (act_o1 == null ? 0 : ((BigDecimal) act_o1).doubleValue())
                        + (bct_o1 == null ? 0 : ((BigDecimal) bct_o1).doubleValue())
                        + (cct_o1 == null ? 0 : ((BigDecimal) cct_o1).doubleValue());
                Object act_o2 = o2.get("ALevel");
                Object bct_o2 = o2.get("BLevel");
                Object cct_o2 = o2.get("CLevel");
                Object rct_o2 = (String)o2.get("ratio");
                double ct2 = (rct_o2 == null ? 0 : Double.valueOf((String)rct_o2))
                        + (act_o2 == null ? 0 : ((BigDecimal) act_o2).doubleValue())
                        + (bct_o2 == null ? 0 : ((BigDecimal) bct_o2).doubleValue())
                        + (cct_o2 == null ? 0 : ((BigDecimal) cct_o2).doubleValue());
                if (ct < ct2) {
                    return 1;
                } else if (ct == ct2) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
    }
}
