$(function(){
	initPage();
	
});

function initPage(){
	laydate({
		  elem: '#date' //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		});
	laydate({
		  elem: '#enddate' //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		});
	menu('menu24');
	getCodeList();
	bindevent();
}

function bindevent(){
	
	//日期插件
	$('#date').val(laydate.now(1));
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
function getCodeList(){
	$.ajax({
		url:getRequestUrl("/InspectPlanController/queryPageList.json"),
		dataType:"json",
		data:[],
		success:function(data){
			$("#cycleid").html($("#cycleTemplate").render(data));
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
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
		var postData = collectData("addform");
		layer.confirm("是否提交", {
		    btn: ['确定','取消'] //按钮
		}, function(){
			$("#saveBtn").unbind('click');			
			layer.load();//添加进度条
			$.ajax({
				url:getRequestUrl("/InspectPlanController/saveInsMstThreeTable.json"),
				dataType:"json",
				data:postData,
				success:function(result){
					layer.closeAll('loading'); //关闭进度条
					if(result.opflag){
						lalert("save","success",function(){
							pageForward('/pm/web/inspectplan/inspectplan_list.jsp');
						});						
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
var macount=0;
//回调 machine pop页面 close 向父页面传值
function importMainfo(idsStr,namesStr,species_id,type_id,flag){
	if(flag=="Y"){
		$("#machineform li").remove();
		var mabuild='<li class="h_bg">设备信息'		
    	+'<a class="fb f20 fr" id="machineAddBt" href="#" title="新增">+</a></li>';
		$("#machineform").html(mabuild);
		$("#machineAddBt").click(function(){
			pagePop("设备","machineform","/PM/pm/web/inspectplan/machine_list_pop.jsp",$("#ma_sp_id").val(),$("#ma_type_id").val());
		});
	}
	$("#ma_sp_id").val(species_id);
	$("#ma_type_id").val(type_id);
	
	var idsArray=idsStr.split(",");
	var namesArray=namesStr.split(",");
	var str='';			
	for(var i=0;i<idsArray.length;i++){			
		str=str+'<li class="li_on1" id="insMachineIdList'+macount+'" onclick="removeMachineId('+macount+');"><input type="hidden" name="insMachineIdList['+macount+']" value="'+idsArray[i]+'" idattr="" dc="true"/>'+namesArray[i]+'</li>';
		macount=macount+1;
	}	
	$("#machineform").append(str);
	//添加删除方法
	/*$(".li_on1").bind('click',function(){
		this.remove();
	});*/
}
//添加删除方法
function removeMachineId(id){
	$("#insMachineIdList"+id+"").remove();
}
var chcount=0;
//回调 checkitem pop页面 close 向父页面传值
function importCheckitem(idsStr,namesStr,species_id,type_id,flag){	
	if(flag=="Y"){
		$("#checkitemform li").remove();
		var chbuild='<li class="h_bg">检查项目信息 '                  	
        	+'<a class="fb f20 fr" id="checkitemAddBt" href="#" title="新增">+</a></li>';
		$("#checkitemform").html(chbuild);	
		$("#checkitemAddBt").click(function(){
			pagePop("检查项目","checkitemform","/PM/pm/web/inspectplan/checkitem_list_pop.jsp",$("#ch_sp_id").val(),$("#ch_type_id").val());
		});
	}
	$("#ch_sp_id").val(species_id);
	$("#ch_type_id").val(type_id);
	
	var idsArray=idsStr.split(",");
	var namesArray=namesStr.split(",");
	var str='';	
	for(var i=0;i<idsArray.length;i++){
		str=str+'<li class="li_on2" id="insCheckIdList'+chcount+'" onclick="removeCheckIdList('+chcount+')"><input type="hidden" name="insCheckIdList['+chcount+']" value="'+idsArray[i]+'" idattr="" dc="true"/>'+namesArray[i]+'</li>';
		chcount=chcount+1;
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

