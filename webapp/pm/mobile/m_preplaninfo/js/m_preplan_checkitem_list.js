$(function(){
	initPage();
});
var id="";
var m_id = "";
/**
 * 初始化页面
 * */
function initPage(){
	id = getRequestParameterValue("id");
	m_id = getRequestParameterValue("m_id");
	$("#planNm").text(getRequestParameterValue("planNm"));
	$("#date").text(getRequestParameterValue("date"));
	$("#machNm").text(getRequestParameterValue("machNm"));
	bindevent();
	queryDataList();
}

/**
 * 绑定事件
 */
function bindevent(){
	$("#search_btn").bind('click',queryDataList);
	$("#submitBtn").bind('click',function(){
		saveForm();
	});
	$("#closeBtn").bind('click',goToMachDataFun);
}


/**
 * 跳转设备列表
 */
function goToMachDataFun(){
	var page = "/pm/mobile/m_preplaninfo/m_machine_list.jsp?id="+id;
	pageForward(page);
}


/**
 * 查询方法
 */
function queryDataList(){
	 layer.open({type: 2});
	var postData = collectData("search_area");
	$.ajax({
		url:getRequestUrl("/PrePlanInfoController/queryPrePlanCheckItem.json"),
		dataType:"json",
		data:{"prePlanCheckDetail.plan_machine_id":m_id},
		success:function(result){
			layer.closeAll('loading');
			$("#planNm").html(result.prePlanInfo.pre_plan_nm);
			$("#machNm").html(result.preMachineInfo.machine_nm);
			$("#date").html(result.prePlanInfo.pre_plan_date);
			
			$("#pre_plan_id").val(result.prePlanInfo.pre_plan_id);			
			$("#plan_machine_id").val(result.preMachineInfo.plan_machine_id);
			$( "#dataListForm" ).append(
					$("#dataListTemplate").render(result.prePlanCheckDetailList)
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
				var postData = collectData("com_body");
				 layer.open({type: 2});//添加进度条
				$.ajax({
					url:getRequestUrl("/PrePlanInfoController/updatePerThreeTableMob.json"),
					dataType:"json",
					data:postData,
					success:function(result){
						layer.closeAll('loading');//关闭进度条
						if(result.opflag){
							lalert("save","success",function(){
								pageForward('/pm/mobile/m_preplaninfo/m_machine_list.jsp?id='+id+"&planNm="+escape($("#planNm").text())+"&date="+$("#date").text());
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