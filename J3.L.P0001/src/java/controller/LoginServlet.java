/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.impl.UserDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import utils.SecurityStore;

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null) {
            User user = userDAOImpl.findByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                System.out.println("Login Success");
                SecurityStore.saveAuth(request.getSession(), user);
                request.setAttribute("user", user);
            } else {
                System.out.println("Login Fail");
            }
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
}
