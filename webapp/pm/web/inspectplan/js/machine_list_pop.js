$(function(){
	initPage();  	
	bindevent();
});

var ids="";
var spid="";
var tyid="";
function initPage(){
	ids =getRequestParameterValue("ids");
	spid =getRequestParameterValue("spid");
	tyid =getRequestParameterValue("tyid");
	getCodeLists();	
	
}

function bindevent(){	
	$("#checkGroupId").bind('click',function(){
		checkedAllSimple("checkGroupId");
	});
	
	$("#importMachineListBt").bind('click',function(){
		importMachineAddPage();
	});
	$("#searchBt").bind('click',function(){
		getMachineList();
	});
	// 类别类型联动
	$("#machinespecies").change(function(){
		goforTypeList();
	});
}

//得到页面下拉
function getCodeLists(){
	var postData={"sysCodeInfo.code_value":spid};
	$.ajax({
		url:getRequestUrl("/MachineController/queryPageListForPop.json"),
		dataType:"json",
		data:postData,
		success:function(result){
			$("#machinespecies").html($("#machinespeciesListTemplate").render(result));
			$("#machinetype").html($("#machinetypeListTemplate").render(result));
			$("#areaid").html($("#areaListTemplate").render(result));
			$("#species_id").val(spid);//spid
			$("#type_id").val(tyid);//tyid
			getMachineList();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}
/**
 * 查询提单列表
 */
function getMachineList(){
	var postData = collectData("search_area");
	var newids = buildNewIds(ids);//将id字符串每个值加‘’
	postData=$.extend(postData,{"ids":newids});
	layer.load();//添加进度条
	var parameterData = {
			url:getRequestUrl("/MachineController/queryMachineListPop.json"),
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
	
}
//向父页面传值，关闭layer
function importMachineAddPage(){	
	var re = buildIdsAndNames("checkGroupId");//得到页面选中的数据形成字符串
	if(re==""){
		layer.alert('请选择至少一条数据');
		$("#checkGroupId").prop("checked",false);
	}else{
		var species =$('input[name="checkGroupId"]:checked').eq(0).attr("spval");
		var typeid =$('input[name="checkGroupId"]:checked').eq(0).attr("typeval");
		if(spid!=""&&tyid!=""&&(species!=spid||typeid!=tyid)){
			 layer.confirm("设备类别或类型发生改变，表单将清空，确定导入？", {
			    btn: ['确定','取消'] //按钮
			}, function(){	
				window.parent.importMainfo(re[0],re[1],species,typeid,"Y");//调用父页面方法
				parent.layer.closeAll(); 				
			})
		}else{					
			window.parent.importMainfo(re[0],re[1],species,typeid,"N");//调用父页面方法
			parent.layer.closeAll();    
		}	
		
	}
}

//联动得到类型
function goforTypeList(){
	var postData = {"sysCodeInfo.code_value":$("#species_id").val(),"sysCodeInfo.code_group_value":Class.getConstant('MACH_TYPE_CODE')};
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