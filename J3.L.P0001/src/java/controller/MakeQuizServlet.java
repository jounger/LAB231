/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Answer;
import model.Question;
import model.User;
import utils.SecurityStore;

/**
 *
 * @author nguyenvanan
 */
@WebServlet(name = "MakeQuizServlet", urlPatterns = {"/make-quiz"})
public class MakeQuizServlet extends HttpServlet {

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
        String[] correctAnswers = request.getParameterValues("answers");

        User currentUser = SecurityStore.getAuth(request.getSession());
        List<Answer> answers = new ArrayList<>();
        Answer ans1 = new Answer();
        ans1.setContent(option1);

        Answer ans2 = new Answer();
        ans2.setContent(option2);

        Answer ans3 = new Answer();
        ans3.setContent(option3);

        Answer ans4 = new Answer();
        ans4.setContent(option4);
        // Add to answers list
        answers.add(ans1);
        answers.add(ans2);
        answers.add(ans3);
        answers.add(ans4);

        for (String ans : correctAnswers) {
            answers.get(Integer.parseInt(ans)).setCorrect(true);
        }

        Question q = new Question();
        q.setContent(question);
        q.setAnswers(answers);
        q.setUser(currentUser);

        request.setAttribute("question", q);

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/make-quiz.jsp").forward(request, response);
    }

}
