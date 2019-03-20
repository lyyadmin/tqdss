<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%response.setStatus(200);%>
<html>
<head>
    <title>404</title>
    <script type="text/javascript" src="<%=path %>/js/jquery/jquery.min.js"></script>
</head>
<body>
    <h4>抱歉！地址走丢了,请确认配置文件是否正确</h4>
    <a href="javascript:void(0)" onclick="closewindows()" style="font-size: 20px;color: #0a7ebd">关闭</a>
</body>
</html>
<script>
    function closewindows(){
        window.close();
    };
</script>
