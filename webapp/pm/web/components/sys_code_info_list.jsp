<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>code_id列表</title>
   <%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
</head>
<script type="text/x-jsrender" id="machineListTemplate">
<tbody id="t_r_content" onscroll="aa()">        
<tr> 
        <td class="w47">{{:#index+1}}</td> 		
        <td>{{:code_nm}}</td>             		
        <td class="w47"><a href="javascript:gotoUpdate('{{:code_id}}')"  class="btn3">编辑</a></td> 
        </tr> 
</tbody>
</script>

<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">停机维修信息</a>&nbsp;>&nbsp;子部件管理</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">                
                		<div class="title mb18">子部件设备信息<a href="#" id="gotoAddpageBt" class="btn1 f14 fb fr">新增</a></div>
  						<!--查询条件-->
                          <table  id="search_area" class="w_auto" border="0" cellspacing="0" cellpadding="0">
                          	<tr>
                            	<td id="machinespecies">
                                </td>
                                <td>
                                	<span class="f14">设备名称</span>&nbsp;&nbsp;
                                    <input type="text" name="componentssysCodeInfo.code_nm" value="" class="mr20" dc="true"/>
                                </td>
                                <td ><a href="#"  onclick="getCheckItemListLike()" id="" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                                
                            </tr>
                          </table>
 						<!--表格-->
                         <div class="t_r table_wrap mt18">  
                		<table class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                         <thead>
                            <tr> 
                             <th class="w47">序号</th>                             
                             <th>设备名称</th>                            
                             <th class="w47" id="last" >编辑</th>
                            <!--  <th width="47px">删除</th>
                             <td class="w47"><a href="javascript:deleteCheckItem('{{:check_project_id}}')" class="btn3">删除</a></td>  -->
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

<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/components/js/sys_code_info_list.js"></script>
</html>
