<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<link href="<%=request.getContextPath()%>/pm/common/css/mobiscroll.custom-3.0.0-beta2.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/pm/common/css/HRMobile.css" rel="stylesheet" type="text/css" />
<title>维修单信息——手机端</title>
<%@ include file="/pm/common/jsp/common_mobile.jsp"%> 
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/mobile/m_repair/js/m_repair_add.js"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/mobile/HRMobile.js"></script> 
<script src="<%=request.getContextPath()%>/pm/common/js/mobile/mobiscroll.custom-3.0.0-beta2.min.js" type="text/javascript"></script>
<script type="text/x-jsrender" id="dataListTemplate">
  <li data-id="listviewUpdate-{{:#index+1}}" id="{{:repair_cost_id}}" onclick="goToUpdateDataFun('{{:repair_cost_id}}');">{{:cost_detail}}</li>
</script>
<!-- 设备类别 -->
<script type="text/x-jsrender" id="machinespeciesListTemplate">
	<select  name="machineInfo.machine_species_id" id="species_id" dc="true" class="mr20">
		{{renderOptionFun machinespeciesList "code_value" "code_nm" "" "noflag"/}}
 	</select>
</script>
<!-- 设备类型 -->
<script type="text/x-jsrender" id="machinetypeListTemplate">
	<select name="machineInfo.machine_type_id" id="type_id" dc="true" class="mr20">
		{{renderOptionFun infoList "code_value" "code_nm" "" "noflag"/}}
 	</select>
</script>
<!-- 区域 -->
<script type="text/x-jsrender" id="areaListTemplate">
	<select name="machineInfo.area_id" dc="true" class="mr20">
		{{renderOptionFun areaList "code_value" "code_nm2" "" "区域"/}}
 	</select>
</script>
<!-- 设备列表 -->
<script type="text/x-jsrender" id="machineListTemplate">
	{{for machineInfoList}}
		<tr>
			<td><input type="checkbox" class="ml30" name="checkGroupId" datid="{{:machine_id}}" datnam="{{:machine_nm}}" spval="{{:machine_species_id}}" typeval="{{:machine_type_id}}" /></td>
			<td>{{:machine_type_name}}</td>
			<td>{{:machine_nm}}</td>
		</tr>
	{{/for}}
</script>
<!-- 材料费临时列表 -->
<script type="text/x-jsrender" id="costListTemplate">
	{{for repairCostDetailTempList}}
		<tr>
			<td>{{:cost_type_nm}}</td>
			<td>{{:cost_detail}}</td>
			<td>{{:amount}}</td>
			<td>{{:unit_price}}</td>
			<td>{{:total_price}}</td>
		</tr>
	{{/for}}
</script>
<script type="text/x-jsrender" id="repairDetailGroupDownListTemplate">
	<select data-name="select1">
		{{renderOptionFun maintenanceItemInfoList "maintenance_id" "maintenance_nm" "" ""/}}
 	</select>
