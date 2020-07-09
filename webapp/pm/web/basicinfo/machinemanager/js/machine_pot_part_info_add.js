$(function(){
	initPage();	
});


function initPage(){
	menu('menuA2');
	//getPagaLists();
	bindevent();
}

function bindevent(){
	laydate({
		  elem: '#production_date',
		  istoday: true,
		  format: 'YYYY-MM-DD',
		  choose: function(datas){
			  $("#production_date").val(datas); //结束日选好后，重置开始日的最大日期
		  }
		});
	//保存事件
	$("#saveBtn").bind('click',function(){
		saveFormData();
	});
	//返回
	$("#gobackBt").bind('click',function(){
		pageForward('/pm/web/basicinfo/machinemanager/machine_pot_part_info_list.jsp');
	});
	//检验名称
	$("input[name='machinePotPartInfo.pot_part_nm']").bind('blur',function(){
		docheckDB();
	});
	$("#selPotNm").bind('blur',function(){
		docheckDB();
	});
}

//检查设备名称是否重复
var docheckDBRes = false;
function docheckDB() {
	var nmInput = $("input[name='machinePotPartInfo.pot_part_nm']");
	var potId = $('#selPotId');
	if(nmInput.val().trim()!='' && potId.val().trim()!=''){
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

function saveFormData(){
	var checkflag =	checkFormValue('addform')&&docheckDB();
	if(checkflag){
		layer.confirm("是否提交", {
		    btn: ['确定','取消'] //按钮
		}, function(){
			var postData = collectData("addform");
			$.ajax({
				url:getRequestUrl("/MachinePotPartInfoController/addEntityData.json"),
				dataType:"json",
				data:postData,
				success:function(result){
					if(result.opflag){
						lalert("save","success",function(){
							pageForward('/pm/web/basicinfo/machinemanager/machine_pot_part_info_list.jsp');
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