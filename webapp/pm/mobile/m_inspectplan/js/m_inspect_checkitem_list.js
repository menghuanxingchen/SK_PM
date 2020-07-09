$(function(){
	//页面初始化的事件
	//bindevent();
	initPage();  	
	
});

var plan_machine_id="";
function initPage(){	
	//getCodeLists();
	plan_machine_id =getRequestParameterValue("plan_machine_id");	
	queryDataList();
	
}

function bindevent(){		
	//保存事件
	$("#saveBtn").bind('click',function(){
		saveFormData();
	});
	//取消事件
	$("#cancelBtn").bind('click',function(){
		var insplanid=$("#ins_plan_id").val();
		pageForward('/pm/mobile/m_inspectplan/m_inspect_machine_list.jsp?plan_id='+insplanid);
	});
	//跳回
	$("#backDiv").bind('click',function(){
		var insplanid=$("#ins_plan_id").val();
		pageForward('/pm/mobile/m_inspectplan/m_inspect_machine_list.jsp?plan_id='+insplanid);
	});
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
			
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}
/**
 * 查询方法
 */
function queryDataList(){
	layer.open({type: 2});
	var postData = collectData("search_area");
	postData=$.extend(postData,{"insPlanCheckDetail.plan_machine_id":plan_machine_id});
	$.ajax({
		url:getRequestUrl("/InspectPlanController/queryInspectCheckItemListMob.json"),
		dataType:"json",
		data:postData,
		success:function(result){
			layer.closeAll('loading');
			$("#plan_name").html(result.insPlanInfo.ins_plan_nm);
			$("#ins_plan_id").val(result.insPlanInfo.ins_plan_id);			
			$("#machine_name").html(result.insMachineInfo.machine_nm);
			$("#plan_machine_id").val(result.insMachineInfo.plan_machine_id);
			$("#plan_date").html(result.insPlanInfo.ins_plan_date);
			$( "#dataListForm" ).html(
					$("#dataListTemplate").render(result.checkitemInfoList)
			);
			bindevent();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

//跳转machineList页面
function goToMachDataFun(id){
	pageForward('/pm/web/inspectplan/m_inspect_machine_list.jsp?plan_id='+id);
}
function saveFormData(){	
		layer.open({
			content: '确定提交',
		    btn: ['确定','取消'], //按钮
		    yes: function() {
				var postData = collectData("com_body");
				//layer.load();//添加进度条
				$.ajax({
					url:getRequestUrl("/InspectPlanController/updateInsMstThreeTableMob.json"),
					dataType:"json",
					data:postData,
					success:function(result){
						//layer.closeAll('loading'); //关闭进度条
						var insplanid=$("#ins_plan_id").val();
						if(result.opflag){
							lalert("save","success",function(){
								pageForward('/pm/mobile/m_inspectplan/m_inspect_machine_list.jsp?plan_id='+insplanid);
							});					
							
						}else{				
							lalert("save","error");
						}
						
					},
					error:function(error){
						alert("error");
					}
				});
            }
			
		})
}