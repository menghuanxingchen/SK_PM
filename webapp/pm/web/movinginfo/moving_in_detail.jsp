<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>搬入证详情</title>
	<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
	<%@ include file="/pm/common/jsp/commonJs.jsp"%>
	<script src="<%=request.getContextPath()%>/pm/common/js/jedate/jquery.jedate.js" type="text/javascript"></script>
	<link href="<%=request.getContextPath()%>/pm/common/js/jedate/skin/jedate.css" rel="stylesheet" type="text/css" />
</head>
	<style type="text/css">
 		.over {position: fixed; left:0; top:0; width:100%; z-index:100;}
        .tempContainer {position:fixed; width:100%; margin-right:0px; margin-left:0px; text-align:center; z-index:101;}
	</style>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" id="formId">
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">搬入搬出证管理</a>&nbsp;>&nbsp;搬入证详情</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">搬入证详情</div><!--1019-->
                		
                		<table  class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0" id="approve_line_table"> 
                            <tr> 
                               <th class="w16">审核</th>
                              <!-- <th class="w16">审核</th>
                              <th class="w16">审核</th> -->
                              <th class="w16">确认</th>
                        	</tr> 
                           	<tr> 
                                <td class="w16" id="userId1">
                                	<select id="user_id1" disabled="disabled"  dc="true">
										
								 	</select>
             					</td> 
                    
                             <!--    <td class="w16" id="userId2">
                                	<select id="user_id2" disabled="disabled" dc="true">
										
								 	</select>
                				</td>
                    
                                <td class="w16" id="userId3">
                                	<select id="user_id3" disabled="disabled" dc="true">
										<option value='lg00064'>邵勇</option>
 									</select>
                  			    </td> -->
                                    
                                <td id="userId4">
	                                <select id="user_id4" disabled="disabled" dc="true">
										<option value='lg00999'>门卫</option>
								 	</select>
                       			</td> 
                           </tr> 
                      	   <tr> 
	                           <td id="app_sta1">待审批</td>
	                           <!-- <td id="app_sta2">待审批</td>
	                           <td id="app_sta3">待审批</td>    -->
	                           <td id="app_sta4">待确认</td>   
                          </tr> 
                          <tr> 
	                           <td id="app_time1"></td>
	                           <!-- <td id="app_time2"></td>
	                           <td id="app_time3"></td>  -->  
	                           <td id="app_time4"></td>   
                          </tr> 
                         </table>
                		
                		
  						<!--查询条件-->
                          <table   class="tt  tt2 record_table table_wrap mt18">
                          
                          <tr>
                         		 <td class="odd w10">
	                            	<span >搬入证申请编号：&nbsp;&nbsp;</span>
                                </td>
                                <td>
                                	<input  type="text" id="minNo" disabled name="movingInInfo.minNo"  readonly dc=""/>
                                </td>
                          </tr>
                          	<tr>
                            	<td class="odd w10">
	                            	<span >搬入人姓名：&nbsp;&nbsp;</span>
                                </td>
                                <td>
                                	<input  type="text" id="minName"  name="movingInInfo.minName" readonly dc=""/>
                                </td>
                                <td class="odd w10">
                                	<span >公司名：&nbsp;&nbsp;</span>
                                </td>
                               <td>
                               		<input  type="text" id="minCompany"  name="movingInInfo.minCompany" readonly dc=""/>
                               </td>
                                <td class="odd w10">
                                	<span >车牌号：&nbsp;&nbsp;</span>
                                </td>
                                <td>
                               		 <input type="text" id="minCarno"  name="movingInInfo.minCarno" readonly dc=""/>
                                </td>
                            </tr>
                            <tr>
                            	
                            	<td class="odd w10">
	                            	<span class="odd">联系方式：&nbsp;&nbsp;</span>
	                            	
                                </td>
                               <td>
                                	<input type="text" id="minPhone" readonly name="movingInInfo.minPhone" dc=""/>
                                </td>
                                 <td class="odd w10">
                                	<span >SK联系人：&nbsp;&nbsp;</span>
	                            	
                                </td>
                               <td>
                               		<input type="text" id="skContactsName" name="movingInInfo.skContactsName" readonly dc=""/>
                               			
                               </td>
                                <td class="odd w10"> 
                                	<span >搬入时间：&nbsp;&nbsp;</span>
	                            	
                                </td>
                                 <td>
                                 	<input type="text" id="minTime"  name="movingInInfo.minTime" readonly dc=""/>
                                 </td>
                            </tr>
                            <tr>
                               
                               
                                <td class="odd w10">
	                            	 <span >搬入目的：&nbsp;&nbsp;</span>
	                            </td>
                           		<td colspan="3">
                           			<table class="abcd">
										  <tr>
										     <td><label><input style="width:50px"  name="minGoalType" disabled type="radio" value="送货" />送货 </label> </td>
										     <td><label><input style="width:50px" name="minGoalType" disabled type="radio" value="提货" />提货 </label> </td>
										     <td><label><input style="width:50px" name="minGoalType" disabled type="radio" value="其他" />其他 </label> </td>
										  	 <td><input class="mr20" type="text" placeholder="请输入目的" id="minGoalContent" name="movingInInfo.minGoalContent" dc=""/></td>
										  </tr>
										 
									</table>
                            	</td>
                            </tr>
                            <tr>
	                             <td class="odd w10">
	                            	 <span >以后是否需要搬出：&nbsp;&nbsp;</span>
	                            </td>
                           		<td colspan="3">
                           			<table class="abcd">
										  <tr>
										     <td><label><input style="width:50px" disabled name="moutYN" type="radio" value="Y" />是 </label> </td>
										     <td><label><input style="width:50px" disabled name="moutYN" type="radio" value="N" />否 </label> </td>
										 </tr>
									</table>
	                            
                            	</td>
                            </tr>
                           <tr>
	                          <td class="odd w10">
	                            	 <span >图片：&nbsp;&nbsp;</span>
	                            </td>
                           		<td colspan="3">
	                           		<div class="logoImg amplifyImg"><!--注意：此处的amlifyImg不可少-->
							          <img src="" style="width:100px;heigth:100px" id="picInfo"/>
							        </div>
                            	</td>
                            </tr>
                            <tr>
	                            <td class="odd"> 
	                            	 <span >物品目录：&nbsp;&nbsp;</span>
	                            </td>
                           		<td colspan="3">
	                           		
	                            	 <table border="1" id="tabgoods" class="tt  tt2 record_table table_wrap mt18">
	                            	 	<thead>
										  <tr class="odd">
										     <th>序号</th>
										    <th>物品名</th>
										    <th>数量</th>
										   
										  </tr>
										</thead>
										<tbody id="t_r_content" onscroll="aa()">
										</tbody>
									</table>
                            	</td>
                            </tr>
                          </table>
                          <div class="tex_c mt22">
	                        <a href="javascript:updateApproveAndReject('A');"  id="approve_check" class="btn1 f14 fb mr10">审批</a>
	                        <a href="javascript:updateApproveAndReject('R');"  id="reject_check" class="btn1 f14 fb mr10">驳回</a>
	                        <a href="javascript:updateApproveAndReject('C');" id="confirm_check" class="btn1 f14 fb mr10">确认</a>
                            <a id="submitBtn" href="javascript:history.go(-1);" class="btn1 f14 fb mr10">返回</a>
                          </div>
                        <!-- 右侧 结束-->
                        <div class="over"></div><!--背景层-->
				        
        		</div>
          </div>
    </div>
</body>

<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/movinginfo/js/moving_in_detail.js"></script>
</html>