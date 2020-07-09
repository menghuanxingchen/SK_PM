<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>检查项目管理</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/inspectplan/js/checkitem_list_pop.js"></script>
</head>
<!-- 设备类别 -->
<script type="text/x-jsrender" id="machinespeciesListTemplate">
	<select  name="checkitemInfo.machine_species_id" id="species_id" dc="true" class="mr20">
		{{renderOptionFun machinespeciesList "code_value" "code_nm" "" "noflag"/}}
 	</select>
</script>
<!-- 设备类型 -->
<script type="text/x-jsrender" id="machinetypeListTemplate">
	<select name="checkitemInfo.machine_type_id" id="type_id" dc="true" class="mr20">
		{{renderOptionFun infoList "code_value" "code_nm" "" "noflag"/}}
 	</select>
</script>
<!-- 检查项目管理list -->
<script type="text/x-jsrender" id="checkitemListTemplate">
<tbody id="t_r_content" onscroll="aa()">        
<tr> 
        <td class="w47">
		<input type="checkbox" name="checkGroupId" datid="{{:check_project_id}}" datnam="{{:check_project_nm}}" spval="{{:machine_species_id}}" typeval="{{:machine_type_id}}"/>
		</td> 
        <td class="w80">{{:machine_species_name}}</td> 
        <td class="w80">{{:machine_type_name}}</td> 
        <td>{{:check_project_nm}}</td> 
</tr> 
</tbody>
</script>
<body id="pop">
    <div class="pop_box">
          <div class="popup">
          		<!--breadcrumbs-->
            	<!--右侧 开始--> 
            	<div id="aaa">                
  						<!--查询条件-->
                          <table id="search_area" class="w_auto fl" border="0" cellspacing="0" cellpadding="0">
                          	<tr>
                            	<td id="machinespecies">
                                </td>
                                <td id="machinetype">
                                </td>
                                <td>
                                   	检查项目名&nbsp;&nbsp;<input type="text" name="checkitemInfo.check_project_nm" value="" dc="true"/>
                                   	<a id="searchBt" href="#" class="btn2 ml10 mr10"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a>
                                </td>
                            </tr>
                          </table>
                          <div class="fr"><a href="#" id="importCheckitemListBt" class="btn1 f14 fb fr w60">导入</a></div>
 						<!--表格-->
                         <div class="t_r mt18">  
                		<table class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                         <thead>
                            <tr> 
                             <th class="w47"> <input type="checkbox" id="checkGroupId"/></th>
                             <th class="w80">设备类别</th>
                             <th class="w80">设备类型</th>
                             <th>检查项目名称</th>
                            </tr> 
                            </thead>
                          </table> 
                          <div class="t_r_content  table_wrap" > 
                      		<table id="checkitemList" class="tt" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
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
</html>
