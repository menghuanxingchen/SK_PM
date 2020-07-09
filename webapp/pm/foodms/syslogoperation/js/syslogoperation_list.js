$(function(){

	initPage();
	bindevent();
	
});

/**
 * 初始化页面
 * */
function initPage(){
	querySysLogOperationList();
	
	createSelectByQuery("/SysLogOperationController/loadBusinessSelect.json","sel_businessName","businessName","businessName");
	//createSelectByDB("ttt1","sys_user","phone","id");
	//createSelectByDB("ttt2","sys_user","phone","id","AND phone is not null");
	
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
			url:getRequestUrl("/SysLogOperationController/querySysLogOperationList.json"),
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
		$( "#tbody" ).html($("#userShowTemplate").render(result));
	}
}

/**
 * 查看日期详细信息
 */
function showLogDetail(logID){
	
	$( "#tb_logDetail" ).hide();
	
	$.ajax({
		url:getRequestUrl("/SysLogOperationController/querySysLogOperationDetailByLogID.json"),
		dataType:"json",
		data:{"sysLogOperation.logid":logID},
		async:false,
		success:function(result){
			
			
			$( "#logDetail" ).html($("#logDetailTemplate").render(result.data.logDetailList));
		$( "#tb_logDetail" ).show();
		

//		$.layer({
//			type : 1,
//		    title: "生成知享卡",
//		    border: [1,0.2,"#000",true],
//		    shade: [0.5,'#000',true],
//		    closeBtn: [0,true],
//		    area: ['662px', '500px'],
//		    page: {html : $( "#tb_logDetail" ).html()}
//		});
//		
			
			
		},
		error:function(error){
			layer.alert("error",3);
		}
	});
	

	
}

function showLog(){
	var pagei2 = $.layer({
		   type: 1,   //0-4的选择,
		    title: false,
		    border: [0],
		    closeBtn: [0],
		    shadeClose: true,
		    area: ['460px', '280px'],
		    page: {
		        html: $( "#tb_logDetail" ).html() //此处放了防止html被解析，用了\转义，实际使用时可去掉
		    }
		});
}


/**
 * 清空日志
 */	
function truncateLog(){
	
	layer.confirm('确定清空全部日志吗?', function(){ 
			$.ajax({
				url:getRequestUrl("/SysLogOperationController/truncateLog.json"),
				dataType:"json",
				success:function(result){
					if(result.opflag){
						layer.alert('清空日志操作成功！',1);
						querySysLogOperationList();
						$( "#tb_logDetail" ).hide();
					}
					
				},
				error:function(error){
					layer.alert("error",3);
				}
			});
		});
	
}	
