<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>设备停机维修报表详情</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/maintainlog/js/timeUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/maintainlog/js/potPartStopReportDetail.js"></script>
</head>
<!-- list -->
<script type="text/x-jsrender" id="meRepairTemplate">
<tbody id="t_r_content" onscroll="aa()">        
<tr> 
        <td class="w47">{{:#index+1}}</td> 
        <td>{{:repair_content}}</td> 
        <td class="w110">{{:repair_date}}</td> 
        <td class="w110">{{:repair_person}}</td> 
        <td class="w110">{{:pot_id_nm}}</td> 
		<td class="w110">{{:create_name}}</td> 
 		<td class="w110">{{:repairType}}</td> 
 		
</tr> 
</tbody>
</script>
<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">停机维修信息</a>&nbsp;>&nbsp;设备停机维修报表详情</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">                
                		<div class="title mb18">设备停机维修报表详情</div>
  						
        				<!--表格-->
                          <div class="t_r table_wrap mt18">  
	                			<table class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
		                         <thead>
		                            <tr> 
		                             <th class="w47">序号</th>                             
		                             <th>工作内容</th>
		                             <th class="w110">维修日期</th>
		                             <th class="w110">维修人员</th>
		                             <th class="w110">设备</th>
		                             <th class="w110">提交人</th>
		                             <th class="w110" >维修状态</th>
		                            </tr> 
		                            </thead>
		                          </table> 
		                          <div class="t_r_content"> 
		                      		<table id="meRepairList" class="tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
		                        	</table>    
		                          </div> 
                       	  </div>
                  
                        <!-- 右侧 结束-->
     					<div class="paging" id="pagefoot">                                            
                       </div>
                <div class="tex_c mt22 mb30"><a href="#" id="closeBtn" class="btn1 f14 fb">返回</a></div>
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
