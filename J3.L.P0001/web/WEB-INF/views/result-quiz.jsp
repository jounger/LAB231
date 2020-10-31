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
        <style><%@include file="/static/css/main.css"%></style>
    </head>
    <body>
        <div class="main">
            <jsp:include page="../fragments/menu.jsp" />
            <div class="content">
                <p>Your score <label class="highlight">${quiz.score * 10} (${quiz.score * 100}%) - ${quiz.score >= 0.4 ? 'Passed' : 'Failed'}</label></p>
                <p>Take another test <a href="${pageContext.request.contextPath}/take-quiz"><button>Start</button></a></p>
            </div>
        </div>
    </body>
</html>
