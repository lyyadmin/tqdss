$(function () {
    var h = ($("body").height() - $(".tenly_dialog").height()) / 2;
    $(".tenly_dialog").css("margin-top", h + "px");
    initJwCode();
    //初始化事件
    initclikc();
    saveComputerIp();
});

function saveComputerIp() {
    $.post(path + "/checkout/checkoutAnalysis/saveComputerIp.do", {jwcode: jwcode}, function (data) {
    });
}


/**
 * 初始化所有事件
 */
function initclikc() {
    $(".onKeyToInputCls").click(function () {
        var currentText = $(this).text();
        var driverIdCodeTitle = $("#driverIdTitile option:selected").text();
        var driverId = $("#driverId").val();
        if (driverId.length < 5) {
            $("#driverId").val(driverId + "" + currentText);
            document.getElementById("driverId").focus();
        }
        if (driverId.length == 5) {
            var bl = validateDriverIsExsits("" + driverIdCodeTitle + driverId);
            if (bl) {
                swal("", "未查询到您的编号!!!", "error");
            }
        }
    });
    $(".onKeyCanel").click(function () {
        var driverId = $("#driverId").val();
        if (driverId.length > 0) {
            var endVal = driverId.substr(0, driverId.length - 1);
            $("#driverId").val(endVal);
        }
    });

    $(".delAll").click(function () {
        $("#driverId").val("");
        document.getElementById("driverId").focus();
    });
    /**
     * -----------------------登录按钮--------------
     */
    // $("#mualLogin").unbind("click").bind("click", function () {
    //     modleDialog();
    // });
    //
    // $("#mualLoginOut").unbind("click").bind("click", function () {
    //     $("#close_dialog").show();
    //     return;
    // });
    /**
     * -----------------------退出按钮--------------
     */

    // $("#confirm-btn").on('click', function () {
    //     var authorizationId = $("#verification").val();
    //     if (authorizationId.trim() == "" || authorizationId == undefined || authorizationId == null) {
    //         $("#verification").val("").focus();
    //         swal("", "请输入认证口令", "error");
    //     } else {
    //         //删除cookies
    //         $.ajax({
    //             url: path + "/admin/system/loginOut.do",
    //             type: "post",
    //             dataType: "json",
    //             data: {"authorizationId": authorizationId.trim()},
    //             // contentType:"application/json",
    //             success: function (result) {
    //                 if (result == "1") {
    //                     delCookie("__checoutuser");
    //                     swal("", "已经退出", "success");
    //                 } else {
    //                     swal("", "认证失败，暂不能退出", "error");
    //                 }
    //             }
    //         });
    //         $("#close_dialog").hide();
    //     }
    // });
    // $("#cansel-btn").on('click', function () {
    //     $("#close_dialog").hide();
    // });
}

/**
 * 退勤按钮
 */
function t() {//退勤
    var driverIdCodeTitle = $("#driverIdTitile option:selected").text();
    var driverCode = $("#driverId").val().trim();
    /*
        验证乘务员编号正确性
    */
    if (validateDriverId(driverCode)) {
        document.getElementById("checkoutId").href = "javascript:void(0)";
    } else {
        $("#driverId").val("");
        document.getElementById("checkoutId").href = path + "/checkout/checkoutAnalysis/checkout.do?driver_code=" + driverIdCodeTitle + "" + driverCode;
    }
}

function autoQuery() {//自助查询
    var driverCode = $("#driverId").val();
    if (validateDriverId(driverCode)) {
        document.getElementById("autoQuery").href = "javascript:void(0)";
    } else {
        document.getElementById("autoQuery").href = path + "/checkout/checkOutBuffetQuery/buffetQuery.do?driver_code=" + driverIdCodeTitle + "" + driverCode;
    }
}

/*
	检测网络是否正常
 */
function checkInternet() {
    var bl = false;
    $.ajax({
        async: false,
        url: path + "/checkout/checkoutAnalysis/checkInternetIsOk.do",
        type: "post",
        dataType: "text",
        success: function (data) {
            if (data == "true") {
                bl = true;
            }
        }, error: function () {
            swal("", "系统异常", "error");
        }
    });
    return bl;
}

