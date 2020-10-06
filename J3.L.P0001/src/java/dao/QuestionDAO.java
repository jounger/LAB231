/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Question;

/**
 *
 * @author nguyenvanan
 */
public interface QuestionDAO {

    List<Question> findByUser(int page, int limit, int user_id);
    
    List<Question> findByRandom(int page, int limit);

    Question findById(int id);
    
    void save(Question question);
    
    int countByUser(int user_id);
}
