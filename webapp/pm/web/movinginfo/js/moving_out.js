$(function(){
	menu('menuF12');
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

/**
 * 初始化页面
 * */
function initPage(){

	jeDate('#moutTime',{
		 isinitVal:true,
        format: 'YYYY-MM-DD hh:mm'
    });
	
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
	 
	bindevent();
	
}

/**
 * 绑定事件
 */
function bindevent(){
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
		
		$('#moutName').attr("readonly","readonly")//将input元素设置为readonly
		$('#moutCompany').attr("readonly","readonly")//将input元素设置为readonly
		$('#movingCarno').attr("readonly","readonly")//将input元素设置为readonly
		$('#moutPhone').attr("readonly","readonly")//将input元素设置为readonly
	}else{
		//生成搬入证编号
		$("#moutNo").val("C"+getNowTime())
		$('#moutName').removeAttr("readonly");//去除input元素的readonly属性
		$('#moutCompany').removeAttr("readonly");//去除input元素的readonly属性
		$('#movingCarno').removeAttr("readonly");//去除input元素的readonly属性
		$('#moutPhone').removeAttr("readonly");//去除input元素的readonly属性
	}
	
	$("#divGoodsType").hide();
	 $('input[type=radio][name=moutGoalType]').change(function() {
         if (this.value != '仓库提货' && this.value != '空车出厂') {
             $("#spanGoodsType").hide();
             $("#divGoodsType").show();
         }else{
        	 if(this.value == "仓库提货"){
        		 $("#spanGoodsType").html("请参考出库单！")
        	 }else{
        		 $("#spanGoodsType").html("")
        	 }
        	 $("#spanGoodsType").show();
             $("#divGoodsType").hide();
         }
      });
	/*$.ajax({
		url:getRequestUrl("/MovingOutController/beforeDataList.json"),
		dataType:"json",
		data:[],
		success:function(data){
			
			alert(data.processor.currentUserName);
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});*/
}


/**
 * 添加表格
 */
var count=2;
function addMoveInfoData(){
	var index = $("#tabgoods").find("tr").length
	var tabtr = " <tr id='addr"+count+"'><td>"+index+"</td><td><input  type='text' placeholder='请输入物品名称' dc=''/></td>" +
			"<td><input  type='text' placeholder='请输入数量' dc=''/></td>" +
			"<td><a href='javascript:deleteInfoData("+count+");'  >删除</a></td></tr>";
	$("#tabgoods").append(tabtr);
	count++;
}


/**
 * 删除表格
 */

function deleteInfoData(str){
	
	$("#addr"+str).remove();
	
	addNum();
}


function addNum(){
	 
    var len = $("#tabgoods").find("tr").length
    for(var i = 1;i<len;i++){
        $('#tabgoods tr:eq('+i+') td:first').text(i);
    }
}

//上传图片
function uploadFile(){
   layer.load();//添加进度条
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

function saveMovingOutData(){
	
	var wpmc1 = $('#wpmc1').val()
	//var wpsl1 = $('#wpsl1').val()
	var moutGoalType =$("input[name='moutGoalType']:checked").val();
	var ylbanchuYN = $("input[name='ylbanchuYN']:checked").val();
	
	var applyPerson1 = $('#applyPerson1').val()
	var applyPerson2 = $('#applyPerson2').val()
	
	if((wpmc1 == "" || wpmc1 == null || wpmc1 == "undefined") && moutGoalType!="空车出厂" && moutGoalType!="仓库提货"){
		lalert('请输入物品名称！','error');
	}else if(applyPerson1 != "" || applyPerson2 != ""){
		
		if(applyPerson1 == ""){
			lalert('请选择第一审批人！','error');
		}else if(applyPerson2 == ""){
			lalert('请选择第二审批人！','error');
		}else{
			var checkfalg =	checkFormValue('formId');
			if(checkfalg){
				layer.confirm("是否提交", {
				    btn: ['确定','取消'] //按钮
				}, function(){
					
					var jsonStr= "[";
					for (var i = 1; i < $("#tabgoods tr").length; i++) {
				
						var goodsName = $("#tabgoods tr:eq(" + i + ")").children().eq(1).find("input").val();
						if(goodsName != "" && goodsName!=null){
							 jsonStr += "{\"goodsName\":\"" + goodsName + "\",";
				             var goodsCount = $("#tabgoods tr:eq(" + i + ")").children().eq(2).find("input").val();
				             jsonStr += "\"goodsCount\":\"" + goodsCount + "\"},";
						}
					}
					 jsonStr = jsonStr.substring(0, jsonStr.length - 1) + "]";
					
					var postData = collectData("formId");
					postData=$.extend(postData,{"movingOutInfo.skApplyNo":currentUserid,"movingOutInfo.moutGoalType":moutGoalType,
						"movingOutInfo.applyPerson1":applyPerson1,"movingOutInfo.applyPerson2":applyPerson2,
						"movingOutInfo.ylbanchuYN":ylbanchuYN,"movingOutInfo.json_info":jsonStr,"movingOutInfo.picInfo":$("#picInfo").val()});
					
					
					layer.load();//添加进度条
					$.ajax({
						url:getRequestUrl("/MovingOutController/saveTemporaryEntityData.json"),
						dataType:"json",
						data:postData,
						success:function(result){
							layer.closeAll('loading'); //关闭进度条
							if(result.opflag){
								lalert('save','success',function(){
									pageForward('/MovingListController/defaultJsp.do');
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
		
	}else{
		var checkfalg =	checkFormValue('formId');
		if(checkfalg){
			layer.confirm("是否提交", {
			    btn: ['确定','取消'] //按钮
			}, function(){
				
				var jsonStr= "[";
				for (var i = 1; i < $("#tabgoods tr").length; i++) {
			
					var goodsName = $("#tabgoods tr:eq(" + i + ")").children().eq(1).find("input").val();
					if(goodsName != "" && goodsName!=null){
						 jsonStr += "{\"goodsName\":\"" + goodsName + "\",";
			             var goodsCount = $("#tabgoods tr:eq(" + i + ")").children().eq(2).find("input").val();
			             jsonStr += "\"goodsCount\":\"" + goodsCount + "\"},";
					}
				}
				 jsonStr = jsonStr.substring(0, jsonStr.length - 1) + "]";
				
				var postData = collectData("formId");
				postData=$.extend(postData,{"movingOutInfo.skApplyNo":currentUserid,"movingOutInfo.moutGoalType":moutGoalType,
					"movingOutInfo.applyPerson1":applyPerson1,"movingOutInfo.applyPerson2":applyPerson2,
					"movingOutInfo.ylbanchuYN":ylbanchuYN,"movingOutInfo.json_info":jsonStr,"movingOutInfo.picInfo":$("#picInfo").val()});
				
				
				layer.load();//添加进度条
				$.ajax({
					url:getRequestUrl("/MovingOutController/saveTemporaryEntityData.json"),
					dataType:"json",
					data:postData,
					success:function(result){
						layer.closeAll('loading'); //关闭进度条
						if(result.opflag){
							lalert('save','success',function(){
								pageForward('/MovingListController/defaultJsp.do');
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