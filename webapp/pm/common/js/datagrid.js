
var cn = 0;//grid提交后台的计数器
/**
 * 单个修改table中数据
 * @param containerId ：当前容器
 * @param itemList ：后台接收list
 * @param resultSelectData ：下拉列表数据
 */
function updateTableDataFun(containerId,itemList,resultSelectData){
	var sel = "tdtype",optionValue="optionValue",optionName="optionName",selectedData="selectedData",tdname="tdname",requiredcheck="requiredcheck"
	var objs = $(containerId).parents("td").siblings("td["+sel+"]");
	for (var i = 0, len = objs.length; i < len; i++) {
		var obj = objs[i];
		switch($(obj).attr(sel)){
			case "input":
				if($(obj).text()==""){
					$(obj).html('<input id="inp" dc=""  name="'+itemList+'['+cn+'].'+$(obj).attr(tdname)+'" value="'+$(obj).find("input").val()+'">');
				}else{
					$(obj).html('<input id="inp" dc=""  name="'+itemList+'['+cn+'].'+$(obj).attr(tdname)+'" value="'+$(obj).text()+'">');
				}
		      break;
			case "select":
				var s = renderOptionFun(resultSelectData,$(obj).attr(optionValue),$(obj).attr(optionName),$(obj).attr(selectedData));
				$(obj).html('<select dc="" name="'+itemList+'['+cn+'].'+$(obj).attr(tdname)+'">'+s+'</select>');
				break;
			case "textarea":
				$(obj).html('<textarea dc="" name="'+itemList+'['+cn+'].'+$(obj).attr(tdname)+'">'+$(obj).text()+'</textarea>');
				break;
			case "data":
				if($(obj).text()==""){
					$(obj).html('<input dc="" class="dfinput datepicker" name="'+itemList+'['+cn+'].'+$(obj).attr(tdname)+'" value="'+$(obj).find("input").val()+'">');
				}else{
					$(obj).html('<input dc="" class="dfinput datepicker" name="'+itemList+'['+cn+'].'+$(obj).attr(tdname)+'" value="'+$(obj).text()+'">');
				}
				break;
		}
	}
	cn++;
}


/**
 * 批量修改
 * @param checkRemoveName :checked name属性值
 * @param itemList ： 后台接收list名称
 */
function batchUpdateTableDataFun(checkUpdateName,itemList){
	var sel = "tdtype",optionValue="optionValue",optionName="optionName",selectedData="selectedData",tdname="tdname"
	$('input[name="'+checkUpdateName+'"]:checked').each(function(){ 
		var objs = $(this).parents("td").siblings("td["+sel+"]");
		for (var i = 0, len = objs.length; i < len; i++) {
			var obj = objs[i];
			switch($(obj).attr(sel)){
				case "input":
					if($(obj).text()==""){
						$(obj).html('<input dc="" type="text" name="'+itemList+'['+cn+'].'+$(obj).attr(tdname)+'" value="'+$(obj).find("input").val()+'">');
					}else{
						$(obj).html('<input dc="" type="text" name="'+itemList+'['+cn+'].'+$(obj).attr(tdname)+'" value="'+$(obj).text()+'">');
					}
					break;
				case "select":
					var s = renderOptionFun(resultSelectData,$(obj).attr(optionValue),$(obj).attr(optionName),$(obj).attr(selectedData));
					$(obj).html('<select dc="" name="'+itemList+'['+cn+'].'+$(obj).attr(tdname)+'">'+s+'</select>');
					break;
				case "textarea":
					$(obj).html('<textarea dc="" name="'+itemList+'['+cn+'].'+$(obj).attr(tdname)+'">'+$(obj).text()+'</textarea>');
					break;
				case "data":
					if($(obj).text()==""){
						$(obj).html('<input dc="" class="dfinput datepicker" name="'+itemList+'['+cn+'].'+$(obj).attr(tdname)+'" value="'+$(obj).find("input").val()+'">');
					}else{
						$(obj).html('<input dc="" class="dfinput datepicker" name="'+itemList+'['+cn+'].'+$(obj).attr(tdname)+'" value="'+$(obj).text()+'">');
					}
					break;
			}
		}
		cn++;
	});
}

