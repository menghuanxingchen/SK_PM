<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开始评价</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>


<script type="text/x-jsrender" id="dataListTemplate">
    <tr name="dataList">
        <td width="47px">{{:#index+1}}</td>
        <td width="60px">{{:user_num}} </td>
 		<td width="60px">{{:user_nm}}</td>
		<td width="60px">{{:year_date}}</td>
 		<td width="60px">{{:date_annual}}</td>
 		<td width="80px">{{:test_goal_num}}</td>
	    <td width="80px">{{:modify_num}}</td>
	    <td width="80px">{{:error_rate}}</td>
  		<td width="80px">{{:average_score}}</td>
		<td width="60px">{{:evaluation_state_nm}}</td>
		<td width="80px">{{:evaluation_one}}</td>
		<td width="100px">{{:evaluation_one_name}}</td>
		<td width="80px">
		{{if evaluation_one == currentUserid && evaluation_state !='2'}}
			<input type="text" style="width:100%" name="evaluation_one_num" value="{{:evaluation_one_num}}"/> 
		{{/if}}
		{{if evaluation_one == currentUserid && evaluation_state =='2'}}
			{{:evaluation_one_num}}
		{{/if}}
		{{if evaluation_one != currentUserid}}
			{{:evaluation_one_num}}
		{{/if}}
		</td>
		<td width="200px">
		{{if evaluation_one == currentUserid && evaluation_state !='2'}}
			<input type="text" style="width:100%" name="evaluation_one_remark" value="{{:evaluation_one_remark}}"/> 
		{{/if}}
		{{if evaluation_one == currentUserid && evaluation_state =='2'}}
			{{:evaluation_one_remark}}
		{{/if}}
		{{if evaluation_one != currentUserid}}
			{{:evaluation_one_remark}}
		{{/if}}
		</td>
		<td width="80px">{{:evaluation_two}}</td>
		<td width="100px">{{:evaluation_two_name}}</td>
		<td width="80px">
		{{if evaluation_two == currentUserid && evaluation_state !='2'}}
			<input type="text" style="width:100%" name="evaluation_two_num" value="{{:evaluation_two_num}}"/> 
		{{/if}}
		{{if evaluation_two == currentUserid && evaluation_state =='2'}}
			{{:evaluation_two_num}}
		{{/if}}
		{{if evaluation_two != currentUserid }}
			{{:evaluation_two_num}}
		{{/if}}
	    </td>
		<td width="200px">
		{{if evaluation_two == currentUserid && evaluation_state !='2'}}
			<input type="text" style="width:100%" name="evaluation_two_remark" value="{{:evaluation_two_remark}}"/> 
		{{/if}}
		{{if evaluation_two == currentUserid && evaluation_state =='2'}}
			{{:evaluation_two_remark}}
		{{/if}}
		{{if evaluation_two != currentUserid}}
			{{:evaluation_two_remark}}
		{{/if}}

		</td>
   </tr>
</script>

<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">评价管理</a>&nbsp;>&nbsp;开始评价</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">开始评价
                		<a href="javascript:endEntityDataFun();" id="endEvaluation" class="btn1 f14 fb fr">结束评价</a>
                		</div><!--1019-->
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                          		
                            	<td>
	                            	<span class="f14">年份&nbsp;&nbsp;</span>
	                            	<input id="start" name="evaluationManageInfo.year_date"   class="laydate-icon w110" type="text" value="" dc="true" readonly/>&nbsp;~&nbsp;

                                </td>
                                <td>
                                 <select id="yearDate" name="evaluationManageInfo.date_annual" dc="true">
									  <option value ="全年">全年</option>
									  <option value ="上半年">上半年</option>
									  <option value="下半年">下半年</option>
									</select>&nbsp;&nbsp;
                                </td>
                               
                                <td><a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                                <td>&nbsp;&nbsp;<a href="javascript:saveEntityDataFun();" id="saveEvaluation" class="btn1 f14 fb fr">临时保存</a></td>
                            </tr>
                          </table>
                           <div class="t_r table_wrap mt18" style="overflow-x:auto;">  
                           <div class="t_r mt18" style="width:auto;">  
                          		<table    class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                                 <tr> 
                                                <th width="47px">序号</th>
                                                   <th width="60px">工号</th>
                                                   <th width="60px">姓名</th>
                                                   <th width="60px">年份</th>
                                                   <th width="60px">年度</th>
                                                   <th width="80px">检测目标数</th>
                                                   <th width="80px">修改次数</th>
                                                   <th width="80px">出错率</th>
                                                   <th width="80px">平均分</th>
                                                   <th width="60px">评价状态</th>
                                                   <th width="80px">评价人1工号</th>
                                                   <th width="100px">评价人1姓名</th>
                                                   <th width="80px">评价分数</th>
                                                   <th width="200px">评价说明</th>
                                                   <th width="80px">评价人2工号</th>
                                                   <th width="100px">评价人2姓名</th>
                                                   <th width="80px">评价分数</th>
                                                   <th width="200px">评价说明</th>
                                                 
                                                 </tr> 
                                             </thead> 	           
                                </table>
                                <div class="t_r_content" > 
                                	<table  class="tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
                                    	<tbody id="t_r_content" onscroll="aa()">
                                    	</tbody>
                                      </table> 
                                </div> 
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
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/evaluationManage/js/evaluation_save.js"></script>
</html>