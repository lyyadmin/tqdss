// cookie操作
/*
 设置cookie
 name:cookie名称
 value:cookie值
 expires:cookie生命周期
*/
function setCookie(name,value,expires)
{
	expires = Number(expires);
	var hostname=window.location.hostname;
	var domain="";
	if(!(/^\w+$/.test(hostname)||/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/.test(hostname))){
		var f=hostname.split(".");
		//domain="."+f[f.length-2]+"."+f[f.length-1];
		domain = "." + hostname;
	}
	if(expires){
		var exp=new Date();
		exp.setTime(exp.getTime()+expires);
		if(domain.length)
			document.cookie=name+"="+escape(value)+";domain="+domain+";path=/;expires="+exp.toGMTString();
		else
			document.cookie=name+"="+escape(value)+";path=/;expires="+exp.toGMTString();
	}
	else{
		if(domain.length)
			document.cookie=name+"="+escape(value)+";domain="+domain+";path=/";
		else
			document.cookie=name+"="+escape(value)+";path=/";
	}
}
/*
 取得cookie
 name:cookie名称
*/
function getCookie(name)
{
  var value=document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
  return value?unescape(value[2]):null
}
/*
 删除cookie
 name:cookie名称
*/
function delCookie(name)
{
	var exp = new Date();
	exp.setTime(exp.getTime()-1);
	var value=getCookie(name);
	var hostname=window.location.hostname;
	var domain="";
	if(/^\w+$/.test(hostname)||/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/.test(hostname)) {
		domain=hostname;
	}
	else {
		var f=hostname.split(".");
		domain="."+f[f.length-2]+"."+f[f.length-1];
	}
	if (value!=null) document.cookie=name+"="+value+";domain="+domain+";path=/;expires="+exp.toGMTString();
}