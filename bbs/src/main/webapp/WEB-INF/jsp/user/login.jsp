<%--
  Created by IntelliJ IDEA.
  User: jason
  Date: 2016/8/11
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
    <style type="text/css">
        label.formText{width:135px;text-align:right;display: inline-block;}
        input.formText{width: 240px;height: 25px;margin-top:30px;}
        input.formButton{margin-top:20px; width:120px;height:30px;border:1px solid #666666;background:#FF7F50;font-size: 18px;}
        div.formContainer{ width:450px;height:400px;background:#F5F5DC;position:absolute; left:50%;top:50%;margin: -200px 0 0 -225px;}
        a{color:#FF7F50;text-decoration: none;}
        a:hover {color:orange;text-decoration: underline;}
        #a-fpwd{color: gray;text-decoration: none;}
        #a-fpwd:hover {color:orange;text-decoration: underline;}
        #a-main{color: blue;text-decoration: none;}
        #a-main:hover {color:black;text-decoration: underline;}
        div.logo{cursor: pointer; -moz-user-select: none; -webkit-user-select: none; -ms-user-select: none;
            -khtml-user-select: none; user-select: none;border: 0px solid black;width: 132px;}
        div.prompt{border:0px solid black;margin-left: 144px;color: red;font-size: 15px;}
    </style>
    <script src="/static/js/jquery-3.0.0.js"></script>
    <script type="text/javascript">
        $(function(){
            var loginMsg='<%=request.getAttribute("loginMsg")%>';
            if(loginMsg=="login failed"){
                alert("用户名或密码错误");
            }else if(loginMsg=="login timeout"){
                alert("登陆超时，请重新登录");
            }
        });
        $(function(){
            $("div.logo").click(function(){
                window.location.href="/user/toMain";
            });
        });

        function validForm(obj){
            var emailRegexp = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
            var name=$(obj).attr("name");
            switch (name) {
                case "email":
                    var email=$(obj).val();
                    if(null==email||0==email.length){
                        $("#prompt-for-email").text("email不能为空！");
                    }else if(!emailRegexp.test(email)){
                        $("#prompt-for-email").text("输入email格式不正确！");
                    }else{
                        $("#prompt-for-email").text("");
                    }
                    break;
                case "password":
                    var password=$(obj).val();
                    if(null==password||0==password.length){
                        $("#prompt-for-password").text("密码不能为空！");
                    }else if(password.length<8||password.length>20){
                        $("#prompt-for-password").text("输入密码长度介于8~20个字符之间！");
                    }else{
                        $("#prompt-for-password").text("");
                    }
                    break;
                default:
                    break;
            }
        }
    </script>
</head>
<body>
<div style="border:0px solid black;">
    <div align="right" style="font-size: 15px;">
        <a id="a-main" href="/user/toMain">河畔首页</a>
    </div>
    <div class="logo">
        <h1 style="color: red;margin: 0;padding: 0;">清水河畔</h1>
        <h3 style="color: red;margin: 0;padding: 0;">uestc.cn.com</h3>
    </div>
    <div class="formContainer">
        <h2 align="center">账户登录</h2>
        <div>
            <form action="/user/login" method="post">
                <div>
                    <label class="formText">邮&nbsp;&nbsp;&nbsp;&nbsp;箱：</label>
                    <input class="formText" type="text" name="email" onblur="validForm(this)"/>
                </div>
                <div id="prompt-for-email" class="prompt"></div>
                <div>
                    <label class="formText">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                    <input class="formText" type="password" name="password" onblur="validForm(this)"/>
                </div>
                <div id="prompt-for-password" class="prompt"></div>
                <div>
                    <div align="center" style="font-size: 14px;margin-top: 30px;"><a id="a-fpwd" href="">忘记密码</a></div>
                    <label class="formText"></label>
                    <input class="formButton" type="submit" value="登录"/>
                </div>
            </form>
            <br/>
            <div align="center">未注册清水河畔？<a href="/user/toRegister">免费注册</a></div>
        </div>
    </div>
</div>
</body>
</html>
