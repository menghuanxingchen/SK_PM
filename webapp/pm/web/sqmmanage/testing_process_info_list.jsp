<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>生产线产品管理</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>
<script type="text/x-jsrender" id="lineListTemplate">
       <select id="lineList" name="testingProcessInfo.lineId" class="mr20"  dc="true" >
		{{renderOptionFun lineList "code_value" "code_nm" "" "生产线" /}}
	 </select>
</script>

<script type="text/x-jsrender" id="dataListTemplate">
        <tr name="dataList">
        <td width="47px">{{:#index+1}}</td>
        <td width="180px">{{:lineName}} </td>
		<td width="80px">{{:productCode}}</td>
        <td >{{:productName}}</td>
		<td width="100px">{{:productType}}</td>
        <td >{{:customName}}</td>
 		<td width="100px">{{:equipmentId}}</td>
 		<td >{{:equipmentName}}</td>
		<td width="100px">{{:orderNum}}</td>
		<td width="100px">{{:useYn}}</td>

        <td width="80px"><a href="javascript:updateEntityDataFun('{{:id}}');" class="btn3">编辑</a>  </td>   
		<td width="80px">
		{{if useYn ==='Y'}} 
				<a href="javascript:deleteDataFun('{{:id}}');" class="btn3"> 弃用</a>

		{{else}} 
				<a href="javascript:beginDataFun('{{:id}}');" class="btn3"> 启用</a>
		{{/if}}


	   </td>
        </tr>
</script>

<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">SQM管理</a>&nbsp;>&nbsp;检测流程管理</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">检测流程管理<a href="javascript:addEntityDataFun();" class="btn1 f14 fb fr">新增</a></div><!--1019-->
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                          		
                            	<td id="lineTypeForm">
                                </td>
                              
                                <td>
                                	<span class="f14" >产品Code</span>&nbsp;&nbsp;
                                    <input class="mr20" type="text" placeholder="请输入产品Code" style="width:200px" name="testingProcessInfo.productCode" dc=""/>
                                </td>
                               
                                <td>
                                	<span class="f14" >产品名称</span>&nbsp;&nbsp;
                                    <input class="mr20" type="text" placeholder="请输入产品名称" style="width:200px" name="testingProcessInfo.productName" dc=""/>
                                </td>
                                <td><a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                            </tr>
                          </table>
                           <div class="t_r table_wrap mt18">  
                          		<table    class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                                 <tr> 
                                                	 <th width="47px">序号</th>
                                                   <th width="180px">生产线</th>
                                                   <th width="80px">产品code</th>
                                                   <th >产品名称</th>
                                                   <th width="100px">选型</th>
                                                   <th >客户</th>
                                                   <th width="100px">设备ID</th>
                                                   <th >设备名称</th>
                                                   <th width="100px">顺序</th>
                                                   <th width="100px">状态</th>
                                                   <th width="80px">编辑</th>
                                                   <th width="80px" id="last">操作</th>
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
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmmanage/js/testing_process_info_list.js"></script>
</html>