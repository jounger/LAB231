/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.impl.QuizDAOImpl;
import model.Quiz;
import service.QuizService;

/**
 *
 * @author nguyenvanan
 */
public class QuizServiceImpl implements QuizService {

    private final QuizDAOImpl quizDAOImpl = new QuizDAOImpl();

    @Override
    public void createQuiz(Quiz quiz) {
        quizDAOImpl.save(quiz);
    }

}
