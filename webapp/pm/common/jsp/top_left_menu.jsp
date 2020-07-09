<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script type="text/javascript">
var CsGlobalObj = {};
CsGlobalObj.currentUserid = '<%=request.getSession().getAttribute("sys_hidden_currentUserId")%>';
CsGlobalObj.sys_hidden_deviceType = 'PC';
CsGlobalObj.sys_hidden_currentUsername = '<%=request.getSession().getAttribute("sys_hidden_currentUsername")%>';
CsGlobalObj.sys_hidden_dept = '<%=request.getSession().getAttribute("sys_hidden_dept")%>';
CsGlobalObj.sys_hidden_auth = '<%=request.getSession().getAttribute("sys_hidden_auth")%>';
CsGlobalObj.sys_hidden_subdept = '<%=request.getSession().getAttribute("sys_hidden_subdept")%>';
CsGlobalObj.sys_hidden_dept_name = '<%=request.getSession().getAttribute("sys_hidden_dept_name")%>';
</script>
<script language="javascript" type="text/javascript">
function logout(){
	 layer.confirm("您确定要退出系统吗？",function(){
		 $.ajax({
				url:getRequestUrl("/loginManagerController/initSession.do"),
				dataType:"json",
				data:[],
				success:function(data){
					pageForward("/pm/foodms/login/login.jsp");
				},
				error:function(error){
					lalert('网络原因操作失败！','error');
				}
			});
	 })
}

function changePwdPop(id){
	layer.open({
		  type: 1,
		  //skin: 'layui-layer-rim', //样式类名
		  closeBtn: 0, //不显示关闭按钮
		  shift: 2,
		  title: '密码修改', //不显示标题
		  shadeClose: true, //开启遮罩关闭
		  content: '<table id="pwdForm" class="tt tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">'+
		  			'<tr><td class="odd">密码</td><input type="hidden" value="'+id+'"  name="sysUserInfo.user_id"   dc=""/>'+
		  			'<td><input type="password" id="pwd"  dcrequired="请输入密码"    maxlength="50"/></td></tr>'+
		  			'<tr><td class="odd">确认密码</td>'+
		  			'<td><input type="password" id="pwdCheck" dcrequired="请输入确认密码"  name="sysUserInfo.pwd"  dc="" maxlength="50"/></td>'+
		  			'</tr></table>'+
		  			'<div class="tex_c mt22 mb30"><a href="javascript:changePwd();" class="btn1 f14 fb mr10">提交</a><a href="javascript:layer.closeAll();" class="btn1 f14 fb mr10">关闭</a></div>'
		});
}

function changePwd(){
	if($("#pwd").val()==$("#pwdCheck").val()){
		var checkfalg =	checkFormValue('pwdForm');
		if(checkfalg){
			layer.confirm("是否提交", {
			    btn: ['确定','取消'] //按钮
				}, function(){
					var postData = collectData("pwdForm");
					layer.load();//添加进度条
					$.ajax({
							url:getRequestUrl("/SysUserInfoController/updateEntityData.json"),
							dataType:"json",
							data:postData,
							success:function(result){
								layer.closeAll('loading'); //关闭进度条
								if(result.opflag){
									lalert('save','success',function(){
										 $.ajax({
												url:getRequestUrl("/loginManagerController/initSession.do"),
												dataType:"json",
												data:[],
												success:function(data){
													pageForward("/pm/foodms/login/login.jsp");
												},
												error:function(error){
													lalert('网络原因操作失败！','error');
												}
											});
									});
								}else{
									lalert(result.opmessage,'error');
								}
							},
							error:function(error){
								layer.closeAll('loading'); //关闭进度条
								lalert('网络原因操作失败！','error');
							}
					});
				})
			}
		}else{
			lalert("密码不一致",'warn');
			$("#pwdCheck").focus();
		}
}
</script>
<div class="header">
    	 <div class="fl mt10 ml30"><img src="<%=request.getContextPath()%>/pm/common/images/logo.png" class="mr10 mb3" /><span class="f15">MES系统</span></div>
        <div class="fr mr3 mt22">
        	<a href="#" class="write"><img src="<%=request.getContextPath()%>/pm/common/images/ico/person.png" class="mr10 mb3" />欢迎您，<%=request.getSession().getAttribute("sys_hidden_currentUsername") %></a>
        	<a href="javascript:changePwdPop('<%=request.getSession().getAttribute("id") %>');"  class="write"><img src="<%=request.getContextPath()%>/pm/common/images/ico/block.png" class="mr10 mb3" />密码修改</a>
            <a href="javascript:logout();" class="write"><img src="<%=request.getContextPath()%>/pm/common/images/ico/logout.png" class="ml10 mr10 mb3" />退出</a>
        </div>
    </div>
    <!--left menu-->
    <!--<iframe class="left_menu"  frameborder=0   border=0 src="left_menu.html" mce_src="left_menu.html"></iframe> -->
    <table height="100%"  class="left_menu"  border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td id="divc" width="200">
                <ul class="ce">
                <li id="sysMenu" style="display:none;">
                <a href="#" id="menu7" class="fb" ><img src="<%=request.getContextPath()%>/pm/common/images/ico/menu0.png" class="mr10" />系统功能</a>
                    <ul class="er">
                        <li><a  href="<%=request.getContextPath()%>/RoleInfoController/defaultJsp.do" id="menu71"><span class="ml12">角色信息</span></a></li>
