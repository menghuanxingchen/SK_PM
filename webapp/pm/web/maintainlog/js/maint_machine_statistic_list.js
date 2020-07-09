$(function(){
	menu('menu41');
	initPage();  	
});

var maitem_id="";
var sdate = "";
var edate="";
function initPage(){
	maitem_id =getRequestParameterValue("maitem_id");
	sdate =getRequestParameterValue("sdate");
	edate =getRequestParameterValue("edate");
	$("#start").val(sdate);
	$("#end").val(edate);
	getInfoList();
}

/**
 * 查询提单列表
 */
function getInfoList(){
	var postData = collectData("search_area");
	postData=$.extend(postData,{"maintResStatisDTO.maintenance_id":maitem_id});
	layer.load();//添加进度条
	var parameterData = {
			url:getRequestUrl("/MaintResStatisController/queryStatisOrderMachineList.json"),
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
function gotoRepOrderPage(id){
	var page="/pm/web/maintainlog/maint_rep_order_list.jsp?maitem_id="+maitem_id+"&machine_id="+id+"&sdate="+$("#start").val()+"&edate="+$("#end").val();
	pageForward(page);
}

function buildEchart(result){
	var dataX = [];
	var dataY = [];
	for(var i=0;i<result.length;i++){
		dataX.push(result[i].machine_nm);
		dataY.push(result[i].re_amt);
	}
	 // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {},
        tooltip: {},
        legend: {
            data:['设备名称']
        },
        xAxis: {        	
            data: dataX
        },
        yAxis:{
            },
        series: [{
            name: '设备名称',
            type: 'bar',
            data: dataY
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}
