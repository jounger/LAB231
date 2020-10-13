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
public class Flight {

    private int id;
    private String from;
    private String to;

    private LocalDateTime departureTime;
    private double flightDetail;
    private double price;

    public Flight() {
    }

    public Flight(int id, String from, String to, LocalDateTime departureTime, double flightDetail, double price) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.departureTime = departureTime;
        this.flightDetail = flightDetail;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public double getFlightDetail() {
        return flightDetail;
    }

    public void setFlightDetail(double flightDetail) {
        this.flightDetail = flightDetail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
