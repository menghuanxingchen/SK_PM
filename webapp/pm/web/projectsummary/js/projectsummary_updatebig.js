$(function(){
	menu('menu81');
	initPage();
});
var id = JSON.parse(obj)[0].id;
var count=0;
/**
 * 初始化页面
 * */
function initPage(){
	$.ajax({
		url:getRequestUrl("/ProjectSummaryController/beforeUpdateEntity.json"),
		dataType:"json",
		data:{"projectSummaryInfo.project_id":id},
		async:false,
		success:function(data){
			$("#dataform").html($("#formTemplate").render(data.projectSummaryInfo)); //计划实体-计划名
			$("#addRowsTable").html($("#formListTemplate").render(data.projectSummaryDetailList)); //计划实体-计划名
			count = data.projectSummaryDetailList.length;
			for(var i=0;i<count;i++){
				var date = {
						  elem: '#date'+ i + '',
						  istoday: true,
						 // min: laydate.now(+1), //最大日期
						  isclear: false, //是否显示清空
						  istoday: false //是否显示今天
						};
				laydate(date);
			}
			
			bindevent();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});

}
/**
 * 绑定事件
 */
function bindevent(){
	var planStartDate = {
			  elem: '#planStartDate',
			  istoday: true,
			 // min: laydate.now(+1), //最大日期
			  isclear: false, //是否显示清空
			  istoday: true //是否显示今天
			};
	laydate(planStartDate);
	var planEndDate = {
			  elem: '#planEndDate',
			  istoday: true,
			 // min: laydate.now(+1), //最大日期
			  isclear: false, //是否显示清空
			  istoday: true //是否显示今天
			};
	laydate(planEndDate);
	var realEndDate = {
			  elem: '#realEndDate',
			  istoday: true,
			 // min: laydate.now(+1), //最大日期
			  isclear: false, //是否显示清空
			  istoday: true //是否显示今天
			};
	laydate(realEndDate);
	
	$("#closeBtn").bind('click',function(){
		pageForward('/ProjectSummaryController/defaultJsp.do');
	});
	
	$("#submitBtn").bind('click',function(){
		saveForm();
	});
}


function saveForm(){
	$("#submitBtn").unbind('click');
	var checkfalg =	checkFormValue('formId');
	if(checkfalg){
		var message = "确认提交？";
		var postData = collectData("formId");
		layer.confirm(
				message,
				function(index){
					layer.load();//添加进度条
					$.ajax({
						url:getRequestUrl("/ProjectSummaryController/updateEntityData.json"),
						dataType:"json",
						data:postData,
						success:function(result){
							layer.closeAll('loading'); //关闭进度条
							if(result.opflag){
								lalert('save','success',function(){
									pageForward('/ProjectSummaryController/defaultJsp.do');
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
					layer.close(index);
				},
				function(index){
					layer.close(index);
					return;
				}
		);
	}
}

/**
 * 添加选项调用方法
 */

function addItem(){
	var str =   "<tr id='addr"+count+"'> "
				+ "  <td><input class='laydate-icon w110' type='text' name='projectSummaryDetailList["+count+"].work_date' id='date"+ count + "' dc=''/></td> "
				+ "  <td><input type='text' name='projectSummaryDetailList["+count+"].work_content' dc='true' maxlength='1000'/></td>  "
				+ "  <td><a href='javascript:deleteItem("+count+");' class='btn3' >删除</a></td>   "
				+ "</tr> ";
	$(str).appendTo("#addRowsTable")
	var date = {
			  elem: '#date'+ count + '',
			  istoday: true,
			 // min: laydate.now(+1), //最大日期
			  isclear: false, //是否显示清空
			  istoday: false //是否显示今天
			};
	laydate(date);
	$("#date"+ count + "").val(laydate.now());
	bindValidateEvent('addRowsTable');
	count++;
}

/**
 * 删除选项调用方法
 */
function deleteItem(str){
	$("#addr"+str).remove();
}

function changeBigProNm(projectNm,projectType){
	if(projectType==1){
		layer.confirm('大项目名称修改，同一大项目下的项目名称都将被修改！',function(index){
			layer.close(index);
			return;
		}, function(){
			$("#projectNm").val(projectNm);
		})
	}
	
}