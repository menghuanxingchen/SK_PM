var menuid = getRequestParameterValue("menuid");
var id = getRequestParameterValue("id");
$(function(){
	menu(menuid);
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/queryOfferDetailEntity.json"),
		dataType:"json",
		data:{"repairOrderInfo.id":id},
		success:function(result){
			$("#offerDetailTbody").html($("#offerDetailListTemplate").render(result));
			$("#supplierCode").html($("#supplierCodeGroupDownListTemplate").render(result));
			$("#user_price").val(result.OfferInfo.user_price);
			$("#estimate_time").val(result.OfferInfo.estimate_time);
			$("#remark").val(result.OfferInfo.remark);
			$("#one_charge").val(result.OfferInfo.one_charge);
			$("#tax").val(result.OfferInfo.tax);
			var count = 0;
			$("#offerDetailTbody tr").each(function(){
				var total_price = $(this).find("input[id^='total_price']").val();
				if(total_price == ''){
					total_price = '0';
				}
				$(this).find("input[id^='total_price']").val(parseFloat(total_price).toFixed(2));
				count = count + parseFloat($(this).find("input[id^='total_price']").val());
			})
			var preson_price = parseFloat($("#user_price").val())* parseFloat($("#estimate_time").val());
			if(preson_price.toString() == "NaN"){
				preson_price = 0;
			}
			var tax = parseFloat(parseFloat($("#tax").val()).toFixed(2));
			if(tax.toString() == "NaN" || tax == ''){
				tax = 0;
			}
			var one_charge = parseFloat(parseFloat($("#one_charge").val()).toFixed(2));
			if(one_charge.toString() == "NaN" || one_charge == ''){
				one_charge = 0;
			}
			$("#tatal").html(count.toFixed(2));//材料费总计
			$("#all_price").val((count+one_charge+preson_price).toFixed(2));//材料费和人工费总计
			$("#tatal_all").val((tax+parseFloat($("#all_price").val())).toFixed(2));//材料费和人工费和税金总计
			$("#supplier_code option[value='"+result.OfferInfo.supplier_code+"']").attr("selected",true);
			$("input").attr("readonly",true);
			$("select").attr("disabled","disabled");
			getRepairDetailNm();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * 返回按钮
 * */
function backBtn(){
	history.back(-1);
}

/**
 * 查询维修内容
 * */
function getRepairDetailNm(){
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/getRepairDetailNm.json"),
		dataType:"json",
		data:{"repairOrderInfo.id":id},
		success:function(result){
			$("#repair_detail").val(result.repairDetailNm);
			$("#repair_order_num").val(result.repairOrderEntity.repair_order_num);
			$("#create_time").val(result.repairOrderEntity.create_time);
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