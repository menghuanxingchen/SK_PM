<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设备修改</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/basicinfo/baseinfo/js/machine_update.js"></script>
<!-- 设备类型 -->
<script type="text/x-jsrender" id="machinetypeListTemplate">
	<select id="machine_type_id" name="machineInfo.machine_type_id" dc="true" dcrequired="请选择设备类型">
		{{renderOptionFun infoList "code_value" "code_nm" "" "noflag"/}}
 	</select>
</script>
<script type="text/x-jsrender" id="formTemplate">
  <tr>
  	<td class="odd w10">设备类别</td>
      <td>
      	<select name="machineInfo.machine_species_id" dc="true" id="machine_species_id" dcrequired="请选择设备类别">
		{{renderOptionFun machinespeciesList "code_value" "code_nm" "" "noflag"/}}
 		</select>
      </td>
	<td class="odd w10">设备类型</td>
      <td id="machinetype">	
      </td>
  </tr>

  <tr>
  	<td class="odd">设备位号</td>
      <td><input type="text" name="machineInfo.machine_nm" value="{{:machineInfo.machine_nm}}"  dc="true" dcrequired="请输入设备位号" maxlength="50"/></td>
	<td class="odd">设备生产日期</td>
        <td><input id="datef" name="machineInfo.production_date" class="laydate-icon" type="text" value="{{:machineInfo.production_date}}" dc="true" dcrequired="请选择生产日期" readonly/></td>
    </tr>

    <tr>
    	<td class="odd">使用年期</td>
        <td><input name="machineInfo.use_year" value="{{:machineInfo.use_year}}" type="text" dc="true" dcrequired="请输入使用年期" maxlength="50"/></td>
		<td class="odd">区域</td>
        <td>
		<select name="machineInfo.area_id" dc="true" id="area_id" dcrequired="请选择区域">
		{{renderOptionFun areaList "code_value" "code_nm2" "" "noflag"/}}
 		</select>
	</td>
    </tr>

    <tr>
    	<!-- <td class="odd">供应商</td>
        <td><input name="machineInfo.supplier" type="text"  value="{{:machineInfo.supplier}}"  dc="true" dcrequired="请输入供应商" maxlength="50"/></td> -->
		<td class="odd">要事记项</td>
        <td><input name="machineInfo.remark" type="text" value="{{:machineInfo.remark}}" dc="true" dcrequired="请输入要事记项" maxlength="255"/></td> 
		<td class="odd">关键备件</td>
        <td><input name="machineInfo.part" type="text" value="{{:machineInfo.part}}" dc="true" dcrequired="请输入关键备件" maxlength="50"/></td>
    </tr>

</script>
</head>
<body>

	<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>

    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">设备信息</a>&nbsp;>&nbsp;设备修改</div>
            
            	<div class="main" id="aaa">
                
                		<div class="title mb18">设备修改</div>  						
            			<table id="updateForm" class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                        </table>
        				
                        <div class="tex_c mt22 mb30"><a href="#" id="saveBtn" class="btn1 f14 fb mr10">提交</a><a href="#" id="gobackBt" class="btn1 f14 fb">返回</a></div>
        		</div>
          </div>
    </div>

</body>
</html>
