/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.impl.UserDAOImpl;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.User;
import utils.DBConnection;
import service.UserService;

/**
 *
 * @author nguyenvanan
 */
public class UserServiceImpl implements UserService {

    Connection conn;
    private final UserDAOImpl userDAOImpl = new UserDAOImpl();

    @Override
    public List<User> getUsers(int page, int limit) {
        List<User> users = new ArrayList<>();
        try {
            this.conn = DBConnection.getConnection();
            users = userDAOImpl.find(1, 10);
        } catch (Exception ex) {
        } finally {
            DBConnection.closeConnect(conn);
        }
        return users;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        try {
            this.conn = DBConnection.getConnection();
            user = userDAOImpl.findByUsername(username);
        } catch (Exception ex) {
        } finally {
            DBConnection.closeConnect(conn);
        }
        return user;
    }

    @Override
    public void createUser(User user) {
        try {
            this.conn = DBConnection.getConnection();
            userDAOImpl.save(user);
        } catch (Exception ex) {
        } finally {
            DBConnection.closeConnect(conn);
        }
    }

}
