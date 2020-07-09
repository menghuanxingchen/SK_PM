<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>故障类型修改</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/systemfunction/js/maintain_item_update.js"></script>
<!-- 设备类型 -->
<script type="text/x-jsrender" id="machinetypeListTemplate">
	<select id="machine_type_id" name="maintainiteminfo.machine_type_id" dc="true" dcrequired="请选择设备类型">
		{{renderOptionFun infoList "code_value" "code_nm" "" "noflag"/}}
 	</select>
</script>
<script type="text/x-jsrender" id="formTemplate">

  <tr>
  	<td class="odd w10">设备类别</td>
      <td>
      	<select name="maintainiteminfo.machine_species_id" dc="true" id="machine_species_id" dcrequired="请选择设备类别">
		{{renderOptionFun machinespeciesList "code_value" "code_nm" "" "noflag"/}}
 		</select>
      </td>
	<td class="odd w10">设备类型</td>
      <td id="machinetype">	
      </td>
  </tr>

  <tr>
  	<td class="odd">故障类型</td>
      <td colspan="3"><input type="text" name="maintainiteminfo.maintenance_nm" value="{{:maintenanceItemInfo.maintenance_nm}}"  dc="true" dcrequired="请输入故障类型" maxlength="50"/></td>
    </tr>



</script>
</head>
<body>

	<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>

    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">故障类型信息</a>&nbsp;>&nbsp;故障类型修改</div>
            
            	<div class="main" id="aaa">
                
                		<div class="title mb18">故障类型修改</div>  						
            			<table id="updateForm" class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                        </table>
        				
                        <div class="tex_c mt22 mb30"><a href="#" id="saveBtn" class="btn1 f14 fb mr10">提交</a><a href="#" id="gobackBt" class="btn1 f14 fb">返回</a></div>
        		</div>
          </div>
    </div>

</body>
</html>
