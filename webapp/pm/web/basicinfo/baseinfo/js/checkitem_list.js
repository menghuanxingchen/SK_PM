$(function(){
	menu('menu15');
	initPage();  	
	bindevent();
});


function initPage(){
	getCodeLists();
	getCheckItemList();
}

function bindevent(){		
	//跳转到新增页面
	$("#gotoAddpageBt").bind('click',function(){
		pageForward('/pm/web/basicinfo/baseinfo/checkitem_add.jsp');
	});
	$("#searchBt").bind('click',function(){
		getCheckItemList();
	});
	// 类别类型联动
	$("#machinespecies").change(function(){
		goforTypeList();
	});
}
/**
 * 查询提单列表
 */
function getCheckItemList(){
	var postData = collectData("search_area");
	layer.load();//添加进度条
	var parameterData = {
			url:getRequestUrl("/CheckItemController/queryCheckItemListNames.json"),
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
	$( "#checkitemList" ).html(
		$("#checkitemListTemplate").render(result)
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
	var page="/pm/web/basicinfo/baseinfo/checkitem_update.jsp?checkitem_id="+id;
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
			$("#machinetype").html($("#machinetypeListTemplate").render(result));
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}
//联动得到类型
function goforTypeList(){
	var postData = {"sysCodeInfo.code_value":$("#machine_species_id").val(),"sysCodeInfo.code_group_value":Class.getConstant('MACH_TYPE_CODE')};
	$.ajax({
		url:getRequestUrl("/MachineController/getSubCodeList.json"),
		dataType:"json",
		data:postData,
		success:function(data){
			$( "#machinetype" ).html(
					$("#machinetypeListTemplate").render(data)
			);
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}