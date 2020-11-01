<%-- 
    Document   : search-booking
    Created on : Oct 23, 2020, 12:20:09 AM
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
        <script><%@include file="/static/script/main.js"%></script>
        <script><%@include file="/static/script/search.js"%></script>
    </head>
    <body>
        <div class="main">
            <jsp:include page="../fragments/menu.jsp" />
            <div class="content">
                <form method="GET" action="${pageContext.request.contextPath}/manage-booking">
                    <table border="0">
                        <tr>
                            <td></td>
                            <td>
                                <input type="radio" name="search-type" value="reservation-code" checked/>Reservation code
                                <input type="radio" name="search-type" value="all-booking" />All bookings
                            </td>
                        </tr>
                        <tr id="reservation-code">
                            <td>Enter Reservation code</td>
                            <td><input type="text" name="reservation-code" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" name="submit" value="Search" /></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
