package com.tenly.common.projecttools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PlatFormPar {
    public static Properties jdbcVar = new Properties();
    /**
     * 加载配置文件到内存
     */
    private static void readJdbcProperties(){
        try {
            jdbcVar.load(PlatFormPar.class.getResourceAsStream("/jdbc.properties"));
        }catch (FileNotFoundException e1){
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取配置文件的key的值
     * @param properties
     * @return
     */
    public static String getPropertiesVal(String properties){
        if(jdbcVar==null||jdbcVar.size()==0) readJdbcProperties();
        return jdbcVar.getProperty(properties);
    }
}
