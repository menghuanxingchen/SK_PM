<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>生产线新增</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>
<script type="text/x-jsrender" id="lineListTemplate">
       <select id="lineList" name="lineProductInfo.lineId" class="mr20"  dc="true" >
		{{renderOptionFun lineList "code_value" "code_nm" "" "生产线" /}}
	 </select>
</script>

<body>
	<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" id="formId">
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">SQM管理</a>&nbsp;>&nbsp;生产线新增</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">生产线新增</div>
  						
                        <table class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                        	<tr>
                            	<td class="odd w10">生产线</td>
                                <td id="lineListForm"></td>
                                   <td class="odd">检测间隔时间</td>
                                <td>
                                <input type="text" id="intervalDate" placeholder="请输入检测间隔时间" dctype="检测间隔时间" name="lineProductInfo.intervalDate"  dc="" />
                                </td>   	
                            </tr>
                     
                             <tr>
                                <td class="odd">检测持续时间</td>
                                <td>
                                <input type="text" id="continueDate" placeholder="请输入检测持续时间" dctype="检测持续时间" name="lineProductInfo.continueDate"  dc="" />
                                </td>
                                <td class="odd">故障持续时间</td>
                                <td><input type="text" id="breakdownDate" placeholder="请输入故障持续时间" dctype="故障持续时间" name="lineProductInfo.breakdownDate"  dc="" /></td>
                                
                            </tr>
                            
                        </table>
        				
                        <div class="tex_c mt22 mb30"><a href="#" id="submitBtn" class="btn1 f14 fb mr10">提交</a><a href="#" id="closeBtn" class="btn1 f14 fb">返回</a></div>
                        <!-- 右侧 结束-->
    
        		</div>
          </div>
    </div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmmanage/js/productline_add.js"></script>
</html>