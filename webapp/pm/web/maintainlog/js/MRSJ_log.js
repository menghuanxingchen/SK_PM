//现在选择的设备
var nowMachine = '';

$(function(){
	menu('menuA10');
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
			  format: 'YYYY-MM'
			};
	
	laydate(start);

	//$("#start").val(laydate.now(-7));
	var myDate = new Date();
	var month=myDate.getMonth();
	if(month < 10){
		month="0"+(month+1);
	}
	//$("#start").val(myDate.getFullYear()+"-"+month+"-01");//开始日期上月1号
	$("#start").val(myDate.getFullYear()+'-'+month);//开始日期当前年1月
	
	$("#searchBt").bind('click',function(){
		if($("#start").val()==''){
			lalert('请选择起始日期','error');
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

	nowMachine = $('#selPotNm').val();
	layer.load();//添加进度条
	$.ajax({
		url:getRequestUrl("/MaintResStatisController/queryMRSJECharts.json"),
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
	layer.closeAll('loading'); //关闭进度条
}

/**
 * 跳转页面
 */
function gotoMachinePage(id){
	var page="/pm/web/maintainlog/maint_machine_statistic_list.jsp?maitem_id="+id+"&sdate="+$("#start").val()+"&edate="+$("#end").val();
	pageForward(page);
}
function buildEchart(result){

	var dataX = result.data.dataX;
	var dataY = result.data.dataY;
	var dataZ = result.data.dataZ;
	
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {},
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        legend: {
            data:[nowMachine]
        },
        xAxis: {
        	type: 'category',
            data: dataX,
            name:'日期'
        },
        yAxis:[{
            name:'时间'
            }],
        series: [{
            name: nowMachine+"（分钟）",
            type: 'bar',
            data: dataZ
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

/**
 * POP选择页弹出
 * @returns
 */
function selectPot() {
	var pathName = window.location.pathname;
    var webName = pathName.substring(0, pathName.indexOf('/',2));
	pagePopShow("一级设备选择",webName+"/MachinePotPartInfoController/selectPotPop","1000px","600px");
}

/**
 * pop回调
 * @returns
 */
function selectPotBack(obj) {
	$('#selPotId').val(obj.id);
	$('#selPotNm').val(obj.nm);
}
