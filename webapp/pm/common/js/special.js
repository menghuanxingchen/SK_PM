
	
function pop(startId,popDiv){
	$("#"+startId).click(function(){
	$("#"+popDiv).fadeIn();
	});

	$("body").bind("click",function(e){
		var target = $(e.target);
		if(target.closest(".selbox").length == 0 && target.closest("#"+popDiv).length == 0 && target.closest("#"+startId).length == 0){//���idΪccc֮��ĵط�����
			$("#"+popDiv).fadeOut();
	  }
	})
	
}
/**
* 关闭扩展
* @param popDiv
*/
function colsePopDiv(popDiv){
	$("#"+popDiv).fadeOut();
}

function tab(){
	 $(".tab_content").hide(); 
	 /*$("div.tabList ul li:first").addClass("cur").show(); */
	 $(".tab_content:first").show();
	 $("div.tabList ul li").click(function(){
		$("div.tabList ul li").removeClass("cur"); 
        $(this).addClass("cur");  
        var activeTab = $(this).attr("dv");
		var active = $(this).attr("class");
        $(".tab_content").hide();
        $("#"+activeTab).show();
	});
}

function tab22(){
	 $(".tab_content2").hide(); 
	 /*$("div.tabList ul li:first").addClass("cur").show(); */
	 $(".tab_content2:first").show();
	 $("div.tabList2 ul li").click(function(){
		$("div.tabList2 ul li").removeClass("cur2"); 
        $(this).addClass("cur2");  
        var activeTab2 = $(this).attr("dv2");
		var active = $(this).attr("class");
        $(".tab_content2").hide();
        $("#"+activeTab2).show();
	});
}

function tab33(){
	 $(".tab_content2").hide(); 
	 /*$("div.tabList ul li:first").addClass("cur").show(); */
	 $(".tab_content2:first").show();
	 $("div.tabList2 ul li").click(function(){
		$("div.tabList2 ul li").removeClass("cur3"); 
        $(this).addClass("cur3");  
        var activeTab2 = $(this).attr("dv2");
		var active = $(this).attr("class");
        $(".tab_content2").hide();
        $("#"+activeTab2).show();
	});
}

function addTableDiv(tableId,distinguish,popId){
	var width = 0;
	$("#"+tableId+" colgroup col").each(function(e){
			var tdWidth = $(this).attr("width");
			if(tdWidth == ''){
				$(this).attr("width","100px");
			}
			width += parseInt($(this).attr("width").replace("px",""));
	});
	//�Ƴ�������
	$("#"+tableId).parent(".scrollbox").replaceWith( function(){
				return $(this).contents();
	} );  
	//��ӹ�����
	if(distinguish){
		var pageWidth = $(".rightinfo").width();
		if(width > pageWidth ){
			$("#"+tableId).wrap('<div class="scrollbox"></div>');
		}
	}else{
		var popWidth = $(".layui-layer").width();
		if(width > popWidth){
			
			$("#"+tableId).wrap('<div class="scrollbox"></div>');
		}
	}

}

function changeCss(){
	var h=(document.getElementById("regular").offsetHeight); 
	$(".rightinfo").css({"padding-top":h});
	$("div h3:first-child").css({"margin-top":"0px"});
}

function mtH3(){
}