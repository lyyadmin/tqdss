/**
	 id  varchar 32  0   0   0   0   0   0       0   主键  utf8    utf8_general_ci     -1  0
	 realname    varchar 255 0   -1  0   0   0   0       0   真实名称    utf8    utf8_general_ci     0   0
	 username    varchar 100 0   0   0   0   0   0       0   用户名 utf8    utf8_general_ci     0   0
	 portrait    varchar 250 0   -1  0   0   0   0       0   头像  utf8    utf8_general_ci     0   0
	 password    varchar 100 0   -1  0   0   0   0       0   密码  utf8    utf8_general_ci     0   0
	 salt    varchar 100 0   -1  0   0   0   0       0       utf8    utf8_general_ci     0   0
	 email   varchar 20  0   -1  0   0   0   0       0   邮件  utf8    utf8_general_ci     0   0
	 phone   varchar 20  0   -1  0   0   0   0       0   联系电话    utf8    utf8_general_ci     0   0
	 status  varchar 255 0   -1  0   0   0   0       0   系统用户的状态 utf8    utf8_general_ci     0   0
	 create_by   varchar 32  0   -1  0   0   0   0       0       utf8    utf8_general_ci     0   0
	 create_date datetime    0   0   -1  0   0   0   0       0                   0   0
	 update_by   varchar 32  0   -1  0   0   0   0       0       utf8    utf8_general_ci     0   0
	 update_date datetime    0   0   -1  0   0   0   0       0                   0   0
	 remarks varchar 255 0   -1  0   0   0   0       0       utf8    utf8_general_ci     0   0
	 del_flag    char    1   0   -1  0   0   0   0       0       utf8    utf8_general_ci     0   0
*/
$(function(){
	$(".modal-dialog").draggable();
	pageInit();
});


/**
 * 测试
 */
function pageInit(){
	   jQuery("#editgrid").jqGrid(
	      {
	        url : path + "/system/usermanager/userinfo.do",
	        datatype : "json",
	        colNames:["id", "用户名称", "真实姓名", "邮箱", "电话号码", "角色名称", "所属机构", "是否删除", "英文名称", "备注", "创建时间","更新时间" ],  //列名
			colModel:[ {
				name : "id",
				index : "id",
				align : "center",
				hidden:true,
				sorttype : "string",
				 editable : true
			}, {
				name : "username",
				index : "username",
				align : "center",
				 editable : true
			}, {
				name : "realname",
				index : "realname",
				align : "center",
				sorttype : "string",
				 editable : true
			}, {
				name : "email",
				index : "email",
				align : "center",
				 editable : true
			}, {
				name : "phone",
				index : "phone",
				align : "center",
				 editable : true
			}, {
				name : "name",
				index : "name",
				align : "center",
				 editable : true
			}, {
				name : 'orginazition',
				index : 'orginazition',
				align : "center",
				editable : true,
				formatter : function(cellvalue, options, rowObj){
            		return "<span id='" + rowObj.id + "' class='organizations' style='cursor:pointer; display:block; width:100%; text-decoration:underline;'>点击查看</span>";
            	}
			}, {
				name : "del_flag",
				index : "del_flag",
				align : "center",
				 editable : true
			}, {
				name : "code",
				index : "code",
				align : "center",
				 editable : true
			}, {
				name : "remarks",
				index : "remarks",
				align : "center",
				 editable : true
			}, {
				name : "create_date",
				index : "create_date",
				align : "center",
				 editable : true
			}, {
				name : "update_date",
				index : "update_date",
				align : "center",
				 editable : true
			}],
			multiselect : true,
	        rowNum : 10,
	        rowList : [ 10, 20, 30 ],
	        pager : '#pagered',
	        sortname : 'id',
	        viewrecords : true,
	        height:"70%",
	        autowidth:true,
	        caption : "用户管理",
	        jsonReader: {
		        root: "rows",	//数据行
		        page: "currentPage",	// 当前页
		        total: "total",	// 总页数
		        records: "records",	//总共有几条记录
		        repeatitems: false	// 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
		    }
	      });
	   
	  //修改事件
	  $("#updateUserInfo").click(function() {
		  var gr = jQuery("#editgrid").jqGrid('getGridParam', 'selrow');
		  if(gr!=null){
			  var rowData = jQuery("#editgrid").jqGrid('getRowData', gr);
			  var dom = $("#role");
			  $.post(path+"/system/usermanager/findAll.do",function(result){
				  var sysRoles = result["sysRoles"];
				  dom.empty();
				  for(var i = 0;i<sysRoles.length;i++){
					 dom.append("<option value='"+sysRoles[i].id+"'>"+sysRoles[i].name+"</option>");
				  }
				  
				  var sysOrganizations = result["sysOrganizations"];
				  var objArr = [];
				 for(var i = 0;i<sysOrganizations.length;i++){
					 var obj = {};
					 obj.id = sysOrganizations[i].id;
					 obj.name = sysOrganizations[i].name;
					 obj.pId = sysOrganizations[i].parent_id;
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
						   },callback:{
							   onClick:function(){
								   ///alert(11)
							   },
							   onCheck:function(){
								   //alert(11)
							   }
						   }
				    }
				   $.fn.zTree.init($("#updateOrganizationsTree"), setting, objArr);
			  });
			  $("#editgrid").jqGrid("GridToForm",gr,"#addForm");
			  $("#updateModal").modal();
		  }
		  else{
			  alert("请选中行");
		  }
	  });
	  
	  jQuery("#editgrid").on("click", ".organizations", function(){
		  var value = $(this).text();
		  var id = $(this).attr("id");
		  alert(id);
	  });
	}

