/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.impl.BookingDAOImpl;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Booking;
import model.User;
import utils.SecurityStore;
import utils.Tool;

/**
 *
 * @author nguyenvanan
 */
@WebServlet(name = "ManageBookingServlet", urlPatterns = {"/manage-booking"})
public class ManageBookingServlet extends HttpServlet {

    private final BookingDAOImpl bookingDAOImpl = new BookingDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchType = request.getParameter("search-type");
        String reservationCode = request.getParameter("reservation-code");
        
        request.setAttribute("reservation-code", reservationCode);
        
        if (!Tool.isNull(searchType)) {
            switch (searchType) {
                case "reservation-code": {
                    Booking booking = bookingDAOImpl.findByCode(reservationCode);
                    request.setAttribute("bookings", Arrays.asList(booking));
                }
                break;
                default: {
                    String page = request.getParameter("page");
                    int pageReq = Tool.toInteger(page, 1);
                    int limitReq = 5;
                    request.setAttribute("page", page);
                    User user = SecurityStore.getAuth(request.getSession());
                    List<Booking> bookings = bookingDAOImpl.findByUser(pageReq, limitReq, user.getId());
                    request.setAttribute("bookings", bookings);
                }
                break;
            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/manage-booking.jsp").forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/search-booking.jsp").forward(request, response);
        }

    }
}
