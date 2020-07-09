$(function(){
	menu('menu13');
	initPage();  	
	bindevent();
});


function initPage(){
	getCodeLists();
	getMaTypeList();
}

function bindevent(){		
	//跳转到新增页面
	$("#gotoAddpageBt").bind('click',function(){
		pageForward('/pm/web/basicinfo/baseinfo/machinetype_add.jsp');
	});
	$("#searchBt").bind('click',function(){
		getMaTypeList();
	});
}
/**
 * 查询提单列表
 */
function getMaTypeList(){
	var postData = collectData("search_area");
	layer.load();//添加进度条
	var parameterData = {
			url:getRequestUrl("/MachineTypeController/queryDataList.json"),
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
	$( "#matypeList" ).html(
		$("#maTypeListTemplate").render(result)
	);
	$('#checkitemList tr:odd').addClass('odd'); //奇数行样式
	$('#checkitemList tr:even').addClass('eve');//偶数行样式
	
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
					getCheckItemList();
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
	var page="/pm/web/basicinfo/baseinfo/machinetype_update.jsp?code_id="+id;
	pageForward(page);
}
//得到页面下拉
function getCodeLists(){
	$.ajax({
		url:getRequestUrl("/MachineController/queryPageList.json"),
		dataType:"json",
		data:"",
		success:function(result){
			$("#machinespecies").html($("#machinespeciesListTemplate").render(result));
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}