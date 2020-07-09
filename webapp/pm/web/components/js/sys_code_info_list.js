$(function(){
    //var obj = getPopRequest(); //pop值获取
	menu('menuA3');
    initPage();
    bindevent();
});
function initPage(){
	
	 getCheckItemList()
}
function bindevent(){		
	//跳转到新增页面
	$("#gotoAddpageBt").bind('click',function(){
		pageForward('/pm/web/components/sys_code_info_add.jsp');
	});

	
}
//初始化页面
/*function initPage(){
    $.ajax({
        url:getRequestUrl("/ComponentsSysCodeInfoController/beforeDataList.json"),
        dataType:"json",
        data:[],
        success:function(data){
            //$( "#ListForm" ).html(
            //      $("#ListTemplate").render(data)
            //);

            selDataList();
        },
        error:function(error){
            lalert('网络原因操作失败！','error');
        }
    });
}*/

//查询方法
function  getCheckItemList(){
    var postData = collectData("search_area");
  // postData = collecrData("Likemke");
    layer.load();///添加进度条
    var parameterData = {
            url:getRequestUrl("/ComponentsSysCodeInfoController/selDataList.json"),
            successfunc:renderListDataFun,
            customArray:postData,
            pageller:"pagefoot",
            pageIndex:1,
            pageSize:50,
            
            isAsync:false
    };
    pageBarFortable(parameterData)
}
function  getCheckItemListLike(){
    var postData = collectData("search_area");
 
  
  //  console.log(postData);
   // var postData = collecrData("Likemke");
    layer.load();///添加进度条
    var parameterData = {
            url:getRequestUrl("/ComponentsSysCodeInfoController/queryDataList.json"),
            successfunc:renderListDataFun,
            customArray:postData,
            pageller:"pagefoot",
            pageIndex:1,
            pageSize:50,            
            isAsync:false
    };
   pageBarFortable(parameterData)
}

//查询回调
var datalist;
function renderListDataFun(result){
   

  


$( "#machineList" ).html(
		$("#machineListTemplate").render(result)
	);
	$('#machineList tr:odd').addClass('odd'); //奇数行样式
	$('#machineList tr:even').addClass('eve');//偶数行样式
	 layer.closeAll('loading'); //关闭进度条
	 if(result.length>10)
		{
			$("#last").css({paddingRight:'25px'});
			
		}else{
			$("#last").css({paddingRight:'8px'});
		}
    //行与按钮控制
    //var trList = $("#dataListForm").children("tr");
    //for (var i=0;i<trList.length;i++) {   
    //  var trs = trList.eq(i);
    //  var tdArr = trList.eq(i).find("td");
    //  var flg = result[i].cancel_f;
    //  if(flg=='1'){
    //      $(trs).addClass("bg-grey");
    //      tdArr.eq(15).children().addClass("fgrey");
    //      tdArr.eq(16).children().addClass("fgrey");
    //  }
    //}
}

//详情
function selFun(code_id){
  var page = "/pm/web/sys_code_info_detail.jsp?code_id="+code_id;
  pageForward(page);
}


//跳转更新
function gotoUpdate(id){
	var page="/pm/web/components/sys_code_info_upd.jsp?code_id="+id;
	pageForward(page);
}



//作废信息
function celFun(code_id){
    var message = "确认作废？";
    layer.confirm(
        message,
        function(){
            layer.load();//添加进度条
            $.ajax({
                url:getRequestUrl("/ComponentsSysCodeInfoController/cancelEntityData.json"),
                dataType:"json",
                data:{"sysCodeInfo.code_id":code_id},
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

