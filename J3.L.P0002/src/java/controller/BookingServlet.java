/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.impl.FlightDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Flight;
import utils.Tool;

/**
 *
 * @author nguyenvanan
 */
@WebServlet(name = "BookingServlet", urlPatterns = {"/booking"})
public class BookingServlet extends HttpServlet {

    private final FlightDAOImpl flightDAOImpl = new FlightDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fromPlace = request.getParameter("fromPlace");
        String toPlace = request.getParameter("toPlace");
        String departureDate = request.getParameter("departureDate");
        String returnDate = request.getParameter("returnDate");

        request.setAttribute("fromPlace", fromPlace);
        request.setAttribute("toPlace", toPlace);
        request.setAttribute("departureDate", departureDate);
        request.setAttribute("returnDate", returnDate);

        if (!Tool.isNull(fromPlace, toPlace, departureDate)) {
            List<Flight> departingFlights = flightDAOImpl.findByRouteAndDate(1, 10, fromPlace, toPlace, departureDate);
            request.setAttribute("departingFlights", departingFlights);
            if (!Tool.isNull(returnDate)) {
                List<Flight> returnFlights = flightDAOImpl.findByRouteAndDate(1, 10, toPlace, fromPlace, returnDate);
                request.setAttribute("returnFlights", returnFlights);
            }
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/booking.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
