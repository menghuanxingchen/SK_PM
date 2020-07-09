$(function(){
	menu('menu31');
	initPage();
});

/**
 * 初始化页面
 * */
var id = getRequestParameterValue("id");
function initPage(){
	var postData = collectData("approve_line_table");
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/queryDownList3.json"),
		dataType:"json",
		data:"",
		success:function(result){
			$("#repairPlace").html($("#repairPlacedownListTemplate").render(result));
			$("#userId2").html($("#sysUserInfoDownListTemplate2").render(result));
			$("#userId3").html($("#sysUserInfoDownListTemplate3").render(result));
			$("#userId4").html($("#factoryManagerInfoDownListTemplate4").render(result));
			$("#userId6").html($("#factoryManagerInfoDownListTemplate6").render(result));
			queryRepairOrderBeforeUpdate();
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
	$("#submitBtn").unbind();
	$("#submitBtn").bind('click',function(){
		addRepairOrderInfoData();
	});
	laydate({
		  elem: '#repair_add_table .laydate-icon', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		  event: 'focus' ,//响应事件。如果没有传入event，则按照默认的click
		  min: laydate.now() //最大日期
	});
}

/**
 * 详情查询
 * */
function queryRepairOrderBeforeUpdate(){
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/queryRepairOrderBeforeUpdate.json"),
		dataType:"json",
		data:{"repairOrderInfo.id":id},
		success:function(result){
			if(result.opflag){
				$("#repair_place  option[value='" + result.repairOrderInfo.repair_place + "']").attr("selected",true);
				$("#repair_order_num").val(result.repairOrderInfo.repair_order_num);
				$("#create_time").val(result.repairOrderInfo.create_time);
				$("#require_time").val(result.repairOrderInfo.require_time);
				$("#repair_detail").val(result.repairOrderInfo.repair_detail);
				$("#remark").val(result.repairOrderInfo.remark);
				$("#userId1").html($("#loginUserInfoDownListTemplate").render(result));
				$("#userId5").html($("#sysUserInfoDownListTemplate5").render(result));
				$("#user_id2   option[value='" + result.approveUser2.approve_user_id + "']").attr("selected",true);
				$("#user_id3   option[value='" + result.approveUser3.approve_user_id + "']").attr("selected",true);
				$("#user_id1").attr("disabled","disabled");
				$("#user_id4").attr("disabled","disabled");
				$("#user_id5").attr("disabled","disabled");
				$("#user_id6").attr("disabled","disabled");
				bindevent();
			}
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * 详情查询
 * */
function updateRepairOrderInfoData(){
	var checkfalg =	checkFormValue('aaa');
	if(checkfalg){
		var postData = collectData("aaa");
		postData["repairOrderInfo.id"]=id;
		var message = "确认修改？";
		layer.confirm(
				message,
				function(index){
					layer.load('loading');//添加进度条
					$.ajax({
						url:getRequestUrl("/RepairOrderInfoController/updateRepairOrderInfoData.json"),
						dataType:"json",
						data:postData,
						success:function(result){
							layer.close(index);
							layer.closeAll('loading');
							if(result.opflag){
								lalert('update','success', function(){
									  var page = "/pm/web/repair/repair_order_list.jsp";
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