<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>LBC文档</title>
	<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
	<%@ include file="/pm/common/jsp/commonJs.jsp"%>
	<script src="<%=request.getContextPath()%>/pm/common/js/layuitree/layui.js" type="text/javascript"></script>
	<link href="<%=request.getContextPath()%>/pm/common/js/layuitree/layui.css" rel="stylesheet" type="text/css" />
</head>

<style type="text/css">
		*{margin: 0;padding: 0;list-style: none;font-size: 14px}
		#container{
	    width: 200px;
	    height:auto;
	    border: 1px solid #e4e4e4;
	    margin-right: auto;
	    margin-left: auto;
	    margin-top: 50px;
			padding: 10px;
		}
		.second-wrapper {
			margin:10px 0 10px 20px;
			display: none;
		}
		.three-wrapper {
			margin:10px 0 10px 20px;
			display: none;
		}
		.active {
			background: orange;
		}
		.nav:hover{
	color: #B8624A
}

.div-c{ float:left;width:15%;border:1px solid #000} 
.div-d{ float:right;width:84%;border:1px solid #000} 

</style>




<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">SQM查询</a>&nbsp;>&nbsp;LBC文档</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">LBC文档</div><!--1019-->
  						<!--查询条件-->

                        <div class="div-c">
							<div id="container">
									    <div class="frist-title">
										    <i class="layui-icon layui-icon-triangle-r myicon" style="font-size: 15px; color: #000;"></i>
										    <i class="layui-icon layui-icon-triangle-d myicon" style="font-size: 15px; color: #000;display: none;"></i>
										    <span class="text">文件分组</span>
									    </div>
									    <ul class="second-wrapper">
									    </ul>
								    </div>
							</div> 
							<div class="div-d">
							<input id="noteName" type="hidden" />
							<div class="t_r table_wrap mt18" style="overflow-x:auto;">  
                            <div class="t_r mt18" style="width:auto;">  
							<table  id="tabListTable"  class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                                 <tr> 
	                                                <th width="47px">序号</th>
													<th width="220px">标题</th>
													<th width="120px">类别</th>
													<th width="80px">大小</th>
													<th width="280px">文件名</th>
													<th width="150px">上传时间</th>
													<th width="80px">上传者</th>
													<th width="80px">版本</th>
													<th width="80px">备注</th>
													<th width="80px">下载</th>
                                                 </tr> 
                                                 
                                             </thead> 	
                                             <tbody id="t_r_content" onscroll="aa()">
	                                         </tbody>           
                                </table>
							</div> 
						  </div> 
						</div> 
                        <!-- 右侧 结束-->
        		</div>
          </div>
    </div>
</body>

<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmquery/js/document_manager.js"></script>
</html>