<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!-- 文件缺失原因填写窗口 -->
<div class="modal" id="startToEndTrainModal" role="dialog">
    <div class="modal-dialog" style="height: 75px;margin: 13% auto;width: 40%;">
        <div class="modal-content">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <span class="panel-title">下拉选择数据缺失原因</span>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="font-size: 20px;color: red;">关闭</button>
                </div>
            </div>
            <div class="panel-body" style="text-align:center;height: 100%;background: #FFF;">
                <form id="loseForm">
                    <table class="table table-strap">
                        <tr>
                            <td>
                                <select id="loseName" name="lose_name" class="form-control"
                                        style="height:35px;width:250px;"></select>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="panel-footer" style="text-align:right">
                <button type="button" style="margin-left:0px;width:100px;"
                        class="btn btn-primary" onclick="saveDataLoseCause()" data-dismiss="modal">确定
                </button>
                <button type="button" style="margin-left:50px;width:100px;"
                        class="btn btn-primary" data-dismiss="modal">取消
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 对分析结果有异议的表维护 在规则页面 添加 -->
<div class="modal" id="existsModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <span class="panel-title">添加记录</span>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="font-size: 20px;color: red;">关闭</button>
                </div>
            </div>
            <div class="panel-body" style="text-align:center">
                <form id="existsForm">
                    <table class="table table-striped">
                        <tr>
                            <td><label>名称</label><input id="conName" name="cause_name" class="form-control"></td>
                        </tr>
                        <tr>
                            <td><label>类型</label>
                                <select id="types" name="types" class="form-control">
                                    <option value="1">文件缺失原因</option>
                                    <option value="2">项点分析异议</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><label>其它</label>
                                <input id="other" name="other" class="form-control">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="panel-footer" style="text-align:right">
                <button type="button" style="margin-left:0px;width:100px;"
                        class="btn btn-primary" onclick="submitExists()" data-dismiss="modal">确定
                </button>
                <button type="button" style="margin-left:50px;width:100px;"
                        class="btn btn-primary" data-dismiss="modal">取消
                </button>
            </div>
        </div>
    </div>
</div>
<!-- 对分析结果有异议的表维护 在规则页面 修改-->
<%--<div class="modal" id="existsUpdateModal" role="dialog">--%>
    <%--<div class="modal-dialog">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="panel panel-primary">--%>
                <%--<div class="panel-heading">--%>
                    <%--<span class="panel-title">修改记录</span>--%>
                    <%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="panel-body" style="text-align:center">--%>
                <%--<form id="existsUpdateForm">--%>
                    <%--<table class="table table-striped">--%>
                        <%--<tr>--%>
                            <%--<td><label>名称</label><input id="conName" name="cause_name" class="form-control"></td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td><label>类型</label>--%>
                                <%--<select id="types" name="types" class="form-control">--%>
                                    <%--<option value="1">文件缺失原因</option>--%>
                                    <%--<option value="2">项点分析异议</option>--%>
                                <%--</select>--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td><label>其它</label>--%>
                                <%--<input id="other" name="other" class="form-control">--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                    <%--</table>--%>
                <%--</form>--%>
            <%--</div>--%>
            <%--<div class="panel-footer" style="text-align:right">--%>
                <%--<button type="button" style="margin-left:0px;width:100px;"--%>
                        <%--class="btn btn-primary" onclick="submitExistsUpdate()" data-dismiss="modal">确定--%>
                <%--</button>--%>
                <%--<button type="button" style="margin-left:50px;width:100px;"--%>
                        <%--class="btn btn-primary" data-dismiss="modal">取消--%>
                <%--</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<!-- 显示加载效果 基于bootstrap实现-->
<div class="modal" id="myModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <span class="panel-title">详情</span>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="font-size: 20px;color: red;">关闭</button>
                </div>
            </div>
            <div class="panel-body" style="text-align:center">
                <table id="driverDetailGrid" class="table-hover text-nowrap table-striped"></table>
            </div>
            <div class="panel-footer" id="showOrHidden" style="text-align:right">
                <button type="button" style="margin-left:0px;width:100px;"
                        class="btn btn-primary" onclick="exceptionSubmits()" data-dismiss="modal">确定
                </button>
                <button type="button" style="margin-left:50px;width:100px;"
                        class="btn btn-primary" data-dismiss="modal">取消
                </button>
            </div>
        </div>
    </div>
</div>

<%--查询历史lkj数据文件窗口--%>
<div class="modal" id="findHostoryModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <span class="panel-title">数据明细</span>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="font-size: 20px;color: red;">关闭</button>
                </div>
            </div>
            <div class="panel-body" style="text-align:center">
                <table id="findHostoryGrid" class="table-hover text-nowrap table-striped"></table>
            </div>
        </div>
    </div>
</div>