<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>搬入证信息</title>
<%@ include file="/pm/common/jsp/common_mobile.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmquery/js/m_sample_detail_manager.js"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/jedate/jquery.jedate.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/pm/common/js/jedate/skin/jedate.css" rel="stylesheet" type="text/css" />

	<style type="text/css">
 		.over {position: fixed; left:0; top:0; width:100%; z-index:100;}
        .tempContainer {position:fixed; width:100%; margin-right:0px; margin-left:0px; text-align:center; z-index:101;}
	</style>

</head>
<body>
<div id="com_body">
    <div id="_centent">
        <header>
            <div class="rt-bk">
                <i class="bk" onclick="backpage();"></i>
            </div>
            <div class="top-name"><p>样本信息详细</p></div>
        </header>
    
    <section class="mt-1"> 
        <div class="ps-lt">
            <div class="lt-dsb cl-bb">
                <p class="title w100" style="width:200px">PKG+产品信息</p>
                <p>
                <input  type="text" class="w470"readonly id="pkg" name="pkg"  dc=""/>
                </p>
            </div>
           <div class="lt-dsb cl-bb">
                <p class="title w100" style="width:200px">修改号</p><p><input  type="text" class="w470" id="revno" name="revno" readonly   dc=""/></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100" style="width:200px">重量(KG)</p><p><input  type="text" class="w470" id="weight" name="weight" readonly   dc=""/></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100" style="width:200px">修改日期</p><p><input  type="text" class="w470" id="revdate" name="revdate" readonly  dc=""/></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100" style="width:200px">备注</p><p><input  type="text" class="w470" id="bigo" name="bigo" readonly dc=""/></p>
            </div>
            
             <div class="lt-dsbpic cl-bb">
                <p class="title w100" style="width:200px">图片1</p>
               
               	<div class="logoImg amplifyImg"><!--注意：此处的amlifyImg不可少-->
		          <img src="" style="width:200px;heigth:200px" id="SAMPLE1"/>
		        </div>
                
            </div>
              <div class="lt-dsbpic cl-bb">
                <p class="title w100" style="width:200px">图片2</p>
               
               	<div class="logoImg amplifyImg"><!--注意：此处的amlifyImg不可少-->
		          <img src="" style="width:200px;heigth:200px" id="SAMPLE2"/>
		        </div>
                
            </div>
            <div class="lt-dsbpic cl-bb">
                <p class="title w100" style="width:200px">图片3</p>
               
               	<div class="logoImg amplifyImg"><!--注意：此处的amlifyImg不可少-->
		          <img src="" style="width:200px;heigth:200px" id="SAMPLE3"/>
		        </div>
                
            </div>
            <div class="lt-dsbpic cl-bb">
                <p class="title w100" style="width:200px">图片4</p>
               
               	<div class="logoImg amplifyImg"><!--注意：此处的amlifyImg不可少-->
		          <img src="" style="width:200px;heigth:200px" id="SAMPLE4"/>
		        </div>
                
            </div>
        </div>
    </section>
    
   
     
    <footer id="com_foot" >
    	 <input type='submit' name='backtopage' onClick="backpage()" style='width:90%' class='submit ml30' value='返回'/>
    	   
    </footer>
</div> 
</div>
</body>
</html>