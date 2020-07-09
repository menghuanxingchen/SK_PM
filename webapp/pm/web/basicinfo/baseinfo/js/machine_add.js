$(function(){
	initPage();
});


function initPage(){
	menu('menu14');
	getPagaLists();	
	
}

function bindevent(){	
	//日期插件
	laydate({
		  elem: '#datef' //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		});
	
	//保存事件
	$("#saveBtn").bind('click',function(){
		saveFormData();
	});
	//返回
	$("#gobackBt").bind('click',function(){
		pageForward('/pm/web/basicinfo/baseinfo/machine_list.jsp');
	});
	// 类别类型联动
	$("#maspeid").change(function(){
		goforTypeList();
	});
	
}
//联动得到类型
function goforTypeList(){
	var postData = {"sysCodeInfo.code_value":$("#maspeid").val(),"sysCodeInfo.code_group_value":Class.getConstant('MACH_TYPE_CODE')};
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
//得到页面下拉
function getPagaLists(){
	$.ajax({
		url:getRequestUrl("/MachineController/queryPageList.json"),
		dataType:"json",
		data:"",
		success:function(result){
			$("#machinespecies").html($("#machinespeciesListTemplate").render(result));
			$("#machinetype").html($("#machinetypeListTemplate").render(""));
			$("#areaid").html($("#areaListTemplate").render(result));	
			bindevent();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
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
				url:getRequestUrl("/MachineController/saveMachineInfo.json"),
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
								  //time: 1500, //2秒后自动关闭
								});
						}else{
							lalert("save","success");
							pageForward('/pm/web/basicinfo/baseinfo/machine_list.jsp');
						}
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


