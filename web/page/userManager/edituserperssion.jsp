<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="users" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String uname=(String)request.getSession().getAttribute("loginusername");
%>
<!DOCTYPE html>
<html>
<head>
    <title>修改用户权限页</title>

    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/Mycss/usepage.css">
    <script type="text/javascript" src="../../js/jquery-2.2.2.min.js"></script>
    <script type="text/javascript" src="../../js/mainJs/data.js"></script>
</head>
<body>
hello
</body>
</html>
