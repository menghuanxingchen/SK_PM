<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>巡检实绩</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/inspectplan/js/inspectplans_actual_list.js"></script>
</head>
<script type="text/javascript">
 var auth = '<%=request.getSession().getAttribute("sys_hidden_auth")%>';
</script>
<!-- 设备类别 -->
<script type="text/x-jsrender" id="machinespeciesListTemplate">
	<select  name="insPlanInfo.machine_species_id" id="species_id" dc="true" class="mr20">
		{{renderOptionFun machinespeciesList "code_value" "code_nm" "" "设备类别"/}}
 	</select>
</script>
<!-- 周期-->
<script type="text/x-jsrender" id="cycleListTemplate">
       <select id='cycle' name="insPlanInfo.ins_plan_cycle" class="mr20"  dc="true" >
		{{renderOptionFun cycleList "code_value" "code_nm" "" "周期" /}}
	 </select>
</script>
<script type="text/x-jsrender" id="orderListTemplate">
       <select id='orderType' name="insPlanInfo.orderType" class="mr20 w120"  dc="true" >
		{{renderOptionFun orderList "code_value" "code_nm" orderList[0].code_value "noflag" /}}
	 </select>
</script>
<!-- 巡检计划list -->
<script type="text/x-jsrender" id="inspectplansListTemplate">
<tbody id="t_r_content" onscroll="aa()">   
      {{if ins_plan_state=='01'}}
			<tr style="background-color: #e70935;color:#fff;">
		{{/if}}
		{{if ins_plan_state=='02'}}
			<tr style="background-color: #ff8608;color:#fff;">
		{{/if}}
		{{if ins_plan_state=='03'}}
        	<tr>
		{{/if}}
		<td class="w47">
				<input type="checkbox" name="checkGroup" datid="{{:ins_plan_id}}"/>
		</td> 
        <td class="w47">{{:#index+1}}</td> 
        <td>{{:ins_plan_nm}}</td>
		<td class="w60">{{:machine_species_name}}</td> 
        <td class="w60">{{:ins_plan_cycle_name}}</td> 
        <td class="w150">{{:ins_plan_date}}</td> 
        <td class="w47">{{:weekday}}</td> 
 		<td class="w150">{{:operate_time}}</td> 
		<td class="w60">{{:update_name}}</td> 
		<td class="w100p">{{:ins_plan_state_name}}</td> 		
        <td class="w47">
		<!-- {{if ins_plan_state == '02'}}
		<a href="javascript:updatePlanState('{{:ins_plan_id}}')"   class="btn3">确认</a>
		{{/if}}</td> -->
		{{if ins_plan_state == '02'}}
		<a href="javascript:updatePlanState('{{:ins_plan_id}}');" id="confirm{{:#getIndex()}}" class="btn3">确认</a>  
		{{/if}}
		{{if ins_plan_state == '01'}}
		<a href="javascript:deleteInsPlan('{{:ins_plan_id}}');" id="delete{{:#getIndex()}}" class="btn3">删除</a>  
		{{/if}}
        <td class="w47"><a href="javascript:gotoCheckDetailList('{{:ins_plan_id}}','{{:ins_plan_nm}}')"  class="btn3">详情</a></td> 
        </tr> 
</tbody>
</script>
<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">保养巡查</a>&nbsp;>&nbsp;巡检实绩</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">                
                		<div class="title mb18">巡检实绩</div>
  						<!--查询条件-->
                          <table id="search_area" class="w_auto" border="0" cellspacing="0" cellpadding="0">
                          	<tr>
                          		<td>
                                	<span class="f14">计划名称</span>
                                    <input class="mr20 w110" placeholder="请输入计划名称" name="insPlanInfo.ins_plan_nm" id="planNm" type="text" value="" dc="true"/>
                                </td>
                            	<td id="machinespecies">
                                </td>
                                 <td id="cycles">
                                </td>
                                 <td>
                                	<span class="f14">计划时间</span>
                                    <input id="start" name="insPlanInfo.date_start"   class="laydate-icon w110" type="text" value="" dc="true" />&nbsp;~&nbsp;
                                    <input id="end" name="insPlanInfo.date_end"  class="laydate-icon mr20 w110" type="text"  value="" dc="true"/>
                                </td>
                                
                                <td id="orderListForm">
                                </td>
                                <td><a id="searchBt" href="#" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a>
                                  <a href="#" id="updateStateBt" class="btn2">批量确认</a>
                                   <a href="#" id="deleteBatchBt" class="btn2">批量删除</a></td>
                            </tr>
                          </table>
 						<!--表格-->
                          <div class="t_r table_wrap mt18">  
                		<table class="t_r_title tt" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
                         <thead>
                            <tr> 
                            	<th class="w47">
                             	<input type="checkbox" id="checkGroup"/>
                             	</th>
                              <th class="w47">序号</th>
                              <th>计划名称</th>
                               <th class="w60">设备类别</th>
                              <th class="w60">计划周期</th>
                              <th class="w150">计划时间</th>
                              <th class="w47">星期</th>
                              <th class="w150">实际时间</th>
                               <th class="w60">巡检人</th> 
                              <th class="w100p">状态</th>                             
                              <th class="w47">确认</th>
                              <th id="last" class="w47">操作</th>
                            </tr> 
                           </table>
                            <div class="t_r_content" > 
                      		<table id="inspectplanList" class="tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
                        	</table>   
                            </div> 
                       	  </div>
                  
                       <!--  分页  开始-->                          
                       <div class="paging" id="pagefoot">                                            
                       </div>
                       <!--  分页  结束-->         
        
                        <!-- 右侧 结束-->
    
        		</div>
          </div>
    </div>


</body>
</html>
