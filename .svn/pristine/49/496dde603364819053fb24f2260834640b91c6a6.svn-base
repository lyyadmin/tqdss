<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script>
    var path = '<%=path%>';
</script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>自助查询</title>
    <link type="text/css" rel="stylesheet" href="<%=path %>/css/jquery/ui.jqgrid.css"  />
    <link type="text/css" rel="stylesheet" href="<%=path %>/css/bootstrap-css/bootstrap.min.css"  />
    <link type="text/css" rel="stylesheet" href="<%=path %>/css/bootstrap-css/bootstrap-table.min.css"  />
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery/jquery.treegrid.min.css"/>
    <link type="text/css" rel="stylesheet" href="<%=path %>/css/bootstrap-css/bootstrap-datetimepicker.min.css"  />
    <link type="text/css" rel="stylesheet" href="<%=path%>/js/test/skin/jedate.css">
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/custom-css/commonCondition.css">
    <%--<link type="text/css" rel="stylesheet" href="<%=path%>/project/lzm/checkout-analysis/css/checkout-1.css">--%>
    <link type="text/css" rel="stylesheet"
          href="<%=path%>/project/lzm/checkout-analysis/css/checkout-buffet-query-one.css"/>
    <style type="text/css">
        .active{
            color: #555;
        }
        a{
            color: #fff;
        }
        .h1, .h2, .h3, h1, h2, h3 {
            margin-top: 5px;
            margin-bottom: 10px;
        }
        #userinfo{
            margin-top: 10px !important;
        }
        #myModal,#findHostoryModal,#tuiQinPageModel,#evaluteModal{
            padding: 5px 5px 5px 5px !important;
        }
        .fixed-table-body {
            overflow-x: auto;
            overflow-y: auto;
            height:70%;
        }
    </style>
</head>
<body>
<ul id="myTab" class="nav nav-tabs" style="background: #214be4;">
    <li class="active">
        <a href="#habits" id="habitAna" data-toggle="tab" style="font-size: 20px;">作业执标分析</a>
    </li>
    <li>
        <a href="#itemPoint" id="tabItemPoint" data-toggle="tab" style="font-size: 20px;">操作项点分析</a>
    </li>
    <li>
        <a href="#checkoutQuery" id="tabCheckOutHostorySearch" data-toggle="tab" style="font-size: 20px;">退勤历史查询</a>
    </li>
</ul>
<div id="myTabContent" class="tab-content">
    <div class="tab-pane fade in active" id="habits">
        <%@include file="/project/lzm/checkout-analysis/self-help-query/tab-working-habits.jsp" %>
    </div>
    <div class="tab-pane fade" id="itemPoint">
        <%@include file="/project/lzm/checkout-analysis/self-help-query/tab-operation-item-point.jsp" %>
    </div>
    <div class="tab-pane fade" id="checkoutQuery">
        <%@include file="/project/lzm/checkout-analysis/self-help-query/tab-checkout-query.jsp" %>
    </div>
</div>
<div style="position: absolute;right: 10px;top:8px;">
    <a href="javascript:void(0)" onclick="returnTuiQinPage()" id="homepages" style="font-size: 20px;">返回退勤页</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="javascript:window.opener=null;window.close()" style="font-size: 20px;">关闭当前窗口</a>
</div>
<script type="text/javascript" src="<%=path %>/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="<%=path %>/js/bootstrap-group/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/bootstrap-group/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/bootstrap-group/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/bootstrap-group/bootstrap-table.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/bootstrap-group/bootstrap-table-treegrid.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery/jquery.treegrid.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/bootstrap-group/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/echarts/echarts.min.js"></script>
<script type="text/javascript" src="<%=path %>/project/lzm/checkout-analysis/js/checkout-buffet-query-one.js"></script>
<script type="text/javascript" src="<%=path %>/js/test/jedate.js"></script>
<script type="text/javascript" src="<%=path %>/js/tenly.checkout.list.js"></script>
<script type="text/javascript" src="<%=path %>/project/lzm/checkout-analysis/js/common.js"></script>
</body>
</html>
<script>
    var driver_code = "${driver_code}";
    var driverCode = driver_code;
    function returnTuiQinPage(){
        document.getElementById("homepages").href=path+"/checkout/checkoutAnalysis/checkout.do?driver_code="+driver_code;
    }
</script>
