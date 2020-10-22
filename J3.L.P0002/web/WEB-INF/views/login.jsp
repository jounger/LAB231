<%-- 
    Document   : login
    Created on : Sep 21, 2020, 8:20:08 PM
    Author     : nguyenvanan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <style><%@include file="/static/css/main.css"%></style>
        <script><%@include file="/static/script/main.js"%></script>
    </head>
    <body>
        <div class="main">
            <jsp:include page="../fragments/menu.jsp" />
            <div class="content">
                <h3 class="title">Login Form</h3>
                <form method="POST" action="${pageContext.request.contextPath}/login">
                    <table border="0">
                        <tr>
                            <td>Email:</td>
                            <td><input type="text" name="email" value="${user.email}" /></td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td><input type="password" name="password" value="${user.password}" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <button type="submit" name="submit">Sign in</button>
                                <a href="${pageContext.request.contextPath}/registration">Register</a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
