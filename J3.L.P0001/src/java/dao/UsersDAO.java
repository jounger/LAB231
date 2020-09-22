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
    List<Users> getUsers(int page, int limit);
    
    Users getUser(int id);
    
    void createUser(Users user);
}
