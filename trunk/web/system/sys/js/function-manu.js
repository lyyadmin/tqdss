/**
	id varchar 32  0   0   0   0   0   0       0       utf8    utf8_general_ci     -1  0
	name    varchar 100 0   -1  0   0   0   0       0   资源路径    utf8    utf8_general_ci     0   0
	code    varchar 30  0   -1  0   0   0   0       0       utf8    utf8_general_ci     0   0
	type    varchar 50  0   -1  0   0   0   0       0   资源类型    utf8    utf8_general_ci     0   0
	url varchar 200 0   -1  0   0   0   0       0   点击后前往的地址    utf8    utf8_general_ci     0   0
	parent_id   varchar 32  0   -1  0   0   0   0       0   父编号 utf8    utf8_general_ci     0   0
	parent_ids  varchar 1000    0   -1  0   0   0   0       0   父编号列表   utf8    utf8_general_ci     0   0
	permission  varchar 100 0   -1  0   0   0   0       0   权限字符串   utf8    utf8_general_ci     0   0
	isshow  tinyint 1   0   -1  0   0   0   0   0   0   是否显示                0   0
	sort    int 5   0   -1  0   0   0   0       0   排序              0   0
	menu_icon   varchar 255 0   -1  0   0   0   0       0   图标  utf8    utf8_general_ci     0   0
	remarks varchar 255 0   -1  0   0   0   0       0   摘要  utf8    utf8_general_ci     0   0
	create_by   varchar 32  0   -1  0   0   0   0       0       utf8    utf8_general_ci     0   0
	create_date datetime    0   0   -1  0   0   0   0       0                   0   0
	update_by   varchar 32  0   -1  0   0   0   0       0       utf8    utf8_general_ci     0   0
	update_date datetime    0   0   -1  0   0   0   0       0                   0   0
	del_flag    char    1   0   -1  0   0   0   0       0       utf8    utf8_general_ci     0   0
*/
$(function(){
	$(".modal-dialog").draggable();
    var colModel = [
                    {name: "id",index: "id",align: "center", sorttype: "string",hidden:true,editable : true}, 
                    {name: "name",index: "name", align: "center",editable : true}, 
                    {name: "code",index: "code",align: "center", sorttype: "string",editable : true}, 
                    {name: "type",index: "type", align: "center",editable : true},
                    {name: "url",index: "url", align: "center",editable : true}, 
                    {name: "permission",index: "permission", align: "center",editable : true},
                    {name: "remarks",index: "remarks",align: "center",editable : true},
                    {name: "create_date",index: "create_date",align: "center",editable : true},
                    {name: "update_date",index: "update_date",align: "center",editable : true}
                ];
    var colNames = ["id","名称","关键字", "类型", "连接地址", "权限","备注","创建时间","更新时间"];
    getGrid("userinfo","","page",colModel,colNames,"");
 });
 function getGrid(tblId,postData,pageId,colModel,colNames,data){
     jQuery("#"+tblId).jqGrid({
         url        :path + "/system/functionmenu/pageQueryFunctionMenu.do",
         datatype   : "json",                              //从服务器端返回的数据类型，（表格期望接收的数据类型）。可选类型：xml，xmlstring，json，local，function
         height     : "70%",                               //高度
         colNames   :colNames,                             //列名
         colModel   :colModel,
         caption    : "",                                   //标题
         pager      : "#"+pageId,                           //#page分页控件绑定的位置对象
         rowNum     : 20,                                  //每页显示条数
         rowList    : [20,50,100],                          //分页下拉选项内容
         rownumbers : true,                                 //是否显示行数
         viewrecords: true,                                 //是否显示总条数
         emptyrecords: "无数据",                              //服务器返回空列表时显示的内容
         autowidth  : true,                                 //自动调节宽度
         sortname   : 'SORT',                               //按SORT进行排序 默认asc
         caption : "功能权限管理",
         jsonReader :{
             root:"rows",
             page:"currentPage",
             total:"total",
             records:"records",
             repeatitems:false
         },
         editurl:path+"/system/functionmenu/updateFunctionMenu.do"
     });
     jQuery("#"+tblId).jqGrid("navGrid",pageId,{edit:false,add:false,del:false});
//     tenlyStyle();
 }
 /**
  * 添加
  * @param obj
  */
 function addRow(obj){
	 insertOption();
	 $("#myModal").modal();
 }
/**
 * 修改
 */
 function update(obj){
	 var gr = jQuery("#userinfo").jqGrid('getGridParam', 'selrow');
	 var rowData = jQuery("#editgrid").jqGrid('getRowData', gr);
	 if(gr!=null){
		 $("#userinfo").jqGrid("editGridRow",gr,{
			reloadAfterSubmit:false
		 });
	 }else{
		 swal("请选择更新的行！");
	 }
 }
 /**
  * 修改
  */ 
  function del(obj){
	  var gr = jQuery("#userinfo").jqGrid('getGridParam', 'selrow');
	  if(gr!=null){
		  var rowData = jQuery("#editgrid").jqGrid('getRowData', gr);
		  swal({
			  title:"确定删除吗？",
			  text:"",
			  type:"warning",
			  showCancelButton:true,
			  confirmButtonColor:"#DD6B55",
			  confirmButtonText:"确定",
			  closeOnConfirm:true
		  },function(result){
			  if(result){
				  $.post(path+"/system/functionmenu/delFunctionMenu.do",{id:rowData.id},function(result){});
			  }
		  });
	  }else{
		  swal("请选择删除的行！");
	  }
  }
  
  /**
   * 提交按钮
   */
  function submits(obj){
//	  validateForm();
//	  $("#addForm").bootstrapValidator("validate");
	  $.post(path+"/system/functionmenu/addFunctionMenu.do",$("#addForm").serialize(),function(result){});
	  $("#userinfo").trigger("reloadGrid");
  }
  /*
   * 为select赋值
   */
  function insertOption(){
	  var selectDom = $("#parentId");
	  selectDom.empty();
	  $.post(path+"/system/functionmenu/findAll.do",function(result){
		  for(var i = 0;i<result.length;i++){
			  if(i==0){
				  selectDom.append("<option value='0'>-------顶级级菜单-------</option>");
			  }
			  selectDom.append("<option value='"+result[i].parentId+"'>"+result[i].name+"</option>");
		  }
		  $("#parentId").selectpicker("refresh");
	  });
  }
  
  /**
   * 验证form表单
   */
  function validateForm(){
//	  $("#addForm").bootstrapValidator({
//		  message:"this is value valid",
//		  feedbackIcons:{
//			  valid:"glyphicon glyphicon-ok",
//			  invalid:"glyphicon glyphicon-remove",
//			  validating:"glyphicon glyphicon-refresh"
//		  },
//		  fields:{
//			  remarks:{
//				  message:"名称验证失败",
//				  validators:{
//					  notEmpty:{
//						  message:"名称不能为空"
//					  }
//				  }
//			  }
//		  }
//	  });
  }