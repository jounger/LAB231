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
        <style><%@include file="/static/css/category.css"%></style>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Nova+Mono&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="main">
            <jsp:include page="../fragments/header.jsp" />
            <jsp:include page="../fragments/menu.jsp" />
            <div class="container">
                <div class="article">
                    <c:choose>
                        <c:when test="${not empty articles}">
                            <c:forEach items="${articles}" var="article">
                                <div class="item">
                                    <h2 class="title"><a href="${pageContext.request.contextPath}/home?id=${article.id}">${article.title}</a></h2>
                                    <div class="box">
                                        <img src="${pageContext.request.contextPath}/static/images/${article.image}" />
                                        <div class="text primary">${article.introContent}</div>
                                    </div>
                                </div>
                            </c:forEach>
                            <c:if test="${totalPages >= 2}">
                            <div class="paging">
                                <form method="GET" action="${pageContext.request.contextPath}/category">
                                    <input name="id" value="${categoryId}" class="d-none" />
                                    <c:forEach begin="1" end="${totalPages}" var="item">
                                        <input type="submit" name="page" value="${item}" class="${page == item ? 'active' : ''}" ${ page == item ? 'disabled': '' } />
                                    </c:forEach>
                                </form>
                            </div>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <p>Search does not match any result</p>
                        </c:otherwise>
                    </c:choose>
                </div>
                <jsp:include page="../fragments/sidebar.jsp" />
            </div>
        </div>
    </body>
</html>
