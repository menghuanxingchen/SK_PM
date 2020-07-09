<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>厂长意见</title>
<%@ include file="/pm/common/jsp/commonCss.jsp"%>
<%@ include file="/pm/common/jsp/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pm/web/proposal/js/proposal_director_opinion_pop.js"></script>
</head>
<body id="pop">
    <div class="pop_box">
          <div class="popup">
            	<!--右侧 开始--> 
            	<div id="aaa">                
  						<!--查询条件-->
                          <table class="tt  tt2 record_table table_wrap mt18" border="0" cellspacing="0" cellpadding="0" id="improvement_effect_table">
                             <tr>
                                <td class="odd w10">厂长意见</td>
                               <td colspan="3"><textarea rows="3" id="director_opinion" name="proposalOperateInfo.director_opinion" dc="true" dcrequired="请输入意见"></textarea></td>
                            </tr>
                         </table>
                          <div class="tex_c mt22 mb30">
	                        <a href="javascript:updateProposalImprovementEffect('C');" class="btn1 f14 fb mr10">提交</a>
	                        <a href="javascript:closelayer();" class="btn1 f14 fb mr10">返回</a>
                        </div>
                        <!-- 右侧 结束-->
        		</div>
          </div>
    </div>
</body>
</html>