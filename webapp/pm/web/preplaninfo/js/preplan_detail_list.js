$(function(){
	menu('menu22');
	initPage();
	bindevent();
});

var id="";
var machType = "";
var cycle = "";
var start = "";
var end = "";
var orderType = "";
/**
 * 初始化页面
 * */
function initPage(){
	id = getRequestParameterValue("id");
	$("#planNm").val(getRequestParameterValue("planNm"));
	machType = getRequestParameterValue("machType");
	cycle = getRequestParameterValue("cycle");
	start = getRequestParameterValue("start");
	end = getRequestParameterValue("end");
	orderType = getRequestParameterValue("orderType");
	queryDataList();
}

/**
 * 绑定事件
 */
function bindevent(){
	$("#search_btn").bind('click',queryDataList);
}

/**
 * 查询方法
 */
function queryDataList(){
	$.ajax({
		url:getRequestUrl("/PrePlanInfoController/queryPrePlanCheckDetail.json"),
		dataType:"json",
		data:{"prePlanInfo.pre_plan_id":id},
		success:function(result){
			$("#plan_nm").val(result.prePlanInfo.pre_plan_nm);
			if(result.prePlanInfo.pre_plan_state != Class.getConstant('PLAN_STATE')){ //计划状态——待确认
				$("#confirmBtn").remove();
			}
			//生成表头
			//var titleinfo=result.datatitle;
			/*var str ="<thead><tr>";
			for(var i=0;i<titleinfo.length;i++){				
				str = str+"<th>"+titleinfo[i]+"</th>";
			}	                                           
			str = str+"</tr></thead> ";
			$("#detailtitle").html(str);	*/
			//生成表内容
			var datalist=result.data;                    
			var strbody ="<tbody id='t_r_content' onscroll='aa()'>";
			
			/*strbody ="<thead><tr>";
			for(var i=0;i<titleinfo.length;i++){				
				strbody = strbody+"<th>"+titleinfo[i]+"</th>";
			}	                                           
			strbody = strbody+"</tr></thead> ";*/
			
			for(var p=0;p<datalist.length;p++){		
				if(p==0){
					strbody ="<thead><tr>";
					for(var i=0;i<datalist[p].length;i++){
					strbody = strbody+"<th>"+datalist[p][i]+"</th>";
					}
					strbody = strbody+"</tr></thead> "
				}
				else{
					strbody =strbody+"<tr>";
					for(var j=0;j<datalist[p].length;j++){
						strbody = strbody+"<td width='100px'>"+datalist[p][j]+"</td>";
					}
				}
				strbody =strbody+"</tr>";
			}	              
			
			var dataprop=result.dataprop;
			for(var p=0;p<dataprop.length;p++){	
				strbody =strbody+"<tr>";
				//strbody =strbody+"<tbody id='t_r_content' onscroll='aa()'><tr>";
				for(var j=0;j<datalist[p].length;j++){
					var str = dataprop[p][j];
					if(str==undefined){
						str = '';
					}
					strbody = strbody+"<td width='100px'>"+str+"</td>";
				}
				strbody = strbody+"</tr>";
			}
			strbody = strbody+"</tbody>";
			$("#detailList").html(strbody);
		},
		error:function(error){
			lalert("网络原因操作失败！","error");
		}
	});

}

function goBack(){
	var page = "/pm/web/preplaninfo/preplan_history_list.jsp?planNm="+escape($("#planNm").val())+"&machType="+machType+"&cycle="+cycle+"&start="+start+"&end="+end+"&orderType="+orderType;
	pageForward(page);
}

function updateConfirm(){

	var message = "确认审批？";
	layer.confirm(
			message,
			function(index){
				$.ajax({
					url:getRequestUrl("/PrePlanInfoController/updateConfirmData.json"),
					dataType:"json",
					data:{"prePlanInfo.pre_plan_id":id},
					success:function(result){
						lalert("update","success");
						queryDataList();
					},
					error:function(error){
						lalert('网络原因操作失败！','error');
					}
				});
				layer.close(index);
			},
			"信息",
			function(index){
				layer.close(index);
				return;
			}
	);

}
