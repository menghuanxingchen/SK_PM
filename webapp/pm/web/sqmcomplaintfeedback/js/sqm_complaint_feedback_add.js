$(function(){
	menu('menuE1');
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
	$.ajax({
		url:getRequestUrl("/SqmComplaintFeedbackController/beforeDataList.json"),
		dataType:"json",
		data:[],
		success:function(data){
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
	bindevent();
	var start = {
			  elem: '#cf_date',
  			  choose: function(datas){
  				$('#cf_date').attr("value",datas);
   			  }
			};
	laydate(start);
}
/**
 * 绑定事件属性
 * */
function bindevent(){
	
	$("#closeBtn").bind('click',function(){
		pageForward('/SqmComplaintFeedbackController/defaultJsp.do');
	});
	
	$("#submitBtn").bind('click',function(){
		saveForm();
	});
	
}


function saveForm(){
		
	var checkfalg =	checkFormValue('formId');
		if(checkfalg){
			layer.confirm("是否提交", {
			    btn: ['确定','取消'] //按钮
			}, function(){
					var postData = collectData("formId");
					layer.load();//添加进度条
					$.ajax({
							url:getRequestUrl("/SqmComplaintFeedbackController/addEntityData.json"),
							dataType:"json",
							data:postData,
							success:function(result){
								layer.closeAll('loading'); //关闭进度条
								if(result.opflag){
									lalert('save','success',function(){
										pageForward('/SqmComplaintFeedbackController/defaultJsp.do');
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
				
				/*else{
					layer.closeAll();
					$("#submitBtn").bind('click',function(){
						saveForm();
					});
				}*/
		})
	}else{
		lalert('请检查输入内容！','error');
	}
	
}
