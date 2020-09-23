/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.AnswerDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Answer;
import utils.DBConnection;

/**
 *
 * @author nguyenvanan
 */
public class AnswerDAOImpl implements AnswerDAO {

    private final Connection conn;

    public AnswerDAOImpl() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void saveInQuestion(Answer answer, int question_id) {
        try {
            String sql = "INSERT INTO Answer(content, is_correct, question_id) VALUES(?,?,?)";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, answer.getContent());
            pstm.setBoolean(2, answer.isCorrect());
            pstm.setInt(3, question_id);
            int executeUpdate = pstm.executeUpdate();
        } catch (Exception e) {

        }
    }

}
