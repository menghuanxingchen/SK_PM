<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>1-4L检测结果</title>
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
  		<td width="100px">{{:test_state==0?'未进行':(fill_STATE==1?'进行中':(fill_STATE==2?'异常':'已完成'))}}</td>
		<td width="100px">{{:filling_date}}</td>
		<td width="100px">{{:filling_pipeline}}</td>
		<td width="100px">{{:oil_in_pipeline}}</td>
		<td width="100px">{{:labelling1}}</td>
		<td width="100px">{{:labelling2}}</td>
		<td width="100px">{{:labelling3}}</td>
		<td width="100px">{{:labelling4}}</td>
		<td width="100px">{{:labelling5}}</td>
		<td width="100px">{{:oilbarrel}}</td>
		<td width="100px">{{:oilbarrelCode}}</td>

		<td width="80px">{{:filling_weight}}</td>
		<td width="100px">{{:filling_splash_yn}}</td>
		<td width="150px">{{:filling_front_pressure}}</td>
		<td width="150px">{{:filling_back_pressure}}</td>
		<td width="100px">{{:filling_first_article}}</td>
		<td width="150px">{{:filling_pakage_qualified}}</td>
		<td width="150px">{{:thread_mouth_wc}}</td>
		<td width="100px">{{:camera_product_code}}</td>
		<td width="100px">{{:aluminum_foil_stripper}}</td>
		<td width="80px">{{:powerof_sealing_machine}}</td>
		<td width="100px">{{:bottlebody_printer_date}}</td>
		<td width="100px">{{:bottlebody_printer_lot}}</td>
		<td width="100px">{{:bottlebody_distinct}}</td>
		<td width="100px">{{:recheck_weight}}</td>
		<td width="100px">{{:reinspection_supplier_selection}}</td>
		<td width="100px">{{:reinspection_IBC_leadseal}}</td>

		<td width="100px">{{:unpacker_code}}</td>
		<td width="100px">{{:unpacker_list}}</td>
		<td width="100px">{{:weight_removal_formula}}</td>
		<td width="100px">{{:ehether_cullercan_cull}}</td>
		<td width="100px">{{:excludingcode_spraying_date}}</td>
		<td width="100px">{{:stripping_machinebatch_number}}</td>
		<td width="100px">{{:inspection_stripping_machine}}</td>
		<td width="100px">{{:product_specification_confirmation}}</td>
		<td width="80px">{{:scan_first_case}}</td>
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
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">SQM检测结果</a>&nbsp;>&nbsp;1-4L检测结果</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">1-4L检测结果<a href="javascript:downloadExecl('test4ListTable','1-4L检测结果');" class="btn1 f14 fb fr">导出</a></div><!--1019-->
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                          	<tr>
                          		
                            	<td>
	                            	<span class="f14">日期&nbsp;&nbsp;</span>
	                            	<input id="start" name="testResult4Info.filling_datest"   class="laydate-icon w110" type="text" value="" dc="true" readonly/>&nbsp;~&nbsp;
									<input id="end" name="testResult4Info.filling_dateed"   class="laydate-icon w110" type="text" value="" dc="true" readonly/>
                                </td>
                                <td>
                                
                                </td>
                               
                                <td><a href="#" id="search_btn" class="btn2"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                                
                            </tr>
                          </table>
                           <div class="t_r table_wrap mt18" style="overflow-x:auto;">  
                           <div class="t_r mt18" style="width:auto;">  
                          		<table  id="test4ListTable"  class="t_r_title tt" border="0" cellspacing="0" cellpadding="0"   style="table-layout:fixed;" > 
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
												<th width="100px">贴标1</th>
												<th width="100px">贴标2</th>
												<th width="100px">贴标3</th>
												<th width="100px">贴标4</th>
												<th width="100px">贴标5</th>
												<th width="100px">理桶机状态</th>
												<th width="100px">理桶机产品Code</th>
												<th width="80px">灌装机重量</th>
												<th width="100px">灌装机是否溅油</th>
												<th width="150px">灌装机前压力表压力</th>
												<th width="150px">灌装机后压力表压力</th>
												<th width="100px">灌装机首样送检</th>
												<th width="100px">灌装机包材是否合格</th>
												<th width="150px">螺纹口喷码机（WC专用）油品规格</th>
												<th width="100px">照相机产品CODE</th>
												<th width="100px">铝箔剔除机测试正常</th>
												<th width="80px">封口机功率</th>
												<th width="100px">瓶身喷码机日期</th>
												<th width="100px">瓶身喷码机批号</th>
												<th width="100px">瓶身喷码机清晰可见</th>
												<th width="100px">复检称重量</th>
												<th width="100px">复检称IBC供应商选择</th>
												<th width="100px">复检称IBC铅封</th>
												
												<th width="100px">开箱机纸箱CODE</th>
												<th width="100px">开箱机胶带选择（列表）</th>
												<th width="100px">整箱剔除机箱重配方</th>
												<th width="100px">整箱剔除机是否能剔除</th>
												<th width="100px">整箱剔除机外箱喷码日期</th>
												<th width="100px">整箱剔除机批号</th>
												<th width="100px">整箱剔除机小标检查（1L、4L）</th>
												<th width="100px">整箱剔除机产品规格确认</th>
												<th width="80px">第一箱扫码</th>
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
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmtestingmanage/js/testing4_result.js"></script>
</html>