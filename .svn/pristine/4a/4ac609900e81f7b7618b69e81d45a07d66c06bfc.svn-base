package com.tenly.test;

import byxx.cxf.utils.CXFUtils;
import byxx.resource.DriverAlcoholPojo;
import byxx.resource.DriverDutyPojo;
import byxx.resource.PbResource;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.tenly.common.projecttools.Commons;
import com.tenly.common.projecttools.DBUtils;
import com.tenly.common.projecttools.SQLServerDBUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class TestJL {
    public static void main(String[] args) {
        String driverIds = "4505193";
        PbResource pbResource = CXFUtils.getPbResource();
        String sql = "select * from yw_data_mid where sjh='"+driverIds+"' order by zhuanchushijian DESC limit 0,10";
        //上次转储时间
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;

        //中间库的链接信息
        SQLServerDBUtils dbsql = null;
        try {
             conn = DBUtils.getConnection();
             pstm = conn.prepareStatement(sql);
             resultSet = pstm.executeQuery();
             String onTime = "";//上次的lkj转储时间
             String tmp = "";//临时变量
            long nd = 1000*24*60*60;
            long nh = 1000*60*60;
             while(resultSet.next()){
                //系统转储时间
                String zhuanchushijian = resultSet.getString("zhuanchushijian");
                String strDate = zhuanchushijian.substring(0,4)+"-"+zhuanchushijian.substring(4,6)+"-"+zhuanchushijian.substring(6,8)+" 00:00:00";
                 Date zhuanchudate = Commons.getDate(strDate);
                if(!tmp.equals("")){
                    Date tmpzhuanchushijian = Commons.getDate(tmp);
                    //获取两个时间的毫秒时间差异
                    long diff = Math.abs(tmpzhuanchushijian.getTime()-zhuanchudate.getTime());
                    //计算差多少个小时
                    long hour = diff/nh;
                    Double aDouble = Double.valueOf(hour);
                    if(aDouble>12&&aDouble<36){
                        onTime = strDate;
                        break;
                    }else if(aDouble>36){
                        //时间太长也不行，所以以最大时间向前推一天
                        //防止其它时间转进来文件，但是这个交路不属于这一次的（比如是上次的文件）
                        onTime = Commons.beforeOneHourToNowDate(Commons.getStringToDate(tmp),24);
                        break;
                    }
                }
                if(tmp.equals("")){
                    tmp = strDate;
                }
            }
            tmp = Commons.endOneHourToNowDate(Commons.getStringToDate(tmp),12);
            //存储运安数据的临时变量driverDuty转换成otodutypojolist


            List<Map<String,Object>> otodutypojolist = new ArrayList<Map<String,Object>>();
            List<DriverDutyPojo> driverDuty = pbResource.getDriverDutyRecordByWorkNoAndTimeStr(
                    Integer.valueOf(driverIds),onTime,tmp);

            for (DriverDutyPojo dutyPojo :driverDuty) {
                otodutypojolist.add(CXFUtils.objectToMap(dutyPojo));
            }
            //对运安数据进行排序
            Commons.sortWithDate(otodutypojolist,"ondutytime","offdutytime");

//            for(Map<String,Object> map :otodutypojolist){
//                System.out.println(map);
//            }
            DBUtils.close(conn,pstm,resultSet);//关闭所有事务

            conn = DBUtils.getConnection();
            String lkjsql  = "SELECT * from yw_data_mid where shifashijian>='"+onTime+"' and zhongdaoshijian<='"+tmp+"'";
                    lkjsql+=" and sjh='"+driverIds+"' ORDER  by zhongdaoshijian desc;";
            pstm = conn.prepareStatement(lkjsql);
            resultSet = pstm.executeQuery();
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

            while(resultSet.next()){
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("traincheci",resultSet.getString("traincheci"));
                map.put("sjh",resultSet.getString("sjh"));
                map.put("train_num",resultSet.getString("train_num"));
                map.put("shifazhan",resultSet.getString("shifazhan"));
                map.put("shifashijian",resultSet.getString("shifashijian"));
                map.put("zhongdaozhan",resultSet.getString("zhongdaozhan"));
                map.put("zhongdaoshijian",resultSet.getString("zhongdaoshijian"));
                map.put("yun_an_isnotexits","0");
                map.put("lkj_isnotexits","1");
                map.put("is_exists_suspicious","0");
                list.add(map);
            }
            Commons.getListToSortReverseOrderByDate(list);

            /**
             * taskId=77003326, driverSecCode=H41, model=HXD1D, trainNum=K8401, driverSecStr=,
             * lowerCode=杭州客车台, ondutytime=2019-03-03T21:27:04+08:00, score=0, offdutytype=1,
             * examtime=null, team=1, loco=146, cargo=1, taskName=普通班轮乘回段, driverid=773824884,
             * jobid=775230418, workNo=4506066, dispLocoCode=null, offdutycode=望江门退勤, id=406865,
             * ondutycode=上海南出勤, offdutytime=2019-03-04T02:25:41+08:00, group=5, dispManCode=望江门派班室,
             * driverName=王立挺, note=null, ondutytype=1
             */
            StringBuilder sb = new StringBuilder();
            for(int i = otodutypojolist.size()-1;i>=0;i--){
                Object ondutytime = otodutypojolist.get(i).get("ondutytime");
                Object offdutytime = otodutypojolist.get(i).get("offdutytime");
                Object driverName = otodutypojolist.get(i).get("driverName");
                Object dispManCode = otodutypojolist.get(i).get("dispManCode");
                Object group = otodutypojolist.get(i).get("group");
                Object ondutycode = otodutypojolist.get(i).get("ondutycode");
                Object id = otodutypojolist.get(i).get("id");
                Object offdutycode = otodutypojolist.get(i).get("offdutycode");
                Object dispLocoCode = otodutypojolist.get(i).get("dispLocoCode");
                Object workNo = otodutypojolist.get(i).get("workNo");
                Object jobid = otodutypojolist.get(i).get("jobid");
                Object driverid = otodutypojolist.get(i).get("driverid");
                Object taskName = otodutypojolist.get(i).get("taskName");
                Object cargo = otodutypojolist.get(i).get("cargo");
                Object loco = otodutypojolist.get(i).get("loco");
                Object team = otodutypojolist.get(i).get("team");
                Object examtime = otodutypojolist.get(i).get("examtime");
                Object offdutytype = otodutypojolist.get(i).get("offdutytype");
                Object score = otodutypojolist.get(i).get("score");
                Object lowerCode = otodutypojolist.get(i).get("lowerCode");
                Object driverSecStr = otodutypojolist.get(i).get("driverSecStr");
                Object trainNum = otodutypojolist.get(i).get("trainNum");
                Object model = otodutypojolist.get(i).get("model");
                Object driverSecCode = otodutypojolist.get(i).get("driverSecCode");
                Object taskId = otodutypojolist.get(i).get("taskId");
                if(ondutytime!=null&&offdutytime!=null){
                    //直接就有始发终到
                    Date ondutydate = CXFUtils.DateToXML((XMLGregorianCalendarImpl) ondutytime);
                    String ondutytimestr = CXFUtils.DateToString(ondutydate);
                    Date offdutydate = CXFUtils.DateToXML((XMLGregorianCalendarImpl) offdutytime);
                    String offdutytimestr = CXFUtils.DateToString(offdutydate);
                    //出勤信息
                    sb.append(ondutytimestr+","+driverName+","+dispManCode+","+group+",");
                    sb.append(id+","+ondutycode+","+dispLocoCode+","+workNo+",");
                    sb.append(jobid+","+driverid+","+driverName+","+taskName+","+cargo+",");
                    sb.append(loco+","+team+","+examtime+","+offdutytype+","+score+",");
                    sb.append(lowerCode+","+driverSecStr+","+trainNum+","+model+","+driverSecCode);
                    //退勤信息
                    sb.append("&&");
                    sb.append(offdutytimestr+","+driverName+","+dispManCode+","+group);
                    sb.append(","+id+","+offdutycode+","+dispLocoCode+","+workNo);
                    sb.append(jobid+","+driverid+","+driverName+","+taskName+","+cargo);
                    sb.append(loco+","+team+","+examtime+","+offdutytype+","+score);
                    sb.append(lowerCode+","+driverSecStr+","+trainNum+","+model+","+driverSecCode);
                    sb.append(taskId+"_");
                }else if(ondutytime!=null&&offdutytime==null){
                    //只有出勤信息
                    Date ondutydate = CXFUtils.DateToXML((XMLGregorianCalendarImpl) ondutytime);
                    String ondutytimestr = CXFUtils.DateToString(ondutydate);
                    sb.append(ondutytimestr+","+driverName+","+dispManCode+","+group+",");
                    sb.append(id+","+ondutycode+","+dispLocoCode+","+workNo+",");
                    sb.append(jobid+","+driverid+","+driverName+","+taskName+","+cargo+",");
                    sb.append(loco+","+team+","+examtime+","+offdutytype+","+score+",");
                    sb.append(lowerCode+","+driverSecStr+","+trainNum+","+model+","+driverSecCode);
                    sb.append(taskId+",");
                }else if(ondutytime==null&&offdutytime!=null){
                    //只有退勤信息
                    //只有出勤信息
                    Date offdutydate = CXFUtils.DateToXML((XMLGregorianCalendarImpl) offdutytime);
                    String offdutytimestr = CXFUtils.DateToString(offdutydate);
                    sb.append("&&");
                    sb.append(offdutytimestr+","+driverName+","+dispManCode+","+group+",");
                    sb.append(id+","+offdutycode+","+dispLocoCode+","+workNo+",");
                    sb.append(jobid+","+driverid+","+driverName+","+taskName+","+cargo+",");
                    sb.append(loco+","+team+","+examtime+","+offdutytype+","+score+",");
                    sb.append(lowerCode+","+driverSecStr+","+trainNum+","+model+","+driverSecCode);
                    sb.append(taskId+"_");
                }else{
                    //数据存在异常，待考虑如何处理
                }
            }

            String sbstr = sb.toString();
            String[] splits = sbstr.split("_");
            List<Map<String,Object>> yuanchutuiqinlist = new ArrayList<Map<String,Object>>();
            for(String ss:splits){
                Map<String,Object> map = new HashMap<String,Object>();
                String[] ersplits = ss.split("&&");
                String[] chuqinsplits = ersplits[0].split(",");
                String[] tuiqinsplits = ersplits[1].split(",");
                /**
                 * 2019-03-03 02:04:00,王立挺,望江门派班室,5,400128,金华公寓出勤,null,4506066,775230337,773824884,
                 * 王立挺,普通班轮乘回段,1,336,1,null,1,0,杭州客车台,,K1210,HXD3C,H41
                 * &&2019-03-03 09:59:15,王立挺,望江门派班室,5,400128,上海南退勤,null,4506066775230337,773824884,
                 * 王立挺,普通班轮乘回段,1336,1,null,1,0杭州客车台,,K1210,HXD3C,H4177003326_
                 * yun_an_isnotexits 、lkj_isnotexits、is_exists_suspicious
                 */
                map.put("shifashijian",chuqinsplits[0]);
                map.put("shifazhan",chuqinsplits[5]);
                map.put("traincheci",chuqinsplits[chuqinsplits.length-2]);
                map.put("zhongdaoshijian",tuiqinsplits[0]);
                map.put("zhongdaozhan",tuiqinsplits[5]);
                map.put("sjh",chuqinsplits[7]);
                map.put("yun_an_isnotexits","1");
                map.put("lkj_isnotexits","0");
                map.put("is_exists_suspicious","0");
                yuanchutuiqinlist.add(map);
            }
            Commons.getListToSortReverseOrderByDate(yuanchutuiqinlist);

            /**
             * 对运安出退勤数据和lkj进行比较，这里暂未使用测酒数据，测酒数据需要确认如何进行关联
             */
            for (Map<String, Object> yunan : yuanchutuiqinlist) {
                boolean bl = false;
                String shifashijian = (String) yunan.get("shifashijian");
                if (StringUtils.isNotBlank(shifashijian) && StringUtils.isNotEmpty(shifashijian)) {
                    for (Map<String, Object> lkj : list) {
                        String lkjshifashijian = (String) lkj.get("shifashijian");
                        //计算两个日期的时间差
                        if (StringUtils.isNotBlank(lkjshifashijian) && StringUtils.isNotEmpty(lkjshifashijian)) {
                            Date date1 = Commons.getDate(shifashijian);
                            Date date2 = Commons.getDate(lkjshifashijian);
                            long diff = date1.getTime() - date2.getTime();
                            long hour = Math.abs(diff / nh);
                            if (hour <= 2) {
                                bl = true;
                                lkj.put("yun_an_isnotexits", "1");
                                lkj.put("lkj_isnotexits", "1");
                                break;
                            }
                        }
                    }
                    if (!bl) {
                        yunan.put("yun_an_isnotexits", "1");
                        yunan.put("lkj_isnotexits", "0");
                        list.add(yunan);
                    }
                }
            }
            Commons.getListToSortReverseOrderByDate(list);
            System.out.println(sb.toString());
            //lkj数据
            String lkjstr = "";
            for (int i = 0;i<list.size();i++){
                lkjstr+=""+list.get(i).get("shifashijian")+"("+list.get(i).get("shifazhan")+"),"+list.get(i).get("traincheci");
                lkjstr+=","+list.get(i).get("zhongdaoshijian")+"("+list.get(i).get("zhongdaozhan")+"_";
            }
            System.out.println("------------------lkj开始-------------------");
            System.out.println(lkjstr);
            System.out.println("------------------lkj结束-------------------");
            DBUtils.close(conn,pstm,resultSet);
            //获取测酒数据
//            List<DriverAlcoholPojo> driverAlcoholByWorkNoAndTimeStr = pbResource.getDriverAlcoholByWorkNoAndTimeStr(4506066, onTime, systime);
//            List<Map<String,Object>> lclhol = new ArrayList<Map<String,Object>>();
//            for (DriverAlcoholPojo pojo:driverAlcoholByWorkNoAndTimeStr) {
//                lclhol.add(CXFUtils.objectToMap(pojo));
//            }

            //从中间库获取酒测数据，杭州是sqlserver2008 上海是oracle
           // sql server Commons.endOneHourToNowDate(Commons.getStringToDate(onTime),24), systime
            String sqlserver = "SELECT * FROM VIEW_CJ WHERE strTime>=? and strTime<=? and strNumber=?";
            //判断所属机务段，然后跟据机务段获取中间库的链接
            dbsql = new SQLServerDBUtils();
            List<Map<String,Object>> lclhol =dbsql.excuteQuery(sqlserver, new Object[]{onTime, tmp, driverIds});
            for(Map<String,Object> ot : lclhol)
            {
                System.out.println(ot);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn,pstm,resultSet);
        }
    }
}
