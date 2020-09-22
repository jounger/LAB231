/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.UsersDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Roles;
import model.Users;
import utils.DBConnection;

/**
 *
 * @author nguyenvanan
 */
public class UsersDAOImpl implements UsersDAO {

    private final Connection conn;

    private final RolesDAOImpl rolesDAOImpl = new RolesDAOImpl();

    public UsersDAOImpl() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public List<Users> findUsers(int page, int limit) {
        List<Users> users = new ArrayList<>();
        try {
            String sql = "SELECT u.id, u.username, u.email, u.role_id FROM Users u ORDER BY u.id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, (page - 1) * limit);
            pstm.setInt(2, limit);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("username");
                int role_id = rs.getInt("role_id");

                Users user = new Users();
                user.setId(id);
                user.setUsername(username);
                user.setEmail(email);

                Roles role = rolesDAOImpl.findRoleById(role_id);
                user.setRoles(Arrays.asList(role));
                users.add(user);
            }
        } catch (Exception e) {
        }
        return users;

    }

    @Override
    public Users findUserById(int user_id) {
        try {
            String sql = "SELECT u.id, u.username, u.email, u.role_id FROM Users u WHERE u.id=?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, user_id);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("username");
                int role_id = rs.getInt("role_id");

                Users user = new Users();
                user.setId(id);
                user.setUsername(username);
                user.setEmail(email);

                Roles role = rolesDAOImpl.findRoleById(role_id);
                user.setRoles(Arrays.asList(role));
                return user;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Users findUserByUsername(String user_username) {
        try {
            String sql = "SELECT u.id, u.username, u.password, u.email, u.role_id FROM Users u WHERE u.username=?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, user_username);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("username");
                int role_id = rs.getInt("role_id");

                Users user = new Users();
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);

                Roles role = rolesDAOImpl.findRoleById(role_id);
                user.setRoles(Arrays.asList(role));
                return user;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public void saveUser(Users user) {
        try {
            String sql = "INSERT INTO Users(username, password, email, role_id) VALUES(?,?,?,?)";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getPassword());
            pstm.setString(3, user.getEmail());
            pstm.setInt(4, user.getRoles().get(0).getId());
            pstm.executeQuery();
        } catch (Exception e) {

        }
    }

}
