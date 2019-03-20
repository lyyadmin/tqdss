var exception = null,itemObjs = null,checkAndItemOjb = null,dataLose = null;
var secondRequest = false;
//始发终到数据的数据缺失数组,缓存确认时验证提交
var startTerminalData = [];
//乘务员的出退勤时间
var checkInAndOutTimes = "";
//始发终到所有数据,项点分析结果数据,作为退勤结果用的始发终到和项点分析结果数据
var realyStartAndEndStationData = null,itemAnasysResultData=null,startAndEndStation = null,itemData = null;
$(function () {
    $(".modal-dialog").draggable();
    allInit();
});

/**
 * 所有初始化方法
 */
function allInit(){
    var data = checkining();
    //乘务员基本信息
    queryDriverById(driverCode);
    if(data=="2"){//正在休息中

    }else if(data=="1"){//正出勤中
        $(".chuqin-cls").hide();
        $(".checkining-cls").show();

    }else if(data=="0"){//已经退勤
        $(".checkining-cls").hide();
        $(".chuqin-cls").show();
        //注册所有事件
        getClick();
        //项点分析结果
        itemGrid(driverCode);
        //当前乘务员的交路
        currentDriverStartAndEnd();
        //初始化数据缺失原因下拉列表
        getLoseFileCause();
        //A类问题确认下拉选项值
        getExceptionCause();
    }else{

        //异常
    }
}

/**
 * 注册所有事件
 */
function getClick(){
    //弹出框自动显示和固定滚动条
    $("#myModal").on('hidden.bs.modal', function () {
        document.documentElement.style.overflowY = "auto";
    });

    //初始化始发终到的车站，当前乘务员的交路图
    $tenly.init('canvas', 'graphDivId', path, []);
    $tenly.setItemClick(function (item) {
        if (item.lkj_isnotexits != undefined && item.lkj_isnotexits == "0" || item.is_exists_suspicious == "0") {
            dataLose = null;
            dataLose = item;
            $("#startToEndTrainModal").modal();
        }
    });

    //交路图显示和隐藏按钮
    $("#up_down_btn").on('click', function () {
        $('#graphDivId').toggle(function () {
            if ($("#up_down_btn").attr("src") == path + "/images/arrow_up.png") {
                $("#up_down_btn").attr("src", path + "/images/arrow_down.png");
            } else {
                $("#up_down_btn").attr("src", path + "/images/arrow_up.png");
                $tenly.resize();
            }
        });
    });

    //项点分析结果按级别分类显示
    $("#levelSelect").unbind("change").bind("change", function () {
        //getDriverAnayContext(driverCode);
        itemGrid(driverCode);
    });

    //提交确认退勤
    $("#submitAll").unbind("click").bind("click", function () {
        //判断是否已经退勤
        var isExits = checkDriverCheckInAndOut(driverCode);
        if (isExits) {
            swal("", "您已经退勤！", "error");
            return;
        }
        //判断文件缺失是否有未被确认startTerminalData
        var defectIs = false;
        if (startTerminalData != null && startTerminalData.length > 0) {
            for (var i = 0; i < startTerminalData.length; i++) {
                if (startTerminalData[i].defect == "" ||
                    startTerminalData[i].defect == null ||
                    startTerminalData[i].defect == undefined) {
                    defectIs = true;
                    swal("", "您有未确认数据缺失原因", "error");
                }
            }
        }
        //判断项点分析结果表格中的A类问题  itemAnasysResultData
        for(var i = 0;i<itemAnasysResultData.length;i++){
            if(itemAnasysResultData[i].level=="A"){
                if(itemAnasysResultData[i].is_confirm_A==undefined
                    ||itemAnasysResultData[i].is_confirm_A==""){
                    defectIs = true;
                    swal("","请您确认项点分析结果(I类)部分内容","error");
                    break;
                }
            }
        }
        if (defectIs) return;
        //进行提交，需要进行修改 数据丢失原因集合startTerminalData，出退勤时间checkInAndOutTimes
        isPro(startTerminalData);
    });
}


