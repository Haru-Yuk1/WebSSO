<%--
  Created by IntelliJ IDEA.
  User: 13664
  Date: 2024/11/29
  Time: 上午11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登出</title>
</head>
<body>
<h2>登出成功</h2>
<script>
    fetch("http://localhost:8080/web1/LogoutServlet").then(() => {
    });
    fetch("http://localhost:8080/web2/LogoutServlet").then(() => {

    });
</script>

</body>
<style>
    h2 {
        text-align: center;
        font-size: 24px;
        margin-bottom: 30px;
    }

</style>
</html>
