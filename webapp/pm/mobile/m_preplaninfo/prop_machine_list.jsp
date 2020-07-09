<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>保养计划-设备列表——手机端</title>
<%@ include file="/pm/common/jsp/common_mobile.jsp"%> 
<script type="text/x-jsrender" id="dataListTemplate">
	<div class="lt-dsb">
			<input type="hidden"  name="prePlanPropMachineInfo[{{:#index}}].prop_plan_id"  value="{{:prop_plan_id}}" dc=""/>
			<input type="hidden"  name="prePlanPropMachineInfo[{{:#index}}].pre_plan_id"  value="{{:pre_plan_id}}" dc=""/>
			<input type="hidden"  name="prePlanPropMachineInfo[{{:#index}}].editType"  value="{{:editType}}" dc=""/>
         <input type="text" id="machineNm{{:#index}}" name="prePlanPropMachineInfo[{{:#index}}].prop_machine_id" class="input_line w600 fl" value="{{:prop_machine_id}}" placeholder="请输入预见性设备名称" dc="" maxlength="50"/><a href="javascript:goToCheckItemDataFun('{{:#index}}');" class="fr"><i class="go"></i></a>
     </div>
</script>
</head>
<body>
<div id="com_body">
    <div id="_centent">
        <header>
            <div class="rt-bk">
                <i class="bk"></i>
            </div>
            <div class="top-name"><p>预见性设备列表</p></div>
        </header>
    
    <section class="mt-1"> 
        <div class="ps-lt" id="dataListForm">
     
        </div>
    </section>
    
    
    <div class="jg"></div>
    </div>
    
    <footer id="com_foot">
    	<input type="submit" id="submit" class="submit ml30" value="提交"/>
        <input type="submit" id="cancel" class="cancel ml30" value="返回"/>       
    </footer>
</div> 
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/mobile/m_preplaninfo/js/prop_machine_list.js"></script>
</html>