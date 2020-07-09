$(function(){

	initPage();

	
});

/**
 * 初始化页面
 * */
function initPage(){
	showbox(parent.layer.getFrameData(window.name));
}



function saveItem(){
	var checkfalg=	checkFormValue('editFormTable') ;
	if(checkfalg){
		var data = collectData("editFormTable");
		$.ajax({
			url:getRequestUrl("/sysResourceController/addResource.json"),
			dataType:"json",
			data:data,
			success:function(result){
				if(result.opflag){
					parent.layer.msg(result.opmessage,2,1);
					parent.querySysResourceTree();//刷新主页
					parent.showResNode(resutl.data.id);
					parent.closeLayer(); //执行关闭
					
				}else{
					layer.alert(result.opmessage);
				}
			},
			error:function(error){
				layer.alert("error",3);
			}
		});
	}
}

function showbox(id){
	$.ajax({
		url:getRequestUrl("/sysResourceController/querySysResourceById.json"),
		dataType:"json",
		data:{"sysResource.id":id},
		success:function(result){
			if(result.opflag){
				debugger;
				$("#editFormTable").html($( "#editFormTemplate" ).render(result.data));
				bindValidateEvent("editFormTable");
			}else{
				layer.alert(result.opmessage);
			}
			
		}
	});
}
