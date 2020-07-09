<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>保养计划新增</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
</head>
<script type="text/x-jsrender" id="formTemplate">
    <select id='planCycle' name="prePlanInfo.pre_plan_cycle" dcrequired="请输入周期" class="select1"  dc="true" >
		{{renderOptionFun planCycleList "code_value" "code_nm" "" "周期" /}}
	 </select>
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
	  <div class="contents">
          <div class="main_wrap" id="formId">
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">保养巡查</a>&nbsp;>&nbsp;保养计划新增</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">保养计划基本信息</div>
  						 <!--pop flag start -->
                                <input id="ma_sp_id" type="hidden" value=""/>
                                <input id="ma_type_id" type="hidden" value=""/>
                                <input id="ch_sp_id" type="hidden" value=""/>
                        		<input id="ch_type_id" type="hidden" value=""/> 
                        <!--pop flag end-->
            			<table class="tt tt2 record_table table_wrap mt18 mb30" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                            	<td class="odd w10">计划名称</td>
                                <td><input type="text" placeholder="请填写计划名称" dcrequired="请输入计划名称" name="prePlanInfo.pre_plan_nm" onclick="value='';focus()" dc="" maxlength="50"/></td>
                                <td class="odd w10">周期</td>
                                <td id="planForm" >
                                </td>
                                <td class="odd w10">计划日期</td>
                                <td><input id="date" dcrequired="请选择计划时间"  name="prePlanInfo.pre_plan_date"  class="laydate-icon" dc="" type="text" /></td>
                                <td class="odd w10">结束日期</td>
                                <td><input id="enddate" dcrequired="请选择计划结束时间"  name="prePlanInfo.pre_plan_date_end"  class="laydate-icon" dc="" type="text" /></td>
                            </tr>
                        </table>
                        <ul class="fl w30 new_info"  id="machineform">
                        	<li class="h_bg">设备信息<a class="fb f20 fr" href="#" title="新增" id="machineAddBt">+</a></li>
                        </ul>
                        <ul class="fr w67 new_info" id="checkitemform">
                        	<li class="h_bg">检查项目信息<a class="fb f20 fr" href="#" title="新增" id="checkitemAddBt">+</a></li>
                        </ul>
                        <div class="cb"></div>
                        <div class="tex_c mt22 mb30"><a href="#" id="submitBtn" class="btn1 f14 fb mr10">提交</a><a href="#" class="btn1 f14 fb" id="closeBtn">返回</a></div>
                        <!-- 右侧 结束-->
    
        		</div>
          </div>
    </div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/preplaninfo/js/preplan_add.js"></script>
</html>