/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Constant;
import dao.impl.CategoryDAOImpl;
import dao.impl.ContactDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Contact;
import utils.Tool;

/**
 *
 * @author nguyenvanan
 */
@WebServlet(name = "ContactServlet", urlPatterns = {"/contact"})
public class ContactServlet extends HttpServlet {

   private final ContactDAOImpl contactDAOImpl = new ContactDAOImpl();
   private final CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Show in sidebar
        List<Category> categories = categoryDAOImpl.findAll();
        request.setAttribute("categories", categories);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/contact.jsp").forward(request, response);

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String company = request.getParameter("company");
        String message = request.getParameter("message");
        
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        request.setAttribute("company", company);
        request.setAttribute("message", message);
        
        if(!Tool.isNull(name, email, phone, company, message)) {
            Contact contact = new Contact();
            contact.setName(name);
            contact.setEmail(email);
            contact.setPhone(phone);
            contact.setCompany(company);
            contact.setMessage(message);
            contactDAOImpl.save(contact);
            request.setAttribute(Constant.SUCCESS_MESSAGE_ATTR, "Message has been sent");
        } else {
            request.setAttribute(Constant.ERROR_MESSAGE_ATTR, "You must fulfill all the field");
        }
        
        // Show in sidebar
        List<Category> categories = categoryDAOImpl.findAll();
        request.setAttribute("categories", categories);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/contact.jsp").forward(request, response);
    }
}
