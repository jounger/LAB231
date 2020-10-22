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
                <form method="POST" action="${pageContext.request.contextPath}/booking">
                    <h2>1. Select Departing Flight</h2>
                    <h3>${fromPlace} to ${toPlace}</h3>
                    <fmt:formatDate type="date" value="${departureDate}" pattern="dd/MM/yyyy" />

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
                            <c:forEach items="departingFlights" var="item">
                                <tr>
                                    <td><fmt:formatDate type="time" value="${item.departureTime}" timeStyle="short" /></td>
                                    <td><fmt:formatDate type="time" value="${item.arrivalTime}" timeStyle="short" /></td>
                                    <td>${item.flightDetail}</td>
                                    <td><label>$</label>${item.price}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <h2>2. Select Return Flight</h2>
                    <h3>${toPlace} to ${fromPlace}</h3>
                    <fmt:formatDate type="date" value="${returnDate}" pattern="dd/MM/yyyy" />

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
                            <c:forEach items="returnFlights" var="item">
                                <tr>
                                    <td><fmt:formatDate type="time" value="${item.departureTime}" timeStyle="short" /></td>
                                    <td><fmt:formatDate type="time" value="${item.arrivalTime}" timeStyle="short" /></td>
                                    <td>${item.flightDetail}</td>
                                    <td><label>$</label>${item.price}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
