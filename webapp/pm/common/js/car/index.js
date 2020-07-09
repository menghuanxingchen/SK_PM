var index
	
var provinces = new Array("京","沪","浙","苏","粤","鲁","晋","冀",
            "豫","川","渝","辽","吉","黑","皖","鄂",
            "津","贵","云","桂","琼","青","新","藏",
            "蒙","宁","甘","陕","闽","赣","湘");

var keyNums = new Array("0","1","2","3","4","5","6","7","8","9",
            "Q","W","E","R","T","Y","U","I","O","P",
            "A","S","D","F","G","H","J","K","L",
            "Del","Z","X","C","V","B","N","M","OK");
		
	function showProvince(){
			$("#pro").html("");
			var ss="";
			for(var i=0;i<provinces.length;i++){
				ss=ss+addKeyProvince(i)
			} 
			$("#pro").html("<ul class='clearfix ul_pro'>"+ss+"<li class='li_close' onclick='closePro();'><span name='spanKey'>关闭</span></li><li class='li_clean' onclick='cleanPro();'><span name='spanKey'>清空</span></li></ul>");
	} 
	function showKeybord(){
			$("#pro").html("");
			var sss="";
			for(var i=0;i<keyNums.length;i++){
				sss=sss+'<li class="ikey ikey'+i+' '+(i>9?"li_zm":"li_num")+' '+(i>28?"li_w":"")+'" ><span name="spanKey" onclick="choosekey(this,'+i+');">'+keyNums[i]+'</span></li>'
			} 
			$("#pro").html("<ul class='clearfix ul_keybord'>"+sss+"</ul>");
	}
    function addKeyProvince(provinceIds){
        var addHtml = '<li>';
            addHtml += '<span name="spanKey" onclick="chooseProvince(this);">'+provinces[provinceIds]+'</span>';
            addHtml += '</li>';
            return addHtml;
    }

    function chooseProvince(obj){
       $("#movingCarno").val($(obj).text());
	   showKeybord();
	}	
	
	
	function choosekey(obj,jj){	
		var mincarno = $("#movingCarno").val()
		if(jj==29){
			$("#movingCarno").val(mincarno.substring(0,mincarno.length-1))
		}else if(jj==37){
			layer.close(index);
		
		}else{
			
			$("#movingCarno").val(mincarno+$(obj).text());
		
		}
	}
	function closePro(){
       layer.close(index)
	}		
	function cleanPro(){
		$("#movingCarno").val("");
	}	
	function trimStr(str){return str.replace(/(^\s*)|(\s*$)/g,"");}
	
window.onload = function() {
	$("#movingCarno").click(function(){
		layer.close(index)
		index = layer.open({
			type: 1
			,content: '<div id="pro"></div>'
			,anim: 'up'
			,shade :false 
			,style: 'position:fixed; bottom:0; left:0; width: 100%; height: auto; padding:0; border:none;'
		  });
		 showProvince()
	})
	
	$("movingCarno").blur(function(){
		layer.close(index)
	});

	document.onclick = function (e) { 
		e = e || window.event; 
		var o = e.target || e.srcElement;//当前点击对象 
		if ($('#movingCarno')[0]!=o && o.localName != "span" ) 
		{ 
			layer.close(index)
		} 
	}
	
	
}
