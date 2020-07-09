$(function(){
	//页面初始化的事件
	initPage();  	
	
});

var id = getRequestParameterValue("id");
var repair_cost_id = getRequestParameterValue("repair_cost_id");
function initPage(){	
	bindevent();
	getCodeLists();
}

function bindevent(){		
	//跳回
	$("#cancelBtn").unbind();
	$("#cancelBtn").bind('click',function(){
		pageForward('/pm/mobile/m_repair/m_repair_add.jsp?id='+id);
	});
	//save
	$("#submitBtn").unbind();
	$("#submitBtn").bind('click',function(){
		updateRepairCostDetailTempMob();
	});
}

//得到页面下拉
function getCodeLists(){
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/queryDownList6.json"),
		dataType:"json",
		data:"",
		success:function(result){			
			$("#costType").html(
					$("#expensesTypeGroupDownListTemplate").render(result)
			);
			getRepairCostDetailTempByIdMob();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

//save前查询
function getRepairCostDetailTempByIdMob(){
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/getRepairCostDetailTempByIdMob.json"),
		dataType:"json",
		data:{"repairCostDetailTemp.repair_cost_id":repair_cost_id},
		success:function(result){
			$("#cost_type option[value='"+result.repairCostDetailTemp.cost_type+"']").attr("selected","selected");
			$("#cost_detail").val(result.repairCostDetailTemp.cost_detail);
			$("#amount").val(result.repairCostDetailTemp.amount);
			$("#unit_price").val(result.repairCostDetailTemp.unit_price);
			$("#total_price").val(result.repairCostDetailTemp.total_price);
			$("#remark").val(result.repairCostDetailTemp.remark);
			$("#spec").html($("#transportSpecCodeGroupDownListTemplate").render(result));
			if(result.repairCostDetailTemp.cost_type == Class.getConstant('TRANS_FLAG')){
				$("#transportSpec option[value='"+result.repairCostDetailTemp.spec+"']").attr("selected","selected");
				$("#spec").find("[data-name='select1']").attr("name", "repairCostDetailTemp.spec");
				$("#spec").find("[data-name='select1']").attr("dc",true);
				$("#hiddenSpec").find("input").removeAttr("dc");
				$("#spec").show();
				$("#hiddenSpec").hide();
			}else{
				$("#specNm").val(result.repairCostDetailTemp.spec);
				$("#spec").find("[data-name='select1']").removeAttr("dc");
				$("#hiddenSpec").find("input").attr("dc",true);
				$("#hiddenSpec").show();
				$("#spec").hide();
			}
			
			$("#costType").change(function(){
				if($(this).find("[data-name='select1']").val() == Class.getConstant('TRANS_FLAG')){
					$("#transportSpec option[value='']").attr("selected","selected");
					$("#spec").find("[data-name='select1']").attr("name", "repairCostDetailTemp.spec");
					$("#spec").find("[data-name='select1']").attr("dc",true);
					$("#hiddenSpec").find("input").removeAttr("dc");
					$("#spec").show();
					$("#hiddenSpec").hide();
				}else{
					$("#spec").find("[data-name='select1']").removeAttr("dc");
					$("#hiddenSpec").find("input").attr("dc",true);
					$("#hiddenSpec").find("input").val("");
					$("#hiddenSpec").show();
					$("#spec").hide();
				}
			})
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * update 方法
 */
function updateRepairCostDetailTempMob(){
	layer.open({
			content: '确定提交',
		    btn: ['确定','取消'], //按钮
		    yes: function() {
		    	var postData = collectData("com_body");
		    	postData["repairCostDetailTemp.repair_cost_id"] = repair_cost_id;
		    	postData["repairCostDetailTemp.repair_result_id"] = id;
		    	postData["repairCostDetailTemp.cost_type"] = $("#cost_type").val();
		    	$.ajax({
		    		url:getRequestUrl("/RepairOrderInfoController/updateRepairCostDetailTempMob.json"),
		    		dataType:"json",
		    		data:postData,
		    		success:function(result){
		    			if(result.opflag){
		    				pageForward('/pm/mobile/m_repair/m_repair_add.jsp?id='+id);
		    			}
		    		},
		    		error:function(error){
		    			layer.closeAll('loading');
		    			lalert('网络原因操作失败！','error');
		    		}
		    	});
		    }
	})
}

/**
 * 费用变化时 合计计算
 */
function totalMeony(){
	var amount = parseFloat($("#amount").val().trim());
	var unit_price = parseFloat($("#unit_price").val().trim());
	var total_price = (amount*unit_price).toFixed(2);
	if(total_price == "NaN"){
		$("#total_price").val(0);
	}else{
		$("#total_price").val(total_price);
	}
}

/**
 * 跳转页
 */
function goBackToAddResult(){
	var page = "/pm/mobile/m_repair/m_repair_add.jsp?id="+id;
	pageForward(page);
}