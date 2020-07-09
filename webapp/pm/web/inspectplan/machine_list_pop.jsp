<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>设备管理</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/inspectplan/js/machine_list_pop.js"></script>
</head>
<!-- 设备类别 -->
<script type="text/x-jsrender" id="machinespeciesListTemplate">
	<select  name="machineInfo.machine_species_id" id="species_id" dc="true" class="mr20">
		{{renderOptionFun machinespeciesList "code_value" "code_nm" "" "noflag"/}}
 	</select>
</script>
<!-- 设备类型 -->
<script type="text/x-jsrender" id="machinetypeListTemplate">
	<select name="machineInfo.machine_type_id" id="type_id" dc="true" class="mr20">
		{{renderOptionFun infoList "code_value" "code_nm" "" "noflag"/}}
 	</select>
</script>
<!-- 区域 -->
<script type="text/x-jsrender" id="areaListTemplate">
	<select name="machineInfo.area_id" dc="true" class="mr20">
		{{renderOptionFun areaList "code_value" "code_nm2" "" "区域"/}}
 	</select>
</script>
<!-- 巡检计划list -->
<script type="text/x-jsrender" id="machineListTemplate">
<tbody id="t_r_content" onscroll="aa()">        
<tr> 
<td class="w47">
<input type="checkbox" name="checkGroupId" datid="{{:machine_id}}" datnam="{{:machine_nm}}" spval="{{:machine_species_id}}" typeval="{{:machine_type_id}}"/></td> 
<td class="w80">{{:machine_species_name}}</td>  
<td class="w80">{{:machine_type_name}}</td> 
<td class="w100p">{{:area_name}}</td>        
<td>{{:machine_nm}}</td>         
</tr> 
</tbody>
</script>
<body id="pop">
    <div class="pop_box">
          <div class="popup">
            	<!--右侧 开始--> 
            	<div id="aaa">                
  						<!--查询条件-->
                          <table id="search_area" class="w_auto fl" border="0" cellspacing="0" cellpadding="0">
                          	<tr>
                            	<td id="machinespecies">
                                </td>
                                <td id="machinetype">
                                </td>
                                <td id="areaid">
                                </td>
                                <td>
                                   	设备名&nbsp;&nbsp;<input type="text" name="machineInfo.machine_nm" value="" onclick="value='';focus()" dc="true"/>
                                   	<a href="#" class="btn2 ml10 mr10" id="searchBt"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a>
                                </td>
                            </tr>
                          </table>
                          <div class="fr"><a href="#" id="importMachineListBt" class="btn1 f14 fb fr w60">导入</a></div>
 						<!--表格-->
                         <div class="t_r mt18">  
                		<table class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                         <thead>
                            <tr> 
                             <th class="w47">
                             <input type="checkbox" id="checkGroupId"/>
                             </th>
                             <th class="w80">设备类别</th>
                             <th class="w80">设备类型</th>
                             <th class="w100p">区域</th>
                             <th>设备名称</th>                      
                            </tr> 
                            </thead>
                          </table> 
                          <div class="t_r_content" > 
                      		<table id="machineList" class="tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
                        	</table>    
                          </div> 
                       	  </div>
                  
                       <!--  分页  开始-->                          
                       <div class="paging mt22" id="pagefoot">                                            
                       </div>
                       <!--  分页  结束-->         
        
                        <!-- 右侧 结束-->
    
        		</div>
          </div>
    </div>


</body>
</html>
