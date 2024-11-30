<%@ page import="entity.LoginHistory" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="static dao.LoginHistoryManager.viewLoginHistory" %>
<%@ page import="cache.SystemCache" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: 13664
  Date: 2024/11/29
  Time: 下午8:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看历史登录记录</title>
</head>
<body>
<h2 style="text-align: center">查看历史登录记录</h2>
<table>
    <thead>
    <tr>
        <th>用户名</th>
        <th>登录时间</th>
    </tr>

    </thead>
    <tbody>
        <%
      ArrayList<LoginHistory> loginHistories = viewLoginHistory(request.getSession().getAttribute("username").toString());
      //格式化日期
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (LoginHistory loginHistory : loginHistories) {

    %>
    <tr>
        <td><%=loginHistory.getUsername()%></td>
        <td><%=sdf.format(loginHistory.getLoginDate())%></td>
    </tr>
        <%
      }
    %>
</table>
<button onclick="location.href='index.jsp'">返回</button>
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

    button {
        background-color: #2196F3;
        color: white;
        width: 150px;
        height: 50px;
        margin: 20px auto;
        border: 1px solid #ddd;
        display: block;
        text-align: center;
        cursor: pointer;

    }
    button:hover {
        background-color: #1976D2;
    }

</style>
</html>
