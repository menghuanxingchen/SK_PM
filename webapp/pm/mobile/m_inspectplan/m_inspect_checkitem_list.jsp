<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>检查项目录入</title>
<%@ include file="/pm/common/jsp/common_mobile.jsp"%> 
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/mobile/m_inspectplan/js/m_inspect_checkitem_list.js"></script>
<script type="text/x-jsrender" id="dataListTemplate">
 <section class="mt-2"> 
        <div class="ps-lt">
            <div class="lt-dsb">
                <p class="fb"><i class="bor_l"></i>
				{{:check_project_nm}}
				<input type="hidden" name="checkitemList[{{:#index}}].id" value="{{:id}}" dc=""/>
				</p>
            </div>
            <div class="llt-dsb cl-bb">
            	<table>
                	<tr>
                    	<td class="w90">结果</td>
                        <td><input type="radio" dc="" name="checkitemList[{{:#index}}].check_detail" class="radio" value="Y" 
							{{if check_detail=="Y"}}checked{{/if}}							
							/>正常&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" dc="" name="checkitemList[{{:#index}}].check_detail" class="radio" value="N"
							{{if check_detail=="N"}}checked{{/if}}		
							/>异常
						</td>
                    </tr>
                    <tr>
                    	<td>记录</td>
                        <td><input name="checkitemList[{{:#index}}].check_detail2" type="text" value="{{:check_detail2}}" dc="" maxlength="500"/></td>
                    </tr>
                </table>
            </div>
        </div>
    </section>
</script>
</head>

<body>
<div id="com_body">
    <div id="_centent">
        <header>
            <div class="rt-bk" id="backDiv">
                <i class="bk"></i>
            </div>
            <div class="top-name"><p>检查项目录入</p></div>
        </header>
    
    <section class="mt-1"> 
        <div class="ps-lt">
            <div class="lt-dsb">
                <p class="title">计划名称</p>
                <p id="plan_name"></p>
                <input type="hidden" name="insPlanInfo.ins_plan_id" id="ins_plan_id" value="" dc=""/>
            </div>
            <div class="lt-dsb">
                <p class="title">设备名称</p>
                <p id="machine_name"></p>
                  <input type="hidden" name="insMachineInfo.plan_machine_id" id="plan_machine_id" value="" dc=""/>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title">巡检日期</p><p id="plan_date"></p>
            </div>
        </div>
    </section>
    
    <div id="dataListForm"></div> 

    <div class="jg"></div>
    </div>
    
    <footer id="com_foot">
    	<input type="submit" name="cancel" class="cancel ml30" value="取消" id="cancelBtn"/>
        <input type="submit" name="submit" class="submit ml30" value="提交" id="saveBtn"/>       
    </footer>


</div> 

</body>

</html>
