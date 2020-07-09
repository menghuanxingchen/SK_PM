<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9">
<meta http-equiv="x-ua-compatible" content="IE=9" >
<link href="<%=request.getContextPath()%>/pm/common/css/style_mobile.css" rel="stylesheet" type="text/css" />
<%-- <link href="<%=request.getContextPath()%>/pm/common/css/HRMobile.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/pm/common/css/mobiscroll.custom-3.0.0-beta2.min.css" rel="stylesheet" type="text/css" /> --%>

<link href="<%=request.getContextPath()%>/pm/common/js/mobile/mobiscroll/css/mobiscroll.core-2.5.2.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/pm/common/js/mobile/mobiscroll/css/mobiscroll.animation-2.5.2.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/pm/common/js/mobile/mobiscroll/css/mobiscroll.android-ics-2.5.2.css" rel="stylesheet" type="text/css" />

<script src="<%=request.getContextPath()%>/pm/common/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/jsrender.mini.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/commonJsRender.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/mobile/main_mobile.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/mobile/handsome.min.js" type="text/javascript"></script> 
<script src="<%=request.getContextPath()%>/pm/common/js/mobile/scripts.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/mobile/mobiscroll/js/mobiscroll.core-2.5.2.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/mobile/mobiscroll/js/mobiscroll.core-2.5.2-zh.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/mobile/mobiscroll/js/mobiscroll.datetime-2.5.1.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/mobile/mobiscroll/js/mobiscroll.datetime-2.5.1-zh.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/mobile/mobiscroll/js/mobiscroll.android-ics-2.5.2.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/mobile/layer_mobile/layer_mobile.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/mobile/main_mobile.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/jsConstants.js" type="text/javascript"></script>

<%-- <script src="<%=request.getContextPath()%>/pm/common/js/mobile/HRMobile.js" type="text/javascript"></script> --%>
<%-- <script src="<%=request.getContextPath()%>/pm/common/js/mobile/mobiscroll.custom-3.0.0-beta2.min.js" type="text/javascript"></script> --%>
<%-- <script src="<%=request.getContextPath()%>/pm/common/js/mobile/special.js" type="text/javascript"></script> --%>
<script type="text/javascript">
	var path = '<%=request.getContextPath()%>';
</script>
<script type="text/javascript">
var CsGlobalObj = {};
CsGlobalObj.currentUserid = '<%=request.getSession().getAttribute("sys_hidden_currentUserId")%>';
CsGlobalObj.sys_hidden_deviceType = 'Mobile';
CsGlobalObj.sys_hidden_currentUsername = '<%=request.getSession().getAttribute("sys_hidden_currentUsername")%>';
CsGlobalObj.sys_hidden_dept = '<%=request.getSession().getAttribute("sys_hidden_dept")%>';
CsGlobalObj.sys_hidden_auth = '<%=request.getSession().getAttribute("sys_hidden_auth")%>';
CsGlobalObj.sys_hidden_subdept = '<%=request.getSession().getAttribute("sys_hidden_subdept")%>';
CsGlobalObj.sys_hidden_dept_name = '<%=request.getSession().getAttribute("sys_hidden_dept_name")%>';
CsGlobalObj.loginType = '<%=request.getSession().getAttribute("loginType")%>';
</script>
<script type="text/javascript">
(function (doc, win) {
    var docEl = doc.documentElement,
      resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
      recalc = function () {
        var clientWidth = docEl.clientWidth;
        oSize = 100 * (clientWidth / 720);
        
        if (!clientWidth) return;
        if (oSize > 75) {
          oSize = 75; // éå¶remå¼ 1080(æå¤§å®½åº¦) / 7.2ï¼è®¾è®¡å®½åº¦ï¼ =100
			var com_body = document.getElementById('com_body');  
    		/* com_body.style.width="7.2rem"; */
			com_body.style.margin="0 auto";
			 /* var com_foot = document.getElementById('com_foot');  
			 if(com_foot!=null){
				com_foot.style.width="7.2rem";
				com_foot.style.margin="0 auto"; 
			 } */
         }
        docEl.style.fontSize = oSize + 'px';
        
      };

    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
  })(document, window);
     /* (function (doc, win) {
       var docEl = doc.documentElement,
         resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
         recalc = function () {
           var clientWidth = docEl.clientWidth;
           oSize = 100 * (clientWidth / 720);
           
           if (!clientWidth) return;
           if (oSize > 75) {
             oSize = 75; // éå¶remå¼ 1080(æå¤§å®½åº¦) / 7.2ï¼è®¾è®¡å®½åº¦ï¼ =100
	var com_body = document.getElementById('com_body');  
       		com_body.style.width="7.2rem";
	com_body.style.margin="0 auto";
            }
           docEl.style.fontSize = oSize + 'px';
           
         };
 
       if (!doc.addEventListener) return;
       win.addEventListener(resizeEvt, recalc, false);
       doc.addEventListener('DOMContentLoaded', recalc, false);
     })(document, window);
      */
     //å¼¹åºå±
     function Show_Hidden(popid){
    	    if(popid.style.display=="block"){
    	        popid.style.display='none';
    	        $(".pop_bg").hide();
    			 $(".pop_close").hide();
    			 $("body").css("overflow", "auto");  
    			
    	    }else{
    	        popid.style.display='block';
    	        $(".pop_bg").css("display", "block");  
    			 $(".pop_close").css("display", "block");  
    			 $("body").css("overflow", "hidden");      			
    	    }
    	}
      /*å¾å°requeståæ°*/
     function getRequestParameterValue(name) {
  		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
  		var r = window.location.search.substr(1).match(reg);
  		if(r!=null) {
  			return unescape(r[2]);
  		} else {
  			return "";
  		}
  	} 
