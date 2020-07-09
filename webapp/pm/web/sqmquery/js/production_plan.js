$(function(){
	menu('menuE2');
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
	
	var start = {}, end = {};
	start.maxDate = '2099-12-31 23:59:59'
    jeDate('#startdate',{
    	onClose:false,
    	 skinCell:"jedateblue",     
        format: 'YYYY-MM-DD',
        minDate: '2014-06-16 23:59:59', //设定最小日期为当前日期
        maxDate: function (that) {
            //that 指向实例对象
            return jeDate.valText(that.valCell) == "" ? jeDate.nowDate({DD:0}) : start.maxDate;
        }, //设定最大日期为当前日期
        donefun: function(obj){
        	$("#enddate").val("")
            end.minDate = obj.val; //开始日选好后，重置结束日的最小日期
            end.maxDate = fun_date(obj.val)
            jeDate("#enddate",LinkageEndDate(false));
        }
    });
    jeDate('#enddate',LinkageEndDate);
    function LinkageEndDate(istg) {
        return {
        	onClose:false,
            trigger : istg || "click",
            skinCell:"jedateblue",     
            format: 'YYYY-MM-DD',
            minDate: function (that) {

                //that 指向实例对象
                var nowMinDate = jeDate.valText('#startdate') == "" && jeDate.valText(that.valCell) == "";
                return nowMinDate ? jeDate.nowDate({DD:0}) : end.minDate ;
            }, //设定最小日期为当前日期
            maxDate:  function (that) {
            	
                //that 指向实例对象
                var nowMaxDate = jeDate.valText('#startdate') == "" && jeDate.valText(that.valCell) == "";
                return nowMaxDate ? '2099-12-31 12:59:59' : end.maxDate ;
            }, //设定最大日期为当前日期
            donefun: function(obj){
                start.maxDate = obj.val; //将结束日的初始值设定为开始日的最大日期
            }
        };    
    }
    $("#search_btn").bind('click',queryDataList);
}




function fun_date(dateS){

    var date1 = new Date(dateS),
    time1=date1.getFullYear()+"-"+(date1.getMonth()+1)+"-"+date1.getDate();//time1表示当前时间
    var date2 = new Date(date1);
    date2.setDate(date1.getDate()+7);
    var time2 = date2.getFullYear()+"-"+(date2.getMonth()+1)+"-"+date2.getDate();
    
    return time2;
}

/**
 * 查询方法
 */
function queryDataList(){

	var postData = collectData("search_area");
var tete = getRequestUrl("/SqmqueryController/queryProductionDataList.json")

	var parameterData = {
			url:getRequestUrl("/SqmqueryController/queryProductionDataList.json"),
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

function backWright(){
	$.ajax({
		url:getRequestUrl("/SqmqueryController/backWrightDataList.json"),
		dataType:"json",
		async:false,
		data:{},
		success:function(data){
			
			if(data.DataJson == "true"){
				lalert('handle','success',function(){
				});
			}else{
				lalert(result.opmessage,'error');
			}
		},
		error:function(error){
			lalert(error);
		}
	});
}

