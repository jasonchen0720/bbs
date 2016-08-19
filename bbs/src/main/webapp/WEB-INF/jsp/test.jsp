<%--
  Created by IntelliJ IDEA.
  User: jason
  Date: 2016/8/19
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/static/js/jquery-3.0.0.js"></script>
    <script type="text/javascript">
        function test() {
            var sendData = $("#test").serialize();
            var options = {
                url: '/test/test',
                type: 'get',
                dataType: 'json',
                data: sendData,
                success: function (data) {
                    if (data.code == 0) {
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
     <button onclick="test()">测试</button>
</body>
</html>
