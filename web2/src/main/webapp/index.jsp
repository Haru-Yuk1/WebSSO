<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.User" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: 13664
  Date: 2024/11/24
  Time: 下午4:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Web1</title>
</head>
<body>
<h2 style="text-align: center">Welcome to web1</h2>

<table>
    <thead>
    <tr>
        <th>用户名</th>
        <th>登录时间</th>
    </tr>

    </thead>
    <tbody>
        <%
      ArrayList<User> users = new ArrayList<>();
        users.add(new User("张三", "123", new Date()));
        users.add(new User("李四", "123", new Date()));
        users.add(new User("王五", "123", new Date()));
        for (User user : users) {

    %>
    <tr>
        <td><%=user.getUsername()%></td>
        <td><%=user.getLoginDate()%></td>
    </tr>
        <%
      }
    %>
</table>
<button onclick="window.location.href='/web1'">前往Web1</button>
</body>
<style>
    table {
        width: 50%;
        text-align: center;
        margin: 0 auto;
        border-collapse: collapse;
    }

    th, td {
        border: 1px solid #ddd;
        padding: 10px;
        text-align: center;
    }
    th {
        background-color: #2196F3;
        color: white;
        font-weight: bold;
    }
    button{

        margin-top: 20px;
        display: block;
        margin-left: auto;
        margin-right: auto;
        background-color: #2196F3;
        color: white;
        width: 100px;
        height: 30px;
        border: 1px solid #ddd;

    }

</style>
</html>
