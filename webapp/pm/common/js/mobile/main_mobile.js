
function lalert(message,icon,obj){
	if(icon=='warn'){
		layer.open({
			  content: message,
			  scrollbar: false,
			  closeBtn: 1,
			  icon: 0,
			  time: 2, //2秒后自动关闭
			  skin: 'footer',
			  end:obj
			});
		/*layer.alert(message,{
			  icon: 0
		},obj);*/
	}else if(icon=='success'){
		if(message=='save'){
			/*layer.alert('提交成功',{
				  icon: 1
			},obj);*/
			layer.open({
				  content: '提交成功',
				  scrollbar: false,
				  closeBtn: 1,
				  icon: 1,
				  time: 2, //2秒后自动关闭
				  skin: 'footer',
				  end:obj
				});
		}else if(message=='update'){
			layer.open({
				  content: '修改成功',
				  scrollbar: false,
				  closeBtn: 1,
				  icon: 1,
				  time: 2, //2秒后自动关闭
				  skin: 'footer',
				  end:obj
				});
			/*layer.alert('修改成功',{
				  icon: 1
			},obj);*/
		}else if(message=='delete'){
			layer.open({
				  content: '删除成功',
				  scrollbar: false,
				  closeBtn: 1,
				  icon: 1,
				  time: 2, //2秒后自动关闭
				  skin: 'footer',
				  end:obj
				});
			/*layer.alert('删除成功',{
				  icon: 1
			},obj);*/
		}else if(message=='upload'){
			layer.open({
				  content: '上传成功',
				  scrollbar: false,
				  closeBtn: 1,
				  icon: 1,
				  time: 2, //2秒后自动关闭
				  skin: 'footer',
				  end:obj
				});
			/*layer.alert('上传成功',{
				  icon: 1
			},obj);*/
		}else if(message=='download'){
			layer.open({
				  content: '下载成功',
				  scrollbar: false,
				  closeBtn: 1,
				  icon: 1,
				  time: 2, //2秒后自动关闭
				  skin: 'footer',
				  end:obj
				});
			/*layer.alert('下载成功',{
				  icon: 1
			},obj);*/
		}else{
			layer.open({
				  content: message,
				  scrollbar: false,
				  closeBtn: 1,
				  icon: 1,
				  time: 2, //2秒后自动关闭
				  skin: 'footer',
				  end:obj
				});
			/*layer.alert(message,{
				  icon: 1
			},obj);*/
		}
	}else if(icon=='smile'){
		layer.open({
			  content: message,
			  scrollbar: false,
			  closeBtn: 1,
			  icon: 8,
			  time: 2, //2秒后自动关闭
			  skin: 'footer',
			  end:obj
			});
		/*layer.alert(message,{
			  icon: 8
		},obj);*/
	}else if(icon=='cry'){
		layer.open({
			  content: message,
			  scrollbar: false,
			  closeBtn: 1,
			  icon: 9,
			  time: 2, //2秒后自动关闭
			  skin: 'footer',
			  end:obj
			});
		/*layer.alert(message,{
			  icon: 9
		},obj);*/
	}else if(icon=='error'){
		if(message=='save'){
			layer.open({
				  content: '提交失败',
				  scrollbar: false,
				  closeBtn: 1,
				  icon: 11,
				  time: 2, //2秒后自动关闭
				  skin: 'footer',
				  end:obj
				});
			/*layer.alert('提交失败',{
				  icon: 11
			},obj);*/
		}else if(message=='update'){
			layer.open({
				  content: '修改失败',
				  scrollbar: false,
				  closeBtn: 1,
				  icon: 11,
				  time: 2, //2秒后自动关闭
				  skin: 'footer',
				  end:obj
				});
		/*	layer.alert('修改失败',{
				  icon: 11
			},obj);*/
		}else if(message=='delete'){
			layer.open({
				  content: '删除失败',
				  scrollbar: false,
				  closeBtn: 1,
				  icon: 11,
				  time: 2, //2秒后自动关闭
				  skin: 'footer',
				  end:obj
				});
			/*layer.alert('删除失败',{
				  icon: 11
			},obj);*/
		}else if(message=='upload'){
			layer.open({
				  content: '上传失败',
				  scrollbar: false,
				  closeBtn: 1,
				  icon: 11,
				  time: 2, //2秒后自动关闭
				  skin: 'footer',
				  end:obj
				});
			/*layer.alert('上传失败',{
				  icon: 11
			},obj);*/
		}else if(message=='download'){
			layer.open({
				  content: '下载失败',
				  scrollbar: false,
				  closeBtn: 1,
				  icon: 11,
				  time: 2, //2秒后自动关闭
				  skin: 'footer',
				  end:obj
				});
			/*layer.alert('下载失败',{
				  icon: 11
			},obj);*/
		}else{
			layer.open({
				  content: message,
				  scrollbar: false,
				  closeBtn: 1,
				  icon: 11,
				  time: 2, //2秒后自动关闭
				  skin: 'footer',
				  end:obj
				});
			/*layer.alert(message,{
				  icon: 11
			},obj);*/
		}
	}else if(icon=='ask'){
		layer.open({
			  content: message,
			  scrollbar: false,
			  closeBtn: 1,
			  icon: 4,
			  time: 2, //2秒后自动关闭
			  skin: 'footer',
			  end:obj
			});
		/*layer.alert(message,{
			  icon: 4
		},obj);*/
	}
	 
}
function checkFormValue(containerId){
	var falg = true;
	if(containerId==null||""==containerId)
		container = "*";
	//1 检查必填项目
	var sel = "dcrequired";
	var objs = $("#"+containerId+" [" + sel + "]");
	var postData = {};
	for (var i = 0, len = objs.length; i < len; i++) {
		var obj = objs[i];
		if($(obj).val()==""){
			
			var requiredMsg = $(obj).attr(sel)==""?"此项目不能为空!":$(obj).attr(sel);
			if(falg)
				$(obj).focus();
			falg = false;
			layer.open({
				content: ''+requiredMsg,
			    btn: ['确定']
			})
		    return falg;
		}
	}
	//2 直接读取dccheck属性
	sel = "dccheck";
	objs = $("#"+containerId+" [" + sel + "]");
	for (var i = 0, len = objs.length; i < len; i++) {
		var obj = objs[i];
		if($(obj).attr("dccheck")=='false'){
			falg = false;
			$(obj).focus();
			break;
		}
	}
	
	return falg;
}
/**
 * 本地服务调用方法 URL处理方法，自动拼接应用名
 */
