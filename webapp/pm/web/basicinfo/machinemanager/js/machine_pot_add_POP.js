var PopXpx = getRequestParameterValue("PopXpx");

$(function(){
	initPage();  	
	bindevent();
});


function initPage(){
	getMaTypeList();
	$(".contents").css("width",PopXpx);
}

function bindevent(){		
	$("#searchBt").bind('click',function(){
		getMaTypeList();
	});
}
/**
 * 查询提单列表
 */
function getMaTypeList(){
	var postData = collectData("search_area");
	layer.load();//添加进度条
	var parameterData = {
			url:getRequestUrl("/MachinePotInfoController/queryDataList.json"),
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
	$( "#maPotList" ).html(
		$("#maPotListTemplate").render(result)
	);
	$('#checkitemList tr:odd').addClass('odd'); //奇数行样式
	$('#checkitemList tr:even').addClass('eve');//偶数行样式
	
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
 * 获取选择信息
 * @returns
 */
function returnInfo(id,nm) {
	var obj = {'id':id,'nm':nm};
	closePOP(obj);
}

/**
 * 关闭POP
 * @returns
 */
function closePOP(obj) {
	window.parent.selectPotBack(obj);
	parent.layer.closeAll() //关闭所有弹窗
}