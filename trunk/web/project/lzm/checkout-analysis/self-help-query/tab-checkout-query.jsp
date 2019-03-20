<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 页面头信息 -->
<div class="container">
    <div class="row">
        <div class="form-inline" style="text-align: center;margin-top: 0px;padding: 10px 10px 10px 10px;">
            <div class="form-group cy-mar-ver-s">
                <label class="cy-pad-hor-s">退勤日期自：</label>
                <input class="form-control dateinput dateicon ondutytime-cls-change" id="ondutytime" name="start"
                       type="text">
                <label class="cy-pad-hor-s">至：</label>
                <input class="form-control dateinput dateicon" id="offdutytime" type="text" name="end">
            </div>
            <div class="form-group cy-mar-ver-s">
                <button class="btn btn-primary cy-pad-rgt-s" style="font-size: 20px;" id="checkInAndOutSearch" type="button">查询</button>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <table class="table" id="tuiqininfo"></table>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="tuiQinPageModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="padding-right: 0px;">
    <div class="modal-dialog" style="width: 100%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="font-size: 20px;color: red;">关闭</button>
                <h4 class="modal-title" id="myModalLabel">退勤页面</h4>
            </div>
            <div class="modal-body" style="height:80%;overflow-y: auto">
                <!-- 退勤结果 -->
                <div class="titile-cls">
                        <img alt="" class="title_bg" src="<%=path %>/images/checkout-images/tui_qin_jie_guo.png">
                </div>
                <div class="form-inline" style="margin-left:100px;">
                    <label id="causeDetail"><span class="span-cls-header"></span></label>
                </div>
                <!-- 交路图 -->
                <div class="" style="margin-top:10px;">
                    <div class="titile-cls">
                        <span class="">
                            <img alt="" class="title_bg" src="<%=path %>/images/checkout-images/lkjshujuwanzhengxin.png">
                        </span>
                        <span id="resault-label"></span>
                    </div>
                    <div style="width:97%;height:200px;margin-left:1.4%;position: absolute;overflow-y: auto;">
                        <div class="panel" id="graphDivId" style="width:100%;height:200px;margin:0;padding:0;position: absolute;">
                            <canvas id="canvas" width="100%;"></canvas>
                        </div>
                    </div>
                </div>
                <!-- 表格 -->
                <div style="margin-top:210px;">
                    <div class="titile-cls">
                        <img alt="" class="title_bg" src="<%=path %>/images/checkout-images/xiang_dian_jie_guo.png">
                    </div>
                    <table id="userinfo" class="table" style="margin-top:0px;height:250px;"></table>
                </div>
            </div>
        </div>
    </div>
</div>
<%--项点详情模态框--%>
<div class="modal" id="myModal" role="dialog" aria-labelledby="itemModalDetail"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 100%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="font-size: 20px;color: red;">关闭</button>
                <h4 class="modal-title" id="itemModalDetail">项点详情</h4>
            </div>
            <div class="panel-body" style="text-align:center;height: 80%;">
                <table id="driverDetailGrid" class="table"></table>
            </div>
        </div>
    </div>
</div>
<%--查询历史lkj数据文件窗口--%>
<div class="modal" id="findHostoryModal" role="dialog">
    <div class="modal-dialog" style="width: 100%;">
        <div class="modal-content">
            <div class="modal-header">
                <div class="panel-heading">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="font-size: 20px;color: red;">关闭</button>
                    <h4 class="modal-title" id="">数据明细</h4>
                </div>
            </div>
            <div class="panel-body" style="text-align:center;height: 80%">
                <table id="findHostoryGrid" class="table-hover text-nowrap table-striped"></table>
            </div>
        </div>
    </div>
</div>
