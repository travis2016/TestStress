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
    <title>内容显示</title>
</head>
<body>
111

</body>
</html>