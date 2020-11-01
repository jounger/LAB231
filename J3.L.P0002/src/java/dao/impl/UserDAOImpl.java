/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;
import utils.DBConnection;
import dao.UserDAO;
import java.sql.Statement;

/**
 *
 * @author nguyenvanan
 */
public class UserDAOImpl implements UserDAO {

    private Connection conn;

    @Override
    public User findByEmail(String user_email) {
        try {
            this.conn = DBConnection.getConnection();
            String sql = "SELECT * FROM User u WHERE u.email=?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, user_email);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int sex = rs.getInt("sex");
                int age = rs.getInt("age");
                String cardNumber = rs.getString("card_number");

                User user = new User(id, email, password, firstname, lastname, address, phone, sex, age, cardNumber);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return null;
    }

    @Override
    public int save(User user) {
        try {
            this.conn = DBConnection.getConnection();
            String sql = "INSERT INTO User(email, password, firstname, lastname, address, phone, sex, age, card_number) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstm = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, user.getEmail());
            pstm.setString(2, user.getPassword());
            pstm.setString(3, user.getFirstname());
            pstm.setString(4, user.getLastname());
            pstm.setString(5, user.getAddress());
            pstm.setString(6, user.getPhone());
            pstm.setInt(7, user.getSex());
            pstm.setInt(8, user.getAge());
            pstm.setString(9, user.getCardNumber());

            int executeUpdate = pstm.executeUpdate();

            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                return id;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return -1;
    }

}
