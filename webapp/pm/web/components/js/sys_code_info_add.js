
$(function(){
	menu('menuA3');
    initPage();
});
function initPage(){
	
	bindevent();
}
function bindevent(){	
	
	//保存事件
	$("#saveBtn").bind('click',function(){
		saveFormData();
	});
	//返回
	$("#gobackBt").bind('click',function(){
		pageForward('/pm/web/components/sys_code_info_list.jsp');
	});
	
	
}


//提交前check
function checkListValue(){
	var postData = collectData("addform");
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

//保存
function saveFormData(){
	var checkflag1 = checkListValue();
	var checkflag =	checkFormValue('addform');
	//console.log(checkflag);
	if(checkflag1){
	if(checkflag){
		layer.confirm("是否提交", {
		    btn: ['确定','取消'] //按钮
		}, function(){
			var postData = collectData("addform");
		//	alert(postData);
			//console.log(postData);
			$.ajax({
				url:getRequestUrl("/ComponentsSysCodeInfoController/addEntityData.json"),
				dataType:"json",
				data:postData,
				success:function(result){
					console.log(result);
					if(result.opflag){
						console.log(result.opmessage);
					
							lalert("save","success");
							//alert(123123);
							pageForward('/pm/web/components/sys_code_info_list.jsp');
						
					}else{				
						lalert("save","error");
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

