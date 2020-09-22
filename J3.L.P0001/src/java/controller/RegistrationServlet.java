/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Roles;
import model.Users;
import service.impl.RolesServiceImpl;
import service.impl.UsersServiceImpl;

/**
 *
 * @author nguyenvanan
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    private final RolesServiceImpl rolesServiceImpl = new RolesServiceImpl();
    private final UsersServiceImpl usersServiceImpl = new UsersServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Roles> roles = rolesServiceImpl.getRoles(1, 10);

        request.setAttribute("roles", roles);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role_id = request.getParameter("role_id");

        Roles role = rolesServiceImpl.getRoleById(Integer.parseInt(role_id));
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(Arrays.asList(role));
        user.setEmail(email);

        usersServiceImpl.createUser(user);

        request.setAttribute("user", user);

        List<Roles> roles = rolesServiceImpl.getRoles(1, 10);
        request.setAttribute("roles", roles);

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
    }

}
