$(function(){
	initPage();	
});


function initPage(){
	menu('menu74');
	bindevent();
}

function bindevent(){	
	//保存事件
	$("#saveBtn").bind('click',function(){
		saveFormData();
	});
	//返回
	$("#gobackBt").bind('click',function(){
		pageForward('/pm/web/systemfunction/supplier_list.jsp');
	});
}


function saveFormData(){
	var checkflag =	checkFormValue('addform');
	if(checkflag){
		layer.confirm("是否提交", {
		    btn: ['确定','取消'] //按钮
		}, function(){
			var postData = collectData("addform");
			postData=$.extend(postData,{"sysCodeInfo.code_group_value":Class.getConstant('SUPPLIER_GROUP')});
			$.ajax({
				url:getRequestUrl("/SupplierController/saveSysCodeInfo.json"),
				dataType:"json",
				data:postData,
				success:function(result){
					if(result.opflag){
						lalert("save","success",function(){
							pageForward('/pm/web/systemfunction/supplier_list.jsp');
						});
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

