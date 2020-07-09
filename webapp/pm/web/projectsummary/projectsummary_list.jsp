<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>项目计划</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>
<script type="text/x-jsrender" id="projectPlanTypeTemplate">
       <select name="projectSummaryInfo.project_type" id="projectType" class="mr20"  dc="true" >
		{{renderOptionFun projectPlanType "code_value" "code_nm" "" "项目类型"/}}
	 </select>
</script>
<script type="text/x-jsrender" id="projectPlanStateTemplate">
       <select name="projectSummaryInfo.state" class="mr20"  dc="true" >
		{{renderOptionFun projectPlanState "code_value" "code_nm" "1" "项目状态"  /}}
	 </select>
</script>
<script type="text/x-jsrender" id="dataListTemplate">
        <tr name="dataList">
        <td width="35px">{{:#index+1}}</td>
        <td class="w60">{{:project_nm}}
{{if project_type ==1}}
		{{:big_project_step_nm}} 
{{/if}}
      </td>
		<td class="wb-n tex_l">{{:content}}</td>
		<td class="w47">{{:duty_user}}</td>
        <td class="w60">{{:project_type_nm}}</td>
        <td class="w80">{{:plan_start_date}}</td>
		<td class="w80">{{:plan_end_date}}</td>		
		<td class="w80">{{:work_date1}}<br/>{{:work_content1}}</td>		
		<td class="w80">{{:work_date2}}<br/>{{:work_content2}}</td>		
		<td class="w80">{{:real_end_date}}</td>		
		<td class="w40">{{:state_nm}}</td>		
        <td class="w47"><a href="javascript:updateEntityDataFun('{{:project_id}}','{{:project_type}}');" class="btn3">编辑</a>  </td>   
		<td class="w47"><a href="javascript:deleteDataFun('{{:project_id}}');" class="btn3"> 删除</a></td>
        </tr>
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">项目管理</a>&nbsp;>&nbsp;项目计划</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">项目计划<a href="javascript:bigAddEntityForward();" class="btn1 f13 fb fr">新增大项目</a><a href="javascript:smallAddEntityDataFun();" class="btn1 f13 fb fr">新增小项目</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
  						<!--查询条件-->
  						<div class="w100" id="search_area">
  							<span id="projectPlanTypeForm"></span>
  							
  							<span class="f14" >项目名称</span>&nbsp;&nbsp;
  							<input class="mr20 w110" type="text" placeholder="请输入项目名称" name="projectSummaryInfo.project_nm" dc=""/>
  						    
  						    <span id="projectPlanStateForm"></span>
  						    
  						    <span class="f14">指示计划时间</span>&nbsp;&nbsp;
  						    <input class="laydate-icon w110" type="text" name="projectSummaryInfo.plan_start_date" id="start" dc=""/>&nbsp;~&nbsp;<input  class="laydate-icon mr20 w110" name="projectSummaryInfo.plan_end_date" id="end" dc="" type="text"    />
  							
  							<span id="orderListForm"></span>
  							
                            <a href="#" id="search_btn" class="btn2 mr20"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a><a href="javascript:excelDownload();" class="btn1 f13 fb">导出Excel</a>
  						</div>
                           <div class="t_r table_wrap mt18">  
                          		<table  class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                        <tr> 
									           <th width="35px">序号</th>
										           <th class="w60">项目名称</th>
										           <th >内容</th>
										           <th class="w47">负责人</th>
										           <th class="w60">项目类型</th>
										           <th class="w80">指示开始时间</th>
										           <th class="w80">计划完成时间</th>
										           <th class="w80" id="work_date1">最近一次操作</th>
										           <th class="w80" id="work_date2">最近二次操作</th>
										           <th class="w80">实际完成时间</th>
										           <th class="w40">状态</th>
										           <th class="w47">编辑</th>
										           <th class="w47" id="last">删除</th>
										 </tr> 
                                    </thead> 	           
                                </table>
                                <div class="t_r_content" > 
                                	<table id="dataForm" class="tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
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
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/projectsummary/js/projectsummary_list.js"></script>
</html>