var myCharts = undefined;
var myChartsArea = undefined;
var myWgCharts = undefined;
var myChartsProRato = undefined;
var myChartsirregularitiesTimesObj = undefined;
var queryItemIrregularitiesJL = undefined;
var targetOfViolationCharts = undefined;
var violationOfStandardCharts = undefined;
var myChartsstrongItem = undefined;
var myChartsLessItem = undefined;
var colors = ['#E53935', '#E6C104', '#4D81E1', '#F39800', '#1D5DFA'];
//传递字段名称和值
var field_name;
var field_value;

$(function () {
    //表格拖拽
    initall();
});

//初始化
function initall(){
    //格式化初始日期
    $(".habits-date").datetimepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        language: 'zh-CN',
        startView: 2,
        minView: 2,
        forceParse: false,
        pickerPosition: "bottom-left"
    });

    $("#startDate").val(getLeftDate(getCurrentSystemDate(),100));
    $("#endDate").val(getCurrentSystemDate());
    //筛选条件,作业项点的选择
    findAllItemName( "taskSceneHabits", "taskItemHabits", "taskItemPointHabits", "taskProblemHabits");
    getBindClikWithItem("taskSceneHabits", "taskItemHabits", "taskItemPointHabits", "taskProblemHabits");
    //强弱项,绘制echarts图形
    initHibitsEcharts();
    //查询乘务员评价
    initEvaluateEcharts();
    //执标分析查询按钮
    $("#opHibitsQuery").click(function () {
        //强弱项
        initHibitsEcharts();
        //评价分析图表
        initEvaluateEcharts();
    });

    //查询方式，为查询方式绑定一个change事件
    $("#queryWay").change(function () {
        var dom = $("#conditionlabel");
        var wayl = this.value;
        getDomAppendCondition(wayl, dom);
    });

    /**
     * 操作项点
     */
    $("#tabItemPoint").unbind("click").bind("click", function () {
        //日期初始化
        $(".form_datetime").datetimepicker({
            format: "yyyy-mm-dd",
            autoclose: true,
            todayBtn: true,
            startView: 2,
            minView: 2,
            language: 'zh-CN',
            pickerPosition: "bottom-left"
        });
        $("#start").val(getLeftDate(getCurrentSystemDate(), 100));
        $("#end").val(getCurrentSystemDate());
        findAllItemName("taskScene", "taskItem", "taskItemPoint", "taskProblem");
        //查询项点和初始化图形
        initEcharts();
    });

    //项点分析查询按钮
    $("#itemQuery").click(function () {
        var condititonForm = $("#itemPontForm").serializeObject();
        condititonForm.driver_code = driver_code;
        $.ajax({
            url: path + "/checkout/checkOutBuffetQuery/queryItemConditionOne.do",
            type: "post",
            dataType: "json",
            data: condititonForm,
            success: function (data) {
                initPar(data);
            }
        });

    });
    getBindClikWithItem("taskScene","taskItem","taskItemPoint","taskProblem");
    //退勤历史查询页
    $("#tabCheckOutHostorySearch").unbind("click").bind("click", function () {
        $("#ondutytime").val(getLeftDate(getCurrentSystemDate(),100));
        $("#offdutytime").val(getCurrentSystemDate());
        jeDate("#ondutytime",{
            format: "YYYY-MM-DD",
            onClose:false,
            isClear:false,
            isToday:false
        });
        jeDate("#offdutytime",{
            format: "YYYY-MM-DD",
            onClose:false,
            isClear:false,
            isToday:false
        });
        tuiQinInfo();
        //初始化交路图
        $tenly.init('canvas', 'graphDivId', path, []);
        $tenly.setItemClick(function (item) {
            if (item.lkj_isnotexits != undefined && item.lkj_isnotexits == "0") {
                dataLose = null;
                dataLose = item;
            }
        });
    });
    $("#checkInAndOutSearch").click(function(){
        tuiQinInfo();
    });
}


function getBindClikWithItem(taskScene,taskItem,taskItemPoint,taskProblem){
    $("#"+taskScene).change(function () {
        //添加所需要执行的操作代码
        var selvalue = $("#"+taskScene+" option:selected").val();
        if (selvalue != "0") {
            getItemAllChildren(selvalue, taskScene, 1,taskScene,taskItem,taskItemPoint,taskProblem);
        } else {
            findAllItemName(taskScene, taskItem, taskItemPoint, taskProblem);
        }
    });
    $("#"+taskItem).change(function () {
        //添加所需要执行的操作代码
        var selvalue = $("#"+taskItem+" option:selected").val();
        if (selvalue != "0") {
            getItemAllChildren(selvalue, taskItem, 2,taskScene,taskItem,taskItemPoint,taskProblem);
        }
    });
    $("#"+taskItemPoint).change(function () {
        //添加所需要执行的操作代码
        var selvalue = $("#"+taskItemPoint+" option:selected").val();
        if (selvalue != "0") {
            getItemAllChildren(selvalue, taskItemPoint, 3,taskScene,taskItem,taskItemPoint,taskProblem);
        }
    });
}


function initItemToHabits(idName) {
    var taskSceneDom = $("#" + idName);
    taskSceneDom.empty();
    taskSceneDom.append("<option value='0' selected='true'>全部</option>");
    return taskSceneDom;
}

function initItemSelect(data, taskScene, taskItem, taskItemPoint, taskProblem) {
    var taskSceneDom = initItemToHabits(taskScene);

    var taskItemDom = initItemToHabits(taskItem);

    var taskItemPointDom = initItemToHabits(taskItemPoint);

    var taskProblemDom = initItemToHabits(taskProblem);
    var ctItemSence = 0;
    var ctItem = 0;
    for (var i = 0; i < data.length; i++) {
        var itemId = "" + data[i].id;
        if (itemId.length < 3) {
            //作业场景
            ctItemSence++;
            if(data[i].name!=""&&data[i].name!=null&&data[i].name!=undefined) {
                taskSceneDom.append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
            }
        } else if (3 <= itemId.length && itemId.length < 5) {
            //作业项目
            ctItem++;
            if(data[i].name!=""&&data[i].name!=null&&data[i].name!=undefined){
                taskItemDom.append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
            }
        } else if (5 <= itemId.length && itemId.length < 7) {
            //作业项点
            if(data[i].name!=""&&data[i].name!=null&&data[i].name!=undefined) {
                taskItemPointDom.append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
            }
        } else {
            //作业问题
            if(data[i].name!=""&&data[i].name!=null&&data[i].name!=undefined) {
                taskProblemDom.append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
            }
        }
    }
}


/**
 * 获取选中作业项点的下级
 * @param selValue
 * @param selId
 */
