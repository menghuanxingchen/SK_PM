$(function(){
	//页面初始化的事件
	bindevent();
	initPage();  	
	
});

var plan_id="";
var machine_sp="";
function initPage(){	
	plan_id =getRequestParameterValue("plan_id");	
	machine_sp =getRequestParameterValue("machine_sp");	
	goforTypeList();
	getCodeLists();
	queryDataList();
	
}

function bindevent(){		
	//跳回
	$("#backDiv").bind('click',function(){
		pageForward('/pm/mobile/m_inspectplan/m_inspectplan_list.jsp');
	});
	
}
//得到页面下拉
function getCodeLists(){
	$.ajax({
		url:getRequestUrl("/MachineController/queryPageListForPop.json"),
		dataType:"json",
		data:"",
		success:function(result){
			buildPop(result.areaList,"区域","maAreapop","maAreafram","pop1");				
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}
//联动得到类型
function goforTypeList(){
	var postData = {"sysCodeInfo.code_value":machine_sp,"sysCodeInfo.code_group_value":Class.getConstant('MACH_TYPE_CODE')};
	$.ajax({
		url:getRequestUrl("/MachineController/getSubCodeList.json"),
		dataType:"json",
		data:postData,
		success:function(result){
			buildPop(result.infoList,"设备类型","matypepop","matypefram","pop2");
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}
/**
 * 查询方法
 */
function queryDataList(){
	layer.open({type: 2});
	var postData = collectData("search_area");
	postData=$.extend(postData,{"insPlanMachineInfo.ins_plan_id":plan_id},{"insPlanMachineInfo.area_id":$("#maAreafram").attr("value")},{"insPlanMachineInfo.machine_type_id":$("#matypefram").attr("value")});
	$.ajax({
		url:getRequestUrl("/InspectPlanController/queryInspectMachineListMob.json"),
		dataType:"json",
		data:postData,
		success:function(result){
			layer.closeAll('loading');
			$("#ins_plan_nm").val(result.insPlanInfo.ins_plan_nm);
			$("#ins_plan_date").val(result.insPlanInfo.ins_plan_date);
			$( "#dataListForm" ).html(
					$("#dataListTemplate").render(result.machineInfoList)
			);
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

//跳转machineList页面
function goToMachDataFun(id){
	pageForward('/pm/mobile/m_inspectplan/m_inspect_checkitem_list.jsp?plan_machine_id='+id);
}

