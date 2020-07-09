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
		url:getRequestUrl("/ProposalInfoController/queryProposalInfoDetail.json"),
		dataType:"json",
		data:{"proposalInfo.id":id},
		success:function(result){
			$("#proposaType").html($("#proposalItemdownListTemplate").render(result));
			$("#userId1").html($("#loginUserInfoDownListTemplate").render(result));
			$("#userId2").html($("#sysUserInfoDownListTemplate1").render(result));
			$("#userId3").html($("#factoryManagerInfoDownListTemplate2").render(result));
			$("#userId4").html($("#sysUserInfoDownListTemplate3").render(result));
			$("#userId5").html($("#factoryManagerInfoDownListTemplate4").render(result));
			$("#app_sta1").html(result.appuser1.approval_nm);
			$("#app_sta2").html(result.appuser2.approval_nm);
			$("#app_sta3").html(result.appuser3.approval_nm);
			$("#app_sta4").html(result.appuser4.approval_nm);
			$("#proposal_num").val(result.proposalInfo.proposal_num);
			$("#create_time").val(result.proposalInfo.create_time);
			$("#proposal_detail").val(result.proposalInfo.proposal_detail);
			$("#anticipate_result").val(result.proposalInfo.anticipate_result);
			$("#resource_input").val(result.proposalInfo.resource_input);
			$("#comparative_conclusion").val(result.proposalInfo.comparative_conclusion);
			$("#proposal_type option[value='"+result.proposalInfo.proposal_type+"']").attr("selected",true);
			$("input").attr("readonly",true);
			$("#user_id4").attr("disabled","disabled");
//			$("select").attr("disabled","disabled");
//			$("textarea").attr("readonly",true);
//			$("#reportExport_btn").unbind();
//			$("#reportExport_btn").bind('click',reportExport);
//			searchOfferDetail();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * 报价按钮是否隐藏
 */
//function searchOfferDetail(){
//	$.ajax({
//		url:getRequestUrl("/RepairOrderInfoController/getRepairOfferInfoById.json"),
//		dataType:"json",
//		data:{"id":id},
//		success:function(result){
//			if(result.opflag){
//				$("#offer_check").show();
//			}else{
//				$("#offer_check").hide();
//			}
//			queryRepairDataEntity();
//		},
//		error:function(error){
//			lalert('网络原因操作失败！','error');
//		}
//	});
//}
///**
// * 查看报价
// */
//function gotoOfferDetail(){
//	var page = "/pm/web/repair/offer_detail.jsp?id="+id+"&&menuid="+menuid;
//	pageForward(page);
//}
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
//	layer.load();
//	$.ajax({
//		url:getRequestUrl("/RepairOrderInfoController/repairExport.json"),
//		dataType:"json",
//		data:{"repairOrderInfo.id":id},
//		success:function(result){
//			layer.closeAll('loading');
//			if(result.filePath == ''){
//				lalert("网络原因操作失败！","error");
//			}else{
//				//window.open(result.filePath,"page");
//				location.href = path +  result.filePath;
//			}
//		},
//		error:function(error){
//			layer.closeAll('loading');
//			lalert("网络原因操作失败！","error");
//		}
//	});
}

/**
 * 修改
 * */
function updateRepairOrderInfoData(){
	var checkfalg =	checkFormValue('aaa');
	if(checkfalg){
		var postData = collectData("aaa");
		postData["proposalInfo.id"]=id;
		var message = "确认修改？";
		layer.confirm(
				message,
				function(index){
					layer.load('loading');//添加进度条
					$.ajax({
						url:getRequestUrl("/ProposalInfoController/updateProposalInfoData.json"),
						dataType:"json",
						data:postData,
						success:function(result){
							layer.close(index);
							layer.closeAll('loading');
							if(result.opflag){
								lalert('update','success', function(){
									  var page = "/pm/web/proposal/proposal_info_list.jsp";
										pageForward(page);
								  });
							}else{
								lalert('update','error');
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
 * 审批change
 * @param obj
 */
function approvechange(obj){
	$("#userId3   option[value='" + $(obj).val() + "']").prop("selected",true);
	
}