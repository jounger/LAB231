<%-- 
    Document   : take-quiz
    Created on : Sep 21, 2020, 8:19:47 PM
    Author     : nguyenvanan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take Quiz Page</title>
        <style><%@include file="/static/css/main.css"%></style>
        <script><%@include file="/static/script/main.js"%></script>
    </head>
    <body>
        <div class="main">
            <jsp:include page="../fragments/menu.jsp" />
            <div class="content">
                <fmt:setLocale value="en_US" />
                <form method="POST" action="${pageContext.request.contextPath}/booking">
                    <h2>1. Select Departing Flight</h2>
                    <h3>${fromPlace} to ${toPlace}</h3>
                    <fmt:parseDate type="both" pattern="yyyy-MM-dd" value="${departureDate}" var="departureDateParsed"/>
                    <fmt:formatDate type="date" value="${departureDateParsed}" pattern="dd/MM/yyyy" />

                    <table border="0">
                        <thead>
                            <tr>
                                <td>Departs</td>
                                <td>Arrives</td>
                                <td>Flight Detail</td>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${departingFlights}" var="item">
                                <tr>
                                    <td>
                                        <fmt:parseDate type="both" pattern="yyyy-MM-dd'T'HH:mm:ss" value="${item.departureTime}" var="departureTimeParsed"/>
                                        <fmt:formatDate type="time" value="${departureTimeParsed}" timeStyle="short" />
                                    </td>
                                    <td>
                                        <fmt:parseDate type="both" pattern="yyyy-MM-dd'T'HH:mm:ss" value="${item.arrivalTime}" var="arrivalTimeParsed"/>
                                        <fmt:formatDate type="time" value="${arrivalTimeParsed}" timeStyle="short" />
                                    </td>
                                    <td>${Integer.valueOf(Math.floor(item.flightDetail))}h${Integer.valueOf(item.flightDetail*60%60)}</td>
                                    <td><input type="radio" name="departure-flight-id" value="${item.id}" />$${item.price}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <%--<c:forEach begin="1" end="${totalDepartingFlightPages}" var="item">
                        <input type="submit" name="page" value="${item}" />
                    </c:forEach>--%>

                    <h2>2. Select Return Flight</h2>
                    <h3>${toPlace} to ${fromPlace}</h3>
                    <fmt:parseDate type="both" pattern="yyyy-MM-dd" value="${returnDate}" var="returnDateParsed"/>
                    <fmt:formatDate type="date" value="${returnDateParsed}" pattern="dd/MM/yyyy" />

                    <table border="0">
                        <thead>
                            <tr>
                                <td>Departs</td>
                                <td>Arrives</td>
                                <td>Flight Detail</td>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${returnFlights}" var="item">
                                <tr>
                                    <td>
                                        <fmt:parseDate type="both" pattern="yyyy-MM-dd'T'HH:mm:ss" value="${item.departureTime}" var="departureTimeParsed"/>
                                        <fmt:formatDate type="time" value="${departureTimeParsed}" timeStyle="short" />
                                    </td>
                                    <td>
                                        <fmt:parseDate type="both" pattern="yyyy-MM-dd'T'HH:mm:ss" value="${item.arrivalTime}" var="arrivalTimeParsed"/>
                                        <fmt:formatDate type="time" value="${arrivalTimeParsed}" timeStyle="short" />
                                    </td>
                                    <td>${Integer.valueOf(Math.floor(item.flightDetail))}h${Integer.valueOf(item.flightDetail*60%60)}</td>
                                    <td><input type="radio" name="return-flight-id" value="${item.id}" />$${item.price}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