/**
 * 处理正在出勤中或者休息中
 * @returns {string}
 */
function checkining(){
    var res = "";
    $.ajax({
        async:false,
        url:path+"/checkout/checkoutAnalysis/queryCheckining.do",
        type:"post",
        dataType:"json",
        data:{"driver_code":driverCode},
        success:function(data){
            res = data;
        }
    });
    return res;
}


/**
 * 查询乘务员出退勤
 * @param driverId
 * @returns {Boolean}
 */
function checkDriverCheckInAndOut(driverId) {
    var isExits = false;
    $.ajax({
        async: false,
        url: path + "/checkout/checkoutAnalysis/queryDriverSecondRequest.do",
        type: "post",
        dataType: "text",
        data: {driver_id: driverId},
        success: function (data) {
            if (data == "1") {
                isExits = true;
            }else if(data==""){
                isExits = true;
            }
        }
    });
    return isExits;
}

/**
 * 最终的退勤结果信息
 */
function checkOutResultDetail() {
    //直接查询后台，获取分析数据
    var gridObj = itemData;
    //查询交路数据
    var sas = startAndEndStation;
    var resultInfo = "乘务员同志，您辛苦了！";
    if(sas==null){
        $(".delete-cls").remove();
        $("#causeDetail").append("<span id = 'result' class='span-cls delete-cls'>暂未查询到有效信息</span>");
        return;
    }else if(sas.length==1&&sas[0].yun_an_isnotexits=="1"&&sas[0].lkj_isnotexits=="0"){
        return;
    }
    var startend = true;
    for (var i = 0; i < sas.length; i++) {
        var lkj = sas[i].lkj_isnotexits;
        var yunan = sas[i].yun_an_isnotexits;
        if (lkj == "1" && yunan == "1" || yunan == "0") {
            startend = true;
        } else {
            startend = false;
            break;
        }
    }
    if (startend) {
        resultInfo += "您的本次出乘文件转储完整。";
    } else {
        resultInfo += "您的本次出乘文件转储有缺项，请填写机调-18说明。";
    }

    if (gridObj == null||gridObj.length<1) {
        $(".delete-cls").remove();
        $("#causeDetail").append("<span id = 'result' class='span-cls delete-cls'>"+resultInfo+"</span>");
        sendMSG();
        return;
    }
    //乘务员信息
    var driverInfo = $("#driverInfo").text();
    driverInfo = driverInfo.substr(0, driverInfo.length - 6);
    var allObj = {};
    var arrs = [];
    var countA = 0;

    //把A对象拿出来
    var temp1 = [];
    for (var j = 0; j < gridObj.length; j++) {
        if (gridObj[j].level == 'A') {
            temp1.push(gridObj[j]);
        }
    }

    //遍历A类结果
    for (var i = 0; i < temp1.length; i++) {
        countA += temp1[i].count_problem_code;
        arrs.push(temp1[i].new_problem_code + "" + temp1[i].count_problem_code + "次");
    }

    allObj.arrsA = arrs;
    allObj.countA = countA;
    arrs = [];
    var countB = 0;
    for (var i = 0; i < gridObj.length; i++) {
        if (gridObj[i].level == "B") {
            countB += gridObj[i].count_problem_code;
            arrs.push(gridObj[i].new_problem_code + "" + gridObj[i].count_problem_code + "次");
        }
    }
    allObj.arrsB = arrs;
    allObj.countB = countB;
    arrs = [];

    var countC = 0;
    for (var i = 0; i < gridObj.length; i++) {
        if (gridObj[i].level == "C") {
            countC += gridObj[i].count_problem_code;
            arrs.push(gridObj[i].new_problem_code + "" + gridObj[i].count_problem_code + "次");
        }
    }
    allObj.arrsC = arrs;
    allObj.countC = countC;

    if ((allObj.arrsA).length == 0 && (allObj.arrsB).length == 0 && (allObj.arrsC).length == 0) {
        $(".delete-cls").remove();
        if (startend) {
            $("#causeDetail").append("<span id = 'result' " +
                "class='span-cls delete-cls'>" + resultInfo + "执标较为规范。</span>");
            sendMSG();
        } else {
            $("#causeDetail").append("<span id = 'result' " +
                "class='span-cls delete-cls'>" + resultInfo + "</span>");
            sendMSG();
        }
    } else {
        $(".delete-cls").remove();
        var countstr = [];
        for (var i = 0; i < gridObj.length; i++) {
            if (gridObj[i].level == "A") {
                countstr.push(gridObj[i].new_problem_code + "" + gridObj[i].count_problem_code + "次");
            }
        }
        if (countstr.length > 0) {
            var res = resultInfo + "系统检出" + countstr.join(",") + ",请填写机调-18说明。";
            $("#causeDetail").append("<span id = 'result' class='span-cls delete-cls'>" + res + "</span>");
            sendMSG();
        } else {
            if (startend) {
                var tmArr = [];
                for (var i = 0; i < gridObj.length; i++) {
                    if (gridObj[i].level == "B" || gridObj[i].level == "C") {
                        tmArr.push(gridObj[i].new_index_code + "");
                    }
                }
                if (tmArr.length > 0) {
                    var res = resultInfo + "执标较为规范，请在" + tmArr.join(",") + "上进一步改进。";
                    $("#causeDetail").append("<span id = 'result' class='span-cls delete-cls'>" + res + "</span>");
                    sendMSG();
                } else {
                    var res = resultInfo + "执标较为规范";
                    $("#causeDetail").append("<span id = 'result' class='span-cls delete-cls'>" + res + "</span>");
                    sendMSG();
                }
            } else {
                $("#causeDetail").append("<span id = 'result' class='span-cls delete-cls'>" + resultInfo + "</span>");
                sendMSG();
            }
        }
    }
}