<%--                         <li><a  href="<%=request.getContextPath()%>/CostTypeController/defaultJsp.do" id="menu73"><span class="ml12">费用类别信息</span></a></li> --%>
                        <li><a href="<%=request.getContextPath()%>/SupplierController/defaultJsp.do" id="menu74"><span class="ml12">供应商信息</span></a></li>
                        <li><a href="<%=request.getContextPath()%>/pm/web/systemfunction/material_unit_list.jsp" id="menu75"><span class="ml12">材料单位信息</span></a></li> 
                    </ul>
                </li> 
                 <li id="basMenu" style="display:none;">
                 <a href="#" id="menu1" class="fb" ><img src="<%=request.getContextPath()%>/pm/common/images/ico/menu1.png" class="mr10" />基础信息</a>
                    <ul class="er">
                        <li><a  href="<%=request.getContextPath()%>/SysUserInfoController/defaultJsp.do" id="menu11"><span class="ml12">用户信息</span></a></li>
<%--                   <li><a  href="<%=request.getContextPath()%>/SysCodeInfoController/defaultJsp.do" id="menu12"><span class="ml12">共通代码</span></a></li>  --%>
                        <li><a  href="<%=request.getContextPath()%>/MachineTypeController/defaultJsp.do" id="menu13"><span class="ml12">设备类型</span></a></li> 
                        <li><a  href="<%=request.getContextPath()%>/MachineController/defaultJsp.do" id="menu14"><span class="ml12">设备信息</span></a></li>
                        <li><a  href="<%=request.getContextPath()%>/CheckItemController/defaultJsp.do" id="menu15"><span class="ml12">检查项目信息</span></a></li>
                        <li><a  href="<%=request.getContextPath()%>/MaintenanceItemController/defaultJsp.do" id="menu16"><span class="ml12">故障类型信息</span></a></li> 
                    </ul>
                </li>
            <%-- 普通用户看   --%>
               <li id="basMenu1" style="display:block;">
                 <a href="#" id="menu10" class="fb" ><img src="<%=request.getContextPath()%>/pm/common/images/ico/menu1.png" class="mr10" />基础信息</a>
                    <ul class="er">
   <%--                    <li><a  href="<%=request.getContextPath()%>/SysUserInfoController/defaultJsp.do" id="menu101"><span class="ml12">用户信息</span></a></li> --%>
   <%--              <li><a  href="<%=request.getContextPath()%>/SysCodeInfoController/defaultJsp.do" id="menu102"><span class="ml12">共通代码</span></a></li>  --%>
                        <li><a  href="<%=request.getContextPath()%>/MachineTypeController/defaultJsp.do" id="menu103"><span class="ml12">设备类型</span></a></li> 
                        <li><a  href="<%=request.getContextPath()%>/MachineController/defaultJsp.do" id="menu104"><span class="ml12">设备信息</span></a></li>
                        <li><a  href="<%=request.getContextPath()%>/CheckItemController/defaultJsp.do" id="menu105"><span class="ml12">检查项目信息</span></a></li>
                        <li><a  href="<%=request.getContextPath()%>/MaintenanceItemController/defaultJsp.do" id="menu106"><span class="ml12">故障类型信息</span></a></li> 
                    </ul>
                </li>
                
                
                <li id="basMenu2">
                    <a href="#" id="menu2" class="fb" ><img src="<%=request.getContextPath()%>/pm/common/images/ico/menu2.png" class="mr10" />保养巡查</a>
                    <ul class="er">
                        <li><a  href="<%=request.getContextPath()%>/PrePlanInfoController/defaultJsp.do" id="menu21"><span class="ml12">保养计划</span></a></li>
                        <li><a  href="<%=request.getContextPath()%>/pm/web/preplaninfo/preplan_history_list.jsp" id="menu22"><span class="ml12">保养实绩</span></a></li>
                        <li><a  href="<%=request.getContextPath()%>/InspectPlanController/defaultJsp.do" id="menu24"><span class="ml12">巡检计划</span></a></li>
                        <li><a  href="<%=request.getContextPath()%>/pm/web/inspectplan/inspectplan_actual_list.jsp" id="menu25"><span class="ml12">巡检实绩</span></a></li>
                        <li><a  href="<%=request.getContextPath()%>/SecCheckInfoController/defaultJsp.do" id="menu26"><span class="ml12">安全检查</span></a></li>
                    </ul>
                </li>
                <li id="basMenu3">
                 	<a href="#" id="menu3" class="fb" ><img src="<%=request.getContextPath()%>/pm/common/images/ico/menu3.png" class="mr10" />维修信息</a>
                    <ul class="er">
                        <li><a  href="<%=request.getContextPath()%>/RepairOrderInfoController/defaultJsp.do" id="menu31"><span class="ml12">维修单信息</span></a></li>
                        <li><a  href="<%=request.getContextPath()%>/MaintainLogController/defaultJsp.do" id="menu32"><span class="ml12">工作日志信息</span></a></li> 
                    </ul>
                </li>
               <li id="basMenu4">
                	<a href="#" id="menu4" class="fb" ><img src="<%=request.getContextPath()%>/pm/common/images/ico/menu4.png" class="mr10" />报表信息</a>
                    <ul class="er">
                    	<li><a  href="<%=request.getContextPath()%>/ReportAllController/defaultJsp.do" id="menu43"><span class="ml12">计划统计报表</span></a></li>
                        <li><a  href="<%=request.getContextPath()%>/MaintResStatisController/defaultJsp.do" id="menu41"><span class="ml12">维修故障统计</span></a></li>
                        <li><a  href="<%=request.getContextPath()%>/MaintResStatisController/defaultJspArea.do" id="menu44"><span class="ml12">维修地点统计</span></a></li>
                        <li><a  href="<%=request.getContextPath()%>/UserReportController/defaultJsp.do" id="menu42"><span class="ml12">员工业绩统计</span></a></li>                        
                    </ul>
                </li>
                <li id="basMenu5">
                	<a href="#" id="menu6" class="fb" ><img src="<%=request.getContextPath()%>/pm/common/images/ico/menu_6.png" class="mr10" />结算</a>
                    <ul class="er">
                        <li><a  href="<%=request.getContextPath()%>/pm/web/account/repair_order_unpayed.jsp" id="menu61"><span class="ml12">未结算维修单</span></a></li>
                        <li><a  href="<%=request.getContextPath()%>/pm/web/account/repair_order_payed.jsp" id="menu62"><span class="ml12">已结算维修单</span></a></li>
                    <%--   <li><a  href="<%=request.getContextPath()%>/pm/web/account/workout_list.jsp" id="menu63"><span class="ml12">出勤统计列表</span></a></li>--%>
                    </ul>
                </li>
                <li id="basMenu6">
                	<a href="#" id="menu8" class="fb" ><img src="<%=request.getContextPath()%>/pm/common/images/ico/menu7.png" class="mr10" />项目信息</a>
                    <ul class="er">
                    	<li><a  href="<%=request.getContextPath()%>/ProjectSummaryController/defaultJsp.do" id="menu81"><span class="ml12">项目信息</span></a></li>
                    </ul>
                </li>
                <li id="basMenu7">
                	<a href="#" id="menu5" class="fb" ><img src="<%=request.getContextPath()%>/pm/common/images/ico/menu6.png" class="mr10" />提案书</a>
                    <ul class="er">
                        <li><a  href="<%=request.getContextPath()%>/pm/web/proposal/proposal_info_list.jsp" id="menu51"><span class="ml12">Report信息</span></a></li>
                    </ul>
                </li>
                 <li id="basMenu8">
                	<a href="#" id="menu9" class="fb" ><img src="<%=request.getContextPath()%>/pm/common/images/ico/menu8.png" class="mr10" />报废信息</a>
                    <ul class="er">
                    	<li><a  href="<%=request.getContextPath()%>/ScrapController/defaultJsp.do" id="menu91"><span class="ml12">报废信息</span></a></li>
                    </ul>
                </li>
                <li id="basMenu9">
                	<a href="#" id="menuA" class="fb" ><img src="<%=request.getContextPath()%>/pm/common/images/ico/menu8.png" class="mr10" />停机维修信息</a>
                    <ul class="er">
                    	<li id="potMenu" style="display:none;"><a  href="<%=request.getContextPath()%>/MachinePotInfoController/defaultJsp.do" id="menuA1"><span class="ml12">设备管理</span></a></li>
                    	<li id="potPartMenu" style="display:none;"><a  href="<%=request.getContextPath()%>/MachinePotPartInfoController/defaultJsp.do" id="menuA2"><span class="ml12">部件管理</span></a></li>
                    	<li id="comMenu" style="display:none;"><a  href="<%=request.getContextPath()%>/ComponentsSysCodeInfoController/defaultJsp.do" id="menuA3"><span class="ml12">子部件管理</span></a></li>
                    	<li id="workMenu" style="display:none;"><a  href="<%=request.getContextPath()%>/WorktimeInfoController/defaultJsp.do" id="menuA5"><span class="ml12">工作班制管理</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/EmergentRepairInfoController/defaultJsp.do" id="menuA4"><span class="ml12">停机维修管理</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/MaintResStatisController/MTBFJsp.do" id="menuA6"><span class="ml12">平均故障间隔</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/MaintResStatisController/MTTAJsp.do" id="menuA7"><span class="ml12">平均故障时间</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/MaintResStatisController/MTTRJsp.do" id="menuA8"><span class="ml12">平均维修时间</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/MaintResStatisController/MRSJJsp.do" id="menuA10"><span class="ml12">每日故障时间</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/MaintResStatisController/MRCSJsp.do" id="menuA11"><span class="ml12">每日故障次数</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/MaintResStatisController/potPartStopCount.do" id="menuA9"><span class="ml12">部件停机次数</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/MaintResStatisController/codeStopCount.do" id="menuAA"><span class="ml12">子部件停机次数</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/MaintResStatisController/potPartStopReport.do" id="menuA12"><span class="ml12">停机维修报表</span></a></li>
                    </ul>
                </li>  
                <li id="basMenu10">
                	<a href="#" id="menuE" class="fb" ><img src="<%=request.getContextPath()%>/pm/common/images/ico/menu7.png" class="mr10" />SQM查询</a>
                    <ul class="er">
                    	<li><a  href="<%=request.getContextPath()%>/SqmqueryController/defaultJsp.do" id="menuE2"><span class="ml12">生产计划</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/SqmqueryController/PracticalJsp.do" id="menuE3"><span class="ml12">灌装日报</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/SqmqueryController/SampleManagerJsp.do" id="menuE4"><span class="ml12">样本信息</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/SqmqueryController/DocumentManagerJsp.do" id="menuE5"><span class="ml12">LBC文档</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/SqmComplaintFeedbackController/defaultJsp.do" id="menuE1"><span class="ml12">抱怨及内部反馈</span></a></li>
                    </ul>
                </li>   
                <li id="basMenu11">
                	<a href="#" id="menuD" class="fb" ><img src="<%=request.getContextPath()%>/pm/common/images/ico/menu7.png" class="mr10" />SQM检测</a>
                    <ul class="er">
                    	<li><a  href="<%=request.getContextPath()%>/SqmtestingManageController/monitorJsp.do" id="menuD17"><span class="ml12">监测情况</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/SqmtestingManageController/defaultJsp.do" id="menuD18"><span class="ml12">1-4L检测结果</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/pm/web/sqmtestingmanage/testing18_result.jsp" id="menuD19"><span class="ml12">18L检测结果</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/pm/web/sqmtestingmanage/testing200_result.jsp" id="menuD20"><span class="ml12">200L检测结果</span></a></li>
                    </ul>
                </li>
                <li id="basMenu12">
                	<a href="#" id="menuC" class="fb" ><img src="<%=request.getContextPath()%>/pm/common/images/ico/menu7.png" class="mr10" />评价管理</a>
                    <ul class="er">
                    	<li><a  href="<%=request.getContextPath()%>/SvaluationManageController/defaultJsp.do" id="menuC14"><span class="ml12">评价生成</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/pm/web/evaluationManage/evaluation_save.jsp" id="menuC15"><span class="ml12">开始评价</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/pm/web/evaluationManage/evaluation_result.jsp" id="menuC16"><span class="ml12">评价结果</span></a></li>
                    	
                    </ul>
                </li>
                              
                <li id="basMenu13">
                	<a href="#" id="menuB" class="fb" ><img src="<%=request.getContextPath()%>/pm/common/images/ico/menu7.png" class="mr10" />SQM管理</a>
                    <ul class="er">
                    	<li><a  href="<%=request.getContextPath()%>/SqmmanageController/defaultJsp.do" id="menuB10"><span class="ml12">生产线管理</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/TestItemInfoController/defaultJsp.do" id="menuB11"><span class="ml12">检测项管理</span></a></li>
                    	<li><a  href="<%=request.getContextPath()%>/TestingProcessInfoController/defaultJsp.do" id="menuB12"><span class="ml12">检测流程管理</span></a></li>
                    	<li id="fillplan" ><a  href="<%=request.getContextPath()%>/pm/web/sqmmanage/getfillplan_list.jsp" id="menuB13"><span class="ml12">灌装计划</span></a></li>
                    </ul>
                </li>
                <li id="basMenu14">
                	<a href="#" id="menuF" class="fb" ><img src="<%=request.getContextPath()%>/pm/common/images/ico/menu3.png" class="mr10" />搬入搬出管理</a>
                    <ul class="er">
                    	<li><a  href="<%=request.getContextPath()%>/MovingListController/defaultJsp.do" id="menuF10"><span class="ml12">搬入搬出信息</span></a></li>   
                    	<li><a  href="<%=request.getContextPath()%>/MovingInController/defaultJsp.do" id="menuF11"><span class="ml12">搬入证申请</span></a></li> 
                    	<li><a  href="<%=request.getContextPath()%>/MovingOutController/defaultJsp.do" id="menuF12"><span class="ml12">搬出证申请</span></a></li>                  	
                    </ul>
                </li>
               </ul>
            </td>
            <td width="11px"><img id="arrowLeft" src="<%=request.getContextPath()%>/pm/common/images/left.png" onclick="TestBlack('divc');" /><img id="arrowRight" style="display:none;" src="<%=request.getContextPath()%>/pm/common/images/right.png" onclick="TestBlack('divc');"  /></td>
          </tr>
    </table>
