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
        <style><%@include file="/static/css/main.css"%></style>
    </head>
    <body>
        <div class="main">
            <jsp:include page="../fragments/menu.jsp" />
            <div class="content">
                <c:choose>
                    <c:when test="${not empty sessionScope.AUTH_USER.username}">
                        <p>Welcome <label class="highlight">${sessionScope.AUTH_USER.username}</label></p>
                    </c:when>
                    <c:otherwise>
                        <p>You're not logged in</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>
