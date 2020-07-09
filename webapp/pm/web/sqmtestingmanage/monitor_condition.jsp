<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>监测情况</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>
<style>
table.t_r_title tr:HOVER{
background: #B0E2FF;
}

</style>

<script type="text/x-jsrender" id="dataListTemplate">
    <tr name="dataList">
        <td width="47px">{{:#index+1}}</td>
        <td width="100px">{{:cnfymd}}</td>
        <td width="100px">{{:vsl}}</td>
        <td width="60px">{{:turn}}</td>
        <td width="80px">{{:prod}}</td>
        <td width="120px">{{:prodname}}</td>
		<td width="80px">{{:lineid}}</td>
        <td width="80px">{{:linename}}</td>
 		<td width="60px">{{:fill_STATE==0?'未进行':(fill_STATE==1?'进行中':(fill_STATE==2?'异常':(fill_STATE==3?'已完成':'故障')))}}</td>
        <td width="80px">{{:blndqty}}</td>
        <td width="80px">{{:lotno}}</td>
        <td width="70px">{{:pkg}}</td>
        <td width="70px">{{:htank}}</td>
        <td width="70px">{{:moveqty}}</td>
        <td width="70px">{{:marginqty}}</td>
        <td width="70px">{{:mgubunnm}}</td>
        <td width="70px">{{:density}}</td>
        <td width="60px">{{:labelstate}}</td>
        <td width="60px">{{:chrgqty}}</td>
        <td width="60px">{{:capa}}</td>
        <td width="100px" title="{{:fill_STDATE}}">{{:fill_STDATE}}</td>
		<td width="100px" title="{{:fill_EDDATE}}">{{:fill_EDDATE}}</td>

   </tr>
</script>


<script type="text/x-jsrender" id="dataTestListTemplate">
    <tr name="dataList">
        <td width="47px">{{:#index+1}}</td>
        <td width="100px">{{:create_time}} </td>
        <td width="100px">{{:equipmentName}} </td>
        <td width="60px">{{:test_num}} </td>
        <td width="80px">{{:update_num}} </td>
        <td width="120px">{{:testItemName}} </td>
		<td width="80px">{{:testItemName=='设备状态'?(testResult=='y'?'正常':'异常'):(testResult=='y'?'是':(testResult=='n'?'否':testResult)))}}</td>
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
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">SQM检测结果</a>&nbsp;>&nbsp;当日计划进行情况</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">监测情况</div><!--1019-->
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                            	<td>
	                            	<a href="javascript:queryDataFun('');" class="btn1 f14 fb fr">全 部</a>&nbsp;&nbsp;&nbsp;&nbsp;
	                            </td>
                                <td>
                                	<a href="javascript:queryDataFun('2');" class="btn1 f14 fb fr">异 常</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                </td>
                                <td>
                                	<a href="javascript:queryDataFun('4');" class="btn1 f14 fb fr">故障</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                </td>
                               <td>
                                	<a href="javascript:queryDataFun('1');" class="btn1 f14 fb fr">进 行 中</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                </td>
                                <td>
                                	<a href="javascript:queryDataFun('0');" class="btn1 f14 fb fr">未 进 行</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                </td>
                                <td>
                                	<a href="javascript:queryDataFun('3');" class="btn1 f14 fb fr">已 完 成</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                </td>
                            </tr>
                          </table>
                            


 						   <div class="t_r table_wrap mt18" style="overflow-x:auto;">  
                           <div class="t_r mt18" style="width:auto;">  
                          		<table  id="tabfill"   class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                                 <tr> 
                                                    <th width="47px">序号</th>                                                
		                                            <th width="100px">日期</th>
													<th width="100px">VSL</th>
													<th width="60px">次数</th>
													<th width="80px">编号</th>
													<th width="120px">产品名称</th>
													<th width="80px">生产ID</th>
													<th width="80px">生产线名</th>
													<th width="60px">状态</th>	
													<th width="80px">调配量</th>
													<th width="80px">LOTNO</th>
													<th width="70px">PKG</th>
													<th width="70px">罐装Tank</th>
													<th width="70px">移送量</th>
													<th width="70px">余量</th>
													<th width="70px">移送区分</th>
													<th width="70px">密度</th>
																						
													<th width="60px">贴标签</th>
													<th width="60px">罐装数量</th>
													<th width="60px">罐装容量</th>										
													<th width="100px">实际开始时间</th>
													<th width="100px">实际结束时间</th>
                                                 </tr> 
                                             </thead> 	
                                        <tbody id="t_r_content" class="abc" onscroll="aa()">
                                    	</tbody>           
                                </table>
                               
                       	  </div>
                   </div> 

                        <div class="title mb18">设备检测信息（根据选择计划筛选设备信息）</div><!--1019-->
                        
                         <div class="t_r table_wrap mt18" style="overflow-x:auto;">  
                           <div class="t_r mt18" style="width:auto;">  
                          		<table  id="testListTable"  class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
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
                                             <tbody id="test_r_content" onscroll="aa()">
	                                         </tbody>           
                                </table>
                               
                       	  </div>
                   </div> 
                    <div class="paging" id="pagefoot"></div>
        		</div>
          </div>
    </div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmtestingmanage/js/monitor_condition.js"></script>
</html>