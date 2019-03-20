<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<div class="container search_container">
    <div class="row">
        <form id="itemPontForm" style="display: inline-block;">
            <div class="form-inline" style="padding-left:10px;">
                <label>开车日期自&nbsp;&nbsp;</label><input type="text" id="start" name="startDate"
                                           class="form_datetime form-control" style="width:200px;">
                <label>&nbsp;&nbsp;至&nbsp;&nbsp;</label><input type="text" id="end" name="end"
                                           class="form_datetime form-control" style="width:200px;">
            </div>
            <div class="form-inline" style="padding-left:10px;margin-top:10px;">
                <label>作业场景:</label><select class="selectpicker form-control" id="taskScene" name="taskScene"
                                            style="width:200px;padding: 0;"></select>
                <label>作业项目:</label><select class="selectpicke form-control" id="taskItem" name="taskItem"
                                            style="width:200px;padding: 0;"></select>
                <label>作业项点:</label><select class="selectpicke form-control" id="taskItemPoint" name="taskItemPoint"
                                            style="width:200px;padding: 0;"></select>
                <label>作业问题:</label><select class="selectpicker form-control" id="taskProblem" name="taskProblem"
                                            style="width:200px;padding: 0;"></select>
            </div>
        </form>
        <button class="btn btn-primary" id="itemQuery" style="display: inline-block;font-size: 20px;">查询</button>
    </div>
</div>
<div class="container twoview">
    <!-- 违标的车次分布、违标的时段分布 -->
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h3>线别分布、时段分布</h3>
            </div>
            <div class="form-group search_container threeview" style="float: right;width:49%;height:60%;">
                <div id="violationTimePeriod" style="width:100%;height:100%;"></div>
            </div>
            <div class="form-group search_container threeview" style="float: left;width:49%;height:60%;">
                <div id="besiegedVehicle" style="width:100%;height:100%;"></div>
            </div>

        </div>
    </div>
    <!-- 违标区段分布、违标项点分布 -->
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h3>违标区段分布、违标项点分布</h3>
            </div>
            <div class="form-group search_container threeview" style="float: left;width:49%;height:60%;">
                <div id="targetOfViolation" style="width:100%;height:100%;"></div>
            </div>
            <div class="form-group search_container threeview" style="float: right;width:49%;height:60%;">
                <div id="violationOfStandard" style="width:100%;height:100%;"></div>
            </div>
        </div>
    </div>
</div>

<%--违标线别 柱子点击事件模态框--%>
<div class="modal" id="taskLineModal" role="dialog">
    <div class="modal-dialog" style="width: 100%;">
        <div class="modal-content">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <span class="panel-title">作业详情</span>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="font-size: 20px;color: red;">关闭</button>
                </div>
            </div>
            <div class="panel-body" style="text-align:center;height: 80%;">
                <table id="taskLineGrid" class="table-hover text-nowrap table-striped"></table>
            </div>
        </div>
    </div>
</div>

<%--作业详情--%>
<div class="modal" id="problemLineModal" role="dialog">
    <div class="modal-dialog" style="width: 100%;">
        <div class="modal-content">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <span class="panel-title">作业详情</span>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="font-size: 20px;color: red;">关闭</button>
                </div>
            </div>
            <div class="panel-body" style="text-align:center;height: 80%;">
                <table id="problemLineGridDetail" class="table-hover text-nowrap table-striped"></table>
            </div>
        </div>
    </div>
</div>

<%--违标时段 柱子点击事件模态框--%>
<div class="modal" id="taskPeriodModal" role="dialog">
    <div class="modal-dialog" style="width: 100%;">
        <div class="modal-content">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <span class="panel-title">作业详情</span>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="font-size: 20px;color: red;">关闭</button>
                </div>
            </div>
            <div class="panel-body" style="text-align:center;height: 80%;">
                <table id="taskPeriodGrid" class="table-hover text-nowrap table-striped"></table>
            </div>
        </div>
    </div>
</div>

<%--违标时段 作业详情--%>
<div class="modal" id="problemPeriodModal" role="dialog">
    <div class="modal-dialog" style="width: 100%;">
        <div class="modal-content">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <span class="panel-title">作业详情</span>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"  style="font-size: 20px;color: red;">关闭</button>
                </div>
            </div>
            <div class="panel-body" style="text-align:center;height: 80%;">
                <table id="problemPeriodGridDetail" class="table-hover text-nowrap table-striped"></table>
            </div>
        </div>
    </div>
</div>

<%--违标区段 柱子点击事件模态框--%>
<div class="modal" id="taskZoneModal" role="dialog">
    <div class="modal-dialog" style="width: 100%;">
        <div class="modal-content">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <span class="panel-title">作业详情</span>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="font-size: 20px;color: red;">关闭</button>
                </div>
            </div>
            <div class="panel-body" style="text-align:center;height: 80%;">
                <table id="taskZoneGrid" class="table-hover text-nowrap table-striped"></table>
            </div>
        </div>
    </div>
</div>

<%--违标区段 作业详情--%>
<div class="modal" id="problemZoneModal" role="dialog">
    <div class="modal-dialog" style="width: 100%;">
        <div class="modal-content">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <span class="panel-title">作业详情</span>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="font-size: 20px;color: red;">关闭</button>
                </div>
            </div>
            <div class="panel-body" style="text-align:center;height: 80%;">
                <table id="problemZoneGridDetail" class="table-hover text-nowrap table-striped"></table>
            </div>
        </div>
    </div>
</div>

<%--作业违标 柱子点击事件模态框--%>
<div class="modal" id="taskViolationModal" role="dialog">
    <div class="modal-dialog" style="width: 100%;">
        <div class="modal-content">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <span class="panel-title">作业详情</span>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"  style="font-size: 20px;color: red;">关闭</button>
                </div>
            </div>
            <div class="panel-body" style="text-align:center;height: 80%;">
                <table id="taskViolationGrid" class="table-hover text-nowrap table-striped"></table>
            </div>
        </div>
    </div>
</div>

<%--作业违标 作业详情--%>
<div class="modal" id="problemViolationModal" role="dialog">
    <div class="modal-dialog" style="width: 100%;">
        <div class="modal-content">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <span class="panel-title">作业详情</span>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"  style="font-size: 20px;color: red;">关闭</button>
                </div>
            </div>
            <div class="panel-body" style="text-align:center;height: 80%;">
                <table id="problemViolationGridDetail" class="table-hover text-nowrap table-striped"></table>
            </div>
        </div>
    </div>
</div>
<div class="modal" id="queryLKJModal" role="dialog">
    <div class="modal-dialog" style="width: 100%;">
        <div class="modal-content">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <span class="panel-title">作业详情</span>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"  style="font-size: 20px;color: red;">关闭</button>
                </div>
            </div>
            <div class="panel-body" style="text-align:center;height: 80%;">
                <table id="queryLKJGrid" class="table-hover text-nowrap table-striped"></table>
            </div>
        </div>
    </div>
</div>