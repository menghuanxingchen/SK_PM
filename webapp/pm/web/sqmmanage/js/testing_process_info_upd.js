$(function(){
	menu('menuB12');
	initPage();
});
var id="";
/**
 * 初始化页面
 * */
function initPage(){
	id = getRequestParameterValue("id");

	$.ajax({
		url:getRequestUrl("/TestingProcessInfoController/beforeUpdateEntity.json"),
		dataType:"json",
		data:{"lineProductInfo.id":id},
		success:function(result){
			$("#dataListForm").html($("#dataListTemplate").render(result)); 
			
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
	
	bindValidateEvent("formId");
	$("#closeBtn").bind('click',function(){
		pageForward('/TestingProcessInfoController/defaultJsp.do');
	});
	
	$("#submitBtn").bind('click',function(){
		saveForm();
	});
}

function saveForm(){
		layer.confirm("是否提交", {
		    btn: ['确定','取消'] //按钮
			}, function(){
			$("#submitBtn").unbind('click');
			var checkfalg =	checkFormValue('formId');
			if(checkfalg){
				var postData = collectData("formId");
				layer.load();//添加进度条
				$.ajax({
						url:getRequestUrl("/TestingProcessInfoController/updateEntityData.json"),
						dataType:"json",
						data:postData,
						success:function(result){
							layer.closeAll('loading'); //关闭进度条
							if(result.opflag){
								lalert('update','success',function(){
									pageForward('/TestingProcessInfoController/defaultJsp.do');
								});
							}else{
								lalert(result.opmessage,'error');
							}
						},
						error:function(error){
							layer.closeAll('loading'); //关闭进度条
							lalert('网络原因操作失败！','error');
						}
				});
		}
	})
	
}




