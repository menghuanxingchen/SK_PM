<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>搬出证详情</title>
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
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">搬入搬出证管理</a>&nbsp;>&nbsp;搬出证详情</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">搬出证详情</div><!--1019-->
                		
                		<table  class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0" id="approve_line_table"> 
                            <tr> 
                              <th class="w16">审核</th>
                              <th class="w16">审核</th>
                              <th class="w16">确认</th>
                        	</tr> 
                           	<tr> 
                                <td class="w16" id="userId2">
                                	<select id="user_id2" disabled="disabled" dc="true">
										
								 	</select>
                				</td>
                    
                                <td class="w16" id="userId3">
                                	<select id="user_id3" disabled="disabled" dc="true">
										
 									</select>
                  			    </td>
                                    
                                <td id="userId4">
	                                <select id="user_id4" disabled="disabled" dc="true">
										<option value='lg00999'>门卫</option>
								 	</select>
                       			</td> 
                       
                           </tr> 
                      	   <tr> 
	                          
	                           <td id="app_sta2">待审批</td>
	                           <td id="app_sta3">待审批</td>   
	                           <td id="app_sta4">待确认</td>   
                          </tr> 
                           <tr> 
	                           
	                           <td id="app_time2"></td>
	                           <td id="app_time3"></td>   
	                           <td id="app_time4"></td>   
                          </tr> 
                         </table>
                		
                		
  						<!--查询条件-->
                          <table   class="tt  tt2 record_table table_wrap mt18">
                          
                          <tr>
                         		 <td class="odd w10">
	                            	<span >搬出证申请编号：&nbsp;&nbsp;</span>
                                </td>
                                <td>
                                	<input  type="text" id="moutNo" disabled name="movingOutInfo.moutNo"  dc=""/>
                                </td>
                          </tr>
                          <tr>
                            	<td class="odd w10">
	                            	<span >申请人：&nbsp;&nbsp;</span>
                                </td>
                                <td>
                                	<input  type="text" disabled id="skApplyName" name="movingOutInfo.skApplyName" dc=""/>
                                </td>
                                <td class="odd w10">
                                	<span >部门：&nbsp;&nbsp;</span>
                                </td>
                               <td>
                               		<input  type="text" disabled id="skApplyDept" name="movingOutInfo.skApplyDept" dc=""/>
                               </td>
                                <td class="odd w10">
                                	<span >搬出时间：&nbsp;&nbsp;</span>
                                </td>
                                <td>
                                	<input name="movingOutInfo.moutTime" disabled id="moutTime" type="text"   dc="true" />
                                </td>
                            </tr>
                             <tr>
                                <td class="odd w10">
	                            	 <span >搬出目的：&nbsp;&nbsp;</span>
	                            </td>
                           		<td colspan="3">
                           			<table class="abcd">
										  <tr>
										     <td><label><input style="width:50px" disabled name="moutGoalType" type="radio" value="仓库提货" />仓库提货</label> </td>
										     <td><label><input style="width:50px" disabled name="moutGoalType" type="radio" value="空车出厂" />空车出厂</label> </td>
										     <td><label><input style="width:50px" disabled name="moutGoalType" type="radio" value="垃圾" />垃圾</label></td>
										     <td><label><input style="width:50px" disabled name="moutGoalType" type="radio" value="自消费出库" />自消费出库</label></td>
										     <td><label><input style="width:50px" disabled name="moutGoalType" type="radio" value="其他" />其他</label> </td>
										  	 <td><input class="mr20" type="text" placeholder="请输入目的" id="moutGoalContent" name="movingOutInfo.moutGoalContent" dc=""/></td>
										  </tr>
										 
									</table>
                            	</td>
                            	
                            	<!--  <td class="odd w10">
	                            	 <span >原辅料搬出：&nbsp;&nbsp;</span>
	                            </td>
                           		<td >
                           			<table class="abcd">
										  <tr>
										     <td><label><input style="width:50px" disabled name="ylbanchuYN" type="radio" value="Y" />是 </label> </td>
										     <td><label><input style="width:50px" disabled name="ylbanchuYN" type="radio" value="N" />否 </label> </td>
										 </tr>
									</table>
	                            
                            	</td> -->
                            </tr>
                            
                          	<tr>
                            	<td class="odd w10">
	                            	<span >搬出人姓名：&nbsp;&nbsp;</span>
                                </td>
                                <td>
                                	<input  type="text" disabled id="moutName"  name="movingOutInfo.moutName" dc=""/>
                                </td>
                                <td class="odd w10">
                                	<span >公司名：&nbsp;&nbsp;</span>
                                </td>
                               <td>
                               		<input  type="text" disabled id="moutCompany" name="movingOutInfo.moutCompany" dc=""/>
                               </td>
                                <td class="odd w10">
                                	<span >车牌号：&nbsp;&nbsp;</span>
                                </td>
                                <td>
                               		 <input type="text" disabled id="moutCarno" name="movingOutInfo.moutCarno" dc=""/>
                                </td>
                                
                            </tr>
                            <tr>
                            	
                            	<td class="odd w10">
	                            	<span class="odd">联系方式：&nbsp;&nbsp;</span>
	                            	
                                </td>
                               <td>
                                	<input type="text" disabled id="moutPhone" name="movingOutInfo.moutPhone" dc=""/>
                                </td>
                            </tr>
                            <tr>
	                          <td class="odd w10">
	                            	 <span >搬出图片：&nbsp;&nbsp;</span>
	                            </td>
                           		<td colspan="3">
                           			<div class="logoImg amplifyImg"><!--注意：此处的amlifyImg不可少-->
							          <img src="" style="width:100px;heigth:100px" id="picInfo"/>
							        </div>
                            	</td>
                            </tr>
                            <!-- <tr id="detailtr">
	                          <td class="odd w10">
	                            	 <span >确认图片：&nbsp;&nbsp;</span>
	                            </td>
                           		<td colspan="3">
                           			<div class="logoImg amplifyImg">注意：此处的amlifyImg不可少
							          <img src="" style="width:100px;heigth:100px" id="picQueRenInfoDetail"/>
							        </div>
                            	</td>
                            </tr>
                           <tr id="uploadtr">
	                             <td class="odd w10">
	                            	 <span >上传确认图片：&nbsp;&nbsp;</span>
	                            </td>
                           		<td colspan="3">
                           			<input type="file" class="form-control" id="picfileInfo" name="picfileInfo" onchange ="uploadFile()" />
	                            	<input type="hidden" id="picQueRenInfo"  dc="" />
                            	</td>
                            </tr> -->
                            <tr>
	                            <td class="odd"> 
	                            	 <span >物品目录：&nbsp;&nbsp;</span>
	                            </td>
                           		<td colspan="3">
	                            	 <table border="1" id="tabgoods" class="tt  tt2 record_table table_wrap mt18">
		                            	 <head>
		                            	      <tr class="odd">
											     <th>序号</th>
											    <th>物品名</th>
											    <th>数量</th>
											  </tr>
		                            	 </head>
										 <tbody id="t_r_content">
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
        		</div>
          </div>
    </div>
</body>

<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/movinginfo/js/moving_out_detail.js"></script>
</html>