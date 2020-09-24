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
import java.util.List;
import model.Role;
import utils.DBConnection;
import dao.RoleDAO;

/**
 *
 * @author nguyenvanan
 */
public class RoleDAOImpl implements RoleDAO {

    private final Connection conn;

    public RoleDAOImpl() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        try {
            String sql = "SELECT r.id, r.name FROM [Role] r";
            PreparedStatement pstm = this.conn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Role role = new Role();
                role.setId(id);
                role.setName(name);
                roles.add(role);
            }
        } catch (Exception e) {

        }
        return roles;
    }

    @Override
    public Role findById(int role_id) {
        try {
            String sql = "SELECT r.id, r.name FROM [Role] r WHERE r.id=?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, role_id);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Role role = new Role();
                role.setId(id);
                role.setName(name);
                return role;
            }
        } catch (Exception e) {
        }
        return null;
    }
}
