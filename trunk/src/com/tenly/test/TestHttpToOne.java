package com.tenly.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

public class TestHttpToOne {

    public static void main(String[] args) {
        String str = "{\"strNumber\":\"4450475\",\"strStartTime\":\"2018-12-31 18:13:51.000\",\"strEndTime\":\"2019-01-31 18:13:51.000\"}";
        String url  = "http://10.117.102.200:89/ashx/ZhongJianKu/Process.ashx";
        HttpClient client  = null;
        PostMethod method = null;
        try{
           client = new HttpClient();
           method = new PostMethod(url);
           method.addParameter("Action","GetExam");
           method.addParameter("data",str);
           client.executeMethod(method);
           String st = new String(method.getResponseBody(),"utf-8");
            System.out.println(st);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            method.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
    }

}
