<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>员工业绩统计详情</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>

<script type="text/x-jsrender" id="dataListTemplate">
        <tr name="dataList">
        <td class="w47">{{:#index+1}}</td>
		<td>{{:plan_nm}}</td>
		<td class="w90">{{:plan_date}}</td>
		<td class="w90">{{:operate_time}}</td>
        </tr>
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">报表信息</a>&nbsp;>&nbsp;员工业绩统计详情</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">员工业绩统计详情</div>
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                                <td>
                                	<span class="f14">时间</span>&nbsp;&nbsp;
                                    <input class="w110" type="text" name="userReportDto.start_date" id="start" dc="" disabled />&nbsp;~&nbsp;<input  class="mr20 w110" name="userReportDto.end_date" id="end" dc="" type="text"  disabled/>
                                </td>
                                <td><a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                            </tr>
                          </table>
                           <div class="t_r table_wrap mt18">  
                          		<table    class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                                 <tr> 
                                                   <th class="w47">序号</th>
                                                   <th>计划名称</th>
                                                   <th class="w90">计划时间</th>
                                                   <th class="w90" id="last">实绩时间</th>
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
                  
                       <div class="tex_c mt22 mb30"><a href="#" class="btn1 f14 fb" id="closeBtn">返回</a></div>
        
                        <!-- 右侧 结束-->
        		</div>
          </div>
    </div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/report/userreport/js/user_report_detail.js"></script>
</html>