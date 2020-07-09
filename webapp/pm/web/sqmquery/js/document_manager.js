$(function(){
	menu('menuE5');
	initPage();
	

});

/**
 * 初始化页面
 * */
function initPage(){
	
	bindevent();
	$(".frist-title").click();
	queryDataList($("#noteName").val());
}

/**
 * 绑定事件
 */
function bindevent(){
	$.ajax({
		url:getRequestUrl("/SqmqueryController/queryDocumentDataList.json"),
		dataType:"json",
		async:false,
		data:{},
		success:function(data){
			
			var secondLists =  new Array();
			var threeLists =  new Array();
			
			$.each(data.TreeData, function(i, item) {
				secondLists[i]=item.notename
				threeLists[i]=item.id
	        });
		
			var fristTitle = $(".frist-title")
			
			fristTitle.on("click",function(){
				var secondListStr = "";
				for (var i = 0; i < secondLists.length; i++) {
					secondListStr +="<li>"+
						"<div class='nav'>"+
							"<span class='Titletext'>"+secondLists[i]+"</span><span style='display:none' class='idno'>"+threeLists[i]+"</span>"+
						"</div>"+
					"</li>";
				}
				$(".second-wrapper").html(secondListStr);
				$(this).find(".myicon").toggle();
				$(this).next().toggle();
				var text = $(this).find(".text").text();
				console.log(text)
			})	


			$("#container").on("click",".nav",function(){
				var allNav = $("#container").find(".nav").removeClass("active");
				$(this).addClass("active");
				
				var Titletext = $(this).parent().find(".Titletext").text();
				var idno = $(this).parent().find(".idno").text();
				$("#noteName").val(idno)
				queryDataList(idno)
				console.log(Titletext,idno)
			})
			
		},
		error:function(error){
			lalert(error);
		}
	});

}

/**
 * 查询方法
 */
function queryDataList(noteName){

	$("#tabListTable>tbody").html("");

		$.ajax({
			url:getRequestUrl("/SqmqueryController/queryDocumentTableDataList.json"),
			dataType:"json",
			data:{"documentInfo.NOTE":noteName},
			success:function(data){
			
				var strbody="";

				for(var i=0;i<data.TreeListData.length;i++){

					//生成表内容
					strbody =strbody+"<tr><td>"+data.TreeListData[i].id+"</td><td>"+data.TreeListData[i].filetitle+"</td><td>"+data.TreeListData[i].notename+"</td>" +
							"<td>"+data.TreeListData[i].filesize+"</td><td>"+data.TreeListData[i].filename+"</td>" +
							"<td>"+data.TreeListData[i].createdate+"</td><td>"+data.TreeListData[i].createuser+"</td>" +
							"<td>"+data.TreeListData[i].fileversion+"</td><td>"+data.TreeListData[i].fileremark+"</td>" +
							"<td><a href='http://114.113.147.106:9090/SKLBC/SampleImage/FileManager/"+data.TreeListData[i].filename+"'>下载</a></td></tr>";
				}
				$("#t_r_content").append(strbody);
			},
			error:function(error){
				lalert(error);
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


