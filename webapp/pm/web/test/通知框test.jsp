<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>通知框test</title>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
</head>
<body>
<button id="test1">小小提示层</button>

<script>

$('#test1').on('click', function(){
  //layer.msg('Hello layer');
 lalert('通知','warn');
});


</script>
</body>
</html>