<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>未结算维修单</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/account/js/repair_order_unpayed.js"></script>
<script type="text/x-jsrender" id="reapirOrderListTemplate">
	<tr role="row">
 		<td class="w47">{{:#index+1}}</td>
        <td class="w100p">{{:repair_order_num}}</td>
		<td class="w140">{{:create_time}}</td>
		<td class="w80">{{:require_time}}</td>
		<td class="w80">{{:repair_date}}</td>
		<td class="w220">{{:repair_detail}}</td>
		<td class="w60">{{:create_id_nm}}</td>
		<td width="65px">{{:dept_nm}}</td>
        <td class="w60">{{:repair_user_id_nm}}</td>
		<td class="w80">{{:approval_state_nm}}</td>
		<td class="w140">{{:repair_place_nm}}</td>
		<td class="w80">{{:tatal_all}}</td>
		<td class="w80">{{:after_repair_tatal_all}}</td>
		<td class="w80">{{:factory_manager_confirm_date}}</td>
		<td class="w100p">
			{{if flag==='7'}} 
				<input class="laydate-icon w110" type="text" id="account_dt{{:#getIndex()}}" name="repairOrderInfo.account_date" dc="true" readonly/>
			{{/if}}
		</td>
		<td class="w47">
			{{if flag==='7'}} 
				<a href="javascript:updateAccount('{{:id}}','{{:#getIndex()}}');"  class="btn3">结算</a>
			{{/if}}
		</td> 
		<td class="w47"><a href="javascript:queryRepairDataEntity('{{:id}}','');"  class="btn3" >详情</a></td> 
	</tr>
</script>
<script type="text/x-jsrender" id="departGroupdownListTemplate">
	<select id="depart_group" class="w180 mr20" name="repairOrderInfo.dept_code" dc="true">
		{{renderOptionFun departGrouplist "code_value" "code_nm" "" "申请人部门"/}}
 	</select>
</script>
<script type="text/x-jsrender" id="departGroupSubDownListTemplate">
	<select id="depart_group_sub" class="mr20" name="repairOrderInfo.sub_dept_code" dc="true">
		{{renderOptionFun departGroupSubList "code_value" "code_nm" "" "职能部门"/}}
 	</select>
</script>
<script type="text/x-jsrender" id="departListTemplate">
       <select id="item_type" name="itemInfo.item_type" class="mr20"  dc="true" >
		{{renderOptionFun projectTypeList "code_value" "code_nm" "" "" /}}
	 </select>
</script>
<script type="text/x-jsrender" id="subDepartListTemplate">
       <select id="state" name="itemInfo.item_state" class="mr20"  dc="true" >
		{{renderOptionFun projectStateList "code_value" "code_nm" "" "" /}}
	 </select>
</script> 
<script type="text/x-jsrender" id="dataListTemplate">
        <tr name="dataList">
        <td class="w47">{{:#index+1}}</td>
        <td class="w67"><a href="javascript:detailDataFun('{{:work_out_id}}');" class="btn3">{{:work_out_nm}}</a></td>
		<td class="w80">
			{{if flag==='7'}} 
				<input class="laydate-icon w110" type="text" id="pay_time{{:#getIndex()}}" name="pay_time" dc="true" readonly/>
			{{/if}}
		</td>
	{{if flag==='7'}}
		<td class="w47"><input type="hidden" id ="pay_yn" value="{{:pay_yn}}"/><a href="javascript:updatePayYnFun('{{:work_out_id}}','{{:pay_yn}}','{{:#getIndex()}}');" class="pay_nm">{{:pay_nm}}</a></td>
	{{else}}
		<td class="w47"><input type="hidden" id ="pay_yn" value="{{:pay_yn}}"/><a href="javascript: return false;"  class="over">{{:pay_nm}}</a></td>
	 {{/if}}
		<td class="w47"><a href="javascript:updateEntityDataFun('{{:work_out_id}}');" class="btn3">编辑</a></td>
		<td class="w47"><a href="javascript:deleteDataFun('{{:work_out_id}}');" class="btn3">删除</a></td>
        </tr>
</script>
<script type="text/x-jsrender" id="orderListTemplate">
       <select id='codeKind' name="sysUserInfo.orderType" class="mr20"  dc="true" >
		{{renderOptionFun orderList "code_value" "code_nm" "" "排序类型" /}}
	 </select>
</script>

</head>
<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">结算</a>&nbsp;>&nbsp;未结算维修单</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">未结算维修单  </div>
  						<!--查询条件-->
                          <table class="w_auto fl" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                            	<td id="">
                                </td>
                                <td id="departGroup">
                                </td>
                                <td id="departGroupSub">
                                </td>
                                <td>
                                	<span class="f14">全项完成日期</span>&nbsp;&nbsp;
                                    <input class="laydate-icon w110" type="text" id="start_dt" name="repairOrderInfo.start_dt" dc="true" readonly/>&nbsp;~&nbsp;<input class="laydate-icon w110 mr20" type="text" id="end_dt" name="repairOrderInfo.end_dt" dc="true" readonly/>
                                </td>
                                <td>
                                	<select class="mr20"  dc="true" name="repairOrderInfo.orderItem">
                                        <option value="">选择排序</option>
                                        <option value="2">申请日期</option>
                                        <option value="5" selected>全项完成日期</option>
                                    </select>
                                </td>
                                <td><a href="javascript:queryDataList();" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                            </tr>
                          </table>
                          <div class="fr"><a href="javascript:addEntityDataFun();" class="btn1 f14 fb fr">新增</a></div>
 						<!--表格 出勤统计列表-->
 						<div class="t_r table_wrap mt18">  
                          		<table    class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                                 <tr> 
                                                   <th class="w47">序号</th>
                                                   <th class="w67">出勤表名称</th>
                                                   <th class="w80">结算时间</th>
                                                   <th class="w47" >操作</th>
                                                   <th class="w47" >编辑</th>
                                                   <th class="w47" >删除</th>
                                                 </tr>
                                             </thead> 	           
                                </table>
                                <div class="w_t_r_content" > 
                                	<table  class="tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
                                    	<tbody id="w_t_r_content" onscroll="aa()">
                                    	</tbody>
                                      </table> 
                                </div> 
                       	  </div>
 
                       	  <!--表格 未结算维修单-->
 						<div class="table_wrap" style="overflow-x:auto;">
 						  <div class="t_r mt18" style="width:auto;">  
                					<table class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
	                                        <thead>
		                                         <tr> 
		                                           <th class="w47">序号</th>
		                                           <th class="w100p">申请编号</th>
		                                           <th class="w140">申请日期</th>
		                                           <th class="w80">计划完成日期</th>
		                                           <th class="w80">实际完成日期</th>
		                                           <th class="w220">维修内容</th>
		                                           <th class="w60">申请人</th>
		                                           <th width="65px">申请人部门</th>
		                                           <th class="w60">维修担当</th>
		                                           <th class="w80">审批状态</th>
		                                           <th class="w140">维修地点</th>
		                                           <th class="w80">维修前报价</th>
		                                           <th class="w80">维修后费用</th>
		                                           <th class="w80">全项完成日期</th>
		                                           <th class="w100p">结算时间</th>
		                                           <th class="w47">操作</th>
		                                           <th class="w47">详情</th>
                                       			 </tr> 
	                                         </thead>
	                                   </table>
	                              <div class="t_r_content" style="height:1000px;"> 
	                                   <table class="t_r_t tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
	                                         <tbody id="t_r_content" onscroll="aa()">
	                                         		
	                                         </tbody>
                                        </table> 
                                    </div>
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
