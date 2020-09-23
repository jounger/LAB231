/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.impl.RoleDAOImpl;
import java.util.ArrayList;
import java.util.List;
import model.Role;
import service.RoleService;

/**
 *
 * @author nguyenvanan
 */
public class RoleServiceImpl implements RoleService {

    private final RoleDAOImpl roleDAOImpl = new RoleDAOImpl();

    @Override
    public List<Role> getRoles(int page, int limit) {
        List<Role> roles = new ArrayList<>();
        roles = roleDAOImpl.find(page, limit);
        return roles;
    }

    @Override
    public Role getRoleById(int id) {
        return roleDAOImpl.findById(id);
    }

}
