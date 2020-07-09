$(function(){
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
}

/**
 * 查询方法
 */
function queryDataList(){
	var postData = collectData("search_area");
	var parameterData = {
			url:getRequestUrl("/TsLmsPutwareController/queryDataList.json"),
			successfunc:renderListDataFun,
			customArray:postData,
			pageller:"pagefoot",
			pageIndex:1,
			pageSize:10,
			checkedId:"checkboxGroupId",
			isAsync:false
	};
	pageBarFortable(parameterData)
}

//回调
function renderListDataFun(result){
	$( "#dataListId" ).html(
		$("#dataListTemplate").render(result)
	);
	//奇偶行颜色不同
	$('.tablelist tbody tr:odd').addClass('odd');
}

/**
 * 新增
 */
function addEntityDataFun(){
	var page = "/pm/tslms/putware/putware_add.jsp";
	pageForward(page);
}

/**
 * 修改
 * @param id
 */
function updateEntityDataFun(id){
	var page = "/pm/tslms/putware/putware_update.jsp?putid="+id;
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
					url:getRequestUrl("/TsLmsPutwareController/deleteEntityData.json"),
					dataType:"json",
					data:{"tsLmsPutware.putid":id},
					success:function(result){
						layer.alert("删除成功",1);
						queryDataList();
					},
					error:function(error){
						alert("error");
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

