<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>巡检计划</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/inspectplan/js/inspectplans_list.js"></script>
</head>
<!-- 设备类别 -->
<script type="text/x-jsrender" id="machinespeciesListTemplate">
	<select  name="insPlanInfo.machine_species_id" id="species_id" dc="true" class="mr20">
		{{renderOptionFun machinespeciesList "code_value" "code_nm" "" "设备类别"/}}
 	</select>
</script>
<!-- 周期-->
<script type="text/x-jsrender" id="cycleListTemplate">
       <select id='codeKind' name="insPlanInfo.ins_plan_cycle" class="mr20"  dc="true" >
		{{renderOptionFun cycleList "code_value" "code_nm" "" "周期" /}}
	 </select>
</script>
<!-- 巡检计划list -->
<script type="text/x-jsrender" id="inspectplansListTemplate">  
<tbody id="t_r_content" onscroll="aa()">                              
<tr name="dataList"> 
		
        
			<td class="w47">
		{{if create_id == currentUserid}}
			<input type="checkbox" name="checkGroup" datid="{{:ins_plan_id}}"/> 
		{{/if}}
			</td>
	  
        <td class="w47">{{:#index+1}}</td> 
        <td>{{:ins_plan_nm}}</td> 
		<td  class="w150">{{:machine_species_name}}</td> 
        <td class="w60">{{:ins_plan_cycle_name}}</td> 
        <td class="w150">{{:ins_plan_date}}</td>
		<td class="w60">{{:create_name}}</td>  

        <td class="w47">
         {{if create_id == currentUserid}}
            <a href="javascript:gotoUpdatepage('{{:ins_plan_id}}')" class="btn3">编辑</a>
         {{/if}}
       </td> 
        <td class="w47">
        {{if create_id == currentUserid}}
    		<a href="javascript:deleteInsPlan('{{:ins_plan_id}}')" class="btn3">删除</a>
	    {{/if}}
	    </td> 

</tr> 
</tbody> 
</script>
<!-- 巡检计划list groupby -->
<script type="text/x-jsrender" id="inspectplansListTemplate1">  
<tbody id="t_r_content" onscroll="aa()">                              
<tr name="dataList"> 
        <td class="w47">{{:#index+1}}</td> 
        <td>{{:ins_plan_nm}}</td> 
		<td  class="w150">{{:machine_species_name}}</td> 
        <td class="w60">{{:ins_plan_cycle_name}}</td> 
</tr> 
</tbody> 
</script>
<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">保养巡查</a>&nbsp;>&nbsp;巡检计划</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">                
                		<div class="title mb18">巡检计划<a href="#" id="gotoAddpageBt" class="btn1 f14 fb fr">新增</a></div>
  						<!--查询条件-->
  						<div class="w100" id="search_area">
  							<span class="f14">计划名称&nbsp;&nbsp;</span>
                            <input class="mr20 w110" placeholder="请输入计划名称" name="insPlanInfo.ins_plan_nm" type="text" value="" dc="true"/>
  							
  							<span id="machinespecies"></span>
  							
  							<span id="cycles"></span>
  							
  							<span class="f14">计划日期&nbsp;&nbsp;</span>
                            <input id="start" name="insPlanInfo.date_start"   class="laydate-icon w110" type="text" value="" dc="true" />&nbsp;~&nbsp;
                            <input id="end" name="insPlanInfo.date_end"  class="laydate-icon mr20 w110" type="text"  value="" dc="true"/>
                            
                            <select class="mr20 w120" name="insPlanInfo.orderType" dc="true">
                            	<option value="ins_plan_date asc">计划时间升序</option>
                                <option value="ins_plan_date desc">计划时间降序</option>
                                <option value="ins_plan_group,T.ins_plan_date asc">计划组</option>
                            </select>
                            
                            <select class="mr20 w130" id="groupby">
                            	<option value="0">计划查询</option>
                                <option value="1">计划组类别查询</option>
                            </select>
                            
                            <a href="#" id="searchBt" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a>
  							<a href="#" id="updateStateBt" class="btn2">批量删除</a>
  						</div>                          
 						<!--表格-->
                         <div class="t_r table_wrap mt18">  
                		<table id="table0" class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;"> 
                         <thead>
                            <tr> 
                            <th class="w47">
                           	<input type="checkbox" id="checkGroup"/>
                           	</th>
                             <th class="w47">序号</th>
                             <th>计划名称</th>
                              <th class="w150">设备类别</th>
                             <th class="w60">计划周期</th>
                             <th class="w150">计划日期</th>
                             <th class="w60">创建人</th>  
                             <th class="w47">编辑</th>
                             <th class="w47" id="last">删除</th>
                            </tr> 
                            </thead>
                          </table> 
                          <table id="table1" style="display:none" class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;"> 
                         <thead>
                            <tr> 
                             <th class="w47">序号</th>
                             <th>计划名称</th>
                             <th class="w150">设备类别</th>
                             <th class="w60" id="last1">计划周期</th>
                            </tr> 
                            </thead>
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
