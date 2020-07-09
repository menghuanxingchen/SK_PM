$(function(){
	menu('menu62');
	initPage();
});


/**
 * 初始化页面
 * */
function initPage(){

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
		url:getRequestUrl("/WorkoutInfoController/WorkOutInfoDetailNewEntity.json"),
		dataType:"json",
		data:[],
		success:function(result){

			$("#dataListForm").html($("#dataListFormTemp").render(result.workOutDetailInfo)); 
		    $("#dataListMachForm").html($("#dataListMachFormTemp").render(result.workOutDetailMachInfo)); 
			//校验项目
			bindValidateEvent("dataListForm");
			bindValidateEvent("dataInfoForm");
			bindValidateEvent("outputList_Total");
			bindValidateEvent("dataListMachForm");
			
		    for(var i = 0; i<result.workOutDetailMachInfo.length;i++)
			{ 
		    	if(result.workOutDetailMachInfo[i].unit2.length == 0){
		    	var hideName ="input[name='workOutDetailMachInfo["+i+"].work_hour2']" ;
		    	 $(hideName).hide();
		    	}
			}
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
	bindevent();
	addCarTempRows();
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
	var start = {
			  elem: '#work_out_ym',
			  istoday: true,
			  choose: function(datas){
			     end.min = datas; //开始日选好后，重置结束日的最小日期
			     end.start = datas //将结束日的初始值设定为开始日
			  }
			};
 
	laydate(start);	
	//$("#work_out_ym").val(laydate.now()); 
	//$("#work_out_nm").val($("#work_out_ym").val().substr(0,4)+"年"+$("#work_out_ym").val().substr(5,2)+"月"+"出勤统计表"); 
	$("#work_out_ym").val(getPreMonth(laydate.now())); 
	$("#work_out_nm").val(getPreMonth2(laydate.now())); 
	//绑定添加行数按钮-材料费
	$("#addCarTempRows").bind('click',addCarTempRows);
	
	//删除行数-材料费
	$("#delCarTempRows").bind('click',function(){
		deleteTableRows("checkboxMaterialCostId");
	});
	
	checkedAll("checkboxMaterialCostId");
}
/**
 * 获取上一个月
 *
 * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
 */
function getPreMonth(date) {
    var arr = date.split('-');
    var year = arr[0]; //获取当前日期的年份
    var month = arr[1]; //获取当前日期的月份
    var day = arr[2]; //获取当前日期的日
    var days = new Date(year, month, 0);
    days = days.getDate(); //获取当前日期中月的天数
    var year2 = year;
    var month2 = parseInt(month) - 1;
    if (month2 == 0) {//如果是1月份，则取上一年的12月份
        year2 = parseInt(year2) - 1;
        month2 = 12;
    }
    var day2 = day;
    var days2 = new Date(year2, month2, 0);
    days2 = days2.getDate();
    if (day2 > days2) {//如果原来日期大于上一月的日期，则取当月的最大日期。比如3月的30日，在2月中没有30
        day2 = days2;
    }
    if (month2 < 10) {
        month2 = '0' + month2;//月份填补成2位。
    }
    var t2 = year2 + '-' + month2 + '-' + day2;
    return t2;
}
/**
 * 获取上一个月拼文字
 *
 * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
 */
function getPreMonth2(date) {
    var arr = date.split('-');
    var year = arr[0]; //获取当前日期的年份
    var month = arr[1]; //获取当前日期的月份
    var day = arr[2]; //获取当前日期的日
    var days = new Date(year, month, 0);
    days = days.getDate(); //获取当前日期中月的天数
    var year2 = year;
    var month2 = parseInt(month) - 1;
    if (month2 == 0) {//如果是1月份，则取上一年的12月份
        year2 = parseInt(year2) - 1;
        month2 = 12;
    }
    var day2 = day;
    var days2 = new Date(year2, month2, 0);
    days2 = days2.getDate();
    if (day2 > days2) {//如果原来日期大于上一月的日期，则取当月的最大日期。比如3月的30日，在2月中没有30
        day2 = days2;
    }
    if (month2 < 10) {
        month2 = '0' + month2;//月份填补成2位。
    }
    var t2 = year2 + '年' + month2 + '月出勤表';
    return t2;
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
		if(workhour2==""){
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
 
	if(!checkFormValue("MaterialCost")){
		return;
	}
	if($("input#work_out_nm").val() ==""){
		$("input#work_out_nm").focus();
		return;
	}
	if($("input#work_out_ym").val() ==""){
		alert("1");
		$("input#work_out_ym").focus();
		return;
	}
	
	layer.confirm("是否保存", {
	    btn: ['确定','取消'] //按钮
	}, function(){
			var postData = collectData("aaa");
		 
			layer.load();//添加进度条
			$.ajax({
					url:getRequestUrl("/WorkoutInfoController/addEntityData.json"),
					dataType:"json",
					data:postData,
					success:function(result){
						layer.closeAll('loading'); //关闭进度条
						if(result.opflag){
							lalert('save','success',function(){
								//$("#closeBtn").trigger("onclick");
								//pageForward('/WorkoutInfoController/defaultJsp.do');\
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
