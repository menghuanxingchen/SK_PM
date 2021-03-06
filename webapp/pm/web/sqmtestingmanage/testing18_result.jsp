<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>18L检测结果</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>


<script type="text/x-jsrender" id="dataListTemplate">
    <tr name="dataList">
        <td width="47px">{{:#index+1}}</td>
		<td width="47px"><a href="javascript:detailEntityDataFun('{{:lineId}}','{{:lotnumver}}','{{:produnctCode}}');" class="btn3">详情</a>  </td>   
        <td width="60px">{{:lineId}} </td>
 		<td width="100px">{{:lotnumver}}</td>
		<td width="100px">{{:produnctCode}}</td>
 		<td width="100px">{{:produnctName}}</td>
 		<td width="100px">{{:productType}}</td>
	    <td width="100px">{{:customName}}</td>
	    <td width="100px">{{:unusualYn}}</td>
  		<td width="100px">{{:test_state}}</td>
		<td width="100px">{{:filling_date}}</td>
		<td width="100px">{{:filling_pipeline}}</td>
		<td width="100px">{{:oil_in_pipeline}}</td>

		<td width="100px">{{:oilbarrel}}</td>
		<td width="100px">{{:oilbarrelCode}}</td>

		<td width="100px">{{:filling_weight}}</td>
		<td width="100px">{{:filling_splash_yn}}</td>
		<td width="100px">{{:filling_front_pressure}}</td>
		<td width="100px">{{:filling_back_pressure}}</td>
		<td width="150px">{{:filling_first_article}}</td>
		<td width="150px">{{:filling_gland}}</td>
		<td width="150px">{{:printer_mouth_zic}}</td>
		<td width="150px">{{:printer_mouth_num}}</td>
		<td width="150px">{{:printer_mouth_lot}}</td>
		<td width="100px">{{:recheck_weight}}</td>
		<td width="100px">{{:reinspection_supplier_selection}}</td>
		<td width="100px">{{:reinspection_IBC_leadseal}}</td>

		<td width="150px">{{:weight_removal_formula}}</td>
		<td width="150px">{{:ehether_cullercan_cull}}</td>
		<td width="150px">{{:scan_first_num}}</td>
        <td width="150px">{{:start_pack_code}}</td>
        <td width="150px">{{:pack_code}}</td>
        <td width="500px" title="{{:abnormal_remarks}}">{{:abnormal_remarks}}</td>

   </tr>
</script>

<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">SQM检测结果</a>&nbsp;>&nbsp;18L检测结果</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">18L检测结果<a href="javascript:downloadExecl('test18ListTable','18L检测结果');" class="btn1 f14 fb fr">导出</a></div><!--1019-->
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                          		
                            	<td>
	                            	<span class="f14">日期&nbsp;&nbsp;</span>
	                            	<input id="start" name="testResult18Info.filling_datest"   class="laydate-icon w110" type="text" value="" dc="true" readonly/>&nbsp;~&nbsp;
									<input id="end" name="testResult18Info.filling_dateed"   class="laydate-icon w110" type="text" value="" dc="true" readonly/>
                                </td>
                                <td>
                                
                                </td>
                               
                                <td><a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                                
                            </tr>
                          </table>
                           <div class="t_r table_wrap mt18" style="overflow-x:auto;">  
                           <div class="t_r mt18" style="width:auto;">  
                          		<table  id="test18ListTable"  class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
                                	<thead>
                                                 <tr> 
                                                <th width="47px">序号</th>
                                                <th width="47px">操作</th>
                                                <th width="100px">生产线</th>
												<th width="100px">批号</th>
												<th width="100px">产品Code</th>
												<th width="100px">产品名称</th>
												<th width="100px">选型</th>
												<th width="100px">客户</th>
												<th width="100px">是否有异常</th>
												<th width="100px">状态</th>
												<th width="100px">罐装日期</th>
												<th width="100px">灌装机管线</th>
												<th width="100px">进油管线</th>
												<th width="100px">理桶机状态</th>
												<th width="100px">理桶机产品Code</th>
												
												<th width="100px">灌装机重量：</th>
												<th width="100px">灌装机是否溅油</th>
												<th width="150px">灌装机前压力表压力</th>
												<th width="150px">灌装机后压力表压力</th>
												<th width="100px">灌装机首样送检</th>
												<th width="150px">灌装机压盖效果是否正常</th>
												<th width="150px">桶身喷码机（ZIC专用）日期</th>
												<th width="150px">桶身喷码机（ZIC专用）顺序号</th>
												<th width="150px">桶身喷码机（ZIC专用）批号</th>
												<th width="100px">复检称重量</th>
												<th width="100px">复检称IBC供应商选择</th>
												<th width="100px">复检称IBC铅封</th>
												
												<th width="150px">重量剔除机箱重配方</th>
												<th width="150px">重量剔除机是否能剔除</th>
												<th width="150px">扫码数量核对正确</th>
												<th width="150px">开始物流码</th>
												<th width="150px">结束物流码</th>
												<th width="500px">异常记录</th>
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
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmtestingmanage/js/testing18_result.js"></script>
</html>