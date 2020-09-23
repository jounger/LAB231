/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Role;

/**
 *
 * @author nguyenvanan
 */
public interface RoleService {

    List<Role> getRoles(int page, int limit);

    Role getRoleById(int id);
}
