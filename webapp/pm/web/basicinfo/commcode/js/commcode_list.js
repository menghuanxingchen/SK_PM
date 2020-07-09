$(function(){
	menu('menu12');
	initPage();
	bindevent();
});


/**
 * 初始化页面
 * */
function initPage(){
	queryDataList();
}

/**
 * 绑定事件
 */
function bindevent(){

	$("#search_btn").bind('click',queryDataList);
	laydate({
		  elem: '#search_area .laydate-icon', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		  event: 'focus' //响应事件。如果没有传入event，则按照默认的click
		});

}

/**
 * 查询方法
 */
function queryDataList(){
	var postData = collectData("search_area");
	var parameterData = {
			url:getRequestUrl("/SysCodeInfoController/queryDataList.json"),
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
	$( "#t_r_content" ).html(
		$("#dataListTemplate").render(result)
	);
	//奇偶行颜色不同
	$('.tt tr:odd').addClass('odd');
	$('.tt tr:even').addClass('eve');
}

/**
 * 新增
 */
function addEntityDataFun(){
	var page = "/pm/web/basicinfo/commcode/commcode_add.jsp";
	pageForward(page);
}

/**
 * 修改
 * @param id
 */
function updateEntityDataFun(id){
	var page = "/pm/web/basicinfo/commcode/commcode_update.jsp?code_id="+id;
	pageForward(page);
}

/**
 * 删除信息
 * @param id
 */
function deleteDataFun(id){
	var message = "确认删除？";
	layer.confirm(
			message,
			function(index){
				$.ajax({
					url:getRequestUrl("/SysCodeInfoController/deleteEntityData.json"),
					dataType:"json",
					data:{"sysCodeInfo.code_id":id},
					success:function(result){
						lalert("delete","success");
						queryDataList();
					},
					error:function(error){
						lalert('网络原因操作失败！','error');
					}
				});
				layer.close(index);
			},
			"信息",
			function(index){
				layer.close(index);
				return;
			}
	);
}

