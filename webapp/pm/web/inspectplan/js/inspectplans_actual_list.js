$(function(){	
	menu('menu25');
	initPage();  	

});

//传参数--by fish
var planNm = "";
var machType = "";
var cycle = "";
var start = "";
var end = "";
var orderType = "";
//传参数--by fish

function initPage(){
	getCodeLists();
}

function bindevent(){	
	if(auth!='2'){//限制批量确认和批量删除权限
		$("#updateStateBt").hide();	
		$("#deleteBatchBt").hide();	
	}
	//跳转到详情页面
	$("#gotoCheckDetailBt").bind('click',function(){
		pageForward('/rapast/skitlms/importmgmt/imp_list.jsp');
	});
	$("#searchBt").bind('click',function(){
		getInspectPlanActualList();
	});
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

	$("#deleteBatchBt").bind('click',function(){
		deletecheckBatch();
	});

}
//得到页面下拉
function getCodeLists(){
	$.ajax({
		url:getRequestUrl("/InspectPlanController/queryPageList.json"),
		dataType:"json",
		data:"",
		async: false,
		success:function(result){
			$("#machinespecies").html($("#machinespeciesListTemplate").render(result));
			$("#cycles").html($("#cycleListTemplate").render(result));
			$( "#orderListForm" ).html(
					$("#orderListTemplate").render(result)
			);
			
			//传参数--by fish
			$("#planNm").val(getRequestParameterValue("planNm"));
			$("#machineSpecies").val(getRequestParameterValue("machType"));
			$("#cycle").val(getRequestParameterValue("cycle"));
			$("#start").val(getRequestParameterValue("start"));
			$("#end").val(getRequestParameterValue("end"));
			$("#orderType").val(getRequestParameterValue("orderType"));
			//传参数--by fish

		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
	//页面初始化的事件
	bindevent();
	getInspectPlanActualList();
}
/**
 * 查询提单列表
 */
function getInspectPlanActualList(){
	var postData = collectData("search_area");
	layer.load();//添加进度条
	var parameterData = {
			url:getRequestUrl("/InspectPlanController/queryInspectplanList.json"),
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
	$( "#inspectplanList" ).html(
		$("#inspectplansListTemplate").render(result)
	);
	
	$('#inspectplanList tr:odd').addClass('odd'); //奇数行样式
	$('#inspectplanList tr:even').addClass('eve');//偶数行样式
	layer.closeAll('loading'); //关闭进度条
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
 * 确认修改计划状态
 */
function updatePlanState(id){
	layer.confirm('是否确认？', {
	    btn: ['确定','取消'] //按钮
	}, function(){	
		var postData={"insPlanInfo.ins_plan_id":id,"insPlanInfo.ins_plan_state":Class.getConstant('PLAN_STATE_CHECKED')};
		$.ajax({
			url:getRequestUrl("/InspectPlanController/updateInspectplans.json"),
			dataType:"json",
			data:postData,
			success:function(result){
				if(result.opflag){
					lalert("update","success");
					getInspectPlanActualList();
				}
			},
			error:function(error){
				lalert("update","error");
			}
		});
	});
}

function gotoCheckDetailList(plan_id){
	//pageForward('/pm/web/inspectplan/inspectplan_checkdetail_list.jsp?plan_id='+plan_id);
	//传参数--by fish
	var planNm = escape($("#planNm").val());
	var machType = $("#species_id").val();
	var cycle = $("#cycle").val();
	var start = $("#start").val();
	var end = $("#end").val();
	var orderType = $("#orderType").val();
	pageForward('/pm/web/inspectplan/inspectplan_checkdetail_list.jsp?plan_id='+plan_id+"&planNm="+planNm+"&machType="+machType+"&cycle="+cycle+"&start="+start+"&end="+end+"&orderType="+orderType);
}

/**
 * 删除计划 
 */
function deleteInsPlan(id){
	layer.confirm('是否删除？', {
	    btn: ['确定','取消'] //按钮
	}, function(){		
		layer.load();//添加进度条
		$.ajax({
			url:getRequestUrl("/InspectPlanController/deleteInspectplans.json?plan_id="+id),
			dataType:"json",
			success:function(result){
				layer.closeAll('loading'); //关闭进度条
				if(result.opflag){
					lalert("delete","success");
					getInspectPlanActualList();
				}
			},
			error:function(error){
				lalert("delete","error");
			}
		});
	});
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
			var postData={"insPlanInfo.ins_plan_id":idsStr,"insPlanInfo.ins_plan_state":Class.getConstant('PLAN_STATE_CHECKED')};
			$.ajax({
				url:getRequestUrl("/InspectPlanController/updateStateBatch.json"),
				dataType:"json",
				type:"POST",
				data:postData,
				success:function(result){
					if(result.opflag){
						lalert("update","success",function(){
							pageForward('/pm/web/inspectplan/inspectplan_actual_list.jsp');
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

//批量delete
function deleteBatch(){
	    var res=buildIdsBatch("checkGroup");//用于返回 选中条数  和id用，隔开的字符串
		var len = res[0];
		var idsStr = buildNewIds(res[1]);//将id字符串加‘’		
			var postData={"insPlanInfo.ins_plan_id":idsStr};
			$.ajax({
				url:getRequestUrl("/InspectPlanController/deleteBatch.json"),
				dataType:"json",
				type:"POST",
				data:postData,
				success:function(result){
					if(result.opflag){
						lalert("delete","success",function(){
							pageForward('/pm/web/inspectplan/inspectplan_actual_list.jsp');
						});
					}else{				
						lalert("delete","error");
					}
					
				},
				error:function(error){
					alert("error");
				}
			});
	
	
}
//批量delete前验证
function deletecheckBatch(){
	var res=buildIdsBatch("checkGroup");//用于返回 选中条数  和id用，隔开的字符串
	if(res==""){
		layer.alert('请选择至少一条数据');
		$("#checkGroup").prop("checked",false);
	}else{
		var len = res[0];
		var idsStr = buildNewIds(res[1]);//将id字符串加‘’		
		layer.confirm("是否批量删除"+len+"条数据", {
		    btn: ['确定','取消'] //按钮
		}, function(){	
			var postData={"insPlanInfo.ins_plan_id":idsStr};
			$.ajax({
				url:getRequestUrl("/InspectPlanController/checkDeleteBatch.json"),
				dataType:"json",
				type:"POST",
				data:postData,
				success:function(result){
					if(result.checktempList.length==0){
                            deleteBatch();
					}else{				
						layer.alert('有实际记录的巡检不能删除');
					}
					
				},
				error:function(error){
					alert("error");
				}
			});
		})
	}
}