<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>故障设备信息</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/maintainlog/js/maint_machine_statistic_list.js"></script>
</head>
<!-- list -->
<script type="text/x-jsrender" id="infoListTemplate">
<tbody id="t_r_content" onscroll="aa()">   
<tr>
        <td class="w47">{{:#index+1}}</td> 
        <td>{{:machine_nm}}</td> 
        <td class="w80">{{:re_amt}}</td> 
        <td class="w47"><a href="javascript:gotoRepOrderPage('{{:machine_id}}')"  class="btn3">详情</a></td> 
</tr> 
</tbody>
</script>
<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">报表信息</a>&nbsp;>&nbsp;故障设备信息</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">                
                		<div class="title mb18">故障设备信息<a href="#" onclick="history.back(-1);"  class="btn1 f14 fb fr">返回</a></div>
  						<!--查询条件-->
                          <table id="search_area" class="w_auto" border="0" cellspacing="0" cellpadding="0">
                          	<tr>
                            	<td id="mtype">
                                </td>
                                <td id="mstate">
                                </td>
                                <td>
                                   <span class="f14">时间&nbsp;&nbsp;</span>
                                    <input id="start" name="maintResStatisDTO.st_date"   class="laydate-icon w110" type="text" value="" dc="true" readonly/>&nbsp;~&nbsp;
                                    <input id="end" name="maintResStatisDTO.en_date"  class="laydate-icon mr20 w110" type="text"  value="" dc="true" readonly/>
                                </td>
                                <td><a href="#" id="searchBt" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                                
                            </tr>
                          </table>
 						 <!--Echart-->
                          <div id="main" style="height:500px;border:1px solid #ccc;padding:10px;"></div> 
 						<!--表格-->
                         <div class="t_r table_wrap mt18">  
	                		<table class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
	                         <thead>
	                            <tr> 
	                             <th class="w47">序号</th>
	                              <th>设备名称</th>
	                              <th class="w80">执行次数</th>
	                              <th class="w47">详情</th>
	                            </tr> 
	                            </thead>
	                          </table> 
                          <div class="t_r_content"> 
                      		<table id="infoList" class="tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
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
