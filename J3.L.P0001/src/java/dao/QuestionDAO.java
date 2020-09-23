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

    List<Question> find(int page, int limit);

    Question findById(int id);
    
    void save(Question question);
}
