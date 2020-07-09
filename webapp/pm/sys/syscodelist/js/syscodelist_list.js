$(function(){

	initPage();
	bindevent();
	
});

/**
 * 初始化页面
 * */
function initPage(){
	querySysLogOperationList();
	
	
	//createSelectByQuery("/SysLogOperationController/loadBusinessSelect.json","sel_businessName","businessName","businessName");
	
}

/**
 * 事件绑定
 */
function bindevent(){
	$("#search_BNT").bind('click',querySysLogOperationList);
}

/**
 * 查询用户
 */
function querySysLogOperationList(){
	var postData = collectData("search_DIV");
	
	var parameterData = {
			url:getRequestUrl("/transcodeController/querySysCodeList.json"),
			successfunc:showPage,
			customArray:postData,
			pageller:"pagefoot",
			pageIndex:1,
			isAsync:false
	};
	pageBarFortable(parameterData)
}


//回调
function showPage(result,response){
	if(response.opflag){
		$( "#tbody" ).html($("#listShowTemplate").render(result));
	}
}

var tempKindname="";
var tempKindvalue="";

function showCodeDetail(kindCode,kindName){
	
	tempKindname  = kindCode;
	tempKindvalue = kindName;
	
	$("#cap_kindecode").html(kindCode+" 码表数据");
	var postData = 	{"sysCodelist.kindcode":kindCode};
	var parameterData = {
			url:getRequestUrl("/transcodeController/querySysCodeListByCodeKind.json"),
			successfunc:code_showPage,
			customArray:postData,
			pageller:"code_pagefoot",
			pageIndex:1,
			isAsync:false
	};
	pageBarFortable(parameterData)
}

function code_showPage(result,response){
	if(response.opflag){
		$( "#codeDetail" ).html($("#codeDetailTemplate").render(result));
		$( "#div_codetable" ).show();
	}
}



function openAdd(){
	_layer_index = $.layer({
	    type: 2,
	    title:false,
	    area: ['720px' , '385px'],
	    shadeClose: true,
	    closeBtn: false,
	    bgcolor: '',
	    border: [0],
	    iframe: {src: getRequestUrl('/pm/sys/syscodelist/syscodelist_add.jsp')}
	});
}

function closeLayer(){
	layer.close(_layer_index);
}

var _layer_index;
function editCode(id){
	_layer_index = $.layer({
	    type: 2,
	    title:false,
	    area: ['720px' , '385px'],
	    bgcolor: '',
	    shadeClose: true,
	    data:id,
	    closeBtn: false,
	    border: [0],
	    iframe: {src: getRequestUrl('/pm/sys/syscodelist/syscodelist_edit.jsp')}
	});
}


function delCode(id,codekind){
	//layer.confirm('确定删除此条数据吗?', function(){ 
		$.ajax({
			url:getRequestUrl("/transcodeController/deleteSysCodeListById.json"),
			dataType:"json",
			data:{"sysCodelist.codelistid":id},
			success:function(result){
				
				if(result.opflag){
				//	layer.alert(result.opmessage);
					querySysLogOperationList();
					showCodeDetail(codekind);
				}else{
					layer.alert(result.opmessage,2);
				}
				
			}
		});
	//});
}
