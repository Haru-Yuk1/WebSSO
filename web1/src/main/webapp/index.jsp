<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.User" %>
<%@ page import="java.util.Date" %>
<%@ page import="cache.SystemCache" %>
<%@ page import="java.util.HashMap" %>
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
      String username = request.getParameter("username");
      String loginDate= request.getParameter("loginDate");
//      SystemCache.getUserLoginDateMap().put(username, loginDate);
//        if (currentUser != null) {
//          SystemCache.setCurrentUser(currentUser);
//          SystemCache.getRegisteredUsers().add(currentUser);
//        }
//      ArrayList<User> users = SystemCache.getRegisteredUsers();
//        for (User user : users) {

    %>
    <tr>
      <td><%=username%></td>
      <td><%=loginDate%></td>
    </tr>
    <%
//      }
    %>
  </table>
    <button onclick="window.location.href='/web2'">前往Web2</button>
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
