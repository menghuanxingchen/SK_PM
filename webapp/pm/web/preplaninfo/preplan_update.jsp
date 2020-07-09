<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>保养计划修改</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
</head>
<script type="text/x-jsrender" id="cycleTemplate">
    <select id='planCycle' name="prePlanInfo.pre_plan_cycle" dcrequired="请输入周期" class="select1"  value="" dc="true" disabled>
				{{renderOptionFun planCycleList "code_value" "code_nm" prePlanInfoEo.pre_plan_cycle "周期" /}}
	</select>
</script>
<script type="text/x-jsrender" id="updateflagTemplate">
    <select id='updageflagid' name="prePlanInfo.update_flag" class="select1"  dc="true" >
		{{renderOptionFun updateflagList "code_value" "code_nm" updateflagList[1].code_value "noflag"/}}
	 </select>
</script>
<script type="text/x-jsrender" id="formTemplate">
    <tr>
      <td class="odd">计划名称</td><input type="hidden" name="prePlanInfo.pre_plan_id"  value="{{:prePlanInfoEo.pre_plan_id}}" dc=""/>
								<input type="hidden" name="prePlanInfo.pre_plan_group"  value="{{:prePlanInfoEo.pre_plan_group}}" dc=""/>
      <td><input type="text" dcrequired="请输入计划名称" name="prePlanInfo.pre_plan_nm"  value="{{:prePlanInfoEo.pre_plan_nm}}" dc="" maxlength="50"/></td>
      <td class="odd">周期</td>
      <td id="planForm" >
      </td>
      <td class="odd">计划日期</td>
      <td><input id="date"  dcrequired="请选择计划时间" name="prePlanInfo.pre_plan_date" value="{{:prePlanInfoEo.pre_plan_date}}"  class="laydate-icon" dc="" type="text" /></td>
	<td class="odd">结束日期</td>
      <td><input id="enddate"  dcrequired="请选择计划结束时间" name="prePlanInfo.pre_plan_date_end" value="{{:prePlanInfoEo.pre_plan_date_end}}"  class="laydate-icon" dc="" type="text" /></td>
            
<td id="updateflag">		
	  </td>
    </tr>
</script>
<script type="text/x-jsrender" id="machineTemplate">
	<li class="li_on1" id="perMachineIdList{{:#index}}" onclick="removeMachineId({{:#index}});"><input type="hidden" name="perMachineIdList[{{:#index}}]" idattr="" value="{{:pre_machine_id}}" dc="true"/>{{:machine_nm}}</li>
</script>
<script type="text/x-jsrender" id="checkitemTemplate">
 	<li class="li_on2" id="perCheckIdList{{:#index}}" onclick="removeCheckIdList({{:#index}});"><input type="hidden" name="perCheckIdList[{{:#index}}]" idattr="" value="{{:check_project_id}}" dc="true"/>{{:check_project_nm}}</li>
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
	  <div class="contents">
          <div class="main_wrap" id="formId">
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">保养巡查</a>&nbsp;>&nbsp;保养计划修改</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">保养计划基本信息</div>
  						
            			<table class="tt tt2 record_table table_wrap mt18 mb30" id="dataform" border="0" cellspacing="0" cellpadding="0">
                            
                        </table>
                         <!--pop flag start -->
                                <input id="ma_sp_id" type="hidden" value=""/>
                                <input id="ma_type_id" type="hidden" value=""/>
                                <input id="ch_sp_id" type="hidden" value=""/>
                        		<input id="ch_type_id" type="hidden" value=""/> 
                        <!--pop flag end-->
                        <ul class="fl w30 new_info" >
                        	 <li class="h_bg">设备信息<a class="fb f20 fr" href="#" title="新增" id="machineAddBt">+</a></li>
                        	 <ul class="new_info_list"  id="machineform">
		                        <li></li>
		                     </ul>
                        	 
                        </ul>
                        
                        <ul class="fr w67 new_info">
                        	 <li class="h_bg">检查项目信息<a class="fb f20 fr" href="#" title="新增" id="checkitemAddBt">+</a></li>
                        	 <ul class="new_info_list"  id="checkitemform">
		                        <li></li>
		                     </ul>
                        </ul>
                        <div class="cb"></div>
                        <div class="tex_c mt22 mb30"><a href="#" id="submitBtn" class="btn1 f14 fb mr10">提交</a><a href="#" class="btn1 f14 fb" id="closeBtn">返回</a></div>
                        <!-- 右侧 结束-->
    
        		</div>
          </div>
    </div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/preplaninfo/js/preplan_update.js"></script>
</html>