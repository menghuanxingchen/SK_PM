<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>MES首页</title>
</head>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<style>
*{
    padding: 0;
    margin: 0;
    box-sizing: border-box;
}
body{
    background: url("<%=request.getContextPath()%>/pm/mobile/img/bg.png");
    background-size: 100% 100%;
    height: 100vh;
}
.container{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    flex-wrap: wrap;
}


.logo input{
    display: flex;
    flex-direction: row;
    width: 405px;
    padding: 15px 0 15px 40px;
    background: #fafafa;
    outline: none;
    border: 2px #e5e5e5 solid;
    border-radius: 6px;
}
.input1{
    margin-top: 30px;
    margin-bottom: 25px;
    position: relative;
}
.input1 img{
    position: absolute;
    top: 12px;
    left: 12px;
}
.input2 img{
    position: absolute;
    top: 12px;
    left: 12px;
}
.input2{
    position: relative;
    margin-bottom: 25px;
}
.logo button{
    width: 405px;
    padding: 10px 0 10px 0;
    border-radius:4px;
    outline: none;
    border: none;
    background: #7554bf;
    color: #fff;
}
.register{
    background: url("<%=request.getContextPath()%>/pm/mobile/img/bg-login.png")no-repeat;
    background-size:100% 100%;
    height: 440px;
    width: 320px;
    padding-left: 50px;
}
.register p{
    padding-top: 100px;
    padding-bottom: 46px;
    color: #fff;
}
.register a{
    color: #fff;
    text-decoration: none;
}
.register a img{
    padding-left: 5px;
    vertical-align: middle;
}

@media (max-width: 600px) {
    .logo{
        width: 100%;
    }
    .logo input{
        width: 100%;
    }
    .logo button{
        width: 100%;
    }
    .register{
        width: 100%;
        padding-right: 0;
        height: 310px;
    }
}
</style>
<body>
<div id="com_body">
    <div class="container">
        <div id="login" class="logo">
            <div style="color: #73a2ea;text-align:center; height:1.5rem;">
	            <img src="<%=request.getContextPath()%>/pm/common/images/logo.png" />
	            <span style="border-left:1px solid #73a2ea;padding-left:0.1rem; font-size:0.24rem; font-weight:600;">MES系统</span>
            </div>
            <div class="input1">
                <img src="<%=request.getContextPath()%>/pm/mobile/img/yonghuming.png" alt="">
                <input type="text" id="loginname" placeholder="请输入用户名" name="sysUserInfo.user_num" dc="true" />
            </div>
            <div class="input2">
                <img src="<%=request.getContextPath()%>/pm/mobile/img/mima.png" alt="">
                <input type="password" id="password" name="sysUserInfo.pwd" dc="true" placeholder="请输入登录密码"/>
            </div>
            <button onclick="login()">登录</button>
        </div>
    </div>
</div>
</body>
<script src="<%=request.getContextPath()%>/pm/common/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/handsome.min.js" type="text/javascript"></script> 


<script type="text/javascript">

/**
 * 登录
 * **/
function login(){
	var userId = "";
	var restaurantid = "";
	var postData = collectData("login");
	postData=$.extend(postData,{"sysUserInfo.loginType":"UNIAPP"});
	$.ajax({
		url:path+"/loginManagerController/checkSysUserInfo.json",
		dataType:"json",
		data:postData,
		success:function(result){
			if(result.opflag){
				//lalert(result.opmessage,'success');
				if(result.sysUserinfo){
					currentUserId = result.sysUserinfo.user_num;
					window.location=path+"/loginManagerController/showManipage.do";
				}
			}else{
				lalert(result.opmessage,'error');
			}
		},
		error:function(error){
			lalert('网络原因操作失败','error');
		}
	});
}
</script>
</html>