<%-- 
    Document   : home
    Created on : Sep 23, 2020, 8:17:40 PM
    Author     : nguyenvanan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <jsp:include page="../fragments/menu.jsp"></jsp:include>
        <c:choose>
            <c:when test="${not empty sessionScope.AUTH_USER.username}">
                <p>Welcome ${sessionScope.AUTH_USER.username}</p>
            </c:when>
            <c:otherwise>
                <p>You're not logged in</p>
            </c:otherwise>
        </c:choose>
    </body>
</html>
