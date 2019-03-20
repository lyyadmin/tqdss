package com.tenly.common.projecttools;

import java.io.*;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import byxx.resource.DriverDutyPojo;
import byxx.resource.PbResource;

import com.tenly.system.bean.PageBean;

import org.apache.commons.lang3.StringUtils;

import byxx.cxf.utils.CXFUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import sun.management.counter.perf.PerfLongArrayCounter;


@SuppressWarnings("all")
public class Commons {
	/**
	 */
	public static String getDatePoor(Date endDate, Date nowDate) {
		 
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    // long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    // long sec = diff % nd % nh % nm / ns;
	    return min+"";
	}
	/**|
	 * |计算两个时间范围的时间差
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int getTimeDistance(Date beginDate , Date endDate ) {
	    Calendar beginCalendar = Calendar.getInstance();
	    beginCalendar.setTime(beginDate);
	    Calendar endCalendar = Calendar.getInstance();
	    endCalendar.setTime(endDate);
	    long beginTime = beginCalendar.getTime().getTime();
	    long endTime = endCalendar.getTime().getTime();
	    int betweenDays = (int)((endTime - beginTime) / (1000 * 60 * 60 *24));//先算出两时间的毫秒数之差大于一天的天数
	    
	    endCalendar.add(Calendar.DAY_OF_MONTH, -betweenDays);//使endCalendar减去这些天数，将问题转换为两时间的毫秒数之差不足一天的情况
	    endCalendar.add(Calendar.DAY_OF_MONTH, -1);//再使endCalendar减去1天
	    if(beginCalendar.get(Calendar.DAY_OF_MONTH)==endCalendar.get(Calendar.DAY_OF_MONTH))//比较两日期的DAY_OF_MONTH是否相等
	        return betweenDays + 1;	//相等说明确实跨天了
	    else
	        return betweenDays + 0;	//不相等说明确实未跨天
		}
	
	/**
	 * 类型转换   传入一个字符串返回一个date日期类型
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String dateStr) throws ParseException{
		//暂时特殊处理日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = sdf.parse(dateStr);
		return parse;
	}
	
	/**
	 * 计算两个时间范围的相差分数值
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static double getTimes(Date beginDate , Date endDate ){
		long l=beginDate.getTime()-endDate.getTime();
		long day=l/(24*60*60*1000);
		long hour=(l/(60*60*1000)-day*24);
		double mins = l/(60*1000);
		long min=((l/(60*1000))-day*24*60-hour*60);
		long s=(l/1000-day*24*60*60-hour*60*60-min*60);
		return mins;
	}
	/**
	 * 实现date加一天操作
	 * @param date  日期
	 * @param val	增加的值（注意：是天数）
	 * @return
	 */
	public static String getLeiJiaDate(Date date,int val){
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.add(Calendar.DAY_OF_MONTH, val);
		Date time = cd.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String newDate = sdf.format(time);
		return newDate;
	}

	/**
	 * 实现date加一天操作
	 * @param date  日期
	 * @param val	增加的值（注意：是天数）
	 * @return
	 */
	public static String getjianquxiaoshi(Date date,int val){
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.set(Calendar.HOUR, Calendar.HOUR-val);
		Date time = cd.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String newDate = sdf.format(time);
		return newDate;
	}

