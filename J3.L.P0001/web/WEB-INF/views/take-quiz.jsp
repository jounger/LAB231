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
        <p>Welcome ${sessionScope.AUTH_USER.username}</p>
        <p>Enter number of questions:</p>
        <form method="POST" action="${pageContext.request.contextPath}/take-quiz">
            <input type="number" name="quantity" value="${quiz.quantity}" /> ${ERROR_MESSAGE}
            <button type="submit" name="submit">Start</button>
        </form>
    </body>
</html>
