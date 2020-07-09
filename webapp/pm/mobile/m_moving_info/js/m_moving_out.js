$(function(){
	//页面初始化的事件
	initPage();  	
	
});

function getProcessorId(){
	var commonData = parent.CsGlobalObj||parent.parent.CsGlobalObj;
	var id =commonData.currentUserid||"";
	return id;
}


function getProcessorName(){
	var commonData = parent.CsGlobalObj||parent.parent.CsGlobalObj;
	var name =commonData.sys_hidden_currentUsername||"";
	
	return name;
}
function getProcessordept(){
	var commonData = parent.CsGlobalObj||parent.parent.CsGlobalObj;
	var dept =commonData.sys_hidden_dept_name||"";

	return dept;
}
var currentUserName =getProcessorName();
var currentUserDept =getProcessordept();
var currentUserid =getProcessorId();

var id = getRequestParameterValue("id");
function initPage(){	
	bindevent();
	//默认填写其他锁定
	$("#moutGoalContent").attr("disabled","disabled");
	
	 $('input[type=radio][name=moutGoalType]').change(function() {
	
          if (this.value == '其他') {
        	  $("#moutGoalContent").removeAttr("disabled");
         }
          else {
        	  $("#moutGoalContent").attr("disabled","disabled");
         }
	});
	 
}

function bindevent(){	
	jeDate('#moutTime',{
		onClose:false,
        format: 'YYYY-MM-DD hh:mm'
    });
	
	var movingNo = getUrlParam("movingNo");
	var movingPerson = getUrlParam("movingPerson");
	var movingPhone = getUrlParam("movingPhone");
	var movingCarno = getUrlParam("movingCarno");
	var movingCompany = getUrlParam("movingCompany");

	$("#skApplyName").val(currentUserName)
	$("#skApplyDept").val(currentUserDept)
	
	if(movingNo!="" && movingNo!=null){
		movingNo = movingNo.substr(1);
		$("#moutNo").val("C"+movingNo)
		$("#moutName").val(movingPerson)
		$("#moutCompany").val(movingCompany)
		$("#movingCarno").val(movingCarno)
		$("#moutPhone").val(movingPhone)
		$('#movingCarno').attr("disabled","disabled");//禁用车牌号选择 ，从之前带过来
	}else{
		//生成搬入证编号
		$("#moutNo").val("C"+getNowTime())
		$('#movingCarno').attr("disabled",false);//取消禁用
	}
	
	 $("#dataListForm").hide();
	 $('input[type=radio][name=moutGoalType]').change(function() {
        if (this.value != '仓库提货' && this.value != '空车出厂') {
            $("#spanGoodsType").hide();
            $("#dataListForm").show();
        }else{
        	if(this.value == "仓库提货"){
       		 	$("#spanGoodsType").html("请参考出库单！")
	       	 }else{
	       		 $("#spanGoodsType").html("")
	       	 }
       	 	$("#spanGoodsType").show();
            $("#dataListForm").hide();
        }
     });
	 
	//save
	$("#submitBtn").unbind();
	$("#submitBtn").bind('click',function(){
		saveMovingoutData();
	});
	
	$("#backBtn").unbind();
	$("#backBtn").bind('click',function(){
		goBack();
	});
}

//上传图片
function uploadFile(){
	layer.open({type: 2});
   var formData = new FormData();
   var file = document.getElementById("picfileInfo").files[0];
   formData.append("picInfo", file);

   $.ajax({
	   url: getRequestUrl("/MovingOutController/uploadPicEntityData.json"),
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
function saveMovingoutData(){
	$('#movingCarno').attr("disabled",false);//取消禁用
	var wpmc1 = $('#wpmc1').val()
	var applyPerson1 = $('#applyPerson1').val()
	var applyPerson2 = $('#applyPerson2').val()
	
	//var wpsl1 = $('#wpsl1').val()
	var moutGoalType =$("input[name='moutGoalType']:checked").val();
	if((wpmc1 == "" || wpmc1 == null || wpmc1 == "undefined") && moutGoalType!="空车出厂" && moutGoalType!="仓库提货"){
		layer.open({
			content: '请输入物品名称！',
		    btn: ['确定']
		})
	}else if(applyPerson1 != "" || applyPerson2 != ""){
		
		if(applyPerson1 == ""){
			lalert('请选择第一审批人！','error');
		}else if(applyPerson2 == ""){
			lalert('请选择第二审批人！','error');
		}else{
			layer.open({
				content: '确定提交',
			    btn: ['确定','取消'], //按钮
			    yes: function() {
			    	
			    	var moutGoalType =$("input[name='moutGoalType']:checked").val();
			    	var ylbanchuYN = $("input[name='ylbanchuYN']:checked").val();
					
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
						postData=$.extend(postData,{"movingOutInfo.skApplyNo":currentUserid,"movingOutInfo.moutGoalType":moutGoalType,
							"movingOutInfo.applyPerson1":applyPerson1,"movingOutInfo.applyPerson2":applyPerson2,
							"movingOutInfo.ylbanchuYN":ylbanchuYN,"movingOutInfo.json_info":jsonStr,"movingOutInfo.picInfo":$("#picInfo").val()});
						
					  
						layer.open({type: 2});//添加进度条
						$.ajax({
							url:getRequestUrl("/MovingOutController/saveTemporaryEntityData.json"),
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
		
	}
	/*else if((wpsl1 == "" || wpsl1 == null || wpsl1 == "undefined") && moutGoalType!="空车出厂"){
		layer.open({
			content: '请输入物品数量！',
		    btn: ['确定']
		})
	}*/
	else{
		layer.open({
			content: '确定提交',
		    btn: ['确定','取消'], //按钮
		    yes: function() {
		    	
		    	var moutGoalType =$("input[name='moutGoalType']:checked").val();
		    	var ylbanchuYN = $("input[name='ylbanchuYN']:checked").val();
				
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
					postData=$.extend(postData,{"movingOutInfo.skApplyNo":currentUserid,"movingOutInfo.moutGoalType":moutGoalType,
						"movingOutInfo.applyPerson1":applyPerson1,"movingOutInfo.applyPerson2":applyPerson2,
						"movingOutInfo.ylbanchuYN":ylbanchuYN,"movingOutInfo.json_info":jsonStr,"movingOutInfo.picInfo":$("#picInfo").val()});
					
				  
					layer.open({type: 2});//添加进度条
					$.ajax({
						url:getRequestUrl("/MovingOutController/saveTemporaryEntityData.json"),
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
}
/**
 * 跳转页
 */
function goBack(){
	window.history.back(-1);
}

/**
 * 获取url参数
 */
function getUrlParam(name) {
	  var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)");
      var result = window.location.search.substr(1).match(reg);
      return result?decodeURIComponent(result[2]):null;
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