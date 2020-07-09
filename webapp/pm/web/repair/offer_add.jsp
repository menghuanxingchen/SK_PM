<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>报价新增</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/repair/js/offer_add.js"></script>
<script type="text/x-jsrender" id="supplierCodeGroupDownListTemplate">
	<select id="supplier_code" name="offerInfo.supplier_code" dc="true" class="w198 mr20 ml10">
		{{renderOptionFun offerSupplierGrouplist "code_value" "code_nm" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="transportSpecCodeGroupDownListTemplate">
	<select data-name="select1" >
		{{renderOptionFun transportSpecGrouplist "code_value" "code_nm" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="toolUseSpecCodeGroupDownListTemplate">
	<select data-name="select1" >
		{{renderOptionFun toolUseSpecGrouplist "code_value" "code_nm" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="personExpensesCodeGroupDownListTemplate">
	<select data-name="select1">
		{{renderOptionFun personExpensesGrouplist "code_value" "code_nm" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="expensesTypeGroupDownListTemplate">
	<select data-name="select1" dcrequired="请选择分类">
		{{renderOptionFun expensesTypeGrouplist "code_value" "code_nm" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="offerUnitGroupDownListTemplate" >
	<select data-name="select1">
		{{renderOptionFun offerUnitGrouplist "code_value" "code_nm" "" ""/}}
 	</select>
</script>
</head>
<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">维修信息</a>&nbsp;>&nbsp;维修单信息</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">报价新增</div>
                        <!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                                <td id="supplierCode">
                                </td>
                            </tr>
                          </table>
                        <table class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            	<td class="odd w10">维修单号</td>
                                <td>
                                	<input type="text" id="repair_order_num" readonly/>
                                </td>
                                <td class="odd w10">申请日期</td>
                                <td>
                                	<input id="create_time" type="text" readonly/>
                                </td>
                            </tr>
                             <tr>
                            	<td class="odd w10">维修内容</td>
                                <td colspan="5"><textarea rows="3" id="repair_detail" readonly></textarea></td>
                            </tr>
                         </table>
                         
<!--                         <div class="title_l mt18">人工费报价内容</div>  -->
                        <table class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0" id="personOfferTable" style="display:none">
                            <tr>
                             	<td class="odd">预估工时</td>
                                <td><input type="text" id="estimate_time" name="offerInfo.estimate_time" dc="true" dctype="数字" maxlength='9' onKeyUp="personTotalMeony();"/></td>
                            	<td class="odd">单价</td>
                                <td><input type="text" id="user_price_hide" value="******" readonly/><input type="text" id="user_price" name="offerInfo.user_price" dc="true" dctype="金额" maxlength='9' onKeyUp="personTotalMeony();" readonly/></td>
                                <td class="odd">备注</td>
                               <td><input type="text" name="offerInfo.remark" dc="true" maxlength='50'/></td>
                            </tr>
                             <tr>
                            	<td class="odd w10">一次性费用</td>
                                <td colspan="5"><input type="text" id="one_charge" name="offerInfo.one_charge" dc="true" dctype="金额" maxlength='9' onKeyUp="personTotalMeony();" /></td>
                            </tr>
                         </table>
                       
                          <div class="title_l">其他费用报价内容<a href="javascript:addItem();" class="btn1 f14 fb fr">添加行</a></div>
                          <table class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                            <tr class="odd">
                            	<td width="90px">分类</td>
                                <td>名称</td>
							    <td class="w180">规格</td>
                                <td class="w80">数量</td>
                                <td class="w80">单位</td>
                                <td>品牌</td>
                                <td class="w80">单价</td>
                                <td class="w80">合计</td>
                                <td>备注</td>
                                <td class="w47">操作</td>
                            </tr>
                            <tbody id="addRowsTable">
							</tbody>
                        </table>
        				<div class="fr f14 fb">费用：<span id="total" class="red"></span>元</div>
        				<div class="cb"></div>
                         <table class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                             <tr>
                            	<td class="odd w10">合计</td>
                                <td><input type="text" id="all_price" readonly/></td>
                            </tr>
                            <tr>
                            	<td class="odd w10">税金</td>
                                <td><input type="text" id="tax" name="offerInfo.tax" dc="true" dctype="金额" maxlength='9' onKeyUp="taxTotalMeony();"/></td>
                            </tr>
                             <tr>
                            	<td class="odd w10">总计</td>
                                <td><input type="text" id="tatal_all" name="offerInfo.tatal_all" dc="true" readonly/></td>
                            </tr>
                          </table>
                          <div class="cb"></div>
                        <div class="tex_c mt22 mb30"><a href="javascript:addOfferInfo();" class="btn1 f14 fb mr10">提交</a><a href="<%=request.getContextPath()%>/pm/web/repair/repair_order_list.jsp" class="btn1 f14 fb mr10">返回</a><a href="javascript:updateGoToRepair();" class="btn1 f14 fb mr10">直接维修</a></div>
                        <!-- 右侧 结束-->
    
        		</div>
        		</div>
          </div>
</body>
</html>
