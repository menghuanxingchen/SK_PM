$(function(){
	$("#excute").bind('click',function(){
		var url = path + $(this).attr("operaurl");
		var paramsData = collectData("docBody");
		$.ajax({
			url:url,
			dataType:"json",
			data:paramsData,
			success:function(result){
				var dataJsonStr = JSON.stringify(result);
				$("#resultBody").html(dataJsonStr);
			},
			error:function(error){
				alert("error");
			}
		});
	});
})




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