</script>
</head>
<body>
<div id="com_body">
    <div id="_centent">
        <header>
            <div class="rt-bk">
                <i class="bk" onclick="goBackToList();"></i>
            </div>
            <div class="top-name"><p>维修结果录入</p></div>
        </header>
     <!--pop flag -->
    <input id="ma_sp_id" type="hidden" value=""/>
    <input id="ma_type_id" type="hidden" value=""/>
    <section class="mt-1"> 
        <div class="ps-lt">
            <div class="lt-dsb">
                <p class="title">维修内容</p><p><input type="text" class=" w470" dc="" id="repair_detail"  name="repairResultInfo.repair_detail" readonly/></p>
            </div>
            <div class="lt-dsb">
                <p class="title">要求完成日期</p><p><input type="text" class="w470" dc="" id="require_date"  name="repairResultInfo.require_date" readonly/></p>
            </div>
             <div class="lt-dsb">
                <p class="title">费用类型</p><p><input type="text" class=" w470" dc=""  id="repair_type" readonly/></p>
            </div>
            <div class="lt-dsb">
                <p class="title">工时</p><p><input type="text" class=" w470" dc=""  id="task_time"　name="repairResultInfo.task_time" maxlength="10"/></p>
            </div>
            <div class="lt-dsb">
                <p class="title">人工费</p><p><input type="text" class="w470" dc="" id="unit_price"　name="repairResultInfo.unit_price" maxlength="10"/></p>
            </div>
            <div class="lt-dsb">
                <p class="title">一次性费用</p><p><input type="text" class="w470" dc="" id="one_charge" name="repairResultInfo.one_charge" maxlength="10"/></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title">备注</p><p><input type="text" class="w470" dc="" name="repairResultInfo.remark" maxlength="50"/></p>
            </div>
        </div>
    </section>
    
    <section class="mt-2"> 
        <div class="ps-lt">
            <!-- Demo configurator markup START, ignore this in your implementation -->
            <div id="main" class="demo-main" style="display:none;">
                <div>
                    <select name="demo" id="demo" class="settings">
                         
                        <optgroup label="Listview">
                            <option value="listviewUpdate" >Create, remove, update</option>
                        </optgroup>
                    </select>
                </div>
            </div>
            <!-- Demo configurator markup END -->
          
            <!-- 报价内容列表 START -->
                <ul id="listviewUpdate-demo" class="md-wo-list" style="display:none" >
                </ul>
            <!-- 报价内容列表 END -->
			
			<div class="lt-dsb">
				<a href="javascript:goToSaveDataFun();" class="cancel_ad ml30 fl">添加材料费</a>
        		<a href="javascript:queryCostDetailListForMob();" class="cancel_ad mr30 fr">查看</a>
        	</div>
        </div>		
    </section>
    
    <section class="mt-2"> 
        <div class="ps-lt">
	
		 	<div class="llt-dsb bor_b h_auto" id="repairResultMachineDiv">
            	<table id="repairResultMachine" colspan="0" border="0">
                </table>
            </div>
			
			<div class="lt-dsb">
				<a class="cancel_ad ml30 fl" href="javascript:chooseMachine();">选择设备</a>
        		<a class="cancel_ad mr30 fr" href="javascript:deleteMachine();">删除</a>
        	</div>

        </div>    
    </section>
    <div class="jg"></div>
     <footer id="com_foot">
     	<input type="submit" name="cancel" id="repairAddcancelBtn" class="cancel ml30" value="取消"/>
        <input type="submit" name="submit" id="submitBtn" class="submit ml30" value="提交"/>       
    </footer>
    </div>
    
	<div id="machine_choose" style="display:none">
	 <header>
            <div class="rt-bk">
                <i class="bk" onclick="goBackToAddResult();"></i>
            </div>
            <div class="top-name"><p>设备选择</p></div>
        </header>
	 <section class="mt-1"> 
        <div class="ps-lt" id="search_area">
         <div class="lt-dsb">
                <p class="title w100">设备类别</p><p id="machinespecies"></p>
            </div>
          <div class="lt-dsb">
                <p class="title w100">设备类型</p><p id="machinetype"></p>
            </div>
               <div class="lt-dsb">
                <p class="title w100">区域</p><p id="areaid"></p>
            </div>
            <div class="lt-dsb cl-bb">
            	<p class="title">设备名</p><p><input class="list_input w470" id="machine_nm" type="text" name="machineInfo.machine_nm" dc=""/>&nbsp;</p>
            </div>
        </div>
    </section>
    <section class="mt-2"> 
        <div class="ps-lt">
			<table class="ofer_detail_tt" width="100%" border="0" cellpadding="0" cellspacing="0">
				<colgroup>
			    	<col width="15%" />
			        <col width="30%" />
			        <col width="" />
			    </colgroup>
				<thead>
					<tr>
						<th><input type="checkbox" class="ml30" id="checkGroupId"/></th>
						<th>设备类型</th>
						<th>设备名称</th>
					</tr>
				</thead>
				<tbody id="machineList">
				</tbody>
			</table>
        </div>    
    </section>
    <div class="jg"></div>
	<footer id="com_foot2">
    	<input type="submit" name="cancel" id="cancelBtn" class="cancel ml30" value="取消"/>
        <input type="submit" name="submit" id="machineSubmitBtn" class="submit ml30" value="提交"/>       
    </footer>
	</div>
	
	<div id="cost_list" style="display:none">
	 <header>
            <div class="rt-bk">
                <i class="bk" onclick="goBackToAddResult();"></i>
            </div>
            <div class="top-name"><p>材料费列表</p></div>
        </header>
	<section class="mt-2"> 
        <div class="ps-lt">
            <table class="ofer_detail_tt" width="100%" border="0" cellpadding="0" cellspacing="0">
				<colgroup>
					<col width="20%" />
					<col width="21%" />
					<col width="17%" />
					<col width="17%" />
					<col width="25%" />
				</colgroup>
				<thead>
					<tr>
						<th>分类</th>
						<th>名称</th>
						<th>数量</th>
						<th>单价</th>
						<th>合计</th>
					</tr>
				</thead>
				<tbody id="cost_list_tbody">
				</tbody>
			</table>
        </div>
    </section>
    <div class="jg"></div>
    <footer id="com_foot2">
    	<input type="submit" name="cancel" id="costCancelBtn" class="cancel ml30 w660" value="取消"/>
    </footer>
	</div>
	</div>
</body>
</html>