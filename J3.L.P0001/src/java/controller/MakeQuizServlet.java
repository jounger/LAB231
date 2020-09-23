/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Option;
import model.Question;
import model.User;
import service.impl.QuestionServiceImpl;
import utils.SecurityStore;

/**
 *
 * @author nguyenvanan
 */
@WebServlet(name = "MakeQuizServlet", urlPatterns = {"/make-quiz"})
public class MakeQuizServlet extends HttpServlet {
    
    private final QuestionServiceImpl questionServiceImpl = new QuestionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/make-quiz.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String question = request.getParameter("question");
        String option1 = request.getParameter("option1");
        String option2 = request.getParameter("option2");
        String option3 = request.getParameter("option3");
        String option4 = request.getParameter("option4");
        String[] answers = request.getParameterValues("answers");

        User currentUser = SecurityStore.getAuth(request.getSession());
        List<Option> options = new ArrayList<>();
        Option opt1 = new Option();
        opt1.setContent(option1);

        Option opt2 = new Option();
        opt2.setContent(option2);

        Option opt3 = new Option();
        opt3.setContent(option3);

        Option opt4 = new Option();
        opt4.setContent(option4);
        // Add to answers list
        options.add(opt1);
        options.add(opt2);
        options.add(opt3);
        options.add(opt4);

        for (String ans : answers) {
            options.get(Integer.parseInt(ans)).setCorrect(true);
        }

        Question q = new Question();
        q.setContent(question);
        q.setOptions(options);
        q.setUser(currentUser);
        
        questionServiceImpl.createQuestion(q);

        request.setAttribute("question", q);
        request.setAttribute("message", "CREATE QUESTION SUCCESSFUL!");

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/make-quiz.jsp").forward(request, response);
    }

}
