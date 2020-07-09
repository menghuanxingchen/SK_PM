<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>安全检查管理</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/seccheckinfo/js/seccheck_add.js"></script>
<script type="text/x-jsrender" id="repairPlacedownListTemplate">
	<select id="repair_place" name="secCheckInfo.work_type" dc="true" dcrequired="请输入工作分类" >
		{{renderOptionFun repairPlaceList "code_value" "code_nm" "" "工作分类"/}}
 	</select>
</script>
</head>
<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">计划信息</a>&nbsp;>&nbsp;安全检查管理</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">安全检查单新增</div>
        				
        				 <table class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0" id="repair_add_table">
                            <tr>
                               <td class="odd w10">工作分类</td>
                               <td id="repairPlace">
                                </td>
                            	<td class="odd w10">内容</td>
                                <td><input type="text" name="secCheckInfo.sec_detail" dc="true" maxlength='25' dcrequired="请输入内容"/></td>  	 
                            </tr>
                            <tr>
                                <td class="odd">数量次数</td>
                                <td><input type="text" name="secCheckInfo.sec_num" dc="true" maxlength='25' dctype='数字' dcrequired="请输入数量次数"/></td>  
                            	<td class="odd">上次检定日期</td>
                                <td>
                                	<input class="laydate-icon" type="text" name="secCheckInfo.up_check_date" dcrequired="请输入上次检定日期" dc="true" readonly/>
                                </td>
                            </tr>
                            <tr>
                                <input type="hidden" name="secCheckInfo.frequency" dc="true" maxlength='25' dcrequired="请输入频率" value="10年" readonly/>
                            	<td class="odd">失效日期</td>
                                <td>
                                	<input class="laydate-icon" type="text" name="secCheckInfo.failure_date" dc="true" readonly/>
                                </td>	
                                <td class="odd">当前状态</td>
                                <td><input type="text" name="secCheckInfo.cur_state" dc="true" maxlength='25' dcrequired="请输入当前状态"/></td>  
                            </tr>
                            <tr>
                            	<td class="odd">计划送检日期</td>
                                <td>
                                	<input class="laydate-icon" type="text" name="secCheckInfo.sec_plan_date" dc="true" readonly/>
                                </td>
                                <td class="odd">实际送检日期</td>
                                <td>
                                	<input class="laydate-icon" type="text" name="secCheckInfo.sec_check_date" dc="true" readonly />
                                </td>	  	
                            </tr>
                            <tr>
                            	<td class="odd">送检结果</td>
                                <td  colspan="3"><input type="text" name="secCheckInfo.sec_result" dc="true" maxlength='25'  dcrequired="请输入送检结果"/></td>  	
                            </tr>
                            <tr>
                                <td class="odd">检定单位</td>
                                <td colspan="3"><input type="text" name="secCheckInfo.check_dept" dc="true" maxlength='25'  dcrequired="请输入检定单位"/></td>  	
                            </tr>
                            <tr>
                            	<td class="odd">备注</td>
                                <td colspan="3"><textarea rows="3" cols="5" name="secCheckInfo.remark" dc="true" maxlength='25'></textarea></td>
                            </tr>
                        </table>
                        
                        
                        <div class="tex_c mt22"><a href="javascript:addRepairOrderInfoData();" class="btn1 f14 fb mr10">提交</a><a href="<%=request.getContextPath()%>/pm/web/seccheckinfo/seccheck_list.jsp" class="btn1 f14 fb">返回</a></div>
                        <!-- 右侧 结束-->
        		</div>
        		</div>
          </div>
</body>
</html>
