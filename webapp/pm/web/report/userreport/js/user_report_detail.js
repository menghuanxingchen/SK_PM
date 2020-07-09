$(function(){
	menu('menu42');
	initPage();
});

/**
 * 初始化页面
 * */
var id = "";
var type="";
function initPage(){
	id = getRequestParameterValue("id");
	type = getRequestParameterValue("type");
	$("#start").val(getRequestParameterValue("start"));
	$("#end").val(getRequestParameterValue("end"));
	$("#closeBtn").bind('click',function(){
		pageForward('/UserReportController/defaultJsp.do');
	});
	queryDataList();
}

/**
 * 查询方法
 */
function queryDataList(){
	var postData = collectData("search_area");
	postData=$.extend(postData,{"userReportDto.update_id":id},{"userReportDto.type":type});
	$.ajax({
		url:getRequestUrl("/UserReportController/queryUserReportDetail.json"),
		dataType:"json",
		data:postData,
		success:function(result){
			$( "#t_r_content" ).html(
				$("#dataListTemplate").render( result.userReportDtoList
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
		}},
			error:function(error){
				lalert("网络原因操作失败！","error");
			}
		});
}
