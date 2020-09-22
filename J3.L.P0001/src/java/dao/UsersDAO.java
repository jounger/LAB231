/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Users;

/**
 *
 * @author nguyenvanan
 */
public interface UsersDAO {
    List<Users> findUsers(int page, int limit);
    
    Users findUserById(int id);
    
    Users findUserByUsername(String username);
    
    void saveUser(Users user);
}
