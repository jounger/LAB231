/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnection;
import dao.CategoryDAO;
import model.Category;

/**
 *
 * @author nguyenvanan
 */
public class CategoryDAOImpl implements CategoryDAO {
    
    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        try {
            String sql = "SELECT * FROM Category;";

            PreparedStatement pstm = conn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Category category = new Category(id, name);
                list.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return list;
    }

    @Override
    public Category findById(int category_id) {
        Connection conn = DBConnection.getConnection();
        try {
            String sql = "SELECT * FROM Category WHERE id=?;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, category_id);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Category category = new Category(id, name);
                return category;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return null;
    }

}
