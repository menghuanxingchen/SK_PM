<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>安全检查</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/seccheckinfo/js/seccheck_list.js"></script>
<script type="text/x-jsrender" id="reapirOrderListTemplate">
	<tr>
 		<td class="w47">{{:#index+1}}</td>
        <td class="w80">{{:work_type_nm}}</td>
		<td class="w100p">{{:sec_detail}}</td>
		<td class="w80">{{:sec_num}}</td>
		<td class="w100p">{{:up_check_date}}</td>
        <td class="w100p">{{:failure_date}}</td>
		<td class="w80">{{:cur_state}}</td>
		<td class="w150">{{:sec_plan_date}}</td>
		<td class="w150">{{:sec_check_date}}</td>
		<td class="w80">{{:sec_result}}</td>
        <td class="w80">{{:check_dept}}</td>
		<td class="w80">{{:remark}}</td>
		<td class="w47">
			<a href="javascript:updateDataFun('{{:sec_check_id}}');"  class="btn3">编辑</a>
		</td> 
        <td class="w47"><a href="javascript:deleteDataFun('{{:sec_check_id}}');"  class="btn3" >删除</a></td> 
	</tr>

</script>
<script type="text/x-jsrender" id="repairPlacedownListTemplate">
	<select  class="mr20" id="repair_place" name="secCheckInfo.work_type" dc="true">
		{{renderOptionFun repairPlaceList "code_value" "code_nm" "" "工作分类"/}}
 	</select>
</script>

</head>
<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">计划信息</a>&nbsp;>&nbsp;安全检查</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">安全检查<a href="javascript:addDataFun();" class="btn1 f14 fb fr">新增</a></div>
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>

                                <td id="repairPlace">
                                </td>
                                <td>
                                	<span class="f14">计划送检日期</span>&nbsp;&nbsp;
                                    <input class="laydate-icon w110" type="text" id="start_dt" name="secCheckInfo.start_dt"  dc="true" readonly/>&nbsp;~&nbsp;<input class="laydate-icon mr20 w110" type="text" id="end_dt" name="secCheckInfo.end_dt"  dc="true" readonly/>
                                </td>
                                <td>
                                	<span class="f14">内容</span>&nbsp;&nbsp;
                                    <input type="text" name="secCheckInfo.sec_detail" value="" class="mr20" dc="true"/>
                                </td>
                                <td>
                                	<select class="mr20 w150" name="secCheckInfo.orderType" dc="true">
                                        <option value="sec_plan_date asc">计划送检日期正序</option>
                                        <option value="sec_plan_date desc">计划送检日期倒序</option>
                                    </select>
                                </td>
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
		                                           <th class="w47">序号</th>
		                                           <th class="w80">工作分类</th>
		                                           <th class="w100p">内容</th>
		                                           <th class="w80">数量次数</th>
		                                           <th class="w100p">上次检定日期</th>
		                       <!--                     <th class="w47">频率</th> -->
		                                           <th class="w100p">失效日期</th>
		                                           <th class="w80">当前状态</th>
		                                           <th class="w150">计划送检日期</th>
		                                           <th class="w150">实际送检日期</th>
		                                           <th class="w80">送检结果</th>
		                                           <th class="w80">鉴定单位</th>
		                                           <th class="w80">备注</th>
		                                           <th class="w47">编辑</th>
		                                           <th class="w47" id="last">删除</th>
                                       			 </tr> 
	                                         </thead>
	                                    </table>
	                              <div class="t_r_content" style="height:1000px;"> 
	                                   <table class="t_r_t tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
	                                         <tbody id="t_r_content" onscroll="aa()">
	                                         <!-- <tbody class="t_r_content" id="t_r_content" onscroll="aa()"> -->
	                                         		
	                                         </tbody>
                                        </table> 
                                  </div>
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
</html>
