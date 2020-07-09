<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>设备信息</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/basicinfo/baseinfo/js/machine_list.js"></script>
</head>
<!-- 设备类别 -->
<script type="text/x-jsrender" id="machinespeciesListTemplate">
	<select id="machine_species_id" name="machineInfo.machine_species_id" dc="true" class="mr20">
		{{renderOptionFun machinespeciesList "code_value" "code_nm" "" "设备类别"/}}
 	</select>
</script>
<!-- 设备类型 -->
<script type="text/x-jsrender" id="machinetypeListTemplate">
	<select name="machineInfo.machine_type_id" dc="true" class="mr20">
		{{renderOptionFun infoList "code_value" "code_nm" "" "设备类型"/}}
 	</select>
</script>
<!-- 区域 -->
<script type="text/x-jsrender" id="areaListTemplate">
	<select name="machineInfo.area_id" dc="true" class="w180 mr20">
		{{renderOptionFun areaList "code_value" "code_nm" "" "区域"/}}
 	</select>
</script>
<!-- 巡检计划list -->
<script type="text/x-jsrender" id="machineListTemplate">
<tbody id="t_r_content" onscroll="aa()">        
<tr> 
        <td class="w47">{{:#index+1}}</td> 
		<td class="w80">{{:machine_species_name}}</td> 
        <td>{{:machine_nm}}</td> 
        <td class="w80">{{:machine_type_name}}</td> 
        <td width="120px">{{:area_name}}</td> 
		<td width="120px">{{:remark}}</td> 
        <td class="w47"><a href="javascript:gotoUpdate('{{:machine_id}}')"  class="btn3">编辑</a></td> 
        </tr> 
</tbody>
</script>
<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">基础信息</a>&nbsp;>&nbsp;设备信息</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">                
                		<div class="title mb18">设备信息<a href="#" id="gotoAddpageBt" class="btn1 f14 fb fr">新增</a></div>
  						<!--查询条件-->
                          <table id="search_area" class="w_auto" border="0" cellspacing="0" cellpadding="0">
                          	<tr>
                            	<td id="machinespecies">
                                </td>
                                <td id="machinetype">
                                </td>
                                <td id="areaid">
                                </td>
                                <td>
                                   	设备名&nbsp;&nbsp;<input type="text" name="machineInfo.machine_nm" value=""  class="mr20" onclick="value='';focus()" dc="true"/>
                                </td>
                                <td><a href="#" id="searchBt" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                                
                            </tr>
                          </table>
 						<!--表格-->
                         <div class="t_r table_wrap mt18">  
	                		<table class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
	                         <thead>
	                            <tr> 
	                             <th class="w47">序号</th>
	                             <th class="w80">设备类别</th>
	                             <th>设备名称</th>
	                             <th class="w80">设备类型</th>
	                             <th width="120px">区域</th>
	                             <th width="120px">要事记项</th>
	                             <th class="w47" id="last">编辑</th>
	                             <!-- <th id="last" class="w47">删除</th>
	                             <td class="w47"><a href="javascript:deleteMachine('{{:machine_id}}')" class="btn3">删除</a></td>  -->
	                            </tr> 
	                            </thead>
	                          </table> 
                          <div class="t_r_content"> 
                      		<table id="machineList" class="tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
                        	</table>    
                          </div> 
                       	  </div>
                  
                       <!--  分页  开始-->                          
                       <div class="paging" id="pagefoot">                                            
                       </div>
                       <!--  分页  结束-->         
        
                        <!-- 右侧 结束-->
    
        		</div>
          </div>
    </div>


</body>
<script type="text/javascript">
	var table_height=$("#table_height").height();
	if(table_height>="386")
	{
		$("#last").css({paddingRight:'25px'});
	}
</script>
</html>
