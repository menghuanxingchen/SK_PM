$(function(){
	menu('menu51');
	initPage();
});

/**
 * 初始化页面
 * */
var id = getRequestParameterValue("id");
function initPage(){
	$.ajax({
		url:getRequestUrl("/ProposalInfoController/getProposalDetailNm.json"),
		dataType:"json",
		data:{"proposalInfo.id":id},
		success:function(result){
			$("#proposal_num").val(result.proposalEntity.proposal_num);
			$("#create_time").val(result.proposalEntity.create_time);
			$("#require_date").val(result.proposalDistributionInfoEntity.require_date);
			getProposalResultDetail();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

function getProposalResultDetail(){
	$.ajax({
		url:getRequestUrl("/ProposalInfoController/getProposalResultDetail.json"),
		dataType:"json",
		data:{"proposalInfo.id":id},
		success:function(result){
			$("#operate_detail").val(result.proposalOperateEntity.operate_detail);
			$("#operate_expense").val(result.proposalOperateEntity.operate_expense);
			$("#improvement_effect").val(result.proposalOperateEntity.improvement_effect);
			$("#director_opinion").val(result.proposalOperateEntity.director_opinion);
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