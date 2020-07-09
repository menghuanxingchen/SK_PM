var id = getRequestParameterValue("id");
var menuid = getRequestParameterValue("menuid");
$(function(){
	menu(menuid);
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/getRepairResultInfo.json"),
		dataType:"json",
		data:{"id":id},
		success:function(result){
			$("#repairCostDetail_tbody").html($("#repairCostDetailListTemplate").render(result));
			var count = 0;
			$("#repairCostDetail_tbody tr").each(function(){
				var total_price = $(this).find("input[id^='total_price']").val();
				if(total_price == ''){
					total_price = '0';
				}
				$(this).find("input[id^='total_price']").val(parseFloat(total_price).toFixed(2));
				count = count + parseFloat($(this).find("input[id^='total_price']").val());
			})
			$("#tatal").html(count.toFixed(2));//材料费总计
			$("#repairMachineDetail_tbody").html($("#repairMachineDetailListTemplate").render(result));
			$("#repair_detail").val(result.repairResult.repair_detail);
			$("#require_date").val(result.repairResult.require_date);
			$("#unit_price").val(result.repairResult.unit_price);
			$("#task_time").val(result.repairResult.task_time);
			$("#one_charge").val(result.repairResult.one_charge);
			$("#finish_date").val(result.repairResult.finish_date);
			$("#remark").val(result.repairResult.remark);
			$("#tax").val(result.repairResultTax);
			$("#total_all").val(result.tatalAll);
			$("#repair_order_num").val(result.repairOrderEntity.repair_order_num);
			$("#create_time").val(result.repairOrderEntity.create_time);
			$("input").attr("readonly",true);
			$("textarea").attr("readonly",true);
			$("select").attr("disabled","disabled");
			if(result.repairResult.showMoney == 'Y'){
				$("#user_price_hide").hide();
				$("#unit_price").show();
			}else{
				$("#user_price_hide").show();
				$("#unit_price").hide();
			}
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
