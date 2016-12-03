function check ()
    {
        var reg = /^\s*|\s*$/g;
        if (textnickname.value.replace(reg,'') == "")
        {
            alert ("请输入显示名");
            textnickname.focus ();
//            location.replace();
            return false;
        }
        if (textusername.value.replace(reg,'') == "")
        {
            alert ("请输入用户名");
            textusername.focus ();
//            location.replace();
            return false;
        }
        if (textpassword.value.replace(reg,'') == "")
        {
            alert ("请输入密码");
            textpassword.focus ();
//            location.replace();
            return false;
        }
        return true;
    }

//登录页面判断用户
function checkuserlogin(){
	var reg = /^\s*|\s*$/g;
	if (loginusername.value.replace(reg,'') == "")
    {
        alert ("请输入用户名");
        textnickname.focus ();
//        location.replace();
        return false;
    }
    if (loginpassword.value.replace(reg,'') == "")
    {
        alert ("请输入密码");
        textusername.focus ();
//        location.replace();
        return false;
    }
    return true;
}

function checkUserExit(){
	//用户不存在，则注册,返回true，异步加载
	$.ajax({
        type : "POST",
        url : "regist/judgeUserExitAction",
        data : "userName="+(textusername.value),
        async:false,
        cache:false,
        success : function(msg) { 
        	console.log("返回信息了");
            if(msg=="true"){
            	alert("用户已存在"); 
            	result=false;
            }else{
            	result= true;
            }
        },
        error : function(e) {
         alert("error");
         result= false;
        }
       });
	return result;
}

function register()  
{  
	var nickname=document.getElementById("textnickname").value;
	var username=document.getElementById("textusername").value;
	var password=document.getElementById("textpassword").value;
	var gender=document.getElementById("regAction_user_gender").value;
	var user={
			"nickname":encodeURIComponent(nickname),
			"username":username,
			"password":password,
			"gender":gender
			};
	var paramusers = $.toJSON(user);
//	var url = "http://localhost:8088/TestProject/regist/regAction.action";
	var url = window.location.href+"/regist/regAction.action";
	$.ajax( {
		type : "POST",
		url : url,
		contentType: "application/json",
		data :paramusers,
		dataType : "JSON",
		success : function(data) {
			//data为后台返回的Json信息
			if(data && data.status == 1){
				window.location.href = getRootPath_web()+"/RegisterSuccess.jsp";
			}else if(data.status == 0 && data.error_code.equal('20001')){
				//20001：参数格式错误
				$("#regAction__execute").after('<span style="color:red">参数格式错误</span>');
				//alert('错误信息：失败');
			}
		},
		 error: function(error){ 
	            ///请求出错处理 	             
	            alert('错误信息：'+error.status);
	            window.location.href = getRootPath_web()+"/Register.jsp"
	     } 
	})
//	var form = document.getElementById("regAction");
////	alert ("执行注册");
//	form.action="regist/regAction.action";
//	form.submit();
} 

function login()  
{  
	var username=document.getElementById("loginusername").value;
	var password=document.getElementById("loginpassword").value;
	var user={
			"username":username,
			"password":password
			};
	var paramusers = $.toJSON(user);
	var url = window.location.href+"/logins/loginAction.action";
	$.ajax( {
		type : "POST",
		url : url,
		contentType: "application/json",
		data :paramusers,
		dataType : "JSON",
		success : function(data) {
			//data为后台返回的Json信息			
			if(data && data.status == 1){
				window.location.href = getRootPath_web()+"/userMain.jsp";
			}else if(data.status == 0 && data.error_code==20001){
				//20001：参数格式错误
//				$(".errormsgs").show();
				$("#loginpassword").after('<span class="errormsgs" style="color:red">'+data.errorMsg+'</span>')
			}
		},
		 error: function(error){ 
	            ///请求出错处理 	             
	            alert('错误信息：'+error.status);
	            window.location.href = getRootPath_web()+"/login.jsp"
	     } 
	})
//	var form = document.getElementById("loginAction");
////	alert ("执行登录");
//	form.action="logins/loginAction.action";
//	form.submit();
}

//动态删除行
function DelHtml(){
	$('.errormsgs').remove();
}

//获取当前项目路径和host
function getRootPath_web() {
	//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	var curWwwPath = window.document.location.href;
	//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	//获取主机地址，如： http://localhost:8083
	var localhostPaht = curWwwPath.substring(0, pos);
	//获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
}