$(function(){
	menu('menu25');
	initPage();  	
	//页面初始化的事件
	bindevent();
});

var plan_id="";
//传参数--by fish
var machType = "";
var cycle = "";
var start = "";
var end = "";
var orderType = "";
//传参数--by fish
function initPage(){
	plan_id =getRequestParameterValue("plan_id");
	//传参数--by fish
	$("#planNm").val(getRequestParameterValue("planNm"));
	machType = getRequestParameterValue("machType");
	cycle = getRequestParameterValue("cycle");
	start = getRequestParameterValue("start");
	end = getRequestParameterValue("end");
	orderType = getRequestParameterValue("orderType");
	//传参数--by fish
	getInspectPlanCheckList();
}

function bindevent(){	
	//跳转到新增页面
	$("#checkBt").bind('click',function(){
		updatePlanState(plan_id);
	});
	//返回
	$("#backBT").click(function(){
		//pageForward("/pm/web/inspectplan/inspectplan_actual_list.jsp");
		//传参数--by fish
		pageForward("/pm/web/inspectplan/inspectplan_actual_list.jsp?planNm="+escape($("#planNm").val())+"&machType="+machType+"&cycle="+cycle+"&start="+start+"&end="+end+"&orderType="+orderType);
	});
}
/**
 * 查询提单列表
 */
function getInspectPlanCheckList(){
	var postData = ({"insPlanInfo.ins_plan_id":plan_id});
	//layer.load();//添加进度条
	$.ajax({
		url:getRequestUrl("/InspectPlanController/queryInspectplanCheckDetialList.json"),
		dataType:"json",
		data:postData,
		success:function(result){
			//计划名称
			$("#plan_name").html(result.en.ins_plan_nm);
			$("#plan_date").html(result.en.ins_plan_date);
			if(result.en.ins_plan_state==Class.getConstant('PLAN_STATE')){
				$("#checkBt").show();
			}else{
				$("#checkBt").hide();
			}
			//生成表
			var datalist=result.data;
			var str ="<thead><tr>";
			for(var i=0;i<datalist[0].length;i++){				
				str = str+"<th>"+datalist[0][i]+"</th>";
			}	                                           
			str = str+"</tr></thead> ";
			$("#detailList").html(str);	
			//滚轮自适应
			//var table_height=$("#table_height").height();
			if(result.length>7)
			{
				$("#last").css({paddingRight:'25px'});
				
			}else{
				$("#last").css({paddingRight:'8px'});
			}
			//生成表内容
			var datalist=result.data;
			var strbody ="<tbody id='t_r_content' onscroll='aa()'>";
			for(var p=1;p<datalist.length;p++){		
				strbody =strbody+"<tr>";
				for(var j=0;j<datalist[p].length;j++){
					strbody = strbody+"<td width='100px'>"+datalist[p][j]+"</td>";
				}
				strbody = strbody+"</tr>";
			}	                                           
			strbody = strbody+"</tbody>";
			$("#detailList").append(strbody);
		},
		error:function(error){
			lalert("update","error");
		}
	});
}

/**
 * 确认修改计划状态
 */
function updatePlanState(id){
	layer.confirm('是否确认？', {
	    btn: ['确定','取消'] //按钮
	}, function(){	
		var postData={"insPlanInfo.ins_plan_id":id,"insPlanInfo.ins_plan_state":Class.getConstant('PLAN_STATE_CHECKED')};
		$.ajax({
			url:getRequestUrl("/InspectPlanController/updateInspectplans.json"),
			dataType:"json",
			data:postData,
			success:function(result){
				if(result.opflag){
					lalert("update","success",function(){
						pageForward('/pm/web/inspectplan/inspectplan_actual_list.jsp');
					});	
				}
			},
			error:function(error){
				lalert("update","error");
			}
		});
	});
}