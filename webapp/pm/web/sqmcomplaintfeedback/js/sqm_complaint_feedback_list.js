$(function(){
	menu('menuE1');
    initPage();
});

//初始化页面
function initPage(){
    $.ajax({
        url:getRequestUrl("/SqmComplaintFeedbackController/beforeDataList.json"),
        dataType:"json",
        data:[],
        success:function(data){
            //$( "#ListForm" ).html(
            //      $("#ListTemplate").render(data)
            //);

            queryDataList();
            bindevent();
        },
        error:function(error){
            lalert('网络原因操作失败！','error');
        }
    });
}
/**
 * 绑定事件
 */
function bindevent(){
	
	$("#search_btn").bind('click',queryDataList);
}
//查询方法
function queryDataList(){
    var postData = collectData("search_area");
    layer.load();///添加进度条
    var parameterData = {
            url:getRequestUrl("/SqmComplaintFeedbackController/queryDataList.json"),
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

//查询回调
var datalist;
function renderListDataFun(result){
    layer.closeAll('loading'); //关闭进度条
	
	$( "#t_r_content" ).html($("#dataListTemplate").render(result));
    datalist = result;
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

//详情
function selFun(id){
  var page = "/pm/web/sqmcomplaintfeedback/sqm_complaint_feedback_detail.jsp?id="+id;
  pageForward(page);
}

//新增
function addEntityDataFun(){
    var page = "/pm/web/sqmcomplaintfeedback/sqm_complaint_feedback_add.jsp";
    pageForward(page);
}

//修改
function updateEntityDataFun(id){
    var page = "/pm/web/sqmcomplaintfeedback/sqm_complaint_feedback_upd.jsp?id="+id;
    pageForward(page);
}

//作废信息
function deleteDataFun(id){
    var message = "确认作废？";
    layer.confirm(
        message,
        function(){
            layer.load();//添加进度条
            $.ajax({
                url:getRequestUrl("/SqmComplaintFeedbackController/cancelEntityData.json"),
                dataType:"json",
                data:{"sqmComplaintFeedback.id":id},
                success:function(result){
                    layer.closeAll('loading'); //关闭进度条
                    lalert("操作成功","success");
                    queryDataList();
                },
                error:function(error){
                    layer.closeAll('loading'); //关闭进度条
                    lalert('网络原因操作失败！','error');
                }
            });
        });
}
//pop导入
function popImport(){
    //var rd_n = $("input[type='radio']:checked").val();
    //if(rd_n > 0){
    //    var obj = new Object();
    //    obj['entity_name'] = datalist[rd_n-1].entity_name;
    //    popClose(obj);
    //}else{
    //    lalert('必须选择一条','warn');
    //}
}

