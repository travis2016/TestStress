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
<title>组权限分配页</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/Mycss/usepage.css">
    <script type="text/javascript" src="../../js/jquery-2.2.2.min.js"></script>
    <script type="text/javascript" src="../../js/mainJs/data.js"></script>
</head>
<body onload="lodaUserData('searchGroupList')">
	<div class="row">
      <div class="col-lg-12">
         <br><h1 class="page-header">组权限分配页</h1>
      </div>
        <table data-toggle="table" class="table table-bordered">
            <thead>
            <tr id="menus">
                <th data-field="userNames">组名</th>
                <th data-field="userActons">组操作</th>
            </tr>
            </thead>
            <tbody>
            <tr id="dataString">
                <td></td>
                <td></td>
            </tr>
            </tbody>
        </table>
    </div> 
</body>
</html>