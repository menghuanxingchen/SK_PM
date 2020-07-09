$(function(){
	//页面初始化的事件
	initPage();  	
	
});

var id = getRequestParameterValue("id");
var flag = false;
function initPage(){	
	queryDataList();
}

function bindevent(){	
	$("#repairResultMachineDiv").hide();
	//save
	$("#submitBtn").unbind();
	$("#submitBtn").bind('click',function(){
		saveRepairCostDetailTempMob();
	});
	$("#machineSubmitBtn").unbind();
	$("#machineSubmitBtn").bind('click',function(){
		importCheckitemAddPage();
	});
	$("#repairAddcancelBtn").unbind();
	$("#repairAddcancelBtn").bind('click',function(){
		pageForward('/pm/mobile/m_repair/m_repair_list.jsp');
	});
	$("#cancelBtn").unbind();
	$("#cancelBtn").bind('click',function(){
		$("#_centent").attr("style","");
		$("#machine_choose").attr("style","display:none");
		$("#cost_list").attr("style","display:none");
	});
	$("#checkGroupId").unbind();
	$("#checkGroupId").bind('click',function(){
		checkedAllSimple("checkGroupId");
	});
	
	$("#costCancelBtn").unbind();
	$("#costCancelBtn").bind('click',function(){
		pageForward('/pm/mobile/m_repair/m_repair_add.jsp?id='+id);
	});
}

/**
 * 查询方法
 */
function queryDataList(){
	 layer.open({type: 2});
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/queryRepairCostDetailTempListMob.json"),
		dataType:"json",
		data:{"repairCostDetailTemp.repair_result_id":id},
		success:function(result){
			layer.closeAll('loading');
			$( "#listviewUpdate-demo" ).html(
					$("#dataListTemplate").render(result.data)
			);
			 getRepairDetailNm();
			 listviewUpdateInit();
			 
		},
		error:function(error){
			layer.closeAll('loading');
			lalert('网络原因操作失败！','error');
		}
	});
}

