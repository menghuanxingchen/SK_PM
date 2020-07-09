$(function(){
	menu('menu81');
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
	bindevent();
	$.ajax({
		url:getRequestUrl("/ProjectSummaryController/beforeDataList.json"),
		dataType:"json",
		data:[],
		async:false,
		success:function(data){
			$( "#projectPlanTypeForm" ).html(
					$("#projectPlanTypeTemplate").render(data)
			);
			$("#projectType").focus();
			$( "#projectPlanStateForm" ).html(
					$("#projectPlanStateTemplate").render(data)
			);
				/*$("#work_date1").text(data.projectSummaryInfoDtoEo.workDate1);
				$("#work_date2").text(data.projectSummaryInfoDtoEo.workDate2);*/
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
	queryDataList();
}

function getWorkDate(){
	var postData = collectData("search_area");
	$.ajax({
		url:getRequestUrl("/ProjectSummaryController/getWorkDataList.json"),
		dataType:"json",
		data:postData,
		async:false,
		success:function(data){
			if(data.projectSummaryInfoDtoEo.workDate1==null){
				$("#work_date1").text("最近一次时间");
			}else{
				$("#work_date1").text(data.projectSummaryInfoDtoEo.workDate1);
			}
			if(data.projectSummaryInfoDtoEo.workDate2==null){
				$("#work_date2").text("最近二次时间");
			}else{
				$("#work_date2").text(data.projectSummaryInfoDtoEo.workDate2);
			}
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
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
	$("#start").val();
			
	$("#search_btn").bind('click',queryDataList);
}

/**
 * 查询方法
 */
function queryDataList(){
	var postData = collectData("search_area");
	var parameterData = {
			url:getRequestUrl("/ProjectSummaryController/queryDataList.json"),
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
var excelList;
//回调
function renderListDataFun(result){
	excelList = result;
	$( "#t_r_content" ).html(
			$("#dataListTemplate").render( result
		          )
	);
	if(result.length>9)
	{
		$("#last").css({paddingRight:'25px'});
		
	}else{
		$("#last").css({paddingRight:'8px'});
	}
	
	//奇偶行颜色不同
	$('.t_r_content tbody tr:odd').addClass('odd');
	//var table_height=$("#table_height").height();
	
}

/**
 * 大项目新增页面跳转
 */
function bigAddEntityForward(){
	var page = "/ProjectSummaryController/bigAddEntityForward.do";
	pageForward(page);
}

/**
 * 小项目新增页面跳转
 */
function smallAddEntityDataFun(){
	var page = "/ProjectSummaryController/smallAddEntityDataFun.do";
	pageForward(page);
}

/**
 * 修改
 * @param id
 */
function updateEntityDataFun(id,type){
	var obj = [{
			"id" : id,"type":type
	}];
	forwardPage("/ProjectSummaryController/goToUpdateEntityData.do",obj);
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
					url:getRequestUrl("/ProjectSummaryController/deleteEntityData.json"),
					dataType:"json",
					data:{"projectSummaryInfo.project_id":id},
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

function excelDownload(){
	var myArray=[];
	for(var i=0;i<excelList.length;i++){
		myArray[i]=excelList[i].project_id;
	}
	layer.load();
	$.ajax({
		url:getRequestUrl("/ProjectSummaryController/excelDownload.json"),
		dataType:"json",
		data:{"excelList":myArray},
		success:function(result){
			layer.closeAll('loading');
			lalert("download","success");
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

