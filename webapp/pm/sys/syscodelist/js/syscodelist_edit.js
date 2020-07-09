$(function(){
	
	initPage();
	

});

/**
 *  页面初始化
 */
function initPage(){

	showbox(parent.layer.getFrameData(window.name));
}


function showbox(id){
	$.ajax({
		url:getRequestUrl("/transcodeController/loadSysCodeListById.json"),
		dataType:"json",
		data:{"sysCodelist.codelistid":id},
		success:function(result){
			
			if(result.opflag){
				$("#showbox").html($( "#editTemplate" ).render(result.sysCodelist));
				bindValidateEvent("formadd");
			}else{
				layer.alert(result.opmessage);
			}
			
		}
	});
}


/**
 * 新增员工信息
 */
function editCode(){

	var checkfalg=	checkFormValue('formadd') ;

	if(checkfalg){
			var pdata = collectData("formadd");
			//检查代码表数据是否重复
			
			$.ajax({
				url:getRequestUrl("/transcodeController/saveUpdateSysCodeListById.json"),
				dataType:"json",
				data:pdata,
				success:function(result){
					if(result.opflag){
						parent.layer.msg(result.opmessage,2,1);
						parent.querySysLogOperationList();//刷新
						parent.showCodeDetail($("#kindcode").val(),$("#kindname").val());
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