function getItemAllChildren(selValue, selId, level,taskScene,taskItem,taskItemPoint,taskProblem) {
    $.ajax({
        url: path + "/checkout/checkOutBuffetQuery/queryItemByLevel.do",
        type: "post",
        dataType: "json",
        data: {"itemValue": selValue, "level": level},
        success: function (data) {
            if (level == 1) {
                getIteretorListByselId(data.childrenList, taskItem);//作业项目
                getIteretorListByselId(data.pointChildrenList, taskItemPoint);//作业项点
                getIteretorListByselId(data.proChildrenList, taskProblem);//作业问题
            } else if (level == 2) {
                getIteretorListByselId(data.pointChildrenList, taskItemPoint);
                getIteretorListByselId(data.proChildrenList, taskProblem);
            } else if (level == 3) {
                getIteretorListByselId(data.proChildrenList, taskProblem);
            }
        }
    });
}

function getIteretorListByselId(objList, selId) {
    var dom = $("#" + selId);
    dom.empty();
    dom.append("<option value='0'>全部</option>");
    for (var i = 0; i < objList.length; i++) {
        dom.append("<option value='" + objList[i].id + "'>" + objList[i].name + "</option>");
    }
}

/**
 * 查询所有项点（作业场景、作业问题、作业项目、作业问题）
 */
function findAllItemName(taskSceneHabits, taskItemHabits, taskItemPointHabits, taskProblemHabits) {
    $.ajax({
        async: false,
        url: path + "/checkout/checkOutBuffetQuery/findAllItemName.do",
        type: "post",
        dataType: "json",
        success: function (data) {
            initItemSelect(data,taskSceneHabits, taskItemHabits, taskItemPointHabits, taskProblemHabits);
        }
    });
}

/**
 * 操作项点分析数据请求
 */
function initEcharts() {
    var condititonForm = $("#itemPontForm").serializeObject();
    condititonForm.driver_code = driver_code;
    $.ajax({
        url: path + "/checkout/checkOutBuffetQuery/queryItemConditionOne.do",
        type: "post",
        dataType: "json",
        data: condititonForm,
        success: function (data) {
            //交路分布、时段分布
            initPar(data);
        }
    });
}

/**
 * 作业执标分析强弱项请求数据
 */
function initHibitsEcharts() {
    var condititonForm = $("#conditionForm").serializeObject();
    condititonForm.driver_code = driver_code;
    $.ajax({
        url: path + "/checkout/checkOutBuffetQuery/queryItemConditionTwo.do",
        type: "post",
        dataType: "json",
        data: condititonForm,
        success: function (data) {
            operationStrongLess(data);
        }
    });
}

function getX(data, key, Suffix) {
    var date = [];
    for (var j = 0; j < data.length; j++) {
        date.push(data[j][key] + Suffix);
    }
    return date;
}

function getChartsObj(legend, irregularitiesTimesObj, key, Suffix,position,sort) {
    var o =  {
        color: colors,
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data: legend
        },
        grid: {
            left: '3.5%',
            right: '4%',
            bottom: '5%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            data: getX(irregularitiesTimesObj, key, Suffix)
        },
        yAxis: [
            {   name:"作业次数",
                type: 'value',
                minInterval:1
            },{
                name:"百分比",
                type:"value",
                axisLabel: {
                    formatter: '{value} %'
                }
            }
        ],
        series: getSeries(irregularitiesTimesObj, legend,sort)
    };
    var start = 30;
    var end = 70;
    if(position=="2"){  //设置x可以默认显示多长的数据
        start = 0;
        end = 100;
    }
    o.dataZoom=[
        {
            show: true,
            realtime: true,
            start: start,
            end: end,
            botttom: 0
        },
        {
            type: 'inside',
            realtime: true,
            start: 65,
            end: 85
        }
    ];


    return o;
}

/**
 * 项点分析 四个图形初始化
 * @param obj
 */
function initPar(obj) {
    //时段分布
    var irregularitiesTimesObj = obj.queryItemIrregularitiesTimes;
    myChartsirregularitiesTimesObj = echarts.init(document.getElementById("violationTimePeriod"));
    var legend = ["A类", "B类", "C类", "占比"];
    var timeSlotOption = getChartsObj(legend, irregularitiesTimesObj, "hour_time", "点","2","not");
    myChartsirregularitiesTimesObj.setOption(timeSlotOption, true);
    myChartsirregularitiesTimesObj.off("click");
	myChartsirregularitiesTimesObj.on("click", function (param) {
		field_name="startTime";
        field_value=param.name;
		var o = commonGridParm(field_name,field_value,param);
        $("#taskPeriodModal").modal();
        var data = getCommonGridData(o);
	    getCommonGrid(data,"taskPeriodGrid","operatePeriodFormatter");

	});

    //违标线别分布
    var data = obj.queryItemIrregularitiesJL;
    queryItemIrregularitiesJL = echarts.init(document.getElementById("besiegedVehicle"));
    var option = getChartsObj(legend, data, "line_name", "","","not");
    queryItemIrregularitiesJL.setOption(option, true);
    queryItemIrregularitiesJL.off("click");
	queryItemIrregularitiesJL.on("click", function (param) {
		field_name="line_name";
        field_value=param.name;
		var o = commonGridParm(field_name,field_value,param);
        $("#taskLineModal").modal();
        var data = getCommonGridData(o);
	    getCommonGrid(data,"taskLineGrid","operateLineFormatter");
	});

    //违标区段分布
    var queryItemIrregularitiesArea = obj.queryItemIrregularitiesArea;
    targetOfViolationCharts = echarts.init(document.getElementById("targetOfViolation"));
    var areaOption = getChartsObj(legend, queryItemIrregularitiesArea, "zhandian", "","","middle");
    targetOfViolationCharts.setOption(areaOption, true);
    targetOfViolationCharts.off("click");
	targetOfViolationCharts.on("click", function (param) {
		field_name="zhandian";
        field_value=param.name;
		var o = commonGridParm(field_name,field_value,param);
        $("#taskZoneModal").modal();
        var data = getCommonGridData(o);
	    getCommonGrid(data,"taskZoneGrid","operateZoneFormatter");

	});

    //作业违标分布
    var queryItemIrregularitiesPoint = obj.queryItemIrregularitiesPoint;
    violationOfStandardCharts = echarts.init(document.getElementById("violationOfStandard"));
    var violationOption = getChartsObj(legend, queryItemIrregularitiesPoint, "new_code_name", "","2","desc");
    violationOfStandardCharts.setOption(violationOption, true);
    violationOfStandardCharts.off('click');
    violationOfStandardCharts.on("click", function (param) {
   		var o = $("#itemPontForm").serializeObject();
        o.driver_code = driver_code;
        o.item_code_name = param.data.tag;
    	if (param.seriesName === 'A类') {
    		o.problem_level='A';
        }else if(param.seriesName === 'B类'){
        	o.problem_level='B';
        }else if(param.seriesName === 'C类'){
        	o.problem_level='C';
        }
        $("#taskViolationModal").modal();
         var data = getViolationGridData(o);
	     getViolationGrid(data);
    });
}

 function commonGridParm(field_name,field_value,param){
    	var o = $("#itemPontForm").serializeObject();
        o.driver_code = driver_code;
        o.field_name=field_name;
        o.field_value=field_value;
		if (param.seriesName === 'A类') {
    		o.problem_level='A';
        }else if(param.seriesName === 'B类'){
        	o.problem_level='B';
        }else if(param.seriesName === 'C类'){
        	o.problem_level='C';
        }
        return o;
}

