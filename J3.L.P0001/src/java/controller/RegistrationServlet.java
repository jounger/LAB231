/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.impl.RolesDAOImpl;
import dao.impl.UsersDAOImpl;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
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
import utils.DBConnection;

/**
 *
 * @author nguyenvanan
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    private final RolesDAOImpl rolesDAOImpl = new RolesDAOImpl();
    private final UsersDAOImpl usersDAOImpl = new UsersDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Roles> roles = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            roles = rolesDAOImpl.getRoles(1, 10);
        } catch (Exception ex) {
            DBConnection.rollback(conn);
        } finally {
            DBConnection.closeConnect(conn);
        }

        request.setAttribute("roles", roles);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role_id = request.getParameter("role_id");
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            Roles role = rolesDAOImpl.getRole(Integer.parseInt(role_id));
            Users user = new Users();
            user.setUsername(username);
            user.setPassword(password);
            user.setRoles(Arrays.asList(role));
            user.setEmail(email);
            
            usersDAOImpl.createUser(user);
            
            request.setAttribute("user", user);
            
            List<Roles> roles = rolesDAOImpl.getRoles(1, 10);
            request.setAttribute("roles", roles);
        } catch (Exception ex) {
            DBConnection.rollback(conn);
            request.setAttribute("errorMessage", "HIHI abcd");
        } finally {
            DBConnection.closeConnect(conn);
        }
        
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
