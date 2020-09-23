/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Option;
import utils.DBConnection;
import dao.OptionDAO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nguyenvanan
 */
public class OptionDAOImpl implements OptionDAO {

    private final Connection conn;

    public OptionDAOImpl() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void saveInQuestion(Option answer, int question_id) {
        try {
            String sql = "INSERT INTO [Option](content, is_correct, question_id) VALUES(?,?,?)";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, answer.getContent());
            pstm.setBoolean(2, answer.isCorrect());
            pstm.setInt(3, question_id);
            int executeUpdate = pstm.executeUpdate();
        } catch (Exception e) {

        }
    }

    @Override
    public List<Option> findAllByQuestion(int question_id) {
        List<Option> options = new ArrayList<>();
        try {
            String sql = "SELECT o.id, o.content, o.is_correct FROM [Option] o WHERE o.question_id=?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, question_id);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String content = rs.getString("content");
                boolean correct = rs.getBoolean("is_correct");
                Option option = new Option();
                option.setId(id);
                option.setContent(content);
                option.setCorrect(correct);
            }
        } catch (Exception e) {
        }
        return options;
    }

    @Override
    public Option findById(int option_id) {
        try {
            String sql = "SELECT o.id, o.content, o.is_correct FROM [Option] o WHERE o.id=?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, option_id);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String content = rs.getString("content");
                boolean correct = rs.getBoolean("is_correct");
                Option option = new Option();
                option.setId(id);
                option.setContent(content);
                option.setCorrect(correct);
                return option;
            }
        } catch (Exception e) {
        }
        return null;
    }

}