/**
 * 线别、区段、时段项点分布
 * @param obj
 * 违标分布下钻出来的父子表格
 */
function getCommonGridData(obj){
    var res = null;
    $.ajax({
        async:false,
        url:path+"/checkout/checkOutBuffetQuery/getCommonDetail.do",
        type:"post",
        dataType:"json",
        data:obj,
        success:function(data){
            res = data;
        }
    });
    return res;
}
/**
 * 违标项点分布
 * @param obj
 * 违标分布下钻出来的父子表格
 */
function getViolationGridData(obj){
    var res = null;
    $.ajax({
        async:false,
        url:path+"/checkout/checkOutBuffetQuery/getViolationDetail.do",
        type:"post",
        dataType:"json",
        data:obj,
        success:function(data){
            res = data;
        }
    });
    return res;
}
//作业违标分布   下钻表格数据 初始化
function getViolationGrid(ol) {
    var $table = $("#taskViolationGrid");
    $table.bootstrapTable('destroy');
    $table.bootstrapTable({
        data: ol,
        idField: 'id',
        dataType: 'json',
        columns: [
            {field: 'item_name', title: '项点名称'},
            {field: 'ct_scenario_code', title: '违标次数', sortable: true, align: 'center',
                formatter:function(value,row,index){
                    if(row.ct_scenario_code==''||row.ct_scenario_code==null||row.ct_scenario_code==undefined){
                        return '0';
                    }else{
                        return row.ct_scenario_code;
                    }
                }},
            {field: 'scan_detail', title: '查看详情', align: 'center',
                events : operateEvents,formatter:"operateFormatter"
            }
        ],
        //在哪一列展开树形
        treeShowField: 'item_name',
        //指定父id列
        parentIdField: 'pid',
        onResetView: function (data) {
            $table.treegrid({
                initialState: 'collapsed',// 所有节点都折叠
                // initialState: 'expanded',// 所有节点都展开，默认展开
                treeColumn: 0,
                onChange: function () {
                    $table.bootstrapTable('resetWidth');
                }
            });
            //只展开树形的第一级节点
            $table.treegrid('getRootNodes').treegrid('expand');
        },
        onCheck: function (row) {
        },
        onUncheck: function (row) {
        }
    });
}

//线别、区段、时段分布   下钻表格数据 初始化
function getCommonGrid(ol,id,fun) {
    var $table = $("#"+id);
    $table.bootstrapTable('destroy');
    $table.bootstrapTable({
        data: ol,
        idField: 'id',
        dataType: 'json',
        columns: [
            {field: 'item_name', title: '项点名称'},
            {field: 'ct_scenario_code', title: '违标次数', sortable: true, align: 'center',
                formatter:function(value,row,index){
                        if(row.ct_scenario_code==''||row.ct_scenario_code==null||row.ct_scenario_code==undefined){
                            return '0';
                        }else {
                            return row.ct_scenario_code;
                        }
                }},
            {field: 'scan_detail', title: '查看详情', align: 'center',
                events : operateEvents,formatter:fun
            }
        ],
        //在哪一列展开树形
        treeShowField: 'item_name',
        //指定父id列
        parentIdField: 'pid',
        onResetView: function (data) {
            $table.treegrid({
                initialState: 'collapsed',// 所有节点都折叠
                // initialState: 'expanded',// 所有节点都展开，默认展开
                treeColumn: 0,
                onChange: function () {
                    $table.bootstrapTable('resetWidth');
                }
            });
            //只展开树形的第一级节点
            $table.treegrid('getRootNodes').treegrid('expand');
        },
        onCheck: function (row) {
        },
        onUncheck: function (row) {
        }
    });
}
// 格式化按钮
function operateFormatter(value, row, index) {
    if(row.scan_detail=="1"){
        return [
            '<button type="button" class="RoleOfadd btn btn-primary"   ' +
            'style="color: #0000FF;text-decoration: underline;' +
            'cursor: pointer;background: transparent;border: 0;outline: none;">查看全部作业</button>'
        ].join('');
    }
    return "";
}

function operateLineFormatter(value, row, index) {
    if(row.scan_detail=="1"){
        return [
            '<button type="button" class="RoleOfLine btn btn-primary"   ' +
            'style="color: #0000FF;text-decoration: underline;' +
            'cursor: pointer;background: transparent;border: 0;outline: none;">查看全部作业</button>'
        ].join('');
    }
    return "";
}
function operateZoneFormatter(value, row, index) {
    if(row.scan_detail=="1"){
        return [
            '<button type="button" class="RoleOfZone btn btn-primary"   ' +
            'style="color: #0000FF;text-decoration: underline;' +
            'cursor: pointer;background: transparent;border: 0;outline: none;">查看全部作业</button>'
        ].join('');
    }
    return "";
}
function operatePeriodFormatter(value, row, index) {
    if(row.scan_detail=="1"){
        return [
            '<button type="button" class="RoleOfPeriod btn btn-primary"   ' +
            'style="color: #0000FF;text-decoration: underline;' +
            'cursor: pointer;background: transparent;border: 0;outline: none;">查看全部作业</button>'
        ].join('');
    }
    return "";
}

function getLKJBtn(value, row, index){
        return [
            '<button type="button" class="queryLKJDetail btn btn-primary"   ' +
            'style="color: #0000FF;text-decoration: underline;' +
            'cursor: pointer;background: transparent;border: 0;outline: none;">查看数据明细</button>'
        ].join('');
}

