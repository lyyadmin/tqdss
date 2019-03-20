<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/header_commons_1.0.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>日勤处理</title>
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
		     			<input id="searchIdOrName" name="searchIdOrName" type="text" class="form-control" placeholder="请输入内容   如：司机号 或者名称">
		               <button class="btn btn-primary" id="searchDriver">查询</button>
		               <button class="btn btn-primary" id="oneKeyProcess">一键处理</button>
		     	</div>
		     </div>
     	</div>
      <div class="box-shadow" style="margin-top:10px;width:48%;float: left;height:70%"> 
	     <!-- 表格 -->
	      <div class="panel panel-primary">
	      		<div class="panel-heading">
						<h3 class="panel-title">作业问题</h3>
				</div>
	             <div class="panel-body">
	                <table id="driverGrid"></table>
	             </div>
	      </div>
       </div>
       <div class="box-shadow" style="margin-top:10px;width:48%;float: right;height:70%"> 
	     <!-- 表格 -->
	      <div class="panel panel-primary">
	      		<div class="panel-heading">
						<h3 class="panel-title">文件丢失处理</h3>
				</div>
	             <div class="panel-body">
	                	<table id="fileLoseGrid"></table>
	             </div>
	      </div>
       </div>
    </div>
   
       <!--其它所有modal  -->
  <script type="text/javascript" src="<%=path %>/project/lzm/checkout-analysis/js/checkout-daily-diligence.js"></script>
  <script type="text/javascript" src="<%=path %>/project/lzm/checkout-analysis/js/common.js"></script>
  </body>
</html>