/**
 * 提交更新
 * @param obj
 * @returns
 */
function submits(obj){
	$.ajax({
		url:path+"/system/usermanager/updateinfo.do",
		type:"post",
		dataType:"json",
		data:$("#addForm").serialize(),
		success:function(result){}
	});
}
/**
 * 新增用户 模态框
 * @param obj
 */
function addRowModal(obj){
	 var dom = $("#addrole");
	 var group = $("#group");
	 $.post(path+"/system/usermanager/findAll.do",function(result){
		  var sysRoles = result["sysRoles"];
		  dom.empty();
		  for(var i = 0;i<sysRoles.length;i++){
			 dom.append("<option value='"+sysRoles[i].id+"'>"+sysRoles[i].name+"</option>");
		  }
		  
		  var sysOrganizations = result["sysOrganizations"];
		  var objArr = [];
		 for(var i = 0;i<sysOrganizations.length;i++){
			 var obj = {};
			 obj.id = sysOrganizations[i].id;
			 obj.name = sysOrganizations[i].name;
			 obj.pId = sysOrganizations[i].parent_id;
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
				   },callback:{
					   onClick:function(){
						   ///alert(11)
					   },
					   onCheck:function(){
						   //alert(11)
					   }
				   }
		    }
		   $.fn.zTree.init($("#addOrganizationsTree"), setting, objArr);
	  });
	$("#addModal").modal();
};
function addUser(obj){
	var username = $("#username_add").val();
	if (username == "") {
		swal("","用户名称不能为空","error");
		return;
	}
	var realname = $("#realname_add").val();
	if (realname == "") {
		swal("","真实姓名不能为空","error");
		return;
	}
	var oldPassword = $("#oldPassword_add").val();
	var newPaswword = $("#newPaswword_add").val();
	if (oldPassword != newPaswword) {
		swal("","两次输入密码不一样，请重新输入","error");
		$("#oldPassword_add").val("");
		$("#newPaswword_add").val("");
		return;
	}
	var organizations = $.fn.zTree.getZTreeObj("addOrganizationsTree").getCheckedNodes(true);
	if (organizations.length == 0) {
		swal("","请选择组织架构","error");
		return;
	}
	var organizationsId = new Array();
	for (var i = 0; i < organizations.length; i++) {
		organizationsId.push(organizations[i].id);
	}
	
	$.ajax({
		url:path+"/system/usermanager/add.do",
		type:"post",
		dataType:"json",
		data:{
			username : $("#username_add").val(),
			realname : $("#realname_add").val(),
			oldPassword : $("#oldPassword_add").val(),
			newPaswword : $("#newPaswword_add").val(),
			email : $("#email_add").val(),
			phone : $("#phone_add").val(),
			roleId : $("#addrole option:selected").val(),
			organizations : organizationsId.join(",")
		},
		success:function(result){
			 $("#addModal").modal("hide");
			 $("#editgrid").jqGrid("clearGridData");
			 $("#editgrid").jqGrid("setGridParam",{
				 datatype:"json",
				 url:path + "/system/usermanager/userinfo.do"
			 }).trigger("reloadGrid");
		}
	});
}













