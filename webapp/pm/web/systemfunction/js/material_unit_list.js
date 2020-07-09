$(function(){
	menu('menu75');
	initPage();  	
	bindevent();
});


function initPage(){
	getInfoList();
}

function bindevent(){		
	//跳转到新增页面
	$("#gotoAddpageBt").bind('click',function(){
		pageForward('/pm/web/systemfunction/material_unit_add.jsp');
	});
	$("#searchBt").bind('click',function(){
		getInfoList();
	});
}
/**
 * 查询提单列表
 */
function getInfoList(){
	var postData = collectData("search_area");
	postData=$.extend(postData,{"sysCodeInfo.code_group_value":Class.getConstant('MATERIAL_UNIT_GROUP')});
	layer.load();//添加进度条
	var parameterData = {
			url:getRequestUrl("/SupplierController/querySysCodeInfoList.json"),
			successfunc:renderListDataFun,
			customArray:postData,
			pageller:"pagefoot",
			pageIndex:1,
			pageSize:50,
			isAsync:false
	};
	pageBarFortable(parameterData);
}
/**
 * 查询回调
 * @param result
 */
function renderListDataFun(result){
	$( "#infoList" ).html(
		$("#infoListTemplate").render(result)
	);
	$('#infoList tr:odd').addClass('odd'); //奇数行样式
	$('#infoList tr:even').addClass('eve');//偶数行样式
	
	layer.closeAll('loading'); //关闭进度条
	
	//var table_height=$("#table_height").height();
		if(result.length>10)
		{	
			$("#last").css({paddingRight:'25px'});
		}else{
			$("#last").css({paddingRight:'8px'});
		}

	
}
/**
 * 删除计划 
 */
function deleteCheckItem(id){
	layer.confirm('是否删除？', {
	    btn: ['确定','取消'] //按钮
	}, function(){			
		$.ajax({
			url:getRequestUrl("/CheckItemController/deleteCheckItem.json?id="+id),
			dataType:"json",
			success:function(result){
				if(result.opflag){
					lalert("delete","success");
					getInfoList();
				}
			},
			error:function(error){
				lalert("delete","error");
			}
		});
	});
}

/**
 * 跳转到update页面
 */
function gotoUpdate(id){
	var page="/pm/web/systemfunction/material_unit_update.jsp?code_id="+id;
	pageForward(page);
}
