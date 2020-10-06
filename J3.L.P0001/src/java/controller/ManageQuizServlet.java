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
import dao.impl.QuestionDAOImpl;
import model.User;
import utils.SecurityStore;

/**
 *
 * @author nguyenvanan
 */
@WebServlet(name = "ManageQuizServlet", urlPatterns = {"/manage-quiz"})
public class ManageQuizServlet extends HttpServlet {

    private final QuestionDAOImpl questionDAOImpl = new QuestionDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        int pageReq = page == null ? 1 : Integer.parseInt(page);
        int limitReq = limit == null ? 5 : Integer.parseInt(limit);

        User user = SecurityStore.getAuth(request.getSession());
        List<Question> questions = questionDAOImpl.findByUser(pageReq, limitReq, user.getId());
        
        int totalElements = questionDAOImpl.countByUser(user.getId());
        
        int totalPages = (int) Math.ceil((double) totalElements / (double)limitReq);

        request.setAttribute("totalElements", totalElements);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("page", pageReq);
        request.setAttribute("limit", limitReq);
        request.setAttribute("questions", questions);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/manage-quiz.jsp").forward(request, response);
    }

}
