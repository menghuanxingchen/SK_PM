/**
 * 二级子部件停机次数页面
 * @returns
 */
//现在选择的设备
var nowMachine = '';

$(function(){
	menu('menuA9');
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
				  $("#selTime").val(datas);
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
	nowMachine = $('#selPotNm').val();
	layer.load();//添加进度条
	$.ajax({
		url:getRequestUrl("/MaintResStatisController/queryPotPartStopCountECharts.json"),
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
	$("#machineTitle").html("设备（"+nowMachine+"）的停机次数报表");
	$("#machineStopCount").html('<th class="w47">次数</th>').append(
		$("#machineStopCountTemplate").render(result.machineStopCount)
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
	var dataX = result.data.dataX;
	var dataY = result.data.dataY;
	var dataZ = result.data.dataZ;
	
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

	var dataList = [];
	for (var i = 0; i < dataX.length; i++) {
		var data = [dataX[i],dataY[i]];
		dataList[i] = data;
	}
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
        xAxis: {        	
            data: dataX
        },
        yAxis:[{
            name:'停机次数',
            min: 0,
            max: 5,
            interval: 4,
            },
            {
                name: '停机占比',
                min: 0,
                max: 120,
                interval: 20,
                axisLabel: {
                    formatter: '{value} %'
                }
            }
        ],
        series: [{
            name: '停机次数(次)',
            type: 'bar',
            barWidth:50,
            data: dataY
        },
        {
            name:'停机占比(%)',
            type:'line',
            yAxisIndex: 1,
            data:dataZ
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

