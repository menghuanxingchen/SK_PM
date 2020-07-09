<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>搬入搬出列表</title>
<%@ include file="/pm/common/jsp/common_mobile.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/mobile/m_moving_info/js/m_moving_list.js"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/jedate/jquery.jedate.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/pm/common/js/jedate/skin/jedate.css" rel="stylesheet" type="text/css" />


<style type="text/css">
.applyList {
    background: #fff;
}

.applyList .list {
    display: flex;
    display: -webkit-flex;
    padding: 10px 5px 5px;
    margin: 0 15px;
    border-bottom: 1px solid #ddd;
}

.applyList .list:last-child {
    border: 0;
}

/* 无图纯文字模式 */
.list-no-img {
    flex-direction: column;
}
.list-no-img .top .title {
    font-weight: bold;
    height: 30%;
    font-size: 0.28rem;
}
.list-no-img .top .content {
    display: -webkit-box;
    overflow: hidden;
    text-overflow: ellipsis;
    -webkit-line-clamp: 4;
    -webkit-box-orient: vertical;
    line-height: 0.5rem;
    font-size: 0.24rem;
    margin-bottom: 5px;
    color: #666;
    margin: 5px 0;
}

.list-no-img .info {
    font-size: 0.24rem;
    color: #bbb;
    display: flex;
}
.list-no-img .info .info-r-5 {
    margin-left: 30%;
}
.list-no-img .info .list-ellipsis-1 {
    max-width: 40%;
}
.list-no-img .info .info-r-5 .m_btn_apply {
    width: 1.2rem; /* 宽度 */
	height: 0.6rem; /* 高度 */
	border-width: 0px; /* 边框宽度 */
	border-radius: 3px; /* 边框半径 */
	background: #1E90FF; /* 背景颜色 */
	cursor: pointer; /* 鼠标移入按钮范围时出现手势 */
	outline: none; /* 不显示轮廓线 */
	font-family: Microsoft YaHei; /* 设置字体 */
	color: white; /* 字体颜色 */
	font-size: 0.28rem; /* 字体大小 */
}

/* 无图纯文字模式结束 */
.applyList li:nth-child(2n+1) {
        background: #e4f0fb;
    }
</style>
</head>
<body>
<div id="com_body">
	<header>
        <div class="rt-bk">
                <i class="bk" onclick="goBack();"></i>
        </div>
        <div class="top-name"><p></p></div>
    </header>
     <div class="ps-lt" id="search_area">
         <div class="lt-dsb">
                <p class="title">类型</p>
                <p>
                <select id="movingType" name="movingInfo.movingType" class="mr20"  dc="true" >
                    <option value ="">全部</option>
				 	<option value ="搬入证">搬入证</option>
					<option value ="搬出证">搬出证</option>
			    </select>
                </p>
            </div>
            <div class="lt-dsb">
                 <p class="title">编号</p>
                 <p>
                 <input class="list_input w470" type="text" placeholder="请输入编号"  id="movingNo" value="" name="movingInfo.movingNo" dc="" />&nbsp;
                 </p>
            </div>
             <div class="lt-dsb">
                 <p class="title">搬出申请人</p>
                 <p>
                 <input class="list_input w470" type="text" placeholder="搬出申请人"  id="skApplyName" value="" name="movingInfo.skApplyName" dc="" />&nbsp;
                 </p>
            </div>
            <div class="lt-dsb">
                 <p class="title">搬入/搬出人</p>
                 <p>
                 <input class="list_input w470" type="text" placeholder="请输入搬入/搬出人"  id="movingPerson" value="" name="movingInfo.movingPerson" dc="" />&nbsp;
                 </p>
            </div>
            <div class="lt-dsb">
                 <p class="title">公司名</p>
                 <p>
                 <input class="list_input w470" type="text" placeholder="请输入公司名"  id="movingCompany" value="" name="movingInfo.movingCompany" dc="" />&nbsp;
                 </p>
            </div>
            <div class="lt-dsb">
                <p class="title">审批状态</p>
                <p>
                <select id="applyType" name="movingInfo.applyType" class="mr20"  dc="true" >
                    <option value ="">全部</option>
				 	<option value ="1">待审批</option>
					<option value ="6">已完成</option>
			    </select>
                </p>
            </div>
            <div class="lt-dsb">
                 <p class="title">日期</p>
                 <p>
                 <input class="laydate-icon list_input w150" type="text" id="movingStdate" name="movingInfo.movingStdate" dc=""/>&nbsp;~&nbsp;
                 <input class="laydate-icon list_input w150" type="text" id="movingEddate" name="movingInfo.movingEddate" dc=""/>
                 </p>
            </div>
        </div>

	    <ul class="applyList" id="applyulList">
				
		</ul>
</div>
</body>
</html>