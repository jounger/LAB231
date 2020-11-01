/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Constant;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import dao.impl.UserDAOImpl;
import utils.Tool;

/**
 *
 * @author nguyenvanan
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    private final UserDAOImpl usersDAOImpl = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String cardNumber = request.getParameter("card-number");
        
        request.setAttribute("email", email);
        request.setAttribute("password", password);
        request.setAttribute("firstname", firstname);
        request.setAttribute("lastname", lastname);
        request.setAttribute("address", address);
        request.setAttribute("phone", phone);
        request.setAttribute("sex", sex);
        request.setAttribute("age", age);
        request.setAttribute("cardNumber", cardNumber);

        if (!Tool.isNull(email, password, firstname, lastname, address, phone, sex, age, cardNumber)) {
            User isExist = usersDAOImpl.findByEmail(email);
            if(isExist != null) {
                request.setAttribute(Constant.ERROR_MESSAGE_ATTR, "User has been existed");
            } else {
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setFirstname(firstname);
                user.setLastname(lastname);
                user.setAddress(address);
                user.setPhone(phone);
                user.setSex(Tool.toInteger(sex, 0));
                user.setAge(Tool.toInteger(age, 0));
                user.setCardNumber(cardNumber);

                int id = usersDAOImpl.save(user);
                if (id != -1) {
                    request.setAttribute(Constant.SUCCESS_MESSAGE_ATTR, "Successful register new account");
                }
            }
        } else {
            request.setAttribute(Constant.ERROR_MESSAGE_ATTR, "You must fulfill all the field");
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
    }

}
