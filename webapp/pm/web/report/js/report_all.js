$(function(){
	menu("menu43");
	initPage();
	bindevent();
});

/**
 * 初始化页面
 * */
function initPage(){
	$.ajax({
		url:getRequestUrl("/ReportAllController/beforeReportAllDataList.json"),
		dataType:"json",
		data:[],
		success:function(data){
			$( "#machTypeForm" ).html(
					$("#machTypeListTemplate").render(data)
			);
			$( "#chkTypeForm" ).html(
					$("#chkTypeListTemplate").render(data)
			);
			/*$( "#machSpeciesForm" ).html(
					$("#machSpeciesListTemplate").render(data)
			);*/
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}
/**
 * 查询当前月份第一天
 * @returns {String}
 */
function currentMonthStart(){
	var d = new Date(),str = '';
	 str += d.getFullYear()+'-';
	 if(d.getMonth()>8){
		 str  += d.getMonth() + 1+'-';
	 }else{
		 str  += '0'+(d.getMonth() + 1)+'-';
	 }
	 
	 str  += '01';
	return str;
}
/**
 * 查询当前月份最后一天
 * @returns {String}
 */
function currentMonthLast(){
	var d = new Date();
	var str2 = '';
	 str2 += d.getFullYear()+'-';
	 if(d.getMonth()>8){
		 str2  += d.getMonth() + 1+'-';
	 }else{
		 str2  += '0'+(d.getMonth() + 1)+'-';
	 }
	
	var year= d.getFullYear();
	var month = d.getMonth() + 1;
	 var new_year = year;    //取当前的年份           
	 var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）           
	 if(month>12)            //如果当前大于12月，则年份转到下一年           
	 {          
	  new_month -=12;        //月份减           
	  new_year++;            //年份增           
	 }          
	 var new_date = new Date(new_year,new_month,1);                //取当年当月中的第一天           
	 var date_count =   (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月的天数         
	 var last_date =   new Date(new_date.getTime()-1000*60*60*24);//获得当月最后一天的日期  
	 str2  += date_count;
	return str2;  
}
/**
 * 绑定事件
 */
function bindevent(){
	var start = {
			  elem: '#start',
			  istoday: true,
			  max: laydate.now(+1), //最大日期
			  isclear: false, //是否显示清空
			  choose: function(datas){
			     end.min = datas; //开始日选好后，重置结束日的最小日期
			     end.start = datas //将结束日的初始值设定为开始日
			  }
			};
	var end = {
			  elem: '#end',
			  istoday: true,
			  max: laydate.now(+1), //最大日期
			  isclear: false, //是否显示清空
			  choose: function(datas){
			    start.max = datas; //结束日选好后，重置开始日的最大日期
			  }
			};
	laydate(start);
	laydate(end);
	$("#start").val(currentMonthStart());
	$("#end").val(currentMonthLast());
	
	$("#machTypeForm").change(function(){
		$.ajax({
			url:getRequestUrl("/ReportAllController/getSpeciesCodeList.json"),
			dataType:"json",
			data:{"subGroupCode":$("#machType").val()},
			success:function(data){
				$( "#machSpeciesForm" ).html(
						$("#machSpeciesListTemplate").render(data)
				);
			},
			error:function(error){
				lalert('网络原因操作失败！','error');
			}
		});
	});
	
	$("#search_btn").bind('click',queryDataList);
	$("#reportExport_btn").bind('click',reportExport);
}

/**
 * 查询方法
 */
function queryDataList(){
	layer.load();
	var postData = collectData("search_area");
	postData['reportAllDto.planTypeNm'] = $("#planType option:selected").text();
	postData['reportAllDto.machineTypeNm'] = $("#machType option:selected").text();

	$.ajax({
		url:getRequestUrl("/ReportAllController/queryPlanCheckDetail.json"),
		dataType:"json",
		data:postData,
		success:function(result){
			layer.closeAll('loading');
			
			$("#pm_rate").val(result.pmRate);
					
			$("#detailList").html('');
			//生成表头
			for(var x=0;x<result.reportList.length;x++){
				//生成表内容
				var datalist=result.reportList[x];
				var strbody = "";
				if(x==0){
					strbody ="<table  class='tt t_report' border='0' cellspacing='0' cellpadding='0'>";
				}else{
					strbody ="<table  class='tt t_report mt30' border='0' cellspacing='0' cellpadding='0'>";
				}
				for(var p=0;p<datalist.length;p++){		
					if(p==0){
						if($('#planType').val()==Class.getConstant('PRE_TYPE')){
							strbody =strbody + "<thead><tr><th style='background:none; text-align:left;' colspan='"+(datalist[p].length+1)+"'>"+result.reportListEntity[x].pre_plan_nm + " (" + result.reportListEntity[x].pre_plan_date + ")" + "</th></tr><tr><th class='w47'>序号</th>";
						}else{
							strbody =strbody + "<thead><tr><th style='background:none; text-align:left;' colspan='"+(datalist[p].length+1)+"'>"+result.reportListEntity[x].ins_plan_nm + " (" + result.reportListEntity[x].ins_plan_date + ")" + "</th></tr><tr><th class='w47'>序号</th>";
						}
						for(var i=0;i<datalist[p].length;i++){
						strbody = strbody+"<th>"+datalist[p][i]+"</th>";
						}
						strbody = strbody+"</tr></thead> "
					}
					else{
						strbody =strbody+"<tr><td>"+p+"</td>";
						for(var j=0;j<datalist[p].length;j++){
							strbody = strbody+"<td>"+datalist[p][j]+"</td>";
						}
					}
					strbody =strbody+"</tr>";
				}	        
				
				if(result.reportPorpList!=""){
					var dataprop=result.reportPorpList[x];
					for(var p=0;p<dataprop.length;p++){	
						strbody =strbody+"<tr><td>"+(p+1)+"</td>";
						for(var j=0;j<datalist[0].length;j++){
							var str = dataprop[p][j];
							if(str==undefined){
								str = '';
							}
							strbody = strbody+"<td>"+str+"</td>";// width='100px'
						}
						strbody = strbody+"</tr>";
					}
				}
				
				strbody = strbody+"</table>";
				$("#detailList").append(strbody);
			}
			
			
		},
		error:function(error){
			layer.closeAll('loading');
			lalert("网络原因操作失败！","error");
		}
	});
}

/**
 * 报表导出方法
 */
function reportExport(){
	layer.load();
	var postData = collectData("search_area");
	postData['reportAllDto.planTypeNm'] = $("#planType option:selected").text();
	postData['reportAllDto.machineTypeNm'] = $("#machType option:selected").text();
	
	$.ajax({
		url:getRequestUrl("/ReportAllController/reportExport.json"),
		dataType:"json",
		data:postData,
		success:function(result){
			layer.closeAll('loading');
			if(result.filePath == ''){
				lalert("网络原因操作失败！","error");
			}else{
				//window.open(result.filePath,"page");
				location.href = path +  result.filePath;
			}
		},
		error:function(error){
			layer.closeAll('loading');
			lalert("网络原因操作失败！","error");
		}
	});
}

