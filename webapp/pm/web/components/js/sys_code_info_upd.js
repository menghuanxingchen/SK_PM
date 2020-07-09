var code_id = "";

$(function(){
	menu('menuA3');
    initPage();
});
function initPage(){	
	code_id=getRequestParameterValue("code_id");
	getMachineInfo(code_id);
	bindevent();
}
function bindevent(){		
	//跳转到新增页面
	//保存事件
	$("#saveBtn").bind('click',function(){
		updateFormData();
	});
	//返回
	$("#gobackBt").bind('click',function(){
		pageForward('/pm/web/components/sys_code_info_list.jsp');
	});
	
	
}

//数据显示
function getMachineInfo(id){
	//alert(id);
	$.ajax({
		url : getRequestUrl("/ComponentsSysCodeInfoController/queryComponentsEntity.json"),
		dataType:"json",
		data:{"componentssysCodeInfo.code_id":id},
		success:function(result){	
			console.log(result);
			$("#updateForm").html($("#formTemplate").render(result));	
		//	alert(123);
			
		
			
		},
		error:function(error){
			layer.alert('error', {
			    skin: 'layui-layer-lan', //样式类名
			    closeBtn: 0
			});
		}
	});
}
//检验设备是否存在
function checkListValue1(){
	var postData = collectData("updateForm");
	var check= false;
	$.ajax({
		url:getRequestUrl("/ComponentsSysCodeInfoController/queryComponentsEntityValue.json"),
		async:false,
		dataType:"json",
		data:postData,
		success:function(result){
			console.log(result.data);
		if(result.data!=""){
			alert("设备名已存在");
			check = false;
		}else{
			check = true;
		}
		},
		error:function(error){
			alert("error");
		}
	});
  return check;
	
}


function updateFormData(){
	var checkflag1 = checkListValue1();
	var checkflag =	checkFormValue('updateForm');
	if(checkflag1){
	if(checkflag){
		layer.confirm("是否提交", {
		    btn: ['确定','取消'] //按钮
		}, function(){
			var postData = collectData("updateForm");
			//console.log(postData);
			postData=$.extend(postData,{"componentssysCodeInfo.code_id":code_id});
			console.log(postData);
			$.ajax({
				url:getRequestUrl("/ComponentsSysCodeInfoController/updEntityData.json"),
				dataType:"json",
				data:postData,
				success:function(result){
					if(result.opflag){
						lalert("update","success",function(){
							pageForward('/pm/web/components/sys_code_info_list.jsp');
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
}

