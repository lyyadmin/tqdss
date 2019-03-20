<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/header_commons_1.0.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>功能权限</title>
	<link type="text/css" rel="stylesheet" href="<%=path %>/css/tenly.table.css"  />
	<link type="text/css" rel="stylesheet" href="${contextPath }/css/commonCondition.css" />
    <style type="text/css">
	.add-btn{
			background:#006AE6;
			padding:4px 0px;
		}
	.add-btn:hover{
		background:#006AE6;
		padding:4px 0px;
	}
	.add-btn:focus{
		background:#006AE6;
		padding:4px 0px;
	}
	.div-cls-middle{
		text-align:center;
		padding-top:10px;
	}
    </style>
  </head>
  
  <body>
  <!-- 查询搜索框 -->
    <div class="panel panel-default" style="width:98%">
        <div class="row" style="height:60px;">
            <div class="form-inline div-cls-middle">
                       <div class="input-group">
                              <input id="name" name="name" type="text" class="form-control" placeholder="请输入搜索名称">
                       </div>
                       <div class="input-group">
                              <button type="button" style="margin-left:50px;width:100px;"
                                                class="btn btn-primary my-btn" onclick="search(this)">搜索</button>
                       </div>
            </div>
        </div>
    </div>
    <!-- 按钮 -->
    <div class="panel margin-panel-cls">
        <button type="button" style="margin-left:0px;width:50px;"
            class="btn btn-primary add-btn" onclick="addRow(this)">新增</button>
        <button type="button" style="margin-left:50px;width:50px;"
            class="btn btn-primary add-btn" onclick="update(this);">修改</button>
        <button type="button" style="margin-left:50px;width:50px;"
            class="btn btn-primary add-btn" onclick="del(this);">删除</button>
    </div>
    <div>
<!--       <div class="panel-heading">功能权限管理界面</div> -->
<!--             <div class="panel" style="margin:0px 0px 5px 0px;"> -->
                <table id="userinfo"></table>
                <div id="page"></div>
<!--                 </div> -->
   </div>
   <!--添加的模态对话框  -->
   <%@include file="/system/sys/add-function-menu.jsp" %>
   
     <script type="text/javascript" src="<%=path %>/js/jquery-ui.js"></script>
     <script type="text/javascript" src="<%=path %>/system/sys/js/function-manu.js"></script>
  </body>
</html>
