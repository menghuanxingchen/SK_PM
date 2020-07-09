$(function(){
	initPage();	
});


function initPage(){
	menu('menu13');
	getPagaLists();
	bindevent();
}

function bindevent(){	
	//保存事件
	$("#saveBtn").bind('click',function(){
		saveFormData();
	});
	//返回
	$("#gobackBt").bind('click',function(){
		pageForward('/pm/web/basicinfo/baseinfo/machinetype_list.jsp');
	});
	
}


function saveFormData(){
	var checkflag =	checkFormValue('addform');
	if(checkflag){
		layer.confirm("是否提交", {
		    btn: ['确定','取消'] //按钮
		}, function(){
			var postData = collectData("addform");
			$.ajax({
				url:getRequestUrl("/MachineTypeController/addEntityData.json"),
				dataType:"json",
				data:postData,
				success:function(result){
					if(result.opflag){
						lalert("save","success",function(){
							pageForward('/pm/web/basicinfo/baseinfo/machinetype_list.jsp');
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
//得到页面下拉
function getPagaLists(){
	$.ajax({
		url:getRequestUrl("/MachineController/queryPageList.json"),
		dataType:"json",
		data:"",
		success:function(result){
			$("#machinespecies").html($("#machinespeciesListTemplate").render(result));
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

