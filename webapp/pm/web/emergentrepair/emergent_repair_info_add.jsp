<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=0">
    <title>停机维修新增</title>
    <%@ include file="/pm/common/jsp/commonCss.jsp"%>
	<%@ include file="/pm/common/jsp/commonJs.jsp"%>
	<style type="text/css">
		#clearPotA{
			position: relative;
			right: 7%;
		}
		
	</style> 
</head>
<script type="text/x-jsrender" id="jobTemplate">
<select id="job_type" name="emergentRepairInfo.job_type" dcrequired="请选择职能" dc="true" class="mr20">
		{{renderOptionFun selectList "code_value" "code_nm" "" "职能"/}}
 	</select>
</script>
<script type="text/x-jsrender" id="sonPotTemplate">
<select id="job_type" name="emergentRepairInfo.pot_son_part_id" dc="true" class="mr20">
		{{renderOptionFun potSonPartList "code_value" "code_nm" "" "子部件"/}}
 	</select>
</script>
<script type="text/x-jsrender" id="potPartTemplate">
<select id="pot_part" name="emergentRepairInfo.pot_part_id" dc="true" class="mr20">
		{{renderOptionFun data "pot_part_id" "pot_part_nm" "" "部件"/}}
 	</select>
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>

    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">停机维修信息</a>&nbsp;>&nbsp;停机维修新增</div>
            
            	<div class="main" id="aaa">
                
                		<div class="title mb18">停机维修新增</div>  						
            			<table id="addform" class="tt record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                            	<td class="odd w10">工作内容</td>
                                <td colspan="3"><input name="emergentRepairInfo.repair_content" type="text" value=""  dc="true" dcrequired="请输入工作内容" maxlength="50"/></td>
                            	<td class="odd w10">提交人</td>
                                <td colspan="3"><input name="emergentRepairInfo.create_id" disabled="disabled"  type="text"  dc="true"  maxlength="50"/></td>
                            
                            </tr>

                            <tr>
                            	<td class="odd">维修日期</td>
                                <td colspan="3"><input id="repair_date" name="emergentRepairInfo.repair_date" type="text" value=""  dc="true" dcrequired="请选择维修日期" maxlength="50" readonly/></td>
                            	<td class="odd">维修人员</td>
                                <td colspan="3"><input name="emergentRepairInfo.repair_person" type="text" value="王健,周麟,孙文学,曹万引"  dc="true" dcrequired="请输入维修人员，设备关键词" maxlength="50"/></td>
                            </tr>      
                            <tr>
                            	<td class="odd">设备</td>
                            	<td colspan="3"><input type="text" id="selPotNm" value=""  class="mr20" onclick="selectPot()" readonly/>
                            	<a id="clearPotA" style="visibility: hidden;" href="javascript:clearPot()"><img alt="清空" title="清空" src="<%=request.getContextPath()%>/pm/web/basicinfo/machinemanager/img/delete.png"></a>
                                <input type="hidden" id="selPotId" name="emergentRepairInfo.pot_id" value="" class="mr20" dc="true"/></td>
                                <td colspan="2" id="potPartList">
                                	<select id="job_type" name="emergentRepairInfo.pot_son_part_id" dc="true" class="mr20">
										<option>-请选择-</option>
 									</select>
                                </td>
                            	<td colspan="2" id="sonPotList"></td>
                            </tr>                      
<!--                             <tr>
                            	<td class="odd">开始时间</td>
                                <td colspan="3"><input name="emergentRepairInfo.start_time" id="rep_st" dcrequired="请选择开始时间" type="text" value=""  dc="true" maxlength="50"/></td>
                                <td class="odd">结束时间</td>
                                <td colspan="3"><input name="emergentRepairInfo.end_time" id="rep_ed" dcrequired="请选择结束时间" type="text" value=""  dc="true" maxlength="50"/></td>
                            </tr>   
                            <tr>
                            	<td class="odd w10">分钟数</td>
                                <td colspan="3"><input type="text" name="emergentRepairInfo.emergent_hours" disabled="disabled"  dc="true" id="emergent_hours"  />
								</td> 
								                           
							</tr> -->                          
                        </table>
        				
                        <div class="tex_c mt22 mb30"><a href="javascript:saveForm();" class="btn1 f14 fb mr10">提交</a><a href="javascript:pageBack();" id="gobackBt" class="btn1 f14 fb">返回</a></div>
        		</div>
          </div>
    </div>
</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/emergentrepair/js/emergent_repair_info_add.js"></script>
</html>
