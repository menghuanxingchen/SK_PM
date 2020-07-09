<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="<%=request.getContextPath()%>/pm/common/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath() %>/pm/common/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript">
	/*
		module : 业务模块
		fileType : 文件类型
		urlId : url隐藏控件的ID
		uploadify_callback ：回调函数，返回值为urlId(上传控件的ID),json格式的file对象（包含文件信息），jsonData（包含文件路径）
	*/
	var uploadifyNum = 1;
	//初始化Uploadify实例
	function uploadifyInit(module,fileType,urlId,uploadify_callback){
		if(fileType==null||$.trim(fileType)==""
		    ||module==null||$.trim(module)==""
		    ||urlId==null||$.trim(urlId)=="") {
		    alert("文件插件创建失败");
			return;
		}
		uploadifyInitFromParameters(module,fileType,urlId,uploadify_callback);
	}
	function uploadifyInitBtn(module,fileType,urlId,uploadify_callback){
		uploadifyInit(module,fileType,urlId,uploadify_callback);
		$("#"+urlId).siblings("div.uploadify").toggleClass("innerdiv");
	}
	function uploadexcelBtn(urlId,uploadify_callback){
		uploadifyInitForExcel(urlId, uploadify_callback);
		$("#"+urlId).siblings("div.uploadify").toggleClass("innerdiv");
	}
	//初始化Uploadify实例(xls文件)
	function uploadifyInitForExcel(urlId,uploadify_callback){
		if(urlId==null||$.trim(urlId)=="") {
			alert("文件插件创建失败");
			return;
		}
		uploadifyInitFromParameters("excel","excel",urlId,uploadify_callback);
	}
	//根据参数，初始化Uploadify实例
	function uploadifyInitFromParameters(module,fileType,urlId,uploadify_callback) {
		urlId = urlId.replace(".","\\\\.");
		var fileTypeExts = "";
		var fileSizeLimit = "";
		var controllerString = "";
		var controllerId = "";
		var buttonText = "upload";
		var width = "";
		var height="20";
		var buttonClass="";
		if(fileType == "excel"){
			buttonClass="btn1 upload";
		}else if(fileType == "file"){
			buttonClass="ipt_button1";
		}else{
			buttonClass="btn1 upload";
		}
		//根据文件类型生成参数
		switch(fileType)
			{
				case "image"://图片
				  	fileTypeExts = "*.gif; *.jpg; *.jpeg; *.png; *.bmp;";
				  	fileSizeLimit = "2MB";
				  	controllerString = '<img id="uploadify'+uploadifyNum+'ImageId"'
				  					  +' src="<%=request.getContextPath()%>/pm/common/uploadify/register_pic_01_07.jpg" '
				  					  +'width="150px" height="100px" '
				  					  +'onclick="showUploadifyImg(\'uploadify'+uploadifyNum+'ImageId\')"/>';
				  	width = "50"
				  	break;
				
				case "file"://文件
				  	fileTypeExts = "*.doc; *.docx; *.xls; *.xlsx; *.ppt; *.pptx; *.pdf; *.rar; *.zip;";
				  	fileSizeLimit = "5MB";
				  	height="30";
				  	buttonText = "附件上传";
				  	break;
				case "upload"://文件
				  	fileTypeExts = "*.doc; *.docx; *.xls; *.xlsx; *.ppt; *.pptx; *.pdf; *.rar; *.zip;*.gif; *.jpg; *.jpeg; *.png; *.bmp;";
				  	fileSizeLimit = "5MB";
				  	width = "50"
				  	break; 	
				case "office"://办公文件
				  	fileTypeExts = "*.doc; *.docx; *.xls; *.xlsx; *.ppt; *.pptx; *.pdf;";
				  	fileSizeLimit = "5MB";
				  	width = "50"
				  	break;
				  	
				case "compress"://压缩包
				  	fileTypeExts = "*.rar; *.zip;";
				  	fileSizeLimit = "5MB";
				  	width = "50"
				  	break;
				  	
				case "excel"://excel文件
				  	fileTypeExts = "*.xls;";
				  	fileSizeLimit = "2MB";
				  	buttonText = "导入";
					width = "40";
					height="24";
				  	break;
				default:
	        		return;
			}
		var commonData = parent.CsGlobalObj||parent.parent.CsGlobalObj;
		//var userid = commonData.currentUserid||"";
		var userid = "admin";
		//生成原始文件控件
		controllerString += '<input type="file" name="uploadify" id="uploadify'+uploadifyNum+'"/>';
		$("#"+urlId).after(controllerString);
		//生成uploadify文件控件
		$("#uploadify"+uploadifyNum).uploadify({
			'fileSizeLimit'		: fileSizeLimit,
	        'fileTypeExts' 		: fileTypeExts,
	        'swf'       		: '<%=request.getContextPath()%>/pm/common/uploadify/uploadify.swf',
	        'uploader'          : '<%=request.getContextPath()%>/UploadifyController/uploadTempFile.do',//后台处理的请求
	        'buttonText'     	: buttonText,
	        //'buttonImage'     	: '< %=request.getContextPath()%>/pm/common/uploadify/haha.jpg',
	        'width'     		: width,
	        'height'     		: height,
	        'buttonClass'     	: buttonClass,
	        //'buttonClass'     	: 'btn1 upload',
	        'fileObjName'		: "uploadify",
	        'multi' 			: false,
	        'removeCompleted' 	: false,
	        'formData' 			: {"uploadifyVo.module":module,"uploadifyVo.userid":userid, "uploadifyVo.handler":"<%=session.getId()%>"+uploadifyNum},
	        'onInit' 			: function(instance) {
	        	$("#uploadify"+uploadifyNum).mouseover(function(){
	        		controllerId = this.id;
	        	});
	        	uploadifyNum ++;
	        },
			'onUploadSuccess' 	: function(file,data,response) {
				if(response==true) {
					if($("#"+controllerId+"-queue>div").size()>1) {
						$("#"+controllerId+"-queue div:first").remove();
					}
					var jsonData = jQuery.parseJSON(data);
					//alert("id:" + file.id + " -索引:" + file.index + " -文件名称:" + file.name + " -文件大小:" + file.size + " -文件类型:" + file.type + " -创建日期:" + file.creationdate + " -修改日期:" + file.modificationdate + " -文件状态:" + file.filestatus + " –服务器端消息:" + data + " –是否上传成功:" + response);
					$("#"+urlId).val(jsonData.url);
					$("#"+controllerId+"ImageId").prop("src","<%=request.getContextPath()%>"+jsonData.url);
					if(uploadify_callback!=null) {
						uploadify_callback(urlId,file,jsonData);
					}
				}
			},
			'onFallback' 		: function() {
	            alert('没有检测到Flash插件，为了不影响使用，请安装Flash插件');
	        }
	    });
	}
	//销毁所有Uploadify实例
	function uploadifyDestroy() {
		try {
			for(var i=1; i<=uploadifyNum; i++) {
				if($("#uploadify"+i).length>0) {
					$("#uploadify"+i).uploadify('destroy');
				}
			}
			uploadifyNum = 1;
        } catch(e) {
        }
	}
	//显示图片(通过ID)
	function showUploadifyImg(id) {
		window.open($("#"+id).prop("src"));
	}
	//显示图片(通过URL)
	function openImgInNewDialog(url) {
		window.open(url);
	}
	//把URL格式化成原始文件名
	function formatUrl2FileName(url) {
		if(url==null||$.trim(url)=="") {
			return "";
		} else {
			var fileName = url.substring(url.lastIndexOf("/")+1);
			var name = fileName.substring(0, fileName.lastIndexOf("_"));
			var suffix = fileName.substring(fileName.lastIndexOf(".")+1);
			return name + "." + suffix;
		}
	}
	//把URL格式化成带有日期的文件名
	function formatUrl2FileNameDate(url) {
		if(url==null||$.trim(url)=="") {
			return "";
		} else {
			var fileName = url.substring(url.lastIndexOf("/")+1);
			return fileName;
		}
	}
</script>