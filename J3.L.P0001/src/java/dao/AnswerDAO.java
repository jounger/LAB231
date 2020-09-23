/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Answer;

/**
 *
 * @author nguyenvanan
 */
public interface AnswerDAO {

    void saveInQuestion(Answer answer, int question_id);
}
