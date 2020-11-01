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
import utils.SecurityStore;
import utils.Tool;

/**
 *
 * @author nguyenvanan
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    private final FlightDAOImpl flightDAOImpl = new FlightDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get request
        String flightType = request.getParameter("flight-type");
        String fromPlace = request.getParameter("fromPlace");
        String toPlace = request.getParameter("toPlace");
        String departureDate = request.getParameter("departureDate");
        String returnDate = request.getParameter("returnDate");

        request.setAttribute("flightType", flightType);
        request.setAttribute("fromPlace", fromPlace);
        request.setAttribute("toPlace", toPlace);
        request.setAttribute("departureDate", departureDate);
        request.setAttribute("returnDate", returnDate);

        if (!Tool.isNull(fromPlace, toPlace, departureDate)) {
            switch (flightType) {
                case "round-trip":
                    if (!Tool.isNull(returnDate)) {
                        response.sendRedirect(this.getServletContext().getContextPath() + "/booking?flight-type="
                                + flightType + "&fromPlace=" + fromPlace + "&toPlace=" + toPlace + "&departureDate="
                                + departureDate + "&returnDate" + returnDate);
                        return;
                    } else {
                        request.setAttribute(Constant.ERROR_MESSAGE_ATTR, "Round trip require return date");
                    }

                    break;
                case "one-way": {
                    response.sendRedirect(this.getServletContext().getContextPath() + "/booking?flight-type="
                            + flightType + "&fromPlace=" + fromPlace + "&toPlace=" + toPlace + "&departureDate="
                            + departureDate);
                    return;
                }

            }
        } else {
            request.setAttribute(Constant.ERROR_MESSAGE_ATTR, "You must fulfill all the field");
        }

        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> fromPlaces = flightDAOImpl.findAllDeparture();
        List<String> toPlaces = flightDAOImpl.findAllArrival();

        request.setAttribute("fromPlaces", fromPlaces);
        request.setAttribute("toPlaces", toPlaces);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }

}
