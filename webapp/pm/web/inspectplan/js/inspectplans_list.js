$(function(){
	menu('menu24');
	//页面初始化的事件
	bindevent();
	initPage();  	
	
});

function getProcessorId(){

	var commonData = parent.CsGlobalObj||parent.parent.CsGlobalObj;
	var id =commonData.currentUserid||"";

	return id;
}


function initPage(){
	
	$("#start").val(laydate.now(+1));
	getCodeLists();
}

function bindevent(){		
	//跳转到新增页面
	$("#gotoAddpageBt").bind('click',function(){
		pageForward('/pm/web/inspectplan/inspectplan_add.jsp');
	});
	$("#searchBt").bind('click',function(){
		getInspectPlanList();
	});
	var start = {
			  elem: '#start',
			  istoday: true,
//			  min: laydate.now(+1), //最大日期
			  isclear: false, //是否显示清空
			  istoday: false, //是否显示今天
			  choose: function(datas){
			     end.min = datas; //开始日选好后，重置结束日的最小日期
			     end.start = datas //将结束日的初始值设定为开始日
			  }
			};
	var end = {
			  elem: '#end',
			  istoday: true,
			  min: laydate.now(+1), //最大日期
			  isclear: false, //是否显示清空
			  istoday: false, //是否显示今天
			  choose: function(datas){
			    start.max = datas; //结束日选好后，重置开始日的最大日期
			  }
			};
	laydate(start);
	laydate(end);
	
	//check all
	$("#checkGroup").bind('click',function(){
		checkedAllSimple("checkGroup");
	});
	
	$("#updateStateBt").bind('click',updateStateBatch);
}
//得到页面下拉
function getCodeLists(){
	$.ajax({
		url:getRequestUrl("/InspectPlanController/queryPageList.json"),
		dataType:"json",
		data:"",
		success:function(result){
			$("#machinespecies").html($("#machinespeciesListTemplate").render(result));
			$("#cycles").html($("#cycleListTemplate").render(result));			
			getInspectPlanList();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}
/**
 * 查询提单列表
 */
function getInspectPlanList(){
	var postData = collectData("search_area");
	layer.load();//添加进度条
	if($("#groupby").val()==0){
		var parameterData = {
				url:getRequestUrl("/InspectPlanController/queryInspectplanList.json"),
				successfunc:renderListDataFun,
				customArray:postData,
				pageller:"pagefoot",
				pageIndex:1,
				pageSize:50,
				isAsync:false
		};
	}else{		
		postData=$.extend(postData,{"insPlanInfo.orderType":""});
		var parameterData = {
				url:getRequestUrl("/InspectPlanController/queryInspectplanListGroupby.json"),
				successfunc:renderListDataFun1,
				customArray:postData,
				pageller:"pagefoot",
				pageIndex:1,
				pageSize:50,
				isAsync:false
		};		
	}
	pageBarFortable(parameterData);
}
/**
 * 查询回调
 * @param result
 */
function renderListDataFun(result){
	var currentUserid =getProcessorId();
	for(var i=0;i<result.length;i++){
		result[i]['currentUserid']=currentUserid;
	}
	$("#table0").show();
	$("#table1").hide();
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
	
}
/**
 * 查询回调
 * @param result
 */
function renderListDataFun1(result){
	$("#table0").hide();
	$("#table1").show();
	$( "#inspectplanList" ).html(
		$("#inspectplansListTemplate1").render(result)
	);
	$('#inspectplanList tr:odd').addClass('odd'); //奇数行样式
	$('#inspectplanList tr:even').addClass('eve');//偶数行样式
	
	layer.closeAll('loading'); //关闭进度条
	
	if(result.length>10)
	{
		$("#last1").css({paddingRight:'25px'});
		
	}else{
		$("#last1").css({paddingRight:'8px'});
	}
	
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
					getInspectPlanList();
				}
			},
			error:function(error){
				lalert("delete","error");
			}
		});
	});
}
//跳转到新增页面
function gotoUpdatepage(id){
	pageForward('/pm/web/inspectplan/inspectplan_update.jsp?plan_id='+id);
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
		layer.confirm("是否批量删除"+len+"条数据", {
		    btn: ['确定','取消'] //按钮
		}, function(){	
			var postData={"insPlanInfo.ins_plan_id":idsStr};
			$.ajax({
				url:getRequestUrl("/InspectPlanController/deleteBatch.json"),
				dataType:"json",
				type:"POST",
				data:postData,
				success:function(result){
					if(result.opflag){
						lalert("update","success",function(){
							getInspectPlanList();
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