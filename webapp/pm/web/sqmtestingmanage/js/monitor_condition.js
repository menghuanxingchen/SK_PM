$(function(){
	menu('menuD17');
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){

	bindevent();
	queryDataList("");//默认全部
	
	$("tr.clickableRow[hidden!='hidden']").hover(function(){
		$(this).addClass("hoverRow");
	},function(){
		$(this).removeClass("hoverRow");
	});
	
}

/**
 * 绑定事件
 */
function bindevent(){
	
	$("#tabfill tbody").on("click","tr",function() {
		var td = $(this).find("td");
		var CNFYMD = td.eq(1).text();
		var lineId = td.eq(6).text();
		var produnctCode = td.eq(4).text();
		var lotno = td.eq(10).text();
		

		var parameterData = {
				url:getRequestUrl("/TestResultController/queryDataList.json"),
				successfunc:renderTestResultListDataFun,
				customArray:{"testResult.lineId":lineId,"testResult.lotnumver":lotno,"testResult.produnctCode":produnctCode,"testResult.CNFYMD":CNFYMD},
				pageller:"pagefoot",
				pageIndex:1,
				pageSize:30,
				checkedId:"checkboxGroupId",
				isAsync:false
		};
		
		pageBarFortable(parameterData)
	});
}

/**
 * 查询
 */
function queryDataFun(fillstate){
	queryDataList(fillstate)
}

/**
 * 查询方法
 */
function queryDataList(fillstate){

	var datestr = timeStamp2String();
	
	var parameterData = {
			url:getRequestUrl("/SqmtestingManageController/queryFillingPlanInfoDataList.json"),
			successfunc:renderListDataFun,
			customArray:{"fillingPlanInfo.FILL_STATE":fillstate,"fillingPlanInfo.CNFYMD":datestr},
			pageller:"pagefoot",
			pageIndex:1,
			pageSize:15,
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

//回调
function renderTestResultListDataFun(result){
	
	$( "#test_r_content" ).html(
		
			$("#dataTestListTemplate").render( result
		          )
	);
	//奇偶行颜色不同
	$('.test_r_content tbody tr:odd').addClass('odd');
	//var table_height=$("#table_height").height();
	if(result.length>10)
	{
		$("#last").css({paddingRight:'25px'});
		
	}else{
		$("#last").css({paddingRight:'8px'});
	}
}


function timeStamp2String(){
    var datetime = new Date();
   
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + ''+ month +''+date;
}

