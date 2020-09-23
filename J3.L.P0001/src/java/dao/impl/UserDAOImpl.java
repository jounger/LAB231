/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Role;
import model.User;
import utils.DBConnection;
import dao.UserDAO;

/**
 *
 * @author nguyenvanan
 */
public class UserDAOImpl implements UserDAO {

    private final Connection conn;

    private final RoleDAOImpl rolesDAOImpl = new RoleDAOImpl();

    public UserDAOImpl() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public List<User> find(int page, int limit) {
        List<User> users = new ArrayList<>();
        try {
            String sql = "SELECT u.id, u.username, u.email, u.role_id FROM [User] u ORDER BY u.id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, (page - 1) * limit);
            pstm.setInt(2, limit);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("username");
                int role_id = rs.getInt("role_id");

                User user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setEmail(email);

                Role role = rolesDAOImpl.findById(role_id);
                user.setRoles(Arrays.asList(role));
                users.add(user);
            }
        } catch (Exception e) {
        }
        return users;

    }

    @Override
    public User findById(int user_id) {
        try {
            String sql = "SELECT u.id, u.username, u.email, u.role_id FROM [User] u WHERE u.id=?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, user_id);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("username");
                int role_id = rs.getInt("role_id");

                User user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setEmail(email);

                Role role = rolesDAOImpl.findById(role_id);
                user.setRoles(Arrays.asList(role));
                return user;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public User findByUsername(String user_username) {
        try {
            String sql = "SELECT u.id, u.username, u.password, u.email, u.role_id FROM [User] u WHERE u.username=?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, user_username);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("username");
                int role_id = rs.getInt("role_id");

                User user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);

                Role role = rolesDAOImpl.findById(role_id);
                user.setRoles(Arrays.asList(role));
                return user;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public void save(User user) {
        try {
            String sql = "INSERT INTO [User](username, password, email, role_id) VALUES(?,?,?,?)";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getPassword());
            pstm.setString(3, user.getEmail());
            pstm.setInt(4, user.getRoles().get(0).getId());
            int executeUpdate = pstm.executeUpdate();
        } catch (Exception e) {
        }
    }

}
