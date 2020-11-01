/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Constant;
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
        String flightType = request.getParameter("flight-type");
        String fromPlace = request.getParameter("fromPlace");
        String toPlace = request.getParameter("toPlace");
        String departureDate = request.getParameter("departureDate");
        String returnDate = request.getParameter("returnDate");
        String page = request.getParameter("page");

        request.setAttribute("fromPlace", fromPlace);
        request.setAttribute("toPlace", toPlace);
        request.setAttribute("departureDate", departureDate);
        request.setAttribute("returnDate", returnDate);

        int limitReq = 5;
        int pageReq = Tool.toInteger(page, 1);
        request.setAttribute("page", page);

        if (!Tool.isNull(fromPlace, toPlace, departureDate)) {
            switch (flightType) {
                case "round-trip":
                    if (!Tool.isNull(returnDate)) {
                        List<Flight> departingFlights = flightDAOImpl.findByRouteAndDate(pageReq, limitReq, fromPlace, toPlace, departureDate);
                        request.setAttribute("departingFlights", departingFlights);

                        List<Flight> returnFlights = flightDAOImpl.findByRouteAndDate(pageReq, limitReq, toPlace, fromPlace, returnDate);
                        request.setAttribute("returnFlights", returnFlights);
                    }

                    break;
                case "one-way":
                    List<Flight> departingFlights = flightDAOImpl.findByRouteAndDate(pageReq, limitReq, fromPlace, toPlace, departureDate);
                    request.setAttribute("departingFlights", departingFlights);
                    break;

            }
        } else {
            response.sendRedirect(this.getServletContext().getContextPath() + "/home");
            return;
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/booking.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
