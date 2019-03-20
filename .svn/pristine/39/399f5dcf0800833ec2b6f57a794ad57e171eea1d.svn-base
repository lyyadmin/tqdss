package com.tenly.common.projecttools;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tenly.common.systools.StringTools;

public class Util {
	public static Integer parseInt(String string) {
		if (StringTools.isEmpty(string) || !string.matches("-?\\d+")) return null;
		return new Integer(string);
	}
	public static String parseString(String string) {
		if (StringTools.isEmpty(string)) return null;
		return string;
	}
	
	public static String getPages(HttpServletRequest request, Integer pageIndex, Integer pageSize, Integer recordCount, String url, String jsFunction) {	
		if (recordCount <= 0) {
			return "";
		}
		Integer pageCount = 0;
		if (recordCount % pageSize == 0) {
			pageCount = recordCount / pageSize;
		} else {
			pageCount = recordCount / pageSize + 1;
		}
		if (pageIndex <= 0) {
			pageIndex = 1;
		}
		if (pageIndex > pageCount) {
			pageIndex = pageCount;		
		}	
		StringBuffer buffer = new StringBuffer();
		String hrefContent = "javascript:void(0);";
		String textFirst = "首页";
		String textPrev = "上一页";
		String textNext = "下一页";
		String textLast = "尾页";
		
		url = request.getRequestURI() + url;
		String url2 = url;
		if (url2.indexOf("?") > -1) {
			url2 = url2 + "&page=1&pagesize="; 
		} else {
			url2 = url2 + "?page=1&pagesize="; 			
		}
		
		if (!"".equals(jsFunction)) {
			//Ajax分页
			String jsSel = "";
			if (pageSize == 15) {
				jsSel = "<select id=\"pageSizeSelId\" onchange=\""+jsFunction+"('1',this.value);\">" +
							"<option value=\"15\" selected=\"selected\">15</option>" +
							"<option value=\"30\">30</option>" +
							"<option value=\"50\">50</option>" +
							"<option value=\"100\">100</option>" +
							"<option value=\"500\">500</option>" +
							"<option value=\"1000\">1000</option>" +
						"</select>";
			} else if (pageSize == 30) {
				jsSel = "<select id=\"pageSizeSelId\" onchange=\""+jsFunction+"('1',this.value);\">" +
							"<option value=\"15\">15</option>" +
							"<option value=\"30\" selected=\"selected\">30</option>" +
							"<option value=\"50\">50</option>" +
							"<option value=\"100\">100</option>" +
							"<option value=\"500\">500</option>" +
							"<option value=\"1000\">1000</option>" +
						"</select>";
			} else if (pageSize == 50) {
				jsSel = "<select id=\"pageSizeSelId\" onchange=\""+jsFunction+"('1',this.value);\">" +
							"<option value=\"15\">15</option>" +
							"<option value=\"30\">30</option>" +
							"<option value=\"50\" selected=\"selected\">50</option>" +
							"<option value=\"100\">100</option>" +
							"<option value=\"500\">500</option>" +
							"<option value=\"1000\">1000</option>" +
						"</select>";
			} else if (pageSize == 100) {
				jsSel = "<select id=\"pageSizeSelId\" onchange=\""+jsFunction+"('1',this.value);\">" +
							"<option value=\"15\">15</option>" +
							"<option value=\"30\">30</option>" +
							"<option value=\"50\">50</option>" +
							"<option value=\"100\" selected=\"selected\">100</option>" +
							"<option value=\"500\">500</option>" +
							"<option value=\"1000\">1000</option>" +
						"</select>";
			} else if (pageSize == 500) {
				jsSel = "<select id=\"pageSizeSelId\" onchange=\""+jsFunction+"('1',this.value);\">" +
							"<option value=\"15\">15</option>" +
							"<option value=\"30\">30</option>" +
							"<option value=\"50\">50</option>" +
							"<option value=\"100\">100</option>" +
							"<option value=\"500\" selected=\"selected\">500</option>" +
							"<option value=\"1000\">1000</option>" +
						"</select>";
			} else if (pageSize == 1000) {
				jsSel = "<select id=\"pageSizeSelId\" onchange=\""+jsFunction+"('1',this.value);\">" +
							"<option value=\"15\">15</option>" +
							"<option value=\"30\">30</option>" +
							"<option value=\"50\">50</option>" +
							"<option value=\"100\">100</option>" +
							"<option value=\"500\">500</option>" +
							"<option value=\"1000\" selected=\"selected\">1000</option>" +
						"</select>";
			}
			buffer.append("<div class=\"green-black\">每页&nbsp;<span style=\"font-weight: bold;\">" + jsSel + "</span>&nbsp;条记录&nbsp;");
		}else {
			//非Ajax分页
			String jsHref1 = "";
			String jsHref2 = "";
			String jsHref3 = "";
			String jsHref4 = "";
			String jsHref5 = "";
			String jsHref6 = "";
			if (pageSize == 15) {
				jsHref1 = "<span style=\"color:#FF0000;\">15</span>&nbsp;";
				jsHref2 = "<a href=\"" + url2 + "30\">30</a>&nbsp;";
				jsHref3 = "<a href=\"" + url2 + "50\">50</a>&nbsp;";
				jsHref4 = "<a href=\"" + url2 + "100\">100</a>&nbsp;";
				jsHref5 = "<a href=\"" + url2 + "500\">500</a>&nbsp;";
				jsHref6 = "<a href=\"" + url2 + "1000\">1000</a>&nbsp;";
			} else if (pageSize == 30) {
				jsHref1 = "<a href=\"" + url2 + "15\">15</a>&nbsp;";
				jsHref2 = "<span style=\"color:#FF0000;\">30</span>&nbsp;";
				jsHref3 = "<a href=\"" + url2 + "50\">50</a>&nbsp;";
				jsHref4 = "<a href=\"" + url2 + "100\">100</a>&nbsp;";
				jsHref5 = "<a href=\"" + url2 + "500\">500</a>&nbsp;";
				jsHref6 = "<a href=\"" + url2 + "1000\">1000</a>&nbsp;";
			} else if (pageSize == 50) {
				jsHref1 = "<a href=\"" + url2 + "15\">15</a>&nbsp;";
				jsHref2 = "<a href=\"" + url2 + "30\">30</a>&nbsp;";
				jsHref3 = "<span style=\"color:#FF0000;\">50</span>&nbsp;";
				jsHref4 = "<a href=\"" + url2 + "100\">100</a>&nbsp;";
				jsHref5 = "<a href=\"" + url2 + "500\">500</a>&nbsp;";
				jsHref6 = "<a href=\"" + url2 + "1000\">1000</a>&nbsp;";
			} else if (pageSize == 100) {
				jsHref1 = "<a href=\"" + url2 + "15\">15</a>&nbsp;";
				jsHref2 = "<a href=\"" + url2 + "30\">30</a>&nbsp;";
				jsHref3 = "<a href=\"" + url2 + "50\">50</a>&nbsp;";
				jsHref4 = "<span style=\"color:#FF0000;\">100</span>&nbsp;";
				jsHref5 = "<a href=\"" + url2 + "500\">500</a>&nbsp;";
				jsHref6 = "<a href=\"" + url2 + "1000\">1000</a>&nbsp;";
			} else if (pageSize == 500) {
				jsHref1 = "<a href=\"" + url2 + "15\">15</a>&nbsp;";
				jsHref2 = "<a href=\"" + url2 + "30\">30</a>&nbsp;";
				jsHref3 = "<a href=\"" + url2 + "50\">50</a>&nbsp;";
				jsHref4 = "<a href=\"" + url2 + "100\">100</a>&nbsp;";
				jsHref5 = "<span style=\"color:#FF0000;\">500</span>&nbsp;";
				jsHref6 = "<a href=\"" + url2 + "1000\">1000</a>&nbsp;";
			} else if (pageSize == 1000) {
				jsHref1 = "<a href=\"" + url2 + "15\">15</a>&nbsp;";
				jsHref2 = "<a href=\"" + url2 + "30\">30</a>&nbsp;";
				jsHref3 = "<a href=\"" + url2 + "50\">50</a>&nbsp;";
				jsHref4 = "<a href=\"" + url2 + "100\">100</a>&nbsp;";
				jsHref5 = "<a href=\"" + url2 + "500\">500</a>&nbsp;";
				jsHref6 = "<span style=\"color:#FF0000;\">1000</span>&nbsp;";
			}
			buffer.append("每页显示&nbsp;<span style=\"font-weight: bold;\">" + jsHref1 + jsHref2 + jsHref3 + jsHref4 + jsHref5 + jsHref6 + "</span>条记录");
		}
		
		
		if (url.indexOf("?") > -1) {
			url = url + "&pagesize=" + pageSize + "&page="; 
		} else {
			url = url + "?pagesize=" + pageSize + "&page="; 			
		}
		
		buffer.append("<span style=\"padding:0 5px;\">共<b>" + recordCount + "</b>条记录," +
						"当前<b>" + ((pageIndex-1)*pageSize+1) + "</b>-<b>" + ((pageIndex*pageSize) > recordCount ? recordCount : (pageIndex*pageSize))+ "</b>条</span>" );
		
		if (pageIndex != 1) {
			//不是第一页的显示上一页标签
			if (!"".equals(jsFunction)) {
				buffer.append("<a href=\"" + hrefContent + "\" onclick=\"" + jsFunction + "('1'," + pageSize +");\">" + textFirst + "</a>&nbsp;");
				buffer.append("<a href=\"" + hrefContent + "\" onclick=\"" + jsFunction + "('" + (pageIndex - 1) + "'," + pageSize +");\">" + textPrev + "</a>&nbsp;");
			} else {
				buffer.append("<a href=\"" + url + "1\">" + textFirst + "</a>&nbsp;");
				buffer.append("<a href=\"" + url + (pageIndex - 1) + "\">" + textPrev + "</a>&nbsp;");
			}
		}
		
		Integer pageStart = 0;
		Integer pageEnd = 0;
		if (pageIndex - 4 <= 1) {
			pageStart = 1;
			pageEnd = 9 > pageCount ? pageCount : 9;
		} else if (pageIndex + 4 > pageCount) {
			pageStart = pageCount - 9 > 0 ? pageCount - 9 : 1;
			pageEnd = pageCount;			
		} else {
			pageStart = pageIndex - 4;
			pageEnd = pageIndex + 4;
		}
		
		if (pageStart > 1) {
			buffer.append("...&nbsp;");
		}
		
		for (int i = pageStart; i <= pageEnd; i++) {
			if (i == pageIndex) {
				buffer.append("<span class=\"current\">" + i + "</span>&nbsp;");
			} else {
				if (!"".equals(jsFunction)) {
					buffer.append("<a href=\"" + hrefContent + "\" onclick=\"" + jsFunction + "('" + i + "'," + pageSize +");\">" + i + "</a>&nbsp;");
				} else {
					buffer.append("<a href=\"" + url + i + "\">" + i + "</a>&nbsp;");
				}
			}
		}
		
		if (pageEnd < pageCount ) {
			buffer.append("...&nbsp;");
		}
		
		if (pageIndex < pageCount) {
			//不是最后一页的显示下一页标签
			if (!"".equals(jsFunction)) {
				buffer.append("<a href=\"" + hrefContent + "\" onclick=\"" + jsFunction + "('" + (pageIndex + 1) + "'," + pageSize +");\">" + textNext + "</a>&nbsp;");
				buffer.append("<a href=\"" + hrefContent + "\" onclick=\"" + jsFunction + "('" + pageCount + "'," + pageSize +");\">" + textLast + "</a>&nbsp;</div>");
			} else {
				buffer.append("<a href=\"" + url + (pageIndex + 1) + "\">" + textNext + "</a>&nbsp;");
				buffer.append("<a href=\"" + url + pageCount + "\">" + textLast + "</a>&nbsp;</div>");
			}
		}
		
		return buffer.toString();
	}
	
