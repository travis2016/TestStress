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
<title>用户信息页</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css">
<link rel="stylesheet" href="../../css/Mycss/usepage.css">
<script type="text/javascript" src="../../js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="../../js/mainJs/data.js"></script>

</head>
<body onload="lodaUserData()">
 	<div class="row">
      <div class="col-lg-12">
         <br><h1 class="page-header" style="text-align:left;">用户表</h1>
      </div>
    <table data-toggle="table" class="table table-bordered">
	   	<thead>
    	    <tr id="menus">
	            <th data-field="userNames">用户名</th>
	            <th data-field="useSex">用户性别</th>
	            <th data-field="userNickname">用户显示名称</th>
	            <th data-field="userGroup">用户所属组</th>
	            <th data-field="userActons">用户操作</th>
	        </tr>
    	</thead>
    	<tbody>
    		<tr id="dataString">
    			<td></td>
    			<td></td>
    			<td></td>
    			<td></td>
    			<td></td>
    		</tr>
    	</tbody>
	</table>
    </div> 
</body>
</html>