/**
 * 验证乘务员编号
 * @param driverCode
 * @returns
 */
function validateDriverId(driverCode) {
    var driverIdCodeTitle = $("#driverIdTitile option:selected").text();
    if (driverCode == undefined || driverCode == null || driverCode == "") {
        swal("", "请输入乘务员编号", "error");
        return true;
    } else {
        if (driverCode.trim().length != 5) {
            swal("", "编号位数不正确", "error");
            return true;
        } else {
            var patrn = /^([0-9]\d*|0)(\.\d*[0-9])?$/;
            if (!patrn.exec(driverCode)) {
                swal("", "必须是0-9数字 ", "error");
                return true;
            }
        }
    }
    //验证当前乘务员编码是否存在
    var bl = validateDriverIsExsits("" + driverIdCodeTitle + driverCode);
    return bl;
}

/**
 * 验证乘务员是否存在
 * 首先要查机务段，因为不同机务段，乘务员编号可能会有重复
 * 所以登录在哪个地方只能是登录的地方的机务段
 * @returns {Boolean}
 */
function validateDriverIsExsits(driver_code) {
    var bl = false;
    $.ajax({
        async: false,
        url: path + "/checkout/checkoutAnalysis/queryDriverIsExsits.do",
        type: "post",
        dataType: "json",
        data: {"driver_code": driver_code},
        success: function (data) {
            if (data == "0") {
                swal("", "未查询到您的编号!!!", "error");
                bl = true;
            }
        }
    });

    return bl;
}

/**
 * 验证粘贴的内容
 * @param e
 */
function paste(e) {
    $("#driverId").val("");
    var driverCode = $("#driverId").val().trim();
    if(driverCode.length>=5){
        $("#driverId").val(driverCode.substr(0,5));
        return;
    }
    var data = e.clipboardData;
    var driverCode = data.getData('text').trim(); // 获得粘贴的内容
    var patrn = /^([0-9]\d*|0)(\.\d*[0-9])?$/;
    if (!patrn.exec(driverCode)) {
        swal("", "编号必须为5位0-9数字", "error");
        return;
    }
    if (driverCode.trim().length > 5) {
        swal("", "编号必须为5位0-9数字", "error");
        return;
    }
    if (driverCode.length == 5) {
        var subdriver = $("#driverIdTitile option:selected").text();
        var bl = validateDriverIsExsits("" + subdriver.trim() + driverCode.trim());
        if (bl) {
            swal("", "未查询到您的编号!!!", "error");
            return;
        }
    }
}

/**
 * 页面登录窗口
 */
function modleDialog() {
    $("#name_tip").html("");
    $("#pwd_tip").html("");
    $("#dialog_mask").toggle();
}

/**
 * 初始化机务段段代码
 */
function initJwCode() {
    $("#driverIdTitile").empty();
    var dom = $("#driverIdTitile");
    dom.append("<option value='" + jwcode + "'>" + jwcode + "</option>");
}

/**
 * 输入的时候验证   位数  格式  类别   以及是否存在这个乘务员编号
 */
function getOnInput() {
    var driverIdCodeTitle = $("#driverIdTitile option:selected").text().trim();
    var checkoutId = $("#driverId").val().trim();
    if (checkoutId == "" || checkoutId == undefined || checkoutId == null) return;
    var patrnreg = /^\+?[0-9]*$/;
    if (!patrnreg.test(checkoutId)) {
        swal("", "编号必须为5位0-9数字", "error");
        checkoutId = checkoutId.substring(0, checkoutId.length - 1);
        $("#driverId").val(checkoutId);
        return;
    }
    if (checkoutId.length > 5) {
        checkoutId = checkoutId.substring(0, checkoutId.length - 1);
        $("#driverId").val(checkoutId);
        return;
    }
    //验证当前乘务员编码是否存在
    if (checkoutId.length == 5) {
        var bl = validateDriverIsExsits("" + driverIdCodeTitle + checkoutId);
        if (bl) {
            swal("", "未查询到您的编号!!!", "error");
            return;
        }
    }
}