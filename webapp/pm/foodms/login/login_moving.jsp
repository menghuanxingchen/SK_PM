<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<title>跳转</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9">
<meta http-equiv="x-ua-compatible" content="IE=9" >
<link  href="<%=request.getContextPath()%>/pm/common/css/style_login.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
</head>
<body >

    
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script>
        (function (doc, win) {
        	var TYPE = getRequestParameterValue("TYPE");//TEMPORARY 临时人员扫描
        	var user_num = getRequestParameterValue("sysUserInfo.user_num");
        	var pwd = getRequestParameterValue("sysUserInfo.pwd");
        	

        	var postData = {"sysUserInfo.user_num":user_num,"sysUserInfo.pwd":pwd};
        	postData=$.extend(postData,{"sysUserInfo.loginType":TYPE});
        	$.ajax({
        		url:path+"/loginManagerController/checkSysUserInfo.json",
        		dataType:"json",
        		data:postData,
        		success:function(result){
        			if(!result.opflag){
        				lalert(result.opmessage,'error');
        			}else{
        				window.location=path+"/loginManagerController/showManipage.do";
        			}
        		},
        		error:function(error){
        			lalert('网络原因操作失败','error');
        		}
        	});
        	
        })(document, window);
       
</script>
<script src="<%=request.getContextPath()%>/pm/common/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/handsome.min.js" type="text/javascript"></script> 
<script src="<%=request.getContextPath()%>/pm/common/js/scripts.js" type="text/javascript"></script>
</html>
