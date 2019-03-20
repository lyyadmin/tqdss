<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="modal" id="updataConditionModal" role="dialog">
        <div class="modal-dialog">
        	<div class="modal-content">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">添加记录</h4>
					</div>
				</div>
	            <div class="panel-body" style="text-align:center">
	               	<form id="updateConditionForm" class="form-horizontal">
	               		<input hidden="hiddend" name = "conditionId" value="">
	                    <table class="table table-striped">
	                      <tr>
	                      	  <td><label>规则名称</label><input id = "conName" name="conName" class="form-control"></td>
	                      	  <td><label>项点名称</label>
                     	  		  <select id="conSymbolFirst" name="conSymbolFirst" class="form-control">
                     	  				<option>未进行贯通实验</option>
                     	  		  </select>
	                      	  </td>
	                      </tr>
	                      <tr>
	                      	  <td><label>条件一</label>
		                          <select id="conSymbolFirst" name="conSymbolFirst" class="form-control">
			    						<option>&lt;</option>
			    						<option>&gt;</option>
			    						<option>&lt;=</option>
			    						<option>=&gt;</option>
			    						<option>=</option>
			    						<option>!=</option>
		                          </select>
	                          </td>
	                          <td><label>值一</label><input id="conValue" name="conValue" class="form-control"></td>
	                      </tr>
	                      <tr>
	                      	  <td><label>条件二</label><input id = "conSymbolSedond" name="conSymbolSedond" class="form-control"></td>
	                          <td><label>值二</label>
	                          	  <select id = "conValueEnd" name="conValueEnd" class="form-control">
			    						<option>&lt;</option>
			    						<option>&gt;</option>
			    						<option>&lt;=</option>
			    						<option>=&gt;</option>
			    						<option>=</option>
			    						<option>!=</option>
	                          	  </select>
	                          </td>
	                      </tr>
	                      <tr>
	                      	<td><label>启用状态</label>
		                          <select id = "conType" name="conType" class="form-control">
		                          		<option value="1">启用</option>
		                          		<option value="0">未启用</option>
		                          </select>
	                          </td>
	                          <td><label>退勤结果</label>
		                          <input type="text" id = "other" name="other" class="form-control">
	                          </td>
	                      </tr>
	                    </table>
	                 </form> 		              
		       </div>
       		   <div class="panel-footer" style="text-align:right">
                   <button type="button" style="margin-left:0px;width:100px;"
                                      class="btn btn-primary" onclick="updateRowData()" data-dismiss="modal">确定</button>
                      <button type="button" style="margin-left:50px;width:100px;"
                                      class="btn btn-primary" data-dismiss="modal">取消</button>
               </div>
		    </div>
        </div>
  	</div>
