<%--
  Created by IntelliJ IDEA.
  User: jason
  Date: 2016/8/11
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>清水河畔-帖子详情</title>
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

        ul li span {
            margin-right: 0px;
            /*border: 1px solid red;*/
        }

        ul li {
            width: 800px;
            min-height: 150px;
            height: auto;
            margin-top: 20px;
            border: 1px solid #DDD;
            position:relative;
        }

        ul.inner li {
            min-height: 50px;
            height: auto;
            margin-bottom: 0px;
            border-left: 0px solid #DDD;
            border-right: 0px solid #DDD;
        }

        div.replyBoxClose {
            border: 0px solid red;
            display: none;
            width: 800px;
            height: 250px;
            background: #F5F5F5;
            margin-bottom: 10px;
        }
        div.replyBoxOpen {
            border: 0px solid red;
            display: block;
            width: 800px;
            height: 250px;
            background: #F5F5F5;
            margin-bottom: 10px;
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
        function publishComment() {
            var sendData = $("#comment-publish form").serialize();
            var options = {
                url: '/comment/publishComment',
                type: 'get',
                dataType: 'json',
                data: sendData,
                success: function (data) {
                    var response = JSON.parse(data);
                    if (response.code == 0) {
                        window.location.href = "/issue/issueDetail/${issue.issueId}";
                        alert(response.message);
                    } else {

                        alert(response.message);
                    }

                }
            };
            $.ajax(options);
        }

        function reply(replyBoxId) {
            var sendData = $("#" + replyBoxId + " form").serialize();
            var options = {
                url: '/reply/sendReply',
                type: 'get',
                dataType: 'json',
                data: sendData,
                success: function (data) {
                    var response = JSON.parse(data);
                    if (response.code == 0) {
                        window.location.href = "/issue/issueDetail/${issue.issueId}";
                        alert(response.message);
                    } else if (response.code == -1) {
                        window.location.href = "/issue/issueDetail/${issue.issueId}";
                        alert(response.message);
                    } else {
                        window.location.href = "/issue/issueDetail/${issue.issueId}";
                        alert(response.message);
                    }
                }
            };
            $.ajax(options);
        }

        function toReply(replyDivId, replyToId, replyToName) {
            if($("#" + replyDivId).attr("class")=="replyBoxClose") {
                $("div.replyBoxOpen").attr("class", "replyBoxClose");
                $("#" + replyDivId).attr("class","replyBoxOpen")
                $("#" + replyDivId + " form input[name=replyToId]").val(replyToId);
                $("#" + replyDivId + " form input[name=replyToName]").val(replyToName);
            }else{
                $("#" + replyDivId).attr("class","replyBoxClose");
                $("#" + replyDivId + " form input[name=replyToId]").val("");
                $("#" + replyDivId + " form input[name=replyToName]").val("");
            }
            //$("div.replyBoxClose").css("display", "none");
            //$("#" + replyDivId).css("display", "block");
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
        <h2>${issue.columnBelong} zone</h2>
        <div>
            <div style="color: blue;margin-bottom: 20px;">
                <span style="margin-right: 40px">${issue.creatorName}<span/>
                <span style="margin-right: 40px">${issue.issueTime}<span/>
                <span style="margin-right: 40px">${issue.issueContent}<span/>
            </div>
            <div>
                <ul>
                    <c:if test="${fn:length(issue.comments)>0}">
                        <c:set var="index" value="0"/>
                        <c:forEach var="comment" items="${issue.comments}">
                            <c:set var="index" value="${index+1}"/>
                            <li>
                                <div style="height: 150px;border-bottom: 1px solid #DDD; position:relative;">
                                    <span>${index}楼</span>
                                    <span>${comment.authorName}</span>
                                    <span>${comment.publishTime}</span>
                                    <span>${comment.commentContent}</span>
                                    <button style="position:absolute;bottom:0;right: 0;"
                                            onclick="toReply(${index},${comment.authorId},'${comment.authorName}')">我说一句
                                    </button>
                                </div>
                                <c:if test="${fn:length(comment.replies)>0}">
                                    <ul class="inner" style="background-color:#F2F2F2" ;>
                                        <c:forEach var="reply" items="${comment.replies}">
                                            <li>
                                                <span style="color: blue">${reply.replierName}:</span>
                                                <span>回复${reply.replyToName}：</span>
                                                <span>${reply.replyContent}</span>
                                                <div style="position:absolute;bottom: 0px;right: 0;">
                                                    <span>${reply.replyTime}</span>
                                                    <button onclick="toReply(${index},${reply.replierId},'${reply.replierName}')">
                                                        回复
                                                    </button>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                                <c:if test="${fn:length(comment.replies)==0}">
                                    <button style="position:absolute;bottom:0;right: 0;"
                                            onclick="toReply(${index},${comment.authorId},'${comment.authorName}')">我说一句
                                    </button>
                                </c:if>
                            </li>
                            <div id="${index}" class="replyBoxClose">
                                <div align="center" style="padding-top: 50px;">
                                    <form>
                                        <input type="text" name="replyToId" value="" readOnly="readonly"
                                               style="display: none;"/>
                                        <input type="text" name="replyToName" value="" readOnly="readonly"
                                               style="display: none;"/>
                                        <input type="text" name="commentId" value="${comment.commentId}"
                                               readOnly="readonly" style="display: none;"/>
                                        <textarea name="replyContent"
                                                  style="width:700px;height:100px;display: inline-block;"></textarea>
                                    </form>
                                    <button onclick="reply(${index})">确定</button>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <div id="prompt-no-comment">
                        <c:if test="${issue.comments== null||fn:length(issue.comments) == 0}">
                            <p style="font-size: 20px">还没有人留言！</p>
                        </c:if>
                    </div>
                </ul>
            </div>
        </div>
    </div>
    <div id="comment-publish">
        <form action="/comment/publishComment">
            <input type="text" name="issueId" value="${issue.issueId}" readOnly="readonly" style="display: none;"/>
            <textarea name="commentContent" style="width:800px;height:150px;display: inline-block;"></textarea>
        </form>
        <button onclick="publishComment()">发表评论</button>
    </div>
</div>
</body>
</html>
