<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>搬出证申请</title>
	<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
	<%@ include file="/pm/common/jsp/commonJs.jsp"%>
	<script src="<%=request.getContextPath()%>/pm/common/js/jedate/jquery.jedate.js" type="text/javascript"></script>
	<link href="<%=request.getContextPath()%>/pm/common/js/jedate/skin/jedate.css" rel="stylesheet" type="text/css" />
</head>

<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" id="formId">
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">搬入搬出证管理</a>&nbsp;>&nbsp;搬出证申请</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">搬出证申请</div><!--1019-->
  						<!--查询条件-->
                          <table   class="tt  tt2 record_table table_wrap mt18">
                          
                          <tr>
                         		 <td class="odd w10">
	                            	<span >搬出证申请编号：&nbsp;&nbsp;</span>
                                </td>
                                <td>
                                	<input  type="text" id="moutNo" disabled name="movingOutInfo.moutNo"  dc=""/>
                                </td>
                                
                                <td class="odd w10">
	                            	<span >设置审批人(可不选)：&nbsp;&nbsp;</span>
                                </td>
                                <td>
                                	<table class="abcd">
										  <tr>
										     <td> 
										     		 <select name="applyPerson1" id="applyPerson1"  tip="第一审批人">
														<option value="">--第一审批人--</option>
														
														<option value="lg00158" >乔海滨</option>
														<option value="lg00302" >赵正博</option>
														<option value="lg00157" >张志勇</option>
														<option value="lg00258" >李彦晖</option>
												    </select>
										     </td>
										     <td>
										     		<select name="applyPerson2" id="applyPerson2"  tip="第二审批人">
														<option value="">--第二审批人--</option>
														<option value="lg00064" >邵勇</option>
														<option value="lg00158" >乔海滨</option>
														<option value="lg00302" >赵正博</option>
														<option value="lg00157" >张志勇</option>
														<option value="lg00258" >李彦晖</option>
												    </select>
										     </td>
										  </tr>
										 
									</table>
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
                                	<input name="movingOutInfo.moutTime" dcrequired="请选择搬出时间" readonly class="dateinput dateicon" id="moutTime" type="text" placeholder="请选择搬出时间"   dc="true" />
                                </td>
                            </tr>
                             <tr>
                                <td class="odd w10">
	                            	 <span >搬出目的：&nbsp;&nbsp;</span>
	                            </td>
                           		<td colspan="3">
                           			<table class="abcd">
										  <tr>
										     <td><label><input style="width:50px" checked name="moutGoalType" type="radio" value="仓库提货" />仓库提货</label> </td>
										     <td><label><input style="width:50px" name="moutGoalType" type="radio" value="空车出厂" />空车出厂</label> </td>
										     <td><label><input style="width:50px" name="moutGoalType" type="radio" value="垃圾" />垃圾</label> </td>
										     <td><label><input style="width:50px" name="moutGoalType" type="radio" value="自消费出库" />自消费出库</label> </td>
										     <td><label><input style="width:50px" name="moutGoalType" type="radio" value="其他" />其他</label> </td>
										  	 <td><input class="mr20" type="text" placeholder="请输入目的" id="moutGoalContent" name="movingOutInfo.moutGoalContent" dc=""/></td>
										  </tr>
										 
									</table>
                            	</td>
                            	
                            	
                            </tr>
                            
                          	<tr>
                            	<td class="odd w10">
	                            	<span >搬出人姓名：&nbsp;&nbsp;</span>
                                </td>
                                <td>
                                	<input  type="text" id="moutName" placeholder="请输入搬出人姓名" dcrequired="请输入搬出人姓名"  name="movingOutInfo.moutName" dc=""/>
                                </td>
                                <td class="odd w10">
                                	<span >公司名：&nbsp;&nbsp;</span>
                                </td>
                               <td>
                               		<input  type="text" id="moutCompany" placeholder="请输入公司名" dcrequired="请输入公司名" name="movingOutInfo.moutCompany" dc=""/>
                               </td>
                                <td class="odd w10">
                                	<span >车牌号：&nbsp;&nbsp;</span>
                                </td>
                                <td>
                               		 <input type="text" id="movingCarno" placeholder="请输入车牌号" dcrequired="请输入车牌号" name="movingOutInfo.moutCarno" dc=""/>
                                </td>
                                
                            </tr>
                            <tr>
                            	<td class="odd w10">
	                            	<span class="odd">联系方式：&nbsp;&nbsp;</span>
                                </td>
                               <td>
                                	<input type="text" id="moutPhone" placeholder="请输入联系方式" dcrequired="请输入联系方式" name="movingOutInfo.moutPhone" dc=""/>
                                </td>
                            </tr>
                           <tr>
	                             <td class="odd w10">
	                            	 <span >图片：&nbsp;&nbsp;</span>
	                            </td>
                           		<td colspan="3">
                           			<input type="file" class="form-control" id="picfileInfo" name="picfileInfo" onchange ="uploadFile()" />
	                            	<input type="hidden" id="picInfo"  dc="" />
                            	</td>
                            </tr>
                           
                            <tr>
	                            <td class="odd"> 
	                            	 <span >物品目录：&nbsp;&nbsp;</span>
	                            </td>
                           		<td colspan="3">
                           			<span id="spanGoodsType">请参考出库单！</span>
                           			<div id="divGoodsType">
		                           		<br/>
		                           		<a href="javascript:addMoveInfoData();" class="btn1 f14 fb fr">添加</a>
		                           		<br/>
		                            	 <table border="1" id="tabgoods" class="tt  tt2 record_table table_wrap mt18">
											  <tr class="odd">
											     <th>序号</th>
											    <th>物品名</th>
											    <th>数量</th>
											    <th>操作</th>
											  </tr>
											  <tr>
											    <td>1</td>
											    <td><input id="wpmc1" type="text" placeholder="请输入物品名称"   dc=""/></td>
											    <td><input  type="text" placeholder="请输入数量" dc=""/></td>
											    <td></td>
											  </tr>
											  <tr>
											    <td>2</td>
											    <td><input  type="text" placeholder="请输入物品名称" dc=""/></td>
											    <td><input  type="text" placeholder="请输入数量" dc=""/></td>
											    <td></td>
											  </tr>
											  <tr>
											    <td>3</td>
											    <td><input  type="text" placeholder="请输入物品名称" dc=""/></td>
											    <td><input  type="text" placeholder="请输入数量" dc=""/></td>
											    <td></td>
											  </tr>
											  
										</table>
									</div>
                            	</td>
                            </tr>
                          </table>
                        
                           <div class="tex_c mt22"><a id="submitBtn" href="javascript:saveMovingOutData();" class="btn1 f14 fb mr10">申请</a></div>
                        <!-- 右侧 结束-->
        		</div>
          </div>
    </div>
</body>

<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/movinginfo/js/moving_out.js"></script>
</html>