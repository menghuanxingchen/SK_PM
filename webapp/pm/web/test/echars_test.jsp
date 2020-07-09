<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>EChars</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
</head>
<body>
 <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="height:400px"></div>
    <!-- ECharts单文件引入 -->
    <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
     <script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
                var option = {tooltip : {
                    show: true,
                    trigger: 'item'
                },
                title : {
                    text: '某地区蒸发量和降水量',
                    x: "center"
                },
                legend: {
                    data:['邮件营销','联盟广告','直接访问','搜索引擎'],
                    x: "right"
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data : ['周一','周二','周三','周四','周五','周六','周日']
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        splitArea : {show : true}
                    }
                ],
                series : [
                    {
                        name:'邮件营销',
                        type:'bar',
                        itemStyle: {        // 系列级个性化样式，纵向渐变填充
                            normal: {
                                borderColor:'red'
                            }
                        },
                        data:[220, 232, 101, 234, 190, 330, 210]
                    },
                    {
                        name:'联盟广告',
                        type:'bar',
                        stack: '总量',
                        data:[120, '-', 451, 134, 190, 230, 110]
                    },
                    {
                        name:'直接访问',
                        type:'bar',
                        stack: '总量',
                        itemStyle: {                // 系列级个性化
                            normal: {
                                borderWidth: 6,
                                borderColor:'tomato',
                                color: 'red'
                            }
                        },
                        data:[
                            320, 332, 100, 334,
                            {
                                value: 390,
                                symbolSize : 10,   // 数据级个性化
                                itemStyle: {
                                    normal: {
                                        color :'lime'
                                    }
                                }
                            },
                            330, 320
                        ]
                    },
                    {
                        name:'搜索引擎',
                        type:'bar',
                        barWidth: 40,                   // 系列级个性化，柱形宽度
                        itemStyle: {
                            normal: {                   // 系列级个性化，横向渐变填充
                                borderRadius: 5,
                                color : (function (){
                                    var zrColor = require('zrender/tool/color');
                                    return zrColor.getLinearGradient(
                                        0, 0, 1000, 0,
                                        [[0, 'rgba(30,144,255,0.8)'],[1, 'rgba(138,43,226,0.8)']]
                                    )
                                })(),
                                label : {
                                    show : true,
                                    textStyle : {
                                        fontSize : '20',
                                        fontFamily : '微软雅黑',
                                        fontWeight : 'bold'
                                    }
                                }
                            }
                        },
                        data:[
                            620, 732, 
                            {
                                value: 701,
                                itemStyle : { normal: {label : {position: 'inside'}}}
                            },
                            734, 890, 
                            {
                                value: 930,
                                itemStyle : { normal: {label : {show: false}}}
                            },
                            820
                        ],
                        markPoint : {
                            data : [
                                {name : '最高', value : 930, xAxis: '周六', yAxis: 930, symbolSize:14}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name : '平均值'},
                                {type : 'max'},
                                {type : 'min'}
                            ]
                        }
                    }
                ]
            };
                //为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
    </script>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
</html>