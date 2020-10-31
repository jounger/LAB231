/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Constant;
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
import dao.impl.QuestionDAOImpl;
import utils.SecurityStore;
import utils.Tool;

/**
 *
 * @author nguyenvanan
 */
@WebServlet(name = "MakeQuizServlet", urlPatterns = {"/make-quiz"})
public class MakeQuizServlet extends HttpServlet {

    private final QuestionDAOImpl questionDAOImpl = new QuestionDAOImpl();

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
        
        request.setAttribute("question", question);
        request.setAttribute("option1", option1);
        request.setAttribute("option2", option2);
        request.setAttribute("option3", option3);
        request.setAttribute("option4", option4);
        request.setAttribute("answers", answers);

        if (!Tool.isNull(question, option1, option2, option3, option4) && !Tool.isNull(answers)) {
            boolean isExist = questionDAOImpl.isExist(question);
            if(isExist) {
                request.setAttribute(Constant.ERROR_MESSAGE_ATTR, "Question has been exist");
            } else {
                User currentUser = SecurityStore.getAuth(request.getSession());
                List<Option> options = new ArrayList<>();
                Question q = new Question();
                q.setContent(question);

                Option opt1 = new Option();
                Option opt2 = new Option();
                Option opt3 = new Option();
                Option opt4 = new Option();

                opt1.setContent(option1);
                options.add(opt1);
                opt2.setContent(option2);
                options.add(opt2);
                opt3.setContent(option3);
                options.add(opt3);
                opt4.setContent(option4);
                options.add(opt4);
                for (String ans : answers) {
                    options.get(Integer.parseInt(ans)).setCorrect(true);
                }
                q.setOptions(options);
                q.setUser(currentUser);

                questionDAOImpl.save(q);
                request.setAttribute(Constant.SUCCESS_MESSAGE_ATTR, "Create success full new question");
            }
        } else {
            request.setAttribute(Constant.ERROR_MESSAGE_ATTR, "You must fulfill question & options");
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/make-quiz.jsp").forward(request, response);
    }

}
