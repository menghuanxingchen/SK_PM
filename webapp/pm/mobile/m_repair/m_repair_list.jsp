<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>维修单信息——手机端</title>
<%@ include file="/pm/common/jsp/common_mobile.jsp"%> 
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/mobile/m_repair/js/m_repair_list.js"></script>
<script type="text/x-jsrender" id="dataListTemplate">
<a class="table_area" href="javascript:goToMachDataFun('{{:id}}');" onclick=""><table class="list_table">
	<colgroup>
    	<col width="55%" />
        <col width="" />
    </colgroup>
    <thead>
        <tr>
		   <th style="width:4rem">申请日期</th>
           <th align="center">维修内容</th>
        </tr>
    </thead>
    <tbody>
		<tr>
         	<td style="width:4rem">{{:create_time}}</td>
		 	<td align="left">{{:repair_detail}}</td>
         </tr>
    </tbody>
</table></a>
</script>
<script type="text/javascript">
function getLoginType(){
	var commonData = parent.CsGlobalObj||parent.parent.CsGlobalObj;
	var loginType =commonData.loginType||"";
	return loginType;
}
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
		if(getLoginType()!='MB'&&getLoginType()!='PC'){
			 loadURL("haleyAction://back");
		}else{
			var page = "/pm/mobile/main/index_m.jsp";
			pageForward(page);
		}    
	            }


</script>
</head>
<body>
<div id="com_body">
    <div id="_centent">
        <header>
            <div class="rt-bk">
                <i class="bk" onclick="goBack();"></i>
            </div>
            <div class="top-name"><p>维修单列表</p></div>
        </header>
    <section class="mt-1"> 
        <div class="ps-lt" id="search_area">
         <div class="lt-dsb">
                <p class="title">维修内容</p><p><input class="list_input w200" id="repair_detail" type="text" name="repairOrderInfo.repair_detail" dc=""/>&nbsp;</p>
            </div>
            <div class="lt-dsb">
                <p class="title">申请日期</p><p><input class="laydate-icon list_input w150" type="text" id="start_dt" name="repairOrderInfo.start_dt" dc=""/>
                 &nbsp;~&nbsp;<input  class="laydate-icon list_input w150" id="end_dt" name="repairOrderInfo.end_dt" dc="" type="text"    /></p>
            </div>
            <div class="lt-dsb">
              <a href="javascript:goToSaveDataFun();" class="cancel_ad w630 ml43 fl">维修单新增</a>
            </div>
        </div>
    </section>
    <section class="mt-2"> 
        <div class="ps-lt" id="dataListForm"></div>
    </section>
</div>
</html>