//-----------------------popList start----------------//    
function buildPop(result,title,popid,popfram,popShowpart){
	var str="<ul>";
	if(title!="noflag"){
		str=str+"<li onclick=putValOn(\'\',\'"+title+"\',\'"+popfram+"\',\'"+popShowpart+"\')><a>"+title+"</a></li>";
	}
	for(var i=0;i<result.length;i++){
		if(i!=result.length-1){
			str=str+"<li onclick=putValOn(\'"+result[i].code_value+"\',\'"+result[i].code_nm+"\',\'"+popfram+"\',\'"+popShowpart+"\')><a>"+result[i].code_nm+"</a></li>";	
		}else{
			str=str+"<li style='border-bottom:none;' onclick=putValOn(\'"+result[i].code_value+"\',\'"+result[i].code_nm+"\',\'"+popfram+"\',\'"+popShowpart+"\')><a>"+result[i].code_nm+"</a></li>";
		}
		
	}	
	
	str=str+"</ul>";
	$("#"+popid).html(str);
}

function putValOn(val,name,popfram,popShowpart){
	$("#"+popfram).attr("value",val);
	$("#"+popfram).html(name);
	$("#"+popShowpart).hide();
	$("body").css("overflow", "auto");
	queryDataList();
}      
//-----------------------popList end----------------//    
//初始化日期
function dateBuild(){
	var currYear = (new Date()).getFullYear();
	var opt = {};
	opt.date = {
		preset: 'date'
	};
	
	opt.def = {
		theme: 'android-ics light', //皮肤样式
		display: 'modal', //显示方式 
		mode: 'scroller', //日期选择模式
		lang: 'zh',
		startYear: currYear - 10, //开始年份
		endYear: currYear + 10 //结束年份
	};   
	return opt;
}
//组装Pop页面中选中的数据形成字符串
function buildIdsAndNames(checkboxname){
	if($('input[name="'+checkboxname+'"]:checked').length==0){
		return "";
	}else{
		var impMaidlist=new Array();
		var impManamelist=new Array();
		$('input[name="'+checkboxname+'"]:checked').each(function(i){ 
			impMaidlist[i]=$(this).attr("datid");
			impManamelist[i]=$(this).attr("datnam");
		});
		var idsStr=impMaidlist.join(",");
		var namesStr=impManamelist.join(",");
		return [idsStr,namesStr];
	}
	
}
//check 全选
function checkedAllSimple(checkedId){	
	if($("#"+checkedId).prop("checked")){
		$('input[name="'+checkedId+'"]').each(function(i){ 
			$(this).prop("checked",true);
		});
	}else{
		$('input[name="'+checkedId+'"]').each(function(i){ 
			$(this).prop("checked",false);
		});
	}
}
 </script>
