$(function(){
	menu('menuA2');
	initPage();
	bindevent();
});


function initPage(){
	getMaTypeList();
}

function bindevent(){		
	//跳转到新增页面
	$("#gotoAddpageBt").bind('click',function(){
		pageForward('/pm/web/basicinfo/machinemanager/machine_pot_part_info_add.jsp');
	});
	$("#searchBt").bind('click',function(){
		getMaTypeList();
	});
	//清除input按钮
	$('#selPotNm').bind('mouseover',function(){
		$('#clearPotA').css('visibility','');
	});
	$('#selPotNm').bind('mouseout',function(){
		$('#clearPotA').css('visibility','hidden');
	});
	$('#clearPotA').bind('mouseover',function(){
		$('#clearPotA').css('visibility','');
	});
	$('#clearPotA').bind('mouseout',function(){
		$('#clearPotA').css('visibility','hidden');
	});
}
/**
 * 查询提单列表
 */
function getMaTypeList(){
	var postData = collectData("search_area");
	layer.load();//添加进度条
	var parameterData = {
			url:getRequestUrl("/MachinePotPartInfoController/queryDataList.json"),
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
	$( "#maPotPartList" ).html(
		$("#maPotPartListTemplate").render(result)
	);
	$('#maPotPartList tr:odd').addClass('odd'); //奇数行样式
	$('#maPotPartList tr:even').addClass('eve');//偶数行样式
	
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
	var page="/pm/web/basicinfo/machinemanager/machine_pot_part_info_upd.jsp?pot_part_id="+id;
	pageForward(page);
};

/**
 * POP选择页弹出
 * @returns
 */
function selectPot() {
	var pathName = window.location.pathname;
    var webName = pathName.substring(0, pathName.indexOf('/',2));
	pagePopShow("一级设备选择",webName+"/MachinePotPartInfoController/selectPotPop","1000px","600px");
}

/**
 * pop回调
 * @returns
 */
function selectPotBack(obj) {
	$('#selPotId').val(obj.id);
	$('#selPotNm').val(obj.nm);
}

/**
 * 清空一级设备选择
 * @returns
 */
function clearPot() {
	$('#selPotId').val('');
	$('#selPotNm').val('');
}
