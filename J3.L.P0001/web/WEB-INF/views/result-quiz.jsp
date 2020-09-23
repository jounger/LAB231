<%-- 
    Document   : result-quiz
    Created on : Sep 22, 2020, 11:40:46 PM
    Author     : nguyenvanan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Quiz Page</title>
    </head>
    <body>
        <jsp:include page="../fragments/menu.jsp"></jsp:include>
        <p>Your score ${quiz.score} (${quiz.score * 10}%) - ${quiz.score >= 4 ? 'Passed' : 'Failed'}</p>
        <p>Take another test</p>
        <form action="${pageContext.request.contextPath}/take-quiz">
            <input type="submit" value="Start" />
        </form>
    </body>
</html>
