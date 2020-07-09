<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>抱怨及内部反馈新增</title>
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
            	<div class="crumbs" id="bre_left"><img src="<%=request.getContextPath()%>/pm/common/images/ico/position.png" class="mr10" /><a href="">SQM查询</a>&nbsp;>&nbsp;抱怨及内部新增</div>
            
            	<!--右侧 开始--> 
            	<div class="main" id="aaa">
                
                		<div class="title mb18">抱怨及内部反馈详细</div>
  						
                        <table class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0">
                        	<tr>
                                   <td class="odd">日期</td>
                                <td>
                                <input type="hidden"  name="sqmComplaintFeedback.id" dc="" dcrequired="主键" readonly="readonly" >
                                <input type="text" id="cf_date" placeholder="请输入日期" dcrequired="日期"  name="sqmComplaintFeedback.cf_date" value="" readonly="readonly"  dc="true" >
                                </td>   	
                            	<td class="odd w10">来源</td>
                                <td><select name="sqmComplaintFeedback.cf_source" placeholder="请输入来源" dcrequired="来源"  dc="true">
								      <option value="">-来源-</option>
								      <option value="1">内部</option>
								      <option value="2">外部</option>
						        </select></td>
                            </tr>
                             <tr>
						        <td class="odd w10">类别</td>
                                <td><select name="sqmComplaintFeedback.cf_type" placeholder="请输入类别" dcrequired="类别" dc="true">
								      <option value="">-类别-</option>
								      <option value="1">投诉</option>
								      <option value="2">反馈</option>
						        </select></td>
                                <td class="odd">反馈地点</td>
                                <td><input name="sqmComplaintFeedback.cf_address" type="text"  dc="true"  placeholder="请输入反馈地点" dcrequired="反馈地点" maxlength="50"/></td>
                            </tr>
                             <tr>
                                <td class="odd">产品类型</td>
                                <td><input name="sqmComplaintFeedback.product_type" type="text"  dc="true"  placeholder="请输入产品类型" dcrequired="产品类型" maxlength="50"/></td>
                                <td class="odd">级别</td>
                                <td><input name="sqmComplaintFeedback.product_rank" type="text"  dc="true"  placeholder="请输入级别" dcrequired="级别" maxlength="50"/></td>
                                
                            </tr>
                            <tr>
                                <td class="odd">包装</td>
                                <td><input name="sqmComplaintFeedback.product_pack" type="text"  dc="true"  placeholder="请输入包装" dcrequired="包装" maxlength="50"/></td>
                                <td class="odd">数量（桶）</td>
                                <td><input name="sqmComplaintFeedback.number_barrel" type="text"  dc="true"  placeholder="请输入数量（桶）" dcrequired="数量（桶）" maxlength="50"/></td>
                            </tr>
                            <tr>
                                <td class="odd">数量（升）</td>
                                <td><input name="sqmComplaintFeedback.number_litres" type="text"  dc="true"  placeholder="请输入数量（升）" dcrequired="数量（升）" maxlength="50"/></td>
                                <td class="odd">批号</td>
                                <td><input name="sqmComplaintFeedback.lotnumber" type="text"  dc="true"  placeholder="请输入批号" dcrequired="批号" maxlength="50"/></td>
                            </tr>
                            <tr>
                                <td class="odd">抱怨类型</td>
                                <td><input name="sqmComplaintFeedback.feedback_type" type="text"  dc="true"  placeholder="请输入抱怨类型" dcrequired="抱怨类型" maxlength="50"/></td>
                                <td class="odd">是否关闭</td>
                                <td><select name="sqmComplaintFeedback.close_yn" placeholder="请选择是否关闭" dcrequired="是否关闭" dc="true">
								      <option value="">-是否关闭-</option>
								      <option value="y">关闭</option>
								      <option value="n">未关闭</option>
						        </select></td> 
                            </tr>
                            <tr>
                                <td class="odd">内容概述（客户问题反馈）</td>
                                <td colspan="6"><textarea name="sqmComplaintFeedback.feedback_content"  dc="true"  placeholder="内容概述（客户问题反馈）" maxlength="50"></textarea></td>
                            </tr> 
                            <tr>
                                <td class="odd">处理内容、结果</td>
                                <td colspan="6"><textarea name="sqmComplaintFeedback.processing_result"  dc="true"  placeholder="处理内容、结果"></textarea></td>
                            </tr>
                            <tr>
                                <td class="odd">增加措施</td>
                                <td colspan="6"><textarea name="sqmComplaintFeedback.add_measures"  dc="true"  placeholder="增加措施" maxlength="50"></textarea></td>
                            </tr>                         
                        </table>
        				
                        <div class="tex_c mt22 mb30"><a href="#" id="submitBtn" class="btn1 f14 fb mr10">提交</a><a href="#" id="closeBtn" class="btn1 f14 fb">返回</a></div>
                        <!-- 右侧 结束-->
    
        		</div>
          </div>
    </div>
</body>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/web/sqmcomplaintfeedback/js/sqm_complaint_feedback_upd.js"></script>
</html>