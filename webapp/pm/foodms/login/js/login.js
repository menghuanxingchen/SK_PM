
/**
 * 登录
 * **/
function login(){
	var userId = "";
	var restaurantid = "";
	var postData = collectData("login");
	postData=$.extend(postData,{"sysUserInfo.loginType":$("input[name='loginRadio'][checked]").val()});
	$.ajax({
		url:path+"/loginManagerController/checkSysUserInfo.json",
		dataType:"json",
		data:postData,
		success:function(result){
			if(result.opflag){
				//lalert(result.opmessage,'success');
				if(result.sysUserinfo){
					currentUserId = result.sysUserinfo.user_num;
					window.location=path+"/loginManagerController/showManipage.do";
				}
			}else{
				lalert(result.opmessage,'error');
			}
		},
		error:function(error){
			lalert('网络原因操作失败','error');
		}
	});
}