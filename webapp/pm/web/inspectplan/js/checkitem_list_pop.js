$(function(){
	initPage();  	
	bindevent();
});

var ids="";
var spid="";
var tyid="";
function initPage(){
	ids =getRequestParameterValue("ids");	
	spid =getRequestParameterValue("spid");
	tyid =getRequestParameterValue("tyid");
	getCodeLists();
}

function bindevent(){	
	$("#checkGroupId").bind('click',function(){
		checkedAllSimple("checkGroupId");
	});
	$("#importCheckitemListBt").bind('click',function(){
		importCheckitemAddPage();
	});
	$("#searchBt").bind('click',function(){
		getCheckItemList();
	});
	// 类别类型联动
	$("#machinespecies").change(function(){
		goforTypeList();
	});
}
//得到页面下拉
function getCodeLists(){
	var postData={"sysCodeInfo.code_value":spid};
	$.ajax({
		url:getRequestUrl("/MachineController/queryPageListForPop.json"),
		dataType:"json",
		data:postData,
		success:function(result){
			$("#machinespecies").html($("#machinespeciesListTemplate").render(result));
			$("#machinetype").html($("#machinetypeListTemplate").render(result));
			$("#species_id").val(spid);
			$("#type_id").val(tyid);
			getCheckItemList();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}
/**
 * 查询提单列表
 */
function getCheckItemList(){
	var postData = collectData("search_area");
	var newids = buildNewIds(ids);
	postData=$.extend(postData,{"ids":newids});
	var parameterData = {
			url:getRequestUrl("/CheckItemController/queryCheckItemListPop.json"),
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
}
//向父页面传值，关闭layer
function importCheckitemAddPage(){
    var re = buildIdsAndNames("checkGroupId");//得到页面选中的数据形成字符串
	if(re==""){
		layer.alert('请选择至少一条数据');
		$("#checkGroupId").prop("checked",false);
	}else{
		var species =$('input[name="checkGroupId"]:checked').eq(0).attr("spval");
		var typeid =$('input[name="checkGroupId"]:checked').eq(0).attr("typeval");
		if(spid!=""&&tyid!=""&&(species!=spid||typeid!=tyid)){
			 layer.confirm("设备类别或类型发生改变，表单将清空，确定导入？", {
			    btn: ['确定','取消'] //按钮
			}, function(){	
				window.parent.importCheckitem(re[0],re[1],species,typeid,"Y");//调用父页面方法
				parent.layer.closeAll(); 				
			})
		}else{					
			window.parent.importCheckitem(re[0],re[1],species,typeid,"N");//调用父页面方法
			parent.layer.closeAll();    
		}	
		
	}
}
//联动得到类型
function goforTypeList(){
	var postData = {"sysCodeInfo.code_value":$("#species_id").val(),"sysCodeInfo.code_group_value":Class.getConstant('MACH_TYPE_CODE')};
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