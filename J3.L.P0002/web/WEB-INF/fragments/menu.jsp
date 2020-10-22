<%-- 
    Document   : menu
    Created on : Sep 21, 2020, 6:15:41 PM
    Author     : nguyenvanan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="top-menu">
    <c:choose>
        <c:when test="${empty sessionScope.AUTH_USER}">
            <a href="${pageContext.request.contextPath}/login">Login</a>
            <a href="${pageContext.request.contextPath}/registration">Register</a>
        </c:when>
        <c:otherwise>
            <a >${sessionScope.AUTH_USER.firstname}</a>
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </c:otherwise>
    </c:choose>
</div>
<div class="menu">
    <a href="${pageContext.request.contextPath}/home">Home</a>
    <a href="${pageContext.request.contextPath}/booking">Book</a>
    <a href="${pageContext.request.contextPath}/manage-quiz">Manage Booking</a>
</div>