/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Quiz;

/**
 *
 * @author nguyenvanan
 */
public interface QuizDAO {

    Quiz findById(int id);

    Quiz findCurrentQuiz();

    int save(Quiz quiz);
}
