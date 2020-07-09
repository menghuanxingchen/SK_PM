var pot_id = getRequestParameterValue("pot_id");
var nowPotNM = '';

$(function(){
	menu('menuA1');
	initPage();
	bindevent();	
});
function initPage(){	
	getmaTypeInfo(pot_id);
}

function bindevent(){	
	//保存事件
	$("#saveBtn").bind('click',function(){
		updateFormData();
	});
	//返回
	$("#gobackBt").bind('click',function(){
		pageForward('/pm/web/basicinfo/machinemanager/machine_pot_info_list.jsp');
	});
}

//检查设备名称是否重复
var docheckDBRes = false;
function docheckDB() {
	var nmInput = $("input[name='machinePotInfo.pot_nm']");
	nmInput.css('border-color','');
	if(nmInput.val().trim()!='' && nmInput.val()!=nowPotNM){
		$.ajax({
			url:getRequestUrl("/MachinePotInfoController/docheckDB.json"),
			dataType:"json",
			data:{'machinePotInfo.pot_nm':nmInput.val()},
			success:function(result){
				if(result.opflag){
					nmInput.css('border-color','red');
					alert('设备名称已存在');
				}else{				
					docheckDBRes = true;
				}
			},
			error:function(error){
				alert("error");
			}
		});
		return docheckDBRes;
	}else if(nmInput.val()==nowPotNM){
		return true;
	}
	return false;
}


/**
 * 更新设备信息
 * @returns
 */
function updateFormData(){
	var checkflag =	checkFormValue('checkupdateform')&&docheckDB();
	if(checkflag){
		layer.confirm("是否提交", {
		    btn: ['确定','取消'] //按钮
		}, function(){
			var postData = collectData("checkupdateform");
			postData=$.extend(postData,{"machinePotInfo.pot_id":pot_id});
			$.ajax({
				url:getRequestUrl("/MachinePotInfoController/updEntityData.json"),
				dataType:"json",
				data:postData,
				success:function(result){
					if(result.opflag){
						lalert("update","success",function(){
							pageForward('/pm/web/basicinfo/machinemanager/machine_pot_info_list.jsp');
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
function getmaTypeInfo(id){
	$.ajax({
		url : getRequestUrl("/MachinePotInfoController/queryBeforeUpdate.json"),
		dataType:"json",
		data:{"machinePotInfo.pot_id":id},
		success:function(result){
			$("#checkupdateform").html($("#formTemplate").render(result.dataInfo));
			nowPotNM = $("input[name='machinePotInfo.pot_nm']").val();
			//检验名称
			$("input[name='machinePotInfo.pot_nm']").bind('blur',function(){
				docheckDB();
			});
			laydate({
				  elem: '#production_date',
				  istoday: true,
				  format: 'YYYY-MM-DD',
				  choose: function(datas){
					  $("#production_date").val(datas); //结束日选好后，重置开始日的最大日期
				  }
			});
		},
		error:function(error){
			layer.alert('error', {
			    skin: 'layui-layer-lan', //样式类名
			    closeBtn: 0
			});
		}
	});
}
