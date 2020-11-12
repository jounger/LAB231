/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Category;

/**
 *
 * @author nguyenvanan
 */
public interface CategoryDAO {
    
    List<Category> findAll();

    Category findById(int id);
}
