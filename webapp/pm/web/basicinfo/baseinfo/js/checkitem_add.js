$(function(){
	initPage();	
});


function initPage(){
	menu('menu15');
	getPagaLists();	
}

function bindevent(){	
	//保存事件
	$("#saveBtn").bind('click',function(){
		saveFormData();
	});
	//返回
	$("#gobackBt").bind('click',function(){
		pageForward('/pm/web/basicinfo/baseinfo/checkitem_list.jsp');
	});
	// 类别类型联动
	$("#maspeid").change(function(){
		goforTypeList();
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
				url:getRequestUrl("/CheckItemController/saveCheckItemInfo.json"),
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
							lalert("save","success",function(){
								pageForward('/pm/web/basicinfo/baseinfo/checkitem_list.jsp');
							});
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
//得到页面下拉
function getPagaLists(){
	$.ajax({
		url:getRequestUrl("/MachineController/queryPageList.json"),
		dataType:"json",
		data:"",
		success:function(result){
			$("#machinespecies").html($("#machinespeciesListTemplate").render(result));
			$("#machinetype").html($("#machinetypeListTemplate").render(""));
			bindevent();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
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