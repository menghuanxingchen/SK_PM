$.views.converters({
	"transcode":function(codevalue){
		var kind = this.tagCtx.props.kind;
		if(typeof kind=='undefined' || kind=="" || kind==null){
			return codevalue;
		}
		kind = $.trim(kind);
		var codeList = codeListObj[kind];
		if( typeof codeList=='undefined' || codeList=="" || codeList==null || codeList==undefined ){
			$.ajax({
				type : "POST",
				dataType : "json",
				url : path+"/transcodeController/transcodeJson.json",
				data :"kind="+ kind,
				async: false,
				success : function(data) {
					codeListObj[kind] = data.codeData[kind]||{};
					codeList = data.codeData[kind]||{};
				},
				error : function(data) {
					//alert("异常信息：" + msg);
				}
			});
		}
		var codename = codeList[codevalue];
		if(typeof codename == 'undefined'||codename==''||codename==null){
			codename = codevalue;
		}
		var valStr = "<span style='display:none;'>"+codevalue+"</span>";
		valStr = valStr+codename;
		return valStr;
	}
});

//列表中图标转换模版
$.views.converters({
	"iconClass":function(enable){
		if(enable==1){
			
			return "class='icon-right'";
		}else{
			
			return "class='icon-cancel'";
		}
	}
});

//停用／启用
$.views.converters({
	"stoporstart":function(enable){
		if(enable==1){
			
			return "停用";
		}else{
			
			return "启用";
		}
	}
});

/**
 * 根据给定数据列表，显示和展示的属性
 * @param optionDataList
 * @param optionValue
 * @param optionName
 * @param selectedData
 * @returns {String}
 */
function renderOptionFun(optionDataList,optionValue,optionName,selectedData,isflag){
	var html = "";
	if(isflag == ""){
		html = "<option value=''>-请选择-</option>";
	}else if(isflag == "noflag"){
		html = "";
	}else{
		html = "<option value=''>-"+isflag+"-</option>";
	}
	
	if(isNotEmpty(optionDataList)){
		for(var i = 0; i < optionDataList.length; i++){
			var realvalue = optionDataList[i][optionValue];
			var showname = optionDataList[i][optionName];
			var isChecked = realvalue == selectedData?"selected":"";
			html +="<option value='"+realvalue+"' "+isChecked+">"+showname+"</option>";
		}
	}
	return html;
}

//注册jsrender标签
$.views.tags("renderOptionFun", renderOptionFun);

/**
 * 
 */
$.views.tags({
	"rootpath":function(){
		return path;
	}
});
/**
 * 1.formatNum:去掉float型无用的0
 * 2.
 */
$.views.helpers({
    "formatNum": function(val){
    	if(val == null || (val != "0" && val == "" )){
    		return null;
    	}else{
    		return parseFloat(val);
    	}
    },
    "formatRate": function(val){
    	if(val == null || (val != "0" && val == "" )){
    		return null;
    	}else{
    		var rateNum = parseFloat(val) * 100;
    		var rate = rateNum.toFixed(0);
    		return rate+"%";
    	}
    }
 });