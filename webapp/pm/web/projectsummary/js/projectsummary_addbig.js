$(function(){
	menu('menu81');
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
	$("#projectNm").focus();
	$.ajax({
		url:getRequestUrl("/ProjectSummaryController/beforeDataList.json"),
		dataType:"json",
		data:[],
		success:function(data){
			
			$( "#projectPlanStateFormAdd" ).html(
					$("#projectPlanStateAddTemplate").render(data)
			);
			$( "#projectStep" ).html(
					$("#projectStepTemplate").render(data)
			);
			
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
	var planStartDate = {
			  elem: '#planStartDate',
			  istoday: true,
			 // min: laydate.now(+1), //最大日期
			  isclear: false, //是否显示清空
			  istoday: true //是否显示今天
			};
	laydate(planStartDate);
	$("#planStartDate").val(laydate.now());
	var planEndDate = {
			  elem: '#planEndDate',
			  istoday: true,
			 // min: laydate.now(+1), //最大日期
			  isclear: false, //是否显示清空
			  istoday: true //是否显示今天
			};
	laydate(planEndDate);
	$("#planEndDate").val(laydate.now());
			
	$("#search_btn").bind('click',queryDataList);
	
	
	$("#closeBtn").bind('click',function(){
		pageForward('/ProjectSummaryController/defaultJsp.do');
	});
	
	$("#submitBtn").bind('click',function(){
		saveForm();
	});
}

/**
 * 查询方法
 */
function queryDataList(){
	var postData = collectData("search_area");
	var parameterData = {
			url:getRequestUrl("/ProjectSummaryController/queryDataListBigGroup.json"),
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
	
}

function saveForm(){
	$("#submitBtn").unbind('click');
	var checkfalg =	checkFormValue('formId');
	if(checkfalg){
		var message = "确认提交？";
		var postData = collectData("formId");
		layer.confirm(
				message,
				function(index){
					layer.load();//添加进度条
					$.ajax({
						url:getRequestUrl("/ProjectSummaryController/addEntityData.json"),
						dataType:"json",
						data:postData,
						success:function(result){
							layer.closeAll('loading'); //关闭进度条
							if(result.opflag){
								lalert('save','success',function(){
									pageForward('/ProjectSummaryController/defaultJsp.do');
								});
							}else{
								lalert(result.opmessage,'error');
							}
							
						},
						error:function(error){
							layer.closeAll('loading'); //关闭进度条
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
		bindevent();
}

/**
 * 添加选项调用方法
 */
var count=0;
function addItem(){
	var str =   "<tr id='addr"+count+"'> "
				+ "  <td><input class='laydate-icon w110' type='text' name='projectSummaryDetailList["+count+"].work_date' id='date"+ count + "' dc=''/></td> "
				+ "  <td><input type='text' name='projectSummaryDetailList["+count+"].work_content' dc='true' maxlength='100'/></td>  "
				+ "  <td><a href='javascript:deleteItem("+count+");' class='btn3' >删除</a></td>   "
				+ "</tr> ";
	$(str).appendTo("#addRowsTable")
	var date = {
			  elem: '#date'+ count + '',
			  istoday: true,
			 // min: laydate.now(+1), //最大日期
			  isclear: false, //是否显示清空
			  istoday: false //是否显示今天
			};
	laydate(date);
	$("#date"+ count + "").val(laydate.now());
	bindValidateEvent('addRowsTable');
	count++;
}

/**
 * 删除选项调用方法
 */
function deleteItem(str){
	$("#addr"+str).remove();
}

function getProjectNm(proNm,groupId){
	$("#projectNm").attr("readonly","true");
	$("#projectNm").val(proNm);
	$("#groupId").val(groupId);
}
