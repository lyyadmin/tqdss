package com.tenly.common.projecttools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HVUtils {
	private static String HIVE_DRIVER = PlatFormPar.getPropertiesVal("HIVE_DRIVER");
	private static String HIVE_URL = PlatFormPar.getPropertiesVal("HIVE_URL");
	private static String HIVE_USER = PlatFormPar.getPropertiesVal("HIVE_USER");
	private static String HIVE_PASS = PlatFormPar.getPropertiesVal("HIVE_PASS");
	static {
		try {
			Class.forName(HIVE_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException{
		Connection conn = DriverManager.getConnection(HIVE_URL, HIVE_USER, HIVE_PASS);
		return conn;
	}
	public static void getHiveClose(Connection conn,Statement stt,ResultSet rs){
		try {
			if (rs != null) {
				rs.close();
				rs = null;
            }
			if (stt != null) {
            	stt.close();
            	stt = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
