<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>灌装日报</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>


<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">SQM查询</a>&nbsp;>&nbsp;灌装日报</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">灌装日报<a href="javascript:downloadExecl('tabListTable','灌装日报');" class="btn1 f14 fb fr">导出</a></div><!--1019-->
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                          		
                            	<td>
	                            	<span class="f14">生产日期&nbsp;&nbsp;</span>
	                            	<input id="start" name="ProductionPlan.startdate"   class="laydate-icon w110" type="text" value="" dc="true" readonly/>
									
                                </td>
                                <td>
                                
                                </td>
                               
                                <td><a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                                
                            </tr>
                          </table>
                           <div class="t_r table_wrap mt18" style="overflow-x:auto;">  
                           <div class="t_r mt18" style="width:auto;">  
                          		<table  id="tabListTable"  class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead >
                                        <tr id="thead_content">
                                        </tr>
                                    </thead> 	
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
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmquery/js/production_practical.js"></script>
</html>