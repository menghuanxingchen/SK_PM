<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=0">
    <title>停机维修列表</title>
    <%@ include file="/pm/common/jsp/commonCss.jsp"%>
	<%@ include file="/pm/common/jsp/commonJs.jsp"%>
</head>
<script type="text/x-jsrender" id="meRepairTemplate">
<tbody id="t_r_content" onscroll="aa()">        
<tr> 
        <td class="w47">{{:#index+1}}</td> 
        <td>{{:repair_content}}</td>
        <td class="w110">{{:repair_date}}</td> 
        <td class="w110">{{:repair_person}}</td> 
        <td class="w110">{{:pot_id_nm}}</td> 
		<td class="w110">{{:create_name}}</td> 
        <td class="w110"><a href="javascript:updFun('{{:emergent_id}}')"  class="btn3">编辑</a></td>
 		<td class="w110">{{:repairType}}</td> 
 		<td class="w110"><a href="javascript:repairFun('{{:emergent_id}}')"  class="btn3">维修反馈</a></td>
        <td class="w110">{{:sqm_normal_parameter}}</td>
        <td class="w110">{{if sqm_relieve_yn=='N'}}<a href="javascript:interfaceRelieveFault('{{:emergent_id}}','N')"  class="btn3">解除</a>{{/if}}</td> 
		<td class="w110">{{if sqm_relieve_yn=='N'}}<a href="javascript:interfaceRelieveFault('{{:emergent_id}}','Y')"  class="btn3">解除并删除</a>{{else}}<a href="javascript:celFun('{{:emergent_id}}')"  class="btn3">删除</a>{{/if}}</td> 
</tr> 
</tbody>
</script>
<script type="text/x-jsrender" id="jobTemplate">
<span class="f14">职能</span>&nbsp;&nbsp;
<select id="job_type" name="emergentRepairInfo.job_type" dc="true" class="mr20">
		{{renderOptionFun selectList "code_value" "code_nm" "" "职能"/}}
 	</select>
</script>
<body>

<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">停机维修管理</a>&nbsp;>&nbsp;停机维修信息</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">                
                		<div class="title mb18">停机维修信息<a href="javascript:addFun();" id="gotoAddpageBt" class="btn1 f14 fb fr">新增</a></div>
  						<!--查询条件-->
                          <table  id="search_area" class="w_auto" border="0" cellspacing="0" cellpadding="0">
                          	<tr>
                                <td >设备&nbsp;&nbsp;</td>
                            	<td ><input  type="text" id="selPotNm" value="" class="mr20" onclick="selectPot()" readonly/>
                            	<a id="clearPotA" style="visibility: hidden;" href="javascript:clearPot()"><img alt="清空" title="清空" src="<%=request.getContextPath()%>/pm/web/basicinfo/machinemanager/img/delete.png"></a>
                                <input type="hidden" id="selPotId" name="emergentRepairInfo.pot_id" value="" class="mr20" dc="true"/></td>
                                <td>
                                	<span class="f14">工作内容</span>&nbsp;&nbsp;
                                    <input type="text" name="emergentRepairInfo.repair_content" value="" class="mr20" dc="true"/>
                                </td>
                                <td>
                                	<span class="f14">工作日期</span>&nbsp;&nbsp;
                                    <input type="text" id="st" name="emergentRepairInfo.search_start" value="" class="laydate-icon w110" dc="true"/>
									&nbsp;~&nbsp;
                                    <input type="text" id="ed" name="emergentRepairInfo.search_end" value="" class="laydate-icon w110 mr20" dc="true"/>
                                </td>
                                <td><a href="javascript:queryDataList();" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                                
                            </tr>
                          </table>
 						<!--表格-->
                         <div class="t_r table_wrap mt18">  
                		<table class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                         <thead>
                            <tr> 
                             <th class="w47">序号</th>                             
                             <th>工作内容</th>
                             <th class="w110">维修日期</th>
                             <th class="w110">维修人员</th>
                             <th class="w110">设备</th>
                             <th class="w110">提交人</th>
                             <th class="w110" >编辑</th>
                             <th class="w110" >维修状态</th>
                             <th class="w110" >维修反馈</th>
                             <th class="w110">sqm正常参数</th>
                             <th class="w110">解除故障</th>
                             <th class="w110" id="last" >删除</th>
                            <!--  <th width="47px">删除</th>
                             <td class="w47"><a href="javascript:deleteCheckItem('{{:check_project_id}}')" class="btn3">删除</a></td>  -->
                            </tr> 
                            </thead>
                          </table> 
                          <div class="t_r_content"> 
                      		<table id="meRepairList" class="tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
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
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/emergentrepair/js/emergent_repair_info_list.js"></script>
</html>
