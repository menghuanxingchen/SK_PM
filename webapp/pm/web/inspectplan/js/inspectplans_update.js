$(function(){
	initPage();
	
});

var plan_id="";
function initPage(){
	menu('menu24');	
	plan_id =getRequestParameterValue("plan_id");	
	getReadyForPage();	
}

function bindevent(){		
	//保存事件
	$("#saveBtn").bind('click',function(){
		saveFormData();
	});
	//pagePop 共通弹出layer方法	
	$("#machineAddBt").click(function(){
		pagePop("设备","machineform","/PM/pm/web/inspectplan/machine_list_pop.jsp",$("#ma_sp_id").val(),$("#ma_type_id").val());
	});
	$("#checkitemAddBt").click(function(){
		pagePop("检查项目","checkitemform","/PM/pm/web/inspectplan/checkitem_list_pop.jsp",$("#ch_sp_id").val(),$("#ch_type_id").val());
	});
	//返回
	$("#backBT").click(function(){
		pageForward("/pm/web/inspectplan/inspectplan_list.jsp");
	});
}
function getReadyForPage(){
	$.ajax({
		url:getRequestUrl("/InspectPlanController/queryUpdatePage.json"),
		dataType:"json",
		data:{"insPlanInfo.ins_plan_id":plan_id},
		success:function(data){
			returnbuildpage(data);
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}
var ma_count=0;//'设备'列表计数器
var ch_count=0;//'检查项'列表计数器
var timestr="";
function returnbuildpage(data){
	$("#mainid").html($("#mainTemplate").render(data));
	// 形成下来
	$("#cycleid").html($("#cycleTemplate").render(data));
	$("#planCycle").val(data.insPlanInfo.ins_plan_cycle);
	$("#updateflag").html($("#updateflagTemplate").render(data));//修改区分	
	//日期半天设置插件格式
	var dateformat="YYYY-MM-DD";
	if(data.insPlanInfo.ins_plan_cycle==Class.getConstant('PLAN_CYCLE_HALFDAY')){
		dateformat="YYYY-MM-DD hh:mm:ss";
	}
	var date = {
			  elem: '#date',
			  istoday: true,
			  format: dateformat,
			  min: laydate.now(), //最大日期
			  choose: function(datas){
				  }
			};
	laydate(date);
	
	var enddate = {
			  elem: '#enddate',
			  istoday: true,
			  format: dateformat,
			  min: laydate.now(), //最大日期
			  choose: function(datas){
				  }
			};
	laydate(enddate);
	//形成 设备list	
	var str='';
	for(var i=0;i<data.MachineInfoList.length;i++){
		ma_count=ma_count+1;
		str=str+'<li class="li_on1" id="insMachineIdList'+i+'" onclick="removeMachineIdList('+i+');"><input type="hidden" name="insMachineIdList['+i+']" value="'+data.MachineInfoList[i].ins_machine_id+'" idattr="" dc="true"/>'+data.MachineInfoList[i].machine_nm+'</li>';
	}	
	$("#machineform").append(str);
	//添加删除方法
	/*$(".li_on1").bind('click',function(){
		this.remove();
	});*/
	$("#ma_sp_id").val(data.MachineInfoList[0].machine_species_id);
	$("#ma_type_id").val(data.MachineInfoList[0].machine_type_id);
	
	//形成 项目list
	var str2='';
	for(var i=0;i<data.checkitemInfoList.length;i++){
		ch_count=ch_count+1;
		str2=str2+'<li class="li_on2" id="insCheckIdList'+i+'" onclick="removeCheckIdList('+i+');"><input type="hidden" name="insCheckIdList['+i+']" value="'+data.checkitemInfoList[i].check_project_id+'" idattr="" dc="true"/>'+data.checkitemInfoList[i].check_project_nm+'</li>';
	}	
	$("#checkitemform").append(str2);
	//添加删除方法
	/*$(".li_on2").bind('click',function(){
		this.remove();
	});*/
	$("#ch_sp_id").val(data.checkitemInfoList[0].machine_species_id);
	$("#ch_type_id").val(data.checkitemInfoList[0].machine_type_id);
	bindevent();
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
function saveFormData(){	
	var checkflag =	checkFormValue('addform')&&checkListValue();
	if(checkflag){
		layer.confirm("是否提交", {
		    btn: ['确定','取消'] //按钮
		}, function(){
			$("#saveBtn").unbind('click');
			var postData = collectData("addform");
			postData=$.extend(postData,{"insPlanInfo.ins_plan_id":plan_id});
			layer.load();//添加进度条
			$.ajax({
				url:getRequestUrl("/InspectPlanController/updateInsMstThreeTable.json"),
				dataType:"json",
				data:postData,
				success:function(result){
					layer.closeAll('loading'); //关闭进度条
					if(result.opflag){
						if(result.opmessage=="done"){
							alert("有已经操作过的计划不能批量修改或删除！");
							//保存事件
							$("#saveBtn").bind('click',function(){
								saveFormData();
							});
						}else{
							lalert("save","success",function(){
								pageForward('/pm/web/inspectplan/inspectplan_list.jsp');
							});					
						}
					}else{				
						lalert("save","error");
					}
					
				},
				error:function(error){
					alert("error");
				}
			});
		})
	}
}

//回调 machine pop页面 close 向父页面传值
function importMainfo(idsStr,namesStr,species_id,type_id,flag){
	if(flag=="Y"){
		$("#machineform li").remove();
	}
	$("#ma_sp_id").val(species_id);
	$("#ma_type_id").val(type_id);	

	var idsArray=idsStr.split(",");
	var namesArray=namesStr.split(",");
	var str='';
	for(var i=0;i<idsArray.length;i++){		
		str=str+'<li class="li_on1" id="insMachineIdList'+ma_count+'" onclick="removeMachineIdList('+ma_count+');"><input type="hidden" name="insMachineIdList['+ma_count+']" value="'+idsArray[i]+'" idattr="" dc="true"/>'+namesArray[i]+'</li>';
		ma_count=ma_count+1;
	}	
	$("#machineform").append(str);
	//添加删除方法
	/*$(".li_on1").bind('click',function(){
		this.remove();
	});*/
}
//添加删除方法
function removeMachineIdList(id){
	$("#insMachineIdList"+id+"").remove();
}
//回调 checkitem pop页面 close 向父页面传值
function importCheckitem(idsStr,namesStr,species_id,type_id,flag){
	if(flag=="Y"){
		$("#checkitemform li").remove();
	}
	$("#ch_sp_id").val(species_id);
	$("#ch_type_id").val(type_id);

	var idsArray=idsStr.split(",");
	var namesArray=namesStr.split(",");
	var str='';
	for(var i=0;i<idsArray.length;i++){		
		str=str+'<li class="li_on2" id="insCheckIdList'+ch_count+'" onclick="removeCheckIdList('+ch_count+');"><input type="hidden" name="insCheckIdList['+ch_count+']" value="'+idsArray[i]+'" idattr="" dc="true"/>'+namesArray[i]+'</li>';
		ch_count=ch_count+1;
	}	
	$("#checkitemform").append(str);
	//添加删除方法
	/*$(".li_on2").bind('click',function(){
		this.remove();
	});*/
}
//添加删除方法
function removeCheckIdList(id){
	$("#insCheckIdList"+id+"").remove();
}