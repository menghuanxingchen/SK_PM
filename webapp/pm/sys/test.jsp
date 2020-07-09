<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<%@ include file="/pm/common/jsp/commonJs.jsp"%> 
</head>
<body>
表单提交

<div id="studentDiv">
<form action="<%=request.getContextPath() %>/employeeManagerController/FormObjCvtForm.json" method="post"> 
    <input type="text" name="student.name" value="luandong" dc /> 
    <select name="student.id" dc>
    	<option value="100" selected>100</option>
    	<option value="200">200</option>
    </select>
	<textarea name="student.email" dc rows="" cols="">我是深知你好!</textarea>
	<input type="checkbox" name="student.chk" dc value="001"/>001 &nbsp;<input type="checkbox" name="student.chk" dc value="002"/>002
     
</form>
</div>
<hr>
ajax json提交
<div id="testUserDiv">
<form action="<%=request.getContextPath() %>/employeeManagerController/FormObjCvtForm2.json" method="post"> 
    <input type="text" name="testUser.name" value="user2"  dc /> 
    <input type="text" name="testUser.age"   value="1500"   dc /> 
</form>
</div>

<div id="studentListDiv">
    <input type="text" name="list[0].logid" value="user1"  dc /> 
    <input type="text" name="list[0].businessName"   value="15001"   dc /> <BR/>
    
        <input type="text" name="list[1].logid" value="user2"  dc /> 
    <input type="text" name="list[1].businessName"   value="15002"   dc /> <BR/>
    
        <input type="text" name="list[2].logid" value="user3"  dc /> 
    <input type="text" name="list[2].businessName"   value="15003"   dc /> <BR/>
</div>

<input type="button" value="多个实体对象提交" onclick="ajaxPost()"/> 

<input type="button" value="List批量对象提交" onclick="ajaxPostList()"/> 

<%@ include file="/pm/common/jsp/commonJs.jsp"%> 
<script>

function ajaxPost(){
	
	var studentData = collectData("studentDiv");
	var testUserData = collectData("testUserDiv");
	var postData = concatObj(studentData,testUserData);

		$.ajax({
			url:path+"/employeeManagerController/testFormParas.json",
			cache: true,
			type: "POST",
			dataType: "json",
			data: postData,
			success:function(result){
				alert(1);
				var data = result.message;
				layer.msg(data,2,1);
				closeLayer();
			}
		});
	
}


function ajaxPostList(){
  /*
	var postData = {};

	postData["list[1].logid"] = "luandong1";
	postData["list[1].businessName"] = "101";
	
	postData["list[2].logid"] = "luandong2";
	postData["list[2].businessName"] = "102";
	*/
//var dataStr="list[0].logid=id1&list[0].businessName=name1&list[1].logid=id1&list[1].businessName=name1";
	
var postData = collectData("studentListDiv");

		$.ajax({
			url:getRequestUrl("/SysLogOperationController/testList.json"),
			data: postData,
			success:function(result){
				
				alert(1);
			}
		});
	
}
</script>
</body>
</html>