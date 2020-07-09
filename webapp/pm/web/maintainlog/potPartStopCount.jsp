<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>二级部件停机次数统计</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/maintainlog/js/timeUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/maintainlog/js/potPartStopCount.js"></script>
</head>
<!-- list -->
<script type="text/x-jsrender" id="machineStopCountTemplate">
	 <td class="w80">{{:#data}}</td>
</script>
<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">停机维修信息</a>&nbsp;>&nbsp;部件停机次数</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">                
                		<div class="title mb18">部件停机次数报表</div>
  						<!--查询条件-->
                          <table id="search_area" class="w_auto" border="0" cellspacing="0" cellpadding="0">
                          	<tr>
                                <td>
                                   <span class="f14">时间&nbsp;&nbsp;</span>
                                   <input id="selTime" name="selTime"   class="laydate-icon w110" type="text" value="" dc="true" readonly/>&nbsp;&nbsp;
                                </td>
                                <td>
                                	<span class="f14">设备</span>&nbsp;&nbsp;
                                    <input type="text" id="selPotNm" value="LB-V343罐" class="mr20" onclick="selectPot()" readonly/>
                                    <input type="hidden" id="selPotId" name="selPotid" value="06ef8d3ba7004b0c885ca5421e4e167b" class="mr20" dc="true"/>
                                </td>
                                <td><a href="#" id="searchBt" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                                
                            </tr>
                          </table>
                          <!--Echart-->
                          <div id="main" style="height:500px;border:1px solid #ccc;padding:10px;"></div> 
        				<!--表格-->
        				<div class="title mb18" id="machineTitle">设备（LB-V343罐）的停机次数报表</div>
                          <div class="t_r table_wrap mt18">  
	                			<table class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
	                         	<thead>
	                            	<tr> 
	                             		<th class="w47">月份</th>
	                              		<th class="w80">1</th>
	                              		<th class="w80">2</th>
	                              		<th class="w80">3</th>
	                              		<th class="w80">4</th>
	                              		<th class="w80">5</th>
	                              		<th class="w80">6</th>
	                              		<th class="w80">7</th>
	                              		<th class="w80">8</th>
	                              		<th class="w80">9</th>
	                              		<th class="w80">10</th>
	                              		<th class="w80">11</th>
	                              		<th class="w80">12</th>
	                            	</tr> 
	                            </thead>
	                          	</table> 
                          <div class="t_r_content"> 
                      		<table class="tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
                        		<tbody id="t_r_content" onscroll="aa()">
                        		<tr id="machineStopCount"> 
	                             	<!-- <th class="w47">次数</th> -->
	                            </tr>
	                            </tbody>
                        	</table>    
                          </div> 
                       	  </div>
                  
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
