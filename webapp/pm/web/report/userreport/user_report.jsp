<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>员工业绩统计</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>

<script type="text/x-jsrender" id="dataListTemplate">
        <tr name="dataList">
        <td class="w47">{{:#index+1}}</td>
		<td>{{:user_nm}}</td>
		<td class="w90">{{:typeNm}}</td>
		<td class="w90">{{:r_cnt}}</td>
		<td class="w90">{{:l_cnt}}</td>
		<td class="w90">{{:all_cnt}}</td>
        <td class="w47"><a href="javascript:goToUserReportDetailFun('{{:update_id}}','{{:type}}');" class="btn3">详情</a>  </td>   
        </tr>
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">报表信息</a>&nbsp;>&nbsp;员工业绩统计</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">员工业绩统计</div>
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                                <td>
                                                                                       员工姓名 <input id="user_nm" class="mr20" type="text"  name="userReportDto.user_nm" dc=""/>
                                </td>
                                <td>
                                	<span class="f14">时间</span>&nbsp;&nbsp;
                                    <input class="laydate-icon w110" type="text" name="userReportDto.start_date" id="start" dc=""/>&nbsp;~&nbsp;<input  class="laydate-icon mr20 w110" name="userReportDto.end_date" id="end" dc="" type="text"    />
                                </td>
                                <td><a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                            </tr>
                          </table>
                          <!--Echart-->
                          <div id="main" style="height:500px;border:1px solid #ccc;padding:10px;"></div> 
                           <div class="t_r table_wrap mt18">  
                          		<table    class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                                 <tr> 
                                                   <th class="w47">序号</th>
                                                   <th>员工姓名</th>
                                                   <th class="w90">计划类型</th>
                                                   <th class="w90">按时次数</th>
                                                   <th class="w90">逾期次数</th>
                                                   <th class="w90">计划执行次数</th>
                                                   <th class="w47" id="last">详情</th>
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
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/report/userreport/js/user_report.js"></script>
</html>