$(function(){
	menu('menuC16');
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
	$.ajax({
		url:getRequestUrl("/SvaluationManageController/beforeDataList.json"),
		dataType:"json",
		data:[],
		success:function(data){
			$( "#evaluationOneForm" ).html(
					$("#evaluationOneTemplate").render(data)
			);
			
			$( "#evaluationTwoForm" ).html(
					$("#evaluationTwoTemplate").render(data)
			);
			
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
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
			  format: 'YYYY'
			};
	
	laydate(start);
	
	var myDate = new Date();
	
	$("#start").val(myDate.getFullYear());//开始日期当前年1月
	
	$("#search_btn").bind('click',queryDataList);
}

/**
 * 查询方法
 */
function queryDataList(){

	var postData = collectData("search_area");

	var parameterData = {
			url:getRequestUrl("/SvaluationManageController/queryEvaluationResultDataList.json"),
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


