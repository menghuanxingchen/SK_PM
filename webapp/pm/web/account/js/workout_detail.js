$(function(){
	menu('menu62');
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){

	id = getRequestParameterValue("id");
	
	var addHtml = $("#dataInfoFormTemp").html();
	$("#dataInfoForm").html(addHtml);
	
	var addHtml = $("#outputListTotalTemp").html();
	$("#outputList_Total").html(addHtml);
	
	var addHtml1 = $("#dataListFormTemp").html();
	$("#dataListForm").html(addHtml1);
	
	var addHtml2 = $("#dataListMachFormTemp").html();
	$("#dataListMachForm").html(addHtml2);
	
	var addHtml3 = $("#outputMachineTemp").html();
	$("#dataListMachForm_Total").html(addHtml3);//机械费总额
	
	var addHtml4 = $("#initMatTemplate").html();
	$("#MaterialCost").html(addHtml4);//材料费
	
	var allafter = 0; //人工费合计初始值
	$.ajax({
		url:getRequestUrl("/WorkoutInfoController/WorkOutInfoDetailEntity.json"),
		dataType:"json",
		data:{"workOutInfo.work_out_id":id}, 
		success:function(result){
			 
			$("#work_out_nm").val(result.workOutInfo.work_out_nm); 
			$("#dataInfoForm").html($("#dataInfoFormTemp").render(result.workOutInfo)); 
		 	$("#dataListForm").html($("#dataListFormTemp").render(result.workOutDetailInfo)); 
		 	$("#outputList_Total").html($("#outputListTotalTemp").render(result.workOutInfo)); 
		    $("#dataListMachForm").html($("#dataListMachFormTemp").render(result.workOutDetailMachInfo)); 
		    $("#dataListMachForm_Total").html($("#outputMachineTemp").render(result.workOutInfo));
		    $("#MaterialCost").html($("#initMatTemplate").render(result.repairMaterialCostInfo));
		    
		    //人工费合计计算
		    allafter = result.workOutInfo.human_cost*1 + result.workOutInfo.meal_cost*1 ;
		    
		    if(allafter == "" || allafter.toString() == "NaN"){
				allafter = 0;
			}
			$("#labourCostTotal").html(parseFloat(allafter).toFixed(2));
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
	bindevent();
}
/**
 * 绑定事件属性
 * */
function bindevent(){
	 
	//校验项目
	//bindValidateEvent("formId");
	/*
	$("#closeBtn").bind('click',function(){
		pageForward('/WorkoutInfoController/defaultJsp.do');
	});
	*/
    }
 
 
