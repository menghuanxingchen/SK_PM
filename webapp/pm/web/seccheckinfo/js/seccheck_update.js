$(function(){
	menu('menu26');
	initPage();
});

/**
 * 初始化页面
 * */
var sec_check_id = getRequestParameterValue("sec_check_id");
function initPage(){
	$.ajax({
		url:getRequestUrl("/SecCheckInfoController/queryDownList.json"),
		dataType:"json",
		data:"",
		success:function(result){
			$("#repairPlace").html($("#repairPlacedownListTemplate").render(result));
			querySecCheckBeforeUpdate();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * 绑定事件属性
 * */
function bindevent(){
	bindValidateEvent('aaa');
	$("#submitBtn").bind('click',function(){
		updateSecCheckInfoData();
	});
	laydate({
		  elem: '#repair_add_table .laydate-icon', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		  event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	});
}

/**
 * 详情查询
 * */
function querySecCheckBeforeUpdate(){
	$.ajax({
		url:getRequestUrl("/SecCheckInfoController/querySecCheckBeforeUpdate.json"),
		dataType:"json",
		data:{"sec_check_id":sec_check_id},
		success:function(result){
			if(result.opflag){
				$("#repair_place  option[value='" + result.secCheckInfo.work_type + "']").attr("selected",true);
				$("#sec_detail").val(result.secCheckInfo.sec_detail);
				$("#sec_num").val(result.secCheckInfo.sec_num);
				$("#up_check_date").val(result.secCheckInfo.up_check_date);				
				$("#frequency").val(result.secCheckInfo.frequency);
				$("#failure_date").val(result.secCheckInfo.failure_date);
				
				$("#cur_state").val(result.secCheckInfo.cur_state);
				$("#sec_plan_date").val(result.secCheckInfo.sec_plan_date);
				$("#sec_check_date").val(result.secCheckInfo.sec_check_date);
				$("#sec_result").val(result.secCheckInfo.sec_result);
				$("#check_dept").val(result.secCheckInfo.check_dept);
				$("#remark").val(result.secCheckInfo.remark);
				
				$("#sec_check_id").val(result.secCheckInfo.sec_check_id);
				$("#create_id").val(result.secCheckInfo.create_id);
				$("#create_time").val(result.secCheckInfo.create_time);
				bindevent();
			}
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * 修改
 * */
function updateSecCheckInfoData(){
	var postData = collectData("aaa");
	var checkfalg =	checkFormValue('aaa');
	if(checkfalg){
	var message = "确认修改？";
	layer.confirm(
			message,
			function(index){
					$.ajax({
						url:getRequestUrl("/SecCheckInfoController/updateSecCheckInfoData.json"),
						dataType:"json",
						data:postData,
						success:function(result){
							layer.close(index);
							if(result.opflag){
								layer.alert('修改成功',{
									  icon: 1
								}, function(){
									  var page = "/pm/web/seccheckinfo/seccheck_list.jsp";
										pageForward(page);
								  });
							}else{
								lalert('update','error');
							}
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
	      )
			}else{
				layer.closeAll();
				$("#submitBtn").bind('click',function(){
					updateSecCheckInfoData();
				});
			}
}