$(function(){
	menu('menu31');
	initPage();
});

/**
 * 初始化页面
 * */
var id = getRequestParameterValue("id");
var showMoneyYn = "";
function initPage(){
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/queryDownList5.json"),
		dataType:"json",
		data:"",
		success:function(result){
			$("#supplierCode").html($("#supplierCodeGroupDownListTemplate").render(result));
			$("#supplier_code option[value='1']").attr("selected","selected");
			$("#personOfferTable tr").find("input").attr("readonly",true);
			$("#tax").val("0.00");
			getRepairDetailNm();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});

}

function getRepairDetailNm(){
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/getRepairDetailNm.json"),
		dataType:"json",
		data:{"repairOrderInfo.id":id},
		success:function(result){
			$("#repair_order_num").val(result.repairOrderEntity.repair_order_num);
			$("#create_time").val(result.repairOrderEntity.create_time);
			$("#repair_detail").val(result.repairDetailNm);
			$("#user_price").val(Class.getConstant('USER_PRICE_WITH_TAX'));
			showMoneyYn = result.showMoney;
			if(result.showMoney == 'Y'){
				$("#user_price_hide").hide();
				$("#user_price").show();
			}else{
				$("#user_price_hide").show();
				$("#user_price").hide();
			}
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

function getTransportSpec(cnt,costTypeValue){
	$("#amount" + cnt).val('0');
	$("#unit_price" + cnt).val('0');
	$("#total_price" + cnt).val('0');
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/queryDownList5.json"),
		dataType:"json",
		data:"",
		success:function(result){
			if(costTypeValue == Class.getConstant('TRANS_FLAG')){
				$("#spec" + cnt).html($("#transportSpecCodeGroupDownListTemplate").render(result));
				$("#spec" + cnt).find("[data-name='select1']").attr("name", "offerDetailList["+cnt+"].spec");
				$("#spec" + cnt).find("[data-name='select1']").attr("dc",true);
				$("#spec" + cnt).show();
				$("#tool_use_spec" + cnt).hide();
				$("#person_expenses_spec" + cnt).hide();
			}else if(costTypeValue == Class.getConstant('TOOL_USE_FLAG')){
				$("#tool_use_spec" + cnt).html($("#toolUseSpecCodeGroupDownListTemplate").render(result));
				$("#tool_use_spec" + cnt).find("[data-name='select1']").attr("name", "offerDetailList["+cnt+"].spec");
				$("#tool_use_spec" + cnt).find("[data-name='select1']").attr("dc",true);
				$("#tool_use_spec" + cnt).show();
				$("#spec" + cnt).hide();
				$("#person_expenses_spec" + cnt).hide();
			}else if(costTypeValue == Class.getConstant('PERSON_EXPENSES')){
				$("#person_expenses_spec" + cnt).html($("#personExpensesCodeGroupDownListTemplate").render(result));
				$("#person_expenses_spec" + cnt).find("[data-name='select1']").attr("name", "offerDetailList["+cnt+"].spec");
				$("#person_expenses_spec" + cnt).find("[data-name='select1']").attr("dc",true);
				$("#person_expenses_spec" + cnt).show();
				$("#tool_use_spec" + cnt).hide();
				$("#spec" + cnt).hide();
			}
			$("#hiddenSpec" + cnt).find("input").removeAttr("dc");
			$("#hiddenSpec" + cnt).hide();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}
/**
 * 绑定事件属性
 * */
function addOfferInfo(){
	var checkfalg =	checkFormValue('aaa');
	if(checkfalg){
		var postData = collectData("aaa");
		postData["repairOrderInfo.id"]=id;
		var message = "确认提交？";
		layer.confirm(
				message,
				function(index){
					layer.load('loading');//添加进度条
					$.ajax({
						url:getRequestUrl("/RepairOrderInfoController/addOfferInfo.json"),
						dataType:"json",
						data:postData,
						success:function(result){
							layer.close(index);
							layer.closeAll('loading');
							if(result.opflag){
								lalert('save','success', function(){
									  var page = "/pm/web/repair/repair_order_list.jsp";
										pageForward(page);
								  });
							}else{
								lalert(result.opmessage,'warn', function(){
									  var page = "/pm/web/repair/repair_order_list.jsp";
										pageForward(page);
								  });
							}
						},
						error:function(error){
							layer.close(index);
							layer.closeAll('loading');
							lalert('网络原因操作失败！','error');
						}
					});
				},
				function(index){
					layer.close(index);
					return;
				}
		)
	}
}

/**
 * 不报价直接维修
 * */
function updateGoToRepair(){
	var message = "确认不报价，直接维修？";
	layer.confirm(
			message,
			function(index){
				layer.load('loading');//添加进度条
				$.ajax({
					url:getRequestUrl("/RepairOrderInfoController/updateGoToRepair.json"),
					dataType:"json",
					data:{"repairOrderInfo.id":id},
					success:function(result){
						layer.close(index);
						layer.closeAll('loading');
						lalert('save','success', function(){
							  var page = "/pm/web/repair/repair_order_list.jsp";
								pageForward(page);
						  });
					},
					error:function(error){
						layer.close(index);
						layer.closeAll('loading');
						lalert('网络原因操作失败！','error');
					}
				});
			},
			function(index){
				layer.close(index);
				return;
			}
	)
}

/**
 * 添加选项调用方法
 */
var count=0;
function addItem(){
	var str =   "<tr id='addr"+count+"'> "
				+ "  <td id='costType"+ count + "'></td> "
				+ "  <td><input type='text' name='offerDetailList["+count+"].cost_detail' dc='true' maxlength='50'/></td>  "
				+ "  <td id='spec"+count+"'></td>  "
				+ "  <td id='tool_use_spec"+count+"'></td>  "
				+ "  <td id='person_expenses_spec"+count+"'></td>  "
				+ "  <td id='hiddenSpec"+count+"'><input type='text' name='offerDetailList["+count+"].spec' dc='true'/></td>  "
				+ "  <td><input type='text' id='amount"+ count + "' name='offerDetailList["+count+"].amount' dc='true' value='0' dctype='数字' onKeyUp='totalMeony("+count+");' maxlength='9' dcrequired='请输入数量'/></td>  "
				+ "  <td id='offerUnit"+ count + "'></td>  "
				+ "  <td><input type='text' name='offerDetailList["+count+"].brand' dc='true' maxlength='50'/></td>  "
				+ "  <td><input type='text' id='unit_price_hide"+ count + "' value='******' readonly/><input type='text' id='unit_price"+ count + "' name='offerDetailList["+count+"].unit_price' value='0' dc='true' dctype='金额' onKeyUp='totalMeony("+count+");' maxlength='9' dcrequired='请输入单价' readonly/></td>  "
				+ "  <td><input type='text' id='total_price"+ count + "' name='offerDetailList["+count+"].total_price' value='0' dc='true' maxlength='50' readonly/></td>  "
				+ "  <td><input type='text' name='offerDetailList["+count+"].remark' dc='true' maxlength='50'/></td>  "
				+ "  <td><a href='javascript:deleteItem("+count+");' class='btn3' >删除</a></td>   "
				+ "</tr> ";
	$(str).appendTo("#addRowsTable")
	bindValidateEvent('addRowsTable');
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/queryDownList6.json"),
		dataType:"json",
		data:"",
		success : function(result) {
			if(result.opflag){
				$("#costType" + count + "").html(
						$("#expensesTypeGroupDownListTemplate").render(result)
				);
				$("#offerUnit" + count + "").html(
						$("#offerUnitGroupDownListTemplate").render(result)
				);
				$("#costType" + count).find("[data-name='select1']").attr("name", "offerDetailList["+count+"].cost_type");
				$("#costType" + count).find("[data-name='select1']").attr("dc",true);
				$("#offerUnit" + count).find("[data-name='select1']").attr("name", "offerDetailList["+count+"].unit");
				$("#offerUnit" + count).find("[data-name='select1']").attr("dc",true);
				$("#spec" + count).hide();
				$("#tool_use_spec" + count).hide();
				$("#person_expenses_spec" + count).hide();
				$("#unit_price_hide" + count).hide();
				$("#costType" + count).change(function(){
					var cnt = $(this).attr("id").substring(8);
					var costTypeValue = $(this).find("[data-name='select1']").val();
					//合同固定运费  或者 工具使用
					if(costTypeValue == Class.getConstant('TRANS_FLAG') || costTypeValue == Class.getConstant('TOOL_USE_FLAG') || costTypeValue == Class.getConstant('PERSON_EXPENSES')){
						getTransportSpec(cnt,costTypeValue);
					}else{
						$("#amount" + cnt).val('0');
						$("#unit_price" + cnt).val('0');
						$("#total_price" + cnt).val('0');
						$("#unit_price" + cnt).attr("readonly","readonly");
						$("#unit_price_hide" + cnt).hide();
						$("#unit_price" + cnt).show();
//						if(costTypeValue == Class.getConstant('MEAL_FLAG')){
//							//餐补（含税）
//							$("#unit_price" + cnt).val(Class.getConstant('MEAL_PRICE_WITH_TAX'));
//							if(showMoneyYn == 'Y'){
//								$("#unit_price_hide" + cnt).hide();
//								$("#unit_price" + cnt).show();
//							}else{
//								$("#unit_price_hide" + cnt).show();
//								$("#unit_price" + cnt).hide();
//							}
//						}else if(costTypeValue == Class.getConstant('OVERTIME_FLAG') || costTypeValue == Class.getConstant('PERSON_EXPENSES')){
//							//加班（含税）
//							$("#unit_price" + cnt).val(Class.getConstant('USER_PRICE_WITH_TAX'));
//							if(showMoneyYn == 'Y'){
//								$("#unit_price_hide" + cnt).hide();
//								$("#unit_price" + cnt).show();
//							}else{
//								$("#unit_price_hide" + cnt).show();
//								$("#unit_price" + cnt).hide();
//							}
//						}else{
//							$("#unit_price" + cnt).attr("readonly",false);
//							$("#unit_price_hide" + cnt).hide();
//							$("#unit_price" + cnt).show();
//						}
						//维修报价时材料费分类是 材料费 或者 一单一议用车运费的情况
						if(costTypeValue == Class.getConstant('MATERIAL_FLAG') || costTypeValue == Class.getConstant('YDYY_FLAG')){
							$("#unit_price" + cnt).attr("readonly",false);
							$("#unit_price_hide" + cnt).hide();
							$("#unit_price" + cnt).show();
						}
						$("#spec" + cnt).find("[data-name='select1']").removeAttr("dc");
						$("#tool_use_spec" + cnt).find("[data-name='select1']").removeAttr("dc");
						$("#hiddenSpec" + cnt).find("input").attr("dc",true);
						$("#hiddenSpec" + cnt).find("input").val("");
						$("#hiddenSpec" + cnt).show();
						$("#spec" + cnt).hide();
						$("#person_expenses_spec" + cnt).hide();
						$("#tool_use_spec" + cnt).hide();
					}
					totalMeony(cnt);
				})
				$("#spec" + count).change(function(){
					var cnt = $(this).attr("id").substring(4);
					var specValue = $(this).find("[data-name='select1']").val();
					$("#amount" + cnt).val('0');
					$("#total_price" + cnt).val('0');
					if(specValue == Class.getConstant('CAR_TG')){
						$("#unit_price" + cnt).val(Class.getConstant('CAR_TG_FLAG'));
					}else if(specValue == Class.getConstant('CAR_SL')){
						$("#unit_price" + cnt).val(Class.getConstant('CAR_SL_FLAG'));
					}else if(specValue == Class.getConstant('HC_TG')){
						$("#unit_price" + cnt).val(Class.getConstant('HC_TG_FLAG'));
					}else if(specValue == Class.getConstant('HC_SL')){
						$("#unit_price" + cnt).val(Class.getConstant('HC_SL_FLAG'));
					}else if(specValue == Class.getConstant('MT_SY')){
						$("#unit_price" + cnt).val(Class.getConstant('MT_SY_FLAG'));
					}else if(specValue == Class.getConstant('GM_CY')){
						$("#unit_price" + cnt).val(Class.getConstant('GM_CY_FLAG'));
					}else if(specValue == Class.getConstant('MB_DLDT')){
						$("#unit_price" + cnt).val(Class.getConstant('MB_DLDT_FLAG'));
					}
					if(showMoneyYn == 'Y'){
						$("#unit_price_hide" + cnt).hide();
						$("#unit_price" + cnt).show();
					}else{
						$("#unit_price_hide" + cnt).show();
						$("#unit_price" + cnt).hide();
					}
				})
				$("#tool_use_spec" + count).change(function(){
					var cnt = $(this).attr("id").substring(13);
					var specValue = $(this).find("[data-name='select1']").val();
					$("#amount" + cnt).val('0');
					$("#total_price" + cnt).val('0');
					if(specValue == Class.getConstant('DHJ1')){
						$("#unit_price" + cnt).val(Class.getConstant('DHJ_IN_4HOUR'));
					}else if(specValue == Class.getConstant('DHJ2')){
						$("#unit_price" + cnt).val(Class.getConstant('DHJ_OVER_4HOUR'));
					}else if(specValue == Class.getConstant('QHJ1')){
						$("#unit_price" + cnt).val(Class.getConstant('QHJ_IN_4HOUR'));
					}else if(specValue == Class.getConstant('QHJ2')){
						$("#unit_price" + cnt).val(Class.getConstant('QHJ_OVER_4HOUR'));
					}else if(specValue == Class.getConstant('DG1')){
						$("#unit_price" + cnt).val(Class.getConstant('DG_IN_4HOUR'));
					}else if(specValue == Class.getConstant('DG2')){
						$("#unit_price" + cnt).val(Class.getConstant('DG_OVER_4HOUR'));
					}else if(specValue == Class.getConstant('DC1')){
						$("#unit_price" + cnt).val(Class.getConstant('DC_IN_4HOUR'));
					}else if(specValue == Class.getConstant('DC2')){
						$("#unit_price" + cnt).val(Class.getConstant('DC_OVER_4HOUR'));
					}else if(specValue == Class.getConstant('DZ1')){
						$("#unit_price" + cnt).val(Class.getConstant('DZ_IN_4HOUR'));
					}else if(specValue == Class.getConstant('DZ2')){
						$("#unit_price" + cnt).val(Class.getConstant('DZ_OVER_4HOUR'));
					}else if(specValue == Class.getConstant('JMJ1')){
						$("#unit_price" + cnt).val(Class.getConstant('JMJ_IN_4HOUR'));
					}else if(specValue == Class.getConstant('JMJ2')){
						$("#unit_price" + cnt).val(Class.getConstant('JMJ_OVER_4HOUR'));
					}else if(specValue == Class.getConstant('FG1')){
						$("#unit_price" + cnt).val(Class.getConstant('FG_IN_4HOUR'));
					}else if(specValue == Class.getConstant('FG2')){
						$("#unit_price" + cnt).val(Class.getConstant('FG_OVER_4HOUR'));
					}else if(specValue == Class.getConstant('WCJ1')){
						$("#unit_price" + cnt).val(Class.getConstant('WCJ_IN_4HOUR'));
					}else if(specValue == Class.getConstant('WCJ2')){
						$("#unit_price" + cnt).val(Class.getConstant('WCJ_OVER_4HOUR'));
					}else if(specValue == Class.getConstant('YQ')){
						$("#unit_price" + cnt).val(Class.getConstant('YQ_IN_4HOUR'));
					}else if(specValue == Class.getConstant('YQue')){
						$("#unit_price" + cnt).val(Class.getConstant('YQue_IN_4HOUR'));
					}
					if(showMoneyYn == 'Y'){
						$("#unit_price_hide" + cnt).hide();
						$("#unit_price" + cnt).show();
					}else{
						$("#unit_price_hide" + cnt).show();
						$("#unit_price" + cnt).hide();
					}
				})
				
				$("#person_expenses_spec" + count).change(function(){
					var cnt = $(this).attr("id").substring(20);
					var specValue = $(this).find("[data-name='select1']").val();
					$("#amount" + cnt).val('0');
					$("#total_price" + cnt).val('0');
					if(specValue == Class.getConstant('PERSON_HOUR_EXPENSES') || specValue == Class.getConstant('OVERTIME_HOUR_EXPENSES')){
						$("#unit_price" + cnt).val(Class.getConstant('USER_PRICE_WITH_TAX'));
					}else if(specValue == Class.getConstant('MEAL_SUPPLY_EXPENSES')){
						$("#unit_price" + cnt).val(Class.getConstant('MEAL_PRICE_WITH_TAX'));
					}
					if(showMoneyYn == 'Y'){
						$("#unit_price_hide" + cnt).hide();
						$("#unit_price" + cnt).show();
					}else{
						$("#unit_price_hide" + cnt).show();
						$("#unit_price" + cnt).hide();
					}
				})
				count++;
			}
			bindValidateEvent("aaa");
		},
		error : function(error) {
			lalert('网络原因操作失败！','error');
		}
	});
}


/**
 * 删除选项调用方法
 */
function deleteItem(str){
	$("#addr"+str).remove();
	var cnt = '0';
	if($("#addRowsTable tr").length > 0){
		$("#addRowsTable tr").each(function(i){
			cnt = $(this).attr("id").substring(4);
		})
		totalMeony(cnt);
	}else{
		var person_price = parseFloat((parseFloat($("#user_price").val().trim())*parseFloat($("#estimate_time").val().trim())).toFixed(2));
		var tax = parseFloat(parseFloat($("#tax").val().trim()).toFixed(2));
		var one_charge = parseFloat(parseFloat($("#one_charge").val().trim()).toFixed(2));
		if(person_price.toString() == "NaN"){
			person_price = 0;
		}
		if(tax == "" || tax.toString() == "NaN"){
			tax = 0;
		}
		if(one_charge == "" || one_charge.toString() == "NaN"){
			one_charge = 0;
		}
		$("#total").html('0');
		$("#all_price").val((person_price+one_charge).toFixed(2));
		$("#tatal_all").val((person_price+one_charge+tax).toFixed(2));
	}
}

/**
 * 费用列表变化时 合计计算
 */
function totalMeony(count){
	var amount = parseFloat($("#amount" + count).val().trim());
	var unit_price = parseFloat($("#unit_price" + count).val().trim());
	var total_price = (amount*unit_price).toFixed(2);
	var person_price = parseFloat((parseFloat($("#user_price").val().trim())*parseFloat($("#estimate_time").val().trim())).toFixed(2));
	var tax = parseFloat(parseFloat($("#tax").val().trim()).toFixed(2));
	var one_charge = parseFloat(parseFloat($("#one_charge").val().trim()).toFixed(2));
	
	var all = 0;
	var allmaterial = 0;
	if(total_price.toString() == "NaN"){
		$("#total_price" + count).val(0);
	}else{
		$("#total_price" + count).val(total_price);
	}
	$("#addRowsTable tr").each(function(i){
		var cnt = $(this).attr("id").substring(4);
		all = all + parseFloat(parseFloat($(this).find("input[id='total_price"+cnt+"']").val()).toFixed(2));
		if($("#costType"+cnt).find("select").val() == Class.getConstant('OFFER_EXPENSES_TYPE_MATERIAL')){
			allmaterial = allmaterial + parseFloat(parseFloat($(this).find("input[id='total_price"+cnt+"']").val()).toFixed(2));
		}
	})
	$("#total").html(all.toFixed(2));
	if(person_price.toString() == "NaN"){
		person_price = 0;
	}
	if(tax == "" || tax.toString() == "NaN"){
		tax = 0;
	}
	if(one_charge == "" || one_charge.toString() == "NaN"){
		one_charge = 0;
	}
	$("#all_price").val((person_price+one_charge+all).toFixed(2));
	$("#tax").val((allmaterial*0.13).toFixed(2));
	$("#tatal_all").val((person_price+one_charge+all+allmaterial*0.13).toFixed(2));
}

/**
 * 人工费或工时变化时 合计计算
 */
function personTotalMeony(){
	var total = parseFloat(parseFloat($("#total").text()).toFixed(2));
	var person_price = parseFloat(parseFloat($("#user_price").val().trim())*parseFloat($("#estimate_time").val().trim()).toFixed(2));
	var tax = parseFloat(parseFloat($("#tax").val().trim()).toFixed(2));
	var one_charge = parseFloat(parseFloat($("#one_charge").val().trim()).toFixed(2));
	if(total == "" || total.toString() == "NaN"){
		total = 0;
	}
	if(person_price.toString() == "NaN"){
		person_price = 0;
	}
	if(tax == "" || tax.toString() == "NaN"){
		tax = 0;
	}
	if(one_charge == "" || one_charge.toString() == "NaN"){
		one_charge = 0;
	}
	$("#all_price").val((person_price+one_charge+total).toFixed(2));
	$("#tatal_all").val((person_price+one_charge+total+tax).toFixed(2));
}

/**
 * 税金变化时 合计计算
 */
function taxTotalMeony(){
	var all_price = parseFloat(parseFloat($("#all_price").val()).toFixed(2));
	var tax = parseFloat(parseFloat($("#tax").val().trim()).toFixed(2));
	if(all_price == "" || all_price.toString() == "NaN"){
		all_price = 0;
	}
	if(tax == "" || tax.toString() == "NaN"){
		tax = 0;
	}
	$("#tatal_all").val((all_price+tax).toFixed(2));
}