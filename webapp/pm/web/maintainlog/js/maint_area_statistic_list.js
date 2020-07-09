$(function(){
	menu('menu44');
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
	$("#start").val(laydate.now(-7));
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
			url:getRequestUrl("/MaintResStatisController/queryMaintAreaStatisList.json"),
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
	var dataX = [];
	var dataY1 = [];
	var dataY2 = [];
	var dataY3 = [];
	var dataY4 = [];
	var total1=0;
	var total2=0;
	var total3=0;
	var total4=0;
	for(var i=0;i<result.length;i++){
		dataX.push(result[i].placename.substring(0,5));
		dataY1.push(result[i].dept01);
		total1=total1+Number(result[i].dept01);
		dataY2.push(result[i].dept02);
		total2=total2+Number(result[i].dept02);
		dataY3.push(result[i].dept03);
		total3=total3+Number(result[i].dept03);
		dataY4.push(result[i].total_all);
		total4=total4+Number(result[i].total_all);
	}
	buildEchart(dataX,dataY1,"main01","电仪");
	buildEchart(dataX,dataY2,"main02","机械");
	buildEchart(dataX,dataY3,"main03","安全");
	buildEchart(dataX,dataY4,"main04","金额");
	$( "#t_r_content" ).html(
		$("#infoListTemplate").render(result)
	);
	//总计
	$( "#infoList tbody" ).append("<tr>" 
	    +"<td colspan='2'>总计</td> "
	    +"<td class='w47'>"+total1+"</td> "
	    +"<td class='w47'>"+total2+"</td> "
		+"<td class='w47'>"+total3+"</td> "
		+"<td class='w47'>"+total4+"</td> "  
	+"</tr>");

	
	$('#infoList tr:odd').addClass('odd'); //奇数行样式
	$('#infoList tr:even').addClass('eve');//偶数行样式
	
	layer.closeAll('loading'); //关闭进度条
	
	if(result.length>10)
	{
		$("#last").css({paddingRight:'25px'});
		
	}else{
		$("#last").css({paddingRight:'8px'});
	}
}
function buildEchart(dataX,dataY,mainid,str){	
	 // 基于准备好的dom，初始化echarts实例
   var myChart = echarts.init(document.getElementById(mainid));

   // 指定图表的配置项和数据
   var option = {
       title: {},
       tooltip: {},
       legend: {
           data:[str]
       },
       xAxis: {        	
           data: dataX
       },
       yAxis:{
           },
       series: [{
           name: str,
           type: 'bar',
           barWidth : 50,//柱图宽度
           data: dataY
       }]
   };

   // 使用刚指定的配置项和数据显示图表。
   myChart.setOption(option);
}
/**
 * 跳转页面
 */
function gotoMachinePage(id){
	var page="/pm/web/maintainlog/maint_machine_statistic_list.jsp?maitem_id="+id+"&sdate="+$("#start").val()+"&edate="+$("#end").val();
	pageForward(page);
}

