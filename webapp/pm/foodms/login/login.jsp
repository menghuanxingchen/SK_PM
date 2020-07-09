<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<title>登录</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9">
<meta http-equiv="x-ua-compatible" content="IE=9" >
<link  href="<%=request.getContextPath()%>/pm/common/css/style_login.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
</head>
<body >
<div id="com_body">   
    
         <div id="login">
            
            <div class="lolo"><img src="<%=request.getContextPath()%>/pm/common/images/logo.png" /><span style="border-left:1px solid #73a2ea;padding-left:0.1rem; font-size:0.24rem; font-weight:600;">MES系统</span><span style="color:#ff8888;font-size:0.2rem;padding-left:0.1rem; font-weight:600;">Manufacturing Execution System</span></div>
    
            <div class="forms">  
    
                <form action="#" method="get">
    
                    <div class="h200 login_info">
	                    <p class="h100">用户名:<input type="text" autocomplete="off" id="loginname" name="sysUserInfo.user_num" dc="true" /></p>
	                    				密&nbsp;码:<input type="password" autocomplete="off" id="password" name="sysUserInfo.pwd" dc="true"/>
                     </div>       
                    <div class="choose">
                        <input type="radio"  id="loginRadio1" value="PC" name="loginRadio" class="radio" checked/>网页版&nbsp;&nbsp;
                        <input type="radio"  id="loginRadio2" value="MB" name="loginRadio" class="radio" />手机版
                    </div>                                                   
    
                </form>
                <a href="javascript:login();" class="btn1">登 录</a>
    
            </div>
                
        </div>
    
    </div>
    
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script>
        (function (doc, win) {
        	$("#loginname").focus();
          var docEl = doc.documentElement,
            resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
            recalc = function () {
              var clientWidth = docEl.clientWidth;
              oSize = 100 * (clientWidth / 720);
              
              if (!clientWidth) return;
              if (oSize > 75) {
                oSize = 75; // 限制rem值 1080(最大宽度) / 7.2（设计宽度） =100
				var com_body = document.getElementById('com_body');  
          		com_body.style.width="7.2rem";
				com_body.style.margin="0 auto";
               }
              docEl.style.fontSize = oSize + 'px';
              
            };
    
          if (!doc.addEventListener) return;
          win.addEventListener(resizeEvt, recalc, false);
          doc.addEventListener('DOMContentLoaded', recalc, false);
        })(document, window);
        
        $(document).keydown(function(event){  
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if (e && e.keyCode == 13) {
                login();
            }
    });
</script>
<script src="<%=request.getContextPath()%>/pm/common/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/foodms/login/js/login.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/handsome.min.js" type="text/javascript"></script> 
<script src="<%=request.getContextPath()%>/pm/common/js/scripts.js" type="text/javascript"></script>
</html>
