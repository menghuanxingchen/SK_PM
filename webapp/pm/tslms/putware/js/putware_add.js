$(function(){
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
	$.ajax({
		url:getRequestUrl("/TsLmsPutwareController/beforeAddEntity.json"),
		dataType:"json",
		data:[],
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
/**
 * 绑定事件属性
 * */
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
		var checkfalg =	checkFormValue('formId');
		if(checkfalg){
			$(this).unbind();
			var postData = collectData("formId");
			$.ajax({
				url:getRequestUrl("/TsLmsPutwareController/addEntityData.json"),
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
