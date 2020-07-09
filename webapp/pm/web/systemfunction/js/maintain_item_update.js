$(function(){
	menu('menu16');
	initPage();
});
var maintitem_id="";
function initPage(){	
	maintitem_id =getRequestParameterValue("maintitem_id");	
	getMaintenanceItem(maintitem_id);
	
}

function bindevent(){	
	//保存事件
	$("#saveBtn").bind('click',function(){
		updateFormData();
	});
	//返回
	$("#gobackBt").bind('click',function(){
		pageForward('/pm/web/systemfunction/maintain_item_list.jsp');
	});
	// 类别类型联动
	$("#machine_species_id").change(function(){
		goforTypeList();
	});
}


function updateFormData(){
	var checkflag =	checkFormValue('updateForm');
	if(checkflag){
		layer.confirm("是否提交", {
		    btn: ['确定','取消'] //按钮
		}, function(){
			var postData = collectData("updateForm");
			postData=$.extend(postData,{"maintainiteminfo.maintenance_id":maintitem_id});
			$.ajax({
				url:getRequestUrl("/MaintenanceItemController/updateMaintenanceItemInfo.json"),
				dataType:"json",
				data:postData,
				success:function(result){
					if(result.opflag){
						lalert("update","success",function(){
							pageForward('/pm/web/systemfunction/maintain_item_list.jsp');
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
function getMaintenanceItem(id){
	$.ajax({
		url : getRequestUrl("/MaintenanceItemController/queryMaintenanceItemInfo.json"),
		dataType:"json",
		data:{"maintainiteminfo.maintenance_id":id},
		success:function(result){		
			$("#updateForm").html($("#formTemplate").render(result));
			$("#machine_species_id").val(result.maintenanceItemInfo.machine_species_id);
			$("#machinetype").html($("#machinetypeListTemplate").render(result));
			$("#machine_type_id").val(result.maintenanceItemInfo.machine_type_id);
			bindevent();
		},
		error:function(error){
			layer.alert('error', {
			    skin: 'layui-layer-lan', //样式类名
			    closeBtn: 0
			});
		}
	});
}

//联动得到类型
function goforTypeList(){
	var postData = {"sysCodeInfo.code_value":$("#machine_species_id").val(),"sysCodeInfo.code_group_value":Class.getConstant('MACH_TYPE_CODE')};
	$.ajax({
		url:getRequestUrl("/MachineController/getSubCodeList.json"),
		dataType:"json",
		data:postData,
		success:function(data){
			$( "#machinetype" ).html(
					$("#machinetypeListTemplate").render(data)
			);
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}
