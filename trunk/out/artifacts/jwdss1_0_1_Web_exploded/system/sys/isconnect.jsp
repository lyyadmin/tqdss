<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>网络连接</title>
    <script type="text/javascript" src="<%=path %>/js/jquery/jquery.min.js"></script>
</head>
<body>
<div style="overflow: hidden;white-space: nowrap;float: left">
    <h1>温馨提示：网络连接失败，请检查网络连接是否正常。点击</h1><a href="javascript:void(0)" onclick="closewindows()" style="font-size: 20px;color: #0a7ebd">关闭</a><h1>重新连接</h1>
</div>
</body>
</html>
<script>
    function closewindows(){
        window.close();
    };
</script>