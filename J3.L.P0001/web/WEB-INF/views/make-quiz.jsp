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
                    <td><textarea name="question" value="${question.content}" rows="5" cols="50"></textarea></td>
                </tr>
                <tr>
                    <td>Option1:</td>
                    <td><textarea name="option1" value="${answers[0].content}" rows="4" cols="50"></textarea></td>
                </tr>
                <tr>
                    <td>Option2:</td>
                    <td><textarea name="option2" value="${answers[1].content}" rows="4" cols="50"></textarea></td>
                </tr>
                <tr>
                    <td>Option3:</td>
                    <td><textarea name="option3" value="${answers[2].content}" rows="4" cols="50"></textarea></td>
                </tr>
                <tr>
                    <td>Option4:</td>
                    <td><textarea name="option4" value="${answers[3].content}" rows="4" cols="50"></textarea></td>
                </tr>
                <tr>
                    <td>Answer(s):</td>
                    <td>
                        <input type="checkbox" name="answers" value="1" ${answers[0].isCorrect ? 'checked': ''}> Option 1
                        <input type="checkbox" name="answers" value="2" ${answers[1].isCorrect ? 'checked': ''}> Option 2
                        <input type="checkbox" name="answers" value="3" ${answers[2].isCorrect ? 'checked': ''}> Option 3
                        <input type="checkbox" name="answers" value="4" ${answers[3].isCorrect ? 'checked': ''}> Option 4
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><button type="submit" name="submit">Save</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
