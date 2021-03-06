<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>工人分配新增</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/repair/js/distribution_add.js"></script>

<script type="text/x-jsrender" id="departGroupDownListTemplate">
	<select id="depart_group" name="sysUserInfo.dept_code" dc="true" dcrequired="请选择部门">
		{{renderOptionFun departGroupList "code_value" "code_nm" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="departGroupSubDownListTemplate">
	<select id="depart_group_sub" name="sysUserInfo.sub_dept_code" dc="true" dcrequired="请选择职能部门">
		{{renderOptionFun departGroupSubList "code_value" "code_nm" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="repairCategoryDownListTemplate">
	<select id="repair_category" name="distributionInfo.repair_type" dc="true" dcrequired="请选择维修类别">
		{{renderOptionFun repairCategoryList "code_value" "code_nm" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="stateYndownListTemplate">
	<select id="state_yn" name="distributionInfo.notice_yn" dc="true" dcrequired="请选择是否通知厂长">
		{{renderOptionFun stateYnList "code_value" "code_nm" stateYnList[0].code_value ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="repairClassifyListTemplate">
	<select id="repair_classify" name="distributionInfo.repair_classify" dc="true" dcrequired="请选择维修类型">
		{{renderOptionFun repairClassifyList "code_value" "code_nm2" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="userIdDownListTemplate">
	<select id="user_id" name="distributionInfo.user_id" dc="true" dcrequired="请选择分配人">
		{{renderOptionFun sysUserInfoList "user_num" "user_nm" "" ""/}}
 	</select>
</script>
</head>
<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
     <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">维修信息</a>&nbsp;>&nbsp;维修单信息</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">工人分配新增</div>
  						
  						<table class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                         	<tr>
                         		<td class="odd w10">维修内容</td>
                                <td colspan="3"><textarea rows="3" id="repair_detail" readonly></textarea></td>
                            </tr>
                         </table>
                          
            			<table class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0" id="formId">
                            <tr>
                            	<td class="odd w10">维修类别</td>
                                <td width="40%" id="repairCategory">
                                </td>
                                <td class="odd w10">部门</td>
                                <td id="departGroup">
                                </td>
                            </tr>

                            <tr>
                            	<td class="odd">职能部门</td>
                                <td id="departGroupSub">
                                </td>
                                <td class="odd">分配人</td>
                                <td id="userId">
                                </td>
                            </tr>

                            <tr>
                            	<td class="odd">要求完成时间</td>
                                <td><input class="laydate-icon" type="text" id="require_date" name="distributionInfo.require_date" dc="true" dcrequired="请选择要求完成时间" readonly/></td>
                                <td class="odd">是否通知厂长</td>
                                <td id="stateYn">
                                </td>
                            </tr>

 							<tr>
                            	<td class="odd">维修类型</td>
                            	<td id="repairClassify">
                            	<td class="odd">是否修改PID</td>
                                <td>
		                                <select class="ssel"  name="distributionInfo.distribution_pid" dc="true">
						                		<option value=''>请选择</option>
						                		<option value='1'>需要</option>
						                    	<option value='2'>不需要</option>
		                                </select>
                                </td>
                            </tr>
                        </table>
        				
                        <div class="tex_c mt22 mb30"><a href="javascript:addDistributionInfoData();" class="btn1 f14 fb mr10">提交</a><a href="javascript:returnBtnFun();" class="btn1 f14 fb">返回</a></div>
                        <!-- 右侧 结束-->
    
        		</div>
          </div>
    </div>
</body>
</html>
