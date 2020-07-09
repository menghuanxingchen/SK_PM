<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>维修单信息——手机端</title>
<%@ include file="/pm/common/jsp/common_mobile.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/mobile/m_repair/js/m_repair_order_add.js"></script>
<script type="text/x-jsrender" id="repairPlacedownListTemplate">
	<select id="repair_place" name="repairOrderInfo.repair_place" dc="true" dcrequired="请选择维修地点">
		{{renderOptionFun repairPlaceList "code_value" "code_nm2" "" ""/}}
 	</select>
</script>
</head>
<body>
<div id="com_body">
    <div id="_centent">
        <header>
            <div class="rt-bk">
                <i class="bk" onclick="goBack();"></i>
            </div>
            <div class="top-name"><p>维修单录入</p></div>
        </header>
    
    <section class="mt-1"> 
        <div class="ps-lt">
            <div class="lt-dsb">
                <p class="title w100">维修地点</p><p id="repairPlace"></p>
            </div>
           
            <div class="lt-dsb cl-bb">
                <p class="title w100">维修内容</p><p><input type="text"  class="w470" name="repairOrderInfo.repair_detail" dc="" /></p><!--<p><textarea rows="3" cols="5" type="text"  class="w470" name="repairOrderInfo.repair_detail" dc=""/></textarea> -->
                <input class="laydate-icon list_input w150" type="hidden" id="require_time" name="repairOrderInfo.require_time" dc=""/></p>
            </div>
        </div>
    </section>
    <div class="jg"></div>
    </div>
    <footer id="com_foot">
    	<input type="submit" name="cancel" id="cancelBtn" class="cancel ml30" value="取消"/>
        <input type="submit" name="submit" id="submitBtn" class="submit ml30" value="提交"/>       
    </footer>
</div> 
</body>
</html>