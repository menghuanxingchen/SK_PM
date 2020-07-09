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
	queryDataList();
}

function bindevent(){		
	var date_opt=dateBuild();
	$("#start_dt").val('').scroller('destroy').scroller($.extend(date_opt['date'], date_opt['def']));
	$("#end_dt").val('').scroller('destroy').scroller($.extend(date_opt['date'], date_opt['def']));
	
//	var d = new Date(); //实例一个时间对象
//	var date =  d.getFullYear()+"-"+(d.getMonth()>9?d.getMonth():"0"+(d.getMonth()))+"-"+(d.getDate()>9?d.getDate():"0"+d.getDate());

	$("#start_dt").val(CurentTime());
	$("#end_dt").val( CurentTime());
	
	$("#repair_detail").on('input',function(e){  
		queryDataList();
	});
	$("#start_dt").change(queryDataList);
	$("#end_dt").change(queryDataList);
}

/**
 * 查询方法
 */
function queryDataList(){
	 layer.open({type: 2});
	var postData = collectData("search_area");
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/queryRepairOrderInfoListMob.json"),
		dataType:"json",
		data:postData,
		success:function(result){
			layer.closeAll('loading');
			$( "#dataListForm" ).html(
					$("#dataListTemplate").render(result.data)
			);
		},
		error:function(error){
			layer.closeAll('loading');
			lalert('网络原因操作失败！','error');
		}
	});
}

//跳转machineList页面
function goToMachDataFun(id){
	pageForward('/pm/mobile/m_repair/m_repair_add.jsp?id='+id);
}

//跳转材料费添加页面
function goToSaveDataFun(){
	pageForward('/pm/mobile/m_repair/m_repair_order_add.jsp');
}

/**
 * 跳转首页
 */
/*function goBack(){
	var page = "/pm/mobile/main/index_m.jsp";
	pageForward(page);
}*/