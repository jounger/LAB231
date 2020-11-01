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
        <script><%@include file="/static/script/home.js"%></script>
    </head>
    <body>
        <div class="main">
            <jsp:include page="../fragments/menu.jsp" />
            <div class="content">
                <form method="POST" action="${pageContext.request.contextPath}/home">
                    <table border="0">
                        <tr>
                            <td><input type="radio" name="flight-type" value="round-trip" ${(flightType == null || flightType == "round-trip") ? "checked": ""}/>Round Trip</td>
                            <td><input type="radio" name="flight-type" value="one-way" ${flightType == "one-way" ? "checked": ""} />One Way</td>
                        </tr>
                        <tr>
                            <td>From</td>
                            <td>
                                <select name="fromPlace">
                                    <c:forEach items="${fromPlaces}" var="item">
                                        <option value="${item}" ${fromPlace == item ? "selected" : ""}>${item}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>To</td>
                            <td>
                                <select name="toPlace">
                                    <c:forEach items="${toPlaces}" var="item">
                                        <option value="${item}" ${toPlace == item ? "selected" : ""}>${item}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Departure</td>
                            <td><input type="date" name="departureDate" value="${departureDate}"/></td>
                        </tr>
                        <tr id="return-date">
                            <td>Return</td>
                            <td><input type="date" name="returnDate" value="${returnDate}"/></td>
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
