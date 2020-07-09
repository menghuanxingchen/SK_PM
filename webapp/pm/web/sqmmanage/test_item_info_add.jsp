<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>检测项新增</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%> 
</head>
<script type="text/x-jsrender" id="lineListTemplate">
       <select id="lineList" name="testItemInfo.lineId" class="mr20"  dc="true" >
		{{renderOptionFun lineList "code_value" "code_nm" "" "生产线" /}}
	 </select>
</script>

<body>
	<%@ include file="/pm/common/jsp/top_left_menu.jsp"%>
    <div class="contents">
          <div class="main_wrap" id="formId">
          		<!--breadcrumbs-->
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">SQM管理</a>&nbsp;>&nbsp;检测项新增</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">检测项新增</div>
  						
                        <table class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                        	<tr>
                            	<td class="odd w10">生产线</td>
                                <td id="lineListForm"></td>
                                   	
                            </tr>
                            <tr>
                            	<td class="odd">产品Code</td>
                                <td><input type="text" id="productCode" placeholder="请输入产品Code" dctype="产品Code" name="testItemInfo.productCode"  dc="" /></td>
                                <td class="odd">产品名称</td>
                                <td><input type="text" id="productName" placeholder="请输入产品名称" dctype="产品名称" name="testItemInfo.productName"  dc="" /></td>
                            </tr>
         
                        </table>
        				
                        <div class="tex_c mt22 mb30"><a href="#" id="submitBtn" class="btn1 f14 fb mr10">提交</a><a href="#" id="closeBtn" class="btn1 f14 fb">返回</a></div>
                        <!-- 右侧 结束-->
    
        		</div>
          </div>
    </div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmmanage/js/test_item_info_add.js"></script>
</html>