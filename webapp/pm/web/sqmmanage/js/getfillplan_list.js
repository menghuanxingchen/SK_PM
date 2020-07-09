$(function(){
	menu('menuB13');
	initPage();
});

function getProcessorId(){
	var commonData = parent.CsGlobalObj||parent.parent.CsGlobalObj;
	var id =commonData.currentUserid||"";
	return id;
}
var currentUserid =getProcessorId();

/**
 * 初始化页面
 * */
function initPage(){
	$.ajax({
		url:getRequestUrl("/SqmmanageController/beforeDataList.json"),
		dataType:"json",
		data:[],
		success:function(data){
			$( "#lineTypeForm" ).html(
					$("#lineListTemplate").render(data)
			);
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
	bindevent();
	queryDataList();
	
	$("#start").val(getNowFormatDate())
	$("#end").val(getNowFormatDate())
}

/**
 * 绑定事件
 */
function bindevent(){
	
	var start = {
			  elem: '#start',
			  istoday: true,
			  format: 'YYYYMMDD'
			};
	
	laydate(start);
	
	
	var end = {
			  elem: '#end',
			  istoday: true,
			  format: 'YYYYMMDD'
			};
	
	laydate(end);
	
	$("#search_btn").bind('click',queryDataList);
}

/**
 * 查询方法
 */
function queryDataList(){

	var postData = collectData("search_area");

	var parameterData = {
			url:getRequestUrl("/SqmmanageController/queryFillPlanDataList.json"),
			successfunc:renderListDataFun,
			customArray:postData,
			pageller:"pagefoot",
			pageIndex:1,
			pageSize:25,
			checkedId:"checkboxGroupId",
			isAsync:false
	};
	
	pageBarFortable(parameterData)
}

//回调
function renderListDataFun(result){
	for(var i=0;i<result.length;i++){
		result[i]['currentUserid']=currentUserid;
	}
	$( "#t_r_content" ).html(
		
			$("#dataListTemplate").render( result
		          )
	);
	//奇偶行颜色不同
	$('.t_r_content tbody tr:odd').addClass('odd');
	//var table_height=$("#table_height").height();
	if(result.length>10)
	{
		$("#last").css({paddingRight:'25px'});
		
	}else{
		$("#last").css({paddingRight:'8px'});
	}
}


/**
 * 启用信息
 * @param id
 */
function beginDataFun(id){
	$.ajax({
		url:getRequestUrl("/SqmmanageController/getfillplandata.json"),
		dataType:"json",
		data:{},
		success:function(result){
			lalert("handle","success");
			queryDataList();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	})
}

/**
 * 取消检测
 * @param id
 */
function quxiaoDataFun(id){
	$.ajax({
		url:getRequestUrl("/SqmmanageController/quxiaoDataFun.json"),
		dataType:"json",
		data:{"fillingPlanInfo.id":id},
		success:function(result){
			lalert("handle","success");
			queryDataList();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	})
}


function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

