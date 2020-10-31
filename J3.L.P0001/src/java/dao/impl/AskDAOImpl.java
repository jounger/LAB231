/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.AskDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Answer;
import model.Ask;
import model.Question;
import utils.DBConnection;

/**
 *
 * @author nguyenvanan
 */
public class AskDAOImpl implements AskDAO {

    private Connection conn;

    private final QuestionDAOImpl questionDAOImpl = new QuestionDAOImpl();
    private final AnswerDAOImpl answerDAOImpl = new AnswerDAOImpl();

    @Override
    public List<Ask> findByQuiz(int page, int limit, int quiz_id) {
        List<Ask> asks = new ArrayList<>();
        try {
            this.conn = DBConnection.getConnection();
            String sql = "WITH Ordered AS(SELECT *, ROW_NUMBER() OVER (ORDER BY id) AS RowNumber FROM [Ask] WHERE quiz_id=?) "
                    + "SELECT * FROM Ordered WHERE RowNumber BETWEEN ? AND ?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, quiz_id);
            pstm.setInt(2, (page - 1) * limit);
            pstm.setInt(3, limit);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Date dateAnswered = rs.getDate("date_answered");
                int questionId = rs.getInt("question_id");

                List<Answer> answers = answerDAOImpl.findAllByAsk(id);
                Question question = questionDAOImpl.findById(questionId);

                Ask ask = new Ask();
                ask.setId(id);
                ask.setQuestion(question);
                ask.setAnswers(answers);
                ask.setDateAnswered(dateAnswered);
                asks.add(ask);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return asks;
    }

    @Override
    public void saveInQuiz(Ask ask, int quiz_id) {
        try {
            this.conn = DBConnection.getConnection();
            String sql = "INSERT INTO Ask(question_id, date_answered, quiz_id) VALUES(?,GETDATE(),?);";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, ask.getQuestion().getId());
            pstm.setInt(2, quiz_id);
            int executeUpdate = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
    }

    @Override
    public void update(Ask ask) {
        try {
            this.conn = DBConnection.getConnection();
            String sql = "UPDATE Ask SET date_answered=GETDATE() WHERE id=?";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, ask.getId());
            int executeUpdate = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
    }

}