	/**
	 * 顺序排序
	 * 对list中对象进行排序
	 * @param list
	 */
	public static void getListToSortReverseOrder(List<Map<String,Object>> list){
		Collections.sort(list, new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				int le1 = ((Long)o1.get("problem_level")).intValue();
				int le2 = ((Long)o2.get("problem_level")).intValue();
				if (le1 > le2) {
                    return 1;
                } else if (le1 == le2) {
                	int countDeductScore1 = ((Long)o1.get("count_problem_code")).intValue();
                	int countDeductScore2 = ((Long)o2.get("count_problem_code")).intValue();
    				if(countDeductScore1<countDeductScore2){
    					return 1;
    				}else if(countDeductScore1==countDeductScore2){
    					return 0;
    				}else{
                        return -1;
    				}  
                } else {
                    return -1;  
                }
			}
        });
	}
	/**
	 * 顺序
	 * 对list中对象进行排序 之运接口出来数据进行排序
	 * @param list
	 */
	public static void getListToSortOrder(List<Map<String,Object>> list,String fie){
		Collections.sort(list, new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				Integer le1 = (Integer)o1.get("yj_level");
				Integer le2 = (Integer)o2.get("yj_level");
				if(le1==null||le2==null){
					return -1;
				}
				if (le1 < le2) {  
                    return 1;  
                } else if (le1 == le2) {
                    return 0;  
                } else {  
                    return -1;  
                }
			}
        });
	}
	/**
	 * 排序字符串的日期
	 * @param list
	 */
	public static void getListToSortReverseOrderByDate(List<Map<String,Object>> list){
		Collections.sort(list, new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				String tm = (String)o1.get("shifashijian");
				String tm2 = (String)o2.get("shifashijian");
				if(StringUtils.isBlank(tm)||StringUtils.isEmpty(tm)
						||StringUtils.isBlank(tm2)||StringUtils.isEmpty(tm2)){
					return 1;
				}
				try {
	                    Date dt1 = Commons.getDate(tm);
	                    Date dt2 = Commons.getDate(tm2);
	                    if (dt1.getTime() > dt2.getTime()) {
	                        return 1;
	                    } else if (dt1.getTime() < dt2.getTime()) {
	                        return -1;
	                    } else {
	                        return 0;
	                    }
					}catch (Exception e) {
	                        e.printStackTrace();
	                }
				return -1;
			}
        });
	}
	
	/**
	 * 排序字符串的日期
	 * 始发终到专门的时间排序
	 * @param list
	 */
	public static void getListToSortReverseOrderByDateWithYUNAN(List<Map<String,Object>> list){
		Collections.sort(list, new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				XMLGregorianCalendar tm = (XMLGregorianCalendar)o1.get("offdutytime");
				XMLGregorianCalendar tm2 = (XMLGregorianCalendar)o2.get("offdutytime");
				if(tm==null){
					XMLGregorianCalendar tmOndutytime = (XMLGregorianCalendar)o1.get("ondutytime");
					if(tm2==null){
						XMLGregorianCalendar tm2OndutyTime = (XMLGregorianCalendar)o2.get("ondutytime");
						 Date dt1 = CXFUtils.DateToXML(tmOndutytime);
		                 Date dt2 = CXFUtils.DateToXML(tm2OndutyTime);
		                 if(dt1.getTime()>dt2.getTime()){
		                	 return 1;
		                 }else if(dt1.getTime()==dt2.getTime()){
		                	 return 0;
		                 }else {
		                	 return -1;
		                 }
					}else {
						 Date dt1 = CXFUtils.DateToXML(tmOndutytime);
		                 Date dt2 = CXFUtils.DateToXML(tm2);
		                 if(dt1.getTime()>dt2.getTime()){
		                	 return 1;
		                 }else if(dt1.getTime()==dt2.getTime()){
		                	 return 0;
		                 }else {
		                	 return -1;
		                 }
					}
				}else{
					if(tm2==null){
						XMLGregorianCalendar tm2OndutyTime = (XMLGregorianCalendar)o2.get("ondutytime");
						 Date dt1 = CXFUtils.DateToXML(tm);
		                 Date dt2 = CXFUtils.DateToXML(tm2OndutyTime);
		                 if(dt1.getTime()>dt2.getTime()){
		                	 return 1;
		                 }else if(dt1.getTime()==dt2.getTime()){
		                	 return 0;
		                 }else {
		                	 return -1;
		                 }
					}else{
	                    Date dt1 = CXFUtils.DateToXML(tm);
	                    Date dt2 = CXFUtils.DateToXML(tm2);
	                    if (dt1.getTime() > dt2.getTime()) {
	                        return 1;
	                    } else if (dt1.getTime() == dt2.getTime()) {
	                        return 0;
	                    } else {
	                        return -1;
	                    }
					}
				}
				
//				
//				if(tm==null||tm2==null||
//						tm2.equals("null")||tm.equals("null")
//						||tm.equals("")||tm2.equals("")){
//					return -1;
//				}
//				if(tm==null||tm2==null){
//					return -1;
//				}
//				try {
//	                    Date dt1 = CXFUtils.DateToXML(tm);
//	                    Date dt2 = CXFUtils.DateToXML(tm2);
//	                    if (dt1.getTime() > dt2.getTime()) {
//	                        return 1;
//	                    } else if (dt1.getTime() < dt2.getTime()) {
//	                        return -1;
//	                    } else {
//	                        return 0;
//	                    }
//					}catch (Exception e) {
//	                        e.printStackTrace();
//	                }
//				System.out.println(tm);
//		System.out.println(tm2);
//		System.out.println("==================================");
//				return tm.compare(tm2);
			}
        });
	}
	
	
	
	
	/**
	 * 排序字符串的日期
	 * 为数据表格计划时间排序
	 * @param list
	 */
	public static void getListToSortReverseOrderByDataGrid(List<Map<String,Object>> list ,String c1){
		Collections.sort(list, new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				XMLGregorianCalendar tm = (XMLGregorianCalendar)o1.get("ondutytime");
				XMLGregorianCalendar tm2 = (XMLGregorianCalendar)o2.get("ondutytime");
				if(tm==null||tm2==null||
						tm2.equals("null")||tm.equals("null")
						||tm.equals("")||tm2.equals("")){
					return -1;
				}
				Date dt1 = CXFUtils.DateToXML(tm);
				Date dt2 = CXFUtils.DateToXML(tm2);

//				if(tm==null||tm2==null){
//					return -1;
//				}
//				try {
//	                    Date dt1 = CXFUtils.DateToXML(tm);
//	                    Date dt2 = CXFUtils.DateToXML(tm2);
//	                    if (dt1.getTime() > dt2.getTime()) {
//	                        return 1;
//	                    } else if (dt1.getTime() < dt2.getTime()) {
//	                        return -1;
//	                    } else {
//	                        return 0;
//	                    }
//					}catch (Exception e) {
//	                        e.printStackTrace();
//	                }
//				System.out.println(tm);
//		System.out.println(tm2);
//		System.out.println("==================================");
                if(dt1.getTime()>dt2.getTime()){
                    return 1;
                }else if(dt1.getTime()==dt2.getTime()){
                    return 0;
                }else{
                    return -1;
                }
			}
        });
	}
	/**
	 * 
	 * @param list
	 */
	public static void getListToSortReverseOrderByDateWithYUNANOndutytime(
			List<Map<String,Object>> list,String offdutytime){
		Collections.sort(list, new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				XMLGregorianCalendar tm = (XMLGregorianCalendar)o1.get("ondutytime");
				XMLGregorianCalendar tm2 = (XMLGregorianCalendar)o2.get("ondutytime");
				if(tm==null||tm2==null){
					return -1;
				}
				try {
	                    Date dt1 = CXFUtils.DateToXML(tm);
	                    Date dt2 = CXFUtils.DateToXML(tm2);
	                    if (dt1.getTime() > dt2.getTime()) {
	                        return 1;
	                    } else if (dt1.getTime() < dt2.getTime()) {
	                        return -1;
	                    } else {
	                        return 0;
	                    }
					}catch (Exception e) {
	                        e.printStackTrace();
	                }
				return -1;
			}
        });
	}
	/**
	 * 格式化字符串  为日期类型
	 * @param dt
	 * @return
	 */
	public static Date getStringToDate(String dt){
		//SimpleDateFormat 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parse=null;
		try {
			parse = sdf.parse(dt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parse;
	}
	/**
	 * 向前推行天数
	 * @param dates
	 * @param day
	 * @return
	 */
	public static String getToBeforeDay(String dates,int day){
		 	SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 	Date parse=null;
		 	Date updateDate4 = null;
			try {
				parse = sdf.parse(dates);
				Calendar calendar = Calendar.getInstance();
		        calendar.setTime(parse);
		        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-day);
		        updateDate4 = calendar.getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return sdf.format(updateDate4);
	}
	/**
	 * 向前推行天数
	 * @param dates
	 * @param day
	 * @return
	 */
	public static String getToAfterDay(String dates,int day){
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parse=null;
		Date updateDate4 = null;
		try {
			parse = sdf.parse(dates);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(parse);
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)+day);
			updateDate4 = calendar.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sdf.format(updateDate4);
	}
	
	
	/**
	 * 获取系统当前日期
	 * @return
	 */
	public static String getSystemCurrentDate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(System.currentTimeMillis());
	}
	
	/**
	 * Calendar calendar = Calendar.getInstance();　
	　　　　calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);　
	　　　　SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");　
	　　　　System.out.println("一个小时前的时间：" + df.format(calendar.getTime()));　
	　　　　System.out.println("当前的时间：" + df.format(new Date()));
	 */
	public static String beforeOneHourToNowDate(Date dt,int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(calendar.getTime());
	} 
	
	
	public static void getWriteFile(List<String> list){
		FileWriter fileWritter =null;
		BufferedWriter bw = null;
		try{
		      String data = " This content will append to the end of the file";
//		      File file =new File("E://test//newfile//DriverPlanPojo.txt");
		      File file =new File("E://test//newfile//DriverdutyPojo.txt");
		      //if file doesnt exists, then create it
		      if(!file.exists()){
		    	  file.createNewFile();
		      }
		      FileOutputStream fos = new FileOutputStream(file);
		       bw = new BufferedWriter(new OutputStreamWriter(fos));
		      //true = append file
//		       fileWritter = new FileWriter(file.getName(),true);
		      for(int i=0;i<list.size();i++){
		    	  bw.write(list.get(i));
		    	  bw.newLine();
		      }
		      bw.close();
		     }catch(IOException e){
		      e.printStackTrace();
		     }finally{
		    	 try {
		    		 bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     }
	}
	 /**
     * JSON 转 POJO
     */
     public static <T> T getObject(String pojo, Class<T> tclass) {
            try {
                return JSONObject.parseObject(pojo, tclass);
            } catch (Exception e) {
//                log.error(tclass + "转 JSON 失败");
            }
            return null;
     }
     
     /**
      * POJO 转 JSON    
      */
     public static <T> String getJson(T tResponse){
         String pojo = JSONObject.toJSONString(tResponse);
         return pojo;
     }
     
     /**
      * List<T> 转 json 保存到数据库
      */
     public static <T> String listToJson(List<T> ts) {
         String jsons = JSON.toJSONString(ts);
         return jsons;
     }

     /**
      * json 转 List<T>
      */
     public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
         @SuppressWarnings("unchecked")
         List<T> ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
         return ts;
     }

	/**
	 * 重写分页
	 * @param page
	 * @param pageSize
	 * @param pageBean
	 */
	public static PageBean getBuildPageBeanList(String page, String pageSize,
												PageBean pageBean) {
		if(StringUtils.isNotBlank(page)&&!StringUtils.isEmpty(page)
				&&StringUtils.isNotBlank(pageSize)&&!StringUtils.isEmpty(pageSize)){
			List<Map<String,Object>> list = (List<Map<String,Object>>)pageBean.getRows();
			List<Map<String,Object>> newList = new ArrayList<Map<String,Object>>();
			Integer pageNumber = Integer.valueOf(page);
			Integer pagesize = Integer.valueOf(pageSize);
			int pages = (pageNumber-1)*pagesize;
			int showPage = pages+pagesize;
			if(showPage>list.size()){
				showPage = list.size();
			}
			for(int i = pages;i<showPage;i++){
				newList.add(list.get(i));
			}
			pageBean.setCurrentPage(pageNumber);
			pageBean.setTotal(list.size());
			pageBean.setRows(newList);
		}
		return pageBean;
	}


	public String getNewDateByQuarter(String year,String quarter){
		if(StringUtils.isNotBlank(quarter)&&!StringUtils.isEmpty(quarter)){
			if(quarter.equals("1")){

			}else if(quarter.equals("4")){

			}else if(quarter.equals("7")){

			}else if(quarter.equals("10")){

			}
		}

		return null;
	}


	/**
	 * @param list 要排序的集合
	 * @param c1   排序条件1
	 * @param c2   排序条件2
	 */
	public static void getSort(
			List<Map<String,Object>> list,final String c1,final String  c2){
		Collections.sort(list, new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				BigDecimal oo1 = (BigDecimal)o1.get(c1);
				BigDecimal oo2 = (BigDecimal)o2.get(c1);
				double vv1 = oo1.doubleValue();
				double vv2 = oo2.doubleValue();
				if(vv1>vv2){
					return 1;
				}else if(vv1==vv2){
					BigDecimal oo1_1 = (BigDecimal)o1.get(c2);
					BigDecimal oo2_1 = (BigDecimal)o2.get(c2);
					double oo1_2 = oo1_1.doubleValue();
					double oo2_2 = oo2_1.doubleValue();
					if(oo1_2<oo2_2){
						return 1;
					}else if (oo1_1==oo2_1){
						return 0;
					}else {
						return -1;
					}
				}else {
					return -1;
				}
			}
		});
	}



	/**
	 * @param list 要排序的集合
	 * @param c1   排序条件1
	 * @param c2   排序条件2
	 */
	public static void getSortDesc(
			List<Map<String,Object>> list, final String c1, final String  c2){
		Collections.sort(list, new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				BigDecimal oo1 = (BigDecimal)o1.get(c1);
				BigDecimal oo2 = (BigDecimal)o2.get(c1);
				double vv1 = oo1.doubleValue();
				double vv2 = oo2.doubleValue();
				if(vv1>vv2){
					return 1;
				}else if(vv1==vv2){
					BigDecimal oo1_1 = (BigDecimal)o1.get(c2);
					BigDecimal oo2_1 = (BigDecimal)o2.get(c2);
					double oo1_2 = oo1.doubleValue();
					double oo2_2 = oo2.doubleValue();
					if(oo1_2>oo2_2){
						return 1;
					}else if (oo1_1==oo2_1){
						return 0;
					}else {
						return -1;
					}
				}else {
					return -1;
				}
			}
		});
	}

	/**
	 * 把时间向后推行时间
	 * @param dt
	 * @param hour
	 * @return
	 */
	public static String endOneHourToNowDate(Date dt,int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(calendar.getTime());
	}


	/**
	 * 把时间向后推行时间
	 * @param dt
	 * @param hour
	 * @return
	 */
	public static String afterTimeMinute(Date dt,int mm) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + mm);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(calendar.getTime());
	}


	/**
	 * 把时间向后推行时间 beforTimeMinute
	 * @param dt
	 * @param hour
	 * @return
	 */
	public static String beforTimeMinute(Date dt,int mm) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - mm);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(calendar.getTime());
	}


	/**
	 * 各机务段的出退勤地点
	 * @param place
	 * @param driverFirstToTwo
	 * @return
	 */
	public static boolean getPlace(String place,String driverFirstToTwo,List<String> places){
		boolean bl = false;
        for(String ssw:places){
            if(ssw.contains(place)||place.contains(ssw)||place.equals(ssw)){
                bl = true;
                break;
            }
        }
		return bl;
	}

    /**
     * 匹配地点所属那个机务段
     * @param place
     * @param list
     * @return
     */
    public static String getCJPlace(String place,List<Map<String,Object>> list){
	    String ssw = "";
        for(Map<String,Object> map:list){
            String place1 = (String)map.get("places");
            if(StringUtils.isNotBlank(place1)&&StringUtils.isNotEmpty(place1)){
                String[] splits = place1.split(",");
                for(String ss:splits){
                    if(ss.contains(place)||place.contains(ss)||ss.trim().equals(place.trim())){
                        ssw = (String)map.get("jiwuduan_code");
                        break;
                    }
                }
            }

        }
        return ssw;
    }



	public static boolean getNew(String s1 ,String s2){

		return true;
	}

	/**
	 * @param list 要排序的集合
	 * @param c1   排序条件1
	 * @param c2   排序条件2
	 */
	public static void getDateSort(
			List<Map<String,Object>> list,final String c1){
		Collections.sort(list, new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				XMLGregorianCalendar xDate1 = (XMLGregorianCalendar)o1.get(c1);
				XMLGregorianCalendar xDate2 = (XMLGregorianCalendar)o2.get(c1);
				if(xDate1==null||xDate2==null){
					return -1;
				}
				Date dt1 = DateToXML(xDate1);
				Date dt2 = DateToXML(xDate2);
				if(dt1.getTime()>dt2.getTime()){
					return 1;
				}else if(dt1.getTime()==dt2.getTime()){
					return 0;
				}else {
					return -1;
				}
			}
		});
	}



	public static void getDateSortASCWithString(
			List<LinkedHashMap<String,Object>> list,final String c1){
		Collections.sort(list, new Comparator<LinkedHashMap<String,Object>>() {
			@Override
			public int compare(LinkedHashMap<String,Object> o1, LinkedHashMap<String,Object> o2) {
				try {
					String xDate1 = (String) o1.get(c1);
					String xDate2 = (String) o2.get(c1);
					if (xDate1 == null || xDate2 == null) {
						return -1;
					}
					Date dt1 = getDate(xDate1);
					Date dt2 = getDate(xDate2);
					if (dt1.getTime() > dt2.getTime()) {
						return 1;
					} else if (dt1.getTime() == dt2.getTime()) {
						int indexnum1 = (Integer)o1.get("indexnum");
						int indexnum2 = (Integer)o2.get("indexnum");
						if(indexnum1>indexnum2){
							return 1;
						}else if(indexnum1==indexnum2){
							return 0;
						}else{
							return -1;
						}
					} else {
						return -1;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return 0;
			}
		});
	}



	/**
	 * @param list 要排序的集合
	 * @param c1   排序条件1
	 * @param c2   排序条件2
	 */
	public static void getDateSortDesc(
			List<Map<String,Object>> list,final String c1){
		Collections.sort(list, new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				XMLGregorianCalendar xDate1 = (XMLGregorianCalendar)o1.get(c1);
				XMLGregorianCalendar xDate2 = (XMLGregorianCalendar)o2.get(c1);
				if(xDate1==null||xDate2==null){
					return -1;
				}
				Date dt1 = DateToXML(xDate1);
				Date dt2 = DateToXML(xDate2);
				if(dt1.getTime()<dt2.getTime()){
					return 1;
				}else if(dt1.getTime()==dt2.getTime()){
					return 0;
				}else {
					return -1;
				}
			}
		});
	}



	/**
	 * list 主要为评价内容进行排序   升序
	 */
	public static List<Map<String,Object>> sortEvalByDate(
			List<Map<String,Object>> list,final String c1){
		Collections.sort(list, new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				String dt1 = (String)o1.get(c1);
				String dt2 = (String)o2.get(c1);
				int date1 = Integer.valueOf(dt1);
				int date2 = Integer.valueOf(dt2);

				if(date1>date2){
					return 1;
				}else if(date1==date2){
					return 0;
				}else {
					return -1;
				}
			}
		});
		return list;
	}

	/**
	 * 2018-12-11 一次性排序
	 * @param list 要排序的集合
	 * @param c1   排序条件1
	 * @param c2   排序条件2
	 */
	public static void sortWithDate(
			List<Map<String,Object>> list,final String c1,final String c2){
		Collections.sort(list, new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Object ocl1 = o1.get(c1);//出勤
                Object ocl2 =  o2.get(c1);//出勤时间
                if(ocl1==null){
                    Object tuiqin1 = o1.get(c2);
                    if(ocl2==null){
                        Object tuiqin2 = o2.get(c2);
                        XMLGregorianCalendar tuiqinTime1 = (XMLGregorianCalendar)tuiqin1;
                        XMLGregorianCalendar tuiqinTime2 = (XMLGregorianCalendar)tuiqin2;
                        Date dt1 = DateToXML(tuiqinTime1);
                        Date dt2 = DateToXML(tuiqinTime2);
                        if(dt1.getTime()<dt2.getTime()){
                            return 1;
                        }else if(dt1.getTime()==dt2.getTime()){
                            return 0;
                        }else {
                            return -1;
                        }
                    }else{
                        XMLGregorianCalendar tuiqinTime1 = (XMLGregorianCalendar)ocl2;
                        XMLGregorianCalendar tuiqinTime2 = (XMLGregorianCalendar)tuiqin1;
                        Date dt1 = DateToXML(tuiqinTime1);
                        Date dt2 = DateToXML(tuiqinTime2);
                        if(dt1.getTime()<dt2.getTime()){
                            return 1;
                        }else if(dt1.getTime()==dt2.getTime()){
                            return 0;
                        }else {
                            return -1;
                        }
                    }
                }else{
                    if(ocl2==null){
                        Object tuiqin1 = o2.get(c2);
                        XMLGregorianCalendar tuiqinTime1 = (XMLGregorianCalendar)ocl1;
                        XMLGregorianCalendar tuiqinTime2 = (XMLGregorianCalendar)tuiqin1;
                        Date dt1 = DateToXML(tuiqinTime1);
                        Date dt2 = DateToXML(tuiqinTime2);
                        if(dt1.getTime()<dt2.getTime()){
                            return 1;
                        }else if(dt1.getTime()==dt2.getTime()){
                            return 0;
                        }else {
                            return -1;
                        }
                    }else{
                        XMLGregorianCalendar tuiqinTime1 = (XMLGregorianCalendar)ocl1;
                        XMLGregorianCalendar tuiqinTime2 = (XMLGregorianCalendar)ocl2;
                        Date dt1 = DateToXML(tuiqinTime1);
                        Date dt2 = DateToXML(tuiqinTime2);
                        if(dt1.getTime()<dt2.getTime()){
                            return 1;
                        }else if(dt1.getTime()==dt2.getTime()){
                            return 0;
                        }else {
                            return -1;
                        }
                    }
                }
			}
		});
	}




	private static String getNewDate(String date,int day) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date parse = sdf.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(parse);
		cal.add(Calendar.DATE, +day);
		Date time = cal.getTime();
		String format = sdf.format(time);
		return format;
	}

	private static Date stringToDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date parse = sdf.parse(date);
		return parse;
	}

	private static String getReduce(String date,int day) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date parse = sdf.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(parse);
		cal.add(Calendar.DATE, -day);
		Date time = cal.getTime();
		String format = sdf.format(time);
		return format;
	}


	private  static Date DateToXML(XMLGregorianCalendar gc) {
		GregorianCalendar ca = gc.toGregorianCalendar();
		return ca.getTime();
	}
	private static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gc;
	}

	/**
	 *
	 * @param pbResource
	 * @param driverID
	 * @param str
	 * @param date
	 * @param beforDateDay 以date日期为准  向前推行天数
	 * @param endDateDay   以date七日为准   向后推行天数
	 * @return
	 * @throws IllegalAccessException
	 * @throws ParseException
	 */
	public static Map<String,Object> getYunAnDate(
			PbResource pbResource, int driverID, String date, int beforDateDay, int endDateDay)
			throws IllegalAccessException, ParseException {
		Map<String,Object> oMap = new HashMap<String,Object>();

		//获取时间范围内的出退勤时
		List<DriverDutyPojo> driverDutyRecordByWorkNoAndTimeStr =
				pbResource.getDriverDutyRecordByWorkNoAndTimeStr(
						driverID,
						getReduce(date,beforDateDay),
						getNewDate(date,endDateDay));

		List<Map<String, Object>> tmp_1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> tmp_2 = new ArrayList<Map<String, Object>>();
		for (DriverDutyPojo ddp : driverDutyRecordByWorkNoAndTimeStr) {
			if(ddp.getOndutytime()==null){
				tmp_1.add(CXFUtils.objectToMap(ddp));
			}else{
				tmp_2.add(CXFUtils.objectToMap(ddp));
			}
		}
		Commons.getDateSort(tmp_1, "offdutytime");//升序排序
		Commons.getDateSort(tmp_2,"ondutytime");//升序排序


		Date paramDate = stringToDate(date);
		//退勤时间
		List<Map<String,Object>> offList =  new ArrayList<Map<String,Object>>();
		for(Map<String,Object> t1:tmp_1){
			XMLGregorianCalendar offdutytime = (XMLGregorianCalendar)t1.get("offdutytime");
			Date offTime = DateToXML(offdutytime);
			if(paramDate.getTime()<offTime.getTime()){
				offList.add(t1);
			}

		}
		Date offDt = DateToXML((XMLGregorianCalendar)offList.get(0).get("offdutytime"));
		Date ondt = null;
		XMLGregorianCalendar onoxmlTime = (XMLGregorianCalendar)tmp_1.get(0).get("ondutytime");
		if(onoxmlTime==null){
			//出勤时间
			List<Map<String,Object>> onList = new ArrayList<Map<String,Object>>();
			for(Map<String,Object> t2:tmp_2){
				XMLGregorianCalendar ondutytime = (XMLGregorianCalendar)t2.get("ondutytime");
				Date onTime = DateToXML(ondutytime);
				if(onTime.getTime()<paramDate.getTime()){
					onList.add(t2);
				}
			}
			if(onList.size()>0){
				ondt = DateToXML((XMLGregorianCalendar)onList.get(0).get("ondutytime"));
			}
		}else {
			ondt = DateToXML(onoxmlTime);
		}
		oMap.put("ondutytime",ondt.getTime());
		oMap.put("offdutytime",offDt.getTime());
		return oMap;
	}

	/**
	 * 获取本地机器ip和主机名称
	 * @return
	 */
	public static Map<String,String> getHostNameIp(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("ip","");
		map.put("hostname","");
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String ip=addr.getHostAddress().toString(); //获取本机ip
        String hostName=addr.getHostName().toString(); //获取本机计算机名称
		map.put("ip",ip);
		map.put("hostname",hostName);
		return map;
	}

	/**
	 * 检测网络是否正常
	 * @param ipPath
	 * @return
	 */
	public static boolean isConnect(String ipPath){
		boolean connect = false;
		Runtime runtime = Runtime.getRuntime();
		Process process;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			String p = "ping "+ipPath.trim();
			process = runtime.exec(p);
			is = process.getInputStream();
			isr = new InputStreamReader(is,"GBK");
			 br = new BufferedReader(isr);
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			is.close();
			isr.close();
			br.close();
			if (null != sb && !sb.toString().equals("")) {
				String logString = "";
				if (sb.toString().indexOf("TTL") > 0) {
					// 网络畅通
					connect = true;
				}else {
					// 网络不畅通
					connect = false;
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}finally {
			try {
				if(is!=null) is.close();
				if(isr!=null) isr.close();
				if(br!=null) br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return connect;
	}

	/**
	 * 获取lkj转储时间
	 * @return
	 */
	public static Map<String,String> getChuTuiQinShiJian(String driverId,String fsjh) {
		long nh = 1000*60*60;
		long nm = 1000*60;
		Map<String,String> map = new HashMap<String,String>();
		map.put("ontime","");
		map.put("currenttime","");
		String msql="select * from yw_checkout_confirm where driver_id='"+driverId+"' order by sys_date desc limit 0,10";
		//上次转储时间
		try {
			String onTime = "";//上次的lkj转储时间
			List<Map<String, Object>> confirmlist = DBUtils.queryAll(msql, null);
			if(confirmlist!=null&&confirmlist.size()>0){//这种情况是在本系统有退勤记录
				out:for(int i = 0;i<confirmlist.size();i++){
					String systuiqinshijain = (String)confirmlist.get(i).get("sys_date");
					if(i==0){
						if(StringUtils.isNotBlank(systuiqinshijain)&&StringUtils.isNotEmpty(systuiqinshijain)){
							onTime = systuiqinshijain;
							break out;
						}
					}
				}
			}else{
				//没有在本系统有退勤记录的时候
				map.put("currenttime",getSystemCurrentDate());
				map.put("ontime",beforeOneHourToNowDate(Commons.getDate(getSystemCurrentDate()),48));
				return map;
			}
			//系统当前时间
			String lkjsql = "select * from yw_data_mid where (sjh='"+driverId+"' or fsjh='"+driverId+"') and shifashijian>'"+onTime+"'";
			       lkjsql+=" and zhongdaoshijian<'"+getSystemCurrentDate()+"'";
			if(StringUtils.isNotBlank(onTime)&&StringUtils.isNotEmpty(onTime)){
				//查询在该时间范围是否有lkj数据
				List<Map<String,Object>> list = DBUtils.queryAll(lkjsql, null);
				if(list==null||list.size()<1){
					//如果没有lkj数据、就展示上一次退勤的数据
					if(confirmlist.size()>=2){
						String first = (String)confirmlist.get(0).get("sys_date");//最新退勤
						String ontimetuiqin = (String)confirmlist.get(1).get("sys_date");//上次退勤
						map.put("currenttime",first);
						map.put("ontime",ontimetuiqin);
					}else if(confirmlist.size()==1){
						String first = (String)confirmlist.get(0).get("sys_date");//最新退勤
						//最新退勤时间向前推48小时
						String ontimetuiqin = beforeOneHourToNowDate(Commons.getDate(first),48);
						map.put("currenttime",first);
						map.put("ontime",ontimetuiqin);
					}
				}else{
					map.put("currenttime",getSystemCurrentDate());
					map.put("ontime",onTime);
				}
			}else{
				//没有在本系统有退勤记录的时候
				map.put("currenttime",getSystemCurrentDate());
				map.put("ontime",beforeOneHourToNowDate(Commons.getDate(getSystemCurrentDate()),48));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 比较地点是否在同一个派班室
	 * @param d1
	 * @param d2
	 * @param pointlist
	 * @return
	 */
	public static boolean getIsSamePlace(String d1, String d2, List<Map<String, Object>> pointlist) {
		boolean bl = false;
		if(d1.trim().equals(d2.trim())) return true;
		String d1PrentCode = "";
		for(Map<String,Object> list:pointlist){
			String pointName = (String)list.get("point_name");
			if(d1.trim().equals(pointName.trim())){
				d1PrentCode = (String)list.get("parent_id");
				break;
			}
		}

		String d2PrentCode = "";
		for(Map<String,Object> list:pointlist){
			String pointName = (String)list.get("point_name");
			if(d2.trim().equals(pointName.trim())){
				d2PrentCode = (String)list.get("parent_id");
				break;
			}
		}

		if(StringUtils.isNotBlank(d1PrentCode)&&StringUtils.isNotEmpty(d1PrentCode)
				&&StringUtils.isNotBlank(d2PrentCode)&&StringUtils.isNotEmpty(d2PrentCode)){
			String[] split = d1PrentCode.split(",");
			String[] split1 = d2PrentCode.split(",");
			out:
			for(String str:split){
				for(String ss:split1){
					if(str.trim().equals(ss.trim())){
						bl = true;
						break out;
					}
				}
			}
		}
		return bl;
	}

	/**
	 * lkj表头信息
	 * @return
	 */
	public static List<Map<String,Object>> getcolums(){
		List<Map<String,Object>> colums = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("field","shijian_time");
		map.put("title","时间");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","event");
		map.put("title","事件");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","mileage");
		map.put("title","里程");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","distance");
		map.put("title","距离");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","signal");
		map.put("title","信号");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","signaler");
		map.put("title","信号机");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","zhagang_zhagang1");
		map.put("title","闸缸/闸缸1");
		map.put("align","center");
		colums.add(map);
		map.put("field","speed");
		map.put("title","速度");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","speed_limit");
		map.put("title","限速");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","condition");
		map.put("title","工况");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","guanya");
		map.put("title","管压");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","zhagang_zhagang1");
		map.put("title","缸压");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","speed_current");
		map.put("title","转速/电流");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","jungang1_jungang");
		map.put("title","均缸1");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","jungang2_zhagang2");
		map.put("title","均缸2");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","xianluhao");
		map.put("title","线路号");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","xianluming");
		map.put("title","线路名");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","xingbie");
		map.put("title","行别");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","chezhan");
		map.put("title","车站");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","chezhanhao");
		map.put("title","车站号");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","mode");
		map.put("title","模式");
		map.put("align","center");
		colums.add(map);
		map = new HashMap<String,Object>();
		map.put("field","qita");
		map.put("title","其它");
		map.put("align","center");
		colums.add(map);
		return colums;
	}
}

