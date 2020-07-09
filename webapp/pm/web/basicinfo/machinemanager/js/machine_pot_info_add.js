$(function(){
	initPage();	
});


function initPage(){
	menu('menuA1');
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
		pageForward('/pm/web/basicinfo/machinemanager/machine_pot_info_list.jsp');
	});
	//检验名称
	$("input[name='machinePotInfo.pot_nm']").bind('blur',function(){
		docheckDB();
	});
	
}

//检查设备名称是否重复
var docheckDBRes = false;
function docheckDB() {
	var nmInput = $("input[name='machinePotInfo.pot_nm']");
	if(nmInput.val().trim()!=''){
		nmInput.css('border-color','');
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
				url:getRequestUrl("/MachinePotInfoController/addEntityData.json"),
				dataType:"json",
				data:postData,
				success:function(result){
					if(result.opflag){
						lalert("save","success",function(){
							pageForward('/pm/web/basicinfo/machinemanager/machine_pot_info_list.jsp');
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
//得到页面下拉
function getPagaLists(){
	$.ajax({
		url:getRequestUrl("/MachineController/queryPageList.json"),
		dataType:"json",
		data:"",
		success:function(result){
			$("#machinespecies").html($("#machinespeciesListTemplate").render(result));
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

