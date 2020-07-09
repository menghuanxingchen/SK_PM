var menuid='menu31';
$(function(){
	menu(menuid);
	initPage();
});


/**
 * 初始化页面
 * */
function initPage(){
	bindevent();
	downList();
}

/**
 * 绑定事件
 */
function bindevent(){
	var start = {
			  elem: '#start_dt',
			  istoday: true,
			  choose: function(datas){
			     end.min = datas; //开始日选好后，重置结束日的最小日期
			     end.start = datas //将结束日的初始值设定为开始日
			  }
			};
	var end = {
			  elem: '#end_dt',
			  istoday: true,
			  choose: function(datas){
			    start.max = datas; //结束日选好后，重置开始日的最大日期
			  }
			};
	laydate(start);
	laydate(end);
	
	var d = new Date(); //实例一个时间对象
/*	var str2 = '';
	 if(d.getMonth()-2>9){
		 str2 += d.getFullYear()+'-';
		 str2  += d.getMonth()-2+'-';
	 }else{
		 if(d.getMonth()-2 == '0'){
			 str2 += d.getFullYear()-1+'-';
			 str2  += '12'+'-';
		 }else{
			 str2 += d.getFullYear()+'-';
			 str2  += '0'+(d.getMonth()-2)+'-';
		 }
	 }
	 str2  += d.getDate()>9?d.getDate():"0"+d.getDate();*/
	var date =  d.getFullYear()-1+"-"+(d.getMonth()+1>9?d.getMonth()+1:"0"+(d.getMonth()+1))+"-"+(d.getDate()>9?d.getDate():"0"+d.getDate());
	$("#start_dt").val(date);
	$("#end_dt").val(laydate.now());
}

/**
 * 查询条件判断
 */
function conditionCheck(){
	var okFlag = true;
	if($("#repair_user_choose").val() == '' && $("#depart_group").val() != ''){
		var checkfalg =	checkFormValue('search_area');
		if(!checkfalg){
			okFlag = false;
		}
	}
	return okFlag;
}
/**
 * 维修单信息查询
 */
function queryDataList(){
	if(conditionCheck()){
		var postData = collectData("search_area");
		layer.load('loading');//添加进度条
		var parameterData = {
				url:getRequestUrl("/RepairOrderInfoController/queryRepairOrderInfoList.json"),
				successfunc:renderListDataFun,
				customArray:postData,
				pageller:"pagefoot",
				pageIndex:1,
				pageSize:50,
//				checkedId:"",
				isAsync:false
		};
		pageBarFortable(parameterData)
	}
}

//回调
function renderListDataFun(result){
	layer.closeAll('loading');
	$( "#t_r_content" ).html(
		$("#reapirOrderListTemplate").render(result)
	);
	var allbefore = 0;
	var allafter = 0;
	$( "#t_r_content tr" ).each(function(i){
		var val1 = $(this).find(":eq(8)").text();
		var val2 = $(this).find(":eq(9)").text();
		if(val1 == "" || val1 == null){
			val1 = 0;
		}
		if(val2 == "" || val2 == null){
			val2 = 0;
		}
		allbefore = allbefore + parseFloat(parseFloat(val1).toFixed(2));
		allafter = allafter + parseFloat(parseFloat(val2).toFixed(2));
	})
	if(allbefore == "" || allbefore.toString() == "NaN"){
		allbefore = 0;
	}
	if(allafter == "" || allafter.toString() == "NaN"){
		allafter = 0;
	}
	$("#tatalBefere").html(parseFloat(allbefore).toFixed(2));
	$("#tatalAfter").html(parseFloat(allafter).toFixed(2));
	//var table_height=$("#table_height").height();
	if(result.length>10)
	{
		$("#last").css({paddingRight:'25px'});
		
	}else{
		$("#last").css({paddingRight:'8px'});
	}
	//奇偶行颜色不同
	$('.t_r_t tbody tr:odd').addClass('odd');
	$('.t_r_t tbody tr:even').addClass('odd');
}
	
/**
 * 新增
 */
function addDataFun(){
	var page = "/pm/web/repair/repair_order_add.jsp";
	pageForward(page);
}

/**
 * 修改
 * @param id
 */
