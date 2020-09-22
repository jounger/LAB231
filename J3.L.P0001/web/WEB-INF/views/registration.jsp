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
        <style><%@include file="/WEB-INF/resources/css/register.css"%></style>
    </head>
    <body>
        <jsp:include page="../fragments/menu.jsp"></jsp:include>
            <h1>Registration Form</h1>
            <form method="POST" action="${pageContext.request.contextPath}/registration">
            <table border="0">
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" value="${user.username}" /><small>${errorMessage}</small></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" value="${user.password}" /></td>
                </tr>
                <tr>
                    <td>User Role:</td>
                    <td style="width: 100px">
                        <select name="role_id" class="capitalize">
                            <c:if test="${not empty roles}">
                                <c:forEach items="${roles}" var="item">
                                    <option value="${item.id}" ${user.roles[0].id == item.id ? 'selected' : ''}>${fn:toLowerCase(item.name)}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="email" name="email" value="${user.email}" /></td>
                </tr>
            </table>
            <button type="submit" name="registration">Submit</button>
        </form>

    </body>
</html>
