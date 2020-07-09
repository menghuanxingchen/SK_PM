<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>巡检计划——手机端</title>
<%@ include file="/pm/common/jsp/common_mobile.jsp"%> 
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/mobile/m_inspectplan/js/m_inspectplan_list.js"></script>
<script type="text/x-jsrender" id="dataListTemplate">
<a class="table_area" href="javascript:goToMachDataFun('{{:ins_plan_id}}','{{:machine_species_id}}');" onclick=""><table class="list_table">
    <thead>
        <tr>
           <th class="w33">计划名</th>
		   <th class="w33">计划时间</th>
           <th class="w17">目标</th>
           <th class="w17">实际</th>
        </tr>
    </thead>
    <tbody>
         <tr>
         	<td class="w33">{{:ins_plan_nm}}</td>
		 	<td class="w33">{{:ins_plan_date}}</td>
         	<td class="w17">100%</td>
         	<td class="w17 orange">{{:ins_pm_rate}}%</td>
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
            <div class="top-name"><p>巡检计划列表</p></div>
        </header>
    <section class="mt-1"> 
        <div class="ps-lt" id="search_area">
        <!--  <div class="lt-dsb">
                <p class="title">计划名称</p><p><input class="list_input w470" type="text" id="ins_plan_nm" name="insPlanInfo.ins_plan_nm" dc=""/>&nbsp;</p>
            </div> -->
            <div class="lt-dsb">
                <p class="title">日期区间</p><p><input class="laydate-icon list_input w150" type="text" id="start" name="insPlanInfo.date_start" dc=""/>
                 &nbsp;~&nbsp;<input class="laydate-icon list_input w150" id="end" name="insPlanInfo.date_end" dc="" type="text"    /></p>
            </div>
            <div class="lt-dsb cl-bb">
				<!-- 计划状态-->
              <div id="pop1" style="display:none;">
                	<a onclick="Show_Hidden(pop1)"><div class="pop_close pop_bg"></div></a>
                	 <div class="pop_wrap" id="statepop">
                    </div>
                </div>                
                
                <!--设备类别-->
                <div id="pop2" style="display:none;">
                	<a onclick="Show_Hidden(pop2)"><div class="pop_close pop_bg"></div></a>
                	<div class="pop_wrap" id="maspeciespop">                        
                    </div>
                </div>  
            <a class="area" onclick="Show_Hidden(pop1)" id="statefram" value="">状态</a><a class="equipment" onclick="Show_Hidden(pop2)" id="maspeciesfram" value="">设备类别</a>
        </div>
        </div>
    </section>
    <section class="mt-2"> 
        <div class="ps-lt" id="dataListForm"></div>
    </section>
</div>
</html>