var getRequestUrl = function(url) {
	if (url.substr(0, 1) != '/')
		url = "/" + url;

	if (url.indexOf("?") == -1) {
		url += "?_call_rnd=" + new Date();
	} else {
		url += "&_call_rnd=" + new Date();
	}

	return path + url+makeProcessor();
}

/**
 * 构造processor对象的提交数据
 * 注意：分页相关的参数直接在pageBarFortable中进行了构造，其它参数在此方法中完成
 */
function makeProcessor(){
	
	var commonData = parent.CsGlobalObj||parent.parent.CsGlobalObj;
	/*var orgid = commonData.sys_hidden_orgid||"";*/
	var currentUserId = commonData.currentUserid||"";
	/*var ipaddress = commonData.sys_hidden_ipaddress||"";*/
	var deviceType = commonData.sys_hidden_deviceType||"";
	var dept 	   = commonData.sys_hidden_dept||"";
	var auth 	   = commonData.sys_hidden_auth||"";
	var subdept    = commonData.sys_hidden_subdept||"";
	
	
	return "&processor.currentUserId="+currentUserId+"&processor.deviceType="+deviceType+"&processor.dept="+dept+"&processor.auth="+auth+"&processor.subdept="+subdept;
}

/**
 * 数据收集
 * containerId 指定html容器下的数据
 * */
