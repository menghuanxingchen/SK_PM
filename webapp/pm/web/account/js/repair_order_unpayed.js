var menuid='menu61';
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
	WorkOutQueryDataList();
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
//	d.setMonth(d.getMonth()-2);
//	var date =  d.getFullYear()+"-"+(d.getMonth()>9?d.getMonth():"0"+(d.getMonth()))+"-"+(d.getDate()>9?d.getDate():"0"+d.getDate());
	var str2 = '';
	 if(d.getMonth()-2>9){
		 str2 += d.getFullYear()+'-';
		 str2  += d.getMonth()-2+'-';
	 }else{
//		 if(d.getMonth()-2 == -2){
		 if(d.getMonth()-2 <= 0){
			 str2 += d.getFullYear()-1+'-';
			 str2  += '12'+'-01';
		 }else{
			 str2 += d.getFullYear()+'-';
			 str2  += '0'+(d.getMonth()-2)+'-';
			 str2  += d.getDate()>9?d.getDate():"0"+d.getDate();
//			 strf = str2.substring(0, 6);
//			 strb = str2.substring(7, 11);
//			 strfn = strf+strb;
		 }
	 }
	 
	
	$("#start_dt").val(str2);
	//$("#end_dt").val(laydate.now());
	$("#end_dt").val(getPreMonth());
}
/**
 * 显示上个月的最后一天
 *
 * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
 */
function getPreMonth() {
    
    var nowdays = new Date();
    var year = nowdays.getFullYear();
    var month = nowdays.getMonth();
    if(month==0)
    {
        month=12;
        year=year-1;
    }
    if (month < 10) {
        month = "0" + month;
    }
    var firstDay = year + "-" + month + "-" + "01";//上个月的第一天

    

    var myDate = new Date(year, month, 0);
    var lastDay = year + "-" + month + "-" + myDate.getDate();//上个月的最后一天
    return lastDay;
}
/**
 * 维修单信息查询
 */
function queryDataList(){
	
	WorkOutQueryDataList();
	var postData = collectData("search_area");
	postData["repairOrderInfo.approval_state"]=Class.getConstant('APPROVAL_STATE_DONE');
	postData["repairOrderInfo.pay_yn"]=Class.getConstant('PAY_N');
	postData["repairOrderInfo.repair_type"]=Class.getConstant('PAY_Y');
	postData["unpayedpageflag"]=Class.getConstant('UNPAYEDPAGE_FLAG');
	layer.load('loading');//添加进度条
	var parameterData = {
			url:getRequestUrl("/RepairOrderInfoController/queryRepairOrderInfoList.json"),
			successfunc:renderListDataFun,
			customArray:postData,
			pageller:"pagefoot",
			pageIndex:1,
			pageSize:50,
//			checkedId:"",
			isAsync:false
	};
	pageBarFortable(parameterData)
}

//回调
function renderListDataFun(result){
	layer.closeAll('loading');
	$( "#t_r_content" ).html(
		$("#reapirOrderListTemplate").render(result)
	);
	var d = new Date(); //实例一个时间对象
//	d.setMonth(d.getMonth()+1);
	d.setMonth(d.getMonth());
	var date =  d.getFullYear()+"-"+(d.getMonth()>9?d.getMonth():"0"+(d.getMonth()))+"-"+(d.getDate()>9?d.getDate():"0"+d.getDate());
	$( "#t_r_content tr" ).each(function(i){
		laydate({
			  elem: '#account_dt'+i
			});
		$('#account_dt'+i).val(date);
	})
	//var table_height=$("#table_height").height();
	if(result.length>10)
	{
		$("#last").css({paddingRight:'25px'});
		
	}else{
		$("#last").css({paddingRight:'8px'});
	}
	//奇偶行颜色不同
	$('.t_r_t tbody tr:odd').addClass('odd');
	$('.t_r_t tbody tr:even').addClass('eve');
	
}	

/**
 * 结算
 * @param id
 */
