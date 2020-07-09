var pot_part_id = getRequestParameterValue("pot_part_id");
var nowPotId = '';
var nowPotPartNm = '';

$(function(){
	menu('menuA2');
	initPage();
	bindevent();	
});
function initPage(){	
	getmaTypeInfo(pot_part_id);	
}

function bindevent(){	
	//保存事件
	$("#saveBtn").bind('click',function(){
		updateFormData();
	});
	//返回
	$("#gobackBt").bind('click',function(){
		pageForward('/pm/web/basicinfo/machinemanager/machine_pot_part_info_list.jsp');
	});
	
}

//检查设备名称是否重复
var docheckDBRes = false;
function docheckDB() {
	var nmInput = $("input[name='machinePotPartInfo.pot_part_nm']");
	var potId = $('#selPotId');
	if(nmInput.val()==nowPotPartNm && potId.val()==nowPotId){
		return true;
	}else if(nmInput.val().trim()!='' && potId.val().trim()!=''){
		nmInput.css('border-color','');
		$.ajax({
			url:getRequestUrl("/MachinePotPartInfoController/docheckDB.json"),
			dataType:"json",
			data:{'machinePotPartInfo.pot_part_nm':nmInput.val(),'machinePotPartInfo.pot_id':potId.val()},
			success:function(result){
				if(result.opflag){
					nmInput.css('border-color','red');
					alert('该设备下已存在该部件名称');
				}else{				
					docheckDBRes = true;
				}
			},
			error:function(error){
				alert("error");
			}
		});
		return docheckDBRes;
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
			postData=$.extend(postData,{"machinePotPartInfo.pot_part_id":pot_part_id});
			$.ajax({
				url:getRequestUrl("/MachinePotPartInfoController/updEntityData.json"),
				dataType:"json",
				data:postData,
				success:function(result){
					if(result.opflag){
						lalert("update","success",function(){
							pageForward('/pm/web/basicinfo/machinemanager/machine_pot_part_info_list.jsp');
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
		url : getRequestUrl("/MachinePotPartInfoController/queryBeforeUpdate.json"),
		dataType:"json",
		data:{"machinePotPartInfo.pot_part_id":id},
		success:function(result){
			$("#checkupdateform").html($("#formTemplate").render(result.dataInfo));
			nowPotPartNm = $("input[name='machinePotPartInfo.pot_part_nm']").val();
			nowPotId = $("#selPotId").val();
			//检验名称
			$("input[name='machinePotPartInfo.pot_part_nm']").bind('blur',function(){
				docheckDB();
			});
			$("#selPotNm").bind('blur',function(){
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

/**
 * POP选择页弹出
 * @returns
 */
function selectPot() {
	pagePopShow("一级设备选择","machine_pot_add_POP.jsp","1000px","600px");
}

/**
 * pop回调
 * @returns
 */
function selectPotBack(obj) {
	$('#selPotId').val(obj.id);
	$('#selPotNm').val(obj.nm);
}