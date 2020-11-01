/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Constant;
import dao.impl.UserDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import utils.SecurityStore;
import utils.Tool;

/**
 *
 * @author nguyenvanan
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private final UserDAOImpl userDAOImpl = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        request.setAttribute("email", email);
        request.setAttribute("password", password);
        
        if (!Tool.isNull(email, password)) {
            User user = userDAOImpl.findByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                System.out.println("Login Success");
                SecurityStore.saveAuth(request.getSession(), user);
                response.sendRedirect(this.getServletContext().getContextPath() + "/home");
                return;
            } else {
                request.setAttribute(Constant.ERROR_MESSAGE_ATTR, "Username or password is not correct");
            }
        } else {
            request.setAttribute(Constant.ERROR_MESSAGE_ATTR, "You must fulfill all the field");
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
}
