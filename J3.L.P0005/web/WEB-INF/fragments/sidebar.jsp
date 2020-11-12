<%-- 
    Document   : footer
    Created on : Nov 4, 2020, 7:11:41 PM
    Author     : nguyenvanan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="sidebar">
    <div>
        <h3 class="title">Categories</h3>
        <c:if test="${not empty categories}">
            <ul>
                <c:forEach items="${categories}" var="item">
                    <li><a href="${pageContext.request.contextPath}/category?id=${item.id}">${item.name}</a></li>
                </c:forEach>
            </ul>
        </c:if>
    </div>
</div>
