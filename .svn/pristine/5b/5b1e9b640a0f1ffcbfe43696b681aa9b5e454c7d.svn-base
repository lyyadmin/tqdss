package com.tenly.project.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Logger;

import javax.xml.datatype.XMLGregorianCalendar;

import byxx.resource.DriverAlcoholPojo;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.tenly.common.projecttools.*;
import com.tenly.system.bean.SysUser;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import byxx.cxf.utils.CXFUtils;
import byxx.resource.DriverDutyPojo;
import byxx.resource.DriverPlanPojo;
import byxx.resource.PbResource;

import com.tenly.project.bean.DataLose;
import com.tenly.project.bean.YwDriverOperationEvaluation;
import com.tenly.project.dao.ICheckOutDao;
import com.tenly.project.service.ICheckOutService;
import com.tenly.system.bean.PageBean;

@SuppressWarnings("all")
@Service
public class CheckOutServiceImpl implements ICheckOutService {
    @Autowired
    private ICheckOutDao checkOutDao;

    @Override
    public Map<String, Object> queryCurrentCheckOut(String driverID) {

        return checkOutDao.queryCurrentCheckOut(driverID);
    }

    /**
     * 当前司机的业务分析结果
     */
    @Override
    public void queryCurrentDriverAnalyData(String driverId, PageBean pageBean) throws Exception {
        String fsjh="";
        Map<String, String> chuTuiQinShiJian = Commons.getChuTuiQinShiJian(driverId,fsjh);
        List<Map<String, Object>> list = null;//分析结果数据
        String onTime =  chuTuiQinShiJian.get("ontime");//Commons.endOneHourToNowDate(Commons.getStringToDate(chuTuiQinShiJian.get("ontime")), 24);
        String tmp = chuTuiQinShiJian.get("currenttime");//Commons.endOneHourToNowDate(Commons.getStringToDate(chuTuiQinShiJian.get("currenttime")), 24);
        list = checkOutDao.queryCurrentDriverAnalyData(driverId,onTime,tmp, pageBean);
        String noteStartDateAndTermial = onTime+"_"+tmp;//记录始发终到时间
        if (list != null && list.size() > 0) {
            for(Map<String,Object> oo:list){
                oo.put("noteStartDateAndTermial",noteStartDateAndTermial);
            }
        }
        pageBean.setRows(list);
        //删除该乘务员历史记录
        checkOutDao.deleteTempTblDataByDriverCode(driverId);
        //插入数据到数据库
        checkOutDao.saveAnasysisResult(driverId,noteStartDateAndTermial);
    }

    /**
     * 把系统表出来的编号，替换成中文名称
     *
     * @param noteStartDateAndTermial
     * @param list
     * @param allItemList
     */
    private void getAllItemNameByCode(
            String noteStartDateAndTermial,
            List<Map<String, Object>> list, List<Map<String, Object>> allItemList) {
        for (Map<String, Object> info : list) {
            String scenario_code = (String) info.get("scenario_code");
            String index_type_code = (String) info.get("index_type_code");
            String index_code = (String) info.get("index_code");
            String problem_code = (String) info.get("problem_code");
            for (Map<String, Object> item : allItemList) {
                String id = "" + (Integer) item.get("id");
                getNameByCode(info, scenario_code, index_type_code, index_code, problem_code, item, id);
            }
            info.put("noteStartDateAndTermial", noteStartDateAndTermial);
        }
    }

