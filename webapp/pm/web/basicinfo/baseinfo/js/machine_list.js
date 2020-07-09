$(function(){
	menu('menu14');
	initPage();  	
	bindevent();
});


function initPage(){
	getCodeLists();
	getMachineList();
}

function bindevent(){		
	//跳转到新增页面
	$("#gotoAddpageBt").bind('click',function(){
		pageForward('/pm/web/basicinfo/baseinfo/machine_add.jsp');
	});
	$("#searchBt").bind('click',function(){
		getMachineList();
	});
	// 类别类型联动
	$("#machinespecies").change(function(){
		goforTypeList();
	});
}
/**
 * 查询提单列表
 */
function getMachineList(){
	var postData = collectData("search_area");
	layer.load();//添加进度条
	var parameterData = {
			url:getRequestUrl("/MachineController/queryMachineListNames.json"),
			successfunc:renderListDataFun,
			customArray:postData,
			pageller:"pagefoot",
			pageIndex:1,
			pageSize:50,
			isAsync:false
	};
	pageBarFortable(parameterData);
}
/**
 * 查询回调
 * @param result
 */
function renderListDataFun(result){
	$( "#machineList" ).html(
		$("#machineListTemplate").render(result)
	);
	$('#machineList tr:odd').addClass('odd'); //奇数行样式
	$('#machineList tr:even').addClass('eve');//偶数行样式
	
	layer.closeAll('loading'); //关闭进度条
	
	//var table_height=$("#table_height").height();
	if(result.length>10)
	{
		$("#last").css({paddingRight:'25px'});
		
	}else{
		$("#last").css({paddingRight:'8px'});
	}
}
/**
 * 删除计划 
 */
function deleteMachine(id){
	layer.confirm('是否删除？', {
	    btn: ['确定','取消'] //按钮
	}, function(){			
		$.ajax({
			url:getRequestUrl("/MachineController/deleteMachine.json?id="+id),
			dataType:"json",
			success:function(result){
				if(result.opflag){
					lalert("delete","success");
					getMachineList();
				}
			},
			error:function(error){
				lalert("delete","error");
			}
		});
	});
}

/**
 * 跳转到update页面
 */
function gotoUpdate(id){
	var page="/pm/web/basicinfo/baseinfo/machine_update.jsp?machine_id="+id;
	pageForward(page);
}

//得到页面下拉
function getCodeLists(){
	$.ajax({
		url:getRequestUrl("/MachineController/queryPageList.json"),
		dataType:"json",
		data:"",
		success:function(result){
			$("#machinespecies").html($("#machinespeciesListTemplate").render(result));
			$("#machinetype").html($("#machinetypeListTemplate").render(result));
			$("#areaid").html($("#areaListTemplate").render(result));
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
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