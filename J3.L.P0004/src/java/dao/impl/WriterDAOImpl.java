/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.WriterDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Writer;
import utils.DBConnection;

/**
 *
 * @author nguyenvanan
 */
public class WriterDAOImpl implements WriterDAO {

    private Connection conn;

    @Override
    public Writer findById(int user_id) {
        try {
            this.conn = DBConnection.getConnection();
            String sql = "SELECT * FROM User u WHERE u.id=?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, user_id);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String fullname = rs.getString("fullname");

                Writer writer = new Writer(id, fullname);
                return writer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return null;
    }

}
