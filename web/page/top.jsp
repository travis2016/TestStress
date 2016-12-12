<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="logins" uri="/struts-tags"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String uname=(String)request.getSession().getAttribute("loginusername");
%>
<!DOCTYPE html>
<html>
<head>
<title>top</title>
	  <meta http-equiv="pragma" content="no-cache">
	  <meta http-equiv="cache-control" content="no-cache">
	  <meta http-equiv="expires" content="0">    
	  <script type="text/javascript" src="../js/jquery-2.2.2.min.js"></script>
      <script type="text/javascript" src="../js/bootstrap.min.js"></script>
      <script type="text/javascript" src="../js/metisMenu/metisMenu.min.js"></script> 
      <script type="text/javascript" src="../js/mainJs/main.js"></script>
      <script>
		window.onload=
		function(){
			var linkList=window.parent.document.getElementsByTagName("link");//获取父窗口link标签对象列表
			var head=document.getElementsByTagName("head").item(0);
			//外联样式
			for(var i=0;i<linkList.length;i++){
				var l=document.createElement("link");
				l.rel = 'stylesheet'
				l.type = 'text/css'; 
				l.href=linkList[i].href;
				head.appendChild(l);
			}
		}
		
		function exitSystem(){
			top.location.href="<%=basePath %>login.jsp";
		}
	</script>
</head>
<body id="topBody">
	<div>
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
               <a class="navbar-brand" href="javascript:void(0)">Stress Main</a>
            </div>
            <div class="dropdown">
	           <ul class="nav navbar-top-links  navbar-right">
	           	  <li class="dropdown">
	           	  	<a href="#" class="dropdown-toggle" data-toggle="dropdown" >
	           	  		<i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
	           	  	</a>
	           	  	<ul class="dropdown-menu">
	           	  	  <li>
	           	  	   <a href="<%=basePath %>userMain.jsp">
	           	  	      <i class="fa fa-user fa-fw"></i>
                          <i> <%=uname %></i>
                       </a>
                       </li>
                       <li>
						 <a href="<%=basePath %>login.jsp" onclick="exitSystem()">                       
	                          <i class="fa fa-sign-in fa-fw"></i>
	                         <i>退出系统</i>
	                     </a>
                       </li>
	           	  	</ul>
	           	  </li>
	           </ul>
	        </div>
	        </nav>
	        </div>
</body>
</html>