	public static Date parseDate(String string, boolean end) {
		if (StringTools.isEmpty(string)) return null;
		try {
			if (string.matches("^\\d{4}/\\d{1,2}/\\d{1,2}.*$")) {
				if (string.matches("^\\d{4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}$")) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
					return sdf.parse(string);
				}
				else if (string.matches("^\\d{4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					return sdf.parse(string);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				if(end) {
					return new Date(sdf.parse(string).getTime() + 86399999L);
				}
				return sdf.parse(string);
			}
			if (string.matches("^\\d{4}-\\d{1,2}-\\d{1,2}.*$")) {
				if (string.matches("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}$")) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					return sdf.parse(string);
				}
				else if (string.matches("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					return sdf.parse(string);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if(end) {
					return new Date(sdf.parse(string).getTime() + 86399999L);
				}
				return sdf.parse(string);
			}
			if (string.matches("^\\d{4}\\.\\d{1,2}\\.\\d{1,2}.*$")) {
				if (string.matches("^\\d{4}\\.\\d{1,2}\\.\\d{1,2} \\d{1,2}:\\d{1,2}$")) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
					return sdf.parse(string);
				}
				else if (string.matches("^\\d{4}\\.\\d{1,2}\\.\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
					return sdf.parse(string);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
				if(end) {
					return new Date(sdf.parse(string).getTime() + 86399999L);
				}
				return sdf.parse(string);
			}
			if (string.matches("^\\d{1,2}/\\d{1,2}/\\d{4}.*$")) {
				if (string.matches("^\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{1,2}$")) {
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
					return sdf.parse(string);
				}
				else if (string.matches("^\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					return sdf.parse(string);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				if(end) {
					return new Date(sdf.parse(string).getTime() + 86399999L);
				}
				return sdf.parse(string);
			}
			if (string.matches("^\\d{1,2}-\\d{1,2}-\\d{4}.*$")) {
				if (string.matches("^\\d{1,2}-\\d{1,2}-\\d{4} \\d{1,2}:\\d{1,2}$")) {
					SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
					return sdf.parse(string);
				}
				else if (string.matches("^\\d{1,2}-\\d{1,2}-\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
					SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
					return sdf.parse(string);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
				if(end) {
					return new Date(sdf.parse(string).getTime() + 86399999L);
				}
				return sdf.parse(string);
			}
			if (string.matches("^\\d{1,2}\\.\\d{1,2}\\.\\d{4}.*$")) {
				if (string.matches("^\\d{1,2}\\.\\d{1,2}\\.\\d{4} \\d{1,2}:\\d{1,2}$")) {
					SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy HH:mm");
					return sdf.parse(string);
				}
				else if (string.matches("^\\d{1,2}\\.\\d{1,2}\\.\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
					SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy HH:mm:ss");
					return sdf.parse(string);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
				if(end) {
					return new Date(sdf.parse(string).getTime() + 86399999L);
				}
				return sdf.parse(string);
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	/**
	 * 返回不同浏览器不同格式的导入excel头信息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getHeaderType(HttpServletRequest request,String name) throws UnsupportedEncodingException {
		String HeaderType = "";
		String userAgent = request.getHeader("User-Agent");
		if(userAgent.indexOf("MSIE") != -1){
			//it is ie
			HeaderType = "attachment; filename=" + java.net.URLEncoder.encode(name, "UTF-8").replace("+","%20") + ".xls";
		}else{
			HeaderType = "attachment; filename*=UTF-8''" + java.net.URLEncoder.encode(name, "UTF-8").replace("+","%20") + ".xls";
		}
		return HeaderType;
	}
	
	 /** 
     * 获取SimpleDateFormat 
     * @param parttern 日期格式 
     * @return SimpleDateFormat对象 
     * @throws RuntimeException 异常：非法日期格式 
     */  
    private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {  
        return new SimpleDateFormat(parttern);  
    }
    /** 
     * 将日期字符串转化为日期。失败返回null。 
     * @param date 日期字符串 
     * @param parttern 日期格式 
     * @return 日期 
     */  
    public static Date StringToDate(String date, String parttern) {  
        Date myDate = null;  
        if (date != null) {  
            try {  
                myDate = getDateFormat(parttern).parse(date);  
            } catch (Exception e) {  
            }  
        }  
        return myDate;  
    }  
    
    /** 
     * 将日期转化为日期字符串。失败返回null。 
     * @param date 日期 
     * @param parttern 日期格式 
     * @return 日期字符串 
     */  
    public static String DateToString(Date date, String parttern) {  
        String dateString = null;  
        if (date != null) {  
            try {  
                dateString = getDateFormat(parttern).format(date);  
            } catch (Exception e) {  
            }  
        }  
        return dateString;  
    }
    
    /**
	 * 日期加天数
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getNewDate(String oldDate,String day) throws UnsupportedEncodingException {
		
		String backTime = "";
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = format.parse(oldDate);
			Calendar ca = Calendar.getInstance();
			ca.setTime(date);
			ca.add(Calendar.DATE, Integer.valueOf(day));
			d = ca.getTime();
			backTime = format.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return backTime;
	}
	
	/**
	 * 对于list进行排序
	 * @param dataList 排序集合
	 * @param sortKey 排序字段
	 * @param sortType 排序类型 desc降序 asc升序排序
	 */
	public static void sortMapList(List<LinkedHashMap<String, Object>> dataList,final String sortKey,final String sortType){
		Collections.sort(dataList,new Comparator<LinkedHashMap<String, Object>>(){
			@Override
			public int compare(LinkedHashMap<String, Object> o1,
					LinkedHashMap<String, Object> o2) {
				String s1 = "";
				String s2 = "";
				try {
					s1 = o1.get(sortKey).toString();
					s2 = o2.get(sortKey).toString();
					Double o11 = Double.valueOf(s1);
					Double o22 = Double.valueOf(s2);
					if(sortType.equals("desc"))
						return o22.compareTo(o11);
					else 
						return o11.compareTo(o22);
				} catch (Exception e) {
					if(sortType.equals("desc"))
						return o2.get(sortKey).toString().compareTo(s1);
					else 
						return o1.get(sortKey).toString().compareTo(s2);
				}
			}
		});
	}
	
	/**
	 * 对图形结果集进行排序,并限制输出前几位
	 * @param dataList 数据集合
	 * @param sortKey 排序key
	 * @param sortType 排序类型 desc降序 asc升序排序
	 * @param topNum 输出前几位数字
	 */
	public static List<LinkedHashMap<String,Object>> sortDataListLimit(List<LinkedHashMap<String, Object>> dataList,final String sortKey,final String sortType,int topNum){
		List<LinkedHashMap<String,Object>> newList = new ArrayList<LinkedHashMap<String,Object>>();
		sortMapList(dataList, sortKey, sortType);
		for(int i=0;i<topNum;i++){
			if (i<dataList.size()) {
				LinkedHashMap<String,Object> map = dataList.get(i);
				newList.add(map);
			}
		}
		return newList;
	}
	
	/**
	 * 拼成jqGrid需要的数据
	 * @param data 原始数据
	 * @param everyPageRecord  每页显示的数据条数
	 * @param noneColumns  数据中不需要显示的字段名
	 * @return
	 */
	public static Map<String, Object> makeDataForJqGrid(
			List<Map<String,Object>> data, int everyPageRecord, List<String> noneColumns) {
		Map<String, Object> newCommentInfos = new HashMap<String, Object>();
		int records = data.size();
		int page = 1;
		int total = records%everyPageRecord == 0?records/everyPageRecord:records/everyPageRecord+1;
		newCommentInfos.put("page", String.valueOf(page));
		newCommentInfos.put("total", String.valueOf(total));
		newCommentInfos.put("records", String.valueOf(records));
		
		List<HashMap<String, Object>> allRows = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < data.size(); i++) {
			HashMap<String, Object> rows = new HashMap<String, Object>();
			rows.put("id", i);
			Map<String, Object> map = data.get(i);
			List<Object> vals = new ArrayList<Object>();
			for (String key : map.keySet()) {
				if (noneColumns != null && !noneColumns.isEmpty()) {
					if (!noneColumns.contains(key)) {
						vals.add(map.get(key));
					}
				} else {
					vals.add(map.get(key));
				}
			}
			rows.put("cell", vals.toArray());
			allRows.add(rows);
		}
		newCommentInfos.put("rows", allRows.toArray());
		return newCommentInfos;
	}
	public static String getSystemCurrentDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sdf.format(System.currentTimeMillis());
		return format;
	}

	public static List<LinkedHashMap<String, Object>> getYuanShiShuDataForLKJ(
			String date,String kaichetime, String train_no, String region) {
		// 一级分区为开车日期
		// 二级分区为车次加开车时间
		List<String> list1 = new ArrayList<String>();
		if (region.contains(";")) {
			String[] arr = region.split(";");
			for (int i = 0; i < arr.length; i++) {
				if (!arr[i].trim().equals("")) {
					list1.add(arr[i]);
				}
			}
		} else {
			list1.add(region);
		}

		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>();
		try {
			con = HVUtils.getConnection();
			stmt = con.createStatement();
			String sql = "select * from yw_data_detail where"
					+ " driverdate='" + date + "' and traincheci = '" + train_no + "' and drivertime='"+kaichetime+"'";
			result = stmt.executeQuery(sql);
			ResultSetMetaData metaData = result.getMetaData();
			String[] columnNames = new String[metaData.getColumnCount()];
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				String columnName = metaData.getColumnName(i);
				if(columnName.contains("\\.")){
					columnNames[i-1] = columnName.split("\\.")[1];
				}else{
					columnNames[i-1] = columnName;
				}
			}
			while (result.next()) {
				for (int i = 0; i < list1.size(); i++) {
					int startIndex = Integer.parseInt(list1.get(i).split("-")[0].trim()) + 1;
					int endIndex = Integer.parseInt(list1.get(i).split("-")[1].trim()) + 1;
					if (result.getInt("indexnum") >= startIndex && result.getInt("indexnum") <= endIndex) {
						LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
						for (int j = 1; j <= columnNames.length; j++) {
							map.put(columnNames[j - 1], result.getObject(j));
						}
						list.add(map);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}


	Commons.getDateSortASCWithString(list,"shijian_time");
		return list;

	}

}
