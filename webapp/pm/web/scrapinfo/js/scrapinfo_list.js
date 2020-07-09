$(function(){
	menu('menu91');
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
	var postData={"sysCodeInfo.code_value":$("#species_id").val()};
	$.ajax({
		url:getRequestUrl("/ScrapController/beforeDataList.json"),
		dataType:"json",
		data:[],
		success:function(result){
			$("#machinespecies").html($("#machinespeciesListTemplate").render(result));
			$("#machinetype").html($("#machinetypeListTemplate").render(result));
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
	var start = {
			  elem: '#start',
			  istoday: true,
			 // min: laydate.now(+1), //最大日期
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
	//$("#start").val(laydate.now(-7));
	var myDate = new Date();
	var month=myDate.getMonth();
	if(month < 10){
		month="0"+month;
	}
	//$("#start").val(myDate.getFullYear()+"-"+month+"-01");//开始日期上月1号
	$("#start").val(myDate.getFullYear()+"-01-01");//开始日期当前年1月1号		
	$("#search_btn").bind('click',queryDataList);
	
	// 类别类型联动
	$("#machinespecies").change(function(){
		goforTypeList();
	});
}

/**
 * 查询方法
 */
function queryDataList(){
	var postData = collectData("search_area");
	var parameterData = {
			url:getRequestUrl("/ScrapController/queryDataList.json"),
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
	if(result.length>10)
	{
		$("#last").css({paddingRight:'25px'});
		
	}else{
		$("#last").css({paddingRight:'8px'});
	}
	
	//奇偶行颜色不同
	$('.t_r_content tbody tr:odd').addClass('odd');
	//var table_height=$("#table_height").height();
	
	if(auth != '1'){
		$("td[name='approval2']").html("");
	}
	if(auth != '2'){
		$("td[name='approval1']").html("");
	}
}

/**
 * 新增页面跳转
 */
function addEntityForward(){
	var page = "/ScrapController/addEntityForward.do";
	pageForward(page);
}

/**
 * 修改
 * @param id
 */
function updateEntityDataFun(id,type){
	var obj = [{
			"id" : id
	}];
	forwardPage("/ScrapController/goToUpdateEntityData.do",obj);
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
					url:getRequestUrl("/ScrapController/deleteEntityData.json"),
					dataType:"json",
					data:{"scrapInfo.scrap_id":id},
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
//联动得到类型
function goforTypeList(){
	var postData = {"sysCodeInfo.code_value":$("#species_id").val(),"sysCodeInfo.code_group_value":Class.getConstant('MACH_TYPE_CODE')};
	$.ajax({
		url:getRequestUrl("/MachineController/getSubCodeList2.json"),
		dataType:"json",
		data:postData,
		success:function(data){
			$( "#machinetype" ).html(
					$("#machinetypeListTemplate").render(data)
			);
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}
//审批
function updateApproveAndReject(id,type,num){
	var postData;
	if(num==1){
		var postData = {"scrapInfo.scrap_id":id,"scrapInfo.approval_id1":type};
	}else if(num==2){
		var postData = {"scrapInfo.scrap_id":id,"scrapInfo.approval_id2":type};
	}
	$.ajax({
		url:getRequestUrl("/ScrapController/updateEntityData.json"),
		dataType:"json",
		data:postData,
		success:function(data){
			lalert("approval","success");
			queryDataList();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

