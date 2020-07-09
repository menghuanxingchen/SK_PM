$(function(){
	menu('menu91');
	initPage();
});
var id = JSON.parse(obj)[0].id;
var count=0;
/**
 * 初始化页面
 * */
function initPage(){
	$.ajax({
		url:getRequestUrl("/ScrapController/beforeUpdateEntity.json"),
		dataType:"json",
		data:{"scrapInfo.scrap_id":id},
		async:false,
		success:function(data){
			$("#formId").html($("#formTemplate").render(data.scrapInfo)); //计划实体-计划名
			
			bindevent();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});

}
/**
 * 绑定事件
 */
function bindevent(){
	$("#machineAddBt").click(function(){
		pagePop("设备","machineform","/PM/pm/web/scrapinfo/p_machine_list.jsp",$("#ma_sp_id").val(),$("#ma_type_id").val());
	});
	var planStartDate = {
			  elem: '#scrapDate',
			  istoday: true,
			 // min: laydate.now(+1), //最大日期
			  isclear: false, //是否显示清空
			  istoday: true //是否显示今天
			};
	laydate(planStartDate);
	//$("#scrapDate").val(laydate.now());
	
	$("#closeBtn").bind('click',function(){
		pageForward('/ScrapController/defaultJsp.do');
	});
	
	$("#submitBtn").bind('click',function(){
		saveForm();
	});
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
						url:getRequestUrl("/ScrapController/updateEntityData.json"),
						dataType:"json",
						data:postData,
						success:function(result){
							layer.closeAll('loading'); //关闭进度条
							if(result.opflag){
								lalert('save','success',function(){
									pageForward('/ScrapController/defaultJsp.do');
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
}
//回调 machine pop页面 close 向父页面传值
function importMainfo(idsStr,namesStr,species_id,type_id,flag){
	$("#machineSpecies").val(species_id);
	$("#machineType").val(type_id);
	var idsArray=idsStr.split(",");
	$("#machineId").val(idsArray[0]);
	var namesArray=namesStr.split(",");
	$("#machineAddBt").val(namesArray[0]);
}
