<%-- 
    Document   : menu
    Created on : Sep 21, 2020, 6:15:41 PM
    Author     : nguyenvanan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <a href="${pageContext.request.contextPath}/home">Home</a>
    <a href="${pageContext.request.contextPath}/take-quiz">Take Quiz</a>
    <a href="${pageContext.request.contextPath}/make-quiz">Make Quiz</a>
    <a href="${pageContext.request.contextPath}/manage-quiz">Manage Quiz</a>
    <c:if test="${not empty sessionScope.AUTH_USER.username}">
        <a href="${pageContext.request.contextPath}/logout">Logout</a> | 
        <a>User: ${sessionScope.AUTH_USER.username}</a>
    </c:if>
</div>