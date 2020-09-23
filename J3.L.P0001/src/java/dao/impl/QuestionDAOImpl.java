/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.QuestionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Option;
import model.Question;
import model.User;
import utils.DBConnection;

/**
 *
 * @author nguyenvanan
 */
public class QuestionDAOImpl implements QuestionDAO {

    private final Connection conn;

    private final UserDAOImpl userDAOImpl = new UserDAOImpl();

    private final OptionDAOImpl optionDAOImpl = new OptionDAOImpl();

    public QuestionDAOImpl() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public List<Question> find(int page, int limit) {
        List<Question> questions = new ArrayList<>();
        try {
            String sql = "SELECT q.id, q.content, q.date_created, q.user_id FROM Question q "
                    + "ORDER BY q.id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, (page - 1) * limit);
            pstm.setInt(2, limit);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String content = rs.getString("content");
                Date dateCreated = rs.getDate("date_created");
                int userId = rs.getInt("user_id");
                Question question = new Question();
                question.setId(id);
                question.setContent(content);
                question.setDateCreated(dateCreated);
                User user = userDAOImpl.findById(userId);
                question.setUser(user);
                questions.add(question);
            }
        } catch (Exception e) {

        }
        return questions;
    }
    
    @Override
    public List<Question> findByRandom(int page, int limit) {
        List<Question> questions = new ArrayList<>();
        try {
            String sql = "SELECT q.id, q.content, q.date_created, q.user_id FROM Question q "
                    + "ORDER BY NEWID() OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, (page - 1) * limit);
            pstm.setInt(2, limit);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String content = rs.getString("content");
                Date dateCreated = rs.getDate("date_created");
                int userId = rs.getInt("user_id");
                Question question = new Question();
                question.setId(id);
                question.setContent(content);
                question.setDateCreated(dateCreated);
                User user = userDAOImpl.findById(userId);
                question.setUser(user);
                questions.add(question);
            }
        } catch (Exception e) {

        }
        return questions;
    }

    @Override
    public Question findById(int role_id) {
        try {
            String sql = "SELECT q.id, q.content, q.date_created, q.user_id FROM Question q WHERE q.id=?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, role_id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String content = rs.getString("content");
                Date dateCreated = rs.getDate("date_created");
                int userId = rs.getInt("user_id");
                Question question = new Question();
                question.setId(id);
                question.setContent(content);
                question.setDateCreated(dateCreated);
                User user = userDAOImpl.findById(userId);
                question.setUser(user);
                return question;
            }
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public void save(Question question) {
        try {
            String sql = "INSERT INTO Question(content, date_created, user_id) VALUES(?,GETDATE(),?)";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, question.getContent());
            pstm.setInt(2, question.getUser().getId());
            pstm.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = pstm.getGeneratedKeys();
            int questionId = rs.getInt(1);
            // SAVE question's options
            for (Option ans : question.getOptions()) {
                optionDAOImpl.saveInQuestion(ans, questionId);
            }
        } catch (Exception e) {

        }
    }

}
