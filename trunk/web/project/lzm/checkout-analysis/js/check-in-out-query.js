$(function () {
    /* 图形展示 */
    /*$(".modal-dialog").draggable();*/
    //注册所有事件
    initClick();
});


/**
 * 注册所有事件
 */
function initClick(){
    /**
     * 设置分析结果中表格查看详情滚动条
     */
    $("#myModal").on('hidden.bs.modal', function () {
        document.documentElement.style.overflowY = "auto";
    });

    //交路图形显示和隐藏事件
    $("#up_down_btn").on('click', function () {
        $('#graphDivId').toggle(function () {
            if ($("#up_down_btn").attr("src") == path + "/images/arrow_up.png") {
                $("#up_down_btn").attr("src", path + "/images/arrow_down.png");
            } else {
                $("#up_down_btn").attr("src", path + "/images/arrow_up.png");
            }
        });
    });

    $("#checkInAndOutSearch").unbind("click").bind("click", function () {
        //退勤结果信息提示和交路图
        checkOutResultDetail();
        //项点分析结果
        getDriverAnayContext(driverCode);
    });
}

/**
 * 查询乘务员出退勤时间
 */
function queryChuTuiQinDate(){
    $.ajax({
        async:false,
        url:path+"/checkout/checkOutBuffetQuery/getChuTuiQinDate.do",
        type:"post",
        dataType:"json",
        data:{"driver_code":driverCode},
        success:function(data){
            if(data!=null){
                initConditionTime(data.maxchutuiqin);
                inputchutuiqinDate(data.chuTuiQinDate);
            }
        }
    });
}

/**
 * 初始化筛选条件中的输入框中的值
 * @param data
 */
function initConditionTime(data){
    var chuqinshijian = data[0].chuqin_time;
    var tuiqinshijain = data[0].tuiqin_time;
    $("#ondutytime").val(chuqinshijian.substr(0,10));
    $("#offdutytime").val(tuiqinshijain.substr(0,10));
}
/**
 * 填充出退勤时间组件
 * @param data
 */
function inputchutuiqinDate(data){
    var ondate=[];
    var offdate=[];
    for(var i=0;i<data.length;i++){
        if(data[i].type=='1'||data[i].type==1){
            var onObj = {};
            onObj.ym=data[i].ondutytime_ym;
            var tarr = (data[i].onday).split(",");
            for(var t = 0;t<tarr.length;t++){
                tarr[t]=parseInt(tarr[t]);
            }
            onObj.days=tarr;
            ondate.push(onObj);
        }else if(data[i].type=='2'||data[i].type==2){
            var offObj={};
            offObj.ym=data[i].ondutytime_ym;
            var tarr = (data[i].onday).split(",");
            for(t in tarr){
                tarr[t]=parseInt(tarr[t]);
            }
            offObj.days=tarr;
            offdate.push(offObj);
        }
    }
    jeDate("#ondutytime",{
        valiDate:[],
        format: "YYYY-MM-DD",
        customdate:ondate,
        isDYAjax:true
    });
    jeDate("#offdutytime",{
        valiDate:[],
        format: "YYYY-MM-DD",
        customdate:offdate
    });
}


/**
 * 退勤结果和交路图
 * map.put("resInfo",resInfo);
   map.put("jlInfo",jlInfo);
 */
function checkOutResultDetail() {
    //直接查询后台，获取数据
    var ondutytime = $("#ondutytime").val();
    var offdutytime = $("#offdutytime").val();
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
                    graphStartAndEndStation(data.jlInfo);
                }
            }
        }
    });
}

/**
 * 绘制始发终到  交路图形
 */
function graphStartAndEndStation(data) {
    $tenly.update(data);
}

/**
 *乘务员分析表格
 * 2018-10-31
 * 此表后台有所更新
 * @param driverId
 */
function getDriverAnayContext(driverId) {
    getGrid(driverId);
}

/**
 项点分析结果
 */
function getGrid(driverId) {
    var ondutytime = $("#ondutytime").val();
    var offdutytime = $("#offdutytime").val();
    $("#userinfo").bootstrapTable('destroy');
    $("#userinfo").bootstrapTable({
        url: path + "/checkout/checkOutBuffetQuery/getCurrentDriverAnalyData.do",         //请求后台的URL（*）
        dataType: "json",
        async: false,
        method: 'get',                      //请求方式（*）
        pagination: true,
        paginationLoop: false,
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        showPaginationSwitch: false,			//是否显示数据条数选择框
        pageList: [10, 10, 20],
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        order: "asc",
        sortName: '',
        limit: 1,
        offset: 10,
        queryParamsType: "",
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                page: params.pageNumber,   //每页多少条数据
                pageSize: params.pageSize, 	 // 页码
                "driver_id": driverId,
                "ondutytime": ondutytime,
                "offdutytime": offdutytime
            };
            return param;
        },				//传递参数（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
        clickToSelect: true,                //是否启用点击选中行
        height: "auto",                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        detailView: false,                   //是否显示父子表
        responseHandler: function (res) {//在ajax请求成功后，填充数据之前可以对返回的数据进行处理
            var data = res["rows"];
            return {
                "total": res.total,//总页数
                "pageNumber": res.currentPage,
                "rows": data //数据转成json对象
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
        url: path + "/checkout/checkOutBuffetQuery/getCurrentRowDetail.do",         //请求后台的URL（*）
        dataType: "json",
        async: false,
        method: 'get',                      //请求方式（*）
        pagination: true,
        paginationLoop: false,
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        showPaginationSwitch: false,			//是否显示数据条数选择框
        pageSize: "10",
        pageNumber: "1",
        pageList: [5, 10, 20],
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        order: "asc",
        sortName: '',
        limit: 1,
        offset: 10,
        queryParamsType: "",
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                page: params.pageNumber,   //每页多少条数据
                pageSize: params.pageSize 	 // 页码
            };
            for (var key in postData) {
                param[key] = postData[key];
            }
            return param;
        },				//传递参数（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
        clickToSelect: true,                //是否启用点击选中行
        height: "auto",                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "myId",                     //每一行的唯一标识，一般为主键列
        detailView: false,                   //是否显示父子表
        responseHandler: function (res) {//在ajax请求成功后，填充数据之前可以对返回的数据进行处理
            var data = res["rows"];
            return {
                "total": res.total,//总页数
                "pageNumber": res.currentPage,
                "rows": data //数据转成json对象
            };
        },                   //是否显示父子表
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
        sortable: false,
        sortOrder: "asc",
        order: "asc",
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
