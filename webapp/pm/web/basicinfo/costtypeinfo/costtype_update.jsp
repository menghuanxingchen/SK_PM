<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>费用类别信息修改</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>
<script type="text/x-jsrender" id="dataListTemplate">
      <tr>
     	<td class="odd w10">费用类别名称</td><input type="hidden" value="{{:sysCodeInfo.code_id}}"  name="sysCodeInfo.code_id"   dc=""/>
         <td><input type="text" placeholder="请输入名称" value="{{:sysCodeInfo.code_nm}}" name="sysCodeInfo.code_nm" maxlength="50" dc=""/></td>
     </tr>
</script>
<body>
	<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" id="formId">
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">系统功能</a>&nbsp;>&nbsp;费用类别信息</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">费用类别信息修改</div>
  						
                        <table id="dataListForm" class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
 	
                        </table>
                        <div class="tex_c mt22 mb30"><a href="#" id="submitBtn" class="btn1 f14 fb mr10">提交</a><a href="#" id="closeBtn" class="btn1 f14 fb">返回</a></div>
                        <!-- 右侧 结束-->
    
        		</div>
          </div>
    </div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/basicinfo/costtypeinfo/js/costtype_update.js"></script>
</html>