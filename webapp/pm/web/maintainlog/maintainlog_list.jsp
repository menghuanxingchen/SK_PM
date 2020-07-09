<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>维修日志信息</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript">
var deptval = '<%=request.getSession().getAttribute("sys_hidden_dept")%>';
var subdeptval ='<%=request.getSession().getAttribute("sys_hidden_subdept")%>';
var auth = '<%=request.getSession().getAttribute("sys_hidden_auth")%>';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/maintainlog/js/maintainlog_list.js"></script>
</head>
<!-- 维修类型 -->
<script type="text/x-jsrender" id="mltypeListTemplate">
	<select  name="maintainlogInfo.maintain_type" dc="true" class="mr20">
		{{renderOptionFun mltypeList "code_value" "code_nm" "" "维修类型"/}}
 	</select>
</script>
<!-- 灌装机-->
<script type="text/x-jsrender" id="maListTemplate">
	<select  name="maintainlogInfo.machineid" dc="true" class="mr20">
		{{renderOptionFun machineList "machine_id" "machine_nm" "" "灌装机"/}}
 	</select>
</script>
<!-- 状态 -->
<script type="text/x-jsrender" id="mlStateListTemplate">
	<select name="maintainlogInfo.check_state" dc="true" class="mr20">
		{{renderOptionFun mlstateList "code_value" "code_nm" "" "状态"/}}
 	</select>
</script>
<script type="text/x-jsrender" id="departListTemplate">
       <select id="dept"  name="maintainlogInfo.dept_type" class="mr20" dc="true"  >
		{{renderOptionFun departList "code_value" "code_nm" "" "部门" /}}
	 </select>
</script>
<script type="text/x-jsrender" id="subDepartListTemplate">
       <select id="subdept" name="maintainlogInfo.subdept_type" class="mr20"  dc="true" >
		{{renderOptionFun subDepartList "code_value" "code_nm" "" "职能部门" /}}
	 </select>
</script>
<!-- list -->
<script type="text/x-jsrender" id="infoListTemplate">
<tbody id="t_r_content" onscroll="aa()">   
<tr>
		<td class="w47">
		<input type="checkbox" name="checkGroup" datid="{{:maintainlog_id}}"/>
		</td> 
        <td class="w47">{{:#index+1}}</td> 
		<td class="w47">{{:subdept_type_nm}}</td> 
		<td class="w60">{{:maint_type__name}}</td>
		<td class="w60">{{:machinename}}</td>  
        <td>{{:log_contents}}</td> 
        <td class="w100p">{{:maintain_time}}</td> 
        <td class="w100p">{{:human_amount}}</td> 
 		<td class="w100p">{{:time_amount}}</td> 
		<td class="w100p">{{:log_remark}}</td> 
		<td class="w47">{{:create_name}}</td>      
		<td class="w47">
        {{if confirm_yn == 'Y'}}
				已确认
        {{/if}}
         {{if confirm_yn == 'N'}}
		<a href="javascript:updateConfirm('{{:maintainlog_id}}');" id="confirm{{:#getIndex()}}" class="btn3">确认</a> 
          {{/if}}
		</td>  
        <td class="w47"><a href="javascript:gotoUpdatePage('{{:maintainlog_id}}')"  class="btn3">编辑</a></td> 
        <td class="w47"><a href="javascript:deleteInfo('{{:maintainlog_id}}')" class="btn3">删除</a></td>
</tr> 
</tbody>
</script>
<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">维修信息</a>&nbsp;>&nbsp;维修日志信息</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">                
                		<div class="title mb18">维修日志信息<a href="#" id="gotoAddpageBt" class="btn1 f14 fb fr">新增</a></div>
  						<!--查询条件-->
                          <table id="search_area" class="w_auto" border="0" cellspacing="0" cellpadding="0">
                          	<tr>
                          		<td id="departTypeForm">
                                </td>
                                <td id="subDepartTypeForm">
                                </td>
                            	<td id="mtype">
                                </td>
                               <!-- <td id="mstate">
                                </td>  -->
                                <td>
                                   <span class="f14">维修日期&nbsp;&nbsp;</span>
                                    <input id="start" name="maintainlogInfo.date_start"   class="laydate-icon w180" type="text" value="" dc="true" readonly/>&nbsp;~&nbsp;
                                    <input id="end" name="maintainlogInfo.date_end"  class="laydate-icon mr20 w180" type="text"  value="" dc="true" readonly/>
                                </td>
                                <td id="machineList">
                                </td>
                                <td>
  							        <input class="mr20 w210"   placeholder="担当或维修人员人工或设备关键词"  type="text" name="maintainlogInfo.human_amount" dc=""/>
  							    </td>
                                <td><a href="#" id="searchBt" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a>
                                <a href="#" id="updateStateBt" class="btn2">批量确认</a></td>
                                
                            </tr>
                            
                          </table>
 						<!--表格-->
                         <div class="t_r table_wrap mt18">  
	                		<table class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
	                         <thead>
	                            <tr> 
	                            <th class="w47">
                             	<input type="checkbox" id="checkGroup"/>
                             	</th>
	                             <th class="w47">序号</th>
	                              <th class="w47">职能</th>
	                              <th class="w60">维修类型</th>
	                              <th class="w60">灌装机</th>  
	                              <th>工作内容</th>
	                              <th class="w100p">维修时间</th>
	                              <th class="w100p">担当或维修人员人工或设备关键词</th>
	                              <th class="w100p">维修人员工时或停机分钟数</th>
	                              <th class="w100p">备注(code+停机起始时间)</th>
	                              <th class="w47">创建人</th>
	                              <!-- <th class="w100p">状态</th> 
	                              <td class="w100p">{{:check_state_name}}</td> 
	                              <th class="w47">确认</th> 
	                              <td class="w47">
									{{if check_state == '02'}}
									<a href="javascript:updateLogState('{{:maintainlog_id}}')"   class="btn3">确认</a>
									{{/if}}</td>-->
								  <th class="w47">确认</th>
	                              <th class="w47">编辑</th>
	                              <th class="w47" id="last">删除</th>
	                            </tr> 
	                            </thead>
	                          </table> 
                          <div class="t_r_content"> 
                      		<table id="infoList" class="tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
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
<script type="text/javascript">
	var table_height=$("#table_height").height();
	if(table_height>="386")
	{
		$("#last").css({paddingRight:'25px'});
	}
</script>
</html>
