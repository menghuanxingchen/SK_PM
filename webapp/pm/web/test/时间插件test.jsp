<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>时间插件test</title>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
</head>
<body>
<input id="hello" class="laydate-icon-danlan">
<script>
laydate({
  elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
  event: 'focus' //响应事件。如果没有传入event，则按照默认的click
});
</script>
</body>
</html>