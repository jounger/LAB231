/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author nguyenvanan
 */
public class Booking {

    private int id;
    private String reservationCode;
    private LocalDateTime ticketIssueDate;
    private Flight flight;
    private User user;

    public Booking() {
    }

    public Booking(int id, String reservationCode, LocalDateTime ticketIssueDate, Flight flight, User user) {
        this.id = id;
        this.reservationCode = reservationCode;
        this.ticketIssueDate = ticketIssueDate;
        this.flight = flight;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public LocalDateTime getTicketIssueDate() {
        return ticketIssueDate;
    }

    public void setTicketIssueDate(LocalDateTime ticketIssueDate) {
        this.ticketIssueDate = ticketIssueDate;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
