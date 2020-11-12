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
    </head>
    <body>
        <div class="main">
            <jsp:include page="../fragments/header.jsp" />
            <jsp:include page="../fragments/menu.jsp" />
            <div class="content">
                <div class="article">
                    <c:choose>
                        <c:when test="${not empty article}">
                            <h3 class="title">${article.title}</h3>
                            <span class="text title">
                                <fmt:setLocale value="en_US" />
                                <fmt:parseDate type="both" pattern="yyyy-MM-dd'T'HH:mm:ss" value="${article.publishedDate}" var="publishedDateParsed"/>
                                Posted on <fmt:formatDate value="${publishedDateParsed}" pattern="MMM dd, yyyy"/> - Full Article </span>
                            <div class="text">
                                <img src="${pageContext.request.contextPath}/static/images/${article.image}" />
                                ${article.content}
                            </div>
                            <hr class="line">                            
                            <ul>
                                <c:forEach items="${articlesLast}" var="item">
                                    <li class="text"><a href="${pageContext.request.contextPath}/home?id=${item.id}">${item.title}</a></li>
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

            <jsp:include page="../fragments/footer.jsp" />
        </div>
    </body>
</html>
