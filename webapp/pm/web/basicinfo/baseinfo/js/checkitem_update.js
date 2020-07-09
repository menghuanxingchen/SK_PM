$(function(){
	menu('menu15');
	initPage();		
});
var checkitem_id="";
function initPage(){	
	checkitem_id =getRequestParameterValue("checkitem_id");	
	getCheckitemInfo(checkitem_id);	
}

function bindevent(){	
	//保存事件
	$("#saveBtn").bind('click',function(){
		updateFormData();
	});
	//返回
	$("#gobackBt").bind('click',function(){
		pageForward('/pm/web/basicinfo/baseinfo/checkitem_list.jsp');
	});
	// 类别类型联动
	$("#machine_species_id").change(function(){
		goforTypeList();
	});
}


function updateFormData(){
	var checkflag =	checkFormValue('checkupdateform');
	if(checkflag){
		layer.confirm("是否提交", {
		    btn: ['确定','取消'] //按钮
		}, function(){
			var postData = collectData("checkupdateform");
			postData=$.extend(postData,{"checkitemInfo.check_project_id":checkitem_id});
			$.ajax({
				url:getRequestUrl("/CheckItemController/updateCheckitemInfo.json"),
				dataType:"json",
				data:postData,
				success:function(result){
					if(result.opflag){
						if(result.opmessage=="name"){
							layer.open({
								  content: '检查项目名已存在！',
								  scrollbar: false,
								  closeBtn: 1,
								  icon: 1
								  //time: 1500, //2秒后自动关闭
								});
						}else{
							lalert("update","success",function(){
								pageForward('/pm/web/basicinfo/baseinfo/checkitem_list.jsp');
							});
						}
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
function getCheckitemInfo(id){
	$.ajax({
		url : getRequestUrl("/CheckItemController/queryCheckitemEntity.json"),
		dataType:"json",
		data:{"checkitemInfo.check_project_id":id},
		success:function(result){
			$("#checkupdateform").html($("#formTemplate").render(result));
			$("#machine_species_id").val(result.checkitemInfo.machine_species_id);
			$("#machinetype").html($("#machinetypeListTemplate").render(result));
			$("#machine_type_id").val(result.checkitemInfo.machine_type_id);
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
