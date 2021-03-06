$(function(){
	
	initPage();
	
	bindValidateEvent("formadd");
});

/**
 *  页面初始化
 */
function initPage(){

	if(parent.tempKindname!="")
		$("#kindcode").val( parent.tempKindname);
	
	if(parent.tempKindvalue!="")
		$("#kindname").val( parent.tempKindvalue);
}


/**
 * 新增员工信息
 */
function addCode(){

	var checkfalg=	checkFormValue('formadd') ;

	if(checkfalg){
			var data = collectData("formadd");
			//检查代码表数据是否重复
			
			$.ajax({
				url:getRequestUrl("/transcodeController/saveSysCodeListByCode.json"),
				dataType:"json",
				data:data,
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
