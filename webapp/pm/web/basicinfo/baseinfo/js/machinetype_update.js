$(function(){
	menu('menu13');
	initPage();
	bindevent();	
});
var code_id="";
function initPage(){	
	code_id =getRequestParameterValue("code_id");	
	getmaTypeInfo(code_id);	
}

function bindevent(){	
	//保存事件
	$("#saveBtn").bind('click',function(){
		updateFormData();
	});
	//返回
	$("#gobackBt").bind('click',function(){
		pageForward('/pm/web/basicinfo/baseinfo/machinetype_list.jsp');
	});
	
}


function updateFormData(){
	var checkflag =	checkFormValue('checkupdateform');
	if(checkflag){
		layer.confirm("是否提交", {
		    btn: ['确定','取消'] //按钮
		}, function(){
			var postData = collectData("checkupdateform");
			postData=$.extend(postData,{"sysCodeInfo.code_id":code_id});
			$.ajax({
				url:getRequestUrl("/MachineTypeController/updateEntityData.json"),
				dataType:"json",
				data:postData,
				success:function(result){
					if(result.opflag){
						lalert("update","success",function(){
							pageForward('/pm/web/basicinfo/baseinfo/machinetype_list.jsp');
						});
					}else{				
						lalert("update","error");
					}				
				},
				error:function(error){
					alert("error");
				}
			});
		})
	}
}
//得到页面初始值
function getmaTypeInfo(id){
	$.ajax({
		url : getRequestUrl("/MachineTypeController/beforeUpdateEntity.json"),
		dataType:"json",
		data:{"sysCodeInfo.code_id":id},
		success:function(result){
			$("#checkupdateform").html($("#formTemplate").render(result));
			$("#speciesid").val(result.sysCodeInfo.code_group_value);
		},
		error:function(error){
			layer.alert('error', {
			    skin: 'layui-layer-lan', //样式类名
			    closeBtn: 0
			});
		}
	});
}
