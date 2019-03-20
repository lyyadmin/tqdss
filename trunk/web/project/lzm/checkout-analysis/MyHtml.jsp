<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/common/header_commons_1.0.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>退勤分析</title>
    <meta http-equiv=Content-Type content=text/html; charset=utf-8>
    <link type="text/css" rel="stylesheet" href="<%=path %>/project/lzm/checkout-analysis/css/MyHtml.css"/>
</head>
<body style="background:url(<%=path %>/images/checkout_bg.jpg) 0 0 no-repeat;background-size:100% 100%;">
<div class="container middle-cls">
    <div class="form-inline">
        <span class="label-cls">工号:</span>
        <select class="form-control" id="driverIdTitile">
            <option value=''>44</option>
        </select>
        <input id="driverId" name="driverId" type="text" class="form-control"
               autofocus="autofocus" onpaste="paste(event)" oninput="getOnInput()"
               style="width:300px;" placeholder="请输入乘务员编号...只能为0-9的5位数字">
        <a id="checkoutId" class="btn btn-primary" onclick="t()" href="" target="checkoutIframe">退勤</a><span
            class="zhanwei">&nbsp;</span>
        <table align="center">
            <tr>
                <td>
                    <button class="btn onKeyToInputCls">1</button>
                </td>
                <td>
                    <button class="btn onKeyToInputCls">2</button>
                </td>
                <td>
                    <button class="btn onKeyToInputCls">3</button>
                </td>
            </tr>
            <tr>
                <td>
                    <button class="btn onKeyToInputCls">4</button>
                </td>
                <td>
                    <button class="btn onKeyToInputCls">5</button>
                </td>
                <td>
                    <button class="btn onKeyToInputCls">6</button>
                </td>
            </tr>
            <tr>
                <td>
                    <button class="btn onKeyToInputCls">7</button>
                </td>
                <td>
                    <button class="btn onKeyToInputCls">8</button>
                </td>
                <td>
                    <button class="btn onKeyToInputCls">9</button>
                </td>
            </tr>
            <tr>
                <td>
                    <button class="btn delAll">清空</button>
                </td>
                <td>
                    <button class="btn onKeyToInputCls">0</button>
                </td>
                <td>
                    <button class="btn onKeyCanel">删除</button>
                </td>
            </tr>
        </table>
    </div>
</div>
<script type="text/javascript" src="<%=path %>/project/lzm/checkout-analysis/js/MyHtml.js"></script>
</body>
</html>
<script type="text/javascript">
    var loginStatusWithInterceptor = "${userloginStatusWithTQ}";
    var jwcode = "${jwcode}";
    var sp = "${_salt}";
    setCookie("__checoutuser",sp,0);
</script>





