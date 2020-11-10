<%-- 
    Document   : footer
    Created on : Nov 4, 2020, 7:11:41 PM
    Author     : nguyenvanan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="sidebar">
    <div>
        <h3 class="title">Digital News</h3>
        <c:if test="${not empty articlesLast}">
            <p class="text">${articlesLast[0].introContent}</p>
        </c:if>
    </div>

    <div>
        <h3 class="title">Search</h3>
        <form action="${pageContext.request.contextPath}/search">
            <input name="search-title" class="search-box" value="${searchTitle}" />
            <button type="submit" class="search-btn">Go</button>
        </form>
    </div>

    <div>
        <h3 class="title">Last Articles</h3>
        <ul>
            <c:forEach items="${articlesLast}" var="item">
                <li class="text"><a href="${pageContext.request.contextPath}/home?id=${item.id}">${item.title}</a></li>
            </c:forEach>
        </ul>
    </div>
</div>
