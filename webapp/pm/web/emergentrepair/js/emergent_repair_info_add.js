var emergent_id = '';

$(function(){
    initPage();
});

//初始化页面
function initPage(){
    $.ajax({
        url:getRequestUrl("/EmergentRepairInfoController/beforeDataList.json"),
        dataType:"json",
        data:[],
        success:function(data){
        	$( "#jobList" ).html(
            		$("#jobTemplate").render(data)
            	);
        	$( "#sonPotList" ).html(
            		$("#sonPotTemplate").render(data)
            	);
        	initPageTame()
            queryData();
        	intPotpart();
        },
        error:function(error){
            lalert('网络原因操作失败！','error');
        }
    });
}

/**
 * 绑定事件属性
 * */
function intPotpart(){
	//部件和子部件联动
	$("#potPartList").change(function(){
		$.ajax({
			url:getRequestUrl("/EmergentRepairInfoController/getSonPotList.json"),
			dataType:"json",
			data:{"subGroupCode":$("#pot_part").val()},
			success:function(data){
				$( "#sonPotList" ).html(
						$("#sonPotTemplate").render(data)
				);
			},
			error:function(error){
				lalert('网络原因操作失败！','error');
			}
		});
	});
}

//数据显示
function queryData(){
/*	var start = {
			  elem: '#rep_st',
			  format: 'YYYY-MM-DD hh:mm',
			  istime:true,
			  choose: function(datas){
			     $("#rep_st").attr("dccheck","true");
			     $("#rep_st").css("background-color","#ffffff");
			     
			     var startDate = new Date($("#rep_st").val());
			     var endDate = new Date($("#rep_ed").val());
			     
			     $("#emergent_hours").val(getIntervalHour(startDate,endDate))
			     
			  }
			};
	var end = {
			  elem: '#rep_ed',
			  format: 'YYYY-MM-DD hh:mm',
			  istime:true,
			  choose: function(datas){
			    $("#rep_ed").attr("dccheck","true");
			    $("#rep_ed").css("background-color","#ffffff");
			    
			    var startDate = new Date($("#rep_st").val());
			     var endDate = new Date($("#rep_ed").val());
			     
			    $("#emergent_hours").val(getIntervalHour(startDate,endDate))
			  }
			};*/
	var date = {
			  elem: '#repair_date',
			  format: 'YYYY-MM-DD',
			  choose: function(datas){
				  start.min = datas;
				  $("#repair_date").attr("dccheck","true");
				  $("#repair_date").css("background-color","#ffffff");
				  
				  
			  },
			  clear: function(){
				  start.min = ""; //开始日选好后，重置结束日的最小日期
			  }
			};
	/*laydate(start);
	laydate(end);*/
	laydate(date);
}

//计算两个时间相差了几个小时
function getIntervalHour(startDate, endDate) {
       var ms = endDate.getTime() - startDate.getTime();
       if (ms < 0) return 0;
       return (ms/1000/60).toFixed(2);
   }

//初始化页面制御
function initPageTame(){
	//清除input按钮
	$('#selPotNm').bind('mouseover',function(){
		$('#clearPotA').css('visibility','');
	});
	$('#selPotNm').bind('mouseout',function(){
		$('#clearPotA').css('visibility','hidden');
	});
	$('#clearPotA').bind('mouseover',function(){
		$('#clearPotA').css('visibility','');
	});
	$('#clearPotA').bind('mouseout',function(){
		$('#clearPotA').css('visibility','hidden');
	});
    bindValidateEvent('aaa');
}

//提交前check
function checkListValue(){
	/*var stTime = $("#rep_st").val();
	var edTime = $("#rep_ed").val();*/
	var selPotNm = $("#selPotNm").val();
	var repair_date = $("#repair_date").val();
	var pot_part=$("#pot_part").val();
	if(/*stTime<=edTime && repair_date<=stTime &&*/ selPotNm!="" && selPotNm != null && pot_part!="" && pot_part != null){
		return true;
	}else{
		return false;
	}
}

//提交执行
function saveForm(){
    var checkfalg = checkFormValue('aaa')&&checkListValue();
    if(checkfalg){
        layer.confirm("是否提交?", {
            btn: ['确定','取消'] //按钮
        }, function(){
        	if (!!window.ActiveXObject || "ActiveXObject" in window){
        		var stTime = Date.parse($("#rep_st").val().replace(/-/g,"/"));
        		var edTime = Date.parse($("#rep_ed").val().replace(/-/g,"/"));
        		var minus = (edTime-stTime)/(1000*60);
        		var total_time = minus+"";
        	}else{
        		var stTime = new Date($("#rep_st").val()).getTime();
        		var edTime = new Date($("#rep_ed").val()).getTime();
        		var minus = (edTime-stTime)/(1000*60);
        		var total_time = minus+"";
        	}
            var postData = collectData("aaa");
            postData["emergentRepairInfo.total_time"]=total_time;
            postData["emergentRepairInfo.repairType"]="N";
            postData["emergentRepairInfo.job_type"]="1";
            layer.load();//添加进度条
            $.ajax({
                    //url:getRequestUrl("/EmergentRepairInfoController/addEntityData.json"),
                    url:getRequestUrl("/SqmInterfaceController/interfaceEmergentRepair.json"),
                    dataType:"json",
                    data:postData,
                    success:function(result){
                        layer.closeAll('loading'); //关闭进度条
                        if(result.opflag){
                            lalert('save','success',function(){
                                pageForward('/EmergentRepairInfoController/defaultJsp.do');
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
        })
    }else{
    	lalert('请选择设备、部件或输入正确时间！','error');
    }
}
/**
 * POP选择页弹出
 * @returns
 */
function selectPot() {
	var pathName = window.location.pathname;
    var webName = pathName.substring(0, pathName.indexOf('/',2));
	pagePopShow("设备选择",webName+"/MachinePotPartInfoController/selectPotPop","1000px","600px");
}

/**
 * pop回调
 * @returns
 */
function selectPotBack(obj) {
	$('#selPotId').val(obj.id);
	$('#selPotNm').val(obj.nm);
	selectPotPart(obj.id);
	$("body").trigger("");
}

/**
 * 根据设备选择部件
 */
function selectPotPart(id){
	$.ajax({
        url:getRequestUrl("/MachinePotPartInfoController/queryDataListByPotId.json"),
        dataType:"json",
        data:{"machinePotPartInfo.pot_id":id},
        success:function(result){
        	$("#potPartList").empty();
        	$( "#potPartList" ).html(
            		$("#potPartTemplate").render(result)
            	);
        },
        error:function(error){
            layer.closeAll('loading'); //关闭进度条
            lalert('网络原因操作失败！','error');
        }
});
}

/**
 * 清空一级设备选择
 * @returns
 */
function clearPot() {
	$('#selPotId').val('');
	$('#selPotNm').val('');
}
/**
 * 返回方法
*/
function pageBack(){
	pageForward('/EmergentRepairInfoController/defaultJsp.do');
}