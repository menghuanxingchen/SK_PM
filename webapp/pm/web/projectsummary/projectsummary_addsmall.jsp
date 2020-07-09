<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>小项目新增</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
</head>
<script type="text/x-jsrender" id="projectPlanStateTemplate">
       <select name="projectSummaryInfo.state" class="mr20"  dc="true" >
		{{renderOptionFun projectPlanState "code_value" "code_nm" "" "noflag" /}}
	 </select>
</script>

<script type="text/x-jsrender" id="projectPlanStateAddTemplate">
       <select name="projectSummaryInfo.state" class="mr20"  dc="true" >
		{{renderOptionFun projectPlanState "code_value" "code_nm" "" "noflag" /}}
	 </select>
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
	  <div class="contents">
          <div class="main_wrap" id="formId">
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">项目管理</a>&nbsp;>&nbsp;小项目新增</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">小项目基本信息</div>
        				<div class="w100" id="formId">
                         <table class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0" id="repair_add_table">
                            <tr>
                               <td class="odd w10">项目名称</td>
                               <input type="hidden" name="projectSummaryInfo.project_type" dc="true" value="2" />
                               <td id="repairPlace"><input type="text" name="projectSummaryInfo.project_nm" dcrequired="请输入名称" id="projectNm" dc="true" maxlength='25' dc=""/>
                                </td>
                                <td class="odd w10">内容</td>
                                <td><input type="text" name="projectSummaryInfo.content" dc="true" maxlength='1000' dcrequired="请输入内容"/></td>  	
                            </tr>
                            <tr>
                                <td class="odd">负责人</td>
                                <td><input type="text" name="projectSummaryInfo.duty_user" dc="true" maxlength='25' dcrequired="请输入负责人"/></td>
                                <td class="odd">指示开始时间</td>
                                <td>
                                	<input class="laydate-icon" id="planStartDate" type="text" name="projectSummaryInfo.plan_start_date" dc="true" />
                                </td>  
                            </tr>
                            <tr>
                            	<td class="odd">状态</td>
                                <td  id="projectPlanStateForm">
                                </td>	
                            	<td class="odd">计划完成时间</td>
                                <td>
                                	<input class="laydate-icon" id="planEndDate" type="text" name="projectSummaryInfo.plan_end_date" dc="true" />
                                </td>
                            </tr>
                        </table>
                        
                        <div class="title_l">项目详细信息<a href="javascript:addItem();" class="btn1 f14 fb fr">添加行</a></div>
                          <table class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                            <tr class="odd">
                            	<td class="w130">操作时间</td>
                                <td>内容</td>
                                <td class="w47">操作</td>
                            </tr>
                            <tbody id="addRowsTable">
							</tbody>
                        </table>
                        <div class="tex_c mt22"><a href="javascript:saveForm();" class="btn1 f14 fb mr10">提交</a><a href="<%=request.getContextPath()%>/ProjectSummaryController/defaultJsp.do" class="btn1 f14 fb">返回</a></div>
                        <!-- 右侧 结束-->
    					</div>
        		</div>
          </div>
    </div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/projectsummary/js/projectsummary_addsmall.js"></script>
</html>