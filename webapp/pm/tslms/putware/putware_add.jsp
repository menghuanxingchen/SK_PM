<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>入库信息录入</title>
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
    <li><label>流水号</label><input name="tsLmsPutware.putid" dcrequired="请输入流水号" type="text" class="dfinput" value="" dc="" /></li>
    <li><label>入库单号</label><input name="tsLmsPutware.putno"  type="text" class="dfinput" value="" dc="" /></li>
	<li><label>仓库</label><input name="tsLmsPutware.warename"  type="text" class="dfinput" value="" dc="" /></li>
	<li><label>客户</label><input name="tsLmsPutware.customer"  type="text" class="dfinput" value="" dc="" /></li>
    <li><label>部门</label><input name="tsLmsPutware.part"  type="text" class="dfinput" value="" dc="" /></li>
	<li><label>物料规格</label><input name="tsLmsPutware.materialtype"  type="text" class="dfinput" value="" dc="" /></li>
	<li><label>商品名称</label><input name="tsLmsPutware.materialname"  type="text" class="dfinput" value="" dc="" /></li>
	<li><label>批次</label><input name="tsLmsPutware.matercount"  type="text" class="dfinput" value="" dc="" /></li>
	<li><label>入库时间</label><input name="tsLmsPutware.puttime" type="text" class="dfinput datepicker" value="" dc="" /></li>
	<li><label>入库数量</label><input name="tsLmsPutware.putcount" dcrequired="请输入数字" dctype="数字" type="text" class="dfinput" value="" dc="" /></li>
    </ul>
</script>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="javascript:;">入库管理</a></li>
    <li><a href="javascript:;">录入入库信息</a></li>
    </ul>
    </div>
    <div class="formbody" id="formId">
    </div>
<div style="margin:0 auto;text-align: center;padding-bottom: 20px;">
<input name="" type="button" id="submitBtn" class="btn" value="创建"/>&nbsp;&nbsp;&nbsp;&nbsp;<input name="" id="closeBtn" type="button" class="btn" value="返回"/>
</div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/tslms/putware/js/putware_add.js"></script>
</html>