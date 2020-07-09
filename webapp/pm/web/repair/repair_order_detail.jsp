<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>维修单新增</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/repair/js/repair_order_detail.js"></script>
<script type="text/x-jsrender" id="repairPlacedownListTemplate">
	<select id="repair_place" name="repairOrderInfo.repair_place" dc="true">
		{{renderOptionFun repairPlaceList "code_value" "code_nm" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="sysUserInfoDownListTemplate2">
	<select id="user_id2" name="approveInfoList[1].approve_user_id" dc="true">
		{{renderOptionFun sysUserInfoList "user_num" "user_nm" appuser1.approve_user_id ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="sysUserInfoDownListTemplate3">
	<select id="user_id3" name="approveInfoList[2].approve_user_id" dc="true">
		{{renderOptionFun sysUserInfoList "user_num" "user_nm" appuser2.approve_user_id ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="sysUserInfoDownListTemplate5">
	<select id="user_id5" name="approveInfoList[4].approve_user_id" dc="true">
		{{renderOptionFun loginUser "user_num" "user_nm" loginUser[0].user_num ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="loginUserInfoDownListTemplate">
	<select id="user_id1" name="approveInfoList[0].approve_user_id" dc="true">
		{{renderOptionFun loginUser "user_num" "user_nm" loginUser[0].user_num ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="factoryManagerInfoDownListTemplate4">
	<select id="user_id4" name="approveInfoList[3].approve_user_id" dc="true">
		{{renderOptionFun factoryManagerInfo "user_num" "user_nm" factoryManagerInfo[0].user_num ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="factoryManagerInfoDownListTemplate6">
	<select id="user_id6" name="approveInfoList[5].approve_user_id" dc="true">
		{{renderOptionFun factoryManagerInfo "user_num" "user_nm" factoryManagerInfo[0].user_num ""/}}
 	</select>
</script>
</head>
<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">维修信息</a>&nbsp;>&nbsp;维修单信息</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">维修单新增</div>
  						<!--表格-->
                         <table  class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0" id="approve_line_table"> 
                            <tr> 
                              <th class="w8">申请</th>
                              <th class="w8">初审</th>
                              <th class="w8">分配</th>
                              <th class="w8">报价</th>
                              <th class="w8">报价审批</th>
                              <th class="w8">维修</th>
                              <th class="w8">验证</th>
                              <th class="w8">最终确认</th>
                        	</tr> 
                           	<tr> 
                                <td class="w8" id="userId1">
             					</td> 
                    
                                <td class="w8" id="userId2">
                				</td>
                				
                                <td class="w8" id="userId3">
                  			    </td>
                                
                                <td class="w8">
                    				<input type="text" id="baojiauserId" readonly/>
                				</td>
                				    
                                <td class="w8" id="userId4">
                       			</td> 
                       
                       			<td class="w8">
                    				<input type="text" id="weixiuuserId" readonly/>
                				</td>
                				
		                       <td class="w8" id="userId5">
		                       </td>
	                       
		                       <td class="w8" id="userId6">
		                       </td> 
                           </tr> 
                      	   <!--  <tr> 
	                           <td>申请</td>
	                           <td id="app_sta1">待审批</td>
	                           <td id="app_sta2">待审批</td>   
	                           <td id="app_staBaojia">待审批</td>
	                           <td id="app_sta3">待审批</td>   
	                           <td id="app_staWeixiu">待审批</td>
	                           <td id="app_sta4">待审批</td>   
	                           <td id="app_sta5">待审批</td>     
                          </tr> -->
                          <tr> 
	                           <td><input type="text" id="app_time0" readonly/></td>
	                           <td><input type="text" id="app_time1" readonly/></td>
	                           <td><input type="text" id="app_time2" readonly/></td>   
	                           <td><input type="text" id="app_timeBaojia" readonly/></td>
	                           <td><input type="text" id="app_time3" readonly/></td>   
	                           <td><input type="text" id="app_timeWeixiu" readonly/></td>
	                           <td><input type="text" id="app_time4" readonly/></td>   
	                           <td><input type="text" id="app_time5" readonly/></td>     
                          </tr> 
                         </table> 
        				
        				 <table class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0" id="repair_add_table">
                            <tr>
                            	<td class="odd w10">维修单号</td>
                                <td>
                                	<input type="text" id="repair_order_num" readonly/>
                                </td>
                                <td class="odd w10">申请日期</td>
                                <td>
                                	<input id="create_time" type="text" readonly/>
                                </td>
                            </tr>
                            <tr>
                            	<td class="odd w10">要求完成时间</td>
                                <td>
                                	<input class="laydate-icon" id="require_time" type="text" name="repairOrderInfo.require_time" dc="true" readonly/>
                                </td>
                                <td class="odd w10">维修地点</td>
                               <td id="repairPlace">
                                </td>
                            </tr>
                            <tr>
                            	<td class="odd">维修内容描述</td>
                                <td colspan="3"><textarea rows="5" cols="5" id="repair_detail" name="repairOrderInfo.repair_detail" dc="true" maxlength='50'/></textarea></td>  	
                            </tr>
                            <tr>
                            	<td class="odd">备注</td>
                                <td colspan="3"><textarea rows="3" cols="5" id="remark" name="repairOrderInfo.remark" dc="true" maxlength='50'></textarea></td>
                            </tr>
                        </table>
                        
                        
                        <div class="tex_c mt22">
                        <a href="javascript:backBtn();" class="btn1 f14 fb mr10">返回</a>
                        <a href="javascript:gotoOfferDetail();" id="offer_check" class="btn1 f14 fb w80 mr10">查看报价</a>
                        <a href="javascript:gotoRepairDataEntity();" id="result_check" class="btn1 f14 fb w100p mr10">查看维修结果</a>
                        <a href="javascript:updateApproveAndReject('A');"  id="approve_check" class="btn1 f14 fb mr10">审批</a>
                        <a href="javascript:updateApproveAndReject('R');"  id="reject_check" class="btn1 f14 fb mr10">驳回</a>
                        <a href="javascript:updateApproveAndReject('C');" id="confirm_check" class="btn1 f14 fb mr10">确认</a>
                        <a href="#" id="reportExport_btn" class="btn1 f14 fb mr10">导出</a>
                        </div>
                        <!-- 右侧 结束-->
        		</div>
        		</div>
          </div>
</body>
</html>
