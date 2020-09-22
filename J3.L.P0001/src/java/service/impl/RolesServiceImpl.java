/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.impl.RolesDAOImpl;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.Roles;
import service.RolesService;
import utils.DBConnection;

/**
 *
 * @author nguyenvanan
 */
public class RolesServiceImpl implements RolesService {

    Connection conn;
    private final RolesDAOImpl rolesDAOImpl = new RolesDAOImpl();

    @Override
    public List<Roles> getRoles(int page, int limit) {
        List<Roles> roles = new ArrayList<>();
        try {
            this.conn = DBConnection.getConnection();
            roles = rolesDAOImpl.findRoles(page, limit);
        } catch (Exception ex) {
        } finally {
            DBConnection.closeConnect(conn);
        }
        return roles;
    }

    @Override
    public Roles getRoleById(int id) {
        Roles role = null;
        try {
            this.conn = DBConnection.getConnection();
            role = rolesDAOImpl.findRoleById(id);
        } catch (Exception ex) {
        } finally {
            DBConnection.closeConnect(conn);
        }
        return role;
    }

}
