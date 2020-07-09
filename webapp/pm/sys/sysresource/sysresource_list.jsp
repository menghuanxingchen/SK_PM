<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/pm/common/css/zTreeStyle/zTreeStyle.css" type="text/css">
<title>资源管理</title>
</head>
<body>


	    	<div class="filemenu">
                <ul class="tab_menutxt">
            		<li class="on"><span class="txt">菜单资源表</span></li>
            	</ul>
            </div>

            
            
            <div class="tab_box tableone" style="float:left;width:100%;">
           <div style="float: left;width:45%;">
            <table  cellpadding="0" cellspacing="0" style="width:90%">
            <tr>
            <td>
			<div >
			<ul id="treeDemo" class="ztree"></ul>
			</div>
			</td>
			</tr>
			</table>
			</div>
			<div id="div_codetable" style="width:55%;float:left;">
              <table  cellpadding="0" cellspacing="0" >
				<caption id="cap_kindecode">菜单资源定义</caption>
				<thead>
				</thead>
				<tbody id="resourceFormTable">
				</tbody>
				</table>
				<div  class="page" id="code_page">
            	<div id='code_pagefoot'></div>

            	</div>
			</div>
			
            </div>
            

           

            
</body>

<script id="resourceFormTemplate" type="text/x-jsrender">
			<input type="hidden" name="sysResource.parentid" class="sdsbox" dc="true" value="{{:parentid}}"/>
				<tr>
					<td>菜单ID</td>
	                <td><input type="text" name="sysResource.id"  id="kindname" class="sdsbox" dc="true" dcrequired="菜单编号" value="{{:id}}"/></td>
	                <td>菜单名称</td>
	                <td><input type="text" name="sysResource.resourcename"  id="resourcename" class="sdsbox" dc="true" dcrequired="菜单名称" value="{{:resourcename}}"/></td>
	     		</tr>
	     		<tr>
					<td>类型</td>
	                <td><select name="sysResource.type" id="type" style="width:145px;" class="ssel" dc="true" dcrequired="菜单类型"><option value="">请选择</option><option value="0">PC端功能</option><option value="1">手机端功能</option></select></td>
	                <td>启用/禁用</td>
	                <td><select name="sysResource.enabled" id="enabled" style="width:145px;" class="ssel" dc="true" dcrequired="启用/禁用"><option value="">请选择</option><option value="1">启用</option><option value="0">禁用</option></select></td>
	     		</tr> 
				<tr>
					<td>排序值</td>
	                <td><input name="sysResource.sort" id="edit_sort" style="width:145px;" class="ssel" dc="true" dcrequired="排序值" dctype="数字" value='{{:sort}}'/></td>
	                <td></td>
	                <td></td>
	     		</tr>
	     		<tr>
					<td>菜单URL</td>
	                <td colspan="3"><input type="text" style="width:440px;" name="sysResource.url"  id="kindname" class="sdsbox" dc="true" dcrequired="菜单URL" value="{{:url}}"/></td>
	     		</tr> 
	     		<tr>
					<td>描述</td>
	                <td colspan="3"><input type="text" style="width:440px;" name="sysResource.description"  id="description" class="sdsbox" dc="true"  value="{{:description}}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	     		</tr> 
				<tr>
            			<td align="right" colspan="4">
                		<a href="javascript:;" class="oranbtn close" id="submit" onClick="saveEdit()">保 存</a>
						<a id="a_delItem" href="javascript:;" class="oranbtn close" id="submit" onClick="saveDel('{{:id}}')">删 除</a>
						<a id="a_addItem"  href="javascript:;" class="oranbtn close" id="submit" onClick="addNewItem('{{:id}}')">+新建子菜单</a>
                		</td>
            	</tr>
</script>
<%@ include file="/pm/common/jsp/commonJs.jsp"%> 
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/common/js/jquery.ztree.all-3.5.js"></script>
<script src="<%=request.getContextPath()%>/pm/sys/sysresource/js/sysresource_list.js" type="text/javascript"></script>

</html>