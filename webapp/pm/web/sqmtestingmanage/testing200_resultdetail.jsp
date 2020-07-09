<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>200L检测结果详情</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>


<script type="text/x-jsrender" id="dataListTemplate">
    <tr name="dataList">
         <td width="47px">{{:#index+1}}</td>
        <td width="100px">{{:create_time}} </td>
        <td width="100px">{{:equipmentName}} </td>
        <td width="60px">{{:test_num}} </td>
        <td width="80px">{{:update_num}} </td>
        <td width="120px">{{:testItemName}} </td>
		<td width="80px">{{:testResult}} </td>
        <td width="80px">{{:create_name}} </td>
        <td width="80px">{{:update_cause}} </td>
        <td width="80px">{{:update_time}} </td>
   </tr>
</script>

<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">SQM检测结果</a>&nbsp;>&nbsp;200L检测结果详情</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">200L检测结果详情<a href="javascript:history.go(-1)" class="btn1 f14 fb fr">返回</a>
                                
                                </div><!--1019-->
  						<!--查询条件-->
                         
                           <div class="t_r table_wrap mt18" style="overflow-x:auto;">  
                           <div class="t_r mt18" style="width:auto;">  
                          		<table  id="test4ListTable"  class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                                 <tr> 
                                                <th width="47px">序号</th>
                                                <th width="100px">日期时间</th>
												<th width="100px">设备</th>
												<th width="80px">检测次数</th>
												<th width="80px">修改次数</th>
												<th width="100px">检测项</th>
												<th width="80px">检测结果</th>
												<th width="100px">检测员</th>
												<th width="200px">修改理由</th>
												<th width="100px">修改时间</th>
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
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmtestingmanage/js/testing200_resultdetail.js"></script>
</html>