<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>实施结果新增</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/proposal/js/proposal_result_add.js"></script>
</head>
<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">提案书</a>&nbsp;>&nbsp;提案书信息</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">实施结果新增</div>
                        <table class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0" id="reapir_result_table">
                             <tr>
                            	<td class="odd w10">提案书单号</td>
                                <td colspan="3">
                                	<input type="text" id="proposal_num" readonly/>
                                </td>
                            </tr>
                            <tr>
                           	 	<td class="odd w10">申请日期</td>
                                <td>
                                	<input id="create_time" type="text" readonly/>
                                </td>
                                <td class="odd w10">要求完成日期</td>
                                <td><input type="text" id="require_date" readonly/></td>
                            </tr>
                            <tr>
                            	<td class="odd w10">实施内容</td>
                                <td colspan="3"><textarea rows="3" id="operate_detail" name="proposalOperateInfo.operate_detail" dc="true" dcrequired="请输入实施内容"></textarea></td>
                            </tr>
                             <tr>
                                <td class="odd w10">实施费用</td>
                                <td colspan="3"><input type="text" id="operate_expense" name="proposalOperateInfo.operate_expense" dc="true" dctype='金额'/></td>
                            </tr>
                         </table>
                        <div class="tex_c mt22 mb30">
	                        <a href="javascript:addRepairResultInfo();" class="btn1 f14 fb mr10">提交</a>
	                        <a href="<%=request.getContextPath()%>/pm/web/proposal/proposal_info_list.jsp" class="btn1 f14 fb mr10">返回</a>
                        </div>
                        <!-- 右侧 结束-->
        		</div>
        		</div>
          </div>
</body>
</html>
