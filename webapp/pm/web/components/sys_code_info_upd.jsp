<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>code_id修改</title>
    <%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
</head>

<!-- 设备类型 -->

<script type="text/x-jsrender" id="formTemplate"> 
    <tr>
    	<td class="odd">设备名称</td>
        <td><input name="componentssysCodeInfo.code_nm" value="{{:data.code_nm}}" type="text" dc="true" dcrequired="请输入设备名称" maxlength="50"/></td>
		
	</td>
    </tr> 
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>

    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">停机维修信息</a>&nbsp;>&nbsp;子部件管理</a>&nbsp;>&nbsp;设备修改</div>
            
            	<div class="main" id="aaa">
                
                		<div class="title mb18">设备修改</div>  						
            			<table id="updateForm" class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                        </table>
        				
                        <div class="tex_c mt22 mb30"><a href="#" id="saveBtn"  class="btn1 f14 fb mr10">提交</a><a href="#" id="gobackBt" class="btn1 f14 fb">返回</a></div>
        		</div>
          </div>
    </div>
</body>

<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/components/js/sys_code_info_upd.js"></script>
</html>
