<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>项目管理新增</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>
 <script  type="text/x-jsrender" id ="dataListFormTemp">
	<tr id="dataListForm{{:#index}}">
		<td>{{:worker_nm}}<input id ="worker_nm" type ="hidden" value="{{:worker_nm}}"  name="workOutDetailInfo[{{:#index}}].worker_nm" dc=""/>
		<input id ="type" type ="hidden" value="{{:type}}"  name="workOutDetailInfo[{{:#index}}].type" dc=""/></td>  
    	<td><input id ="work_out_detail_id" type ="hidden" value=""  name="workOutDetailInfo[{{:#index}}].work_out_detail_id"  style="border:0px;" dc=""/>
            <input id ="work_hour" value="0" name="workOutDetailInfo[{{:#index}}].work_hour" class="toadd"  onchange="addAll()" dcrequired="请输入出勤天数" dc="" dctype="数字"/></td>
    	<td>{{:unit}}<input id ="unit" type ="hidden" value="{{:unit}}"  name="workOutDetailInfo[{{:#index}}].unit" dc=""/></td>
 	</tr>
</script>
 <script  type="text/x-jsrender" id ="outputListTotalTemp">
	<tr  id ="outputListTotal">
		<td>加班</td>
		<td><input id ="overtime_hour" value="0" name="workOutInfo.overtime_hour" onchange="addAll()" dc="" dcrequired="请输入加班小时" dctype="数字"/>
            <input id ="work_hour_total" type ="hidden" value="0" name="workOutInfo.work_hour"  dc=""  /></td>
		<td><span>小时</span></td>
	</tr>
	<tr>
		<td width="30%" class="odd w10">固定人力费</td>
		<td width="40%" class="odd w10"><input id ="human_cost" value="0"  name="workOutInfo.human_cost" readonly="true"  dc=""/></td>
		<td width="30%" class="odd w10"><span>元</span></td>
	</tr>
</script>
<script  type="text/x-jsrender" id ="dataInfoFormTemp" >
	<tr id="dataInfoForm">
        <td width="50px">餐费</td>
        <td><input id ="meal_hour" value="0"  name="workOutInfo.meal_hour" onchange="addMeal()" dctype="数字" dcrequired="请输入餐费" dc=""/>
        </td>
        <td width="30px"><span>小时</span></td>
	</tr>
	<tr>
    <td width="50px"  class="odd w10">餐补</td>
    <td  class="odd w10"><input id ="meal_cost" value="0"  name="workOutInfo.meal_cost"  readonly="true" dc=""/>
    </td>
    <td width="30px" class="odd w10"><span>元</span></td>
</tr>
</script>

<script  type="text/x-jsrender" id ="dataListMachFormTemp">
	<tr id="dataListMachForm{{:#index}}">
		<td>{{:worker_nm}}<input id ="worker_nm" type ="hidden" value="{{:worker_nm}}"  name="workOutDetailMachInfo[{{:#index}}].worker_nm" dc=""/>
						  <input id ="type" type ="hidden" value="{{:type}}"  name="workOutDetailMachInfo[{{:#index}}].type" dc=""/>
		</td>
    	<td><input id ="work_out_detail_id" type ="hidden" value=""  name="workOutDetailMachInfo[{{:#index}}].work_out_detail_id" dc=""/>
			<input id ="use_hour_cost" type ="hidden"   value="{{:use_hour_cost}}"  name="workOutDetailMachInfo[{{:#index}}].use_hour_cost" dc=""/>
			<input id ="use_day_cost"  type ="hidden"   value="{{:use_day_cost}}"  name="workOutDetailMachInfo[{{:#index}}].use_day_cost" dc=""/>
			<input id ="work_hour" value="0"  class="toaddMach" onchange="addMach()" name="workOutDetailMachInfo[{{:#index}}].work_hour"  dc="" dcrequired="请输入使用时间"  dctype="数字" /></td>
    	<td>{{:use_hour_cost}} {{:unit}}<input id ="unit" type ="hidden" value="{{:unit}}"  name="workOutDetailMachInfo[{{:#index}}].unit" dc="" /></td>
		<td><input id ="work_hour2" value="0" class="toaddMach2" onchange="addMach2()" name="workOutDetailMachInfo[{{:#index}}].work_hour2" dc=""  dcrequired="请输入使用时间"  dctype="数字" /></td>
    	<td>{{:use_day_cost}} {{:unit2}}<input id ="unit2" type ="hidden" value="{{:unit2}}"  name="workOutDetailMachInfo[{{:#index}}].unit2" dc=""/></td>
 	</tr>
