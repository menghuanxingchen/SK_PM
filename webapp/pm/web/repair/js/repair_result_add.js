$(function(){
	menu('menu31');
	initPage();
});

/**
 * 初始化页面
 * */
var id = getRequestParameterValue("id");
var expanseYn = "";
var showMoneyYn = "";
function initPage(){
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/getRepairDetailNm.json"),
		dataType:"json",
		data:{"repairOrderInfo.id":id},
		success:function(result){
			$("#repair_order_num").val(result.repairOrderEntity.repair_order_num);
			$("#create_time").val(result.repairOrderEntity.create_time);
			$("#repair_detail").val(result.repairDetailNm);
			$("#require_date").val(result.requireTime);
			$("#tax").val(result.offerTax);
			$("#total_all").val(result.totalAll);
			$("#personMoney tr").find("input").attr("readonly",true);
			expanseYn = result.expanseYn;
			if(expanseYn == 'Y'){
				$("#repair_type").val("有费用");
				$("#user_price").val(Class.getConstant('USER_PRICE_WITH_TAX'));
			}else{
				$("#repair_type").val("无费用");
				$("#addRows").attr("href","javascript:return false;");
			}
			showMoneyYn = result.showMoney;
			if(result.showMoney == 'Y'){
				$("#user_price_hide").hide();
				$("#user_price").show();
			}else{
				$("#user_price_hide").show();
				$("#user_price").hide();
			}
			searchOfferDetail();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * 税金变化时 合计计算
 */
function taxTotalMeony(){
	var total = parseFloat(parseFloat($("#total").text()).toFixed(2));
	var tax = parseFloat(parseFloat($("#tax").val().trim()).toFixed(2));
	if(total == "" || total.toString() == "NaN"){
		total = 0;
	}
	if(tax == "" || tax.toString() == "NaN"){
		tax = 0;
	}
	$("#total_all").val((total+tax).toFixed(2));
}

/**
 * 绑定事件属性
 * */
function bindevent(){
	laydate({
		  elem: '#reapir_result_table .laydate-icon', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		  event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	});
}

/**
 * 维修结果新增
 * */
function addRepairResultInfo(){
	var checkfalg =	checkFormValue('aaa');
	if(checkfalg){
		var postData = collectData("aaa");
		postData["repairOrderInfo.id"]=id;
		postData["repairResultInfo.tatal_all"]=$("#total_all").val();
		postData["repairResultInfo.tax"]=$("#tax").val();
		var message = "确认提交？";
		layer.confirm(
				message,
				function(index){
					layer.load('loading');//添加进度条
					$.ajax({
						url:getRequestUrl("/RepairOrderInfoController/addRepairResultInfo.json"),
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
 * 人工费或工时变化时 合计计算
 */
function personTotalMeony(){
	var total = parseFloat(parseFloat($("#total").text()).toFixed(2));
	var person_price = parseFloat(parseFloat($("#user_price").val().trim())*parseFloat($("#task_time").val().trim()).toFixed(2));
	var one_charge = parseFloat(parseFloat($("#one_charge").val().trim()).toFixed(2));
	var tax = parseFloat(parseFloat($("#tax").val().trim()).toFixed(2));
	if(total == "" || total.toString() == "NaN"){
		total = 0;
	}
	if(person_price.toString() == "NaN"){
		person_price = 0;
	}
	if(one_charge == "" || one_charge.toString() == "NaN"){
		one_charge = 0;
	}
	if(tax == "" || tax.toString() == "NaN"){
		tax = 0;
	}
	$("#all_price").val((person_price+one_charge+total+tax).toFixed(2));
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
				$("#spec" + cnt).find("[data-name='select1']").attr("name", "repairCostDetailList["+cnt+"].spec");
				$("#spec" + cnt).find("[data-name='select1']").attr("dc",true);
				$("#spec" + cnt).show();
				$("#tool_use_spec" + cnt).hide();
				$("#person_expenses_spec" + cnt).hide();
			}else if(costTypeValue == Class.getConstant('TOOL_USE_FLAG')){
				$("#tool_use_spec" + cnt).html($("#toolUseSpecCodeGroupDownListTemplate").render(result));
				$("#tool_use_spec" + cnt).find("[data-name='select1']").attr("name", "repairCostDetailList["+cnt+"].spec");
				$("#tool_use_spec" + cnt).find("[data-name='select1']").attr("dc",true);
				$("#tool_use_spec" + cnt).show();
				$("#spec" + cnt).hide();
				$("#person_expenses_spec" + cnt).hide();
			}else if(costTypeValue == Class.getConstant('PERSON_EXPENSES')){
				$("#person_expenses_spec" + cnt).html($("#personExpensesCodeGroupDownListTemplate").render(result));
				$("#person_expenses_spec" + cnt).find("[data-name='select1']").attr("name", "repairCostDetailList["+cnt+"].spec");
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
 * 添加费用调用方法
 */
var count=0;
function addItem(){
	count = $("#addRowsTable tr").length;
	var str =   "<tr id='addr"+count+"'> "
				+ "  <td id='costType"+ count + "'></td> "
				+ "  <td><input type='text' name='repairCostDetailList["+count+"].cost_detail' dc='true' maxlength='50'/></td>  "
				+ "  <td id='spec"+count+"'></td>  "
				+ "  <td id='tool_use_spec"+count+"'></td>  "
				+ "  <td id='person_expenses_spec"+count+"'></td>  "
				+ "  <td id='hiddenSpec"+count+"'><input type='text' name='repairCostDetailList["+count+"].spec' dc='true'/></td>  "
				+ "  <td><input type='text' id='amount"+ count + "' name='repairCostDetailList["+count+"].amount' dc='true' value='0' onKeyUp='totalMeony("+count+");' dctype='数字' maxlength='9' dcrequired='请输入数量'/></td>  "
				+ "  <td><input type='text' id='unit_price_hide"+ count + "' value='******' readonly/><input type='text' id='unit_price"+ count + "' name='repairCostDetailList["+count+"].unit_price' dc='true' value='0' onKeyUp='totalMeony("+count+");' dctype='金额' maxlength='9' dcrequired='请输入单价' readonly/></td>  "
				+ "  <td><input type='text' id='total_price"+ count + "' name='repairCostDetailList["+count+"].total_price' value='0' dc='true' maxlength='50' readonly/></td>  "
				+ "  <td><input type='text' name='repairCostDetailList["+count+"].remark' dc='true' maxlength='50'/></td>  "
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
				$("#costType" + count).find("[data-name='select1']").attr("name", "repairCostDetailList["+count+"].cost_type");
				$("#costType" + count).find("[data-name='select1']").attr("dc",true);
				$("#spec" + count).hide();
				$("#tool_use_spec" + count).hide();
				$("#person_expenses_spec" + count).hide();
				$("#unit_price_hide" + count).hide();
				$("#costType" + count).change(function(){
					var cnt = $(this).attr("id").substring(8);
					var costTypeValue = $(this).find("[data-name='select1']").val();
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
//						}else if(costTypeValue == Class.getConstant('OVERTIME_FLAG')){
//							//加班（含税）
//							$("#unit_price" + cnt).val(Class.getConstant('USER_PRICE_WITH_TAX') || costTypeValue == Class.getConstant('PERSON_EXPENSES'));
//							if(showMoneyYn == 'Y'){
//								$("#unit_price_hide" + cnt).hide();
//								$("#unit_price" + cnt).show();
//							}else{
//								$("#unit_price_hide" + cnt).show();
//								$("#unit_price" + cnt).hide();
//							}
//						}else{
//							$("#unit_price" + cnt).attr("readonly",false);
//						}
						//维修报价时材料费分类是 材料费 或者 一单一议用车运费的情况
						if(costTypeValue == Class.getConstant('MATERIAL_FLAG') || costTypeValue == Class.getConstant('YDYY_FLAG')){
							$("#unit_price" + cnt).attr("readonly",false);
						}
						$("#spec" + cnt).find("[data-name='select1']").removeAttr("dc");
						$("#tool_use_spec" + cnt).find("[data-name='select1']").removeAttr("dc");
						$("#hiddenSpec" + cnt).find("input").attr("dc",true);
						$("#hiddenSpec" + cnt).find("input").val("");
						$("#hiddenSpec" + cnt).show();
						$("#spec" + cnt).hide();
						$("#tool_use_spec" + cnt).hide();
						$("#person_expenses_spec" + cnt).hide();
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
		},
		error : function(error) {
			lalert('网络原因操作失败！','error');
		}
	});
	bindValidateEvent('aaa');
}


/**
 * 删除费用调用方法
 */
function deleteItem(str){
	layer.confirm(
			"是否确定删除？",
			function(index){
				layer.close(index);
				$("#addr"+str).remove();
				var all = 0;
				$("#addRowsTable tr").each(function(i){
					var cnt = $(this).attr("id").substring(4);
					all = all + parseFloat(parseFloat($(this).find("input[id='total_price"+cnt+"']").val()).toFixed(2));
				})
				var tax = parseFloat(parseFloat($("#tax").val().trim()).toFixed(2));
				if(tax == "" || tax.toString() == "NaN"){
					tax = 0;
				}
				$("#total").html(all.toFixed(2));
				$("#total_all").val((all+tax).toFixed(2));
				personTotalMeony();
			},
			function(index){
				layer.close(index);
				return;
			}
	)
}

/**
 * 设备弹出窗
 */
function equipPop(){
	pagePop("设备","machineForm","/PM/pm/web/inspectplan/machine_list_pop.jsp",$("#ma_sp_id").val(),$("#ma_type_id").val());
}

//回调 machine pop页面 close 向父页面传值
function importMainfo(idsStr,namesStr,species_id,type_id,flag){
	$("#ma_sp_id").val(species_id);
	$("#ma_type_id").val(type_id);
	if(flag == 'Y'){
		$("#addMachineRowsTable").empty();
	}
	var idsArray=idsStr.split(",");
	var namesArray=namesStr.split(",");
	var rowLength = $("#addMachineRowsTable tr").length;
	$("#addMachineRowsTable tr").each(function(){
		rowLength = Number($(this).attr("id").substring(3))+1;
	})
	for(var i=0;i<idsArray.length;i++){
		var rowindex = i+rowLength;
		var str =  "<tr id='row"+rowindex+"'> "
		+ "  <td><input type='hidden' name='repairMachineDetailList["+rowindex+"].machine_id' dc='true' idattr='' value='"+idsArray[i]+"'/>"+namesArray[i]+"</td>  "
		+ "  <td><input type='text' name='repairMachineDetailList["+rowindex+"].down_hour' dc='true' dctype='数字' maxlength='5' dcrequired='请输入停机时间'/></td>  "
		+ "  <td colspan='3' id='repairDetail"+ rowindex+ "'></td> "
		+ "  <td><a href='javascript:deleteMachineItem("+rowindex+");' class='btn3' >删除</a></td>   "
		+ "</tr> ";
		$("#addMachineRowsTable").append(str);
		bindValidateEvent('addMachineRowsTable');
		$.ajax({
			url:getRequestUrl("/RepairOrderInfoController/queryDownList7.json"),
			dataType:"json",
			data:{"maintenanceItemInfo.machine_species_id":species_id,"maintenanceItemInfo.machine_type_id":type_id},
			async: false,
			success : function(result) {
				if(result.opflag){
					$("#repairDetail" + rowindex).html(
							$("#repairDetailGroupDownListTemplate").render(result)
					);
					$("#repairDetail" + rowindex).find("[data-name='select1']").attr("name", "repairMachineDetailList["+rowindex+"].repair_detail");
					$("#repairDetail" + rowindex).find("[data-name='select1']").attr("dc",true);
					$("#repairDetail" + rowindex).find("[data-name='select1']").attr("dcrequired",'请选择维修内容');
				}
			},
			error : function(error) {
				lalert('网络原因操作失败！','error');
			}
		});
	}	
}

//联动得到类型
//function goforTypeList(id){
//	var postData = {"sysCodeInfo.code_value":$("#"+id).val(),"sysCodeInfo.code_group_value":Class.getConstant('MACH_TYPE_CODE')};
//	$.ajax({
//		url:getRequestUrl("/MachineController/getSubCodeList.json"),
//		dataType:"json",
//		data:postData,
//		success:function(data){
//			var index = id.substr(10);
//			$( "#machSpecies"+index ).html(
//					$("#machinetypeListTemplate").render(data)
//			);
//		},
//		error:function(error){
//			lalert('网络原因操作失败！','error');
//		}
//	});
//}

/**
 * 删除设备调用方法
 */
function deleteMachineItem(str){
	$("#row"+str).remove();
}

/**
 * 查看报价
 */
function gotoOfferDetail(){
	var page = "/pm/web/repair/offer_detail.jsp?id="+id;
	pageForward(page);
}

/**
 * 费用列表变化时 合计计算
 */
function totalMeony(cnt){
	var amount = parseFloat($("#amount" + cnt).val().trim());
	var unit_price = parseFloat($("#unit_price" + cnt).val().trim());
	var total_price = (amount*unit_price).toFixed(2);
	var person_price = parseFloat((parseFloat($("#user_price").val().trim())*parseFloat($("#task_time").val().trim())).toFixed(2));
	var one_charge = parseFloat(parseFloat($("#one_charge").val().trim()).toFixed(2));
	var tax = parseFloat(parseFloat($("#tax").val().trim()).toFixed(2));
	var all = 0;
	if(total_price.toString() == "NaN"){
		$("#total_price" + cnt).val(0);
	}else{
		$("#total_price" + cnt).val(total_price);
	}
	$("#addRowsTable tr").each(function(i){
		var cnt = $(this).attr("id").substring(4);
		all = all + parseFloat(parseFloat($(this).find("input[id='total_price"+cnt+"']").val()).toFixed(2));
	})
	$("#total").html(all.toFixed(2));
	if(person_price.toString() == "NaN"){
		person_price = 0;
	}
	if(one_charge == "" || one_charge.toString() == "NaN"){
		one_charge = 0;
	}
	if(tax == "" || tax.toString() == "NaN"){
		tax = 0;
	}
	$("#all_price").val((person_price+one_charge+all+tax).toFixed(2));
	$("#total_all").val((all+tax).toFixed(2));
}

/**
 * 报价按钮是否隐藏
 */
function searchOfferDetail(){
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/getRepairOfferInfoById.json"),
		dataType:"json",
		data:{"id":id},
		success:function(result){
			if(result.opflag){
				$("#offer_check").show();
			}else{
				$("#offer_check").hide();
			}
//			if(expanseYn == 'Y'){
//				addItem();
//			}
			offerCostDetail();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * 报价信息获取
 * */
function offerCostDetail(){
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/queryOfferDetailEntity.json"),
		dataType:"json",
		data:{"repairOrderInfo.id":id},
		success:function(result){
			$("#addRowsTable").html($("#offerDetailListTemplate").render(result));
			$("#addRowsTable tr").find("input").attr("readonly",true);
			$("#addRowsTable tr").find("select").attr("disabled","disabled");
			var count = 0;
			$("#addRowsTable tr").each(function(){
				var total_price = $(this).find("input[id^='total_price']").val();
				if(total_price == ''){
					total_price = '0';
				}
				$(this).find("input[id^='total_price']").val(parseFloat(total_price).toFixed(2));
				count = count + parseFloat($(this).find("input[id^='total_price']").val());
			})
			$("#total").html(count.toFixed(2));//材料费总计
			personTotalMeony();
			bindevent();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}