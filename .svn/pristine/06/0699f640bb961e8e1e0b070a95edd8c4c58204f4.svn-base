//package com.tenly.test;
//
//import com.google.common.collect.Lists;
//import org.apache.http.Consts;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//import java.math.BigInteger;
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestHttp {
//    /**
//     * 使用 HttpClient 需要以下 5 个步骤：
//     1. 创建 HttpClient 的实例
//     2. 创建某种连接方法的实例，在这里是 httppost<span style="font-family:Arial,Helvetica,sans-serif">,在httppost 的构造函数中传入待连接的地址</span>
//     3. 调用第一步中创建好的实例的 execute 方法来执行第二步中创建好的 method 实例
//     4. 读 response
//     5. 释放连接。无论执行方法是否成功，都必须释放连接
//     */
//
//    public static String conResult, encrypt_app, encrypt_sign;
//    public static String smsUrl = "http://10.117.102.200:89/ashx/ZhongJianKu/Process.ashx?Action=GetBeginEndWork&Data={\"strNumber\":\"4411099\",\"strStartTime\":\"2018-05-31\",\"strEndTime\":\"2019-07-31\"}";
//
//
//    public static void main(String[] args) {
//        try {
////            sendSms();
//            //requestdata();
//            https();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void sendSms() throws Exception {
//        //初始化httpclient
//        HttpClient httpclient = new DefaultHttpClient();
//        //获取httppost
//        HttpPost httppost = new HttpPost(smsUrl.trim());
//        try {
//            //添加参数
//            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//            //设置报文头以及参数的格式
//            //httppost.addHeader("Content-type","application/x-www-form-urlencoded");
//            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
//            //执行post请求
//            HttpResponse response = httpclient.execute(httppost);
//
//            //打印服务器返回的状态
//            System.out.println("服务器返回的状态:"+response.getStatusLine().getStatusCode());
//            // if (response.getStatusLine().getStatusCode() == 200)
//            /**
//             * 读返回数据
//             * */
//            conResult = EntityUtils.toString(response.getEntity());
//            System.out.println(conResult);
//
//            //断开连接
//            httpclient.getConnectionManager().shutdown();
//
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // return conResult;
//    }
//
//
//    public static void requestdata()throws Exception{
//        //初始化httpclient
//        HttpClient httpclient = new DefaultHttpClient();
//        //获取httpget
//        HttpGet httpget = new HttpGet(smsUrl);
//        //添加参数
//        List<NameValuePair> namevaluepair = new ArrayList<NameValuePair>();
////        namevaluepair.add(new BasicNameValuePair("app_key", app_key));
////        namevaluepair.add(new BasicNameValuePair("city", city));
////        namevaluepair.add(new BasicNameValuePair("start_addr", start_addr));
////        namevaluepair.add(new BasicNameValuePair("end_addr", end_addr));
//        // 设置报文头以及格式
//        httpget.addHeader("Content-type","application/x-www-form-urlencoded");
//        httpget.addHeader("Accept-Language", "zh-cn, zh;q=0.75, en-us;q=0.50, en;q=0.25");
//        String str = EntityUtils.toString(new UrlEncodedFormEntity(namevaluepair,"utf-8"));
//        httpget.setURI(new URI(httpget.getURI().toString()  + str));
//        System.out.println("请求地址："+smsUrl+str);
//        // 发送请求
//
//        HttpResponse httpresponse = httpclient.execute(httpget);
//        //读取返回的数据
//        String  body = EntityUtils.toString(httpresponse.getEntity());
//        System.out.println(body);
//        System.out.println("------------------");
//
//        //关闭连接
//        httpclient.getConnectionManager().shutdown();
//    }
//
//    public static void https(){
//        //初始化httpclient
//        HttpClient httpclient = new DefaultHttpClient();
//        //获取httpget
//        HttpGet httpget = new HttpGet(smsUrl);
//        try{
//            //添加参数
//            List<NameValuePair> namevaluepair = new ArrayList<NameValuePair>();
//            namevaluepair.add(new BasicNameValuePair("app_key", ""));//app_key参数
//            namevaluepair.add(new BasicNameValuePair("city", ""));//city
//            namevaluepair.add(new BasicNameValuePair("start_addr", ""));//start_addr
//            namevaluepair.add(new BasicNameValuePair("end_addr", ""));//end_addr
//            // 设置报文头以及格式
//            httpget.addHeader("Content-type","application/x-www-form-urlencoded");
//            httpget.addHeader("Accept-Language", "zh-cn, zh;q=0.75, en-us;q=0.50, en;q=0.25");
//            String str = EntityUtils.toString(new UrlEncodedFormEntity(namevaluepair,"utf-8"));
//            httpget.setURI(new URI(httpget.getURI().toString()  + str));
//            System.out.println("请求地址："+smsUrl+str);
//            // 发送请求
//            HttpResponse httpresponse = httpclient.execute(httpget);
//            //读取返回的数据
//            String  body = EntityUtils.toString(httpresponse.getEntity());
//
//            System.out.println(body);
//            System.out.println("------------------");
//        }catch (Exception e){
//
//        }finally {
//            //关闭连接
//            httpclient.getConnectionManager().shutdown();
//        }
//    }
//
//
//    public void postUrl () {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//        String result = "";
//        try {
//            // 封装请求参数
//            List<NameValuePair> paramsList = Lists.newArrayList();
//            paramsList.add(new BasicNameValuePair("nameZh", "3"));
//            paramsList.add(new BasicNameValuePair("level", "1"));
//            // 转换为键值对
//            String params = EntityUtils.toString(new UrlEncodedFormEntity(paramsList, Consts.UTF_8));
//            // 请求地址
//            String uri = "http://10.95.136.132/api/ecmtest/area/add";
//            uri += "?" + params;
//            // 创建httpPost.
//            HttpPost httpPost = new HttpPost(uri);
//            // 通过请求对象获取响应对象
//            response = httpclient.execute(httpPost);
//            // 判断网络连接状态码是否正常(0--200都数正常)
//            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                result= EntityUtils.toString(response.getEntity(), "utf-8");
//            }
//            System.out.println(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (response != null) {
//                    response.close();
//                }
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
