/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.impl.UserDAOImpl;
import java.util.ArrayList;
import java.util.List;
import model.User;
import service.UserService;

/**
 *
 * @author nguyenvanan
 */
public class UserServiceImpl implements UserService {

    private final UserDAOImpl userDAOImpl = new UserDAOImpl();

    @Override
    public List<User> getUsers(int page, int limit) {
        List<User> users = new ArrayList<>();
        users = userDAOImpl.find(1, 10);
        return users;
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAOImpl.findByUsername(username);

    }

    @Override
    public void createUser(User user) {
        userDAOImpl.save(user);

    }

}
