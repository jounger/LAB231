/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.impl.BookingDAOImpl;
import java.io.IOException;
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
        if (!Tool.isNull(searchType)) {
            if (!Tool.isNull(reservationCode)) {
                Booking booking = bookingDAOImpl.findByCode(reservationCode);
                request.setAttribute("booking", booking);
            } else {
                User user = SecurityStore.getAuth(request.getSession());
                List<Booking> bookings = bookingDAOImpl.findByUser(1, 10, user.getId());
            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/manage-booking.jsp").forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/search-booking.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
