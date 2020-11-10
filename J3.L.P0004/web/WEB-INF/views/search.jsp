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
        <style><%@include file="/static/css/search.css"%></style>
    </head>
    <body>
        <div class="main">
            <jsp:include page="../fragments/preheader.jsp" />
            <jsp:include page="../fragments/header.jsp" />
            <jsp:include page="../fragments/menu.jsp" />
            <div class="content">
                <div class="article">
                    <c:choose>
                        <c:when test="${not empty articles}">
                            <c:forEach items="${articles}" var="article">
                                <h3 class="title"><a href="${pageContext.request.contextPath}/home?id=${article.id}">${article.title}</a></h3>
                                <div class="box">
                                    <img src="${pageContext.request.contextPath}/static/images/${article.image}" />
                                    <div class="text">${article.introContent}</div>
                                </div>
                            </c:forEach>
                            <c:if test="${totalPages >= 2}">
                            <div class="paging">
                                <c:forEach begin="1" end="${totalPages}" var="item">
                                    <input type="submit" name="page" value="${item}" class="${page == item ? 'active' : ''}"/>
                                </c:forEach>
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

            <jsp:include page="../fragments/footer.jsp" />
        </div>
    </body>
</html>
