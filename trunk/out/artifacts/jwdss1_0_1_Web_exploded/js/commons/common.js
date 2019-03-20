/**
 * 设置提交按钮是否生效
 * @param obj 提交按钮ID
 * @param bool true生效,false无效
 */
function setdisabled(obj,bool) {
	if (bool) {
		jQuery('#'+obj).attr('disabled', 'disabled');
	} else {
		jQuery('#'+obj).removeAttr('disabled');
	}
}
/**
 * 去除多余空格函数 trim:去除两边空格 lTrim:去除左空格 rTrim: 去除右空格 用法： var str = " hello "; str =
 * str.trim();
 */
String.prototype.trim = function() {
	return this.replace(/(^[\\s]*)|([\\s]*$)/g, "");
}
String.prototype.lTrim = function() {
	return this.replace(/(^[\\s]*)/g, "");
}
String.prototype.rTrim = function() {
	return this.replace(/([\\s]*$)/g, "");
}

/**
 * 替换字符串
 * @param oldStr
 * @param newStr
 */
String.prototype.myReplace = function(oldStr, newStr) {
	var reg = new RegExp(oldStr, "g");
	return this.replace(reg, newStr);
}

/**
 * SearchPage
 * @param formId Form表单Id
 * @param target 目标显示ID
 * @param page 页数
 * @param pagesize 页面条数
 */
function getSearchPage(_context_path, formId, targetDiv, page, pagesize) {
	$('#page').val(page);
	$('#pagesize').val(pagesize);
	$(formId).ajaxSubmit({
		target : targetDiv,
		beforeSubmit : function() {
			$(targetDiv).html('<div class="border tdbg"><img src="' + _context_path + '/images/loading.gif">正在查询，请稍后！！！</div>');
		},
		error : function() {
			alert('系统系统错误!');
		},
		success : function() {
			$(targetDiv).fadeIn('slow');
		}
	});
}

/**
 * SearchPage
 * @param formId Form表单Id
 * @param target 目标显示ID
 */
function getSearch(_context_path, formId, targetDiv) {
	$(formId).ajaxSubmit({
		target : targetDiv,
		beforeSubmit : function() {
			$(targetDiv).html('<div class="border tdbg"><img src="' + _context_path + '/images/loading.gif">正在查询，请稍后！！！</div>');
		},
		error : function() {
			alert('系统系统错误!');
		},
		success : function() {
			$(targetDiv).fadeIn('slow');
		}
	});
}

/**
 * 获取CheckBox被选中的所有值
 * 
 * @param name checkbox的Name属性的值
 */
function getCheckboxValues(name) {
	var str = "";
	var checkValues = $("input[name='" + name + "']:checked");
	if (checkValues.size() < 1) {
		return str;
	}
	checkValues.each(function() {
				if (str == "") {
					str = $(this).val();
				} else {
					str += "," + $(this).val();
				}
			});
	return str;
}

/**
 * 通用Ajax数据库删除、恢复
 * 
 * @param url
 * @param data
 * @param callback
 */
function jDelete(url, data, message) {
	//删除
	message = message || "您确定要删除吗?";
	var flag = confirm(message);
	if (flag) {
		jQuery.ajax({
			async : false,
			type : 'POST',
			url : url,
			dataType : 'json',
			data : data,
			error : function() {
				alert('系统错误！');
			},
			success : function(json) {
				if (json.status == 'success') {
					window.location.reload();
				} else {
					alert(json.msg);
				}
			}
		});
	}
}

/**
 * 验证身份证号码
 * @param {} num
 * @return {Boolean}
 */
