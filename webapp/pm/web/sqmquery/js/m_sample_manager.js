var TYPE = getRequestParameterValue("TYPE");
var user_num = getRequestParameterValue("sysUserInfo.user_num");
var pwd = getRequestParameterValue("sysUserInfo.pwd");
$(function(){
	login();
		
});
function getProcessorId(){
	var commonData = parent.CsGlobalObj||parent.parent.CsGlobalObj;
	var id =commonData.currentUserid||"";
	return id;
}
var currentUserid =getProcessorId();

function login(){
	var postData = {"sysUserInfo.user_num":user_num,"sysUserInfo.pwd":pwd};
	    postData=$.extend(postData,{"sysUserInfo.loginType":TYPE});
	$.ajax({
		url:path+"/loginManagerController/checkSysUserInfo.json",
		dataType:"json",
		data:postData,
		success:function(result){
			if(result.opflag){
				if(result.sysUserinfo){
					initPage();
				}
			}else{
				lalert(result.opmessage,'error');
			}
		},
		error:function(error){
			lalert('网络原因操作失败','error');
		}
	});
}

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

    $("#search_btn").bind('click',queryDataList);
    
}

/**
 * 查询方法
 */
function queryDataList(){
	
	if($("#PKG").val()=="" ||$("#PKG").val() == null){
		alert("请填写PKG!")
	}else{
		
		$("#t_r_content").html("");
		   var PKG = $("#PKG").val()
		   var prod = $("#prod").val()
		   var isNew = $("#isNew").prop("checked")
		   var isComplete = $("#isComplete").prop("checked")
		   var strStan = $(':radio[name="strStan"]:checked').val();
		   var pSort = $(':radio[name="pSort"]:checked').val();

			$.ajax({
				url:getRequestUrl("/SqmqueryController/querySampleManagerDataList.json"),
				dataType:"json",
				data:{"sampleInfo.PKG":PKG,"sampleInfo.prod":prod,"sampleInfo.isNew":isNew,"sampleInfo.isComplete":isComplete,
					  "sampleInfo.strStan":strStan,"sampleInfo.pSort":pSort},
				success:function(data){
				
					var strbody="";

					for(var i=0;i<data.data.length;i++){

						//生成表内容
						strbody =strbody+"<tr><td>"+(i+1)+"</td><td>"+data.data[i].pkg+"</td><td>"+data.data[i].pkgname+"</td>" +
								"<td>"+data.data[i].prodcode+"</td><td>"+data.data[i].prodname+"</td>" +
								"<td><a href='/PM/pm/web/sqmquery/m_sample_detail_manager.jsp?colcnt=5&pkg="+data.data[i].pkg+"&prod="+data.data[i].prodcode+"&revno="+data.data[i].samplevcode+"'>"+data.data[i].samplev+"</a></td>" +
								"<td>"+data.data[i].samplevcode+"</td>" +"<td>"+data.data[i].samplevdate+"</td><td>"+data.data[i].samplevbigo+"</td><td>"+data.data[i].samplevweight+"</td>" +
								"<td><a href='/PM/pm/web/sqmquery/m_sample_detail_manager.jsp?colcnt=10&pkg="+data.data[i].pkg+"&prod="+data.data[i].prodcode+"&revno="+data.data[i].sampletcode+"'>"+data.data[i].samplet+"</a></td>" +
								"<td>"+data.data[i].sampletcode+"</td><td>"+data.data[i].sampletdate+"</td><td>"+data.data[i].sampletbigo+"</td><td>"+data.data[i].sampletweight+"</td>" +
								"<td><a href='/PM/pm/web/sqmquery/m_sample_detail_manager.jsp?colcnt=15&pkg="+data.data[i].pkg+"&prod="+data.data[i].prodcode+"&revno="+data.data[i].samplebcode+"'>"+data.data[i].sampleb+"</a></td>" +
								"<td>"+data.data[i].samplebcode+"</td><td>"+data.data[i].samplebdate+"</td><td>"+data.data[i].samplebbigo+"</td><td>"+data.data[i].samplebweight+"</td>" +
								"<td><a href='/PM/pm/web/sqmquery/m_sample_detail_manager.jsp?colcnt=20&pkg="+data.data[i].pkg+"&prod="+data.data[i].prodcode+"&revno="+data.data[i].sampleccode+"'>"+data.data[i].samplec+"</a></td>" +
								"<td>"+data.data[i].sampleccode+"</td><td>"+data.data[i].samplecdate+"</td><td>"+data.data[i].samplecbigo+"</td><td>"+data.data[i].samplecweight+"</td></tr>";
					}
					$("#t_r_content").append(strbody);
				},
				error:function(error){
					lalert(error);
				}
			});
	}

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


