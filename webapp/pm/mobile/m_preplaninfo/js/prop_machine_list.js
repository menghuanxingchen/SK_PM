$(function(){
	initPage();
});
var id="";
var chk_type = "";
var planNm = "";
var date = "";
var prop_id = "";
/**
 * 初始化页面
 * */
function initPage(){
	id = getRequestParameterValue("id");
	chk_type = getRequestParameterValue("chk_type");
	planNm = getRequestParameterValue("planNm");
	date = getRequestParameterValue("date");
	bindevent();
	queryDataList();
}

/**
 * 绑定事件
 */
function bindevent(){
	$("#search_btn").bind('click',queryDataList);
	$("#submit").bind('click',chkSaveForm);
	$("#cancel").bind('click',function(){
		var page = "/pm/mobile/m_preplaninfo/menu_m.jsp?id="+id;
		pageForward(page);
	});
}

/**
 * 查询方法
 */
var list =  new Array();
function queryDataList(){
	 layer.open({type: 2});
	var postData = collectData("search_area");
	$.ajax({
		url:getRequestUrl("/PrePlanInfoController/queryPrePlanPropMachList.json"),
		dataType:"json",
		data:{"prePlanPropMachineInfo.pre_plan_id":id},
		async: false,
		success:function(result){
			layer.closeAll('loading');
			list = result.prePlanPropMachineInfoList;
			$( "#dataListForm" ).html(
					$("#dataListTemplate").render(result.prePlanPropMachineInfoList)
			);
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}

/**
 * 保存项目名称
 */
var flag = false;
function checkItemIsNotNull(){
	  for(var i = 0;i<5;i++){
   	   if($('#machineNm'+i+'').val()!=""){
   		    flag = true;
   		    return;
   	   }else{
   		 flag = false; //deleteDataFun(id);
       }
      }
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
					url:getRequestUrl("/PrePlanInfoController/deletePropMachEntityData.json"),
					dataType:"json",
					data:{"prePlanPropMachineInfo.pre_plan_id":id},
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

function goToCheckItemDataFun(index){
			checkItemIsNotNull();
			if(flag&&Class.getConstant('EDITTYPE_UPDATE')==list[index].editType&&$('#machineNm'+index+'').val()==""){
					layer.open({
							content: '填写数据之前，检查项目名称会先被保存。若检查项目名称被删除，相应的数据也将被删除，不能恢复！',
						    btn: ['确定','取消'], //按钮
							yes: function(){
								$("input[name='prePlanPropMachineInfo["+index+"].editType']").val('A');
								saveForm();
								return;
							}
						})
				}else if(flag&&Class.getConstant('EDITTYPE_ADD')==list[index].editType&&$('#machineNm'+index+'').val()==""){
					lalert("请先填写检查项目","warn");
					return;
				}else if(flag){
					layer.open({
						content: '填写数据之前，检查项目名称会先被保存。',
					    btn: ['确定','取消'], //按钮
						yes: function(){
							checkItemDataFun(index);
							return;
						}
					})
			}else{
				layer.open({
					content: '请至少填写一个检查项目名称。',
				    btn: ['确定'] //按钮
				})
			}
}
/**
 * 跳转检查项目
 */
function checkItemDataFun(index){
	checkItemIsNotNull();
	var checkfalg =	checkFormValue('dataListForm');
	if(checkfalg&&flag){
	var postData = collectData("dataListForm");
		postData=$.extend(postData,{"chk_type":chk_type});
		layer.open({type: 2});//添加进度条
			$.ajax({
				url:getRequestUrl("/PrePlanInfoController/updatePrePlanPropMachineInfo.json"),
				dataType:"json",
				data:postData,
				async: false,
				success:function(result){
					layer.closeAll('loading');//关闭进度条
					queryDataList();
					if(result.opflag){
						lalert("save","success",function(){
							pageForward('/pm/mobile/m_preplaninfo/prop_checkitem_list.jsp?id='+id+"&prop_id="+list[index].prop_plan_id);
						});					
						
					}else{				
						lalert("save","error");
					}
					
				},
				error:function(error){
					layer.closeAll('loading'); //关闭进度条
					alert("error");
				}
			});
	}
}

function saveForm(){
	checkItemIsNotNull();
	var checkfalg =	checkFormValue('dataListForm');
	if(checkfalg&&flag){
		layer.open({
			content: '确定提交',
		    btn: ['确定','取消'], //按钮
		    yes: function() {
				var postData = collectData("dataListForm");
				postData=$.extend(postData,{"chk_type":chk_type});
				 layer.open({type: 2});//添加进度条
				$.ajax({
					url:getRequestUrl("/PrePlanInfoController/updatePrePlanPropMachineInfo.json"),
					dataType:"json",
					data:postData,
					async: false,
					success:function(result){
						layer.closeAll('loading');//关闭进度条
						queryDataList();
						if(result.opflag){
							lalert("save","success",function(){
								queryDataList();
							});					
						}else{				
							lalert("save","error");
						}
					},
					error:function(error){
						layer.closeAll('loading'); //关闭进度条
						alert("error");
					}
				});
	        }
			
		})
	}
}
/**
 * 保存项目名称
 */
function chkSaveForm(){
	checkItemIsNotNull();
	for(var i=0;i<list.length;i++){
		if(flag&&Class.getConstant('EDITTYPE_UPDATE')==list[i].editType&&$('#machineNm'+i+'').val()==""){
			layer.open({
					content: '若检查项目名称被删除，相应的数据也将被删除，不能恢复！',
				    btn: ['确定','取消'], //按钮
					yes: function(){
						saveForm();
						return;
					}
				})
			}else{
				saveForm();
				return;
			}
	}
}