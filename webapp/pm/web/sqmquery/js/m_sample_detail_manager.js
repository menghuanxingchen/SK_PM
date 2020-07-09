$(function(){
	initPage();
});

function initPage(){
	
	
	queryDataList();
	picbig();
}

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
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
 * 查询方法
 */
function queryDataList(){

	var pkg = getUrlParam('pkg');
	var prod = getUrlParam('prod');
	var colcnt = getUrlParam('colcnt');
	var revno = getUrlParam('revno');
	
	
	$.ajax({
		url:getRequestUrl("/SqmqueryController/querySampleDetailDataList.json"),
		dataType:"json",
		data:{"sampleInfo.colcnt":colcnt,"sampleInfo.PKG":pkg,"sampleInfo.prod":prod,"sampleInfo.REVNO":revno},
		success:function(data){

			if(data.data.length != 0){
				$("#pkg").val(data.data[0].pkgprod)
				$("#revno").val(data.data[0].revno)
				$("#revdate").val(data.data[0].revdate)
				$("#bigo").val(data.data[0].bigo)
				$("#weight").val(data.data[0].weight)
					
				$("#SAMPLE1").attr('src',"http://114.113.147.106:9090/"+data.data[0].sample1);
				$("#SAMPLE2").attr('src',"http://114.113.147.106:9090/"+data.data[0].sample2);
				$("#SAMPLE3").attr('src',"http://114.113.147.106:9090/"+data.data[0].sample3);
				$("#SAMPLE4").attr('src',"http://114.113.147.106:9090/"+data.data[0].sample4);
			}
			
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
	
	
}

/**
 * 跳转页
 */
function backpage(){
	window.history.back(-1);
}

