var emergent_id = getRequestParameterValue("emergent_id");
var sqm_relieve_yn="";
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
            //$( "#ListForm" ).html(
            //      $("#ListTemplate").render(data)
            //);

            queryData();
        },
        error:function(error){
            lalert('网络原因操作失败！','error');
        }
    });
}

//数据显示
function queryData(){
    if(emergent_id == undefined || emergent_id==''){
        //初始化页面制御
        initPageTame(new Object());
        return;
    }

    layer.load();//添加进度条
    $.ajax({
        url:getRequestUrl("/EmergentRepairInfoController/queryBeforeUpdate.json"),
        dataType:"json",
        data:{"emergentRepairInfo.emergent_id":emergent_id},
        success:function(result){
            layer.closeAll('loading'); //关闭进度条
            if(result.opflag){
            	result.dataInfo["selectList"] = result.selectList;
            	result.dataInfo["potSonPartList"] = result.potSonPartList;
            	result.dataInfo["data"] = result.data;
            	sqm_relieve_yn=result.dataInfo.sqm_relieve_yn;
            	$( "#addform" ).html(
                		$("#addformTemplate").render(result.dataInfo)
                	);
            	initLaydate();
                //初始化页面制御
                initPageTame(result);
            }
        },
        error:function(error){
            layer.closeAll('loading'); //关闭进度条
            lalert('网络原因操作失败！','error');
        }
    });
}
function initLaydate(){
	
	jeDate('#repair_date',{
        format: 'YYYY-MM-DD'
    });
	 jeDate('#repairStart',{
	        format: 'YYYY-MM-DD hh:mm',
	        donefun:function(obj) {
	        	var startDate = new Date($("#repairStart").val());
	    	    var endDate = new Date($("#repairEnd").val());
	    	    
	    	    $("#repairHours").val(getIntervalHour(startDate,endDate))
	        }
	    });
	 jeDate('#repairEnd',{
	        format: 'YYYY-MM-DD hh:mm',
	        donefun:function(obj) {
	        	debugger
	        	var startDate = new Date($("#repairStart").val());
	    	    var endDate = new Date($("#repairEnd").val());
	    	    
	    	    $("#repairHours").val(getIntervalHour(startDate,endDate))
	        }
	    });
	

	var startDate = new Date($("#repairStart").val());
    var endDate = new Date($("#repairEnd").val());
	$("#repairHours").val(getIntervalHour(startDate,endDate))
}
function changeDate(){

	 var startDate = new Date($("#repairStart").val());
	 var endDate = new Date($("#repairEnd").val());
	    
	 $("#repairHours").val(getIntervalHour(startDate,endDate))
}
//计算两个时间相差了几个小时
function getIntervalHour(startDate, endDate) {
       var ms = endDate.getTime() - startDate.getTime();
       if (ms < 0) return 0;
       return (ms/1000/60).toFixed(2);
   }



//初始化页面制御
function initPageTame(result){
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
	
	var stTime = $("#rep_st").val();
	var edTime = $("#rep_ed").val();
	var selPotNm = $("#selPotNm").val();
	var repair_date = $("#repair_date").val();
	debugger
	if(/*stTime<=edTime && repair_date<=stTime && */selPotNm!="" && selPotNm != null){
		return true;
	}else{
		return false;
	}
}

//提交执行
function saveForm(){

    var checkfalg = checkFormValue('aaa')&&checkListValue();
    var repairHours = $("#repairHours").val()
    
    var mesType = "是否提交?";
    if(repairHours >3){
    	mesType = "维修时间超过3小时，是否确认提交？"
    }
    
    if(checkfalg){
        layer.confirm(mesType, {
            btn: ['确定','取消'] //按钮
        }, function(){
        	var stTime = new Date($("#rep_st").val()).getTime();
        	var edTime = new Date($("#rep_ed").val()).getTime();
        	var minus = (edTime-stTime)/(1000*60);
        	var total_time = minus+"";
            var postData = collectData("aaa");
            postData["emergentRepairInfo.total_time"]=total_time;
            postData["emergentRepairInfo.start_time"]=$("#rep_st").val();
            postData["emergentRepairInfo.end_time"]=$("#rep_ed").val();
            postData["emergentRepairInfo.repairType"]="Y";
            postData["emergentRepairInfo.sqm_relieve_yn"]=sqm_relieve_yn;
            layer.load();//添加进度条
            $.ajax({
                    url:getRequestUrl("/EmergentRepairInfoController/updEntityDataFeedback.json"),
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
    	lalert('请选择设备或输入正确时间！','error');
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
        data:["machinePotPartInfo.pot_id",id],
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
