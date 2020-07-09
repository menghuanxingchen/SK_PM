<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>检测流程修改</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>


<script type="text/x-jsrender" id="dataListTemplate">
   	  <tr>
         <td class="odd w10">生产线</td>
         <td >
 			<select id="lineList" disabled="disabled" name="testingProcessInfo.lineId" class="mr20"  dc="true" >
				{{renderOptionFun lineList "code_value" "code_nm" testingProcessInfo.lineId "生产线" /}}
			 </select>
			<input type="hidden" value="{{:testingProcessInfo.id}}"  name="testingProcessInfo.id"   dc=""/>
		</td>
            <td class="odd">选型</td>
             <td >
             <input type="text" dctype="选型" name="testingProcessInfo.productType" value="{{:testingProcessInfo.productType}}"  dc="" />
            </td>        	
         </tr>
         <tr>
             <td class="odd">产品Code</td>
             <td><input type="text" disabled="disabled" dctype="产品Code" name="testingProcessInfo.productCode" value="{{:testingProcessInfo.productCode}}"  dc="" /></td>
             <td class="odd">产品名称</td>
             <td><input type="text" disabled="disabled" dctype="产品名称" name="testingProcessInfo.productName" value="{{:testingProcessInfo.productName}}"  dc="" /></td>
        </tr>
        <tr>
             <td class="odd">客户</td>
             <td><input type="text" dctype="客户" name="testingProcessInfo.customName" value="{{:testingProcessInfo.customName}}" dc="" maxlength="11"/></td>
             <td class="odd">设备</td>
             <td><input type="text" disabled="disabled" dctype="设备" name="testingProcessInfo.equipmentName" value="{{:testingProcessInfo.equipmentName}}" dc="" /></td>
                               
        </tr>
		<tr>
            <td class="odd">排序</td>
            <td><input type="text" placeholder="请输入排序" dctype="排序" name="testingProcessInfo.orderNum" value="{{:testingProcessInfo.orderNum}}" dc="" /></td>
       </tr>
                             
</script>
<body>
	<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" id="formId">
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">SQM管理</a>&nbsp;>&nbsp;检测流程修改</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">检测流程修改</div><!--1019-->
  						
                        <table id="dataListForm" class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
 	
                        </table>
                        <div class="tex_c mt22 mb30"><a href="#" id="submitBtn" class="btn1 f14 fb mr10">提交</a><a href="#" id="closeBtn" class="btn1 f14 fb">返回</a></div>
                        <!-- 右侧 结束-->
    
        		</div>
          </div>
    </div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmmanage/js/testing_process_info_upd.js"></script>
</html>