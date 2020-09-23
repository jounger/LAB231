<%-- 
    Document   : manage-quiz
    Created on : Sep 21, 2020, 8:20:03 PM
    Author     : nguyenvanan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Quiz Page</title>
    </head>
    <body>
        <jsp:include page="../fragments/menu.jsp"></jsp:include>
        <p>Number of questions: ${totalElement}</p>
        <table border="0">
            <thead>
                <tr>
                    <th>Question</th>
                    <th>Date Created</th>
                </tr>
            </thead>
            <tbody>

            <c:if test="${not empty questions}">
                <c:forEach items="${questions}" var="item">
                    <tr>
                        <td>${item.content}</td>
                        <td>${item.dateCreated}</td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>

</body>
</html>
