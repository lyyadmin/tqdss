<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/hqh_header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'home.jsp' starting page</title>
<script type="text/javascript"
	src="<%=path%>/project/lzm/home/js/ans.js"></script>
<style type="text/css">
/* .content-cls { */
/* 	height: 25px; */
/* 	width: 99.5%; */
/* 	margin: 3px; */
/* 	text-align: center; */
/* } */

/* .average-cls { */
/* 	margin-top: 50px; */
/* 	margin: 5px 10px 10px 10px; */
/* 	padding: 5px; */
/* } */

/* .margin-top-cls {
	margin-top: 10px;
} */

    /* text-align:center;
    height:30px;
    line-height:30px;
    background:-webkit-linear-gradient(top,#336aa7,#336aa7,white); */
.titile-cls{
   background:#FFF;
   border-top-left-radius: 5px;
   border-top-right-radius: 5px;
   border-bottom-right-radius: 0;
   border-bottom-left-radius: 0;
   box-shadow: 5px 5px 5px #ddd;
    border-left:solid 1px #ddd;
    border-right:solid 1px #ddd;
    border-top:solid 1px #ddd;
    margin-top: 10px;
}

body{
	background:#F5F5F5;
	font-family:Microsoft YaHei;
}
.div-table {
	display: table;
	margin-left: auto;
    margin-right: auto;
}
.div-row {
	display: table-row;
}

.div-cell {
	display: table-cell; 
	vertical-align: middle; 
	padding: 6px 5px;
}
.container {
	width: auto;
	margin-left:10px;
	margin-right:10px;
}
.panel-body{
	background:#FFF;
    box-shadow: 3px 3px 3px #ddd;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
    border-bottom-right-radius: 5px;
    border-bottom-left-radius: 5px;
    padding-left:15px;
    padding-bottom: 15px;
    padding-top:0;
    border-left:solid 1px #ddd;
    border-right:solid 1px #ddd;
    border-bottom:solid 1px #ddd;
}
.title{
	display:inline-block;
	position:relative;
	background:#00F;
	color:#FFF;
	padding: 5px 23px;
    border-radius: 3px;
    font-size:14px;
    margin:10px 10px;
    font-weight:bold;
}
.btn-primary {
    color: #fff;
    background-color: #f39800;
    border-color: #f39800;
    padding: 4px 30px;
    box-shadow: 2px 2px 5px #ddd;
    margin-left: 10px;
}
.btn-primary:hover {
    color: #fff;
    background-color: #f39800;
    border-color: #f39800;
    padding: 4px 30px;
}
.top-elem{
	margin-top:10px;
}
.input-group-addon {
    padding: 0px 8px;
    font-size: 14px;
    font-weight: 400;
    line-height: 1;
    color: #555;
    text-align: center;
    background-color: #FFF;
    border: 1px solid #ccc;
    border-radius: 0;
    -webkit-box-shadow: 2px 2px 5px #ddd;
    box-shadow: 2px 2px 5px #ddd;
}
.search_container{
	width:auto;
	margin:10px;
	background:#FFF;
    box-shadow: 3px 3px 3px #ddd;
    border-radius: 5px;
    padding:15px 0;
    border:solid 1px #ddd;
    text-algin:center;
}
.form-control {
    display: block;
    width: 183px;
    height: 30px;
    padding:6px 12px;
    font-size: 14px;
    line-height: 30px;
    color: #333;
    font-weight:bold;
    border: 1px solid #ccc;
    border-radius: 0;
    -webkit-box-shadow: 2px 2px 5px #ddd;
    box-shadow: 2px 2px 5px #ddd;
    -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
    -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
    transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}
.glyphicon-calendar {
    width: 16px;
    height: 16px;
    background: url(<%=path%>/images/calendor_icon.png) no-repeat 0 center;
    background-size: 16px;
}
.input-append.date .add-on i, .input-prepend.date .add-on i, .input-group.date .input-group-addon span {
    cursor: pointer;
    width: 16px;
    height: 16px;
}
.input-group .form-control:last-child, .input-group-addon:last-child, .input-group-btn:first-child>.btn-group:not(:first-child)>.btn, .input-group-btn:first-child>.btn:not(:first-child), .input-group-btn:last-child>.btn, .input-group-btn:last-child>.btn-group>.btn, .input-group-btn:last-child>.dropdown-toggle {
    border-top-left-radius: 0;
    border-bottom-left-radius: 0;
}
.input-group .form-control {
    position: relative;
    z-index: 2;
    float: left;
    width: 122px;
    margin-bottom: 0;
}
.glyphicon-calendar:before {
    content: "";
}
form {
    margin-bottom: 0;
}
</style>
</head>
<body onload="bootstrapDate('anayDatePicker,contrastDatePicker');">
	<div class="container search_container">
			<form id="driverAnayForm" class="b2-example bs-example-form" role="form">
				<div class="div-table">
					<div class="div-row">
						<div class="div-cell">
							<h5 style="float: right;">分析时间</h5>
						</div>
						<div class="div-cell">
							<div class="input-group date" id="anayDatePicker"> 
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
								<input type="text" class="form-control" id="anayDate" name="anayDate">
							</div>
						</div>
						<div class="div-cell">
							<h5 style="float: right;">对比时间段</h5>
						</div>
						<div class="div-cell">
							<div class="input-group date" id="contrastDatePicker"> 
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
								<input type="text" class="form-control" id="contrastDate" name="contrastDate">
							</div>
						</div>
						<div class="div-cell">
							<input type="button" id="search"
	                            class="btn btn-primary" value="查询" />
						</div>
					</div>
				</div>
			</form>
	</div>
	<div class="container margin-top-cls">
		<div class="row titile-cls" style="margin-top:0;">
			<span class="title">上海局集团公司各机务段达标率(平均分60分以上)</span>
		</div>
		<div class="row panel-body" style="height:28%;">
			<div class="center-block">
				<div id="hg" class="average-cls"></div>
			</div>
		</div>
		<div class="row titile-cls">
			<span class="title">存在的典型问题</span>
		</div>
		<div class="row panel-body average-cls">
			<div class="col-md-6 brd">
				<div class="center-block">
					<div id="left" class="average-cls"></div>
				</div>
			</div>
			<div class="col-md-6 brd">
				<div class="center-block">
					<div id="right" class="average-cls"></div>
				</div>
			</div>
		</div>
		<div class="row titile-cls">
			<span class="title">关注的重点问题</span>
		</div>
		<div class="row panel-body average-cls" style="margin-bottom:10px;">
			<div class="col-md-6 brd">
				<div class="center-block">
					<div id="bom-left" class="average-cls"></div>
				</div>
			</div>
			<div class="col-md-6 brd">
				<div class="center-block">
					<div id="bom-right" class="average-cls"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
