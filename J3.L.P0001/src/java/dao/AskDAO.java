/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Ask;

/**
 *
 * @author nguyenvanan
 */
public interface AskDAO {

    List<Ask> findByQuiz(int page, int limit, int quiz_id);

    void saveInQuiz(Ask ask, int quiz_id);

    void update(Ask ask);
}
