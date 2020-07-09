$(function(){
	menu('menuB10');
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
	$.ajax({
		url:getRequestUrl("/SqmmanageController/beforeDataList.json"),
		dataType:"json",
		data:[],
		success:function(data){
			$( "#lineListForm" ).html(
					$("#lineListTemplate").render(data)
			);
			
			
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
	bindevent();
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
	var lineList = $("#lineList").val();

	if(intervalDate!="" && intervalDate != null && continueDate!="" && continueDate != null 
			&& breakdownDate!="" && breakdownDate != null && lineList!="" && lineList != null){
		return true;
	}else{
		return false;
	}
}

function saveForm(){
		
	var checkfalg =	checkFormValue('formId') && checkListValue();
		if(checkfalg){
			layer.confirm("是否提交", {
			    btn: ['确定','取消'] //按钮
			}, function(){
					var postData = collectData("formId");
					layer.load();//添加进度条
					$.ajax({
							url:getRequestUrl("/SqmmanageController/addEntityData.json"),
							dataType:"json",
							data:postData,
							success:function(result){
								layer.closeAll('loading'); //关闭进度条
								if(result.opflag){
									lalert('save','success',function(){
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
				
				/*else{
					layer.closeAll();
					$("#submitBtn").bind('click',function(){
						saveForm();
					});
				}*/
		})
	}else{
		lalert('时间或生产线不能为空！','error');
	}
	
}
