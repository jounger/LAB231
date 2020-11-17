<%-- 
    Document   : home
    Created on : Sep 23, 2020, 8:17:40 PM
    Author     : nguyenvanan
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <style><%@include file="/static/css/main.css"%></style>
        <style><%@include file="/static/css/home.css"%></style>
        <script><%@include file="/static/js/main.js"%></script>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Nova+Mono&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="main">
            <jsp:include page="../fragments/header.jsp" />
            <jsp:include page="../fragments/menu.jsp" />
            <fmt:setLocale value="en_US" />
            <div class="container">
                <div class="article">
                    <c:choose>
                        <c:when test="${not empty article}">
                            <label class="info fs-italic"><span class="secondary">Category ${article.category.name}</span><span class="dot green"></span>Full Category</label>
                            <h2 class="title">${article.title}</h2>
                            <label class="info fs-italic"><span class="primary">Posted on
                                    <fmt:parseDate type="both" pattern="yyyy-MM-dd'T'HH:mm:ss" value="${article.publishedDate}" var="publishedDateParsed"/>
                                    <fmt:formatDate value="${publishedDateParsed}" pattern="MMMM dd, yyyy"/></span><span class="dot"></span>Full Article </label>
                            <div class="content text primary">
                                <img src="${pageContext.request.contextPath}/static/images/${article.image}" />
                                ${article.content}
                            </div>
                            <hr class="line">                            
                            <ul>
                                <c:forEach items="${articlesLast}" var="item">
                                    <fmt:parseDate type="both" pattern="yyyy-MM-dd'T'HH:mm:ss" value="${article.publishedDate}" var="publishedDateParsedItem"/>
                                    <li class="text"><a href="${pageContext.request.contextPath}/home?id=${item.id}">${item.title}</a>
                                        <span class="fs-italic primary">Posted on <fmt:formatDate value="${publishedDateParsedItem}" pattern="MMMM dd, yyyy"/></span></li>
                                </c:forEach>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <p>We do not have any news</p>
                        </c:otherwise>
                    </c:choose>
                </div>
                <jsp:include page="../fragments/sidebar.jsp" />
            </div>
        </div>
    </body>
</html>
