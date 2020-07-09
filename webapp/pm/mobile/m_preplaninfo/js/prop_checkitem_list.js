$(function(){
	initPage();
});
var id="";
var prop_id = "";

/**
 * 初始化页面
 * */
function initPage(){
	id = getRequestParameterValue("id");
	prop_id = getRequestParameterValue("prop_id");
	bindevent();
	queryDataList();
}

/**
 * 绑定事件
 */
function bindevent(){
	$("#submitBtn").bind('click',function(){
		saveForm();
	});
	$("#closeBtn").bind('click',goToMachDataFun);
}


/**
 * 跳转设备列表
 */
function goToMachDataFun(){
	var page = "/pm/mobile/m_preplaninfo/prop_machine_list.jsp?id="+id;
	pageForward(page);
}

/**
 * 查询方法
 */
function queryDataList(){
	 layer.open({type: 2});
	var postData = collectData("search_area");
	$.ajax({
		url:getRequestUrl("/PrePlanInfoController/queryPrePlanPropCheckitemList.json"),
		dataType:"json",
		data:{"prePlanPropCheckDetail.prop_plan_id":prop_id},
		success:function(result){
			layer.closeAll('loading');
			$( "#dataListForm" ).append(
					$("#dataListTemplate").render(result.prePlanPropCheckDetailList)
			);
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}


function saveForm(){
		layer.open({
			content: '确定提交',
		    btn: ['确定','取消'], //按钮
		    yes: function() {
				var postData = collectData("dataListForm");
				 layer.open({type: 2});//添加进度条
				$.ajax({
					url:getRequestUrl("/PrePlanInfoController/updatePrePlanPropCheckitemInfo.json"),
					dataType:"json",
					data:postData,
					success:function(result){
						layer.closeAll('loading');//关闭进度条
						if(result.opflag){
							lalert("save","success",function(){
								pageForward('/pm/mobile/m_preplaninfo/prop_machine_list.jsp?id='+id);
							});					
							
						}else{				
							lalert("save","error");
						}
						
					},
					error:function(error){
						layer.closeAll('loading'); //关闭进度条
						alert("error");
					}
				});
            }
			
		})
}