$(function(){
	menu('menu71');
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
	bindevent();
}
/**
 * 绑定事件属性
 * */
function bindevent(){
	bindValidateEvent("formId");
	$("#closeBtn").bind('click',function(){
		pageForward('/RoleInfoController/defaultJsp.do');
	});
	
	$("#submitBtn").bind('click',function(){
		saveForm();
	});
	
}

function saveForm(){
			var checkfalg =	checkFormValue('formId');
				if(checkfalg){
			layer.confirm("是否提交", {
			    btn: ['确定','取消'] //按钮
			}, function(){
					var postData = collectData("formId");
					layer.load();//添加进度条
					$.ajax({
							url:getRequestUrl("/RoleInfoController/addEntityData.json"),
							dataType:"json",
							data:postData,
							success:function(result){
								layer.closeAll('loading'); //关闭进度条
								if(result.opflag){
									lalert('save','success',function(){
										pageForward('/RoleInfoController/defaultJsp.do');
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
		})
			}
}
