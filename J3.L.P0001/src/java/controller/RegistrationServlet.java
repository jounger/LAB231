/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Constant;
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
import dao.impl.RoleDAOImpl;
import dao.impl.UserDAOImpl;
import utils.Tool;

/**
 *
 * @author nguyenvanan
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    private final RoleDAOImpl rolesDAOImpl = new RoleDAOImpl();
    private final UserDAOImpl usersDAOImpl = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role_id = request.getParameter("role_id");
        
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.setAttribute("email", email);
        request.setAttribute("role_id", role_id);

        if (!Tool.isNull(username, password, email, role_id)) {
            User isExist = usersDAOImpl.findByUsername(username);
            if(isExist != null) {
                request.setAttribute(Constant.ERROR_MESSAGE_ATTR, "User has been exist");
            } else {
                Role role = rolesDAOImpl.findById(Integer.parseInt(role_id));
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setRoles(Arrays.asList(role));
                user.setEmail(email);

                usersDAOImpl.save(user);
                request.setAttribute(Constant.SUCCESS_MESSAGE_ATTR, "Successful register new account");
            }
        } else {
            request.setAttribute(Constant.ERROR_MESSAGE_ATTR, "You must fulfill all the field");
        }
        processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Role> roles = rolesDAOImpl.findAll();
        request.setAttribute("roles", roles);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
    }

}