</script>
<script type="text/x-jsrender" id ="dataListMachTotalTemp">
	<tr>
    <td class="odd w10">机械费</td>
    <td class="odd w10" colspan="3"><input id ="mach_cost" value="0"  name="workOutInfo.mach_cost"  readonly="true" dc=""/></td>
    <td class="odd w10"><span>元</span></td>
    </tr>
</script>

<!-- 材料费补充-后添加 -->
<script type="text/x-jsrender" id="addMatTemplate">
<tr class="">
	<td><input type="checkbox" width="50px" name="checkboxMaterialCostId" /></td>
    <td><input class="laydate-icon w110" type="text" name="rapairMaterialCostDetail[{{:rowsCount}}].confirm_date" dc="" dcrequired="请输入完成日期" /></td>
	<td><input type="text" name="rapairMaterialCostDetail[{{:rowsCount}}].cost_nm" dc="" dcrequired="请输入费用种类" /></td>
	<td><input type="text" name="rapairMaterialCostDetail[{{:rowsCount}}].amount" dc="" dctype="金额" dcrequired="请输入金额"/></td>
	<td><input type="text" name="rapairMaterialCostDetail[{{:rowsCount}}].material_cost_detail" dc="" dcrequired="请输入事由"/></td>
	<td><input type="text" name="rapairMaterialCostDetail[{{:rowsCount}}].dept_nm" dc="" dcrequired="请输入部门"/></td>
	<td><input type="text" name="rapairMaterialCostDetail[{{:rowsCount}}].remark" dc=""/></td>
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
                		<div class="title mb18">出勤表新增
                		</div>
                		<table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                		 <tr id="search_tr">      
                		     <td><span class="f14" >出勤表名称</span>&nbsp;&nbsp;
                                 <input class="mr20 w210" type="text"  id="work_out_nm" placeholder="请输入出勤表名称" name="workOutInfo.work_out_nm" dc=""/>
                             </td>
                		 
                          	<td><span class="f14" >出勤月份：</span>&nbsp;&nbsp;
                                    <input class="laydate-icon w110" type="text" id="work_out_ym" name="workOutInfo.work_out_ym" onchange="changeWorkOutYm()" dc="" dcrequired="请选择开始时间" readonly/>
                            </td></tr>
                        </table>
                        <br>                        
                		<div class="title_l f13">固定人力费统计：</div>
                        <table  id="dataListFormT"  class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
	  						<thead>
		  						<tr>
		  						<th class="odd">姓名</th>
		                        <th class="odd">合计</th>
		                        <th class="odd">单位</th></tr></thead>
		                    <tbody id="dataListForm"  >
									
							</tbody>
							<tfoot id="outputList_Total">
								
							</tfoot>
  						</table>
  						 
                        <div class="title_l">餐费统计：</div>
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
                        <div class="title_l">机械费统计：</div>
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
                         <div class="title_l">维修材料费：<a href="#" id="delCarTempRows" class="btn1 f14 fb fr">删除行</a>
                         <a href="#" id="addCarTempRows" class="btn1 f14 fb fr">添加行</a></div> 	    
                        <table  id="MaterialCostList"  class="tt tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                         <thead><tr>
                         		<th class="odd w47"><input type="checkbox" id="checkboxMaterialCostId"/></th>
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
                        <div class="tex_c mt22 mb30"><a href="#" id="submitBtn" class="btn1 f14 fb mr10">保存</a><a href="#" id="closeBtn" class="btn1 f14 fb" >返回</a></div>
                        <!-- 右侧 结束-->
        		</div>
          </div>
    </div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/account/js/workout_add.js"></script>
</html>