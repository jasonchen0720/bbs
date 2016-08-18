<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>个人信息</title>
    <style type="text/css">
        button {
            width: 120px;
            height: 25px;
            border-bottom: 1px solid #666666;
            background: #2E8B57;
            font-size: 15px;
            color: white;
        }

        input.myInfo {
            border-style: none;
            background-color: inherit;
            font-size: 15px;
            font-family: cursive;
        }

        div.logo {
            cursor: pointer;
            -moz-user-select: none;
            -webkit-user-select: none;
            -ms-user-select: none;
            -khtml-user-select: none;
            user-select: none;
            border: 0px solid black;
            width: 132px;
        }

        #input-submit {
            width: 60px;
            height: 25px;
            border-bottom: 1px solid #666666;
            background: #2E8B57;
            font-size: 15px;
            color: white;
        }

        #page-header a {
            color: blue;
            text-decoration: none;
        }

        #page-header a:hover {
            color: orange;
            text-decoration: underline;
        }

        #page-header span, a {
            margin-right: 10px;
        }

        a.commonHref {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        a.commonHref:hover {
            color: orange;
            text-decoration: underline;
        }
    </style>
    <script src="/static/js/jquery-3.0.0.js"></script>
    <script src="/static/js/bbs-header.js"></script>
    <script type="text/javascript">
        $(function () {
            var user = '<%=session.getAttribute("user") %>';
            if (user == 'null') {
                window.location.href = "/user/toLogin";
            } else {
                $("<span>欢迎您${user.username}</span>").appendTo($("#page-header"));
                initPageHeader(1);
            }
        });
        $(function () {
            $("div.logo").click(function () {
                window.location.href = "/user/toMain";
            });
        });
        function enableModify() {
            var input = document.getElementById("input-name");
            input.removeAttribute("readonly");
            input.style.background = "white";
            input.removeAttribute("readonly");
            input.style.background = "white";
            document.getElementById("input-submit").style.display = "block";
        }
    </script>
</head>
<body>
<div>
    <div id="page-header" align="right" style="font-size: 15px;"></div>
    <div class="logo">
        <h1 style="color: red;margin: 0;padding: 0;">清水河畔</h1>
        <h3 style="color: red;margin: 0;padding: 0;">uestc.com.cn</h3>
    </div>
    <div align="center">
        <h2>我的个人信息</h2>
        <div>
            <p>
                <button onclick="enableModify()">编辑信息</button>
                <a href="" class="commonHref">密码修改</a>
            </p>
            <form action="">
                <label>我的昵称：</label>
                <input id="input-name" class="myInfo" type="text" name="name" readonly="readonly"
                       value="${user.username}">
                <br/><br/>
                <label>我的邮箱：</label>
                <input id="input-email" class="myInfo" type="text" name="email" readonly="readonly"
                       value="${user.email}">
                <br/><br/>
                <input id="input-submit" type="submit" value="保存" style="display:none;"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>