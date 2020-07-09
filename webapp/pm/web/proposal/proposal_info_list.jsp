<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>提案书信息</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/proposal/js/proposal_info_list.js"></script>
<script type="text/x-jsrender" id="proposalInfoListTemplate">
		{{if approval_state !=='1' &&　approval_state !=='2'　&&　approval_state !=='6'　&&　approval_state !=='7'}}
			<tr role="row" style="background-color: #ff8608; color:#fff;">
		{{else}}
        	<tr role="row">
		{{/if}}
 		<td class="w47">{{:#index+1}}</td>
        <td width="100px"><a href="javascript:queryRepairDataEntity('{{:id}}','{{:flag}}','{{:approval_state}}');"  class="btn3" >{{:proposal_num}}</a></td>
		<td class="w80">{{:proposal_type_nm}}</td>
		<td class="w330">{{:proposal_detail}}</td>
		<td class="w60">{{:create_id_nm}}</td>
        <td class="w60">{{:operate_user_nm}}</td>
		<td class="w80">
			{{if flag==='1'}} 
				<a href="javascript:updateApproveAndReject('{{:id}}','A');"  class="btn3">审批</a> | <a href="javascript:updateApproveAndReject('{{:id}}','R');"  class="btn3">驳回</a>
			{{else flag==='2'}} 
				<a href="javascript:distributFun('{{:id}}');"  class="btn3">分配</a> | <a href="javascript:updateApproveAndReject('{{:id}}','R');"  class="btn3">驳回</a>
			{{else flag==='4'}}
				<a href="javascript:updateApproveAndReject('{{:id}}','C','{{:approval_state}}');" class="btn3">确认</a>
			{{else flag==='5'}}
				<a href="javascript:repairResultFun('{{:id}}');"  class="btn3">实施</a>
			{{/if}}
		</td> 
		<td class="w47"><a href="javascript:queryRepairDataEntity('{{:id}}','{{:flag}}','{{:approval_state}}');"  class="btn3" >详情</a></td> 
		<td class="w60">{{:deal_user_id_nm}}</td>
		<td class="w60">{{:approval_state_nm}}</td>
		<td width="65px">{{:dept_nm}}</td> 
		<td class="w80">{{:factory_manager_approval_date}}</td>
		<td class="w80">{{:require_date}}</td>
		<td class="w47">
			{{if approval_state==='1' &&　editYnFlag==='Y'}} 
				<a href="javascript:updateDataFun('{{:id}}');"  class="btn3">编辑</a>
			{{else}}
				<a href="javascript: return false;"  class="over">编辑</a>
			{{/if}}
		</td> 
        <td class="w47">
			{{if deleteYnFlag==='Y'}} 
				<a href="javascript:deleteDataFun('{{:id}}');"  class="btn3" >删除</a>
			{{else}}
				<a href="javascript:return false;" class="over">删除</a>
			{{/if}}
		</td>
	</tr>
</script>
<script type="text/x-jsrender" id="departGroupdownListTemplate">
	<select id="depart_group" class="w180 mr20 ml23" name="proposalInfo.dept_code" dc="true">
		{{renderOptionFun departGrouplist "code_value" "code_nm" "" "部门"/}}
 	</select>
</script>
<script type="text/x-jsrender" id="approveGroupdownListTemplate">
	<select id="approve_group" class="mr20" name="proposalInfo.approval_state" dc="true">
		{{renderOptionFun proposalApproveStatelist "code_value" "code_nm2" "" "审批状态"/}}
 	</select>
</script>
<script type="text/x-jsrender" id="departGroupSubDownListTemplate">
	<select id="depart_group_sub" class="mr20" name="proposalInfo.sub_dept_code" dc="true">
		{{renderOptionFun departGroupSubList "code_value" "code_nm" "" "职能部门"/}}
 	</select>
</script>
<!--<script type="text/x-jsrender" id="userChooseGroupSubDownListTemplate">
	<select id="repair_user_choose"　class="mr20" name="repair_user_flag" dc="true" dcrequired="请选择人员区分">
		{{renderOptionFun userChooseList "code_value" "code_nm" "" "人员区分"/}}
 	</select>
</script>
 <script type="text/x-jsrender" id="repairPlaceGroupSubDownListTemplate"> 
	<select id="repair_place" style="margin-right:20px;" name="repairOrderInfo.repair_place" dc="true">
		{{renderOptionFun repairPlaceList "code_value" "code_nm" "" "维修地点"/}}
 	</select>
</script>
 <script type="text/x-jsrender" id="repairCategoryGroupSubDownListTemplate"> 
	<select id="repair_type" style="margin-right:20px;" name="repairOrderInfo.repair_type" dc="true">
		{{renderOptionFun repairCategorylist "code_value" "code_nm" "" "费用类型"/}}
 	</select>
</script>-->
<script type="text/x-jsrender" id="allUserGroupSubDownListTemplate">
	<select id="deal_user_id" style="margin-right:20px;"　 name="proposalInfo.deal_user_id" dc="true">
		{{renderOptionFun allUser "user_num" "user_nm" "" "处理人"/}}
 	</select>
</script>
<script type="text/x-jsrender" id="allUserGroupSubDownListTemplate2">
	<select id="deal_user_id2" style="margin-right:20px;"　 name="proposalInfo.create_id" dc="true">
		{{renderOptionFun allUser "user_num" "user_nm" "" "申请人"/}}
 	</select>
</script>
</head>
<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">提案书</a>&nbsp;>&nbsp;Report信息</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                  		<!--pop flag start -->
                                <input id="proposal_info_id" type="hidden" value="" idattr=""/>
                        <!--pop flag end-->
                		<div class="title mb18">Report信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:excelDownload();" id="reportExport_btn" class="btn1 f14 fb fr mr20 ml10">导出</a><a href="javascript:addDataFun();" class="btn1 f14 fb fr">新增</a></div>          
  						<!--查询条件-->
  						<div class="w100" id="search_area">
  							<span class="f14" >内容情形描述</span>&nbsp;&nbsp;
  							<input class="mr20 w110" type="text" name="proposalInfo.proposal_detail" dc=""/>
  							<span id="approveGroup"></span>
<!--   							<span id="userChoose"></span> 							 -->
							<select class="mr20"  dc="true" name="proposalInfo.userFlag" id="repair_user_choose" dcrequired="请选择人员区分">
                            	<option value="">-人员区分-</option>
                                <option value="1">申请人</option>
                                <option value="2">实施人</option>
                            </select>
  							<span id="departGroup"></span>							
  							<span id="departGroupSub"></span>
  							
  							<span class="f14">申请时间</span>&nbsp;&nbsp;
                            <input class="laydate-icon w110" type="text" id="start_dt" name="proposalInfo.start_dt" dc="true" readonly/>&nbsp;~&nbsp;<input class="laydate-icon w110 mr20" type="text" id="end_dt" name="proposalInfo.end_dt" dc="true" readonly/>
  							
  							<select class="mr20"  dc="true" name="proposalInfo.orderItem">
                            	<option value="">-选择排序-</option>
                                <option value="1">分配日期</option>
                            </select>
                            
                            <span id="allUser"></span>
<!--                             <span id="repairPlace"></span> -->
<!--                             <span id="repairCategory"></span> -->
                            <span class="f14" >内容分类</span>&nbsp;&nbsp;
  							<input class="mr20 w110" type="text" name="proposalInfo.proposal_type_nm" dc=""/>
  							<span id="allUser2"></span>
                            <a href="javascript:queryDataList();" class="btn2 ml23"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a>
  							
  						</div>
                        
 						<!--表格-->
 						  <div class="t_r table_wrap mt18">  
	                              <div class="t_r_content" style="height:1000px;"> 
	                                   <table id="repairOrderListTable" class="t_r_t tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
	                                         <thead>
		                                         <tr> 
		                                           <th class="w47">序号</th>
		                                           <th width="100px">申请编号</th>
		                                           <th class="w80">内容分类</th>
		                                           <th class="w330">内容情形描述</th>
		                                           <th class="w60">申请人</th>
		                                           <th class="w60">实施人</th>
		                                           <th class="w80">操作</th>
		                                           <th class="w47">详情</th>
		                                           <th class="w60">处理人</th>
		                                           <th class="w60">审批状态</th>
		                                           <th width="65px">申请人部门</th>
		                                           <th class="w80">全项完成日期</th>
		                                           <th class="w80">分配日期</th>
		                                           <th class="w47">编辑</th>
		                                           <th id="last" class="w47">删除</th>
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
                       	  </div>
                       <!-- 右侧 结束-->
        		</div>
          </div>
    </div>
</body>
</html>
