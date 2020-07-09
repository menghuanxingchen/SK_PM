$(function(){
	menu('menu41');
	initPage();  	
	
});


function initPage(){
	bindevent();
	getInfoList();
}

function bindevent(){		
	var start = {
			  elem: '#start',
			  istoday: true,
			  choose: function(datas){
			     end.min = datas; //开始日选好后，重置结束日的最小日期
			     end.start = datas //将结束日的初始值设定为开始日
			  }
			};
	var end = {
			  elem: '#end',
			  istoday: true,
			  choose: function(datas){
			    start.max = datas; //结束日选好后，重置开始日的最大日期
			  }
			};
	laydate(start);
	laydate(end);
	//$("#start").val(laydate.now(-7));
	var myDate = new Date();
	var month=myDate.getMonth();
	if(month < 10){
		month="0"+month;
	}
	//$("#start").val(myDate.getFullYear()+"-"+month+"-01");//开始日期上月1号
	$("#start").val(myDate.getFullYear()+"-01-01");//开始日期当前年1月1号
	$("#end").val(laydate.now());
	$("#searchBt").bind('click',function(){
		getInfoList();
	});
	
}
/**
 * 查询提单列表
 */
function getInfoList(){
	var postData = collectData("search_area");
	layer.load();//添加进度条
	var parameterData = {
			url:getRequestUrl("/MaintResStatisController/queryStatisOrderTypeList.json"),
			successfunc:renderListDataFun,
			customArray:postData,
			pageller:"pagefoot",
			pageIndex:1,
			pageSize:50,
			isAsync:false
	};
	pageBarFortable(parameterData);
}
/**
 * 查询回调
 * @param result
 */
function renderListDataFun(result){
	buildEchart(result);
	$( "#infoList" ).html(
		$("#infoListTemplate").render(result)
	);
	$('#infoList tr:odd').addClass('odd'); //奇数行样式
	$('#infoList tr:even').addClass('eve');//偶数行样式
	
	layer.closeAll('loading'); //关闭进度条
	
	//var table_height=$("#table_height").height();
	if(result.length>10)
	{
		$("#last").css({paddingRight:'25px'});
		
	}else{
		$("#last").css({paddingRight:'8px'});
	}
}

/**
 * 跳转页面
 */
function gotoMachinePage(id){
	var page="/pm/web/maintainlog/maint_machine_statistic_list.jsp?maitem_id="+id+"&sdate="+$("#start").val()+"&edate="+$("#end").val();
	pageForward(page);
}
function buildEchart(result){
	var dataX = [];
	var dataY = [];
	for(var i=0;i<result.length;i++){
		dataX.push(result[i].maintenance_nm.substring(0,5));
		dataY.push(result[i].amt);
	}
	 // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {},
        tooltip: {},
        legend: {
            data:['故障类型']
        },
        xAxis: {        	
            data: dataX
        },
        yAxis:{
            },
        series: [{
            name: '故障类型',
            type: 'bar',
            barWidth : 50,//柱图宽度
            data: dataY
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}
