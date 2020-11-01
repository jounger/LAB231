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
                <h3 class="title">Registration Form</h3>
                <form method="POST" action="${pageContext.request.contextPath}/registration">
                    <table border="0">
                        <thead>
                            <tr>
                                <th>New Account</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Email:</td>
                                <td><input type="email" name="email" value="${email}" /></td>
                            </tr>
                            <tr>
                                <td>Password:</td>
                                <td><input type="password" name="password" value="${password}" /></td>
                            </tr>
                            <tr>
                                <td>Verify Password:</td>
                                <td><input type="password" name="re-password" value="${rePassword}" /></td>
                            </tr>
                        </tbody>
                        <thead>
                            <tr>
                                <th>Contact Information</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>First Name:</td>
                                <td><input type="text" name="firstname" value="${firstname}" /></td>
                            </tr>
                            <tr>
                                <td>Last Name:</td>
                                <td><input type="text" name="lastname" value="${lastname}" /></td>
                            </tr>
                            <tr>
                                <td>Address:</td>
                                <td><input type="text" name="address" value="${address}" /></td>
                            </tr>
                            <tr>
                                <td>Phone:</td>
                                <td><input type="text" name="phone" value="${phone}" /></td>
                            </tr>
                            <tr>
                                <td>Sex:</td>
                                <td>
                                    <select name="sex">
                                        <option value="0" ${sex == 0 ? "checked": ""}>Male</option>
                                        <option value="1" ${sex == 1 ? "checked": ""}>Female</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Age:</td>
                                <td><input type="text" name="age" value="${age}" /></td>
                            </tr>
                            <tr>
                                <td>Card Number:</td>
                                <td><input type="text" name="card-number" value="${cardNumber}" /></td>
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
