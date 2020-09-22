/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Roles;

/**
 *
 * @author nguyenvanan
 */
public interface RolesService {

    List<Roles> getRoles(int page, int limit);

    Roles getRoleById(int id);
}
