package com.tenly.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestGetIP {
    public static void main(String[] args) {
//        String dt = "2019-01-24 00:00:00";
//        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//
//            Date parse = sdf.parse(dt);
//            System.out.println(parse);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//
////        InetAddress addr = null;
////        try {
////            addr = InetAddress.getLocalHost();
////        } catch (UnknownHostException e) {
////            e.printStackTrace();
////        }
////        String ip=addr.getHostAddress().toString(); //获取本机ip
////        String hostName=addr.getHostName().toString(); //获取本机计算机名称
        isConnect("10.128.238.77");
    }

    public static boolean isConnect(String ipPath){
        boolean connect = false;
        Runtime runtime = Runtime.getRuntime();
        Process process;
        try {
            process = runtime.exec("ping "+ipPath);
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"GBK");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            isr.close();
            br.close();
            System.out.println(sb.toString());
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
        }
        return connect;
    }
}
