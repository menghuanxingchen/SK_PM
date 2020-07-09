$(function(){
	initPage();	
});


function initPage(){
	menu('menu32');
	getPagaLists();
	bindevent();
}

function bindevent(){	
	//日期插件
	laydate({
		 elem: '#datef', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		istime:true,
		format: 'YYYY-MM-DD'
		});
	$("#datef").val(laydate.now());
	//保存事件
	$("#saveBtn").bind('click',function(){
		saveFormData();
	});
	//返回
	$("#gobackBt").bind('click',function(){
		pageForward('/pm/web/maintainlog/maintainlog_list.jsp');
	});
	
	bindValidateEvent('addform');
	
}


function saveFormData(){
	var checkflag =	checkFormValue('addform');
	if(checkflag){
		layer.confirm("是否提交", {
		    btn: ['确定','取消'] //按钮
		}, function(){
			var postData = collectData("addform");
			$.ajax({
				url:getRequestUrl("/MaintainLogController/saveMaintainLogInfo.json"),
				dataType:"json",
				data:postData,
				success:function(result){
					if(result.opflag){
						lalert("save","success",function(){
							pageForward('/pm/web/maintainlog/maintainlog_list.jsp');
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
		url:getRequestUrl("/MaintainLogController/queryPageList.json"),
		dataType:"json",
		data:"",
		success:function(result){
			$("#mtype").html($("#mltypeListTemplate").render(result));
			$("#subdeptName").val(result.subDept.code_nm);
			$("#subdeptId").val(result.subDept.code_value);
			$("#machineList").html($("#maListTemplate").render(result));
			
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

