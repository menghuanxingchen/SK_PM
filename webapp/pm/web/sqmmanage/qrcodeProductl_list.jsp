<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设备二维码管理</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>
<script type="text/x-jsrender" id="lineListTemplate">
       <select id="lineList" name="machinePotPartInfo.pot_id" class="mr20"  dc="true" >
		{{renderOptionFun lineList "code_value" "code_nm" "" "生产线" /}}
	 </select>
</script>


<script type="text/x-jsrender" id="dataListTemplate">
    <tbody id="t_r_content" onscroll="aa()">
<tr name="dataList"> 
		<td class="w110">
		<input type="checkbox" name="checkGroup" datid="{{:pot_part_id}}"/> 
		</td>
        <td class="w110">{{:#index+1}}</td> 
		<td class="w110">{{:pot_nm}}</td> 
        <td >{{:pot_part_nm}}</td> 
        <td class="w210">{{:production_date}}</td> 
        <td class="w110">{{:use_year}}</td> 
        <td class="w110">{{:supplier}}</td> 

</tr>
</tbody>
 
 
</script>

<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">SQM管理</a>&nbsp;>&nbsp;设备二维码打印</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">设备二维码打印<a href="javascript:printDataFun();" class="btn1 f14 fb fr">打印</a></div><!--1019-->
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                          		
                            <%-- 	 <td >设备&nbsp;&nbsp;</td>
                            	<td ><input  type="text" id="selPotNm" value="" class="mr20" onclick="selectPot()" readonly/>
                            	<a id="clearPotA" style="visibility: hidden;" href="javascript:clearPot()"><img alt="清空" title="清空" src="<%=request.getContextPath()%>/pm/web/basicinfo/machinemanager/img/delete.png"></a>
                                <input type="hidden" id="selPotId" name="machinePotPartInfo.pot_id" value="" class="mr20" dc="true"/></td>
                              
                                --%>
                                
                                <td id="lineTypeForm">
                                </td>
                              
                                <td><a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                            </tr>
                          </table>
                           <div class="t_r table_wrap mt18">  
                          		<table    class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                                 <tr> 
                                                 	<th class="w110">
					                             	<input type="checkbox" id="checkGroup"/>
					                             	</th>
                                                	 <th class="w110">序号</th> 
                                                	 <th class="w110">生产线</th>                             
						                             <th >设备名称</th>
						                             <th class="w210">生产日期</th>
						                             <th class="w110">使用年限</th>
						                             <th class="w110" id="last" >供应商</th>
                                                 </tr> 
                                             </thead> 	           
                                </table>
                                <div class="t_r_content" > 
                                	<table  id="maPotList" class="tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
                                    	
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
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmmanage/js/qrcodeProdunt_list.js"></script>
</html>