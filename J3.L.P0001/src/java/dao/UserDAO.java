/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.User;

/**
 *
 * @author nguyenvanan
 */
public interface UserDAO {

    List<User> find(int page, int limit);

    User findById(int id);

    User findByUsername(String username);

    void save(User user);
}
