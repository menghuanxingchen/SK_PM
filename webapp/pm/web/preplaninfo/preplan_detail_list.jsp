<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>保养实绩详情</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
</head>
<script> 
  function aa(){ 
     var a=document.getElementById("t_r_content").scrollTop; 
     <!-- document.getElementById("t_r_t").scrollLeft=b; -->
  } 
</script> 
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">保养巡查</a>&nbsp;>&nbsp;保养实绩详情</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                <input type="hidden" id="planNm" />
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                <td>
                                	<span class="f14">计划名称 </span>&nbsp;&nbsp;
                                    <input  type="text" id="plan_nm" style="border-bottom-style:none;border-top-style:none;border-left-style:none;border-right-style:none;" disabled/>
                                </td>
                            </tr>
                          </table>
                          <div class="t_r table_wrap mt18" id="table">  
                          		<!-- <table id="detailtitle"  class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"> 
                                </table> -->
                                <div class="t_r_content" > 
                                	<table  class="tt" border="0" cellspacing="0" cellpadding="0" id="detailList"> 
                                	</table> 
                                </div> 
                       	  </div>
                  
                       <div class="tex_c mt22 mb30"><!-- <a  href="javascript:method1('detailList','保养实绩');" class="btn1 f14 fb mr10">导出</a> -->
                       <a href="javascript:updateConfirm();" id="confirmBtn" class="btn1 f14 fb mr10">确认</a><a href="javascript:goBack();" class="btn1 f14 fb mr10">返回</a></div>
        
                        <!-- 右侧 结束-->
        		</div>
          </div>
    </div>
</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/preplaninfo/js/preplan_detail_list.js"></script>
</html>