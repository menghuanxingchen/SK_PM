<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>共同code信息新增</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
</head>
<script type="text/x-jsrender" id="formTemplate">
    <ul class="">
    <li><label>code组</label>
	 <select id='codeKind' name="sysCodeInfo.code_group_value" class="select1"  dc="true" >
		{{renderOptionFun codeGroupList "code_group_value" "code_group_name" "" "" /}}
	 </select></li>
    <li><label>code value</label><input id='codeValue' dcrequired="请输入code value" type="text" class="dfinput" value="" dc="" /></li>
	<li><label>code 名称</label><input id='codeNm' dcrequired="请输入code 名称" type="text" class="dfinput" value="" dc="" /></li>
	<li><label>下级code组</label> 
	 <select name="sysCodeInfo.sub_code_group_value" class="select1"  dc="true" >
		{{renderOptionFun codeGroupList "code_group_value" "code_group_name" "" "" /}}
	 </select></li>
    </ul>
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
	 <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">共同code信息</a>&nbsp;>&nbsp;共同code信息新增</div>
            	
			    <div class="crumbs" id="formId">
			    </div>
				<div style="margin:0 auto;text-align: center;padding-bottom: 20px;">
						<input name="" type="button" id="submitBtn" class="btn" value="创建"/>&nbsp;&nbsp;&nbsp;&nbsp;<input name="" id="closeBtn" type="button" class="btn" value="返回"/>
				</div>
		</div>
	</div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/basicinfo/commcode/js/commcode_add.js"></script>
</html>