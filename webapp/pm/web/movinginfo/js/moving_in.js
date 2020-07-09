$(function(){
	menu('menuF11');
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){

	jeDate('#minTime',{
		onClose:false,
        format: 'YYYY-MM-DD hh:mm'
    });
	
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
	 
	 bindevent();
}

/**
 * 绑定事件
 */
function bindevent(){
	
	var movingNo = getUrlParam("movingNo");

	if(movingNo != "" && movingNo!=null){
		
		$.ajax({
			url:getRequestUrl("/MovingListController/queryMovingInDataList.json"),
			dataType:"json",
			data:{"movingInfo.movingNo":movingNo},
			success:function(result){
				$("#minNo").val(result.data.minNo)
				$("#minName").val(result.data.minName)
				$("#minCompany").val(result.data.minCompany)
				$("#minCarno").val(result.data.minCarno)
				$("#minPhone").val(result.data.minPhone)
				$("#minTime").val(result.data.minTime)
				$("#minGoalContent").val(result.data.minGoalContent)
				
				$("input[name='minGoalType'][value="+result.data.minGoalType+"]").attr("checked",true); 
				$("input[name='moutYN'][value="+result.data.moutYN+"]").attr("checked",true); 
			},
			error:function(error){
				lalert('网络原因操作失败！','error');
			}
		});
		
	}else{
		//生成搬入证编号
		$("#minNo").val("R"+getNowTime())
	}

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

function saveMovingInData(){
	var skContactsName = $("#deal_user_id2").find("option:selected").text(); 
	var minGoalType =$("input[name='minGoalType']:checked").val();
	var moutYN = "Y";//$("input[name='moutYN']:checked").val();
	
	var checkfalg =	checkFormValue('formId');
		if(checkfalg){
			layer.confirm("是否提交", {
			    btn: ['确定','取消'] //按钮
			}, function(){
				
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
				
				var postData = collectData("formId");
				postData=$.extend(postData,{"movingInInfo.skContactsName":skContactsName,"movingInInfo.minGoalType":minGoalType,
					"movingInInfo.moutYN":moutYN,"movingInInfo.json_info":jsonStr,"movingInInfo.picInfo":$("#picInfo").val()});
				
			  
				layer.load();//添加进度条
				$.ajax({
					url:getRequestUrl("/MovingInController/saveTemporaryEntityData.json"),
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

/**
 * 获取url参数
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return ""; //返回参数值
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