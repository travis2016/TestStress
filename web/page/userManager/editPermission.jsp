<%--
  User: travissong
  Date: 2017/7/13
  Time: 14:47
  修改组权限
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="logins" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>修改权限页</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <script src="../../js/jquery-2.2.2.min.js" type="text/javascript"></script>
    <script src="../../js/bootstrap-treeview.min.js" type="text/javascript"></script>
    <script src="../../js/mainJs/data.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript">
        var loc = location.href;
        var n1 = loc.length;//地址的总长度
        var n2 = loc.indexOf("=");//取得=号的位置
        var groupnames = decodeURI(loc.substr(n2+1, n1-n2));//从=号后面的内容
        gerdata(groupnames,"<%=basePath %>");
    </script>
</head>
<body>
    <div id="treeview-checkable" class=""></div>
</body>
</html>
