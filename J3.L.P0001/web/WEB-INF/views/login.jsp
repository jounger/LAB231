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
    </head>
    <body>
        <jsp:include page="../fragments/menu.jsp"></jsp:include>
            <h1>Login Form</h1>
            <form method="POST" action="${pageContext.request.contextPath}/login">
            <table border="0">
                <tr>
                    <td>Username:</td>
                    <td>
                        <input type="text" name="username" value="${user.username}" />
                        <c:if test="${not empty errorMessage}">
                            <c:out value="${errorMessage}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" value="${user.password}" /></td>
                </tr>
            </table>
            <button type="submit" name="login">Submit</button>
        </form>
    </body>
</html>
