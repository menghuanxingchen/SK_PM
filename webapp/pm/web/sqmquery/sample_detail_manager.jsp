<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>样本详细信息</title>
	<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
	<%@ include file="/pm/common/jsp/commonJs.jsp"%>
	<script src="<%=request.getContextPath()%>/pm/common/js/jedate/jquery.jedate.js" type="text/javascript"></script>
	<link href="<%=request.getContextPath()%>/pm/common/js/jedate/skin/jedate.css" rel="stylesheet" type="text/css" />
</head>



<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">SQM查询</a>&nbsp;>&nbsp;样本详细信息</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">样本详细信息</div><!--1019-->
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                          		
                            	<td>
	                            	<span class="f14">PKG+产品信息&nbsp;&nbsp;</span>
	                            	<input class="mr20" style="width:300px" type="text" id="pkg" name="pkg" dc=""/>
                                </td>
                                <td>
                                	<span class="f14">修改号&nbsp;&nbsp;</span>
	                            	<input class="mr20" type="text" style="width:200px"  id="revno" name="revno" dc=""/>
                                </td>
                               <td>
	                            	<span class="f14">重量(KG)&nbsp;&nbsp;</span>
	                            	<input class="mr20" type="text" style="width:200px"  id="weight" name="weight" dc=""/>
                                </td>
                                <td>
	                            	<span class="f14">修改日期&nbsp;&nbsp;</span>
	                            	<input class="mr20" type="text" style="width:200px" id="revdate" name="revdate" dc=""/>
                                </td>
                            </tr>
                            <tr>
                            	<td colspan=4>
                            		<br/>
                                	<span class="f14">备注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	                            	<input class="mr20" style="width: 600px" type="text"  id="bigo" name="bigo" dc=""/>
                                </td>
                                <td>
                                <a href="javascript:history.go(-1)" class="btn1 f14 fb fr">返回</a>
                                </td>
                            </tr>
                            <tr>
                            	<td>
                            		<br/>
                                	<span class="f14">图片1&nbsp;&nbsp;</span>
	                            	<img src="" width=400px height=400px id="SAMPLE1"/>
                                </td>
                               <td>
                            		<br/>
                                	<span class="f14">图片2&nbsp;&nbsp;</span>
	                            	<img src="" width=400px height=400px id="SAMPLE2"/>
                                </td>
                            </tr>
                          
                            <tr>
                            	<td>
                            		<br/>
                                	<span class="f14">图片3&nbsp;&nbsp;</span>
	                            	<img src="" width=400px height=400px id="SAMPLE3"/>
                                </td>
                                <td>
                            		<br/>
                                	<span class="f14">图片4&nbsp;&nbsp;</span>
	                            	<img src="" width=400px height=400px id="SAMPLE4"/>
                                </td>
                            </tr>
                        
                          </table>
                           
        		</div>
          </div>
    </div>
</body>

<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmquery/js/sample_detail_manager.js"></script>
</html>