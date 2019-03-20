package com.tenly.common.projecttools;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
	/*
	 * 获取驱动
	 */
	private static String driverClassName = "com.mysql.jdbc.Driver";
	/*
	 * 获取URL
	 */
	private static String URL = "jdbc:mysql://10.128.238.77:3306/jwdss";
	/*
	 * 获取用户名
	 */
	private static String username = "root";
	/*
	 * 获取密码
	 */
	private static String password = "Passw0rd";

	/**
	 * 载入数据库驱动类
	 */
	static {
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

    public static Connection getConnection() throws SQLException {
	     return DriverManager.getConnection(URL, username, password);
	 }


	 public static void close(Connection conn, Statement stat, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 close(conn, stat);
	}

	// 释放资源
	public static void close(Connection conn, Statement stat) {
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 查询方法
	 * @param sql
	 * @param conditionMap
	 * @return
	 */
	public static List<Map<String, Object>> queryAll(String sql, Map<Integer, Object> conditionMap){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			if(conditionMap!=null&&conditionMap.size()!=0){
				int paramNum = conditionMap.size();
				for(int i=1;i<=paramNum;i++){
					Object paramValue = conditionMap.get(i);
					if("java.lang.Integer".equalsIgnoreCase(paramValue.getClass().getName())){
						pstmt.setInt(i, Integer.parseInt(paramValue.toString()));
					}else if("java.lang.String".equalsIgnoreCase(paramValue.getClass().getName())){
						pstmt.setString(i, paramValue.toString());
					}
				}
			}
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnNum = rsmd.getColumnCount();
			while(rs.next()){
				Map<String, Object> dataMap = new HashMap<String, Object>(0);
				for(int i=1;i<=columnNum;i++){
					dataMap.put(rsmd.getColumnName(i), rs.getObject(i));
				}
				resultList.add(dataMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn,pstmt,rs);
		}
		return resultList;
	}
}