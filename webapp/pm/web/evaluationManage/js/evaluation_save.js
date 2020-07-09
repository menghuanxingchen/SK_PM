$(function(){
	menu('menuC15');
	initPage();
});
function getProcessorId(){
	var commonData = parent.CsGlobalObj||parent.parent.CsGlobalObj;
	var id =commonData.currentUserid||"";
	return id;
}
var currentUserid =getProcessorId();
/**
 * 初始化页面
 * */
function initPage(){
	//结束按钮权限
	$("#endEvaluation").hide()
	
	if(currentUserid != "lg00302" && currentUserid != "lg00158" && currentUserid != "lg00157"){
		$("#saveEvaluation").hide()
	}
	
	//结束按钮权限
	if(currentUserid == "lg00258" || currentUserid == "admin"){
		$("#endEvaluation").show()
	}
	
	$.ajax({
		url:getRequestUrl("/SvaluationManageController/beforeDataList.json"),
		dataType:"json",
		data:[],
		success:function(data){
			$( "#evaluationOneForm" ).html(
					$("#evaluationOneTemplate").render(data)
			);
			
			$( "#evaluationTwoForm" ).html(
					$("#evaluationTwoTemplate").render(data)
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
	var start = {
			  elem: '#start',
			  istoday: true,
			  format: 'YYYY'
			};
	
	laydate(start);
	
	var myDate = new Date();
	
	$("#start").val(myDate.getFullYear());//开始日期当前年1月
	
	$("#search_btn").bind('click',queryDataList);
}

/**
 * 查询方法
 */
function queryDataList(){

	var postData = collectData("search_area");

	var parameterData = {
			url:getRequestUrl("/SvaluationManageController/queryEvaluationSaveDataList.json"),
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
	for(var i=0;i<result.length;i++){
		result[i]['currentUserid']=currentUserid;
	}
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
}

//提交前check
function checkSCYearValue(){
	var EvaluationOne = $("#EvaluationOne").val();

	
	var EvaluationTwo = $("#EvaluationTwo").val();

	if(EvaluationOne!="" && EvaluationOne != null  && EvaluationTwo!="" && EvaluationTwo != null){
		return true;
	}else{
		return false;
	}
}

/**
 * 临时保存
 */
function addEntityDataFun(){
	
}

//提交前check
function checkYearValue(){
	var start = $("#start").val();

	if(start!="" && start != null  ){
		return true;
	}else{
		return false;
	}
}

function checkListValue(){
	var opflag;
	var postData = collectData("search_area");
	$.ajax({
		url:getRequestUrl("/SvaluationManageController/checkListValueData.json"),
		dataType:"json",
		async: false,
		data:postData,
		success:function(result){
			opflag =  result.opflag
		},
		error:function(error){
		    lalert('网络原因操作失败！','error');
		}
	});
	return opflag;
}

/**
 * 结束
 */
function endEntityDataFun(){
	var checkYearfalg =	 checkYearValue();
	var checkListfalg = checkListValue();

	if(!checkYearfalg){
		lalert('请选择年份！','error');
	}else if(checkListfalg == "end"){
		lalert('评价已经结束！','error');
	}else{
		
		layer.confirm("是否结束"+$("#start").val()+$("#yearDate").val()+"评价?", {
		    btn: ['确定','取消'] //按钮
			}, function(){
			$("#submitBtn").unbind('click');
			var postData = collectData("search_area");
			
			layer.load();//添加进度条
			$.ajax({
					url:getRequestUrl("/SvaluationManageController/updateEndEntityData.json"),
					dataType:"json",
					data:postData,
					success:function(result){
						layer.closeAll('loading'); //关闭进度条
						if(result.opflag){
							lalert('handle','success',function(){
								pageForward('/pm/web/evaluationManage/evaluation_save.jsp');
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
			})
		
	}
}


/**
 * 保存
 */
function saveEntityDataFun(){
	var checkYearfalg =	 checkYearValue();

	if(!checkYearfalg){
		lalert('请选择年份！','error');
	}else{
		var jsonStr= "[";
		for (var i = 0; i < $("#t_r_content tr").length; i++) {
		
			 var year_date = $("#t_r_content tr:eq(" + i + ")").children().eq(3).text();
             jsonStr += "{\"year_date\":\"" + year_date + "\",";
             var date_annual = $("#t_r_content tr:eq(" + i + ")").children().eq(4).text();
             jsonStr += "\"date_annual\":\"" + date_annual + "\",";
             var user_num = $("#t_r_content tr:eq(" + i + ")").children().eq(1).text();
             jsonStr += "\"user_num\":\"" + user_num + "\",";
             var evaluation_one = $("#t_r_content tr:eq(" + i + ")").children().eq(10).text();
             jsonStr += "\"evaluation_one\":\"" + evaluation_one + "\",";
             var evaluation_one_num = $("#t_r_content tr:eq(" + i + ")").children().eq(12).find("input").val();
             jsonStr += "\"evaluation_one_num\":\"" + evaluation_one_num + "\",";
             var evaluation_one_remark = $("#t_r_content tr:eq(" + i + ")").children().eq(13).find("input").val();
             jsonStr += "\"evaluation_one_remark\":\"" + evaluation_one_remark + "\",";
             var evaluation_two = $("#t_r_content tr:eq(" + i + ")").children().eq(14).text();
             jsonStr += "\"evaluation_two\":\"" + evaluation_two + "\",";
             var evaluation_two_num = $("#t_r_content tr:eq(" + i + ")").children().eq(16).find("input").val();
             jsonStr += "\"evaluation_two_num\":\"" + evaluation_two_num + "\",";
             var evaluation_two_remark = $("#t_r_content tr:eq(" + i + ")").children().eq(17).find("input").val();
             jsonStr += "\"evaluation_two_remark\":\"" + evaluation_two_remark + "\"},";
		}
		 jsonStr = jsonStr.substring(0, jsonStr.length - 1) + "]";
		
		
		
		layer.load();//添加进度条
		$.ajax({
				url:getRequestUrl("/SvaluationManageController/saveTemporaryEntityData.json"),
				dataType:"json",
				data:{"evaluationManageInfo.json_info": jsonStr },
				success:function(result){
					
					if(result.opflag){
						lalert('handle','success',function(){
							pageForward('/pm/web/evaluationManage/evaluation_save.jsp');
						});
					}else{
						lalert(result.opmessage,'error');
					}
				},
				error:function(error){
					
					lalert('网络原因操作失败！','error');
				}
			});
		
	}
}
