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
	
	var addHtml1 = $("#outputListTotalTemp").html();
	$("#outputList_Total").html(addHtml1);
	
	var addHtml2 = $("#dataListFormTemp").html();
	$("#dataListForm").html(addHtml2);
	
	var addHtml3 = $("#dataListMachFormTemp").html();
	$("#dataListMachForm").html(addHtml3);
	
	$("#dataListMachForm_Total").html($("#dataListMachTotalTemp").html());

	$.ajax({
		url:getRequestUrl("/WorkoutInfoController/WorkOutInfoDetailEntity.json"),
		dataType:"json",
		data:{"workOutInfo.work_out_id":id}, 
		success:function(result){
		 
			$("#work_out_nm").val(result.workOutInfo.work_out_nm); 
			$("#work_out_ym").val(result.workOutInfo.work_out_ym); 
			
			$("#dataInfoForm").html($("#dataInfoFormTemp").render(result.workOutInfo)); 
		 	$("#dataListForm").html($("#dataListFormTemp").render(result.workOutDetailInfo)); 
		 	$("#outputList_Total").html($("#outputListTotalTemp").render(result.workOutInfo)); 
		 	$("#dataListMachForm_Total").html($("#dataListMachTotalTemp").render(result.workOutInfo)); 
		    $("#dataListMachForm").html($("#dataListMachFormTemp").render(result.workOutDetailMachInfo));
		    
		    if(result.repairMaterialCostInfo.length > 0){
		    	$("#MaterialCost").html($("#initMatTemplate").render(result.repairMaterialCostInfo));
		    }else {
		    	addCarTempRows();
		    }

		    bindValidateEvent("dataListForm");
			bindValidateEvent("dataInfoForm");
			bindValidateEvent("outputList_Total");
			bindValidateEvent("dataListMachForm");
			bindValidateEvent("MaterialCost");
 
			
		    
		    for(var i = 0; i<result.workOutDetailMachInfo.length;i++)
			{ 
		    	if(result.workOutDetailMachInfo[i].unit2.length == 0){
		    	var hideName ="input[name='workOutDetailMachInfo["+i+"].work_hour2']";
		    	 $(hideName).hide();
		    	}
			}
			var count = $(".materialCostTr").length;
			AddRowsObject.rowsCount = count;
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
	
	$("#closeBtn").bind('click',function(){
		var page = "/pm/web/account/repair_order_unpayed.jsp";
	    pageForward(page);
	});

	//保存按钮绑定
	$("#submitBtn").bind('click',function(){
		saveForm();
	});

	//绑定添加行数按钮-材料费
	$("#addCarTempRows").bind('click',addCarTempRows);
	
	//删除行数-材料费
	$("#delCarTempRows").bind('click',function(){
		deleteTableRows("checkboxMaterialCostId");
	});
	checkedAll("checkboxMaterialCostId");
}


/**
 * 计算固定人力费
 */
function addAll(){
	 
	var totalval = parseFloat(0);
	$(".toadd").each(function(index,element){
		var tempvalu= $(this).val();
		if(tempvalu==""){
			tempvalu=0;
			$(this).val("0");
		}
		totalval += parseFloat(tempvalu);
	});
	if(totalval.toString() == "NaN"){
		totalval = 0;
	}
	var overHour =$("#overtime_hour").val();
	if(overHour==""){
		$("#overtime_hour").val("0");
	}
	
	var dayPrice =Class.getConstant('USER_PRICE_WITH_TAX') * 8;
	var hourPrice =Class.getConstant('USER_PRICE_WITH_TAX');
	var totalPrice = overHour * hourPrice  + totalval * dayPrice ;
	//alert("overHour:"+overHour+" userPrice:"+userPrice+" totalval"+totalval);
	//USER_PRICE_WITH_TAX: "31.8",//人工费含税31.8元/小时
    //MEAL_PRICE_WITH_TAX: "12.72"//餐费餐补 含税     12.72元/次

	$("#human_cost").val(totalPrice.toFixed(2));
	$("#work_hour_total").val(totalval);
}

/**
 * 计算餐费合计
 */
function addMeal(){
	
	var overHour =$("#meal_hour").val();//餐饮时间 
	var mealPrice =Class.getConstant('MEAL_PRICE_WITH_TAX');//餐饮单价
	var totalPrice =overHour * mealPrice ;
 
	if(overHour==""){
		$("#meal_hour").val("0");
	}
    //MEAL_PRICE_WITH_TAX: "12.72"//餐费餐补 含税     12.72元/次
	$("#meal_cost").val(totalPrice.toFixed(2));
}

/**
 * 计算机械费合计1
 */
function addMach(){

	var totalval = parseFloat(0);
	$(".toaddMach").each(function(index,element){
		
		var tempvalu= $(this).val();
		//alert("index"+index);
		var hourvalu= $("input[name='workOutDetailMachInfo["+index+"].use_hour_cost']").val() ; 
		var dayvalu=$("input[name='workOutDetailMachInfo["+index+"].use_day_cost']").val() ; 
		var workhour2= $("input[name='workOutDetailMachInfo["+index+"].work_hour2']").val() ; 
		var unit2 =$("input[name='workOutDetailMachInfo["+index+"].unit2']").val(); 
		
		if(dayvalu==""){
			dayvalu=0;
		}
		if(hourvalu==""){
			hourvalu=0;
		}
		if(workhour2=="" && unit2 != ""){
			workhour2=0;
		}
		if(tempvalu==""){
			tempvalu=0;
			$(this).val("0");
		}
 
		totalval += parseFloat(tempvalu * hourvalu + dayvalu * workhour2 );
	 
	});
	if(totalval.toString() == "NaN"){
		totalval = 0;
	}
	  $("#mach_cost").val(totalval.toFixed(2));
}

/**
 * 计算机械费合计2
 */
function addMach2(){

	var totalval = parseFloat(0);
	$(".toaddMach2").each(function(index,element){
				
		var tempvalu= $(this).val();
		//alert("index"+index);
		var hourvalu= $("input[name='workOutDetailMachInfo["+index+"].use_hour_cost']").val() ; 
		var dayvalu=$("input[name='workOutDetailMachInfo["+index+"].use_day_cost']").val() ; 
		var workhour= $("input[name='workOutDetailMachInfo["+index+"].work_hour']").val() ; 
		
		if(dayvalu==""){
			dayvalu=0;
		}
		if(hourvalu==""){
			hourvalu=0;
		}
		if(workhour==""){
			workhour=0;
		}
		if(tempvalu==""){
			tempvalu=0;
			$(this).val("0");
		}
	 
		totalval += parseFloat(workhour * hourvalu + dayvalu * tempvalu );
	 
	});
	if(totalval.toString() == "NaN"){
		totalval = 0;
	}
	  $("#mach_cost").val(totalval.toFixed(2));
}

/**
 * 绑定保存事件属性
 * */
function saveForm(){
	
	if(!checkFormValue('MaterialCost')){
		return;
	}
	
	if($("input#work_out_nm").val() ==""){
		$("input#work_out_nm").focus();
		return;
	}
	addAll();
	layer.confirm("是否保存", {
	    btn: ['确定','取消'] //按钮
	}, function(){
			var postData = collectData("aaa");
		 
			layer.load();//添加进度条
			$.ajax({
					url:getRequestUrl("/WorkoutInfoController/updateEntityData.json"),
					dataType:"json",
					data:postData,
					success:function(result){
						layer.closeAll('loading'); //关闭进度条
						if(result.opflag){
							lalert('save','success',function(){
								//pageForward('/WorkoutInfoController/defaultJsp.do');
								//$("#closeBtn").bind("click",function(){ window.history.back();}); 
								var page = "/pm/web/account/repair_order_unpayed.jsp";
								pageForward(page);
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

/**
 * 添加行数-材料费信息补充
 */ 
function addCarTempRows(){
	var str = $("#addMatTemplate").render(AddRowsObject);
	
	$("#MaterialCost").append(str);
	
	var strConfirmDate = $("input#work_out_ym").val().substring(5,7) +"月份";
	$("input[name='rapairMaterialCostDetail["+AddRowsObject.rowsCount+"].confirm_date']").val(strConfirmDate);
	            
  	AddRowsObject.rowsCount++;
	bindValidateEvent("MaterialCost");
}
