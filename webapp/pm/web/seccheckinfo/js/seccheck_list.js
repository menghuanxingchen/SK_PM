$(function(){
	menu('menu26');
	initPage();
});


/**
 * 初始化页面
 * */
function initPage(){
	bindevent();
	downList();
}

/**
 * 绑定事件
 */
function bindevent(){
//	$("#search_btn").bind('click',queryDataList);
	laydate({
		  elem: '#search_area .laydate-icon', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		  event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	});
}

/**
 * 安全检查信息查询
 */
function queryDataList(){
	var postData = collectData("search_area");
	var parameterData = {
			url:getRequestUrl("/SecCheckInfoController/querySecCheckInfoList.json"),
			successfunc:renderListDataFun,
			customArray:postData,
			pageller:"pagefoot",
			pageIndex:1,
			pageSize:50,
//			checkedId:"",
			isAsync:false
	};
	pageBarFortable(parameterData)
}

//回调
function renderListDataFun(result){
	laydate({
		elem: '#start_dt', 
		event: 'focus'
	});
	laydate({
		elem: '#end_dt', 
		event: 'focus'
	});
	$( "#t_r_content" ).html(
		$("#reapirOrderListTemplate").render(result)
	);
	//奇偶行颜色不同
	$('.t_r_t tbody tr:odd').addClass('odd');
	$('.t_r_t tbody tr:even').addClass('eve');
	//var table_height=$("#table_height").height();
	if(result.length>10)
	{
		$("#last").css({paddingRight:'25px'});
		
	}else{
		$("#last").css({paddingRight:'8px'});
	}
}

/**
 * 新增
 */
function addDataFun(){
	var page = "/pm/web/seccheckinfo/seccheck_add.jsp";
	pageForward(page);
}

/**
 * 修改
 * @param id
 */
function updateDataFun(sec_check_id){
	var message = "确认修改？";
	layer.confirm(
			message,
			function(index){
				layer.close(index);
				var page = "/pm/web/seccheckinfo/seccheck_update.jsp?sec_check_id="+sec_check_id+"";
				pageForward(page);
			},
			function(index){
				layer.close(index);
				return;
			}
	);
}
/**
 * 删除信息
 * @param id
 */
function deleteDataFun(sec_check_id){
	var message = "确认删除？";
	layer.confirm(
			message,
			function(index){
				$.ajax({
					url:getRequestUrl("/SecCheckInfoController/deleteSecCheckInfoData.json"),
					dataType:"json",
					data:{"secCheckInfo.sec_check_id":sec_check_id},
					success:function(result){
						layer.close(index);
						lalert('delete','success');
						queryDataList();
					},
					error:function(error){
						layer.close(index);
						lalert('网络原因操作失败！','error');
					}
				});
			},
			function(index){
				layer.close(index);
				return;
			}
	);
}




/**
 * 下拉列表
 * */
function downList(){
	$.ajax({
		url:getRequestUrl("/SecCheckInfoController/queryDownList.json"),
		dataType:"json",
		data:"",
		success:function(result){
			$("#repairPlace").html($("#repairPlacedownListTemplate").render(result));
			queryDataList();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}