<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="logins" uri="/struts-tags"%>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        String uTrueName=(String)request.getSession().getAttribute("username");
        String uname=(String)request.getSession().getAttribute("loginusername");
    %>
    <!DOCTYPE html>
    <html>
    <head>
        <title>列表树页</title>
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <script src="../js/jquery-2.2.2.min.js" type="text/javascript"></script>
        <script src="../js/bootstrap-treeview.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            function treeData() {
                var loginName = "<%=uTrueName%>";
                //请求接口返回data数据
                var returnData=$.ajax({
                    type: "POST",
                    url: "selectTreeAction",
                    dataType : "JSON",
                    data: "username="+loginName,
                    success: function (data) {
                        if (data) {
                            return data.treeData;
                        }
                    },
                    error: function (e) {
                        alert("查询权限异常");
                    }
                });
                console.log(returnData);
                return returnData;
            }
            $(function(){
                var options = {
                    color: "#428bca",
                    expandIcon: 'glyphicon glyphicon-chevron-right',
                    collapseIcon: 'glyphicon glyphicon-chevron-down',
                    nodeIcon: 'glyphicon glyphicon-bookmark',
                    data:treeData()
                };

                $('#treeview').treeview(options);

                $('#treeview').on('nodeSelected', function(event, data) {
                    // clickNode(event, data)
                    if(data.href != undefined){
                        alert(data.href);
                        alert(data.text);
                        alert(data.nodeId);
                        window.parent.document.getElementById("iframeright").src="<%=basePath%>/page/middle_left.jsp";

                        var arr = $('#treeview').treeview('getSelected');
                        alert(JSON.stringify(arr));
                    }
                });
            });
        </script>
    </head>
    <body style="padding:10px">
    <!--带复选框和Icon-->
    <div class="container">
        <div class="row">
            <div class="col-sm-4">
                <div id="treeview" class=""></div>
            </div>
        </div>
    </div>
    </body>
</html>