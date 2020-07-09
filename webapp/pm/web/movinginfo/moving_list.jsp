<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>搬入搬出信息</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>

<!--  如果未审批可以删除-->
<script type="text/x-jsrender" id="dataListTemplate">
    <tr name="dataList">
        <td width="47px">{{:#index+1}}</td>
 		<td width="47px">
		{{if movingType === '搬入证' && moutYN === "Y" && approval_state =="6" && currentUserid == skContactsNo}} 
			<a href="javascript:movingOutApprove('{{:movingNo}}','{{:movingPerson}}','{{:movingCompany}}','{{:movingCarno}}','{{:movingPhone}}');"  class="btn3">申请搬出</a>
		{{/if}}
		{{if movingType === '搬入证' && approval_state =="5" && currentUserid =="lg00888"}} 
			<a href="javascript:movingInApprove('{{:movingNo}}');"  class="btn3">申请</a>
		{{/if}}
		{{if deal_user_id === currentUserid && deal_user_id !="lg00999" && approval_state !="6"}} 
		<a href="javascript:applyEntityDataFun('{{:movingNo}}','{{:movingType}}');" class="btn3">审核</a>
		{{/if}}  
		{{if deal_user_id ==currentUserid && deal_user_id =="lg00999" && approval_state !="6"}} 
		<a href="javascript:confirmEntityDataFun('{{:movingNo}}','{{:movingType}}');" class="btn3">确认</a>
		{{/if}} 
		{{if deal_user_id ==currentUserid  && approval_state !="6"}} 
		{{else}}
		<a href="javascript:detailEntityDataFun('{{:movingNo}}','{{:movingType}}');" class="btn3">详情</a>
			{{if create_id ==currentUserid  &&  cntApprove == ""}} 
			<a href="javascript:deleteEntityDataFun('{{:movingNo}}');" class="btn3">删除</a>
			{{/if}} 
		{{/if}}  
	    </td>   
        <td width="100px">{{:movingType}} </td>
		<td width="100px">{{:movingNo}} </td>
		<td width="100px">{{:deal_user_name}} </td>
		<td width="100px">{{:approval_state_name}} </td>
		<td width="100px">{{:skApplyName}} </td>
 		<td width="100px">{{:movingPerson}}</td>
		<td width="100px">{{:movingCompany}}</td>
 		<td width="100px">{{:movingCarno}}</td>
 		<td width="100px">{{:movingPhone}}</td>
		<td width="100px">{{:movingTime}}</td>
	    <td width="100px">{{:movingGoalType}} {{:movingGoalContent}}</td>
	   
   </tr>
</script>
<style>
 tr:nth-child(2n){
                 background:#e4f0fb;
 }
</style>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">搬入搬出证管理</a>&nbsp;>&nbsp;搬入搬出信息</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">搬入搬出信息</div><!--1019-->
  						<!--查询条件-->
                          <div class="w100"  id="search_area">
                          <span class="f14">类型&nbsp;&nbsp;</span>
	                            	<select id="movingType" name="movingInfo.movingType" class="mr20"  dc="true" >
	                            		 <option value ="">全部</option>
										 <option value ="搬入证">搬入证</option>
  										 <option value ="搬出证">搬出证</option>
									 </select>
									 <span class="f14">审核状态&nbsp;&nbsp;</span>
	                            	<select id="applyType" name="movingInfo.applyType" class="mr20"  dc="true" >
					                    <option value ="">全部</option>
									 	<option value ="1">待审批</option>
										<option value ="6">已完成</option>
								    </select>
								    <span class="f14">编号&nbsp;&nbsp;</span>
	                            	<input class="mr20" style="width:200px"  type="text" placeholder="请输入编号"  id="movingNo" value="" name="movingInfo.movingNo" dc=""/>
									<span class="f14">搬出申请人&nbsp;&nbsp;</span>
	                            	<input class="mr20" style="width:150px"  type="text" placeholder="搬出申请人"  id="skApplyName" value="" name="movingInfo.skApplyName" dc=""/>
									<span class="f14">搬入/搬出人&nbsp;&nbsp;</span>
	                            	<input class="mr20" style="width:150px"  type="text" placeholder="搬入/搬出人"  id="movingPerson" value="" name="movingInfo.movingPerson" dc=""/>
									<span class="f14">车牌号&nbsp;&nbsp;</span>
	                            	<input class="mr20" type="text" style="width:180px"  placeholder="请输入车牌号"  id="movingCarno" value="" name="movingInfo.movingCarno" dc=""/>
	                            	<span class="f14">公司名&nbsp;&nbsp;</span>
	                            	<input class="mr20" type="text" style="width:180px"  placeholder="请输入公司名"  id="movingCompany" value="" name="movingInfo.movingCompany" dc=""/>
									<br>
									<span class="f14">日期&nbsp;&nbsp;</span>
	                            	<input id="movingStdate" name="movingInfo.movingStdate"   class="laydate-icon w110" type="text" value="" dc="true" readonly/>&nbsp;~&nbsp;
									<input id="movingEddate" name="movingInfo.movingEddate"   class="laydate-icon w110" type="text" value="" dc="true" readonly/>
                          		   <a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a>
                       
                          </div>
                          
                          
                          
                          <div class="t_r table_wrap mt18" style="overflow-x:auto;">  
                           <div class="t_r mt18" style="width:auto;">
                          		<table  id="ListTable"  class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" >
                                	<thead>
                                          <tr> 
	                                           <th width="47px">序号</th>
	                                           <th width="100px">操作</th>
	                                           <th width="40px">类型</th>
	                                           <th width="90px">编号</th>
	                                           <th width="50px">现审批人</th>
	                                           <th width="40px">状态</th>
	                                           <th width="60px">搬出申请人</th>
											   <th width="60px">搬入/搬出人</th>
											   <th width="100px">公司名</th>
											   <th width="80px">车牌号</th>
											   <th width="80px">联系方式</th>
											   <th width="80px">搬入日期</th>
											   <th width="120px">搬入/搬出目的</th>
                                          </tr> 
                                       </thead> 	
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
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/movinginfo/js/moving_list.js"></script>
</html>