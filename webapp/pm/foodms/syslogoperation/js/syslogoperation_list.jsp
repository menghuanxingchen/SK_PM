<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
<title>日志</title>
	<script id="userShowTemplate" type="text/x-jsrender">
		 <tr>
	            	<td>{{:businessName}}</td>
					<td align="left">[{{:operationType}}] {{:memo}}</td>
					<td>{{:restaurantid}}</td>
	                <td>{{:operationTime}}</td>
	                <td>{{:operationUserName}}{{:ipaddress}}</td>
	                <td>{{:deviceType}}</td>
	                <td>
	                    <a href="javascript:;" onClick="showLogDetail('{{:logid}}')">详细</a>
	                </td>
	     </tr>       
	</script>
	
	<script id="logDetailTemplate" type="text/x-jsrender">
		 <tr>
	            	<td>{{:tablename}}</td>
					<td>{{:columnName}}</td>
	                <td>{{:colunmComt}}</td>
	                <td>{{:oldVal}}</td>
	                <td>{{:newVal}}</td>
	     </tr>       
	</script>
</head>
<body>
	    	<div class="filemenu">
                <ul class="tab_menutxt">
            		<li class="on"><span class="txt">业务日志</span></li>
            	</ul>
            </div>
            <div class="tab_box tableone">
            <div class="searchbar" id="search_DIV">
                <span>业务功能：
                	<select class="ssel"  name="sysLogOperation.businessName" id="sel_businessName" dc="true">
                		<option value=''>请选择</option>
                    </select>
                </span>
                <span>操作类型：<select class="ssel"  name="sysLogOperation.operationType" dc="true">
                		<option value=''>请选择</option>
                		<option value='新增'>新增</option>
                    	<option value='修改'>修改</option>
                        <option value='删除'>删除</option>
                    </select></span>
                <a href="#" class="oranbtn" id="search_BNT"> 查询</a>
            </div>
			<table cellpadding="0" cellspacing="0">
			<caption>业务日志</caption>
			<thead>
        	<tr>
       	  	  	<th>业务功能</th>
       	  	  	<th>操作说明</th>
       	  	  	<th>商户</th>
                <th>操作时间</th>
                <th>操作人</th>
                <th>操作设备</th>
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
                	<a class="oranbtn showbtn" href="javascript:;" onclick="truncateLog()">清空全部日志</a>
            	</span>
            </div>
            
            <div id="tb_logDetail" style="display:none">
            <table  cellpadding="0" cellspacing="0" >
			<caption>日志详情</caption>
			<thead>
        	<tr>
       	  	  	<th>数据表</th>
       	  	  	<th>商户</th>
                <th>操作时间</th>
                <th>原值</th>
                <th>新值</th>
            </tr>
			</thead>
			<tbody id="logDetail">
			</tbody>
			</table>
			</div>
			
            	<!-- 222222222222222222222222222222222222222222222222222222222222222222222222222222222222 -->
            </div>
            
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%> 
<script src="<%=request.getContextPath()%>/pm/foodms/syslogoperation/js/syslogoperation_list.js" type="text/javascript"></script>
</html>