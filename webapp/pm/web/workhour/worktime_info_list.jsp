<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=0">
    <title>工作班制管理</title>
    <%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/workhour/js/worktime_info_list.js"></script>
</head>
<script type="text/x-jsrender" id="dateListTemplate">
	<tr>
 		<td class="w47" name="worktimeInfo[{{:#index}}].days" dc="">{{:days}}</td>
        <td class="w80" name="worktimeInfo[{{:#index}}].week" dc="">{{:week}}</td>
		<td class="w100p"><input type="text" name="worktimeInfo[{{:#index}}].work_hour" class="numCheck" maxlength="2" dc="" dctype="数字" value="{{:work_hour}}"></td>
		<input type="hidden" name="worktimeInfo[{{:#index}}].workhour_id" dc="" value="{{:workhour_id}}">
		<input type="hidden" name="worktimeInfo[{{:#index}}].date_group" dc="" value="{{:date_group}}">
	</tr>

</script>
<body>
 <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">计划信息</a>&nbsp;>&nbsp;工作班制管理</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">工作班制管理<a href="javascript:addDataFun();" class="btn1 f14 fb fr">保存</a></div>
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                                <td>
                                	<span class="f14">月份</span>&nbsp;&nbsp;
                                    <input class="laydate-icon w110" type="text" id="start_dt" name="worktimeInfo.date_group"  dc="true" readonly/>
                                </td>
                                <td>&nbsp;&nbsp;</td>
                                <td><a href="javascript:queryDataList();" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                            </tr>
                          </table>
 						<!--表格-->
                        <!-- <div class="t_r table_wrap mt18"> 
                                        <table  class="t_r_t t_r_title tt tt1" id="t_r_t" border="0" cellspacing="0" cellpadding="0">  -->  
                          <div class="table_wrap" style="overflow-x:auto;">
                          <div class="t_r mt18" style="width:auto;">  
                					<table class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
	                                        <thead>
		                                         <tr> 
		                                           <th class="w47">日期</th>
		                                           <th class="w80">星期</th>
		                                           <th class="w100p">工时(小时)</th>
                                       			 </tr> 
	                                         </thead>
	                                          <tbody id="dateList">
	                                         </tbody>
	                                    </table>
                       	  </div>
                       	  </div>
                       <!-- 右侧 结束-->
        		</div>
          </div>
    </div>
</body>
</html>
