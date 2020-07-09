<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script> -->
<script src="<%=request.getContextPath()%>/pm/common/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/jsrender.mini.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/commonJsRender.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/jquery.autocomplete.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/layer/layer.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/messages_zh.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/laydate-v1.1/laydate/laydate.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/leftmenu.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/tableExport.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/jquery.base64.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/main.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/jsConstants.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/echars/echarts.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/echars/echarts.min.js" type="text/javascript"></script>
<script type="text/javascript">
	var path = '<%=request.getContextPath()%>';
</script>
<script type="text/javascript">
	function getrequest(message){
		message = message.replace('<',' ');
		message = message.replace('>',' ');
		message = message.replace('"',' ');
		message = message.replace('\'',' ');
		message = message.replace('/',' ');
		message = message.replace('%',' ');
		message = message.replace(';',' ');
		message = message.replace('(',' ');
		message = message.replace(')',' ');
		message = message.replace('+',' ');
		//2017.1.18add
		message = message.replace('\'',' '); //替换单引号
		return message;
	}

	function getRequestParameterValue(name) {
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		
		var r = window.location.search.substr(1).match(reg);
		if(r!=null) {
			return getrequest(unescape(r[2]));
		} else {
			return "";
		}
	}

//将id字符串加‘’
function buildNewIds(ids){
	var resultI=ids.split(',');
	var result = [];
	if(resultI.length!=0){
		for(var i = 0;i<resultI.length;i++){			
		 result.push("'"+resultI[i]+"'");
			
		}
	}
	return result.join(",");
}
//--------------------------pop start----------------//
//弹出pop
function pagePop(title,formid,url,spid,tyid){
	var pageIdArray = collectDataI(formid);//收集页面数据
	var pageIds=pageIdArray.join(",");//将页面数据形成字符串用于页面传值
	if(pageIds==""){
		spid="";
		tyid="";
	}
	layer.open({
        shade: [0.5, '#000', false],
        type: 2,
        area: ['1000px', '600px'],
        fix: true, //固定
        maxmin: true,
        title: [title, false],
        content:url+"?ids="+pageIds+"&spid="+spid+"&tyid="+tyid
    });

}
/**
 * 收集页面数据
 * 通过“idattr”属性获得页面的值
 * */
function collectDataI(containerId) {
	if(containerId==null||""==containerId)
		container = "*";
	var sel = "idattr";
	var objs = $("#"+containerId+" [" + sel + "]");
	var result = [];
	for (var i = 0, len = objs.length; i < len; i++) {
		var obj = objs[i];
		if($(obj).val()!=""){
			result[i]=$(obj).val();
		}
	}
	return result;
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

//自定义弹出pop
function pagePopShow(title,url,Xpx,Ypx){
	layer.open({
        shade: [0.5, '#000', false],
        type: 2,
        area: [Xpx, Ypx],
        fix: true, //固定
        maxmin: true,
        title: [title, false],
        content:url+"?PopXpx="+Xpx+"&PopYpx="+Ypx
    });

}
//--------------------------pop end----------------//
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
//批量确认和删除 用于返回 选中条数  和id用，隔开的字符串
function buildIdsBatch(checkboxname){
	if($('input[name="'+checkboxname+'"]:checked').length==0){
		return "";
	}else{
		var idlist=new Array();
		$('input[name="'+checkboxname+'"]:checked').each(function(i){ 
			idlist[i]=$(this).attr("datid");
		});
		var len=idlist.length;
		var idsStr=idlist.join(",");
		return [len,idsStr];
	}
	
}
</script>
