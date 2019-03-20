<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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