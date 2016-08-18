<%--
  Created by IntelliJ IDEA.
  User: jason
  Date: 2016/8/12
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>清水河畔—发帖</title>
    <style type="text/css">
        #page-header a{color:blue;text-decoration: none;}
        #page-header a:hover {color:orange;text-decoration: underline;}
        #page-header span,a{margin-right:10px;}
        div.logo{cursor: pointer; -moz-user-select: none; -webkit-user-select: none; -ms-user-select: none;
            -khtml-user-select: none; user-select: none;border: 0px solid black;width: 132px;}
        input.hiddenBorder{border-style: none;font-size: 18px;}
    </style>
    <script src="/static/js/jquery-3.0.0.js"></script>
    <script src="/static/js/bbs-header.js"></script>
    <script type="text/javascript">
        $(function(){
            var user = '<%=session.getAttribute("user") %>';
            if(user=='null'){
                initPageHeader(0);
            }else{
                $("<span>欢迎您${user.username}</span>").appendTo($("#page-header"));
                initPageHeader(1);
            }
        });
        $(function(){
            $("div.logo").click(function(){
                window.location.href="/user/toMain";
            });
        });
    </script>
</head>
<body>
<div>
    <div id="page-header" align="right" style="font-size: 15px;"></div>
    <div class="logo">
        <h1 style="color: red;margin: 0;padding: 0;">清水河畔</h1>
        <h3 style="color: red;margin: 0;padding: 0;">uestc.com.cn</h3>
    </div>
    <div>
        <h2>${columnBelong} zone</h2>
        <h3>请写下你的主题：</h3>
        <form action="/issue/publish" method="post">
            <div>
                <input class="hiddenBorder" type="text" name="columnBelong" value="${columnBelong}" readOnly="readonly" style="display: none"/>
            </div>
            <div>
               <textarea name="issueContent" style="width:800px;height:150px;display: inline-block;"></textarea>
            </div>
            <div>
                <input type="submit" value="发帖"/>
            </div>

        </form>
    </div>
</div>

</body>
</html>
