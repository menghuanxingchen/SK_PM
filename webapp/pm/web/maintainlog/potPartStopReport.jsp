<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>设备停机维修报表</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/maintainlog/js/timeUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/maintainlog/js/potPartStopReport.js"></script>
</head>
<!-- list -->
<script type="text/x-jsrender" id="machineStopCountTemplate">
 
	<tr>
	<td class="w47"><a href="javascript:godetail('{{:start_time}}','{{:pot_id}}')"  class="btn3">{{:start_time}}</a></td>
	 <td class="w80">{{:pot_nm}}</td>
     
     <td class="w80">{{:emergent_id}}</td>
	 <td class="w80">{{:hourNum}}</td>
     </tr> 

</script>
<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">停机维修信息</a>&nbsp;>&nbsp;设备停机维修报表</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">                
                		<div class="title mb18">设备停机维修报表<a href="javascript:downloadExecl('machineStopCount','停机维修报表');" id="reportExport_btn" class="btn1 f14 fb fr mr20 ml10">导出</a></div>
  						<!--查询条件-->
                          <table id="search_area" class="w_auto" border="0" cellspacing="0" cellpadding="0">
                          	<tr>
                                <td>
                                   <span class="f14">日期&nbsp;&nbsp;</span>
                                   <input id="st_date" name="maintResStatisDTO.st_date"   class="laydate-icon w110" type="text" value="" dc="true" readonly/>&nbsp;&nbsp;
                                </td>
                                <td>
                                	<span class="f14">设备</span>&nbsp;&nbsp;
                                    
                                </td>
                                 <td >
	                                <input  type="text" id="selPotNm" value="" class="mr20" onclick="selectPot()" readonly/>
	                            	<a id="clearPotA" style="visibility: hidden;" href="javascript:clearPot()"><img alt="清空" title="清空" src="<%=request.getContextPath()%>/pm/web/basicinfo/machinemanager/img/delete.png"></a>
	                                <input type="hidden" id="selPotId" name="maintResStatisDTO.selPotId" value="" class="mr20" dc="true"/>
                                </td>
                                <td><a href="#" id="searchBt" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                                
                            </tr>
                          </table>
        				<!--表格-->
                          <div class="t_r table_wrap mt18">  
	                			<table id="machineStopCount" class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
		                         	<thead>
		                            	<tr> 
		                            		<th class="w47">日期</th>
		                             		<th class="w80">设备</th>
		                              		
		                              		<th class="w80">次数</th>
		                              		<th class="w80">小时数</th>
		                            	</tr> 
		                            </thead>
		                              <tbody id="t_r_content" onscroll="aa()">
	                                  </tbody>
	                          	</table> 
                         
                       	  </div>
                  
                        <!-- 右侧 结束-->
     					<div class="paging" id="pagefoot">                                            
                       </div>
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