//初始化操作按钮的方法
window.operateEvents = {
	//违标项点
    'click .RoleOfadd': function (e, value, row, index) {
        //操作业务 add(row.id);
        var o = {id:row.id,driver_code:row.driver_code,pid:row.pid};
        var itemForm = $("#itemPontForm").serializeObject();
        problemViolationGridDetail(o,itemForm);
        $("#problemViolationModal").modal();
    },
    //线别违标
    'click .RoleOfLine': function (e, value, row, index) {
        var o = {id:row.id,driver_code:row.driver_code,pid:row.pid};
        var itemForm = $("#itemPontForm").serializeObject();
        problemCommonGridDetail("problemLineGridDetail",o,itemForm);
        $("#problemLineModal").modal();
    },
    //时段违标
    'click .RoleOfPeriod': function (e, value, row, index) {
        var o = {id:row.id,driver_code:row.driver_code,pid:row.pid};
        var itemForm = $("#itemPontForm").serializeObject();
        problemCommonGridDetail("problemPeriodGridDetail",o,itemForm);
        $("#problemPeriodModal").modal();
    },
    //区段违标
    'click .RoleOfZone': function (e, value, row, index) {
        var o = {id:row.id,driver_code:row.driver_code,pid:row.pid};
        var itemForm = $("#itemPontForm").serializeObject();
        problemCommonGridDetail("problemZoneGridDetail",o,itemForm);
        $("#problemZoneModal").modal();
    },
    //LKJ点击事件，查看数据明细
    'click .queryLKJDetail': function (e, value, row, index) {
        $("#queryLKJModal").modal();
        findHostoryRecord(row);
    }

};
//项点分析查询
function problemCommonGridDetail(id,obj,itemForm){
    obj.startDate = itemForm.startDate;
    obj.endDate = itemForm.end;
    obj.driver_code = driver_code;
    obj.field_name=field_name;
    obj.field_value=field_value;
    var $tables = $("#"+id);
    $tables.bootstrapTable('destroy');
    $tables.bootstrapTable({
        url: path + "/checkout/checkOutBuffetQuery/queryProblemCommonDetail.do",         //请求后台的URL（*）
        dataType:"json",
        async:false,
        method: 'post',                      //请求方式（*）
        pagination: true,
        paginationLoop:false,
        paginationPreText:'上一页',
        paginationNextText:'下一页',
        showPaginationSwitch:false,			//是否显示数据条数选择框
        pageSize:"10",
        pageNumber: "1",
        pageList: [10,10,20],
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        order: "asc",
        sortName:'',
        limit:1,
        offset: 10,
        queryParamsType:"",
        queryParams:  function queryParams(params) {   //设置查询参数
            obj.page = params.pageNumber;
            obj.pageSize = params.pageSize;
            return obj;
        },				//传递参数（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        contentType:"application/x-www-form-urlencoded;charset=UTF-8",
        clickToSelect: true,                //是否启用点击选中行
        height: "auto",                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        detailView: false,                   //是否显示父子表
        responseHandler: function (res) {   //在ajax请求成功后，填充数据之前可以对返回的数据进行处理
            var data=res["rows"];
            return {"total": res.total,"pageNumber":res.currentPage,"rows": data};
        },
        columns: [{field: 'ids',title: "序号",align: 'center',
            formatter:function(value,row,index){
                return index+1;
            }},
          {field: 'date',title: "开车日期",align: 'center',formatter:function(value,row,index){
                return formtterDate(row.date);
           }},
          {field: 'startTime',title: "作业时间",align: 'center'},
          {field: 'train_batch_no',title: "车次",align: 'center'},
          {field: 'zhandian',title: "站点",align: 'center'},
          {field: "memo",title: '问题描述',align: 'center'},
          {field: "qualified",title: '是否达标',align: 'center'},
          {field:"dataDetailForLKJ",title:"查看详情",align:"center", events:operateEvents,formatter:"getLKJBtn"},
          {field:"time",titile:"",align:"center",visible:false}
        ]
    });
}

//项点问题记录查询
function problemViolationGridDetail(obj,itemForm){
        obj.startDate = itemForm.startDate;
        obj.endDate = itemForm.end;
        obj.driver_code = driver_code;
        var $tables = $("#problemViolationGridDetail");
        $tables.bootstrapTable('destroy');
        $tables.bootstrapTable({
        url: path + "/checkout/checkOutBuffetQuery/queryProblemViolationGridDetail.do",         //请求后台的URL（*）
        dataType:"json",
        async:false,
        method: 'get',                      //请求方式（*）
        pagination: true,
        paginationLoop:false,
        paginationPreText:'上一页',
        paginationNextText:'下一页',
        showPaginationSwitch:false,			//是否显示数据条数选择框
        pageSize:"10",
        pageNumber: "1",
        pageList: [10,10,20],
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        order: "asc",
        sortName:'',
        limit:1,
        offset: 10,
        queryParamsType:"",
        queryParams:  function queryParams(params) {   //设置查询参数
            obj.page = params.pageNumber;
            obj.pageSize = params.pageSize;
            return obj;
        },				//传递参数（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        contentType:"application/x-www-form-urlencoded;charset=UTF-8",
        clickToSelect: true,                //是否启用点击选中行
        height: "auto",                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        detailView: false,                   //是否显示父子表
        responseHandler: function (res) {   //在ajax请求成功后，填充数据之前可以对返回的数据进行处理
            var data=res["rows"];
            return {
                "total": res.total,//总页数
                "pageNumber":res.currentPage,
                "rows": data //数据转成json对象
            };
        },
        columns: [{
            field: 'ids',
            title: "序号",
            align: 'center',
            formatter:function(value,row,index){
                return index+1;
            }
        },{
            field: 'date',
            title: "开车日期",
            align: 'center',
            formatter:function(value,row,index){
                return formtterDate(row.date);
            }
        }, {
            field: 'startTime',
            title: "作业时间",
            align: 'center'
        }, {
            field: 'train_batch_no',
            title: "车次",
            align: 'center'
        }, {
            field: 'zhandian',
            title: "站点",
            align: 'center'
        }, {
            field: "memo",
            title: '问题描述',
            align: 'center'
        },{
            field:"qualified",
            title:"是否达标",
            align:"center"
        },{
            field:"dataDetailForLKJ",
            title:"查看详情",
            align:"center",
            events:operateEvents,
            formatter:"getLKJBtn"
        }]
    });
}

/**
 * 格式化日期
 * @param date
 */
function formtterDate(date){
    if(date!=undefined&&date!=null&&date!=""){
        return date.substr(0,4)+"-"+date.substr(4,2)+"-"+date.substr(6,2);
    }
   return "";
}
/**
 *   sb.append("select date,startTime,train_batch_no," +
 "zhandian,scenario_code,index_type_code,index_code,problem_code,memo");
 * @param obj
 * @param lengendarr
 * @returns {Array}
 */

