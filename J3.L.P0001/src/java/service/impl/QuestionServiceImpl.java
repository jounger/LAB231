/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.impl.QuestionDAOImpl;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.Question;
import model.Question;
import service.QuestionService;
import utils.DBConnection;

/**
 *
 * @author nguyenvanan
 */
public class QuestionServiceImpl implements QuestionService {

    Connection conn;
    private final QuestionDAOImpl questionDAOImpl = new QuestionDAOImpl();

    @Override
    public List<Question> getQuestions(int page, int limit) {
        List<Question> questions = new ArrayList<>();
        try {
            this.conn = DBConnection.getConnection();
            questions = questionDAOImpl.find(page, limit);
        } catch (Exception ex) {
        } finally {
            DBConnection.closeConnect(conn);
        }
        return questions;
    }

    @Override
    public Question getQuestionById(int id) {
        Question question = null;
        try {
            this.conn = DBConnection.getConnection();
            question = questionDAOImpl.findById(id);
            return question;
        } catch (Exception ex) {
        } finally {
            DBConnection.closeConnect(conn);
        }
        return question;
    }

    @Override
    public void createQuestion(Question question) {
        try {
            this.conn = DBConnection.getConnection();
            questionDAOImpl.save(question);
        } catch (Exception ex) {
        } finally {
            DBConnection.closeConnect(conn);
        }
    }

}
