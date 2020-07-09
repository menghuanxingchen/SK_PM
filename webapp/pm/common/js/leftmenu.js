$(function(){
    $(".ce > li > a").click(function(){
	     $(this).addClass("xz").parents().siblings().find("a").removeClass("xz");
		 $(this).parents().siblings().find(".er").hide(300);
		 $(this).siblings(".er").toggle(300);
		 $(this).parents().siblings().find(".er > li > .thr").hide().parents().siblings().find(".thr_nr").hide();
		
	})
	
   $(".er > li > a").click(function(){
        $(this).addClass("sen_x").parents().siblings().find("a").removeClass("sen_x");
        $(this).parents().siblings().find(".thr").hide(300);	
	    $(this).siblings(".thr").toggle(300);	
	})

/*    $(".thr > li > a").click(function(){
	     $(this).addClass("xuan").parents().siblings().find("a").removeClass("xuan");
		 $(this).parents().siblings().find(".thr_nr").hide();	
	     $(this).siblings(".thr_nr").toggle();
	})*/
})

function menu(id){
	var menuid = id;
	id = id.substr(0, 5);
	$("#"+id).addClass("xz").parents().siblings().find("a").removeClass("xz");
	 $("#"+id).parents().siblings().find(".er").hide(300);
	 $("#"+id).siblings(".er").toggle(300);
	 $("#"+id).parents().siblings().find(".er > li > .thr").hide().parents().siblings().find(".thr_nr").hide();
	 er(menuid);
}
	
function er(id){
	$("#"+id).addClass("sen_x").parents().siblings().find("a").removeClass("sen_x");
    $("#"+id).parents().siblings().find(".thr").hide(300);	
    $("#"+id).siblings(".thr").toggle(300);	
}
	
function TestBlack(TagName){
 var obj = document.getElementById(TagName);
 if(obj.style.display==""){
  obj.style.display = "none";
  document.getElementById( "aaa" ).style.margin = "30px 40px 0px 40px"; 
  document.getElementById( "bre_left" ).style.padding = "0px 0px 0px 40px"; 
  document.getElementById( "arrowLeft" ).style.display = "none";
  document.getElementById( "arrowRight" ).style.display = "block";  
 }else{
  obj.style.display = "";
  document.getElementById( "aaa" ).style.margin = "30px 40px 0px 239px"; 
  document.getElementById( "bre_left" ).style.padding = "0px 0px 0px 239px"; 
  document.getElementById( "arrowLeft" ).style.display = "block";
  document.getElementById( "arrowRight" ).style.display = "none";  
 }
}
