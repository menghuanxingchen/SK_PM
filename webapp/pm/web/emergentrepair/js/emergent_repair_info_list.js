$(function(){
    //var obj = getPopRequest(); //pop值获取
    initPage();
    menu("menuA4");
});

//初始化页面
function initPage(){
    $.ajax({
        url:getRequestUrl("/EmergentRepairInfoController/beforeDataList.json"),
        dataType:"json",
        data:[],
        success:function(data){
        	$( "#jobList" ).html(
            		$("#jobTemplate").render(data)
            	);

        	var start = {
      			  elem: '#st',
      			  choose: function(datas){
      			     end.min = datas; //开始日选好后，重置结束日的最小日期
      			     end.start = datas //将结束日的初始值设定为开始日
      			  },
      			  clear:function(){
      				  delete end["min"];
      				delete end["start"];
      			  }
      			};
      	var end = {
      			  elem: '#ed',
      			  choose: function(datas){
      			    start.max = datas; //结束日选好后，重置开始日的最大日期
      			  },
      			clear:function(){
    				  delete start["max"];
    			  }
      			};
      	laydate(start);
      	laydate(end);
            queryDataList();
            initPageTame()
        },
        error:function(error){
            lalert('网络原因操作失败！','error');
        }
    });
}

//查询方法
function queryDataList(){
    var postData = collectData("search_area");
    layer.load();///添加进度条
    var parameterData = {
            url:getRequestUrl("/EmergentRepairInfoController/queryDataList.json"),
            successfunc:renderListDataFun,
            customArray:postData,
            pageller:"pagefoot",
            pageIndex:1,
            pageSize:10,
            checkedId:"checkboxGroupId",
            isAsync:false
    };
    pageBarFortable(parameterData)
}

//查询回调
var datalist;
function renderListDataFun(result){
    layer.closeAll('loading'); //关闭进度条
    $( "#meRepairList" ).html(
    		$("#meRepairTemplate").render(result)
    	);

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


//新增
function addFun(){
    var page = "/pm/web/emergentrepair/emergent_repair_info_add.jsp";
    pageForward(page);
}

//修改
function updFun(emergent_id){
    var page = "/pm/web/emergentrepair/emergent_repair_info_upd.jsp?emergent_id="+emergent_id;
    pageForward(page);
}

//维修反馈
function repairFun(emergent_id){
    var page = "/pm/web/emergentrepair/emergent_repair_info_repair.jsp?emergent_id="+emergent_id;
    pageForward(page);
}

//初始化页面制御
function initPageTame(){
	//清除input按钮
	$('#selPotNm').bind('mouseover',function(){
		$('#clearPotA').css('visibility','');
	});
	$('#selPotNm').bind('mouseout',function(){
		$('#clearPotA').css('visibility','hidden');
	});
	$('#clearPotA').bind('mouseover',function(){
		$('#clearPotA').css('visibility','');
	});
	$('#clearPotA').bind('mouseout',function(){
		$('#clearPotA').css('visibility','hidden');
	});
}

/**
 * POP选择页弹出
 * @returns
 */
function selectPot() {
	var pathName = window.location.pathname;
    var webName = pathName.substring(0, pathName.indexOf('/',2));
	pagePopShow("设备选择",webName+"/MachinePotPartInfoController/selectPotPop","1000px","600px");
}
/**
 * 清空一级设备选择
 * @returns
 */
function clearPot() {
	$('#selPotId').val('');
	$('#selPotNm').val('');
}
/**
 * pop回调
 * @returns
 */
function selectPotBack(obj) {
	$('#selPotId').val(obj.id);
	$('#selPotNm').val(obj.nm);

	$("body").trigger("");
}
//作废信息
function celFun(emergent_id){
    var message = "确认删除？";
    layer.confirm(
        message,
        function(){
            layer.load();//添加进度条
            $.ajax({
                url:getRequestUrl("/EmergentRepairInfoController/cancelEntityData.json"),
                dataType:"json",
                data:{"emergentRepairInfo.emergent_id":emergent_id},
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
//解除故障
function interfaceRelieveFault(emergent_id,deleteYn){
    var message = deleteYn=='N'?'<table id="sqmNormalform" class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0"><tbody><tr><td class="odd w10">设备正常参数</td><td colspan="3"><input name="emergentRepairInfo.sqm_normal_parameter" type="text" value="" dc="true" dcrequired="请输入设备正常参数" maxlength="50"></td></tr></tbody></table>':"确认解除并删除?";
    layer.confirm(
    	message,
        function(){
        	var checkfalg = deleteYn=='Y'?true:checkFormValue('sqmNormalform');
            if(checkfalg){
            	  layer.load();//添加进度条
                  $.ajax({
                      url:getRequestUrl("/SqmInterfaceController/interfaceRelieveFault.json"),
                      dataType:"json",
                      data:{"emergentRepairInfo.emergent_id":emergent_id,"emergentRepairInfo.sqm_normal_parameter":$('input[name="emergentRepairInfo.sqm_normal_parameter"]').val(),"deleteYn":deleteYn},
                      success:function(result){
                          if(result.opflag){
                              layer.closeAll('loading'); //关闭进度条
                              lalert("操作成功","success");
                              queryDataList();
                          }else{
                              lalert(result.opmessage,'error');
                          }
                      },
                      error:function(error){
                          layer.closeAll('loading'); //关闭进度条
                          lalert('网络原因操作失败！','error');
                      }
                  });
            }
        });
}
