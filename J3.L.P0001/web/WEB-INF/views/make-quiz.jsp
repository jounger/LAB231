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
    </head>
    <body>
        <jsp:include page="../fragments/menu.jsp"></jsp:include>
        <form method="POST" action="${pageContext.request.contextPath}/make-quiz">
            <table border="0">
                <tr>
                    <td>Question</td>
                    <td><textarea name="question" rows="5" cols="50">${question.content}</textarea></td>
                </tr>
                <tr>
                    <td>Option1:</td>
                    <td><textarea name="option1" rows="4" cols="50">${question.options[0].content}</textarea></td>
                </tr>
                <tr>
                    <td>Option2:</td>
                    <td><textarea name="option2" rows="4" cols="50">${question.options[1].content}</textarea></td>
                </tr>
                <tr>
                    <td>Option3:</td>
                    <td><textarea name="option3" rows="4" cols="50">${question.options[2].content}</textarea></td>
                </tr>
                <tr>
                    <td>Option4:</td>
                    <td><textarea name="option4" rows="4" cols="50">${question.options[3].content}</textarea></td>
                </tr>
                <tr>
                    <td>Answer(s):</td>
                    <td>
                        <input type="checkbox" name="answers" value="0" ${question.options[0].correct ? 'checked' : ''} /> Option 1
                        <input type="checkbox" name="answers" value="1" ${question.options[1].correct ? 'checked' : ''} /> Option 2
                        <input type="checkbox" name="answers" value="2" ${question.options[2].correct ? 'checked' : ''} /> Option 3
                        <input type="checkbox" name="answers" value="3" ${question.options[3].correct ? 'checked' : ''} /> Option 4
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><button type="submit" name="submit">Save</button> <span>${message}</span></td>
                </tr>
            </table>
        </form>
    </body>
</html>
