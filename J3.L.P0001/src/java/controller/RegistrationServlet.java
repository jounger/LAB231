/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Role;
import model.User;
import service.impl.RoleServiceImpl;
import service.impl.UserServiceImpl;

/**
 *
 * @author nguyenvanan
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    private final RoleServiceImpl rolesServiceImpl = new RoleServiceImpl();
    private final UserServiceImpl usersServiceImpl = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Role> roles = rolesServiceImpl.getRoles(1, 10);

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

        Role role = rolesServiceImpl.getRoleById(Integer.parseInt(role_id));
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(Arrays.asList(role));
        user.setEmail(email);

        usersServiceImpl.createUser(user);

        request.setAttribute("user", user);

        List<Role> roles = rolesServiceImpl.getRoles(1, 10);
        request.setAttribute("roles", roles);

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
    }

}
