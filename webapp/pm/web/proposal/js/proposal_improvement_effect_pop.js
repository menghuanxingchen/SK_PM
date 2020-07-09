$(function(){
	menu('menu51');
});

var id = getRequestParameterValue("ids");
/**
 * 保存按钮
 * */
function updateProposalImprovementEffect(str){
	var checkfalg =	checkFormValue('aaa');
	if(checkfalg){
		var postData = collectData("aaa");
		postData["proposalInfo.id"] = id;
		postData["updateflag"] = str;
		var message = "确认提交？";
		layer.confirm(
				message,
				function(index){
					layer.load('loading');//添加进度条
					$.ajax({
						url:getRequestUrl("/ProposalInfoController/updateProposalInfoApproveAndReject.json"),
						dataType:"json",
						data:postData,
						success:function(result){
							layer.close(index);
							if(result.opflag){
								lalert('save','success',function(){
									  var page = "/pm/web/proposal/proposal_info_list.jsp";
									  parent.window.location.href=path+page;
								  });
							}else{
								lalert('save','error');
							}
						},
						error:function(error){
							layer.close(index);
							parent.layer.closeAll();
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

function closelayer(){
	layer.closeAll();
}