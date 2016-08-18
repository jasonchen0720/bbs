<%--
  Created by Jason chen
  Date: 2016/5/21
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<style type="text/css">
#page-header a{color:blue;text-decoration: none;}
#page-header a:hover {color:orange;text-decoration: underline;}
#page-header span,a{margin-right:10px;}
label.formText{width:135px;text-align:right;display:inline-block;}
input.formText{width:240px;height:25px;margin-top:25px;}
button.formButton{ width:120px;height:30px;margin-top:25px; border:1px solid #666666;background:#FF7F50;font-size:18px;} 
div.formContainer{ width:450px;height:450px;background:#F5F5DC;position:absolute; left:50%;top:50%;margin: -225px 0 0 -225px;}
div.prompt{border:0px solid black;margin-left: 144px;color: red;font-size: 15px;}
</style>
<script src="/static/js/jquery-3.0.0.js"></script>
<script src="/static/js/bbs-register.js"></script>
<script src="/static/js/bbs-header.js"></script>
<script type="text/javascript">
$(function(){
	initPageHeader(0);
});
</script>
</head>
<body>
<div>
    <div id="page-header" align="right" style="font-size: 15px;"></div>   
    <div>
        <h1 style="color: red;margin: 0;padding: 0;">清水河畔</h1>
        <h3 style="color: red;margin: 0;padding: 0;">uestc.cn.com</h3>
    </div>  
    <div class="formContainer">
       <h2 align="center">请填写注册信息</h2>
       <form id="form-reg" name="form_reg">
           <div>
               <label class="formText">邮&nbsp;&nbsp;&nbsp;&nbsp;箱：</label>
               <input class="formText" type="text" name="email" onblur="validForm(this)"  style="margin-top:0px;"/>
           </div>
           <div id="prompt-for-email" class="prompt"></div>
           <div>
                <label class="formText">名&nbsp;&nbsp;&nbsp;&nbsp;字：</label>
                <input class="formText" type="text" name="username" onblur="validForm(this)"/>
          </div>
          <div id="prompt-for-name" class="prompt"></div>
          <div>
               <label class="formText">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
               <input class="formText" type="password" name="password" onblur="validForm(this)" />
          </div>
          <div id="prompt-for-password" class="prompt"></div>
          <div>
               <label class="formText">密码确认：</label>
               <input class="formText" type="password" name="confirmedPassword" onblur="validForm(this)"/>
           </div>
           <div id="prompt-for-rpassword" class="prompt"></div>

      </form>
      <label class="formText"></label>
      <button class="formButton" onclick="regisReq()">注册</button>     
    </div>
</div>
</body>
</html>