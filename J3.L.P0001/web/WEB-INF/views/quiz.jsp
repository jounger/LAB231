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
    </head>
    <body>
        <div class="main">
            <jsp:include page="../fragments/menu.jsp"></jsp:include>
            <p>Welcome ${sessionScope.AUTH_USER.username}</p>
            <p>Time remaining: <span style="color:red">10:00</span></p>
            <form method="POST" action="${pageContext.request.contextPath}/take-quiz?quiz_id=${quiz.id}">
                <h5>${question.content}</h5>
                <input type="checkbox" name="answers" value="${question.options[0].id}" /> ${question.options[0].content} <br>
                <input type="checkbox" name="answers" value="${question.options[1].id}" /> ${question.options[1].content} <br>
                <input type="checkbox" name="answers" value="${question.options[2].id}" /> ${question.options[2].content} <br>
                <input type="checkbox" name="answers" value="${question.options[3].id}" /> ${question.options[3].content} <br>

                <button type="submit" name="submit">Next</button>
            </form>
        </div>
    </body>
</html>
