<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>维修结果新增</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/repair/js/repair_result_add.js"></script>
<script type="text/x-jsrender" id="expensesTypeGroupDownListTemplate">
	<select data-name="select1" dcrequired="请选择分类">
		{{renderOptionFun expensesTypeGrouplist "code_value" "code_nm" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="repairDetailGroupDownListTemplate">
	<select data-name="select1">
		{{renderOptionFun maintenanceItemInfoList "maintenance_id" "maintenance_nm" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="toolUseSpecCodeGroupDownListTemplate">
	<select data-name="select1">
		{{renderOptionFun toolUseSpecGrouplist "code_value" "code_nm" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="personExpensesCodeGroupDownListTemplate">
	<select data-name="select1" >
		{{renderOptionFun personExpensesGrouplist "code_value" "code_nm" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="transportSpecCodeGroupDownListTemplate">
	<select data-name="select1">
		{{renderOptionFun transportSpecGrouplist "code_value" "code_nm" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="offerDetailListTemplate">
{{for offerDetailList}}
	 <tr class="odd" id='addr{{:#getIndex()}}'>
          <td class="w80">
			<select name='repairCostDetailList[{{:#getIndex()}}].cost_type' dc='true'>
				{{renderOptionFun expensesTypeGrouplist "code_value" "code_nm" cost_type ""/}}
 			</select>
		  </td>
          <td><input type="text" value="{{:cost_detail}}" name='repairCostDetailList[{{:#getIndex()}}].cost_detail' dc='true'/></td>
		{{if cost_type==='1'}}
			<td class="w47">
				<select name='repairCostDetailList[{{:#getIndex()}}].spec' dc='true'>
					{{renderOptionFun transportSpecGrouplist "code_value" "code_nm" spec ""/}}
 				</select>
			</td>
		{{else cost_type==='4'}}
			<td class="w47">
				<select name='repairCostDetailList[{{:#getIndex()}}].spec' dc='true'>
					{{renderOptionFun toolUseSpecGrouplist "code_value" "code_nm" spec ""/}}
 				</select>
			</td>
		{{else cost_type==='8'}}
			<td class="w47">
				<select name='repairCostDetailList[{{:#getIndex()}}].spec' dc='true'>
					{{renderOptionFun personExpensesGrouplist "code_value" "code_nm" spec ""/}}
 				</select>
			</td>
		{{else}}
        	<td class="w47"><input type="text" value="{{:spec}}" name='repairCostDetailList[{{:#getIndex()}}].spec' dc='true'/></td>
		{{/if}}
          <td class="w80"><input type="text" value="{{:amount}}" name='repairCostDetailList[{{:#getIndex()}}].amount' dc='true'/></td>
		{{if showMoney==='Y'}}
			<td class="w47"><input type="text" value="{{:unit_price}}" name='repairCostDetailList[{{:#getIndex()}}].unit_price' dc='true'/></td>
		{{else}}
			<td class="w47"><input type="hidden" value="{{:unit_price}}" name='repairCostDetailList[{{:#getIndex()}}].unit_price' dc='true'/><input type="text" value="******"/></td>
		{{/if}}
          <td class="w80"><input id="total_price{{:#getIndex()}}" type="text" value="{{:total_price}}" name='repairCostDetailList[{{:#getIndex()}}].total_price' dc='true'/></td>
 		  <td><input type="text" value="{{:remark}}"  name='repairCostDetailList[{{:#getIndex()}}].remark' dc='true'/></td>
		  <td><a href='javascript:deleteItem("{{:#index}}");' class='btn3' >删除</a></td>
     </tr>
{{/for}}
</script>
</head>
<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">维修信息</a>&nbsp;>&nbsp;维修单信息</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">维修结果新增</div>
                         <!--pop flag start -->
                                <input id="ma_sp_id" type="hidden" value=""/>
                                <input id="ma_type_id" type="hidden" value=""/>
                        <!--pop flag end-->
                        <table class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0" id="reapir_result_table">
                             <tr>
                            	<td class="odd w10">维修单号</td>
                                <td>
                                	<input type="text" id="repair_order_num" readonly/>
                                </td>
                                <td class="odd w10">申请日期</td>
                                <td>
                                	<input id="create_time" type="text" readonly/>
                                </td>
                            </tr>
                            <tr>
                                <td class="odd w10">要求完成日期</td>
                                <td><input type="text" id="require_date" name="repairResultInfo.require_date" dc="true" readonly/></td>
                                <td class="odd w10">费用类型</td>
                                <td><input type="text" id="repair_type" readonly/></td>
                            </tr>
                            <tr>
                            	<td class="odd w10">维修内容</td>
                                <td colspan="3"><textarea rows="3" id="repair_detail" name="repairResultInfo.repair_detail" dc="true" readonly></textarea></td>
                            </tr>
                         </table>
                          <div class="title_l">人工服务费<input type="hidden" id="all_price"/></div>
                         <table id="personMoney" class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="odd w10">工时</td>
                                <td><input type="text" id="task_time" name="repairResultInfo.task_time" dc="true" dctype="数字" maxlength='9' onKeyUp="personTotalMeony();"/></td>
                                <td class="odd w10">人工费（单价RMB）</td>
                                <td><input type="text" id="user_price_hide" value="******" readonly/><input type="text" id="user_price" name="repairResultInfo.unit_price" dc="true" dctype="金额" maxlength='9' readonly/></td>   	
                            </tr>
                            <tr>
                            	<td class="odd w10">一次性费用</td>
                                <td colspan="3">
                                <input type="text" id="one_charge" name="repairResultInfo.one_charge" dc="true" dctype="金额" maxlength='9' onKeyUp="personTotalMeony();"/>
                                </td>
                            </tr>
                          </table>
                          <div class="title_l">维修材料费<a id="addRows" href="javascript:addItem();" class="btn1 f14 fb fr">添加行</a></div>
                          <table class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                            <tr class="odd">
                            	<td class="w110">分类</td>
                                <td>名称</td>
                                <td class="w180">规格</td>
                                <td class="w100p">数量</td>
                                <td class="w80p">单价</td>
                                 <td class="w80p">合计</td>
                                 <td>备注</td>
                                <td class="w47">操作</td>
                            </tr>
                            <tbody id="addRowsTable">
							</tbody>
                        </table>
        				<div class="fr f14 fb">费用：<span id="total" class="red"></span>元</div>
                        <div class="cb"></div>
                         <div class="title_l">税金录入</div>
                        <table class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                        	<tr>
                            	<td class="odd w10">税金</td>
                                <td><input type="text" id="tax" onKeyUp="taxTotalMeony();"/></td>
                            </tr>
                            <tr>
                            	<td class="odd w10">合计</td>
                                <td><input type="text" id="total_all" readonly/></td>
                            </tr>
                            <tr>
                            	<td class="odd w10">维修结果/维修方法:<br/>
                            	                                          备件更换记录:<br/>
                            	                                          安全装置/阀门等恢复</td>
                                <td><textarea rows="3" cols="5" name="repairResultInfo.remark" dc="true" maxlength='1000'></textarea></td>
                            </tr>
                        </table>
                        <div class="title_l">停机时间<a href="javascript:equipPop();" class="btn1 f14 fb fr">增加设备</a></div>
                         <table class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0" id="machineForm">
                            <tr class="odd">
                            	<td class="w100p">设备名称</td>
                                <td class="w100p">停机时间</td>
                                <td colspan="3">维修内容</td>
                                <td class="w47">操作</td>
                            </tr>
                             <tbody id="addMachineRowsTable">
							</tbody>
                        </table>   
                        <div class="tex_c mt22 mb30">
	                        <a href="javascript:addRepairResultInfo();" class="btn1 f14 fb mr10">提交</a>
	                        <a href="<%=request.getContextPath()%>/pm/web/repair/repair_order_list.jsp" class="btn1 f14 fb mr10">返回</a>
	                        <a href="javascript:gotoOfferDetail();" id="offer_check" class="btn1 f14 fb">查看报价</a>
                        </div>
                        <!-- 右侧 结束-->
    
        		</div>
        		</div>
          </div>
</body>
</html>
