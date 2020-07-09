<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>维修单新增</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/proposal/js/proposal_info_add.js"></script>
<script type="text/x-jsrender" id="proposalItemdownListTemplate">
	<select id="repair_place" name="proposalInfo.proposal_type" dc="true" dcrequired="请选择内容分类">
		{{renderOptionFun proposalItem "code_value" "code_nm2" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="sysUserInfoDownListTemplate1">
	<select id="user_id2" name="approveInfoList[1].approve_user_id" dc="true" onchange="approvechange(this)" dcrequired="请选择审批人">
		{{renderOptionFun sysUserInfoList "user_num" "user_nm" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="sysUserInfoDownListTemplate3">
	<select id="user_id5" name="approveInfoList[3].approve_user_id" dc="true">
		{{renderOptionFun loginUser "user_num" "user_nm" loginUser[0].user_num ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="loginUserInfoDownListTemplate">
	<select id="user_id1" name="approveInfoList[0].approve_user_id" dc="true">
		{{renderOptionFun loginUser "user_num" "user_nm" loginUser[0].user_num ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="factoryManagerInfoDownListTemplate2">
	<select id="user_id4" name="approveInfoList[2].approve_user_id" dc="true">
		{{renderOptionFun sysUserInfoList "user_num" "user_nm" "" ""/}}
 	</select>
</script>
<script type="text/x-jsrender" id="factoryManagerInfoDownListTemplate4">
	<select id="user_id6" name="approveInfoList[4].approve_user_id" dc="true">
		{{renderOptionFun factoryManagerInfo "user_num" "user_nm" factoryManagerInfo[0].user_num ""/}}
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
                
                		<div class="title mb18">Report新增</div>
  						<!--表格-->
                         <table  class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0" id="approve_line_table"> 
                            <tr> 
                              <th class="w16">申请</th>
                              <th class="w16">审核</th>
                              <th class="w16">审核并分配</th>
                              <th class="w16">验证</th>
                              <th class="w16">Approval</th>
                        	</tr> 
                           	<tr> 
                                <td class="w16" id="userId1">
             					</td> 
                    
                                <td class="w16" id="userId2">
                				</td>
                    
                                <td class="w16" id="userId3">
                  			    </td>
                                    
                                <td id="userId4">
                       			</td> 
                       
		                       <td class="w16" id="userId5">
		                       </td>
                           </tr> 
                      	   <tr> 
	                           <td>申请</td>
	                           <td>待审批</td>
	                           <td>待审批</td>   
	                           <td>待审批</td>   
	                           <td>待审批</td>     
                          </tr> 
                         </table> 
        				
        				 <table class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0" id="repair_add_table">
                            <tr>
                                <td class="odd w10">内容分类</td>
                               <td id="proposalItem">
                                </td>
                            </tr>
                            <tr>
                            	<td class="odd">内容情形描述</td>
                                <td colspan="3"><textarea rows="5" cols="5" name="proposalInfo.proposal_detail" dc="true" maxlength='1000' dcrequired="请输入提案内容"/></textarea></td>  	
                            </tr>
                            <tr>
                            	<td class="odd">弊端/不安全行为状态改善</td>
                                <td colspan="3"><textarea rows="5" cols="5" name="proposalInfo.anticipate_result" dc="true" maxlength='1000' dcrequired="请输入效果预期"/></textarea></td>  	
                            </tr>
                            <tr>
                            	<td class="odd">费用等投入/即时干预</td>
                                <td colspan="3"><textarea rows="5" cols="5" name="proposalInfo.resource_input" dc="true" maxlength='1000' dcrequired="请输入费用/人力投入"/></textarea></td>  	
                            </tr>
                            <tr>
                            	<td class="odd">结论/结果</td>
                                <td colspan="3"><textarea rows="3" cols="5" name="proposalInfo.comparative_conclusion" dc="true" maxlength='1000' dcrequired="请输入比较结论"></textarea></td>
                            </tr>
                        </table>
                        
                        
                        <div class="tex_c mt22"><a href="javascript:addRepairOrderInfoData();" class="btn1 f14 fb mr10">提交</a><a href="<%=request.getContextPath()%>/pm/web/proposal/proposal_info_list.jsp" class="btn1 f14 fb">返回</a></div>
                        <!-- 右侧 结束-->
        		</div>
        		</div>
          </div>
</body>
</html>
