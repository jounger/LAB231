<%-- 
    Document   : registration
    Created on : Sep 21, 2020, 6:14:04 PM
    Author     : nguyenvanan
--%>

<%@page  language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
        <style><%@include file="/static/css/main.css"%></style>
        <script><%@include file="/static/script/main.js"%></script>
    </head>
    <body>
        <div class="main">
            <jsp:include page="../fragments/menu.jsp" />
            <div class="content">
                
                <form method="POST" action="${pageContext.request.contextPath}/registration">
                    <table border="0">
                        <thead>
                            <tr>
                                <td><h4 class="title">Registration Form</h4></td>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                            <td>User Name:</td>
                            <td><input type="text" name="username" value="${username}" /></td>
                            </tr>
                            <tr>
                                <td>Password:</td>
                                <td><input type="password" name="password" value="${password}" /></td>
                            </tr>
                            <tr>
                                <td>User Type:</td>
                                <td style="width: 100px">
                                    <select name="role_id" class="capitalize">
                                        <c:if test="${not empty roles}">
                                            <c:forEach items="${roles}" var="item">
                                                <option value="${item.id}" ${role_id == item.id ? 'selected' : ''}>${fn:toLowerCase(item.name)}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Email:</td>
                                <td><input type="email" name="email" value="${email}" /></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><button type="submit" name="submit">Register</button></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
