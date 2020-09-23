/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Question;
import service.impl.QuestionServiceImpl;

/**
 *
 * @author nguyenvanan
 */
@WebServlet(name = "ManageQuizServlet", urlPatterns = {"/manage-quiz"})
public class ManageQuizServlet extends HttpServlet {

    private final QuestionServiceImpl questionServiceImpl = new QuestionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        List<Question> questions = questionServiceImpl.getQuestions(Integer.parseInt(page), Integer.parseInt(limit));
        request.setAttribute("questions", questions);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/manage-quiz.jsp").forward(request, response);
    }

}
