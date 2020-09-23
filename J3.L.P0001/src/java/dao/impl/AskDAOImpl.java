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

    private final Connection conn;

    private final QuestionDAOImpl questionDAOImpl = new QuestionDAOImpl();
    private final AnswerDAOImpl answerDAOImpl = new AnswerDAOImpl();

    public AskDAOImpl() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public List<Ask> findByQuiz(int page, int limit, int quiz_id) {
        List<Ask> asks = new ArrayList<>();
        try {
            String sql = "SELECT a.id, a.question_id, a.date_answered, a.quiz_id FROM Ask a WHERE a.quiz_id=? "
                    + "ORDER BY a.id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
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
        }
        return asks;
    }

    @Override
    public void saveInQuiz(Ask ask, int quiz_id) {
        try {
            String sql = "INSERT INTO Ask(question_id, date_answered, quiz_id) VALUES(?,GETDATE(),?);";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, ask.getQuestion().getId());
            pstm.setInt(2, quiz_id);
            int executeUpdate = pstm.executeUpdate();
        } catch (Exception e) {
        }
    }

}