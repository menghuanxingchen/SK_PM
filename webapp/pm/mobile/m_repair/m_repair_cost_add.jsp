<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>维修单信息——手机端</title>
<%@ include file="/pm/common/jsp/common_mobile.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/mobile/m_repair/js/m_repair_cost_add.js"></script>
<script type="text/x-jsrender" id="expensesTypeGroupDownListTemplate">
	<select id="cost_type" data-name="select1" dcrequired="请选择分类">
		{{renderOptionFun expensesTypeGrouplist "code_value" "code_nm" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="transportSpecCodeGroupDownListTemplate">
	<select data-name="select1">
		{{renderOptionFun transportSpecGrouplist "code_value" "code_nm" "" ""/}}
 	</select>
</script>
</head>
<body>
<div id="com_body">
    <div id="_centent">
        <header>
            <div class="rt-bk">
                <i class="bk" onclick="goBackToAddResult();"></i>
            </div>
            <div class="top-name"><p>材料费录入</p></div>
        </header>
    
    <section class="mt-1"> 
        <div class="ps-lt">
            <div class="lt-dsb">
                <p class="title w100">分类</p><p id="costType"></p>
            </div>
            <div class="lt-dsb">
                <p class="title w100">名称</p><p><input type="text"  class="w470" name="repairCostDetailTemp.cost_detail" dc="" maxlength="50"/></p>
            </div>
            <div class="lt-dsb">
                <p class="title w100">规格</p><p id="hiddenSpec"><input type="text"  class="w470" name="repairCostDetailTemp.spec" dc="" maxlength="50"/></p><p id="spec"></p>
            </div>
            <div class="lt-dsb">
                <p class="title w100">数量</p><p><input type="text"  class="w470" id="amount" name="repairCostDetailTemp.amount" onKeyUp='totalMeony();' maxlength='9' dc="" /></p>
            </div>
            <div class="lt-dsb">
                <p class="title w100">单价</p><p><input type="text"  class="w470" id="unit_price"  name="repairCostDetailTemp.unit_price" onKeyUp='totalMeony();' maxlength='9' dc="" /></p>
            </div>
            <div class="lt-dsb">
                <p class="title w100">合计</p><p><input type="text"  class="w470" id="total_price" name="repairCostDetailTemp.total_price" dc="" readonly/></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">备注</p><p><input type="text"  class="w470" name="repairCostDetailTemp.remark" dc="" maxlength="50"/></p>
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