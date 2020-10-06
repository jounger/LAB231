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
import utils.DBConnection;

/**
 *
 * @author nguyenvanan
 */
public class QuestionDAOImpl implements QuestionDAO {

    private Connection conn;

    private final OptionDAOImpl optionDAOImpl = new OptionDAOImpl();

    @Override
    public List<Question> findByUser(int page, int limit, int user_id) {
        List<Question> questions = new ArrayList<>();
        try {
            this.conn = DBConnection.getConnection();
            String sql = "WITH Ordered AS(SELECT *, ROW_NUMBER() OVER (ORDER BY id) AS RowNumber FROM [Question] WHERE user_id=?) "
                    + "SELECT * FROM Ordered WHERE RowNumber BETWEEN ? AND ?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, user_id);
            int pageRequest = ((page - 1) * limit) + 1;
            pstm.setInt(2, pageRequest);
            pstm.setInt(3, pageRequest + limit - 1);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String content = rs.getString("content");
                Date dateCreated = rs.getDate("date_created");

                List<Option> options = optionDAOImpl.findAllByQuestion(id);

                Question question = new Question();
                question.setId(id);
                question.setContent(content);
                question.setDateCreated(dateCreated);
                question.setOptions(options);
                questions.add(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return questions;
    }

    @Override
    public List<Question> findByRandom(int page, int limit) {
        List<Question> questions = new ArrayList<>();
        try {
            this.conn = DBConnection.getConnection();
            String sql = "WITH Ordered AS(SELECT *, ROW_NUMBER() OVER (ORDER BY NEWID()) AS RowNumber FROM [Question]) "
                    + "SELECT * FROM Ordered WHERE RowNumber BETWEEN ? AND ?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            int pageRequest = ((page - 1) * limit) + 1;
            pstm.setInt(1, pageRequest);
            pstm.setInt(2, pageRequest + limit - 1);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String content = rs.getString("content");
                Date dateCreated = rs.getDate("date_created");

                List<Option> options = optionDAOImpl.findAllByQuestion(id);

                Question question = new Question();
                question.setId(id);
                question.setContent(content);
                question.setDateCreated(dateCreated);
                question.setOptions(options);
                questions.add(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return questions;
    }

    @Override
    public Question findById(int question_id) {
        try {
            this.conn = DBConnection.getConnection();
            String sql = "SELECT q.id, q.content, q.date_created, q.user_id FROM Question q WHERE q.id=?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, question_id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String content = rs.getString("content");
                Date dateCreated = rs.getDate("date_created");

                List<Option> options = optionDAOImpl.findAllByQuestion(id);

                Question question = new Question();
                question.setId(id);
                question.setContent(content);
                question.setDateCreated(dateCreated);
                question.setOptions(options);
                return question;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return null;
    }

    @Override
    public void save(Question question) {
        try {
            this.conn = DBConnection.getConnection();
            String sql = "INSERT INTO Question(content, date_created, user_id) VALUES(?,GETDATE(),?)";
            PreparedStatement pstm = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, question.getContent());
            pstm.setInt(2, question.getUser().getId());
            int executeUpdate = pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                // SAVE question's options
                for (Option ans : question.getOptions()) {
                    optionDAOImpl.saveInQuestion(ans, (int) id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
    }

    @Override
    public int countByUser(int user_id) {
        try {
            this.conn = DBConnection.getConnection();
            String sql = "SELECT count(q.id) as total_count FROM Question q WHERE q.user_id=?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, user_id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int total = rs.getInt("total_count");
                return total;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return 0;
    }

}
