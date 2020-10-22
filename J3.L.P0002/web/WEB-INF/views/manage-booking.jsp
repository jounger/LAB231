<%-- 
    Document   : manage-quiz
    Created on : Sep 21, 2020, 8:20:03 PM
    Author     : nguyenvanan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Quiz Page</title>
        <style><%@include file="/static/css/main.css"%></style>
    </head>
    <body>
        <div class="main">
            <jsp:include page="../fragments/menu.jsp" />
            <div class="content">
                <p>Number of questions: <label class="highlight">${totalElements}</label></p>
                <form method="GET" action="${pageContext.request.contextPath}/manage-booking">

                    <h3>eTicket Receipt</h3>
                    <p>Prepared For</p>
                    <p>${booking.user.firstname}, ${booking.user.lastname}</p>
                    <p>RESERVATION CODE: ${booking.reservationCode}</p>
                    <p>TICKET ISSUE DATE: <fmt:formatDate type="both" value="${booking.ticketIssueDate}" pattern="dd/MM/yyyy hh:mm:ss" /></p>

                    <h3>Itinerary Detail</h3>
                    <table border="0">
                        <thead>
                            <tr>
                                <th>TRAVEL DATE</th>
                                <th>DEPARTURE</th>
                                <th>ARRIVAL</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><fmt:formatDate type="both" value="${booking.flight.departureTime}" pattern="dd/MM/yyyy hh:mm:ss" /></td>
                                <td>${booking.from}</td>
                                <td>${booking.to}</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>Time: <fmt:formatDate type="time" value="${booking.flight.departureTime}" timeStyle="short" /></td>
                                <td>Time: <fmt:formatDate type="time" value="${booking.flight.arrivalTime}" timeStyle="short" /></td>
                            </tr>
                        </tbody>
                    </table>

                </form>
            </div>
        </div>
    </body>
</html>
