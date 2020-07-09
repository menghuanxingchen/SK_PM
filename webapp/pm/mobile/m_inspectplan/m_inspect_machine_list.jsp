<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>巡检设备列表——手机端</title>
<%@ include file="/pm/common/jsp/common_mobile.jsp"%> 
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/mobile/m_inspectplan/js/m_inspect_machine_list.js"></script>
<script type="text/x-jsrender" id="dataListTemplate">
<a class="table_area" href="javascript:goToMachDataFun('{{:plan_machine_id}}');" onclick=""><table class="list_table">
	<colgroup>
    	<col width="50%" />
        <col width="20%" />
		<col width="20%" />
    </colgroup>
    <thead>
        <tr>
           <th>设备名</th>
           <th>目标</th>
           <th>实际</th>
        </tr>
    </thead>
    <tbody>
         <tr>
         	<td>{{:machine_nm}}</td>
         	<td>100%</td>
         	<td class="orange">{{:ins_check_rate}}%</td>
         </tr>
    </tbody>
</table></a>
</script>
</head>
<body>
<div id="com_body">
    <div id="_centent">
        <header>
            <div class="rt-bk" id="backDiv">
                <i class="bk"></i>
            </div>
            <div class="top-name"><p>巡检设备列表</p></div>
        </header>
    <section class="mt-1"> 
        <div class="ps-lt" id="search_area">
         <div class="lt-dsb">
                <p class="title">计划名称</p><p><input class="list_input w470" type="text" id="ins_plan_nm" readonly/>&nbsp;</p>
            </div>
            <div class="lt-dsb">
                 <p class="title">计划日期</p><p><input class="list_input w470" type="text" id="ins_plan_date" readonly/>&nbsp;</p>
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
</html>