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
@WebServlet(name = "ErrorServlet", urlPatterns = {"/error"})
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status = request.getParameter("status");
        System.out.println("STATUS"+ status);
        String status_name = "";
        switch (status) {
            case "400":
                status_name = "Not Found";
                break;
            case "401":
                status_name = "Not Authentication";
                break;
            case "403":
                status_name = "Not Authorization";
                break;
            case "500":
                status_name = "Internal Error";
                break;
        }
        request.setAttribute("title", status_name);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);

    }
}
