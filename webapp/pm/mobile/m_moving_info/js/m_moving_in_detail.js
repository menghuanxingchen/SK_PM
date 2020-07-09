var flag = getRequestParameterValue("flag");
$(function(){
	//页面初始化的事件
	initPage();  	

});

var id = getRequestParameterValue("id");
function initPage(){	
	
	bindevent();
}

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
			$("#minGoalType").val(result.data.minGoalType)
			$("#moutYN").val(result.data.moutYN)
			$("#deal_user_id").val(result.data.deal_user_id)
			
			$("#deal_user_name").val(result.data.deal_user_name)
			$("#approval_state_name").val(result.data.approval_state_name)
			
			if(result.data.picInfo != ""&&result.data.picInfo != null){
				$("#picInfo").attr('src',result.data.picInfo);
			}else{
				$("#picInfo").hide();
			}
			
			queryDetailDataEntity();
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});

	getMovingInfoList(movingNo);
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
				strth = strth + "<tr><td>"+(x+1)+"</td><td >"+data.movingGoodsList[x].goodsName+"</td><td>"+data.movingGoodsList[x].goodsCount+"</td></tr>"
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

	var buttype=""
	if(flag=='1'){
		buttype = "<input type='submit' name='applyok' onClick=\"updateApproveAndReject('A')\" style='width:25%'  class='submit ml30' value='批准'/>  "+
    			  "<input type='submit' name='applyno' onClick=\"updateApproveAndReject('R')\" style='width:25%'  class='submit ml30' value='驳回'/>"+
    			  "<input type='submit' name='backtopage' onClick=\"backpage()\" style='width:25%'  class='submit ml30' value='返回'/>";
		if($("#deal_user_id").val() == "lg00999"){
			buttype = "<input type='submit' name='queren' onClick=\"updateApproveAndReject('C')\" style='width:42%'  class='submit ml30' value='确认'/>"+
			          "<input type='submit' name='backtopage' onClick=\"backpage()\" style='width:42%'  class='submit ml30' value='返回'/>";
		}
	}else if(flag=='2'){
		buttype = "<input type='submit' name='queren' onClick=\"updateApproveAndReject('C')\" style='width:42%' class='submit ml30' value='确认'/>"+
        		  "<input type='submit' name='backtopage' onClick=\"backpage()\" style='width:42%' class='submit ml30' value='返回'/>";
	}else{
		buttype="<input type='submit' name='backtopage' onClick=\"backpage()\" style='width:90%' class='submit ml30' value='返回'/>"
	}
	$("#com_foot").append(buttype);
}

/**
 * 审批/驳回
 */
var message = "";
function updateApproveAndReject(str){
	
	var movingNo = getUrlParam("movingNo");
	if(str=='R'){
		message = "是否驳回？";
		updateProposalInfoApproveAndReject(movingNo,message,str);
	}else if(str=='A'){
		message = "是否批准？";
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
	layer.open({
		content: message,
	    btn: ['确定','取消'], //按钮
	    yes: function() {
			
			$.ajax({
				url:getRequestUrl("/MovingListController/updateProposalInfoApproveAndReject.json"),
				dataType:"json",
				data:{"movingInfo.movingNo":movingNo,"updateflag":str},
				success:function(result){
					
					lalert('save','success',function(){
						 var page = "/pm/mobile/m_moving_info/m_moving_list.jsp";
						 pageForward(page);
					});
				},
				error:function(error){
					
					lalert('网络原因操作失败！','error');
				}
			});
	    }
	})
}

/**
 * 返回
 */
function backpage(){
	window.history.back(-1);
}

/**
 * 获取url参数
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
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