//绘制交路图形series
function getSeries(obj, lengendarr,sort) {
    var dataA = [];
    var objArr = [];
    var dataB = [];
    var dataC = [];
    var dataRatio = [];
    for (var i = 0; i < obj.length; i++) {
        dataA.push({value: obj[i].ALevel, tag: obj[i].code_name});//x抽数据
        dataB.push({value: obj[i].BLevel, tag: obj[i].code_name});
        dataC.push({value: obj[i].CLevel, tag: obj[i].code_name});
        dataRatio.push(((obj[i].ratio) * 100).toFixed(2));//占比
    }
    objArr.push(dataA);
    objArr.push(dataB);
    objArr.push(dataC);
    var series = [];
    for (var t = 0; t < objArr.length; t++) {
        var o = barAndLine(lengendarr[t], "bar", objArr[t]);
        series.push(o);
    }
    var oj = barAndLine(lengendarr[3], "line", dataRatio);
    series.push(oj);
    //console.info("Series::"+JSON.stringify(series));
    return series;
}
//对数组进行排序 升序
function sequce(a,b){
        var o1 = parseFloat(a.show_index_type_code);
        var o2 = parseFloat(b.show_index_type_code);
        if (o1<o2) {
            return 1;
        }else if(o1==o2){
            return 0
        }else{
            return -1;
        }
}

//对数组进行排序 升序
function sequceArr(a,b){
    var o1 = parseFloat(a);
    var o2 = parseFloat(b);
    if (o1>o2) {
        return 1;
    }else if(o1<o2){
        return -1
    }else{
        return 0;
    }
}

//对数组进行排序 降序
function sequceArrdesc(a,b){
    var o1 = parseFloat(a);
    var o2 = parseFloat(b);
    if (o1<o2) {
        return 1;
    }else if(o1>o2){
        return -1
    }else{
        return 0;
    }
}

//对数组对象进行排序 降序
function sequceDesc(a,b){
    var o1 = parseFloat(a.value);
    var o2 = parseFloat(b.value);
    if (o1<o2) {
        return 1;
    }else if(o1>o2){
        return -1
    }else{
        return 0;
    }
}


//把数组排序过的  把最大值放在中间
function getMiddle(arr){
    var o = arr;
    if(o.length<1) return o;
    var narr = [];

    var narr1 = [];
    var narr2 = [];
    for(var i = 0;i<(o.length)/2;i++){
        narr1.push(o[i]);
    }

    for (var j = o.length-1;j>=(o.length)/2;j--){
        narr2.push(o[j]);
    }

    /**
     * 如下处理就是把数据排序之后，然后把最大的往中间靠
     */
    for (var j = 0;j<narr2.length;j++){//大的值
            if(j%2==0){
                narr.push(narr2[j]);
            }else{
                narr.unshift(narr2[j]);
            }
    }

    for(var k = narr1.length-1;k>=0;k--){//小的值
        if(k%2==0){
            narr.push(narr1[k]);
        }else{
            narr.unshift(narr1[k]);
        }
    }
    return narr;
}

function barAndLine(lengend, attr, dataArr) {
    var oj = {};
    oj.name = lengend;
    oj.type = attr;
    if (attr == "line") {
        oj.smooth = true;
        oj.yAxisIndex=1;
        oj.label = {
            normal: {
                show: true,
                color:'#000',
                position: 'top',
                formatter:'{c}%'
            }
        };
    }
    oj.stack = '1';
    oj.data = dataArr;

    return oj;
}

/**
 * 违标区段分布、违标项点分布
 *map.put("queryItemIrregularitiesArea", queryItemIrregularitiesArea);
 map.put("queryItemIrregularitiesPoint", queryItemIrregularitiesPoint);
 targetOfViolation 区段div--id
 violationOfStandard 违标项点div--id
 */

function jlWidthAreaxAxis(obj, field) {
    var xDataName = [];
    for (var i = 0; i < obj.length; i++) {
        xDataName.push(obj[i][field]);//标题
    }
    return xDataName;
}

/**
 * map.put("queryCurrentDriverStrongItem", queryCurrentDriverStrongItem);
 * 作业执标分析强弱项
    map.put("queryCurrentDriverLossItem", queryCurrentDriverLossItem);
 */
function itemOpertationSL(title,legend,xAxis,series,yAxis,color){
   return {
        color:color,
        title: {
            text: title
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: legend
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            data: xAxis

        },
        yAxis: yAxis,
        series: series
    };
}

/**
 * 强弱项分析
 * @param dataObj
 */
function operationStrongLess(dataObj) {
    //强项
    var sXData = [];
    var sYData = [];
    seriesArr = [];
    var queryCurrentDriverStrongItem = dataObj.queryCurrentDriverStrongItem;
    //降序排序
    queryCurrentDriverStrongItem.sort(sequce);
    var count = 1;
    for (var i =0; i<queryCurrentDriverStrongItem.length; i++) {
        if (count>3) break;
        sXData.push(queryCurrentDriverStrongItem[i].new_code_name);
        sYData.push(queryCurrentDriverStrongItem[i].show_index_type_code);
        count++;
    }
    var syAxis = [{
        type: 'value',
        name: '作业次数',
        axisLabel: {
            formatter: '{value} 次'
        }
    }];
    seriesArr.push({name:"作业次数",type:"bar",data:sYData});
    // var strongItemOption = itemOpertationSL("操作强项",["操作强项"],sXData,seriesArr,syAxis,['#62b54b']);
    // if(!myChartsstrongItem){
    //     myChartsstrongItem = echarts.init(document.getElementById("strongItem"));
    // }
    // myChartsstrongItem.setOption(strongItemOption,true);


    //弱项
    var lXData = [];
    var lYData = [];//违标率
    var weibiaocishu = [];//违标次数
    var lessItemSeriesArr = [];
    var queryCurrentDriverLossItem = dataObj.queryCurrentDriverLossItem;
    var count = 1;
    for (var i = queryCurrentDriverLossItem.length - 1; i > 0; i--) {
        if (count == 4) break;
        lXData.push(queryCurrentDriverLossItem[i].new_code_name);//x轴
        var ratio = queryCurrentDriverLossItem[i].ratio_index_type_code;
        lYData.push((ratio*100).toFixed(2));//违标率
        weibiaocishu.push(queryCurrentDriverLossItem[i].count_index_type_code);//违标次数
        count++;
    }
    //双y抽
    var lYAxis = [
        {
            type: 'value',
            name: '发生问题率',
            minInterval:1,
            axisLabel: {
                formatter: '{value} %'
            }
        },{
        type: 'value',
        name: '违标次数',
        minInterval:1,
        axisLabel: {
            formatter: '{value} 次'
        }
    }];
    //series
    lessItemSeriesArr.push({name:"发生问题率",type:"bar",data:lYData,yAxisIndex: 0});
    lessItemSeriesArr.push({name:"违标次数",type:"line",data:weibiaocishu,yAxisIndex: 1});
    //返回整个option
    var lessItemOption = itemOpertationSL("操作弱项",["操作弱项"],lXData,lessItemSeriesArr,lYAxis,['#979797','#20B2AA']);
    if(!myChartsLessItem){
        myChartsLessItem = echarts.init(document.getElementById("lessItem"));
    }
    myChartsLessItem.setOption(lessItemOption,true);
}

