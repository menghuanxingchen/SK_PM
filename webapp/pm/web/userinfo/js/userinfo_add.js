$(function(){
	menu('menu11');
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
	$.ajax({
		url:getRequestUrl("/SysUserInfoController/beforeAddEntity.json"),
		dataType:"json",
		data:[],
		success:function(data){
			$( "#departTypeForm" ).html(
					$("#departListTemplate").render(data)
			);
			
			$( "#positionForm" ).html(
					$("#positionListTemplate").render(data)
			);
			
			$( "#subDepartTypeForm" ).html(
					$("#subDepartListTemplate").render(data)
			);
			
			$( "#sqmRoleTypeForm" ).html(
					$("#sqmRoleListTemplate").render(data)
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
	$("#departTypeForm").change(function(){
		$.ajax({
			url:getRequestUrl("/SysUserInfoController/getSubCodeList.json"),
			dataType:"json",
			data:{"subGroupCode":$("#dept").val()},
			success:function(data){
				$( "#subDepartTypeForm" ).html(
						$("#subDepartListTemplate").render(data)
				);
			},
			error:function(error){
				lalert('网络原因操作失败！','error');
			}
		});
	});
	bindValidateEvent("formId");
	$("#closeBtn").bind('click',function(){
		pageForward('/SysUserInfoController/defaultJsp.do');
	});
	
	$("#submitBtn").bind('click',function(){
		saveForm();
	});
	
}

function saveForm(){
		if($("#pwd").val()==$("#pwdCheck").val()){
			var checkfalg =	checkFormValue('formId');
				if(checkfalg){
			layer.confirm("是否提交", {
			    btn: ['确定','取消'] //按钮
			}, function(){
					var postData = collectData("formId");
					layer.load();//添加进度条
					$.ajax({
							url:getRequestUrl("/SysUserInfoController/addEntityData.json"),
							dataType:"json",
							data:postData,
							success:function(result){
								layer.closeAll('loading'); //关闭进度条
								if(result.opflag){
									lalert('save','success',function(){
										pageForward('/SysUserInfoController/defaultJsp.do');
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
			}
	}else{
		lalert("密码不一致",'warn');
		$("#pwdCheck").focus();
	}
}
