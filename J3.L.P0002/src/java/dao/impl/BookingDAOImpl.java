/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import common.Constant;
import dao.BookingDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Booking;
import model.Flight;
import utils.DBConnection;
import utils.Tool;

/**
 *
 * @author nguyenvanan
 */
public class BookingDAOImpl implements BookingDAO {

    private Connection conn;

    private final FlightDAOImpl flightDAOImpl = new FlightDAOImpl();

    @Override
    public List<Booking> findByUser(int page, int limit, int user_id) {
        List<Booking> bookings = new ArrayList<>();
        try {
            this.conn = DBConnection.getConnection();
            String sql = "WITH Ordered AS(SELECT *, ROW_NUMBER() OVER (ORDER BY id) AS RowNumber FROM Booking b WHERE b.user_id=?) "
                    + "SELECT * FROM Ordered WHERE RowNumber BETWEEN ? AND ?;";
            String mysql = "SELECT * FROM Booking b WHERE b.user_id=? ORDER BY b.id LIMIT ? OFFSET ?;";

            PreparedStatement pstm = null;
            switch (Constant.DATABASE) {
                case "MYSQL": {
                    pstm = this.conn.prepareStatement(mysql);
                    int pageRequest = ((page - 1) * limit);
                    pstm.setInt(2, limit);
                    pstm.setInt(3, pageRequest);
                }
                break;
                default: {
                    pstm = this.conn.prepareStatement(sql);
                    int pageRequest = ((page - 1) * limit) + 1;
                    pstm.setInt(2, pageRequest);
                    pstm.setInt(3, pageRequest + limit - 1);
                }
            }
            pstm.setInt(1, user_id);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String reservationCode = rs.getString("reservation_code");
                LocalDateTime ticketIssueDate = Tool.convertToLocalDatetimeViaInstant(rs.getTimestamp("ticket_issue_date"));
                int flightId = rs.getInt("flight_id");

                Flight flight = flightDAOImpl.findById(flightId);
                Booking booking = new Booking();
                booking.setId(id);
                booking.setReservationCode(reservationCode);
                booking.setTicketIssueDate(ticketIssueDate);
                if (flight != null) {
                    booking.setFlight(flight);
                }
                bookings.add(booking);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return bookings;
    }

    @Override
    public Booking findByCode(String code) {
        try {
            this.conn = DBConnection.getConnection();
            String sql = "SELECT * FROM Booking b WHERE b.reservation_code = ?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, code);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String reservationCode = rs.getString("reservation_code");
                LocalDateTime ticketIssueDate = Tool.convertToLocalDatetimeViaInstant(rs.getTimestamp("ticket_issue_date"));
                int flightId = rs.getInt("flight_id");

                Flight flight = flightDAOImpl.findById(flightId);
                Booking booking = new Booking();
                booking.setId(id);
                booking.setReservationCode(reservationCode);
                booking.setTicketIssueDate(ticketIssueDate);
                if (flight != null) {
                    booking.setFlight(flight);
                }
                return booking;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return null;
    }

    @Override
    public int saveInUser(int user_id, Booking booking) {
        try {
            this.conn = DBConnection.getConnection();
            String sql = "INSERT INTO Booking(reservation_code, ticket_issue_date, flight_id, user_id) VALUES(?,?,?,?)";
            PreparedStatement pstm = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int executeUpdate = pstm.executeUpdate();

            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                return id;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return -1;
    }

}
