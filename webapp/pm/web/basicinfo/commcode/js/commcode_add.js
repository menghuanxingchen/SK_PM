$(function(){
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
	$.ajax({
		url:getRequestUrl("/SysCodeInfoController/beforeAddEntity.json"),
		dataType:"json",
		data:[],
		success:function(data){
			var addHtml = $("#formTemplate").render(data);
			$("#formId").html(addHtml);
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
		pageForward('/SysCodeInfoController/defaultJsp.do');
	});
	
	$("#codeKind").change(function(){
		if($("#codeKind").val()==""){
			$("select[name='sysCodeInfo.sub_code_group_value']").val(0);
			$("select[name='sysCodeInfo.sub_code_group_value']").attr("disabled","disabled");
		}else{
			$("#codeValue").val("");
			$("#codeNm").val("");
			$("select[name='sysCodeInfo.sub_code_group_value']").attr("disabled",false);
		}
		
	});
	
	$("#submitBtn").bind('click',function(){
		var checkfalg =	checkFormValue('formId');
		if(checkfalg){
			$(this).unbind();
			if($("#codeKind").val()==''){
				$("#codeValue").attr("name","sysCodeGroupInfo.code_group_value");
				$("#codeNm").attr("name","sysCodeGroupInfo.code_group_name");
			}else{
				$("#codeValue").attr("name","sysCodeInfo.code_value");
				$("#codeNm").attr("name","sysCodeInfo.code_nm");
			}
			var postData = collectData("formId");
			layer.confirm('确认新增？',
			 function(){
				$.ajax({
					url:getRequestUrl("/SysCodeInfoController/addEntityData.json"),
					dataType:"json",
					data:postData,
					success:function(result){
						if(result.opflag){
							 lalert('save','success');
							pageForward('/SysCodeInfoController/defaultJsp.do');
						}else{
							lalert(result.opmessage,'error');
						}
						bindevent();
					},
					error:function(error){
						lalert('网络原因操作失败！','error');
					}
				});
			});
			bindevent();
		}
	});
	
	
}