/**
 * 发送短信
 */
function sendMSG(){
    var content = $("#result").text();
    $.ajax({
        url:path+"/checkout/checkoutAnalysis/sendMessages.do",
        type:"post",
        dataType:"json",
        data:{"driverCode":driverCode,"sendContent":content},
        success:function(data){
        }
    });
}

/**
 * 提交当前文件缺失原因
 */
function saveDataLoseCause() {
    var cause = $("#loseName option:selected").text();
    if (cause == "" || cause == null) {
        swal("", "请选择缺失原因", "error");
        return;
    }
    //startTerminalData
    for (var i = 0; i < startTerminalData.length; i++) {
        if (startTerminalData[i].id == dataLose.id) {
            startTerminalData[i].defect = cause;
        }
    }

}

/**
 *获取文件缺失原因
 */
function getLoseFileCause() {
    $.ajax({
        url: path + "/checkout/checkoutrule/getLoseFileCause.do",
        type: "post",
        dataType: "json",
        success: function (result) {
            var dom = $("#loseName");
            dom.empty();
            for (var i = 0; i < result.length; i++) {
                dom.append("<option value='" + result[i].lose_id + "'>" + result[i].cause_name + "</option>");
            }
        }
    });
}

/**
 * 获取对分析有异议的原因
 * @returns
 */
function getExceptionCause() {
    $.ajax({
        url: path + "/checkout/checkoutAnalysis/getExceptionCause.do",
        type: "post",
        dataType: "json",
        success: function (result) {
            exception = result;
        }
    });
}

/**
 * 当前司机退勤的始发终到
 */
