/**
 * 通用控制日期加减方法
 * @param date
 * @param type
 * @param callback
 */
function checkDate(date,type){
	var bdate = date.split("-");
	var cd = bdate[1]+"/"+bdate[2]+"/"+bdate[0];
	var nd = new Date(cd);
	nd = nd.valueOf();
	if(type == "left"){
		nd = nd - 24 * 60 * 60 * 1000;
	}else{
		nd = nd + 24 * 60 * 60 * 1000;
	}
	nd = new Date(nd);
	var y = nd.getFullYear();
	var m = nd.getMonth() + 1;
	var d = nd.getDate();
	var newdate = y+"-"+m+"-"+d;
	return newdate;		
}

/**
 * @param day   表示先前推几天或者几个月
 * @returns {String}
 */
function getLeftDate(date,num){
	var bdate = date.split("-");
	var cd = bdate[1]+"/"+bdate[2]+"/"+bdate[0];
	var nd = new Date(cd);
	nd = nd.valueOf();
	nd = nd - 24 * num*60 * 60 * 1000;

	nd = new Date(nd);
	var y = nd.getFullYear();
	var m = (nd.getMonth()+1);
	var d = nd.getDate();
	var rex = /[0-9]/;
	if(rex.test(m)&&m<10){
		m = "0"+m;
	}
	if(rex.test(d)&&d<10){
		d = "0"+d;
	}
	var newdate = y+"-"+m+"-"+d;
	return newdate;		
}
/**
 * 获取系统当前日期
 */
function getCurrentSystemDate(){
	var nd = new Date();
	var y = nd.getFullYear();
	var m = nd.getMonth() + 1;
	var d = nd.getDate();
	var rex = /[0-9]/;
	if(rex.test(m)&&m<10){
		m = "0"+m;
	}
	if(rex.test(d)&&d<10){
		d = "0"+d;
	}
	var newdate = y+"-"+m+"-"+d;
	return newdate;
}

function bootstrapDate(id) {
	var ids = id.split(",");
	for (var i = 0; i < ids.length; i++) {
		$("#" + ids[i]).datetimepicker({
			format: "yyyy-mm-dd",
			minView: 3
		});
	}
	
}


function stringToDate(dateStr,separator){
    if(!separator){
        separator="-";
    }
    var dateArr = dateStr.split(separator);
    var year = parseInt(dateArr[0]);
    var month;
    //处理月份为04这样的情况
    if(dateArr[1].indexOf("0") == 0){
        month = parseInt(dateArr[1].substring(1));
    }else{
        month = parseInt(dateArr[1]);
    }
    var day = parseInt(dateArr[2]);
    var date = new Date(year,month -1,day);
    return date;
}
function dateToString(date){
    var year = date.getFullYear();
    var month =(date.getMonth() + 1).toString();
    var day = (date.getDate()).toString();
    if (month.length == 1) {
        month = "0" + month;
    }
    if (day.length == 1) {
        day = "0" + day;
    }
    var dateTime = year + "-" + month + "-" + day;
    return dateTime;
}



