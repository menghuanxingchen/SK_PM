$(function(){
	menu('menuB12');
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
	$.ajax({
		url:getRequestUrl("/TestingProcessInfoController/beforeDataList.json"),
		dataType:"json",
		data:[],
		success:function(data){
			$( "#lineTypeForm" ).html(
					$("#lineListTemplate").render(data)
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
	
	$("#search_btn").bind('click',queryDataList);
}

/**
 * 查询方法
 */
function queryDataList(){

	var postData = collectData("search_area");

	var parameterData = {
			url:getRequestUrl("/TestingProcessInfoController/queryDataList.json"),
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
	var page = "/pm/web/sqmmanage/testing_process_info_add.jsp";
	pageForward(page);
}

/**
 * 修改
 * @param id
 */
function updateEntityDataFun(id){
	var page = "/pm/web/sqmmanage/testing_process_info_upd.jsp?id="+id;
	pageForward(page);
}

/**
 * 删除信息
 * @param id
 */
function deleteDataFun(id){
	$.ajax({
		url:getRequestUrl("/TestingProcessInfoController/deleteEntityData.json"),
		dataType:"json",
		data:{"testingProcessInfo.id":id},
		success:function(result){
			lalert("handle","success");
			queryDataList();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * 启用信息
 * @param id
 */
function beginDataFun(id){
	$.ajax({
		url:getRequestUrl("/TestingProcessInfoController/beginEntityData.json"),
		dataType:"json",
		data:{"testingProcessInfo.id":id},
		success:function(result){
			lalert("handle","success");
			queryDataList();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