function currentDriverStartAndEnd() {
    var isnext=true;
    $.ajax({
        url: path + "/checkout/checkoutAnalysis/currentDriverStartAndEnd.do",
        type: "post",
        datatype: "json",
        data: {driverId: driverCode},
        success: function (data) {
            var result = data.lkj;
            if (result == null || result.length < 1) {
                $(".delsubmit-cls").html("");
                $(".chuqin-cls").hide();
                $(".recorder-cls").show();
                swal("", "暂未查询到信息！", "error");
                isnext=false;
            }
            if(isnext){
                realyStartAndEndStationData = result;
                startAndEndStation = result;
                //画图
                // var lkjdata = result.lkj;
                graphStartAndEndStation(result);
                var yunandata = result.yunan;
                //grahYunAndata(yunandata);
                // var t1 = [];
                // for(var i = 0;i<lkjdata.length;i++){
                //     var lkjhtml = "";
                //     lkjhtml+=lkjdata[i].shifashijian+"("+lkjdata[i].shifazhan+")<="+lkjdata[i].traincheci+"(车次)=>"
                //     lkjhtml+=lkjdata[i].zhongdaoshijian+"("+lkjdata[i].zhongdaozhan+")";
                //     t1.push(lkjhtml);
                // }
                // var lkjarr = "<p>LKJ数据:</p>"+t1.join(",").replace(",","<font color='black'>===》</font>");

                //运安数据
                /*var t2 = [];
                for(var i=0;i<yunandata.length;i++){
                    var yunanhtml="";
                        yunanhtml+=yunandata[i].shifashijian+"("+yunandata[i].shifazhan+")<="+yunandata[i].traincheci+"(车次)=>"
                        yunanhtml+=yunandata[i].zhongdaoshijian+"("+yunandata[i].zhongdaozhan+")";
                    t2.push(yunanhtml);
                }
                var yunanarr = "<p>运安出退勤数据:</p>"+t2.join(",").replace(",","<font color='black'>===》</font>");
                // $("#lkjdata").html(lkjarr);
                $("#yunandata").html(yunanarr);*/

                //graphStartAndEndStation(result);// 画图
                var dataExists = false;
                if (result.length > 0) {
                    for (var i = 0; i < result.length; i++) {
                        //resault-label
                        if (result[i].yun_an_isnotexits == '1' && result[i].lkj_isnotexits == '0') {
                            dataExists = true;
                            break;
                        }
                    }
                }

                if (dataExists) {
                    $("#resault-label").html("数据存在缺失，请确认");
                    $("#up_down_btn").click();
                } else {
                    if (result.length < 1 || result == null || result == "" || result == undefined) {
                        $("#resault-label").html("未查询到交路");
                    } else {
                        $("#resault-label").html("数据完整");
                    }
                }
                if (result.length > 0) {
                    for (var i = 0; i < result.length; i++) {
                        if (result[i].yun_an_isnotexits == '1' && result[i].lkj_isnotexits == "0") {
                            result[i].id = i;
                            result[i].defect_value = "";
                            startTerminalData.push(result[i]);
                        }
                    }
                }
                getDriverAnayContext(driverCode);// 获取分析数据
            }
        }
    });
}


function grahYunAndata(data){
    var c=document.getElementById("yunancanvas");
    var x = 0;//文字宽度
    var y = 0;//文字高度
    var chang=0;
    var kuang=0;
    var gao = 0;
    var images=new Image();
    var cxt=c.getContext("2d");

    for(var i = 0;i<data.length;i++){
        cxt.beginPath();
        cxt.font = "16px 微软雅黑";
        cxt.fillStyle = "black";
        cxt.beginPath();
        cxt.fillRect(x,y,x+100,y+50);

        cxt.fillText(data[i].shifashijian, x,y+50);
        cxt.fillText(data[i].shifazhan, x,y);
        x=x+100;
        cxt.closePath();
       // cxt.stroke();
    }
}


/**
 * 绘制始发终到
 */
function graphStartAndEndStation(data) {
    $tenly.update(data);
    $.post(path + "/checkout/checkoutAnalysis/getLoseCause.do", function (data) {
        var dom = $(".sel-cls");
        dom.empty();
        for (var i = 0; i < data.length; i++) {
            if (i == 0) {
                dom.append("<option value='0' selected='selected'></option>");
            }
            dom.append("<option value='" + data[i].lose_id + "'>" + data[i].lose_cause + "</option>");
        }
    });
}

