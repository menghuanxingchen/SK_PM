$(function(){
	menu('menu21');
	initPage();
});
var id="";
/**
 * 初始化页面
 * */
var macount=0;
var chcount=0;
function initPage(){
	id = getRequestParameterValue("id");

	$.ajax({
		url:getRequestUrl("/PrePlanInfoController/beforeUpdateEntity.json"),
		dataType:"json",
		data:{"prePlanInfo.pre_plan_id":id},
		success:function(data){
			$("#dataform").html($("#formTemplate").render(data)); //计划实体-计划名
			$("#machineform").append($("#machineTemplate").render(data.machList)); //设备列表
			$("#checkitemform").append($("#checkitemTemplate").render(data.checkList)); //检查项目列表
			$("#planForm").html($("#cycleTemplate").render(data)); //周期
			$("#updateflag").html($("#updateflagTemplate").render(data));//修改区分	
			macount = data.machList.length;
			chcount = data.checkList.length;
			$("#ma_sp_id").val(data.machList[0].machine_species_id);
			$("#ma_type_id").val(data.machList[0].machine_type_id);
			$("#ch_sp_id").val(data.checkList[0].machine_species_id);
			$("#ch_type_id").val(data.checkList[0].machine_type_id);
			bindevent();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
	//pagePop 共通弹出layer方法
	$("#machineAddBt").click(function(){
		pagePop("设备","machineform","/PM/pm/web/inspectplan/machine_list_pop.jsp",$("#ma_sp_id").val(),$("#ma_type_id").val());
	});
	$("#checkitemAddBt").click(function(){
		pagePop("检查项目","checkitemform","/PM/pm/web/inspectplan/checkitem_list_pop.jsp",$("#ch_sp_id").val(),$("#ch_type_id").val());
	});
}
/**
 * 绑定事件属性
 * */
function bindevent(){
	var str = $("#date").val().substring(10,19);
	var formatDate = "";
	if($("#date").val().length==10){
		formatDate = 'YYYY-MM-DD';
	}else{
		formatDate = 'YYYY-MM-DD hh:mm:ss';
	}
	//只能选择今天以后的日期修改
	var date = {
			  elem: '#date',
			  istoday: true,
			  format: formatDate,
			  min: laydate.now(), //最大日期
			  choose: function(datas){
				  }
			};
	laydate(date);
	
	
	var endstr = $("#enddate").val().substring(10,19);
	var endformatDate = "";
	if($("#enddate").val().length==10){
		endformatDate = 'YYYY-MM-DD';
	}else{
		endformatDate = 'YYYY-MM-DD hh:mm:ss';
	}
	//只能选择今天以后的日期修改
	var enddate = {
			  elem: '#enddate',
			  istoday: true,
			  format: endformatDate,
			  min: laydate.now(), //最大日期
			  choose: function(datas){
				  }
			};
	laydate(enddate);
	
	$("#closeBtn").bind('click',function(){
		pageForward('/PrePlanInfoController/defaultJsp.do');
	});
	
	$("#submitBtn").bind('click',function(){
		saveForm();
	});
	
	//表格点击删除行
	/*$(".li_on1").bind('click',function(){
		this.remove();
	});
	
	$(".li_on2").bind('click',function(){
		this.remove();
	});*/
	
}
function checkListValue(){
	var flag=true;
	var machinecount= $("#machineform li[class='li_on1']").length;
	var checkcount= $("#checkitemform li[class='li_on2']").length;
	if(machinecount==0){
		lalert('请选择设备','warn');
		flag=false;
		return flag;
	}else if(checkcount==0){
		lalert('请选择检查项目','warn');
		flag=false;
		return flag;
	}
	return flag;
}
function saveForm(){
	var checkfalg =	checkFormValue('formId')&&checkListValue();
	if(checkfalg){
		layer.confirm("是否提交", {
		    btn: ['确定','取消'] //按钮
		}, function(){
		$("#submitBtn").unbind('click');
			var postData = collectData("formId");
			postData=$.extend(postData,{"prePlanInfo.pre_plan_id":id});
			layer.load();//添加进度条
				$.ajax({
						url:getRequestUrl("/PrePlanInfoController/updateEntityData.json"),
						dataType:"json",
						data:postData,
						success:function(result){
							layer.closeAll('loading'); //关闭进度条
							if(result.opflag){
								if(result.opmessage=="done"){
									alert("有已经操作过的计划不能批量修改！");
									//保存事件
									$("#submitBtn").bind('click',function(){
										saveForm();
									});
								}else{
									lalert('save','success',function(){
										pageForward('/PrePlanInfoController/defaultJsp.do');
									});
								}
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

//回调 machine pop页面 close 向父页面传值
function importMainfo(idsStr,namesStr,species_id,type_id,flag){
	if(flag=="Y"){
		$("#machineform li").remove();
		/*var mabuild='<li class="h_bg">设备信息'		
    	+'<a class="fb f20 fr" id="machineAddBt" href="#" title="新增">+</a></li>';
		$("#machineform").html(mabuild);	*/	
	}
	$("#ma_sp_id").val(species_id);
	$("#ma_type_id").val(type_id);	
	var idsArray=idsStr.split(",");
	var namesArray=namesStr.split(",");
	var str='';
	for(var i=0;i<idsArray.length;i++){		
		str=str+'<li class="li_on1" id="perMachineIdList'+macount+'" onclick="removeMachineId('+macount+');"><input type="hidden" name="perMachineIdList['+macount+']" value="'+idsArray[i]+'" idattr="" dc="true"/>'+namesArray[i]+'</li>';
		macount=macount+1;
	}	
	$("#machineform").append(str);
	//添加删除方法
	/*$(".li_on1").bind('click',function(){
		this.remove();
	});*/
}
function removeMachineId(id){
	$("#perMachineIdList"+id+"").remove();
}
//回调 checkitem pop页面 close 向父页面传值
function importCheckitem(idsStr,namesStr,species_id,type_id,flag){
	if(flag=="Y"){
		$("#checkitemform li").remove();
		/*var chbuild='<li class="h_bg">检查项目信息 '                  	
        	+'<a class="fb f20 fr" id="checkitemAddBt" href="#" title="新增">+</a></li>';
		$("#checkitemform").html(chbuild);*/	
	}
	$("#ch_sp_id").val(species_id);
	$("#ch_type_id").val(type_id);
	var idsArray=idsStr.split(",");
	var namesArray=namesStr.split(",");
	var str='';
	for(var i=0;i<idsArray.length;i++){		
		str=str+'<li class="li_on2" id="perCheckIdList'+chcount+'" onclick="removeCheckIdList('+chcount+');"><input type="hidden" name="perCheckIdList['+chcount+']" value="'+idsArray[i]+'" idattr="" dc="true"/>'+namesArray[i]+'</li>';
		chcount=chcount+1;
	}	
	$("#checkitemform").append(str);
	//添加删除方法
	/*$(".li_on2").bind('click',function(){
		this.remove();
	});*/
}
function removeCheckIdList(id){
	$("#perCheckIdList"+id+"").remove();
}