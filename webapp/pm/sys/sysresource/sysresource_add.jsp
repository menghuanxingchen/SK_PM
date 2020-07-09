<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
<title>资源管理</title>
</head>
<body>
            
            <div class="tab_box tableone" style="float:left;width:100%;">
              <table >
				<caption id="edit_cap">创建新菜单</caption>
				<thead>
				</thead>
				<tbody id="editFormTable">
				</tbody>
				</table>
			 </div>
</body>
<script id="editFormTemplate" type="text/x-jsrender">
			<input type="hidden" id="edit_parentid" name="sysResource.parentid" class="sdsbox" dc="true"  value="{{:id}}"/>
				<tr>
					<td>菜单ID</td>
	                <td><input type="text" name="sysResource.id"  id="edit_resid" class="sdsbox" dc="true"  value="" placeholder="不写ID系统将自动生成"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td>菜单名称</td>
	                <td><input type="text" name="sysResource.resourcename"  id="edit_resourcename" class="sdsbox" dc="true" dcrequired="菜单名称" value=""/></td>
	     		</tr>
	     		<tr>
					<td>类型</td>
	                <td><select name="sysResource.type" id="edit_type" style="width:145px;" class="ssel" dc="true" dcrequired="菜单类型"><option value="">请选择</option><option value="0">PC端功能</option><option value="1">手机端功能</option></select></td>
	                <td>启用/禁用</td>
	                <td><select name="sysResource.enabled" id="edit_enabled" style="width:145px;" class="ssel" dc="true" dcrequired="启用/禁用"><option value="">请选择</option><option value="1">启用</option><option value="0">禁用</option></select></td>
	     		</tr> 
				<tr>
					<td>排序值</td>
	                <td><input name="sysResource.sort" id="edit_sort" style="width:145px;" class="ssel" dc="true" dcrequired="排序值" dctype="数字" value="1"/></td>
	                <td>上级菜单</td>
	                <td>{{:resourcename}}</td>
	     		</tr>
	     		<tr>
					<td>菜单URL</td>
	                <td colspan="3"><input type="text" style="width:440px;" name="sysResource.url"  id="edit_url" class="sdsbox" dc="true" dcrequired="菜单URL" value=""/></td>
	     		</tr> 
	     		<tr>
					<td>描述</td>
	                <td colspan="3"><input type="text" style="width:440px;" name="sysResource.description"  id="edit_description" class="sdsbox" dc="true"  value=""/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	     		</tr> 
				<tr>
            			<td align="right" colspan="4">
                		<a href="javascript:;" class="oranbtn close" id="submit" onClick="saveItem()">保 存</a>
						<a href="javascript:;" class="oranbtn close" id="submit" onClick="parent.closeLayer();">取 消</a>
                		</td>
            	</tr>
</script>

<%@ include file="/pm/common/jsp/commonJs.jsp"%> 
<script src="<%=request.getContextPath()%>/pm/sys/sysresource/js/sysresource_add.js" type="text/javascript"></script>

</html>