/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Role;

/**
 *
 * @author nguyenvanan
 */
public interface RoleDAO {

    List<Role> findAll();

    Role findById(int id);
}
