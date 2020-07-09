/**
 * 报表
 * @returns
 */
//现在选择的设备
var nowMachine = '';

$(function(){
	menu('menuA12');
	initPage();  	
});

function initPage(){
	bindevent();
	getInfoList();
}

function bindevent(){		
	var start = {
			  elem: '#st_date',
			  istoday: true,
			  format: 'YYYY-MM',
			  choose: function(datas){
				  $("#st_date").val(datas);
			  }
			};
	laydate(start);
	
	/*var end = {
			  elem: '#en_date',
			  istoday: true,
			  format: 'YYYY-MM',
			  choose: function(datas){
				  $("#en_date").val(datas);
			  }
			};
	laydate(end);*/
	
	$("#st_date").val(new Date().Format('yyyy-MM'));
	/*$("#en_date").val(new Date().Format('yyyy-MM'));*/
	
	$("#searchBt").bind('click',function(){
		if($("#st_date").val()==''){
			lalert('请选择日期','error');
			return false;
		}
		/*if($("#en_date").val()==''){
			lalert('请选择日期','error');
			return false;
		}*/
		getInfoList();
		initPageTame();
	});
	
}
/**
 * 查询提单列表
 */
function getInfoList(){
	var postData = collectData("search_area");
	nowMachine = $('#selPotNm').val();
	layer.load();//添加进度条
	
	var parameterData = {
			url:getRequestUrl("/MaintResStatisController/queryPotPartStopReport.json"),
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

	$("#t_r_content").html(
		$("#machineStopCountTemplate").render(result)
	);
	$('#infoList tr:odd').addClass('odd'); //奇数行样式
	$('#infoList tr:even').addClass('eve');//偶数行样式
	
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
 * 跳转页面
 */
function godetail(st_time,pot_id){
	var page="/pm/web/maintainlog/potPartStopReportDetail.jsp?st_time="+st_time+"&pot_id="+pot_id;
	pageForward(page);
}


/**
 * POP选择页弹出
 * @returns
 */
function selectPot() {
	var pathName = window.location.pathname;
    var webName = pathName.substring(0, pathName.indexOf('/',2));
	pagePopShow("一级设备选择",webName+"/MachinePotPartInfoController/selectPotPop","1000px","600px");
}

//初始化页面制御
function initPageTame(){
	//清除input按钮
	$('#selPotNm').bind('mouseover',function(){
		$('#clearPotA').css('visibility','');
	});
	$('#selPotNm').bind('mouseout',function(){
		$('#clearPotA').css('visibility','hidden');
	});
	$('#clearPotA').bind('mouseover',function(){
		$('#clearPotA').css('visibility','');
	});
	$('#clearPotA').bind('mouseout',function(){
		$('#clearPotA').css('visibility','hidden');
	});
}
/**
 * 清空一级设备选择
 * @returns
 */
function clearPot() {
	$('#selPotId').val('');
	$('#selPotNm').val('');
}
/**
 * pop回调
 * @returns
 */
function selectPotBack(obj) {
	$('#selPotId').val(obj.id);
	$('#selPotNm').val(obj.nm);
}

