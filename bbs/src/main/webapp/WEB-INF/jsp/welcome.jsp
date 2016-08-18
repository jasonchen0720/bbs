<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>清水河畔</title>
    <style type="text/css">
        #page-header a{color:blue;text-decoration: none;}
        #page-header a:hover {color:orange;text-decoration: underline;}
        #page-header span,a{margin-right:10px;}
        a.commonHref{color:black;text-decoration: none;cursor: pointer;}
        a.commonHref:hover {color:orange;text-decoration: underline;}
        div.logo{cursor: pointer; -moz-user-select: none; -webkit-user-select: none; -ms-user-select: none;
            -khtml-user-select: none; user-select: none;border: 0px solid black;width: 132px;}
    </style>
    <script src="/static/js/jquery-3.0.0.js"></script>
    <script type="text/javascript">
        $(function(){
            $("div.logo").click(function(){
                window.location.href="/user/toMain";
            });
        });
    </script>
</head>
<body>
<div>
    <div class="logo">
        <h1 style="color: red;margin: 0;padding: 0;">清水河畔</h1>
        <h3 style="color: red;margin: 0;padding: 0;">uestc.com.cn</h3>
    </div>
    <div align="center">
        <p><a class="commonHref" href="/user/toLogin">登录</a></p>
        <p><a class="commonHref" href="/user/toRegister">注册</a></p>
    </div>
</div>


</body>
</html>