function updateDataFun(id){
	var message = "确认修改？";
	layer.confirm(
			message,
			function(index){
				layer.close(index);
				var page = "/pm/web/repair/repair_order_update.jsp?id="+id;
				pageForward(page);
			},
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
				layer.load('loading');//添加进度条
				$.ajax({
					url:getRequestUrl("/RepairOrderInfoController/deleteRepairOrderInfoData.json"),
					dataType:"json",
					data:{"repairOrderInfo.id":id},
					success:function(result){
						layer.close(index);
						layer.closeAll('loading');
						lalert('delete','success');
						queryDataList();
					},
					error:function(error){
						layer.close(index);
						layer.closeAll('loading');
						lalert('网络原因操作失败！','error');
					}
				});
			},
			function(index){
				layer.close(index);
				return;
			}
	);
}


/**
 * 查询维修详情
 * @param id
 */
function queryRepairDataEntity(id,flag){
	var page = "/pm/web/repair/repair_order_detail.jsp?id="+id+"&flag="+flag+"&menuid="+menuid;
	pageForward(page);
}

/**
 * 下拉列表
 * */
function downList(){
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/queryDownList4.json"),
		dataType:"json",
		data:"",
		success:function(result){
			$("#departGroup").html($("#departGroupdownListTemplate").render(result));
			$("#approveGroup").html($("#approveGroupdownListTemplate").render(result));
			$("#departGroupSub").html($("#departGroupSubDownListTemplate").render(result));
			$("#userChoose").html($("#userChooseGroupSubDownListTemplate").render(result));
			$("#allUser").html($("#allUserGroupSubDownListTemplate").render(result));
			$("#allUser2").html($("#allUserGroupSubDownListTemplate2").render(result));
			$("#repairPlace").html($("#repairPlaceGroupSubDownListTemplate").render(result));
			$("#repairCategory").html($("#repairCategoryGroupSubDownListTemplate").render(result));
			$("#repairClassify").html($("#repairClassifyListTemplate").render(result));
//			$("#deal_user_id option[value='"+result.loginUserId+"']").attr("selected","selected");
			$("#depart_group").unbind();
			$("#depart_group").bind("change",function(){
				queryForSubDepart($("#depart_group").val());
			});
			queryDataList();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * 审批/驳回
 */
function updateApproveAndReject(id,str){
	var message = "";
	if(str=='R'){
		message = "是否驳回？";
	}else if(str=='A'){
		message = "是否审批？";
	}else{
		message = "是否确认？";
	}
	layer.confirm(
			message,
			function(index){
				layer.load('loading');//添加进度条
				$.ajax({
					url:getRequestUrl("/RepairOrderInfoController/updateApproveAndReject.json"),
					dataType:"json",
					data:{"repairOrderInfo.id":id,"updateflag":str},
					success:function(result){
						layer.close(index);
						layer.closeAll('loading');
						lalert('save','success',function(){
							queryDataList();
						});
					},
					error:function(error){
						layer.close(index);
						layer.closeAll('loading');
						lalert('网络原因操作失败！','error');
					}
				});
			},
			function(index){
				layer.close(index);
				return;
			}
	);
}

/**
 * 分配人员
 */
function distributFun(id){
	var page = "/pm/web/repair/distribution_add.jsp?id="+id;
	pageForward(page);
}

/**
 * 不能编辑提示
 */
//function editEnable(str){
//	var message = '';
//	if(str == 'edit'){
//		message = '修改';
//	}else{
//		message = '删除';
//	}
//	lalert('审批后不可'+message+'！','warn');
//}

/**
 * 报价
 */
function offerAddFun(id){
	var page = "/pm/web/repair/offer_add.jsp?id="+id;
	pageForward(page);
}

/**
 * 维修
 */
function repairResultFun(id){
	var page = "/pm/web/repair/repair_result_add.jsp?id="+id;
	pageForward(page);
}
/**
 * 部门onChange事件
 * */
function queryForSubDepart(group){
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/queryDownList2.json"),
		dataType:"json",
		data:{"sysUserInfo.dept_code":group},
		success:function(result){
			$("#departGroupSub").html($("#departGroupSubDownListTemplate").render(result));
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}