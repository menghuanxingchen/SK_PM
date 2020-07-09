<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>搬入证申请</title>
<%@ include file="/pm/common/jsp/common_mobile.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/mobile/m_moving_info/js/m_moving_in.js"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/jedate/jquery.jedate.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/car/layer.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/pm/common/js/car/index.js" type="text/javascript"></script>

<link href="<%=request.getContextPath()%>/pm/common/js/jedate/skin/jedate.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
	
*{	margin: 0; padding: 0;list-style:none;border:0; box-sizing:border-box;}

.clearfix:after { content: "."; display: block; font-size: 0; height: 0; clear: both; visibility: hidden; }
.clearfix { display: inline-table;}
*html .clearfix { height: 1%; }
.clearfix { display: block; }
*+html .clearfix { min-height: 1%; }
.che_tit{ text-align:center; padding:20px;}
.ul_pro{ background-color:#CED3D9; text-align:center; padding:4px 2px; font-size:14px;}
.ul_pro li{ float:left; width:11.11%; padding:2px;box-sizing: border-box;}
.ul_pro .li_close{  float:right; width:22.22%;}
.ul_pro .li_close span{ background-color:#ACB3BB;}
.ul_pro .li_clean{  float:right; width:22.22%;}
.ul_pro li span{ display:block; background-color:#fff; border-radius:4px; box-shadow: 2px 2px 2px #888888; /* max-width:48px; margin:0 auto; */ line-height:32px; padding-top:2px;  }
.ul_pro li span:active{ background-color:#4DA9F2; color:#fff;}

.ul_input{ padding:0px 5px;width: 4.7rem !important; margin-left:100px;font-size:0.35rem }
.ul_input li{ float:left; width:14%; padding:2px;text-align:center; }
.ul_input li span{ display:block; background-color:#fff; border:1px solid #ccc; border-radius:4px; width:30px; margin:0 auto; height:30px; line-height:40px; }

.ul_keybord{ background-color:#CED3D9; text-align:center; padding:4px 2px; font-size:14px;}
.ul_keybord li{ float:left; width:10%; padding:2px;box-sizing: border-box;}
.ul_keybord .ikey20{ margin-left:5%;}
.ul_keybord .li_w{ width:11.11%; }

.ul_keybord .li_close{  float:right; width:22.22%;}
.ul_keybord .li_close span{ background-color:#ACB3BB;}
.ul_keybord .li_clean{  float:right; width:22.22%;}
.ul_keybord li span{ display:block; background-color:#fff; border-radius:4px; box-shadow: 2px 2px 2px #888888; /* max-width:48px; margin:0 auto; */ line-height:32px; padding-top:2px;  }
.ul_keybord li span:active{ background-color:#4DA9F2; color:#fff;}
</style>
<script type="text/x-jsrender" id="allUserGroupSubDownListTemplate2">
	<select id="deal_user_id2" style="width: 4.7rem !important" dcrequired="请选择SK联系人"　 name="movingInInfo.skContactsNo" dc="true">
		{{renderOptionFun allUser "user_num" "user_nm" "" "SK联系人"/}}
 	</select>
</script>
</head>
<body>
<div id="com_body">
    <div id="_centent">
        <header>
            <div class="rt-bk">
                <i class="bk" onclick="goBack();"></i>
            </div>
            <div class="top-name"><p></p></div>
        </header>
    
    <section class="mt-1"> 
        <div class="ps-lt">
            <div class="lt-dsb">
                <p class="title w100">搬入证编号</p><p><input  type="text" class="w470" id="minNo" disabled name="movingInInfo.minNo"  dc=""/></p>
            </div>
           
            <div class="lt-dsb cl-bb">
                <p class="title w100">搬入人姓名</p><p><input  type="text" class="w470" id="minName" placeholder="请输入搬入人姓名" dcrequired="请输入搬入人姓名"  name="movingInInfo.minName" dc=""/></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">公司名</p><p><input  type="text" class="w470" id="minCompany" placeholder="请输入公司名" dcrequired="请输入公司名" name="movingInInfo.minCompany" dc=""/></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">车牌号</p>
            
            	<p><input type="text" class="w470"  id="movingCarno"  readOnly placeholder="请输入车牌号" dcrequired="请输入车牌号" name="movingInInfo.movingCarno" dc=""/></p>
				
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">联系方式</p><p><input type="text" class="w470" id="minPhone" placeholder="请输入联系方式" dcrequired="请输入联系方式" name="movingInInfo.minPhone" dc=""/></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">SK联系人</p><p><span id="userChoose" ></span></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">搬入时间</p><p><input class="w470" name="movingInInfo.minTime" dcrequired="请选择搬入时间" readonly class="dateinput dateicon" id="minTime" type="text" placeholder="请选择搬入时间"   dc="true" /></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">搬入目的</p>
                <p>
	                <input style="width:25px" checked name="minGoalType" type="radio" value="送货" />送货
	                <input style="width:25px" name="minGoalType" type="radio" value="提货" />提货
	                <input style="width:25px" name="minGoalType" type="radio" value="垃圾" />垃圾
	                <input style="width:25px" name="minGoalType" type="radio" value="其他" />其他
                </p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                <p>
                	<input class="w470" type="text" placeholder="请输入目的" id="minGoalContent" name="movingInInfo.minGoalContent" dc=""/>
                </p>
            </div>
          	<!--<div class="lt-dsb cl-bb">
                <p class="title w100">是否搬出</p>
                <p>
                	<input style="width:50px" checked name="moutYN" type="radio" value="Y" />是
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	<input style="width:50px" name="moutYN"  type="radio" value="N" />否 
                </p>
            </div> -->
             <div class="lt-dsb cl-bb">
                <p class="title w100">上传图片</p>
                <p>
                	<input type="file" style="width:150px" id="picfileInfo" name="picfileInfo" onchange ="uploadFile()" />
	                <input type="hidden" id="picInfo"  dc="" />
                </p>
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
				    <tbody>
				         <tr>
				         	<td>1</td>
				         	<td><input style="width:90%" type="text" placeholder="请输入物品名称" dcrequired="请输入物品名称" dc=""/></td>
				         	<td><input style="width:85%" type="text" placeholder="请输入数量" dcrequired="请输入物品数量" dc=""/></td>
				         </tr>
				         <tr>
				         	<td>2</td>
				         	<td><input style="width:90%" type="text" placeholder="请输入物品名称" dc=""/></td>
				         	<td><input style="width:85%" type="text" placeholder="请输入数量" dc=""/></td>
				         </tr>
				         <tr>
				         	<td>3</td>
				         	<td><input style="width:90%" type="text" placeholder="请输入物品名称" dc=""/></td>
				         	<td><input style="width:85%" type="text" placeholder="请输入数量" dc=""/></td>
				         </tr>
				         
				    </tbody>
				</table>
				</div>
		    </section>
   
    <footer id="com_foot">
    	
        <input type="submit" name="submit"  style="width:90%" id="submitBtn" class="submit ml30" value="提交"/>       
    </footer>

</div> 
</div>
</body>
</html>