/**
 * 乘务员分析表格
 * 2018-10-31
 * hqh 更新2018-12-17
 * 此表后台有所更新
 * @param driverId
 */
function getDriverAnayContext(driverId) {
    $.ajax({
        url: path + "/checkout/checkoutAnalysis/queryCurrentDriverAnalyData.do",
        type: "post",
        dataType: "json",
        data: {"driver_id": driverId},
        success: function (data) {
            if (data != null) {
                itemAnasysResultData = data;
                itemData = data;
                //退勤结果信息提示，及发送短信
                checkOutResultDetail();
                //saveAnasysisResults(data);
            }
        }
    });
}

/**
 * 保存分析出来的作业问题
 * @param param
 */
function saveAnasysisResults(param) {
    var paramObj = JSON.stringify(param);
    $.ajax({
        url: path + "/checkout/checkoutAnalysis/saveAnasysisResults.do",
        type: "post",
        dataType: "text",
        data: {"res": paramObj},
        success: function (res) {
        },error:function(){
        }
    });
}


/**
 * 项点分析结果展示ABC级别
 * @param data
 * @returns {*}
 */
function showGridByLevel(data) {
    var levelSel = $("#levelSelect option:selected").val();
    var obj = [];
    if (levelSel == '0' || data == null) {
        return data;
    }
    for (var i = 0; i < data.length; i++) {
        if (levelSel == '1') {
            if (data[i].level == "A" || data[i].level == "B") {
                obj.push(data[i]);
            }
        } else if (levelSel == '3') {
            if (data[i].level == "C") {
                obj.push(data[i]);
            }
        }
    }
    return obj;
}
//项点分析结果 2019-01-22
function itemGrid(driverId){
    var proLevel = $("#levelSelect").val();
    $("#userinfo").bootstrapTable('destroy');
    $("#userinfo").bootstrapTable({
        url: path + "/checkout/checkoutAnalysis/getItemGridData.do",
        dataType: "json",
        showPaginationSwitch: false,
        striped: true,
        cache: false,
        sortName: '',
        queryParamsType: "",
        queryParams: function queryParams(params) {
            var param = {
                driver_id: driverId,
                problem_level:proLevel
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
        },                   //是否显示父子表
        columns: [
            {
                field: 'ids', title: "序号", align: 'center', formatter: function (value, row, index) {
                    return index + 1;
                }
            },
            {field: 'id', title: "序号", visible: false},
            {field: 'driver_id', title: "乘务员编号", visible: false, align: 'center'},
            {field: 'new_scenario_code', title: "作业场景", align: 'center'},
            {field: 'new_index_type_code', title: "作业项目", align: 'center'},
            {field: 'new_index_code', title: "作业项点", align: 'center'},
            {field: "new_problem_code", title: '作业问题', align: 'center'},
            {field: "count_problem_code", title: '违标次数', align: 'center'},
            {
                field: "level", title: '违标级别', align: 'center',
                formatter:function(value, row, index){
                    if(value=="C"){
                        return "III类";
                    }else if(value=="B"){
                        return "II类";
                    }else if(value=="A"){
                        return "I类";
                    }
                }
            }, {
                field: "querydetail", title: '查看详情', align: 'center',
                formatter: function (value, row, index) {
                    var i = 0;
                    if(i==0){
                        checkInAndOutTimes = row.noteStartDateAndTermial;
                        i++
                    }
                    var e = '<button type="button" class="btn btn-primary"  ' +
                        'onclick="queryScanDetail(\'' + row.id + '\')"  ' +
                        'style="color: #0000FF;' +
                        'text-decoration: underline;' +
                        'cursor: pointer;' +
                        'background: transparent;' +
                        'border: 0;' +
                        'outline: none;">查看详情</button>';
                    return e;
                }
            }]
    });
}

/**
 * 查询项点分析结果 点击查询详情按钮
 * @param rowId
 */
function queryScanDetail(rowId) {
    var rowData = $('#userinfo').bootstrapTable('getRowByUniqueId', rowId);
    console.info(rowData);
    var level = rowData.level;
    if (level == 'B' || level == "C") {//B级跟C级不需要确认
        $("#showOrHidden").html("");
    } else {
        $("#showOrHidden").html("");
        var divBtn = '<button type="button" style="margin-left:0px;width:100px;"' +
            '                        class="btn btn-primary" ' +
            '                 onclick="exceptionSubmits('+rowId+')" data-dismiss="modal">确定' +
            '                </button>' +
            '                <button type="button" style="margin-left:50px;width:100px;"' +
            '                        class="btn btn-primary" data-dismiss="modal">取消' +
            '                </button>';
        $("#showOrHidden").html(divBtn);
    }
    getModalGrid(rowData);
    $("#myModal").modal();
}

/**
 * 查询乘务员基本信息
 * 显示分析页面头部
 */
function queryDriverById(driverId) {
    $.ajax({
        url: path + "/checkout/checkoutAnalysis/queryCurrentCheckOut.do",
        type: "post",
        dataType: "json",
        data: {"driver_id": driverId},
        success: function (data) {
            var groupByIdAndName = data.driver_name +
                "(" + data.driver_id + ")退勤分析结果";
            $("#driverInfo").text(groupByIdAndName);
        }
    });
}

/**
 * 项点分析结果详情表格
 */
function getModalGrid(postData) {
    $("#driverDetailGrid").bootstrapTable('destroy');
    $("#driverDetailGrid").bootstrapTable({
        url: path + "/checkout/checkoutAnalysis/itemsDetailPro.do",         //请求后台的URL（*）
        dataType: "json",
        async: false,
        method: 'post',                      //请求方式（*）
        pagination: true,
        paginationLoop: false,
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        showPaginationSwitch: false,			//是否显示数据条数选择框
        pageSize: "20",
        pageNumber: "1",
        pageList: [20, 30, 40],
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        order: "asc",
        sortName: '',
        limit: 1,
        offset: 20,
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
        uniqueId: "exception_id",                     //每一行的唯一标识，一般为主键列
        detailView: false,                   //是否显示父子表
        responseHandler: function (res) {//在ajax请求成功后，填充数据之前可以对返回的数据进行处理
            var data = res["rows"];
            return {
                "total": res.total,//总页数
                "pageNumber": res.currentPage,
                "rows": data //数据转成json对象
            };
        },                   //是否显示父子表
        columns: [{
            field: 'Number',
            title: "序号",
            formatter: function (value, row, index) {
                return index + 1;
            }
        }, {
            field: 'train_batch_no', title: "车次"
        }, {
            field: 'startTime', title: "作业时间"
        }, {
            field: 'zhandian', title: "地点"
        }, {
            field: 'new_problem_code', title: "作业问题"
        }, {
            field: "memo", title: '问题描述'
        }, {
            field: 'driverException',
            title: '问题确认',
            formatter: function (value, row, index) {
                if (row.driverException == "B" || row.driverException == "C") {
                    return "II/III类无需确认";
                }
                var exobj = exception;
                var selHtml = "<select name='driverException'><option value='" + row.exception_id + "_0_无'>无</option>";
                for (i in exobj) {
                    if (exobj[i].cause_name == "" || exobj[i].cause_name == undefined) continue;
                    selHtml += "<option " +
                                    "value='" + row.exception_id + "_1_" + exobj[i].cause_name + "'>"
                                    + exobj[i].cause_name + "" +
                               "</option>";
                }
                selHtml += "</select>";
                return selHtml;
            },
        }, {
            field: "queryDetailBtn", title: "查看数据明细", events: operateEvents, formatter: "operatePeriodFormatter"
        }, {
            field: "date", title: '开车日期', visible: false
        }, {
            field: "time", title: '时间', visible: false
        }, {
            field: "region", title: '记录', visible: false
        }, {
            field: "scenario_code", title: '作业场景', visible: false
        }, {
            field: "index_type_code", title: '作业项目', visible: false
        }, {
            field: "index_code", title: '作业项点', visible: false
        }, {
            field: "problem_code", title: '作业问题', visible: false
        }, {
            field: "deduct_score", title: 'deduct_score', visible: false
        }, {
            field: "qualified", title: 'qualified', visible: false
        }, {
            field: "driver_id", title: '乘务员编号', visible: false
        }]
    });
}


function operatePeriodFormatter(value, row, index) {
    if (row.queryDetailBtn == "0") {
        return [
            '<button type="button" class="RoleOfadd btn btn-primary"  ' +
            'style="color: #0000FF;text-decoration: underline;' +
            'cursor: pointer;background: transparent;border: 0;outline: none;">查看数据明细</button>'
        ].join('');
    }
    return "";
}

//初始化操作按钮的方法
window.operateEvents = {
    //违标项点
    'click .RoleOfadd': function (e, value, row, index) {
        //操作业务 add(row.id);
        $("#findHostoryModal").modal();
        findHostoryRecord(row);

    }
};


/**
 * 针对分析，查询历史详情，追索到原始lkj记录数据
 */
function findHostoryRecord(data) {
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
            {field: 'shijian_time', title: "时间", align: 'center'},
            {field: 'event', title: "事件", align: 'center'},
            {field: "mileage", title: '里程', align: 'center'},
            {field: "distance", title: '距离', align: 'center'},
            {field: "signal", title: '信号', align: 'center'},
            {field: "signaler", title: '信号机', align: 'center'},
            {field: "speed", title: '速度', align: 'center'},
            {field: "speed_limit", title: '限速', align: 'center'},
            {field: "condition", title: '工况', align: 'center'},
            {field: "guanya", title: '管压', align: 'center'},
            {field: "zhagang_zhagang1", title: '缸压', align: 'center'},
            {field: "speed_current", title: '电流', align: 'center'},
            {field: "jungang1_jungang", title: '均缸1', align: 'center'},
            {field: "jungang2_zhagang2", title: '均缸2', align: 'center'},
            {field: "xianluhao", title: '线路号', align: 'center'},
            {field: "xianluming", title: '线路名', align: 'center'},
            {field: "xingbie", title: '行别', align: 'center'},
            {field: "chezhan", title: '车站', align: 'center'},
            {field: "chezhanhao", title: '车站号', align: 'center'},
            {field: "mode", title: '模式', align: 'center', visible: false},
            {field: "qita", title: '其它', align: 'center'}
        ]
    });
}

