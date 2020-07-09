<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>项目管理新增</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>
 <script  type="text/x-jsrender" id ="dataListFormTemp">
	<tr>
		<td>{{:worker_nm}}</td>  
    	<td>{{:work_hour}}</td>
    	<td>{{:unit}}</td>
 	</tr>
</script>
 <script  type="text/x-jsrender" id ="outputListTotalTemp">
	<tr>
		<td>加班</td>
		<td>{{:overtime_hour}}</td>
		<td><span>小时</span></td>
	</tr>
	<tr>
		<td width="30%" class="odd w10">固定人力费</td>
		<td width="40%" class="odd w10">{{:human_cost}}</td>
		<td width="30%" class="odd w10"><span>元</span></td>
	</tr>
</script>
<script  type="text/x-jsrender" id ="dataInfoFormTemp" >
	<tr>
        <td width="50px">餐费</td>
        <td>{{:meal_hour}}
        </td>
        <td width="30px"><span>小时</span></td>
	</tr>
	<tr>
    <td width="50px"  class="odd w10">餐补</td>
    <td  class="odd w10">{{:meal_cost}}
    </td>
    <td width="30px" class="odd w10"><span>元</span></td>
</tr>
</script>

 <script  type="text/x-jsrender" id ="dataListMachFormTemp">
	<tr>
		<td>{{:worker_nm}}</td> 
    	<td>{{:work_hour}}</td>
    	<td>{{:use_hour_cost}} {{:unit}}</td>
    	<td id ="work_hour2">{{:work_hour2}}</td>
    	<td>{{:use_day_cost}} {{:unit2}}</td>
 	</tr>
</script>

 <script  type="text/x-jsrender" id ="outputMachineTemp">
	<tr>
		<td width="30%" class="odd w10">机械费</td>
		<td width="40%" class="odd w10" colspan="3">{{:mach_cost}}</td>
		<td width="30%" class="odd w10" id="last"><span>元</span></td>
	</tr>
</script>

<!-- 材料费补充-页面加载 -->
<script type="text/x-jsrender" id="initMatTemplate">
<tr class="odd w10">
	<td>{{:confirm_date}}</td>
	<td>{{:cost_nm}}</td>
	<td>{{:amount}}</td>
	<td>{{:material_cost_detail}}</td>
	<td>{{:dept_nm}}</td>
	<td>{{:remark}}</td>
</tr>
</script>
<body>
	<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" id="formId">
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">结算</a>&nbsp;>&nbsp;出勤管理详细</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18"><input class="title mb18" type ="text" id ="work_out_nm" style="border:0px;" readonly/> 
                		</div>
                		<table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr><td><span class="f14" >固定人力费统计：</span></td></tr>
                        </table>
                        <table  id="dataListFormT"  class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
	  						<thead>
		  						<tr>
		  						<th class="odd">姓名</th>
		                        <th class="odd">合计</th>
		                        <th class="odd">单位</th></tr></thead>
		                    <tbody id="dataListForm" class="dataListForm" >
									
							</tbody>
							<tfoot id="outputList_Total">
								
							</tfoot>
  						</table>
  						<table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr><td><span class="f14" >餐费统计：</span></td></tr>
                        </table>
                        <table  id="dataInfoFormT"  class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                        <thead><tr>
			  						<th class="odd w10">科目</th>
			                        <th class="odd w10">合计</th>
			                        <th class="odd w10">单位</th>
			                   </tr></thead>
		                    <tbody id="dataInfoForm" >
 
							</tbody>
							<tfoot id="dataInfoForm_Total">
							</tfoot>
                        </table>
                        <div class="fr f14 fb mb30 ml23">人工费合计：<span id="labourCostTotal" class="red"></span>元</div>
                        
                        <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr><td><span class="f14" >机械费统计：</span></td></tr>
                        </table>
                        <table  id="dataListMachFormT"  class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                         <thead><tr>
		  						<th class="odd w10">设备名称</th>
		                        <th class="odd w10">使用时间 0-4小时/天</th>
		                        <th class="odd w10">单位</th>
		                        <th class="odd w10">使用时间  4小时以上/天</th>
		                        <th class="odd w10">单位</th>
		                        </tr></thead>
		                    <tbody id="dataListMachForm">
							</tbody>
							<tfoot id="dataListMachForm_Total">
						 
							</tfoot>
                        </table>
                        
                        <table class="w_auto" border="0" cellspacing="0" cellpadding="0">
                          	<tr><td><span class="f14" >维修材料费：</span></td></tr>
                        </table>
                        <table  id="MaterialCostList"  class="tt tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                         <thead><tr>
                      			<th class="odd w8">完成日期</th>
		                        <th class="odd w8">费用种类</th>
		                        <th class="odd w8">金额（元）</th>
		                        <th class="odd">事由</th>
		                        <th class="odd w8">部门</th>
		                        <th class="odd ">备注</th>
		                        </tr></thead>
		                    <tbody id="MaterialCost">
		                    
		                    </tbody>
							<tfoot id="MaterialCost_total">
							</tfoot>
                        </table>
                        <table  id="ss" border="0" cellspacing="0" cellpadding="0">
                        <tr><td>公式：</td></tr>
                        <tr><td>固定人力费  =（工时合计+加班）* 8 * 人工费单价 </td></tr>
                        <tr><td>餐补 = 餐费 * 餐费单价</td></tr>
                        <tr><td>机械费 = 使用时间合计 * 机械费单价</td></tr>
                        <tr><td>单价参考：</td></tr>
                        <tr><td>人工费含税：31.8元/小时</td></tr>
                        <tr><td>餐费餐补含税 ：12.72元/次</td></tr>
                        </table>
                        <div class="tex_c mt22 mb30"><a href="#" id="closeBtn" class="btn1 f14 fb" onclick="javascript: history.go(-1);">返回</a>
                        </div>
                        <!-- 右侧 结束-->
    
        		</div>
          </div>
    </div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/account/js/workout_detail.js"></script>
</html>