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
		url:getRequestUrl("/RepairOrderInfoController/queryDownList.json"),
		dataType:"json",
		data:{"repairOrderInfo.id":id},
		success:function(result){
			$("#departGroup").html($("#departGroupDownListTemplate").render(result));
			$("#departGroupSub").html($("#departGroupSubDownListTemplate").render(result));
			$("#userId").html($("#userIdDownListTemplate").render(result));
			getRepairDetailNm();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

function getRepairDetailNm(){
	$.ajax({
		url:getRequestUrl("/ProposalInfoController/getProposalDetailNm.json"),
		dataType:"json",
		data:{"proposalInfo.id":id},
		success:function(result){
			$("#proposal_detail").val(result.proposalEntity.proposal_detail);
			bindevent();
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
	$("#depart_group").unbind();
	$("#depart_group").bind("change",function(){
		queryForSubDepart($("#depart_group").val());
	})
	$("#depart_group_sub").unbind();
	$("#depart_group_sub").bind("change",function(){
		queryForUser();
	})
	laydate({
		  elem: '#formId .laydate-icon', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		  event: 'focus' ,//响应事件。如果没有传入event，则按照默认的click
		  min: laydate.now() //最大日期
	});
}

/**
 * 部门onChange事件
 * */
function queryForSubDepart(group){
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/queryDownList2.json"),
		dataType:"json",
		data:{"sysUserInfo.dept_code":group,"sysUserInfo.sub_dept_code":$("#depart_group_sub").val()},
		success:function(result){
			$("#departGroupSub").html($("#departGroupSubDownListTemplate").render(result));
			$("#userId").html($("#userIdDownListTemplate").render(result));
			$("#depart_group_sub").unbind();
			$("#depart_group_sub").bind("change",function(){
				queryForUser();
			})
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * 下属部门onChange事件
 * */
function queryForUser(){
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/queryDownList2.json"),
		dataType:"json",
		data:{"sysUserInfo.dept_code":$("#depart_group").val(),"sysUserInfo.sub_dept_code":$("#depart_group_sub").val()},
		success:function(result){
			$("#userId").html($("#userIdDownListTemplate").render(result));
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * 人员分配新增
 * */
function addDistributionInfoData(){
	var checkfalg =	checkFormValue('aaa');
	if(checkfalg){
		var postData = collectData("formId");
		postData["proposalInfo.id"]=id;
		var message = "确认提交？";
		layer.confirm(
				message,
				function(index){
					layer.load('loading');//添加进度条
					$.ajax({
						url:getRequestUrl("/ProposalInfoController/addProposalDistributionInfoData.json"),
						dataType:"json",
						data:postData,
						success:function(result){
							layer.close(index);
							layer.closeAll('loading');
							if(result.opflag){
								lalert('save','success', function(){
									  var page = "/pm/web/proposal/proposal_info_list.jsp";
										pageForward(page);
								  });
							}else{
								lalert(result.opmessage,'warn', function(){
									  var page = "/pm/web/proposal/proposal_info_list.jsp";
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
 * 返回
 */
function returnBtnFun(){
	var page = "/pm/web/proposal/proposal_info_list.jsp";
	pageForward(page);
}