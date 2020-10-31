<%-- 
    Document   : manage-quiz
    Created on : Sep 21, 2020, 8:20:03 PM
    Author     : nguyenvanan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Quiz Page</title>
        <style><%@include file="/static/css/main.css"%></style>
        <style><%@include file="/static/css/manage-quiz.css"%></style>
    </head>
    <body>
        <div class="main">
            <jsp:include page="../fragments/menu.jsp" />
            <div class="content">
                <p>Number of questions: <label class="highlight">${totalElements}</label></p>
                <form method="GET" action="${pageContext.request.contextPath}/manage-quiz">

                    <table border="0">
                        <thead>
                            <tr class="highlight">
                                <th>Question</th>
                                <th>DateCreated</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:if test="${not empty questions}">
                                <c:forEach items="${questions}" var="item">
                                    <tr>
                                        <td>${item.content}</td>
                                        <td>
                                            <fmt:setLocale value="en_US" />
                                            <fmt:formatDate value="${item.dateCreated}" pattern="dd-MMM-yyyy" />
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td>
                                    <c:forEach begin="1" end="${totalPages}" var="item">
                                        <input type="submit" name="page" value="${item}" />
                                    </c:forEach>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
