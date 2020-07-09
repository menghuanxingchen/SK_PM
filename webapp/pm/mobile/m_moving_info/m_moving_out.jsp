<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<title>搬出证申请</title>
<%@ include file="/pm/common/jsp/common_mobile.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/pm/mobile/m_moving_info/js/m_moving_out.js"></script>
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
                <p class="title w100">搬出证编号</p><p><input  type="text" class="w470" id="moutNo" disabled name="movingOutInfo.moutNo"  dc=""/></p>
            </div>
           <div class="lt-dsb cl-bb">
                <p class="title w100">申请人</p><p><input  type="text" class="w470" id="skApplyName" readOnly  name="movingOutInfo.skApplyName" dc=""/></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">部门</p><p><input  type="text" class="w470" id="skApplyDept" readOnly  name="movingOutInfo.skApplyDept" dc=""/></p>
            </div>
            <div class="lt-dsb cl-bb">
               <p class="title w100">第一审批人</p>
               	<p>
               		<select id="applyPerson1" style="width: 4.7rem !important" 　 name="movingInInfo.skContactsNo">
						<option value="">--（可不选）--</option>
						<option value="lg00158" >乔海滨</option>
						<option value="lg00302" >赵正博</option>
						<option value="lg00157" >张志勇</option>
						<option value="lg00258" >李彦晖</option>
				 	</select>
               	</p>
            </div>
            <div class="lt-dsb cl-bb">
               <p class="title w100">第二审批人</p>
               	<p>
               		<select id="applyPerson2" style="width: 4.7rem !important"　 name="movingInInfo.skContactsNo">
						<option value="">--（可不选）--</option>
						<option value="lg00064" >邵勇</option>
						<option value="lg00158" >乔海滨</option>
						<option value="lg00302" >赵正博</option>
						<option value="lg00157" >张志勇</option>
						<option value="lg00258" >李彦晖</option>
				 	</select>
               	</p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">搬出时间</p><p><input class="w470" name="movingOutInfo.moutTime" dcrequired="请选择搬出时间" readonly class="dateinput dateicon" id="moutTime" type="text" placeholder="请选择搬入时间"   dc="true" /></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">搬出人姓名</p><p><input  type="text" class="w470" id="moutName"  placeholder="请输入搬出人姓名" dcrequired="请输入搬出人姓名"  name="movingOutInfo.moutName" dc=""/></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">公司名</p><p><input  type="text" class="w470" id="moutCompany"  placeholder="请输出公司名" dcrequired="请输出公司名" name="movingOutInfo.moutCompany" dc=""/></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">车牌号</p><p><input type="text" class="w470" id="movingCarno"  readOnly placeholder="请输入车牌号" dcrequired="请输入车牌号" name="movingOutInfo.movingCarno" dc=""/></p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">联系方式</p><p><input type="text" class="w470" id="moutPhone"  placeholder="请输入联系方式" dcrequired="请输入联系方式" name="movingOutInfo.moutPhone" dc=""/></p>
            </div>
          
            <div class="lt-dsb cl-bb">
                <p class="title w100">搬出目的</p>
                <p>
	                <input style="width:30px" checked name="moutGoalType" type="radio" value="仓库提货" />仓库提货
	                <input style="width:30px" name="moutGoalType" type="radio" value="空车出厂" />空车出厂
	                <input style="width:30px" name="moutGoalType" type="radio" value="垃圾" />垃圾
                </p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                <p>
                	<input style="width:40px" name="moutGoalType" type="radio" value="自消费出库" />自消费出库
	                <input style="width:40px" name="moutGoalType" type="radio" value="其他" />其他
                </p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                <p>
                	<input class="w470" type="text" placeholder="请输入目的" id="moutGoalContent" name="movingOutInfo.moutGoalContent" dc=""/>
                </p>
            </div>
           
             <div class="lt-dsb cl-bb">
                <p class="title w100">上传图片</p>
                <p>
                	<input type="file" style="width:150px" id="picfileInfo" name="picfileInfo" onchange ="uploadFile()" />
	                <input type="hidden" id="picInfo"  dc="" />
                </p>
            </div>
            <div class="lt-dsb cl-bb">
                <p class="title w100">物品目录</p>
                <p id="spanGoodsType">
                	请参考出库单！
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
				         	<td><input id="wpmc1" style="width:90%" type="text" placeholder="请输入物品名称"  dc=""/></td>
				         	<td><input id="wpsl1" style="width:85%" type="text" placeholder="请输入数量"  dc=""/></td>
				         </tr>
				         <tr>
				         	<td>2</td>
				         	<td><input style="width:90%" type="text" placeholder="请输入物品名称" dc=""/></td>
				         	<td><input style="width:85%"  type="text" placeholder="请输入数量" dc=""/></td>
				         </tr>
				         <tr>
				         	<td>3</td>
				         	<td><input style="width:90%" type="text" placeholder="请输入物品名称" dc=""/></td>
				         	<td><input style="width:85%" type="text" placeholder="请输入数量" dc=""/></td>
				         </tr>
				         <tr>
				         	<td>4</td>
				         	<td><input style="width:90%" type="text" placeholder="请输入物品名称" dc=""/></td>
				         	<td><input style="width:85%" type="text" placeholder="请输入数量" dc=""/></td>
				         </tr>
				         <tr>
				         	<td>5</td>
				         	<td><input style="width:90%" type="text" placeholder="请输入物品名称" dc=""/></td>
				         	<td><input style="width:85%" type="text" placeholder="请输入数量" dc=""/></td>
				         </tr>
				         <tr>
				         	<td>6</td>
				         	<td><input style="width:90%" type="text" placeholder="请输入物品名称" dc=""/></td>
				         	<td><input style="width:85%" type="text" placeholder="请输入数量" dc=""/></td>
				         </tr>
				         <tr>
				         	<td>7</td>
				         	<td><input style="width:90%" type="text" placeholder="请输入物品名称" dc=""/></td>
				         	<td><input style="width:85%" type="text" placeholder="请输入数量" dc=""/></td>
				         </tr>
				         <tr>
				         	<td>8</td>
				         	<td><input style="width:90%" type="text" placeholder="请输入物品名称" dc=""/></td>
				         	<td><input style="width:85%" type="text" placeholder="请输入数量" dc=""/></td>
				         </tr>
				    </tbody>
				</table>
				</div>
		    </section>
    
    <footer id="com_foot">
        <input type="submit" name="submit"  style="width:45%" id="submitBtn" class="submit ml30" value="提交"/>   
        <input type="submit" name="submit"  style="width:45%" id="backBtn" class="submit ml30" value="返回"/>       
    </footer>
</div> 
</div>
</body>
</html>