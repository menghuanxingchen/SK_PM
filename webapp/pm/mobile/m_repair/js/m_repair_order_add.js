$(function(){
	//页面初始化的事件
	initPage();  	
	
});

var id = getRequestParameterValue("id");
function initPage(){	
	bindevent();
	getCodeLists();
}

function bindevent(){	
	var date_opt=dateBuild();
	$("#require_time").val('').scroller('destroy').scroller($.extend(date_opt['date'], date_opt['def']));
	
	var d = new Date(); //实例一个时间对象
	d.setDate(d.getDate()+7);
	var date =  d.getFullYear()+"-"+(d.getMonth()+1>9?d.getMonth()+1:"0"+(d.getMonth()+1))+"-"+(d.getDate()>9?d.getDate():"0"+d.getDate());

	$("#require_time").val(date);
	
	//跳回
	$("#cancelBtn").unbind();
	$("#cancelBtn").bind('click',function(){
		pageForward('/pm/mobile/m_repair/m_repair_list.jsp');
	});
	//save
	$("#submitBtn").unbind();
	$("#submitBtn").bind('click',function(){
		addRepairOrderInfoDataForMob();
	});
}

//得到页面下拉
function getCodeLists(){
	$.ajax({
		url:getRequestUrl("/RepairOrderInfoController/queryDownList3.json"),
		dataType:"json",
		data:"",
		success:function(result){			
			$("#repairPlace").html($("#repairPlacedownListTemplate").render(result));
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * save 方法
 */
function addRepairOrderInfoDataForMob(){
	layer.open({
		content: '确定提交',
	    btn: ['确定','取消'], //按钮
	    yes: function() {
	    	var postData = collectData("com_body");
	    	$.ajax({
	    		url:getRequestUrl("/RepairOrderInfoController/addRepairOrderInfoDataForMob.json"),
	    		dataType:"json",
	    		data:postData,
	    		success:function(result){
	    			if(result.opflag){
	    				pageForward('/pm/mobile/m_repair/m_repair_list.jsp');
	    			}
	    		},
	    		error:function(error){
	    			layer.closeAll('loading');
	    			lalert('网络原因操作失败！','error');
	    		}
	    	});
	    }
	})
}
/**
 * 跳转页
 */
function goBack(){
	var page = "/pm/mobile/m_repair/m_repair_list.jsp";
	pageForward(page);
}