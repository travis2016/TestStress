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
<title>left</title>
	  <meta http-equiv="pragma" content="no-cache">
	  <meta http-equiv="cache-control" content="no-cache">
	  <meta http-equiv="expires" content="0"> 
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
	</script>
<body>
		<div id="maintop">
		 <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
		 	<div class="navbar-default sidebar" role="navigation">
	          <div class="sidebar-nav navbar-collapse">
	        	<ul class="nav" id="side-menu">
	        		<li class="sidebar-search">
	        			<div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
	        		</li>
	        		
	        		<li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 用户管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="<%=basePath %>page/userManager/userinfo.jsp" target="pageIframe" id="useLink1">用户信息页</a>
                                </li>
                                <li>
                                    <a href="<%=basePath %>page/userManager/PermissionAssignment.jsp" target="pageIframe" id="useLink2">权限分配页</a>
                                </li>
                                <li>
                                    <a href="morris.html">操作日志查看页</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                    </li>
	        	</ul>
	          </div>
	        </div>    
        </nav>
       </div>
</body>
</html>