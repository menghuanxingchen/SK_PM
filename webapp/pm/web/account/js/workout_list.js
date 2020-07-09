$(function(){
	menu('menu63');
   initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
 
	bindevent();
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
	d.setMonth(d.getMonth()-2);
	var date =  d.getFullYear()+"-"+(d.getMonth()>9?d.getMonth():"0"+(d.getMonth()))+"-"+(d.getDate()>9?d.getDate():"0"+d.getDate());
	
	$("#start_dt").val(date);
	$("#end_dt").val(laydate.now());
	
	$("#search_btn").bind('click',WorkOutQueryDataList);
}

/**
 * 查询方法
 */
function WorkOutQueryDataList(){
	//alert($("#start_dt").val());
	
	var postData = collectData("search_area");
	var parameterData = {
			url:getRequestUrl("/WorkoutInfoController/queryWorkoutDataList.json"),
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

