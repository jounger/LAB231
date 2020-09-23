/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.User;

/**
 *
 * @author nguyenvanan
 */
public interface UserService {

    List<User> getUsers(int page, int limit);

    User getUserByUsername(String username);

    void createUser(User user);
}
