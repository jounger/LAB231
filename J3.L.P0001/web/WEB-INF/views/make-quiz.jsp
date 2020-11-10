<%-- 
    Document   : make-quiz
    Created on : Sep 21, 2020, 8:19:57 PM
    Author     : nguyenvanan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Make Quiz Page</title>
        <style><%@include file="/static/css/main.css"%></style>
        <style><%@include file="/static/css/make-quiz.css"%></style>
        <script><%@include file="/static/script/main.js"%></script>
    </head>
    <body>
        <div class="main">
            <jsp:include page="../fragments/menu.jsp" />
            <div class="content">
                <form method="POST" action="${pageContext.request.contextPath}/make-quiz">
                    <table border="0">
                        <tr>
                            <td>Question:</td>
                            <td><textarea name="question" rows="7" cols="45">${question}</textarea></td>
                        </tr>
                        <tr>
                            <td>Option1:</td>
                            <td><textarea name="option1" rows="3" cols="45">${options1}</textarea></td>
                        </tr>
                        <tr>
                            <td>Option2:</td>
                            <td><textarea name="option2" rows="3" cols="45">${options2}</textarea></td>
                        </tr>
                        <tr>
                            <td>Option3:</td>
                            <td><textarea name="option3" rows="3" cols="45">${options3}</textarea></td>
                        </tr>
                        <tr>
                            <td>Option4:</td>
                            <td><textarea name="option4" rows="3" cols="45">${options4}</textarea></td>
                        </tr>
                        <tr>
                            <td>Answer(s):</td>
                            <td>
                                <input type="checkbox" name="answers" value="0" /> Option 1
                                <input type="checkbox" name="answers" value="1" /> Option 2
                                <input type="checkbox" name="answers" value="2" /> Option 3
                                <input type="checkbox" name="answers" value="3" /> Option 4
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><button type="submit">Save</button></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
