<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="logins" uri="/struts-tags"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String uname=(String)request.getSession().getAttribute("loginusername");
	if(uname==null)
	{
		System.out.println("here");
%>
	<script>
    	alert('尊敬的游客，由于您未登录，所以无法使用该平台，谢谢合作！');
    	window.location.href = '<%=basePath%>' + 'login.jsp';
    </script>
<% 
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>压测主页面</title>
	  <base href="<%=basePath%>">    
	  <meta http-equiv="pragma" content="no-cache">
	  <meta http-equiv="cache-control" content="no-cache">
	  <meta http-equiv="expires" content="0"> 
	  <link rel="stylesheet" href="css/Mycss/Main.css">
	  <link rel="stylesheet" href="css/bootstrap.min.css">
      <link rel="stylesheet" href="css/metisMenu/metisMenu.min.css">
      <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css" type="text/css">
      <link rel="stylesheet" href="css/style.css">
      <script type="text/javascript" src="js/jquery-2.2.2.min.js"></script>
      <script type="text/javascript" src="js/bootstrap.min.js"></script>
      <script type="text/javascript" src="js/metisMenu/metisMenu.min.js"></script> 
      <script type="text/javascript" src="js/mainJs/main.js"></script>  
	  
</head>
<body>
	<div id="mainTopdiv" name="mainTopdiv">
		<iframe src="<%=basePath%>/page/top.jsp"  id="iframetop" name="iframetop" frameborder="0" scrolling="no" width="100%"></iframe>
	</div>
	<div id="main_middle" name="main_middle">
		<div style="float:left">
			<iframe src="<%=basePath%>/page/middle_left.jsp"  id="iframeleft" name="iframeleft" frameborder="0" scrolling="no" width="20%"></iframe>
		</div>
		<div style="float:right">
		</div>
	</div>
</body>
</html>