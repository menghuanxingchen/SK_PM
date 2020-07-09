$(function(){
	menu('menuD20');
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){

	bindevent();
	queryDataList();
}

/**
 * 绑定事件
 */
function bindevent(){
	
}

/**
 * 查询方法
 */
function queryDataList(){
	
	var td = $(this).find("td");
	var lineId = getUrlParam("lineId");
	var produnctCode = getUrlParam("produnctCode");
	var lotno = getUrlParam("lotnumver");
	

	var parameterData = {
			url:getRequestUrl("/TestResultController/queryDataList.json"),
			successfunc:renderListDataFun,
			customArray:{"testResult.lineId":lineId,"testResult.lotnumver":lotno,"testResult.produnctCode":produnctCode},
			pageller:"pagefoot",
			pageIndex:1,
			pageSize:30,
			checkedId:"checkboxGroupId",
			isAsync:false
	};
	
	pageBarFortable(parameterData)
}

//回调
function renderListDataFun(result){
	
	$( "#t_r_content" ).html(
		
			$("#dataListTemplate").render( result
	)
	);
	//奇偶行颜色不同
	$('.t_r_content tbody tr:odd').addClass('odd');
	//var table_height=$("#table_height").height();
	if(result.length>10)
	{
		$("#last").css({paddingRight:'25px'});
		
	}else{
		$("#last").css({paddingRight:'8px'});
	}
}

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}