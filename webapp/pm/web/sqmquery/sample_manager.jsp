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



<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">SQM查询</a>&nbsp;>&nbsp;样本信息</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">样本信息</div><!--1019-->
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                          		
                            	<td>
	                            	<span class="f14">PKG&nbsp;&nbsp;</span>
	                            	<input class="mr20" type="text" placeholder="请输入PKG"  id="PKG" value="49" name="sampleInfo.PKG" dc=""/>
                                </td>
                                <td>
                                	<span class="f14">产品编号&nbsp;&nbsp;</span>
	                            	<input class="mr20" type="text" placeholder="请输入产品编号" style="width:200px" id="prod" name="sampleInfo.prod" dc=""/>
                                </td>
                               <td>
	                            	<input name='sampleInfo.isNew' class="mr20" type="checkbox" checked id="isNew" dc=""/><label>最新修改号</label>
                                </td>
                                <td>
	                            	<input name='sampleInfo.isComplete' class="mr20" id="isComplete" type="checkbox" dc=""/><label>未录入</label>
                                </td>
                                <td>
                                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="f14">产品基准:&nbsp;&nbsp;</span>
	                            	<input type="radio"  name="strStan" value='0' dc=""/><label>BOM全部</label>
									<input type="radio"  name="strStan"  value="1" dc=""/><label>一年销售</label>
									<input type="radio"  checked name="strStan" value='2' dc=""/><label>一年生产</label>
                                </td>
                                <td>
                                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="f14">排序:&nbsp;&nbsp;</span>
	                            	<input type="radio" name="pSort" checked value='0' dc=""/><label>PKG排序</label>
									<input type="radio" name="pSort"  value="1" dc=""/><label>产品排序</label>
                                </td>
                            	<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                                
                            </tr>
                            <tr>
                            	
                            </tr>
                          </table>
                           <div class="t_r table_wrap mt18" style="overflow-x:auto;">  
                           <div class="t_r mt18" style="width:auto;">  
                          		<table  id="tabListTable"  class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                                 <tr> 
	                                                <th width="47px" rowspan="2">序号</th>
													<th width="80px" rowspan="2">pkg</th>
													<th width="80px" rowspan="2">pkg名称</th>
													<th width="80px"rowspan="2">产品编号</th>
													<th width="200px" rowspan="2">产品名称</th>
													<th width="600px" colspan=5>容器外观</th>
													<th width="600px" colspan=5>容器小招贴-修改日期</th>
													<th width="600px" colspan=5>容器box-样本</th>
													<th width="600px" colspan=5>瓶盖-样本</th>

                                                 </tr> 
                                                  <tr> 
                                                
													<th>样本</th>
													<th>修改编号</th>
													<th>修改日期</th>
													<th  width="200px">备注</th>	
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
    </div>
</body>

<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmquery/js/sample_manager.js"></script>
</html>