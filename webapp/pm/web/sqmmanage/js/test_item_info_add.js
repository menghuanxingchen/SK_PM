$(function(){
	menu('menuB11');
	initPage();
});

//初始化页面
function initPage(){
    $.ajax({
        url:getRequestUrl("/TestItemInfoController/beforeDataList.json"),
        dataType:"json",
        data:[],
        success:function(data){
            $( "#lineListForm" ).html(
                  $("#lineListTemplate").render(data)
            );

           
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
	
	bindValidateEvent("formId");
	$("#closeBtn").bind('click',function(){
		pageForward('/TestItemInfoController/defaultJsp.do');
	});
	
	$("#submitBtn").bind('click',function(){
		saveForm();
	});
	
}


//提交前check
function checkListValue(){
	var productCode = $("#productCode").val();

	
	var lineList = $("#lineList").val();

	if(productCode!="" && productCode != null  && lineList!="" && lineList != null){
		return true;
	}else{
		return false;
	}
}

//提交执行
function saveForm(){
	var checkfalg =	checkFormValue('formId') && checkListValue();
	if(checkfalg){
	   layer.confirm("是否提交?", {
           btn: ['确定','取消'] //按钮
       }, function(){
           var postData = collectData("formId");
           layer.load();//添加进度条
           $.ajax({
                   url:getRequestUrl("/TestItemInfoController/addEntityData.json"),
                   dataType:"json",
                   data:postData,
                   success:function(result){
                       layer.closeAll('loading'); //关闭进度条
                       if(result.opflag){
                           lalert('save','success',function(){
                               pageForward('/TestItemInfoController/defaultJsp.do');
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
	}else{
		lalert('生产线或产品code不能为空！','error');
	}
}

