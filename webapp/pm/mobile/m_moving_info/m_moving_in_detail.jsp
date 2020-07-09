<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>搬入证信息</title>
<%@ include file="/pm/common/jsp/common_mobile.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/mobile/m_moving_info/js/m_moving_in_detail.js"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/jedate/jquery.jedate.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/pm/common/js/jedate/skin/jedate.css" rel="stylesheet" type="text/css" />

	<style type="text/css">
 		.over {position: fixed; left:0; top:0; width:100%; z-index:100;}
        .tempContainer {position:fixed; width:100%; margin-right:0px; margin-left:0px; text-align:center; z-index:101;}
	</style>

</head>
<body>
<div id="com_body">
    <div id="_centent">
        <header>
            <div class="rt-bk">
                <i class="bk" onclick="backpage();"></i>
            </div>
            <div class="top-name"><p></p></div>
        </header>
    
    <section class="mt-1"> 
        <div class="ps-lt">
            <div class="lt-dsb">
                <p class="title w100">搬入证编号</p>
                <p>
                <input  type="text" class="w470" id="minNo" readonly name="movingInInfo.minNo"  dc=""/>
                <input  type="hidden"  id="deal_user_id"  dc=""/>
                </p>
            </div>
           <div class="lt-dsb cl-bb">
                <p class="title w100">审批状态</p><p><input  type="text" class="w470" id="approval_state_name" readonly   dc=""/></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">现审批人</p><p><input  type="text" class="w470" id="deal_user_name" readonly   dc=""/></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">搬入证姓名</p><p><input  type="text" class="w470" id="minName" readonly  name="movingInInfo.minName" dc=""/></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">公司名</p><p><input  type="text" class="w470" id="minCompany" readonly name="movingInInfo.minCompany" dc=""/></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">车牌号</p><p><input type="text" class="w470" id="minCarno" readonly name="movingInInfo.minCarno" dc=""/></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">联系方式</p><p><input type="text" class="w470" id="minPhone" readonly name="movingInInfo.minPhone" dc=""/></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">SK联系人</p><p><input type="text" class="w470" id="skContactsName" readonly name="movingInInfo.skContactsName" dc=""/></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">搬入时间</p><p><input class="w470" name="movingInInfo.minTime"  readonly  id="minTime" type="text"   dc="true" /></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">搬入目的</p>
                <p>
	                <input type="text" class="w470" id="minGoalType" readonly name="movingInInfo.minGoalType" dc=""/>
                </p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                <p>
                	<input class="w470" type="text" readonly id="minGoalContent" name="movingInInfo.minGoalContent" dc=""/>
                </p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">是否搬出</p>
                <p>
               		 <input type="text" class="w470" id="moutYN" readonly name="movingInInfo.moutYN" dc=""/>
                </p>
            </div>
             <div class="lt-dsbpic cl-bb">
                <p class="title w100">搬入图片</p>
               
                	<div class="logoImg amplifyImg"><!--注意：此处的amlifyImg不可少-->
			          <img src="" style="width:100px;heigth:100px" id="picInfo"/>
			        </div>
                
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">物品目录</p>
                <p>
                	
                </p>
            </div>          
        </div>
    </section>
    
    <section class="mt-2"> 
       <div class="ps-lt" id="dataListForm">
        	<table class="list_table" id="tabgoods">
			<colgroup>
		    	<col width="10%" />
		        <col width="50%" />
				<col width="30%" />
		    </colgroup>
		    <thead>
		        <tr>
		           <th>序号</th>
		           <th>物品名</th>
		           <th>数量</th>
		        </tr>
		    </thead>
		    <tbody id="t_r_content">
		    </tbody>
		</table>
		</div>
    </section>
     
    <footer id="com_foot" >
    	 
    	   
    </footer>
</div> 
</div>
</body>
</html>