/**
 * 项点分析结果
 * 保存对分析结果存在疑问的数据  每个A类问题都需要确认
 */
function exceptionSubmits(rowId) {
    var isExits = checkDriverCheckInAndOut(driverCode);
    if (isExits) {
        swal("", "无需重复确认！", "error");
        return;
    }
    var allo = $("#driverDetailGrid").bootstrapTable("getData");//获取所有行数据
    var o = getExceptionDetailDataForJSPGrid();
    //处理数据
    for (var j = 0; j < allo.length; j++) {
        for (var i = 0; i < o.length; i++) {
            var _train_batch_no = allo[j].train_batch_no;
            var _startTime = allo[j].startTime;
            var _zhandian = allo[j].zhandian;

            var train_batch_no = o[i].train_batch_no;
            var startTime = o[i].startTime;
            var zhandian = o[i].zhandian;

            if (_train_batch_no == train_batch_no
                && _startTime == startTime && _zhandian == zhandian) {
                allo[j].driverException = o[i].driverException;
            }
        }
    }
    var ojb = JSON.stringify(allo);
    $.ajax({
        url: path + "/checkout/checkoutAnalysis/saveExceptionDatas.do",
        type: "post",
        dataType: "json",
        data: {"object": ojb},
        success: function (result) {
            if (result == "1") {
                swal("", "保存成功", "success");
                for(var i = 0;i<itemAnasysResultData.length;i++){
                    if(itemAnasysResultData[i].id==rowId){
                        itemAnasysResultData[i].is_confirm_A="1";
                        break;
                    }
                }
            } else {
                swal("", "保存失败", "error");
            }
        }, error: function () {

        }
    });
}

