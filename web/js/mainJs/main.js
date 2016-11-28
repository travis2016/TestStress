$(function() {
	//导航栏生效
    $('#side-menu').metisMenu();
});

//iframe自适应开始
function reinitIframe(){  
	var iframe = document.getElementById("pageIframe");  
	try{  
	    var bHeight = iframe.contentWindow.document.body.scrollHeight;  
	    var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
	    var height = Math.max(bHeight, dHeight);  
	    iframe.height = height;  
	}catch (ex){}  
}  
	  
	var timer1 = window.setInterval("reinitIframe()", 500); //定时开始  
	  
	function reinitIframeEND(){  
	var iframe = document.getElementById("pageIframe");  
	try{  
	    var bHeight = iframe.contentWindow.document.body.scrollHeight;  
	    var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
	    var height = Math.max(bHeight, dHeight);  
	    iframe.height = height;  
	}catch (ex){}  
	// 停止定时  
	window.clearInterval(timer1);  
}  
//iframe自适应结束