/**
 * 作业执标分析
 */
function initEvaluateEcharts(){
    var obj=$("#conditionForm").serializeObject();
    obj.driver_code = driver_code;
    $.ajax({
        url:path+"/checkout/checkOutBuffetQuery/getTaskEvaluate.do",
        type:"post",
        dataType:"json",
        data:obj,
        success:function(data){
            evaluateEcharts(data);
        }
    });
}

/**
 *  map.put("itemPoint",taskEvaluate);
    map.put("evaluate",list);
 * @param data
 */
//执标分析   作业评价
function evaluateEcharts(data) {
    var currentHostory = echarts.init(document.getElementById("current-hostory-evaluate"));
    var option = {
        color: colors,
        title: {
            text: '作业评价',
            x: "center",
            y: "top"
        },
            tooltip: {
                trigger: 'axis'
            },
        legend: {
            orient: 'horizontal',
            x: 'left', // 'center' | 'left' | {number},
            y: 'top', // 'center' | 'bottom' | {number}
            data: ["A类问题违标次数","B类问题违标次数","C类问题违标次数","评价次数"]
        },
        grid: {
            top: 70,
            bottom: 50
        },
        xAxis: [
            {
                type: 'category',
                data: getPingJiaDate(data)
            }
        ],
        yAxis: [
            {
                name: '违标次数',
                type: 'value',
                minInterval:1
            },{
                name: '评价次数',
                type: 'value',
                minInterval:1
            }
        ],
        series: getPingJiaSeries(data)
    };
    currentHostory.setOption(option, true);
    currentHostory.off();
    currentHostory.on("click", function (param) {
        if(param.seriesName=="评价次数"){
            var dateParam = {evaldate:param.name};
            getEvaluateGrid(dateParam);
            $("#evaluteModal").modal();
        }
    });
}

function getPingJiaDate(data){
    var date = [];
    for(var i = 0;i<data.length;i++)
    {
        date.push(data[i].date);
    }

    return date;
}



function getPingJiaSeries(data){
    var Alevel = [];
    var Blevel = [];
    var Clevel = [];
    var ctPinjia = [];
    var seriesArr = [];
    //var date = [];
        for(var i = 0;i<data.length;i++)
        {
            Alevel.push(data[i].ALevel);
            Blevel.push(data[i].BLevel);
            Clevel.push(data[i].CLevel);
            ctPinjia.push(data[i].comment_count);
           // date.push(data[i].date);
        }
    var A = {name:"A类问题违标次数", type: 'line',smooth: true,data:Alevel,yAxisIndex:0};
    var B = {name:"B类问题违标次数", type: 'line',smooth: true,data:Blevel,yAxisIndex:0};
    var C = {name:"C类问题违标次数", type: 'line',smooth: true,data:Clevel,yAxisIndex:0};
    var D = {name:"评价次数", type: 'bar',data:ctPinjia,yAxisIndex:1};
    seriesArr.push(A);
    seriesArr.push(B);
    seriesArr.push(C);
    seriesArr.push(D);
return seriesArr;
}
/**
 * 作业评价
 */
function getEvaluateGrid(obj) {
    obj.driver_code = driver_code;
    $("#evaluteGrid").bootstrapTable('destroy');
    $("#evaluteGrid").bootstrapTable({
        url: path + "/checkout/checkOutBuffetQuery/getEvaluateContentByDriverCode.do",
        dataType: "json",
        async: false,
        method: 'post',
        pagination: true,
        paginationLoop: false,
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        showPaginationSwitch: false,
        pageSize: "5",
        pageNumber: "1",
        pageList: [5, 10, 20],
        striped: true,
        cache: false,
        sortable: false,
        sortOrder: "asc",
        order: "asc",
        sortName: '',
        limit: 1,
        offset: 10,
        queryParamsType: "",
        queryParams: function queryParams(params) {
            obj.page=params.pageNumber;
            obj.pageSize = params.pageSize;
            return obj;
        },
        sidePagination: "server",
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
        clickToSelect: true,
        height: "auto",
        uniqueId: "id",
        detailView: false,
        responseHandler: function (res) {
            var data = res["rows"];
            return {
                "total": res.total,
                "pageNumber": res.currentPage,
                "rows": data
            };
        },
        columns: [{field: 'ids',title: "序号",align: 'center',
            formatter: function (value, row, index) {
                return index + 1;
            }
        }, {field: 'id',title: "序号",visible: false
        },{field: 'comment_date',title: "评价时间",align: 'center',
            formatter:function(value,row,index){
                var dateJoin = row.comment_date.substr(0,4)+"-"+row.comment_date.substr(4,2);
                    dateJoin +="-"+row.comment_date.substr(6,2);
                return dateJoin;
            }
        }, {field: 'realname',title: "评价人", align: 'center'
        }, { field: 'driver_id',title: "评价对象",align: 'center'
        }, {field: "new_scenario_code", title: '作业场景',align: 'center'
        }, {field: "new_index_type_code",title: '作业项目',align: 'center'
        }, {field: "new_index_code",title: '作业项点',align: 'center'
        }, { field: "new_problem_code",title: '作业问题', align: 'center'
        }, {field: "startandenddate",title: '被评价时间段',align: 'center',
            formatter:function(value,row,index){
                var splits = row.startandenddate.substr(0,4)+"-"+row.startandenddate.substr(4,2);
                    splits +="-"+row.startandenddate.substr(6,2);
                    splits +="至"+row.startandenddate.substr(9,4);
                    splits +="-"+row.startandenddate.substr(13,2);
                    splits +="-"+row.startandenddate.substr(15,2);
                    return splits;
            }
        }, {field: "comment_label",title: '评价标签',align: 'center'
        }, {field: "comment_text",title: '评价内容',align: 'center'
        }]
    });
}

/**
 * 序列化json对象
 */
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

var loadChart = 0;
var loadTenlyCHart = 0;
$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
    // 先获取已激活的标签页的名称
    var activeTab = $(e.target)[0].hash;
    if (activeTab == "#itemPoint") {
        if (loadChart > 0) {
            return;
        }
        loadChart++;
        if (myCharts) {
            myCharts.resize();
        }
        if (myChartsArea) {
            myChartsArea.resize();
        }
        if (myWgCharts) {
            myWgCharts.resize();
        }
        if (myChartsProRato) {
            myChartsProRato.resize();
        }
        if (myChartsirregularitiesTimesObj) {
            myChartsirregularitiesTimesObj.resize();
        }
        if (queryItemIrregularitiesJL) {
            queryItemIrregularitiesJL.resize();
        }
        if (targetOfViolationCharts) {
            targetOfViolationCharts.resize();
        }
        if (violationOfStandardCharts) {
            violationOfStandardCharts.resize();
        }
    }else if (activeTab == "#habits") {
        if (myChartsstrongItem) {
            myChartsstrongItem.resize();
        }
        if (myChartsLessItem) {
            myChartsLessItem.resize();
        }
    }
});


