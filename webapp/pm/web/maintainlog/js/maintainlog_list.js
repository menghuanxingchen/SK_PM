$(function(){
	menu('menu32');
	initPage();  	
	
});


function initPage(){
	getCodeLists();
	bindevent();
}

function bindevent(){		
	if(auth!='2'){
		$("#updateStateBt").hide();		
	}
	$("#departTypeForm").change(function(){		
		getSubdepvalList($("#dept").val(),"");
	});
	var start = {
			  elem: '#start',
			  istoday: true,
			  istime:true,
			  format: 'YYYY-MM-DD',
			  choose: function(datas){
			     end.min = datas; //开始日选好后，重置结束日的最小日期
			     end.start = datas //将结束日的初始值设定为开始日
			  }
			};
	var end = {
			  elem: '#end',
			  istoday: true,
			  istime:true,
			  format: 'YYYY-MM-DD',
			  choose: function(datas){
			    start.max = datas; //结束日选好后，重置开始日的最大日期
			  }
			};
	laydate(start);
	laydate(end);
	//$("#start").val(laydate.now(-7));	
	var myDate = new Date();
	var month=myDate.getMonth();
	if(month < 10){
		month="0"+month;
	}
	//$("#start").val(myDate.getFullYear()+"-"+month+"-01");//开始日期上月1号
	$("#start").val(myDate.getFullYear()+"-01-01");//开始日期当前年1月1号
	$("#end").val(laydate.now());
	//跳转到新增页面
	$("#gotoAddpageBt").bind('click',function(){
		pageForward('/pm/web/maintainlog/maintainlog_add.jsp');
	});
	$("#searchBt").bind('click',function(){
		getMaintLogList();
	});
	//check all
	$("#checkGroup").bind('click',function(){
		checkedAllSimple("checkGroup");
	});
	
	$("#updateStateBt").bind('click',function(){
		updateStateBatch();
	});
	
}

function getSubdepvalList(deptval,subdeptval){
	$.ajax({
		url:getRequestUrl("/SysUserInfoController/getSubCodeList.json"),
		dataType:"json",
		data:{"subGroupCode":deptval},
		success:function(data){
			$( "#subDepartTypeForm" ).html(
					$("#subDepartListTemplate").render(data)
			);			
			if(subdeptval!=""){
				$("#subdept").val(subdeptval);
				getMaintLogList();
			}
			
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * 查询提单列表
 */
function getMaintLogList(){
	var postData = collectData("search_area");
	layer.load();//添加进度条
	var parameterData = {
			url:getRequestUrl("/MaintainLogController/queryMaitainLogList.json"),
			successfunc:renderListDataFun,
			customArray:postData,
			pageller:"pagefoot",
			pageIndex:1,
			pageSize:50,
			isAsync:false
	};
	pageBarFortable(parameterData);
}
/**
 * 查询回调
 * @param result
 */
function renderListDataFun(result){
	$( "#infoList" ).html(
		$("#infoListTemplate").render(result)
	);
	$('#infoList tr:odd').addClass('odd'); //奇数行样式
	$('#infoList tr:even').addClass('eve');//偶数行样式
	
	layer.closeAll('loading'); //关闭进度条
	
	//var table_height=$("#table_height").height();
	if(result.length>10)
	{
		$("#last").css({paddingRight:'25px'});
		
	}else{
		$("#last").css({paddingRight:'8px'});
	}
	//权限
	for(var i =0;i<result.length;i++){
		if(auth!='2'){
			$("#confirm"+i).remove();
			
		}
	}
}
/**
 * 删除计划 
 */
function deleteInfo(id){
	layer.confirm('是否删除？', {
	    btn: ['确定','取消'] //按钮
	}, function(){			
		$.ajax({
			url:getRequestUrl("/MaintainLogController/deleteMaintainLog.json?id="+id),
			dataType:"json",
			success:function(result){
				if(result.opflag){
					lalert("delete","success");
					getMaintLogList();					
				}
			},
			error:function(error){
				lalert("delete","error");
			}
		});
	});
}

/**
 * 跳转到update页面
 */
function gotoUpdatePage(id){
	var page="/pm/web/maintainlog/maintainlog_update.jsp?malog_id="+id;
	pageForward(page);
}

//得到页面下拉
function getCodeLists(){
	$.ajax({
		url:getRequestUrl("/MaintainLogController/queryPageList.json"),
		dataType:"json",
		data:"",
		success:function(result){
			$("#mtype").html($("#mltypeListTemplate").render(result));
			$("#mstate").html($("#mlStateListTemplate").render(result));
			$( "#departTypeForm" ).html($("#departListTemplate").render(result));
			$("#machineList").html($("#maListTemplate").render(result));
			$("#dept").val(deptval);	
			getSubdepvalList(deptval,subdeptval);
			
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * 确认  状态
 */
function updateLogState(id){
	layer.confirm('是否确认？', {
	    btn: ['确定','取消'] //按钮
	}, function(){	
		var postData={"maintainlogInfo.maintainlog_id":id,"maintainlogInfo.check_state":Class.getConstant('PLAN_STATE_CHECKED')};
		$.ajax({
			url:getRequestUrl("/MaintainLogController/updateMaintainlogInfo.json"),
			dataType:"json",
			data:postData,
			success:function(result){
				if(result.opflag){
					lalert("update","success");
					getMaintLogList();
				}
			},
			error:function(error){
				lalert("update","error");
			}
		});
	});
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
		layer.confirm("是否批量确认"+len+"条数据", {
		    btn: ['确定','取消'] //按钮
		}, function(){	
			var postData={"maintainlogInfo.maintainlog_id":idsStr};
			$.ajax({
				url:getRequestUrl("/MaintainLogController/updateStateBatch.json"),
				dataType:"json",
				type:"POST",
				data:postData,
				success:function(result){
					if(result.opflag){
						lalert("update","success",function(){
							pageForward('/pm/web/maintainlog/maintainlog_list.jsp');
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
/**
 * 确认--fish
 * @param id
 */
function updateConfirm(id){
	var message = "确认审批？";
	layer.confirm(
			message,
			function(index){
				$.ajax({
					url:getRequestUrl("/MaintainLogController/updateConfirmData.json"),
					dataType:"json",
					data:{"maintainlogInfo.maintainlog_id":id},
					success:function(result){
						lalert("update","success");
						getMaintLogList();
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
