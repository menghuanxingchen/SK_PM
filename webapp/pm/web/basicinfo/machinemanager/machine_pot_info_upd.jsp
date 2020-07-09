<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=0">
    <title>设备修改</title>
    <%@ include file="/pm/common/jsp/commonCss.jsp"%>
	<%@ include file="/pm/common/jsp/commonJs.jsp"%> 
</head>
<!-- 设备信息 -->
<script type="text/x-jsrender" id="formTemplate">
<tr>
     <td class="odd w10">设备名称</td>
     <td colspan="3"><input name="machinePotInfo.pot_nm" type="text" value="{{:pot_nm}}"  dc="true" dcrequired="请输入设备名称" maxlength="50"/></td>
     <td class="odd w10">设备供应商</td>
     <td colspan="3"><input name="machinePotInfo.supplier" type="text" value="{{:supplier}}"  dc="true" dcrequired="请输入设备供应商" maxlength="50"/></td>
</tr>
<tr>
     <td class="odd">生产日期</td>
     <td colspan="3"><input id="production_date" class="laydate-icon" name="machinePotInfo.production_date" type="text" value="{{:production_date}}"  dc="true" dcrequired="请选择设备的生产日期" maxlength="50" readonly/></td>
     <td class="odd">使用年限</td>
     <td colspan="3"><input name="machinePotInfo.use_year" type="text" value="{{:use_year}}"  dc="true" dcrequired="请输入设备的使用年限" maxlength="50"/></td>
</tr>                            
<tr>
     <td class="odd">备注</td>
     <td colspan="3"><input name="machinePotInfo.remark" type="text" value="{{:remark}}"  dc="true" maxlength="50"/></td>
	 <td class="odd"></td>
     <td colspan="3"></td>
</tr>
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">停机维修信息</a>&nbsp;>&nbsp;设备信息</a>&nbsp;>&nbsp;设备信息修改</div>
            
            	<div class="main" id="aaa">
                
                		<div class="title mb18">设备信息修改</div>  						
            			<table id="checkupdateform" class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                        	    
                        </table>
        				
                        <div class="tex_c mt22 mb30"><a href="#" id="saveBtn" class="btn1 f14 fb mr10">提交</a><a href="#" id="gobackBt" class="btn1 f14 fb">返回</a></div>
        		</div>
          </div>
    </div>
</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/basicinfo/machinemanager/js/machine_pot_info_upd.js"></script>
</html>
