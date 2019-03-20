<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%response.setStatus(200);%>
<html>
<head>
    <title>500</title>
    <link type="text/css" rel="stylesheet" href="<%=path %>/js/sweetalert/sweetalert.css"  />
    <script type="text/javascript" src="<%=path %>/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/sweetalert/sweetalert.min.js"></script>
</head>
<body>
    <h3>服务器内部错误或网络连接失败</h3>
    <a href="javascript:void(0)" onclick="closewindows()" style="font-size: 20px;color: #0a7ebd">关闭</a>
</body>
</html>
<script>
    function closewindows(){
        window.close();
    };
    var EventUtil = {
        addHandler: function (element, type, handler) {
            if (element.addEventListener) {
                element.addEventListener(type, handler, false);
            } else if (element.attachEvent) {
                element.attachEvent("on" + type, handler);
            } else {
                element["on" + type] = handler;
            }
        }
    };
    EventUtil.addHandler(window, "online", function () {
        window.close();
    });
    EventUtil.addHandler(window, "offline", function () {
        swal("","网络连接失败","error");
    });
</script>