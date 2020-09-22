/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.impl.UsersDAOImpl;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.Users;
import service.UsersService;
import utils.DBConnection;

/**
 *
 * @author nguyenvanan
 */
public class UsersServiceImpl implements UsersService {

    Connection conn;
    private final UsersDAOImpl usersDAOImpl = new UsersDAOImpl();

    @Override
    public List<Users> getUsers(int page, int limit) {
        List<Users> users = new ArrayList<>();
        try {
            this.conn = DBConnection.getConnection();
            users = usersDAOImpl.findUsers(1, 10);
        } catch (Exception ex) {
        } finally {
            DBConnection.closeConnect(conn);
        }
        return users;
    }

    @Override
    public Users getUserByUsername(String username) {
        Users user = null;
        try {
            this.conn = DBConnection.getConnection();
            user = usersDAOImpl.findUserByUsername(username);
        } catch (Exception ex) {
        } finally {
            DBConnection.closeConnect(conn);
        }
        return user;
    }

    @Override
    public void createUser(Users user) {
        try {
            this.conn = DBConnection.getConnection();
            usersDAOImpl.saveUser(user);
        } catch (Exception ex) {
        } finally {
            DBConnection.closeConnect(conn);
        }
    }

}
