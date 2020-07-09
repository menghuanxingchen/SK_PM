<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>生产线管理</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>
<script type="text/x-jsrender" id="lineListTemplate">
       <select id="lineList" name="lineProductInfo.lineId" class="mr20"  dc="true" >
		{{renderOptionFun lineList "code_value" "code_nm" "" "生产线" /}}
	 </select>
</script>

<script type="text/x-jsrender" id="dataListTemplate">
        <tr name="dataList">
        <td width="47px">{{:#index+1}}</td>
        <td >{{:lineName}} </td>
		
 		<td width="200px">{{:intervalDate}}</td>
 		<td width="200px">{{:continueDate}}</td>
 		<td width="200px">{{:breakdownDate}}</td>

        <td width="100px"><a href="javascript:updateEntityDataFun('{{:id}}');" class="btn3">编辑</a>  </td>   
		<td width="100px"><a href="javascript:deleteDataFun('{{:id}}');" class="btn3"> 删除</a></td>
        </tr>
</script>

<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">SQM管理</a>&nbsp;>&nbsp;生产线管理</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">生产线管理<a href="javascript:addEntityDataFun();" class="btn1 f14 fb fr">新增</a></div><!--1019-->
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                          		
                            	<td id="lineTypeForm">
                                </td>
                              
                                <td><a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                            </tr>
                          </table>
                           <div class="t_r table_wrap mt18">  
                          		<table    class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                                 <tr> 
                                                <th width="47px">序号</th>
                                                   <th >生产线</th>
                                                   
                                                   <th width="200px">检测间隔时间（分）</th>
                                                   <th width="200px">检测持续时间（分）</th>
                                                   <th width="200px">故障持续时间（分）</th>
                                                   
                                                   <th width="100px">编辑</th>
                                                   <th width="100px" id="last">删除</th>
                                                 </tr> 
                                             </thead> 	           
                                </table>
                                <div class="t_r_content" > 
                                	<table  class="tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
                                    	<tbody id="t_r_content" onscroll="aa()">
                                    	</tbody>
                                      </table> 
                                </div> 
                       	  </div>
                  
                       <!--  分页  开始-->
                          
                        <div class="paging" id="pagefoot"></div>
                          <!--  分页  结束-->
        
                        <!-- 右侧 结束-->
        		</div>
          </div>
    </div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmmanage/js/productline_list.js"></script>
</html>