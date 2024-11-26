<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.User" %>
<%@ page import="java.util.Date" %>
<%@ page import="cache.SystemCache" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
    <title>SSO Server</title>
</head>
<body>
<h2 style="text-align: center">Welcome to SSO Server</h2>

<table>
    <thead>
    <tr>
        <th>用户名</th>
        <th>登录时间</th>
    </tr>

    </thead>
    <tbody>
        <%
      ArrayList<User> users = SystemCache.getInstance().getRegisteredUsers();
      //格式化日期
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        for (User user : users) {

    %>
    <tr>
        <td><%=user.getUsername()%></td>
        <td><%=sdf.format(user.getLoginDate())%></td>
    </tr>
        <%
      }
    %>
</table>

<div class="button-container">
    <form action="JumpServlet" method="post">
        <button type="submit" name="action" value="web1">前往Web1</button>
        <button type="submit" name="action" value="web2">前往Web2</button>
    </form>
</div>
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
    .button-container {
        display: flex;
        justify-content: center;
        margin-top: 20px;
    }
    button {
        background-color: #2196F3;
        color: white;
        width: 100px;
        height: 30px;
        margin: 0 10px;
        border: 1px solid #ddd;
        cursor: pointer;

    }
    button:hover {
        background-color: #1976D2;
    }

</style>
</html>
