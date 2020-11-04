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
        <style><%@include file="/static/css/home.css"%></style>
        <script><%@include file="/static/script/main.js"%></script>
    </head>
    <body>
        <div class="main">
            <jsp:include page="../fragments/menu.jsp" />
            <div class="content">
                
                <div class="side-bar">
                    <h3>Digital News</h3>
                    <p>New lastest intro content</p>

                    <h3>Search</h3>
                    <form action="${pageContext.request.contextPath}/article-detail">
                        <input name="search-title" />
                    </form>

                    <h3>Last Articles</h3>
                    <ul>
                        <c:forEach items="${articlesLast}" var="item">
                            <li><a href="${pageContext.request.contextPath}/article-detail?id=${item.id}">${item.title}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>
