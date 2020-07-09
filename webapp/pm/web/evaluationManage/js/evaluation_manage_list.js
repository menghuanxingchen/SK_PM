$(function(){
	menu('menuC14');
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

	if(currentUserid != "lg00258" && currentUserid != "admin"){
		$("#evaluationLink").hide()
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
			url:getRequestUrl("/SvaluationManageController/queryDataList.json"),
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
}

//提交前check
function checkListValue(){
	var EvaluationOne = $("#EvaluationOne").val();

	
	var EvaluationTwo = $("#EvaluationTwo").val();

	if(EvaluationOne!="" && EvaluationOne != null  && EvaluationTwo!="" && EvaluationTwo != null){
		return true;
	}else{
		return false;
	}
}

function checkHavaValue(){
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
 * 新增
 */
function addEntityDataFun(){
	var checkfalg =	 checkListValue();
	var checkHava = checkHavaValue();
	
	if(!checkfalg){
		lalert('请选择评价人！','error');
	
	}else if(checkHava == "have"){
		lalert('评价已经进行中，不能再次生成！','error');
	}
	else if(checkHava == "end"){
		lalert('评价已经结束！','error');
	}
	else{
		layer.confirm("是否生成", {
		    btn: ['确定','取消'] //按钮
			}, function(){
			$("#submitBtn").unbind('click');
			var postData = collectData("search_area");
			layer.load();//添加进度条
			$.ajax({
					url:getRequestUrl("/SvaluationManageController/updateEntityData.json"),
					dataType:"json",
					data:postData,
					success:function(result){
						layer.closeAll('loading'); //关闭进度条
						if(result.opflag){
							lalert('handle','success',function(){
								pageForward('/SvaluationManageController/defaultJsp.do');
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

