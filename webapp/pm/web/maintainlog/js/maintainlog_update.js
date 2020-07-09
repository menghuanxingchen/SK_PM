$(function(){
	menu('menu32');
	initPage();
});
var malog_id="";
function initPage(){	
	malog_id =getRequestParameterValue("malog_id");	
	getCheckitemInfo();	
}

function bindevent(){	
	//日期插件
	laydate({
		  elem: '#datef',
		  istime:true,
		  format: 'YYYY-MM-DD'
		});
	//保存事件
	$("#saveBtn").bind('click',function(){
		updateFormData();
	});
	//返回
	$("#gobackBt").bind('click',function(){
		pageForward('/pm/web/maintainlog/maintainlog_list.jsp');
	});
	
	bindValidateEvent('updateform');
	
}


function updateFormData(){
	var checkflag =	checkFormValue('checkupdateform');
	if(checkflag){
		layer.confirm("是否提交", {
		    btn: ['确定','取消'] //按钮
		}, function(){
			var postData = collectData("updateform");
			postData=$.extend(postData,{"maintainlogInfo.maintainlog_id":malog_id});
			$.ajax({
				url:getRequestUrl("/MaintainLogController/updateMaintainlogInfo.json"),
				dataType:"json",
				data:postData,
				success:function(result){
					if(result.opflag){
						lalert("update","success",function(){
							pageForward('/pm/web/maintainlog/maintainlog_list.jsp');
						});
					}else{				
						lalert("update","error");
					}
					
				},
				error:function(error){
					alert("error");
				}
			});
		})
	}
}
//得到页面初始值
function getCheckitemInfo(){
	$.ajax({
		url : getRequestUrl("/MaintainLogController/queryMaintainLogInfo.json"),
		dataType:"json",
		data:{"maintainlogInfo.maintainlog_id":malog_id},
		success:function(result){
			$("#updateform").html($("#formTemplate").render(result));
			$("#mtype").val(result.maintainlogInfo.maintain_type);
			$("#machineid").val(result.maintainlogInfo.machineid);
			
			bindevent();
		},
		error:function(error){
			layer.alert('error', {
			    skin: 'layui-layer-lan', //样式类名
			    closeBtn: 0
			});
		}
	});
}