function collectData(containerId) {
	if(containerId==null||""==containerId)
		container = "*";
	var sel = "dc";
	var objs = $("#"+containerId+" [" + sel + "]");
	var postData = {};
	
	for (var i = 0, len = objs.length; i < len; i++) {
		
		var obj = objs[i];
		var nodeName = obj.nodeName.toLowerCase();
		var field = $(obj).attr("name");

		if (nodeName == "input") {
			if (obj.type.trim() == "radio"
					&& (obj.checked || obj.checked == "checked")) {

				postData[field] = $(obj).val();
				continue;
			}
			if (obj.type.trim() == "checkbox"
					&& (obj.checked || obj.checked == "checked")) {
				var ov = postData[field] || "";
				var nv = ov + "," + $(obj).val();
				postData[field] = nv.replace(/^,+/, "");
				continue;
			}

			if (obj.type.trim() == "text" || obj.type.trim() == "hidden" || obj.type.trim() == "password") {
				postData[field] = $(obj).val();
				continue;
			}
			continue;
		}

		if (nodeName == "textarea") {
			postData[field] = $(obj).val();
			continue;
		}
		if (nodeName == "select") {
			var val = $(obj).val(); //obj.options[obj.selectedIndex].value;
			postData[field] = val;
			continue;
		}
		if (nodeName == "img") {
			postData[field] = $(obj).attr("value");
			continue;
		}
		if (nodeName == "td") {
			postData[field] = $(obj).html();
			continue;
		}
		/**
		 * update by lingy 新增富文本编辑框 start
		 * 通过取得初始化时editor的ID，取得富文本中的值
		 */
		var ueditorid = $(obj).attr("ueditorid");
		if(isNotEmpty(ueditorid)){
			var editorContent = UE.getEditor(ueditorid).getContent();
			postData[field] = editorContent;
			continue;
		}
		/************update by lingy 新增富文本编辑框 end *********************/
		postData[field] = $(obj).html();

	}
	//返回从表单获取数据的json数据 
	
	return postData;
} 
/**
 * 数据有效可用的时候返回true，否则返回false
 * @param checkData
 * @returns {Boolean}
 */
function isNotEmpty(checkData){
	if(typeof checkData=="string")
		checkData = $.trim(checkData);
	if(typeof checkData!="undefined" && checkData!=null && checkData!="" && checkData!=undefined){
		return true;
	}else{
		return false
	}
}


/**
 * 加载店内职位下拉框
 */
function queryUserRole(selectId){
	
	$.ajax({
		url:getRequestUrl("/sysRoleController/queryUserRole.json"),
		dataType:"json",
		async:false,
		success:function(result){
			if(result.roleList){
				$("#"+selectId).html("<option value=''>请选择</option>");
				for(var i=0;i<result.roleList.length;i++){
					$("#"+selectId).append("<option value='"+result.roleList[i].id+"'>"+result.roleList[i].rolename+"</option>");
				}
			}
		},
		error:function(error){
			layer.alert("error",3);
		}
	});
}

/**
 * 加载店内职位复选框
 * rolechecked为上级元素id
 * checkedname为复选框name
 * disable设置复选框是否可选
 */
function queryUserRoleChecked(rolecheckedid,checkedname,disable){
	
	$.ajax({
		url:getRequestUrl("/sysResourceController/querySysResource.json"),
		dataType:"json",
		data:{type:"0"},
		async:false,
		success:function(result){
			if(result.sysResourceList){
				var j=0;
				var k=0;
				for(var i=0;i<result.sysResourceList.length;i++){
					if(result.sysResourceList[i].type=="1"){
						j++;
						if(j%6==0){
							$("#"+rolecheckedid+"Phone").append("<br><label><input type='checkbox' name='"+checkedname+"' value='"+result.sysResourceList[i].id+"' dc='true'/> "+result.sysResourceList[i].resourcename+"</label>");
						}else{
							$("#"+rolecheckedid+"Phone").append("<label><input type='checkbox' name='"+checkedname+"' value='"+result.sysResourceList[i].id+"' dc='true'/> "+result.sysResourceList[i].resourcename+"</label>");
						}
					}else{
						k++;
						if(k%6==0){
							$("#"+rolecheckedid).append("<br><label><input type='checkbox' name='"+checkedname+"' value='"+result.sysResourceList[i].id+"' dc='true'/> "+result.sysResourceList[i].resourcename+"</label>");
						}else{
							$("#"+rolecheckedid).append("<label><input type='checkbox' name='"+checkedname+"' value='"+result.sysResourceList[i].id+"' dc='true'/> "+result.sysResourceList[i].resourcename+"</label>");
						}
					}
				}
				if(disable!=false&&disable!=undefined){
					$("input[name='"+checkedname+"']").attr('disabled',true);
				}
			}
		},
		error:function(error){
			layer.alert("error",3);
		}
	});
}

/**
 * 页面转向
 * @param page
 */
function pageForward(page){
	window.location.href= path+page;
}


function CurentTime()
{ 
    var now = new Date();
   
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
   
    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
   
    var clock = year + "-";
   
    if(month < 10)
        clock += "0";
   
    clock += month + "-";
   
    if(day < 10)
        clock += "0";
       
    clock += day + "";
   
    /*if(hh < 10)
        clock += "0";
       
    clock += hh + ":";
    if (mm < 10) clock += '0'; 
    clock += mm; */
    return(clock); 
} 