<script language="javascript" type="text/javascript">
	window.onload=function(){ 
		var userId = '<%=request.getSession().getAttribute("sys_hidden_currentUserId")%>';
		var Sqmrolecode = '<%=request.getSession().getAttribute("sys_hidden_Sqmrolecode")%>';
		   if(userId=='admin'||userId=='lg00175'){
			   $("#sysMenu").css('display','block');
			   $("#basMenu").css('display','block');
			   $("#potMenu").css('display','block');
			   $("#potPartMenu").css('display','block');
			   $("#comMenu").css('display','block');
			   $("#basMenu1").css('display','none');
			   $("#workMenu").css('display','block');
		   }
		   if(userId=='lg00179'||userId=='lg00547'||userId=='lg00260'){
			   $("#workMenu").css('display','block');
		   }

		   //检测员有查看权限
		   if( (userId!='admin' && userId!='lg00258') && Sqmrolecode != "1"  ){
			   $("#fillplan").css('display','none');
		   }
		   
		 	//评价显示权限
		   if(userId=='admin'||userId=='lg00258'){
			   $("#menuC14").css('display','block');
		   }else{
			   $("#menuC14").css('display','none');
			   
		   }
		 //开始评价显示权限
		   if(userId=='admin'||userId=='lg00302'||userId=='lg00158'||userId=='lg00157'||userId=='lg00258'){
			   $("#menuC15").css('display','block');
		   }else{
			   $("#menuC15").css('display','none');
			   
		   }
		 
		   //门卫只有搬入搬出权限
		   if(userId=='lg00999'){
			   $("#basMenu1").css('display','none');
			   $("#basMenu2").css('display','none');
			   $("#basMenu3").css('display','none');
			   $("#basMenu4").css('display','none');
			   $("#basMenu5").css('display','none');
			   $("#basMenu6").css('display','none');
			   $("#basMenu7").css('display','none');
			   $("#basMenu8").css('display','none');
			   $("#basMenu9").css('display','none');
			   $("#basMenu10").css('display','none');
			   $("#basMenu11").css('display','none');
			   $("#basMenu12").css('display','none');
			   $("#basMenu13").css('display','none');
			   $("#basMenu14").css('display','block');
		   }
	} 
</script>