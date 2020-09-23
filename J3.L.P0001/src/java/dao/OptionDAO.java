/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Option;

/**
 *
 * @author nguyenvanan
 */
public interface OptionDAO {

    List<Option> findAllByQuestion(int question_id);
    
    Option findById(int id);

    void saveInQuestion(Option answer, int question_id);
}
