var jumpType = getRequestParameterValue("jumpType");
var TYPE = getRequestParameterValue("TYPE");
var user_num = getRequestParameterValue("sysUserInfo.user_num");
var pwd = getRequestParameterValue("sysUserInfo.pwd");
$(function(){
	if(jumpType=='MB'){
		login();
	}
		initPage();
});
function login(){
	var postData = {"sysUserInfo.user_num":user_num,"sysUserInfo.pwd":pwd};
	    postData=$.extend(postData,{"sysUserInfo.loginType":TYPE});
	$.ajax({
		url:path+"/loginManagerController/checkSysUserInfo.json",
		dataType:"json",
		data:postData,
		success:function(result){
			if(result.opflag){
				if(result.sysUserinfo){
					currentUserId = result.sysUserinfo.user_num;
					initPage();
				}
			}else{
				lalert(result.opmessage,'error');
			}
		},
		error:function(error){
			lalert('网络原因操作失败','error');
		}
	});
}

function initPage(){	
	bindevent();
	getCodeLists();
	queryDataList();
}

function bindevent(){	
	var date_opt=dateBuild();
	$("#start").val('').scroller('destroy').scroller($.extend(date_opt['date'], date_opt['def']));
	$("#end").val('').scroller('destroy').scroller($.extend(date_opt['date'], date_opt['def']));
	$("#start").val(CurentTime());
	$("#end").val(CurentTime());
	
	//$("#ins_plan_nm").bind("onblur",queryDataList);
	$("#ins_plan_nm").on('input',function(e){  
		queryDataList();
	});
	$("#start").change(queryDataList);
	$("#end").change(queryDataList);
}
//得到页面下拉
function getCodeLists(){
	$.ajax({
		url:getRequestUrl("/InspectPlanController/queryPageList.json"),
		dataType:"json",
		data:"",
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
	postData=$.extend(postData,{"insPlanInfo.ins_plan_state":$("#statefram").attr("value")},{"insPlanInfo.machine_species_id":$("#maspeciesfram").attr("value")});
	$.ajax({
		url:getRequestUrl("/InspectPlanController/queryInspectplanListMob.json"),
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

//跳转machineList页面
function goToMachDataFun(id,sp){
	pageForward('/pm/mobile/m_inspectplan/m_inspect_machine_list.jsp?plan_id='+id+'&machine_sp='+sp);
}

/**
 * 跳转首页
 */
/*function goBack(){
	var page = "/pm/mobile/main/index_m.jsp";
	pageForward(page);
}*/