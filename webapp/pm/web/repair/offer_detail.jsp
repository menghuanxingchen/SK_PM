<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>报价详情</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/repair/js/offer_detail.js"></script>
<script type="text/x-jsrender" id="supplierCodeGroupDownListTemplate">
	<select id="supplier_code" class="w198 mr20 ml10">
		{{renderOptionFun offerSupplierGrouplist "code_value" "code_nm" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="offerDetailListTemplate">
{{for offerDetailList}}
	 <tr class="odd">
          <td class="w80">
			<select>
				{{renderOptionFun expensesTypeGrouplist "code_value" "code_nm" cost_type ""/}}
 			</select>
		  </td>
          <td><input type="text" value="{{:cost_detail}}"/></td>
		{{if cost_type==='1'}}
			<td class="w47"><input type="text" value="{{:spec_nm}}"/></td>
		{{else cost_type==='4'}}
			<td class="w47"><input type="text" value="{{:tool_use_spec_nm}}"/></td>
		{{else cost_type==='8'}}
			<td class="w47"><input type="text" value="{{:person_expenses_spec_nm}}"/></td>
		{{else}}
        	<td class="w47"><input type="text" value="{{:spec}}"/></td>
		{{/if}}
          <td class="w80"><input type="text" value="{{:amount}}"/></td>
          <td class="w47">
			<select>
				{{renderOptionFun offerUnitGrouplist "code_value" "code_nm" unit ""/}}
 			</select>
		  </td>
          <td><input type="text" value="{{:brand}}"/></td>
		{{if showMoney==='Y'}}
			<td class="w47"><input type="text" value="{{:unit_price}}"/></td>
		{{else}}
			<td class="w47"><input type="text" value="******"/></td>
		{{/if}}
          <td class="w80"><input id="total_price{{:#index}}" type="text" value="{{:total_price}}"/></td>
          <td><input type="text" value="{{:remark}}"/></td>
     </tr>
{{/for}}
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
                
                		<div class="title mb18">报价详情</div>
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
                        <div class="title_l mt18">人工费报价内容</div>
                        <table class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                             	<td class="odd w10">预估工时</td>
                                <td><input id="estimate_time" type="text" /></td>
                            	<td class="odd w10">单价</td>
                                <td><input type="text" id="user_price_hide" value="******" readonly/><input id="user_price" type="text" /></td>
                                <td class="odd w10">备注</td>
                                <td><input id="remark" type="text" /></td>
                            </tr>
                            <tr>
                            	<td class="odd w10">一次性费用</td>
                                <td colspan="5"><input type="text" id="one_charge"/></td>
                            </tr>
                         </table>
                       
                          <div class="title_l">其他费用报价内容</div>
                          <table class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                            <tr class="odd">
                            	<td class="w100p">分类</td>
                                <td>名称</td>
                                <td class="w180">规格</td>
                                <td class="w80">数量</td>
                                <td class="w80">单位</td>
                                <td>品牌</td>
                                <td class="w80">单价</td>
                                <td class="w80">合计</td>
                                <td>备注</td>
                            </tr>
                             <tbody id="offerDetailTbody">
                             </tbody>
                        </table>
        				<div class="fr f14 fb">费用：<span id="tatal" class="red"></span>元</div>
        				<div class="cb"></div>
                         <table class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                             <tr>
                            	<td class="odd w10">合计</td>
                                <td><input id="all_price" type="text" /></td>
                            </tr>
                            <tr>
                            	<td class="odd">税金</td>
                                <td><input id="tax" type="text"/></td>
                            </tr>
                             <tr>
                            	<td class="odd">总计</td>
                                <td><input id="tatal_all" type="text" /></td>
                            </tr>
                          </table>
                          <div class="cb"></div>
                        <div class="tex_c mt22 mb30"><a href="javascript:backBtn();" class="btn1 f14 fb mr10">返回</a></div>
                        <!-- 右侧 结束-->
    
        		</div>
        		</div>
          </div>
</body>
</html>