function checkidcard(idcard) {
	var area = {
		11 : "北京", 12 : "天津", 13 : "河北", 14 : "山西", 15 : "内蒙古", 21 : "辽宁", 22 : "吉林",
		23 : "黑龙江", 31 : "上海", 32 : "江苏", 33 : "浙江", 34 : "安徽", 35 : "福建", 36 : "江西",
		37 : "山东", 41 : "河南", 42 : "湖北", 43 : "湖南", 44 : "广东", 45 : "广西", 46 : "海南",
		50 : "重庆", 51 : "四川", 52 : "贵州", 53 : "云南", 54 : "西藏", 61 : "陕西", 62 : "甘肃",
		63 : "青海", 64 : "宁夏", 65 : "新疆", 71 : "台湾", 81 : "香港", 82 : "澳门", 91 : "国外"
	}

	var idcard, Y, JYM, flag;
	var S, M;
	var idcard_array = new Array();
	idcard_array = idcard.split("");
	// 地区检验
	if (area[parseInt(idcard.substr(0, 2))] == null)
		flag =  4; // 身份证地区非法!
	// 身份号码位数及格式检验
	switch (idcard.length) {
		case 15 :
			if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 || ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0)) {
				ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;// 测试出生日期的合法性
			} else {
				ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;// 测试出生日期的合法性
			}
			if (ereg.test(idcard))
				flag =  0; // 验证通过!
			else
				flag =  2; // 身份证号码位数不对!
			break;
		case 18 :
			// 18位身份号码检测
			// 出生日期的合法性检查
			// 闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
			// 平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
			if (parseInt(idcard.substr(6, 4)) % 4 == 0 || (parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard.substr(6, 4)) % 4 == 0)) {
				ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;// 闰年出生日期的合法性正则表达式
			} else {
				ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;// 平年出生日期的合法性正则表达式
			}
			if (ereg.test(idcard)) {// 测试出生日期的合法性
			// 计算校验位
				S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
						+ (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
						+ (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
						+ (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
						+ (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
						+ (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
						+ (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
						+ parseInt(idcard_array[7]) * 1
						+ parseInt(idcard_array[8]) * 6
						+ parseInt(idcard_array[9]) * 3;
				Y = S % 11;
				M = "F";
				JYM = "10X98765432";
				M = JYM.substr(Y, 1);//判断校验位
				if (M == idcard_array[17]) // 检测ID的校验位
					flag =  0; // 验证通过!
				else
					flag =  3; // 身份证号码校验错误!
			} else
				flag =  2; // 身份证号码出生日期超出范围或含有非法字符!
			break;
		default :
			flag =  1; // 身份证号码位数不对!
			break;
	}
	if (flag == 0) {
		return true;
	} else {
		return false;
	}
}
/**
 * 通用Ajax条目自动搜索Autocomplete
 * 
 * @param id
 * @param url
 */
function jQueryEntrys(id,url) {
	jQuery("#"+id).autocomplete(url,{
		minChars: 0,           // 在触发autoComplete前用户至少需要输入的字符数.Default: 1，如果设为0，在输入框内双击或者删除输入框内内容时显示列表
		multiple: true,        //允许查询多个
		matchContains: false,   //true是查找包含字符  false是不查询包含字符
		parse: function(data) {   
			var rows = [];  
			var code = jQuery(data).find("root > entry").text();
			var str = code.split(",");
			//console.log("str:"+str);    //相当于system.out.println();打印
			for(i=0;i<str.length;i++){
				rows.push({
					data:str[i],
					value:str[i],
					result:str[i]
				});
			}    
	        return rows;
		},
		formatItem: function(row, i, max) {
		   return row;
		}
	}); 
}
/**
 * 通用Ajax图书名称自动搜索Autocomplete
 * 
 * @param id
 * @param url
 */
function jQueryBookNames(id,url) {
	jQuery("#"+id).autocomplete(url,{
		minChars: 1,           // 在触发autoComplete前用户至少需要输入的字符数.Default: 1，如果设为0，在输入框内双击或者删除输入框内内容时显示列表
		multiple: false,        //允许查询多个
		matchContains: true,   //true是查找包含字符  false是不查询包含字符
		max:20,
		parse: function(data) {   
			var rows = [];  
			var code = jQuery(data).find("root > entry").text();
			var str = code.split(",");
			//console.log("str:"+str);    //相当于system.out.println();打印
			for(i=0;i<str.length;i++){
				rows.push({
					data:str[i],
					value:str[i],
					result:str[i]
				});
			}    
			return rows;
		},
		formatItem: function(row, i, max) {
			return row;
		}
	}); 
}

//************* highslide 配置 ***************
graphicsDir : '../',
//hs.outlineType = 'rounded-blue';
hs.wrapperClassName = 'draggable-header';
hs.creditsText = '';
hs.width = 800;
hs.height = 600;
hs.dimmingOpacity = 0.2;
hs.objectLoadTime = 'after';
hs.preserveContent = false;
hs.headingText = "";
hs.align = "center";
//hs.showCredits = true;
hs.Expander.prototype.onBeforeClose = function() {
	//alert("common.onBeforeClose");
	document.body.focus();
} 
function iframeBox(e, config) {
	config.objectType='iframe';
	return hs.htmlExpand(e, config);
}

function inlineBox(e, config){
	return hs.htmlExpand(e, config);
}

function closeBox() {
	return hs.close();
}
// ************* highslide 配置 ***************

/**
 * 通用控制日期加减方法
 * 
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
			minView: 3,
			language: 'zh-CN',
			todayBtn : 'linked', //选择为当前日期（今天）
			autoclose:true  //是否选择完成自动关闭日历
		});
	}
	
}
function bootstrapDateTime(id){
	var ids = id.split(",");
	for (var i = 0; i < ids.length; i++) {
		$("#" + ids[i]).datetimepicker({
			format:"hh:ii:ss",
			autoclose:true,
			//minView: 0,
			startView : 1,
			language:'zh-CN',
			minuteStep:1	
		});
	}
	
}
function bootstrapDateFormat(id,formatVal) {
	var ids = id.split(",");
	for (var i = 0; i < ids.length; i++) {
		$("#" + ids[i]).datetimepicker({
			format: formatVal,
			minView: 3,
			language: 'zh-CN'
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


