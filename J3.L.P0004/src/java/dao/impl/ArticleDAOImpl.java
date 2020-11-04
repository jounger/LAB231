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
import model.Writer;

/**
 *
 * @author nguyenvanan
 */
public class ArticleDAOImpl implements ArticleDAO {

    private Connection conn;

    private final WriterDAOImpl writerDAOImpl = new WriterDAOImpl();

    @Override
    public List<Article> find(int page, int limit) {
        List<Article> list = new ArrayList<>();
        try {
            this.conn = DBConnection.getConnection();
            String sql = "WITH Ordered AS(SELECT *, ROW_NUMBER() OVER (ORDER BY id) AS RowNumber FROM Article) "
                    + "SELECT * FROM Ordered WHERE RowNumber BETWEEN ? AND ?;";

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            int pageRequest = ((page - 1) * limit) + 1;
            pstm.setInt(1, pageRequest);
            pstm.setInt(2, pageRequest + limit - 1);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String introContent = rs.getString("introContent");
                String content = rs.getString("content");
                LocalDateTime publishedDate = Tool.convertToLocalDatetimeViaInstant(rs.getTimestamp("published_date"));
                int user_id = rs.getInt("writer_id");
                Writer writer = writerDAOImpl.findById(user_id);
                Article article = new Article(id, title, introContent, content, writer, publishedDate);
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
    public Article findById(int flight_id) {
        try {
            this.conn = DBConnection.getConnection();
            String sql = "SELECT * FROM Article WHERE id=?;";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, flight_id);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String introContent = rs.getString("introContent");
                String content = rs.getString("content");
                LocalDateTime publishedDate = Tool.convertToLocalDatetimeViaInstant(rs.getTimestamp("published_date"));
                int user_id = rs.getInt("user_id");
                Writer writer = writerDAOImpl.findById(user_id);
                Article article = new Article(id, title, introContent, content, writer, publishedDate);
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
