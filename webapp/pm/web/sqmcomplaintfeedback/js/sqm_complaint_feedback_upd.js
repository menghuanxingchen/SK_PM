var id = getRequestParameterValue("id");
menu('menuE1');
$(function(){
    initPage();
	bindevent();
	var start = {
			  elem: '#cf_date',
  			  choose: function(datas){
  				$('#cf_date').attr("value",datas);
   			  }
			};
	laydate(start);
});


//初始化页面
function initPage(){
    $.ajax({
        url:getRequestUrl("/SqmComplaintFeedbackController/beforeDataList.json"),
        dataType:"json",
        data:[],
        success:function(data){
            //$( "#ListForm" ).html(
            //      $("#ListTemplate").render(data)
            //);

            queryData();
            bindevent();
        },
        error:function(error){
            lalert('网络原因操作失败！','error');
        }
    });
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

//数据显示
function queryData(){
    if(id == undefined || id==''){
        //初始化页面制御
        initPageTame(new Object());
        return;
    }

    layer.load();//添加进度条
    $.ajax({
        url:getRequestUrl("/SqmComplaintFeedbackController/queryBeforeUpdate.json"),
        dataType:"json",
        data:{"sqmComplaintFeedback.id":id},
        success:function(result){
            layer.closeAll('loading'); //关闭进度条
            if(result.opflag){
                dataRead("formId",result.dataInfo);

                //初始化页面制御
                initPageTame(result);
            }
        },
        error:function(error){
            layer.closeAll('loading'); //关闭进度条
            lalert('网络原因操作失败！','error');
        }
    });
}

//初始化页面制御
function initPageTame(result){
    bindValidateEvent('formId');
}

//提交执行
function saveForm(){
    var checkfalg = checkFormValue('formId');
    if(checkfalg){
        layer.confirm("是否提交?", {
            btn: ['确定','取消'] //按钮
        }, function(){
            var postData = collectData("formId");
            layer.load();//添加进度条
            $.ajax({
                    url:getRequestUrl("/SqmComplaintFeedbackController/updEntityData.json"),
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
        })
    }
}
//pop打开
function popOpenPage(){
    //var obj = new Object();
    //pagePop('titleName',obj,'xxx.jsp');
}
//pop回调
function popCallBack(popObj){
    //$('input[name="entity.name"]').val(popObj);
}

