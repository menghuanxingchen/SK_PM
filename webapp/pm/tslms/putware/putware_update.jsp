<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改入库信息</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
</head>
<style type="text/css">
	.forminfo li{
		display:inline-flex;
	}
	.forminfo li label{
		padding-left: 10px;
	}
	.dfinput{
		width:235px !important;
	}
</style>
<script type="text/x-jsrender" id="formTemplate">
 	<div class="formtitle"><span>入库信息</span></div>
    <ul class="forminfo">
    <li><label>入库单号</label><input name="tsLmsPutware.putno"  type="text" class="dfinput" value="{{:tsLmsPutware.putno}}" dc="" /></li>
	<li><label>仓库</label><input name="tsLmsPutware.warename"  type="text" class="dfinput" value="{{:tsLmsPutware.warename}}" dc="" /></li>
	<li><label>客户</label><input name="tsLmsPutware.customer"  type="text" class="dfinput" value="{{:tsLmsPutware.customer}}" dc="" /></li>
    <li><label>部门</label><input name="tsLmsPutware.part"  type="text" class="dfinput" value="{{:tsLmsPutware.part}}" dc="" /></li>
	<li><label>物料规格</label><input name="tsLmsPutware.materialtype"  type="text" class="dfinput" value="{{:tsLmsPutware.materialtype}}" dc="" /></li>
	<li><label>商品名称</label><input name="tsLmsPutware.materialname"  type="text" class="dfinput" value="{{:tsLmsPutware.materialname}}" dc="" /></li>
	<li><label>批次</label><input name="tsLmsPutware.matercount"  type="text" class="dfinput" value="{{:tsLmsPutware.matercount}}" dc="" /></li>
	<li><label>入库时间</label><input name="tsLmsPutware.puttime" type="text" class="dfinput datepicker" value="{{:tsLmsPutware.puttime}}" dc="" /></li>
	<li><label>入库数量</label><input name="tsLmsPutware.putcount"  type="text" class="dfinput" value="{{:tsLmsPutware.putcount}}" dc="" /></li>
    </ul>
    <input name="tsLmsPutware.putid" type="hidden" class="dfinput" value="{{:tsLmsPutware.putid}}" dc="" />
</script>
<script type="text/javascript">
	var putid = '<%=request.getParameter("putid")%>';
</script>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:;">入库管理</a></li>
    <li><a href="javascript:;">修改入库信息</a></li>
    </ul>
    </div>
    <div class="formbody" id="formId">
    </div>
<div style="margin:0 auto;text-align: center;padding-bottom: 20px;">
<input name="" type="button" id="submitBtn" class="btn" value="创建"/>&nbsp;&nbsp;&nbsp;&nbsp;<input name="" id="closeBtn" type="button" class="btn" value="返回"/>
</div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/tslms/putware/js/putware_update.js"></script>
</html>