<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>计划统计报表</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
</head>
<script type="text/x-jsrender" id="chkTypeListTemplate">
       <select name="reportAllDto.planType" id="planType" class="mr20 w70"  dc="true" >
		{{renderOptionFun chkTypeList "code_value" "code_nm" "" "noflag" /}}
	 </select>
</script>
<script type="text/x-jsrender" id="machTypeListTemplate">
       <select id="machType" name="reportAllDto.machineType" class="mr20 w70"  dc="true" >
		{{renderOptionFun machTypeList "code_value" "code_nm" "" "noflag" /}}
	 </select>
</script>
<script type="text/x-jsrender" id="machSpeciesListTemplate">
       <select name="reportAllDto.machine_type_id" class="mr20"  dc="true" >
		{{renderOptionFun machSpeciesList "code_value" "code_nm" "" "设备类型" /}}
	 </select>
</script>
<body>
<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" >
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">报表信息</a>&nbsp;>&nbsp;计划统计报表</div>
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
  						<!--查询条件-->
                          <table class="w_auto" border="0" cellspacing="0" cellpadding="0" id="search_area">
                                <tr>
                                <td>
                                	<span class="f14" >计划名称</span>&nbsp;&nbsp;
                                    <input class="mr20 w110" type="text" placeholder="请输入计划名称" name="reportAllDto.planNm" dc=""/>
                                </td>
                                <td id="chkTypeForm">
                                </td>
                                <td id="machTypeForm">
                                </td>
                                <!-- <td id="machSpeciesForm">
                                </td> -->
                                <td>
                                	<span class="f14">计划时间</span>&nbsp;&nbsp;
                                    <input class="laydate-icon w110" type="text" name="reportAllDto.start_date" id="start" dc=""/>&nbsp;~&nbsp;<input  class="laydate-icon mr20 w110" name="reportAllDto.end_date" id="end" dc="" type="text"    />
                                </td>
                                 <td>
                                	<span class="f14">PM完成率</span>&nbsp;&nbsp;
                                    <input class="mr20"  type="text" id="pm_rate"  disabled/>
                                </td>
                                <td><a href="#" id="search_btn" class="btn2 mr20"><img src="<%=request.getContextPath()%>/pm/common/images/ico/find.png" /></a></td>
                                <td><a href="#" id="reportExport_btn" class="btn1 f14 fb mr10">导出</a></td>
                            </tr>
                          </table>
                          <div class="t_r table_wrap mt18" id="table">  
                                <div class="t_r_content" id="detailList" style="max-height:375px;"> 

                                </div> 

                       	  </div>
                  
                        <!-- 右侧 结束-->
        		</div>
          </div>
    </div>
</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/report/js/report_all.js"></script>
</html>