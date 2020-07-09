<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>MES首页</title>
<%@ include file="/pm/common/jsp/common_mobile.jsp"%> 
</head>

<body style="background:#fff;">
<div id="com_body">
    <div id="_centent">
        
    	<section class="uniapp_index_head"> 
        	<img src="<%=request.getContextPath()%>/pm/common/images/mobile/logo.png"/> 
    	</section>
        
        <section class="mt-1"> 
        
        	<table width="100%" cellspacing="0" cellpadding="0" class="index_menu" id="tableall">
            	<tr class="h285">
                	<td class="nav1"><a href="<%=request.getContextPath()%>/pm/mobile/m_preplaninfo/m_plan_list.jsp" ><img src="<%=request.getContextPath()%>/pm/common/images/mobile/ico_menu1_03.png" />保养计划</a></td>
                    <td class="nav2"><a  href="<%=request.getContextPath()%>/pm/mobile/m_inspectplan/m_inspectplan_list.jsp"><img src="<%=request.getContextPath()%>/pm/common/images/mobile/ico_menu2_03.png" />巡检计划</a></td>
                </tr>
                <tr class="h285">
                	<td class="nav3"><a  href="<%=request.getContextPath()%>/pm/mobile/m_repair/m_repair_list.jsp" ><img src="<%=request.getContextPath()%>/pm/common/images/mobile/ico_menu3_11.png" />维修计划</a></td>
                	<%-- <td class="nav4"><a  href="javascript:logout();" ><img src="<%=request.getContextPath()%>/pm/common/images/mobile/index_bk_03.png" />退出</a></td> --%>
                	<td class="nav3"><a  href="<%=request.getContextPath()%>/pm/mobile/m_moving_info/m_moving_list.jsp" ><img src="<%=request.getContextPath()%>/pm/common/images/mobile/ico_menu3_11.png" />信息列表</a></td>
                </tr>
                <tr class="h285">
                	<td class="nav1"><a href="<%=request.getContextPath()%>/pm/mobile/m_moving_info/m_moving_in.jsp" ><img src="<%=request.getContextPath()%>/pm/common/images/mobile/ico_menu1_03.png" />搬入申请</a></td>
                    <td class="nav2"><a  href="<%=request.getContextPath()%>/pm/mobile/m_moving_info/m_moving_out.jsp"><img src="<%=request.getContextPath()%>/pm/common/images/mobile/ico_menu2_03.png" />搬出申请</a></td>
                </tr>
			</table>
        	
        </section>
    
</div>
</div> 

</html>