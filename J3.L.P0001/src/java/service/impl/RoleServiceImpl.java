/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.impl.RoleDAOImpl;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.Role;
import utils.DBConnection;
import service.RoleService;

/**
 *
 * @author nguyenvanan
 */
public class RoleServiceImpl implements RoleService {

    Connection conn;
    private final RoleDAOImpl roleDAOImpl = new RoleDAOImpl();

    @Override
    public List<Role> getRoles(int page, int limit) {
        List<Role> roles = new ArrayList<>();
        try {
            this.conn = DBConnection.getConnection();
            roles = roleDAOImpl.find(page, limit);
        } catch (Exception ex) {
        } finally {
            DBConnection.closeConnect(conn);
        }
        return roles;
    }

    @Override
    public Role getRoleById(int id) {
        Role role = null;
        try {
            this.conn = DBConnection.getConnection();
            role = roleDAOImpl.findById(id);
        } catch (Exception ex) {
        } finally {
            DBConnection.closeConnect(conn);
        }
        return role;
    }

}
