<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>维修详情</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/repair/js/repair_result_detail.js"></script>
<script type="text/x-jsrender" id="repairCostDetailListTemplate">
{{for repairCostDetailList}}
	 <tr class="odd">
          <td class="w110">
			<select>
				{{renderOptionFun expensesTypeGrouplist "code_value" "code_nm" cost_type ""/}}
 			</select>
		  </td>
          <td><input type="text" value="{{:cost_detail}}"/></td>
		{{if cost_type==='1'}}
			<td class="w180"><input type="text" value="{{:spec_nm}}"/></td>
		{{else cost_type==='4'}}
			<td class="w47"><input type="text" value="{{:tool_use_spec_nm}}"/></td>
		{{else cost_type==='8'}}
			<td class="w47"><input type="text" value="{{:person_expenses_spec_nm}}"/></td>
		{{else}}
        	<td class="w180"><input type="text" value="{{:spec}}"/></td>
		{{/if}}
          <td class="w80p"><input type="text" value="{{:amount}}"/></td>
		{{if showMoney==='Y'}}
			<td class="w80p"><input type="text" value="{{:unit_price}}"/></td>
		{{else}}
			<td class="w47"><input type="text" value="******"/></td>
		{{/if}}
		  <td class="w80p"><input id="total_price{{:#index}}" value="{{:total_price}}" type="text"/></td>
     </tr>
{{/for}}
</script>
<script type="text/x-jsrender" id="repairMachineDetailListTemplate">
{{for repairMachineDetailList}}
	 <tr class="odd">
          <td><input type="text" value="{{:machine_nm}}"/></td>
          <td class="w100p"><input type="text" value="{{:down_hour}}"/></td>
          <td colspan="3">
			<select>
				{{renderOptionFun repairDetailGrouplist "maintenance_id" "maintenance_nm" repair_detail ""/}}
 			</select>
		  </td>
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
                
                		<div class="title mb18">维修详情</div>
  						<!--表格-->
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
                                <td colspan="3"><input type="text" id="require_date" class="laydate-icon" name="repairResultInfo.require_date" dc="true" readonly/></td>
                            </tr>
                            <tr>
                            	<td class="odd w10">维修内容</td>
                                <td colspan="5"><textarea rows="3" id="repair_detail" name="repairResultInfo.repair_detail" dc="true" readonly></textarea></td>
                            </tr>
                         </table>
                         <table class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="odd w10">工时</td>
                                <td><input type="text" id="task_time" name="repairResultInfo.task_time" dc="true"/></td>
                                <td class="odd w10">人工费（单价RMB）</td>
                                <td><input type="text" id="user_price_hide" value="******" readonly/><input type="text" id="unit_price" name="repairResultInfo.unit_price" dc="true"/></td>   	
                            </tr>
                            <tr>
                            	<td class="odd">一次性费用</td>
                                <td colspan="3" align="left"><input type="text" id="one_charge" name="repairResultInfo.one_charge" dc="true"/></td>
                            </tr>
                          </table>
                          <div class="title_l">其他费用</div>
                          <table class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                            <tr class="odd">
                            	<td class="w110">分类</td>
                                <td>名称</td>
                                <td class="w180">规格</td>
                                <td class="w80p">数量</td>
                                <td class="w100p">单价</td>
                                <td class="w80p">合计</td>
                            </tr>
                          <tbody id="repairCostDetail_tbody"></tbody>
                        </table>
        				<div class="fr f14 fb">费用：<span id="tatal" class="red"></span>元</div>
                        <div class="cb"></div>
                        <table class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                         	<tr>
                            	<td class="odd w10">税金</td>
                                <td><input type="text" id="tax" readonly/></td>
                            </tr>
                            <tr>
                            	<td class="odd w10">合计</td>
                                <td><input type="text" id="total_all" readonly/></td>
                            </tr>
                            <tr>
                                <td class="odd w10">实际完成日期</td>
                                <td><input type="text" id="finish_date" readonly/></td>     	
                            </tr>
                            <tr>
                            	<td class="odd w10">备注</td>
                                <td><textarea rows="3" cols="5" id="remark" name="repairResultInfo.remark" dc="true"></textarea></td>
                            </tr>
                        </table>
                        <div class="title_l w100p">停机时间</div>
                         <table class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                            <tr class="odd">
                            	<td>设备名称</td>
                                <td class="w100p">停机时间</td>
                                <td colspan="3">维修内容</td>
                            </tr>
                               <tbody id="repairMachineDetail_tbody"></tbody>
                        </table>   
                        <div class="tex_c mt22 mb30"><a href="javascript:backBtn();" class="btn1 f14 fb mr10">返回</a></div>
                        <!-- 右侧 结束-->
    
        		</div>
        		</div>
          </div>
</body>
</html>
