<%--
  Created by IntelliJ IDEA.
  User: 13664
  Date: 2024/11/25
  Time: 上午9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<%
    String backUrl = request.getParameter("backUrl");
    String wrongInfo=(String)request.getAttribute("wrongInfo");
    System.out.println(wrongInfo);
    if (wrongInfo!=null){
%>
<script>
    alert("<%=wrongInfo%>");
</script>
<%
    }
%>

<div class="register">
    <h2>注册</h2>
    <form action="RegisterServlet" method="post">
        <label for="username">用户名</label>
        <input type="text" id="username" name="username" required/>
        <label for="password">密码</label>
        <input type="password" id="password" name="password" required/>
        <label for="confirmPassword">确认密码</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required/>
<%--        <input type="hidden" name="backUrl" value="<%=backUrl%>"/>--%>
        <button type="submit">注册</button>
    </form>
    <a href="login.jsp">已有账号？点击登录</a>
</div>
</body>
<style>
    body {
        background: #F7F9FB;
        font-family: Arial, sans-serif;
        font-size: 14px;
    }
    .register {
        width: 400px;
        margin: 100px auto;
        background: #FFF;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0,0,0,.1);
    }
    h2 {
        text-align: center;
        font-size: 24px;
        margin-bottom: 30px;
    }
    label {
        display: block;
        margin-bottom: 10px;
        font-weight: bold;
    }
    input[type="text"], input[type="password"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 20px;
        border-radius: 5px;
        border: none;
        background: #F7F9FB;
        box-shadow: inset 0 0 3px rgba(0,0,0,.1);
    }
    button[type="submit"] {
        width: 100%;
        padding: 10px;
        border-radius: 5px;
        border: none;
        background: #007BFF;
        color: #FFF;
        font-weight: bold;
        cursor: pointer;
    }
    button[type="submit"]:hover {
        background: #0069D9;
    }
</style>
</html>
