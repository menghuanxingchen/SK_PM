$(function(){
	//页面初始化的事件
	initPage();  	
	
});

function getProcessorId(){
	var commonData = parent.CsGlobalObj||parent.parent.CsGlobalObj;
	var id =commonData.currentUserid||"";
	return id;
}


function getProcessorName(){
	var commonData = parent.CsGlobalObj||parent.parent.CsGlobalObj;
	var name =commonData.sys_hidden_currentUsername||"";
	
	return name;
}
function getProcessordept(){
	var commonData = parent.CsGlobalObj||parent.parent.CsGlobalObj;
	var dept =commonData.sys_hidden_dept_name||"";

	return dept;
}
var currentUserName =getProcessorName();
var currentUserDept =getProcessordept();
var currentUserid =getProcessorId();

var id = getRequestParameterValue("id");
function initPage(){	
	
	bindevent();
	queryList();
}

function bindevent(){	
	var date_opt=dateBuild();
	$("#movingStdate").val('').scroller('destroy').scroller($.extend(date_opt['date'], date_opt['def']));
	$("#movingEddate").val('').scroller('destroy').scroller($.extend(date_opt['date'], date_opt['def']));
	
	var myDate = new Date();
	var preDate = new Date(myDate.getTime() - 24*60*60*1000); //前一天
	var nextDate = new Date(myDate.getTime() + 24*60*60*1000); //后一天
	$("#movingStdate").val(getyyyyMMdd(preDate))
	$("#movingEddate").val(getyyyyMMdd(nextDate))
	
	
	//$("#movingStdate").val(CurentTime());
	//$("#movingEddate").val(CurentTime());
	
	$("#movingNo").on('input',function(e){  
		queryList();
	});
	$("#movingPerson").on('input',function(e){  
		queryList();
	});
	$("#skApplyName").on('input',function(e){  
		queryList();
	});
	$("#movingStdate").change(queryList);
	$("#movingEddate").change(queryList);
	
	$("#movingType").change(queryList);
	$("#applyType").change(queryList);
	$("#movingCompany").change(queryList);
}

function queryList(){	
	
	$("#applyulList").html("");
	
	var postData = collectData("search_area");
	postData=$.extend(postData,{"processor.pageIndex":1,"processor.pageSize":999});
	
	$.ajax({
		url:getRequestUrl("/MovingListController/queryDataList.json"),
		dataType:"json",
		data:postData,
		success:function(result){
			
			var strth=" ";
			
			for(var i=0;i<result.data.records.length;i++){
				var butType=""
				var approval_state = result.data.records[i]['approval_state']
				var approval_state_name = result.data.records[i]['approval_state_name']
				var deal_user_id = result.data.records[i]['deal_user_id']
				var movingType = result.data.records[i]['movingType']
				var movingNo = result.data.records[i]['movingNo']
				var deal_user_name = result.data.records[i]['deal_user_name']
				var movingPerson = result.data.records[i]['movingPerson']
				var movingPhone = result.data.records[i]['movingPhone']
				var movingCarno = result.data.records[i]['movingCarno']
				var movingGoalType = result.data.records[i]['movingGoalType']
				var movingGoalContent = result.data.records[i]['movingGoalContent']
				var movingTime = result.data.records[i]['movingTime']
				var moutYN = result.data.records[i]['moutYN']
				var movingCompany = result.data.records[i]['movingCompany']
				var skContactsNo = result.data.records[i]['skContactsNo']
				
				if( approval_state !="6" && deal_user_id == currentUserid && currentUserid !="lg00999"){
					butType = "<button type='submit' id='applyBtn' onclick=\"gotopage('"+ movingNo+ "','"+movingType+"')\" class='m_btn_apply'>审批</button> "
				}
				if( approval_state !="6" && deal_user_id == currentUserid && currentUserid =="lg00999"){
					butType = "<button type='submit' id='applyBtn' onclick=\"applyEntityDataFun('"+ movingNo+ "','"+movingType+"')\" class='m_btn_apply'>确认</button> "
				}
				if(approval_state == 6 && moutYN=="Y"  && currentUserid == skContactsNo){
					butType = butType+"<button type='submit' id='applyBtn' onclick=\"outEntityApply('"+ movingNo+ "','"+ movingPerson+ "','"+ movingPhone+ "','"+ movingCarno+ "','"+ movingCompany+ "')\" class='m_btn_apply'>搬出</button>";
				}
				strth=strth+"<li class='list list-no-img'>"+
					"<a class='table_area' href=\"javascript:applyEntityDetail('"+ movingNo+ "','"+movingType+"');\" onclick=''>"+
					  "<div class='top'>"+
						"<div class='title list-ellipsis-2'>"+movingType+"："+movingNo+"&nbsp;&nbsp;</div>"+
						"<p class='content'>"+
							"审批状态："+approval_state_name+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
							"现审批人："+deal_user_name+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
							"公司："+movingCompany+
						"</p>"+
						"<p class='content'>"+
							movingPerson+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
							movingPhone+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
							movingCarno+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
							"目的："+movingGoalType+
							"&nbsp;&nbsp;&nbsp;&nbsp;"+movingGoalContent+
						"</p>"+
					"</div></a>"+
					
					"<div class='info'>"+
						"<span class=''>搬入时间："+movingTime+"</span>"+
						"<span class='info-r-5'>"+butType+"</span>"+
					"</div>"+
				"</li>";
			}

			$("#applyulList").append(strth);
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}



/**
 * 跳转页
 */
function gotopage(movingNo,movingType){
	if(movingType=="搬入证"){
		pageForward("/pm/mobile/m_moving_info/m_moving_in_detail.jsp?movingNo="+movingNo+"&flag=1");
	}else{
		pageForward("/pm/mobile/m_moving_info/m_moving_out_detail.jsp?movingNo="+movingNo+"&flag=1");
	}
}

/**
 * 跳转页
 */
function applyEntityDataFun(movingNo,movingType){
	if(movingType=="搬入证"){
		pageForward('/pm/mobile/m_moving_info/m_moving_in_detail.jsp?movingNo='+movingNo+'&flag=2');
	}else{
		pageForward('/pm/mobile/m_moving_info/m_moving_out_detail.jsp?movingNo='+movingNo+'&flag=2');
	}
}

/**
 * 跳转页
 */
function applyEntityDetail(movingNo,movingType){
	if(movingType=="搬入证"){
		pageForward('/pm/mobile/m_moving_info/m_moving_in_detail.jsp?movingNo='+movingNo);
	}else{
		pageForward('/pm/mobile/m_moving_info/m_moving_out_detail.jsp?movingNo='+movingNo);
	}
}

/**
 * 跳转页
 */
function outEntityApply(movingNo,movingPerson,movingPhone,movingCarno,movingCompany){
	pageForward('/pm/mobile/m_moving_info/m_moving_out.jsp?movingNo='+movingNo+
			'&movingPerson='+movingPerson+'&movingPhone='+movingPhone+'&movingCarno='+movingCarno+'&movingCompany='+movingCompany);
}
/**
 * 跳转页
 */
function goBack(){
	//window.history.back(-1);
	pageForward('/pm/mobile/main/index_moving.jsp');
}

function getyyyyMMdd(d){
	   
    var curr_date = d.getDate();
    var curr_month = d.getMonth() + 1; 
    var curr_year = d.getFullYear();
    String(curr_month).length < 2 ? (curr_month = "0" + curr_month): curr_month;
    String(curr_date).length < 2 ? (curr_date = "0" + curr_date): curr_date;
    var yyyyMMdd = curr_year + "-" + curr_month +"-"+ curr_date;
    return yyyyMMdd;
}    