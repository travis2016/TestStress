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
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../css/tree/default.css">
        <script src="../js/jquery-2.2.2.min.js" type="text/javascript"></script>
        <script src="../js/bootstrap-treeview.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            function buildDomTree() {
                var data = [
                    {
                        text: 'Parent 1',
                        href: '#parent1',
                        tags: ['4'],
                        nodes: [
                            {
                                text: 'Child 1',
                                href: '#child1',
                                tags: ['2'],
                                nodes: [
                                    {
                                        text: 'Grandchild 1',
                                        href: '#grandchild1',
                                        tags: ['0']
                                    },
                                    {
                                        text: 'Grandchild 2',
                                        href: '#grandchild2',
                                        tags: ['0']
                                    }
                                ]
                            },
                            {
                                text: 'Child 2',
                                href: '#child2',
                                tags: ['0']
                            }
                        ]
                    },
                    {
                        text: 'Parent 2',
                        href: '#parent2',
                        tags: ['0']
                    },
                    {
                        text: 'Parent 3',
                        href: '#parent3',
                        tags: ['0']
                    },
                    {
                        text: 'Parent 4',
                        href: '#parent4',
                        tags: ['0']
                    },
                    {
                        text: 'Parent 5',
                        href: '#parent5'  ,
                        tags: ['0']
                    }
                ];

                return data;
            }

            $(function(){

                var options = {
                    color: "#428bca",
                    expandIcon: 'glyphicon glyphicon-chevron-right',
                    collapseIcon: 'glyphicon glyphicon-chevron-down',
                    nodeIcon: 'glyphicon glyphicon-bookmark',
                    data: buildDomTree()
                };

                $('#treeview').treeview(options);
            });
        </script>
    </head>
    <body style="padding:10px">
    <!--带复选框和Icon-->
    <div class="container">
        <div class="row">
            <div class="col-sm-4">
                <h2>Custom Icons</h2>
                <div id="treeview" class=""></div>
            </div>
        </div>
    </div>
    </body>
</html>