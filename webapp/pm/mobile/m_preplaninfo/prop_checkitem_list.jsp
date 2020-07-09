<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>保养计划-检查项目列表——手机端</title>
<%@ include file="/pm/common/jsp/common_mobile.jsp"%> 
<script type="text/x-jsrender" id="dataListTemplate">
	<div class="lt-dsb">
			<input type="hidden"  name="prePlanPropCheckDetail[{{:#index}}].id"  value="{{:id}}" dc=""/>
			<input type="hidden"  name="prePlanPropCheckDetail[{{:#index}}].prop_plan_id"  value="{{:prop_plan_id}}" dc=""/>
		<input type="hidden"  name="prePlanPropCheckDetail[{{:#index}}].editType"  value="{{:editType}}" dc=""/>
         <input type="text" name="prePlanPropCheckDetail[{{:#index}}].check_detail" class="input_line w630"   value="{{:check_detail}}" dc="" maxlength="50"/>
    </div>
</script>
<script type="text/javascript">
	function loadURL(url) {
	                    var iFrame;
	                    iFrame = document.createElement("iframe");
	                    iFrame.setAttribute("src", url);
	                    iFrame.setAttribute("style", "display:none;");
	                    iFrame.setAttribute("height", "0px");
	                    iFrame.setAttribute("width", "0px");
	                    iFrame.setAttribute("frameborder", "0");
	                    document.body.appendChild(iFrame);
	                    // 发起请求后这个iFrame就没用了，所以把它从dom上移除掉
	                    iFrame.parentNode.removeChild(iFrame);
	                    iFrame = null;
	                }

	function goBack() {
	                loadURL("haleyAction://back");
	            }


</script>
</head>
<body>
<div id="com_body">
    <div id="_centent">
        <header>
            <div class="rt-bk">
                <i class="bk"></i>
            </div>
            <div class="top-name"><p>预见性检查内容</p></div>
        </header>
    
    <section class="mt-1"> 
        <div class="ps-lt" id="dataListForm">
            
        </div>
    </section>
    
    
    <div class="jg"></div>
    </div>
    
    <footer id="com_foot">
    	<input type="submit" name="submit" class="submit ml30" id="submitBtn" value="提交"/>
        <input type="submit" name="cancel" class="cancel ml30" id="closeBtn" value="返回"/>       
    </footer>


</div> 
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/mobile/m_preplaninfo/js/prop_checkitem_list.js"></script>
</html>