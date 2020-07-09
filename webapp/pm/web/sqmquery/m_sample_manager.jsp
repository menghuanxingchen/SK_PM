<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>样本信息</title>
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
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18" style="font-size:35px;height:66px;">样本信息</div><!--1019-->
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" style="margin-top:2%"  id="search_area">
                          	<tr>
                          		
                            	<td>
	                            	<span class="f14" style="font-size:28px">PKG&nbsp;&nbsp;</span>
	                            	<input class="mr20" type="text" style="font-size:20px;height:38px;width:170px" placeholder="请输入PKG"  id="PKG" value="49" name="sampleInfo.PKG" dc=""/>
                                </td>
                                <td>
                                	<span class="f14" style="font-size:28px">产品编号&nbsp;&nbsp;</span>
	                            	<input class="mr20" type="text" style="font-size:20px;height:38px;width:170px" placeholder="请输入产品编号"  id="prod" name="sampleInfo.prod" dc=""/>
                                </td>
                              
                                
                            </tr>
                            
                            <tr>
                             	<td><br/>
	                            	<input name='sampleInfo.isNew' style="font-size:20px;height:30px;width:30px" class="mr20" type="checkbox" checked id="isNew" dc=""/><label style="font-size:20px;">最新修改号</label>
                                </td>
                                <td>
	                            	<input name='sampleInfo.isComplete' style="font-size:20px;height:30px;width:30px" class="mr20" id="isComplete" type="checkbox" dc=""/><label style="font-size:20px;">未录入</label>
                                </td> 
                            </tr>
                            <tr>
                            	<td><br/>
                                	<span class="f14" style="font-size:28px">产品基准:&nbsp;&nbsp;</span>
	                            	<input type="radio" style="font-size:20px;height:30px;width:30px" name="strStan" value='0' dc=""/><label>BOM全部</label>
									<input type="radio" style="font-size:20px;height:30px;width:30px" name="strStan"  value="1" dc=""/><label>一年销售</label>
									<input type="radio" style="font-size:20px;height:30px;width:30px" checked name="strStan" value='2' dc=""/><label>一年生产</label>
                                </td>
                                <td>
                                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="f14" style="font-size:28px">排序:&nbsp;&nbsp;</span>
	                            	<input type="radio" style="font-size:20px;height:30px;width:30px" name="pSort" checked value='0' dc=""/><label>PKG排序</label>
									<input type="radio" style="font-size:20px;height:30px;width:30px" name="pSort"  value="1" dc=""/><label>产品排序</label>
                                </td>
                            	<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="search_btn"  class="btn1 f14 fb" style="margin-left:15%;" >查询</a></td>
                                	
                            </tr>
                          </table>
                           <div class="t_r table_wrap mt18" style="width:3500px">  
                           <div class="t_r mt18" style="width:auto;">  
                          		<table  id="tabListTable" style="font-size:18px" class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                                <tr> 
                                                <th width="55px" rowspan="2">序号</th>
												<th width="100px" rowspan="2">pkg</th>
												<th width="100px" rowspan="2">pkg名称</th>
												<th width="120px" rowspan="2">产品编号</th>
												<th width="200px" rowspan="2">产品名称</th>
												<th width="700px" colspan=5>容器外观</th>
												<th width="700px" colspan=5>容器小招贴-修改日期</th>
												<th width="700px" colspan=5>容器box-样本</th>
												<th width="700px" colspan=5>瓶盖-样本</th>

                                                </tr> 
                                                 <tr> 
                                               
												<th>样本</th>
												<th>修改编号</th>
												<th>修改日期</th>
												<th >备注</th>	
												<th>重量(kg)</th>
												
												<th >样本</th>
												<th >修改编号</th>
												<th >修改日期</th>
												<th >备注</th>
												<th >重量(kg)</th>
												
												<th >样本</th>
												<th >修改编号</th>
												<th >修改日期</th>
												<th >备注</th>
												<th >重量(kg)</th>
												
												<th >样本</th>
												<th >修改编号</th>
												<th >修改日期</th>
												<th >备注</th>
												<th >重量(kg)</th>

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

<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmquery/js/m_sample_manager.js"></script>
</html>