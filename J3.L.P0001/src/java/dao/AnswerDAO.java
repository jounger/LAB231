/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Answer;

/**
 *
 * @author nguyenvanan
 */
public interface AnswerDAO {

    List<Answer> findAllByAsk(int ask_id);

    void saveInAsk(Answer answer, int ask_id);
}
