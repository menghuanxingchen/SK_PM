/**
 * 报表
 * @returns
 */
//现在选择的设备
var nowMachine = '';
var st_time = getRequestParameterValue("st_time")+"-01";
var ed_time = getRequestParameterValue("st_time")+"-31";
var pot_id = getRequestParameterValue("pot_id");

$(function(){
	menu('menuA12');
	initPage();  	
});

function initPage(){
	$("#closeBtn").bind('click',function(){
		pageForward('/MaintResStatisController/potPartStopReport.do');
	});
	queryDataList();
}

//查询方法
function queryDataList(){

    layer.load();///添加进度条
    var parameterData = {
            url:getRequestUrl("/EmergentRepairInfoController/queryDataList.json"),
            successfunc:renderListDataFun,
            customArray:{"emergentRepairInfo.search_start":st_time,"emergentRepairInfo.search_end":ed_time,"emergentRepairInfo.pot_id":pot_id},
            pageller:"pagefoot",
            pageIndex:1,
            pageSize:10,
            checkedId:"checkboxGroupId",
            isAsync:false
    };
    pageBarFortable(parameterData)
}

//查询回调
var datalist;
function renderListDataFun(result){
    layer.closeAll('loading'); //关闭进度条
    $( "#meRepairList" ).html(
    		$("#meRepairTemplate").render(result)
    	);


}
/**
 * 跳转页面
 */
function gotoMachinePage(id){
	var page="/pm/web/maintainlog/potPartStopReport.jsp";
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

/**
 * pop回调
 * @returns
 */
function selectPotBack(obj) {
	$('#selPotId').val(obj.id);
	$('#selPotNm').val(obj.nm);
}

