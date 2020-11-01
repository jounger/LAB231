/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import common.Constant;
import dao.FlightDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Flight;
import utils.DBConnection;
import utils.Tool;

/**
 *
 * @author nguyenvanan
 */
public class FlightDAOImpl implements FlightDAO {

    private Connection conn;

    @Override
    public List<Flight> findByRouteAndDate(int page, int limit, String flight_from, String flight_to, String flight_time) {
        List<Flight> flights = new ArrayList<>();
        try {
            this.conn = DBConnection.getConnection();
            String sql = "WITH Ordered AS(SELECT *, ROW_NUMBER() OVER (ORDER BY id) AS RowNumber FROM Flight f WHERE f.from=? AND f.to=? AND f.departure_time>=?) "
                    + "SELECT *, DATEADD(mi, flight_detail*60, departure_time) AS arrival_time FROM Ordered WHERE RowNumber BETWEEN ? AND ?;";
            String mysql = "SELECT *, ADDTIME(departure_time, flight_detail*60*60) AS arrival_time FROM Flight f WHERE f.from=? AND f.to=? AND f.departure_time>=? ORDER BY f.id LIMIT ? OFFSET ?;";

            PreparedStatement pstm = null;
            switch (Constant.DATABASE) {
                case "MYSQL": {
                    pstm = this.conn.prepareStatement(mysql);
                    int pageRequest = ((page - 1) * limit);
                    pstm.setInt(4, limit);
                    pstm.setInt(5, pageRequest);
                }
                break;
                default:{
                    pstm = this.conn.prepareStatement(sql);
                    int pageRequest = ((page - 1) * limit) + 1;
                    pstm.setInt(4, pageRequest);
                    pstm.setInt(5, pageRequest + limit - 1);
                }
            }
            pstm.setString(1, flight_from);
            pstm.setString(2, flight_to);
            pstm.setString(3, flight_time);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String from = rs.getString("from");
                String to = rs.getString("to");
                LocalDateTime depatureTime = Tool.convertToLocalDatetimeViaInstant(rs.getTimestamp("departure_time"));
                LocalDateTime arrivalTime = Tool.convertToLocalDatetimeViaTimestamp(rs.getTimestamp("arrival_time"));
                double flightDetail = rs.getDouble("flight_detail");
                double price = rs.getDouble("price");

                Flight flight = new Flight(id, from, to, depatureTime, flightDetail, price);
                flight.setArrivalTime(arrivalTime);

                flights.add(flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return flights;
    }

    @Override
    public Flight findById(int flight_id) {
        try {
            this.conn = DBConnection.getConnection();
            String sql = "SELECT *, DATEADD(mi, f.flight_detail*60, f.departure_time) AS arrival_time FROM Flight f WHERE f.id=?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, flight_id);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String from = rs.getString("from");
                String to = rs.getString("to");
                LocalDateTime depatureTime = Tool.convertToLocalDatetimeViaInstant(rs.getTimestamp("depature_time"));
                LocalDateTime arrivalTime = Tool.convertToLocalDatetimeViaTimestamp(rs.getTimestamp("arrival_time"));
                double flightDetail = rs.getDouble("flight_detail");
                double price = rs.getDouble("price");

                Flight flight = new Flight(id, from, to, depatureTime, flightDetail, price);
                flight.setArrivalTime(arrivalTime);

                return flight;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return null;
    }

    @Override
    public List<String> findAllDeparture() {
        List<String> list = new ArrayList<>();
        try {
            this.conn = DBConnection.getConnection();
            String sql = "SELECT DISTINCT f.from FROM Flight f;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String from = rs.getString("from");
                list.add(from);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return list;
    }

    @Override
    public List<String> findAllArrival() {
        List<String> list = new ArrayList<>();
        try {
            this.conn = DBConnection.getConnection();
            String sql = "SELECT DISTINCT f.to FROM Flight f;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String to = rs.getString("to");
                list.add(to);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return list;
    }

}