/**
 * 保存对分析结果查看详情中存在异议的数据
 * 此方法保存数据的时候可以通用
 */
function getExceptionDetailDataForJSPGrid() {
    var head = [];
    $("#driverDetailGrid thead tr").each(function (e) {
        //获取头
        $(this).children('th').each(function () {
            var headerProperty = $(this).attr("data-field");
            head.push(headerProperty);
        });
    });

    var bodyText = [];
    $("#driverDetailGrid tbody tr").each(function (e) {
        //获取头
        var objTxt = {};
        $(this).children('td').each(function (index) {
            var str = "";
            var sels = $(this).children();
            if (sels.length === 0) {
                str = $(this).html();
            } else {
                if (sels.length === 1) {
                    var child = sels[0];
                    if (child.tagName === 'SELECT') {
                        //str = $(this).children('select').find("option:selected").text();
                        str = $(this).children('select').find("option:selected").val();
                    } else if (child.tagName === 'INPUT') {

                    } else if (child.tagName === 'BUTTON') {

                    } else {

                    }
                } else {

                }
            }
            var key = head[index];
            objTxt[key] = str;
        });
        bodyText.push(objTxt);
    });
    return bodyText;
}

/**
 * 是否存在问题,一旦点击确认之后不允许再次进行退勤操作
 * @param rowid
 * @param name
 * @param val
 * @param iRow
 * @param iCol
 * dataDefectObj 数据缺失数据
 * 项点分析结果表格中的数据，前端已经做了分页，无法获取全部的行数据，所以这部分的数据直接从后台去取，然后存到数据库中去
 * times  出退勤时间
 */
