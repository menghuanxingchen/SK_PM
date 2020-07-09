$(function(){
	menu('menu41');
	initPage();  	
});
var maitem_id=""
var machine_id="";
var sdate = "";
var edate="";
function initPage(){
	maitem_id =getRequestParameterValue("maitem_id");
	machine_id =getRequestParameterValue("machine_id");
	sdate =getRequestParameterValue("sdate");
	edate =getRequestParameterValue("edate");
	$("#start").val(sdate);
	$("#end").val(edate);
	getInfoList();
}

/**
 * 查询提单列表
 */
function getInfoList(){
	var postData = collectData("search_area");
	postData=$.extend(postData,{"maintResStatisDTO.maintenance_id":maitem_id});
	postData=$.extend(postData,{"maintResStatisDTO.machine_id":machine_id});
	
	layer.load();//添加进度条
	var parameterData = {
			url:getRequestUrl("/MaintResStatisController/queryRepOrderList.json"),
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
	$( "#infoList" ).html(
		$("#infoListTemplate").render(result)
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
 * 查询维修详情
 * @param id
 */
function gotoDetailPage(id){
	var page = "/pm/web/repair/repair_order_detail.jsp?id="+id+"&&menuid=menu41";
	pageForward(page);
}

