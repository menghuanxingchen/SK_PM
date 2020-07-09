<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=0">
    <title>停机维修反馈</title>
    <%@ include file="/pm/common/jsp/commonCss.jsp"%>
	<%@ include file="/pm/common/jsp/commonJs.jsp"%>

	<script src="<%=request.getContextPath()%>/pm/common/js/jedate/jquery.jedate.js" type="text/javascript"></script>
	<link href="<%=request.getContextPath()%>/pm/common/js/jedate/skin/jedate.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		#clearPotA{
			position: relative;
			right: 7%;
		}
		
	</style> 
</head>
<script type="text/x-jsrender" id="addformTemplate">
  <tr>
                            	<td class="odd w10">工作内容</td>
                                <td colspan="3"><input name="emergentRepairInfo.repair_content" disabled="disabled"  type="text" value="{{:repair_content}}"  dc="true" dcrequired="请输入工作内容" maxlength="50"/>
								<input name="emergentRepairInfo.emergent_id" type="hidden" value="{{:emergent_id}}"  dc="true" dcrequired="" maxlength="50"/>
								</td>
                            	<td class="odd w10">提交人</td>
                                <td colspan="3"><input name="emergentRepairInfo.create_id" disabled="disabled"  type="text" value="{{:create_id}}"  dc="true"  maxlength="50"/></td>
                            </tr>

                            <tr>
                            	<td class="odd">维修日期</td>
                                <td colspan="3"><input id="repair_date"  name="emergentRepairInfo.repair_date" class="jeinput" type="text" value="{{:repair_date}}"  dc="true" dcrequired="请选择维修日期" maxlength="50" readonly/></td>
                            	<td class="odd">维修人员</td>
                                <td colspan="3"><input name="emergentRepairInfo.repair_person" disabled="disabled"  type="text" value="{{:repair_person}}"  dc="true" dcrequired="请输入维修人员，设备关键词" maxlength="50"/></td>
                            </tr>      
                            <tr>
                            	<td class="odd">设备</td>
                            	<td colspan="3"><input type="text" disabled="disabled"  id="selPotNm" value="{{:pot_id_nm}}" class="mr20" onclick="selectPot()" readonly/>
								<a id="clearPotA" style="visibility: hidden;" href="javascript:clearPot()"><img alt="清空" title="清空" src="<%=request.getContextPath()%>/pm/web/basicinfo/machinemanager/img/delete.png"></a>
                                <input type="hidden" id="selPotId" name="emergentRepairInfo.pot_id" value="{{:pot_id}}" class="mr20" dc="true"/></td>
                                <td colspan="2" id="potPartList">
                                	<select id="pot_part" disabled="disabled" name="emergentRepairInfo.pot_part_id" dc="true" class="mr20">
										{{renderOptionFun data "pot_part_id" "pot_part_nm" pot_part_id "部件"/}}
 									</select>
                                </td>
                            	<td colspan="2" id="sonPotList">
									<select id="job_type" disabled="disabled" name="emergentRepairInfo.pot_son_part_id" dc="true" class="mr20">
										{{renderOptionFun potSonPartList "code_value" "code_nm" pot_son_part_id "子部件"/}}
 									</select>
								</td>
                            </tr>                      
                            <tr>
                            	<td class="odd">开始时间</td>
                                <td colspan="3"><input name="emergentRepairInfo.start_time" disabled="disabled"  id="rep_st"  type="text" value="{{:start_time}}"  dc="true" maxlength="50"/></td>
                                <td class="odd">结束时间</td>
                                <td colspan="1"><input name="emergentRepairInfo.end_time" disabled="disabled"  id="rep_ed" type="text" value="{{:end_time}}"   dc="true" maxlength="50"/></td>
							<td class="odd">分钟数</td>
                                <td colspan="1"><input type="text" name="emergentRepairInfo.emergent_hours" disabled="disabled"  dc="true" id="emergent_hours" value="{{:emergent_hours}}" />
								</td>                            
							</tr> 
							<tr>
                            	<td class="odd">维修开始时间</td>
                                <td colspan="3"><input name="emergentRepairInfo.repairStart_time" class="dateinput dateicon" id="repairStart" dcrequired="请选择维修开始时间" type="text" placeholder="请选择维修开始时间" onchange="changeDate()" value="{{:repairStart_time}}"  dc="true" /></td>
                                <td class="odd">维修结束时间</td>
                                <td colspan="3"><input name="emergentRepairInfo.repairEnd_time" class="dateinput dateicon" id="repairEnd" dcrequired="请选择维修结束时间" type="text" placeholder="请选择维修开始时间" onchange="changeDate()" value="{{:repairEnd_time}}"  dc="true" /></td>
								
							</tr> 
							<tr> 
								<td class="odd w10">sqm正常参数</td>
                                <td colspan="9"><input type="text" disabled="disabled" name="emergentRepairInfo.sqm_normal_parameter"   dc="true" id="sqm_normal_parameter"  value="{{:sqm_normal_parameter}}"/>
								</td>                            
							</tr>					
							<tr>
                            	<td class="odd w10">维修小时</td>
                                <td colspan="3"><input type="text" name="emergentRepairInfo.repairHours" disabled="disabled"  dc="true" id="repairHours"  value="{{:repairHours}}"/>
								</td> 
								<td class="odd w10">维修内容</td>
                                <td colspan="3"><input type="text" name="emergentRepairInfo.repair_remark"   dc="true" id="repair_remark"  value="{{:repair_remark}}"/>
								</td>                            
							</tr> 
							       
</script>
<script type="text/x-jsrender" id="potPartTemplate">
<select id="pot_part" name="emergentRepairInfo.pot_part_id" dcrequired="请选择部件" dc="true" class="mr20">
		{{renderOptionFun data "pot_part_id" "pot_part_nm" "" "部件"/}}
 	</select>
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>

    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">停机维修信息</a>&nbsp;>&nbsp;停机维修修改</div>
            
            	<div class="main" id="aaa">
                
                		<div class="title mb18">停机维修反馈</div>  						
            			<table id="addform" class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                                               
                        </table>
        				
                        <div class="tex_c mt22 mb30"><a href="javascript:saveForm();" class="btn1 f14 fb mr10">提交</a><a href="javascript:pageBack();" id="gobackBt" class="btn1 f14 fb">返回</a></div>
        		</div>
          </div>
    </div>
</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/emergentrepair/js/emergent_repair_info_repair.js"></script>
</html>
