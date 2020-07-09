$(function(){
	menu('menuB13');
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
	initPageTame();
	bindevent();
	queryDataList();
}

/**
 * 绑定事件
 */
function bindevent(){
	$.ajax({
		url:getRequestUrl("/TestingProcessInfoController/beforeDataList.json"),
		dataType:"json",
		data:[],
		success:function(data){
			$( "#lineTypeForm" ).html(
					$("#lineListTemplate").render(data)
			);
			$("#lineList").val("1");
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
	
	//check all
	$("#checkGroup").bind('click',function(){
		checkedAllSimple("checkGroup");
	});
	$("#search_btn").bind('click',queryDataList);
}

/**
 * 查询方法
 */
function queryDataList(){

	var postData = collectData("search_area");

	var parameterData = {
			url:getRequestUrl("/SqmmanageController/queryProductDataList.json"),
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

//回调
function renderListDataFun(result){
	
	$("#maPotList").html(
		
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

/**
 * POP选择页弹出
 * @returns
 */
function selectPot() {
	var pathName = window.location.pathname;
    var webName = pathName.substring(0, pathName.indexOf('/',2));
	pagePopShow("设备选择",webName+"/MachinePotPartInfoController/selectPotPop","1000px","600px");
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

	$("body").trigger("");
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

//打印
function printDataFun(){
	var lineName=$('#lineList option:selected').text();
	var res=buildIdsBatch("checkGroup");//用于返回 选中条数  和id用，隔开的字符串
	if(res==""){
		layer.alert('请选择至少一条数据');
		$("#checkGroup").prop("checked",false);
	}else{
		var len = res[0];
		var idsStr = buildNewIds(res[1]);//将id字符串加‘’		
		var postData={"machinePotPartInfo.pot_part_id":idsStr,"lineName":lineName};
		$.ajax({
			url:getRequestUrl("/SqmmanageController/printqrCodeBatch.json"),
			dataType:"json",
			type:"POST",
			data:postData,
			success:function(result){
				if(result.opflag){
					lalert("print","success");
				}else{
					lalert("print","error");
				}
				
			},
			error:function(error){
				alert("error");
			}
		});
	}
}
