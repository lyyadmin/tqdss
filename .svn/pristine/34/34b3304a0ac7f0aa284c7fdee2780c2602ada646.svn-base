package com.tenly;

import java.sql.*;

public class TestHiveJDBC {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";//jdbc驱动路径
    private static String url = "jdbc:hive2://master:10000/default";//hive库地址+库名
    private static String user = "root";//用户名
    private static String password = "123456";//密码
    private static String sql = "";
    private static ResultSet res;

    public static void main(String[] args)throws Exception {
        /*
        indexnum            	bigint
        event               	string
        shijian_time        	string
        mileage             	double
        distance            	double
        signal              	string
        signaler            	string
        speed               	double
        speed_limit         	double
        condition           	string
        guanya              	double
        zhagang_zhagang1    	double
        speed_current       	double
        jungang1_jungang    	double
        jungang2_zhagang2   	double
        benbu               	string
        train_type          	string
        train_num           	string
        station_name        	string
        sjh                 	string
        distance_error      	string
        qita                	string
        buchongshuoming     	string
        col1                	string
        col2                	string
        col3                	string
        xianluhao           	string
        xingbie             	string
        chezhan             	string
        driverdate          	string
        traincheci_drivertime	string
         */
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConn();
            System.out.println(conn);
            stmt = conn.createStatement();
            String tableName="yw_data_detail";//hive表名
            sql = "select * from " + tableName+" limit 1,10";
            System.out.println("Running:" + sql);
            res = stmt.executeQuery(sql);
            System.out.println("执行 select * query 运行结果:");
            while (res.next()) {
                System.out.println("\t" + res.getString(2));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
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

    protected static Connection getConn() throws ClassNotFoundException,
            SQLException {
        Class.forName(driverName);
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
}
