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
        <p>Welcome ${sessionScope.AUTH_USER.username}</p>
        <p>Time remaining: <span style="color:red">10:00</span></p>
        <form method="POST" action="${pageContext.request.contextPath}/take-quiz">
            <h5>${question.content}</h5>
            <input type="checkbox" name="answers" value="${question.options[0].id}" /> ${question.options[0].content}
            <input type="checkbox" name="answers" value="${question.options[1].id}" /> ${question.options[1].content}
            <input type="checkbox" name="answers" value="${question.options[2].id}" /> ${question.options[2].content}
            <input type="checkbox" name="answers" value="${question.options[3].id}" /> ${question.options[3].content}

            <button type="submit" name="submit">Next</button>
        </form>
    </body>
</html>
