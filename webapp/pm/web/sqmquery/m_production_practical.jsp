<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>灌装日报</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
	<script src="<%=request.getContextPath()%>/pm/common/js/jedate/jquery.jedate.js" type="text/javascript"></script>
	<link href="<%=request.getContextPath()%>/pm/common/js/jedate/skin/jedate.css" rel="stylesheet" type="text/css" />
</head>

<script type="text/javascript">
	var CsGlobalObj = {};
	CsGlobalObj.currentUserid = '<%=request.getSession().getAttribute("sys_hidden_currentUserId")%>';
	CsGlobalObj.sys_hidden_deviceType = 'PC';
	CsGlobalObj.sys_hidden_currentUsername = '<%=request.getSession().getAttribute("sys_hidden_currentUsername")%>';
	CsGlobalObj.sys_hidden_dept = '<%=request.getSession().getAttribute("sys_hidden_dept")%>';
	CsGlobalObj.sys_hidden_auth = '<%=request.getSession().getAttribute("sys_hidden_auth")%>';
	CsGlobalObj.sys_hidden_subdept = '<%=request.getSession().getAttribute("sys_hidden_subdept")%>';
	CsGlobalObj.sys_hidden_dept_name = '<%=request.getSession().getAttribute("sys_hidden_dept_name")%>';
	function loadURL(url) {
        var iFrame;
        iFrame = document.createElement("iframe");
        iFrame.setAttribute("src", url);
        iFrame.setAttribute("style", "display:none;");
        iFrame.setAttribute("height", "0px");
        iFrame.setAttribute("width", "0px");
        iFrame.setAttribute("frameborder", "0");
        document.body.appendChild(iFrame);
        // 发起请求后这个iFrame就没用了，所以把它从dom上移除掉
        iFrame.parentNode.removeChild(iFrame);
        iFrame = null;
	 }

	function goBack() {
		loadURL("haleyAction://back");
	}


</script>
<body>
 <div class="title mb18"><a href="#" id="back_btn" onclick="goBack();" class="btn1 f14" style="position:fixed;" ><</a></div>

      <div class="main_wrap" >
      		<!--breadcrumbs-->
        	
        	<!--右侧 开始--> 
        	<div class="main" id="aaa">
            
            		<div class="title mb18" style="font-size:35px;height:66px;">灌装日报</div><!--1019-->
				<!--查询条件-->
                      <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                      	<tr>
                      		
                        	<td>
                         	<span class="f14" style="font-size:28px">生产日期&nbsp;&nbsp;</span>
                         	<input id="start"  name="ProductionPlan.startdate"  class="dateinput dateicon" style="font-size:20px;height:38px;width:170px"  type="text" value="" dc="true" readonly/>
					
                            </td>
                           
                            <td>
                            	<a href="#" id="search_btn"  class="btn1 f14 fb" style="margin-left:15%;" >查询</a>
                            </td>
                            
                        </tr>
                      </table>
                       <div class="t_r table_wrap mt18" style="font-size:26px" style="overflow-x:auto;">  
                       <div class="t_r mt18" style="width:auto;">  
                      		<table  id="tabListTable"  class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                            	<thead >
                                    <tr id="thead_content">
                                    </tr>
                                </thead> 	
                                <tbody id="t_r_content" onscroll="aa()">
                                </tbody>           
                            </table>
                           
                   	  </div>
               </div> 
                   <!--  分页  开始-->
                      
                    <div class="paging" id="pagefoot"></div>
                      <!--  分页  结束-->
    
                    <!-- 右侧 结束-->
    		</div>
      </div>

</body>

<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmquery/js/m_production_practical.js"></script>
</html>