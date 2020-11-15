/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import utils.DBConnection;
import dao.ContactDAO;
import model.Contact;

/**
 *
 * @author nguyenvanan
 */
public class ContactDAOImpl implements ContactDAO {

    @Override
    public void save(Contact contact) {
        Connection conn = DBConnection.getConnection();
        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO [Contact](name, email, phone, company, message) VALUES(?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, contact.getName());
            pstm.setString(2, contact.getEmail());
            pstm.setString(3, contact.getPhone());
            pstm.setString(4, contact.getCompany());
            pstm.setString(5, contact.getMessage());
            int executeUpdate = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
    }

}
