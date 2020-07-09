<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>入库信息列表</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
</head>
<script type="text/x-jsrender" id="dataListTemplate">
        <tr>
        <td><input name="checkboxGroupId" type="checkbox" value="{{:putid}}" /></td>
        <td>{{:putno}}</td>
        <td>{{:warename}}</td>
        <td>{{:customer}}</td>
        <td>{{:part}}</td>
		<td>{{:materialtype}}</td>
        <td>{{:materialname}}</td>
        <td>{{:matercount}}</td>
        <td>{{:puttime}}</td>
        <td>{{:putcount}}</td>
        <td><a href="javascript:updateEntityDataFun('{{:putid}}');" class="tablelink">修改</a>     <a href="javascript:deleteDataFun('{{:putid}}');" class="tablelink"> 删除</a></td>
        </tr>
</script>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:;">入库管理</a></li>
    <li><a href="javascript:;">入库信息</a></li>
    </ul>
    </div>
    <div class="rightinfo">
    <div class="tools">
    <ul class="seachform" style="display:inline !important;" id="search_area">
    <li><label>入库单号</label><input name="tsLmsPutware.putno" type="text" class="dfinput" placeholder="入库单号" dc="true"/></li>
    <li><label>仓库</label><input name="tsLmsPutware.warename" type="text" class="dfinput" placeholder="仓库" dc="true"/></li>
    <li><label>&nbsp;</label><input name="" type="button" class="scbtn" id="search_btn" value="查询"/></li>
    </ul>
    	<ul class="toolbar1">
        <li><a href="javascript:addEntityDataFun();" target="rightFrame" ><span><img src="<%=request.getContextPath() %>/pm/common/images/t01.png" /></span>录入入库信息</a></li>
        </ul>
    </div>
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input  type="checkbox" value="" id="checkboxGroupId"/></th>
        <th>入库单号</th>
        <th>仓库</th>
        <th>客户</th>
        <th>部门</th>
        <th>物料规格</th>
        <th>商品名称</th>
        <th>批次</th>
        <th>入库时间</th>
        <th>入库数量</th>
        <th>操作</th>
        </tr>
        </thead>
         <tbody id="dataListId">
        </tbody>
    </table>
    <div class="pagin" id="pagefoot">
    </div>
    </div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/tslms/putware/js/putware_list.js"></script>
</html>