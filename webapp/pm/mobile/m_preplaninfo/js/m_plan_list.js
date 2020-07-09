$(function(){
		initPage();
});
/**
 * 初始化页面
 * */
function initPage(){
	bindevent();
	getCodeLists();
	queryDataList();
}

/**
 * 绑定事件
 */
function bindevent(){
	var date_opt=dateBuild();
	$("#start").val('').scroller('destroy').scroller($.extend(date_opt['date'], date_opt['def']));
	$("#end").val('').scroller('destroy').scroller($.extend(date_opt['date'], date_opt['def']));
    $("#start").val(CurentTime());
	$("#end").val( CurentTime());
	
	$("#search_btn").bind('click',queryDataList);
	$("#start").change(queryDataList);
	$("#end").change(queryDataList);
}
//得到页面下拉
function getCodeLists(){
	$.ajax({
		url:getRequestUrl("/InspectPlanController/queryPageList.json"),
		dataType:"json",
		data:{"TYPE":"MB","user_num":"lg00691","user_num":"1","loginType":"Mobile","user_nm":"赵柏宁","dept_code":"3","position_id":"3","sub_dept_code":"2","user_id":"01e06e7d63d242108774acf85dad6343"},
		success:function(result){			
			buildPop(result.stateList,"状态","statepop","statefram","pop1");			
			buildPop(result.machinespeciesList,"设备类别","maspeciespop","maspeciesfram","pop2");
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
	postData=$.extend(postData,{"prePlanInfo.pre_plan_state":$("#statefram").attr("value")},{"prePlanInfo.machine_species_id":$("#maspeciesfram").attr("value")});
	$.ajax({
		url:getRequestUrl("/PrePlanInfoController/queryDataListMList.json"),
		dataType:"json",
		data:postData,
		success:function(result){
			layer.closeAll('loading');
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
 * 跳转设备列表
 */
function goToMachDataFun(id,sp){
	var page = "/pm/mobile/m_preplaninfo/menu_m.jsp?id="+id+'&machine_sp='+sp;
	pageForward(page);
}

/**
 * 跳转首页
 */
/*function goBack(){
	var page = "/pm/mobile/main/index_m.jsp";
	pageForward(page);
}*/