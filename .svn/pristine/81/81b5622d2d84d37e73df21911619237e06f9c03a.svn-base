<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<div class="container">
    <div class="row search_container">
        <div class="form-inline" style="padding-left:10px;">
            <form id="conditionForm" style="display: inline-block;">
                <div id="conditionlabel" style="display: inline-block;">
                    <label>开车日期自</label>
                    <input type="text" id="startDate" name="startDate"
                           class="habits-date form-control" style="width:200px;">
                    <label>至</label>
                    <input type="text" id="endDate" name="endDate"
                           class="habits-date form-control" style="width:200px;">
                </div>
                <div class="form-inline">
                    <label>作业场景:</label>
                    <select class="selectpicker form-control" id="taskSceneHabits"
                            name="taskScene"
                            style="width:200px;padding: 0;">
                    </select>
                    <label>作业项目:</label>
                    <select class="selectpicke form-control" id="taskItemHabits"
                            name="taskItem"
                            style="width:200px;padding: 0;">
                    </select>
                    <label>作业项点:</label>
                    <select class="selectpicke form-control" id="taskItemPointHabits"
                            name="taskItemPoint"
                            style="width:200px;padding: 0;">
                    </select>
                    <label>作业问题:</label>
                    <select class="selectpicker form-control" id="taskProblemHabits"
                            name="taskProblem"
                            style="width:200px;padding: 0;">
                    </select>
                </div>
            </form>
            <button class="btn btn-primary" id="opHibitsQuery" style="display: inline-block;font-size: 20px;">查询</button>
        </div>
    </div>
</div>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <%--<div class="form-group search_container box threeview" style="float: left;width:49%;height:60%;">
                <div id="strongItem" style="width:100%;height:100%;"></div>
            </div>--%>
            <div class="form-group search_container">
                <div id="lessItem" style="width:100%;height:60%;"></div>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="form-group search_container">
                <div id="current-hostory-evaluate" style="width:100%;height:60%;"></div>
            </div>
        </div>
    </div>
</div>
<%--作业评价   模态框  点击折线图气泡出现的表格--%>
<div class="modal" id="evaluteModal" role="dialog">
    <div class="modal-dialog" style="width: 100%;">
        <div class="modal-content">
            <div class="modal-header">
                <div class="panel-heading">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="font-size: 20px;color: red;">关闭</button>
                    <h4 class="modal-title" id="">详情</h4>
                </div>
            </div>
            <div class="panel-body" style="text-align:center;height: 80%">
                <table id="evaluteGrid" class="table-hover text-nowrap table-striped"></table>
            </div>
        </div>
    </div>
</div>