function getRepairDetailNm(){
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/getRepairDetailNm.json"),
		dataType:"json",
		data:{"repairOrderInfo.id":id},
		success:function(result){
			$("#repair_detail").val(result.repairDetailNm);
			$("#require_date").val(result.requireTime);
			if(result.expanseYn == 'N'){
				$("#task_time").attr("readonly","readonly");
				$("#unit_price").attr("readonly","readonly");
				$("#one_charge").attr("readonly","readonly");
				$("#repair_type").val("无费用");
			}else{
				$("#repair_type").val("有费用");
				flag = true;
			}
			getMachineInfo();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

//得到设备列别 设备类型 区域
function getMachineInfo(){
	$("#machine_nm").val('');
	$("#checkGroupId").prop("checked",false);
	$.ajax({
		url:getRequestUrl("/MachineController/queryPageListForPop.json"),
		dataType:"json",
		data:"",
		success:function(result){
			$("#machinespecies").html($("#machinespeciesListTemplate").render(result));
			$("#machinetype").html($("#machinetypeListTemplate").render(result));
			$("#areaid").html($("#areaListTemplate").render(result));
			// 类别类型联动
			$("#machinespecies").change(function(){
				goforTypeList();
			});
			// 类别类型联动
			$("#machinetype").change(function(){
				queryMachineInfoListPopForRepairMob();
			});
			// 类别类型联动
			$("#areaid").change(function(){
				queryMachineInfoListPopForRepairMob();
			});
			$("#machine_nm").on('input',function(e){  
				queryMachineInfoListPopForRepairMob();
			});
			bindevent();
			queryMachineInfoListPopForRepairMob();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
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
			queryMachineInfoListPopForRepairMob();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

//跳转材料费添加页面
function goToSaveDataFun(){
	if(flag){
		pageForward('/pm/mobile/m_repair/m_repair_cost_add.jsp?id='+id);
	}else{
		layer.open({
			content: '维修单无费用，不能添加材料费信息',
		    btn: ['确定'], //按钮
		    yes: function() {
		    	layer.closeAll();
		    }
		})
	}
}

//跳转材料费修改页面
function goToUpdateDataFun(repair_cost_id){
	pageForward('/pm/mobile/m_repair/m_repair_cost_update.jsp?id='+id+"&repair_cost_id="+repair_cost_id);
}

/**
 * save 方法
 */
function saveRepairCostDetailTempMob(){
	layer.open({
		content: '确定提交',
	    btn: ['确定','取消'], //按钮
	    yes: function() {
	    	var postData = collectData("com_body");
	    	postData["repairCostDetailTemp.repair_result_id"] = id;
	    	postData["repairOrderInfo.id"] = id;
	    	$.ajax({
	    		url:getRequestUrl("/RepairOrderInfoController/addRepairResultInfoForMob.json"),
	    		dataType:"json",
	    		data:postData,
	    		success:function(result){
	    			if(result.opflag){
	    				pageForward('/pm/mobile/m_repair/m_repair_list.jsp');
	    			}
	    		},
	    		error:function(error){
	    			layer.closeAll('loading');
	    			lalert('网络原因操作失败！','error');
	    		}
	    	});
	    }
	})
}

/**
 * 临时材料费列表查询方法
 */
function queryCostDetailListForMob(){
	$("#_centent").attr("style","display:none");
	$("#machine_choose").attr("style","display:none");
	$("#cost_list").attr("style","");
	 layer.open({type: 2});
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/queryCostDetailListForMob.json"),
		dataType:"json",
		data:{"repairCostDetailTemp.repair_result_id":id},
		success:function(result){
			layer.closeAll('loading');
			$( "#cost_list_tbody" ).html(
					$("#costListTemplate").render(result)
			);
		},
		error:function(error){
			layer.closeAll('loading');
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * 设备列表查询方法
 */
function queryMachineInfoListPopForRepairMob(){
	var postData = collectData("machine_choose");
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/queryMachineInfoListPopForRepairMob.json"),
		dataType:"json",
		data:postData,
		success:function(result){
			$( "#machineList" ).html(
					$("#machineListTemplate").render(result)
				);
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

//向父页面传值，关闭layer
function importCheckitemAddPage(){
	$("#repairResultMachineDiv").show();
	var species =$("#species_id").val();
	var typeid =$("#type_id").val();
    var re = buildIdsAndNames("checkGroupId");//得到页面选中的数据形成字符串
    var idsArray=re[0].split(",");
	var namesArray=re[1].split(",");
	machineInfoBack(idsArray,namesArray,species,typeid);
//	var ids =getRequestParameterValue("ids");
//	var spid =$("#ma_sp_id").val();
//	var tyid =$("#ma_type_id").val();
//	if(re==""){
//		lalert('请至少选择一条数据！','warn');
//		$("#checkGroupId").prop("checked",false);
//	}else{
//		var idsArray=re[0].split(",");
//		var namesArray=re[1].split(",");
//		if(spid!=""&&tyid!=""&&(species!=spid||typeid!=tyid)){
//			 layer.confirm("设备类别或类型发生改变，表单将清空，确定导入？", {
//			    btn: ['确定','取消'] //按钮
//			}, function(){	
//				$("#repairResultMachine tr").empty();
//				machineInfoBack(idsArray,namesArray);
//			})
//		}else{	
//			machineInfoBack(idsArray,namesArray);
//		}	
//	}
}

/**
 * 设备信息赋值
 */
function machineInfoBack(idsArray,namesArray,species,typeid){
//	var rowLength = $("#repairResultMachine tr").length;
	 $("#repairResultMachine").empty();
	for(var i=0;i<idsArray.length;i++){
		var rowindex = i;
		var str =  "<tr id='machine_name"+i+"'> "
				+"<td colspan='2' class='gray fb'><input type='hidden' value='"+idsArray[i]+"' name='repairMachineDetailList["+rowindex+"].machine_id' dc=''/><input type='checkbox' class='mr25' id='"+rowindex+"' name='machineCheckbox'/>"+namesArray[i]+"</td> "
				+"</tr>"
				+"<tr id='down_hour"+i+"'>"
				+"<td>停机时间&nbsp;&nbsp;</td>"
				+"<td><input type='text' class='w470' name='repairMachineDetailList["+rowindex+"].down_hour' dc='' maxlength='5'/></td>"
				+"</tr>"
				+"<tr id='repair_detail"+i+"'>"
				+"<td>维修内容&nbsp;&nbsp;</td>"
				+"<td id='repairDetail"+rowindex+"'></td>"
				+"</tr>";
	
		$("#repairResultMachine").append(str);
		$.ajax({
			url:getRequestUrl("/RepairOrderInfoController/queryDownList7.json"),
			dataType:"json",
			data:{"maintenanceItemInfo.machine_species_id":species,"maintenanceItemInfo.machine_type_id":typeid},
			async: false,
			success : function(result) {
				if(result.opflag){
					$("#repairDetail" + rowindex).html(
							$("#repairDetailGroupDownListTemplate").render(result)
					);
					$("#repairDetail" + rowindex).find("[data-name='select1']").attr("name", "repairMachineDetailList["+rowindex+"].repair_detail");
					$("#repairDetail" + rowindex).find("[data-name='select1']").attr("dc",true);
					goBackToAddResult();
				}
			},
			error : function(error) {
				lalert('网络原因操作失败！','error');
			}
		});
	}	
}

/**
 * 跳转页
 */
function goBackToList(){
	var page = "/pm/mobile/m_repair/m_repair_list.jsp";
	pageForward(page);
}

/**
 * 跳转页
 */
function goBackToAddResult(){
	$("#_centent").attr("style","");
	$("#machine_choose").attr("style","display:none");
	$("#cost_list").attr("style","display:none");
}

/**
 * 选择设备
 */
function chooseMachine(){
	getMachineInfo();
	$("#_centent").attr("style","display:none");
	$("#machine_choose").attr("style","");
	$("#cost_list").attr("style","display:none");
}

/**
 * 删除设备
 */
function deleteMachine(){
	if($('input[name="machineCheckbox"]:checked').length == 0){
		layer.open({
			content: '请至少选择一条',
		    btn: ['确定'], //按钮
		    yes: function() {
		    	layer.closeAll();
		    }
		})
	}else{
		$('input[name="machineCheckbox"]:checked').each(function(){
			var index = $(this).attr("id");
			$("#machine_name"+index).remove();
			$("#down_hour"+index).remove();
			$("#repair_detail"+index).remove();
		});
	}
	if($('#repairResultMachine tr').length == 0){
		$("#repairResultMachineDiv").hide();
	}
}