<%-- 
    Document   : menu
    Created on : Sep 21, 2020, 6:15:41 PM
    Author     : nguyenvanan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="menu">
    <div class="top-menu">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/static/images/logo.png" class="logo-img"/>
            <p class="logo-text">Fast, Frequent & Safe Flights</p>
        </div>
        <div class="route">
            <c:choose>
                <c:when test="${empty sessionScope.AUTH_USER}">
                    <a href="${pageContext.request.contextPath}/login">
                        <img src="${pageContext.request.contextPath}/static/images/home.png"/>
                        <p>Login</p>
                    </a>
                    <a href="${pageContext.request.contextPath}/registration">
                        <img src="${pageContext.request.contextPath}/static/images/register_t.png"/>
                        <p>Register</p>
                    </a>
                </c:when>
                <c:otherwise>
                    <a>
                        <img src="${pageContext.request.contextPath}/static/images/home.png"/>
                        <p>${sessionScope.AUTH_USER.firstname}</p>
                    </a>
                    <a href="${pageContext.request.contextPath}/logout">
                        <img src="${pageContext.request.contextPath}/static/images/quit_t.png"/>
                        <p>Logout</p>
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="main-menu">
        <a href="${pageContext.request.contextPath}/home"><button>Home</button></a>
        <a href="${pageContext.request.contextPath}/booking"><button>Book</button></a>
        <a href="${pageContext.request.contextPath}/manage-booking"><button>Manage Booking</button></a>
    </div>
</div>
