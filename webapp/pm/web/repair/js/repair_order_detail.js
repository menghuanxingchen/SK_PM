var menuid = getRequestParameterValue("menuid");
var id = getRequestParameterValue("id");
var flag = getRequestParameterValue("flag");

$(function(){
	menu(menuid);
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/queryRepairOrderForOrderDetail.json"),
		dataType:"json",
		data:{"repairOrderInfo.id":id},
		success:function(result){
			$("#repairPlace").html($("#repairPlacedownListTemplate").render(result));
			$("#userId1").html($("#loginUserInfoDownListTemplate").render(result));
			$("#userId2").html($("#sysUserInfoDownListTemplate2").render(result));
			$("#userId3").html($("#sysUserInfoDownListTemplate3").render(result));
			$("#userId4").html($("#factoryManagerInfoDownListTemplate4").render(result));
			$("#userId5").html($("#sysUserInfoDownListTemplate5").render(result));
			$("#userId6").html($("#factoryManagerInfoDownListTemplate6").render(result));
//			$("#app_sta1").html(result.appuser1.approval_nm);
//			$("#app_sta2").html(result.appuser2.approval_nm);
//			$("#app_sta3").html(result.appuser3.approval_nm);
//			$("#app_sta4").html(result.appuser4.approval_nm);
//			$("#app_sta5").html(result.appuser5.approval_nm);
			$("#require_time").val(result.repairOrderInfo.require_time);
			$("#repair_order_num").val(result.repairOrderInfo.repair_order_num);
			$("#create_time").val(result.repairOrderInfo.create_time);
			$("#repair_detail").val(result.repairOrderInfo.repair_detail);
			$("#remark").val(result.repairOrderInfo.remark);
			$("#repair_place option[value='"+result.repairOrderInfo.repair_place+"']").attr("selected",true);
			
			$("#app_time0").val(result.repairOrderInfo.create_time.substring(0,10));
			$("#app_time1").val(result.repairOrderInfo.approval_date);
			$("#app_time2").val(result.repairOrderInfo.distribute_date);
			$("#app_time3").val(result.repairOrderInfo.factory_manager_approval_date);
			$("#app_time4").val(result.repairOrderInfo.confirm_date);
			$("#app_time5").val(result.repairOrderInfo.factory_manager_confirm_date);
			$("#app_timeBaojia").val(result.repairOrderInfo.offer_date);
			$("#app_timeWeixiu").val(result.repairOrderInfo.repair_date);
			$("#baojiauserId").val(result.repairUser);
			$("#weixiuuserId").val(result.repairUser);
			
			$("input").attr("readonly",true);
			$("select").attr("disabled","disabled");
			$("textarea").attr("readonly",true);
			$("#reportExport_btn").unbind();
			$("#reportExport_btn").bind('click',reportExport);
			searchOfferDetail();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
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
			queryRepairDataEntity();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}
/**
 * 查看报价
 */
function gotoOfferDetail(){
	var page = "/pm/web/repair/offer_detail.jsp?id="+id+"&&menuid="+menuid;
	pageForward(page);
}
/**
 * 维修详情按钮
 * @param id
 */
function queryRepairDataEntity(){
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/getRepairResultInfoById.json"),
		dataType:"json",
		data:{"id":id},
		success:function(result){
			if(result.opflag){
				$("#result_check").show();
			}else{
				$("#result_check").hide();
			}
			if(flag=='1'){
				$("#approve_check").show();
				$("#reject_check").show();
				$("#confirm_check").hide();
			}else if(flag=='4'){
				$("#confirm_check").show();
				$("#approve_check").hide();
				$("#reject_check").hide();
			}else{
				$("#approve_check").hide();
				$("#reject_check").hide();
				$("#confirm_check").hide();
			}
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * 查询维修详情
 * @param id
 */
function gotoRepairDataEntity(){
	var page = "/pm/web/repair/repair_result_detail.jsp?id="+id+"&&menuid="+menuid;
	pageForward(page);
}

/**
 * 审批/驳回
 */
function updateApproveAndReject(str){
	var message = "";
	if(str=='R'){
		message = "是否驳回？";
	}else if(str=='A'){
		message = "是否审批？";
	}else{
		message = "是否确认？";
	}
	layer.confirm(
			message,
			function(index){
				$.ajax({
					url:getRequestUrl("/RepairOrderInfoController/updateApproveAndReject.json"),
					dataType:"json",
					data:{"repairOrderInfo.id":id,"updateflag":str},
					success:function(result){
						layer.close(index);
						lalert('save','success',function(){
							  var page = "/pm/web/repair/repair_order_list.jsp";
								pageForward(page);
						  });
					},
					error:function(error){
						layer.close(index);
						lalert('网络原因操作失败！','error');
					}
				});
			},
			function(index){
				layer.close(index);
				return;
			}
	);
}

/**
 * 返回按钮
 * */
function backBtn(){
	history.back(-1);
}

/**
 * 报表导出方法
 */
function reportExport(){
	layer.load();
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/repairExport.json"),
		dataType:"json",
		data:{"repairOrderInfo.id":id},
		success:function(result){
			layer.closeAll('loading');
			if(result.filePath == ''){
				lalert("网络原因操作失败！","error");
			}else{
				//window.open(result.filePath,"page");
				location.href = path +  result.filePath;
			}
		},
		error:function(error){
			layer.closeAll('loading');
			lalert("网络原因操作失败！","error");
		}
	});
}