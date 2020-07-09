<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>保养实绩</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/preplaninfo/js/preplan_history_list.js"></script>
</head>
<script type="text/javascript">
 var auth = '<%=request.getSession().getAttribute("sys_hidden_auth")%>';
</script>
<script type="text/x-jsrender" id="departTypeListTemplate">
       <select id='machineSpecies' name="prePlanInfo.machine_species_id" class="mr20"  dc="true" >
		{{renderOptionFun departTypeList "code_value" "code_nm" "" "设备类别" /}}
	 </select>
</script>
<script type="text/x-jsrender" id="cycleListTemplate">
       <select id='cycle' name="prePlanInfo.pre_plan_cycle" class="mr20"  dc="true" >
		{{renderOptionFun cycleList "code_value" "code_nm" "" "周期" /}}
	 </select>
</script>
<script type="text/x-jsrender" id="orderListTemplate">
       <select id='orderType' name="prePlanInfo.orderType" class="mr20 w120"  dc="true" >
		{{renderOptionFun orderList "code_value" "code_nm" orderList[0].code_value "noflag" /}}
	 </select>
</script>
<script type="text/x-jsrender" id="dataListTemplate">
		{{if pre_plan_state=='01'}}
			<tr style="background-color: #e70935; color:#fff;">
			<td class="w47"></td> 	
		{{/if}}
		{{if pre_plan_state=='02'}}
			<tr style="background-color: #ff8608; color:#fff;">
			<td class="w47">
			<input type="checkbox" name="checkGroup" datid="{{:pre_plan_id}}"/>
			</td> 	
		{{/if}}
		{{if pre_plan_state=='03'}}
        	<tr>
			<td class="w47"></td> 	
		{{/if}}
		{{if pre_plan_state=="null"}}
        	<tr>
			<td class="w47"></td> 	
		{{/if}}
        <td class="w47">{{:#index+1}}</td>
        <td>{{:pre_plan_nm}} </td>
 		<td class="w60">{{:machine_species_name}}</td>
        <td class="w60">{{:pre_plan_cycle_nm}}</td>
        <td class="w150">{{:pre_plan_date}}</td>
		<td class="w150">{{:operate_date}}</td>
		<td class="w60">{{:update_name}}</td>
		<td class="w100p">{{:pre_plan_state_nm}}</td>
        <td class="w47">
		{{if pre_plan_state == '02'}}
		<a href="javascript:updateConfirm('{{:pre_plan_id}}');" id="confirm{{:#getIndex()}}" class="btn3">确认</a>  
		{{/if}}
		{{if pre_plan_state == '01'}}
		<a href="javascript:deleteDataFun('{{:pre_plan_id}}');" id="delete{{:#getIndex()}}" class="btn3">删除</a>  
		{{/if}}
		</td>   
		<td width="47px"><a href="javascript:detailEntityDataFun('{{:pre_plan_id}}');" class="btn3"> 详情</a></td>
        </tr>
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">保养巡查</a>&nbsp;>&nbsp;保养实绩</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">保养实绩</div><!--1019-->
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr >
                          		<td>
                                	<span class="f14" >计划名称</span>&nbsp;&nbsp;
                                    <input class="mr20 w110" type="text" placeholder="请输入计划名称" name="prePlanInfo.pre_plan_nm" id="planNm" dc=""/>
                                </td>
                            	<td id="departTypeForm">
                                </td>
                                <td id="cycleListForm">
                                </td>
                                <td>
                                	<span class="f14">计划时间</span>&nbsp;&nbsp;
                                    <input class="laydate-icon  w110" type="text" name="prePlanInfo.start_date" id="start" dc=""/>&nbsp;~&nbsp;<input  class="laydate-icon mr20  w110" name="prePlanInfo.end_date" id="end" dc="" type="text"    />
                                </td>
                                <td id="orderListForm">
                                </td>
                                <td><a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a>
                                <a href="#" id="updateStateBt" class="btn2">批量确认</a></td>
                            </tr>
                          </table>
                           <div class="t_r table_wrap mt18">  
                          		<table  class="t_r_title tt" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;" > 
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
                                                   <th class="w150">实际时间</th>
                                                    <th class="w60">操作人</th>
                                                   <th class="w100p">状态</th>
                                                   <th class="w47">操作</th>
                                                   <th id="last" class="w47">详情</th>
                                                 </tr> 
                                             </thead> 	           
                                </table>
                                <div class="t_r_content"> 
                                	<table  class="tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
                                    	<tbody id="t_r_content" onscroll="aa()">
                                    	</tbody>
                                      </table> 
                                </div>
                       	  </div>
                       <!--  分页  开始-->
                          
                        <div class="paging" id="pagefoot"></div>
                          <!--  分页  结束-->
        
                        <!-- 右侧 结束-->
        		</div>
          </div>
    </div>
</body>
</html>