<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>巡检实绩详情</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/inspectplan/js/inspectplan_checkdetail_list.js"></script>
</head>

<body>
   <%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">保养巡查</a>&nbsp;>&nbsp;巡检实绩详情</div>
              <input type="hidden" id="planNm" /> <!--传参数--by fish  -->
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">                
                		<div class="title mb18">巡检实绩详情</div>
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0">
                          	<tr>
                                <td>
                                	<span class="f14">计划名称:</span>&nbsp;&nbsp;
                                    <span class="f14" id="plan_name"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </td>
                                 <td>
                                	<span class="f14">计划日期:</span>&nbsp;&nbsp;
                                    <span class="f14" id="plan_date"></span>
                                </td>
                            </tr>
                          </table>
                      <div class="t_r table_wrap mt18">  
                		<!-- <table id="detailtitle" class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                        </table> --> 
                        <div class="t_r_content" > 
                      	<table id="detailList"class="tt" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
                        </table> 
                        </div> 
                  	  </div>
                  
                       <!--  分页  开始-->                          
                       <div class="paging" id="pagefoot">                                            
                       </div>
                       <!--  分页  结束-->         
        
                        <!-- 右侧 结束-->
					     <div class="tex_c mt22 mb30">
					    <!--  <a href="javascript:downloadExecl('detailList','巡检计划');" class="btn1 f14 fb mr10">导出</a> -->
                         <a href="#" id="checkBt" class="btn1 f14 fb mr10">确认</a><a href="#" id="backBT" class="btn1 f14 fb mr10">返回</a></div>
        		</div>
          </div>
    </div>
</body>
</html>
