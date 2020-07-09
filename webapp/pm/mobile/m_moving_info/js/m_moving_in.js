var TYPE = getRequestParameterValue("TYPE");
var user_num = getRequestParameterValue("sysUserInfo.user_num");
var pwd = getRequestParameterValue("sysUserInfo.pwd");
$(function(){
	if(TYPE=='TEMPORARY'){
		login();
	}
		initPage();
});
function login(){
	var postData = {"sysUserInfo.user_num":user_num,"sysUserInfo.pwd":pwd};
	    postData=$.extend(postData,{"sysUserInfo.loginType":TYPE});
	$.ajax({
		url:path+"/loginManagerController/checkSysUserInfo.json",
		dataType:"json",
		data:postData,
		success:function(result){
			if(result.opflag){
				if(result.sysUserinfo){
					initPage();
				}
			}else{
				lalert(result.opmessage,'error');
			}
		},
		error:function(error){
			lalert('网络原因操作失败','error');
		}
	});
}


var id = getRequestParameterValue("id");
function initPage(){	
	bindevent();
	//默认填写其他锁定
	$("#minGoalContent").attr("disabled","disabled");
	
	 $('input[type=radio][name=minGoalType]').change(function() {
	
          if (this.value == '其他') {
        	  $("#minGoalContent").removeAttr("disabled");
         }
          else {
        	  $("#minGoalContent").attr("disabled","disabled");
         }
	});
	 
}

function bindevent(){	
	jeDate('#minTime',{
		onClose:false,
        format: 'YYYY-MM-DD hh:mm'
    });
	
	$("#minNo").val("R"+getNowTime())
	
	$.ajax({
		url:getRequestUrl("/MovingListController/queryDownList.json"),
		dataType:"json",
		data:"",
		success:function(result){
			$("#userChoose").html($("#allUserGroupSubDownListTemplate2").render(result));
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
	
	//save
	$("#submitBtn").unbind();
	$("#submitBtn").bind('click',function(){
		saveMovingInData();
	});
}

//上传图片
function uploadFile(){
	layer.open({type: 2});
   var formData = new FormData();
   var file = document.getElementById("picfileInfo").files[0];
   formData.append("picInfo", file);

   $.ajax({
	   url: getRequestUrl("/MovingInController/uploadPicEntityData.json"),
	   type: 'POST',
	   dataType:"json",
	   cache: false,
	   data: formData,
	   processData: false,
	   contentType: false,
	   success:function(result){
		   $("#picInfo").val(result.filePath)
		   layer.closeAll('loading'); //关闭进度条
		   lalert('upload','success',function(){
			});
		},
		error:function(error){
			layer.closeAll('loading'); //关闭进度条
			lalert('网络原因上传失败！','error');
		}
   });
}

/**
 * save 方法
 */
function saveMovingInData(){

	layer.open({
		content: '确定提交',
	    btn: ['确定','取消'], //按钮
	    yes: function() {
	    	
	    	var skContactsName = $("#deal_user_id2").find("option:selected").text(); 
			var minGoalType =$("input[name='minGoalType']:checked").val();
			var moutYN = "Y";//$("input[name='moutYN']:checked").val();
			
			var checkfalg =	checkFormValue('com_body');
				if(checkfalg){
		    	
		    	var jsonStr= "[";
				for (var i = 1; i < $("#tabgoods tr").length; i++) {
					var goodsName = $("#tabgoods tr:eq(" + i + ")").children().eq(1).find("input").val();
					if(goodsName !="" && goodsName != null){
						 jsonStr += "{\"goodsName\":\"" + goodsName + "\",";
			             var goodsCount = $("#tabgoods tr:eq(" + i + ")").children().eq(2).find("input").val();
			             jsonStr += "\"goodsCount\":\"" + goodsCount + "\"},";
					}
				}
				 jsonStr = jsonStr.substring(0, jsonStr.length - 1) + "]";
				
				var postData = collectData("com_body");
				postData=$.extend(postData,{"movingInInfo.skContactsName":skContactsName,"movingInInfo.minGoalType":minGoalType,
					"movingInInfo.moutYN":moutYN,"movingInInfo.json_info":jsonStr,"movingInInfo.picInfo":$("#picInfo").val()});
				
			  
				layer.open({type: 2});//添加进度条
				$.ajax({
					url:getRequestUrl("/MovingInController/saveTemporaryEntityData.json"),
					dataType:"json",
					data:postData,
					success:function(result){
						layer.closeAll('loading'); //关闭进度条
						if(result.opflag){
							lalert('save','success',function(){
								var page = "/pm/mobile/m_moving_info/m_moving_list.jsp";
								 pageForward(page);
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
			}
	    
	    }
		})
}
/**
 * 跳转页
 */
function goBack(){
	window.history.back(-1);
}

function getNowTime() {
    var date = new Date();
    this.year = date.getFullYear();
    this.month = date.getMonth() + 1;
    this.date = date.getDate();
    this.hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
    this.minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
    this.second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
    this.milliSeconds = date.getMilliseconds();
    var currentTime = this.year+''+this.month + '' + this.date + '' + this.hour + '' + this.minute + '' + this.second + '' + this.milliSeconds;
    return currentTime;
}