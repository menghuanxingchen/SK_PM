<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>搬入证申请</title>
	<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
	<%@ include file="/pm/common/jsp/commonJs.jsp"%>
	<script src="<%=request.getContextPath()%>/pm/common/js/jedate/jquery.jedate.js" type="text/javascript"></script>
	<link href="<%=request.getContextPath()%>/pm/common/js/jedate/skin/jedate.css" rel="stylesheet" type="text/css" />
</head>

<script type="text/x-jsrender" id="allUserGroupSubDownListTemplate2">
	<select id="deal_user_id2" style="margin-right:20px;"  dcrequired="请选择SK联系人"　 name="movingInInfo.skContactsNo" dc="true">
		{{renderOptionFun allUser "user_num" "user_nm" "" "SK联系人"/}}
 	</select>
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" id="formId">
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">搬入搬出证管理</a>&nbsp;>&nbsp;搬入证申请</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">搬入证申请</div><!--1019-->
  						<!--查询条件-->
                          <table   class="tt  tt2 record_table table_wrap mt18">
                          
                          <tr>
                         		 <td class="odd w10">
	                            	<span >搬入证申请编号：&nbsp;&nbsp;</span>
                                </td>
                                <td>
                                	<input  type="text" id="minNo" disabled name="movingInInfo.minNo"  dc=""/>
                                </td>
                          </tr>
                          	<tr>
                            	<td class="odd w10">
	                            	<span >搬入人姓名：&nbsp;&nbsp;</span>
                                </td>
                                <td>
                                	<input  type="text" id="minName" placeholder="请输入搬入人姓名" dcrequired="请输入搬入人姓名"  name="movingInInfo.minName" dc=""/>
                                </td>
                                <td class="odd w10">
                                	<span >公司名：&nbsp;&nbsp;</span>
                                </td>
                               <td>
                               		<input  type="text" id="minCompany" placeholder="请输入公司名" dcrequired="请输入公司名" name="movingInInfo.minCompany" dc=""/>
                               </td>
                                <td class="odd w10">
                                	<span >车牌号：&nbsp;&nbsp;</span>
                                </td>
                                <td>
                               		 <input type="text" id="minCarno" placeholder="请输入车牌号" dcrequired="请输入车牌号" name="movingInInfo.minCarno" dc=""/>
                                </td>
                            </tr>
                            <tr>
                            	
                            	<td class="odd w10">
	                            	<span class="odd">联系方式：&nbsp;&nbsp;</span>
                                </td>
                               <td>
                                	<input type="text" id="minPhone" placeholder="请输入联系方式" dcrequired="请输入联系方式" name="movingInInfo.minPhone" dc=""/>
                                </td>
                                 <td class="odd w10">
                                	<span >SK联系人：&nbsp;&nbsp;</span>
                                </td>
                               <td>
                               		<span id="userChoose"></span> 		
                               </td>
                                <td class="odd w10"> 
                                	<span >搬入时间：&nbsp;&nbsp;</span>
                                </td>
                                 <td>
                                	<input name="movingInInfo.minTime" dcrequired="请选择搬入时间" readonly class="dateinput dateicon" id="minTime" type="text" placeholder="请选择搬入时间"   dc="true" />
                                </td>
                            </tr>
                            <tr>
                                <td class="odd w10">
	                            	 <span >搬入目的：&nbsp;&nbsp;</span>
	                            </td>
                           		<td colspan="3">
                           			<table class="abcd">
										  <tr>
										     <td><label><input style="width:40px" checked name="minGoalType" type="radio" value="送货" />送货 </label> </td>
										     <td><label><input style="width:40px" name="minGoalType" type="radio" value="提货" />提货 </label> </td>
										     <td><label><input style="width:40px" name="minGoalType" type="radio" value="垃圾" />垃圾 </label> </td>
										     <td><label><input style="width:40px" name="minGoalType" type="radio" value="其他" />其他 </label> </td>
										  	 <td><input class="mr20" type="text" placeholder="请输入目的" id="minGoalContent" name="movingInInfo.minGoalContent" dc=""/></td>
										  </tr>
										 
									</table>
                            	</td>
                            </tr>
                          <!--   <tr>
	                             <td class="odd w10">
	                            	 <span >以后是否需要搬出：&nbsp;&nbsp;</span>
	                            </td>
                           		<td colspan="3">
                           			<table class="abcd">
										  <tr>
										     <td><label><input style="width:50px" checked name="moutYN" type="radio" value="Y" />是 </label> </td>
										     <td><label><input style="width:50px" name="moutYN" type="radio" value="N" />否 </label> </td>
										 </tr>
									</table>
	                            
                            	</td>
                            </tr> -->
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
										    <td><input  type="text" placeholder="请输入物品名称" dcrequired="请输入物品名称"  dc=""/></td>
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
                            	</td>
                            </tr>
                          </table>
                        
                           <div class="tex_c mt22"><a id="submitBtn" href="javascript:saveMovingInData();" class="btn1 f14 fb mr10">申请</a></div>
                        <!-- 右侧 结束-->
        		</div>
          </div>
    </div>
</body>

<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/movinginfo/js/moving_in.js"></script>
</html>