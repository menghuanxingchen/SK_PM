$(function(){
	menu('menu11');
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
	$.ajax({
		url:getRequestUrl("/SysUserInfoController/beforeDataList.json"),
		dataType:"json",
		data:[],
		success:function(data){
			$( "#departTypeForm" ).html(
					$("#departListTemplate").render(data)
			);
			
			$( "#orderListForm" ).html(
					$("#orderListTemplate").render(data)
			);
			
			$( "#subDepartTypeForm" ).html(
					$("#subDepartListTemplate").render(data)
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
	$("#departTypeForm").change(function(){
		$.ajax({
			url:getRequestUrl("/SysUserInfoController/getSubCodeList.json"),
			dataType:"json",
			data:{"subGroupCode":$("#dept").val()},
			success:function(data){
				$( "#subDepartTypeForm" ).html(
						$("#subDepartListTemplate").render(data)
				);
			},
			error:function(error){
				lalert('网络原因操作失败！','error');
			}
		});
	});
	$("#search_btn").bind('click',queryDataList);
}

/**
 * 查询方法
 */
function queryDataList(){
	var postData = collectData("search_area");

	var parameterData = {
			url:getRequestUrl("/SysUserInfoController/queryDataList.json"),
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
	var page = "/pm/web/userinfo/userinfo_add.jsp";
	pageForward(page);
}

/**
 * 修改
 * @param id
 */
function updateEntityDataFun(id){
	var page = "/pm/web/userinfo/userinfor_update.jsp?id="+id;
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
					url:getRequestUrl("/SysUserInfoController/deleteEntityData.json"),
					dataType:"json",
					data:{"sysUserInfo.user_id":id},
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

