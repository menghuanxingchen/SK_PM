$(function(){
	menu('menuF10');
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
			  elem: '#movingStdate',
			  istoday: true,
			  format: 'YYYY-MM-DD'
			};
	
	var end = {
			  elem: '#movingEddate',
			  istoday: true,
			  format: 'YYYY-MM-DD'
			};
	
	laydate(start);
	
	laydate(end);
	
	var myDate = new Date();
	var preDate = new Date(myDate.getTime() - 24*60*60*1000); //前一天
	var nextDate = new Date(myDate.getTime() + 24*60*60*1000); //后一天
	$("#movingStdate").val(preDate.format("yyyy-MM-dd"))
	$("#movingEddate").val(nextDate.format("yyyy-MM-dd"))
	
//	$("#start").val(myDate.getFullYear());//开始日期当前年1月
//	$("#end").val(myDate.getFullYear());//开始日期当前年1月
	
	$("#search_btn").bind('click',queryDataList);
}

/**
 *对Date的扩展，将 Date 转化为指定格式的String
 *月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
 *年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
 *例子：
 *(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
 *(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
 */
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

/**
 * 查询方法
 */
function queryDataList(){

	var postData = collectData("search_area");

	var parameterData = {
			url:getRequestUrl("/MovingListController/queryDataList.json"),
			successfunc:renderListDataFun,
			customArray:postData,
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
	for(var i=0;i<result.length;i++){
		result[i]['currentUserid']=currentUserid;
	}
		$( "#t_r_content" ).html(
			
			$("#dataListTemplate").render( result)
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


function deleteEntityDataFun(movingNo){
	
	var message = "确认删除？";
	layer.confirm(
			message,
			function(index){
				$.ajax({
					url:getRequestUrl("/MovingListController/deleteMovingInfoData.json"),
					dataType:"json",
					data:{"movingInfo.movingNo":movingNo},
					success:function(result){
						layer.close(index);
						layer.closeAll('loading');
						lalert('delete','success');
						queryDataList();
					},
					error:function(error){
						layer.close(index);
						layer.closeAll('loading');
						lalert('网络原因操作失败！','error');
					}
				});
				layer.close(index);
			},
			function(index){
				layer.close(index);
				return;
			}
	);
	
	
}
function detailEntityDataFun(movingNo,movingType){
	if(movingType=="搬入证"){
		pageForward('/MovingListController/movingInDetailJsp.do?movingNo='+movingNo);
	}else{
		pageForward('/MovingListController/movingOutDetailJsp.do?movingNo='+movingNo);
	}
}

function applyEntityDataFun(movingNo,movingType){
	if(movingType=="搬入证"){
		pageForward('/MovingListController/movingInDetailJsp.do?movingNo='+movingNo+'&flag=1');
	}else{
		pageForward('/MovingListController/movingOutDetailJsp.do?movingNo='+movingNo+'&flag=1');
	}
}

function confirmEntityDataFun(movingNo,movingType){
	if(movingType=="搬入证"){
		pageForward('/MovingListController/movingInDetailJsp.do?movingNo='+movingNo+'&flag=2');
	}else{
		pageForward('/MovingListController/movingOutDetailJsp.do?movingNo='+movingNo+'&flag=2');
	}
}

function movingOutApprove(movingNo,movingPerson,movingCompany,movingCarno,movingPhone){
	pageForward('/MovingOutController/defaultJsp.do?movingNo='+movingNo+
			'&movingPerson='+movingPerson+'&movingCompany='+movingCompany+'&movingCarno='+movingCarno+'&movingPhone='+movingPhone);
}

function movingInApprove(movingNo){
	pageForward('/MovingInController/defaultJsp.do?movingNo='+movingNo);
}