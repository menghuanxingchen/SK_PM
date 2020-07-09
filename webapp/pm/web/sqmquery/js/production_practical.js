$(function(){
	menu('menuE3');
	initPage();
});
function getProcessorId(){
	var commonData = parent.CsGlobalObj||parent.parent.CsGlobalObj;
	var id =commonData.currentUserid||"";
	return id;
}
var currentUserid =getProcessorId();
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
			  format: 'YYYY-MM-DD'
			};
	
	
	
	laydate(start);
	//默认今天
	var date1 = new Date();
	time1=date1.getFullYear()+"-"+(date1.getMonth()+1)+"-"+date1.getDate();//time1表示当前时间
	$("#start").val(time1)
	
	$("#search_btn").bind('click',queryDataList);
}

/**
 * 查询方法
 */
function queryDataList(){
	 $("#tabListTable>tbody").html("");
	 $("#tabListTable>thead>tr").html("");
	
    var startdate = $("#start").val().replace("-","")
    
	$.ajax({
		url:getRequestUrl("/SqmqueryController/queryPracticalDataList.json"),
		dataType:"json",
		data:{"productionPractica.startdate":startdate},
		success:function(data){
			
			var strThead = "<th width='47px'>序号</th><th width='100px'>日期</th><th width='100px'>VSL</th><th width='100px'>次数</th><th width='100px'>编号</th>" +
					"<th width='200px'>产品名称</th><th width='100px'>配比量</th><th width='100px'>计划量</th><th width='100px'>LOTNO</th>";
			var strth = "";
			//生成表头
			for(var x=0;x<data.theadPracticaList.length;x++){
				
				//生成表头内容
				strth = strth + "<th width='100px' colspan='2'>"+data.theadPracticaList[x].id+"</th>"
			}
			
			strThead = strThead +strth+"<th width='100px'>罐装量</th><th width='100px'>差异</th><th width='100px'>备注</th><th width='100px'>罐装时间CAN</th><th width='100px'>罐装时间D/M</th>"
			
			$("#thead_content").append(strThead);
			
			
			var strbody="";

			for(var i=0;i<data.productionPracticaList.length;i++){

				//生成表内容
				strbody =strbody+"<tr><td>"+(i+1)+"</td><td>"+data.productionPracticaList[i].cnfymd+"</td><td>"+data.productionPracticaList[i].vsl+"</td><td>"+data.productionPracticaList[i].turn+"</td>" +
						"<td>"+data.productionPracticaList[i].prod+"</td><td>"+data.productionPracticaList[i].prodname+"</td><td>"+data.productionPracticaList[i].blndqty+"</td><td>"+data.productionPracticaList[i].planqty+"</td>" +
						"<td>"+data.productionPracticaList[i].lotno+"</td><td>"+data.productionPracticaList[i].qty1+"</td><td>"+data.productionPracticaList[i].qty2+"</td><td>"+data.productionPracticaList[i].qty3+"</td>" +
						"<td>"+data.productionPracticaList[i].qty4+"</td><td>"+data.productionPracticaList[i].qty5+"</td><td>"+data.productionPracticaList[i].qty6+"</td><td>"+data.productionPracticaList[i].qty7+"</td>" +
						"<td>"+data.productionPracticaList[i].qty8+"</td><td>"+data.productionPracticaList[i].qty9+"</td><td>"+data.productionPracticaList[i].qty10+"</td><td>"+data.productionPracticaList[i].qty11+"</td>" +
						"<td>"+data.productionPracticaList[i].qty12+"</td><td>"+data.productionPracticaList[i].qty13+"</td><td>"+data.productionPracticaList[i].qty14+"</td><td>"+data.productionPracticaList[i].qty15+"</td>" +
						"<td>"+data.productionPracticaList[i].qty16+"</td><td>"+data.productionPracticaList[i].chrgqty+"</td><td>"+data.productionPracticaList[i].chrgqty2+"</td>" +
						"<td>"+data.productionPracticaList[i].bigo+"</td><td>"+data.productionPracticaList[i].cantime+"</td><td>"+data.productionPracticaList[i].dmtime+"</td></tr>";
			}
			$("#t_r_content").append(strbody);
		},
		error:function(error){
			lalert('网络原因操作失败！','error');
		}
	});
}
var excelList;
//回调
function renderListDataFun(result){
	
	excelList = result;
	
	for(var i=0;i<result.length;i++){
		result[i]['currentUserid']=currentUserid;
	}
	$( "#t_r_content" ).html(
		
			$("#dataListTemplate").render( result
		          )
	);
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


