<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
<title>添加员工信息</title>

</head>
<body>
<div class="showbox" id="showbox">
	  <form method="" action="" id="formadd">
		<table cellpadding="0" cellspacing="0">
					<caption>维护代码表数据</caption>
                    <tr>
            		<td align="right" width="80">代码表编号(kindcode)</td>
                	<td align="left" width="190"><input type="text" name="sysCodelist.kindcode" id="kindcode" class="sdsbox" dc="true" dcrequired="代码表编号" dctype="数字或字母"/></td>
                	<td align="right" width="80">代码表名称(kindname)</td>
                	<td align="left" width="190"><input type="text" name="sysCodelist.kindname"  id="kindname" class="sdsbox" dc="true" dcrequired="代码表名称" /></td>
            		</tr>
                    <tr>
            		<td align="right">代码值(codevalue)</td>
                	<td align="left">
						<input type="text" class="sdsbox" name="sysCodelist.codevalue" dc="true" dcrequired="请录入代码值" dctype="数字或字母"/>
                    </td>
                	<td align="right">代码名(codename)</td>
                	<td align="left"><input type="text" class="sdsbox dtpicker" name="sysCodelist.codename" dc="true" dcrequired="请录入代码名" /></td>
            		</tr>
                    <tr>
                	<td align="right">上级代码值</td>
                	<td align="left"><input type="text" class="sdsbox" name="sysCodelist.parentcodevalue" dc="true"/></td>
                	<td align="right">排序值</td>
                	<td align="left"><input type="text" class="sdsbox" name="sysCodelist.ordernum" dc="true" dcrequired="请录入排序值" dctype="数字"/></td>
            		</tr>
            		
            		<tr>
                	<td align="right">扩展1</td>
                	<td align="left"><input type="text" class="sdsbox" name="sysCodelist.ext1" dc="true"/></td>
                	<td align="right">扩展2</td>
                	<td align="left"><input type="text" class="sdsbox" name="sysCodelist.ext2" dc="true" /></td>
            		</tr>
            		
            		<tr>
            			<td align="right" colspan="4">
                		<a href="javascript:;" class="oranbtn close" id="submit" onClick="addCode()">确定</a>
                		<a href="javascript:;" class="oranbtn close" id="close" onClick="parent.closeLayer();">关闭</a>
                		</td>
            		</tr>
				</table>
			</form>
</div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%> 
<script src="<%=request.getContextPath()%>/pm/sys/syscodelist/js/syscodelist_add.js" type="text/javascript"></script>
</html>