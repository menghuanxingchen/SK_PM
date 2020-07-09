$(function(){
	menu('menu26');
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
	$.ajax({
		url:getRequestUrl("/SecCheckInfoController/queryDownList.json"),
		dataType:"json",
		data:"",
		success:function(result){
			$("#repairPlace").html($("#repairPlacedownListTemplate").render(result));
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
	bindValidateEvent("aaa");
	$("#submitBtn").bind('click',function(){
		addRepairOrderInfoData();
	});
	laydate({
		  elem: '#repair_add_table .laydate-icon', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		  event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	});
}

/**
 * 保存按钮
 * */
function addRepairOrderInfoData(){
	var postData = collectData("aaa");
	var checkfalg =	checkFormValue('aaa');
	if(checkfalg){	
	var message = "确认提交？";
	layer.confirm(
			message,
			function(index){
					$.ajax({
						url:getRequestUrl("/SecCheckInfoController/addSecCheckInfoData.json"),
						dataType:"json",
						data:postData,
						success:function(result){
							layer.close(index);
							if(result.opflag){
								layer.alert('提交成功',{
									  icon: 1
								}, function(){
									  var page = "/pm/web/seccheckinfo/seccheck_list.jsp";
										pageForward(page);
								  });
							}else{
								lalert('save','error');
							}
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
	)

	}else{
		layer.closeAll();
		$("#submitBtn").bind('click',function(){
			addRepairOrderInfoData();
		});
	}
}