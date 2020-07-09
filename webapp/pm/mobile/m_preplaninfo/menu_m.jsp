<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>预见预防菜单——手机端</title>
<%@ include file="/pm/common/jsp/common_mobile.jsp"%> 
</head>
<body style="background:#fff;">
<div id="com_body">
    <div id="_centent">
        
    	<section class="index_head"> 
        	<img src="<%=request.getContextPath()%>/pm/common/images/mobile/logo.png"/> 
    	</section>
        
        <section class="mt-1"> 
        
        	<table width="100%" cellspacing="0" cellpadding="0" class="index_menu">
            	<tr class="h400">
                	<td class="nav1"><a href="javascript:goToMachineList();" ><img src="<%=request.getContextPath()%>/pm/common/images/mobile/ico_menu1_03.png" />预防性维护</a></td>
                    <td class="nav2"><a  href="javascript:goToPropMachineList();"><img src="<%=request.getContextPath()%>/pm/common/images/mobile/ico_menu2_03.png" />预见性维护</a></td>
                </tr>
                <tr>
                	<%-- <td class="nav3"><a  href="javascript:goToRepairMachineList();" ><img src="<%=request.getContextPath()%>/pm/common/images/mobile/ico_menu3_11.png" />大修项目</a></td> --%>
                	<td class="nav4"><a  href="javascript:goBack();"><img src="<%=request.getContextPath()%>/pm/common/images/mobile/index_bk_03.png"/>返回</a></td>
                </tr>
			</table>
        
        </section>
    
</div>
</div> 
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/mobile/m_preplaninfo/js/menu_m.js"></script>
</html>
