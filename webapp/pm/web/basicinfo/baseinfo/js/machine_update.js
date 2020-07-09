$(function(){
	menu('menu14');
	initPage();
});
var machine_id="";
function initPage(){	
	machine_id =getRequestParameterValue("machine_id");	
	getMachineInfo(machine_id);
	
}

function bindevent(){	
	//日期插件
	laydate({
		  elem: '#datef' //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		});
	//保存事件
	$("#saveBtn").bind('click',function(){
		updateFormData();
	});
	//返回
	$("#gobackBt").bind('click',function(){
		pageForward('/pm/web/basicinfo/baseinfo/machine_list.jsp');
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
			postData=$.extend(postData,{"machineInfo.machine_id":machine_id});
			$.ajax({
				url:getRequestUrl("/MachineController/updateMachineInfo.json"),
				dataType:"json",
				data:postData,
				success:function(result){
					if(result.opflag){
						if(result.opmessage=="name"){
							layer.open({
								  content: '设备名已存在！',
								  scrollbar: false,
								  closeBtn: 1,
								  icon: 1
								 // time: 1500, //2秒后自动关闭
								});
						}else{
							lalert("update","success",function(){
								pageForward('/pm/web/basicinfo/baseinfo/machine_list.jsp');
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
function getMachineInfo(id){
	$.ajax({
		url : getRequestUrl("/MachineController/queryMachineEntity.json"),
		dataType:"json",
		data:{"machineInfo.machine_id":id},
		success:function(result){		
			$("#updateForm").html($("#formTemplate").render(result));
			$("#machine_species_id").val(result.machineInfo.machine_species_id);
			$("#machinetype").html($("#machinetypeListTemplate").render(result));
			$("#machine_type_id").val(result.machineInfo.machine_type_id);
			$("#area_id").val(result.machineInfo.area_id);
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
