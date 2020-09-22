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
    </head>
    <body>
        <jsp:include page="../fragments/menu.jsp"></jsp:include>
        <h4>Welcome ${sessionScope.LOGGED_USER.username}</h4>
        <h4>Enter number of questions:</h4>
        <form method="POST" action="${pageContext.request.contextPath}/take-quiz">
            <input type="text" name="quantity" value="${quiz.quantity}" />
            <button type="submit" name="submit">Start</button>
        </form>
    </body>
</html>
