/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Article;
import utils.DBConnection;
import utils.Tool;
import dao.ArticleDAO;
import model.Category;

/**
 *
 * @author nguyenvanan
 */
public class ArticleDAOImpl implements ArticleDAO {
    
    private final CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();
    
    @Override
    public List<Article> find(int page, int limit) {
        List<Article> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        try {
            String sql = "WITH Ordered AS(SELECT *, ROW_NUMBER() OVER (ORDER BY id) AS RowNumber FROM Article) "
                    + "SELECT * FROM Ordered WHERE RowNumber BETWEEN ? AND ?;";

            PreparedStatement pstm = conn.prepareStatement(sql);
            
            int pageRequest = ((page - 1) * limit) + 1;
            pstm.setInt(1, pageRequest);
            pstm.setInt(2, pageRequest + limit - 1);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String image = rs.getString("image");
                String introContent = rs.getString("intro_content");
                String content = rs.getString("content");
                LocalDateTime publishedDate = Tool.convertToLocalDatetimeViaInstant(rs.getTimestamp("published_date"));
                int categoryId = rs.getInt("category_id");
                Category category = categoryDAOImpl.findById(categoryId);
                Article article = new Article(id, title, image, introContent, content, category, publishedDate);
                list.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return list;
    }

    @Override
    public List<Article> findByCategory(int page, int limit, int category_id) {
        List<Article> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        try {
            String sql = "WITH Ordered AS(SELECT *, ROW_NUMBER() OVER (ORDER BY id) AS RowNumber FROM Article WHERE category_id=?) "
                    + "SELECT * FROM Ordered WHERE RowNumber BETWEEN ? AND ?;";

            PreparedStatement pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, category_id);
            int pageRequest = ((page - 1) * limit) + 1;
            pstm.setInt(2, pageRequest);
            pstm.setInt(3, pageRequest + limit - 1);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String image = rs.getString("image");
                String introContent = rs.getString("intro_content");
                String content = rs.getString("content");
                LocalDateTime publishedDate = Tool.convertToLocalDatetimeViaInstant(rs.getTimestamp("published_date"));
                int categoryId = rs.getInt("category_id");
                Category category = categoryDAOImpl.findById(categoryId);
                Article article = new Article(id, title, image, introContent, content, category, publishedDate);
                list.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return list;
    }
    
    @Override
    public int countByCategory(int category_id) {
        Connection conn = DBConnection.getConnection();
        try {
            String sql = "SELECT count(a.id) as total_count FROM Article a WHERE a.category_id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, category_id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int total = rs.getInt("total_count");
                return total;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return 0;
    }

    @Override
    public Article findById(int article_id) {
        Connection conn = DBConnection.getConnection();
        try {
            String sql = "SELECT * FROM Article WHERE id=?;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, article_id);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String image = rs.getString("image");
                String introContent = rs.getString("intro_content");
                String content = rs.getString("content");
                LocalDateTime publishedDate = Tool.convertToLocalDatetimeViaInstant(rs.getTimestamp("published_date"));
                int categoryId = rs.getInt("category_id");
                Category category = categoryDAOImpl.findById(categoryId);
                Article article = new Article(id, title, image, introContent, content, category, publishedDate);
                return article;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(conn);
        }
        return null;
    }

}
