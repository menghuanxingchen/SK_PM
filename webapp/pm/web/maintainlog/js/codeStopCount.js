/**
 * 三级子部件停机次数页面
 * @returns
 */

$(function(){
	menu('menuAA');
	initPage();  	
	
});


function initPage(){
	bindevent();
	getInfoList();
}

function bindevent(){		
	var start = {
			  elem: '#selTime',
			  istoday: true,
			  format: 'YYYY-MM',
			  choose: function(datas){
				  $("#selTime").val(datas); //日期赋值
			  }
			};
	laydate(start);
	$("#selTime").val(new Date().Format('yyyy-MM'));
	$("#searchBt").bind('click',function(){
		if($("#selTime").val()==''){
			lalert('请选择日期','error');
			return false;
		}
		getInfoList();
	});
	
}
/**
 * 查询提单列表
 */
function getInfoList(){
	var postData = collectData("search_area");
	layer.load();//添加进度条
	$.ajax({
		url:getRequestUrl("/MaintResStatisController/queryCodeStopCountECharts.json"),
		dataType:"json",
		data:postData,
		success:function(data){
			renderListDataFun(data);
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}
/**
 * 查询回调
 * @param result
 */
function renderListDataFun(result){
	buildEchart(result);
//	$( "#infoList" ).html(
//		$("#infoListTemplate").render(result)
//	);
//	$('#infoList tr:odd').addClass('odd'); //奇数行样式
//	$('#infoList tr:even').addClass('eve');//偶数行样式
//	
	layer.closeAll('loading'); //关闭进度条
//	
//	//var table_height=$("#table_height").height();
//	if(result.length>10)
//	{
//		$("#last").css({paddingRight:'25px'});
//		
//	}else{
//		$("#last").css({paddingRight:'8px'});
//	}
}

/**
 * 跳转页面
 */
function gotoMachinePage(id){
	var page="/pm/web/maintainlog/maint_machine_statistic_list.jsp?maitem_id="+id+"&sdate="+$("#start").val()+"&edate="+$("#end").val();
	pageForward(page);
}

/**
 * 生成ECharts图表
 * @param result
 * @returns
 * @author 姜易平
 */
function buildEchart(result){
	var dataX = result.data.dataX;
	var dataY = result.data.dataY;
	 // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {},
        tooltip: {
        	formatter: '{b}<br />停机{c}次'
        },
        xAxis: {        	
            data: dataX,
            name:'部件'
        },
        yAxis:{
            name:'停机次数'
            },
        series: [{
            name: '',
            type: 'bar',
            barWidth : 50,//柱图宽度
            data: dataY
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}
