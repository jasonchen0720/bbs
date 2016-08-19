<%--
  Created by IntelliJ IDEA.
  User: jason
  Date: 2016/8/11
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>清水河畔-主题列表</title>
    <style type="text/css">
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

        a.issue {
            color: blue;
            text-decoration: none;
        }

        a.issue:hover {
            color: orange;
            text-decoration: underline;
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

        ul {
            margin: 0 auto;
            padding: 0;
            list-style: none;
        }

        ul li {
            width: 1200px;
            height: 75px;
            margin-bottom: 10px;
            border: 1px solid #DDD;
        }

        ul li span {
            margin-right: 10px;
        }

        div.issueBoxClose {
            border: 0px solid red;
            display: none;
            width: 800px;
            background: #F5F5F5;
        }

        div.issueBoxOpen {
            border: 0px solid red;
            display: block;
            width: 800px;
            background: #F5F5F5;
        }
    </style>
    <script src="/static/js/jquery-3.0.0.js"></script>
    <script src="/static/js/bbs-header.js"></script>
    <script type="text/javascript">
        $(function () {
            var user = '<%=session.getAttribute("user") %>';
            if (user == 'null') {
                initPageHeader(0);
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
        function toIssue() {
            if ($("#issue-publish").attr("class") == "issueBoxClose") {
                $("#issue-publish").attr("class", "issueBoxOpen")
            } else {
                $("#issue-publish").attr("class", "issueBoxClose");
            }
        }
        function issue() {
            var sendData = $("#issue-publish form").serialize();
            var options = {
                url: '/issue/publish',
                type: 'get',
                dataType: 'json',
                data: sendData,
                success: function (data) {
                    if (data.code == 0) {
                        window.location.href = "/issue/issues/${columnBelong}";
                        alert(data.message);
                    } else {
                        alert(data.message);
                    }
                }
            };
            $.ajax(options);
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
    <div>
        <h2>${columnBelong} zone</h2>
        <ul>
            <c:if test="${issues == null ||fn:length(issues) == 0}">
                <div align="center" style="font-size: 20px">还没有人创建帖子，赶紧发一贴！</div>
            </c:if>
            <c:if test="${fn:length(issues)>0}">
                <c:forEach var="issue" items="${issues}">
                    <li>
                        <span>${issue.creatorName}</span>
                        <span>${issue.issueTime}</span>
                        <a class="issue" href="/issue/issueDetail/${issue.issueId}">${issue.issueContent}</a>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
    </div>
    <div align="center">
        <p>
            <button onclick="toIssue()">我要发帖</button>
        </p>
    </div>
    <div id="issue-publish" class="issueBoxClose">
        <div><h3>写下你的主题：</h3></div>
        <div align="center">
            <form>
                <input class="hiddenBorder" type="text" name="columnBelong" value="${columnBelong}" readOnly="readonly"
                   style="display: none"/>
                <textarea name="issueContent" style="width:600px;height:150px;display: inline-block;"></textarea>
            </form>
            <button onclick="issue()">提交</button>
        </div>
    </div>
</div>
</body>
</html>
