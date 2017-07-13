<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="logins" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String uname=(String)request.getSession().getAttribute("loginusername");
%>
<!DOCTYPE html>
<head>
<title>个人所属组分配页</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/Mycss/usepage.css">
    <script type="text/javascript" src="../../js/jquery-2.2.2.min.js"></script>
    <script type="text/javascript" src="../../js/mainJs/data.js"></script>
</head>
<body onload="lodaUserData('searchPersionList')">
<div class="row">
    <div class="col-lg-12">
        <br><h1 class="page-header">个人权限分配页</h1>
    </div>
</div>
</body>
</html>
