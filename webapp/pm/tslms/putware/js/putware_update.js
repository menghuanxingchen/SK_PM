$(function(){
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
	$.ajax({
		url:getRequestUrl("/TsLmsPutwareController/beforeUpdateEntity.json"),
		dataType:"json",
		data:{"tsLmsPutware.putid":putid},
		success:function(data){
			var addHtml = $("#formTemplate").render(data);
			$("#formId").html(addHtml);
			bindevent();
		},
		error:function(error){
			alert("error");
		}
	});
}

function bindevent(){
	$(".datepicker").datetimepicker({
		timepicker:false,
		format:'Y-m-d'
	});
	
	bindValidateEvent("formId");
	$("#closeBtn").bind('click',function(){
		pageForward('/TsLmsPutwareController/defaultJsp.do');
	});
	
	$("#submitBtn").bind('click',function(){
		var checkfalg=	checkFormValue('formId');
		if(checkfalg){
			$(this).unbind();
			var postData = collectData("formId");
			$.ajax({
				url:getRequestUrl("/TsLmsPutwareController/updateEntityData.json"),
				dataType:"json",
				data:postData,
				success:function(result){
					pageForward('/TsLmsPutwareController/defaultJsp.do');
				},
				error:function(error){
					alert("error");
				}
			});
		}
	});
}

