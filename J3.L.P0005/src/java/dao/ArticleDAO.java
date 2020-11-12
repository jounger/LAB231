/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Article;

/**
 *
 * @author nguyenvanan
 */
public interface ArticleDAO {
    
    List<Article> find(int page, int limit);
    
    List<Article> findByCategory(int page, int limit, int categoryId);
    
    int countByCategory(int categoryId);

    Article findById(int id);
}
