/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

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
    public List<Flight> findByRouteAndDate(int page, int limit, String flight_from, String flight_to, String flight_depatureDate) {
        List<Flight> flights = new ArrayList<>();
        try {
            this.conn = DBConnection.getConnection();
            String sql = "WITH Ordered AS(SELECT *, ROW_NUMBER() OVER (ORDER BY id) AS RowNumber FROM [Flight] f WHERE f.from=? AND f.to=? AND f.depature_date>=?) "
                    + "SELECT * FROM Ordered WHERE RowNumber BETWEEN ? AND ?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, flight_from);
            pstm.setString(2, flight_to);
            pstm.setString(3, flight_depatureDate);
            int pageRequest = ((page - 1) * limit) + 1;
            pstm.setInt(4, pageRequest);
            pstm.setInt(5, pageRequest + limit - 1);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String from = rs.getString("from");
                String to = rs.getString("to");
                LocalDateTime depatureTime = Tool.convertToLocalDatetimeViaInstant(rs.getTimestamp("depature_time"));
                double flightDetail = rs.getDouble("flight_detail");
                double price = rs.getDouble("price");

                Flight flight = new Flight(id, from, to, depatureTime, flightDetail, price);
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
            String sql = "SELECT * FROM [Flight] f WHERE f.id=?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, flight_id);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String from = rs.getString("from");
                String to = rs.getString("to");
                LocalDateTime depatureTime = Tool.convertToLocalDatetimeViaInstant(rs.getTimestamp("depature_time"));
                double flightDetail = rs.getDouble("flight_detail");
                double price = rs.getDouble("price");

                Flight flight = new Flight(id, from, to, depatureTime, flightDetail, price);
                return flight;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return null;
    }

}
