/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nguyenvanan
 */
@WebServlet(name = "ManageQuizServlet", urlPatterns = {"/manage-quiz"})
public class ManageQuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/manage-quiz.jsp").forward(request, response);
    }

}
