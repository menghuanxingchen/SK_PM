var flag = getRequestParameterValue("flag");

$(function(){
	
	initPage();
	queryDetailDataEntity();
});

/**
 * 初始化页面
 * */
function initPage(){
	
	var movingNo = getUrlParam("movingNo");
	
	$.ajax({
		url:getRequestUrl("/MovingListController/queryDownListForProposal.json"),
		dataType:"json",
		data:{"movingInfo.movingNo":movingNo},
		success:function(result){

			$("#user_id1").append("<option value='"+result.movingInInfo.skContactsNo+"' >"+result.movingInInfo.skContactsName+"</option>");
	       // $("#user_id2").append("<option value='"+result.sysUserInfoList[0].user_num+"' >"+result.sysUserInfoList[0].user_nm+"</option>");
	        
			
			$("#app_sta1").html(result.appuser1.approval_nm);
			/*$("#app_sta2").html(result.appuser2.approval_nm);
			$("#app_sta3").html(result.appuser3.approval_nm);*/
			$("#app_sta4").html(result.appuser4.approval_nm);
			
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
	
	
	//默认填写其他锁定
	$("#minGoalContent").attr("disabled","disabled");
	
	$("#minGoalType").attr("disabled","disabled");
	$("#moutYN").attr("disabled","disabled");
	
	
	bindevent();
	picbig();
}

function picbig(){
	var imgsObj = $('.amplifyImg img');//需要放大的图像
    if(imgsObj){
        $.each(imgsObj,function(){
            $(this).click(function(){
                var currImg = $(this);
                coverLayer(1);
                var tempContainer = $('<div class=tempContainer></div>');//图片容器
                with(tempContainer){//width方法等同于$(this)
                    appendTo("body");
                    var windowWidth=$(window).width()/2;
                    var windowHeight=$(window).height()/2;
                    //获取图片原始宽度、高度
                    var orignImg = new Image();
                    orignImg.src =currImg.attr("src") ;
                    var currImgWidth= orignImg.width;
                    var currImgHeight = orignImg.height;
                    if(currImgWidth<windowWidth){//为了让图片不失真，当图片宽度较小的时候，保留原图
                        if(currImgHeight<windowHeight){
                            var topHeight=(windowHeight-currImgHeight)/2;
                            if(topHeight>35){/*此处为了使图片高度上居中显示在整个手机屏幕中：因为在android,ios的微信中会有一个title导航，35为title导航的高度*/
                                topHeight=topHeight-35;
                                css('top','100px');
                            }else{
                                css('top','100px');
                            }
                            html('<img border=0 src=' + currImg.attr('src') + '>');
                        }else{
                            css('top',0);
                            html('<img border=0 src=' + currImg.attr('src') + ' height='+windowHeight+'>');
                        }
                    }else{
                        var currImgChangeHeight=(currImgHeight*windowWidth)/currImgWidth;
                        if(currImgChangeHeight<windowHeight){
                            var topHeight=(windowHeight-currImgChangeHeight)/2;
                            if(topHeight>35){
                                topHeight=topHeight-35;
                                css('top','100px');
                            }else{
                                css('top','100px');
                            }
                            html('<img border=0 src=' + currImg.attr('src') + ' width='+windowWidth+';>');
                        }else{
                            css('top','100px');
                            html('<img border=0 src=' + currImg.attr('src') + ' width='+windowWidth+'; height='+windowHeight+'>');
                        }
                    }
                }
                tempContainer.click(function(){
                    $(this).remove();
                    coverLayer(0);
                });
            });
        });
    }
    else{
        return false;
    }
    //使用禁用蒙层效果
    function coverLayer(tag){
        with($('.over')){
            if(tag==1){
                css('height',$(document).height());
                css('display','block');
                css('opacity',1);
                css("background-color","#FFFFFF");
                css("background-color","rgba(0,0,0,0.7)" );  //蒙层透明度
            }
            else{
                css('display','none');
            }
        }
    }
}

/**
 * 绑定事件
 */
function bindevent(){
	
	var movingNo = getUrlParam("movingNo");
	
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
			$("#skContactsName").val(result.data.skContactsName)
			$("#minTime").val(result.data.minTime)
			$("#minGoalContent").val(result.data.minGoalContent)
			
			if(result.data.picInfo != ""&&result.data.picInfo != null){
				$("#picInfo").attr('src',result.data.picInfo);
			}else{
				$("#picInfo").hide();
			}
			
			$("#app_time1").html(result.data.apply_date)
			/*$("#app_time2").html(result.data.approval_date)
			$("#app_time3").html(result.data.factory_manager_approval_date)*/
			$("#app_time4").html(result.data.confirm_date)
			
			$("input[name='minGoalType'][value="+result.data.minGoalType+"]").attr("checked",true); 
			$("input[name='moutYN'][value="+result.data.moutYN+"]").attr("checked",true); 
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});

	getMovingInfoList(movingNo);
}

/**
 * 获取物品list
 */
function getMovingInfoList(movingNo){
	$.ajax({
		url:getRequestUrl("/MovingListController/queryMovingInDataList.json"),
		dataType:"json",
		data:{"movingInfo.movingNo":movingNo},
		success:function(data){
			var strth="";
			
			for(var x=0;x<data.movingGoodsList.length;x++){
				
				//生成表头内容
				strth = strth + "<tr><td>"+(x+1)+"</td><td>"+data.movingGoodsList[x].goodsName+"</td><td>"+data.movingGoodsList[x].goodsCount+"</td></tr>"
			}

			$("#t_r_content").append(strth);
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}


/**
 * 详情按钮
 * @param id
 */
function queryDetailDataEntity(){

	if(flag=='1'){
		$("#approve_check").show();
		$("#reject_check").show();
		$("#confirm_check").hide();
		if($("#app_sta3").text() == "已批准"){
			$("#reject_check").hide();
			$("#approve_check").text("确认")
		}
	}else if(flag=='2'){
		$("#confirm_check").show();
		$("#approve_check").hide();
		$("#reject_check").hide();
	}else{
		$("#approve_check").hide();
		$("#reject_check").hide();
		$("#confirm_check").hide();
	}
}


/**
 * 审批/驳回
 */
function updateApproveAndReject(str){
	var message = "";
	var movingNo = getUrlParam("movingNo");
	if(str=='R'){
		message = "是否驳回？";
		updateProposalInfoApproveAndReject(movingNo,message,str);
	}else if(str=='A'){
		message = "是否审批？";
		updateProposalInfoApproveAndReject(movingNo,message,str);
	}else{
		message = "是否确认？";
		updateProposalInfoApproveAndReject(movingNo,message,str);
	}
}


/**
 * 审批驳回
 */
function updateProposalInfoApproveAndReject(movingNo,message,str){
	layer.confirm(
		message,
		function(index){
			layer.load('loading');//添加进度条
			$.ajax({
				url:getRequestUrl("/MovingListController/updateProposalInfoApproveAndReject.json"),
				dataType:"json",
				data:{"movingInfo.movingNo":movingNo,"updateflag":str},
				success:function(result){
					layer.close(index);
					layer.closeAll('loading');
					lalert('save','success',function(){
						 var page = "/pm/web/movinginfo/moving_list.jsp";
						 pageForward(page);
					});
				},
				error:function(error){
					layer.close(index);
					layer.closeAll('loading');
					lalert('网络原因操作失败！','error');
				}
			});
		},
		function(index){
			layer.close(index);
			return;
		}
	);
}
/**
 * 获取url参数
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

