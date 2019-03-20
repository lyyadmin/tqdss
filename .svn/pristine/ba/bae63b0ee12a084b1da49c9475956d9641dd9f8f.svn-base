/**
	id varchar 64  0   0   0   0   0   0       0   编号  utf8    utf8_general_ci     -1  0
	name    varchar 100 0   0   0   0   0   0       0   角色名称    utf8    utf8_general_ci     0   0
	code    varchar 255 0   -1  0   0   0   0       0   英文名称    utf8    utf8_general_ci     0   0
	is_sys  varchar 64  0   -1  0   0   0   0       0   是否系统数据  utf8    utf8_general_ci     0   0
	usable  varchar 64  0   -1  0   0   0   0       0   是否可用    utf8    utf8_general_ci     0   0
	create_by   varchar 64  0   -1  0   0   0   0       0   创建者 utf8    utf8_general_ci     0   0
	create_date datetime    0   0   -1  0   0   0   0       0   创建时间                0   0
	update_by   varchar 64  0   -1  0   0   0   0       0   更新者 utf8    utf8_general_ci     0   0
	update_date datetime    0   0   -1  0   0   0   0       0   更新时间                0   0
	remarks varchar 255 0   -1  0   0   0   0       0   备注信息    utf8    utf8_general_ci     0   0
	del_flag    char    1   0   0   0   0   0   0   '0' 0   删除标记    utf8    utf8_general_ci     0   0
	*/