function isPro(dataDefectObj) {
    //获取退勤结果信息
    var result = $("#result").text();
    var o = {};
    var dataDefect = JSON.stringify(dataDefectObj);
    var sasData = JSON.stringify(realyStartAndEndStationData);
    o.dataLose = dataDefect;
    o.sasData = sasData;
    o.driver_id = driverCode;
    o.checkInAndOutTimes = checkInAndOutTimes;
    o.checkoutResult = result;
    swalCommonsToAjax(o);
}

function swalCommonsToAjax(obj) {
    swal({
        title: "确认退勤窗口",
        text: "",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        closeOnConfirm: false
    }, function (isConfirm) {
        if (isConfirm) {
            $.ajax({
                url: path + "/checkout/checkoutAnalysis/saveAll.do",
                type: "post",
                dataType: "json",
                data: obj,
                success: function (result) {
                    if (result == "1") {
                        saveForTmpToOperationException(obj.driver_id);
                        //homeQuery   是否关闭当前窗口
                        closeWindow();
                    } else {
                        swal("", "处理失败", "error");
                    }
                }
            });
        }
    });
}

function closeWindow(){
    swal({
        title: "温馨提示！您已成功退勤，是否关闭当前窗口?",
        text: "",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        closeOnConfirm: false
    }, function (isConfirm) {
        if (isConfirm) {
            window.close();
        }
    });
}


/**
 * 处理yw_checkout_operation_exception_tmp表数据到yw_checkout_operation_exception
 */
function saveForTmpToOperationException(driverID){
    var object = {"driver_id":driverID};
    $.post(path + "/checkout/checkoutAnalysis/saveForTmpToOperationException.do",object,function(data){});
}

   
   