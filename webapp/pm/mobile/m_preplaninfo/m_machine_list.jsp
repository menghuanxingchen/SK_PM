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
<a class="table_area" href="javascript:goToCheckItemDataFun('{{:plan_machine_id}}','{{:machine_nm}}');" ><table class="list_table">
	<colgroup>
    	<col width="50%" />
        <col width="20%" />
		<col width="20%" />
    </colgroup>
    <thead>
        <tr>
           <th class="w33">设备名</th>
           <th class="w17">目标</th>
           <th class="w17">实际</th>
        </tr>
    </thead>
    <tbody>
         <tr>
         	<td class="w33">{{:machine_nm}}</td>
         	<td class="w33">100%</td>
         	<td class="w17 orange">{{:m_pm_rate}}%</td>
         </tr>
    </tbody>
</table></a>
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
                <i class="bk" onclick="goBack()" ></i>
            </div>
            <div class="top-name"><p>检查设备列表</p></div>
        </header>
    <section class="mt-1"> 
        <div class="ps-lt" id="search_area">
        	<div class="lt-dsb">
                <p class="title">计划名称</p><p><input class="list_input w470" type="text" id="planNm" disabled/></p>
            </div>
        	<div class="lt-dsb">
                <p class="title">日期</p><p><input class="list_input w470" type="text"  id="date" disabled/></p>
            </div>
            
            <div class="lt-dsb cl-bb">
				<!--设备类别-->
              <div id="pop1" style="display:none;">
                	<a onclick="Show_Hidden(pop1)"><div class="pop_close pop_bg"></div></a>
                	 <div class="pop_wrap" id="maAreapop">
                    </div>
                </div>                
                
                <!-- 计划状态-->
                <div id="pop2" style="display: none;">
                	<a onclick="Show_Hidden(pop2)"><div class="pop_close pop_bg"></div></a>
                	<div class="pop_wrap" id="matypepop">
                    </div>
                </div>  
           <a class="area" onclick="Show_Hidden(pop1)" id="maAreafram">区域</a><a class="equipment" onclick="Show_Hidden(pop2)" id="matypefram">设备类型</a>
        </div>
        </div>
    </section>
    <section class="mt-2"> 
        <div class="ps-lt" id="dataListForm"></div>
    </section>
</div>
</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/mobile/m_preplaninfo/js/m_machine_list.js"></script>
</html>