/**
 * 针对分析，查询历史详情，追索到原始lkj记录数据
 */
function findHostoryRecord(data) {
    $("#queryLKJGrid").bootstrapTable('destroy');
    $("#queryLKJGrid").bootstrapTable({
        url: path + "/checkout/checkoutAnalysis/findHostoryRecordWithLKJ.do",
        dataType: "json",
        method: 'post',
        pagination: true,
        paginationLoop: false,
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        showPaginationSwitch: false,
        pageSize: "10",
        pageNumber: "1",
        pageList: [10, 20, 30],
        striped: true,
        cache: false,
        sortable: false,
        sortOrder: "asc",
        order: "asc",
        sortName: '',
        limit: 1,
        offset: 10,
        queryParamsType: "",
        queryParams: function queryParams(params) {
            data.page = params.pageNumber;
            data.pageSize = params.pageSize;
            return data;
        },
        sidePagination: "server",
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
        clickToSelect: true,
        height: "auto",
        uniqueId: "id",
        detailView: false,
        responseHandler: function (res) {
            var data = res["rows"];
            return {
                "total": res.total,
                "pageNumber": res.currentPage,
                "rows": data
            };
        },
        columns: [

            {field: 'shijian_time',title: "时间",align:'center'},
            {field: 'event', title: "事件",align:'center'},
            {field: "mileage", title: '里程',align:'center'},
            {field: "distance",title: '距离',align:'center'},
            {field: "signal",title: '信号',align:'center'},
            {field: "signaler",title: '信号机',align:'center'},
            {field: "speed",title: '速度',align:'center'},
            {field: "speed_limit",title: '限速',align:'center'},
            {field: "condition",title: '工况',align:'center'},
            {field: "guanya",title: '管压',align:'center'},
            {field: "zhagang_zhagang1",title: '缸压',align:'center'},
            {field: "speed_current",title: '电流',align:'center'},
            {field: "jungang1_jungang",title: '均缸1',align:'center'},
            {field: "jungang2_zhagang2",title: '均缸2',align:'center'},
            {field: "xianluhao",title: '线路号',align:'center'},
            {field: "xianluming",title: '线路名',align:'center'},
            {field: "xingbie",title: '行别',align:'center'},
            {field: "chezhan",title: '车站',align:'center'},
            {field: "chezhanhao",title: '车站号',align:'center'},
            {field: "mode",title: '模式',align:'center',visible:false},
            {field: "qita",title: '其它',align:'center'}
        ]
    });
}
//退勤历史查询
function tuiQinInfo() {
    var ondutytime = $("#ondutytime").val();
    var offdutytime = $("#offdutytime").val();
    var o = {};
    $("#tuiqininfo").bootstrapTable('destroy');
    $("#tuiqininfo").bootstrapTable({
        url: path + "/checkout/checkOutBuffetQuery/findAllForChuTuiQinInfo.do",
        dataType: "json",
        method: 'post',
        pagination: true,
        paginationLoop: false,
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        showPaginationSwitch: false,
        pageList: [10, 20, 30],
        striped: true,
        cache: false,
        sortName: '',
        limit: 1,
        offset: 10,
        queryParamsType: "",
        queryParams: function queryParams(params) {
            o.page = params.pageNumber;
            o.pageSize = params.pageSize;
            o.ondutytime = ondutytime;
            o.offdutytime = offdutytime;
            o.driverCode = driver_code;
            return o;
        },
        sidePagination: "server",
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
        clickToSelect: true,
        height: "auto",
        uniqueId: "id",
        detailView: false,
        responseHandler: function (res) {
            var data = res["rows"];
            return {"total": res.total,"pageNumber": res.currentPage,"rows": data};
        },
        columns: [
            {field: "id",visible:false},
            {field: 'chuqin_time', title: "运安出勤时间",align:'center'},
            {field: "tuiqin_time", title: '运安退勤时间',align:'center'},
            {field: "sys_date",title: '系统退勤时间',align:'center'},
            {field: 'queryDetails',title: "查看详情",align:'center',formatter:function(value,row,index){
                    return '<a href="javascript:void(0)" ' +
                        'style="color: #0000FF;text-decoration: underline;' +
                        'cursor: pointer;background: transparent;border: 0;outline: none;" ' +
                        'onclick="queryDetails('+row.id+')">查看详情</a>';
                }
            }
        ]
    });
}

function queryDetails(rowId){
    $('#tuiQinPageModel').on('shown.bs.modal', function () {
        $tenly.resize();
    });
    var rowData = $("#tuiqininfo").bootstrapTable("getRowByUniqueId",rowId);
    var driverId = rowData.driver_id;
    var ondutytime = rowData.chuqin_time;
    var offdutytime = rowData.tuiqin_time;
    checkOutResultDetail(driverId,ondutytime,offdutytime);
    getGrid(driverId,ondutytime,offdutytime);
    $("#myModalLabel").text("退勤时间："+offdutytime);
    $("#tuiQinPageModel").modal();
}
/**
 * 退勤结果和交路图
 * map.put("resInfo",resInfo);
 map.put("jlInfo",jlInfo);
 */
function checkOutResultDetail(driverCode,ondutytime,offdutytime) {
    //直接查询后台，获取数据
    $.ajax({
        url: path + "/checkout/checkOutBuffetQuery/getResultInfo.do",
        type: "post",
        dataType: "json",
        data: {"driver_id": driverCode, "ondutytime": ondutytime,"offdutytime":offdutytime},
        success: function (data) {
            if(data!=null){
                //退勤结果
                var dom = $("#causeDetail");
                $(".delete-cls").remove();
                var resInfo = data.resInfo;
                if(resInfo!=null&&resInfo.length>0){
                    dom.append("<span id = 'result' class='span-cls delete-cls'>" + resInfo[0].result_data + "</span>");
                    //交路图
                    $tenly.update(data.jlInfo);
                }
            }
        }
    });
}
/**
 项点分析结果
 */
