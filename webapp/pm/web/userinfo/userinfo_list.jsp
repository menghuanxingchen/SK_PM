<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户信息</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>
<script type="text/x-jsrender" id="departListTemplate">
       <select id="dept" name="sysUserInfo.dept_code" class="mr20"  dc="true" >
		{{renderOptionFun departList "code_value" "code_nm" "" "部门" /}}
	 </select>
</script>
<script type="text/x-jsrender" id="subDepartListTemplate">
       <select name="sysUserInfo.sub_dept_code" class="mr20"  dc="true" >
		{{renderOptionFun subDepartList "code_value" "code_nm" "" "职能部门" /}}
	 </select>
</script>
<script type="text/x-jsrender" id="dataListTemplate">
        <tr name="dataList">
        <td width="47px">{{:#index+1}}</td>
        <td width="100px">{{:user_num}} </td>
		<td width="100px">{{:user_nm}}</td>
        <td width="100px">{{:dept_code_nm}}</td>
		<td width="100px">{{:sub_dept_nm}}</td>
        <td width="100px">{{:position_nm}}</td>
        <td width="100px">{{:sqm_role_nm}}</td>
		<td >{{:email}}</td>
        <td width="47px"><a href="javascript:updateEntityDataFun('{{:user_id}}');" class="btn3">编辑</a>  </td>   
		<td width="47px"><a href="javascript:deleteDataFun('{{:user_id}}');" class="btn3"> 删除</a></td>
        </tr>
</script>
<script type="text/x-jsrender" id="orderListTemplate">
       <select id='codeKind' name="sysUserInfo.orderType" class="mr20"  dc="true" >
		{{renderOptionFun orderList "code_value" "code_nm" "" "排序类型" /}}
	 </select>
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">用户信息</a>&nbsp;>&nbsp;用户信息</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">用户信息<a href="javascript:addEntityDataFun();" class="btn1 f14 fb fr">新增</a></div><!--1019-->
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                          		
                            	<td id="departTypeForm">
                                </td>
                                <td id="subDepartTypeForm">
                                </td>
                                <td>
                                	<span class="f14" >姓名</span>&nbsp;&nbsp;
                                    <input class="mr20" type="text" placeholder="请输入姓名" name="sysUserInfo.user_nm" dc=""/>
                                </td>
                                <td>
                                	<span class="f14" >工号</span>&nbsp;&nbsp;
                                    <input class="mr20" type="text" placeholder="请输入工号" name="sysUserInfo.user_num" dc=""/>
                                </td>
                                <td id="orderListForm">
                                </td>
                                <td><a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                            </tr>
                          </table>
                           <div class="t_r table_wrap mt18">  
                          		<table    class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                                 <tr> 
                                                   <th width="47px">序号</th>
                                                   <th width="100px">工号</th>
                                                   <th width="100px">姓名</th>
                                                   <th width="100px">部门</th>
                                                   <th width="100px">职能部门</th>
                                                   <th width="100px">职位</th>
                                                   <th width="100px">SQM角色</th>
                                                   <th >email</th>
                                                   <th width="47px">编辑</th>
                                                   <th width="47px" id="last">删除</th>
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
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/userinfo/js/userinfo_list.js"></script>
</html>