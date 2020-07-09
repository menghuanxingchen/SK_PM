//现在选择的设备
var nowMachine = '';

$(function(){
	menu('menuA7');
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
			  format: 'YYYY-MM',
			  choose: function(datas){
			     end.min = datas; //开始日选好后，重置结束日的最小日期
			     end.start = datas //将结束日的初始值设定为开始日
			  }
			};
	var end = {
			  elem: '#end',
			  istoday: true,
			  format: 'YYYY-MM',
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
		month="0"+(month+1);
	}
	//$("#start").val(myDate.getFullYear()+"-"+month+"-01");//开始日期上月1号
	$("#start").val(myDate.getFullYear()+"-01");//开始日期当前年1月
	$("#end").val(myDate.getFullYear()+"-"+month);
	$("#searchBt").bind('click',function(){
		if($("#start").val()==''){
			lalert('请选择起始日期','error');
			return false;
		}else if($("#end").val()==''){
			lalert('请选择结束日期','error');
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
		url:getRequestUrl("/MaintResStatisController/queryMTTAECharts.json"),
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
	
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {},
        tooltip: {
//        	formatter: '{a}<br />{b}: {c}分钟<br />{c/60}小时'
        	formatter:function(params){
        		var str = params.seriesName+'<br />'+params.name+':'+params.value+'分钟<br />'+(params.value/60).toFixed(2)+'小时';
        		return str;
        	}
        },
        legend: {
            data:[nowMachine]
        },
        xAxis: {        	
            data: dataX,
            name:'月份'
        },
        yAxis:{
        	name:'/分钟'
        },
        series: [{
            name: nowMachine,
            type: 'bar',
            barWidth : 50,//柱图宽度
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            data: dataY
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
