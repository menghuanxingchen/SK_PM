<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>平均故障间隔时间统计</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/maintainlog/js/MTBF_log.js"></script>
</head>
<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">停机维修信息</a>&nbsp;>&nbsp;平均故障间隔时间统计</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">                
                		<div class="title mb18">平均故障间隔时间（MTBF）</div>
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
                                <td>
                                	<span class="f14">设备</span>&nbsp;&nbsp;
                                    <input type="text" id="selPotNm" value="LB-V343罐" class="mr20" onclick="selectPot()" readonly/>
                                    <input type="hidden" id="selPotId" name="selPotId" value="06ef8d3ba7004b0c885ca5421e4e167b" class="mr20" dc="true"/>
                                </td>
                                <td><a href="#" id="searchBt" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                                
                            </tr>
                          </table>
                          <!--Echart-->
                          <div id="main" style="height:500px;border:1px solid #ccc;padding:10px;"></div> 
        
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
