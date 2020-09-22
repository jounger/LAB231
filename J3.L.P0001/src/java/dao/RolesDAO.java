/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Roles;

/**
 *
 * @author nguyenvanan
 */
public interface RolesDAO {
    List<Roles> getRoles(int page, int limit);
    
    Roles getRole(int id);
}
