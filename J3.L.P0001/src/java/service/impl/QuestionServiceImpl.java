/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.impl.QuestionDAOImpl;
import java.util.ArrayList;
import java.util.List;
import model.Question;
import service.QuestionService;

/**
 *
 * @author nguyenvanan
 */
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDAOImpl questionDAOImpl = new QuestionDAOImpl();

    @Override
    public List<Question> getQuestions(int page, int limit) {
        List<Question> questions = new ArrayList<>();
        questions = questionDAOImpl.find(page, limit);
        return questions;
    }
    
    @Override
    public List<Question> getRandomQuestions(int page, int limit) {
        List<Question> questions = new ArrayList<>();
        questions = questionDAOImpl.findByRandom(page, limit);
        return questions;
    }

    @Override
    public Question getQuestionById(int id) {
        return questionDAOImpl.findById(id);
    }

    @Override
    public void createQuestion(Question question) {
        questionDAOImpl.save(question);
    }

}
