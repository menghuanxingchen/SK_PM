<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>巡检计划新增</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/inspectplan/js/inspectplans_add.js"></script>
<script type="text/x-jsrender" id="cycleTemplate">
    <select id='planCycle' name="insPlanInfo.ins_plan_cycle" dcrequired="请选择周期" class="select1"  dc="true" >
		{{renderOptionFun cycleList "code_value" "code_nm" "" "周期" /}}
	 </select>
</script>
</head>
<body>
	 <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents" id="addform">
          <div class="main_wrap" >
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">巡检计划</a>&nbsp;>&nbsp;巡检计划新增</div>
            
            	<div class="main" id="aaa">
                
                		<div class="title mb18">巡检计划基本信息</div>
  						
            			<table class="tt tt2 record_table table_wrap mt18 mb30" border="0" cellspacing="0" cellpadding="0">
                        	<tr>                             	
                            	<td class="odd w10">计划名称</td>
                                <td><input type="text" name="insPlanInfo.ins_plan_nm"  onclick="value='';focus()" dc="true" dcrequired="请输入计划名称"/></td>
                            	<td class="odd w10">周期</td> 
                                <td id="cycleid">
                                </td>                           
                            	<td class="odd w10">计划日期</td>
                                <td><input id="date"  name="insPlanInfo.ins_plan_date"  class="laydate-icon" dc="" type="text" dcrequired="请选择计划时间" readonly/>
                                
                               <!--pop flag -->
                                <input id="ma_sp_id" type="hidden" value=""/>
                                <input id="ma_type_id" type="hidden" value=""/>
                                <input id="ch_sp_id" type="hidden" value=""/>
                        		<input id="ch_type_id" type="hidden" value=""/> 
                                </td>
                                <td class="odd w10">结束日期</td>
                                <td><input id="enddate"  name="insPlanInfo.Ins_plan_date_end"  class="laydate-icon" dc="" type="text" dcrequired="请选择计划结束时间" readonly/>
                                </td>
                            </tr>
                        </table>
                        <ul class="fl w30 new_info" id="machineform">
                        	<li class="h_bg">设备信息
                        	<a class="fb f20 fr" id="machineAddBt" href="#" title="新增">+</a></li>
                        </ul>
                         <ul class="fr w67 new_info" id="checkitemform">
                        	<li class="h_bg">检查项目信息                        	
                        	<a class="fb f20 fr" id="checkitemAddBt" href="#" title="新增">+</a></li>
                        </ul>
                         <div class="cb"></div>
                        <div class="tex_c mt22 mb30"><a href="#" id="saveBtn" class="btn1 f14 fb mr10">提交</a><a href="#" id="backBT" class="btn1 f14 fb">返回</a></div>
        		</div>
          </div>
    </div>


</body>
</html>
