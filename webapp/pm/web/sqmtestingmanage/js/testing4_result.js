$(function(){
	menu('menuD18');
	initPage();
});
function getProcessorId(){
	var commonData = parent.CsGlobalObj||parent.parent.CsGlobalObj;
	var id =commonData.currentUserid||"";
	return id;
}
var currentUserid =getProcessorId();
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
	var start = {
			  elem: '#start',
			  istoday: true,
			  format: 'YYYY-MM-DD'
			};
	
	var end = {
			  elem: '#end',
			  istoday: true,
			  format: 'YYYY-MM-DD'
			};
	
	laydate(start);
	
	laydate(end);
	
	var myDate = new Date();
	
//	$("#start").val(myDate.getFullYear());//开始日期当前年1月
//	$("#end").val(myDate.getFullYear());//开始日期当前年1月
	
	$("#search_btn").bind('click',queryDataList);
}

/**
 * 查询方法
 */
function queryDataList(){

	var postData = collectData("search_area");

	var parameterData = {
			url:getRequestUrl("/SqmtestingManageController/query4DataList.json"),
			successfunc:renderListDataFun,
			customArray:postData,
			pageller:"pagefoot",
			pageIndex:1,
			pageSize:50,
			checkedId:"checkboxGroupId",
			isAsync:false
	};
	
	pageBarFortable(parameterData)
}
var excelList;
//回调
function renderListDataFun(result){
	
	excelList = result;
	
	for(var i=0;i<result.length;i++){
		result[i]['currentUserid']=currentUserid;
	}
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

function detailEntityDataFun(lineId,lotnumver,produnctCode){
	var page = "/pm/web/sqmtestingmanage/testing4_resultdetail.jsp?lineId="+lineId+"&&lotnumver="+lotnumver+"&&produnctCode="+produnctCode;
	pageForward(page);
}
