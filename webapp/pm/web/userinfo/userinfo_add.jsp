<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户信息新增</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>
<script type="text/x-jsrender" id="departListTemplate">
       <select id="dept" name="sysUserInfo.dept_code" class="mr20"  dc="true" >
		{{renderOptionFun departList "code_value" "code_nm" "" "部门" /}}
	 </select>
</script>
<script type="text/x-jsrender" id="subDepartListTemplate">
       <select name="sysUserInfo.sub_dept_code" class="mr20"  dc="true" >
		{{renderOptionFun subDepartList "code_value" "code_nm" "" "职能部门" /}}
	 </select>
</script>
<script type="text/x-jsrender" id="positionListTemplate">
       <select name="sysUserInfo.position_id" class="mr20"  dc="true" >
		{{renderOptionFun positionList "code_value" "code_nm" "" "职位" /}}
	 </select>
</script>
<script type="text/x-jsrender" id="sqmRoleListTemplate">
      <select id="sqm_role_code" name="sysUserInfo.sqm_role_code" class="mr20"  dc="true" >
				{{renderOptionFun sqmRoleList "code_value" "code_nm" "" "SQM角色" /}}
	 </select>
</script>
<body>
	<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" id="formId">
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">用户信息</a>&nbsp;>&nbsp;用户信息</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">用户信息新增</div>
  						
                        <table class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                        	<tr>
                            	<td class="odd w10">工号</td>
                                <td><input type="text" placeholder="请输入工号" dcrequired="请输入工号" name="sysUserInfo.user_num"   dc="" maxlength="50"/></td>
                                <td class="odd w10">姓名</td>
                                <td><input type="text" placeholder="请输入姓名" dcrequired="请输入姓名" name="sysUserInfo.user_nm"   dc="" maxlength="50"/></td>         	
                            </tr>
                            <tr>
                            	<td class="odd">密码</td>
                                <td><input type="password" id="pwd" dcrequired="请输入密码"   maxlength="50"/></td>
                                <td class="odd">确认密码</td>
                                <td><input type="password" id="pwdCheck" dcrequired="请输入确认密码" name="sysUserInfo.pwd"   dc="" maxlength="50"/></td>
                            </tr>
                            <tr>
                            	<td class="odd">职位</td>
                                <td id="positionForm">
                                </td>
                                <td class="odd">邮箱</td>
                                <td><input type="text" placeholder="请输入邮箱地址"  dctype="邮箱" name="sysUserInfo.email"  dc="" maxlength="50"/></td>
                            </tr>
                             <tr>
                            	<td class="odd">部门</td>
                                <td id="departTypeForm">
                                </td>
                                <td class="odd">职能部门</td>
                                <td id="subDepartTypeForm">
                                </td>
                            </tr>
                            <tr>
                            	<td class="odd">办公室电话</td>
                                <td><input type="text" placeholder="请输入办公室电话" dctype="固定电话" name="sysUserInfo.Telephone"  dc="" maxlength="11"/></td>
                                <td class="odd">手机</td>
                                <td><input type="text" placeholder="请输入手机号码"  dctype="手机号" name="sysUserInfo.mobile"  dc="" maxlength="11"/></td>
                            </tr>
                            <tr>
                            	<td class="odd">SQM评价</td>
                                <td>  <input type="text" placeholder="(N/Y)" value="N"  dctype="SQM评价" name="sysUserInfo.evaluatYn"  dc="" />
								</td>
								<td class="odd">SQM角色</td>
                                <td id="sqmRoleTypeForm">
                                </td>
                            </tr>
                        </table>
        				
                        <div class="tex_c mt22 mb30"><a href="#" id="submitBtn" class="btn1 f14 fb mr10">提交</a><a href="#" id="closeBtn" class="btn1 f14 fb">返回</a></div>
                        <!-- 右侧 结束-->
    
        		</div>
          </div>
    </div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/userinfo/js/userinfo_add.js"></script>
</html>