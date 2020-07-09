<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设备报废新增</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
</head>
<script type="text/x-jsrender" id="mainMachineYnTemplate">
       <select name="scrapInfo.main_machine_yn" class="mr20"  dc="true" >
		{{renderOptionFun stateYn "code_value" "code_nm" "" "noflag" /}}
	 </select>
</script>
<script type="text/x-jsrender" id="spareYnTemplate">
       <select name="scrapInfo.spare_yn" class="mr20"  dc="true" >
		{{renderOptionFun stateYn "code_value" "code_nm" "" "noflag" /}}
	 </select>
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
	  <div class="contents">
          <div class="main_wrap">
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">设备报废</a>&nbsp;>&nbsp;设备报废新增</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">设备报废基本信息</div>
                  
        				<div class="w100" id="formId">
                         <table class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0" >
                            <tr>
                               <td class="odd w10">报废设备名称</td>
                               <td><input id="machineAddBt" type="text" dcrequired="请选择设备" readonly/>
                               <input id="machineId" type="hidden" name="scrapInfo.scrap_machine_id"  dc="true"  />
                               <input id="machineType" type="hidden" name="scrapInfo.machine_type_id"  dc="true"   />
                               <input id="machineSpecies" type="hidden" name="scrapInfo.machine_species_id"  dc="true" />
                                </td>
                            	 <td class="odd w10">报废数量</td>
                                 <td><input  type="text" name="scrapInfo.scrap_num" dcrequired="请添加数量"  dctype="数字" dc="true" maxlength='25' dc=""/>
                                </td>
                            </tr>
                            <tr>
                            	<td class="odd w10">报废原因</td>
                                <td><input type="text" name="scrapInfo.scrap_reason" dc="true" maxlength='500' /></td>  	
                                <td class="odd">报废时间时间</td>
                                <td>
                                	<input class="laydate-icon" id="scrapDate" type="text" name="scrapInfo.scrap_date" dc="true" />
                                </td>
                            </tr>
                            <tr>
                            	<td class="odd">是否属于关键设备</td>
                                <td id="mainMachineYnForm">
                                </td>	
                                </td>
                            	<td class="odd">是否有备件</td>
                                <td  id="spareYnForm">
                                </td>	
                            </tr>
                        </table>
                        
                        <div class="tex_c mt22"><a href="javascript:saveForm();" class="btn1 f14 fb mr10">提交</a><a href="<%=request.getContextPath()%>/ScrapController/defaultJsp.do" class="btn1 f14 fb">返回</a></div>
                        <!-- 右侧 结束-->
    					</div>
        		</div>
          </div>
    </div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/scrapinfo/js/scrapinfo_add.js"></script>
</html>