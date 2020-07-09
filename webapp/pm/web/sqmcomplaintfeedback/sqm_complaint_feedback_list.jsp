<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>抱怨及内部反馈</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>
<script type="text/x-jsrender" id="lineListTemplate">
       <select id="lineList" name="sqmComplaintFeedback.lineId" class="mr20"  dc="true" >
		{{renderOptionFun lineList "code_value" "code_nm" "" "生产线" /}}
	 </select>
</script>

<script type="text/x-jsrender" id="dataListTemplate">
        <tr name="dataList" role="row">
        <td width="80px">{{:cf_date}} </td>
        <td  width="47px">{{:#index+1}}</td>
        <td class="w47">{{:cf_source=='1'?"内部":"外部"}} </td>
        <td class="w47">{{:cf_type=='1'?"投诉":"反馈"}} </td>
        <td width="80px">{{:cf_address}} </td>
        <td width="80px">{{:product_type}} </td>
        <td width="110px">{{:product_rank}} </td>		
 		<td class="w47">{{:product_pack}}</td>
 		<td class="w47">{{:number_barrel}}</td>
 		<td class="w47">{{:number_litres}}</td>
 		<td width="110px">{{:lotnumber}}</td>
 		<td width="110px">{{:feedback_type}}</td>
 		<td width="220px">{{:feedback_content}}</td>
        <td width="220px">{{:processing_result}}</td>
 		<td class="w47">{{:close_yn=='y'?"关闭":"未关闭"}}</td>
 		<td width="220px">{{:add_measures}}</td>
        <td width="100px"><a href="javascript:updateEntityDataFun('{{:id}}');" class="btn3">详细</a>  </td>   
		<td width="100px"><a href="javascript:deleteDataFun('{{:id}}');" class="btn3"> 删除</a></td>
        </tr>
</script>

<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">SQM查询</a>&nbsp;>&nbsp;抱怨及内部反馈</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">抱怨及内部反馈<a href="javascript:addEntityDataFun();" class="btn1 f14 fb fr">新增</a></div><!--1019-->
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                               <td>
                               <span class="f14">类别</span>&nbsp;&nbsp;
                                    <select name="sqmComplaintFeedback.cf_type" class="mr20" dc="true">
								      <option value="">-类别-</option>
								      <option value="1">投诉</option>
								      <option value="2">反馈</option>
						        </select>
						       </td>
						        <td>
						           <span class="f14">来源</span>&nbsp;&nbsp;
                                    <select name="sqmComplaintFeedback.cf_source" class="mr20" dc="true">
								      <option value="">-来源-</option>
								      <option value="1">内部</option>
								      <option value="2">外部</option>
						        </select>
						       </td>
                                <td>
                                	<span class="f14">内容概述（客户问题反馈）</span>&nbsp;&nbsp;
                                    <input class="mr20" type="text" placeholder="请输入内容概述" name="sqmComplaintFeedback.feedback_content" dc="">
                                </td>
                                <td><a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                            </tr>
                          </table>
                          <div class="table_wrap" style="overflow-x:auto;">
                               <div class="t_r mt18" style="width:auto;">  
                					<table class="t_r_title tt" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
                                	<thead>
                                                 <tr> 
                                                   <th width="80px">日期</th>
                                                   <th width="47px">序号</th>
                                                   <th class="w47">来源</th>
                                                   <th class="w47">类别</th>
                                                   <th width="80px">反馈地点</th>
                                                   <th width="80px">产品类型</th>
                                                   <th width="110px">级别</th>
                                                   <th class="w47">包装</th>
                                                   <th class="w47">数量（桶）</th>
                                                   <th class="w47">数量（升）</th>
                                                   <th width="110px">批号</th>
                                                   <th width="110px">抱怨类别</th>
                                                   <th width="220px">内容概述（客户问题反馈）</th>
                                                   <th width="220px">处理内容、结果</th>
                                                   <th class="w47">是否关闭</th>
                                                   <th width="220px">增加措施</th>
                                                   <th width="100px">详细</th>
                                                   <th width="100px" id="last">删除</th>
                                                 </tr> 
                                             </thead> 	           
                                </table>
                                <div class="t_r_content" > 
                                	<table  class="tt tt1" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;"> 
                                    	<tbody id="t_r_content" onscroll="aa()">
                                    	</tbody>
                                      </table> 
                                </div> 
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
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmcomplaintfeedback/js/sqm_complaint_feedback_list.js"></script>