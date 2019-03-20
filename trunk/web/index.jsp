<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="height:100%;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${siteName }</title>
<link href="${contextPath}/css/bootstrap.min.css?v=v3.2.0" rel="stylesheet">
<link href="${contextPath}/css/tenly.login.css" rel="stylesheet">
<script type="text/javascript" src="${contextPath}/js/ajax.js"></script>
<script type="text/javascript" src="${contextPath}/js/cookie.js"></script>
<script type="text/javascript" src="${contextPath}/js/string.js"></script>
<script type="text/javascript" src="${contextPath }/js/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
});
function login(contextPath) {
	var username = $("#username").val();
	var password = $("#password").val();
	var reg=/^(?!.*[%\'\"?])/g;
	if(!reg.test(username)){
		alert('用户名不能使用特殊符号');
	}else{
		ajaxpost(contextPath+"/admin/system/login.do?username="+username+"&password="+password,function(xmlHttp) {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					var dom=xmlHttp.responseXML;
					var status=dom.getElementsByTagName("status")[0].firstChild.nodeValue;
					document.getElementById("error_username").innerHTML="";
					document.getElementById("error_password").innerHTML="";
					if (status=="1") {
						document.getElementById("error_username").innerHTML=dom.getElementsByTagName("error")[0].firstChild.nodeValue;
						document.getElementById("username").focus();
					} else if(status=="2") {
						document.getElementById("error_password").innerHTML=dom.getElementsByTagName("error")[0].firstChild.nodeValue;
						document.getElementById("password").focus();
					} else {
						setCookie("__adminuser",dom.getElementsByTagName("userid")[0].firstChild.nodeValue,dom.getElementsByTagName("expire")[0].firstChild.nodeValue);
						window.location.href = "${contextPath}/admin/system/main.do";
					}
				}
			}
		});		
	}
	return false;
}
</script>
</head>
<body onkeydown="enterSubmit(event)">
	<div id="login_content">
		<img src="${contextPath }/images/login/login_logo.png" alt="" class="login_icon"/>
		<div class="login_title"></div>
		<div class="form_container">
		  <div class="user_login">用户登录</div>
       	  <table border="0" cellpadding="0" cellspacing="10">
           	<tr class="login_line_box">
           			<td class="user_name_label">用户名：</td>
                   <td class="user_name_input"><input type="text" id="username" name="username" placeholder="" value="admin"/></td>
               </tr>
               <tr class="error_box">
               <td colspan="2"><span id="error_username" style="float:right; color:#FF0000;width: 100%;text-align:right;"></span></td>
               </tr>
               <tr class="login_line_box">       
               	   <td class="user_name_label">密码：</td>   
                   <td class="user_name_input"><input type="password" id="password" name="password"  placeholder="" value="123"/></td>
               </tr>
               <tr class="error_box">
               <td colspan="2"><span id="error_password" style="float:right; color:#FF0000;width: 100%;text-align:right;"></span></td>
               </tr>
               <tr  class="btn_bg">
               	<td colspan="2">
               		<img src="${contextPath }/images/login/login_lock.png" alt="" />
               		<input type="button" value="登录" onclick="login('${contextPath}');" id="submit_btn"/>
               	</td>
               </tr>
           </table>
       </div>
    </div>
</body>
<script type="text/javascript">
	var width = $("body").width();
	var height = $("body").height();
	var ratioW = width/1920;
	var ratioH = height/1080;
	var l = (1920-width)/2;
	var t = (1080-height)/2;
	$("#login_content").css({
		"transform":"scale("+ratioW+")",
		"-ms-transform":"scale("+ratioW+")",
		"-webkit-transform":"scale("+ratioW+")",
		"left":-l+"px",
		"top":-t+"px"
	});
	function enterSubmit(obj) {
    //当enter 键按下后，需要执行的事件
        var button = document.getElementById('eventquery');
        if (obj.keyCode == 13) {
            $("#submit_btn").click();
            obj.returnValue = false;
        }
    }
</script>
</html>