
$(
	function(){
		var endDate = getCurrentSystemDate();
		var startDate = getLeftDate(endDate, 180);
		$("#startDate").val(startDate);
		$("#endDate").val(endDate);
		
		setJwd();
		setWorkShop();
		setWorkTeam();
		setGroup();
		setDriverInfo();
		setTrainNumber();
		getSearchData();
		
	}	
)
/**
 * 初始化加载jqgrid
 */
function getSearchData(){
	$("#gridTable").jqGrid({
		url:path + "/workHabit/personalHabitPointAnis/getGrxgSingleAnsi.html",
        datatype: "json",
        postData:$("#formId").serialize(),
        height:"auto",
        width:1150,
        colNames:['序号','开车日期',"时间",'一级指标','二级指标', '三级指标', '扣分','扣分事项','车站'],
        //cellEdit:false,//启用或禁用单元格编辑功能
        colModel:[
          		{name:'id',index:'id', align:"center",width:90, editable:true,sorttype:"int",summaryType:"count",summaryTpl:"({0}条)合计"}, 
          		{name:'startDate',index:'startDate', width:70, editable:false},
          		{name:'current',index:'current', width:55, editable:true, editoptions:{readonly:true}},
        		{name:'first',index:'first', width:70, editable:true},
        		{name:'two',index:'two', width:70,editable:true},
        		{name:'three',index:'three', width:100, align:"left",editable:true,editrules:{number:true}},        		
        		{name:'score',index:'score', width:60, align:"center",editable:true,editrules:{number:true},sorttype:'number',formatter:'number',summaryTpl : "<b>{0}</b>",summaryType: "sum"},
        		{name:'note',index:'note', width:180,align:"left",editable:true,editrules:{number:true}},
        		{name:'stop',index:'stop', width:80, sortable:false,editable:true}
             ],
        sortname:'startDate',
        loadonce:true,
        rowNum:30,
        rowList:[30,100,150],
        sortorder:'asc',
        viewrecords:true,
        pager:"#gridPager",
        grouping: true,
        groupingView: {
            groupField: ["startDate","first","two"],
            groupColumnShow: [true,true,true],
            groupText: ["<b>{0}</b>"],
            groupOrder: ["asc","asc","asc"],
            groupSummary: [true,true,true],
            groupCollapse: false
        },
        caption: "分析结果",
	});
}
/**
 *  查询事件，重新加载jqgrid
 */
function search(){
	$("#gridTable").jqGrid("clearGridData");
	$("#gridTable").jqGrid('setGridParam',{
		url:path + "/workHabit/personalHabitPointAnis/getGrxgSingleAnsi.html",
        datatype: "json",
        postData:$("#formId").serialize(),
        mtype:"post",
	}).trigger("reloadGrid");
}

	//属性初始化
//	$('#gridTable').jqGrid('navGrid','#gridPager',{
//
//		   refresh:true, refreshtext: "刷新", 
//
//		   edit:true,edittext: "编辑",
//
//		   add:true,addtext: "添加",
//
//		   del:true,deltext: "删除",
//		   search:false,
//		   //addfunc:openDialog4Adding, //增加
//		   //editfunc:openDialog4Updating,//编辑
//		   //delfunc:openDialog4Deleting//删除
//
//	   });
//	
//	if(resultData!=null){
//		for(var i=0;i<=resultData.length;i++){
//			jQuery("#gridTable").jqGrid('addRowData',i+1,resultData[i]);
//		}
//		jQuery("#gridTable").jqGrid().trigger("reloadGrid"); //添加完数据后客户端自动刷新一次、实现客户端数据与服务器数据的完全分离
//	}
	//配置对话框 //设置对话框为模态对话框 设置为模态窗口弹出
	
//   $("#consoleDlg").dialog({
//	   autoOpen:false,
//	   modal:true     
//   });

 
////增加功能
//var openDialog4Adding = function() {
//    var consoleDlg = $("#consoleDlg");    
//    consoleDlg.find("input").removeAttr("disabled").val("");       
//    consoleDlg.dialog({
//    	title:"新增",
//    	resizable:false,
// 	    width:480,
// 	    buttons:{
// 		   "取消":function(){
// 			   $("#consoleDlg").dialog("close");
// 		   },
// 		   "新增":addItem
// 	   }
//    	});
//    consoleDlg.dialog("open");
//};
////新增事件的逻辑
//var addItem = function(){
//	//获取新的模态窗口的值
//	var consoleDlg = $("#consoleDlg");
//	var invdate = $.trim(consoleDlg.find("#invdate").val());  
//    var name = $.trim(consoleDlg.find("#name").val());  
//    var amount = $.trim(consoleDlg.find("#amount").val());  
//    var tax = $.trim(consoleDlg.find("#tax").val());  
//    var total = $.trim(consoleDlg.find("#total").val());  
//    var note = $.trim(consoleDlg.find("#note").val());   
////拼成json格式
//    var params = {  
//            "invdate" : invdate,  
//            "name" : name,  
//            "amount" : amount,  
//            "tax" : tax,  
//            "total" : total,  
//            "note" : note
//    };   
////发送ajax请求 获取新的行信息
//    $.ajax({
//    	url:path + "/workHabit/personalHabitPointAnis/addRecords.html",
//    	data : params,
//    	dataType : "json",
//    	cache : false,
//        success : function(response, textStatus) {    
//        	/* alert("id123-->" + response.id + "; message-->" + response.message); */
//            if (response.message == true) {
//                var dataRow = {  
//                    id : response.id,    //从server端获得系统分配的id  
//                    invdate : invdate,  
//                    name : name,  
//                    amount : amount,  
//                    tax : tax,  
//                    total : total,  
//                    note : note  
//                };
//                $("#gridTable").jqGrid("addRowData",    
//                		response.id, dataRow, "last");    //将新行插入到末尾                   
//				//添加完成之后关闭模态窗口
//                consoleDlg.dialog("close");  
//                alert("添加成功!");  
//            }else{
//                alert("添加失败!");
//            }
//        },
//	    error : function(textStatus, e) {    
//	        alert("系统异常: " + textStatus);  
//	    }
//    });
//};

