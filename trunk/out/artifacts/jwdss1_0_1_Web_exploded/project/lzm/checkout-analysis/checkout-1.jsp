<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script>
    var path = '<%=path%>';
</script>
<OBJECT class="CLSID:8856F961-340A-11D0-A96B-00CC04FD705A2" id=wb name=wb></OBJECT>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>退勤分析</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link type="text/css" rel="stylesheet" href="<%=path %>/css/jquery/ui.jqgrid.css"  />
    <link type="text/css" rel="stylesheet" href="<%=path %>/css/jquery/jquery-ui-1.10.4.custom.min.css"  />
    <link type="text/css" rel="stylesheet" href="<%=path %>/css/bootstrap-css/bootstrap.min.css"  />
    <link type="text/css" rel="stylesheet" href="<%=path %>/css/bootstrap-css/bootstrap-table.css"  />
    <link type="text/css" rel="stylesheet" href="<%=path %>/js/sweetalert/sweetalert.css"  />
    <link type="text/css" rel="stylesheet" href="<%=path %>/project/lzm/checkout-analysis/css/checkout-1.css"/>
    <style>
        .tbl-cls thead tr th {
            background: #214be4;
        }
    </style>
</head>
<body style="overflow-x:hidden;">
<!-- 页面头信息 -->
<div class="gradual-color-cls"
     style="background: url(<%=path %>/images/checkout-images/center.png) 0 0 no-repeat;background-size:100% 100%;">
    <div class="row">
        <div class="form-line" style="text-align:center;width:100%;height: 60px;line-height: 60px;">
            <label class="driver-titles-cls" id="driverInfo"></label>
            <a class="tuichu-cls typeface" id="homeQuery"
               style="position: absolute;right: 10px;margin-right: 15px;color: #FFF;background:url(<%=path %>/images/exit.png) 0 0 no-repeat;background-size:20px 22px;"
               href="" onclick="homeQuery()" target="checkoutIframe">关闭当前窗口</a>
            <script type="text/javascript">
                function homeQuery() {
                    document.getElementById("homeQuery").href = "javascript:window.opener=null;window.close()";
                }
            </script>
            <a type="button" class="text-btn tuichu-cls print-info"
               onclick="beforePrint();window.print();afterPrint()" data-dismiss="modal"
               style="position: absolute;right: 158px;">打印</a>
            <a id="autoQuery" class="text-btn tuichu-cls auto-query" href="javascript:void(0)"
               onclick="autoQuery()" style="position: absolute;right: 225px;">自助查询</a>
        </div>
    </div>
</div>
<div class="checkining-cls" style="display: none;">
    <div class="" style="text-align: center;vertical-align: middle;height:40%;">正出勤或者休息中...</div>
</div>
<div class="recorder-cls" style="display: none;">
    <div class="" style="text-align: center;vertical-align: middle;height:40%;font-size: 20px;">暂未查询到信息...</div>
</div>
<div class="panel-default chuqin-cls" style="display: none;">
    <!-- 退勤结果 -->
    <div class="box-shadow box-cls">
        <div class="titile-cls">
            <span class=""><img alt="" class="title_bg"
                                src="<%=path %>/images/checkout-images/tui_qin_jie_guo.png"></span>
        </div>
        <div class="form-inline" style="margin-left:100px;">
            <label id="causeDetail"><span class="span-cls-header"></span></label>
        </div>
    </div>
    <!-- 交路图 -->
    <div class="box-shadow box-cls">
        <div class="titile-cls">
            <span class=""><img alt="" class="title_bg"
                                src="<%=path %>/images/checkout-images/lkjshujuwanzhengxin.png"></span>
            <span id="resault-label"></span>
            <img alt="按钮" src="<%=path %>/images/arrow_down.png" id="up_down_btn">
        </div>
        <div style="width:97%;margin:0 auto;">
            <div class="panel" id="graphDivId" style="width:100%;display: none;">
                <canvas id="canvas"></canvas>
            </div>
        </div>
    </div>
    <!-- 表格 -->
    <div class="box-shadow box-cls" style="margin-top:10px;">
        <div class="titile-cls">
                <span class="">
                    <img alt="" class="title_bg" src="<%=path %>/images/checkout-images/xiang_dian_jie_guo.png">
                </span>

            <select id="levelSelect" name="levelSelect" class="form-control"
                    style="display: inline-block;width:150px;float: right;">
                <option value="0">全部</option>
                <option value="1" selected="selected">I类+II类</option>
                <option value="3">III类</option>
            </select>
            <span style="float: right;height: 34px;line-height: 34px;font-size: 20px;margin-right: 5px;">违标级别</span>
        </div>
        <div class="panel" style="width:auto;" id="userinfo-box">
            <table id="userinfo" class="tbl-cls"></table>
        </div>
    </div>
    <!-- 确认提交按钮 -->
    <div style="text-align: center" class="delsubmit-cls">
        <button type="button" style="background: #00B83F" class="btn btn-primary print-cls" id="submitAll">确认提交</button>
    </div>
</div>

<!--其它所有modal  -->
<%@include file="/project/lzm/checkout-analysis/common-modal.jsp" %>
<script type="text/javascript" src="<%=path %>/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/project/lzm/checkout-analysis/js/checkout.js"></script>
<script type="text/javascript" src="<%=path %>/js/tenly.checkout.list.js"></script>

<script type="text/javascript" src="<%=path %>/js/jquery/jquery-ui.js"></script>
<!-- bootstrap -->
<script type="text/javascript" src="<%=path %>/js/bootstrap-group/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/bootstrap-group/bootstrap-table.js"></script>
<script type="text/javascript" src="<%=path %>/js/bootstrap-group/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/commons/cookie.js"></script>
<script type="text/javascript" src="<%=path %>/js/commons/ajax.js"></script>
<!-- 弹出框 -->
<script type="text/javascript" src="<%=path %>/js/sweetalert/sweetalert.min.js"></script>
<script type="text/javascript" src="<%=path %>/project/lzm/checkout-analysis/js/common.js"></script>

</body>
</html>
<script>
    var driverCode = "${driver_code}";
    $(function () {
        window.onbeforeprint = beforePrint;
        window.onafterprint = afterPrint;
        checkining();
    });

    function beforePrint() {
        $(".print-cls").hide();
    }

    function afterPrint() {
        $(".print-cls").show();
    }

    function autoQuery() {//自助查询
        document.getElementById("autoQuery").href =
            path + "/checkout/checkOutBuffetQuery/buffetQuery.do?driver_code=" + driverCode;
    }
</script>
