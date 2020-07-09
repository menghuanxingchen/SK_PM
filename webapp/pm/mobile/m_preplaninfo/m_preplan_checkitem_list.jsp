<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>保养计划-检查项目列表——手机端</title>
<%@ include file="/pm/common/jsp/common_mobile.jsp"%> 
<script type="text/x-jsrender" id="dataListTemplate">
<section class="mt-2"> 
        <div class="ps-lt">
            <div class="lt-dsb lt-dsb-ha">
                <p class="fb"><i class="bor_l"></i>{{:check_project_nm}}</p>
				<input type="hidden" name="checkitemList[{{:#index}}].id" value="{{:id}}" dc=""/>
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
<script type="text/javascript">
	function loadURL(url) {
	                    var iFrame;
	                    iFrame = document.createElement("iframe");
	                    iFrame.setAttribute("src", url);
	                    iFrame.setAttribute("style", "display:none;");
	                    iFrame.setAttribute("height", "0px");
	                    iFrame.setAttribute("width", "0px");
	                    iFrame.setAttribute("frameborder", "0");
	                    document.body.appendChild(iFrame);
	                    // 发起请求后这个iFrame就没用了，所以把它从dom上移除掉
	                    iFrame.parentNode.removeChild(iFrame);
	                    iFrame = null;
	                }

	function goBack() {
	                loadURL("haleyAction://back");
	            }


</script>
</head>
<body>
<div id="com_body">
    <div id="_centent">
        <header>
            <div class="rt-bk">
                <i class="bk" onclick="goToMachDataFun();"></i>
            </div>
            <div class="top-name"><p>检查项目录入</p></div>
        </header>
    
    <section class="mt-1" id="dataListForm"> 
        <div class="ps-lt">
            <div class="lt-dsb">
                <p class="title">计划名称</p><p id="planNm"></p>
                <input type="hidden" name="prePlanInfo.pre_plan_id" id="pre_plan_id" value="" dc=""/>
            </div>
            <div class="lt-dsb">
                <p class="title">设备名称</p><p id="machNm"></p>
                <input type="hidden" name="prePlanMachineInfo.plan_machine_id" id="plan_machine_id" value="" dc=""/>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title">巡检日期</p><p id="date"></p>
            </div>
        </div>
    </section>

    <div class="jg"></div>
    </div>
    
    <footer id="com_foot">
    	<input type="submit" name="cancel" class="cancel ml30" value="取消" id="closeBtn"/>
        <input type="submit" name="submit" class="submit ml30" value="提交" id="submitBtn"/>       
    </footer>
</div> 
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/mobile/m_preplaninfo/js/m_preplan_checkitem_list.js"></script>
</html>