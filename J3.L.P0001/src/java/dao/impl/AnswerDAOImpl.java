/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.AnswerDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Answer;
import model.Option;
import utils.DBConnection;

/**
 *
 * @author nguyenvanan
 */
public class AnswerDAOImpl implements AnswerDAO {

    private final Connection conn;

    private final OptionDAOImpl optionDAOImpl = new OptionDAOImpl();

    public AnswerDAOImpl() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public List<Answer> findAllByAsk(int ask_id) {
        List<Answer> answers = new ArrayList<>();
        try {
            String sql = "SELECT a.id, a.option_id, a.ask_id FROM Answer a WHERE a.ask_id=?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, ask_id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int optionId = rs.getInt("option_id");

                Answer ans = new Answer();
                ans.setId(id);
                Option option = optionDAOImpl.findById(optionId);
                ans.setOption(option);
                answers.add(ans);
            }
        } catch (Exception e) {
        }
        return answers;
    }

    @Override
    public void saveInAsk(Answer answer, int ask_id) {
        try {
            String sql = "INSERT INTO Answer(option_id, ask_id) VALUES(?, ?);";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, answer.getOption().getId());
            pstm.setInt(2, ask_id);
            int executeUpdate = pstm.executeUpdate();
        } catch (Exception e) {
        }
    }

}
