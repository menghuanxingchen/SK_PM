$(function(){

	initPage();
	bindevent();
	
});

/**
 * 初始化页面
 * */
function initPage(){
	querySysResourceTree();
}



var settingjson = {
		data: {
			view: {
				dblClickExpand: false,
				showLine: true,
				selectedMulti: false
			},
			key: {
				title:"resourcename",
				name:"resourcename",
				url:"noURL"
			},
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "parentid",
				rootPId: ""
			}
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				
				showEditNode(treeNode);
			}
		}

	};

function showEditNode(treeNode){
	$("#cap_kindecode").html("["+treeNode.resourcename+"] 菜单资源定义");
	$( "#resourceFormTable" ).html($("#resourceFormTemplate").render(treeNode));
	$("#type").val(treeNode.type);
	$("#enabled").val(treeNode.enabled);
	bindValidateEvent("resourceFormTable");
	
	curentNodeId = treeNode.id;
	
	if(!treeNode.isParent){
		$("#a_addItem").hide();
	}else{
		$("#a_delItem").hide();
	}
}

function showResNode(id){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var node = treeObj.getNodeByTId(id);
	treeObj.selectNode(node,false);
	
	showEditNode(node);
}



function querySysResourceTree(logID){
	
	$.ajax({
		url:getRequestUrl("/sysResourceController/querySysResourceTree.json"),
		dataType:"json",
//		data:{"sysLogOperation.logid":logID},
		async:false,
		success:function(result){

 			
 			var treeObj = $.fn.zTree.init($("#treeDemo"), settingjson, result.data);
 			treeObj.expandAll(true);
 			showResNode("1");
 			
		},
		error:function(error){
			layer.alert("error",3);
		}
	});
	
}

var _layer_index;

function closeLayer(){
	layer.close(_layer_index);
}

var curentNodeId;

function addNewItem(){
	 
	var id = curentNodeId ;
	 _layer_index = $.layer({
		    type: 2,
		    title:false,
		    area: ['600px' , '385px'],
		    bgcolor: '',
		    shadeClose: true,
		    data:id,
		    closeBtn: false,
		    border: [0],
		    iframe: {src: getRequestUrl('/pm/sys/sysresource/sysresource_add.jsp')}
		});
}




function saveEdit(){
	var checkfalg =	checkFormValue('resourceFormTable') ;
	if(checkfalg){
		var data = collectData("resourceFormTable");
		$.ajax({
			url:getRequestUrl("/sysResourceController/editResource.json"),
			dataType:"json",
			data:data,
			success:function(result){
				if(result.opflag){
					layer.msg(result.opmessage,2,1);
					querySysResourceTree();//刷新主页
					showResNode(resutl.data.id);
				}else{
					layer.alert(result.opmessage);
				}
			},
			error:function(error){
				layer.alert(error,3);
			}
		});
	}
}


function saveDel(delId){
	
	layer.confirm('确定删除此条数据吗?', function(){ 
	$.ajax({
		url:getRequestUrl("/sysResourceController/deleteResource.json"),
		dataType:"json",
		data:{"sysResource.id":delId},
		success:function(result){
			if(result.opflag){
				layer.msg(result.opmessage,2,1);
				querySysResourceTree();//刷新主页
				
			}else{
				layer.alert(result.opmessage);
			}
		},
		error:function(error){
			layer.alert(error,3);
		}
	});
	});
}
