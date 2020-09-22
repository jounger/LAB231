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
    </head>
    <body>
        <jsp:include page="../fragments/menu.jsp"></jsp:include>
        <h4>Welcome ${sessionScope.LOGGED_USER.username}</h4>
        <h4>Time remaining: <span style="color:red">10:00</span></h4>
        <form method="POST" action="${pageContext.request.contextPath}/quiz?qa=${qa.id}">
            <h5>${question.content}</h5>
            <input type="checkbox" name="answers" value="${question.answers[0].id}" /> ${question.answers[0].content}
            <input type="checkbox" name="answers" value="${question.answers[1].id}" /> ${question.answers[1].content}
            <input type="checkbox" name="answers" value="${question.answers[2].id}" /> ${question.answers[2].content}
            <input type="checkbox" name="answers" value="${question.answers[3].id}" /> ${question.answers[3].content}

            <button type="submit" name="submit">Next</button>
        </form>
    </body>
</html>
