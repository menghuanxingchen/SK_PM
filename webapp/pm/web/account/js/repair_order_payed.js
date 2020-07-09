var menuid='menu62';
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
	d.setMonth(d.getMonth());
//	var date =  d.getFullYear()+"-"+(d.getMonth()>9?d.getMonth():"0"+(d.getMonth()))+"-"+(d.getDate()>9?d.getDate():"0"+d.getDate());
	var date =  "";
	if(d.getMonth() == '0'){
		date =  d.getFullYear()-1 +"-12-01";
	}else{
		date =  d.getFullYear()+"-"+(d.getMonth()>9?d.getMonth():"0"+(d.getMonth()))+"-01";
	}
	
//	$("#start_dt").val(date);
//	$("#end_dt").val(laydate.now());
	$("#start_dt").val(date);
	$("#end_dt").val(preMonthLast());
	
	$("#reportExport_btn").bind('click',reportExport);
}

/**
 * 查询当前月份第一天
 * @returns {String}
 */
function currentMonthStart(){
	var d = new Date(),str = '';
	 str += d.getFullYear()+'-';
	 if(d.getMonth()>9){
		 str  += d.getMonth() + 1+'-';
	 }else{
		 str  += '0'+(d.getMonth() + 1)+'-';
	 }
	 
	 str  += '01';
	return str;
}
/**
 * 查询当前月份最后一天
 * @returns {String}
 */
function currentMonthLast(){
	var d = new Date();
	var str2 = '';
	 str2 += d.getFullYear()+'-';
	 if(d.getMonth()>9){
		 str2  += d.getMonth() + 1+'-';
	 }else{
		 str2  += '0'+(d.getMonth() + 1)+'-';
	 }
	
	var year= d.getFullYear();
	var month = d.getMonth() + 1;
	 var new_year = year;    //取当前的年份           
	 var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）           
	 if(month>12)            //如果当前大于12月，则年份转到下一年           
	 {          
	  new_month -=12;        //月份减           
	  new_year++;            //年份增           
	 }          
	 var new_date = new Date(new_year,new_month,1);                //取当年当月中的第一天           
	 var date_count =   (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月的天数         
	 var last_date =   new Date(new_date.getTime()-1000*60*60*24);//获得当月最后一天的日期  
	 str2  += date_count;
	return str2;  
}

/**
 * 查询上个月份最后一天
 * @returns {String}
 */
function preMonthLast(){
	var d = new Date();
	var str2 = '';
	 if(d.getMonth()>9){
		 str2 += d.getFullYear()+'-';
		 str2  += d.getMonth()+'-';
	 }else{
		 if(d.getMonth() == '0'){
			 str2 += d.getFullYear()-1+'-';
			 str2  += '12'+'-';
		 }else{
			 str2 += d.getFullYear()+'-';
			 str2  += '0'+(d.getMonth())+'-';
		 }
	 }
	
	var year= d.getFullYear();
	var month = d.getMonth();
	 var new_year = year;    //取当前的年份           
	 var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）           
	 if(month>12)            //如果当前大于12月，则年份转到下一年           
	 {          
	  new_month -=12;        //月份减           
	  new_year++;            //年份增           
	 }          
	 var new_date = new Date(new_year,new_month,1);                //取当年当月中的第一天           
	 var date_count =   (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月的天数         
	 var last_date =   new Date(new_date.getTime()-1000*60*60*24);//获得当月最后一天的日期  
	 str2  += date_count;
	return str2;  
}
/**
 * 维修单信息查询
 */
function queryDataList(){
	WorkOutQueryDataList();
	var checkfalg =	checkFormValue('aaa');
	if(checkfalg){
		var postData = collectData("search_area");
		postData["repairOrderInfo.pay_yn"]=Class.getConstant('PAY_Y');
		postData["repairOrderInfo.repair_type"]=Class.getConstant('PAY_Y');
		postData["pageflag"]=Class.getConstant('PAGE_FLAG');
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
		var datestr = $(this).find(":eq(13)").text();
		$(this).find(":eq(13)").html(datestr.substring(0,10));
		var val1 = $(this).find(":eq(10)").text();
		var val2 = $(this).find(":eq(11)").text();
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
	$('.t_r_t tbody tr:even').addClass('eve');
	
	$("#t_r_content tr").unbind();
	$("#t_r_content tr").bind('click',function(){
		$( "#t_r_content tr" ).each(function(i){
			$(this).removeAttr("style");
		})
		$(this).attr("style","background-color: #ff8608; color:#fff;")
	});
}

/**
 * 查询维修详情
 * @param id
 */
function queryRepairDataEntity(id,flag){
	var page = "/pm/web/repair/repair_order_detail.jsp?id="+id+"&&flag="+flag+"&&menuid="+menuid;;
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
 * 报表导出方法
 */
function reportExport(){
	layer.load();
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/accountExport.json"),
		dataType:"json",
		data:{"repairOrderInfoDTO.start_dt":$("#start_dt").val(),"repairOrderInfoDTO.end_dt":$("#end_dt").val(),"repairOrderInfoDTO.pay_yn":Class.getConstant('PAY_Y'),"repairOrderInfoDTO.repair_type":Class.getConstant('PAY_Y')},
		success:function(result){
			layer.closeAll('loading');
			if(result.filePath == ''){
				lalert("网络原因操作失败！","error");
			}else{
				//window.open(result.filePath,"page");
				location.href = path +  result.filePath;
			}
		},
		error:function(error){
			layer.closeAll('loading');
			lalert("网络原因操作失败！","error");
		}
	});
}


/**
 * 查询方法
 */
function WorkOutQueryDataList(){
	//alert($("#start_dt").val());

	var postData = collectData("search_area");
	postData["pay_yn"]=Class.getConstant('PAY_Y');
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
function updatePayYnFun(id,pay_yn){
	
	if(pay_yn =="N"){
		var message = "确认结算？";
		layer.confirm(
				message,
				function(index){
					$.ajax({
						url:getRequestUrl("/WorkoutInfoController/updateForPayYn.json"),
						dataType:"json",
						data:{"workOutInfo.work_out_id":id},
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