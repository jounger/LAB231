/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Question;

/**
 *
 * @author nguyenvanan
 */
public interface QuestionService {

    List<Question> getQuestions(int page, int limit);

    Question getQuestionById(int id);

    void createQuestion(Question question);
}
