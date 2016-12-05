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
        <title>列表树页</title>
        <script src="../js/jquery-2.2.2.min.js" type="text/javascript"></script>
        <link href="../css/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
        <script src="../js/ligerUI/core/base.js" type="text/javascript"></script>
        <script src="../js/ligerUI/plugins/ligerTree.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(function ()
            {
                $("#tree1").ligerTree({ treeLine:false });
            });
        </script>
    </head>
    <body style="padding:10px">
    <!--带复选框和Icon-->
    <div style="width:200px; height:300px; margin:10px; float:left; border:1px solid #ccc;overflow:auto;">
        <ul id="tree1">
            <li isexpand="false">
                <span>节点1</span>
                <ul>
                    <li isexpand="false">
                        <span>节点1.1</span>
                        <ul>
                            <li><span>节点1.1.2</span></li>
                            <li><span>节点1.1.2</span></li>
                        </ul>
                    </li>
                    <li><span>节点1.2</span></li>
                </ul>
            </li>
        </ul>
    </div>

    <div style="display:none">

    </div>
    </body>
</html>