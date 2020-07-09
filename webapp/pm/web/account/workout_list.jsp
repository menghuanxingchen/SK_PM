<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>项目管理列表</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>
<script type="text/x-jsrender" id="departListTemplate">
       <select id="item_type" name="itemInfo.item_type" class="mr20"  dc="true" >
		{{renderOptionFun projectTypeList "code_value" "code_nm" "" "" /}}
	 </select>
</script>
<script type="text/x-jsrender" id="subDepartListTemplate">
       <select id="state" name="itemInfo.item_state" class="mr20"  dc="true" >
		{{renderOptionFun projectStateList "code_value" "code_nm" "" "" /}}
	 </select>
</script> 
<script type="text/x-jsrender" id="dataListTemplate">
        <tr name="dataList">
        <td class="w47">{{:#index+1}}</td>
        <td ><a href="javascript:detailDataFun('{{:work_out_id}}');" class="btn3">{{:work_out_nm}}</a> </td>
		<td class="w47"><input type="hidden" id ="pay_yn" value="{{:pay_yn}}"/><a href="javascript:updatePayYnFun('{{:work_out_id}}','{{:pay_yn}}');" class="pay_nm">{{:pay_nm}}</a></td>
        <td class="w47"><a href="javascript:updateEntityDataFun('{{:work_out_id}}');" class="btn3">编辑</a></td>
		<td class="w47"><a href="javascript:deleteDataFun('{{:work_out_id}}');" class="btn3"> 删除</a></td>
        </tr>
</script>
<script type="text/x-jsrender" id="orderListTemplate">
       <select id='codeKind' name="sysUserInfo.orderType" class="mr20"  dc="true" >
		{{renderOptionFun orderList "code_value" "code_nm" "" "排序类型" /}}
	 </select>
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap">
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">结算</a>&nbsp;>&nbsp;出勤统计表查询列表</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">出勤统计表查询列表<a href="javascript:addEntityDataFun();" class="btn1 f14 fb fr">新增</a></div><!--1019-->
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                                <td>
                                	<span class="f14" >时间：</span>&nbsp;&nbsp;
                                     <input class="laydate-icon w110" type="text" id="start_dt" name="workoutInfoList.start_dt" dc="true" dcrequired="请选择开始时间" readonly/>
                                    </td>
                                <td>&nbsp;~&nbsp;</td>
                                <td>
                               <input class="laydate-icon w110 mr20" type="text" id="end_dt" name="workoutInfoList.end_dt" dc="true" dcrequired="请选择结束时间" readonly/>
                                </td>
                                 <td id="departTypeForm">
                                </td>
                                <!-- <td><span class="f16" >状态：</span>&nbsp;&nbsp;</td>
                                <td id="subDepartTypeForm">                     
                                </td>  -->
                                <td><a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                            </tr>
                          </table>
                           <div class="t_r table_wrap mt18">  
                          		<table    class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                                 <tr> 
                                                   <th class="w47">序号</th>
                                                   <th>出勤表名称</th>
                                                   <th class="w47" >操作</th>
                                                   <th class="w47" >编辑</th>
                                                   <th class="w47" id="last">删除</th>
                                                 </tr>
                                             </thead> 	           
                                </table>
                                <div class="t_r_content" > 
                                	<table  class="tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
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
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/account/js/workout_list.js"></script>
</html>