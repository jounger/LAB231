<%-- 
    Document   : menu
    Created on : Sep 21, 2020, 6:15:41 PM
    Author     : nguyenvanan
--%>
<a href="${pageContext.request.contextPath}/">Home</a>
<a href="${pageContext.request.contextPath}/take-quiz">Take Quiz</a>
<a href="${pageContext.request.contextPath}/make-quiz">Make Quiz</a>
<a href="${pageContext.request.contextPath}/manage-quiz">Manage Quiz</a>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
<p>User: ${sessionScope.LOGGED_USER.username}</p>