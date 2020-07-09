<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>评价生成</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>
<script type="text/x-jsrender" id="evaluationOneTemplate">
       &nbsp;&nbsp;<select id="EvaluationOne" name="evaluationManageInfo.evaluation_one" class="mr20"  dc="true" >
		{{renderOptionFun EvaluationList "user_num" "user_nm" "" "评价人1" /}}
	 </select>
</script>

<script type="text/x-jsrender" id="evaluationTwoTemplate">
       <select id="EvaluationTwo" name="evaluationManageInfo.evaluation_two" class="mr20"  dc="true" >
		{{renderOptionFun EvaluationList "user_num" "user_nm" "" "评价人2" /}}
	 </select>
</script>

<script type="text/x-jsrender" id="dataListTemplate">
    <tr name="dataList">
        <td width="47px">{{:#index+1}}</td>
        <td width="100px">{{:user_num}} </td>
 		<td width="100px">{{:user_nm}}</td>
		<td width="100px">{{:year_date}}</td>
 		<td width="100px">{{:date_annual}}</td>
 		<td width="100px">{{:test_goal_num}}</td>
	    <td width="100px">{{:modify_num}}</td>
	    <td width="100px">{{:error_rate}}</td>
  		<td width="100px">{{:average_score}}</td>
		<td width="100px">{{:evaluation_one}}</td>
		<td width="100px">{{:evaluation_one_name}}</td>
		<td width="100px">{{:evaluation_two}}</td>
		<td width="100px">{{:evaluation_two_name}}</td>
   </tr>
</script>

<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">评价管理</a>&nbsp;>&nbsp;评价生成</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">评价生成
                		<a href="javascript:addEntityDataFun();" id="evaluationLink" class="btn1 f14 fb fr">生成</a>
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
                               <td id="evaluationOneForm">
                               
                               </td>
                               <td id="evaluationTwoForm">
                               </td>
                                <td><a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                            </tr>
                          </table>
                           <div class="t_r table_wrap mt18">  
                          		<table    class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                                 <tr> 
                                                <th width="47px">序号</th>
                                                   <th width="100px">工号</th>
                                                   <th width="100px">姓名</th>
                                                   <th width="100px">年份</th>
                                                   <th width="100px">年度</th>
                                                   <th width="100px">检测目标数</th>
                                                   <th width="100px">修改次数</th>
                                                   <th width="100px">出错率</th>
                                                   <th width="100px">平均分</th>
                                                   <th width="100px">评价人1工号</th>
                                                   <th width="100px">评价人1姓名</th>
                                                   <th width="100px">评价人2工号</th>
                                                   <th width="100px">评价人2姓名</th>
                                                 
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
                  
                       <!--  分页  开始-->
                          
                        <div class="paging" id="pagefoot"></div>
                          <!--  分页  结束-->
        
                        <!-- 右侧 结束-->
        		</div>
          </div>
    </div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/evaluationManage/js/evaluation_manage_list.js"></script>
</html>