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
			bindValidateEvent('aaa');
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * 保存按钮
 * */
function addRepairResultInfo(){
	var checkfalg =	checkFormValue('aaa');
	if(checkfalg){
		var postData = collectData("aaa");
		postData["proposalInfo.id"]=id;
		var message = "确认提交？";
		layer.confirm(
				message,
				function(index){
					layer.load('loading');//添加进度条
					$.ajax({
						url:getRequestUrl("/ProposalInfoController/addProposalResultData.json"),
						dataType:"json",
						data:postData,
						success:function(result){
							layer.close(index);
							layer.closeAll('loading');
							if(result.opflag){
								lalert('save','success',function(){
									  var page = "/pm/web/proposal/proposal_info_list.jsp";
										pageForward(page);
								  });
							}else{
								lalert('save','error');
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