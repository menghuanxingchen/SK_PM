<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>维修日志信息修改</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/maintainlog/js/maintainlog_update.js"></script>
<script type="text/x-jsrender" id="formTemplate">
	<tr>
    	<td class="odd w10">维修类型</td>
        <td>
		<select id="mtype"  name="maintainlogInfo.maintain_type" dc="true" class="mr20">
			{{renderOptionFun mltypeList "code_value" "code_nm" "" "noflag"/}}
 		</select>
        </td>
        <td class="odd w10">维修时间</td>
        <td class="w30">
        <input id="datef"  class="laydate-icon" name="maintainlogInfo.maintain_time" type="text" value="{{:maintainlogInfo.maintain_time}}"  dc="true" dcrequired="请选择维修时间" readonly/>
		</td>
		<td class="odd w10">职能</td>
        <td id="subdept">
            <input id="subdeptName" value="{{:maintainlogInfo.subdept_type_nm}}" type="text" readonly/>
        </td>
    </tr>
    <tr>
    	<td class="odd">工作内容及故障描述和解决措施</td>
        <td colspan="5"><input name="maintainlogInfo.log_contents" type="text" value="{{:maintainlogInfo.log_contents}}"  dc="true" dcrequired="请输入检查项目名称"/></td>
    </tr> 
    <tr>
    	<td class="odd w10">担当或维修人员人工</td>
        <td colspan="3">
        <input name="maintainlogInfo.human_amount" type="text" value="{{:maintainlogInfo.human_amount}}"  dc="true" dcrequired="请输入正强盛人工"/>
        </td>
        <td class="odd w10">维修人员工时或停机分钟数</td>
        <td colspan="2"> 
        <input name="maintainlogInfo.time_amount" type="text" value="{{:maintainlogInfo.time_amount}}"  dc="true" dcrequired="请输入三方人员工时" dctype="数字"/>
		</td>
    </tr> 
    <tr>
    	<td class="odd">备注(code+停机起始时间)</td>
        <td colspan="3"><input name="maintainlogInfo.log_remark" type="text" value="{{:maintainlogInfo.log_remark}}"  dc="true" /></td>
        <td class="odd w10">灌装机</td>
        <td>
	<!-- 电仪灌装机 -->
	<select  name="maintainlogInfo.machineid" id="machineid" dc="true" class="mr20">
		{{renderOptionFun machineList "machine_id" "machine_nm" "" "请选择"/}}
 	</select>
       </td>
</script>
</td>
    </tr> 
</script>
</head>
<body>

	<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">维修信息</a>&nbsp;>&nbsp;维修日志信息修改</div>
            
            	<div class="main" id="aaa">
                
                		<div class="title mb18">维修日志信息修改</div>  						
            			<table id="updateform" class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                        	    
                        </table>
        				
                        <div class="tex_c mt22 mb30"><a href="#" id="saveBtn" class="btn1 f14 fb mr10">提交</a><a href="#" id="gobackBt" class="btn1 f14 fb">返回</a></div>
        		</div>
          </div>
    </div>

</body>
</html>