function getGrid(driverId,ondutytime,offdutytime) {
    $("#userinfo").bootstrapTable('destroy');
    $("#userinfo").bootstrapTable({
        url: path + "/checkout/checkOutBuffetQuery/getCurrentDriverAnalyData.do",
        dataType: "json",
        method: 'get',
        pagination: true,
        paginationLoop: false,
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        showPaginationSwitch: false,
        pageSize: "5",
        pageList: [5, 10, 20],
        striped: true,
        cache: false,
        limit: 1,
        offset: 5,
        queryParamsType: "",
        queryParams: function queryParams(params) {
            var param = {
                page: params.pageNumber,
                pageSize: params.pageSize,
                "driver_id": driverId,
                "ondutytime": ondutytime,
                "offdutytime": offdutytime
            };
            return param;
        },
        sidePagination: "server",
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
        clickToSelect: true,
        uniqueId: "id",
        detailView: false,
        responseHandler: function (res) {
            var data = res["rows"];
            return {
                "total": res.total,
                "pageNumber": res.currentPage,
                "rows": data
            };
        },
        columns: [{
            field: 'ids',
            title: "序号",
            align: 'center',
            formatter: function (value, row, index) {
                return index + 1;
            }
        }, {field: 'id',title: "序号",visible: false
        }, {field: 'driver_id',title: "乘务员编号",visible: false,align: 'center'
        }, {field: 'new_scenario_name',title: "作业场景",align: 'center'
        }, {field: 'new_index_type_name',title: "作业项目",align: 'center'
        }, {field: 'new_index_name',title: "作业项点",align: 'center'
        }, {field: "new_problem_name",title: '作业问题',align: 'center'
        }, {field: "count_problem",title: '违标次数',align: 'center'
        }, {field: "item_level",title: '违标级别',align: 'center',
            formatter:function(value, row, index){
                if(value=="C"){
                    return "III类";
                }else if(value=="B"){
                    return "II类";
                }else if(value=="A"){
                    return "I类";
                }
            }
        }, {field: "querydetail",title: '查看详情',align: 'center',
            formatter: function (value, row, index) {
                checkInAndOutTimes = row.noteStartDateAndTermial;
                var e = '<button href="#" class="btn btn-primary"  ' +
                    'onclick="queryScanDetail(\'' + row.id + '\')">查看详情</button>';
                return e;
            }
        }],
        onLoadError: function(){ //加载失败时执行

        }
    });
}

/**
 * 查询项点分析结果 点击查询详情按钮
 * @param rowId
 */
function queryScanDetail(rowId) {
    var rowData = $('#userinfo').bootstrapTable('getRowByUniqueId', rowId);
    getModalGrid(rowData);
    $("#showOrHidden").remove();
    $("#myModal").modal();
}

/**
 * 项点分析结果详情表格
 */
function getModalGrid(postData) {
    $("#driverDetailGrid").bootstrapTable('destroy');
    $("#driverDetailGrid").bootstrapTable({
        url: path + "/checkout/checkOutBuffetQuery/getCurrentRowDetail.do",
        dataType: "json",
        async: false,
        method: 'get',
        pagination: true,
        paginationLoop: false,
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        showPaginationSwitch: false,
        pageSize: "10",
        pageList: [10, 20, 30],
        striped: true,
        cache: false,
        sortName: '',
        limit: 1,
        offset: 10,
        queryParamsType: "",
        queryParams: function queryParams(params) {
            var param = {
                page: params.pageNumber,
                pageSize: params.pageSize
            };
            for (var key in postData) {
                param[key] = postData[key];
            }
            return param;
        },
        sidePagination: "server",
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
        clickToSelect: true,
        uniqueId: "myId",
        detailView: false,
        responseHandler: function (res) {
            var data = res["rows"];
            return {
                "total": res.total,
                "pageNumber": res.currentPage,
                "rows": data
            };
        },
        columns: [{field: 'myId',title: "序号",visible:false
        }, {field: 'train_batch_no',title: "车次"
        }, {field: 'startTime',title: "作业时间"
        }, {field: 'zhandian',title: "地点"
        }, {field: 'new_problem_name',title: "作业问题"
        }, {field: "memo",title: '问题描述'
        }, {field: "qualified",title: '是否达标'
        },{field:"queryDetailBtn",title:"查看数据明细",
            formatter:function(value,row,index){
                return '<button type="button" onclick="findHostoryRecords('+row.myId+')" class="btn btn-primary"  ' +
                    'style="color: #0000FF;text-decoration: underline;' +
                    'cursor: pointer;background: transparent;border: 0;outline: none;">查看数据明细</button>';
            }
        }]
    });
}

/**
 * 针对分析，查询历史详情，追索到原始lkj记录数据
 */
function findHostoryRecords(data) {
    $("#findHostoryModal").modal();
    var rowData = $('#driverDetailGrid').bootstrapTable('getRowByUniqueId', data);
    $("#findHostoryGrid").bootstrapTable('destroy');
    $("#findHostoryGrid").bootstrapTable({
        url: path + "/checkout/checkoutAnalysis/findHostoryRecordWithLKJ.do",
        dataType: "json",
        method: 'post',
        pagination: true,
        paginationLoop: false,
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        showPaginationSwitch: false,
        pageSize: "10",
        pageNumber: "1",
        pageList: [10,20, 50],
        striped: true,
        cache: false,
        sortName: '',
        limit: 1,
        offset: 10,
        queryParamsType: "",
        queryParams: function queryParams(params) {
            rowData.page = params.pageNumber;
            rowData.pageSize = params.pageSize;
            return rowData;
        },
        sidePagination: "server",
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
        clickToSelect: true,
        height: "auto",
        uniqueId: "id",
        detailView: false,
        responseHandler: function (res) {
            var data = res["rows"];
            return {
                "total": res.total,
                "pageNumber": res.currentPage,
                "rows": data
            };
        },
        columns: [
            {field: 'shijian_time',title: "时间",align:'center'},
            {field: 'event', title: "事件",align:'center'},
            {field: "mileage", title: '里程',align:'center'},
            {field: "distance",title: '距离',align:'center'},
            {field: "signal",title: '信号',align:'center'},
            {field: "signaler",title: '信号机',align:'center'},
            {field: "speed",title: '速度',align:'center'},
            {field: "speed_limit",title: '限速',align:'center'},
            {field: "condition",title: '工况',align:'center'},
            {field: "guanya",title: '管压',align:'center'},
            {field: "zhagang_zhagang1",title: '缸压',align:'center'},
            {field: "speed_current",title: '电流',align:'center'},
            {field: "jungang1_jungang",title: '均缸1',align:'center'},
            {field: "jungang2_zhagang2",title: '均缸2',align:'center'},
            {field: "xianluhao",title: '线路号',align:'center'},
            {field: "xianluming",title: '线路名',align:'center'},
            {field: "xingbie",title: '行别',align:'center'},
            {field: "chezhan",title: '车站',align:'center'},
            {field: "chezhanhao",title: '车站号',align:'center'},
            {field: "mode",title: '模式',align:'center',visible:false},
            {field: "qita",title: '其它',align:'center'}
        ]
    });
}