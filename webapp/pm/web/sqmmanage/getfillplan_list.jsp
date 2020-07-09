<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>灌装计划管理</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>
<script type="text/x-jsrender" id="lineListTemplate">
       <select id="lineList" name="fillingPlanInfo.LINEID" class="mr20"  dc="true" >
		{{renderOptionFun lineList "code_value" "code_nm" "" "生产线" /}}
	 </select>
</script>

<script type="text/x-jsrender" id="dataListTemplate">
        <tr name="dataList">
		 <td>
			{{if fill_STATE == '1' && currentUserid == "lg00258"}} 
				<a href="javascript:quxiaoDataFun('{{:id}}');"  class="btn3">取消检测</a>
            {{/if}}
         </td>
         <td width="47px">{{:#index+1}}</td>
		 <td >{{:cnfymd}}</td>
		 <td >{{:vsl}}</td>
		 <td >{{:turn}}</td>
		 <td >{{:prod}}</td>
		 <td >{{:prodname}}</td>
		 <td >{{:blndqty}}</td>
		 <td >{{:lotno}}</td>
		 <td >{{:pkg}}</td>
		
		 <td >{{:density}}</td>
		
		
		 <td >{{:linename}}</td>
		 <td >{{:labelstate}}</td>
		
		 <td >{{:fill_STATE_NAME}}</td>
		 <td >{{:fill_STDATE}}</td>
		 <td >{{:fill_EDDATE}}</td>
		 <td >{{:over_plan_user}}</td>
        </tr>
</script>
<style>
 tr:nth-child(2n){
    background:#e4f0fb;
 }
</style>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">SQM管理</a>&nbsp;>&nbsp;灌装计划管理</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">灌装计划管理<a href="javascript:beginDataFun();" class="btn1 f14 fb fr" style="margin-left:83%">获取数据</a></div><!--1019-->
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                          		
                            	<td id="lineTypeForm">
                            		
                                </td>
                                <td>
                                	<span class="f14">状态&nbsp;&nbsp;</span>
                                	<select id="FILL_STATE" name="fillingPlanInfo.FILL_STATE" class="mr20"  dc="true" >
										 <option value ="">全部</option>
									  	 <option value ="0">未进行</option>
									  	 <option value="1">进行中</option>
									  	 <option value="2">异常</option>
									  	 <option value="3">已完成</option>
									 </select>
									
                                </td>
                              	<td>
	                            	<span class="f14">日期&nbsp;&nbsp;</span>
	                            	<input id="start" name="fillingPlanInfo.CNFYMD"   class="laydate-icon w110" type="text" value="" dc="true" readonly/>&nbsp;
									
                                </td>
                                <td>
	                            	<span class="f14">~&nbsp;&nbsp;</span>
	                            	<input id="end" name="fillingPlanInfo.ENDCNFYMD"   class="laydate-icon w110" type="text" value="" dc="true" readonly/>&nbsp;
									
                                </td>
                                <td><a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                           		
                            </tr>
                          </table>
 						   <div class="t_r table_wrap mt18" style="overflow-x:auto;">  
                           <div class="t_r mt18" style="width:auto;">  
                          		<table  id="tabfill"   class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                           <tr> 
                                           		  <th width="80px">操作</th>
                                                  <th width="47px">序号</th>
                                                   <th  width="80px">日期</th>
                                                   <th width="70px">VSL</th>
                                                   <th width="47px">次数</th>
                                                   <th width="70px">编号</th>
                                                   <th >产品名称</th>
                                                   <th width="70px">调配量</th>
                                                   <th width="80px">LOTNO</th>
                                                   <th width="50px">PKG</th>
                                                  
                                                   <th width="70px">密度</th>
                                                   
                                                   
                                                   <th width="80px">生产线名</th>
                                                   <th width="70px">贴标签与否</th>
                                                 
                                                   <th width="70px">灌装状态</th>
                                                   <th >计划开始时间</th>
                                                   <th >计划结束时间</th>
                                                   <th width="70px">计划结束人</th>
                                                 </tr> 
                                             </thead> 	  
                                        <tbody id="t_r_content"  class="abc" onscroll="aa()">
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
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmmanage/js/getfillplan_list.js"></script>
</html>