$(function(){
	initPage();
});

function initPage(){
	
	
	queryDataList();
}

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
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


