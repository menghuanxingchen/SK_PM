var menuid='menuA5';
$(function(){
	menu(menuid);
	initPage();
	var start = {
			  elem: '#start_dt',
			  format: 'YYYY-MM'
			};
	laydate(start);
});
//日期格式化
Date.prototype.Format = function (fmt) { //
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
//初始化页面
function initPage(){
	 var frDate = new Date().Format("yyyy-MM");
	 $("#start_dt").val(frDate);
     queryDataList();
}

//查询方法
function queryDataList(){
    var postData = collectData("search_area");
    layer.load();///添加进度条
    var parameterData = {
            url:getRequestUrl("/WorktimeInfoController/queryDataList.json"),
            successfunc:renderListDataFun,
            customArray:postData,
            pageller:"pagefoot",
            pageIndex:1,
            pageSize:10,
            checkedId:"checkboxGroupId",
            isAsync:false
    };
    pageBarFortable(parameterData)
}

//查询回调
var datalist;
function renderListDataFun(result){
    layer.closeAll('loading'); //关闭进度条
  $( "#dateList" ).html(
          $("#dateListTemplate").render(result)
    );
  bindValidateEvent('aaa');
}

//填写数字验证
function numCheck(){
	var result = true;
	var patrn = /^[0-9]*$/;
	$(".numCheck").each(function(){
		if(patrn.exec($(this).val())){
			result = true;
		}else{
			result = false;
			return false;
		}
	});
	return result;
}

//作废信息
function addDataFun(){
	if(numCheck()){
	    var message = "确认保存？";
	    layer.confirm(
	        message,
	        function(){
	            layer.load();//添加进度条
	            var postData = collectData("dateList");
	            $.ajax({
	                url:getRequestUrl("/WorktimeInfoController/updEntityData.json"),
	                dataType:"json",
	                data:postData,
	                success:function(result){
	                    layer.closeAll('loading'); //关闭进度条
	                    lalert("操作成功","success");
	                    queryDataList();
	                },
	                error:function(error){
	                    layer.closeAll('loading'); //关闭进度条
	                    lalert('网络原因操作失败！','error');
	                }
	            });
	        });
	}else{
		lalert('请检查工时输入格式！','error');
	}
}

