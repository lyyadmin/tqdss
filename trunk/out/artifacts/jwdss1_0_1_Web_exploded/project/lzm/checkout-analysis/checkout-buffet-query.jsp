<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/header_commons_1.0.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>自助查询</title>
	<link type="text/css" rel="stylesheet" href="${contextPath }/css/commonCondition.css" />
    <style type="text/css">
    .div-cls-middle{
    		margin-top:20px;
            text-align:center;
        }
	#driverGrid>tbody>tr:hover{
			background-color:#BCBCBC;
		}
	.box-cls{
			height:80px;
		}
		.height-middle{
			varticle-align:middle;
		}
		.box-body-cls{
			height:60%;
		}
		.panel-body {
			 padding: 0px;
		}
    </style>
  </head>
  <body style="margin:10px;overflow-x: hidden">
    <div class="panel-default">
    	<div class="box-shadow box-cls">
		     <div class="row div-cls-middle">
		     	<div class="form-inline height-middle">
		     			起始日期:<input type="text" id="startDate" class="form-control">&nbsp;&nbsp;&nbsp;&nbsp;
		     			截止日期:<input type="text" id="endDate" class="form-control">&nbsp;&nbsp;&nbsp;&nbsp;
		     			<input id="driverCode" name="driver_code" type="text" class="form-control" placeholder="请输入司机编号 ...只能是八位数字">
		               <button class="btn btn-primary" id="searchDriver">查询</button>
		     	</div>
		     </div>
     	</div>
     <div class="box-shadow box-body-cls" style="margin-top:10px;height:73%">
	     <!-- 表格 -->
	      <div class="panel panel-primary">
	      		<div class="panel-heading">
						<h3 class="panel-title">自助查询表格详细</h3>
				</div>
	             <div class="panel-body">
	                <table id="driverGrid"></table>
	             </div>
	      </div>
      </div>
    </div>
       <!--其它所有modal  -->
  <script type="text/javascript" src="<%=path %>/project/lzm/checkout-analysis/js/checkout-buffet-query.js"></script>
  <script type="text/javascript" src="<%=path %>/project/lzm/checkout-analysis/js/common.js"></script>
  </body>
</html>
