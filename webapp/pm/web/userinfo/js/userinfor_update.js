$(function(){
	menu('menu11');
	initPage();
});
var id="";
/**
 * 初始化页面
 * */
function initPage(){
	id = getRequestParameterValue("id");
	$.ajax({
		url:getRequestUrl("/SysUserInfoController/beforeUpdateEntity.json"),
		dataType:"json",
		data:{"sysUserInfo.user_id":id},
		success:function(result){
			$("#dataListForm").html($("#dataListTemplate").render(result)); 
			//下级部门查询并赋值
			$.ajax({
				url:getRequestUrl("/SysUserInfoController/getSubCodeList.json"),
				dataType:"json",
				data:{"subGroupCode":result.sysUserInfoEo.dept_code},
				success:function(data){
					$( "#subDepartTypeForm" ).html(
							$("#subDepartListTemplate").render(data)
					);
					$( "#sqmRoleTypeForm" ).html(
							$("#sqmRoleListTemplate").render(data)
					);
					$("#sqm_role_code").val(result.sysUserInfoEo.sqm_role_code);
					$("#sub_dept_code").val(result.sysUserInfoEo.sub_dept_code);
				},
				error:function(error){
					lalert('网络原因操作失败！','error');
				}
			});
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
	//部门与下级部门联动
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
		layer.confirm("是否提交", {
		    btn: ['确定','取消'] //按钮
			}, function(){
			$("#submitBtn").unbind('click');
			var checkfalg =	checkFormValue('formId');
			if(checkfalg){
				var postData = collectData("formId");
				layer.load();//添加进度条
				$.ajax({
						url:getRequestUrl("/SysUserInfoController/updateEntityData.json"),
						dataType:"json",
						data:postData,
						success:function(result){
							layer.closeAll('loading'); //关闭进度条
							if(result.opflag){
								lalert('update','success',function(){
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
		}
	})
	
}

function changePwdPop(){
	layer.open({
		  type: 1,
		  //skin: 'layui-layer-rim', //样式类名
		  closeBtn: 0, //不显示关闭按钮
		  shift: 2,
		  title: '密码修改', //不显示标题
		  shadeClose: true, //开启遮罩关闭
		  content: '<table id="pwdForm" class="tt tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">'+
		  			'<tr><td class="odd">密码</td><input type="hidden" value="'+id+'"  name="sysUserInfo.user_id"   dc=""/>'+
		  			'<td><input type="password" id="pwd"  dcrequired="请输入密码"    maxlength="50"/></td></tr>'+
		  			'<tr><td class="odd">确认密码</td>'+
		  			'<td><input type="password" id="pwdCheck" dcrequired="请输入确认密码"  name="sysUserInfo.pwd"  dc="" maxlength="50"/></td>'+
		  			'</tr></table>'+
		  			'<div class="tex_c mt22 mb30"><a href="javascript:changePwd();" class="btn1 f14 fb mr10">提交</a><a href="javascript:layer.closeAll();" class="btn1 f14 fb mr10">关闭</a></div>'
		});
}

function changePwd(){
	if($("#pwd").val()==$("#pwdCheck").val()){
		var checkfalg =	checkFormValue('pwdForm');
		if(checkfalg){
			layer.confirm("是否提交", {
			    btn: ['确定','取消'] //按钮
				}, function(){
					var postData = collectData("pwdForm");
					layer.load();//添加进度条
					$.ajax({
							url:getRequestUrl("/SysUserInfoController/updateEntityData.json"),
							dataType:"json",
							data:postData,
							success:function(result){
								layer.closeAll('loading'); //关闭进度条
								if(result.opflag){
									lalert('save','success',function(){
										layer.closeAll();
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
		}else{
			lalert("密码不一致",'warn');
			$("#pwdCheck").focus();
		}
}

