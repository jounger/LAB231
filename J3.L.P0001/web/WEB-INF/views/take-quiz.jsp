<%-- 
    Document   : take-quiz
    Created on : Sep 21, 2020, 8:19:47 PM
    Author     : nguyenvanan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take Quiz Page</title>
        <style><%@include file="/static/css/main.css"%></style>
        <style><%@include file="/static/css/take-quiz.css"%></style>
        <script><%@include file="/static/script/main.js"%></script>
    </head>
    <body>
        <div class="main">
            <jsp:include page="../fragments/menu.jsp" />
            <div class="content">
                <p>Welcome <label class="highlight">${sessionScope.AUTH_USER.username}</label></p>
                <form method="POST" action="${pageContext.request.contextPath}/take-quiz">
                    <table border="0">
                        <tbody>
                            <tr>
                                <td>Enter number of questions:</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td><input type="number" name="quantity" value="${quantity}" /></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><button type="submit" name="submit">Start</button></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
