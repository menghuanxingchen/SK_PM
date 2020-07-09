<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>维修区域统计</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/maintainlog/js/maint_area_statistic_list.js"></script>
</head>
<!-- list -->
<script type="text/x-jsrender" id="infoListTemplate"> 
<tr>
        <td class="w47">{{:#index+1}}</td> 
        <td>{{:placename}}</td> 
        <td class="w47">{{:dept01}}</td> 
        <td class="w47">{{:dept02}}</td> 
		<td class="w47">{{:dept03}}</td>
		<td class="w47">{{:total_all}}</td>  
</tr> 
</script>
<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">报表信息</a>&nbsp;>&nbsp;维修区域统计</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">                
                		<div class="title mb18">维修区域统计</div>
  						<!--查询条件-->
                          <table id="search_area" class="w_auto" border="0" cellspacing="0" cellpadding="0">
                          	<tr>
                            	<td id="mtype">
                                </td>
                                <td id="mstate">
                                </td>
                                <td>
                                   <span class="f14">时间&nbsp;&nbsp;</span>
                                    <input id="start" name="maintAreaStatisDTO.st_date"   class="laydate-icon w110" type="text" value="" dc="true" readonly/>&nbsp;~&nbsp;
                                    <input id="end" name="maintAreaStatisDTO.en_date"  class="laydate-icon mr20 w110" type="text"  value="" dc="true" readonly/>
                                </td>
                                <td><a href="#" id="searchBt" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                                
                            </tr>
                          </table>
                          <!--Echart-->
                          <div id="main01" style="height:500px;border:1px solid #ccc;padding:10px;"></div> 
                          <div id="main02" style="height:500px;border:1px solid #ccc;padding:10px;"></div> 
                          <div id="main03" style="height:500px;border:1px solid #ccc;padding:10px;"></div> 
                          <div id="main04" style="height:500px;border:1px solid #ccc;padding:10px;"></div> 
 						<!--表格-->
                         <div class="t_r table_wrap mt18">  
	                		<table class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
	                         <thead>
	                            <tr> 
	                              <th class="w47">序号</th>
	                              <th>区域</th>
	                              <th class="w47">电仪</th>
	                              <th class="w47">机械</th>
	                              <th class="w47">安全</th>
	                              <th class="w47" id="last">金额</th>
	                            </tr> 	                           
	                            </thead>
	                          </table> 
                          <div class="t_r_content"> 
                      		<table id="infoList" class="tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
                        	 <tbody id="t_r_content" onscroll="aa()">  
	                         </tbody>
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
