$(function(){
	menu('menu51');
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
	$.ajax({
		url:getRequestUrl("/ProposalInfoController/queryDownListForProposal.json"),
		dataType:"json",
		data:"",
		success:function(result){
			$("#proposalItem").html($("#proposalItemdownListTemplate").render(result));
			$("#userId1").html($("#loginUserInfoDownListTemplate").render(result));
			$("#userId2").html($("#sysUserInfoDownListTemplate1").render(result));
			$("#userId3").html($("#factoryManagerInfoDownListTemplate2").render(result));
			$("#userId4").html($("#sysUserInfoDownListTemplate3").render(result));
			$("#userId5").html($("#factoryManagerInfoDownListTemplate4").render(result));
			$("#userId2   option[value='" + result.sysUserInfo2.user_num + "']").prop("selected",true);
			$("#userId3   option[value='" + result.sysUserInfo2.user_num + "']").prop("selected",true);
			$("#user_id1").attr("disabled","disabled");
			$("#user_id4").attr("disabled","disabled");
			$("#user_id5").attr("disabled","disabled");
			$("#user_id6").attr("disabled","disabled");
//			bindevent();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * 绑定事件属性
 * */
function bindevent(){

}

/**
 * 保存按钮
 * */
function addRepairOrderInfoData(){
	var checkfalg =	checkFormValue('aaa');
	if(checkfalg){
		var postData = collectData("aaa");
		var message = "确认提交？";
		layer.confirm(
				message,
				function(index){
					layer.load('loading');//添加进度条
					$.ajax({
						url:getRequestUrl("/ProposalInfoController/addProposalInfoData.json"),
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

/**
 * 审批change
 * @param obj
 */
function approvechange(obj){
	$("#userId3   option[value='" + $(obj).val() + "']").prop("selected",true);
	
}