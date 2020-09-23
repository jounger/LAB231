/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.QuizDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import model.Ask;
import model.Quiz;
import utils.DBConnection;

/**
 *
 * @author nguyenvanan
 */
public class QuizDAOImpl implements QuizDAO {

    private final Connection conn;

    private final AskDAOImpl askDAOImpl = new AskDAOImpl();

    public QuizDAOImpl() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public Quiz findById(int quiz_id) {
        try {
            String sql = "SELECT q.id, q.quantity, q.date_started FROM Quiz q WHERE q.id=?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, quiz_id);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                int quantity = rs.getInt("quantity");
                Date dateStarted = rs.getDate("date_started");

                List<Ask> asks = askDAOImpl.findByQuiz(0, quantity, quiz_id);

                Quiz quiz = new Quiz();
                quiz.setId(id);
                quiz.setQuantity(quantity);
                quiz.setDateStarted(dateStarted);
                quiz.setAsks(asks);
                return quiz;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public void save(Quiz quiz) {
        try {
            String sql = "INSERT INTO Quiz(quantity, date_started, user_id) VALUES(?,GETDATE(),?)";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, quiz.getQuantity());
            pstm.setInt(2, quiz.getUser().getId());
            int executeUpdate = pstm.executeUpdate();
        } catch (Exception e) {
        }
    }

}
