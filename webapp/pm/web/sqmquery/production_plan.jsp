<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>生产计划</title>
	<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
	<%@ include file="/pm/common/jsp/commonJs.jsp"%>
	<script src="<%=request.getContextPath()%>/pm/common/js/jedate/jquery.jedate.js" type="text/javascript"></script>
	<link href="<%=request.getContextPath()%>/pm/common/js/jedate/skin/jedate.css" rel="stylesheet" type="text/css" />
</head>


<script type="text/x-jsrender" id="dataListTemplate">
    <tr name="dataList">
		<td >{{:#index+1}}</td>
		<td >{{:cnfymd}}</td>
		<td >{{:vsl}}</td>
		<td >{{:turn}}</td>
		<td >{{:prod}}</td>
		<td >{{:prodnm}}</td>
		<td >{{:blndqty}}</td>
		<td >{{:lotno}}</td>
		<td >{{:qty1}}</td>
		<td >{{:qty2}}</td>
		<td >{{:qty3}}</td>
		<td >{{:qty4}}</td>
		<td >{{:qty5}}</td>
		<td >{{:qty6}}</td>
		<td >{{:etc}}</td>
		<td >{{:qty7}}</td>
		<td >{{:htank}}</td>
		<td >{{:moveqty}}</td>
		<td >{{:marginqty}}</td>
		<td >{{:mgubunnm}}</td>
		<td >{{:result}}</td>
		<td >{{:notice}}</td>
		<td >{{:labelState}}</td>
		<td >{{:stdate}}</td>
		<td >{{:eddate}}</td>
		<td >{{:bigo}}</td>
   </tr>
</script>

<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">SQM查询</a>&nbsp;>&nbsp;生产计划</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">生产计划<a href="javascript:backWright();"  class="btn1 f14 fb" style="margin-left:83%" >回写</a>&nbsp;&nbsp;&nbsp;<a href="javascript:downloadExecl('tabListTable','生产计划');" class="btn1 f14 fb ">导出</a>&nbsp;&nbsp;</div><!--1019-->
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                          		
                            	<td>
	                            	<span class="f14">生产日期&nbsp;&nbsp;</span>
	                            	<input id="startdate" name="productionPlan.startdate"   class="dateinput dateicon" type="text" value="" dc="true" readonly/>&nbsp;~&nbsp;
									<input id="enddate" name="productionPlan.enddate"   class="dateinput dateicon" type="text" value="" dc="true" readonly/>
                                </td>
                                <td>
                                
                                </td>
                               
                                <td><a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                                
                            </tr>
                          </table>
                           <div class="t_r table_wrap mt18" style="overflow-x:auto;">  
                           <div class="t_r mt18" style="width:auto;">  
                          		<table  id="tabListTable"  class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                                 <tr> 
                                                <th width="47px">序号</th>
												<th width="100px">日期</th>
												<th width="80px">VSL</th>
												<th width="80px">次数</th>
												<th width="80px">编号</th>
												<th width="80px">产品名称</th>
												<th width="80px">调配量</th>
												<th width="100px">LOTNO</th>
												<th width="100px">DM</th>
												<th width="100px">18L</th>
												<th width="100px">4L</th>
												<th width="100px">1L</th>
												<th width="100px">0.9L</th>
												<th width="100px">1Ton</th>
												<th width="100px">ETC</th>
												<th width="100px">BULK</th>
												<th width="100px">灌装TK</th>
												<th width="100px">移送量</th>
												<th width="100px">余量</th>
												<th width="100px">移送区分</th>
												<th width="100px">密度</th>
												<th width="80px">mark</th>
												<th width="80px">贴标与否</th>
												<th width="150px">实际开始日期</th>
												<th width="150px">实际结束日期</th>
												<th width="200px">备注</th>
                                                 </tr> 
                                             </thead> 	
                                             <tbody id="t_r_content" onscroll="aa()">
	                                         </tbody>           
                                </table>
                               
                       	  </div>
                   </div> 
                      
        		</div>
          </div>
    </div>
</body>

<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmquery/js/production_plan.js"></script>
</html>