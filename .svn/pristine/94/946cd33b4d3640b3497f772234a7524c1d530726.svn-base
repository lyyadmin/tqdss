// 字符串
/*
 * 字符串trim函数
 */
String.prototype.trim=function(){
	return this.replace(/(^\s*)|(\s*$)/g,"");
};

/*
 * 字符串encode
 */
String.prototype.encode=function() {
	return encodeURIComponent(this);
}

function getRadioValue(radioName) {
    var rs = document.getElementsByName(radioName);
    if (rs) {
        for (var i = 0; i < rs.length; i++) {
            if (rs[i].checked) {
                return rs[i].value;            
            }
        }
    }
    return null;
}


/*
 * 验证是否是域名
 */
function isValidDomain(str)
{
   var r = new RegExp("^(\\w+\\.){1}(\\w+\\-?\\w+\\.){1,2}[a-zA-Z]{2,3}$");
    //包含http://开头的URL
   //var r=new RegExp("^(http://\\w+\\.){1}(\\w+\\-?\\w+\\.){1,2}[a-zA-Z]{2,3}$");
   return r.test(str);
}

/*
 * 验证是否是域名
 */
function isIP(strIP) {
    var re=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g; //匹配IP地址的正则表达式
    if(re.test(strIP)) {
        if( RegExp.$1 <256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256) return true;
    }
    return false;
}

/*
 * 验证是否是日期格式
 */
function isDate8(str) {
    if (str.trim().length != 8) {
        return false;
    }
    var re=/^\d{4}\d{2}\d{2}$/;
    //var re=/\d{8}/g;
    //var re = str.match(/^\d{4}\d{2}\d{2}$/); 
    if(!re.test(str)) {
        return false;
    }
//    if(r == null) {
//        return false;
//    }
//    str = str.substr(0,4) + "-" + str.substr(4,2) + "-" + str.substr(6,2);
//    var dateArray = str.split("-");
//    var y = parseInt(dateArray[0]);
//    var m = parseInt(dateArray[1]);
//    var d = parseInt(dateArray[2]);
    
    var y = parseInt(str.substr(0,4));
    var m = parseInt(str.substr(4,1) == "0" ? str.substr(5,1) : str.substr(4,2));
    var d = parseInt(str.substr(6,1) == "0" ? str.substr(7,1) : str.substr(6,2));
    var date = new Date(y, m-1,d); 
    return (date.getFullYear()==y&&(date.getMonth()+1)==m&&date.getDate()==d);
}


function getRadioValue(RadioName){
    var obj;   
    obj=document.getElementsByName(RadioName);
    if(obj!=null){
        var i;
        for(i=0;i<obj.length;i++){
            if(obj[i].checked){
                return obj[i].value;           
            }
        }
    }
    return null;
}
