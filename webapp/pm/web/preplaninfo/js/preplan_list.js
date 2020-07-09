$(function(){
	menu('menu21');
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
		url:getRequestUrl("/PrePlanInfoController/beforeDataList.json"),
		dataType:"json",
		data:[],
		success:function(data){
			$( "#departTypeForm" ).html(
					$("#departTypeListTemplate").render(data)
			);
			
			$( "#cycleListForm" ).html(
					$("#cycleListTemplate").render(data)
			);
			
			$( "#orderListForm" ).html(
					$("#orderListTemplate").render(data)
			);
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
	bindevent();
	queryDataList();
}

/**
 * 绑定事件
 */
function bindevent(){
	var start = {
			  elem: '#start',
			  istoday: true,
//			  min: laydate.now(+1), //最大日期
			  isclear: false, //是否显示清空
			  istoday: false, //是否显示今天
			  choose: function(datas){
			     end.min = datas; //开始日选好后，重置结束日的最小日期
			     end.start = datas //将结束日的初始值设定为开始日
			  }
			};
	var end = {
			  elem: '#end',
			  istoday: true,
			  min: laydate.now(+1), //最大日期
			  isclear: false, //是否显示清空
			  istoday: false, //是否显示今天
			  choose: function(datas){
			    start.max = datas; //结束日选好后，重置开始日的最大日期
			  }
			};
	laydate(start);
	laydate(end);
	$("#start").val(laydate.now(+1));
			
	$("#search_btn").bind('click',queryDataList);
	
	//check all
	$("#checkGroup").bind('click',function(){
		checkedAllSimple("checkGroup");
	});
	
	$("#updateStateBt").bind('click',updateStateBatch);
}

/**
 * 查询方法
 */
function queryDataList(){
	var postData = collectData("search_area");
	var parameterData = {
			url:getRequestUrl("/PrePlanInfoController/queryDataList.json"),
			successfunc:renderListDataFun,
			customArray:postData,
			pageller:"pagefoot",
			pageIndex:1,
			pageSize:50,
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
	if($( "#planTypeForm" ).val()==Class.getConstant('PLAN_LIST')){
		$( "#title" ).show();
		$( "#title2" ).hide();
		if(result.length>10)
		{
			$("#last").css({paddingRight:'25px'});
			
		}else{
			$("#last").css({paddingRight:'8px'});
		}
	}else{
		$( "#title" ).hide();
		$( "#title2" ).show();
		if(result.length>10)
		{
			$("#last2").css({paddingRight:'25px'});
			
		}else{
			$("#last2").css({paddingRight:'8px'});
		}
	}
	
	//奇偶行颜色不同
	$('.t_r_content tbody tr:odd').addClass('odd');
	//var table_height=$("#table_height").height();
	
}

/**
 * 新增
 */
function addEntityDataFun(){
	var page = "/pm/web/preplaninfo/preplan_add.jsp";
	pageForward(page);
}

/**
 * 修改
 * @param id
 */
function updateEntityDataFun(id){
	var page = "/pm/web/preplaninfo/preplan_update.jsp?id="+id;
	pageForward(page);
}

/**
 * 删除信息
 * @param id
 */
function deleteDataFun(id){
	var message = "确认删除？";
	layer.confirm(
			message,
			function(index){
				$.ajax({
					url:getRequestUrl("/PrePlanInfoController/deleteEntityData.json"),
					dataType:"json",
					data:{"prePlanInfo.pre_plan_id":id},
					success:function(result){
						lalert("delete","success");
						queryDataList();
					},
					error:function(error){
						lalert('网络原因操作失败！','error');
					}
				});
				layer.close(index);
			},
			function(index){
				layer.close(index);
				return;
			}
	);
}


//批量确认
function updateStateBatch(){
	
	var res=buildIdsBatch("checkGroup");//用于返回 选中条数  和id用，隔开的字符串
	if(res==""){
		layer.alert('请选择至少一条数据');
		$("#checkGroup").prop("checked",false);
	}else{
		var len = res[0];
		var idsStr = buildNewIds(res[1]);//将id字符串加‘’		
		layer.confirm("是否批量删除"+len+"条数据", {
		    btn: ['确定','取消'] //按钮
		}, function(){	
			var postData={"prePlanInfo.pre_plan_group":idsStr};
			$.ajax({
				url:getRequestUrl("/PrePlanInfoController/deleteStateBatch.json"),
				dataType:"json",
				type:"POST",
				data:postData,
				success:function(result){
					if(result.opflag){
						lalert("update","success",function(){
							queryDataList();
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

