$(function(){
	initPage();
});
var id="";
var machine_sp="";
/**
 * 初始化页面
 * */
function initPage(){
	id = getRequestParameterValue("id");
	machine_sp =getRequestParameterValue("machine_sp");	
	goforTypeList();
	getCodeLists();
	bindevent();
	queryDataList();
}

/**
 * 绑定事件
 */
function bindevent(){
	$("#search_btn").bind('click',queryDataList);
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
	postData=$.extend(postData,{"prePlanMachineInfo.pre_plan_id":id},{"prePlanMachineInfo.area_id":$("#maAreafram").attr("value")},{"prePlanMachineInfo.machine_type_id":$("#matypefram").attr("value")});
	$.ajax({
		url:getRequestUrl("/PrePlanInfoController/queryMachListMList.json"),
		dataType:"json",
		data:postData,
		success:function(result){
			layer.closeAll('loading');
			$("#planNm").val(result.prePlanInfoEo.pre_plan_nm);
			$("#date").val(result.prePlanInfoEo.pre_plan_date);
			$( "#dataListForm" ).html(
					$("#dataListTemplate").render(result.data)
			);
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * 跳转检查项目
 */
function goToCheckItemDataFun(m_id,machNm){
	var page = "/pm/mobile/m_preplaninfo/m_preplan_checkitem_list.jsp?id="+id+"&m_id="+m_id;
	pageForward(page);
}

/**
 * 跳转计划列表
 */
function goBack(){
	var page = "/pm/mobile/m_preplaninfo/menu_m.jsp?id="+id;
	pageForward(page);
}