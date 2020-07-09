$(function(){
	menu('menu71');
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
	$("#search_btn").bind('click',queryDataList);
}

/**
 * 查询方法
 */
function queryDataList(){
	var postData = collectData("search_area");
	var parameterData = {
			url:getRequestUrl("/RoleInfoController/queryDataList.json"),
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
 * 新增
 */
function addEntityDataFun(){
	var page = "/pm/web/basicinfo/authinfo/roleinfo_add.jsp";
	pageForward(page);
}

/**
 * 修改
 * @param id
 */
function updateEntityDataFun(id){
	var page = "/pm/web/basicinfo/authinfo/roleinfo_update.jsp?id="+id;
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
					url:getRequestUrl("/RoleInfoController/deleteEntityData.json"),
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
			function(index){
				layer.close(index);
				return;
			}
	);
}

