$(function(){
	menu('menu22');
	initPage();
});
var planNm = "";
var machType = "";
var cycle = "";
var start = "";
var end = "";
var orderType = "";
/**
 * 初始化页面
 * */
function initPage(){
	$.ajax({
		url:getRequestUrl("/PrePlanInfoController/beforeDataList.json"),
		dataType:"json",
		data:[],
		async: false,
		success:function(data){
			$( "#departTypeForm" ).html(
					$("#departTypeListTemplate").render(data)
			);
			
			$( "#cycleListForm" ).html(
					$("#cycleListTemplate").render(data)
			);
			
			$( "#orderListForm" ).html(
					$("#orderListTemplate").render(data)
			);
			
			$("#planNm").val(getRequestParameterValue("planNm"));
			$("#machineSpecies").val(getRequestParameterValue("machType"));
			$("#cycle").val(getRequestParameterValue("cycle"));
			$("#start").val(getRequestParameterValue("start"));
			$("#end").val(getRequestParameterValue("end"));
			$("#orderType").val(getRequestParameterValue("orderType"));
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
	if(auth!='2'){//限制批量确认和批量删除权限
		$("#updateStateBt").hide();	
	}
	var start = {
			  elem: '#start',
			  istoday: true,
			  max: laydate.now(), //最大日期
			  isclear: false, //是否显示清空
			  choose: function(datas){
			     end.min = datas; //开始日选好后，重置结束日的最小日期
			     end.start = datas //将结束日的初始值设定为开始日
			  }
			};
	var end = {
			  elem: '#end',
			  istoday: true,
			  max: laydate.now(), //最大日期
			  isclear: false, //是否显示清空
			  choose: function(datas){
			    start.max = datas; //结束日选好后，重置开始日的最大日期
			  }
			};
	laydate(start);
	laydate(end);
	$("#end").val(laydate.now());
	
	//check all
	$("#checkGroup").bind('click',function(){
		checkedAllSimple("checkGroup");
	});
	
	$("#updateStateBt").bind('click',function(){
		updateStateBatch();
	});
			
	$("#search_btn").bind('click',queryDataList);
}

/**
 * 查询方法
 */
function queryDataList(){
	var postData = collectData("search_area");
	var parameterData = {
			url:getRequestUrl("/PrePlanInfoController/queryDataList.json"),
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
	for(var i =0;i<result.length;i++){
		if(auth!='2'){
			$("#confirm"+i).remove();
			$("#delete"+i).remove();
		}
	}
	
}

/**
 * 详情
 * @param id
 */
function detailEntityDataFun(id){
	var planNm = escape($("#planNm").val());
	var machType = $("#machineSpecies").val();
	var cycle = $("#cycle").val();
	var start = $("#start").val();
	var end = $("#end").val();
	var orderType = $("#orderType").val();
	var page = "/pm/web/preplaninfo/preplan_detail_list.jsp?id="+id+"&planNm="+planNm+"&machType="+machType+"&cycle="+cycle+"&start="+start+"&end="+end+"&orderType="+orderType;
	pageForward(page);
}

/**
 * 确认
 * @param id
 */
function updateConfirm(id){

	var message = "确认审批？";
	layer.confirm(
			message,
			function(index){
				$.ajax({
					url:getRequestUrl("/PrePlanInfoController/updateConfirmData.json"),
					dataType:"json",
					data:{"prePlanInfo.pre_plan_id":id},
					success:function(result){
						lalert("update","success");
						queryDataList();
					},
					error:function(error){
						lalert('网络原因操作失败！','error');
					}
				});
				layer.close(index);
			},
			"信息",
			function(index){
				layer.close(index);
				return;
			}
	);

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
					url:getRequestUrl("/PrePlanInfoController/deleteEntityData.json"),
					dataType:"json",
					data:{"prePlanInfo.pre_plan_id":id},
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

//批量确认
function updateStateBatch(){
	var res=buildIdsBatch("checkGroup");//用于返回 选中条数  和id用，隔开的字符串
	if(res==""){
		layer.alert('请选择至少一条数据');
		$("#checkGroup").prop("checked",false);
	}else{
		var len = res[0];
		var idsStr = buildNewIds(res[1]);//将id字符串加‘’		
		layer.confirm("是否批量确认"+len+"条数据", {
		    btn: ['确定','取消'] //按钮
		}, function(){	
			var postData={"prePlanInfo.pre_plan_id":idsStr};
			$.ajax({
				url:getRequestUrl("/PrePlanInfoController/updateStateBatch.json"),
				dataType:"json",
				type:"POST",
				data:postData,
				success:function(result){
					if(result.opflag){
						lalert("update","success",function(){
							queryDataList();
						});
					}else{				
						lalert("update","error");
					}
					
				},
				error:function(error){
					alert("error");
				}
			});
		})
	}
}