    private void getNameByCode(
            Map<String, Object> info, String scenario_code,
            String index_type_code, String index_code,
            String problem_code, Map<String, Object> item, String id) {
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

    /**
     * 问题项点详情
     * 更新2018-12-19
     * hqh
     */
    @Override
    public List<Map<String, Object>> itemsDetailPro(YwDriverOperationEvaluation checkOut)
            throws Exception {
        //历史接口
        List<Map<String, Object>> itemsDetailPro = checkOutDao.itemsDetailPro(checkOut);
        //查询项点问题级别，如果为BC级别，就不需要有问题确认下拉选项了
        for (Map<String, Object> o : itemsDetailPro) {
            o.put("driverException", (String) o.get("level"));
            o.put("queryDetailBtn", "0");
        }
        return itemsDetailPro;
    }


    /**
     * 填充运安数据  2018-12-03  已经过期
     *
     * @param pbResource
     * @param list
     * @param ct
     * @param ondutytimeDateStr
     * @throws ParseException
     */
    private void getYunAnList(PbResource pbResource, List<DriverPlanPojo> list,
                              int ct, String ondutytimeDateStr, String driver_id) throws ParseException {
        Map<String, DriverPlanPojo> app = new HashMap<String, DriverPlanPojo>();
        for (int i = 0; i <= ct; i++) {
            DriverPlanPojo driverPlanByWorkNoAndTimeStrAndType =
                    pbResource.getDriverPlanByWorkNoAndTimeStrAndType(
                            Integer.valueOf(driver_id),
                            Commons.getLeiJiaDate(Commons.getDate(ondutytimeDateStr), i), 1);
            if (driverPlanByWorkNoAndTimeStrAndType != null) {
                list.add(driverPlanByWorkNoAndTimeStrAndType);
            }
        }
        if (list != null && list.size() > 0) {
            for (DriverPlanPojo pj : list) {
                if (pj.getPlanondutytime() == null) {

                } else {
                    app.put(CXFUtils.DateToString(CXFUtils.DateToXML(pj.getPlanondutytime())), pj);
                }
            }
            list.clear();
        }

        for (Map.Entry<String, DriverPlanPojo> dpp : app.entrySet()) {
            list.add(dpp.getValue());
        }
    }


    @Override
    public List<Map<String, Object>> getLoseCause() {

        return checkOutDao.getLoseCause();
    }

    /**
     * 处理司机是否能够正常退勤
     */
    @Override
    public List<Map<String, Object>> getCurrentDriverCheckOutResult(
            String driverId) throws Exception {

        return checkOutDao.getCurrentDriverCheckOutResult(driverId);
    }

    @Override
    public List<Map<String, Object>> getCondition() throws Exception {

        return checkOutDao.getCondition();
    }

    @Override
    public List<Map<String, Object>> getExceptionCause() throws Exception {

        return checkOutDao.getExceptionCause();
    }

    @Override
    public List<Map<String, Object>> findAllItemsProblem() throws Exception {
        return checkOutDao.findAllItemsProblem();
    }

    @Override
    public List<Map<String, Object>> getCheckOutResultDetailAndLevel()
            throws Exception {
        return checkOutDao.getCheckOutResultDetailAndLevel();
    }

    @Override
    public void saveDataLoseCause(String sjh, String traincheci,
                                  String file_lose_cause) throws Exception {
        checkOutDao.saveDataLoseCause(sjh, traincheci,
                file_lose_cause);
    }

    /**
     * 首先获取运安的出退勤时间，然后和mysql数据库比较和运安出退勤时间是否一致，如果一致，证明已经退勤了，否则允许退勤
     * 1 已经退勤
     * 0 未退勤
     */
    @Override
    public String queryDriverSecondRequest(String driver_id)
            throws Exception {
        PbResource pbResource = CXFUtils.getPbResource();
        if (StringUtils.isBlank(driver_id) || StringUtils.isEmpty(driver_id)) {
            return "";
        }
        int driverId = Integer.valueOf(driver_id);
        List<String> jwdChuTuiQinPlace = checkOutDao.findJWDChuTuiQinPlace(driver_id.substring(0, 2));
        Map<String, Object> yunAnDate = getYunAnDate(pbResource, driverId, driver_id.substring(0, 2), jwdChuTuiQinPlace);
        Object onDate = yunAnDate.get("onDate");
        Object offDate = yunAnDate.get("dtOff");
        if (onDate == null || offDate == null) {
            return "";
        }
        Date ondate = (Date) onDate;
        Date offdate = (Date) offDate;
        String ondatestr = CXFUtils.DateToString(ondate);
        String offdatestr = CXFUtils.DateToString(offdate);
        //待优化
        Map<String, Object> queryDriverSecondRequest = checkOutDao.queryDriverSecondRequest(ondatestr, offdatestr, driver_id);
        if (queryDriverSecondRequest == null) {
            return "0";
        }
        String ontime = (String) queryDriverSecondRequest.get("chuqin_time");
        String offtime = (String) queryDriverSecondRequest.get("tuiqin_time");
        if (ondatestr.equals(ontime) && offdatestr.equals(offtime)) {
            //已经退勤
            return "1";
        }
        return "0";
    }

    @Override
    public List<Map<String, Object>> findAllPositionToDriverId()
            throws Exception {
        return checkOutDao.findAllPositionToDriverId();
    }

    @Override
    public List<Map<String, Object>> findCodeRuleByJiWuDuanCode(
            String jiwuduan_code) throws Exception {
        if (StringUtils.isNotBlank(jiwuduan_code) && !StringUtils.isEmpty(jiwuduan_code)) {
            String[] splits = jiwuduan_code.split("_");
            return checkOutDao.findCodeRuleByJiWuDuanCode(splits[0]);
        }
        return null;
    }

    @Override
    public Map<String, Object> queryDriverIsExsits(String string,
                                                   String driver_code) throws Exception {
        return checkOutDao.queryDriverIsExsits(string, driver_code);
    }

    /**
     * 1、保存数据缺失确认的数据
     * 2、始发终到的完整数据
     * 3、数据分析结果的完整数据
     */
    @Override
    public void saveAll(List<DataLose> dataLoseList,
                        List<Map<String, Object>> ol, String driverId, String checkInAndOutTimes,
                        List<Map<String, Object>> rows, String checkoutResult) throws Exception {
        String systemCurrentDate = Commons.getSystemCurrentDate();
        if (StringUtils.isBlank(checkInAndOutTimes) || StringUtils.isEmpty(checkInAndOutTimes)) {
            /**
             * 处理起始结束时间
             */
            String fsjh="";
            Map<String, String> chuTuiQinShiJian = Commons.getChuTuiQinShiJian(driverId,fsjh);
            String onTime = "";//上次的lkj转储时间
            String tmp = "";//临时变量
            onTime =  chuTuiQinShiJian.get("ontime");
            tmp = chuTuiQinShiJian.get("currenttime");
            checkInAndOutTimes = onTime + "_" + tmp;
        }

        //缺失数据原因   及退勤结果状态和退勤结果说明
        checkOutDao.saveDataLose(dataLoseList, driverId, checkInAndOutTimes, systemCurrentDate, checkoutResult);
        //始发终到交路图数据
        checkOutDao.saveStartAndEndStation(ol, driverId, checkInAndOutTimes, systemCurrentDate);
        //分析结果数据
        checkOutDao.saveAnasysResult(rows, systemCurrentDate);
        //发送短信数据 checkoutResult发送短信内容
        //checkoutResult = checkoutResult.replaceAll("\r|\n", "");
    }

    /**
     * 短信发送
     *
     * @param msg 短信内容
     * @throws Exception 0        使用 配置电话    1   不使用
     */
    private static String Sendmsg(String msg, String phone) {
        HttpClient client = null;
        PostMethod post = null;
        int statusCode = 0;
        String result = null;
        try {
            client = new HttpClient();
            post = new PostMethod("http://10.128.40.98/pisp/service/sms.asmx/postSingle");
            //                        "http://10.128.40.98/pisp/service/sms.asmx/postSingle"
            // 在头文件中设置转码
            post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            post.addParameter("account", "shtlgs");
            post.addParameter("password", "123456");
            post.addParameter("mobile", phone);
            post.addParameter("content", msg);
            post.addParameter("code", "E2WPM2vd");
            client.executeMethod(post);
            Header[] headers = post.getResponseHeaders();
            statusCode = post.getStatusCode();
            System.out.println("statusCode:" + statusCode);
            //返回消息
            result = new String(post.getResponseBodyAsString().getBytes("gbk"));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (post != null) post.releaseConnection();
            if (client != null) client.getHttpConnectionManager().closeIdleConnections(0);
        }
        return "状态：" + statusCode + "，信息:" + result;
    }

    @Override
    public void saveExceptionData(List<YwDriverOperationEvaluation> ywDriverOperationEvaluations) throws Exception {
        checkOutDao.saveExceptionData(ywDriverOperationEvaluations);
    }

    @Override
    public Map<String,Object> getDriverJLWithStartAndEndStation(String driverId) throws Exception {
        Map<String,Object> datamap = new HashMap<String,Object>();

        PbResource pbResource = CXFUtils.getPbResource();
        List<Map<String, Object>> list = null;
        List<Map<String, Object>> yuanchutuiqinlist = null;
        long nh = 1000*60*60;
        long nm = 1000*60;
        try {

            /**
             * 获取各个派班室包含的地点
             */
            List<Map<String,Object>> pointlist = checkOutDao.findAllPoint();

            /**
             * 获取该司机副司机号
             */
            String fsjh="";
            /**
             * 处理起始结束时间
             */
            Map<String, String> chuTuiQinShiJian = Commons.getChuTuiQinShiJian(driverId,fsjh);
            String onTime = "";//上次的lkj转储时间
            String tmp = "";//临时变量
            onTime =  Commons.endOneHourToNowDate(Commons.getStringToDate(chuTuiQinShiJian.get("ontime")), 1);
            tmp = Commons.endOneHourToNowDate(Commons.getStringToDate(chuTuiQinShiJian.get("currenttime")), 1);
//            //临时处理
//            onTime="2019-03-10 06:15:55";
//            tmp="2019-03-11 20:33:50";
//            //向前向后各推一个小时
//            onTime = Commons.beforeOneHourToNowDate(Commons.getDate(onTime),1);
//            tmp = Commons.endOneHourToNowDate(Commons.getDate(tmp),1);
            /**
             * 1、获取运安测酒数据
             * 2、存储运安数据的临时变量driverDuty转换成otodutypojolist
             * 3、进行排序
             */
            List<Map<String, Object>> ylist = new ArrayList<Map<String, Object>>();
            System.out.println(Integer.valueOf(driverId)+"_" +onTime+"_"+tmp);
            List<DriverAlcoholPojo> driverAlcoholByWorkNoAndTimeStr =
                    pbResource.getDriverAlcoholByWorkNoAndTimeStr(Integer.valueOf(driverId), onTime, tmp);
            for (DriverAlcoholPojo dutyPojo : driverAlcoholByWorkNoAndTimeStr) {
                System.out.println(CXFUtils.objectToMap(dutyPojo));
                ylist.add(CXFUtils.objectToMap(dutyPojo));
            }
            /**
             * 获取中间库的测酒数据
             * 分各个机务段
             */
            String jwcode = driverId.substring(0,2);
            List<Map<String,Object>> zhongjiankuCJ = null;
            if(jwcode.equals("45")){
                zhongjiankuCJ = checkOutDao.findAllCJByDriverIdAndDate(driverId,onTime,tmp);
            }else if(jwcode.equals("44")){
                zhongjiankuCJ = checkOutDao.findAllCJByDriverIdAndDateWithORCL(driverId,onTime,tmp);
            }

            if(zhongjiankuCJ!=null&&zhongjiankuCJ.size()>0){
                for(Map<String,Object> os :zhongjiankuCJ){
                    XMLGregorianCalendar strTime = CXFUtils.convertToXMLGregorianCalendar(Commons.getDate((String) os.get("strtime")));
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("checktime",strTime);
                    Object ntype = os.get("ntype");
                    if(ntype instanceof Integer){
                        int nType = (Integer)os.get("ntype");
                        if(nType==1){
                            map.put("clientid",(String)os.get("strsitename")+"&出勤&");
                        }else{
                            map.put("clientid",(String)os.get("strsitename")+"&退勤&");
                        }
                    }else if(ntype instanceof String){
                        String nType = (String)os.get("ntype");
                        if(nType.equals("1")){
                            map.put("clientid",(String)os.get("strsitename")+"&出勤&");
                        }else{
                            map.put("clientid",(String)os.get("strsitename")+"&退勤&");
                        }
                    }
                    map.put("driverid",os.get("strnumber"));
                    ylist.add(map);
                }
            }
            Commons.getDateSort(ylist, "checktime");
            /**
             * 处理同时同地点多次打卡出勤和退勤记录
             */
            for(int i=ylist.size()-1;i>=0;i--){
                Object checktime = ylist.get(i).get("checktime");
                if(i-1>=0){
                    XMLGregorianCalendarImpl chuqincejiu = (XMLGregorianCalendarImpl)ylist.get(i).get("checktime");
                    XMLGregorianCalendarImpl tuiqincejiu = (XMLGregorianCalendarImpl)ylist.get(i-1).get("checktime");
                    Date chuqindate = CXFUtils.DateToXML(chuqincejiu);
                    Date tuiqindate = CXFUtils.DateToXML(tuiqincejiu);
                    long cjtime = chuqindate.getTime()-tuiqindate.getTime();
                    long diff = Math.abs(cjtime/nm);
                    if(diff<10){
                        ylist.remove(i-1);
                    }
                }
            }
            /**
             * 处理数据异常情况
             * 1、当前数据和下一条数据都为出勤或者都为退勤
             * is_strong 0表示出勤 1表示退勤
             */
            Commons.getDateSortDesc(ylist,"checktime");
            //人为移除退勤数据
            //ylist.remove(ylist.size()-1);
            for(int i=ylist.size()-1;i>=0;){
                String sitename = (String)ylist.get(i).get("clientid");
                if(i>=1){
                    String nextsitename = (String)ylist.get(i-1).get("clientid");
                    if(!sitename.contains("出勤")&&!sitename.contains("&出勤&")){
                        XMLGregorianCalendarImpl xml = (XMLGregorianCalendarImpl)ylist.get(i).get("checktime");
                        Date date = CXFUtils.DateToXML(xml);
                        String sswdate = Commons.beforTimeMinute(date, 5);//减了五分钟
                        Map<String,Object> map = new HashMap<>();
                        map.put("checktime",CXFUtils.convertToXMLGregorianCalendar(Commons.getDate(sswdate)));
                        map.put("clientid",ylist.get(i).get("clientid"));
                        map.put("driverid",ylist.get(i).get("strnumber"));
                        map.put("is_strong","1");
                        ylist.add(i+1,map);
                        i--;
                    }else if(!nextsitename.contains("退勤")&&!nextsitename.contains("&退勤&")){
                        XMLGregorianCalendarImpl xml = (XMLGregorianCalendarImpl)ylist.get(i-1).get("checktime");
                        Date date = CXFUtils.DateToXML(xml);
                        String sswdate = Commons.beforTimeMinute(date, 5);//减了五分钟
                        Map<String,Object> map = new HashMap<>();
                        map.put("checktime",CXFUtils.convertToXMLGregorianCalendar(Commons.getDate(sswdate)));
                        map.put("clientid",ylist.get(i-1).get("clientid"));
                        map.put("driverid",ylist.get(i-1).get("strnumber"));
                        map.put("is_strong","0");
                        ylist.add(i,map);
                        i--;
                    }else {
                        i=i-2;
                    }
                }else {
                    if(!sitename.contains("出勤")&&!sitename.contains("&出勤&")){
                        XMLGregorianCalendarImpl xml = (XMLGregorianCalendarImpl)ylist.get(i).get("checktime");
                        Date date = CXFUtils.DateToXML(xml);
                        String sswdate = Commons.beforTimeMinute(date, 5);//减了五分钟
                        Map<String,Object> map = new HashMap<>();
                        map.put("checktime",CXFUtils.convertToXMLGregorianCalendar(Commons.getDate(sswdate)));
                        map.put("clientid",ylist.get(i).get("clientid"));
                        map.put("driverid",ylist.get(i).get("strnumber"));
                        map.put("is_strong","1");
                        ylist.add(i+1,map);
                    }else{
                        XMLGregorianCalendarImpl xml = (XMLGregorianCalendarImpl)ylist.get(i).get("checktime");
                        Date date = CXFUtils.DateToXML(xml);
                        String sswdate = Commons.afterTimeMinute(date, 5);//加了五分钟
                        Map<String,Object> map = new HashMap<>();
                        map.put("checktime",CXFUtils.convertToXMLGregorianCalendar(Commons.getDate(sswdate)));
                        map.put("clientid",ylist.get(i).get("clientid"));
                        map.put("driverid",ylist.get(i).get("strnumber"));
                        map.put("is_strong","0");
                        ylist.add(i,map);
                    }
                    break;
                }
            }
            //处理测酒数据人为添加"&出勤&"字样的模板
            for(Map<String,Object> ls:ylist){
                String clientid = (String)ls.get("clientid");
                ls.put("clientid",clientid.replace("&出勤&","").replace("&退勤&",""));
            }

            Commons.getDateSort(ylist,"checktime");
            List<Map<String,Object>> cejiulist = new ArrayList<Map<String,Object>>();
            for(int i = 0;i<ylist.size();){
                if(i+1<ylist.size()){
                    Map<String,Object> map = new HashMap<>();
                    XMLGregorianCalendarImpl chuqincejiu = (XMLGregorianCalendarImpl)ylist.get(i).get("checktime");
                    XMLGregorianCalendarImpl tuiqincejiu = (XMLGregorianCalendarImpl)ylist.get(i+1).get("checktime");
                    Date chuqindate = CXFUtils.DateToXML(chuqincejiu);
                    Date tuiqindate = CXFUtils.DateToXML(tuiqincejiu);
                    Object chuqincejiudidian = ylist.get(i).get("clientid");
                    Object tuiqincejiudidian = ylist.get(i+1).get("clientid");
                    String ist1 = (String)ylist.get(i).get("is_strong");
                    String ist2 = (String)ylist.get(i+1).get("is_strong");
                    //处理是出勤未测酒还是退勤未测酒
                    if(StringUtils.isNotBlank(ist1)&&StringUtils.isNotEmpty(ist1)){
                        map.put("is_strong",ist1);
                    }
                    if(StringUtils.isNotBlank(ist2)&&StringUtils.isNotEmpty(ist2)){
                        map.put("is_strong",ist2);
                    }
                    map.put("chuqincejiu",chuqindate);
                    map.put("tuiqincejiu",tuiqindate);
                    map.put("chuqincejiudidian",chuqincejiudidian);
                    map.put("tuiqincejiudidian",tuiqincejiudidian);
                    cejiulist.add(map);
                }
                i+=2;
            }
            /**
             * 获取lkj数据
             */
            List<Map<String, Object>> lkjlist = checkOutDao.currentDriverStartAndEnd(driverId, fsjh, onTime, tmp);
            Commons.getListToSortReverseOrderByDate(lkjlist);
            for (Map<String,Object> cc:lkjlist){
                cc.put("yun_an_isnotexits","1");
                cc.put("lkj_isnotexits","1");
                cc.put("is_exists_suspicious","1");
                cc.put("lkj_is_excuted","0");//lkj是否被处理过 0未被处理 1已经处理过
                //System.out.println(cc);
            }
            //存储交路数据
            List<Map<String,Object>> jllist = new ArrayList<Map<String,Object>>();
            for(Map<String,Object> clist:cejiulist){
                Date chuqincejiu = (Date)clist.get("chuqincejiu");
                Date tuiqincejiu = (Date)clist.get("tuiqincejiu");
                //tuiqincejiu = Commons.getDate(Commons.endOneHourToNowDate(tuiqincejiu,1));
                String chuqincejiudidian = (String)clist.get("chuqincejiudidian");
                String tuiqincejiudidian = (String)clist.get("tuiqincejiudidian");
                //临时处理一次出退勤变量集合
                List<Map<String,Object>> tmplist=new ArrayList<>();
                for(int i=lkjlist.size()-1;i>=0;i--){
                    String shifashijian =(String) lkjlist.get(i).get("shifashijian");
                    String zhongdaoshijian =(String) lkjlist.get(i).get("zhongdaoshijian");
                    Date shifahdate = Commons.getDate(shifashijian);
                    Date zhongdaodate = Commons.getDate(zhongdaoshijian);
                    //测酒出退勤时间
                    long cchuqintime = chuqincejiu.getTime();
                    long ctuiqintime = tuiqincejiu.getTime();
                    //lkj出退勤时间
                    long lkjshifadate = shifahdate.getTime();
                    long lkjzhongdaodate = zhongdaodate.getTime();
                    /**
                     * 找出所有在一次出勤时间范围内的所有lkj数据
                     * 出勤时间在lkj起始时间之前，故klj的时间是大于测酒的出勤时间
                     * 测酒退勤时间是大于lkj的终到时间，因为测酒是乘务员结束下班了，lkj文件并不生成了
                     */
                    if(lkjshifadate>cchuqintime&&ctuiqintime>lkjzhongdaodate){
                        lkjlist.get(i).put("lkj_is_excuted","1");
                        tmplist.add(lkjlist.get(i));
                    }
                }
                if(tmplist!=null&&tmplist.size()>0){
                    Commons.getListToSortReverseOrderByDate(tmplist);
                    //先比较头
                    String shifazhan = (String)tmplist.get(0).get("shifazhan");
                    String lkjshifashijian = (String)tmplist.get(0).get("shifashijian");
                    String lkjzhongdaoshijian = (String)tmplist.get(0).get("zhongdaoshijian");
                    boolean isSamePlace = Commons.getIsSamePlace(shifazhan, chuqincejiudidian, pointlist);
                    if(isSamePlace){
                        //正常情况
                        tmplist.get(0).put("yun_an_isnotexits","1");
                        tmplist.get(0).put("lkj_isnotexits","1");
                        tmplist.get(0).put("is_exists_suspicious","1");
                    }else{
                        //出勤时间和lkj始发时间不超过一个小时也是正常的
                        Date lkjshifadate = Commons.getDate(lkjshifashijian);
                        long diff = lkjshifadate.getTime()-chuqincejiu.getTime();
                        long minute = Math.abs(diff/nm);
                        if(minute>60){
                            Map<String,Object> map = new HashMap<String,Object>();
                            map.put("shifazhan",chuqincejiudidian);
                            map.put("shifashijian",CXFUtils.DateToString(chuqincejiu));
                            map.put("zhongdaozhan",shifazhan);
                            map.put("zhongdaoshijian",tmplist.get(0).get("shifashijian"));
                            map.put("traincheci","");
                            map.put("sjh",driverId);
                            map.put("yun_an_isnotexits", "1");
                            map.put("lkj_isnotexits", "0");
                            map.put("is_exists_suspicious", "0");
                            jllist.add(map);
                        }
                    }
                    //然后比较尾部
                    String lkjzhongdaozhan = (String)tmplist.get(tmplist.size()-1).get("zhongdaozhan");
                    boolean isSamePlace1 = Commons.getIsSamePlace(lkjzhongdaozhan, tuiqincejiudidian, pointlist);
                    if(isSamePlace1){
                        //正常情况
                        tmplist.get(tmplist.size()-1).put("yun_an_isnotexits","1");
                        tmplist.get(tmplist.size()-1).put("lkj_isnotexits","1");
                        tmplist.get(tmplist.size()-1).put("is_exists_suspicious","1");
                    }else{
                        //尾部比较的时候，测酒退勤时间和lkj终到时间不会超过60分钟，否则即使地点不一致也属于正常的
                        Date lkjzhongdaodate = Commons.getDate(lkjzhongdaoshijian);
                        long diff = lkjzhongdaodate.getTime()-tuiqincejiu.getTime();
                        long minute = Math.abs(diff/nm);
                        if(minute>60){
                            Map<String,Object> map = new HashMap<String,Object>();
                            map.put("shifazhan",lkjzhongdaozhan);
                            map.put("shifashijian",tmplist.get(tmplist.size()-1).get("zhongdaoshijian"));
                            map.put("zhongdaozhan",tuiqincejiudidian);
                            map.put("zhongdaoshijian",CXFUtils.DateToString(tuiqincejiu));
                            map.put("traincheci","");
                            map.put("sjh",driverId);
                            map.put("yun_an_isnotexits", "1");
                            map.put("lkj_isnotexits", "0");
                            map.put("is_exists_suspicious", "0");
                            jllist.add(map);
                        }
                    }
                    //最后lkj自比较
                    for(int i = 0;i<tmplist.size();i++){
                        if(i+1<tmplist.size()){
                            //存在两条以上的数据，连续性的比较
                            String currentZhongdaozhan = (String)tmplist.get(i).get("zhongdaozhan");
                            String nextShifazhan = (String)tmplist.get(i+1).get("shifazhan");
                            boolean isSamePlace2 = Commons.getIsSamePlace(currentZhongdaozhan, nextShifazhan, pointlist);
                            if(isSamePlace2){
                                //同时都满足的正常
                                tmplist.get(i).put("yun_an_isnotexits","1");
                                tmplist.get(i).put("lkj_isnotexits","1");
                                tmplist.get(i).put("is_exists_suspicious","1");
                            }else{
                                //判文件缺失前在判断一次出勤时间和退勤时间
                                //如果地点不同，但是时间接近不超过一个半小时
                                String onzhongdaoshijian = (String)tmplist.get(i).get("zhongdaoshijian");
                                String nextshifashijian = (String)tmplist.get(i+1).get("shifashijian");
                                Date ondate = Commons.getDate(onzhongdaoshijian);
                                Date nextdate = Commons.getDate(nextshifashijian);
                                long diff = ondate.getTime()-nextdate.getTime();
                                long mm = Math.abs(diff/nm);
                                if(mm>60){
                                    Map<String,Object> map = new HashMap<String,Object>();
                                    map.put("shifazhan",currentZhongdaozhan);
                                    map.put("shifashijian",tmplist.get(i).get("zhongdaoshijian"));
                                    map.put("zhongdaozhan",nextShifazhan);
                                    map.put("zhongdaoshijian",tmplist.get(i+1).get("shifashijian"));
                                    map.put("traincheci","");
                                    map.put("sjh",driverId);
                                    map.put("yun_an_isnotexits", "1");
                                    map.put("lkj_isnotexits", "0");
                                    map.put("is_exists_suspicious", "0");
                                    jllist.add(map);
                                }
                            }
                        }
                    }
                }
                //如果tmplist为空，说明测酒的出勤测酒和退勤测酒时间范围内，没有lkj数据，判缺失
                //如果lkj中没有数据，那就直接把出退勤数据添加需要展示的集合中，返回到页面进行展示
                if(tmplist!=null&&tmplist.size()>0){
                    //如果数据不为null，判断测酒出退勤是否为人为强制加入的出退勤数据
                    Commons.getListToSortReverseOrderByDate(tmplist);
                    for(Map<String,Object> os:tmplist){
                        String ssw = (String)os.get("is_strong");
                        if(StringUtils.isNotBlank(ssw)&&StringUtils.isNotEmpty(ssw)){
                            if(ssw.equals("0")){
                                tmplist.get(0).put("shifashijian",tmplist.get(0).get("shifashijian")+"_无出勤酒测");
                                break;
                            }else if(ssw.equals("1")){
                                tmplist.get(tmplist.size()-1).put("zhongdaozhan",tmplist.get(tmplist.size()-1).get("zhongdaozhan")+"_无退勤酒测");
                                break;
                            }
                        }
                    }
                    for(Map<String,Object> tmlist:tmplist){
                        jllist.add(tmlist);
                    }
                }else{
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("shifazhan",chuqincejiudidian);
                    map.put("shifashijian",CXFUtils.DateToString(chuqincejiu));
                    map.put("zhongdaozhan",tuiqincejiudidian);
                    map.put("zhongdaoshijian",CXFUtils.DateToString(tuiqincejiu));
                    map.put("traincheci","");
                    map.put("sjh",driverId);
                    map.put("yun_an_isnotexits", "1");
                    map.put("is_strong", clist.get("is_strong"));
                    map.put("lkj_isnotexits", "0");
                    map.put("is_exists_suspicious", "0");
                    jllist.add(map);
                }
            }

            /**
             * 把没处理的lkj加入jllist中
             */
            for(Map<String,Object> ts:lkjlist){
                String ssw = (String)ts.get("lkj_is_excuted");
                if(ssw.equals("0")){
                    jllist.add(ts);
                }
            }

            /**
             * 处理首尾测酒不全，lkj存在的情况
             * 1、拿测酒的最后一条数据和测酒首条数据，去判断lkj未被处理的数据
             * 2、然后判断测酒数据缺失情况人为加上了出退勤，框出的lkj数据，需要在本段lkj出退勤注明无测酒出勤和无测酒退勤
             *
             * 直接用测酒的最后一条带有is_strong的数据，在jllist中，并移除，然后拿出这个出勤时间，判断jllistlkj大于这个时间的标识未测酒出退勤
             * 第一条相反，同上
             */
            Commons.getListToSortReverseOrderByDate(jllist);
            //测酒数据
            Map<String, Object> objectMap = cejiulist.get(cejiulist.size() - 1);
            Map<String, Object> startObjectMap = cejiulist.get(0);
            Date tuiqincejiu = (Date)startObjectMap.get("tuiqincejiu");
            String jiucechuqindate = CXFUtils.DateToString(tuiqincejiu);
            Date chuqincejiu = (Date)objectMap.get("chuqincejiu");
            String jiucedate = CXFUtils.DateToString(chuqincejiu);

            /**
             * 处理末尾强制加入的退勤测酒数据，并删除末尾强制加入的测酒数据
             */
            for(int i = 0;i<jllist.size();i++){
                String ssw = (String)jllist.get(i).get("is_strong");
                if(StringUtils.isNotBlank(ssw)&&StringUtils.isNotEmpty(ssw)){
                    if(ssw.equals("0")){
                        String startDate = (String)jllist.get(i).get("shifashijian");
                        long cjtime = chuqincejiu.getTime();
                        Date startDateTime = Commons.getDate(startDate);
                        long sttime = startDateTime.getTime();
                        if(sttime==cjtime){
                            if(i+1<jllist.size()){
                                jllist.get(jllist.size()-1).put("zhongdaozhan",jllist.get(jllist.size()-1).get("zhongdaozhan")+"_无退勤酒测");
                                break;
                            }
                        }
                    }
                }
            }
            /**
             * 删除末尾强制加入的出退勤测酒数据
             */
            for(int i = 0;i<jllist.size();i++){
                String ssw = (String)jllist.get(i).get("is_strong");
                if(StringUtils.isNotBlank(ssw)&&StringUtils.isNotEmpty(ssw)){
                    if(ssw.equals("0")){
                        String startDate = (String)jllist.get(i).get("shifashijian");
                        if(jiucedate.trim().equals(startDate.trim())){
                            jllist.remove(i);
                            break;
                        }
                    }
                }
            }
            /**
             * 处理开始强制加入的出勤测酒问题，然后并删除
             */
            for(int i = 0;i<jllist.size();i++){
                String ssw = (String)jllist.get(i).get("is_strong");
                if(StringUtils.isNotBlank(ssw)&&StringUtils.isNotEmpty(ssw)){
                    if(ssw.trim().equals("1")){
                        String zhongdaodate = (String)jllist.get(i).get("zhongdaoshijian");
                        long cjtime = tuiqincejiu.getTime();
                        Date startDateTime = Commons.getDate(zhongdaodate);
                        long sttime = startDateTime.getTime();
                        if(sttime==cjtime){
                            jllist.get(0).put("shifazhan",jllist.get(0).get("shifazhan")+"_无出勤酒测");
                            break;
                        }
                    }
                }
            }
            /**
             * 删除开始强制加入的出勤测酒数据
             */
            for(int i = 0;i<jllist.size();i++){
                String ssw = (String)jllist.get(i).get("is_strong");
                if(StringUtils.isNotBlank(ssw)&&StringUtils.isNotEmpty(ssw)){
                    if(ssw.trim().equals("1")){
                        String zhongdaodate = (String)jllist.get(i).get("zhongdaoshijian");
                        if(jiucechuqindate.trim().equals(zhongdaodate.trim())){
                            jllist.remove(i);
                            break;
                        }
                    }
                }
            }

            Commons.getListToSortReverseOrderByDate(jllist);
            datamap.put("lkj",jllist);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datamap;
    }


    //-------------------------------------------------固定乘务员编号测试用------------------------------------
//     @Override
//    public void testQueryCurrentDriverAnalyDataGrid(String driverId,
//                                                    PageBean pageBean) throws Exception {
//        String[] driverInfo = Constant.CHUTUIQIN_SETTINGS;
//        String strArr = "";
//        for (String info : driverInfo) {
//            String[] splits = info.split("_");
//            if (driverId.equals(splits[0])) {
//                strArr = info;
//                break;
//            }
//
//        }
//        if ("".equals(strArr)) {
//            return;
//        }
//        String[] infoArr = strArr.split("_");
//        String startDate = infoArr[1];
//        String endDate = infoArr[2];
//        String planDate = infoArr[3];
//        String noteStartDateAndTermial = startDate + "_" + endDate;
//        //测试
//        List<Map<String, Object>> list = checkOutDao.queryCurrentDriverAnalyData(driverId,
//                startDate, endDate, pageBean);
//        //设置级别显示违标级别
//        if (list != null && list.size() > 0) {
//            //查询所有项点名称
//            List<Map<String, Object>> allItemList = checkOutDao.findAllItem();
//            //通过项点编码获取对应的名称
//            getAllItemNameByCode(noteStartDateAndTermial, list, allItemList);
//            Commons.getListToSortReverseOrder(list);
//            pageBean.setRows(list);
//        }
//    }
//
//    /**
//     * 始发终到
//     *
//     * @param driverId
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public List<Map<String, Object>> testCurrentDriverStartAndEnd(
//            String driverId) throws Exception {
//        if (StringUtils.isBlank(driverId) || StringUtils.isEmpty(driverId)) {
//            return null;
//        }
//        String str = driverId.substring(0, 2);
//
//        //获取结果对象  运安接口对象
//        PbResource pbResource = CXFUtils.getPbResource();
//        String[] driverInfo = Constant.CHUTUIQIN_SETTINGS;
//        String strArr = "";
//        for (String info : driverInfo) {
//            String[] splits = info.split("_");
//            if (driverId.equals(splits[0])) {
//                strArr = info;
//                break;
//            }
//
//        }
//        if ("".equals(strArr)) {
//            return null;
//        }
//        String[] infoArr = strArr.split("_");
//        String startDate = infoArr[1];
//        String endDate = infoArr[2];
//        String planDate = infoArr[3];
//
//        //获取上次退勤时间；目的是为了计划于lkj实乘交路进行车次比较，从而判断这趟数据是否丢失
//        DriverPlanPojo driverPlanByWorkNoAndTimeStrAndType =
//                pbResource.getDriverPlanByWorkNoAndTimeStrAndType(
//                        Integer.valueOf(driverId), planDate, 1);
//        //计划车次
//        String planTrainNum = driverPlanByWorkNoAndTimeStrAndType.getPlantrainnum();
//        //计划时间
//        //获取始发终到的数据  lkj数据
//        List<Map<String, Object>> currentDriverStartAndEnd = checkOutDao.currentDriverStartAndEnd(
//                driverId, startDate, endDate);
//
//        for (int y = 0; y < currentDriverStartAndEnd.size(); y++) {
//            Object checi = currentDriverStartAndEnd.get(y).get("traincheci");
//            if (checi != null && !checi.equals("")) {
//                String checistr = (String) checi;
//                if (checistr.contains("f") || checistr.contains("F")) {
//                    currentDriverStartAndEnd.remove(y);
//                }
//            }
//        }
//        //从计划车次和lkj车次进行比较，运安车次存在的，lkj必须有，否则为缺失；lkj可以多车次，因为中途可能换乘；
//        // 疑似缺失就是出勤地点和退勤地点不匹配
//        boolean notEx = true;
//        List<Map<String, Object>> lst = new ArrayList<Map<String, Object>>();
//        //以lkj数据为主：目的是判断如果lkj有，运安没有，说明中途换乘了，正常，没有缺失
//        for (Map<String, Object> map : currentDriverStartAndEnd) {
//            String lkjTrainNum = (String) map.get("traincheci");
//            if (lkjTrainNum.trim().equalsIgnoreCase(planTrainNum.trim())) {
//                //计划和lkj匹配
//                map.put("yun_an_isnotexits", "1");//运安存在
//                map.put("lkj_isnotexits", "1");//lkj存在
//                lst.add(map);
//                notEx = false;
//            } else {
//                //lkj数据存在
//                map.put("yun_an_isnotexits", "0");//运安不存在
//                map.put("lkj_isnotexits", "1");//lkj存在
//                lst.add(map);
//            }
//        }
//
//        //数据缺失
//        if (notEx) {
//            Map<String, Object> maps = new HashMap<String, Object>();
//            maps.put("yun_an_isnotexits", "1");        //运安存在
//            maps.put("lkj_isnotexits", "0");            //lkj不存在
//            maps.put("sjh", driverId);
//            maps.put("shifazhan", "");
//            maps.put("zhongdaozhan", "");
//            //XMLGregorianCalendar
//            XMLGregorianCalendar ls = driverPlanByWorkNoAndTimeStrAndType.getPlanondutytime();
//            if(driverPlanByWorkNoAndTimeStrAndType.getPlanondutytime()!=null){
//                maps.put("shifashijian", CXFUtils.DateToString(CXFUtils.DateToXML(
//                        driverPlanByWorkNoAndTimeStrAndType.getPlanondutytime())));
//            }else{
//                maps.put("shifashijian", "");
//            }
//            maps.put("zhongdaoshijian", "");
//            maps.put("drivertime", "");
//            maps.put("traincheci", planTrainNum);
//            lst.add(maps);
//        }
//
//        //处理疑似缺失数据；依据是：如果出勤地点和退勤地点不匹配，说明这个人没有回到出勤的地方
//        //目的如果这次作业出勤的地点为上海，理应回到上海（包含上海，上海南，上海南翔）证明这次作业是完整的
//        Commons.getListToSortReverseOrderByDate(lst);
//        if (lst.size() > 0 && lst != null) {
//            String shifazhan = (String) lst.get(0).get("shifazhan");
//            String zhongdaozhan = (String) lst.get(lst.size() - 1).get("zhongdaozhan");
//            if (StringUtils.isNotBlank(shifazhan) && !StringUtils.isEmpty(shifazhan)) {
//                boolean place = Commons.getPlace(shifazhan, str);
//                if (!place) {
//                    //可能存在缺失,判断逻辑：如从上海出发没有回到上海
//                    Map<String, Object> map1 = new HashMap<String, Object>();
//                    map1.put("is_exists_suspicious", "0");
//                    map1.put("shifazhan", zhongdaozhan);
//                    map1.put("zhongdaozhan", shifazhan);
//                    lst.add(map1);
//                }
//            }
//            //以日期排序
//            Commons.getListToSortReverseOrderByDate(lst);
//        }
//        return lst;
//    }
    //以上为测试固定标号可用



    @Override
    public void testQueryCurrentDriverAnalyDataGrid(String driverId,
                                                    PageBean pageBean) throws Exception {
        String noteStartDateAndTermial = "2018-12-01_2018-12-31";
        //测试
        List<Map<String, Object>> list = checkOutDao.queryCurrentDriverAnalyData(driverId,
                "2018-12-01 00:00:00", "2018-12-31 00:00:00", pageBean);
        //设置级别显示违标级别
        if (list != null && list.size() > 0) {
            //查询所有项点名称
            List<Map<String, Object>> allItemList = checkOutDao.findAllItem();
            //通过项点编码获取对应的名称
            getAllItemNameByCode(noteStartDateAndTermial, list, allItemList);
            Commons.getListToSortReverseOrder(list);
            pageBean.setRows(list);
        }
    }

    /**
     * 始发终到
     *
     * @param driverId
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> testCurrentDriverStartAndEnd(
            String driverId) throws Exception {

        Map<String,Object> sjhMap = checkOutDao.queryByFuSiJiCode(driverId);
        Object ofsjh = sjhMap.get("fsjh");
        String fsjh="";
        if(ofsjh!=null||!ofsjh.equals("")){
            fsjh = (String)ofsjh;
        }
        //获取始发终到的数据  lkj数据
        List<Map<String, Object>> currentDriverStartAndEnd = checkOutDao.currentDriverStartAndEnd(
                driverId,fsjh, "2018-12-01", "2019-12-31");

        //待修改
        for (int y = 0; y < currentDriverStartAndEnd.size(); y++) {
            Object checi = currentDriverStartAndEnd.get(y).get("traincheci");
            if (checi != null && !checi.equals("")) {
                String checistr = (String) checi;
                if (checistr.contains("f") || checistr.contains("F")) {
                    currentDriverStartAndEnd.remove(y);
                }
            }
        }

        //处理疑似缺失数据；依据是：如果出勤地点和退勤地点不匹配，说明这个人没有回到出勤的地方
        //目的如果这次作业出勤的地点为上海，理应回到上海（包含上海，上海南，上海南翔）证明这次作业是完整的
        for(Map<String,Object> cs :currentDriverStartAndEnd){
            cs.put("is_exists_suspicious", "0");
        }
        Commons.getListToSortReverseOrderByDate(currentDriverStartAndEnd);
        return currentDriverStartAndEnd;
    }
    //以上为平时测试用



    /**
     * //DOTO 2019-03-05 历史处理   暂不用，已经优化处理
     * 2018-11-30 10:34  hqh 始发终到和项点结果表分析更改
     * 处理当前司机的始发终到
     * 约定：
     * yun_an_isnotexits 、lkj_isnotexits、is_exists_suspicious    0 缺失  1 不缺失或者lkj存在
     * 判断逻辑
     * 1、获取运安的出退勤时间，及当前出退勤时间的上次退勤时间
     * 2、通过上次的退勤时间获取人员计划,如果人员计划获取不到，则直接展示lkj交路数据，并且判断疑似缺失
     * 3、通过出退勤时间获取当前乘务员的交路信息
     * 4、对比文件是否缺失、疑似缺失、正常
     * 5、正副司机需要处理，正司机么有交路那就查副司机的交路，二人是一个交路
     *
     *
     * 正在出勤中，逻辑为没有退勤时间
     * getZhengZaiChuQinData(driverId, lst, driverID, pbResource, (Date) onTimeTuiqintime,driverPlanByWorkNoAndTimeStrAndType);
     * 查询出退勤所有终到地点
     * checkOutDao.findJWDChuTuiQinPlace(str);
     * 从运安中获取出退勤时间和上一次退勤时间
     * getYunAnDate(pbResource, driverID, str,places);
     * //获取人员计划
     * pbResource.getDriverPlanByWorkNoAndTimeStrAndType(Integer.valueOf(driverID), CXFUtils.DateToString(onTuiqinTime), 1);
     * 处理数据疑似缺失
     * excuteYiShiQueShiData(str, currentDriverStartAndEnd,dtOff,(String)yunAnDate.get("offdutycode"),places);
     * 处理数据缺失
     * excuteQueShiData(driverId, lst, driverPlanByWorkNoAndTimeStrAndType, planTrainNum, nList, notEx,(String)yunAnDate.get("ondutycode"));
     * 处理计划中车次有两个的情况
     * excuteTwoPlanTrainData(lst, driverPlanByWorkNoAndTimeStrAndType, driverId);
     */
//    public List<Map<String, Object>> getDriverJLWithStartAndEndStation(String driverId) throws Exception {
//        List<Map<String, Object>> lst = new ArrayList<Map<String, Object>>();
//        String str = driverId.substring(0, 2);
//        if (StringUtils.isBlank(driverId) || StringUtils.isEmpty(driverId)) {
//            return null;
//        }
//        //查询各个机务段的出退勤所有地点
//        List<String> places = checkOutDao.findJWDChuTuiQinPlace(str);
//
//        int driverID = Integer.valueOf(driverId);
//        //获取结果对象，运安接口对象
//        PbResource pbResource = CXFUtils.getPbResource();
//        //从运安中获取出退勤时间和上一次退勤时间，给获取人员计划用
//        Map<String, Object> yunAnDate = getYunAnDate(pbResource, driverID, str,places);
//        //待改
//        Object offDateObj = yunAnDate.get("dtOff");
//        Object onDataObj = yunAnDate.get("onDate");
//        Object onTimeTuiqintime = yunAnDate.get("onTuiqinTime");
//        if(onTimeTuiqintime==null) return null;
//        //获取计划
//        Date onTuiqinTime = (Date) onTimeTuiqintime;//退勤时间主要传值给运安接口
//        DriverPlanPojo driverPlanByWorkNoAndTimeStrAndType =
//                pbResource.getDriverPlanByWorkNoAndTimeStrAndType(
//                        Integer.valueOf(driverID), CXFUtils.DateToString(onTuiqinTime), 1);
//        if (offDateObj == null||onDataObj==null) {
//            if(onTimeTuiqintime==null) return null;
//            getZhengZaiChuQinData(
//                    driverId, lst, driverID, pbResource, (Date) onTimeTuiqintime,driverPlanByWorkNoAndTimeStrAndType);
//            return lst;//此人存在正在出勤
//        }
//        Date onDate = (Date) onDataObj;
//        Date dtOff = (Date) offDateObj;
//
//        //查询副司机
//        Map<String,Object> sjhMap = checkOutDao.queryByFuSiJiCode(driverId);
//        Object ofsjh = sjhMap.get("fsjh");
//        String fsjh="";
//        if(ofsjh!=null||!ofsjh.equals("")){
//            fsjh = (String)ofsjh;
//        }
//        if(driverPlanByWorkNoAndTimeStrAndType==null){
//            //直接查询lkj交路信息，因为获取不到计划
//            //然后对lkj数据进行排序
//            //然后处理疑似缺失的数据
//            //返回lkj交路数据
//            List<Map<String, Object>> currentDriverStartAndEnd = checkOutDao.currentDriverStartAndEnd(
//                    driverId, fsjh,CXFUtils.DateToString(onDate), CXFUtils.DateToString(dtOff));
//            for(Map<String,Object> maps:currentDriverStartAndEnd){
//                maps.put("yun_an_isnotexits","0");
//                maps.put("lkj_isnotexits","1");
//            }
//            excuteYiShiQueShiData(str, currentDriverStartAndEnd,dtOff,(String)yunAnDate.get("offdutycode"),places);
//            return currentDriverStartAndEnd;
//        }
//
//        //计划车次
//        String planTrainNum = driverPlanByWorkNoAndTimeStrAndType.getPlantrainnum();//计划出勤时间 注：有可能为空
//        XMLGregorianCalendar plandeptime = driverPlanByWorkNoAndTimeStrAndType.getPlandeptime();//计划开车时间
//        //处理运安读取计划问题
//        Date dt = CXFUtils.DateToXML(plandeptime);
//        long plandeptimelong = dt.getTime();
//        String ondatestr = Commons.beforeOneHourToNowDate(onDate, 4);
//        Date ondatedate = Commons.getDate(ondatestr);
//        long ontimelong = ondatedate.getTime();
//        //处理运安和lkj一起计算
//        //从计划车次和lkj车次进行比较，运安车次存在的，lkj必须有，否则为缺失；lkj可以多车次，因为中途可能换乘；
//        // 疑似缺失就是出勤地点和退勤地点不匹配
//        boolean notEx = true;
//        if(plandeptimelong>ontimelong){
//            //获取始发终到的数据  lkj数据
//            List<Map<String, Object>> currentDriverStartAndEnd = checkOutDao.currentDriverStartAndEnd(
//                    driverId, fsjh,CXFUtils.DateToString(onDate), CXFUtils.DateToString(dtOff));
//            List<Map<String,Object>> nList = new ArrayList<Map<String,Object>>();
//            for(Map<String,Object> dsa:currentDriverStartAndEnd){
//                Object checi = dsa.get("traincheci");
//                if (checi != null && !checi.equals("")) {
//                    String checistr = (String) checi;
//                    if (!checistr.contains("f")||!checistr.contains("F")) {
//                        nList.add(dsa);
//                    }
//                }else{
//                    nList.add(dsa);
//                }
//            }
//            //处理缺失数据
//            excuteQueShiData(
//                    driverId, lst, driverPlanByWorkNoAndTimeStrAndType, planTrainNum, nList, notEx,(String)yunAnDate.get("ondutycode"));
//            Commons.getListToSortReverseOrderByDate(lst);
//            //处理疑似缺失数据
//            excuteYiShiQueShiData(str, lst,dtOff,(String)yunAnDate.get("offdutycode"),places);
//        }else{
//            //不处理计划车次了，那么只会有疑似缺失的数据处理了
//            lst = checkOutDao.currentDriverStartAndEnd(
//                    driverId, fsjh,CXFUtils.DateToString(onDate), CXFUtils.DateToString(dtOff));
//            //处理疑似缺失数据
//            for(Map<String,Object> oe:lst){
//                oe.put("yun_an_isnotexits","1");
//                oe.put("lkj_isnotexits","1");
//            }
//            excuteYiShiQueShiData(str, lst,dtOff,(String)yunAnDate.get("offdutycode"),places);
//        }
//        //处理头尾交路
//        /**
//         * 1、获取出勤时间地点、退勤时间地点
//         */
//        String ondutycoe = (String)yunAnDate.get("ondutycode");
//        String offdutycode = (String)yunAnDate.get("offdutycode");
//        getExcuteStartAndEndStation( onDate,ondutycoe, offdutycode,dtOff,lst,str,places);
//        //处理计划中有两个车次的文件
//        List<Map<String, Object>> list = excuteTwoPlanTrainData(lst, driverPlanByWorkNoAndTimeStrAndType, driverId);
//
//        return list;
//    }

    private List<Map<String,Object>> excuteTwoPlanTrainData(
            List<Map<String,Object>> lst,DriverPlanPojo driverPlanByWorkNoAndTimeStrAndType,String driverid){
        //如果只有计划数据、lkj数据没有查询出来的话
        if(lst!=null&&lst.size()==1){
            Object lkj = lst.get(0).get("lkj_isnotexits");
            Object yunan = lst.get(0).get("yun_an_isnotexits");
            if(lkj!=null&&yunan!=null){
                if("0".equals(lkj)&&"1".equals(yunan)){
                    return lst;
                }
            }
        }

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(Map<String,Object> nlist:lst){
            Object traincheci = nlist.get("traincheci");
                if(traincheci==null){
                    list.add(nlist);
                }else{
                    String st = (String) traincheci;
                    if(!st.contains("/")){
                        list.add(nlist);
                    }
                }
        }
        if(lst!=null&&lst.size()>1){
            for(Map<String,Object> o:lst){
                Object yun_an_isnotexits = o.get("yun_an_isnotexits");
                Object lkj_isnotexits = o.get("lkj_isnotexits");
                if(yun_an_isnotexits!=null&&lkj_isnotexits!=null){
                    String yunanstr = (String)yun_an_isnotexits;
                    String lkjstr = (String)lkj_isnotexits;
                    if(yunanstr.equals("1")&&lkjstr.equals("0")){
                        Object traincheci = o.get("traincheci");
                        if(traincheci!=null){
                            String checi = (String)traincheci;
                            if(checi.contains("/")){
                                String[] splits = checi.split("/");
                                for(String scheci:splits){
                                    boolean bl = false;
                                    for(Map<String,Object> ss:list){
                                        Object ocheci = ss.get("traincheci");
                                        if(ocheci!=null){
                                            String listcheci = (String)ocheci;
                                            if(scheci.trim().equals(listcheci.trim())){
                                                bl=true;
                                            }
                                        }
                                    }
                                    if(!bl){
                                        Map<String, Object> maps = new HashMap<String, Object>();
                                        maps.put("yun_an_isnotexits", "1");        //运安存在
                                        maps.put("lkj_isnotexits", "0");           //lkj不存在
                                        maps.put("sjh", driverid);
                                        maps.put("shifazhan", driverPlanByWorkNoAndTimeStrAndType.getPlandepstation());
                                        maps.put("zhongdaozhan", "");
                                        XMLGregorianCalendar planondutytime = driverPlanByWorkNoAndTimeStrAndType.getPlanondutytime();
                                        if(planondutytime==null){
                                            //计划开车时间
                                            XMLGregorianCalendar ptime = driverPlanByWorkNoAndTimeStrAndType.getPlandeptime();
                                            if(ptime==null){
                                                maps.put("shifashijian", "");
                                            }else{
                                                maps.put("shifashijian", CXFUtils.DateToString(CXFUtils.DateToXML(ptime)));
                                            }
                                        }else{
                                            //计划出勤时间
                                            maps.put("shifashijian", CXFUtils.DateToString(CXFUtils.DateToXML(planondutytime)));
                                        }
                                        maps.put("zhongdaoshijian", "");
                                        maps.put("drivertime", "");
                                        maps.put("traincheci", driverPlanByWorkNoAndTimeStrAndType.getPlantrainnum());
                                        list.add(maps);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Commons.getListToSortReverseOrderByDate(list);
        return list;
    }

    private void getExcuteStartAndEndStation(
            Date ondate,String ondutycode,String offdutycode,Date offDate,List<Map<String,Object>> list,String str,List<String> palces){
            if(list!=null&&list.size()>0){
                Object oshifazhan = list.get(0).get("shifazhan");
                String shifazhan = (String)oshifazhan;
                String ya = (String)(list.get(0).get("yun_an_isnotexits"));
                String lkj = (String)(list.get(0).get("lkj_isnotexits"));
                String is_exists_suspicious = (String)(list.get(list.size()-1).get("is_exists_suspicious"));//0 疑似缺失
                //计算开始站
                if(!Commons.getPlace(shifazhan,str,palces)){
                    //在list集合中插入新的一条记录数据
                    if(ya.equals("1")&&!lkj.equals("0")){
                        if(!"0".equals(is_exists_suspicious)){
                            Map<String,Object> oMap = new HashMap<String,Object>();
                            oMap.put("is_exists_suspicious", "0");
                            oMap.put("shifazhan", ondutycode);
                            oMap.put("shifashijian", CXFUtils.DateToString(ondate));
                            oMap.put("zhongdaozhan", "");
                            oMap.put("zhongdaoshijian", "");
                            list.add(0,oMap);
                        }
                    }
                }
                //计算结束站
                String sz = (String) list.get(0).get("shifazhan");
                String zhongdaozhan = (String) list.get(list.size() - 1).get("zhongdaozhan");
                if(StringUtils.isNotBlank(zhongdaozhan)&&StringUtils.isNotEmpty(zhongdaozhan)){
                    if(ya.equals("1")&&lkj.equals("0")){
                        boolean place = Commons.getPlace(zhongdaozhan, str,palces);
                        if (!place) {
                            //可能存在缺失,判断逻辑：如从上海出发没有回到上海
                            if(!zhongdaozhan.trim().equals(sz.trim())){
                                if(!"0".equals(is_exists_suspicious)) {
                                    Map<String, Object> map1 = new HashMap<String, Object>();
                                    map1.put("is_exists_suspicious", "0");
                                    map1.put("shifazhan", "");
                                    map1.put("shifashijian", "");
                                    map1.put("zhongdaozhan", offdutycode);
                                    map1.put("zhongdaoshijian", CXFUtils.DateToString(offDate));
                                    list.add(map1);
                                }
                            }
                        }
                    }else{
                        if(!"0".equals(is_exists_suspicious)){
                            if (StringUtils.isNotBlank(sz) && !StringUtils.isEmpty(sz)) {
                                boolean place = Commons.getPlace(zhongdaozhan, str,palces);
                                if (!place) {
                                    //可能存在缺失,判断逻辑：如从上海出发没有回到上海
                                    if(!zhongdaozhan.trim().equals(sz.trim())){
                                        Map<String, Object> map1 = new HashMap<String, Object>();
                                        map1.put("is_exists_suspicious", "0");
                                        map1.put("shifazhan", "");
                                        map1.put("shifashijian", "");
                                        map1.put("zhongdaozhan", offdutycode);
                                        map1.put("zhongdaoshijian", CXFUtils.DateToString(offDate));
                                        list.add(map1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
    }



    private void getZhengZaiChuQinData(
            String driverId, List<Map<String, Object>> lst, int driverID,
            PbResource pbResource, Date onTimeTuiqintime,DriverPlanPojo driverPlanByWorkNoAndTimeStrAndType) {
        Date onTuiqinTime = onTimeTuiqintime;
        //计划车次
        String planTrainNum = driverPlanByWorkNoAndTimeStrAndType.getPlantrainnum();
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("yun_an_isnotexits", "1");        //运安存在
        maps.put("lkj_isnotexits", "0");            //lkj不存在
        maps.put("sjh", driverId);
        maps.put("shifazhan", driverPlanByWorkNoAndTimeStrAndType.getPlandepstation());
        maps.put("zhongdaozhan", "");
        XMLGregorianCalendar ptime = driverPlanByWorkNoAndTimeStrAndType.getPlanondutytime();
        if(ptime==null){
            maps.put("shifashijian", "");
        }else{
            maps.put("shifashijian", CXFUtils.DateToString(CXFUtils.DateToXML(
                    driverPlanByWorkNoAndTimeStrAndType.getPlanondutytime())));
        }
        maps.put("zhongdaoshijian", "");
        maps.put("drivertime", "");
        maps.put("traincheci", planTrainNum);
        lst.add(maps);
    }
    //处理疑似缺失数据；依据是：如果出勤地点和退勤地点不匹配，说明这个人没有回到出勤的地方
    //目的如果这次作业出勤的地点为上海，理应回到上海（包含上海，上海南，上海南翔）证明这次作业是完整的
    private void excuteYiShiQueShiData(String str, List<Map<String, Object>> lst,Date offdate,String offdutycode,List<String> places) {
        Commons.getListToSortReverseOrderByDate(lst);
        if (lst != null&&lst.size() > 0  ) {
            String shifazhan = (String) lst.get(0).get("shifazhan");
            String zhongdaozhan = (String) lst.get(lst.size() - 1).get("zhongdaozhan");
            if (StringUtils.isNotBlank(zhongdaozhan) && !StringUtils.isEmpty(zhongdaozhan)) {
                boolean place = Commons.getPlace(zhongdaozhan, str,places);
                if (!place) {
                    //可能存在缺失,判断逻辑：如从上海出发没有回到上海
                    if(!zhongdaozhan.trim().equals(shifazhan.trim())){
                        Map<String, Object> map1 = new HashMap<String, Object>();
                        map1.put("is_exists_suspicious", "0");
                        map1.put("shifazhan", zhongdaozhan);
                        map1.put("zhongdaozhan", shifazhan);
                        map1.put("shifashijian", "");
                        map1.put("zhongdaoshijian", CXFUtils.DateToString(offdate));
                        lst.add(map1);
                    }
                }
            }
            //数据只有一条的时候，一般是不会存在一条数据;注：处理违标的数据
            if(lst.size()==1){
                String ya = (String)lst.get(0).get("yun_an_isnotexits");
                String lkj = (String)lst.get(0).get("lkj_isnotexits");
                if(!ya.equals("1")&&!lkj.equals("0")){
                    Map<String, Object> map1 = new HashMap<String, Object>();
                    map1.put("is_exists_suspicious", "0");
                    map1.put("shifazhan", zhongdaozhan);
                    map1.put("zhongdaozhan", shifazhan);
                    map1.put("shifashijian", "");
                    map1.put("zhongdaoshijian", "");
                    lst.add(map1);
                }else if(ya.equals("1")&&lkj.equals("1")){
                    Map<String, Object> map1 = new HashMap<String, Object>();
                    map1.put("is_exists_suspicious", "0");
                    map1.put("shifazhan", zhongdaozhan);
                    map1.put("zhongdaozhan", shifazhan);
                    map1.put("shifashijian", "");
                    map1.put("zhongdaoshijian", "");
                    lst.add(map1);
                }else if(ya.equals("0")&&lkj.equals("1")){
                    Map<String, Object> map1 = new HashMap<String, Object>();
                    map1.put("is_exists_suspicious", "0");
                    map1.put("shifazhan", zhongdaozhan);
                    map1.put("zhongdaozhan", shifazhan);
                    map1.put("shifashijian", "");
                    map1.put("zhongdaoshijian", "");
                    lst.add(map1);
                }
            }
        }
    }

    private void excuteQueShiData(
            String driverId, List<Map<String, Object>> lst,
            DriverPlanPojo driverPlanByWorkNoAndTimeStrAndType,
            String planTrainNum, List<Map<String, Object>> currentDriverStartAndEnd, boolean notEx,String plankaizhan) {
        //以lkj数据为主：目的是判断如果lkj有，运安没有，说明中途换乘了，正常，没有缺失
        for (Map<String, Object> map : currentDriverStartAndEnd) {
            String lkjTrainNum = (String) map.get("traincheci");
            if (lkjTrainNum.trim().equalsIgnoreCase(planTrainNum.trim())) {
                //计划和lkj匹配
                map.put("yun_an_isnotexits", "1");//运安存在
                map.put("lkj_isnotexits", "1");//lkj存在
                lst.add(map);
                notEx = false;
            } else {
                //lkj数据存在
                map.put("yun_an_isnotexits", "0");//运安不存在
                map.put("lkj_isnotexits", "1");//lkj存在
                lst.add(map);
            }
        }

        //数据缺失
        if (notEx) {
            Map<String, Object> maps = new HashMap<String, Object>();
            maps.put("yun_an_isnotexits", "1");        //运安存在
            maps.put("lkj_isnotexits", "0");           //lkj不存在
            maps.put("sjh", driverId);
            maps.put("shifazhan", plankaizhan);
            maps.put("zhongdaozhan", "");
            XMLGregorianCalendar planondutytime = driverPlanByWorkNoAndTimeStrAndType.getPlanondutytime();
            if(planondutytime==null){
                //计划开车时间
                XMLGregorianCalendar ptime = driverPlanByWorkNoAndTimeStrAndType.getPlandeptime();
                if(ptime==null){
                    maps.put("shifashijian", "");
                }else{
                    maps.put("shifashijian", CXFUtils.DateToString(CXFUtils.DateToXML(ptime)));
                }
            }else{
                //计划出勤时间
                maps.put("shifashijian", CXFUtils.DateToString(CXFUtils.DateToXML(planondutytime)));
            }
            maps.put("zhongdaoshijian", "");
            maps.put("drivertime", "");
            maps.put("traincheci", planTrainNum);
            lst.add(maps);
        }
    }

    @Override
    public List<LinkedHashMap<String, Object>> findHostoryRecordWithLKJ(String page, String pageSize
            , String date, String time, String train_batch_no, String driver_id, String region) throws Exception {
        return checkOutDao.findHostoryRecordWithLKJ(page, pageSize
                , date, time, train_batch_no, driver_id, region);
    }

    /**
     * 处理yw_checkout_operation_exception_tmp表数据到yw_checkout_operation_exception
     * @throws Exception
     */
    @Override
    public void saveForTmpToOperationException(String driver_id) throws Exception {
        checkOutDao.saveForTmpToOperationException(driver_id);
    }

    /**
     * 处理当前这个是否正在出勤之中
     * 1    代表正在出勤中
     * 2    代表正在休息中
     * 0    代表已经退勤，可以出分析结果所有信息
     * @param driver_code
     * @return
     * @throws Exception
     */
    @Override
    public String queryCheckining(String driver_code) throws Exception {
        /*PbResource pbResource = CXFUtils.getPbResource();
        Map<String, Object> yunAnDate =
                getYunAnDate(pbResource, Integer.valueOf(driver_code), driver_code.substring(0, 2));
        if(yunAnDate==null){
            return "1";
        }
        Object obj = yunAnDate.get("dtOff");
        if(obj==null){
            return "1";
        }else {
            return "0";
        }*/
        return "0";
    }

    @Override
    public String sendMessages(String driverCode, String sendContent) throws Exception {
        //driverCode通过编号查询乘务员手机号
        String phone = "";
        if(StringUtils.isBlank(driverCode)||StringUtils.isEmpty(driverCode)
                ||StringUtils.isBlank(sendContent.trim())||StringUtils.isEmpty(sendContent.trim())){
            return "";
        }
        sendContent = sendContent.trim().replaceAll("\r\n","");
        sendContent = sendContent.replaceAll(",","，");
        String str = "";
        if("4450436".equals(driverCode.trim())){
            phone = "17302171271";
             str = Sendmsg(sendContent.trim(),phone);
        }else if("4450233".equals(driverCode.trim())){
            phone="13761007720";
             str = Sendmsg(sendContent.trim(),phone);
        }else if("4450437".equals(driverCode.trim())){
            phone="15205685250";
             str = Sendmsg(sendContent.trim(),phone);
        }else if("4410324".equals(driverCode.trim())){
            phone="13472497544";
            str = Sendmsg(sendContent.trim(),phone);
        }else{
//            phone="18800382031";
//            str = Sendmsg(sendContent,phone);
        }
        return str;
    }

    /**
     * 保存登录日志（包含：用户名称、登录时间、所属机务段、主机名等)
     * @param user 登录用户信息
     * @param jwcode 机务段名称
     * @throws Exception
     */
    @Override
    public void saveComputerIp(SysUser user,String jwcode,String ip,String reName) throws Exception {
        checkOutDao.saveComputerIp(user,jwcode, ip, reName);
    }

    @Override
    public void deleteTempTblDataByDriverCode(List<YwDriverOperationEvaluation> maps) throws Exception {
       // checkOutDao.deleteTempTblDataByDriverCode(maps);
    }


    @Override
    public List<Map<String, Object>> getLKJColumsNames(String date, String time, String train_batch_no) {
//        String[] titleName=new String[]{"序号","日期","时间","记录号","事件","里程","距离","信号灯","信号","速度","限速","工况",
//                "管压","闸缸/闸缸1","转速/电流","均缸1","均缸2"
//                ,"线路号","线路名","行别","车站","车站号","模式","其它","补充说明"};
        List<Map<String, Object>> getcolumsList = Commons.getcolums();
        List<String> list = new ArrayList<String>();
        List<Map<String,Object>> resultList = checkOutDao.getTableTitile(date,time,train_batch_no);
        if(resultList.size()>0){
            Map<String, Object> map = resultList.get(0);
            for(Map<String,Object> tmp:getcolumsList){
                String value =""+ tmp.get("title");
                if(value.trim().equals("管压")){
                    tmp.put("title",String.valueOf(map.get("guanya")));
                    //list.add(String.valueOf(map.get("guanya")));
                }else if(value.trim().equals("闸缸/闸缸1")){
                    tmp.put("title",String.valueOf(map.get("gangya")));
                    //list.add(String.valueOf(map.get("gangya")));
                }else if(value.trim().equals("均缸1")){
                    tmp.put("title",String.valueOf(map.get("jungang")));
                    //list.add(String.valueOf(map.get("jungang")));
                }else if(value.trim().equals("均缸2")){
                    tmp.put("title",String.valueOf(map.get("jungang2")));
                    //list.add(String.valueOf(map.get("jungang2")));
                }else if(value.trim().equals("转速/电流")){
                    tmp.put("title",String.valueOf(map.get("zsdl")));
                    //list.add(String.valueOf(map.get("zsdl")));
                }else{
                    //list.add(titleName[i]);
                }
            }
        }
        return getcolumsList;
    }

    /**
     * 获取出退勤时间 以及以这个出退勤时间为准获取上一次退勤时间
     * @param pbResource
     * @param driverID
     * @param str
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> getYunAnDate(PbResource pbResource, int driverID, String str,List<String> places)
            throws IllegalAccessException, ParseException {
        Map<String, Object> oMap = new HashMap<String, Object>();
        oMap.put("onDate", null);
        oMap.put("dtOff", null);
        oMap.put("onTuiqinTime", null);
        List<Map<String, Object>> tmp_1 = new ArrayList<Map<String, Object>>();
        List<DriverDutyPojo> driverDutyRecordByWorkNoAndTimeStr =
                pbResource.getDriverDutyRecordByWorkNoAndTimeStr(
                        driverID,
                        CXFUtils.getBeforMonth(1),
                        Util.getSystemCurrentDate());

        for (DriverDutyPojo ddp : driverDutyRecordByWorkNoAndTimeStr) {
            tmp_1.add(CXFUtils.objectToMap(ddp));
        }

        if(tmp_1==null||tmp_1.size()<1){
            //待修改
            Logger.getLogger("运安获取出退勤数据失败");
            return oMap;
        }
        Commons.sortWithDate(tmp_1, "ondutytime", "offdutytime");

        Object ot = tmp_1.get(0).get("offdutytime");
        if (ot == null) {
            //此人正在出勤中
            Object otOffdutytime = tmp_1.get(1).get("offdutytime");
            if(otOffdutytime!=null){
                //获取计划
                XMLGregorianCalendar oox = (XMLGregorianCalendar)otOffdutytime;
                oMap.put("onTuiqinTime", CXFUtils.DateToXML(oox));
                return oMap;
            }
        }

        XMLGregorianCalendar off = (XMLGregorianCalendar) ot;

        String offCode = (String) tmp_1.get(0).get("offdutycode");//退勤点
        Object o = tmp_1.get(0).get("ondutytime");//处理出勤时间
        XMLGregorianCalendar ontime = null;
        String ondutycode = "";
        if (o == null) {
            for (Map<String, Object> onObj : tmp_1) {
                Object os = onObj.get("ondutytime");
                Object oncode = onObj.get("ondutycode");
                if (os != null&&oncode!=null) {
                    //如果不等于null,获取出勤点
                    String onPlace = (String) oncode;
                    if (Commons.getPlace(onPlace, str,places)) {
                        ontime = (XMLGregorianCalendar) os;
                        ondutycode = onPlace;
                        break;
                    }
                }
            }
        } else {
            Object codeplace = tmp_1.get(0).get("ondutycode");
            if(codeplace==null){
                for (Map<String, Object> onObj : tmp_1) {
                    Object os = onObj.get("ondutytime");
                    Object oncode = onObj.get("ondutycode");
                    if (os != null&&oncode!=null) {
                        //如果不等于null,获取出勤点
                        String onPlace = (String) oncode;
                        if (Commons.getPlace(onPlace, str,places)) {
                            ontime = (XMLGregorianCalendar) os;
                            ondutycode = onPlace;
                            break;
                        }
                    }
                }
            }else{
                //获取出勤点，出勤点如果是上海到上海就是内勤
                String oncodes = (String) codeplace;
                if (Commons.getPlace(oncodes, str,places)) {
                    ontime = (XMLGregorianCalendar) o;
                    ondutycode = oncodes;
                } else {
                    for (Map<String, Object> onObj : tmp_1) {
                        Object os = onObj.get("ondutytime");
                        Object oncode = onObj.get("ondutycode");
                        if (os != null&&oncode!=null) {
                            //如果不等于null,获取出勤点
                            String onPlace = (String) oncode;
                            if (Commons.getPlace(onPlace, str,places)) {
                                ontime = (XMLGregorianCalendar) os;
                                ondutycode = onPlace;
                                break;
                            }
                        }
                    }
                }
            }
        }
        //出退勤时间处理完毕

        //找这次出勤的上次退勤时间
        Date ondt = null;
        if (ontime != null) {
            ondt = CXFUtils.DateToXML(ontime);
        }
        Date planTime = null;
        List<Map<String,Object>> lst = new ArrayList<Map<String,Object>>();
        for(Map<String,Object> map : tmp_1){
            Object oo = map.get("offdutytime");
            if(oo!=null){
                lst.add(map);
            }
        }
        //asc排序
        Commons.getDateSort(lst,"offdutytime");
        for(int i = lst.size()-1;i>=0;i--){
            XMLGregorianCalendar offdate = (XMLGregorianCalendar) lst.get(i).get("offdutytime");
            if(offdate==null) continue;
            Date xmlOffDate = CXFUtils.DateToXML(offdate);
            long ondatetime = ondt.getTime();
            long offdatetimes = xmlOffDate.getTime();
            if(ondatetime>offdatetimes){
                planTime = xmlOffDate;
                break;
            }
        }

        oMap.put("onDate", ondt);
        oMap.put("ondutycode", ondutycode);
        oMap.put("offdutycode", offCode);
        oMap.put("dtOff", CXFUtils.DateToXML(off));
        oMap.put("onTuiqinTime", planTime);
        return oMap;
    }
}
