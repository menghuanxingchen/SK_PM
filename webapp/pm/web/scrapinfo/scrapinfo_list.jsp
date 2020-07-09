<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设备报废</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>
<script type="text/javascript">
var auth = '<%=request.getSession().getAttribute("sys_hidden_auth")%>';
</script>
<!-- 设备类别 -->
<script type="text/x-jsrender" id="machinespeciesListTemplate">
	<select  name="scrapInfo.machine_species_id" id="species_id" dc="true" class="mr20">
		{{renderOptionFun machinespeciesList "code_value" "code_nm" "" "设备类别"/}}
 	</select>
</script>
<!-- 设备类型 -->
<script type="text/x-jsrender" id="machinetypeListTemplate">
	<select name="scrapInfo.machine_type_id" id="type_id" dc="true" class="mr20">
		{{renderOptionFun infoList "code_value" "code_nm" "" "设备类型"/}}
 	</select>
</script>
<script type="text/x-jsrender" id="dataListTemplate">
        <tr name="dataList">
        <td class="w47">{{:#index+1}}</td>
        <td class="w80">{{:machine_type_nm}} </td>
		<td class="w60">{{:scrap_num}}</td>
		<td class="w60">{{:scrap_machine_nm}}</td>
        <td>{{:scrap_reason}}</td>
        <td class="w80">{{:scrap_date}}</td>
		<td class="w60">{{:main_machine_nm}}</td>		
		<td class="w70">{{:spare_nm}}</td>	
		<td class="w70">{{:user_nm}}</td>	
		{{if approval_id1 == '' }}	
		<td class="w80" name="approval1"><a href="javascript:updateApproveAndReject('{{:scrap_id}}','A',1);"   class="btn3">审批</a> | <a href="javascript:updateApproveAndReject('{{:scrap_id}}','R',2);"  class="btn3">驳回</a></td>
		{{/if}}			
		{{if approval_id1 == 'A' }}	
		<td class="w80">已审批</td>
		{{/if}}	
		{{if approval_id1 == 'R' }}	
		<td class="w80">驳回</td>
		{{/if}}	
		{{if approval_id1 == 'A' && approval_id2 == ''  }}	
		<td class="w80" name="approval2"><a href="javascript:updateApproveAndReject('{{:scrap_id}}','A',1);"  class="btn3">审批</a> | <a href="javascript:updateApproveAndReject('{{:scrap_id}}','R',2);"  class="btn3">驳回</a></td>
		{{/if}}		
		{{if approval_id1 == 'A' && approval_id2 == 'A'  }}	
		<td class="w80">已审批</td>
		{{/if}}	
		{{if approval_id1 == 'A' && approval_id2 == 'R'  }}	
		<td class="w80">驳回</td>
		{{/if}}	
		{{if approval_id1 != 'A'}}	
		<td class="w80"></td>
		{{/if}}	
        <td class="w47"><a href="javascript:updateEntityDataFun('{{:scrap_id}}');" class="btn3">编辑</a>  </td>   
		<td class="w47"><a href="javascript:deleteDataFun('{{:scrap_id}}');" class="btn3"> 删除</a></td>
        </tr>
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">设备报废</a>&nbsp;>&nbsp;设备报废</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">设备报废<a href="javascript:addEntityForward();" class="btn1 f13 fb fr">新增</a></div>
  						<!--查询条件-->
  						<div class="w100" id="search_area">
  							<span id="machinespecies"></span>
  							
  						    <span id="machinetype"></span>
  							
  						    <span class="f14">报废时间</span>&nbsp;&nbsp;
  						    <input class="laydate-icon w110" type="text" name="scrapInfo.scrap_date_start" id="start" dc=""/>&nbsp;~&nbsp;<input  class="laydate-icon mr20 w110" name="scrapInfo.scrap_date_end" id="end" dc="" type="text"    />
  							
                            <a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a>
  						</div>
                           <div class="t_r table_wrap mt18">  
                          		<table  class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                        <tr> 
									           <th class="w47">序号</th>
										           <th class="w80">报废设备名称</th>
										           <th class="w60">报废数量</th>
										           <th class="w60">型号</th>
										           <th>报废原因</th>
										           <th class="w80">报废时间</th>
										           <th class="w60">是否属于关键设备</th>
										           <th class="w70">是否有备件</th>
										           <th class="w70">申请人</th>
										           <th class="w80">科长审批</th>
										           <th class="w80">厂长审批</th>
										           <th class="w47">编辑</th>
										           <th class="w47" id="last">删除</th>
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
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/scrapinfo/js/scrapinfo_list.js"></script>
</html>