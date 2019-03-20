// ajax操作
/*
 创建XMLHttpRequest。
*/
function newXHR() {
  var xmlHttp;
  try {
    xmlHttp=new XMLHttpRequest(); // Firefox,Google
  } catch (e) {
    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP"); // IE
		xmlHttp.abort();
  }
  if (xmlHttp) return xmlHttp;
  throw "Unsupport ajax.";
}
/*
 Ajax HEAD请求
*/
function ajaxhead(url,callback) {
  // 创建XMLHttpRequest
  var xmlHttp=newXHR();
  xmlHttp.open("HEAD",url,false);
  xmlHttp.send(null);
  callback(xmlHttp);
}
/*
 Ajax GET请求
*/
function ajaxget(url,callback) {
  // 创建XMLHttpRequest
  var xmlHttp=newXHR();
  xmlHttp.open("GET",url);
  xmlHttp.onreadystatechange=function() { callback(xmlHttp); };
  xmlHttp.send(null);   
}
/*
 Ajax POST请求
*/
function ajaxpost(url,callback,parameters) {
  // 创建XMLHttpRequest
  var xmlHttp=newXHR();
  xmlHttp.open("POST",url);
  xmlHttp.onreadystatechange=function() { callback(xmlHttp); };
  xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
  xmlHttp.send(parameters);
}

/*
 Ajax GET同步请求
*/
function ajaxgetsync(url) {
  // 创建XMLHttpRequest
  var isFirefox=navigator.userAgent.indexOf("Firefox")>0;
  var xmlHttp=newXHR();
  var result;
  xmlHttp.open("GET",url, false);  
  if (!isFirefox) {
    xmlHttp.onreadystatechange=function() { 
        if (xmlhttp.readyState==4)
        {
            result = xmlhttp.responseXML;
        }
    }
  }
  xmlHttp.send(null);
  if (isFirefox) {
    result = xmlhttp.responseXML;
  }
  alert("result:" + result);
  return result;
}