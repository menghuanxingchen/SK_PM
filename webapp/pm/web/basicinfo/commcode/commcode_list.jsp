<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>共同code信息列表</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
</head>
<script type="text/x-jsrender" id="dataListTemplate">
        <tr>
        <td><input name="checkboxGroupId" type="checkbox" value="{{:code_id}}" /></td>
        <td>{{:code_group_value}}</td>
        <td>{{:code_group_name}}</td>
        <td>{{:code_value}}</td>
        <td>{{:code_nm}}</td>
		<td>{{:sub_code_group_value}}</td>
        <td><a href="javascript:updateEntityDataFun('{{:code_id}}');" class="btn3">修改</a>     <a href="javascript:deleteDataFun('{{:code_id}}');" class="btn3 ml23"> 删除</a></td>
        </tr>
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">计划信息</a>&nbsp;>&nbsp;保养计划管理</div>
            	
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                		<div class="title mb18">保养计划管理<a href="javascript:addEntityDataFun();" class="btn1 f14 fb fr">新增</a></div>
  						
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                                <td class="f14">code组&nbsp;&nbsp;</td>
                                <td><input name="sysCodeInfo.kind_nm" type="text" class="mr20" placeholder="请输入code组名称" dc="true"/></td>
                                <td class="f14">code名称&nbsp;&nbsp;</td>
                                <td><input name="sysCodeInfo.code_nm" type="text" class="mr20" placeholder="请输入code名称" dc="true"/></li></td>
                                <td>
                                	<select class="mr20">
                                        <option>选择排序</option>
                                        <option></option>
                                    </select>
                                </td>
                                <td><a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                            </tr>
                            <tr class="h10"></tr>
                           <tr> 
                            	<td class="f14">计划时间&nbsp;&nbsp;</td>
                                <td colspan="5"><input class="laydate-icon" type="text" />&nbsp;~&nbsp;<input  class="laydate-icon" type="text"   /></td>
                             </tr>
                          </table>
 						<!--表格-->
                           <div class="t_r table_wrap mt18">  
                          		<table  class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" >
                          				<thead> 
										    	<tr>
										        <th><input  type="checkbox" value="" id="checkboxGroupId"/></th>
										        <th>code组Id</th>
										        <th>code组名称</th>
										        <th>codeId</th>
										        <th>code名称</th>
										        <th>下级code组id</th>
										        <th>操作</th>
										        </tr>
									        </thead>
                                        </table> 
                                 <div class="t_r_content" > 
                                	<table  class="tt" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
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
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/basicinfo/commcode/js/commcode_list.js"></script>
</html>