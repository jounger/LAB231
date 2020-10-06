<%-- 
    Document   : quiz
    Created on : Sep 22, 2020, 11:16:11 PM
    Author     : nguyenvanan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Page</title>
        <style><%@include file="/static/css/main.css"%></style>
        <script><%@include file="/static/script/main.js"%></script>
    </head>
    <body>
        <div class="main">
            <jsp:include page="../fragments/menu.jsp" />
            <div class="content">
                <p>Welcome <label class="highlight">${sessionScope.AUTH_USER.username}</label></p>
                <p>Time remaining: <span class="notice" id="timer">00:00</span></p>
                <form method="POST" action="${pageContext.request.contextPath}/take-quiz?quiz_id=${quiz.id}">
                    <p>${question.content}</p>
                    <input type="checkbox" name="answers" value="${question.options[0].id}" /> ${question.options[0].content} <br>
                    <input type="checkbox" name="answers" value="${question.options[1].id}" /> ${question.options[1].content} <br>
                    <input type="checkbox" name="answers" value="${question.options[2].id}" /> ${question.options[2].content} <br>
                    <input type="checkbox" name="answers" value="${question.options[3].id}" /> ${question.options[3].content} <br>

                    <button type="submit" name="submit" id="submit">Next</button>
                </form>
            </div>
        </div>
    </body>
    <script><%@include file="/static/script/quiz.js"%></script>
</html>
