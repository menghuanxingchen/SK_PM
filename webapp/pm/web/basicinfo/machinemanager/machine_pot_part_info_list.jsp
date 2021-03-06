<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=0">
    <title>部件列表</title>
    <%@ include file="/pm/common/jsp/commonCss.jsp"%>
	<%@ include file="/pm/common/jsp/commonJs.jsp"%>
	<style type="text/css">
		#clearPotA{
			position: relative;
			right: 17%;
		}
		
	</style> 
</head>
<!-- 检查项目管理list -->
<script type="text/x-jsrender" id="maPotPartListTemplate">
<tbody id="t_r_content" onscroll="aa()">        
<tr> 
        <td class="w47">{{:#index+1}}</td> 
        <td class="w110">{{:pot_nm}}</td> 
        <td>{{:pot_part_nm}}</td> 
        <td class="w110">{{:use_year}}</td> 
        <td class="w110">{{:supplier}}</td> 
        <td class="w47"><a href="javascript:gotoUpdate('{{:pot_part_id}}')"  class="btn3">编辑</a></td> 
</tr> 
</tbody>
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">停机维修信息</a>&nbsp;>&nbsp;部件信息</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">                
                		<div class="title mb18">部件信息<a href="#" id="gotoAddpageBt" class="btn1 f14 fb fr">新增</a></div>
  						<!--查询条件-->
                          <table  id="search_area" class="w_auto" border="0" cellspacing="0" cellpadding="0">
                          	<tr>
                                <td>
                                	<span class="f14">所属一级设备</span>&nbsp;&nbsp;
                                    <input type="text" id="selPotNm" value="" class="mr20" onclick="selectPot()" readonly/>
                                    <a id="clearPotA" style="visibility: hidden;" href="javascript:clearPot()"><img alt="清空" title="清空" src="<%=request.getContextPath()%>/pm/web/basicinfo/machinemanager/img/delete.png"></a>
                                    <input type="hidden" id="selPotId" name="machinePotPartInfo.pot_id" value="" class="mr20" dc="true"/>
                                </td>
                                <td>
                                	<span class="f14">部件名称</span>&nbsp;&nbsp;
                                    <input type="text" name="machinePotPartInfo.pot_part_nm" value="" class="mr20" dc="true"/>
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
                             <th class="w110">所属一级设备</th>
                             <th>部件名称</th>
                             <th class="w110">使用年限</th>
                             <th class="w110">供应商</th>
                             <th class="w47" id="last" >操作</th>
                            <!--  <th width="47px">删除</th>
                             <td class="w47"><a href="javascript:deleteCheckItem('{{:check_project_id}}')" class="btn3">删除</a></td>  -->
                            </tr> 
                            </thead>
                          </table> 
                          <div class="t_r_content"> 
                      		<table id="maPotPartList" class="tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
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
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/basicinfo/machinemanager/js/machine_pot_part_info_list.js"></script>
</html>
