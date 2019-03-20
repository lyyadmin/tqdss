package com.tenly.common.projecttools;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ODBUtils {
    private static String DRIVER=PlatFormPar.getPropertiesVal("jdbc.oracle_shanghai.driver");
    private static String URL=PlatFormPar.getPropertiesVal("jdbc.oracle_shanghai.url");
    private static String USERNAME=PlatFormPar.getPropertiesVal("jdbc.oracle_shanghai.username");
    private static String PASSWORD=PlatFormPar.getPropertiesVal("jdbc.oracle_shanghai.password");
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static Connection getConnection(){
        Connection conn = null;
        try {
             conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    public static List<Map<String, Object>> doQuery(String sql, Object...objs){
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Map<String, Object>> results = null;
        try {
            con = getConnection();
            st = con.prepareStatement(sql); // 3.sql执行工具
            setParams(st, objs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            rs = st.executeQuery(); // 4.执行sql取到返回数据白结果集
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSetMetaData rsmd = rs.getMetaData(); // 元数据; 对象取取到的结果集数据的描述
            int cloumCount = rsmd.getColumnCount();
            results = new ArrayList<Map<String, Object>>();
            while (rs.next()) { // 判断结果集是否还有数据 (数据是一条记录的方式取出)
                Map<String, Object> record = new HashMap<String, Object>();
                for (int i = 1; i <= cloumCount; i++) {
                    record.put(rsmd.getColumnName(i).toLowerCase(), rs.getObject(i));
                }
                results.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, st, rs);
        }
        return results;
    }

    private static void setParams(PreparedStatement st, Object... objs) {
        // 判断是否有参数
        if (objs == null || objs.length == 0) {
            return;
        }
        int flag = 0;
        try {
            for (int i = 0; i < objs.length; i++) {
                flag = i + 1;
                String paramType = objs[i].getClass().getName(); // 获得参数的类型
                if (Integer.class.getName().equals(paramType)) { // 判断是否是int类型
                    st.setInt(i + 1, (int) objs[i]);
                } else if (Double.class.getName().equals(paramType)) { // 判断是否是dobule类型
                    st.setDouble(i + 1, (double) objs[i]);
                } else if (String.class.getName().equals(paramType)) { // 判断是否是string类型
                    st.setString(i + 1, (String) objs[i]);
                } else {
                    st.setObject(i + 1, objs[i]);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection con, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
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

}
