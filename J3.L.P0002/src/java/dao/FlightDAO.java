/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Flight;

/**
 *
 * @author nguyenvanan
 */
public interface FlightDAO {

    List<Flight> findByRouteAndDate(int page, int limit, String from, String to, String time);

    Flight findById(int id);

    List<String> findAllDeparture();

    List<String> findAllArrival();
}