function updateAccount(id,row){
	var message = "确认结算？";
	layer.confirm(
			message,
			function(index){
				$.ajax({
					url:getRequestUrl("/RepairOrderInfoController/updateForAccount.json"),
					dataType:"json",
					data:{"repairOrderInfo.id":id,"repairOrderInfo.account_date":$("#account_dt"+row).val()},
					success:function(result){
						layer.close(index);
						lalert('save','success');
						queryDataList();
					},
					error:function(error){
						layer.close(index);
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
	var page = "/pm/web/repair/repair_order_detail.jsp?id="+id+"&&flag="+flag+"&&menuid="+menuid;
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
			$("#departGroupSub").html($("#departGroupSubDownListTemplate").render(result));
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


/**
 * 查询方法
 */
function WorkOutQueryDataList(){
	//alert($("#start_dt").val());

	var postData = collectData("search_area");
	postData["pay_yn"]=Class.getConstant('PAY_N');
	var parameterData = {
			url:getRequestUrl("/WorkoutInfoController/queryWorkoutDataList.json"),
			successfunc:WorkOutRenderListDataFun,
			customArray:postData,
			pageller:"pagefoot1",
			pageIndex:1,
			pageSize:50,
			checkedId:"checkboxGroupId",
			isAsync:false
	};
	pageBarFortable(parameterData)
}

//回调
function WorkOutRenderListDataFun(result){
	$( "#w_t_r_content" ).html(
			$("#dataListTemplate").render( result
		          )
	);
	//奇偶行颜色不同
	$('.w_t_r_content tbody tr:odd').addClass('odd');
	//var table_height=$("#table_height").height();
	if(result.length>10)
	{
		$("#last").css({paddingRight:'25px'});
		
	}else{
		$("#last").css({paddingRight:'8px'});
	}
	
	//默认给一个结算日期
	var d = new Date(); //实例一个时间对象
	d.setMonth(d.getMonth());
	var date =  d.getFullYear()+"-"+(d.getMonth()>9?d.getMonth():"0"+(d.getMonth()))+"-"+(d.getDate()>9?d.getDate():"0"+d.getDate());
	$( "#w_t_r_content tr" ).each(function(i){
		laydate({
			  elem: '#pay_time'+i
			});
		$('#pay_time'+i).val(date);
	})

	changePayStatus();
}

/**
 * 新增
 */
function addEntityDataFun(){
	var page = "/pm/web/account/workout_add.jsp";
	pageForward(page);
}

/**
 * 修改
 * @param id
 */
function updateEntityDataFun(id){
	var page = "/pm/web/account/workout_upd.jsp?id="+id;
	pageForward(page);
}

/**
 * 修改
 * @param id
 */
function updatePayYnFun(id,pay_yn,row){
	
	if(pay_yn =="N"){
		var message = "确认结算？";
		layer.confirm(
				message,
				function(index){
					$.ajax({
						url:getRequestUrl("/WorkoutInfoController/updateForPayYn.json"),
						dataType:"json",
						data:{"workOutInfo.work_out_id":id,"workOutInfo.pay_time":$("#pay_time"+row).val()},
						success:function(result){
							layer.close(index);
							lalert('save','success');
							WorkOutQueryDataList();
						},
						error:function(error){
							layer.close(index);
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
}


/**
 * 修改支付与否按钮状态
 */
function changePayStatus(){
 
 
	var totalval = parseFloat(0);
	$(".pay_nm").each(function(index,element){
		var tempvalu= $(this).text();
		
		if(tempvalu=="未结算"){
			$(this).attr("class","btn3");
		}
	}); 
}

/**
 * 跳转到详细页面
 * @param id
 */
function detailDataFun(id){
	var page = "/pm/web/account/workout_detail.jsp?id="+id;
	pageForward(page);
}


/**
 * 跳转到详细页面
 * @param id
 */
function updateRemarkDataFun(id){
	var page = "/pm/web/account/workout_upd.jsp?id="+id;
	pageForward(page);
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
					url:getRequestUrl("/WorkoutInfoController/deleteEntityData.json"),
					dataType:"json",
					data:{"workOutInfo.work_out_id":id},
					success:function(result){
						lalert("delete","success");
						WorkOutQueryDataList();
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
