/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Users;

/**
 *
 * @author nguyenvanan
 */
public interface UsersService {

    List<Users> getUsers(int page, int limit);

    Users getUserByUsername(String username);

    void createUser(Users user);
}
