<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/header_commons_1.0.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>退勤分析</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
<!-- 弹出框 -->
 <script type="text/javascript" src="<%=path %>/js/sweetalert/sweetalert.min.js"></script>
 <script type="text/javascript" src="<%=path %>/js/ztree/jquery.ztree.all-3.5.js"></script>
 <link type="text/css" rel="stylesheet" href="<%=path %>/js/sweetalert/sweetalert.css"  />
 <link type="text/css" rel="stylesheet" href="<%=path %>/js/ztree/zTreeStyle.css"  />
 
    <style type="text/css">
		.panel-body {
			padding: 0px;
		}
		.args-cls{
			margin-top:10px;
		}
		.position-cls{
			text-align:left;
			padding-top:5px;
			padding-right:5px;
		}
		.add-btn{
			background:#4BAD01;
			padding:4px 0px;
		}
		.add-btn:hover{
			background:#4BAD01;
			padding:4px 0px;
		}
		.del-btn:hover{
			background:#EB2E14;
			padding:4px 0px;
		}
		.update-btn:hover{
			background:#EA9518;
			padding:4px 0px;
		}
		.add-btn:focus{
			background:#4BAD01;
			padding:4px 0px;
		}
		.update-btn{
			background:#EA9518;
			padding:4px 0px;
		}
		.del-btn{
			background:#EB2E14;
			padding:4px 0px;
		}
		.update-btn:focus{
			background:#EA9518;
			padding:4px 0px;
		}
		.del-btn:focus{
			background:#EB2E14;
			padding:4px 0px;
		}
		.panel-primary>.panel-heading {
			color: #fff;
			background-color: #006AE6;
			border-color: #006AE6;
		}
		.height-cls{
			height:48%;
		}
		#itemGrid>tbody>tr:hover{
			background-color:#BCBCBC;
		}
		#listGrid>tbody>tr:hover{
			background-color:#BCBCBC;
		}
		
	</style>
  </head>
  <body style="margin:10px;">
  	<!-- 文件缺失参数设置 -->
    <div class="panel search_container" style="width:99.5%">
    	<div class="position-cls">
			<button type="button" style="margin-left:0px;width:74px;"
				class="btn btn-primary add-btn" onclick="addCauseModal()">新增</button>
			<button type="button" style="margin-left:50px;width:74px;"
				class="btn btn-primary update-btn" onclick="updateCause()">修改</button>
			<button type="button" style="margin-left:50px;width:74px;"
				class="btn btn-primary del-btn" onclick="delCauseRow();">删除</button>
		</div>
		<div class="panel panel-primary args-cls">
			<div class="panel-heading">
				<h3 class="panel-title">文件缺失参数设置</h3>
			</div>
			<div class="panel-body">
				<table id = "listGrid"></table>
	    		<div id = "page"></div>
			</div>
		</div>
    </div>
  <!--项点问题  -->
    	<div class="panel-body">
   			<div class="position-cls">
				<button type="button" style="margin-left:0px;width:74px;"
					class="btn btn-primary add-btn" onclick="saveYjCs()">保存</button>
			</div>
	    	<div style="float: left;width:48%">
	    		<div class="panel panel-primary args-cls">
					<div class="panel-heading">
						<h3 class="panel-title">设置项点</h3>
					</div>
					<div class="panel-body height-cls" style="overflow: auto">
						<table class="table table-striped">
							<tr style="height:40px;">
								<td align="right" style="width: 20%;">级别设置&nbsp;&nbsp;</td>
								<td align="left">&nbsp;&nbsp;
									<input type="radio" id="yj1" name="yc" value="1" onchange="changYjLevel(this);" /><label for="yj1">A级</label>&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" id="yj2" name="yc" value="2" onchange="changYjLevel(this);" ><label for="yj2">B级</label>&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" id="yj3" name="yc" value="3" onchange="changYjLevel(this);" ><label for="yj3">C级</label>
								</td>
							</tr>
							<tr>
								<td align="right" style="width: 20%;">处理措施&nbsp;&nbsp;</td>
								<td>
									<select id = "selMeasure" name="measure">
										<option value="1">正常退勤</option>
										<option value="2">不能退勤</option>
										<option value="3">请写分析报告</option>
									</select>
								</td>
							</tr>
							<tr style="height:40px;">
								<td align="right">作业项点&nbsp;&nbsp;</td>
								<td align="left">&nbsp;&nbsp;
									<input type="radio" id="zycj" name="point" value="1" onchange="changPoint(this);"><label for="zycj">作业场景</label>&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" id="zyxm" name="point" value="2" onchange="changPoint(this);"><label for="zyxm">作业项目</label>&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" id="zywt" name="point" value="3" onchange="changPoint(this);"><label for="zywt">作业问题</label>
								</td>
							</tr>
							<tr>
								<td align="right">项点选择&nbsp;&nbsp;</td>
								<td>
									<div class="panel-body redius-cls" style="padding: 0px;overflow-y: auto;">
										<ul id="treeDemo" class="ztree"><li>加载中...</li><li><img src="<%=path %>/images/loading.gif" /></li></ul>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
	    	</div>
	    	<div style="float: right;width:48%;">
		    	<div class="panel panel-primary args-cls" >
					<div class="panel-heading">
						<h3 class="panel-title">项点参数设置</h3>
					</div>
					<div class="panel-body height-cls">
						<table id = "itemGrid"></table>
					</div>
				</div>
			</div>
	    </div>
	    
    <!-- 规则参数设置 -->
<!--     <div class="panel"> -->
<!--     	<div class="position-cls"> -->
<!-- 			<button type="button" style="margin-left:0px;width:74px;" -->
<!-- 				class="btn btn-primary add-btn" onclick="addRowModal(this)">新增</button> -->
<!-- 			<button type="button" style="margin-left:50px;width:74px;" -->
<!-- 				class="btn btn-primary update-btn" onclick="updateCondtion()">修改</button> -->
<!-- 			<button type="button" style="margin-left:50px;width:74px;" -->
<!-- 				class="btn btn-primary del-btn" onclick="delRow();">删除</button> -->
<!-- 		</div> -->
<!-- 		<div class="panel panel-primary args-cls"> -->
<!-- 			<div class="panel-heading"> -->
<!-- 				<h3 class="panel-title">规则参数设置</h3> -->
<!-- 			</div> -->
<!-- 			<div class="panel-body"> -->
<!-- 				<table id = "ruleGrid" class="table-striped" data-toggle="table"></table> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!--     </div> -->

    <!-- 模态框 -->
    <%@include file="/project/lzm/checkout-analysis/addCondition.jsp" %>
    <%@include file="/project/lzm/checkout-analysis/addCause.jsp" %>
    <%@include file="/project/lzm/checkout-analysis/updateCause.jsp" %>
    <%@include file="/project/lzm/checkout-analysis/updateCondition.jsp" %>
    <%@include file="/project/lzm/checkout-analysis/common-modal.jsp" %>
  <script type="text/javascript" src="<%=path %>/js/jquery-ui.js"></script>
  <script type="text/javascript" src="<%=path %>/project/lzm/checkout-analysis/js/common.js"></script>
  <script type="text/javascript" src="<%=path %>/project/lzm/checkout-analysis/js/checkout-rule.js"></script>
  </body>
</html>