$(function(){
	$(".modal-dialog").draggable();
	initPage();
 });
 function initPage(){
	 var colModel = [
	                    {name: "id",index: "id",align: "center", sorttype: "string",hidden:true}, 
	                    {name: "name",index: "name", align: "center"}, 
	                    {name: "code",index: "code",align: "center", sorttype: "string"}, 
	                    {name: "is_sys",index: "is_sys", align: "center",hidden:true},
	                    {name: "usable",index: "usable", align: "center",hidden:true},
	                    {name: "create_by",index: "create_by", align: "center"},
	                    {name: "create_date",index: "create_date",align: "center"},
	                    {name: "update_by",index: "update_by",align: "center"},
	                    {name: "update_date",index: "update_date",align: "center"},
	                    {name: "remarks",index: "remarks",align: "center"},
	                    {name: "del_flag",index: "del_flag",align: "center", hidden:true},
	                ];
	    var colNames = ["","角色名称","英文名称", "是否系统数据", "是否可用", "创建者","创建时间","更新者","更新时间 ","备注信息"," 删除标记"];
	    getGrid("userinfo","","page",colModel,colNames,"");
 }
 function getGrid(tblId,postData,pageId,colModel,colNames,data){
     jQuery("#"+tblId).jqGrid({
         url        :path + "/sys/sysrole/pageQuerySysRole.do",
         datatype   : "json",                              //从服务器端返回的数据类型，（表格期望接收的数据类型）。可选类型：xml，xmlstring，json，local，function
         height     : "70%",                               //高度
         colNames   :colNames,                             //列名
         colModel   :colModel,
         multiselect:true,
         caption    : "",                                   //标题
         pager      : "#"+pageId,                           //#page分页控件绑定的位置对象
         rowNum     : 20,                                  //每页显示条数
         rowList    : [20,50,100],                          //分页下拉选项内容
         rownumbers : true,                                 //是否显示行数
         viewrecords: true,                                 //是否显示总条数
         emptyrecords: "无数据",                              //服务器返回空列表时显示的内容
         autowidth  : true,                                 //自动调节宽度
         sortname   : 'SORT',                              //按SORT进行排序 默认asc
         caption : "角色管理",
         jsonReader :{
             root:"rows",
             page:"currentPage",
             total:"total",
             records:"records",
             repeatitems:false
         }
     }).trigger("reloadGrid");
     jQuery("#"+tblId).jqGrid("navGrid",pageId,{edit:false,add:false,del:false});
 }
 
 /**
  * 修改权限信息
  * @param obj
  */
 function updateRole(obj,id){
	 var rowId = $("#userinfo").jqGrid("getGridParam","selrow");
	 var rowData = $("#userinfo").jqGrid("getRowData",rowId);
	 if(rowId==null||rowId==undefined) {alert("请选择需要修改的行");return;}
	 var param = {"roleId":rowId};
	 //填充角色树
	 $.post(path+"/system/functionmenu/findAll.do",param,function(data){
		 var roleId = data["findRoleFunctionMenu"];
		 var result = data["findAllFunctionMenu"];
		 var objArr = [];
		 for(var i = 0;i<result.length;i++){
			 var obj = {};
			 obj.id = result[i].id;
			 obj.name = result[i].name;
			 obj.pId = result[i].parentId;
			 objArr.push(obj);
		 }
		 var treeObj;
		 var setting = {
				   key:{
					 title:'t'  
				   },
				   check:{
					   enable:true,
					   chkStyle:"checkbox"
				   },data:{
					   simpleData:{
						   enable:true,
					   }
				   },callback:{
					   onClick:function(){
						   ///alert(11)
					   },
					   onCheck:function(){
						   //alert(11)
					   }
				   }
		    }
		   
		 treeObj =  $.fn.zTree.init($("#nodeTree"),setting,objArr);
		 
		 for(var i=0; i<roleId.length;i++){
			 var node = treeObj.getNodeByParam("id",roleId[i].menu_id,null);
			 treeObj.checkNode(node,true,true);
		 }
	 });
	 var rowId = $("#userinfo").jqGrid("getGridParam","selrow");
	 var rowData = $("#userinfo").jqGrid("getRowData",rowId);
	 setValues("roleID",rowData.id);
	 setValues("roleName",rowData.name);
	 setValues("roleCode",rowData.code);
	 setValues("roleRemarks",rowData.remarks);
	 $("#myModal").modal();
 }
 function setValues(id,value){
	 $("#"+id).val(value);
 }
 /**
  * 更新
  * @param obj
  */
 function update(obj){
	 var treeObj = $.fn.zTree.getZTreeObj("nodeTree");
	 var nodes = treeObj.getCheckedNodes(true);
		var array = new Array();
		for(var i=0;i<nodes.length;i++){
			var id = nodes[i].id;
			array.push(id);
		}
		var functionIds = array.join(",");
		//为隐藏域赋值（权限的id拼接成的字符串）
		$("input[name=functionIds]").val(functionIds);
	 $.ajax({
		 url:path+"/sys/sysrole/update.do",
		 type:"post",
		 datatype:"json",
		 data:$("#addForm").serialize(),
		 success:function(result){
			 refreshTable();
		 }
	 });
	 
 }
 
 /**
  * 模态框
  * @param obj
  */
 function addModal(obj){
	 $.post(path+"/system/functionmenu/findAll.do",function(result){
		 var objArr = [];
		 for(var i = 0;i<result.length;i++){
			 var obj = {};
			 obj.id = result[i].id;
			 obj.name = result[i].name;
			 obj.pId = result[i].parentId;
			 objArr.push(obj);
		 }
		 
		 var setting = {
				   key:{
					 title:'t'  
				   },
				   check:{
					   enable:true,
					   chkStyle:"checkbox"
				   },data:{
					   simpleData:{
						   enable:true,
					   }
				   }
		    }
		   $.fn.zTree.init($("#addRoleTree"),setting,objArr);
	 });
	 $("#addRoleModal").modal();
 }
 //添加角色
 function addRoleInfo(obj){
	 var treeObj = $.fn.zTree.getZTreeObj("addRoleTree");
	 var nodes = treeObj.getCheckedNodes(true);
		var array = new Array();
		for(var i=0;i<nodes.length;i++){
			var id = nodes[i].id;
			array.push(id);
		}
		var functionIds = array.join(",");
	 $("input[name=functionRoleIds]").val(functionIds);
	 $.ajax({
		 url:path+"/sys/sysrole/addRoleInfo.do",
		 type:"post",
		 data:$("#addRoleForm").serialize(),
		 success:function(data){
			 refreshTable();
		 }
	 });
	
 }
 
 
 function delRole(obj){

	 var rowIds = $("#userinfo").jqGrid("getGridParam","selarrrow");
	 var selRowIds="";
	 for(var i=0;i<rowIds.length;i++){
		 if(i==(rowIds.length-1)){
			 selRowIds += rowIds[i];
		 }else{
			 selRowIds += rowIds[i]+",";
		 }
		 
	 }
	 //var rowData = $("#userinfo").jqGrid("getRowData",rowId);
	 if(selRowIds=="") {alert("请选择需要删除的行");return;}
	// var param = {"roleId":rowId};
	 $.ajax({
		 url:path+"/sys/sysrole/delRoleInfo.do",
		 type:"post",
		 datatype:"json",
		 data:{roleId : selRowIds},
		 success:function(result){
			 refreshTable();
		 }
	 });
	 
	
	 
 }
 function refreshTable(){
	 jQuery("#userinfo").jqGrid({
         url        :path + "/sys/sysrole/pageQuerySysRole.do",
         datatype   : "json",                              //从服务器端返回的数据类型，（表格期望接收的数据类型）。可选类型：xml，xmlstring，json，local，function
         jsonReader :{
             root:"rows",
             page:"currentPage",
             total:"total",
             records:"records",
             repeatitems:false
         }
     }).trigger("reloadGrid");
 }
 
 
 
 
 
 
 
 
 
 
 
 