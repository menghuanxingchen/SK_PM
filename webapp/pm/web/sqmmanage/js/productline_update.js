$(function(){
	menu('menuB10');
	initPage();
});
var id="";
/**
 * 初始化页面
 * */
function initPage(){
	id = getRequestParameterValue("id");

	$.ajax({
		url:getRequestUrl("/SqmmanageController/beforeUpdateEntity.json"),
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
		pageForward('/SqmmanageController/defaultJsp.do');
	});
	
	$("#submitBtn").bind('click',function(){
		saveForm();
	});
}

//提交前check
function checkListValue(){
	var intervalDate = $("#intervalDate").val();
	var continueDate = $("#continueDate").val();
	var breakdownDate = $("#breakdownDate").val();
	
	if(intervalDate!="" && intervalDate != null && continueDate!="" && continueDate != null && breakdownDate!="" && breakdownDate != null){
		return true;
	}else{
		return false;
	}
}

function saveForm(){
	var checkfalg = checkListValue();
    if(checkfalg){
		layer.confirm("是否提交", {
		    btn: ['确定','取消'] //按钮
			}, function(){
			$("#submitBtn").unbind('click');
			var checkfalg =	checkFormValue('formId');
			if(checkfalg){
				var postData = collectData("formId");
				layer.load();//添加进度条
				$.ajax({
						url:getRequestUrl("/SqmmanageController/updateEntityData.json"),
						dataType:"json",
						data:postData,
						success:function(result){
							layer.closeAll('loading'); //关闭进度条
							if(result.opflag){
								lalert('update','success',function(){
									pageForward('/SqmmanageController/defaultJsp.do');
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
    }else{
    	lalert('时间不能为空！','error');
    }
}