/**
//修改功能
var openDialog4Updating = function(){  
	   var consoleDlg = $("#consoleDlg");       
	   consoleDlg.find("input").removeAttr("disabled");    
	   consoleDlg.dialog({
	   	title:"修改",
	   	resizable:false,
		    width:480,
		    buttons:{
			   "取消":function(){
				   $("#consoleDlg").dialog("close");
			   },
			   "修改":editItem
		   }
	   	});	   
//加载数据到模态窗口
	   loadSelectedRowData();
	   consoleDlg.dialog("open");	   
	};
	
//修改功能
var editItem = function(){
//获取模态窗口参数
	var consoleDlg = $("#consoleDlg");
	var id = $.trim(consoleDlg.find("#selectId").val()); 
	var tempInvdate = $.trim(consoleDlg.find("#invdate").val());
	var invdate = tempInvdate.substring(0,10);
    var name = $.trim(consoleDlg.find("#name").val());  
    var amount = $.trim(consoleDlg.find("#amount").val());  
    var tax = $.trim(consoleDlg.find("#tax").val());  
    var total = $.trim(consoleDlg.find("#total").val());  
    var note = $.trim(consoleDlg.find("#note").val());   
    var params = {  
    		"id" : id,
            "invdate" : invdate,  
            "name" : name,  
            "amount" : amount,  
            "tax" : tax,  
            "total" : total,  
            "note" : note
			};
    $.ajax({
    	url:"${basePath}/itCrud/edit",
    	data : params,
    	dataType : "json",
    	cache : false,
        success : function(response, textStatus) {    
//	        	alert("id-->" + response.id + "; message-->" + response.message);
            if (response.message == true) {
                var dataRow = {  
                	selectId : id,
                    invdate : invdate,  
                    name : name,  
                    amount : amount,  
                    tax : tax,  
                    total : total,  
                    note : note  
                };
	            //将表格中对应记录更新一下
	            $("#gridTable").jqGrid("setRowData", id, dataRow);
                consoleDlg.dialog("close");
                alert("修改成功!");
            }else{
                alert("修改失败!");
            }
        },
	    error : function(textStatus, e) {    
	        alert("系统异常: " + textStatus);  
	    }
    });
}	

var loadSelectedRowData = function(){
	var selectedRowId = $("#gridTable").jqGrid("getGridParam", "selrow");
	//获得当前行各项属性
	var rowData = $("#gridTable").jqGrid("getRowData",selectedRowId);
	if (!selectedRowId) {  
        alert("请先选择需要编辑的行!");    
        return false;
    } else {			
    	var consoleDlg = $("#consoleDlg");  
        consoleDlg.find("#selectId").val(rowData.id);    
        consoleDlg.find("#invdate").val(rowData.invdate);    
        consoleDlg.find("#name").val(rowData.name);
        consoleDlg.find("#amount").val(rowData.amount);
        consoleDlg.find("#tax").val(rowData.tax);    
        consoleDlg.find("#total").val(rowData.total);    
        consoleDlg.find("#note").val(rowData.note);   	
    }
};
//删除功能
var openDialog4Deleting = function() {    
	   var consoleDlg = $("#consoleDlg");       
	   consoleDlg.find("input").attr("disabled", true);    
	    consoleDlg.dialog({
	       	title:"删除",
	       	resizable:false,
	    	    width:480,
	    	    buttons:{
	    		   "取消":function(){
	    			   $("#consoleDlg").dialog("close");
	    		   },
	    		   "删除":deleteItem
	    	   }
	       	});
	   loadSelectedRowData();
	   consoleDlg.dialog("open");
	};
	var deleteItem = function(){
		var consoleDlg = $("#consoleDlg");
		var id = $.trim(consoleDlg.find("#selectId").val());
		var tempInvdate = $.trim(consoleDlg.find("#invdate").val());
		var invdate = tempInvdate.substring(0,10);
	    var name = $.trim(consoleDlg.find("#name").val());  
	    var amount = $.trim(consoleDlg.find("#amount").val());  
	    var tax = $.trim(consoleDlg.find("#tax").val());  
	    var total = $.trim(consoleDlg.find("#total").val());  
	    var note = $.trim(consoleDlg.find("#note").val());   
	    var params = {  
	    		"id" : id,
	            "invdate" : invdate,  
	            "name" : name,  
	            "amount" : amount,  
	            "tax" : tax,  
	            "total" : total,  
	            "note" : note
				};
	    $.ajax({
	    	url:"${basePath}/itCrud/delete",
	    	data : params,
	    	dataType : "json",
	    	cache : false,
	        success : function(response, textStatus) {    
//	        	alert("id-->" + response.id + "; message-->" + response.message);
	            if (response.message == true) {
	               $("#gridTable").jqGrid("delRowData", id); 
	                 
	                consoleDlg.dialog("close");
	                alert("删除成功!");
	            }else{
	                alert("删除失败!");
	            }
	        },
		    error : function(textStatus, e) {    
		        alert("系统异常: " + textStatus);  
		    }
	    });
	}
*/