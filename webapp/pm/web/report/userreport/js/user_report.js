$(function(){
	menu('menu42');
	initPage();
});

/**
 * 初始化页面
 * */
function initPage(){
	bindevent();
	queryDataList();
}

/**
 * 绑定事件
 */
function bindevent(){
	var start = {
			  elem: '#start',
			  istoday: true,
			  max: laydate.now(+1), //最大日期
			  choose: function(datas){
			     end.min = datas; //开始日选好后，重置结束日的最小日期
			     end.start = datas //将结束日的初始值设定为开始日
			  }
			};
	var end = {
			  elem: '#end',
			  istoday: true,
			  max: laydate.now(+1), //最大日期
			  choose: function(datas){
			    start.max = datas; //结束日选好后，重置开始日的最大日期
			  }
			};
	laydate(start);
	laydate(end);
	$("#start").val(laydate.now(-7));
	$("#end").val(laydate.now());
	$("#search_btn").bind('click',queryDataList);
}

/**
 * 查询方法
 */
function queryDataList(){
	var postData = collectData("search_area");
	var parameterData = {
			url:getRequestUrl("/UserReportController/queryDataList.json"),
			successfunc:renderListDataFun,
			customArray:postData,
			pageller:"pagefoot",
			pageIndex:1,
			pageSize:50,
			checkedId:"checkboxGroupId",
			isAsync:false
	};
	pageBarFortable(parameterData)
}

//回调
function renderListDataFun(result){
	$( "#t_r_content" ).html(
			$("#dataListTemplate").render( result
		          )
	);
	buildEchart(result);
	//奇偶行颜色不同
	$('.t_r_content tbody tr:odd').addClass('odd');
	//var table_height=$("#table_height").height();
	if(result.length>10)
	{
		$("#last").css({paddingRight:'25px'});
		
	}else{
		$("#last").css({paddingRight:'8px'});
	}
}

/**
 * 详情页面
 * @param id
 */
function goToUserReportDetailFun(id,type){
	var startDate = $('#start').val();
	var endDate = $('#end').val();
	var page = "/pm/web/report/userreport/user_report_detail.jsp?id="+id+"&start="+startDate+"&end="+endDate+"&type="+type;
	pageForward(page);
}

/**
 * echars
 * @param id
 */
function buildEchart(result){
	var dataX = [];
	var dataY = [];
	for(var i=0;i<result.length;i++){
		dataX.push(result[i].user_nm);
		dataY.push(result[i].all_cnt);
	}
	 // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {},
        tooltip: {},
        legend: {
            data:['姓名']
        },
        xAxis: {        	
            data: dataX
        },
        yAxis:{
            },
        series: [{
            name: '姓名',
            type: 'bar',
            barWidth : 50,//柱图宽度
            data: dataY
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}
