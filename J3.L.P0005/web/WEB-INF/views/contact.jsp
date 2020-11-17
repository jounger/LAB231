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
        <style><%@include file="/static/css/contact.css"%></style>
        <script><%@include file="/static/js/main.js"%></script>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Nova+Mono&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="main">
            <jsp:include page="../fragments/header.jsp" />
            <jsp:include page="../fragments/menu.jsp" />
            <div class="container">
                <div class="article">
                    <form method="POST" action="${pageContext.request.contextPath}/contact" class="text">
                        <fieldset>
                         <legend class="info">All fields are required</legend>
                         <table border="0">
                             <tbody>
                                 <tr>
                                     <td>Name:</td>
                                     <td><input type="text" name="name" value="${name}" /></td>
                                 </tr>
                                 <tr>
                                     <td>Email:</td>
                                     <td><input type="email" name="email" value="${email}" /></td>
                                 </tr>
                                 <tr>
                                     <td>Phone:</td>
                                     <td><input type="text" name="phone" value="${phone}" /></td>
                                 </tr>
                                 <tr>
                                     <td>Company:</td>
                                     <td><input type="text" name="company" value="${company}" /></td>
                                 </tr>
                                 <tr>
                                     <td>Message:</td>
                                     <td><textarea rows="3" cols="17" name="message">${message}</textarea></td>
                                 </tr>
                                 <tr>
                                     <td></td>
                                     <td><button type="submit">send</button></td>
                                 </tr>
                             </tbody>
                         </table>
                        </fieldset>
                   </form>
                </div>
                <jsp:include page="../fragments/sidebar.jsp" />
            </div>
        </div>
    </body>
</html>
