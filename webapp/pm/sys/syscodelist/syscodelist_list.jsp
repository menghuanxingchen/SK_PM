<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
<title>日志</title>
	<script id="listShowTemplate" type="text/x-jsrender">
		 <tr>
	            	<td>{{:kindcode}}</td>
					<td>{{:kindname}}</td>
					<td>{{:kindCnt}}</td>
	                <td>
	                    <a href="javascript:;" onClick="showCodeDetail('{{:kindcode}}','{{:kindname}}')">管理</a>
	                </td>
	     </tr>       
	</script>
	
	<script id="codeDetailTemplate" type="text/x-jsrender">
		 <tr>
					<td>{{:codevalue}}</td>
	                <td>{{:codename}}</td>
	                <td>{{:parentcodevalue}}</td>
	                <td>{{:ordernum}}</td>
 					<td>{{:ext1}}</td>
					<td>{{:ext2}}</td>
 					<td><a href="javascript:;" onClick="editCode('{{:codelistid}}')">修改</a>&nbsp;<a href="javascript:;" onClick="delCode('{{:codelistid}}','{{:kindcode}}')">删除</a></td>
	     </tr>       
	</script>
</head>
<body>
	    	<div class="filemenu">
                <ul class="tab_menutxt">
            		<li class="on"><span class="txt">系统代码表</span></li>
            	</ul>
            </div>
             <div class="searchbar" id="search_DIV">
                <span>代码表编号：
                    <input type="text" class="sdsbox" name="sysCodelist.kindcode" id="kindcode" dc="true"/>
                </span>
                <span>代码表名称：<input type="text" class="sdsbox" name="sysCodelist.kindname" id="kindname" dc="true"/></span>
                <a href="#" class="oranbtn" id="search_BNT"> 查询</a>
            </div>
            
            
            <div class="tab_box tableone" style="float:left;width:100%;">
           <div style="float: left;width:45%;">
			<table cellpadding="0" cellspacing="0" >
			<caption>代码表</caption>
			<thead>
        	<tr>
       	  	  	<th>代码表编号</th>
       	  	  	<th>代码表名称</th>
       	  	  	<th>代码数量</th>
                <th>操作</th>
            </tr>
			</thead>
			<tbody id="tbody">
	        	<!-- 11111111111111111111111111111111111111111111111111111111111111111111111111111111111 -->
			</tbody>
			</table>
			<div class="page" id="page">
            	<div id='pagefoot'></div>
            	<span class="flright">
                	<a class="oranbtn showbtn" href="javascript:;" onclick="openAdd()">新建码值</a>
            	</span>
            </div>
			</div>
			<span style="display:none" id="code_search"><input type="hidden" name="sysCodelist.kindcode" id="code_kindcode" dc="true"/></span>
			<div id="div_codetable" style="width:55%;float:left;display:none">
              <table  cellpadding="0" cellspacing="0" >
				<caption id="cap_kindecode">代码值详细</caption>
				<thead>
	        	<tr>
	       	  	  	<th>代码值</th>
	                <th>代码名</th>
	                <th>上级值</th>
	                <th>排序</th>
	                <th>扩展1</th>
	                 <th>扩展2</th>
	                <th>操作</th>
	            </tr>
				</thead>
				<tbody id="codeDetail">
				</tbody>
				</table>
				<div  class="page" id="code_page">
            	<div id='code_pagefoot'></div>

            	</div>
			</div>
			
            
            	<!-- 222222222222222222222222222222222222222222222222222222222222222222222222222222222222 -->
            </div>
            

            
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%> 
<script src="<%=request.getContextPath()%>/pm/sys/syscodelist/js/syscodelist_list.js" type="text/javascript"></script>
</html>