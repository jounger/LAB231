/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.RolesDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Roles;
import utils.DBConnection;

/**
 *
 * @author nguyenvanan
 */
public class RolesDAOImpl implements RolesDAO {

    private final Connection conn;

    public RolesDAOImpl() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public List<Roles> getRoles(int page, int limit) {
        try {
            String sql = "SELECT r.id, r.name FROM Roles r ORDER BY r.id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, (page - 1) * limit);
            pstm.setInt(2, limit);

            List<Roles> roles = new ArrayList<>();
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Roles role = new Roles();
                role.setId(id);
                role.setName(name);
                roles.add(role);
            }
            return roles;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Roles getRole(int role_id) {
        try {
            String sql = "SELECT r.id, r.name FROM Roles r WHERE r.id=?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, role_id);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Roles role = new Roles();
                role.setId(id);
                role.setName(name);
                return role;
            }
        } catch (Exception e) {
        }
        return null;
    }
}
